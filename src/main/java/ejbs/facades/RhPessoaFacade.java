/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.facades;

import ejbs.entities.RhPessoa;
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
public class RhPessoaFacade extends AbstractFacade<RhPessoa> {

    @PersistenceContext(unitName = "SIG_UCAN-PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RhPessoaFacade() {
        super(RhPessoa.class);
    }
    
    public List<RhPessoa> findAllOrderByNome(){
         Query q = em.createQuery("SELECT p FROM RhPessoa p ORDER BY p.nome");
         return q.getResultList();
    }
}
