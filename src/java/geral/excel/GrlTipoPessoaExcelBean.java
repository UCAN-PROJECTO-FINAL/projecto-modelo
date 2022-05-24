/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geral.excel;

import ejbs.entities.GrlTipoPessoa;
import ejbs.facades.GrlTipoPessoaFacade;
//import ejbs.facades.GrlUpdatesFacade;
import excel.utils.ExcelBeanAbstract;
import geral.beans.GrlTipoPessoaBean;
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
 * @author amaro
 */
@Named(value = "grlTipoPessoaExcelBean")
@RequestScoped
public class GrlTipoPessoaExcelBean extends ExcelBeanAbstract implements Serializable
{

    @Resource
    private UserTransaction userTransaction;

    @Inject
    GrlTipoPessoaBean tipoPessoaBean;

    @EJB
    private GrlTipoPessoaFacade grlTipoPessoaFacade;

//    @EJB
//    private GrlUpdatesFacade grlUpdatesFacade;

    private Date dataTipoPessoaXLSFile;

    private DataFormatter objDefaultFormat;
    private FormulaEvaluator objFormulaEvaluator;

    /**
     * Creates a new instance of GrlTipoPessoaExcelBean
     */
    public GrlTipoPessoaExcelBean()
    {
     //   default_filename = Defs.CAMINHO_TIPO_PESSOA;
//        System.out.println("Defs.CAMINHO_TIPO_PESSOA: " + Defs.CAMINHO_TIPO_PESSOA);
    }

    @Override
    public void carregar()
    {

        try
        {
////LogFile.warnMsg(null, "0: GrlTipoPessoaExcelBean.carregar()");     

            try
            {
                this.userTransaction.begin();
            }
            catch (NotSupportedException | SystemException e)
            {
                LogFile.warnMsg(null, "Falha no estabelcecimento da demarcação da transacao associada a actualizacao do ficheiro excel de tipo de pessoas");
            }
            if (carregarTabelas())
            {

                LogFile.warnMsg(null, "o carregamento/actualizacao do ficheiro excel de tipo de pessoas foi realizado com sucesso");
            }
            try
            {
                this.userTransaction.commit();
            }
            catch (IllegalStateException | SecurityException | HeuristicMixedException | HeuristicRollbackException | RollbackException | SystemException e)
            {
                LogFile.warnMsg(null, "Falha no \"commit\" da actualizacao do ficheiro excel de tipo de pessoas");
            }
////LogFile.warnMsg(null, "1: GrlTipoPessoaExcelBean.carregar()");                        
        }
        catch (ExcepcaoCarregamentoTabelasExcel e)
        {
//LogFile.warnMsg(null, "2: GrlTipoPessoaExcelBean.carregar()"); 
            try
            {
                this.userTransaction.rollback();
            }
            catch (IllegalStateException | SecurityException | SystemException ex)
            {
                LogFile.warnMsg(null, "Falha no \"rollback\" da tentativa de actualizacao do ficheiro excel de tipo de pessoas");
            }
        }
    }

    @SuppressWarnings("empty-statement")
    @Override
    public boolean carregarTabelas() throws ExcepcaoCarregamentoTabelasExcel
    {

//LogFile.warnMsg(null, "0: GrlTipoPessoaExcelBean.carregarTabelas()");             
        if (lerTabela())
        {
//LogFile.warnMsg(null, "1: GrlTipoPessoaExcelBean.carregarTabelas()");            
//            this.grlUpdatesFacade.escreverDataActualizacaoTipoPessoaTabela(dataTipoPessoaXLSFile);
            this.tipoPessoaBean.init();
            return true;
        }
//LogFile.warnMsg(null, "2: GrlTipoPessoaExcelBean.carregarTabelas()");        
        return false;
    }

