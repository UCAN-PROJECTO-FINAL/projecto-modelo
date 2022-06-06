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
import ejbs.entities.PtFormaAquisicao;
import ejbs.facades.AbstractFacade;

/**
 *
 * @author mdnext
 */
@Stateless
public class PtFormaAquisicaoFacade extends AbstractFacade<PtFormaAquisicao> {

    @PersistenceContext(unitName = "SIG_UCAN-PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PtFormaAquisicaoFacade() {
        super(PtFormaAquisicao.class);
    }
    
    public List<PtFormaAquisicao> findAllPtFormaAquisicao()
    {
        Query query = em.createQuery("SELECT m FROM PtFormaAquisicao m ");
        List<PtFormaAquisicao> result = query.getResultList();
        return result;
    }
    
    public PtFormaAquisicao findByPkPtFormaAquisicao(int pkPtFormaAquisicao)
    {
        Query query = em.createQuery("SELECT m FROM PtFormaAquisicao m WHERE m.pkPtFormaAquisicao = :pkPtFormaAquisicao");
        query.setParameter("pkPtFormaAquisicao", pkPtFormaAquisicao);
        List<PtFormaAquisicao> result = query.getResultList();
        return result.isEmpty() ? null : result.get(0);
    }
    
     public PtFormaAquisicao findByDescricao(String descricao)
    {
        Query query = em.createQuery("SELECT m FROM PtFormaAquisicao m WHERE m.descricao LIKE :descricao");
        query.setParameter("descricao", descricao);
        List<PtFormaAquisicao> result = query.getResultList();
        return result.isEmpty() ? null : result.get(0);
    }
    
}
