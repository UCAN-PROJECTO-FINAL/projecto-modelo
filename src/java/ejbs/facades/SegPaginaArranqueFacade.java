/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.facades;

import ejbs.entities.SegPaginaArranque;
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
public class SegPaginaArranqueFacade extends AbstractFacade<SegPaginaArranque> {

    @PersistenceContext(unitName = "SIG_FRT_Master-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SegPaginaArranqueFacade() {
        super(SegPaginaArranque.class);
    }

    /**
     *
     * @param url
     * @return
     */
    public SegPaginaArranque findPaginaArranqueByUrl(String url) {
        url = url.trim();
        Query qrs = em.createQuery("SELECT p FROM SegPaginaArranque p WHERE p.url = :url ", SegPaginaArranque.class);
        qrs.setParameter("url", url);
        List<SegPaginaArranque> list = qrs.getResultList();

        return !list.isEmpty() ? list.get(0) : null;
    }

    public SegPaginaArranque getInstancia() {
        return new SegPaginaArranque();
    }

}
