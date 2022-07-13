/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.facades;

import ejbs.entities.RhEspecialidade;
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
public class RhEspecialidadeFacade extends AbstractFacade<RhEspecialidade> {

    @PersistenceContext(unitName = "SIG_UCAN-PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RhEspecialidadeFacade() {
        super(RhEspecialidade.class);
    }
    
       public List<RhEspecialidade> findAllEspecialidadeOrderByDescricao()
    {
        Query query = em.createQuery("SELECT t FROM RhEspecialidade t ORDER BY t.descricao");
        List<RhEspecialidade> result = query.getResultList();
        return result;
    } 
    
     
    
    public List<RhEspecialidade> findRhEspecialidadeByDescricao(String especialidade)
    {
        Query q = em.createQuery("SELECT c FROM RhEspecialidade c WHERE c.descricao =:especialidade");
        q.setParameter("especialidade", especialidade);
        q.setMaxResults(1);
        
        return q.getResultList();
    }
    
}
