/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seg.excel;

import ejbs.cache.SegFuncionalidadeCache;
import ejbs.cache.SegTipoFuncionalidadeCache;
import ejbs.entities.SegFuncionalidade;
import ejbs.facades.SegFuncionalidadeFacade;
import ejbs.facades.SegFuncionalidadeFormFacade;
import ejbs.facades.SegUpdateFacade;
import utils.excel.ExcelBeanAbstract;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
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
import seg.utils.SegFuncionalidadeFormsLista;
import utils.DataUtils;
import utils.ExcepcaoCarregamentoTabelasExcel;
import utils.FileManager;
import utils.HSSFCellUtilities;
import utils.mensagens.LogFile;

/**
 *
 * @author helena
 */
@Named
@ApplicationScoped

public class SegFuncionalidadeExcelBean extends ExcelBeanAbstract implements Serializable
{

    @EJB
    private SegFuncionalidadeFormFacade segFuncionalidadeFormFacade;

    @Inject
    private SegTipoFuncionalidadeCache segTipoFuncionalidadeCache;

    @EJB
    private SegFuncionalidadeFacade segFuncionalidadeFacade;

    @EJB
    private SegUpdateFacade segUpdatesFacade;

    @Inject
    private SegFuncionalidadeCache segFuncionalidadeCache;

    @Resource
    private UserTransaction userTransaction;

    private Date dataXLSFile;

    private DataFormatter objDefaultFormat;
    private FormulaEvaluator objFormulaEvaluator;

    public SegFuncionalidadeExcelBean()
    {
        default_filename = utils.Defs.FILE_FUNCIONALIDADE_SEG;
    }

    /**
     * @version 2
     * @author helena
     */
    public void carregar()
    {
        try
        {
            try
            {
                this.userTransaction.begin();
            }
            catch (NotSupportedException | SystemException e)
            {
                //System.err.println("Falha no estabelcecimento da demarcação da transacao associada a actualizacao do ficheiro excel de funcionalidade");
            }
            //System.err.println("0: SegFuncionalidadeExcelBean.carregar()");
            if (carregarTabelas())
            {
                //System.err.println("1: SegFuncionalidadeExcelBean.carregar()");

                try
                {
                    //System.err.println("2: SegFuncionalidadeExcelBean.carregar()");
                    this.userTransaction.commit();
                    //System.err.println("2.1: SegFuncionalidadeExcelBean.carregar()");

                }
                catch (IllegalStateException | SecurityException | HeuristicMixedException | HeuristicRollbackException | RollbackException | SystemException e)
                {
                    //System.err.println("3: SegFuncionalidadeExcelBean.carregar()");
                    //System.err.println("Falha no \"commit\" da actualizacao do ficheiro excel de funcionalidade");
                }
            }
            else
            {
                try
                {
                    //System.err.println("4: SegFuncionalidadeExcelBean.carregar()");
                    this.userTransaction.rollback();
                }
                catch (IllegalStateException | SecurityException | SystemException ex)
                {
                    //System.err.println("5: SegFuncionalidadeExcelBean.carregar()");
                    //System.err.println("Falha no \"rollback\" da tentativa de actualizacao do ficheiro excel de funcionalidade");
                }
            }
        }
        catch (ExcepcaoCarregamentoTabelasExcel e)
        {
            //System.err.println("6: SegFuncionalidadeExcelBean.carregar()");
            try
            {
                //System.err.println("7: SegFuncionalidadeExcelBean.carregar()");
                this.userTransaction.rollback();
            }
            catch (IllegalStateException | SecurityException | SystemException ex)
            {
                //System.err.println("8: SegFuncionalidadeExcelBean.carregar()");
                //System.err.println("Falha no \"rollback\" da tentativa de actualizacao do ficheiro excel de funcionalidade");
            }
        }
        //System.err.println("9: SegFuncionalidadeExcelBean.carregar()");
    }

    /**
     * @version 1
     * @author Helena
     * @return
     */
    public static SegFuncionalidadeExcelBean getInstanciaBean()
    {
        FacesContext context = FacesContext.getCurrentInstance();
        SegFuncionalidadeExcelBean segPerfilExcelBean
            = (SegFuncionalidadeExcelBean) context.getELContext().
                getELResolver().getValue(FacesContext.getCurrentInstance().
                    getELContext(), null, "segFuncionalidadesExcelBean");

        return segPerfilExcelBean;
    }

