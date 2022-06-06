/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.facades;

import ejbs.entities.FrtTransporteTipoCombustivel;
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
public class FrtTransporteTipoCombustivelFacade extends AbstractFacade<FrtTransporteTipoCombustivel> {

    @PersistenceContext(unitName = "SIG_UCAN-PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FrtTransporteTipoCombustivelFacade() {
        super(FrtTransporteTipoCombustivel.class);
    }
    
    
    
    public List<FrtTransporteTipoCombustivel> findTipoComustivelByDescricao(String tipocombustivel)
    {
        Query q = em.createQuery("SELECT c FROM FrtTransporteTipoCombustivel c WHERE c.descricao =:tipocombustivel");
        q.setParameter("tipocombustivel", tipocombustivel);
        q.setMaxResults(1);
        
        return q.getResultList();
    }
    
    
    public List<FrtTransporteTipoCombustivel> findAllTipoCombustiveOrderByDescricao()
    {
        Query query = em.createQuery("SELECT t FROM FrtTransporteTipoCombustivel t ORDER BY t.descricao");
        List<FrtTransporteTipoCombustivel> result = query.getResultList();
        return result;
    } 
    
}
