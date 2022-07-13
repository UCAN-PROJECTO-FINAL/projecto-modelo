/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.facades;

import ejbs.entities.GsFornecedor;
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
public class GsFornecedorFacade extends AbstractFacade<GsFornecedor> {

    @PersistenceContext(unitName = "SIG_UCAN-PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GsFornecedorFacade() {
        super(GsFornecedor.class);
    }

    public List<GsFornecedor> findAllOrderByNome() {
        Query query = em.createQuery("SELECT g FROM GsFornecedor g ORDER BY g.nome");
        List<GsFornecedor> result = query.getResultList();
        return result;
    }

    @Override
    public String toString() {
        return "GsFornecedorFacade{" + "em=" + em + '}';
    }
    
    

}
