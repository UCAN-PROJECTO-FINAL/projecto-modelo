/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geral.excel;

import ejbs.cache.GrlSexoCache;
import ejbs.entities.GrlSexo;
//import ejbs.facades.GrlUpdatesFacade;
import excel.utils.ExcelBeanAbstract;
import geral.beans.GrlSexoBean;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.Iterator;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFFormulaEvaluator;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import utils.DataUtils;
import utils.Defs;
import utils.ExcepcaoCarregamentoTabelasExcel;
import utils.FileManager;
import utils.HSSFCellUtilities;
import utils.mensagens.LogFile;
import utils.mensagens.Mensagem;

/**
 *
 * @author aires
 */
@Named(value = "grlSexoExcelBean")
@RequestScoped
public class GrlSexoExcelBean extends ExcelBeanAbstract implements Serializable
{

    @Resource
    private UserTransaction userTransaction;

    @Inject
    GrlSexoBean sexoBean;

    @Inject
    private GrlSexoCache grlSexoCache;
//
//    @EJB
//    private GrlUpdatesFacade grlUpdatesFacade;

    private Date dataSexoXLSFile;
    
    private DataFormatter objDefaultFormat;
    private FormulaEvaluator objFormulaEvaluator;

    /**
     * Creates a new instance of SexoExcelBean
     */
    public GrlSexoExcelBean()
    {
//        default_filename = Defs.CAMINHO_SEXOS;
    }

    @Override
    public void carregar()
    {
        try
        {
////LogFile.warnMsg(null, "0: GrlSexoExcelBean.carregar()");     
            try
            {
                this.userTransaction.begin();
            }
            catch (NotSupportedException | SystemException e)
            {
                LogFile.warnMsg(null, "Falha no estabelcecimento da demarcação da transacao associada a actualizacao do ficheiro excel de sexo");
            }
            if (carregarTabelas())
            {
                LogFile.sucessoMsg(null, "o carregamento/actualizacao do ficheiro excel de sexos foi realizado com sucesso");
            }
            try
            {
                this.userTransaction.commit();
            }
            catch (IllegalStateException | SecurityException | HeuristicMixedException | HeuristicRollbackException | RollbackException | SystemException e)
            {
                LogFile.warnMsg(null, "Falha no \"commit\" da actualizacao do ficheiro excel de sexo");
            }
////LogFile.warnMsg(null, "1: GrlSexoExcelBean.carregar()");                        
        }
        catch (ExcepcaoCarregamentoTabelasExcel e)
        {
//LogFile.warnMsg(null, "2: GrlSexoExcelBean.carregar()"); 
            try
            {
                this.userTransaction.rollback();
            }
            catch (IllegalStateException | SecurityException | SystemException ex)
            {
                LogFile.warnMsg(null, "Falha no \"rollback\" da tentativa de actualizacao do ficheiro excel de sexo");
            }
        }
    }

    @SuppressWarnings("empty-statement")
    @Override
    public boolean carregarTabelas() throws ExcepcaoCarregamentoTabelasExcel
    {
//LogFile.warnMsg(null, "0: GrlSexoExcelBean.carregarTabelas()");             
        if (lerTabela())
        {
//LogFile.warnMsg(null, "1: GrlSexoExcelBean.carregarTabelas()");            
//            this.grlUpdatesFacade.escreverDataActualizacaoSexoTabela(dataSexoXLSFile);
            this.sexoBean.init();
            return true;
        }
//LogFile.warnMsg(null, "2: GrlSexoExcelBean.carregarTabelas()");        
        return false;
    }

