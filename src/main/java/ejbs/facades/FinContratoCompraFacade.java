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
import ejbs.entities.FinContratoCompra;

/**
 *
 * @author mdnext
 */
@Stateless
public class FinContratoCompraFacade extends AbstractFacade<FinContratoCompra> {

    @PersistenceContext(unitName = "SIG_UCAN-PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FinContratoCompraFacade() {
        super(FinContratoCompra.class);
    }
    public List<FinContratoCompra> findAllFinContratoCompra()
    {
        Query query = em.createQuery("SELECT m FROM FinContratoCompra m ");
        List<FinContratoCompra> result = query.getResultList();
        return result;
    }
    
    public FinContratoCompra findByPkFinContratoCompra(int pkFinContratoCompra)
    {
        Query query = em.createQuery("SELECT m FROM FinContratoCompra m WHERE m.pkFinContratoCompra = :pkFinContratoCompra");
        query.setParameter("pkFinContratoCompra", pkFinContratoCompra);
        List<FinContratoCompra> result = query.getResultList();
        return result.isEmpty() ? null : result.get(0);
    }
    
}
