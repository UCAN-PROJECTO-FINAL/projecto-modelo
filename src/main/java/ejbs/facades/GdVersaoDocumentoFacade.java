/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.facades;

import ejbs.entities.GdVersaoDocumento;
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
public class GdVersaoDocumentoFacade extends AbstractFacade<GdVersaoDocumento> {

    @PersistenceContext(unitName = "SIG_UCAN-PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GdVersaoDocumentoFacade() {
        super(GdVersaoDocumento.class);
    }
    
    public List<GdVersaoDocumento> findVersaoDocumentoByDocumento(int codigo)
    {
        Query q = em.createQuery("SELECT d FROM GdVersaoDocumento d WHERE d.fkDocumento.pkDocumento=:codigo ORDER BY d.dataRegisto");
        q.setParameter("codigo", codigo);
        return q.getResultList();
    }
//    public List<VersaoDocumento> findVersaoDocumentoByDocumento(int codigo)
//    {
//        Query q = em.createQuery("SELECT d FROM VersaoDocumento d WHERE d.estado=true AND d.fkDocumento.pkDocumento=:codigo ORDER BY d.dataRegisto");
//        q.setParameter("codigo", codigo);
//        return q.getResultList();
//    }
    
}