    /**
     *
     * @return @throws ExcepcaoCarregamentoTabelasExcel
     */
    @Override
    public boolean lerTabela() throws ExcepcaoCarregamentoTabelasExcel
    {
//LogFile.warnMsg(null, "0: GrlSexoExcelBean.lerTabela()");          
        int nreg = 0;
        try
        {

            this.filename = (this.filename == null) ? default_filename : filename;
            sheet = sheet != null ? sheet : FileManager.getSheetFromXLS_File(filename);
//LogFile.warnMsg(null, "1: GrlSexoExcelBean.lerTabela()");            
        }
        catch (FileNotFoundException e)
        {
//            LogFile.warnMsg(null, "Não foi possível encontrar o ficheiro \"" + Defs.CAMINHO_SEXOS + "\"");
            return false;
        }
        catch (IOException e)
        {
//            LogFile.warnMsg(null, "Não foi possível abrir o ficheiro \"" + Defs.CAMINHO_SEXOS + "\"");
            return false;
        }
        if (sheet == null)
        {
//            LogFile.warnMsg(null, "Não foi possível abrir o ficheiro \"" + Defs.CAMINHO_SEXOS + "\"");
            return false;
        }
//LogFile.warnMsg(null, "2: GrlSexoExcelBean.lerTabela()");        
        HSSFRow row;
        Iterator rows = sheet.rowIterator();
        row = (HSSFRow) rows.next();
        if (row == null)
        {
//LogFile.warnMsg(null, "3: GrlSexoExcelBean.lerTabela()");            
            return false;
        }
//LogFile.warnMsg(null, "4: GrlSexoExcelBean.lerTabela()");        
        dataSexoXLSFile = FileManager.lerVersaoTabela(row, filename);
//        Date dataSexoTabela = this.grlUpdatesFacade.dataSexoTabela();
//LogFile.warnMsg(null, "5.0: GrlSexoExcelBean.lerTabela()\tDefs.CAMINHO_SEXOS:" + Defs.CAMINHO_SEXOS);       
//LogFile.warnMsg(null, "5.1: GrlSexoExcelBean.lerTabela()\tdataSexoXLSFile:" +
        // DataUtils.toStringFull(dataSexoXLSFile)); 
//LogFile.warnMsg(null, "5.2: GrlSexoExcelBean.lerTabela()\tdataSexoTabela:" +
        //DataUtils.toStringFull(dataSexoTabela)); 
//        if (!DataUtils.isMoreRecent(dataSexoXLSFile, dataSexoTabela))
//        {
//            LogFile.warnMsg(
//                null, "A data do nova versão (" + DataUtils.toStringFull(dataSexoXLSFile)
//                + ") do ficheiro \"" + this.filename + "\" eh anterior a data da versão instalada ("
//                + DataUtils.toStringFull(dataSexoTabela) + ")"
//            );
//            return false;
//        }
//LogFile.warnMsg(null, "6: GrlSexoExcelBean.lerTabela()");        
        GrlSexo reg = null;
        
        HSSFWorkbook wb = sheet.getWorkbook();
        objDefaultFormat = new DataFormatter();
        objFormulaEvaluator = new HSSFFormulaEvaluator(wb);
        
        boolean cabecalhoTabela = true;
        while (rows.hasNext())
        {
//LogFile.warnMsg(null, "7: GrlSexoExcelBean.lerTabela()");            
            row = (HSSFRow) rows.next();
            if (row == null)
            {
//LogFile.warnMsg(null, "8: GrlSexoExcelBean.lerTabela()");                
                return false;
            }
//LogFile.warnMsg(null, "9: GrlSexoExcelBean.lerTabela()");            
            if (cabecalhoTabela)
            {
//LogFile.warnMsg(null, "10: GrlSexoExcelBean.lerTabela()");                
                cabecalhoTabela = false;
                continue;
            }
//LogFile.warnMsg(null, "11: GrlSexoExcelBean.lerTabela()");            
            reg = lerCampos(row);

            if (reg != null)
            {
//LogFile.warnMsg(null, "12: GrlSexoExcelBean.lerTabela()\treg: " + this.grlSexoCache.toString(reg));                
                if (escreverTabela(reg))
                {
                    nreg++;
                }
            }
//LogFile.warnMsg(null, "13: GrlSexoExcelBean.lerTabela()");
        } // fim da leitura de cada linha
//LogFile.warnMsg(null, "14: GrlSexoExcelBean.lerTabela()\tnreg: " + nreg );
        return true;
    }

