/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package financas.beans;

//import ejbs.cache.GrlEntidadeCache;
//import estrutura.cache.EstruturaFisicaCache;
import ejbs.cache.GdTipoDocumentoCache;
import ejbs.cache.FinContaCache;
import ejbs.cache.FinSubCategoriaCache;
import ejbs.cache.GrlEntidadeCache;
//import ejbs.entities.GrlEntidade;
import ejbs.entities.GdTipoDocumento;
import ejbs.entities.EstruturaFisica;
import ejbs.entities.FinConta;
import ejbs.entities.FinContasPagar;
import ejbs.entities.GrlEntidade;
import ejbs.facades.FinContasPagarFacade;
import estrutura.ejbs.cache.EstruturaFisicaCache;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
//import org.primefaces.model.file.UploadedFile;
import utils.mensagens.Mensagem;

/**
 *
 * @author majoao
 */
@Named(value = "finAlterarContasPagarBean")
@ViewScoped
public class FinAlterarContasPagarBean implements Serializable {

    /**
     * Creates a new instance of FinContasPagarBean
     */
    @EJB
    private FinContasPagarFacade contasPagarFacade;

    //Inject
    @Inject
    private GrlEntidadeCache GrlEntidadeCache;

    @Inject
    private EstruturaFisicaCache estruturaFisicaCache;

    @Inject
    private GdTipoDocumentoCache finTipoDocumentoCache;

    @Inject
    private FinContaCache finContaCache;
    @Inject
    private FinSubCategoriaCache subCategoriaCache;
    //Entidades
    private FinContasPagar contasPagarEntidade;

    //Listas
    private List<GrlEntidade> listaFornecedores;
    private List<EstruturaFisica> listaEstrutura;
    private List<GdTipoDocumento> listTipoDocEntidade;
    private List<FinConta> listaFinConta;
    private List<FinContasPagar> listFinContasPagar;
    private FinContasPagar finContasPagarSelected;
    //Variaveis
    private UploadedFile fileDoc;
    private String pkFornecedor;
    private String pkEstrutura, pkDocumento, pkConta, pkCategoria;
    private Date dataCadastro;

    public FinAlterarContasPagarBean() {
    }

    @PostConstruct
    public void init() {
        try {
            contasPagarEntidade = new FinContasPagar();
            Map<String, String> parametros = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
            int pkContapagar = Integer.parseInt(parametros.get("pkcontasPagar"));
            contasPagarEntidade = contasPagarFacade.find(pkContapagar);
        } catch (NumberFormatException | NullPointerException e) {
        }
        pkEstrutura = null;
        pkFornecedor = null;
        pkDocumento = null;
        pkConta = null;
        pkCategoria = null;

    }

    public void alterar() {
        try {
            if (pkFornecedor != null && pkConta != null && pkEstrutura != null && pkCategoria != null) {
                contasPagarEntidade.setFkFornecedor(GrlEntidadeCache.findGrlEntidade(Integer.parseInt(pkFornecedor)));
                contasPagarEntidade.setFkCentroCusto(estruturaFisicaCache.findEstruturaFisica(pkEstrutura));
                contasPagarEntidade.setFkConta(finContaCache.findFinConta(Integer.parseInt(pkConta)));
                contasPagarEntidade.setFkCategoria(subCategoriaCache.findFinCategoriaSubcategoria(Integer.parseInt(pkCategoria)));
                //contasPagarEntidade.setFkCentroCusto(pkEstrutura);
                contasPagarEntidade.setDataCadastro(dataCadastro);
                this.contasPagarFacade.create(contasPagarEntidade);
                Mensagem.sucessoMsg(null, "Dados Salvo !");
                init();
            } else if (pkFornecedor != null && pkConta != null && pkCategoria != null) {
                contasPagarEntidade.setFkFornecedor(GrlEntidadeCache.findGrlEntidade(Integer.parseInt(pkFornecedor)));
                //contasPagarEntidade.setFkCentroCusto(estruturaFisicaCache.findEstruturaFisica(pkEstrutura));
                contasPagarEntidade.setFkConta(finContaCache.findFinConta(Integer.parseInt(pkConta)));
                contasPagarEntidade.setFkCategoria(subCategoriaCache.findFinCategoriaSubcategoria(Integer.parseInt(pkCategoria)));
                contasPagarEntidade.setDataCadastro(dataCadastro);
                this.contasPagarFacade.create(contasPagarEntidade);
                Mensagem.sucessoMsg(null, "Dados Salvo !");
                init();
            } else {
                Mensagem.warnMsg(null, "Por favor informe a conta");
            }

        } catch (Exception e) {
            Mensagem.enviarMensagemFatal(null, "Erro ao salvar dados");
        }

    }