    /**
     * @version 2
     * @author helena
     * @return true/false
     * @throws seg.utils.ExcepcaoCarregamentoTabelasExcel
     */
    @SuppressWarnings("empty-statement")
    @Override
    public boolean carregarTabelas() throws ExcepcaoCarregamentoTabelasExcel
    {
        //System.err.println("0: SegFuncionalidadeExcelBean.carregarTabela()");
        if (lerTabela())
        {
            //System.err.println("1: SegFuncionalidadeExcelBean.carregarTabela()");
            this.segUpdatesFacade.escreverDataFuncionalidadeTabela(dataXLSFile);
            this.segFuncionalidadeCache.init();
            //System.err.println("2: SegFuncionalidadeExcelBean.carregarTabela()");
            return true;
        }
        //System.err.println("2: SegFuncionalidadeExcelBean.carregarTabela()");
        return false;
    }

    /**
     * @version 2
     * @author helena
     * @return
     * @throws utils.ExcepcaoCarregamentoTabelasExcel
     * @throws seg.utils.ExcepcaoCarregamentoTabelasExcel
     */
    @Override
    public boolean lerTabela() throws ExcepcaoCarregamentoTabelasExcel
    {
        int nreg = 0;
        String msg;
        List<SegFuncionalidade> segFuncionalidades = new ArrayList();
        List<String> messagesSegFuncionalidadeDuplicados = new ArrayList();

        //System.err.println("0: SegFuncionalidadeExcelBean.lerTabela()");
        try
        {
            this.filename = (this.filename == null) ? default_filename : filename;
            //System.err.println("1: SegFuncionalidadeExcelBean.lerTabela()\tfilename: " + filename);
            sheet = sheet != null ? sheet : FileManager.getSheetFromXLS_File(filename);
            //System.err.println("1.1: SegFuncionalidadeExcelBean.lerTabela()\tfilename: " + filename);
        }
        catch (FileNotFoundException e)
        {
            //System.err.println("2: SegFuncionalidadeExcelBean.lerTabela()");
            return false;
        }
        catch (IOException e)
        {
            //System.err.println("3: SegFuncionalidadeExcelBean.lerTabela()");
            return false;
        }

        try
        {
            HSSFRow row;

            //Pega o iterador que ira varrer todas as linhas desta pagina
            Iterator rows = sheet.rowIterator();

            row = (HSSFRow) rows.next();
            if (row == null)
            {
                //System.err.println("4: SegFuncionalidadeExcelBean.lerTabela()");
                return false;
            }
            //System.err.println("5: SegFuncionalidadeExcelBean.lerTabela()\nfilename: " + filename);

            dataXLSFile = FileManager.lerVersaoTabela(row, filename);
            //System.err.println("6: SegFuncionalidadeExcelBean.lerTabela()\ndataXLSFile: "
//                + DataUtils.toStringFull(dataXLSFile));
            Date dataTabela = this.segUpdatesFacade.dataFuncionalidade();
            //System.err.println("6.1: SegFuncionalidadeExcelBean.lerTabela()\ndataTabela: "
//                + DataUtils.toStringFull(dataTabela));
            if (!DataUtils.isMoreRecent(dataXLSFile, dataTabela))
            {
                //System.err.println("7: SegFuncionalidadeExcelBean.lerTabela()");
                return false;
            }

            //variavel que verifica se o iterador de linhas esta na primeira linha ou nao
            boolean firstRow = true;

            //como cada linha do registo ira representar um dado na bd, esses valores que
            //se encontram em cada linha tem que ser transformado num registo que pode ser
            //armazenado na base de dados, esntao criaremos com esta variavel, para depois 
            //gravar na bd
            SegFuncionalidade segFuncionalidade = null;
            SegFuncionalidadeFormsLista segFuncionalidadeFormsLista = null;
            //System.err.println("8: SegFuncionalidadeExcelBean.lerTabela()");
            //vai correr todas as linhas do ficheiro excel enquanto tiver dados na proxima linha
            int n = this.segFuncionalidadeFormFacade.removeAll();

            HSSFWorkbook wb = sheet.getWorkbook();
            objDefaultFormat = new DataFormatter();
            objFormulaEvaluator = new HSSFFormulaEvaluator(wb);

            //System.err.println("8.1: SegFuncionalidadeExcelBean.lerTabela()\nForam apagados " + n + " registos");
            while (rows.hasNext())
            {
                //System.err.println("8.2: SegFuncionalidadeExcelBean.lerTabela()");
                //pega linha
                row = (HSSFRow) rows.next();

                //se for a primeira linha, ira pular, pois a primeira linha sao apenas titulos
                if (firstRow)
                {
                    if (row == null)
                    {
                        msg = "No ficheiro \"" + utils.Defs.FILE_FUNCIONALIDADE_SEG
                            + "\", a tabela existente na folha \"" + utils.Defs.SHEET_FUNCIONALIDADE
                            + "\" nao tem cabecalho";
                        LogFile.warnMsg(null, msg);
                        //System.err.println("8.3: SegFuncionalidadeExcelBean.lerTabela()");
                        return false;
                    }
                    firstRow = false;
                    continue;
                }
                //System.err.println("8.4: SegFuncionalidadeExcelBean.lerTabela()");
                //caso nao seja a primeira linha, converto os dados dessa linha num registo que 
                //pode ser posto na base de dados
                segFuncionalidadeFormsLista = lerCampos(row);

                //System.err.println("9: SegFuncionalidadeExcelBean.lerTabela()\treg: "
//                    + (segFuncionalidadeFormsLista == null ? "" : this.segFuncionalidadeFacade.toString(segFuncionalidade)));
                if (segFuncionalidadeFormsLista != null)
                {
                    segFuncionalidade = segFuncionalidadeFormsLista.getSegFuncionalidade();
                    List<Integer> lista = segFuncionalidadeFormsLista.getPkFormsLista();
                    //System.err.println("9.1: SegFuncionalidadeExcelBean.lerTabela()\tlista: " + ListUtils.toString(lista));
                }
                if (segFuncionalidadeFormsLista != null)
                {
                    //System.err.println("10: SegFuncionalidadeExcelBean.lerTabela()\tsegFuncionalidade: "
//                        + segFuncionalidadeFacade.toString(segFuncionalidade));
                    SegFuncionalidade segFuncionalidadeConflito = this.segFuncionalidadeCache.temDuplicado(segFuncionalidades, segFuncionalidade);
                    //System.err.println("10.0: SegFuncionalidadeExcelBean.lerTabela()\tsegFuncionalidadeConflito: "
//                        + (segFuncionalidadeConflito == null ? "null" : segFuncionalidadeFacade.toString(segFuncionalidadeConflito)));
                    if (segFuncionalidadeConflito != null)
                    {
                        msg = "No ficheiro excel \"" + this.filename + "\", o registo "
                            + segFuncionalidadeFacade.toString(segFuncionalidade)
                            + " tem dados coincidentes com o registo " + segFuncionalidadeFacade.toString(segFuncionalidadeConflito);
                        messagesSegFuncionalidadeDuplicados.add(msg);
                        if (messagesSegFuncionalidadeDuplicados.size() >= 3)
                        {
                            LogFile.erroMsg(null, messagesSegFuncionalidadeDuplicados);
                            return false;
                        }

                    }
                    else
                    {
                        escreverTabela(segFuncionalidade);
                        segFuncionalidadeFormFacade.create(segFuncionalidadeFormsLista);
                        segFuncionalidades.add(segFuncionalidade);
                        nreg++;
                    }
                    //System.err.println("10.1: SegFuncionalidadeExcelBean.lerTabela()\nnreg: " + nreg);
                }
                else
                {
                    //System.err.println("10.2: SegFuncionalidadeExcelBean.lerTabela()\tnreg: " + nreg);
                    if (!messagesSegFuncionalidadeDuplicados.isEmpty())
                    {
                        LogFile.erroMsg(null, messagesSegFuncionalidadeDuplicados);
                        return false;
                    }
                    //System.err.println("10.3: SegFuncionalidadeExcelBean.lerTabela()\tnreg: " + nreg);
//System.exit(1);
                    return nreg != 0;
                }
                //System.err.println("10.4: SegFuncionalidadeExcelBean.lerTabela()");

                //System.err.println("12: AmbCidCapitulosBean.carregarCapitulosTabela()");
            } // fim da leitura de cada linha 
        }
        catch (ExcepcaoCarregamentoTabelasExcel e)
        {
            //System.err.println("11: SegFuncionalidadeExcelBean.lerTabela()");
            return false;
        }
        //System.err.println("12: SegFuncionalidadeExcelBean.lerTabela()\tnreg: " + nreg);
        return nreg != 0;
    }

