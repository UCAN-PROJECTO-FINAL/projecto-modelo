/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frt.beans;

import ejbs.facades.FrtTransporteTipoAgendamentoFacade;
import ejbs.facades.GrlTipoSolicitacaoFacade;
import ejbs.facades.PtTransporteTipoFacade;
import ejbs.facades.FrtTransporteConfiguracoesFacade;
import ejbs.facades.GrlSexoFacade;
import ejbs.facades.GrlEstadoCivilFacade;
import ejbs.facades.RhFuncaoFacade;
import ejbs.facades.RhEspecialidadeFacade;
import ejbs.facades.RhTipoFuncionarioFacade;
import ejbs.facades.RhNivelAcademicoFacade;
import ejbs.facades.EstruturaLogicaFacade;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import ejbs.entities.FrtTransporteConfiguracoes;
import ejbs.entities.FrtTransporteTipoAgendamento;
import ejbs.entities.GrlTipoSolicitacao;
import ejbs.entities.PtTransporteTipo;
import ejbs.entities.RhFuncionario;
import ejbs.entities.EstruturaLogica;
import ejbs.entities.FinModoPagamento;
import ejbs.entities.FrtTransporteBombaCombustivel;
import ejbs.entities.FrtTransporteTipoAcidente;
import ejbs.entities.FrtTransporteTipoCombustivel;
import ejbs.entities.FrtTransporteTipoIluminacao;
import ejbs.entities.FrtTransporteTipoInfraccao;
import ejbs.entities.FrtTransporteTipoManutencao;
//import ejbs.entities.FrtTransporteTipoPavimento;
import ejbs.entities.GrlSexo;
import ejbs.entities.GrlEstadoCivil;
import ejbs.entities.RhFuncao;
import ejbs.entities.RhEspecialidade;
import ejbs.entities.RhTipoFuncionario;
import ejbs.entities.RhNivelAcademico;
import ejbs.facades.FinModoPagamentoFacade;
import ejbs.facades.FrtTransporteBombaCombustivelFacade;
import ejbs.facades.FrtTransporteTipoAcidenteFacade;
import ejbs.facades.FrtTransporteTipoCombustivelFacade;
import ejbs.facades.FrtTransporteTipoIluminacaoFacade;
import ejbs.facades.FrtTransporteTipoInfraccaoFacade;
import ejbs.facades.FrtTransporteTipoManutencaoFacade;
//import ejbs.facades.FrtTransporteTipoPavimentoFacade;
import javax.annotation.PostConstruct;
import utils.Defs;
import utils.ExcepcaoCarregamentoTabelasExcel;
import utils.mensagens.Mensagem;

/**
 *
 * @author samuel
 */
@Named(value = "transporteConfiguracoesBean")
@ViewScoped
public class FrtTransporteConfiguracoesBean implements Serializable
{

    @EJB
    private FrtTransporteTipoAgendamentoFacade frtTransporteTipoAgendamentoFacade;

    @EJB
    private PtTransporteTipoFacade ptTransporteTipoFacade;

    @EJB
    private GrlTipoSolicitacaoFacade grlTipoSolicitacaoFacade;

    @EJB
    FrtTransporteConfiguracoesFacade frtConfiguracoesFacade;

    @EJB
    EstruturaLogicaFacade estruturaLogicaFacade;

    @EJB
    GrlSexoFacade grlSexoFacade;

    @EJB
    GrlEstadoCivilFacade grlEstadoCivilFacade;

    @EJB
    RhFuncaoFacade rhFuncaoFacade;

    @EJB
    RhEspecialidadeFacade rhEspecialidadeFacade;

    @EJB
    RhTipoFuncionarioFacade rhTipoFuncionarioFacade;

    @EJB
    RhNivelAcademicoFacade rhNivelAcademicoFacade;

    @EJB
    FrtTransporteTipoManutencaoFacade frtTransporteTipoManutencaoFacade;

    @EJB
    FinModoPagamentoFacade finModoPagamentoFacade;

    @EJB
    FrtTransporteTipoInfraccaoFacade frtTransporteTipoInfraccaoFacade;

