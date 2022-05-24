/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.facades;

import ejbs.entities.GrlTipoSolicitacao;
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
public class GrlTipoSolicitacaoFacade extends AbstractFacade<GrlTipoSolicitacao> {

    @PersistenceContext(unitName = "SIG_FRT_Master-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GrlTipoSolicitacaoFacade() {
        super(GrlTipoSolicitacao.class);
    }
    
       public List<GrlTipoSolicitacao> findAllTipoSolicitacaoOrderByDescricao()
    {
        Query query = em.createQuery("SELECT m FROM GrlTipoSolicitacao m ORDER BY m.descricao");
        List<GrlTipoSolicitacao> result = query.getResultList();
        return result;
    }
    
    
    
    public List<GrlTipoSolicitacao> findTipoSolicitacaoByDescricao(String tipoSolicitacao)
    {
        Query q = em.createQuery("SELECT c FROM GrlTipoSolicitacao c WHERE c.descricao =:tipoSolicitacao");
        q.setParameter("tipoSolicitacao", tipoSolicitacao);
        q.setMaxResults(1);
        
        return q.getResultList();
    }
    
    
    
    
    public int codigo()
    {
        Query q = em.createQuery("SELECT r FROM GrlTipoSolicitacao r");
        List<GrlTipoSolicitacao> lista = q.getResultList();
        
        if (lista.isEmpty()){
        
            return 1;
        }else{
        
            return q.getMaxResults() + 1;
        }
        
    }
    
}
