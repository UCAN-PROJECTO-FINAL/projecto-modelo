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
import ejbs.entities.PtMarca;
import ejbs.facades.AbstractFacade;

/**
 *
 * @author mdnext
 */
@Stateless
public class PtMarcaFacade extends AbstractFacade<PtMarca> {

    @PersistenceContext(unitName = "SIG_UCAN-PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PtMarcaFacade() {
        super(PtMarca.class);
    }
    
    public List<PtMarca> findAllMarcaOrderByDescricao(int pkPtMarca)
    {
        Query query = em.createQuery("SELECT m FROM PtMarca m ORDER BY m.descricao");
        List<PtMarca> result = query.getResultList();
        return result;
    }
    
    public PtMarca findByPkPtMarca(int pkPtMarca)
    {
        Query query = em.createQuery("SELECT m FROM PtMarca m WHERE m.pkPtMarca = :pkPtMarca");
        query.setParameter("pkPtMarca", pkPtMarca);
        List<PtMarca> result = query.getResultList();
        return result.isEmpty() ? null : result.get(0);
    }
    
     public PtMarca findByDescricao(String descricao)
    {
        Query query = em.createQuery("SELECT m FROM PtMarca m WHERE m.descricao LIKE :descricao");
        query.setParameter("descricao", descricao);
        List<PtMarca> result = query.getResultList();
        return result.isEmpty() ? null : result.get(0);
    }
    
}
