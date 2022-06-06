/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geral.excel;

import ejbs.cache.GrlEstadoCivilCache;
import ejbs.entities.GrlEstadoCivil;
//import ejbs.facades.GrlUpdatesFacade;
import utils.excel.ExcelBeanAbstract;
import geral.beans.GrlEstadoCivilBean;
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
@Named(value = "grlEstadioCivilExcelBean")
@RequestScoped
public class GrlEstadoCivilExcelBean extends ExcelBeanAbstract implements Serializable
{

    @Resource
    private UserTransaction userTransaction;

    @Inject
    GrlEstadoCivilBean estadoCivilBean;

    @Inject
    private GrlEstadoCivilCache grlEstadoCivilCache;

//    @EJB
//    private GrlUpdatesFacade grlUpdatesFacade;

    private Date dataEstadoCivilXLSFile;

    private DataFormatter objDefaultFormat;
    private FormulaEvaluator objFormulaEvaluator;

    /**
     * Creates a new instance of EstadoCivilExcelBean
     */
    public GrlEstadoCivilExcelBean()
    {
        //default_filename = Defs.ESTADO_CIVIL;
    }

    @Override
    public void carregar()
    {
        try
        {
//LogFile.warnMsg(null, "0: GrlEstadoCivilExcelBean.carregar()");     
            try
            {
                this.userTransaction.begin();
            }
            catch (NotSupportedException | SystemException e)
            {
                LogFile.warnMsg(null, "Falha no estabelcecimento da demarcação da transacao associada a actualizacao do ficheiro excel de Estados Civis");
            }
            if (carregarTabelas())
            {

                LogFile.warnMsg(null, "o carregamento/actualizacao do ficheiro excel de estados civis foi realizado com sucesso");
            }
            try
            {
                this.userTransaction.commit();
            }
            catch (IllegalStateException | SecurityException | HeuristicMixedException | HeuristicRollbackException | RollbackException | SystemException e)
            {
                LogFile.warnMsg(null, "Falha no \"commit\" da actualizacao do ficheiro excel de estado civil");
            }
//LogFile.warnMsg(null, "1: GrlEstadoCivilExcelBean.carregar()");                        
        }
        catch (ExcepcaoCarregamentoTabelasExcel e)
        {
            LogFile.warnMsg(null, "2: GrlEstadoCivilExcelBean.carregar()");
            try
            {
                this.userTransaction.rollback();
            }
            catch (IllegalStateException | SecurityException | SystemException ex)
            {
                LogFile.warnMsg(null, "Falha no \"rollback\" da tentativa de actualizacao do ficheiro excel de estado civil");
            }
        }
    }

    @SuppressWarnings("empty-statement")
    @Override
    public boolean carregarTabelas() throws ExcepcaoCarregamentoTabelasExcel
    {
//        LogFile.warnMsg(null, "0: GrlEstadoCivilExcelBean.carregarTabelas()");
        if (lerTabela())
        {
//            LogFile.warnMsg(null, "1: GrlEstadoCivilExcelBean.carregarTabelas()");
//            this.grlUpdatesFacade.escreverDataActualizacaoEstadoCivilTabela(dataEstadoCivilXLSFile);
            this.estadoCivilBean.init();
            return true;
        }
//        LogFile.warnMsg(null, "2: GrlEstadoCivilExcelBean.carregarTabelas()");
        return false;
    }

