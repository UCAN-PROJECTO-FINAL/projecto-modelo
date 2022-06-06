/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.facades;

import ejbs.entities.GdDocumento;
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
public class GdDocumentoFacade extends AbstractFacade<GdDocumento> {

    @PersistenceContext(unitName = "SIG_UCAN-PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GdDocumentoFacade() {
        super(GdDocumento.class);
    }
    
    
    public List<GdDocumento> findAllDocumentoOrderByData()
    {
        Query q = em.createQuery("SELECT d FROM GdDocumento d ORDER BY d.dataCriacao DESC");
        return q.getResultList();
    }
    
//    public List<Documento> findAllDocumentoOrderByData()
//    {
//        Query q = em.createQuery("SELECT d FROM Documento d WHERE d.estado=true ORDER BY d.dataCriacao");
//        return q.getResultList();
//    }
    
}

