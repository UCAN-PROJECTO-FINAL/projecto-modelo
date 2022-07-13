/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.facades;

import ejbs.entities.GsSubcategoria;
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
public class GsSubcategoriaFacade extends AbstractFacade<GsSubcategoria> {

    @PersistenceContext(unitName = "SIG_UCAN-PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GsSubcategoriaFacade() {
        super(GsSubcategoria.class);
    }

    public List<GsSubcategoria> findAllOrderByDescricao() {
        Query query = em.createQuery("SELECT g FROM GsSubcategoria g ORDER BY g.descricao");
        List<GsSubcategoria> result = query.getResultList();
        return result;
    }

    public List<GsSubcategoria> findAllOrderBypkGsCategoria(String categoria) {
        Query query = em.createQuery("SELECT g FROM GsSubcategoria g WHERE g.fkGsCategoria.pkGsCategoria = :"+categoria);
//        query.setParameter(1, categoria);
        List<GsSubcategoria> result = query.getResultList();
        return result;
    }
}
