/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.facades;

import ejbs.entities.FinDocumento;
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
public class FinDocumentoFacade extends AbstractFacade<FinDocumento> {

    @PersistenceContext(unitName = "SIG_UCAN-PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FinDocumentoFacade() {
        super(FinDocumento.class);
    }
    
//    public List<CtDocument> getDocumentoPorDiario(CtDiario codigoDiario) {
//        TypedQuery<CtDocument> query;
//        query = em.createQuery("SELECT c FROM CtDocument c WHERE "
//                + "c.fkDiario = :id AND c.stateDocument = true", CtDocument.class)
//                .setParameter("id", codigoDiario);
//
//        List<CtDocument> results = query.getResultList();
//        
//
//        if (results == null || results.isEmpty()) {
//            return null;
//        }
//        return results;
//    }
//    
//    
//    
//    public List<CtDocument> listDocuments(){
//        TypedQuery<CtDocument> query;
//        query = em.createQuery("SELECT c FROM CtDocument c WHERE "
//                + "c.stateDocument = true ORDER BY c.pkDocument", CtDocument.class);
//               
//        List<CtDocument> results = query.getResultList();
//
//        if (results.isEmpty()) {
//            return null;
//        }
//        return results;
//    }
//    
    public List<FinDocumento> getDocummento ()
    {
        Query query = em.createQuery ( "SELECT f FROM FinDocumento f ");

        List<FinDocumento> results = query.getResultList ();

        if ( results == null || results.isEmpty () )
        {
            return null;
        }
        return results;
    }
    
}
