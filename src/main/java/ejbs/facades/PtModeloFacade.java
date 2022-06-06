/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.facades;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import ejbs.entities.PtModelo;
import ejbs.facades.AbstractFacade;

/**
 *
 * @author mdnext
 */
@Stateless
public class PtModeloFacade extends AbstractFacade<PtModelo> {

    @PersistenceContext(unitName = "SIG_UCAN-PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PtModeloFacade() {
        super(PtModelo.class);
    }
    
    public List<PtModelo> findAllModeloOrderByDescricao()
    {
        Query query = em.createQuery("SELECT m FROM PtModelo m ORDER BY m.descricao");
        List<PtModelo> result = query.getResultList();
        return result;
    }
    
    public List<PtModelo> findModeloByFkPtMarca(int pkPtMarca)
    {
        Query query = em.createQuery("SELECT m FROM PtModelo m WHERE m.fkPtMarca.pkPtMarca = :pkPtMarca");
        query.setParameter("pkPtMarca", pkPtMarca);
        List<PtModelo> result = query.getResultList();
        return result;
    }
    
    public PtModelo findByPkPtModelo(int pkPtModelo)
    {
        Query query = em.createQuery("SELECT m FROM PtModelo m WHERE m.pkPtModelo = :pkPtModelo");
        query.setParameter("pkPtModelo", pkPtModelo);
        List<PtModelo> result = query.getResultList();
        return result.isEmpty() ? null : result.get(0);
    }
    
     public PtModelo findByDescricao(String descricao)
    {
        Query query = em.createQuery("SELECT m FROM PtModelo m WHERE m.descricao LIKE :descricao");
        query.setParameter("descricao", descricao);
        List<PtModelo> result = query.getResultList();
        return result.isEmpty() ? null : result.get(0);
    }
    
    
}
