/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.facades;

import ejbs.entities.CtDiario;
import ejbs.entities.CtDocument;
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
public class CtDocumentFacade extends AbstractFacade<CtDocument> {

    @PersistenceContext(unitName = "SIG_UCAN-PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CtDocumentFacade() {
        super(CtDocument.class);
    }

    public List<CtDocument> getDocumentoPorDiario(int codigoDiario) {
        TypedQuery<CtDocument> query;
        if (codigoDiario != 0) {
            query = em.createQuery("SELECT c FROM CtDocument c WHERE "
                    + "c.fkDiario.pkDiario =:id AND c.stateDocument = true", CtDocument.class)
                    .setParameter("id", codigoDiario);

            List<CtDocument> results = query.getResultList();

            if (results == null || results.isEmpty()) {
                return null;
            }
            return results;
        }
        return null;
    }

    public List<CtDocument> listDocuments() {
        TypedQuery<CtDocument> query;
        query = em.createQuery("SELECT c FROM CtDocument c WHERE "
                + "c.stateDocument = true ORDER BY c.pkDocument", CtDocument.class);

        List<CtDocument> results = query.getResultList();

        if (results.isEmpty()) {
            return null;
        }
        return results;
    }
}
