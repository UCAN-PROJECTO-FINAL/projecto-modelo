/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estrutura.excel.beans;

import estrutura.ejbs.cache.EstruturaLogicaCache;
import ejbs.entities.EstruturaLogica;
import ejbs.facades.EstruturaGrlExcelPathFacade;
import ejbs.facades.EstruturaGrlUpdadeFacade;
import ejbs.facades.EstruturaLogicaFacade;
import estrutura.utils.Defs;
import utils.excel.ExcelBeanAbstract;
import utils.DataUtils;
import utils.ExcepcaoCarregamentoTabelasExcel;
import utils.FileManager;
import utils.excel.HSSFCellUtilities;
import utils.mensagens.LogFile;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.Iterator;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
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

/**
 *
 * @author adilson
 */
@Named
@RequestScoped
public class EstruturaLogicaExcelBean extends ExcelBeanAbstract implements Serializable
{

    @Resource
    private UserTransaction userTransaction;

    @Inject
    EstruturaLogicaCache estruturaLogicaBean;

    @EJB
    private EstruturaGrlUpdadeFacade grlUpdatesFacade;

    @EJB
    private EstruturaLogicaFacade estruturaLogicaFacade;
    
    @EJB
    private EstruturaGrlExcelPathFacade grlExcelPathFacade;

    private Date dataXLSFile;
    
    private DataFormatter objDefaultFormat;
    private FormulaEvaluator objFormulaEvaluator;

    /**
     * Creates a new instance of Grl_Pais_Excel_Bean
     */
    public EstruturaLogicaExcelBean()
    {
       //default_filename = grlExcelPathFacade.getCurrentGrlExcelPath() + Defs.NOME_FILE_ESTRUTURA_LOGICA;
       default_filename = Defs.CAMINHO_ESTRUTURA_LOGICA;
        
    }

    /**
     *
     * @return
     */
    public static EstruturaLogicaExcelBean getInstanciaBean()
    {
        FacesContext context = FacesContext.getCurrentInstance();
        EstruturaLogicaExcelBean exelBean
                = (EstruturaLogicaExcelBean) context.getELContext().
                getELResolver().getValue(FacesContext.getCurrentInstance().
                        getELContext(), null, "estruturaLogicaExcelBean");

        return exelBean;
    }

    @Override
    public void carregar()
    {
        default_filename = grlExcelPathFacade.getCurrentGrlExcelPath() + Defs.NOME_FILE_ESTRUTURA_LOGICA;
        if (!carregarEstruturas())
        {
            return;
        }
    }

    public boolean carregarEstruturas()
    {
        try
        {
//System.out.println("0: CarregarTabelas.carregarTabelas()");            
            try
            {
                this.userTransaction.begin();
            } catch (NotSupportedException | SystemException e)
            {
                LogFile.warnMsg(null, "Falha no estabelcecimento da demarcação da transacao associada a actualizacao do ficheiro excel de paises");
            }
            if (carregarTabelas())
            {
                LogFile.warnMsg(null, "o carregamento/actualizacao do ficheiro excel de paises foi realizado com sucesso");
            }
            try
            {
                this.userTransaction.commit();
                return true;
            } catch (IllegalStateException | SecurityException | HeuristicMixedException | HeuristicRollbackException | RollbackException | SystemException e)
            {
                LogFile.warnMsg(null, "Falha no \"commit\" da actualizacao do ficheiro excel de paises");
            }
//System.out.println("1: CarregarTabelas.carregarTabelas()");                        
        } catch (ExcepcaoCarregamentoTabelasExcel e)
        {
//System.out.println("2: CarregarTabelas.carregarTabelas()");                        
            try
            {
                this.userTransaction.rollback();
            } catch (IllegalStateException | SecurityException | SystemException ex)
            {
                LogFile.warnMsg(null, "Falha no \"rollback\" da tentativa de actualizacao do ficheiro excel de paises");
            }
        }
        return false;
    }

    /**
     *
     * @throws ExcepcaoCarregamentoTabelasExcel
     */
    @SuppressWarnings("empty-statement")
    @Override
    public boolean carregarTabelas() throws ExcepcaoCarregamentoTabelasExcel
    {
        LogFile.warnMsg(null, "0: EstruturaLogicaExcelBean.carregarTabelas()");
        if (lerTabela())
        {
            LogFile.warnMsg(null, "1: EstruturaLogicaExcelBean.carregarTabelas()");
            this.grlUpdatesFacade.escreverDataActualizacaoEstruturaLogica(this.dataXLSFile);
            this.estruturaLogicaBean.init();
            return true;
        }
        LogFile.warnMsg(null, "2: EstruturaLogicaExcelBean.carregarTabelas()");
        return false;
    }

