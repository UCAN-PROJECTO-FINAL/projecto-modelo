/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frt.beans;

import ejbs.entities.FinModoPagamento;
import ejbs.entities.PtTransporte;
import ejbs.entities.FrtTransporteTipoInfraccao;
import ejbs.facades.FinModoPagamentoFacade;
import ejbs.facades.PtTransporteFacade;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import utils.mensagens.LogFile;

/**
 *
 * @author samuel
 */
@Named(value = "transporteMultasCadastrarBean")
@ViewScoped
public class FrtTransporteMultasCadastrar implements Serializable
{
    @EJB
    private FinModoPagamentoFacade finModoPagamentoFacade;

    @EJB
    private PtTransporteFacade ptTransporteFacade;

    private PtTransporte selectedPtTransporte;
    
    private FrtTransporteTipoInfraccao frtTransporteTipoInfraccao;
    
    private FinModoPagamento finModoPagamento;

    /**
     * Creates a new instance of TransporteMultasCadastrar
     */
    public FrtTransporteMultasCadastrar()
    {

    }

    @PostConstruct
    public void init()
    {
        initConfiguracao();

        dataSystem();

        selectedPtTransporte = new PtTransporte();

    }

    public void initConfiguracao()
    {

    }

    public void salvar()
    {
        FacesContext context = FacesContext.getCurrentInstance();

        init();
        

        LogFile.sucessoMsg(null, "Manutenção Feita Com Sucesso !!!!!!");
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Solicitacao Feita Com Sucesso !!!", ""));

        
    }

    public void adicionar(ActionEvent event)
    {

        int codigo = Integer.parseInt(event.getComponent().getAttributes().get("codigoTransporte").toString());
        System.err.println("Entrou seleccionarTransporte: " + codigo);
        this.selectedPtTransporte = ptTransporteFacade.find(codigo);

    }

    public Date dataSystem()
    {
        Date Data;
        Data = new Date();
        return Data;
    }
    
    public List<FinModoPagamento> listaFormaPagamento()
    {
        return this.finModoPagamentoFacade.findAll();
    }

    public PtTransporte getSelectedPtTransporte()
    {
        return selectedPtTransporte;
    }

    public void setSelectedPtTransporte(PtTransporte selectedPtTransporte)
    {
        this.selectedPtTransporte = selectedPtTransporte;
    }

    public FrtTransporteTipoInfraccao getFrtTransporteTipoInfraccao()
    {
        return frtTransporteTipoInfraccao;
    }

    public void setFrtTransporteTipoInfraccao(FrtTransporteTipoInfraccao frtTransporteTipoInfraccao)
    {
        this.frtTransporteTipoInfraccao = frtTransporteTipoInfraccao;
    }

    public FinModoPagamento getFinModoPagamento()
    {
        return finModoPagamento;
    }

    public void setFinModoPagamento(FinModoPagamento finModoPagamento)
    {
        this.finModoPagamento = finModoPagamento;
    }
    
    

}
