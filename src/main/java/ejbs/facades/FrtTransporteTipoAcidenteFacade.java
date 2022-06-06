/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.facades;

import ejbs.entities.FrtTransporteTipoAcidente;
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
public class FrtTransporteTipoAcidenteFacade extends AbstractFacade<FrtTransporteTipoAcidente>
{

    @PersistenceContext(unitName = "SIG_UCAN-PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager()
    {
        return em;
    }

    public FrtTransporteTipoAcidenteFacade()
    {
        super(FrtTransporteTipoAcidente.class);
    }
    
    public List<FrtTransporteTipoAcidente> findTipoAcidenteByDescricao(String tipoSolicitacao)
    {
        Query q = em.createQuery("SELECT c FROM FrtTransporteTipoAcidente c WHERE c.descricao =:tipoSolicitacao");
        q.setParameter("tipoSolicitacao", tipoSolicitacao);
        q.setMaxResults(1);
        
        return q.getResultList();
    }
    
}
