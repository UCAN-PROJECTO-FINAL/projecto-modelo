/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.facades;

import ejbs.entities.ArClassificacao;
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
public class ArClassificacaoFacade extends AbstractFacade<ArClassificacao> {

    @PersistenceContext(unitName = "SIG_UCAN-PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ArClassificacaoFacade() {
        super(ArClassificacao.class);
    }
    
    public List<ArClassificacao> findClassificacaoByDescricao(String classificacao)
    {
        Query q = em.createQuery("SELECT c FROM ArClassificacao c WHERE c.descricao =:classificacao");
        q.setParameter("classificacao", classificacao);
        q.setMaxResults(1);
        
        return q.getResultList();
    }
    
}