    @EJB
    FrtTransporteTipoAcidenteFacade frtTransporteTipoAcidenteFacade;

    @EJB
    FrtTransporteTipoIluminacaoFacade frtTransporteTipoIluminacaoFacade;

//    @EJB
//    FrtTransporteTipoPavimentoFacade frtTransporteTipoPavimentoFacade;

    @EJB
    FrtTransporteTipoCombustivelFacade frtTransporteTipoCombustivelFacade;

    @EJB
    FrtTransporteBombaCombustivelFacade frtTransporteBombaCombustivelFacade;

    private FrtTransporteConfiguracoes frtConfiguracoes;
    private FrtTransporteTipoAgendamento frtTransporteTipoAgendamento;
    private GrlTipoSolicitacao grlTipoSolicitacao;
    private PtTransporteTipo ptTransporteTipo;
    private RhFuncionario RhFuncionario;
    private EstruturaLogica estruturaLogica;

    private GrlSexo grlSexo;
    private GrlEstadoCivil grlEstadoCivil;
    private RhFuncao rhFuncao;
    private RhEspecialidade rhEspecialidade;
    private RhTipoFuncionario rhTipoFuncionario;
    private RhNivelAcademico rhNivelAcademico;

    private FrtTransporteTipoManutencao frtTransporteTipoManutencao;
    private FinModoPagamento finModoPagamento;
    private FrtTransporteTipoAcidente frtTransporteTipoAcidente;
    private FrtTransporteTipoInfraccao frTransporteTipoInfraccao;
    private FrtTransporteTipoIluminacao frtTransporteTipoIluminacao;
//    private FrtTransporteTipoPavimento frtTransporteTipoPavimento;
    private FrtTransporteTipoCombustivel frtTransporteTipoCombustivel;
    private FrtTransporteBombaCombustivel frtTransporteBombaCombustivel;

    private int codigoFrtTransporteTipoAgendamento = 0;
    private int codigoGrlTipoSolicitacao = 0;
    private int codigoPtTransporteTipo = 0;
    private int codigoRhFuncionario = 0;
    private String codigoEstruturaLogica = "";
    private int codigoGrlSexo = 0;
    private int codigoGrlEstadoCivil = 0;
    private int codigoRhFuncao = 0;
    private int codigoRhEspecialidade = 0;
    private int codigoRhTipoFuncionario = 0;
    private int codigoRhNivelAcademico = 0;

    private int codigofrtTransporteTipoManutencao = 0;
    private int codigofinModoPagamento = 0;
    private int codigofrtTransporteTipoAcidente = 0;
    private int codigofrTransporteTipoInfraccao = 0;
    private int codigofrtTransporteTipoIluminacao = 0;
    private int codigofrtTransporteTipoPavimento = 0;
    private int codigofrtTransporteTipoCombustivel = 0;
    private int codigofrtTransporteBombaCombustivel = 0;

    /**
     * Creates a new instance of ConfiguracoesBean
     */
    public FrtTransporteConfiguracoesBean()
    {
    }

