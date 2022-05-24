/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.facades;

import ejbs.entities.SegAuditoria;
import ejbs.entities.SegConta;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author smakambo
 */
@Stateless
public class SegAuditoriaFacade extends AbstractFacade<SegAuditoria> {

    @PersistenceContext(unitName = "SIG_FRT_Master-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SegAuditoriaFacade() {
        super(SegAuditoria.class);
    }
    
    
    public int remove(SegConta segConta)
    {
        Query q = em.createQuery("DELETE FROM SegAuditoria cf WHERE cf.fkSegConta.pkSegConta = :pkSegConta");
        q.setParameter("pkSegConta", segConta.getPkSegConta());
        return q.executeUpdate();
    }
    
}
