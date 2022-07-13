/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package financas.beans;

import ejbs.cache.GdTipoDocumentoCache;
import ejbs.cache.GrlEntidadeCache;
import ejbs.entities.FinDocumento;
import ejbs.entities.GdTipoDocumento;
import ejbs.facades.FinDocumentoFacade;
import ejbs.facades.GdTipoDocumentoFacade;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import utils.mensagens.Mensagem;
import financas.util.Defs;
import static patri.utils.Ficheiro.gravar;

/**
 *
 * @author majoao
 */
@Named(value = "finCadastroDocumentoBean")
@ViewScoped
public class FinCadastroDocumentoBean implements Serializable {

    /**
     * Creates a new instance of FinCadastroDocumentoBean
     */
    @EJB
    private FinDocumentoFacade finDocumentoFacade;
    @EJB
    private GdTipoDocumentoFacade gdTipoDocumentoFacade;

    private FinDocumento finDocumento;
    private List<FinDocumento> listFinDocumento;
    private List<GdTipoDocumento> listGdTipoDocumento;

    @Inject
    private GdTipoDocumentoCache gdTipoDocumentoCache;
    @Inject
    private GrlEntidadeCache grlEntidadeCache;

    private int fkTipoDoc, fkEntidade;

    private UploadedFile uploadedFile;

    public FinCadastroDocumentoBean() {
    }

    @PostConstruct
    public void init() {
        finDocumento = new FinDocumento();
        listFinDocumento = finDocumentoFacade.findAll();
        listGdTipoDocumento = gdTipoDocumentoFacade.findTipoDocumentoFinancas();

    }

    public void salvar() {
        try {
            finDocumento.setPkDocumento(finDocumentoFacade.count() + 1);
            finDocumento.setDataRegisto(new Date());
            finDocumento.setEstadoDocumento(Boolean.TRUE);
            finDocumento.setFkTipoDoc(gdTipoDocumentoCache.findGdTipoDocumento(fkTipoDoc));
            finDocumento.setFkEntidade(grlEntidadeCache.findGrlEntidade(fkEntidade));
            finDocumento.setPathFile(Defs.DOCUMENTOS_CARREGADOS_DIR +"documento"+ ".pdf");
            finDocumentoFacade.create(finDocumento);
            init();
            Mensagem.sucessoMsg(null, "Dados Salvos");
        } catch (Exception e) {
            Mensagem.enviarMensagem(FacesMessage.SEVERITY_ERROR, "Erro ao efectuar a operação."
                    + "Entre em contacto com o seu Administrador de Sistemas!", null);
        }

    }

    public void carrregarFicheiro(FileUploadEvent event)
    {
            uploadedFile = event.getFile();
            InputStream ficheiro;
             try {
                 ficheiro = uploadedFile.getInputstream();
                 String filename = "documento";
                 gravar(ficheiro,filename,"pdf",Defs.DOCUMENTOS_CARREGADOS_DIR);

             } catch (IOException ex) {
                 Logger.getLogger(FinContratoCompraRegistarBean.class.getName()).log(Level.SEVERE, null, ex);
             }

    }
    public FinDocumento getFinDocumento() {
        return finDocumento;
    }

    public void setFinDocumento(FinDocumento finDocumento) {
        this.finDocumento = finDocumento;
    }

    public List<FinDocumento> getListFinDocumento() {
        return listFinDocumento;
    }

    public void setListFinDocumento(List<FinDocumento> listFinDocumento) {
        this.listFinDocumento = listFinDocumento;
    }

    public GdTipoDocumentoCache getGdTipoDocumentoCache() {
        return gdTipoDocumentoCache;
    }

    public void setGdTipoDocumentoCache(GdTipoDocumentoCache gdTipoDocumentoCache) {
        this.gdTipoDocumentoCache = gdTipoDocumentoCache;
    }

    public GrlEntidadeCache getGrlEntidadeCache() {
        return grlEntidadeCache;
    }

    public void setGrlEntidadeCache(GrlEntidadeCache grlEntidadeCache) {
        this.grlEntidadeCache = grlEntidadeCache;
    }

    public int getFkTipoDoc() {
        return fkTipoDoc;
    }

    public void setFkTipoDoc(int fkTipoDoc) {
        this.fkTipoDoc = fkTipoDoc;
    }

    public int getFkEntidade() {
        return fkEntidade;
    }

    public void setFkEntidade(int fkEntidade) {
        this.fkEntidade = fkEntidade;
    }

    public List<GdTipoDocumento> getListGdTipoDocumento() {
        return listGdTipoDocumento;
    }

    public void setListGdTipoDocumento(List<GdTipoDocumento> listGdTipoDocumento) {
        this.listGdTipoDocumento = listGdTipoDocumento;
    }

}
