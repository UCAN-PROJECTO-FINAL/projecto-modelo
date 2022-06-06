/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package financas.beans;

import ejbs.entities.FinContasPagar;
import ejbs.entities.FinContasReceber;
import ejbs.facades.FinContaFacade;
import ejbs.facades.FinContasPagarFacade;
import ejbs.facades.FinContasReceberFacade;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author majoao
 */
@Named(value = "finMovimentosBean")
@ViewScoped
public class FinMovimentosBean implements Serializable {

    /**
     * Creates a new instance of FinMovimentosBean
     */
    @EJB
    private FinContasReceberFacade contasReceberFacade;
    @EJB
    private FinContasPagarFacade contasPagarFacade;
    @EJB
    private FinContaFacade contaFacade;
    private List<FinContasReceber> listaFinContasReceber;
    private List<FinContasPagar> listFinContasPagar;

    private List<FinListAuxiliar> listaContasPagar;
    private List<FinListAuxiliar> listaContasReceber;

    private FinListAuxiliar finListAuxiliar;
    private BigDecimal saldoInicial, saldoDisponivel;

    public FinMovimentosBean() {
    }

    @PostConstruct
    public void init() {
        listaFinContasReceber = new ArrayList<>();
        listFinContasPagar = new ArrayList<>();
        listaContasPagar = new ArrayList<>();
        listaContasReceber = new ArrayList<>();
        finListAuxiliar = new FinListAuxiliar();
        saldoInicial = BigDecimal.ZERO;
        saldoDisponivel = BigDecimal.ZERO;
    }

    public void changeList(ValueChangeEvent event) {
        //
        listaFinContasReceber = contasReceberFacade.getFinContasReceber(Integer.parseInt((String)event.getNewValue()));
        listFinContasPagar = contasPagarFacade.getFinContasReceber(Integer.parseInt((String)event.getNewValue()));
        contaFacade.getListConta(Integer.parseInt((String)event.getNewValue())).stream().forEach(x ->{
            saldoInicial = x.getSaldoInicial();
            saldoDisponivel = x.getSaldo();
        });
        insertFinContasPagarList();
        insertFinContasReceberList();
    }

    private void insertFinContasPagarList() {
        listFinContasPagar.forEach((finContasPagar) -> {
            try {
                finListAuxiliar = new FinListAuxiliar();
                finListAuxiliar.setChave(finContasPagar.getPkContasPagar());
                finListAuxiliar.setCentroCusto(finContasPagar.getFkCentroCusto().getDesignacao());
                finListAuxiliar.setContaCartao(finContasPagar.getFkConta().getNome());
                finListAuxiliar.setNumeroConta(finContasPagar.getFkConta().getNumeroConta());
                finListAuxiliar.setDataCadastro(finContasPagar.getDataCadastro());
                finListAuxiliar.setDescricao(finContasPagar.getDescricao());
                finListAuxiliar.setFornecedor(finContasPagar.getFkFornecedor().getNome());
                finListAuxiliar.setValor(finContasPagar.getValor().doubleValue());
                listaContasPagar.add(finListAuxiliar);
            } catch (NullPointerException e) {
            }
        });
        
    }

    private void insertFinContasReceberList() {
        listaFinContasReceber.forEach((finContasPagar) -> {
            try {
                finListAuxiliar = new FinListAuxiliar();
                finListAuxiliar.setChave(finContasPagar.getPkContasReceber());
                finListAuxiliar.setCentroCusto(finContasPagar.getFkCentroCusto().getDesignacao());
                finListAuxiliar.setContaCartao(finContasPagar.getFkConta().getNome());
                finListAuxiliar.setNumeroConta(finContasPagar.getFkConta().getNumeroConta());
                finListAuxiliar.setDataCadastro(finContasPagar.getDataCadastro());
                finListAuxiliar.setDescricao(finContasPagar.getDescricao());
                finListAuxiliar.setCliente(finContasPagar.getFkCliente().getNome());
                finListAuxiliar.setValor(finContasPagar.getValor().doubleValue());
                listaContasReceber.add(finListAuxiliar);
            } catch (NullPointerException e) {
            }
        });
    }

    public List<FinContasReceber> getListaFinContasReceber() {
        return listaFinContasReceber;
    }

    public void setListaFinContasReceber(List<FinContasReceber> listaFinContasReceber) {
        this.listaFinContasReceber = listaFinContasReceber;
    }

    public List<FinContasPagar> getListFinContasPagar() {
        return listFinContasPagar;
    }

    public void setListFinContasPagar(List<FinContasPagar> listFinContasPagar) {
        this.listFinContasPagar = listFinContasPagar;
    }

    public List<FinListAuxiliar> getListaContasPagar() {
        return listaContasPagar;
    }

    public void setListaContasPagar(List<FinListAuxiliar> listaContasPagar) {
        this.listaContasPagar = listaContasPagar;
    }

    public List<FinListAuxiliar> getListaContasReceber() {
        return listaContasReceber;
    }

    public void setListaContasReceber(List<FinListAuxiliar> listaContasReceber) {
        this.listaContasReceber = listaContasReceber;
    }

    public BigDecimal getSaldoInicial() {
        return saldoInicial;
    }

    public void setSaldoInicial(BigDecimal saldoInicial) {
        this.saldoInicial = saldoInicial;
    }

    public BigDecimal getSaldoDisponivel() {
        return saldoDisponivel;
    }

    public void setSaldoDisponivel(BigDecimal saldoDisponivel) {
        this.saldoDisponivel = saldoDisponivel;
    }

    
    
}
