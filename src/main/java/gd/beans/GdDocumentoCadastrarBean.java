/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gd.beans;

import estrutura.ejbs.cache.EstruturaFisicaCache;
import estrutura.ejbs.cache.EstruturaLogicaCache;
import estrutura.ejbs.cache.EstruturaLogicaFisicaCache;
import ejbs.entities.GdClassificacao;
import ejbs.entities.GdConfiguracoes;
import ejbs.entities.GdDocumento;
import ejbs.entities.GdEntidade;
import ejbs.entities.EstruturaFisica;
import ejbs.entities.EstruturaLogica;
import ejbs.entities.EstruturaLogicaFisica;
import ejbs.entities.GdTipoDocumento;
import ejbs.facades.GdClassificacaoFacade;
import ejbs.facades.GdConfiguracoesFacade;
import ejbs.facades.GdDocumentoFacade;
import ejbs.facades.GdEntidadeFacade;
import ejbs.facades.GdTipoDocumentoFacade;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import utils.mensagens.LogFile;
//import estrutura.beans.EstruturaLogicaFisicaCarregarBean;



/**
 *
 * @author david-salgueiro
 */
@Named(value = "gdDocumentoCadastrarBean")
@SessionScoped
public class GdDocumentoCadastrarBean implements Serializable {

    @EJB
    private GdDocumentoFacade documentoFacade;

    @EJB
    private GdTipoDocumentoFacade tipoDocumentoFacade;

    @EJB
    private GdClassificacaoFacade classificacaoFacade;

    @EJB
    private GdEntidadeFacade entidadeFacade;
    
    @EJB
    private GdConfiguracoesFacade configuracoesFacade;


    @Inject
    private EstruturaLogicaFisicaCache estruturaLogicaFisicaCache;
    
    @Inject
    private EstruturaFisicaCache estruturaFisicaCache;
    
    @Inject
    private EstruturaLogicaCache estruturaLogicaCache;
    
    @Inject
    private EstruturaLogicaFisicaCarregarBean estruturaLogicaFisicaCarregarBean;
    
    private List<GdEntidade> listaEntidade;
    private List<GdClassificacao> listaClassificacao;
    private List<GdTipoDocumento> listaTipoDocumento;
    private List<GdDocumentoCadastrarBean.EFisicaNode> listaEstruturaFisica;
    private List<GdDocumentoCadastrarBean.ELogicaNode> listaEstruturaLogica;
    private int limitEstruturaFisicaLength,limitEstruturaLogicaLength;
    private String estruturaFisicaSOMLabel="eFisicaList",estruturaLogicaSOMLabel="eLogicaList";
    private GdDocumento documento;
    GdConfiguracoes configuracoes;
    private Path ficheiroTemporario;
    private UploadedFile ficheiroOriginal;
    private int codigoEntidade;
    private int codigoClassificacao;
    private int codigoTipoDocumento;
           
    private boolean btnSalvar = true;
    private boolean btnFormularioCadastro;
    private boolean tabListar = true;
    private boolean estado;
    
    Path filePath;
    
    public GdDocumentoCadastrarBean()
    {
    }
    
    
    @PostConstruct
    public void init()
    {
        limitEstruturaFisicaLength = estruturaFisicaCache.getEstruturaFisicaLevel();
        
        limitEstruturaLogicaLength = estruturaLogicaCache.getEstruturaLogicaLevel();
        inicializaListas();
        
        listaEntidade = entidadeFacade.findAll();
        listaTipoDocumento = tipoDocumentoFacade.findAll();
        listaClassificacao = classificacaoFacade.findAll();
                
        documento = new GdDocumento();
        initConfiguracao();
    }
    
    
    public void initObjecto()
    {
        documento = new GdDocumento();
    }
    
    public void initConfiguracao()
    {
        configuracoes = configuracoesFacade.find();
        codigoTipoDocumento = configuracoes.getFkTipoDocumento().getPkTipoDocumento();
        codigoEntidade = configuracoes.getFkEntidade().getPkEntidade();
        codigoClassificacao = configuracoes.getFkClassificacao().getPkClassificacao();
    }
    
