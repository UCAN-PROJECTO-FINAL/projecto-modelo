/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patri.excel.beans;

import patri.ejbs.cache.PtFormaAquisicaoCache;
import ejbs.entities.PtFormaAquisicao;
import ejbs.facades.PtFormaAquisicaoFacade;
import ejbs.facades.PtExcelPathFacade;
import ejbs.facades.PtGrlupdadeFacade;
import utils.excel.ExcelBeanAbstract;
import utils.DataUtils;
import patri.utils.Defs;
import utils.ExcepcaoCarregamentoTabelasExcel;
import patri.utils.FileManager;
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
public class PtFormaAquisicaoExcelBean extends ExcelBeanAbstract implements Serializable
{

    @Resource
    private UserTransaction userTransaction;

    @Inject
    PtFormaAquisicaoCache patriFormaAquisicaoBean;

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
    private PtGrlupdadeFacade patriGrlupdateFacade;

    @EJB
    private PtFormaAquisicaoFacade patriFormaAquisicaoFacade;
    
    @EJB
    private PtExcelPathFacade grlExcelPathFacade;

    private Date dataXLSFile;
    
    private DataFormatter objDefaultFormat;
    private FormulaEvaluator objFormulaEvaluator;

    /**
     * Creates a new instance of PtFormaAquisicaoExcelBean
     */
    public PtFormaAquisicaoExcelBean()
    {
        //default_filename = grlExcelPathFacade.getCurrentGrlExcelPath() + Defs.NOME_FILE_ESTRUTURA_FISICA
        default_filename = Defs.CAMINHO_FORMA_AQUISICAO;
    }

    /**
     *
     * @return
     */
    public static PtFormaAquisicaoExcelBean getInstanciaBean()
    {
        FacesContext context = FacesContext.getCurrentInstance();
        PtFormaAquisicaoExcelBean exelBean
                = (PtFormaAquisicaoExcelBean) context.getELContext().
                getELResolver().getValue(FacesContext.getCurrentInstance().
                        getELContext(), null, "patriFormaAquisicaoExcelBean");

        return exelBean;
    }

    @Override
    public void carregar()
    {
        default_filename = grlExcelPathFacade.getCurrentGrlExcelPath() + Defs.NOME_FILE_FORMA_AQUISICAO;
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
        LogFile.warnMsg(null, "0: PtFormaAquisicaoExcelBean.carregarTabelas()");
        if (lerTabela())
        {
            LogFile.warnMsg(null, "1: PtFormaAquisicaoExcelBean.carregarTabelas()");
            this.patriGrlupdateFacade.escreverDataActualizacaoPtFormaAquisicao(this.dataXLSFile);
            this.patriFormaAquisicaoBean.init();
            return true;
        }
        LogFile.warnMsg(null, "2: PtFormaAquisicaoExcelBean.carregarTabelas()");
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
            LogFile.warnMsg(null, "0: PtFormaAquisicaoExcelBean.lerTabela()\tsheet: " + (sheet == null ? "null" : "not null"));
            this.filename = (this.filename == null) ? default_filename : filename;
            sheet = sheet != null ? sheet : FileManager.getSheetFromXLS_File(filename);

            if (sheet == null)
            {
                LogFile.warnMsg(null, "1: PtFormaAquisicaoExcelBean.lerTabela()");
                return false;
            }
            HSSFRow row;

            Iterator rows = sheet.rowIterator();

            row = (HSSFRow) rows.next();
            if (row == null)
            {
                LogFile.warnMsg(null, "2: PtFormaAquisicaoExcelBean.lerTabela()\tfilename: " + filename);
                return false;
            }
            LogFile.warnMsg(null, "2.1: PtFormaAquisicaoExcelBean.lerTabela()\tfilename: " + filename);
            dataXLSFile = FileManager.lerVersaoTabela(row, filename);

            Date dataTabela = this.patriGrlupdateFacade.dataPtFormaAquisicao();
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
            LogFile.warnMsg(null, "4: PtFormaAquisicaoExcelBean.lerTabela()");
            PtFormaAquisicao reg = null;
            HSSFWorkbook wb = sheet.getWorkbook();
            objDefaultFormat = new DataFormatter();
            objFormulaEvaluator = new HSSFFormulaEvaluator(wb);
            boolean cabecalhoTabela = true;
            while (rows.hasNext())
            {
                row = (HSSFRow) rows.next();
                if (row == null)
                {
                    LogFile.warnMsg(null, "5: PtFormaAquisicaoExcelBean.lerTabela()");
                    return false;
                }
                if (cabecalhoTabela)
                {
                    cabecalhoTabela = false;
                    continue;
                }
                reg = lerCampos(row);
                LogFile.warnMsg(null, "6: PtFormaAquisicaoExcelBean.lerTabela()\reg: " + reg);
                if (reg != null)
                {
                    if(patriFormaAquisicaoFacade.findByDescricao(reg.getDescricao()) == null)
                    {
                        if (escreverTabela(reg))
                        {
                            nreg++;
                        }
                    }
                }

            } // fim da leitura de cada linha 
            LogFile.warnMsg(null, "7: PtFormaAquisicaoExcelBean.lerTabela()\tnreg: " + nreg);
        } catch (FileNotFoundException e)
        {
            LogFile.warnMsg(null, "Não foi possível encontrar o ficheiro \"" + filename + "\"");
            return false;
        } catch (IOException e)
        {
            LogFile.warnMsg(null, "Não foi possível abrir o ficheiro \"" + filename + "\"");
            return false;
        }
        LogFile.warnMsg(null, "8: PtFormaAquisicaoExcelBean.lerTabela()\nreg: " + nreg);
        
        return true;
    }

    /**
     *
     * @param reg
     * @throws ExcepcaoCarregamentoTabelasExcel
     */
    public boolean escreverTabela(PtFormaAquisicao reg) throws ExcepcaoCarregamentoTabelasExcel
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
    public PtFormaAquisicao lerCampos(HSSFRow row) throws ExcepcaoCarregamentoTabelasExcel
    {
        int pkFormaAquisicao;
        String designacao;
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
        pkFormaAquisicao = HSSFCellUtilities.getIntCellValue(objDefaultFormat, objFormulaEvaluator,cells[0]);
        designacao = HSSFCellUtilities.getStringCellValue(objDefaultFormat, objFormulaEvaluator,cells[1]);
        PtFormaAquisicao reg = new PtFormaAquisicao();
        
        reg.setDescricao(designacao);
        
        return reg;
    }

    /**
     *
     * @param reg
     * @return
     */
    public boolean createRegister(PtFormaAquisicao reg)
    {
        try
        {
            this.patriFormaAquisicaoFacade.create(reg);
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
    public boolean editRegister(PtFormaAquisicao reg)
    {
        try
        {
            this.patriFormaAquisicaoFacade.edit(reg);
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
    public void createRegisterWithPersonalizedException(PtFormaAquisicao reg) throws ExcepcaoCarregamentoTabelasExcel
    {
        if (!this.createRegister(reg))
        {
            LogFile.warnMsg(null,
                    "Tentativa de criar Pais " + reg);
            throw new ExcepcaoCarregamentoTabelasExcel();
        }
    }

    /**
     *
     * @param reg
     * @throws ExcepcaoCarregamentoTabelasExcel
     */
    public void editRegisterWithPersonalizedException(PtFormaAquisicao reg) throws ExcepcaoCarregamentoTabelasExcel
    {
        if (!this.editRegister(reg))
        {
            LogFile.warnMsg(null,
                    "Tentativa de actualizar Pais " + reg);
            throw new ExcepcaoCarregamentoTabelasExcel();
        }
    }

}
