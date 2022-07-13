/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.facades;

import ejbs.entities.ArDocente;
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
public class ArDocenteFacade extends AbstractFacade<ArDocente> {

    @PersistenceContext(unitName = "SIG_UCAN-PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ArDocenteFacade() {
        super(ArDocente.class);
    }
    
    public List<ArDocente> findDocenteByDescricao(String docente)
    {
        Query q = em.createQuery("SELECT c FROM ArDocente c WHERE c.nomeCompleto =:docente");
        q.setParameter("docente", docente);
        q.setMaxResults(1);
        
        return q.getResultList();
    }
    
}