    /**
     *
     * @return @throws ExcepcaoCarregamentoTabelasExcel
     */
    @Override
    public boolean lerTabela() throws ExcepcaoCarregamentoTabelasExcel
    {
//        LogFile.warnMsg(null, "0: GrlEstadoCivilExcelBean.lerTabela()\tsheet: " + (sheet == null ? "null" : "not null"));
        int nreg = 0;
        try
        {
            this.filename = (this.filename == null) ? default_filename : filename;
            sheet = sheet != null ? sheet : FileManager.getSheetFromXLS_File(filename);
//            LogFile.warnMsg(null, "1: GrlEstadoCivilExcelBean.lerTabela()\tfilename: " + filename);
        }
        catch (FileNotFoundException e)
        {
            LogFile.warnMsg(null, "Não foi possível encontrar o ficheiro \"" + filename + "\"");
            return false;
        }
        catch (IOException e)
        {
            LogFile.warnMsg(null, "Não foi possível abrir o ficheiro \"" + filename + "\"");
            return false;
        }
        if (sheet == null)
        {
            LogFile.warnMsg(null, "Não foi possível abrir o ficheiro \"" + filename + "\"");
            return false;
        }
//        LogFile.warnMsg(null, "2: GrlEstadoCivilExcelBean.lerTabela()");
        HSSFRow row;
        Iterator rows = sheet.rowIterator();
        row = (HSSFRow) rows.next();
        if (row == null)
        {
//            LogFile.warnMsg(null, "3: GrlEstadoCivilExcelBean.lerTabela()");
            return false;
        }
//        LogFile.warnMsg(null, "4: GrlEstadoCivilExcelBean.lerTabela()");
        dataEstadoCivilXLSFile = FileManager.lerVersaoTabela(row, filename);
//        Date dataEstadoCivilTabela = this.grlUpdatesFacade.dataEstadoCivilTabela();
//        LogFile.warnMsg(null, "5.0: GrlEstadoCivilExcelBean.lerTabela()\tDefs.ESTADO_CIVIL:" + Defs.ESTADO_CIVIL);
        if (dataEstadoCivilXLSFile != null)
        {
//            LogFile.warnMsg(null, "5.1: GrlEstadoCivilExcelBean.lerTabela()\tdataEstadoCivilXLSFile:"
//                + DataUtils.toStringFull(dataEstadoCivilXLSFile));
        }
//        LogFile.warnMsg(null, "5.2: GrlEstadoCivilExcelBean.lerTabela()\tdataEstadoCivilTabela:"
//            + DataUtils.toStringFull(dataEstadoCivilTabela));

//        if (!DataUtils.isMoreRecent(dataEstadoCivilXLSFile, dataEstadoCivilTabela))
//        {
//            LogFile.warnMsg(
//                null, "A data do nova versão (" + DataUtils.toStringFull(dataEstadoCivilXLSFile)
//                + ") do ficheiro \"" + this.filename + "\" eh anterior a data da versão instalada ("
//                + DataUtils.toStringFull(dataEstadoCivilTabela) + ")"
//            );
//            return false;
//        }
//        LogFile.warnMsg(null, "6: GrlEstadoCivilExcelBean.lerTabela()");
        GrlEstadoCivil reg = null;
        boolean cabecalhoTabela = true;

        HSSFWorkbook wb = sheet.getWorkbook();
        objDefaultFormat = new DataFormatter();
        objFormulaEvaluator = new HSSFFormulaEvaluator(wb);

        while (rows.hasNext())
        {
//            LogFile.warnMsg(null, "7: GrlEstadoCivilExcelBean.lerTabela()");
            row = (HSSFRow) rows.next();
            if (row == null)
            {
//                LogFile.warnMsg(null, "8: GrlEstadoCivilExcelBean.lerTabela()");
                return false;
            }
//            LogFile.warnMsg(null, "9: GrlEstadoCivilExcelBean.lerTabela()");
            if (cabecalhoTabela)
            {
//                LogFile.warnMsg(null, "10: GrlEstadoCivilExcelBean.lerTabela()");
                cabecalhoTabela = false;
                continue;
            }
//            LogFile.warnMsg(null, "11: GrlEstadoCivilExcelBean.lerTabela()");
            reg = lerCampos(row);

            if (reg != null)
            {
//                LogFile.warnMsg(null, "12: GrlEstadoCivilExcelBean.lerTabela()\treg: " + this.grlEstadoCivilCache.toString(reg));
                if (escreverTabela(reg))
                {
                    nreg++;
                }
            }
//            LogFile.warnMsg(null, "13: GrlEstadoCivilExcelBean.lerTabela()");
        } // fim da leitura de cada linha
//        LogFile.warnMsg(null, "14: GrlEstadoCivilExcelBean.lerTabela()\tnreg: " + nreg);
        return true;
    }

