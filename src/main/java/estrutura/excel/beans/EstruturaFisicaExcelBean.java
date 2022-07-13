/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estrutura.excel.beans;

import estrutura.ejbs.cache.EstruturaFisicaCache;
import ejbs.entities.EstruturaFisica;
import ejbs.facades.EstruturaFisicaFacade;
import ejbs.facades.EstruturaGrlExcelPathFacade;
import ejbs.facades.EstruturaGrlUpdadeFacade;
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
import ejbs.entities.LocLocalidade;
import ejbs.facades.LocLocalidadeFacade;
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
public class EstruturaFisicaExcelBean extends ExcelBeanAbstract implements Serializable
{

    @Resource
    private UserTransaction userTransaction;

    @Inject
    EstruturaFisicaCache estruturaFisicaBean;

//	@Inject
//	LocProvinciaExcelBean locProvinciaExcelBean;
//
//	@Inject
//	LocMunicipioExcelBean locMunicipioExcelBean;
//
//	@Inject
//	LocComunaExcelBean locComunasExcelBean;
//
//	@Inject
//	LocBairroExcelBean locBairrosExcelBean;
    @EJB
    private EstruturaGrlUpdadeFacade grlUpdatesFacade;

    @EJB
    private EstruturaFisicaFacade estruturaFisicaFacade;
    
    @EJB
    private LocLocalidadeFacade localidadeFacade;
    
    @EJB
    private EstruturaGrlExcelPathFacade grlExcelPathFacade;

    private Date dataXLSFile;
    
    private DataFormatter objDefaultFormat;
    private FormulaEvaluator objFormulaEvaluator;
    

    /**
     * Creates a new instance of Grl_Pais_Excel_Bean
     */
    public EstruturaFisicaExcelBean()
    {
        //default_filename = grlExcelPathFacade.getCurrentGrlExcelPath() + Defs.NOME_FILE_ESTRUTURA_FISICA
        default_filename = Defs.CAMINHO_ESTRUTURA_FISICA;
    }

    /**
     *
     * @return
     */
    public static EstruturaFisicaExcelBean getInstanciaBean()
    {
        FacesContext context = FacesContext.getCurrentInstance();
        EstruturaFisicaExcelBean exelBean
                = (EstruturaFisicaExcelBean) context.getELContext().
                getELResolver().getValue(FacesContext.getCurrentInstance().
                        getELContext(), null, "estruturaFisicaExcelBean");

        return exelBean;
    }

    @Override
    public void carregar()
    {
        default_filename = grlExcelPathFacade.getCurrentGrlExcelPath() + Defs.NOME_FILE_ESTRUTURA_FISICA;
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
        LogFile.warnMsg(null, "0: EstruturaFisicaExcelBean.carregarTabelas()");
        if (lerTabela())
        {
            LogFile.warnMsg(null, "1: EstruturaFisicaExcelBean.carregarTabelas()");
            this.grlUpdatesFacade.escreverDataActualizacaoEstruturaFisica(this.dataXLSFile);
            this.estruturaFisicaBean.init();
            return true;
        }
        LogFile.warnMsg(null, "2: EstruturaFisicaExcelBean.carregarTabelas()");
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
            LogFile.warnMsg(null, "0: EstruturaFisicaExcelBean.lerTabela()\tsheet: " + (sheet == null ? "null" : "not null"));
            this.filename = (this.filename == null) ? default_filename : filename;
            sheet = sheet != null ? sheet : FileManager.getSheetFromXLS_File(filename);

            if (sheet == null)
            {
                LogFile.warnMsg(null, "1: EstruturaFisicaExcelBean.lerTabela()");
                return false;
            }
            HSSFRow row;

            Iterator rows = sheet.rowIterator();

            row = (HSSFRow) rows.next();
            if (row == null)
            {
                LogFile.warnMsg(null, "2: EstruturaFisicaExcelBean.lerTabela()\tfilename: " + filename);
                return false;
            }
            LogFile.warnMsg(null, "2.1: EstruturaFisicaExcelBean.lerTabela()\tfilename: " + filename);
            dataXLSFile = FileManager.lerVersaoTabela(row, filename);

            Date dataTabela = this.grlUpdatesFacade.dataEstruturaFisicaTabela();
            if (dataTabela != null)
            {
                LogFile.warnMsg(null, "dataXLSFile: " + DataUtils.toStringFull(dataXLSFile));
            }
            LogFile.warnMsg(null, "dataPaisTabela: " + DataUtils.toStringFull(dataXLSFile));
            if (!DataUtils.isMoreRecent(dataXLSFile, dataTabela))
            {
                LogFile.warnMsg(
                        null, "A data do nova versão (" + DataUtils.toStringFull(dataXLSFile)
                        + ") do ficheiro \"" + this.filename + "\" eh anterior a data da versão instalada ("
                        + DataUtils.toStringFull(dataXLSFile) + ")"
                );
                return false;
                
            }
            LogFile.warnMsg(null, "4: EstruturaFisicaExcelBean.lerTabela()");
            EstruturaFisica reg = null;
            HSSFWorkbook wb = sheet.getWorkbook();
            objDefaultFormat = new DataFormatter();
            objFormulaEvaluator = new HSSFFormulaEvaluator(wb);
            boolean cabecalhoTabela = true;
            while (rows.hasNext())
            {
                row = (HSSFRow) rows.next();
                if (row == null)
                {
                    LogFile.warnMsg(null, "5: EstruturaFisicaExcelBean.lerTabela()");
                    return false;
                }
                if (cabecalhoTabela)
                {
                    cabecalhoTabela = false;
                    continue;
                }
                reg = lerCampos(row);
                LogFile.warnMsg(null, "6: EstruturaFisicaExcelBean.lerTabela()\reg: " + this.estruturaFisicaFacade.toString(reg));
                if (reg != null)
                {
                    if(estruturaFisicaFacade.findByPkEstruturaFisica(reg.getPkEstruturaFisica()) == null)
                    {
                        if (escreverTabela(reg))
                        {
                            nreg++;
                        }
                    }
                }

            } // fim da leitura de cada linha 
            LogFile.warnMsg(null, "7: EstruturaFisicaExcelBean.lerTabela()\tnreg: " + nreg);
        } catch (FileNotFoundException e)
        {
            LogFile.warnMsg(null, "Não foi possível encontrar o ficheiro \"" + filename + "\"");
            return false;
        } catch (IOException e)
        {
            LogFile.warnMsg(null, "Não foi possível abrir o ficheiro \"" + filename + "\"");
            return false;
        }
        LogFile.warnMsg(null, "8: EstruturaFisicaExcelBean.lerTabela()\nreg: " + nreg);
        
