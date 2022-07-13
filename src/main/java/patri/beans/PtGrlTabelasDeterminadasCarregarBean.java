/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patri.beans;

import ejbs.facades.PtExcelPathFacade;
import patri.excel.beans.PatriExCarregarTabelas;
import utils.excel.ExcelBeanAbstract;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.inject.Named;
import org.omnifaces.cdi.ViewScoped;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import utils.Associacao;
import utils.ConcurrentHashMapPersonalized;
import patri.excel.beans.PtCategoriaExcelBean;
import patri.excel.beans.PtEstadoConservacaoExcelBean;
import patri.excel.beans.PtFormaAquisicaoExcelBean;
import patri.excel.beans.PtMarcaExcelBean;
import patri.excel.beans.PtModeloExcelBean;

/**
 *
 * @author aires
 */
@Named(value = "ptGrlTabelaDeterminadasCarregarBean")
@ViewScoped
public class PtGrlTabelasDeterminadasCarregarBean implements Serializable
{
    @Inject
    private PatriExCarregarTabelas exCarregarTabelas;

    @Inject
    private PtCategoriaExcelBean ptCategoriaExcelBean;
    
    @Inject
    private PtFormaAquisicaoExcelBean ptFormaAquisicaoExcelBean;
     
    @Inject
    private PtEstadoConservacaoExcelBean ptEstadoConservacaoExcelBean;
      
    @Inject
    private PtMarcaExcelBean ptMarcaExcelBean;
       
    @Inject
    private PtModeloExcelBean ptModeloExcelBean;
    
     @EJB
    private PtExcelPathFacade grlExelPathFacade;
    
    
    
    private static ConcurrentHashMapPersonalized<String, ExcelBeanAbstract> hashtable = new ConcurrentHashMapPersonalized();

    private static boolean hashtableEstahCarregada = false;
    private boolean pathEditionDisabled=true;

    private List<String> grupos;

    private List<List<String>> tabelas;
    
    private String grupo, tabela, path;
    private List<String> listaTabelas;
    private UploadedFile uploadedFile;

    /**
     * Creates a new instance of GrlTabelaDeterminadasCarregarBean
     */
    public PtGrlTabelasDeterminadasCarregarBean()
    {

    }

    @PostConstruct
    public void init()
    {
        grupos = Arrays.asList("Patrimonio");
        grupo = grupos.get(0);
        tabelas = Arrays.asList(
                Arrays.asList("Forma de Aquisição", "Estado de Conservação","Marcas","Modelos","Categorias","Fornecedor")
        );
        listaTabelas = tabelas.get(0);

        tabela = listaTabelas.get(0);
        if (!hashtableEstahCarregada)
        {
            initHashtable();
        }
        grlExelPathFacade.initPath();
        path = grlExelPathFacade.getCurrentGrlExcelPath();
    }
    
    
    // Business Methods
    
    public void actualizarTabelas()
    {
        listaTabelas = getTabelas(grupo);
        tabela = listaTabelas.get(0);
    }

    public void initHashtable()
    {
        hashtable.put(
                new Associacao("Forma de Aquisição", ptFormaAquisicaoExcelBean),
                new Associacao("Estado de Conservação", ptEstadoConservacaoExcelBean),
                new Associacao("Marcas", ptMarcaExcelBean),
                new Associacao("Modelos", ptMarcaExcelBean),
                new Associacao("Categorias", ptModeloExcelBean)
        );

        hashtableEstahCarregada = true;
    }

    public void listarTabela()
    {
//		LogFile.warnMsg(null, "0: PtGrlTabelasDeterminadasCarregarBean.listarTabela()\ttabela: " + tabela);
    }

    /* ---- Metodo para carregar Ficheiro de Carreira e Categoria ---- */
    public void carrregarFicheiroExcel(FileUploadEvent event)
    {
        System.err.println("0:GrlTabelasDeterminadasCarregarBean:carrregarFicheiroExcel()");
//		LogFile.warnMsg(null, "0: PtGrlTabelasDeterminadasCarregarBean.carrregarFicheiroExcel()");
        uploadedFile = event.getFile();
        String filename = uploadedFile.getFileName();
        String fileExtensao = filename.substring((uploadedFile.getFileName().lastIndexOf(".") + 1), uploadedFile.getFileName().length());

        switch (fileExtensao)
        {
            case "xls":
                preProcessarDados();
                break;
            default:
                //LogFile.warnMsg(null, "O ficheiro \"" + fileExtensao + "\" deve ter extensão .xls");
                break;
        }
    }

    private void preProcessarDados()
    {

//		LogFile.warnMsg(null, "1: PtGrlTabelasDeterminadasCarregarBean.preProcessarDadosCandidaturas()\ttabela: " + tabela);
        ExcelBeanAbstract excelBean = (ExcelBeanAbstract) hashtable.get(tabela);
//		LogFile.warnMsg(null, "2: PtGrlTabelasDeterminadasCarregarBean.preProcessarDadosCandidaturas()");
        excelBean.carregar(uploadedFile);
        uploadedFile = null;
//		LogFile.warnMsg(null, "3: PtGrlTabelasDeterminadasCarregarBean.preProcessarDadosCandidaturas()");

    }

    public void changeStatePathInput()
    {
        if(isPathEditionDisabled())
        {
          path = "";
          pathEditionDisabled=false;
        }
        else
        {
            path = this.grlExelPathFacade.getCurrentGrlExcelPath();
            pathEditionDisabled=true;
        }
    }
    
    // Getters and Setters
    
    public List<String> getTabelas(String grupoItem)
    {
        int n = grupos.size();
        for (int i = 0; i < n; i++)
        {
            if (grupos.get(i).equals(grupoItem))
            {
                return tabelas.get(i);
            }
        }
        return null;
    }

    public void carregarTabelas()
    {
        grlExelPathFacade.saveGrlExcelPath(path.trim());
        exCarregarTabelas.carregarTabelas();
    }

    public List<String> getGrupos()
    {
        return grupos;
    }

    public List<String> getListaTabelas()
    {
        return listaTabelas;
    }

    public String getGrupo()
    {
        return grupo;
    }

    public void setGrupo(String grupo)
    {
        this.grupo = grupo;
    }

    public String getTabela()
    {
        return tabela;
    }

    public void setTabela(String tabela)
    {
        this.tabela = tabela;
    }

    public UploadedFile getUploadedFile()
    {
        return uploadedFile;
    }

    public void setUploadedFile(UploadedFile uploadedFile)
    {
        this.uploadedFile = uploadedFile;
    }

    public String getPath()
    {
        return path;
    }

    public void setPath(String path)
    {
        this.path = path;
    }

    public boolean isPathEditionDisabled()
    {
        return pathEditionDisabled;
    }

    public void setPathEditionDisabled(boolean pathEditionDisabled)
    {
        pathEditionDisabled = pathEditionDisabled;
    }

    
    
}
