/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.facades;

import ejbs.entities.FrtTransporteSolicitacaoEstado;
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
public class FrtTransporteSolicitacaoEstadoFacade extends AbstractFacade<FrtTransporteSolicitacaoEstado> {

    @PersistenceContext(unitName = "SIG_FRT_Master-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FrtTransporteSolicitacaoEstadoFacade() {
        super(FrtTransporteSolicitacaoEstado.class);
    }
    
    
    public List<FrtTransporteSolicitacaoEstado> findEstadoSolicitacaoByDescricao(String estado)
    {
        Query q = em.createQuery("SELECT c FROM FrtTransporteSolicitacaoEstado c WHERE c.descricao =:estado");
        q.setParameter("estado", estado);
        q.setMaxResults(1);
        
        return q.getResultList();
    }
    
    
    public List<FrtTransporteSolicitacaoEstado> findAllSolicitacaoEstadoOrderByDescricao()
    {
        Query query = em.createQuery("SELECT t FROM FrtTransporteSolicitacaoEstado t ORDER BY t.descricao");
        List<FrtTransporteSolicitacaoEstado> result = query.getResultList();
        return result;
    } 

    
}
