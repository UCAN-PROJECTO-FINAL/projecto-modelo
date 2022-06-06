/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.facades;

import ejbs.entities.SegConta;
import ejbs.entities.SegLogAcesso;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author smakambo
 */
@Stateless
public class SegLogAcessoFacade extends AbstractFacade<SegLogAcesso> {

    @PersistenceContext(unitName = "SIG_UCAN-PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SegLogAcessoFacade() {
        super(SegLogAcesso.class);
    }
    
      public int remove(SegConta segConta)
    {
        Query q = em.createQuery("DELETE FROM SegLogAcesso cf WHERE cf.fkSegConta.pkSegConta = :pkSegConta");
        q.setParameter("pkSegConta", segConta.getPkSegConta());
        return q.executeUpdate();
    }
    
}
