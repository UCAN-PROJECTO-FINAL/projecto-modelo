/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.facades;

import ejbs.entities.FrtTransporteConfiguracoes;
import ejbs.entities.FrtTransporteTipoAgendamento;
import ejbs.entities.GrlEstadoCivil;
import ejbs.entities.GrlSexo;
import ejbs.entities.GrlTipoSolicitacao;
import ejbs.entities.PtTransporteTipo;
import ejbs.entities.RhEspecialidade;
import ejbs.entities.RhFuncao;
import ejbs.entities.RhNivelAcademico;
import ejbs.entities.RhTipoFuncionario;
import ejbs.entities.FrtTransporteTipoManutencao;
import ejbs.entities.FinModoPagamento;
import ejbs.entities.FrtTransporteTipoAcidente;
import ejbs.entities.FrtTransporteTipoInfraccao;
import ejbs.entities.FrtTransporteTipoIluminacao;
//import ejbs.entities.FrtTransporteTipoPavimento;
import ejbs.entities.FrtTransporteTipoCombustivel;
import ejbs.entities.FrtTransporteBombaCombustivel;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import utils.Defs;

/**
 *
 * @author smakambo
 */
@Stateless
public class FrtTransporteConfiguracoesFacade extends AbstractFacade<FrtTransporteConfiguracoes>
{

    @EJB
    private FrtTransporteTipoAgendamentoFacade frtTransporteTipoAgendamentoFacade;

    @EJB
    private PtTransporteTipoFacade ptTransporteTipoFacade;

    @EJB
    private GrlTipoSolicitacaoFacade grlTipoSolicitacaoFacade;

    @EJB
    private EstruturaLogicaFacade estruturaLogicaFacade;

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
    FrtTransporteTipoInfraccaoFacade frtTransporteTipoInfraccao;

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

