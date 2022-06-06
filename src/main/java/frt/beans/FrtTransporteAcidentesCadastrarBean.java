/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frt.beans;

import ejbs.entities.FrtTransporteTipoAcidente;
import ejbs.entities.FrtTransporteTipoIluminacao;
//import ejbs.entities.FrtTransporteTipoPavimento;
import ejbs.entities.PtTransporte;
import java.io.Serializable;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import utils.mensagens.LogFile;

/**
 *
 * @author smakambo
 */
@Named(value = "transporteAcidentesCadastrarBean")
@ViewScoped
public class FrtTransporteAcidentesCadastrarBean implements Serializable
{

    private FrtTransporteTipoAcidente frtTransporteTipoAcidente;
    private FrtTransporteTipoIluminacao frtTransporteTipoIluminacao;
    //private FrtTransporteTipoPavimento frtTransporteTipoPavimento;
    private PtTransporte ptTransporte;

    /**
     * Creates a new instance of TransporteAcidentesCadastrarBean
     */
    public FrtTransporteAcidentesCadastrarBean()
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

    public Date dataSystem()
    {
        Date Data;
        Data = new Date();
        return Data;
    }

    public FrtTransporteTipoAcidente getFrtTransporteTipoAcidente()
    {
        return frtTransporteTipoAcidente;
    }

    public void setFrtTransporteTipoAcidente(FrtTransporteTipoAcidente frtTransporteTipoAcidente)
    {
        this.frtTransporteTipoAcidente = frtTransporteTipoAcidente;
    }

    public FrtTransporteTipoIluminacao getFrtTransporteTipoIluminacao()
    {
        return frtTransporteTipoIluminacao;
    }

    public void setFrtTransporteTipoIluminacao(FrtTransporteTipoIluminacao frtTransporteTipoIluminacao)
    {
        this.frtTransporteTipoIluminacao = frtTransporteTipoIluminacao;
    }

//    public FrtTransporteTipoPavimento getFrtTransporteTipoPavimento()
//    {
//        return frtTransporteTipoPavimento;
//    }
//
//    public void setFrtTransporteTipoPavimento(FrtTransporteTipoPavimento frtTransporteTipoPavimento)
//    {
//        this.frtTransporteTipoPavimento = frtTransporteTipoPavimento;
//    }

    public PtTransporte getPtTransporte()
    {
        return ptTransporte;
    }

    public void setPtTransporte(PtTransporte ptTransporte)
    {
        this.ptTransporte = ptTransporte;
    }
    
    
    
    

}
