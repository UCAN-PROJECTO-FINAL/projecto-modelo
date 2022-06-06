/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.facades;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import ejbs.entities.PtEstadoConservacao;
import ejbs.facades.AbstractFacade;

/**
 *
 * @author mdnext
 */
@Stateless
public class PtEstadoConservacaoFacade extends AbstractFacade<PtEstadoConservacao> {

    @PersistenceContext(unitName = "SIG_UCAN-PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PtEstadoConservacaoFacade() {
        super(PtEstadoConservacao.class);
    }
    
    public List<PtEstadoConservacao> findAllPtEstadoConservacao()
    {
        Query query = em.createQuery("SELECT m FROM PtEstadoConservacao m ");
        List<PtEstadoConservacao> result = query.getResultList();
        return result;
    }
    
    public PtEstadoConservacao findByPkPtEstadoConservacao(int pkPtEstadoConservacao)
    {
        Query query = em.createQuery("SELECT m FROM PtEstadoConservacao m WHERE m.pkPtEstadoConservacao = :pkPtEstadoConservacao");
        query.setParameter("pkPtEstadoConservacao", pkPtEstadoConservacao);
        List<PtEstadoConservacao> result = query.getResultList();
        return result.isEmpty() ? null : result.get(0);
    }
    
     public PtEstadoConservacao findByDescricao(String descricao)
    {
        Query query = em.createQuery("SELECT m FROM PtEstadoConservacao m WHERE m.descricao LIKE :descricao");
        query.setParameter("descricao", descricao);
        List<PtEstadoConservacao> result = query.getResultList();
        return result.isEmpty() ? null : result.get(0);
    }
    
}
