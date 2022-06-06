/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import ejbs.entities.GdDocumento;
import ejbs.entities.GdVersaoDocumento;
import ejbs.facades.GdDocumentoFacade;
import ejbs.facades.GdVersaoDocumentoFacade;
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
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import static javax.faces.context.FacesContext.getCurrentInstance;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author david-salgueiro
 */
@Named(value = "gdVersaoDocumentoCadastrarBean")
@SessionScoped
public class GdVersaoDocumentoCadastrarBean implements Serializable {

    @EJB
    private GdVersaoDocumentoFacade versaoDocumentoFacade;
    
    @EJB
    private GdDocumentoFacade documentoFacade;
    
    
    private List<GdDocumento> listaDocumento;
    private GdVersaoDocumento versaoDocumento;
    private UploadedFile ficheiroOriginal;
    private int codigoDocumento;
    private String mudancaRealizada;
           
    private boolean btnSalvar = true;
    private boolean btnFormularioCadastro;
    private boolean tabListar = true;
    private boolean estado;
    
    Path filePath;
   
    public GdVersaoDocumentoCadastrarBean() {
    }
    
    @PostConstruct
    public void init()
    {
        listaDocumento = documentoFacade.findAllDocumentoOrderByData();
        versaoDocumento = new GdVersaoDocumento();
        codigoDocumento = 0;
        mudancaRealizada = "";
    }
    

//    public List<Documento> listarDocumento()
//    {
//        return documentoFacade.findAllDocumentoOrderByData();
//    }
    
    //Metodos para formulário  void
    public String voltar()
    {

        this.btnSalvar = true;
        this.btnFormularioCadastro = false;
        this.tabListar = false;
        this.estado = false;
        
        return "indexGD";

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

    public List<GdVersaoDocumento> findVersaoDocumentoByDocumento(int codigo)
    {
       return versaoDocumentoFacade.findVersaoDocumentoByDocumento(codigo);
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
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Documento não anexado", ""));
            
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
           
            versaoDocumento.setFkDocumento(new GdDocumento(this.codigoDocumento));
            versaoDocumento.setCaminhoFicheiro(filePath.getFileName().toString());
            versaoDocumento.setDataRegisto(new java.util.Date());
            versaoDocumento.setMudancaRealizada(this.mudancaRealizada);
            versaoDocumento.setEstado(true);
            versaoDocumentoFacade.create(versaoDocumento);
              
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Registrado com Sucesso!"));
            init();
            

        } catch (Exception ex)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Erro ao salvar os dados, verifique todos os parametros de inserção!"));
            ex.printStackTrace();
        }
           return "";
           
    } // Fim método gravar

    
    public void eliminar(GdVersaoDocumento documento)
    {
        try
        {
            GdVersaoDocumento doc = versaoDocumentoFacade.find(documento.getPkVersaoDocumento());
            versaoDocumentoFacade.remove(doc);
            init();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Versão do Documento excluídos com sucesso!", "Versão do Documento excluídos com sucesso!"));

//            VersaoDocumento doc = versaoDocumentoFacade.find(documento.getPkVersaoDocumento());
//            doc.setEstado(Boolean.FALSE);
//            versaoDocumentoFacade.edit(doc);
//            init();
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Versão do Documento excluídos com sucesso!", ""));

        } 
        catch (Exception exception)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Impossível eliminar Versão do Documento, Verifique se está sendo utilizado em algum registro, ou entre em contacto com o Administrador de Sistema.", "Impossível eliminar Versão do Documento, Verifique se está sendo utilizado em algum registro, ou entre em contacto com o Administrador de Sistema."));
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

    public GdVersaoDocumento getVersaoDocumento() {
        return versaoDocumento;
    }

    public void setVersaoDocumento(GdVersaoDocumento versaoDocumento) {
        this.versaoDocumento = versaoDocumento;
    }

    public String getMudancaRealizada() {
        return mudancaRealizada;
    }

    public void setMudancaRealizada(String mudancaRealizada) {
        this.mudancaRealizada = mudancaRealizada;
    }

    public List<GdDocumento> getListaDocumento() {
        return listaDocumento;
    }

    public void setListaDocumento(List<GdDocumento> listaDocumento) {
        this.listaDocumento = listaDocumento;
    }

    public int getCodigoDocumento() {
        return codigoDocumento;
    }

    public void setCodigoDocumento(int codigoDocumento) {
        this.codigoDocumento = codigoDocumento;
    }
    
    
}
