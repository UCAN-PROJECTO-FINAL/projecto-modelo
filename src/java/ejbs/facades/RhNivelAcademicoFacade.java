/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.facades;

import ejbs.entities.RhNivelAcademico;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author smakambo
 */
@Stateless
public class RhNivelAcademicoFacade extends AbstractFacade<RhNivelAcademico> {

    @PersistenceContext(unitName = "SIG_FRT_Master-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RhNivelAcademicoFacade() {
        super(RhNivelAcademico.class);
    }
    
        public List<RhNivelAcademico> findAllFuncaoOrderByDescricao()
    {
        Query query = em.createQuery("SELECT t FROM RhNivelAcademico t ORDER BY t.descricao");
        List<RhNivelAcademico> result = query.getResultList();
        return result;
    }
    
    public List<RhNivelAcademico> findRhNivelAcademicoByDescricao(String nivelacademico)
    {
        Query q = em.createQuery("SELECT c FROM RhNivelAcademico c WHERE c.descricao =:nivelacademico");
        q.setParameter("nivelacademico", nivelacademico);
        q.setMaxResults(1);
        
        return q.getResultList();
    }
}
