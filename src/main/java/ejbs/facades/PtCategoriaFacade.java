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
import ejbs.entities.PtCategoria;
import ejbs.facades.AbstractFacade;

/**
 *
 * @author mdnext
 */
@Stateless
public class PtCategoriaFacade extends AbstractFacade<PtCategoria> {

    @PersistenceContext(unitName = "SIG_UCAN-PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PtCategoriaFacade() {
        super(PtCategoria.class);
    }
    
    public List<PtCategoria> findAllPtCategoriaPai()
    {
        Query query = em.createQuery("SELECT m FROM PtCategoria m WHERE m.fkPtCategoria = NULL");
        List<PtCategoria> result = query.getResultList();
        return result;
    }
    
    public List<PtCategoria> findAllPtCategoria(String pkPtCategoria)
    {
        Query query = em.createQuery("SELECT m FROM PtCategoria m ");
        List<PtCategoria> result = query.getResultList();
        return result;
    }
    
    public List<PtCategoria> findByfkPtCategoria(String pkPtCategoria)
    {
        Query query = em.createQuery("SELECT m FROM PtCategoria m WHERE m.fkPtCategoria = :pkPtCategoria");
        query.setParameter("pkPtCategoria", pkPtCategoria);
        List<PtCategoria> result = query.getResultList();
        return result;
    }
    
    public PtCategoria findByPkPtCategoria(String pkPtCategoria)
    {
        Query query = em.createQuery("SELECT m FROM PtCategoria m WHERE m.pkPtCategoria = :pkPtCategoria");
        query.setParameter("pkPtCategoria", pkPtCategoria);
        List<PtCategoria> result = query.getResultList();
        return result.isEmpty() ? null : result.get(0);
    }
    
     public PtCategoria findByDescricao(String descricao)
    {
        Query query = em.createQuery("SELECT m FROM PtCategoria m WHERE m.descricao LIKE :descricao");
        query.setParameter("descricao", descricao);
        List<PtCategoria> result = query.getResultList();
        return result.isEmpty() ? null : result.get(0);
    }
    
}
