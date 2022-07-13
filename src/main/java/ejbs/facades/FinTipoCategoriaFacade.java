/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.facades;

import ejbs.entities.FinTipoCategoria;
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
public class FinTipoCategoriaFacade extends AbstractFacade<FinTipoCategoria> {

    @PersistenceContext(unitName = "SIG_UCAN-PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FinTipoCategoriaFacade() {
        super(FinTipoCategoria.class);
    }

    public FinTipoCategoria findFinTipoCategoria(int pkTipoCategoria) {
        Query query = em.createQuery("SELECT p FROM FinTipoCategoria p where p.pkTipoCategoria =:pkTipoCategoria", FinTipoCategoria.class).setParameter("pkTipoCategoria", pkTipoCategoria);
        query.setMaxResults(1);
        List<FinTipoCategoria> results = query.getResultList();
        if (results == null || results.isEmpty()) {
            return null;
        }
        FinTipoCategoria resultado = (FinTipoCategoria) query.getResultList().get(0);
        return resultado;
    }
}
