/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frt.beans;

import ejbs.entities.FrtTransporteTipoManutencao;
import ejbs.entities.FrtTransporteManutencao;
import ejbs.entities.FrtTransporteAgendar;
import ejbs.entities.FinModoPagamento;
import ejbs.entities.FrtTransporteConfiguracoes;
import ejbs.facades.FinModoPagamentoFacade;
import ejbs.facades.FrtTransporteManutencaoFacade;
import ejbs.facades.FrtTransporteAgendarFacade;
import ejbs.facades.FrtTransporteConfiguracoesFacade;
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
import javax.inject.Inject;
import utils.mensagens.LogFile;

/**
 *
 * @author samuel
 */
@Named(value = "frtTransporteManutencaoCadastrarBean")
@ViewScoped
public class FrtTransporteManutencaoCadastrarBean implements Serializable
{

    @EJB
    private FinModoPagamentoFacade finModoPagamentoFacade;

    @EJB
    FrtTransporteConfiguracoesFacade frtConfiguracoesFacade;

    @EJB
    private FrtTransporteAgendarFacade frtTransporteAgendarFacade;
    
    @EJB
    private FrtTransporteManutencaoFacade frtTransporteManutencaoFacade;


    private FrtTransporteTipoManutencao frtTransporteTipoManutencao;
    private FrtTransporteManutencao frtTransporteManutencao;
    private FrtTransporteAgendar frtTransporteAgendar;
    private FinModoPagamento finModoPagamento;
    private FrtTransporteConfiguracoes frtConfiguracoes;

    private int codigofrtTransporteTipoManutencao = 0;
    
    private int codigofinModoPagamentoFacade = 0;

    @Inject
    seg.beans.SegLoginBean segLoginBean;

    /**
     * Creates a new instance of TransporteManutencaoCadastrarBean
     */
    public FrtTransporteManutencaoCadastrarBean()
    {

    }

    @PostConstruct
    public void init()
    {
        frtTransporteTipoManutencao = new FrtTransporteTipoManutencao();
        frtTransporteAgendar = new FrtTransporteAgendar();
        finModoPagamento = new FinModoPagamento();
        frtTransporteManutencao = new FrtTransporteManutencao();
        //frtTransporteAgendar = new FrtTransporteAgendar();

        initConfiguracao();

        dataSystem();

    }

    public void initConfiguracao()
    {

        frtConfiguracoes = frtConfiguracoesFacade.find();
        codigofrtTransporteTipoManutencao = this.frtConfiguracoes.getFkTipoManutencao().getPkTipoManutencao();

    }

    public void salvar()
    {
        FacesContext context = FacesContext.getCurrentInstance();

        //.setPkTipoManutencao(codigofrtTransporteTipoManutencao);
        frtTransporteManutencao.setFkTransporteAgenda(frtTransporteAgendar);
        
        finModoPagamento.setPkModoPagamento(codigofinModoPagamentoFacade);
        frtTransporteManutencao.setFkModoPagamento(finModoPagamento);

        frtTransporteTipoManutencao.setPkTipoManutencao(codigofrtTransporteTipoManutencao);
        frtTransporteManutencao.setFkTipoManutencao(frtTransporteTipoManutencao);

        frtTransporteManutencao.setDataCadastro(new Date());

        frtTransporteManutencaoFacade.create(frtTransporteManutencao);
        init();

        //frtTransporteManutencao.setFk(segLoginBean.getSessaoActual());
       // init();

        LogFile.sucessoMsg(null, "Manutenção Feita Com Sucesso !!!!!!");
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Solicitacao Feita Com Sucesso !!!", ""));

    }

    public void adicionar(ActionEvent event)
    {

        int codigo = Integer.parseInt(event.getComponent().getAttributes().get("codigoTransporte").toString());
        System.err.println("Entrou seleccionarTransporte: " + codigo);
        this.frtTransporteAgendar = frtTransporteAgendarFacade.find(codigo);

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

    public FrtTransporteTipoManutencao getFrtTransporteTipoManutencao()
    {
        return frtTransporteTipoManutencao;
    }

    public void setFrtTransporteTipoManutencao(FrtTransporteTipoManutencao frtTransporteTipoManutencao)
    {
        this.frtTransporteTipoManutencao = frtTransporteTipoManutencao;
    }

    public FrtTransporteAgendar getFrtTransporteAgendar()
    {
        return frtTransporteAgendar;
    }

    public void setFrtTransporteAgendar(FrtTransporteAgendar frtTransporteAgendar)
    {
        this.frtTransporteAgendar = frtTransporteAgendar;
    }

    public FinModoPagamento getFinModoPagamento()
    {
        return finModoPagamento;
    }

    public void setFinModoPagamento(FinModoPagamento finModoPagamento)
    {
        this.finModoPagamento = finModoPagamento;
    }

    public FrtTransporteManutencao getFrtTransporteManutencao()
    {
        return frtTransporteManutencao;
    }

    public void setFrtTransporteManutencao(FrtTransporteManutencao frtTransporteManutencao)
    {
        this.frtTransporteManutencao = frtTransporteManutencao;
    }

    public FrtTransporteConfiguracoes getFrtConfiguracoes()
    {
        return frtConfiguracoes;
    }

    public void setFrtConfiguracoes(FrtTransporteConfiguracoes frtConfiguracoes)
    {
        this.frtConfiguracoes = frtConfiguracoes;
    }

    public int getCodigofrtTransporteTipoManutencao()
    {
        return codigofrtTransporteTipoManutencao;
    }

    public void setCodigofrtTransporteTipoManutencao(int codigofrtTransporteTipoManutencao)
    {
        this.codigofrtTransporteTipoManutencao = codigofrtTransporteTipoManutencao;
    }

    public int getCodigofinModoPagamentoFacade()
    {
        return codigofinModoPagamentoFacade;
    }

    public void setCodigofinModoPagamentoFacade(int codigofinModoPagamentoFacade)
    {
        this.codigofinModoPagamentoFacade = codigofinModoPagamentoFacade;
    }
    
    

}
