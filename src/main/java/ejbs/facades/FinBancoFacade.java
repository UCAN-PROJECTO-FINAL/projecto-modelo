/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.facades;

import ejbs.entities.FinBanco;
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
public class FinBancoFacade extends AbstractFacade<FinBanco> {

    @PersistenceContext(unitName = "SIG_UCAN-PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FinBancoFacade() {
        super(FinBanco.class);
    }
    
    
    
    public List<FinBanco> findAllOrderByDesignacao(){
        Query q = em.createQuery("SELECT l FROM FinBanco l ORDER BY l.descricao ASC");
        return q.getResultList();
    }

    public String toString(FinBanco reg)
    {
        if (reg != null)
            return "[" + reg.getPkBanco()+ ", "
            + reg.getDescricao()+", "+reg.getDescricaoCompleta() +"]";
        return "";
    }
    
}
