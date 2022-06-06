/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.facades;

import ejbs.entities.RhFuncao;
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
public class RhFuncaoFacade extends AbstractFacade<RhFuncao> {

    @PersistenceContext(unitName = "SIG_UCAN-PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RhFuncaoFacade() {
        super(RhFuncao.class);
    }
    
     public List<RhFuncao> findAllFuncaoOrderByDescricao()
    {
        Query query = em.createQuery("SELECT t FROM RhFuncao t ORDER BY t.descricao");
        List<RhFuncao> result = query.getResultList();
        return result;
    }
    
    public List<RhFuncao> findRhFuncaoByDescricao(String funcao)
    {
        Query q = em.createQuery("SELECT c FROM RhFuncao c WHERE c.descricao =:funcao");
        q.setParameter("funcao", funcao);
        q.setMaxResults(1);
        
        return q.getResultList();
    }
    
}