    /**
     *
     * @return @throws ExcepcaoCarregamentoTabelasExcel
     */
    @Override
    public boolean lerTabela() throws ExcepcaoCarregamentoTabelasExcel
    {

//LogFile.warnMsg(null, "0: GrlTipoPessoaExcelBean.lerTabela()");          
        int nreg = 0;
        try
        {

            this.filename = (this.filename == null) ? default_filename : filename;
            sheet = sheet != null ? sheet : FileManager.getSheetFromXLS_File(filename);

//LogFile.warnMsg(null, "1: GrlTipoPessoaExcelBean.lerTabela()");            
        }
        catch (FileNotFoundException e)
        {
//            LogFile.warnMsg(null, "Não foi possível encontrar o ficheiro \"" + Defs.CAMINHO_TIPO_PESSOA + "\"");
            return false;
        }
        catch (IOException e)
        {
//            LogFile.warnMsg(null, "Não foi possível abrir o ficheiro \"" + Defs.CAMINHO_TIPO_PESSOA + "\"");
            return false;
        }
        if (sheet == null)
        {

//            LogFile.warnMsg(null, "Não foi possível abrir o ficheiro \"" + Defs.CAMINHO_TIPO_PESSOA + "\"");
            return false;
        }
        HSSFWorkbook wb = sheet.getWorkbook();
        objDefaultFormat = new DataFormatter();
        objFormulaEvaluator = new HSSFFormulaEvaluator(wb);
//LogFile.warnMsg(null, "2: GrlTipoPessoaExcelBean.lerTabela()");        
        HSSFRow row;
        Iterator rows = sheet.rowIterator();
        row = (HSSFRow) rows.next();
        if (row == null)
        {
//LogFile.warnMsg(null, "3: GrlTipoPessoaExcelBean.lerTabela()");            
            return false;
        }
//LogFile.warnMsg(null, "4: GrlTipoPessoaExcelBean.lerTabela()");        
        dataTipoPessoaXLSFile = FileManager.lerVersaoTabela(row, filename);
//        Date dataTipoPessoaTabela = this.grlUpdatesFacade.dataTipoPessoaTabela();
//LogFile.warnMsg(null, "5.0: GrlTipoPessoaExcelBean.lerTabela()\tDefs.CAMINHO_TIPO_PESSOA:" + Defs.CAMINHO_TIPO_PESSOA);       
//LogFile.warnMsg(null, "5.1: GrlTipoPessoaExcelBean.lerTabela()\tdataTipoPessoaXLSFile:" +
        // DataUtils.toStringFull(dataTipoPessoaXLSFile)); 
//LogFile.warnMsg(null, "5.2: GrlTipoPessoaExcelBean.lerTabela()\tdataTipoPessoaTabela:" +
//        //DataUtils.toStringFull(dataTipoPessoaTabela)); 
//        if (!DataUtils.isMoreRecent(dataTipoPessoaXLSFile, dataTipoPessoaTabela))
//        {
//            LogFile.warnMsg(
//                null, "A data do nova versão (" + DataUtils.toStringFull(dataTipoPessoaXLSFile)
//                + ") do ficheiro \"" + this.filename + "\" eh anterior a data da versão instalada ("
////                + DataUtils.toStringFull(dataTipoPessoaTabela) + ")"
//            );
//            return false;
//        }
//LogFile.warnMsg(null, "6: GrlTipoPessoaExcelBean.lerTabela()");        
        GrlTipoPessoa reg = null;
        boolean cabecalhoTabela = true;
        while (rows.hasNext())
        {
//LogFile.warnMsg(null, "7: GrlTipoPessoaExcelBean.lerTabela()");            
            row = (HSSFRow) rows.next();
            if (row == null)
            {
//LogFile.warnMsg(null, "8: GrlTipoPessoaExcelBean.lerTabela()");                
                return false;
            }
//LogFile.warnMsg(null, "9: GrlTipoPessoaExcelBean.lerTabela()");            
            if (cabecalhoTabela)
            {
//LogFile.warnMsg(null, "10: GrlTipoPessoaExcelBean.lerTabela()");                
                cabecalhoTabela = false;
                continue;
            }
//LogFile.warnMsg(null, "11: GrlTipoPessoaExcelBean.lerTabela()");            
//            reg = lerCampos(row);

            if (reg != null)
            {
//LogFile.warnMsg(null, "12: GrlTipoPessoaExcelBean.lerTabela()\treg: " + this.grlTipoPessoaFacade.toString(reg));                
//                if (escreverTabela(reg))
//                {
//                    nreg++;
//                }
            }
//LogFile.warnMsg(null, "13: GrlTipoPessoaExcelBean.lerTabela()");
        } // fim da leitura de cada linha
//LogFile.warnMsg(null, "14: GrlTipoPessoaExcelBean.lerTabela()\tnreg: " + nreg );
        return true;
    }

