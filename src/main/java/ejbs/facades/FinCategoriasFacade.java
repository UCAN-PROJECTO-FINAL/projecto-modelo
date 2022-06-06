/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.facades;

import ejbs.entities.FinCategorias;
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
public class FinCategoriasFacade extends AbstractFacade<FinCategorias> {

    @PersistenceContext(unitName = "SIG_UCAN-PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FinCategoriasFacade() {
        super(FinCategorias.class);
    }
    
    public FinCategorias findFinCategoria(int pkCategoria) {
        Query query = em.createQuery("SELECT p FROM FinCategorias p where p.pkCategoria =:pkCategoria", FinCategorias.class).setParameter("pkCategoria", pkCategoria);
        query.setMaxResults(1);
        List<FinCategorias> results = query.getResultList();
        if (results == null || results.isEmpty()) {
            return null;
        }
        FinCategorias resultado = (FinCategorias) query.getResultList().get(0);
        return resultado;
    }
    
    public boolean existeRegisto(int pkCategoria)
    {
        FinCategorias reg = this.find(pkCategoria);
        return reg != null;
    }
}