    @PersistenceContext(unitName = "SIG_UCAN-PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager()
    {
        return em;
    }

    public FrtTransporteConfiguracoesFacade()
    {
        super(FrtTransporteConfiguracoes.class);
    }

    public FrtTransporteConfiguracoes find()
    {
        Query query = em.createQuery("SELECT c FROM FrtTransporteConfiguracoes c");
        List<FrtTransporteConfiguracoes> list = query.getResultList();
        return (list == null || list.isEmpty()) ? gerarConfiguracoesPadrao() : list.get(0);
    }

    /**
     *
     * @return
     */
    /**
     *
     * @return
     */
    public FrtTransporteConfiguracoes gerarConfiguracoesPadrao()
    {
        FrtTransporteConfiguracoes configuracoesPadrao = new FrtTransporteConfiguracoes();

        GrlTipoSolicitacao grlTipoSolicitacao = this.grlTipoSolicitacaoFacade.find(Defs.TIPO_SOLICITACAO_PADRAO_CODIGO);
        PtTransporteTipo ptTransporteTipo = this.ptTransporteTipoFacade.find(Defs.TIPO_TRANSPORTE_PADRAO_CODIGO);
        FrtTransporteTipoAgendamento frtTransporteTipoAgendamento = this.frtTransporteTipoAgendamentoFacade.find(Defs.TIPO_AGENDAMENTO_PADRAO_CODIGO);
        GrlSexo grlSexo = this.grlSexoFacade.find(Defs.SEXO_PADRAO_CODIGO);
        GrlEstadoCivil grlEstadoCivil = this.grlEstadoCivilFacade.find(Defs.ESTADO_CIVIL_PADRAO_CODIGO);
        RhFuncao rhFuncao = this.rhFuncaoFacade.find(Defs.FUNCAO_FINCIONARIO_PADRAO_CODIGO);
        RhEspecialidade rhEspecialidade = this.rhEspecialidadeFacade.find(Defs.ESPECIALIDADE_PADRAO_CODIGO);
        RhTipoFuncionario rhTipoFuncionario = this.rhTipoFuncionarioFacade.find(Defs.TIPO_FUNCIONARIO_PADRAO_CODIGO);
        RhNivelAcademico rhNivelAcademico = this.rhNivelAcademicoFacade.find(Defs.NIVEL_ACADEMICO_PADRAO_CODIGO);
        FrtTransporteTipoManutencao frtTransporteTipoManutencao = this.frtTransporteTipoManutencaoFacade.find(Defs.TIPO_MANUTENCAO_PADRAO_CODIGO);
        FinModoPagamento finModoPagamento = this.finModoPagamentoFacade.find(Defs.MODO_PAGAMENTO_PADRAO_CODIGO);
        FrtTransporteTipoAcidente frtTransporteTipoAcidente = this.frtTransporteTipoAcidenteFacade.find(Defs.TIPO_ACIDENTE_PADRAO_CODIGO);
        FrtTransporteTipoInfraccao frtTransporteTipoInfraccao = this.frtTransporteTipoInfraccao.find(Defs.TIPO_INFRACAO_PADRAO_CODIGO);
        FrtTransporteTipoIluminacao frtTransporteTipoIluminacao = this.frtTransporteTipoIluminacaoFacade.find(Defs.TIPO_ILUMINACAO_PADRAO_CODIGO);
//        FrtTransporteTipoPavimento frtTransporteTipoPavimento = this.frtTransporteTipoPavimentoFacade.find(Defs.TIPO_PAVIMENTO_PADRAO_CODIGO);
        FrtTransporteTipoCombustivel frtTransporteTipoCombustivel = this.frtTransporteTipoCombustivelFacade.find(Defs.MODO_PAGAMENTO_PADRAO_CODIGO);
        FrtTransporteBombaCombustivel frtTransporteBombaCombustivel = this.frtTransporteBombaCombustivelFacade.find(Defs.BOMBAS_COMBUSTIVEL_PADRAO_CODIGO);

        configuracoesPadrao.setFkGrlTipoSolicitacao(grlTipoSolicitacao);
        configuracoesPadrao.setFkPtTipoTransporte(ptTransporteTipo);
        configuracoesPadrao.setFkFrtTipoAgendamento(frtTransporteTipoAgendamento);
        configuracoesPadrao.setFkSexo(grlSexo);
        configuracoesPadrao.setFkEstadoCivil(grlEstadoCivil);
        configuracoesPadrao.setFkFuncao(rhFuncao);
        configuracoesPadrao.setFkEspecialidade(rhEspecialidade);
        configuracoesPadrao.setFkTipoFuncionario(rhTipoFuncionario);
        configuracoesPadrao.setFkNivelAcademico(rhNivelAcademico);
        configuracoesPadrao.setFkTipoManutencao(frtTransporteTipoManutencao);
        configuracoesPadrao.setFkModoPagamento(finModoPagamento);
        configuracoesPadrao.setFkTipoAcidente(frtTransporteTipoAcidente);
        configuracoesPadrao.setFkTipoInfracao(frtTransporteTipoInfraccao);
        configuracoesPadrao.setFkTipoIluminacao(frtTransporteTipoIluminacao);
//        configuracoesPadrao.setFkTipoPavimento(frtTransporteTipoPavimento);
        configuracoesPadrao.setFkTipoCombustivel(frtTransporteTipoCombustivel);
        configuracoesPadrao.setFkBombasCombustivel(frtTransporteBombaCombustivel);

        return this.createRegisto(configuracoesPadrao) ? configuracoesPadrao : null;
    }

    /**
     *
     * @return
     */
    public FrtTransporteConfiguracoes reporConfiguracoesPadrao()
    {
        Query query = em.createQuery("SELECT c FROM FrtTransporteConfiguracoes c");
        List<FrtTransporteConfiguracoes> list = query.getResultList();
        if (list == null || list.isEmpty())
        {
            return gerarConfiguracoesPadrao();
        }
        FrtTransporteConfiguracoes configuracoesPadrao = list.get(0);

        GrlTipoSolicitacao grlTipoSolicitacao = this.grlTipoSolicitacaoFacade.find(Defs.TIPO_SOLICITACAO_PADRAO_CODIGO);
        PtTransporteTipo ptTransporteTipo = this.ptTransporteTipoFacade.find(Defs.TIPO_TRANSPORTE_PADRAO_CODIGO);
        FrtTransporteTipoAgendamento frtTransporteTipoAgendamento = this.frtTransporteTipoAgendamentoFacade.find(Defs.TIPO_AGENDAMENTO_PADRAO_CODIGO);
        GrlSexo grlSexo = this.grlSexoFacade.find(Defs.SEXO_PADRAO_CODIGO);
        GrlEstadoCivil grlEstadoCivil = this.grlEstadoCivilFacade.find(Defs.ESTADO_CIVIL_PADRAO_CODIGO);
        RhFuncao rhFuncao = this.rhFuncaoFacade.find(Defs.FUNCAO_FINCIONARIO_PADRAO_CODIGO);
        RhEspecialidade rhEspecialidade = this.rhEspecialidadeFacade.find(Defs.ESPECIALIDADE_PADRAO_CODIGO);
        RhTipoFuncionario rhTipoFuncionario = this.rhTipoFuncionarioFacade.find(Defs.TIPO_FUNCIONARIO_PADRAO_CODIGO);
        RhNivelAcademico rhNivelAcademico = this.rhNivelAcademicoFacade.find(Defs.NIVEL_ACADEMICO_PADRAO_CODIGO);
        FrtTransporteTipoManutencao frtTransporteTipoManutencao = this.frtTransporteTipoManutencaoFacade.find(Defs.TIPO_MANUTENCAO_PADRAO_CODIGO);
        FinModoPagamento finModoPagamento = this.finModoPagamentoFacade.find(Defs.MODO_PAGAMENTO_PADRAO_CODIGO);
        FrtTransporteTipoAcidente frtTransporteTipoAcidente = this.frtTransporteTipoAcidenteFacade.find(Defs.TIPO_ACIDENTE_PADRAO_CODIGO);
        FrtTransporteTipoInfraccao frtTransporteTipoInfraccao = this.frtTransporteTipoInfraccao.find(Defs.TIPO_INFRACAO_PADRAO_CODIGO);
        FrtTransporteTipoIluminacao frtTransporteTipoIluminacao = this.frtTransporteTipoIluminacaoFacade.find(Defs.TIPO_ILUMINACAO_PADRAO_CODIGO);
//        FrtTransporteTipoPavimento frtTransporteTipoPavimento = this.frtTransporteTipoPavimentoFacade.find(Defs.TIPO_PAVIMENTO_PADRAO_CODIGO);
        FrtTransporteTipoCombustivel frtTransporteTipoCombustivel = this.frtTransporteTipoCombustivelFacade.find(Defs.MODO_PAGAMENTO_PADRAO_CODIGO);
        FrtTransporteBombaCombustivel frtTransporteBombaCombustivel = this.frtTransporteBombaCombustivelFacade.find(Defs.BOMBAS_COMBUSTIVEL_PADRAO_CODIGO);

        //RhFuncionario rhFuncionario = this.rhFuncionarioFacade.find(Defs.CODIGO_FUNCIONARIO_PADRAO_CODIGO);
        //EstruturaLogica estruturaLogica = this.estruturaLogicaFacade.find(Defs.ESTRUTURA_LOGICA_PADRAO_CODIGO);
        configuracoesPadrao.setFkGrlTipoSolicitacao(grlTipoSolicitacao);
        configuracoesPadrao.setFkPtTipoTransporte(ptTransporteTipo);
        configuracoesPadrao.setFkFrtTipoAgendamento(frtTransporteTipoAgendamento);
        configuracoesPadrao.setFkSexo(grlSexo);
        configuracoesPadrao.setFkEstadoCivil(grlEstadoCivil);
        configuracoesPadrao.setFkFuncao(rhFuncao);
        configuracoesPadrao.setFkEspecialidade(rhEspecialidade);
        configuracoesPadrao.setFkTipoFuncionario(rhTipoFuncionario);
        configuracoesPadrao.setFkNivelAcademico(rhNivelAcademico);
        configuracoesPadrao.setFkTipoManutencao(frtTransporteTipoManutencao);
        configuracoesPadrao.setFkModoPagamento(finModoPagamento);
        configuracoesPadrao.setFkTipoAcidente(frtTransporteTipoAcidente);
        configuracoesPadrao.setFkTipoInfracao(frtTransporteTipoInfraccao);
        configuracoesPadrao.setFkTipoIluminacao(frtTransporteTipoIluminacao);
//        configuracoesPadrao.setFkTipoPavimento(frtTransporteTipoPavimento);
        configuracoesPadrao.setFkTipoCombustivel(frtTransporteTipoCombustivel);
        configuracoesPadrao.setFkBombasCombustivel(frtTransporteBombaCombustivel);
        //configuracoesPadrao.setFkRhFuncionario(rhFuncionario);
        //configuracoesPadrao.setFkEstruturaLogica(estruturaLogica);

        return this.editRegisto(configuracoesPadrao) ? configuracoesPadrao : null;
    }

    /**
     *
     * @param configuracoes
     * @return
     */
    public boolean createRegisto(FrtTransporteConfiguracoes configuracoes)
    {
        try
        {
            this.create(configuracoes);
            return true;
        } catch (Exception e)
        {
            return false;
        }
    }

    /**
     *
     * @param configuracoes
     * @return
     */
    public boolean editRegisto(FrtTransporteConfiguracoes configuracoes)
    {
        try
        {
            this.edit(configuracoes);
            return true;
        } catch (Exception e)
        {
            return false;
        }
    }

}
