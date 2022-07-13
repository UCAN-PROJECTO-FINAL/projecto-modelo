/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.facades;

import ejbs.entities.GdEntidade;
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
public class GdEntidadeFacade extends AbstractFacade<GdEntidade> {

    @PersistenceContext(unitName = "SIG_UCAN-PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GdEntidadeFacade() {
        super(GdEntidade.class);
    }
    
    
    public List<GdEntidade> findEntidadeFacadeByDesignacao(String designacao)
    {
        Query q = em.createQuery("SELECT c FROM GdEntidade c WHERE c.designacao =:designacao");
        q.setParameter("designacao", designacao);
        q.setMaxResults(1);
        
        return q.getResultList();
    }
    
}

