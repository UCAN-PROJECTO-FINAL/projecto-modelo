/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package financas.beans;

import ejbs.entities.FinConta;
import ejbs.facades.FinContaFacade;
import ejbs.facades.FinContaTransferenciaFacade;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import seg.beans.SegLoginBean;
import utils.mensagens.Mensagem;

/**
 *
 * @author majoao
 */
@Named(value = "finContaTransferencia")
@ViewScoped
public class FinContaTransferencia implements Serializable {

    /**
     * Creates a new instance of FinContaTransferencia
     */
    @EJB
    private FinContaFacade finContaFacade;
    @EJB
    private FinContaTransferenciaFacade finContaTransferenciaFacade;
    @Resource
    private UserTransaction userTransition;

    private FinConta contaOrigem, contaDestino;
    private ejbs.entities.FinContaTransferencia finContaTransferencia;
    private List<FinConta> listaContaOrigem, listaContaDestino;

    private BigDecimal valorTransferir, valorContaOrigem, valorContaDestino;
    private Date dataCadastro;
    private List<ejbs.entities.FinContaTransferencia> listTransferencia = new ArrayList<>();
    @Inject
    SegLoginBean segLoginBean;

    public FinContaTransferencia() {
    }

    @PostConstruct
    public void init() {
        contaOrigem = new FinConta();
        contaDestino = new FinConta();
        finContaTransferencia = new ejbs.entities.FinContaTransferencia();
        valorTransferir = BigDecimal.ZERO;
        listaContaOrigem = finContaFacade.findAll();
        listaContaDestino = new ArrayList<>();
        listTransferencia = finContaTransferenciaFacade.findAll();
        dataCadastro = new Date();
        valorContaOrigem = BigDecimal.ZERO;
        valorContaDestino = BigDecimal.ZERO;
    }

    public void salvar() {
        if (!validarSaldoOrigem()) {
            try {
                //userTransition.begin();
                updateContaOrigem();
                updateContaDestino();
                finContaTransferencia.setPkContaTransferencia(getIDTransferencia());
                finContaTransferencia.setFkContaDestino(contaDestino);
                finContaTransferencia.setFkContaOrigem(contaOrigem);
                finContaTransferencia.setValor(valorTransferir);
                finContaTransferencia.setFkUtilizador(segLoginBean.getContaUtilizador());
                finContaTransferencia.setDataTransferencia(dataCadastro);
                finContaTransferenciaFacade.create(finContaTransferencia);
                // userTransition.commit();
                Mensagem.sucessoMsg(null, "Dados Salvo");
                init();
            } catch (IllegalStateException | SecurityException e) {
                try {
                    userTransition.rollback();
                } catch (IllegalStateException | SecurityException | SystemException ex) {
                    Logger.getLogger(FinContaTransferencia.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            Mensagem.warnMsg(null, "Dados não salvo, o valor á transferir é maior que o saldo disponivel");
        }

    }

    private boolean validarSaldoOrigem() {
        return contaOrigem.getSaldo().subtract(valorTransferir).doubleValue() < 0;
    }

    private int getIDTransferencia() {
        return finContaTransferenciaFacade.count() + 1;
    }

    public void updateContaOrigem() {
        try {
            //userTransition.begin();
            contaOrigem = finContaFacade.find(contaOrigem.getPkConta());
            contaOrigem.setSaldo(contaOrigem.getSaldo().subtract(valorTransferir));
            finContaFacade.edit(contaOrigem);
            //userTransition.commit();
        } catch (SecurityException | IllegalStateException ex) {
            Logger.getLogger(FinContaTransferencia.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void updateContaDestino() {
        try {
            //userTransition.begin();
            contaDestino = finContaFacade.find(contaDestino.getPkConta());
            contaDestino.setSaldo(contaDestino.getSaldo().add(valorTransferir));
            finContaFacade.edit(contaDestino);
            // userTransition.commit();
        } catch (SecurityException | IllegalStateException ex) {
            Logger.getLogger(FinContaTransferencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void changeListOrigem(ValueChangeEvent event) {
        try {
            contaOrigem = finContaFacade.getListConta((Integer) event.getNewValue()).get(0);
            valorContaOrigem = contaOrigem.getSaldo();
            listaContaDestino = finContaFacade.getListContaByMoeda(contaOrigem.getFkMoeda().getPkMoeda());
            listaContaDestino.remove(contaOrigem);
        } catch (NullPointerException e) {
        }

    }

    public void changeListDestino(ValueChangeEvent event) {
        try {
            contaDestino = finContaFacade.find((Integer) event.getNewValue());
            valorContaDestino = contaDestino.getSaldo();
        } catch (NumberFormatException e) {
        }
    }

    public FinContaTransferenciaFacade getFinContaTransferenciaFacade() {
        return finContaTransferenciaFacade;
    }

    public void setFinContaTransferenciaFacade(FinContaTransferenciaFacade finContaTransferenciaFacade) {
        this.finContaTransferenciaFacade = finContaTransferenciaFacade;
    }

    public FinConta getContaOrigem() {
        return contaOrigem;
    }

    public void setContaOrigem(FinConta contaOrigem) {
        this.contaOrigem = contaOrigem;
    }

    public FinConta getContaDestino() {
        return contaDestino;
    }

    public void setContaDestino(FinConta contaDestino) {
        this.contaDestino = contaDestino;
    }

    public List<FinConta> getListaContaOrigem() {
        return listaContaOrigem;
    }

    public void setListaContaOrigem(List<FinConta> listaContaOrigem) {
        this.listaContaOrigem = listaContaOrigem;
    }

    public List<FinConta> getListaContaDestino() {
        return listaContaDestino;
    }

    public void setListaContaDestino(List<FinConta> listaContaDestino) {
        this.listaContaDestino = listaContaDestino;
    }

    public BigDecimal getValorTransferir() {
        return valorTransferir;
    }

    public void setValorTransferir(BigDecimal valorTransferir) {
        this.valorTransferir = valorTransferir;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public BigDecimal getValorContaOrigem() {
        return valorContaOrigem;
    }

    public void setValorContaOrigem(BigDecimal valorContaOrigem) {
        this.valorContaOrigem = valorContaOrigem;
    }

    public BigDecimal getValorContaDestino() {
        return valorContaDestino;
    }

    public void setValorContaDestino(BigDecimal valorContaDestino) {
        this.valorContaDestino = valorContaDestino;
    }

    public List<ejbs.entities.FinContaTransferencia> getListTransferencia() {
        return listTransferencia;
    }

    public void setListTransferencia(List<ejbs.entities.FinContaTransferencia> listTransferencia) {
        this.listTransferencia = listTransferencia;
    }

}
