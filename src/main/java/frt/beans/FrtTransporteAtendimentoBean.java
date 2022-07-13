/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frt.beans;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import ejbs.entities.FrtTransporteSolicitacao;
import ejbs.entities.FrtTransporteAtendimento;
import ejbs.entities.FrtTransporteAgendar;
import ejbs.entities.FrtTransporteSolicitacaoEstado;
import ejbs.facades.FrtTransporteSolicitacaoFacade;
import ejbs.facades.FrtTransporteAgendarFacade;
import ejbs.facades.FrtTransporteAtendimentoFacade;
import java.util.Date;
import javax.annotation.PostConstruct;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;


/**
 *
 * @author samuel
 */
@Named(value = "transporteAtendimentoBean")
@SessionScoped
public class FrtTransporteAtendimentoBean implements Serializable
{



    @EJB
    private FrtTransporteSolicitacaoFacade frtTransporteSolicitacaoFacade;

    @EJB
    private FrtTransporteAgendarFacade frtTransporteAgendarFacade;

    @EJB
    private FrtTransporteAtendimentoFacade frtTransporteAtendimentoFacade;

    private FrtTransporteSolicitacao frtTransporteSolicitacao;

    private FrtTransporteAtendimento frtTransporteAtendimento;

    private FrtTransporteAgendar frtTransporteAgendar;

    private FrtTransporteSolicitacaoEstado frtTransporteSolicitacaoEstado;

    /**
     * Creates a new instance of TransporteAtendimentoBean
     */
    public FrtTransporteAtendimentoBean()
    {

    }

    @PostConstruct
    public void init()
    {

        frtTransporteSolicitacao = new FrtTransporteSolicitacao();
        frtTransporteAtendimento = new FrtTransporteAtendimento();
        frtTransporteAgendar = new FrtTransporteAgendar();
        frtTransporteSolicitacaoEstado = new FrtTransporteSolicitacaoEstado();

    }

    public void adicionar(ActionEvent event)
    {

        int codigo = Integer.parseInt(event.getComponent().getAttributes().get("codigoTransporte").toString());
        System.err.println("Entrou seleccionarTransporte: " + codigo);
        this.frtTransporteAgendar = frtTransporteAgendarFacade.find(codigo);
        //findTransporte();

    }

//    public void findTransporte()
//    {
//        List<PtTransporte> list = agendarTransporteCache.findByTransportePk(selectedPtTransporte.getPkPtTransporte());
//        
//
//        selectedTransporte = list;
//    }
//    
    public void salvar()
    {
        FacesContext context = FacesContext.getCurrentInstance();
        System.out.println("frt.beans.TransporteAtendimentoBean.salvar()");

        
  //          userTransaction.begin();

            frtTransporteSolicitacao.setFkTransporteSolicitacaoEstado(new FrtTransporteSolicitacaoEstado(4));
            frtTransporteSolicitacaoFacade.edit(frtTransporteSolicitacao);

            frtTransporteAtendimento.setFkTransporteSolicitacao(frtTransporteSolicitacao);
            System.out.println("Codigo solicitacao " + frtTransporteSolicitacao);
            
            System.out.println("frtTransporteAgendar" + frtTransporteAgendar);
            FrtTransporteAgendar agenda = frtTransporteAgendarFacade.find(frtTransporteAgendar.getPkAgendarTransporte());
            agenda.setIsAgendado(false);
            frtTransporteAgendarFacade.edit(agenda);
            

            frtTransporteAtendimento.setFkTransporteAgendamento(frtTransporteAgendar);
            System.out.println("Codigo do Agendamento " + frtTransporteAgendar);

            frtTransporteSolicitacaoEstado.setPkTransporteSolicitacaoEstado(4);
            frtTransporteAtendimento.setFkTransporteAtendimentEstado(frtTransporteSolicitacaoEstado);
            System.out.println("Codigo do estado " + frtTransporteSolicitacaoEstado);

            frtTransporteAtendimentoFacade.create(frtTransporteAtendimento);
            System.out.println("Codigo frtTransporteAtendimento " + frtTransporteAtendimento);
           // userTransaction.commit();
            init();

            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Atendimento em Andamenrto  Com Sucesso !!!", ""));

    

    }

