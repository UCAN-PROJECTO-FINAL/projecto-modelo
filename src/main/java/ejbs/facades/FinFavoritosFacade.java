/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.facades;

import ejbs.entities.FinFavoritos;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author majoao
 */
@Stateless
public class FinFavoritosFacade extends AbstractFacade<FinFavoritos> {

    @PersistenceContext(unitName = "SIG_UCAN-PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FinFavoritosFacade() {
        super(FinFavoritos.class);
    }

    public List<FinFavoritos> findAllFavoritos() {
        Query query;
        query = em.createQuery("SELECT f FROM FinFavoritos f WHERE f.pkFinFavoritos <= 5 ORDER BY f.numeroAcesso DESC");
        
        List<FinFavoritos> rs = query.getResultList();
        return (rs == null || rs.isEmpty()) ? null  : rs;
    }

}
