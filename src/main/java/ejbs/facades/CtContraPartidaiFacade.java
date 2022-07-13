/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.facades;

import ejbs.entities.CtContraPartidai;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author majoao
 */
@Stateless
public class CtContraPartidaiFacade extends AbstractFacade<CtContraPartidai> {

    @PersistenceContext(unitName = "SIG_UCAN-PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CtContraPartidaiFacade() {
        super(CtContraPartidai.class);
    }
    
    
    public List<CtContraPartidai> listTransferencias(){
        TypedQuery<CtContraPartidai> query;
        query = em.createQuery("SELECT c FROM CtContraPartidai c "
                + "ORDER BY c.pkContraPartidai", CtContraPartidai.class);

        List<CtContraPartidai> results = query.getResultList();

        if (results.isEmpty()) {
            return null;
        }
        return results;
    }
    
    public List<CtContraPartidai> listTransferenciasPorData(Date data){
        TypedQuery<CtContraPartidai> query;
        query = em.createQuery("SELECT c FROM CtContraPartidai c "
                + "WHERE c.dateMovimento =:data ORDER BY c.pkContraPartidai", CtContraPartidai.class)
         .setParameter("data", data);
        List<CtContraPartidai> results = query.getResultList();

        if (results.isEmpty()) {
            return null;
        }
        return results;
    }
    
    
}
