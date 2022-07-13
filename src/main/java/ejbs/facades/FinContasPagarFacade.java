/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.facades;

import ejbs.entities.FinContasPagar;
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
public class FinContasPagarFacade extends AbstractFacade<FinContasPagar> {

    @PersistenceContext(unitName = "SIG_UCAN-PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FinContasPagarFacade() {
        super(FinContasPagar.class);
    }
    
     public List<FinContasPagar> getFinContasReceber(int pkconta) {
        try {
            Query q = em.createQuery("SELECT f FROM FinContasPagar f WHERE f.fkConta.pkConta =:idconta");
            q.setParameter("idconta", pkconta);
            return q.getResultList();
        } catch (Exception e) {
        }
        return null;
    }
    
}
