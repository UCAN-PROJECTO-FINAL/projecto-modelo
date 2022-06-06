/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frt.beans;

import estrutura.beans.EstruturaLogicaFisicaCarregarBean;
import java.io.Serializable;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import ejbs.entities.GrlTipoSolicitacao;
import ejbs.entities.RhFuncionario;
import ejbs.entities.FrtTransporteSolicitacao;
import ejbs.entities.EstruturaLogica;
import ejbs.entities.EstruturaLogicaFisica;
import ejbs.entities.FrtTransporteConfiguracoes;
import ejbs.entities.FrtTransporteSolicitacaoEstado;
import ejbs.entities.FrtTransporteTipoAgendamento;
import ejbs.entities.PtTransporteTipo;
import ejbs.entities.GrlEndereco;
import ejbs.facades.FrtTransporteConfiguracoesFacade;
import ejbs.facades.FrtTransporteSolicitacaoEstadoFacade;
//import ejbs.entities.PtTransporteTipo;
import ejbs.facades.FrtTransporteSolicitacaoFacade;

//import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import utils.mensagens.LogFile;

/**
 *
 * @author samuel
 */
@Named(value = "transporteSolicitacoesCadastrarBean")
@ViewScoped

public class FrtTransporteSolicitacoesCadastrarBean implements Serializable
{

    @EJB
    private FrtTransporteSolicitacaoEstadoFacade frtTransporteSolicitacaoEstadoFacade;

    @EJB
    FrtTransporteConfiguracoesFacade frtConfiguracoesFacade;

    @EJB
    private FrtTransporteSolicitacaoFacade frtTransporteSolicitacaoFacade;

    @Inject
    seg.beans.SegLoginBean segLoginBean;

    @Inject
    geral.beans.GrlEnderecoBean grlEnderecoBean;

    @Inject
    private EstruturaLogicaFisicaCarregarBean estruturaLogicaFisicaCarregarBean;

    private GrlTipoSolicitacao grlTipoSolicitacao;
    private GrlEndereco grlEndereco;

    private FrtTransporteSolicitacao frtTransporteSolicitacao;
    private RhFuncionario rhFuncionario;
    private EstruturaLogica estruturaLogica;
    private PtTransporteTipo ptTransporteTipo;
    private FrtTransporteConfiguracoes frtConfiguracoes;
    private FrtTransporteTipoAgendamento frtTransporteTipoAgendamento;
    private FrtTransporteSolicitacaoEstado frtTransporteSolicitacaoEstado;

    private int codigoFrtTransporteTipoAgendamento = 0;
    private int codigoGrlTipoSolicitacao = 0;
    private int codigoPtTransporteTipo = 0;
    private int codigoRhFuncionario = 0;

    /**
     * Creates a new instance of TransporteSolicitacoesCadastrarBean
     */
    public FrtTransporteSolicitacoesCadastrarBean()
    {

    }

    @PostConstruct
    public void init()
    {
        frtTransporteSolicitacao = new FrtTransporteSolicitacao();
        //rhFuncionario = new RhFuncionario();
        grlTipoSolicitacao = new GrlTipoSolicitacao();
        ptTransporteTipo = new PtTransporteTipo();
        estruturaLogica = new EstruturaLogica();
        frtTransporteSolicitacaoEstado = new FrtTransporteSolicitacaoEstado();
        grlEndereco = new GrlEndereco();

        //solicitar = new FrtTransporteSolicitacao();
        initConfiguracao();

        dataSystem();
    }

    public void initConfiguracao()
    {

        frtConfiguracoes = frtConfiguracoesFacade.find();
        codigoGrlTipoSolicitacao = frtConfiguracoes.getFkGrlTipoSolicitacao().getPkTipoSolicitacao();
        codigoPtTransporteTipo = frtConfiguracoes.getFkPtTipoTransporte().getPkTipoTransporte();
    }

    public void salvar()
    {
        FacesContext context = FacesContext.getCurrentInstance();

        System.out.println("Codigo Estrutura Logica::::" + estruturaLogica.getPkEstruturaLogica());

        System.out.println("Tipo de solicitacao::::" + grlTipoSolicitacao.getPkTipoSolicitacao());
        System.out.println("Tipo de transporte::::" + ptTransporteTipo.getPkTipoTransporte());

        //System.out.println("Destino ::::" + frtTransporteSolicitacao.getDestinoSolicitacao());
        System.out.println("Quantidade de Pessoas ::::" + frtTransporteSolicitacao.getQtdPessoasSolicitacao());

        System.out.println("Motivo da Solicitacao ::::" + frtTransporteSolicitacao.getMotivoSolicitacao());
        System.out.println("Data ::::" + frtTransporteSolicitacao.getDataSaidaSolicitacao());
        System.out.println("Data Solicitacao ::::" + frtTransporteSolicitacao.getDataSolicitacao());

        System.out.println("Observacao ::::" + frtTransporteSolicitacao.getObservacaoTransporteSolicitacao());

        grlEndereco = grlEnderecoBean.salvaEGetEndereco();
        frtTransporteSolicitacao.setFkGrlEndereco(grlEndereco);

        EstruturaLogicaFisica reg = estruturaLogicaFisicaCarregarBean.saveFroHora();
        System.out.println("registar() ---: " + reg);
        frtTransporteSolicitacao.setFkEstruturaLogicaFisica(reg);
        // rhFuncionario.setPkFuncionario(codigoRhFuncionario);
        //frtTransporteSolicitacao.setFkEstrututuraLogica(estruturaLogica);

        grlTipoSolicitacao.setPkTipoSolicitacao(codigoGrlTipoSolicitacao);
        frtTransporteSolicitacao.setFkTipoSolicitacao(grlTipoSolicitacao);

        ptTransporteTipo.setPkTipoTransporte(codigoPtTransporteTipo);
        frtTransporteSolicitacao.setFkTipoTransporte(ptTransporteTipo);

        //frtTransporteSolicitacao.setDataSaidaSolicitacao(new Date());
        frtTransporteSolicitacao.setDataSolicitacao(new Date());

        frtTransporteSolicitacaoEstado.setPkTransporteSolicitacaoEstado(2);
        frtTransporteSolicitacao.setFkTransporteSolicitacaoEstado(frtTransporteSolicitacaoEstado);

        frtTransporteSolicitacao.setFkConta(segLoginBean.getSessaoActual());

        frtTransporteSolicitacaoFacade.create(frtTransporteSolicitacao);
        init();
        //frtTransporteSolicitacao = new FrtTransporteSolicitacao();

        LogFile.sucessoMsg(null, "Solicitacao Feita Com Sucesso !!!!!!");
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Solicitacao Feita Com Sucesso !!!", ""));

        //limpar();
    }

