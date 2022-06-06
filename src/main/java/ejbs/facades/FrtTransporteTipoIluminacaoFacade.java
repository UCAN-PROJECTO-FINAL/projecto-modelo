/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.facades;

import ejbs.entities.FrtTransporteTipoIluminacao;
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
public class FrtTransporteTipoIluminacaoFacade extends AbstractFacade<FrtTransporteTipoIluminacao>
{

    @PersistenceContext(unitName = "SIG_UCAN-PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager()
    {
        return em;
    }

    public FrtTransporteTipoIluminacaoFacade()
    {
        super(FrtTransporteTipoIluminacao.class);
    }
    
    
     public List<FrtTransporteTipoIluminacao> findTipoIluminacaoByDescricao(String tipoSolicitacao)
    {
        Query q = em.createQuery("SELECT c FROM FrtTransporteTipoIluminacao c WHERE c.descricao =:tipoSolicitacao");
        q.setParameter("tipoSolicitacao", tipoSolicitacao);
        q.setMaxResults(1);
        
        return q.getResultList();
    }
    
}