    @PostConstruct
    public void init()
    {
        this.frtConfiguracoes = this.frtConfiguracoesFacade.find();

        codigoGrlSexo = this.frtConfiguracoes.getFkSexo().getPkGrlSexo();
        codigoGrlEstadoCivil = this.frtConfiguracoes.getFkEstadoCivil().getPkGrlEstadoCivil();
        codigoRhFuncao = this.frtConfiguracoes.getFkFuncao().getPkFuncao();
        codigoRhEspecialidade = this.frtConfiguracoes.getFkEspecialidade().getPkEspecialidade();
        codigoRhTipoFuncionario = this.frtConfiguracoes.getFkTipoFuncionario().getPkTipoFuncionario();
        codigoRhNivelAcademico = this.frtConfiguracoes.getFkNivelAcademico().getPkNivelAcademico();

        codigofrtTransporteTipoManutencao = this.frtConfiguracoes.getFkTipoManutencao().getPkTipoManutencao();
//        codigofinModoPagamento =;
        codigofrtTransporteTipoAcidente = this.frtConfiguracoes.getFkTipoAcidente().getPkTipoAcidente();
//        codigofrTransporteTipoInfraccao =;
//        codigofrtTransporteTipoIluminacao =;
//        codigofrtTransporteTipoPavimento =;
//        codigofrtTransporteTipoCombustivel =;
//        codigofrtTransporteBombaCombustivel =;

        codigoFrtTransporteTipoAgendamento = this.frtConfiguracoes.getFkFrtTipoAgendamento().getPkTipoAgendamento();
        System.out.println("frt.beans.ConfiguracoesBean.init() -" + codigoFrtTransporteTipoAgendamento);

        codigoGrlTipoSolicitacao = this.frtConfiguracoes.getFkGrlTipoSolicitacao().getPkTipoSolicitacao();
        System.out.println("frt.beans.ConfiguracoesBean.init() -" + codigoGrlTipoSolicitacao);

        codigoPtTransporteTipo = this.frtConfiguracoes.getFkPtTipoTransporte().getPkTipoTransporte();
        System.out.println("frt.beans.ConfiguracoesBean.init() -" + codigoPtTransporteTipo);

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

                frtConfiguracoes.setFkSexo(grlSexoFacade.find(codigoGrlSexo));
                frtConfiguracoes.setFkEstadoCivil(grlEstadoCivilFacade.find(codigoGrlEstadoCivil));
                frtConfiguracoes.setFkEspecialidade(rhEspecialidadeFacade.find(codigoRhEspecialidade));
                frtConfiguracoes.setFkFuncao(rhFuncaoFacade.find(codigoRhFuncao));
                frtConfiguracoes.setFkNivelAcademico(rhNivelAcademicoFacade.find(codigoRhNivelAcademico));
                frtConfiguracoes.setFkTipoFuncionario(rhTipoFuncionarioFacade.find(codigoRhTipoFuncionario));

                frtConfiguracoes.setFkTipoManutencao(frtTransporteTipoManutencaoFacade.find(codigofrtTransporteTipoManutencao));
                frtConfiguracoes.setFkModoPagamento(finModoPagamentoFacade.find(codigofinModoPagamento));
                frtConfiguracoes.setFkTipoAcidente(frtTransporteTipoAcidenteFacade.find(codigofrtTransporteTipoAcidente));
                frtConfiguracoes.setFkTipoInfracao(frtTransporteTipoInfraccaoFacade.find(codigofrTransporteTipoInfraccao));
                frtConfiguracoes.setFkTipoIluminacao(frtTransporteTipoIluminacaoFacade.find(codigofrtTransporteTipoIluminacao));
//                frtConfiguracoes.setFkTipoPavimento(frtTransporteTipoPavimentoFacade.find(codigofrtTransporteTipoPavimento));
                frtConfiguracoes.setFkTipoCombustivel(frtTransporteTipoCombustivelFacade.find(codigofrtTransporteTipoCombustivel));
                frtConfiguracoes.setFkBombasCombustivel(frtTransporteBombaCombustivelFacade.find(codigofrtTransporteBombaCombustivel));

                frtConfiguracoesFacade.edit(frtConfiguracoes);
                //gdDocumentoCadastrarBean.initConfiguracao();
            } catch (Exception e)
            {
                Mensagem.enviarJanelaMensagemErro(" Tente novamente!!! ", " Tente novamente!!! ");
            }

