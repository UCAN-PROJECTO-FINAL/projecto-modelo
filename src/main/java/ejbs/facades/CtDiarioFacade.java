/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.facades;

import ejbs.entities.CtDiario;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author majoao
 */
@Stateless
public class CtDiarioFacade extends AbstractFacade<CtDiario> {

    @PersistenceContext(unitName = "SIG_UCAN-PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CtDiarioFacade() {
        super(CtDiario.class);
    }

    public List<CtDiario> listDiarios() {
        TypedQuery<CtDiario> query;
        query = em.createQuery("SELECT c FROM CtDiario c WHERE c.stateDiario = true "
                + "ORDER BY c.pkDiario", CtDiario.class);

        List<CtDiario> results = query.getResultList();

        if (results.isEmpty()) {
            return null;
        }
        return results;
    }

}