    public void inicializaListas()
    {
        listaEstruturaFisica = new ArrayList<>();
        listaEstruturaFisica.add(new EFisicaNode(estruturaFisicaCache.getListaPaisEstruturaFisicas()));
        
        for(int i=1;i<limitEstruturaFisicaLength;i++)
            listaEstruturaFisica.add(new EFisicaNode());
        
        actualizaEstruturaFisicaByNivel(1);
        
        listaEstruturaLogica = new ArrayList<>();
        listaEstruturaLogica.add(new ELogicaNode(estruturaLogicaCache.getListaPaisEstruturaLogica()));
        
        for(int i=1;i<limitEstruturaLogicaLength;i++)
            listaEstruturaLogica.add(new ELogicaNode());
        
        actualizaEstruturaLogicaByNivel(1);
    }
    
    //Os Níveis são contados de 0 acima
    //A função abaixo só deve ser usada para actualizar elementos do nivel 1 acima
    public void actualizaEstruturaFisicaByNivel(int nivel)
    {
       for(int i=nivel; i < limitEstruturaFisicaLength; i++)
       {
        listaEstruturaFisica.get(i).setLista(estruturaFisicaCache.getListaSonsByPkEstruturaFisica(listaEstruturaFisica.get(i - 1).getSelectedFk()));
        listaEstruturaFisica.get(i).setSelectedFk(listaEstruturaFisica.get(i).getLista().get(0).getPkEstruturaFisica());
       }
    }
    
    public void eFisicaChangeListener(int index)
    {
        actualizaEstruturaFisicaByNivel(index+1);
        
    }
    
    public void actualizaEstruturaLogicaByNivel(int nivel)
    {
       for(int i=nivel; i < limitEstruturaLogicaLength; i++)
       {
        listaEstruturaLogica.get(i).setLista(estruturaLogicaCache.getListaSonsByPkEstruturaLogica(listaEstruturaLogica.get(i - 1).getSelectedFk()));
        listaEstruturaLogica.get(i).setSelectedFk(listaEstruturaLogica.get(i).getLista().get(0).getPkEstruturaLogica());
       }
    }
    
    public void eLogicaChangeListener(int index)
    {
        actualizaEstruturaLogicaByNivel(index+1);
        //listFkEstruturaFisica.set(index, pkEstruturaFisica);
        //if(index+1<limitEstruturaFisicaLength)
        //    actualizaEstruturaFisicaByNivel(index+1);
        
    }
    
    public void registar()
    {
        EstruturaLogicaFisica reg = new EstruturaLogicaFisica();
        
        EstruturaLogica estruturaLogica = estruturaLogicaCache.findEstruturaLogica(listaEstruturaLogica.get(limitEstruturaLogicaLength - 1).selectedFk);
                
        EstruturaFisica estruturaFisica =  estruturaFisicaCache.findEstruturaFisica(listaEstruturaFisica.get(limitEstruturaFisicaLength - 1).selectedFk);
        
        if(estruturaLogica!=null && estruturaFisica!=null)
        {
            reg.setFkEstruturaFisica(estruturaFisica);
            reg.setFkEstruturaLogica(estruturaLogica);
            estruturaLogicaFisicaCache.create(reg);
            System.out.println("registar() ---: " + reg);
            
             LogFile.sucessoMsg(null, "Info", "Registrado com Sucesso");
        }
        
        
    }
    
    public String getestruturaFisicaSOMLabelByLevel(int nivel)
    {
        String combinedSOM = "";
        for(int i=nivel+1; i < limitEstruturaFisicaLength; i++)
        {
           combinedSOM+=(estruturaFisicaSOMLabel+i)+" ";
        }
        return combinedSOM;
    }
    
    public String getestruturaLogicaSOMLabelByLevel(int nivel)
    {
        String combinedSOM = "";
        for(int i=nivel+1; i < limitEstruturaLogicaLength; i++)
        {
           combinedSOM+=(estruturaLogicaSOMLabel+i)+" ";
        }
        return combinedSOM;
    }
    //GETTERS AND SETTERS----------------------------------------------------------------
    public EstruturaLogicaFisicaCache getEstruturaLogicaFisicaCache()
    {
        return estruturaLogicaFisicaCache;
    }

