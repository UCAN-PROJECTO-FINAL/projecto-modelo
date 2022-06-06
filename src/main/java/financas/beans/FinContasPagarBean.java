/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package financas.beans;

//import ejbs.cache.GrlEntidadeCache;
import estrutura.ejbs.cache.EstruturaFisicaCache;
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
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import org.primefaces.model.UploadedFile;
import seg.beans.SegLoginBean;
//import org.primefaces.model.file.UploadedFile;
import utils.mensagens.Mensagem;

/**
 *
 * @author majoao
 */
@Named(value = "finContasPagarBean")
@ViewScoped
public class FinContasPagarBean implements Serializable {

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

    @Inject
    SegLoginBean segLoginBean;
    @Inject
    FinFavoritosBeans finFavoritosBeans;
    //Entidades
    private FinContasPagar contasPagarEntidade;

    //Listas
    private List<GrlEntidade> listaFornecedores;
    private List<EstruturaFisica> listaEstrutura;
    private List<GdTipoDocumento> listTipoDocEntidade;
    private List<FinConta> listaFinConta;
    private FinConta finConta;
    private List<FinContasPagar> listFinContasPagar;
    private FinContasPagar finContasPagarSelected;
    //Variaveis
    private UploadedFile fileDoc;
    private String pkFornecedor = "";
    private String pkEstrutura = "", pkDocumento = "", pkConta = "", pkCategoria = "";
    private Date dataCadastro;
    private boolean pago;

    public FinContasPagarBean() {
    }

    @PostConstruct
    public void init() {
        contasPagarEntidade = new FinContasPagar();
        listaFornecedores = GrlEntidadeCache.getListaGrlEntidades();
        listaEstrutura = estruturaFisicaCache.getListaEstruturaFisicas();
        listaFinConta = finContaCache.getListaFinContas();
        listFinContasPagar = contasPagarFacade.findAll();
        finConta = new FinConta();
        pkEstrutura = "";
        pkFornecedor = "";
        pkDocumento = "";
        pkConta = "";
        pkCategoria = "";
        dataCadastro = new Date();
    }

    public void salvar() {
        try {
            if (pkFornecedor != null && pkConta != null && pkEstrutura != null && pkCategoria != null && updateSaldoConta()) {
                contasPagarEntidade.setFkFornecedor(GrlEntidadeCache.findGrlEntidade(Integer.parseInt(pkFornecedor)));
                contasPagarEntidade.setFkCentroCusto(estruturaFisicaCache.findEstruturaFisica(pkEstrutura));
                contasPagarEntidade.setFkConta(finContaCache.findFinConta(Integer.parseInt(pkConta)));
                contasPagarEntidade.setFkCategoria(subCategoriaCache.findFinCategoriaSubcategoria(Integer.parseInt(pkCategoria)));
                //contasPagarEntidade.setFkCentroCusto(pkEstrutura);
                contasPagarEntidade.setDataCadastro(dataCadastro);
                contasPagarEntidade.setFkUtilizador(segLoginBean.getContaUtilizador());
                this.contasPagarFacade.create(contasPagarEntidade);
                Mensagem.sucessoMsg(null, "Dados Salvo !");
                init();
            } else if (pkFornecedor != null && pkConta != null && pkCategoria != null && updateSaldoConta()) {
                contasPagarEntidade.setFkFornecedor(GrlEntidadeCache.findGrlEntidade(Integer.parseInt(pkFornecedor)));
                //contasPagarEntidade.setFkCentroCusto(estruturaFisicaCache.findEstruturaFisica(pkEstrutura));
                contasPagarEntidade.setFkConta(finContaCache.findFinConta(Integer.parseInt(pkConta)));
                contasPagarEntidade.setFkUtilizador(segLoginBean.getContaUtilizador());
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

    private boolean updateSaldoConta() {
        finConta = finContaCache.findFinConta(Integer.parseInt(pkConta));
        double valor = contasPagarEntidade.getValor().doubleValue();
        if (finConta == null || valor <= 0) {
            Mensagem.warnMsg(null, finConta == null ? "Por favor informe a conta" : "O Valor inserido não pode ser menor ou igual a zero");
            return false;
        }
        double valorActual = finConta.getSaldo().doubleValue() - valor;
        finConta.setSaldo(BigDecimal.valueOf(valorActual));
        finContaCache.edit(finConta);
        return true;
    }

    public String eliminar() {
        try {
            contasPagarFacade.remove(finContasPagarSelected);
            Mensagem.sucessoMsg(null, "Dados eliminados !");
        } catch (NullPointerException e) {
            Mensagem.warnMsg(null, "Erro ao eliminar dados");
        }
        return "fin_listar_contas_pagar?faces-redirect=true";
    }

    public String abrirPagina() {
        finFavoritosBeans.salvar("/modulos/fncVisao/financas/fin_contas_pagar.xhtml", "Contas Pagar");
        return "/modulos/fncVisao/financas/fin_contas_pagar";
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

    public boolean isPago() {
        return pago;
    }

    public void setPago(boolean pago) {
        this.pago = pago;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

}
