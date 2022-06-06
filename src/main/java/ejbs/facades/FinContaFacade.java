/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.facades;

import ejbs.entities.FinConta;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author majoao
 */
@Stateless
public class FinContaFacade extends AbstractFacade<FinConta> {

    @PersistenceContext(unitName = "SIG_UCAN-PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FinContaFacade() {
        super(FinConta.class);
    }
    
    public List<FinConta> getListConta(int pkConta){
        Query q = em.createQuery("SELECT c FROM FinConta c WHERE c.pkConta =:pkConta");
        q.setParameter("pkConta", pkConta);
        return q.getResultList() != null ? q.getResultList() : null ;
    }
    
    public List<FinConta> getListContaByMoeda(int pkMoeda){
        Query q = em.createQuery("SELECT c FROM FinConta c WHERE c.fkMoeda.pkMoeda =:pkMoeda");
        q.setParameter("pkMoeda", pkMoeda);
        return q.getResultList() != null ? q.getResultList() : null ;
    }
}