    public void setEstruturaLogicaFisicaCache(EstruturaLogicaFisicaCache estruturaLogicaFisicaCache)
    {
        this.estruturaLogicaFisicaCache = estruturaLogicaFisicaCache;
    }

    public EstruturaFisicaCache getEstruturaFisicaCache()
    {
        return estruturaFisicaCache;
    }

    public void setEstruturaFisicaCache(EstruturaFisicaCache estruturaFisicaCache)
    {
        this.estruturaFisicaCache = estruturaFisicaCache;
    }

    public EstruturaLogicaCache getEstruturaLogicaCache()
    {
        return estruturaLogicaCache;
    }

    public void setEstruturaLogicaCache(EstruturaLogicaCache estruturaLogicaCache)
    {
        this.estruturaLogicaCache = estruturaLogicaCache;
    }

    public List<EFisicaNode> getListaEstruturaFisica()
    {
        return listaEstruturaFisica;
    }

    public void setListaEstruturaFisica(List<EFisicaNode> listaEstruturaFisica)
    {
        this.listaEstruturaFisica = listaEstruturaFisica;
    }

    public List<ELogicaNode> getListaEstruturaLogica()
    {
        return listaEstruturaLogica;
    }

    public void setListaEstruturaLogica(List<ELogicaNode> listaEstruturaLogica)
    {
        this.listaEstruturaLogica = listaEstruturaLogica;
    }
    
    public int getLimitEstruturaFisicaLength()
    {
        return limitEstruturaFisicaLength;
    }

    public void setLimitEstruturaFisicaLength(int limitEstruturaFisicaLength)
    {
        this.limitEstruturaFisicaLength = limitEstruturaFisicaLength;
    }

    public int getLimitEstruturaLogicaLength()
    {
        return limitEstruturaLogicaLength;
    }

    public void setLimitEstruturaLogicaLength(int limitEstruturaLogicaLength)
    {
        this.limitEstruturaLogicaLength = limitEstruturaLogicaLength;
    }

    public String getEstruturaFisicaSOMLabel()
    {
        return estruturaFisicaSOMLabel;
    }

    public void setEstruturaFisicaSOMLabel(String estruturaFisicaSOMLabel)
    {
        this.estruturaFisicaSOMLabel = estruturaFisicaSOMLabel;
    }

    public String getEstruturaLogicaSOMLabel()
    {
        return estruturaLogicaSOMLabel;
    }

    public void setEstruturaLogicaSOMLabel(String estruturaLogicaSOMLabel)
    {
        this.estruturaLogicaSOMLabel = estruturaLogicaSOMLabel;
    }

    public int getCodigoEntidade() {
        return codigoEntidade;
    }

    public void setCodigoEntidade(int codigoEntidade) {
        this.codigoEntidade = codigoEntidade;
    }

    public int getCodigoClassificacao() {
        return codigoClassificacao;
    }

    public void setCodigoClassificacao(int codigoClassificacao) {
        this.codigoClassificacao = codigoClassificacao;
    }

    public int getCodigoTipoDocumento() {
        return codigoTipoDocumento;
    }

    public void setCodigoTipoDocumento(int codigoTipoDocumento) {
        this.codigoTipoDocumento = codigoTipoDocumento;
    }

    public List<GdEntidade> getListaEntidade() {
        return listaEntidade;
    }

    public void setListaEntidade(List<GdEntidade> listaEntidade) {
        this.listaEntidade = listaEntidade;
    }
    
    public void initEntidade()
    {
        listaEntidade = entidadeFacade.findAll();
    }
    
    public void initTipoDoc()
    {
        listaTipoDocumento = tipoDocumentoFacade.findAll();
    }
    
    public void initTClassificacao()
    {
        listaClassificacao = classificacaoFacade.findAll();
    }

    public List<GdClassificacao> getListaClassificacao() {
        return listaClassificacao;
    }

    public void setListaClassificacao(List<GdClassificacao> listaClassificacao) {
        this.listaClassificacao = listaClassificacao;
    }

