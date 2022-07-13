/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geral.excel;

import ejbs.entities.FinCategoriaSubcategoria;
import ejbs.entities.FinCategorias;
import ejbs.facades.FinCategoriaSubcategoriaFacade;
import ejbs.facades.GrlExcelPathFacade;
import ejbs.facades.GrlUpdateFacade;
//import excel.utils.ExcelBeanAbstract;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.Iterator;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.ss.usermodel.Row;
import utils.DataUtils;
import utils.Defs;
import utils.ExcepcaoCarregamentoTabelasExcel;
import utils.FileManager;
import utils.excel.ExcelBeanAbstract;

/**
 *
 * @author gemix
 */
@Named(value = "finSubCategotiaExcelBean")
@ViewScoped
public class FinSubCategotiaExcelBean extends ExcelBeanAbstract implements Serializable
{

    @EJB
    private FinCategoriaSubcategoriaFacade finCategoriaSubcategoriaFacade;
     @EJB
    private GrlUpdateFacade grlUpdatesFacade;
    @EJB
    private GrlExcelPathFacade grlExcelPathFacade;
    
    private Date dataCategoriaXLSFile;

    /**
     * Creates a new instance of GrlTipoDeProvinciaExcelBean
     */
    @Resource
    private UserTransaction userTransaction;

    /**
     *
     */
    public FinSubCategotiaExcelBean()
    {
        default_filename = Defs.CAMINHO_SUBCATEGORIA;
    }

    /**
     *
     * @return
     */
    public static FinSubCategotiaExcelBean getInstanciaBean()
    {
        FacesContext context = FacesContext.getCurrentInstance();
        FinSubCategotiaExcelBean finSubCategotiaExcelBean
                = (FinSubCategotiaExcelBean) context.getELContext().
                        getELResolver().getValue(FacesContext.getCurrentInstance().
                                getELContext(), null, "finSubCategotiaExcelBean");

        return finSubCategotiaExcelBean;
    }
    
     @Override
    public void carregar() {
        default_filename = grlExcelPathFacade.getCurrentGrlExcelPath() + Defs.NOME_FILE_SUBCATEGORIA;
        if (!carregarSubCategoria()) {
            return;
        }
    }

    public boolean carregarSubCategoria() {
        try {
//System.out.println("0: CarregarTabelas.carregarTabelas()");            
            try {
                this.userTransaction.begin();
            } catch (NotSupportedException | SystemException e) {
                //LogFile.warnMsg(null, "Falha no estabelcecimento da demarcação da transacao associada a actualizacao do ficheiro excel de bancos");
            }
            if (carregarTabelas()) {
                //LogFile.warnMsg(null, "o carregamento/actualizacao do ficheiro excel de bancos foi realizado com sucesso");
            }
            try {
                this.userTransaction.commit();
                return true;
            } catch (IllegalStateException | SecurityException | HeuristicMixedException | HeuristicRollbackException | RollbackException | SystemException e) {
                //LogFile.warnMsg(null, "Falha no \"commit\" da actualizacao do ficheiro excel de bancos");
            }
//System.out.println("1: CarregarTabelas.carregarTabelas()");                        
        } catch (ExcepcaoCarregamentoTabelasExcel e) {
//System.out.println("2: CarregarTabelas.carregarTabelas()");                        
            try {
                this.userTransaction.rollback();
            } catch (IllegalStateException | SecurityException | SystemException ex) {
                //LogFile.warnMsg(null, "Falha no \"rollback\" da tentativa de actualizacao do ficheiro excel de paises");
            }
        }
        return false;
    }
    
    /**
     *
     * @return
     * @throws utils.ExcepcaoCarregamentoTabelasExcel
     */
    
    @SuppressWarnings("empty-statement")
    @Override
    public boolean carregarTabelas() throws ExcepcaoCarregamentoTabelasExcel {
        if (lerTabela()) {
            //LogFile.warnMsg(null, "1: FinTipoContaExcelBean.carregarTabelas()");
            this.grlUpdatesFacade.escreverDataActualizacaoSubCategoriaTabela(this.dataCategoriaXLSFile);
            //this.finTipoContaBean.init();
            //this.tipoContaCache.init();
            return true;
        }
        //sLogFile.warnMsg(null, "2: FinTipoContaExcelBean.carregarTabelas()");
        return false;
    }

