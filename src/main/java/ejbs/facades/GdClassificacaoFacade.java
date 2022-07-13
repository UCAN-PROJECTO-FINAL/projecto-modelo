/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.facades;

import ejbs.entities.GdClassificacao;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author david-salgueiro
 */
@Stateless
public class GdClassificacaoFacade extends AbstractFacade<GdClassificacao> {

    @PersistenceContext(unitName = "SIG_UCAN-PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GdClassificacaoFacade() {
        super(GdClassificacao.class);
    }
    
    
    public List<GdClassificacao> findClassificacaoByDescricao(String classificacao)
    {
        Query q = em.createQuery("SELECT c FROM GdClassificacao c WHERE c.descricao =:classificacao");
        q.setParameter("classificacao", classificacao);
        q.setMaxResults(1);
        
        return q.getResultList();
    }
    
}
