/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.facades;

import ejbs.entities.FrtTransporteTipoInfraccao;
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
public class FrtTransporteTipoInfraccaoFacade extends AbstractFacade<FrtTransporteTipoInfraccao> {

    @PersistenceContext(unitName = "SIG_UCAN-PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FrtTransporteTipoInfraccaoFacade() {
        super(FrtTransporteTipoInfraccao.class);
    }
    
    public List<FrtTransporteTipoInfraccao> findTipoInfraccaoByDescricao(String tipoinfraccao)
    {
        Query q = em.createQuery("SELECT c FROM FrtTransporteTipoInfraccao c WHERE c.descricao =:tipoinfraccao");
        q.setParameter("tipoinfraccao", tipoinfraccao);
        q.setMaxResults(1);
        
        return q.getResultList();
    }
    
    
    public List<FrtTransporteTipoInfraccao> findAllTipoInfraccaoOrderByDescricao()
    {
        Query query = em.createQuery("SELECT t FROM FrtTransporteTipoInfraccao t ORDER BY t.descricao");
        List<FrtTransporteTipoInfraccao> result = query.getResultList();
        return result;
    } 

    
}
