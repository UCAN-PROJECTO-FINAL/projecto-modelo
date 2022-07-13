/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.facades;

import ejbs.entities.ArTurma;
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
public class ArTurmaFacade extends AbstractFacade<ArTurma> {

    @PersistenceContext(unitName = "SIG_UCAN-PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ArTurmaFacade() {
        super(ArTurma.class);
    }
    
    public List<ArTurma> findTurmaByDescricao(String turma)
    {
        Query q = em.createQuery("SELECT c FROM ArTurma c WHERE c.descricao =:turma");
        q.setParameter("turma", turma);
        q.setMaxResults(1);
        
        return q.getResultList();
    }
}