    public void upload() {
        if (fileDoc != null) {
            FacesMessage message = new FacesMessage("Successful", fileDoc.getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void carregarDocumento(FileUploadEvent event) {
        System.out.println("event.getFile().getFileName(): " + event.getFile().getFileName());
        FacesMessage message = new FacesMessage("Successful", event.getFile().getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public FinContasPagar getContasPagarEntidade() {
        return contasPagarEntidade;
    }

    public void setContasPagarEntidade(FinContasPagar contasPagarEntidade) {
        this.contasPagarEntidade = contasPagarEntidade;
    }

    public UploadedFile getFileDoc() {
        return fileDoc;
    }

    public void setFileDoc(UploadedFile fileDoc) {
        this.fileDoc = fileDoc;
    }

    public List<GrlEntidade> getListaFornecedores() {
        return listaFornecedores;
    }

    public void setListaFornecedores(List<GrlEntidade> listaFornecedores) {
        this.listaFornecedores = listaFornecedores;
    }

    public String getPkFornecedor() {
        return pkFornecedor;
    }

    public void setPkFornecedor(String pkFornecedor) {
        this.pkFornecedor = pkFornecedor;
    }

    public List<EstruturaFisica> getListaEstrutura() {
        return listaEstrutura;
    }

    public void setListaEstrutura(List<EstruturaFisica> listaEstrutura) {
        this.listaEstrutura = listaEstrutura;
    }

    public List<GdTipoDocumento> getListTipoDocEntidade() {
        return listTipoDocEntidade;
    }

    public void setListTipoDocEntidade(List<GdTipoDocumento> listTipoDocEntidade) {
        this.listTipoDocEntidade = listTipoDocEntidade;
    }

    public String getPkEstrutura() {
        return pkEstrutura;
    }

    public void setPkEstrutura(String pkEstrutura) {
        this.pkEstrutura = pkEstrutura;
    }

    public String getPkDocumento() {
        return pkDocumento;
    }

    public void setPkDocumento(String pkDocumento) {
        this.pkDocumento = pkDocumento;
    }

    public List<FinConta> getListaFinConta() {
        return listaFinConta;
    }

    public void setListaFinConta(List<FinConta> listaFinConta) {
        this.listaFinConta = listaFinConta;
    }

    public String getPkConta() {
        return pkConta;
    }

    public void setPkConta(String pkConta) {
        this.pkConta = pkConta;
    }

    public String getPkCategoria() {
        return pkCategoria;
    }

    public void setPkCategoria(String pkCategoria) {
        this.pkCategoria = pkCategoria;
    }

    public List<FinContasPagar> getListFinContasPagar() {
        return listFinContasPagar;
    }

    public void setListFinContasPagar(List<FinContasPagar> listFinContasPagar) {
        this.listFinContasPagar = listFinContasPagar;
    }

    public FinContasPagar getFinContasPagarSelected() {
        return finContasPagarSelected;
    }

    public void setFinContasPagarSelected(FinContasPagar finContasPagarSelected) {
        this.finContasPagarSelected = finContasPagarSelected;
    }

}