    @Override
    public boolean lerTabela() throws ExcepcaoCarregamentoTabelasExcel {
           int nreg = 0;
        try {
            //LogFile.warnMsg(null, "0: FinTipoContaExcelBean.lerTabela()\tsheet: " + (sheet == null ? "null" : "not null"));
            this.filename = (this.filename == null) ? default_filename : filename;
            sheet = sheet != null ? sheet : FileManager.getSheetFromXLS_File(filename);

            if (sheet == null) {
               // LogFile.warnMsg(null, "1: FinTipoContaExcelBean.lerTabela()");
                return false;
            }
            HSSFRow row;

            Iterator rows = sheet.rowIterator();

            row = (HSSFRow) rows.next();
            if (row == null) {
                //LogFile.warnMsg(null, "2: FinTipoContaExcelBean.lerTabela()\tfilename: " + filename);
                return false;
            }
            //LogFile.warnMsg(null, "2.1: FinTipoContaExcelBean.lerTabela()\tfilename: " + filename);
            dataCategoriaXLSFile = FileManager.lerVersaoTabela(row, filename);

            Date dataTabela = this.grlUpdatesFacade.dataSubCategoriaTabela();
            if (dataTabela != null) {
              //  LogFile.warnMsg(null, "dataXLSFile: " + DataUtils.toStringFull(dataXLSFile));
            }
            if (!DataUtils.isMoreRecent(dataCategoriaXLSFile, dataTabela)) {
//                LogFile.warnMsg(
//                        null, "A data do nova versão (" + DataUtils.toStringFull(dataXLSFile)
//                        + ") do ficheiro \"" + this.filename + "\" eh anterior a data da versão instalada ("
//                        + DataUtils.toStringFull(dataXLSFile) + ")"
//                );
                return false;

            }
            //LogFile.warnMsg(null, "4: FinTipoContaExcelBean.lerTabela()");
            
            FinCategoriaSubcategoria reg = null;
            boolean cabecalhoTabela = true;
            while (rows.hasNext()) {
                row = (HSSFRow) rows.next();
                if (row == null) {
                    //LogFile.warnMsg(null, "5: FinTipoContaExcelBean.lerTabela()");
                    return false;
                }
                if (cabecalhoTabela) {
                    cabecalhoTabela = false;
                    continue;
                }
                reg = lerCampos(row);
                //LogFile.warnMsg(null, "6: FinTipoContaExcelBean.lerTabela()\reg: " + this.tipoCartaoFacade.toString(reg));
                if (reg != null) {
                    if (finCategoriaSubcategoriaFacade.findFinSubCategoria(reg.getPkCategoriaSubcategoria()) == null) {
                        if (createRegister(reg)) {
                            nreg++;
                        }
                    }
                }

            } // fim da leitura de cada linha 
            //LogFile.warnMsg(null, "7: FinTipoContaExcelBean.lerTabela()\tnreg: " + nreg);
        } catch (FileNotFoundException e) {
            //LogFile.warnMsg(null, "Não foi possível encontrar o ficheiro \"" + filename + "\"");
            return false;
        } catch (IOException e) {
            //LogFile.warnMsg(null, "Não foi possível abrir o ficheiro \"" + filename + "\"");
            return false;
        }
        //LogFile.warnMsg(null, "8: FinTipoContaExcelBean.lerTabela()\nreg: " + nreg);

        return true;
    }


    /**
     *
     * @param reg
     */
    public void escreverProvinciaTabela(FinCategoriaSubcategoria reg)
    {
        if (finCategoriaSubcategoriaFacade.existeRegisto(reg.getPkCategoriaSubcategoria()) == false)
        {
            this.createRegister(reg);
        }
        else
        {
            this.editRegister(reg);
        }
    }

    /**
     *
     * @param row
     * @return
     */
    public FinCategoriaSubcategoria lerCampos(HSSFRow row)
    {
        int pk_categoria;
        String descricao;
        int fk_Categoria;

        final int PK_ID_CATEGORIA = 0;
        final int DESCRICAO_CATEGORIA = 1;
        final int FK_ID_TIPOCATEGORIA = 2;

        FinCategoriaSubcategoria reg = new FinCategoriaSubcategoria();

//        HSSFCell cell;
        for (int cn = 0; cn < row.getLastCellNum(); cn++)
        {
            HSSFCell cell = row.getCell(cn, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);

            switch (cn)
            {
                case PK_ID_CATEGORIA:
                    pk_categoria = (int) cell.getNumericCellValue();
                    reg.setPkCategoriaSubcategoria(pk_categoria);
                    break;

                case DESCRICAO_CATEGORIA:
                    descricao = cell.getStringCellValue().trim();
                    reg.setDescricao(descricao);
                    break;

                case FK_ID_TIPOCATEGORIA:
                    if (cell == null)
                    {
                        reg.setFkCategoria(null);
                    }
                    else
                    {
                        fk_Categoria = (int) cell.getNumericCellValue();
                        reg.setFkCategoria(new FinCategorias(fk_Categoria));
                    }
                    break;
            }
        }

        return reg;
    }

    /**
     *
     * @param reg
     * @return
     */
    public boolean createRegister(FinCategoriaSubcategoria reg)
    {
        try
        {
            this.userTransaction.begin();
            this.finCategoriaSubcategoriaFacade.create(reg);
            this.userTransaction.commit();
            return true;
        }
        catch (Exception e)
        {
            try
            {
                this.userTransaction.rollback();
            }
            catch (IllegalStateException | SecurityException | SystemException ex)
            {
                //System.err.println("Roolback: " + ex.toString());
            }
        }
        return false;
    }

    /**
     *
     * @param reg
     * @return
     */
    public boolean editRegister(FinCategoriaSubcategoria reg)
    {
        try
        {
            this.userTransaction.begin();
            this.finCategoriaSubcategoriaFacade.edit(reg);
            this.userTransaction.commit();
            return true;
        }
        catch (Exception e)
        {
            try
            {
                this.userTransaction.rollback();
            }
            catch (IllegalStateException | SecurityException | SystemException ex)
            {
                //System.err.println("Roolback: " + ex.toString());
            }
        }
        return false;
    }

}
