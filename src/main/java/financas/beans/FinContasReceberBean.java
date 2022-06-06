/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package financas.beans;

//import ejbs.cache.GrlEntidadeCache;
//import ejbs.cache.EstruturaFisicaCache;
import ejbs.cache.GdTipoDocumentoCache;
import ejbs.cache.FinContaCache;
import ejbs.cache.FinSubCategoriaCache;
import ejbs.cache.GrlEntidadeCache;
import ejbs.entities.GrlEntidade;
//import ejbs.entities.GrlEntidade;
import ejbs.entities.GdTipoDocumento;
import ejbs.entities.EstruturaFisica;
import ejbs.entities.FinConta;
import ejbs.entities.FinContasReceber;
import ejbs.facades.FinContasReceberFacade;
import estrutura.ejbs.cache.EstruturaFisicaCache;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import seg.beans.SegLoginBean;
//import org.primefaces.model.file.UploadedFile;
import utils.mensagens.Mensagem;

/**
 *
 * @author majoao
 */
@Named(value = "finContasReceberBean")
@ViewScoped
public class FinContasReceberBean implements Serializable {

    /**
     * Creates a new instance of FinContasReceberBean
     */
    @EJB
    private FinContasReceberFacade contasReceberFacade;

    //Inject
    @Inject
    private GrlEntidadeCache comumClienteCache;

    @Inject
    private EstruturaFisicaCache estruturaFisicaCache;

    @Inject
    private GdTipoDocumentoCache finTipoDocumentoCache;
    @Inject
    private FinContaCache finContaCache;
    @Inject
    private FinSubCategoriaCache finSubCategoriaCache;

    //Entidades
    private FinContasReceber contasReceberEntidade;
    private FinConta finConta;
    //Listas
    private List<GrlEntidade> listaClientes;
    private List<EstruturaFisica> listaEstrutura;
    private List<GdTipoDocumento> listTipoDocEntidade;
    private List<FinConta> listaFinConta;
    private List<FinContasReceber> listaFinContasReceber;

    //Variaveis
    private UploadedFile fileDoc;
    private String pkCliente;
    private String pkEstrutura, pkDocumento, pkConta, pkCategoria;
    private Date dataCadastro;
    @Inject
    SegLoginBean segLoginBean;
    @Inject
    FinFavoritosBeans finFavoritosBeans;

    public FinContasReceberBean() {
    }

    @PostConstruct
    public void init() {
        contasReceberEntidade = new FinContasReceber();
        listaEstrutura = estruturaFisicaCache.getListaEstruturaFisicas();
        listaClientes = comumClienteCache.getListaGrlEntidades();
        listaFinConta = finContaCache.getListaFinContas();
        listaFinContasReceber = contasReceberFacade.findAll();
        finConta = new FinConta();
        pkEstrutura = null;
        pkCliente = null;
        pkDocumento = null;
        pkConta = null;
        pkCategoria = null;
        dataCadastro = new Date();
    }

