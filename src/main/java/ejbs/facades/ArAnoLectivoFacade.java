/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.facades;

import ejbs.entities.ArAnoLectivo;
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
public class ArAnoLectivoFacade extends AbstractFacade<ArAnoLectivo> {

    @PersistenceContext(unitName = "SIG_UCAN-PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ArAnoLectivoFacade() {
        super(ArAnoLectivo.class);
    }
    
    public List<ArAnoLectivo> findAnoLectivoByDescricao(String anoLectivo)
    {
        Query q = em.createQuery("SELECT c FROM ArAnoLectivo c WHERE c.descricao =:anoLectivo");
        q.setParameter("anoLectivo", anoLectivo);
        q.setMaxResults(1);
        
        return q.getResultList();
    }
    
}
