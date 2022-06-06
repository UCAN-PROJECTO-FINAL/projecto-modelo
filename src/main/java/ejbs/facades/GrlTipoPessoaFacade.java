/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.facades;

import ejbs.entities.GrlTipoPessoa;
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
public class GrlTipoPessoaFacade extends AbstractFacade<GrlTipoPessoa> {

    @PersistenceContext(unitName = "SIG_UCAN-PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GrlTipoPessoaFacade() {
        super(GrlTipoPessoa.class);
    }
    
    public List<GrlTipoPessoa> findAllOrderByNome()
    {
        Query query = em.createQuery("SELECT t FROM GrlTipoPessoa t ORDER BY t.nome");
        List<GrlTipoPessoa> result = query.getResultList();
        return result;
    }
    
}