    /**
     * @version 2
     * @author helena
     * @param reg
     * @throws utils.ExcepcaoCarregamentoTabelasExcel
     */
    public void escreverTabela(SegFuncionalidade reg) throws ExcepcaoCarregamentoTabelasExcel
    {
//System.err.println("0: SegFuncionalidadeExcelBean.escreverTabela()");
        if (reg.getPkSegFuncionalidade() <= 0)
        {
//System.err.println("1: SegFuncionalidadeExcelBean.escreverTabela()");
            LogFile.erroMsg(null,
                "Tentativa de registar Tipo Funcionalidade " + this.segFuncionalidadeFacade.toString(reg));
            throw new ExcepcaoCarregamentoTabelasExcel();
        }
//System.err.println("2: SegFuncionalidadeExcelBean.escreverTabela()");
//		SegFuncionalidade funcionalidadeByDescricao = this.segFuncionalidadeFacade.findByDescricao(reg.getDescricao());;
//System.err.println("3: SegFuncionalidadeExcelBean.escreverTabela()");        
        SegFuncionalidade funcionalidadeByID = this.segFuncionalidadeFacade.find(reg.getPkSegFuncionalidade());
//System.err.println("4: SegFuncionalidadeExcelBean.escreverTabela()");
        String msg;

        if (funcionalidadeByID == null)
        {
//System.err.println("6: SegFuncionalidadeExcelBean.escreverTabela()");                
            this.createRegisterWithPersonalizedException(reg);
//System.err.println("6.1: SegFuncionalidadeExcelBean.escreverTabela()");
////			// hipotese 2
        }

        else
        {
//System.err.println("7: SegFuncionalidadeExcelBean.escreverTabela()");            
            this.editRegisterWithPersonalizedException(reg);
//System.err.println("7.1: SegFuncionalidadeExcelBean.escreverTabela()");
        }
    }

