/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.facades;

import ejbs.entities.FrtTransporteTipoManutencao;
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
public class FrtTransporteTipoManutencaoFacade extends AbstractFacade<FrtTransporteTipoManutencao> {

    @PersistenceContext(unitName = "SIG_UCAN-PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FrtTransporteTipoManutencaoFacade() {
        super(FrtTransporteTipoManutencao.class);
    }
    
    
      
    public List<FrtTransporteTipoManutencao> findTipoManutencaoByDescricao(String tipoinfraccao)
    {
        Query q = em.createQuery("SELECT c FROM FrtTransporteTipoManutencao c WHERE c.descricao =:tipoinfraccao");
        q.setParameter("tipoinfraccao", tipoinfraccao);
        q.setMaxResults(1);
        
        return q.getResultList();
    }
    
}
