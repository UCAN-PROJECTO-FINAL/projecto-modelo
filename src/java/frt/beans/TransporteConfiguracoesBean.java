/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frt.beans;

import ejbs.facades.FrtTransporteTipoAgendamentoFacade;
import ejbs.facades.GrlTipoSolicitacaoFacade;
import ejbs.facades.PtTransporteTipoFacade;
import ejbs.facades.FrtConfiguracoesFacade;

import ejbs.facades.EstruturaLogicaFacade;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import ejbs.entities.FrtConfiguracoes;
import ejbs.entities.FrtTransporteTipoAgendamento;
import ejbs.entities.GrlTipoSolicitacao;
import ejbs.entities.PtTransporteTipo;
import ejbs.entities.RhFuncionario;
import ejbs.entities.EstruturaLogica;
import javax.annotation.PostConstruct;
import utils.ExcepcaoCarregamentoTabelasExcel;
import utils.mensagens.Mensagem;

/**
 *
 * @author samuel
 */
@Named(value = "transporteConfiguracoesBean")
@ViewScoped
public class TransporteConfiguracoesBean implements Serializable{
    
    @EJB
    private FrtTransporteTipoAgendamentoFacade frtTransporteTipoAgendamentoFacade;

    @EJB
    private PtTransporteTipoFacade ptTransporteTipoFacade;

    @EJB
    private GrlTipoSolicitacaoFacade grlTipoSolicitacaoFacade;
    
    @EJB
    FrtConfiguracoesFacade frtConfiguracoesFacade;
    
    
    @EJB
    EstruturaLogicaFacade estruturaLogicaFacade;
    
    private FrtConfiguracoes  frtConfiguracoes;
    private FrtTransporteTipoAgendamento frtTransporteTipoAgendamento;
    private GrlTipoSolicitacao  grlTipoSolicitacao;
    private PtTransporteTipo   ptTransporteTipo;  
    private RhFuncionario   RhFuncionario; 
    private EstruturaLogica estruturaLogica;
    
    private int codigoFrtTransporteTipoAgendamento = 0;
    private int codigoGrlTipoSolicitacao = 0;
    private int codigoPtTransporteTipo = 0;  
    private int codigoRhFuncionario = 0; 
    private String codigoEstruturaLogica = "";
            

    /**
     * Creates a new instance of ConfiguracoesBean
     */
    public TransporteConfiguracoesBean() {
    }
    
    
    @PostConstruct
    public void init()
    {
        this.frtConfiguracoes = this.frtConfiguracoesFacade.find();
        
        codigoFrtTransporteTipoAgendamento = this.frtConfiguracoes.getFkFrtTipoAgendamento().getPkTipoAgendamento();
        System.out.println("frt.beans.ConfiguracoesBean.init() -" +codigoFrtTransporteTipoAgendamento);
        
        codigoGrlTipoSolicitacao = this.frtConfiguracoes.getFkGrlTipoSolicitacao().getPkTipoSolicitacao();
        System.out.println("frt.beans.ConfiguracoesBean.init() -" +codigoGrlTipoSolicitacao);
        
        codigoPtTransporteTipo = this.frtConfiguracoes.getFkPtTipoTransporte().getPkTipoTransporte();
        System.out.println("frt.beans.ConfiguracoesBean.init() -" +codigoPtTransporteTipo);
        
        //codigoRhFuncionario = this.frtConfiguracoes.getFkRhFuncionario().getPkFuncionario();
        //System.out.println("frt.beans.ConfiguracoesBean.init() -" +codigoRhFuncionario);
        
        codigoEstruturaLogica = this.frtConfiguracoes.getFkEstruturaLogica().getPkEstruturaLogica();
        System.out.println("frt.beans.ConfiguracoesBean.init() -" +codigoEstruturaLogica);
        
    }
    
    
     public boolean salvarRegister() throws ExcepcaoCarregamentoTabelasExcel
    {
        if (frtConfiguracoes != null)
        {
            try
            {
                frtConfiguracoes.setFkFrtTipoAgendamento(frtTransporteTipoAgendamentoFacade.find(codigoFrtTransporteTipoAgendamento));
                frtConfiguracoes.setFkGrlTipoSolicitacao(grlTipoSolicitacaoFacade.find(codigoGrlTipoSolicitacao));
                frtConfiguracoes.setFkPtTipoTransporte(ptTransporteTipoFacade.find(codigoPtTransporteTipo));
                //frtConfiguracoes.setFkRhFuncionario(rhFuncionarioFacade.find(codigoRhFuncionario));
                frtConfiguracoes.setFkEstruturaLogica(estruturaLogicaFacade.find(codigoEstruturaLogica));
                frtConfiguracoesFacade.edit(frtConfiguracoes);
                //gdDocumentoCadastrarBean.initConfiguracao();
            }
            catch (Exception e)
            {
                 Mensagem.enviarJanelaMensagemErro(" Tente novamente!!! ", " Tente novamente!!! ");
            }

            Mensagem.enviarJanelaMensagemInformacao("Configuração gravada com sucesso!", "Configuração gravada com sucesso!");
            return true;
        }

        return false;
    }
    