    /**
     * @version 2
     * @author helena
     * @param row
     * @return SegFuncionalidade
     * @throws seg.utils.ExcepcaoCarregamentoTabelasExcel
     */
    public SegFuncionalidadeFormsLista lerCampos(HSSFRow row) throws ExcepcaoCarregamentoTabelasExcel
    {

        Integer pk_id_funcionalidade;
        String descricao, msg;
        Integer fk_id_funcionalidade_pai;
        Integer fk_id_tipo_funcionalidade;
        String formsLista;

        HSSFCell cells[];
        int numeroCelulas = 6;
        cells = new HSSFCell[numeroCelulas];

        SegFuncionalidade segFuncionalidade = new SegFuncionalidade();
        SegFuncionalidadeFormsLista segFuncionalidadeFormsLista = new SegFuncionalidadeFormsLista();
        //System.err.println("0: SegFuncionalidadeExcelBean.lerCampos()");
        for (int i = 0; i < numeroCelulas; i++)
        {
            cells[i] = row.getCell((short) i);
            if (i == numeroCelulas - 1)
            {
                break;
            }
//            if (HSSFCellUtilities.isCellEmpty(cells[i]))
//            {
//System.err.println("00: SegFuncionalidadeExcelBean.lerCampos()\ti: " + i);                
//                return null;
//            }
        }
        //System.err.println("1: SegFuncionalidadeExcelBean.lerCampos()");
        pk_id_funcionalidade = HSSFCellUtilities.getIntCellValue(objDefaultFormat, objFormulaEvaluator, cells[0]);
        if (pk_id_funcionalidade == null)
        {
            //System.err.println("2: SegFuncionalidadeExcelBean.lerCampos()");
            return null;
//            msg = "Erro na chave primaria da Funcionalidade";
//            LogFile.warnMsg(null, msg);
//            throw new ExcepcaoCarregamentoTabelasExcel();
        }
        if (pk_id_funcionalidade > 0)
        {
            segFuncionalidade.setPkSegFuncionalidade(pk_id_funcionalidade);
        }
        else
        {
            msg = "Erro na chave primaria da Funcionalidade, não pode ser negativa";
            LogFile.warnMsg(null, msg);
            return null;
        }
        //System.err.println("3: SegFuncionalidadeExcelBean.lerCampos()");
        descricao = HSSFCellUtilities.getStringCellValue(objDefaultFormat, objFormulaEvaluator, cells[1]);
        segFuncionalidade.setDescricao(descricao);

        String id = HSSFCellUtilities.getStringCellValue(objDefaultFormat, objFormulaEvaluator, cells[2]);
        segFuncionalidade.setId(id);

        fk_id_funcionalidade_pai = HSSFCellUtilities.getIntCellValue(objDefaultFormat, objFormulaEvaluator, cells[3]);
        if (fk_id_funcionalidade_pai == null)
        {
            msg = "Erro na chave secundaria da Funcionalidade Pai"; 
            LogFile.warnMsg(null, msg);
            throw new ExcepcaoCarregamentoTabelasExcel();
        }
        if (fk_id_funcionalidade_pai > 0)
        {
            segFuncionalidade.setFkSegFuncionalidadePai(this.segFuncionalidadeFacade.find(fk_id_funcionalidade_pai));
        }
        else if (fk_id_funcionalidade_pai == 0) ;
        else
        {
            msg = "Erro na chave secundaria da Funcionalidade Pai, não pode ser negativa";
            LogFile.warnMsg(null, msg);
            return null;
        }

        fk_id_tipo_funcionalidade = HSSFCellUtilities.getIntCellValue(objDefaultFormat, objFormulaEvaluator, cells[4]);
        if (fk_id_tipo_funcionalidade == null)
        {
            msg = "Erro na chave secundaria do tipo de funcionalidade";
            LogFile.warnMsg(null, msg);
            throw new ExcepcaoCarregamentoTabelasExcel();
        }
        if (fk_id_tipo_funcionalidade > 0)
        {
            segFuncionalidade.setFkSegTipoFuncionalidade(segTipoFuncionalidadeCache.find(fk_id_tipo_funcionalidade));
        }
        else
        {
            msg = "Erro na chave secundaria do tipo de funcionalidade, não pode ser negativa";
            LogFile.warnMsg(null, msg);
            return null;
        }

        formsLista = HSSFCellUtilities.getStringCellValue(objDefaultFormat, objFormulaEvaluator, cells[5]);

        segFuncionalidadeFormsLista.setSegFuncionalidade(segFuncionalidade);
        segFuncionalidadeFormsLista.setPkFormsLista(formsLista);
        //System.err.println("4: SegFuncionalidadeExcelBean.lerCampos()\nsegFuncionalidade: "
//            + this.segFuncionalidadeFacade.toString(segFuncionalidade) + "\nformsLista: " + formsLista);
        return segFuncionalidadeFormsLista;
    }

