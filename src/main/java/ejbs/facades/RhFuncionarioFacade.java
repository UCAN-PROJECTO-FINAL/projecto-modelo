/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.facades;

import ejbs.entities.RhFuncionario;
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
public class RhFuncionarioFacade extends AbstractFacade<RhFuncionario> {

    @PersistenceContext(unitName = "SIG_UCAN-PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RhFuncionarioFacade() {
        super(RhFuncionario.class);
    }
    
    public List<RhFuncionario> findAllFuncionarios()
    {
        Query query = em.createQuery("SELECT t FROM RhFuncionario t ORDER BY t.dataCadastro DESC");
        List<RhFuncionario> result = query.getResultList();
        return result;
    }
    
}