    public List<GdTipoDocumento> getListaTipoDocumento() {
        return listaTipoDocumento;
    }

    public void setListaTipoDocumento(List<GdTipoDocumento> listaTipoDocumento) {
        this.listaTipoDocumento = listaTipoDocumento;
    }

    public GdDocumento getDocumento() {
        return documento;
    }

    public void setDocumento(GdDocumento documento) {
        this.documento = documento;
    }
    
    //------INNER CLASSES--------------------------------------------------

    public static class EFisicaNode
    {
        private List<EstruturaFisica> lista;
        private String selectedFk;

        public EFisicaNode(List<EstruturaFisica> lista)
        {
            this.lista = lista;
            this.selectedFk = lista.get(0).getPkEstruturaFisica();
        }
        
        public EFisicaNode()
        {
            this.lista = new ArrayList<>();
        }

        public List<EstruturaFisica> getLista()
        {
            return lista;
        }

        public void setLista(List<EstruturaFisica> lista)
        {
            this.lista = lista;
        }

        public String getSelectedFk()
        {
            return selectedFk;
        }

        public void setSelectedFk(String selectedFk)
        {
            this.selectedFk = selectedFk;
        }
    }
    
    public static class ELogicaNode
    {
        private List<EstruturaLogica> lista;
        private String selectedFk;

        public ELogicaNode(List<EstruturaLogica> lista)
        {
            this.lista = lista;
            this.selectedFk = lista.get(0).getPkEstruturaLogica();
        }
        
        public ELogicaNode()
        {
            this.lista = new ArrayList<>();
        }

        public List<EstruturaLogica> getLista()
        {
            return lista;
        }

        public void setLista(List<EstruturaLogica> lista)
        {
            this.lista = lista;
        }

        public String getSelectedFk()
        {
            return selectedFk;
        }

        public void setSelectedFk(String selectedFk)
        {
            this.selectedFk = selectedFk;
        }
    }
    

    //Metodos para formulário  void
    public String voltar()
    {

        this.btnSalvar = true;
        this.btnFormularioCadastro = false;
        this.tabListar = false;
        this.estado = false;
        
        return "doc_home";

    }

    public void novo()
    {

        this.btnSalvar = false;
        this.btnFormularioCadastro = true;
        this.tabListar = false;
        this.estado = false;

    }

    public void btnListar()
    {

        this.tabListar = true;
        this.btnFormularioCadastro = false;
        this.btnSalvar = true;
        this.estado = false;
    }

