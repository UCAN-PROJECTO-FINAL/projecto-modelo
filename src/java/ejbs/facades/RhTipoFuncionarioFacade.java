/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.facades;

import ejbs.entities.RhTipoFuncionario;
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
public class RhTipoFuncionarioFacade extends AbstractFacade<RhTipoFuncionario> {

    @PersistenceContext(unitName = "SIG_FRT_Master-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RhTipoFuncionarioFacade() {
        super(RhTipoFuncionario.class);
    }
    
      public List<RhTipoFuncionario> findAllTipoFuncionarioOrderByDescricao()
    {
        Query query = em.createQuery("SELECT t FROM RhTipoFuncionario t ORDER BY t.descricao");
        List<RhTipoFuncionario> result = query.getResultList();
        return result;
    }
    
       public List<RhTipoFuncionario> findRhTipoFuncionarioByDescricao(String tipofuncionario)
    {
        Query q = em.createQuery("SELECT c FROM RhNivelAcademico c WHERE c.descricao =:tipofuncionario");
        q.setParameter("tipofuncionario", tipofuncionario);
        q.setMaxResults(1);
        
        return q.getResultList();
    }
    
}
