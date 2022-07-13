/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frt.beans;

import ejbs.entities.FinModoPagamento;
import ejbs.entities.FrtTransporteAgendar;
import java.io.Serializable;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import ejbs.entities.PtTransporte;
import ejbs.entities.FrtTransporteTipoCombustivel;
import ejbs.entities.FrtTransporteBombaCombustivel;

import ejbs.facades.PtTransporteFacade;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import utils.mensagens.LogFile;


/**
 *
 * @author samuel
 */
@Named(value = "transporteAbastecimentoCadastrarBean")
@ViewScoped
public class FrtTransporteAbastecimentoCadastrarBean implements Serializable
{
    @EJB
    private PtTransporteFacade ptTransporteFacade;
    
    //private PtTransporte  selectedPtTransporte;
    private FinModoPagamento finModoPagamento;
    private FrtTransporteAgendar frtTransporteAgendar;
    private FrtTransporteTipoCombustivel frtTransporteTipoCombustivel;
    private FrtTransporteBombaCombustivel frtTransporteBombaCombustivel;

    /**
     * Creates a new instance of TransporteAbastecimentoCadastrarBean
     */
    public FrtTransporteAbastecimentoCadastrarBean() 
    {
        
    }
    
    @PostConstruct
    public void init()
    {
        initConfiguracao();

        dataSystem();

      
       
    }
    
    public void initConfiguracao()
    {
    
    }
    
    public void salvar()
    {
        FacesContext context = FacesContext.getCurrentInstance();
       
                   
                  init();
                  //frtTransporteSolicitacao = new FrtTransporteSolicitacao();
                  
                  
        LogFile.sucessoMsg(null, "Manutenção Feita Com Sucesso !!!!!!");
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Solicitacao Feita Com Sucesso !!!", ""));
      
        //limpar();


        
        
    }
      
    
    
//    public void adicionar(ActionEvent event)
//    {
//
//        int codigo = Integer.parseInt(event.getComponent().getAttributes().get("codigoTransporte").toString());
//        System.err.println("Entrou seleccionarTransporte: " + codigo);
//        this.selectedPtTransporte = ptTransporteFacade.find(codigo);
//          
//    }
    
    public Date dataSystem() 
    {
        Date Data;
        Data = new Date();
        return Data;
    }
    
    
    // Gets e Sets 

//    public PtTransporte getSelectedPtTransporte() {
//        return selectedPtTransporte;
//    }
//
//    public void setSelectedPtTransporte(PtTransporte selectedPtTransporte) {
//        this.selectedPtTransporte = selectedPtTransporte;
//    }

    public FinModoPagamento getFinModoPagamento()
    {
        return finModoPagamento;
    }

    public void setFinModoPagamento(FinModoPagamento finModoPagamento)
    {
        this.finModoPagamento = finModoPagamento;
    }

    public FrtTransporteAgendar getFrtTransporteAgendar()
    {
        return frtTransporteAgendar;
    }

    public void setFrtTransporteAgendar(FrtTransporteAgendar frtTransporteAgendar)
    {
        this.frtTransporteAgendar = frtTransporteAgendar;
    }

    public FrtTransporteTipoCombustivel getFrtTransporteTipoCombustivel()
    {
        return frtTransporteTipoCombustivel;
    }

    public void setFrtTransporteTipoCombustivel(FrtTransporteTipoCombustivel frtTransporteTipoCombustivel)
    {
        this.frtTransporteTipoCombustivel = frtTransporteTipoCombustivel;
    }

    public FrtTransporteBombaCombustivel getFrtTransporteBombaCombustivel()
    {
        return frtTransporteBombaCombustivel;
    }

    public void setFrtTransporteBombaCombustivel(FrtTransporteBombaCombustivel frtTransporteBombaCombustivel)
    {
        this.frtTransporteBombaCombustivel = frtTransporteBombaCombustivel;
    }
    
    
    
    
}
