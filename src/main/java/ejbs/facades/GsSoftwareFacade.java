/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.facades;

import ejbs.entities.GsSoftware;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author jesus
 */
@Stateless
public class GsSoftwareFacade extends AbstractFacade<GsSoftware> {

    @PersistenceContext(unitName = "SIG_UCAN-PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GsSoftwareFacade() {
        super(GsSoftware.class);
    }
    
    public List<GsSoftware> findAllOrderByName() {
        Query query = em.createQuery("SELECT g FROM GsSoftware g ORDER BY g.nome");
        List<GsSoftware> result = query.getResultList();
        return result;
    }
    
}