    public void limpar()
    {

    }

    public Date dataSystem()
    {
        Date Data;
        Data = new Date();
        return Data;
    }

    // Gets e Sets
    public GrlTipoSolicitacao getGrlTipoSolicitacao()
    {
        return grlTipoSolicitacao;
    }

    public void setGrlTipoSolicitacao(GrlTipoSolicitacao grlTipoSolicitacao)
    {
        this.grlTipoSolicitacao = grlTipoSolicitacao;
    }

    public FrtTransporteSolicitacao getFrtTransporteSolicitacao()
    {
        return frtTransporteSolicitacao;
    }

    public void setFrtTransporteSolicitacao(FrtTransporteSolicitacao frtTransporteSolicitacao)
    {
        this.frtTransporteSolicitacao = frtTransporteSolicitacao;
    }

    public RhFuncionario getRhFuncionario()
    {
        return rhFuncionario;
    }

    public void setRhFuncionario(RhFuncionario rhFuncionario)
    {
        this.rhFuncionario = rhFuncionario;
    }

    public EstruturaLogica getEstruturaLogica()
    {
        return estruturaLogica;
    }

    public void setEstruturaLogica(EstruturaLogica estruturaLogica)
    {
        this.estruturaLogica = estruturaLogica;
    }

    public PtTransporteTipo getPtTransporteTipo()
    {
        return ptTransporteTipo;
    }

    public void setPtTransporteTipo(PtTransporteTipo ptTransporteTipo)
    {
        this.ptTransporteTipo = ptTransporteTipo;
    }

//    public FrtConfiguracoes getFrtConfiguracoes() {
//        return frtConfiguracoes;
//    }
//
//    public void setFrtConfiguracoes(FrtConfiguracoes frtConfiguracoes) {
//        this.frtConfiguracoes = frtConfiguracoes;
//    }
    public FrtTransporteTipoAgendamento getFrtTransporteTipoAgendamento()
    {
        return frtTransporteTipoAgendamento;
    }

    public void setFrtTransporteTipoAgendamento(FrtTransporteTipoAgendamento frtTransporteTipoAgendamento)
    {
        this.frtTransporteTipoAgendamento = frtTransporteTipoAgendamento;
    }

    public int getCodigoFrtTransporteTipoAgendamento()
    {
        return codigoFrtTransporteTipoAgendamento;
    }

    public void setCodigoFrtTransporteTipoAgendamento(int codigoFrtTransporteTipoAgendamento)
    {
        this.codigoFrtTransporteTipoAgendamento = codigoFrtTransporteTipoAgendamento;
    }

    public int getCodigoGrlTipoSolicitacao()
    {
        return codigoGrlTipoSolicitacao;
    }

    public void setCodigoGrlTipoSolicitacao(int codigoGrlTipoSolicitacao)
    {
        this.codigoGrlTipoSolicitacao = codigoGrlTipoSolicitacao;
    }

    public int getCodigoPtTransporteTipo()
    {
        return codigoPtTransporteTipo;
    }

    public void setCodigoPtTransporteTipo(int codigoPtTransporteTipo)
    {
        this.codigoPtTransporteTipo = codigoPtTransporteTipo;
    }

    /*
    public FrtTransporteSolicitacao getSolicitar() {
        return solicitar;
    }

    public void setSolicitar(FrtTransporteSolicitacao solicitar) {
        this.solicitar = solicitar;
    }
     */
    public FrtTransporteSolicitacaoEstado getFrtTransporteSolicitacaoEstado()
    {
        return frtTransporteSolicitacaoEstado;
    }

    public void setFrtTransporteSolicitacaoEstado(FrtTransporteSolicitacaoEstado frtTransporteSolicitacaoEstado)
    {
        this.frtTransporteSolicitacaoEstado = frtTransporteSolicitacaoEstado;
    }

    public int getCodigoRhFuncionario()
    {
        return codigoRhFuncionario;
    }

    public void setCodigoRhFuncionario(int codigoRhFuncionario)
    {
        this.codigoRhFuncionario = codigoRhFuncionario;
    }

    public GrlEndereco getGrlEndereco()
    {
        return grlEndereco;
    }

    public void setGrlEndereco(GrlEndereco grlEndereco)
    {
        this.grlEndereco = grlEndereco;
    }

}