        return true;
    }

    /**
     *
     * @param reg
     * @throws ExcepcaoCarregamentoTabelasExcel
     */
    public boolean escreverTabela(EstruturaFisica reg) throws ExcepcaoCarregamentoTabelasExcel
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
    public EstruturaFisica lerCampos(HSSFRow row) throws ExcepcaoCarregamentoTabelasExcel
    {
        String pkEstruturaFisica, designacao, fkEstruturaFisica,fkLocalidade;
        HSSFCell cells[];
        cells = new HSSFCell[3];

        for (int i = 0; i < 3; i++)
        {

            cells[i] = row.getCell((short) i);
            if (HSSFCellUtilities.isCellEmpty(cells[i]))
            {
                return null;
            }
        }
        pkEstruturaFisica = HSSFCellUtilities.getStringCellValue(objDefaultFormat, objFormulaEvaluator,cells[0]);
        designacao = HSSFCellUtilities.getStringCellValue(objDefaultFormat, objFormulaEvaluator,cells[1]);
        fkLocalidade = HSSFCellUtilities.getStringCellValue(objDefaultFormat, objFormulaEvaluator,cells[2]);
        fkEstruturaFisica = this.estruturaFisicaFacade.findFkEstruturaFisica(pkEstruturaFisica);
        EstruturaFisica reg = new EstruturaFisica();
        reg.setPkEstruturaFisica(pkEstruturaFisica);
        reg.setDesignacao(designacao);

        EstruturaFisica regPai = fkEstruturaFisica == null ? null : this.estruturaFisicaFacade.find(fkEstruturaFisica);
        LocLocalidade localidade = fkLocalidade == null ? null : this.localidadeFacade.find(fkLocalidade);
//        if (regPai != null)
//        {
//            System.err.println("");
//        }
        if ((fkEstruturaFisica != null) && (regPai == null))
        {
            return null;
        }
        reg.setFkEstruturaFisica(regPai);
        reg.setFkLocalidade(localidade);
        return reg;
    }

    /**
     *
     * @param reg
     * @return
     */
    public boolean createRegister(EstruturaFisica reg)
    {
        try
        {
            this.estruturaFisicaFacade.create(reg);
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
    public boolean editRegister(EstruturaFisica reg)
    {
        try
        {
            this.estruturaFisicaFacade.edit(reg);
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
    public void createRegisterWithPersonalizedException(EstruturaFisica reg) throws ExcepcaoCarregamentoTabelasExcel
    {
        if (!this.createRegister(reg))
        {
            LogFile.warnMsg(null,
                    "Tentativa de criar Pais " + this.estruturaFisicaFacade.toString(reg));
            throw new ExcepcaoCarregamentoTabelasExcel();
        }
    }

    /**
     *
     * @param reg
     * @throws ExcepcaoCarregamentoTabelasExcel
     */
    public void editRegisterWithPersonalizedException(EstruturaFisica reg) throws ExcepcaoCarregamentoTabelasExcel
    {
        if (!this.editRegister(reg))
        {
            LogFile.warnMsg(null,
                    "Tentativa de actualizar Pais " + this.estruturaFisicaFacade.toString(reg));
            throw new ExcepcaoCarregamentoTabelasExcel();
        }
    }

}
