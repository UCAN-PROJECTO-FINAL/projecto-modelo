/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.facades;

import ejbs.entities.FrtConfiguracoes;
import ejbs.entities.PtTransporteTipo;
import ejbs.entities.FrtTransporteTipoAgendamento;
import ejbs.entities.GrlTipoSolicitacao;
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
public class FrtConfiguracoesFacade extends AbstractFacade<FrtConfiguracoes> {
    
    @EJB
    private FrtTransporteTipoAgendamentoFacade frtTransporteTipoAgendamentoFacade;

    @EJB
    private PtTransporteTipoFacade ptTransporteTipoFacade;

    @EJB
    private GrlTipoSolicitacaoFacade grlTipoSolicitacaoFacade;
    
    
    
    @EJB
    private EstruturaLogicaFacade estruturaLogicaFacade;

    @PersistenceContext(unitName = "SIG_FRT_Master-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FrtConfiguracoesFacade() {
        super(FrtConfiguracoes.class);
    }
    
     
    
    public FrtConfiguracoes find()
    {
        Query query = em.createQuery("SELECT c FROM FrtConfiguracoes c");
        List<FrtConfiguracoes> list = query.getResultList();
        return (list == null || list.isEmpty()) ? gerarConfiguracoesPadrao() : list.get(0);
    }

    /**
     *
     * @return
     */
    public FrtConfiguracoes gerarConfiguracoesPadrao()
    {
        FrtConfiguracoes configuracoesPadrao = new FrtConfiguracoes();

        GrlTipoSolicitacao grlTipoSolicitacao = this.grlTipoSolicitacaoFacade.find(Defs.TIPO_SOLICITACAO_PADRAO_CODIGO);
        PtTransporteTipo ptTransporteTipo = this.ptTransporteTipoFacade.find(Defs.TIPO_TRANSPORTE_PADRAO_CODIGO);
        FrtTransporteTipoAgendamento frtTransporteTipoAgendamento = this.frtTransporteTipoAgendamentoFacade.find(Defs.TIPO_AGENDAMENTO_PADRAO_CODIGO);
       // RhFuncionario rhFuncionario = this.rhFuncionarioFacade.find(Defs.CODIGO_FUNCIONARIO_PADRAO_CODIGO);
        
  
        
        
        configuracoesPadrao.setFkGrlTipoSolicitacao(grlTipoSolicitacao);
        configuracoesPadrao.setFkPtTipoTransporte(ptTransporteTipo);
        configuracoesPadrao.setFkFrtTipoAgendamento(frtTransporteTipoAgendamento);
        
          
        return this.createRegisto(configuracoesPadrao) ? configuracoesPadrao : null;
    }

    /**
     *
     * @return
     */
    public FrtConfiguracoes reporConfiguracoesPadrao()
    {
        Query query = em.createQuery("SELECT c FROM FrtConfiguracoes c");
        List<FrtConfiguracoes> list = query.getResultList();
        if (list == null || list.isEmpty())
        {
            return gerarConfiguracoesPadrao();
        }
        FrtConfiguracoes configuracoesPadrao = list.get(0);
        
        GrlTipoSolicitacao grlTipoSolicitacao = this.grlTipoSolicitacaoFacade.find(Defs.TIPO_SOLICITACAO_PADRAO_CODIGO);
        PtTransporteTipo ptTransporteTipo = this.ptTransporteTipoFacade.find(Defs.TIPO_TRANSPORTE_PADRAO_CODIGO);
        FrtTransporteTipoAgendamento frtTransporteTipoAgendamento = this.frtTransporteTipoAgendamentoFacade.find(Defs.TIPO_AGENDAMENTO_PADRAO_CODIGO);
        //RhFuncionario rhFuncionario = this.rhFuncionarioFacade.find(Defs.CODIGO_FUNCIONARIO_PADRAO_CODIGO);
        //EstruturaLogica estruturaLogica = this.estruturaLogicaFacade.find(Defs.ESTRUTURA_LOGICA_PADRAO_CODIGO);
        
        
        configuracoesPadrao.setFkGrlTipoSolicitacao(grlTipoSolicitacao);
        configuracoesPadrao.setFkPtTipoTransporte(ptTransporteTipo);
        configuracoesPadrao.setFkFrtTipoAgendamento(frtTransporteTipoAgendamento);
        //configuracoesPadrao.setFkRhFuncionario(rhFuncionario);
        //configuracoesPadrao.setFkEstruturaLogica(estruturaLogica);

        return this.editRegisto(configuracoesPadrao) ? configuracoesPadrao : null;
    }

    /**
     *
     * @param configuracoes
     * @return
     */
    public boolean createRegisto(FrtConfiguracoes configuracoes)
    {
        try
        {
            this.create(configuracoes);
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }

    /**
     *
     * @param configuracoes
     * @return
     */
    public boolean editRegisto(FrtConfiguracoes configuracoes)
    {
        try
        {
            this.edit(configuracoes);
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }
    
    
}