            Mensagem.enviarJanelaMensagemInformacao("Configuração gravada com sucesso!", "Configuração gravada com sucesso!");
            return true;
        }

        return false;
    }

    public FrtTransporteConfiguracoes reporConfiguracoesPadrao()
    {
        frtConfiguracoes = this.frtConfiguracoesFacade.reporConfiguracoesPadrao();
        init();
        System.out.println("INICIALIZADA");
        Mensagem.enviarJanelaMensagemInformacao("Configuração gravada com sucesso!", "Configuração gravada com sucesso!");
        return frtConfiguracoes;
    }

    public FrtTransporteConfiguracoes getFrtTransporteConfiguracoes()
    {
        return frtConfiguracoes;
    }

    public void setFrtTransporteConfiguracoes(FrtTransporteConfiguracoes frtConfiguracoes)
    {
        this.frtConfiguracoes = frtConfiguracoes;
    }

    public FrtTransporteTipoAgendamento getFrtTransporteTipoAgendamento()
    {
        return frtTransporteTipoAgendamento;
    }

    public void setFrtTransporteTipoAgendamento(FrtTransporteTipoAgendamento frtTransporteTipoAgendamento)
    {
        this.frtTransporteTipoAgendamento = frtTransporteTipoAgendamento;
    }

    public GrlTipoSolicitacao getGrlTipoSolicitacao()
    {
        return grlTipoSolicitacao;
    }

    public void setGrlTipoSolicitacao(GrlTipoSolicitacao grlTipoSolicitacao)
    {
        this.grlTipoSolicitacao = grlTipoSolicitacao;
    }

    public PtTransporteTipo getPtTransporteTipo()
    {
        return ptTransporteTipo;
    }

    public void setPtTransporteTipo(PtTransporteTipo ptTransporteTipo)
    {
        this.ptTransporteTipo = ptTransporteTipo;
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

    public FrtTransporteConfiguracoes getFrtConfiguracoes()
    {
        return frtConfiguracoes;
    }

    public void setFrtConfiguracoes(FrtTransporteConfiguracoes frtConfiguracoes)
    {
        this.frtConfiguracoes = frtConfiguracoes;
    }

    public GrlSexo getGrlSexo()
    {
        return grlSexo;
    }

    public void setGrlSexo(GrlSexo grlSexo)
    {
        this.grlSexo = grlSexo;
    }

    public GrlEstadoCivil getGrlEstadoCivil()
    {
        return grlEstadoCivil;
    }

    public void setGrlEstadoCivil(GrlEstadoCivil grlEstadoCivil)
    {
        this.grlEstadoCivil = grlEstadoCivil;
    }

    public RhFuncao getRhFuncao()
    {
        return rhFuncao;
    }

    public void setRhFuncao(RhFuncao rhFuncao)
    {
        this.rhFuncao = rhFuncao;
    }

    public RhEspecialidade getRhEspecialidade()
    {
        return rhEspecialidade;
    }

    public void setRhEspecialidade(RhEspecialidade rhEspecialidade)
    {
        this.rhEspecialidade = rhEspecialidade;
    }

    public RhTipoFuncionario getRhTipoFuncionario()
    {
        return rhTipoFuncionario;
    }

    public void setRhTipoFuncionario(RhTipoFuncionario rhTipoFuncionario)
    {
        this.rhTipoFuncionario = rhTipoFuncionario;
    }

    public RhNivelAcademico getRhNivelAcademico()
    {
        return rhNivelAcademico;
    }

    public void setRhNivelAcademico(RhNivelAcademico rhNivelAcademico)
    {
        this.rhNivelAcademico = rhNivelAcademico;
    }

    public int getCodigoGrlSexo()
    {
        return codigoGrlSexo;
    }

    public void setCodigoGrlSexo(int codigoGrlSexo)
    {
        this.codigoGrlSexo = codigoGrlSexo;
    }

    public int getCodigoGrlEstadoCivil()
    {
        return codigoGrlEstadoCivil;
    }

    public void setCodigoGrlEstadoCivil(int codigoGrlEstadoCivil)
    {
        this.codigoGrlEstadoCivil = codigoGrlEstadoCivil;
    }

    public int getCodigoRhFuncao()
    {
        return codigoRhFuncao;
    }

    public void setCodigoRhFuncao(int codigoRhFuncao)
    {
        this.codigoRhFuncao = codigoRhFuncao;
    }

    public int getCodigoRhEspecialidade()
    {
        return codigoRhEspecialidade;
    }

    public void setCodigoRhEspecialidade(int codigoRhEspecialidade)
    {
        this.codigoRhEspecialidade = codigoRhEspecialidade;
    }

    public int getCodigoRhTipoFuncionario()
    {
        return codigoRhTipoFuncionario;
    }

    public void setCodigoRhTipoFuncionario(int codigoRhTipoFuncionario)
    {
        this.codigoRhTipoFuncionario = codigoRhTipoFuncionario;
    }

    public int getCodigoRhNivelAcademico()
    {
        return codigoRhNivelAcademico;
    }

    public void setCodigoRhNivelAcademico(int codigoRhNivelAcademico)
    {
        this.codigoRhNivelAcademico = codigoRhNivelAcademico;
    }

    public FrtTransporteTipoManutencao getFrtTransporteTipoManutencao()
    {
        return frtTransporteTipoManutencao;
    }

    public void setFrtTransporteTipoManutencao(FrtTransporteTipoManutencao frtTransporteTipoManutencao)
    {
        this.frtTransporteTipoManutencao = frtTransporteTipoManutencao;
    }

    public FinModoPagamento getFinModoPagamento()
    {
        return finModoPagamento;
    }

    public void setFinModoPagamento(FinModoPagamento finModoPagamento)
    {
        this.finModoPagamento = finModoPagamento;
    }

    public FrtTransporteTipoAcidente getFrtTransporteTipoAcidente()
    {
        return frtTransporteTipoAcidente;
    }

    public void setFrtTransporteTipoAcidente(FrtTransporteTipoAcidente frtTransporteTipoAcidente)
    {
        this.frtTransporteTipoAcidente = frtTransporteTipoAcidente;
    }

    public FrtTransporteTipoInfraccao getFrTransporteTipoInfraccao()
    {
        return frTransporteTipoInfraccao;
    }

    public void setFrTransporteTipoInfraccao(FrtTransporteTipoInfraccao frTransporteTipoInfraccao)
    {
        this.frTransporteTipoInfraccao = frTransporteTipoInfraccao;
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

    public int getCodigofrtTransporteTipoManutencao()
    {
        return codigofrtTransporteTipoManutencao;
    }

    public void setCodigofrtTransporteTipoManutencao(int codigofrtTransporteTipoManutencao)
    {
        this.codigofrtTransporteTipoManutencao = codigofrtTransporteTipoManutencao;
    }

    public int getCodigofinModoPagamento()
    {
        return codigofinModoPagamento;
    }

    public void setCodigofinModoPagamento(int codigofinModoPagamento)
    {
        this.codigofinModoPagamento = codigofinModoPagamento;
    }

    public int getCodigofrtTransporteTipoAcidente()
    {
        return codigofrtTransporteTipoAcidente;
    }

    public void setCodigofrtTransporteTipoAcidente(int codigofrtTransporteTipoAcidente)
    {
        this.codigofrtTransporteTipoAcidente = codigofrtTransporteTipoAcidente;
    }

    public int getCodigofrTransporteTipoInfraccao()
    {
        return codigofrTransporteTipoInfraccao;
    }

    public void setCodigofrTransporteTipoInfraccao(int codigofrTransporteTipoInfraccao)
    {
        this.codigofrTransporteTipoInfraccao = codigofrTransporteTipoInfraccao;
    }

    public int getCodigofrtTransporteTipoIluminacao()
    {
        return codigofrtTransporteTipoIluminacao;
    }

    public void setCodigofrtTransporteTipoIluminacao(int codigofrtTransporteTipoIluminacao)
    {
        this.codigofrtTransporteTipoIluminacao = codigofrtTransporteTipoIluminacao;
    }

    public int getCodigofrtTransporteTipoPavimento()
    {
        return codigofrtTransporteTipoPavimento;
    }

    public void setCodigofrtTransporteTipoPavimento(int codigofrtTransporteTipoPavimento)
    {
        this.codigofrtTransporteTipoPavimento = codigofrtTransporteTipoPavimento;
    }

    public int getCodigofrtTransporteTipoCombustivel()
    {
        return codigofrtTransporteTipoCombustivel;
    }

    public void setCodigofrtTransporteTipoCombustivel(int codigofrtTransporteTipoCombustivel)
    {
        this.codigofrtTransporteTipoCombustivel = codigofrtTransporteTipoCombustivel;
    }

    public int getCodigofrtTransporteBombaCombustivel()
    {
        return codigofrtTransporteBombaCombustivel;
    }

    public void setCodigofrtTransporteBombaCombustivel(int codigofrtTransporteBombaCombustivel)
    {
        this.codigofrtTransporteBombaCombustivel = codigofrtTransporteBombaCombustivel;
    }

}
