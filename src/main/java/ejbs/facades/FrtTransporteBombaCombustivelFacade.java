/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.facades;

import ejbs.entities.FrtTransporteBombaCombustivel;
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
public class FrtTransporteBombaCombustivelFacade extends AbstractFacade<FrtTransporteBombaCombustivel> {

    @PersistenceContext(unitName = "SIG_UCAN-PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FrtTransporteBombaCombustivelFacade() {
        super(FrtTransporteBombaCombustivel.class);
    }
    
    public List<FrtTransporteBombaCombustivel> findBombasCombustivelByDescricao(String bombas)
    {
        Query q = em.createQuery("SELECT c FROM FrtTransporteBombaCombustivel c WHERE c.descricao =:bombas");
        q.setParameter("bombas", bombas);
        q.setMaxResults(1);
        
        return q.getResultList();
    }
    
    public List<FrtTransporteBombaCombustivel> findAllBombaCombustivelOrderByDescricao()
    {
        Query query = em.createQuery("SELECT t FROM FrtTransporteBombaCombustivel t ORDER BY t.descricao");
        List<FrtTransporteBombaCombustivel> result = query.getResultList();
        return result;
    } 
    
    public int codigo()
    {
        Query q = em.createQuery("SELECT r FROM FrtTransporteBombaCombustivel r");
        List<FrtTransporteBombaCombustivel> lista = q.getResultList();
        
        if (lista.isEmpty()){
        
            return 1;
        }else{
        
            return q.getMaxResults() + 1;
        }
        
    }
    
    
//    public List<FrtTransporteBombaCombustivel> findBombasCombustivelByDescricao(String bomba)
//    {
//        Query q = em.createQuery("SELECT c FROM FrtTransporteBombaCombustivel c WHERE c.descricao =:bomba");
//        q.setParameter("bomba", bomba);
//        q.setMaxResults(1);
//        
//        return q.getResultList();
//    }
    
}