    /**
     *
     * @param reg
     * @return
     */
    public boolean createRegister(SegFuncionalidade reg)
    {
        try
        {
//System.err.println("0: SegFuncionalidadeExcelBean.createRegister()\treg: " + segFuncionalidadeFacade.toString(reg));            
            this.segFuncionalidadeFacade.create(reg);
//System.err.println("1: SegFuncionalidadeExcelBean.createRegister()\treg: " + segFuncionalidadeFacade.toString(reg));            
            return true;
        }
        catch (Exception e)
        {

        }
//System.err.println("2: SegFuncionalidadeExcelBean.createRegister()\treg: " + segFuncionalidadeFacade.toString(reg));        
        return false;
    }

    /**
     *
     * @param reg
     * @return
     */
    public boolean editRegister(SegFuncionalidade reg)
    {
        try
        {
            this.segFuncionalidadeFacade.edit(reg);
//System.err.println("0: SegFuncionalidadeExcelBean.createRegister()\treg: " + segFuncionalidadeFacade.toString(reg));            
            return true;
        }
        catch (Exception e)
        {
//System.err.println("1: SegFuncionalidadeExcelBean.createRegister()\treg: " + segFuncionalidadeFacade.toString(reg));
        }
        return false;
    }

    /**
     *
     * @param reg
     * @throws seg.utils.ExcepcaoCarregamentoTabelasExcel
     */
    public void createRegisterWithPersonalizedException(SegFuncionalidade reg) throws ExcepcaoCarregamentoTabelasExcel
    {
        if (!this.createRegister(reg))
        {
            LogFile.erroMsg(null,
                "Tentativa de criar Tipo Funcionalidade " + this.segFuncionalidadeFacade.toString(reg));
            throw new ExcepcaoCarregamentoTabelasExcel();
        }
    }

    /**
     *
     * @param reg
     * @throws seg.utils.ExcepcaoCarregamentoTabelasExcel
     */
    public void editRegisterWithPersonalizedException(SegFuncionalidade reg) throws ExcepcaoCarregamentoTabelasExcel
    {
        if (!this.editRegister(reg))
        {

            LogFile.erroMsg(null,
                "Tentativa de actualizar Tipo Funcionalidade " + this.segFuncionalidadeFacade.toString(reg));
            throw new ExcepcaoCarregamentoTabelasExcel();
        }
    }

}
