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
import ejbs.entities.PtBemEntrada;
import ejbs.facades.AbstractFacade;

/**
 *
 * @author mdnext
 */
@Stateless
public class PtBemEntradaFacade extends AbstractFacade<PtBemEntrada> {

    @PersistenceContext(unitName = "SIG_UCAN-PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PtBemEntradaFacade() {
        super(PtBemEntrada.class);
    }
    
    public List<PtBemEntrada> findAllPtBemEntrada()
    {
        Query query = em.createQuery("SELECT m FROM PtBemEntrada m ");
        List<PtBemEntrada> result = query.getResultList();
        return result;
    }
    
    public PtBemEntrada findByPkPtBemEntrada(int pkPtBemEntrada)
    {
        Query query = em.createQuery("SELECT m FROM PtBemEntrada m WHERE m.pkPtBemEntrada = :pkPtBemEntrada");
        query.setParameter("pkPtBemEntrada", pkPtBemEntrada);
        List<PtBemEntrada> result = query.getResultList();
        return result.isEmpty() ? null : result.get(0);
    }
    
    public List<PtBemEntrada> findByFkPtCategoria(String pkPtCategoria)
    {
        Query query = em.createQuery("SELECT m FROM PtBemEntrada m WHERE m.fkPtCategoria = :pkPtCategoria");
        query.setParameter("pkPtCategoria", pkPtCategoria);
        List<PtBemEntrada> result = query.getResultList();
        return result;
    }
}