    /**
     *
     * @param reg
     * @throws ExcepcaoCarregamentoTabelasExcel
     */
    public boolean escreverTabela(GrlEstadoCivil reg) throws ExcepcaoCarregamentoTabelasExcel
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
                    (tentativa de gravar o estado civil (reg.pk_estado_civil, reg.nome) 
                    existindo ja grava o estado civil (pk_estado_civil, nome)
                enviar excepcao
                
         */
        if (reg.getPkGrlEstadoCivil() <= 0)
        {

            Mensagem.enviarJanelaMensagemErro("Erro ao salva GrlEstadoCivil",
                "Tentativa de registar estado civil " + this.grlEstadoCivilCache.toString(reg));
            throw new ExcepcaoCarregamentoTabelasExcel();
        }

        GrlEstadoCivil estadoCivilPeloNome = this.grlEstadoCivilCache.findByNome(reg.getNome());
        GrlEstadoCivil estadoCivilPeloCodigo = this.grlEstadoCivilCache.find(reg.getPkGrlEstadoCivil());
        String msg;

        if (estadoCivilPeloCodigo == null)
        {
            if (estadoCivilPeloNome == null) // hipotese 2
            {
                this.createRegisterWithPersonalizedException(reg);
                return true;
            }
            else // hipotese 4  
            {
                Mensagem.enviarJanelaMensagemErro("Erro ao salvar GrlEstadoCivil",
                    "Tentativa de registar GrlEstadoCivil " + this.grlEstadoCivilCache.toString(reg)
                    + " existindo já gravado o estado civil " + this.grlEstadoCivilCache.toString(estadoCivilPeloNome));
                throw new ExcepcaoCarregamentoTabelasExcel();
            }
        }

        // hipotese 1
        if (estadoCivilPeloNome != null)
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
    public GrlEstadoCivil lerCampos(HSSFRow row) throws ExcepcaoCarregamentoTabelasExcel
    {
        final int PK_ESTADO_CIVIL = 1;
        final int NOME = 2;
        Integer pk_estado_civil;
        String nome, msg;

        HSSFCell cell;
        GrlEstadoCivil reg = new GrlEstadoCivil();

        HSSFCell cells[];
        cells = new HSSFCell[2];

        for (int i = 0; i < 2; i++)
        {
            cells[i] = row.getCell((short) i);
            if (HSSFCellUtilities.isCellEmpty(cells[i]))
            {
                return null;
            }
        }

       // pk_estado_civil = HSSFCellUtilities.getIntCellValue(objDefaultFormat, objFormulaEvaluator, cells[0]);
//        if (pk_estado_civil == null)
//        {
//            msg = "Erro na chave primaria do Estado Civil";
//            LogFile.warnMsg(null, msg);
//            throw new ExcepcaoCarregamentoTabelasExcel();
//        }
//        if (pk_estado_civil > 0)
//        {
//            reg.setPkGrlEstadoCivil(pk_estado_civil);
//        }
//        else
//        {
//            msg = "Erro na chave primaria do Estado Civil não pode ser negativa";
//            LogFile.warnMsg(null, msg);
//            return null;
//        }

       // nome = HSSFCellUtilities.getStringCellValue(objDefaultFormat, objFormulaEvaluator, cells[1]);
//        reg.setNome(nome);

        return reg;

    }

    /**
     *
     * @param reg
     * @return
     */
    public boolean createRegister(GrlEstadoCivil reg)
    {
        try
        {
            this.grlEstadoCivilCache.create(reg);
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
     * @return
     */
    public boolean editRegister(GrlEstadoCivil reg)
    {
        try
        {
            this.grlEstadoCivilCache.edit(reg);
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
    public void createRegisterWithPersonalizedException(GrlEstadoCivil reg) throws ExcepcaoCarregamentoTabelasExcel
    {
        if (!this.createRegister(reg))
        {
            Mensagem.enviarJanelaMensagemErro("Tentativa falhada ao criar GrlEstadoCivil",
                "Tentativa de criar GrlEstadoCivil " + this.grlEstadoCivilCache.toString(reg));
            throw new ExcepcaoCarregamentoTabelasExcel();
        }
    }

    /**
     *
     * @param reg
     * @throws ExcepcaoCarregamentoTabelasExcel
     */
    public void editRegisterWithPersonalizedException(GrlEstadoCivil reg) throws ExcepcaoCarregamentoTabelasExcel
    {
        if (!this.editRegister(reg))
        {
            Mensagem.enviarJanelaMensagemErro("Tentativa falhada ao actualizar GrlEstadoCivil",
                "Tentativa de actualizar GrlEstadoCivil " + this.grlEstadoCivilCache.toString(reg));
            throw new ExcepcaoCarregamentoTabelasExcel();
        }
    }

}
