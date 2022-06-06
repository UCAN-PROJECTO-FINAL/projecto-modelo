/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.facades;

import ejbs.entities.ArTipoDocumento;
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
public class ArTipoDocumentoFacade extends AbstractFacade<ArTipoDocumento> {

    @PersistenceContext(unitName = "SIG_UCAN-PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ArTipoDocumentoFacade() {
        super(ArTipoDocumento.class);
    }
    
    public List<ArTipoDocumento> findTipoDocumentoByDescricao(String tipoDocumento)
    {
        Query q = em.createQuery("SELECT c FROM ArTipoDocumento c WHERE c.descricao =:tipoDocumento");
        q.setParameter("tipoDocumento", tipoDocumento);
        q.setMaxResults(1);
        
        return q.getResultList();
    }
    
    
}