    public void btnEditar()
    {

        this.estado = true;
        this.tabListar = false;

    }


    
    public void uploadListener(FileUploadEvent evento)
    {
        this.ficheiroOriginal = evento.getFile();
        carregaDoc();
    }
    
    
    public void carregaDoc()
    {
        try
        {
            String nomeArquivo = ficheiroOriginal.getFileName(); //Nome do arquivo enviado
           
            try (InputStream fos =  ficheiroOriginal.getInputstream())
            {
                Path folder = Paths.get(System.getProperty("jboss.server.data.dir"), "gestaoDocumental");
                System.err.println("folder: " + folder.toString());
                if (!folder.toFile().exists())
                {
                    if (!folder.toFile().mkdirs())
                    {
                        folder = Paths.get(System.getProperty("jboss.server.data.dir")); 
                    }
                
                }
                
//                filePath = Files.createTempFile(folder, nomeArquivo+"-", ".pdf");
                filePath = Files.createTempFile(folder, nomeArquivo+"-", "."+extensao(nomeArquivo));
                Files.copy(fos, filePath, StandardCopyOption.REPLACE_EXISTING);
                
                System.err.println("filePath: " + filePath.getFileName().toString());
                System.out.println("ENTROU");
                
            }
           
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.err.println(e);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Documento não anexado", "Documento não anexado"));
            
        }
    }
    
    
   public String extensao(String strNome)
   {
       String novo = strNome.replace(".", "-");
       String[] arrayStr2 = novo.split("-");
       return arrayStr2[arrayStr2.length-1];
   }

   
    public void fileDownloadView(String nomeArquivo)
    {
        try
        {
        
            Path folder = Paths.get(System.getProperty("jboss.server.data.dir"), "gestaoDocumental");
            System.err.println("FileDownloadView: " + folder.toString()+"/"+nomeArquivo);

            FacesContext context = FacesContext.getCurrentInstance();
            String nomeRelatorio = nomeArquivo;
            HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
            ServletContext sc = (ServletContext) context.getExternalContext().getContext();

            response.setContentType("application/xml");
            response.addHeader("Content-disposition", "attachment; filename =\"" + nomeRelatorio + "\"");
            OutputStream output = response.getOutputStream();

            File ficheiro = new File(folder + "/" + nomeArquivo);
            output.write(Files.readAllBytes(ficheiro.toPath()));

            response.flushBuffer();
            output.flush();
            
            ServletOutputStream responseStream = response.getOutputStream();
            responseStream.flush();
            responseStream.close();

            FacesContext.getCurrentInstance().renderResponse();
            FacesContext.getCurrentInstance().responseComplete();               


        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }
    

   
    public String gravar()
    {

        try
        {
                EstruturaLogicaFisica reg = estruturaLogicaFisicaCarregarBean.saveFroHora();
                System.out.println("registar() ---: " + reg);

//                documento.setFkEntidade(new GdEntidade(codigoEntidade));
                documento.setFkClassificacao(new GdClassificacao(codigoClassificacao));
                documento.setFkTipoDocumento(new GdTipoDocumento(codigoTipoDocumento));
                documento.setCaminho(filePath.getFileName().toString());
                documento.setDataCriacao(new Date());
                documento.setEstado(Boolean.TRUE);
                documento.setFkEstruturaLogicaFisica(reg);
               
                System.err.println("gravar()-- ficheiro: " + ficheiroOriginal.getFileName());
                
                documentoFacade.create(documento);
                
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registrado com Sucesso!", "Registrado com Sucesso!"));
                init();
                initObjecto();
          
        } catch (Exception ex)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao salvar os dados, verifique todos os parametros de inserção!", "Erro ao salvar os dados, verifique todos os parametros de inserção!"));
            ex.printStackTrace();
        }
           return "";
           
    } // Fim método gravar

    
    public List<GdDocumento> listarDocumento()
    {
        return documentoFacade.findAllDocumentoOrderByData();
    }
    
    
    
    public void editar()
    {

        try
        {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Os Dados foram actualizados com sucesso!"));

        } catch (Exception exception)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Impossível alterar os dados , entre em contato com o Administrador de Sistemas"));
        }
        this.estado = false;
        btnListar();

    } // Fim método editar
 
            
    public void eliminar(GdDocumento documento)
    {
        try
        {
            GdDocumento doc = documentoFacade.find(documento.getPkDocumento());
            documentoFacade.remove(doc);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Documento excluídos com sucesso!", "Documento excluídos com sucesso!"));
            
//            Documento doc = documentoFacade.find(documento.getPkDocumento());
//            doc.setEstado(Boolean.FALSE);
//            documentoFacade.edit(doc);
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, " Documento excluídos com sucesso!", ""));

            
        } 
        catch (Exception exception)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Impossível eliminar Documento, Verifique se está sendo utilizado em algum registro, ou entre em contacto com o Administrador de Sistema.", "Impossível eliminar Documento, Verifique se está sendo utilizado em algum registro, ou entre em contacto com o Administrador de Sistema."));
        }

    }
                                                                
 

    public boolean isBtnSalvar() {
        return btnSalvar;
    }

    public void setBtnSalvar(boolean btnSalvar) {
        this.btnSalvar = btnSalvar;
    }

    public boolean isBtnFormularioCadastro() {
        return btnFormularioCadastro;
    }

    public void setBtnFormularioCadastro(boolean btnFormularioCadastro) {
        this.btnFormularioCadastro = btnFormularioCadastro;
    }

    public boolean isTabListar() {
        return tabListar;
    }

    public void setTabListar(boolean tabListar) {
        this.tabListar = tabListar;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    
    
}