    /**
     *
     * @param reg
     * @throws ExcepcaoCarregamentoTabelasExcel
     */
    public boolean escreverTabela(GrlSexo reg) throws ExcepcaoCarregamentoTabelasExcel
    {
        /*
            hipotese1
            (vv) se existir registo com o mesmo id e o mesmo nome
                    nao fazer nada 
        
            hipotese2
            (ff) se nao existir nenhum com o mesmo id ou mesmo nome
                    criar novo registo
               
            hipotese3
            (vf) se existir com o mesmo id e nenhum com o mesmo nome
                    actualiza reg
        
            hipotese4
            (fv) se existir registo com o id diferente e com o mesmo nome 
                lancar mensagem de alerta
                    (tentativa de gravar o sexo (reg.pk_sexo, reg.nome) 
                    existindo ja grava o sexo (pk_sexo, nome)
                enviar excepcao
                
         */
        if (reg.getPkGrlSexo() <= 0)
        {

            LogFile.warnMsg(null,
                "Tentativa de registar sexo " + this.grlSexoCache.toString(reg));
            throw new ExcepcaoCarregamentoTabelasExcel();
        }

        GrlSexo sexoPeloNome = this.grlSexoCache.findByNome(reg.getNome());
        GrlSexo sexoPeloCodigo = this.grlSexoCache.find(reg.getPkGrlSexo());
        String msg;

//        if (sexoPeloCodigo == null)
//        {
//            if (sexoPeloNome == null) // hipotese 2
//            {
//                this.createRegisterWithPersonalizedException(reg);
//                return true;
//            }
//            else // hipotese 4  
//            {
//                LogFile.warnMsg(null,
//                    "Tentativa de registar GrlSexo " + this.grlSexoCache.toString(reg)
//                    + " existindo já gravado o sexo " + this.grlSexoCache.toString(sexoPeloNome));
//
//                throw new ExcepcaoCarregamentoTabelasExcel();
//            }
//        }

        // hipotese 1
        if (sexoPeloNome != null)
        {
            return false;
        }

        // hipotese 3
        this.editRegisterWithPersonalizedException(reg);
        return true;
    }

    /**
     *
     * @param row
     * @return
     * @throws ExcepcaoCarregamentoTabelasExcel
     */
    public GrlSexo lerCampos(HSSFRow row) throws ExcepcaoCarregamentoTabelasExcel
    {
//        final int PK_SEXO = 1;
//        final int NOME = 2;
        Integer pk_sexo;
        String nome, msg;

        HSSFCell cells[];
        cells = new HSSFCell[2];

        GrlSexo reg = new GrlSexo();

        for (int i = 0; i < 2; i++)
        {
            cells[i] = row.getCell((short) i);
            if (HSSFCellUtilities.isCellEmpty(cells[i]))
            {
                return null;
            }
        }
//
//        pk_sexo = HSSFCellUtilities.getIntCellValue(objDefaultFormat, objFormulaEvaluator, cells[0]);
//        if (pk_sexo == null)
//        {
//            msg = "Erro na chave primaria do sexo";
//            LogFile.warnMsg(null, msg);
//            throw new ExcepcaoCarregamentoTabelasExcel();
//        }
//        if (pk_sexo > 0)
//        {
//            reg.setPkGrlSexo(pk_sexo);
////        }
//        else
//        {
//            msg = "Erro na chave primaria do Estado Civil não pode ser negativa";
//            LogFile.warnMsg(null, msg);
//            return null;
//        }
//
//        nome = HSSFCellUtilities.getStringCellValue(objDefaultFormat, objFormulaEvaluator, cells[1]);
//        reg.setNome(nome);

        return reg;
    }

    /**
     *
     * @param reg
     * @return
//     */
//    public boolean createRegister(GrlSexo reg)
//    {
//        try
//        {
//            this.grlSexoCache.create(reg);
//            return true;
//        }
//        catch (Exception e)
//        {
//
//        }
//        return false;
//    }

    /**
     *
     * @param reg
     * @return
     */
    public boolean editRegister(GrlSexo reg)
    {
        try
        {
            this.grlSexoCache.edit(reg);
            return true;
        }
        catch (Exception e)
        {

        }
        return false;
    }

    /**
     *
     * @param reg
     * @throws ExcepcaoCarregamentoTabelasExcel
     */
//    public void createRegisterWithPersonalizedException(GrlSexo reg) throws ExcepcaoCarregamentoTabelasExcel
//    {
//        if (!this.createRegister(reg))
//        {
//            Mensagem.enviarJanelaMensagemErro("Tentativa falhada ao criar GrlSexo",
//                "Tentativa de criar GrlSexo " + this.grlSexoCache.toString(reg));
//            throw new ExcepcaoCarregamentoTabelasExcel();
//        }
//    }

    /**
     *
     * @param reg
     * @throws ExcepcaoCarregamentoTabelasExcel
     */
    public void editRegisterWithPersonalizedException(GrlSexo reg) throws ExcepcaoCarregamentoTabelasExcel
    {
        if (!this.editRegister(reg))
        {
            Mensagem.enviarJanelaMensagemErro("Tentativa falhada ao actualizar GrlSexo",
                "Tentativa de actualizar GrlSexo " + this.grlSexoCache.toString(reg));
            throw new ExcepcaoCarregamentoTabelasExcel();
        }
    }

}
