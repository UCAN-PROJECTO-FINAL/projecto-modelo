/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package localidade.excel.beans;

import localidade.utils.Defs;
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
import localidade.ejbs.cache.LocLocalidadeCache;
import ejbs.entities.LocLocalidade;
import ejbs.facades.LocGrlExcelPathFacade;
import ejbs.facades.LocGrlUpdateFacade;
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
public class LocLocalidadeExcelBean extends ExcelBeanAbstract implements Serializable
{

    @Resource
    private UserTransaction userTransaction;

    @Inject
    LocLocalidadeCache localidadeBean;

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
    private LocGrlUpdateFacade grlUpdatesFacade;

    @EJB
    private LocLocalidadeFacade localidadeFacade;
    
    @EJB
    private LocGrlExcelPathFacade grlExcelPathFacade;

    private Date dataPaisXLSFile;
    
    private DataFormatter objDefaultFormat;
    private FormulaEvaluator objFormulaEvaluator;

    /**
     * Creates a new instance of Grl_Pais_Excel_Bean
     */
    public LocLocalidadeExcelBean()
    {
        //default_filename = grlExcelPathFacade.getCurrentGrlExcelPath() + Defs.NOME_FILE_LOCALIDADE;
        default_filename = Defs.CAMINHO_LOCALIDADE;
    }

    /**
     *
     * @return
     */
    public static LocLocalidadeExcelBean getInstanciaBean()
    {
        FacesContext context = FacesContext.getCurrentInstance();
        LocLocalidadeExcelBean paisExelBean
                = (LocLocalidadeExcelBean) context.getELContext().
                getELResolver().getValue(FacesContext.getCurrentInstance().
                        getELContext(), null, "grlPaisExcelBean");

        return paisExelBean;
    }

    @Override
    public void carregar()
    {
        default_filename = grlExcelPathFacade.getCurrentGrlExcelPath() + Defs.NOME_FILE_LOCALIDADE;
        if (!carregarLocalidades())
        {
            return;
        }
    }

    public boolean carregarLocalidades()
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
        LogFile.warnMsg(null, "0: LocalidadeExcelBean.carregarTabelas()");
        if (lerTabela())
        {
            LogFile.warnMsg(null, "1: LocalidadeExcelBean.carregarTabelas()");
            this.grlUpdatesFacade.escreverDataActualizacaoLocalidade(this.dataPaisXLSFile);
            this.localidadeBean.init();
            return true;
        }
        LogFile.warnMsg(null, "2: LocalidadeExcelBean.carregarTabelas()");
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
            LogFile.warnMsg(null, "0: LocalidadeExcelBean.lerTabela()\tsheet: " + (sheet == null ? "null" : "not null"));
            this.filename = (this.filename == null) ? default_filename : filename;
            sheet = sheet != null ? sheet : FileManager.getSheetFromXLS_File(filename);

            if (sheet == null)
            {
                LogFile.warnMsg(null, "1: LocalidadeExcelBean.lerTabela()");
                return false;
            }
            HSSFRow row;

            Iterator rows = sheet.rowIterator();

            row = (HSSFRow) rows.next();
            if (row == null)
            {
                LogFile.warnMsg(null, "2: LocalidadeExcelBean.lerTabela()\tfilename: " + filename);
                return false;
            }
            LogFile.warnMsg(null, "2.1: LocalidadeExcelBean.lerTabela()\tfilename: " + filename);
            dataPaisXLSFile = FileManager.lerVersaoTabela(row, filename);