    /**
     *
     * @return @throws ExcepcaoCarregamentoTabelasExcel
     */
    @Override
    public boolean lerTabela() throws ExcepcaoCarregamentoTabelasExcel
    {
        int nreg = 0;
        try
        {
            LogFile.warnMsg(null, "0: EstruturaLogicaExcelBean.lerTabela()\tsheet: " + (sheet == null ? "null" : "not null"));
            this.filename = (this.filename == null) ? default_filename : filename;
            sheet = sheet != null ? sheet : FileManager.getSheetFromXLS_File(filename);

            if (sheet == null)
            {
                LogFile.warnMsg(null, "1: EstruturaLogicaExcelBean.lerTabela()");
                return false;
            }
            HSSFRow row;

            Iterator rows = sheet.rowIterator();

            row = (HSSFRow) rows.next();
            if (row == null)
            {
                LogFile.warnMsg(null, "2: EstruturaLogicaExcelBean.lerTabela()\tfilename: " + filename);
                return false;
            }
            LogFile.warnMsg(null, "2.1: EstruturaLogicaExcelBean.lerTabela()\tfilename: " + filename);
            dataXLSFile = FileManager.lerVersaoTabela(row, filename);

            Date dataTabela = this.grlUpdatesFacade.dataEstruturaLogicaTabela();
            if (dataTabela != null)
            {
                LogFile.warnMsg(null, "dataXLSFile: " + DataUtils.toStringFull(dataXLSFile));
            }
            if (!DataUtils.isMoreRecent(dataXLSFile, dataTabela))
            {
                LogFile.warnMsg(
                        null, "A data do nova versão (" + DataUtils.toStringFull(dataXLSFile)
                        + ") do ficheiro \"" + this.filename + "\" eh anterior a data da versão instalada ("
                        + DataUtils.toStringFull(dataXLSFile) + ")"
                );
                return false;
                
            }
            LogFile.warnMsg(null, "4: EstruturaLogicaExcelBean.lerTabela()");
            EstruturaLogica reg = null;
            HSSFWorkbook wb = sheet.getWorkbook();
            objDefaultFormat = new DataFormatter();
            objFormulaEvaluator = new HSSFFormulaEvaluator(wb);
            boolean cabecalhoTabela = true;
            while (rows.hasNext())
            {
                row = (HSSFRow) rows.next();
                if (row == null)
                {
                    LogFile.warnMsg(null, "5: EstruturaLogicaExcelBean.lerTabela()");
                    return false;
                }
                if (cabecalhoTabela)
                {
                    cabecalhoTabela = false;
                    continue;
                }
                reg = lerCampos(row);
                LogFile.warnMsg(null, "6: EstruturaLogicaExcelBean.lerTabela()\reg: " + this.estruturaLogicaFacade.toString(reg));
                if (reg != null)
                {
                    if(estruturaLogicaFacade.findByPkEstruturaFisica(reg.getPkEstruturaLogica()) == null)
                    {
                        if (escreverTabela(reg))
                        {
                            nreg++;
                        }
                    }
                }

            } // fim da leitura de cada linha 
            LogFile.warnMsg(null, "7: EstruturaLogicaExcelBean.lerTabela()\tnreg: " + nreg);
        } catch (FileNotFoundException e)
        {
            LogFile.warnMsg(null, "Não foi possível encontrar o ficheiro \"" + filename + "\"");
            return false;
        } catch (IOException e)
        {
            LogFile.warnMsg(null, "Não foi possível abrir o ficheiro \"" + filename + "\"");
            return false;
        }
        LogFile.warnMsg(null, "8: EstruturaLogicaExcelBean.lerTabela()\nreg: " + nreg);
        
        return true;
    }

    /**
     *
     * @param reg
     * @throws ExcepcaoCarregamentoTabelasExcel
     */
    public boolean escreverTabela(EstruturaLogica reg) throws ExcepcaoCarregamentoTabelasExcel
    {
        if(reg != null)
        {
            this.createRegisterWithPersonalizedException(reg);
            return true;
        }
        return false;
    }

    /**
     *
     * @param row
     * @return
     * @throws ExcepcaoCarregamentoTabelasExcel
     */
    public EstruturaLogica lerCampos(HSSFRow row) throws ExcepcaoCarregamentoTabelasExcel
    {
        String pkEstruturaLogica, designacao, fkEstruturaLogica;
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
        pkEstruturaLogica = HSSFCellUtilities.getStringCellValue(objDefaultFormat, objFormulaEvaluator,cells[0]);
        designacao = HSSFCellUtilities.getStringCellValue(objDefaultFormat, objFormulaEvaluator,cells[1]);
        fkEstruturaLogica = this.estruturaLogicaFacade.findFkEstruturaLogica(pkEstruturaLogica);
        EstruturaLogica reg = new EstruturaLogica();
        reg.setPkEstruturaLogica(pkEstruturaLogica);
        reg.setDesignacao(designacao);

        EstruturaLogica regPai = fkEstruturaLogica == null ? null : this.estruturaLogicaFacade.find(fkEstruturaLogica);
//        if (regPai != null)
//        {
//            System.err.println("");
//        }
        if ((fkEstruturaLogica != null) && (regPai == null))
        {
            return null;
        }
        reg.setFkEstruturaLogica(regPai);
        return reg;
    }

    /**
     *
     * @param reg
     * @return
     */
    public boolean createRegister(EstruturaLogica reg)
    {
        try
        {
            this.estruturaLogicaFacade.create(reg);
            return true;
        } catch (Exception e)
        {

        }
        return false;
    }

    /**
     *
     * @param reg
     * @return
     */
    public boolean editRegister(EstruturaLogica reg)
    {
        try
        {
            this.estruturaLogicaFacade.edit(reg);
            return true;
        } catch (Exception e)
        {

        }
        return false;
    }

    /**
     *
     * @param reg
     * @throws ExcepcaoCarregamentoTabelasExcel
     */
    public void createRegisterWithPersonalizedException(EstruturaLogica reg) throws ExcepcaoCarregamentoTabelasExcel
    {
        if (!this.createRegister(reg))
        {
            LogFile.warnMsg(null,
                    "Tentativa de criar Pais " + this.estruturaLogicaFacade.toString(reg));
            throw new ExcepcaoCarregamentoTabelasExcel();
        }
    }

    /**
     *
     * @param reg
     * @throws ExcepcaoCarregamentoTabelasExcel
     */
    public void editRegisterWithPersonalizedException(EstruturaLogica reg) throws ExcepcaoCarregamentoTabelasExcel
    {
        if (!this.editRegister(reg))
        {
            LogFile.warnMsg(null,
                    "Tentativa de actualizar Pais " + this.estruturaLogicaFacade.toString(reg));
            throw new ExcepcaoCarregamentoTabelasExcel();
        }
    }

}
