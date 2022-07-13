/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.facades;

import ejbs.entities.ArCurso;
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
public class ArCursoFacade extends AbstractFacade<ArCurso> {

    @PersistenceContext(unitName = "SIG_UCAN-PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ArCursoFacade() {
        super(ArCurso.class);
    }
    
    public List<ArCurso> findCursoByDescricao(String curso)
    {
        Query q = em.createQuery("SELECT c FROM ArCurso c WHERE c.descricao =:curso");
        q.setParameter("curso", curso);
        q.setMaxResults(1);
        
        return q.getResultList();
    }
    
    public List<ArCurso> findCursoByFaculdade(int codigoFaculdade)
    {
        Query q = em.createQuery("SELECT c FROM ArCurso c WHERE c.fkFaculdade.pkFaculdade =:codigoFaculdade");
        q.setParameter("codigoFaculdade", codigoFaculdade);
        
        return q.getResultList();
    }
    
}
