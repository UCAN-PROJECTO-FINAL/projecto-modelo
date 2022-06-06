/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.facades;

import ejbs.entities.ArFaculdade;
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
public class ArFaculdadeFacade extends AbstractFacade<ArFaculdade> {

    @PersistenceContext(unitName = "SIG_UCAN-PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ArFaculdadeFacade() {
        super(ArFaculdade.class);
    }
    
    public List<ArFaculdade> findFaculdadeByDescricao(String faculdade)
    {
        Query q = em.createQuery("SELECT c FROM ArFaculdade c WHERE c.descricao =:faculdade");
        q.setParameter("faculdade", faculdade);
        q.setMaxResults(1);
        
        return q.getResultList();
    }
    
}
