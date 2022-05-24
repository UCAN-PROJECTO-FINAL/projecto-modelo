/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.facades;

import ejbs.entities.GrlSexo;
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
public class GrlSexoFacade extends AbstractFacade<GrlSexo> {

    @PersistenceContext(unitName = "SIG_FRT_Master-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GrlSexoFacade() {
        super(GrlSexo.class);
    }
    
      public List<GrlSexo> findAllSexoOrderByDescricao()
    {
        Query query = em.createQuery("SELECT t FROM GrlSexo t ORDER BY t.nome");
        List<GrlSexo> result = query.getResultList();
        return result;
    }
    
    public List<GrlSexo> findRhSexoByDescricao(String sexo)
    {
        Query q = em.createQuery("SELECT c FROM GrlSexo c WHERE c.nome =:sexo");
        q.setParameter("sexo", sexo);
        q.setMaxResults(1);
        
        return q.getResultList();
    }
    
}