            Date dataTabela = this.grlUpdatesFacade.dataPaisTabela();
            if (dataTabela != null)
            {
                LogFile.warnMsg(null, "dataPaisXLSFile: " + DataUtils.toStringFull(dataPaisXLSFile));
            }
            LogFile.warnMsg(null, "dataTabela: " + DataUtils.toStringFull(dataTabela));
            if (!DataUtils.isMoreRecent(dataPaisXLSFile, dataTabela))
            {
                LogFile.warnMsg(
                        null, "A data do nova versão (" + DataUtils.toStringFull(dataPaisXLSFile)
                        + ") do ficheiro \"" + this.filename + "\" eh anterior a data da versão instalada ("
                        + DataUtils.toStringFull(dataTabela) + ")"
                );
                return false;
                
            }
            LogFile.warnMsg(null, "4: LocalidadeExcelBean.lerTabela()");
            LocLocalidade reg = null;
            HSSFWorkbook wb = sheet.getWorkbook();
            objDefaultFormat = new DataFormatter();
            objFormulaEvaluator = new HSSFFormulaEvaluator(wb);
            boolean cabecalhoTabela = true;
            while (rows.hasNext())
            {
                row = (HSSFRow) rows.next();
                if (row == null)
                {
                    LogFile.warnMsg(null, "5: LocalidadeExcelBean.lerTabela()");
                    return false;
                }
                if (cabecalhoTabela)
                {
                    cabecalhoTabela = false;
                    continue;
                }
                reg = lerCampos(row);
                LogFile.warnMsg(null, "6: LocalidadeExcelBean.lerTabela()\reg: " + this.localidadeFacade.toString(reg));
                if (reg != null)
                {
                    if(localidadeFacade.findByPkLocalidade(reg.getPkLocLocalidade()) == null)
                    {
                        if (escreverTabela(reg))
                        {
                            nreg++;
                        }
                    }
                }

            } // fim da leitura de cada linha 
            LogFile.warnMsg(null, "7: LocalidadeExcelBean.lerTabela()\tnreg: " + nreg);
        } catch (FileNotFoundException e)
        {
            LogFile.warnMsg(null, "Não foi possível encontrar o ficheiro \"" + filename + "\"");
            return false;
        } catch (IOException e)
        {
            LogFile.warnMsg(null, "Não foi possível abrir o ficheiro \"" + filename + "\"");
            return false;
        }
        LogFile.warnMsg(null, "8: LocalidadeExcelBean.lerTabela()\nreg: " + nreg);
        
        return true;
    }

    /**
     *
     * @param reg
     * @throws ExcepcaoCarregamentoTabelasExcel
     */
    public boolean escreverTabela(LocLocalidade reg) throws ExcepcaoCarregamentoTabelasExcel
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
    public LocLocalidade lerCampos(HSSFRow row) throws ExcepcaoCarregamentoTabelasExcel
    {
        String pkLocalidade, designacao, fkLocalidade,msg,isDistrictString="";
        boolean isDistrict;
        HSSFCell cells[];
        cells = new HSSFCell[3];

        for (int i = 0; i < 3; i++)
        {

            cells[i] = row.getCell((short) i);
            if (HSSFCellUtilities.isCellEmpty(cells[i]) && i<2)
            {
                return null;
            }
        }

        pkLocalidade = HSSFCellUtilities.getStringCellValue(objDefaultFormat, objFormulaEvaluator,cells[0]);
        if (pkLocalidade == null)
        {
                msg = "Erro na chave primaria do pais";
                LogFile.warnMsg(null, msg);
                throw new ExcepcaoCarregamentoTabelasExcel();
        }
        designacao = HSSFCellUtilities.getStringCellValue(objDefaultFormat, objFormulaEvaluator,cells[1]);
        fkLocalidade = this.localidadeFacade.getFkLocalidade(pkLocalidade);
        if (!HSSFCellUtilities.isCellEmpty(cells[2]))
            isDistrictString = HSSFCellUtilities.getStringCellValue(objDefaultFormat, objFormulaEvaluator,cells[2]);
        //System.err.println("___________________"+isDistrictString);
        

        LocLocalidade reg = new LocLocalidade();
        reg.setPkLocLocalidade(pkLocalidade);
        reg.setDesignacao(designacao);
        if( ! isDistrictString.isEmpty())
        {
            isDistrict = getEquivalent(isDistrictString);
            reg.setEhDestrito(isDistrict);
        }

        LocLocalidade regPai = fkLocalidade == null ? null : this.localidadeFacade.find(fkLocalidade);
        if (regPai != null)
        {
            //System.err.println("5: LocalidadeCarregarBean.processarRow()\tregPai: " + locLocalidadeFacade.toString(regPai));
        }
        if ((fkLocalidade != null) && (regPai == null))
        {
            return null;
        }
        reg.setFkLocLocalidade(regPai);
        return reg;
    }

    public boolean getEquivalent(String value)
    {
        value = value.trim();
        
        if(value.equals("sim"))
            return true;
        return false;
    }
    
    /**
     *
     * @param reg
     * @return
     */
    public boolean createRegister(LocLocalidade reg)
    {
        try
        {
            this.localidadeFacade.create(reg);
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
    public boolean editRegister(LocLocalidade reg)
    {
        try
        {
            this.localidadeFacade.edit(reg);
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
    public void createRegisterWithPersonalizedException(LocLocalidade reg) throws ExcepcaoCarregamentoTabelasExcel
    {
        if (!this.createRegister(reg))
        {
            LogFile.warnMsg(null,
                    "Tentativa de criar Pais " + this.localidadeFacade.toString(reg));
            throw new ExcepcaoCarregamentoTabelasExcel();
        }
    }

    /**
     *
     * @param reg
     * @throws ExcepcaoCarregamentoTabelasExcel
     */
    public void editRegisterWithPersonalizedException(LocLocalidade reg) throws ExcepcaoCarregamentoTabelasExcel
    {
        if (!this.editRegister(reg))
        {
            LogFile.warnMsg(null,
                    "Tentativa de actualizar Pais " + this.localidadeFacade.toString(reg));
            throw new ExcepcaoCarregamentoTabelasExcel();
        }
    }

}
