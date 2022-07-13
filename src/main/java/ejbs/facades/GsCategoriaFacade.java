/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.facades;

import ejbs.entities.GsCategoria;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author jesus
 */
@Stateless
public class GsCategoriaFacade extends AbstractFacade<GsCategoria> {

    @PersistenceContext(unitName = "SIG_UCAN-PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GsCategoriaFacade() {
        super(GsCategoria.class);
    }

    public List<GsCategoria> findAllOrderByDescricao() {
        Query query = em.createQuery("SELECT g FROM GsCategoria g ORDER BY g.descricao");
        List<GsCategoria> result = query.getResultList();
        return result;
    }

}
