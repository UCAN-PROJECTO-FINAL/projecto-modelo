/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.facades;

import ejbs.entities.FrtTransporteSolicitacao;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author smakambo
 */
@Stateless
public class FrtTransporteSolicitacaoFacade extends AbstractFacade<FrtTransporteSolicitacao> {

    @PersistenceContext(unitName = "SIG_UCAN-PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FrtTransporteSolicitacaoFacade() {
        super(FrtTransporteSolicitacao.class);
    }
    
    public List<FrtTransporteSolicitacao> findAllSolicitacoesOrderByPkSolicitacoes()
    {
        Query query = em.createQuery("SELECT t FROM FrtTransporteSolicitacao t ORDER BY t.dataSolicitacao DESC");
        List<FrtTransporteSolicitacao> result = query.getResultList();
        return result;
    }
    
    public List<FrtTransporteSolicitacao> findAllSolicitacoesOrderByEstado(int pkEstado)
    {
        Query query = em.createQuery("SELECT t FROM FrtTransporteSolicitacao t WHERE t.fkTransporteSolicitacaoEstado.pkTransporteSolicitacaoEstado=:pkEstado ORDER BY t.dataSolicitacao DESC");
        query.setParameter("pkEstado", pkEstado);
        List<FrtTransporteSolicitacao> result = query.getResultList();
        return result;
    }
    
    
    public List<FrtTransporteSolicitacao> findAllSolicitacoesEmEspera()
    {
        Query query = em.createQuery("SELECT m FROM FrtTransporteSolicitacao m WHERE m.fkTransporteSolicitacaoEstado.descricao = :estadoSolicitacao");
        query.setParameter("estadoSolicitacao", "Espera");
        //query.setParameter("tipoagendamento2", "Local");
        //query.setParameter("estado", "true");
        List<FrtTransporteSolicitacao> result = query.getResultList();
        return result;
    }
    
    
    public List<FrtTransporteSolicitacao> findAllSolicitacoesByPk(int codigoSolicitacao)
    {
        Query query = em.createQuery("SELECT m FROM FrtTransporteSolicitacao m WHERE m.pkSolicitacao = :id");
        query.setParameter("id", codigoSolicitacao);
        //query.setParameter("tipoagendamento2", "Local");
        //query.setParameter("estado", "true");
        List<FrtTransporteSolicitacao> result = query.getResultList();
        return result;
    }
    
}