    public FrtConfiguracoes reporConfiguracoesPadrao()
    {
        frtConfiguracoes = this.frtConfiguracoesFacade.reporConfiguracoesPadrao();
        System.out.println("INICIALIZADA");
        Mensagem.enviarJanelaMensagemInformacao("Configuração gravada com sucesso!", "Configuração gravada com sucesso!");
        return frtConfiguracoes;
    }

    public FrtConfiguracoes getFrtConfiguracoes() {
        return frtConfiguracoes;
    }

    public void setFrtConfiguracoes(FrtConfiguracoes frtConfiguracoes) {
        this.frtConfiguracoes = frtConfiguracoes;
    }

    public FrtTransporteTipoAgendamento getFrtTransporteTipoAgendamento() {
        return frtTransporteTipoAgendamento;
    }

    public void setFrtTransporteTipoAgendamento(FrtTransporteTipoAgendamento frtTransporteTipoAgendamento) {
        this.frtTransporteTipoAgendamento = frtTransporteTipoAgendamento;
    }

    public GrlTipoSolicitacao getGrlTipoSolicitacao() {
        return grlTipoSolicitacao;
    }

    public void setGrlTipoSolicitacao(GrlTipoSolicitacao grlTipoSolicitacao) {
        this.grlTipoSolicitacao = grlTipoSolicitacao;
    }

    public PtTransporteTipo getPtTransporteTipo() {
        return ptTransporteTipo;
    }

    public void setPtTransporteTipo(PtTransporteTipo ptTransporteTipo) {
        this.ptTransporteTipo = ptTransporteTipo;
    }

    public int getCodigoFrtTransporteTipoAgendamento() {
        return codigoFrtTransporteTipoAgendamento;
    }

    public void setCodigoFrtTransporteTipoAgendamento(int codigoFrtTransporteTipoAgendamento) {
        this.codigoFrtTransporteTipoAgendamento = codigoFrtTransporteTipoAgendamento;
    }

    public int getCodigoGrlTipoSolicitacao() {
        return codigoGrlTipoSolicitacao;
    }

    public void setCodigoGrlTipoSolicitacao(int codigoGrlTipoSolicitacao) {
        this.codigoGrlTipoSolicitacao = codigoGrlTipoSolicitacao;
    }

    public int getCodigoPtTransporteTipo() {
        return codigoPtTransporteTipo;
    }

    public void setCodigoPtTransporteTipo(int codigoPtTransporteTipo) {
        this.codigoPtTransporteTipo = codigoPtTransporteTipo;
    }

    public RhFuncionario getRhFuncionario()
    {
        return RhFuncionario;
    }

    public void setRhFuncionario(RhFuncionario RhFuncionario)
    {
        this.RhFuncionario = RhFuncionario;
    }

    public int getCodigoRhFuncionario()
    {
        return codigoRhFuncionario;
    }

    public void setCodigoRhFuncionario(int codigoRhFuncionario)
    {
        this.codigoRhFuncionario = codigoRhFuncionario;
    }

    public EstruturaLogica getEstruturaLogica()
    {
        return estruturaLogica;
    }

    public void setEstruturaLogica(EstruturaLogica estruturaLogica)
    {
        this.estruturaLogica = estruturaLogica;
    }

    public String getCodigoEstruturaLogica()
    {
        return codigoEstruturaLogica;
    }

    public void setCodigoEstruturaLogica(String codigoEstruturaLogica)
    {
        this.codigoEstruturaLogica = codigoEstruturaLogica;
    }
    
    
    
    
    
    
   
}