    public void salvar() {
        try {
            if (pkCategoria == null) {
                Mensagem.warnMsg(null, "Preencha a Categoria");
            } else if ((pkCliente != null) && (pkCategoria != null)
                    && (pkEstrutura != null) && (pkConta != null)) {
                //contasReceberEntidade.setFkCentroCusto(pkEstrutura);
                System.out.println("1");
                contasReceberEntidade.setFkCliente(comumClienteCache.findGrlEntidade(Integer.parseInt(pkCliente)));
                contasReceberEntidade.setFkCategoria(finSubCategoriaCache.findFinCategoriaSubcategoria(Integer.parseInt(pkCategoria)));
                contasReceberEntidade.setFkCentroCusto(estruturaFisicaCache.findEstruturaFisica(pkEstrutura));
                contasReceberEntidade.setFkConta(finContaCache.findFinConta(Integer.parseInt(pkConta)));
                contasReceberEntidade.setDataCadastro(dataCadastro);
                contasReceberEntidade.setFkUtilizador(segLoginBean.getContaUtilizador());
                this.contasReceberFacade.create(contasReceberEntidade);
                Mensagem.sucessoMsg(null, "Dados Salvo !");
                init();
            } else if ((pkCliente == null) && (pkCategoria != null)
                    && (pkEstrutura != null) && (pkConta != null)) {
                System.out.println("2");
                contasReceberEntidade.setFkCategoria(finSubCategoriaCache.findFinCategoriaSubcategoria(Integer.parseInt(pkCategoria)));
                contasReceberEntidade.setFkCentroCusto(estruturaFisicaCache.findEstruturaFisica(pkEstrutura));
                contasReceberEntidade.setFkConta(finContaCache.findFinConta(Integer.parseInt(pkConta)));
                contasReceberEntidade.setDataCadastro(dataCadastro);
                contasReceberEntidade.setFkUtilizador(segLoginBean.getContaUtilizador());
                this.contasReceberFacade.create(contasReceberEntidade);
                Mensagem.sucessoMsg(null, "Dados Salvo !");
                init();
            } else if ((pkCliente == null) && (pkCategoria != null) && (pkConta != null) && (pkEstrutura != null)) {
                System.out.println("3");
                contasReceberEntidade.setFkCategoria(finSubCategoriaCache.findFinCategoriaSubcategoria(Integer.parseInt(pkCategoria)));
                contasReceberEntidade.setFkConta(finContaCache.findFinConta(Integer.parseInt(pkConta)));
                contasReceberEntidade.setDataCadastro(dataCadastro);
                contasReceberEntidade.setFkUtilizador(segLoginBean.getContaUtilizador());
                this.contasReceberFacade.create(contasReceberEntidade);
                Mensagem.sucessoMsg(null, "Dados Salvo !");
                init();
            } else if ((pkCliente != null) && (pkCategoria != null) && (pkConta != null) && (pkEstrutura == null)) {
                System.out.println("3");
                contasReceberEntidade.setFkCategoria(finSubCategoriaCache.findFinCategoriaSubcategoria(Integer.parseInt(pkCategoria)));
                contasReceberEntidade.setFkCliente(comumClienteCache.findGrlEntidade(Integer.parseInt(pkCliente)));
                contasReceberEntidade.setFkConta(finContaCache.findFinConta(Integer.parseInt(pkConta)));
                contasReceberEntidade.setDataCadastro(dataCadastro);
                contasReceberEntidade.setFkUtilizador(segLoginBean.getContaUtilizador());
                this.contasReceberFacade.create(contasReceberEntidade);
                Mensagem.sucessoMsg(null, "Dados Salvo !");
                init();
            }

        } catch (NumberFormatException | NullPointerException e) {
            Mensagem.enviarMensagemFatal(null, "Erro ao salvar Dados");
            System.out.println("e: " + e.getMessage());
            System.out.println("e: " + e.getCause().toString());
        }

    }

    private boolean updateSaldoConta() {
        finConta = finContaCache.findFinConta(Integer.parseInt(pkConta));
        double valor = contasReceberEntidade.getValor().doubleValue();
        if (finConta == null || valor <= 0) {
            Mensagem.warnMsg(null, finConta == null ? "Por favor informe a conta" : "O Valor inserido não pode ser menor ou igual a zero");
            return false;
        }
        double valorActual = finConta.getSaldo().doubleValue() + valor;
        finConta.setSaldo(BigDecimal.valueOf(valorActual));
        finContaCache.edit(finConta);
        return true;
    }

    public String abrirPagina() {
        finFavoritosBeans.salvar("/modulos/fncVisao/financas/fin_contas_receber.xhtml", "Contas Receber");
        return "/modulos/fncVisao/financas/fin_contas_receber";
    }
    public void upload() {
        if (fileDoc != null) {
            FacesMessage message = new FacesMessage("Successful", fileDoc.getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void handleFileUpload(FileUploadEvent event) {
        System.out.println("event.getFile().getFileName(): " + event.getFile().getFileName());
        FacesMessage message = new FacesMessage("Successful", event.getFile().getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public FinContasReceber getContasReceberEntidade() {
        return contasReceberEntidade;
    }

    public void setContasReceberEntidade(FinContasReceber contasReceberEntidade) {
        this.contasReceberEntidade = contasReceberEntidade;
    }

    public List<GrlEntidade> getListaClientes() {
        return listaClientes;
    }

    public void setListaClientes(List<GrlEntidade> listaClientes) {
        this.listaClientes = listaClientes;
    }

    public UploadedFile getFileDoc() {
        return fileDoc;
    }

    public void setFileDoc(UploadedFile fileDoc) {
        this.fileDoc = fileDoc;
    }

    public String getPkCliente() {
        return pkCliente;
    }

    public void setPkCliente(String pkCliente) {
        this.pkCliente = pkCliente;
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

    public List<FinContasReceber> getListaFinContasReceber() {
        return listaFinContasReceber;
    }

    public void setListaFinContasReceber(List<FinContasReceber> listaFinContasReceber) {
        this.listaFinContasReceber = listaFinContasReceber;
    }

}
