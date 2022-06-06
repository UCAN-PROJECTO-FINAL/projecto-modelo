/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.facades;

import ejbs.entities.FinCategoriaSubcategoria;
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
public class FinCategoriaSubcategoriaFacade extends AbstractFacade<FinCategoriaSubcategoria> {

    @PersistenceContext(unitName = "SIG_UCAN-PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FinCategoriaSubcategoriaFacade() {
        super(FinCategoriaSubcategoria.class);
    }
    
    public FinCategoriaSubcategoria findFinSubCategoria(int pkSubCategoria) {
        Query query = em.createQuery("SELECT p FROM FinCategoriaSubcategoria p where p.pkCategoriaSubcategoria =:pkSubCategoria", FinCategoriaSubcategoria.class).setParameter("pkSubCategoria", pkSubCategoria);
        query.setMaxResults(1);
        List<FinCategoriaSubcategoria> results = query.getResultList();
        if (results == null || results.isEmpty()) {
            return null;
        }
        FinCategoriaSubcategoria resultado = (FinCategoriaSubcategoria) query.getResultList().get(0);
        return resultado;
    }
    
    public boolean existeRegisto(int pkCategoria)
    {
        FinCategoriaSubcategoria reg = this.find(pkCategoria);
        return reg != null;
    }
}