    /**
     *
     * @param reg
     * @throws ExcepcaoCarregamentoTabelasExcel
     */
//    public boolean escreverTabela(GrlTipoPessoa reg) throws ExcepcaoCarregamentoTabelasExcel
//    {
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
                    (tentativa de gravar o tipo pessoa (reg.pk_tipo_pessoa, reg.nome) 
                    existindo ja grava o tipo pessoa (pk_tipo_pessoa, nome)
                enviar excepcao
                
         */
//        if (reg.getPkGrlTipoPessoa() <= 0)
//        {
//
//            LogFile.warnMsg(null,
//         //       "Tentativa de registar tipo pessoa " + this.grlTipoPessoaFacade.toString(reg));
//           // throw new ExcepcaoCarregamentoTabelasExcel();
//        }

     //   GrlTipoPessoa sexoPeloNome = this.grlTipoPessoaFacade.findByNome(reg.getNome());
//        GrlTipoPessoa sexoPeloCodigo = this.grlTipoPessoaFacade.find(reg.getPkGrlTipoPessoa());
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
//                    "Tentativa de registar GrlTipoPessoa " + this.grlTipoPessoaFacade.toString(reg)
//                    + " existindo já gravado o tipo pessoa " + this.grlTipoPessoaFacade.toString(sexoPeloNome));
//
//                throw new ExcepcaoCarregamentoTabelasExcel();
//            }
//        }
//
//        // hipotese 1
//        if (sexoPeloNome != null)
//        {
//            return false;
//        }
//
//        // hipotese 3
//        this.editRegisterWithPersonalizedException(reg);
//        return true;
//    }

    /**
     *
     * @param row
     * @return
     * @throws ExcepcaoCarregamentoTabelasExcel
     */
//    public GrlTipoPessoa lerCampos(HSSFRow row) throws ExcepcaoCarregamentoTabelasExcel
//    {
////        final int PK_SEXO = 1;
////        final int NOME = 2;
//        Integer pk_tipo_pessoa;
//        String nome, msg;
//
//        HSSFCell cells[];
//        cells = new HSSFCell[2];
//
//        GrlTipoPessoa reg = new GrlTipoPessoa();
//
//        for (int i = 0; i < 2; i++)
//        {
//            cells[i] = row.getCell((short) i);
//            if (HSSFCellUtilities.isCellEmpty(cells[i]))
//            {
//                return null;
//            }
//        }
//
//        pk_tipo_pessoa = HSSFCellUtilities.getIntCellValue(objDefaultFormat, objFormulaEvaluator, cells[0]);
//        if (pk_tipo_pessoa == null)
//        {
//            msg = "Erro na chave primaria do tipo de pessoas";
//            LogFile.warnMsg(null, msg);
//            throw new ExcepcaoCarregamentoTabelasExcel();
//        }
//        if (pk_tipo_pessoa > 0)
//        {
//            reg.setPkGrlTipoPessoa(pk_tipo_pessoa);
//        }
//        else
//        {
//            msg = "Erro na chave primaria do Tipo Pessoa Civil não pode ser negativa";
//            LogFile.warnMsg(null, msg);
//            return null;
//        }
//
//        nome = HSSFCellUtilities.getStringCellValue(objDefaultFormat, objFormulaEvaluator, cells[1]);
//        reg.setNome(nome);
//
//        return reg;
//    }

    /**
     *
     * @param reg
     * @return
     */
//    public boolean createRegister(GrlTipoPessoa reg)
//    {
//        try
//        {
//            this.grlTipoPessoaFacade.create(reg);
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
//    public boolean editRegister(GrlTipoPessoa reg)
//    {
//        try
//        {
//            this.grlTipoPessoaFacade.edit(reg);
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
     * @throws ExcepcaoCarregamentoTabelasExcel
     */
//    public void createRegisterWithPersonalizedException(GrlTipoPessoa reg) throws ExcepcaoCarregamentoTabelasExcel
//    {
//        if (!this.createRegister(reg))
//        {
//            Mensagem.enviarJanelaMensagemErro("Tentativa falhada ao criar GrlTipoPessoa",
//                "Tentativa de criar GrlTipoPessoa " + this.grlTipoPessoaFacade.toString(reg));
//            throw new ExcepcaoCarregamentoTabelasExcel();
//        }
//    }

    /**
     *
     * @param reg
     * @throws ExcepcaoCarregamentoTabelasExcel
//     */
//    public void editRegisterWithPersonalizedException(GrlTipoPessoa reg) throws ExcepcaoCarregamentoTabelasExcel
//    {
//        if (!this.editRegister(reg))
//        {
//            Mensagem.enviarJanelaMensagemErro("Tentativa falhada ao actualizar GrlTipoPessoa",
//                "Tentativa de actualizar GrlTipoPessoa " + this.grlTipoPessoaFacade.toString(reg));
//            throw new ExcepcaoCarregamentoTabelasExcel();
//        }
//    }

}