    public void finalizarAtendimento()
    {

        FacesContext context = FacesContext.getCurrentInstance();

//        frtTransporteSolicitacao = frtTransporteAtendimento.getFkTransporteSolicitacao();
//        frtTransporteSolicitacao.setFkTransporteSolicitacaoEstaso(new FrtTransporteSolicitacaoEstado(4));
//        frtTransporteSolicitacaoFacade.edit(frtTransporteSolicitacao);
System.out.println("Codigo do frtTransporteSolicitacao " + frtTransporteSolicitacao);
        frtTransporteSolicitacaoEstado.setPkTransporteSolicitacaoEstado(4);
        frtTransporteAtendimento.setFkTransporteAtendimentEstado(frtTransporteSolicitacaoEstado);

        System.out.println("Codigo do estado " + frtTransporteSolicitacaoEstado);

        frtTransporteAtendimentoFacade.edit(frtTransporteAtendimento);
        init();

        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Atendimento Finalizado Com Sucesso !!!", ""));

    }

    public String retornarPaginaAtendimento(int codigo)
    {
        this.frtTransporteSolicitacao = frtTransporteSolicitacaoFacade.find(codigo);
         System.out.println("Codigo do frtTransporteSolicitacao " + frtTransporteSolicitacao);
        return "frt_transporte_atendimento.xhtml?faces-redirect=true";

    }

    public String retornarPaginaFinalizarAtendimento(int codigo)
    {
        this.frtTransporteAtendimento = frtTransporteAtendimentoFacade.find(codigo);
        return "frt_transporte_atendimento_finalizar.xhtml?faces-redirect=true";

    }
    
    public void rejeitarSolicitacao(int codigo)
    {
        FacesContext context = FacesContext.getCurrentInstance();
        
        this.frtTransporteSolicitacao = frtTransporteSolicitacaoFacade.find(codigo);
        frtTransporteSolicitacao.setFkTransporteSolicitacaoEstado(new FrtTransporteSolicitacaoEstado(5));
        frtTransporteSolicitacaoFacade.edit(frtTransporteSolicitacao);
        
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "A Sua Solicitacao Foi Rejeitado Com Sucesso!!!", ""));
        
                
    }

    public Date dataSystem()
    {
        Date Data;
        Data = new Date();
        return Data;
    }

    public FrtTransporteSolicitacao getFrtTransporteSolicitacao()
    {
        return frtTransporteSolicitacao;
    }

    public void setFrtTransporteSolicitacao(FrtTransporteSolicitacao frtTransporteSolicitacao)
    {
        this.frtTransporteSolicitacao = frtTransporteSolicitacao;
    }

    public FrtTransporteAtendimento getFrtTransporteAtendimento()
    {
        return frtTransporteAtendimento;
    }

    public void setFrtTransporteAtendimento(FrtTransporteAtendimento frtTransporteAtendimento)
    {
        this.frtTransporteAtendimento = frtTransporteAtendimento;
    }

    public FrtTransporteAgendar getFrtTransporteAgendar()
    {
        return frtTransporteAgendar;
    }

    public void setFrtTransporteAgendar(FrtTransporteAgendar frtTransporteAgendar)
    {
        this.frtTransporteAgendar = frtTransporteAgendar;
    }

    public FrtTransporteSolicitacaoEstado getFrtTransporteSolicitacaoEstado()
    {
        return frtTransporteSolicitacaoEstado;
    }

    public void setFrtTransporteSolicitacaoEstado(FrtTransporteSolicitacaoEstado frtTransporteSolicitacaoEstado)
    {
        this.frtTransporteSolicitacaoEstado = frtTransporteSolicitacaoEstado;
    }

}
