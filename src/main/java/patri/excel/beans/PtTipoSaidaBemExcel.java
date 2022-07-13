/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patri.excel.beans;

import patri.ejbs.cache.PtTipoSaidaBemCache;
import ejbs.entities.PtTipoSaidaBem;
import ejbs.facades.PtTipoSaidaBemFacade;
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
public class PtTipoSaidaBemExcel extends ExcelBeanAbstract implements Serializable
{

    @Resource
    private UserTransaction userTransaction;

    @Inject
    PtTipoSaidaBemCache ptTipoSaidaBemBean;

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
    private PtTipoSaidaBemFacade ptTipoSaidaBemFacade;
    
    @EJB
    private PtExcelPathFacade grlExcelPathFacade;

    private Date dataXLSFile;
    
    private DataFormatter objDefaultFormat;
    private FormulaEvaluator objFormulaEvaluator;
    

    /**
     * Creates a new instance of PtTipoSaidaBemExcelBean
     */
    public PtTipoSaidaBemExcel()
    {
        //default_filename = grlExcelPathFacade.getCurrentGrlExcelPath() + Defs.NOME_FILE_ESTRUTURA_FISICA
        default_filename = Defs.CAMINHO_TIPO_SAIDA;
    }

    /**
     *
     * @return
     */
    public static PtTipoSaidaBemExcel getInstanciaBean()
    {
        FacesContext context = FacesContext.getCurrentInstance();
        PtTipoSaidaBemExcel exelBean
                = (PtTipoSaidaBemExcel) context.getELContext().
                getELResolver().getValue(FacesContext.getCurrentInstance().
                        getELContext(), null, "ptTipoSaidaBemExcelBean");

        return exelBean;
    }

    @Override
    public void carregar()
    {
        default_filename = grlExcelPathFacade.getCurrentGrlExcelPath() + Defs.NOME_FILE_TIPO_SAIDA;
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
        LogFile.warnMsg(null, "0: PtTipoSaidaBemExcelBean.carregarTabelas()");
        if (lerTabela())
        {
            LogFile.warnMsg(null, "1: PtTipoSaidaBemExcelBean.carregarTabelas()");
            this.patriGrlupdateFacade.escreverDataActualizacaoPtTipoSaidaBem(this.dataXLSFile);
            this.ptTipoSaidaBemBean.init();
            return true;
        }
        LogFile.warnMsg(null, "2: PtTipoSaidaBemExcelBean.carregarTabelas()");
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
            LogFile.warnMsg(null, "0: PtTipoSaidaBemExcelBean.lerTabela()\tsheet: " + (sheet == null ? "null" : "not null"));
            this.filename = (this.filename == null) ? default_filename : filename;
            sheet = sheet != null ? sheet : FileManager.getSheetFromXLS_File(filename);

            if (sheet == null)
            {
                LogFile.warnMsg(null, "1: PtTipoSaidaBemExcelBean.lerTabela()");
                return false;
            }
            HSSFRow row;

            Iterator rows = sheet.rowIterator();

            row = (HSSFRow) rows.next();
            if (row == null)
            {
                LogFile.warnMsg(null, "2: PtTipoSaidaBemExcelBean.lerTabela()\tfilename: " + filename);
                return false;
            }
            LogFile.warnMsg(null, "2.1: PtTipoSaidaBemExcelBean.lerTabela()\tfilename: " + filename);
            dataXLSFile = FileManager.lerVersaoTabela(row, filename);

            Date dataTabela = this.patriGrlupdateFacade.dataPtTipoSaidaBemTabela();
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
            LogFile.warnMsg(null, "4: PtTipoSaidaBemExcelBean.lerTabela()");
            PtTipoSaidaBem reg = null;
            HSSFWorkbook wb = sheet.getWorkbook();
            objDefaultFormat = new DataFormatter();
            objFormulaEvaluator = new HSSFFormulaEvaluator(wb);
            boolean cabecalhoTabela = true;
            while (rows.hasNext())
            {
                row = (HSSFRow) rows.next();
                if (row == null)
                {
                    LogFile.warnMsg(null, "5: PtTipoSaidaBemExcelBean.lerTabela()");
                    return false;
                }
                if (cabecalhoTabela)
                {
                    cabecalhoTabela = false;
                    continue;
                }
                reg = lerCampos(row);
                LogFile.warnMsg(null, "6: PtTipoSaidaBemExcelBean.lerTabela()\reg: " + reg);
                if (reg != null)
                {
                    if(ptTipoSaidaBemFacade.findByDescricao(reg.getDescricao()) == null)
                    {
                        if (escreverTabela(reg))
                        {
                            nreg++;
                        }
                    }
                }

            } // fim da leitura de cada linha 
            LogFile.warnMsg(null, "7: PtTipoSaidaBemExcelBean.lerTabela()\tnreg: " + nreg);
        } catch (FileNotFoundException e)
        {
            LogFile.warnMsg(null, "Não foi possível encontrar o ficheiro \"" + filename + "\"");
            return false;
        } catch (IOException e)
        {
            LogFile.warnMsg(null, "Não foi possível abrir o ficheiro \"" + filename + "\"");
            return false;
        }
        LogFile.warnMsg(null, "8: PtTipoSaidaBemExcelBean.lerTabela()\nreg: " + nreg);
        
        return true;
    }

    /**
     *
     * @param reg
     * @throws ExcepcaoCarregamentoTabelasExcel
     */
    public boolean escreverTabela(PtTipoSaidaBem reg) throws ExcepcaoCarregamentoTabelasExcel
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
    public PtTipoSaidaBem lerCampos(HSSFRow row) throws ExcepcaoCarregamentoTabelasExcel
    {
        int pkPtTipoSaidaBem;
        String descricao, fkPtTipoSaidaBem,fkLocalidade;
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
        descricao = HSSFCellUtilities.getStringCellValue(objDefaultFormat, objFormulaEvaluator,cells[1]);
        PtTipoSaidaBem reg = new PtTipoSaidaBem();
        
        reg.setDescricao(descricao);
        return reg;
    }

    /**
     *
     * @param reg
     * @return
     */
    public boolean createRegister(PtTipoSaidaBem reg)
    {
        try
        {
            this.ptTipoSaidaBemFacade.create(reg);
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
    public boolean editRegister(PtTipoSaidaBem reg)
    {
        try
        {
            this.ptTipoSaidaBemFacade.edit(reg);
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
    public void createRegisterWithPersonalizedException(PtTipoSaidaBem reg) throws ExcepcaoCarregamentoTabelasExcel
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
    public void editRegisterWithPersonalizedException(PtTipoSaidaBem reg) throws ExcepcaoCarregamentoTabelasExcel
    {
        if (!this.editRegister(reg))
        {
            LogFile.warnMsg(null,
                    "Tentativa de actualizar Pais " + reg);
            throw new ExcepcaoCarregamentoTabelasExcel();
        }
    }

}
