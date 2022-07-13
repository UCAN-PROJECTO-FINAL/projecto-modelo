/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.facades;

import ejbs.entities.ArPeriodo;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author david-salgueiro
 */
@Stateless
public class ArPeriodoFacade extends AbstractFacade<ArPeriodo> {

    @PersistenceContext(unitName = "SIG_UCAN-PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ArPeriodoFacade() {
        super(ArPeriodo.class);
    }
    
    public List<ArPeriodo> findPeriodoByDescricao(String periodo)
    {
        Query q = em.createQuery("SELECT c FROM ArPeriodo c WHERE c.descricao =:periodo");
        q.setParameter("periodo", periodo);
        q.setMaxResults(1);
        
        return q.getResultList();
    }
    
}
