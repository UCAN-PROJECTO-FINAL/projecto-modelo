/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.facades;

import ejbs.entities.ArCadeira;
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
public class ArCadeiraFacade extends AbstractFacade<ArCadeira> {

    @PersistenceContext(unitName = "SIG_UCAN-PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ArCadeiraFacade() {
        super(ArCadeira.class);
    }
    
    
    public List<ArCadeira> findCadeiraByDescricao(String cadeira)
    {
        Query q = em.createQuery("SELECT c FROM ArCadeira c WHERE c.descricao =:cadeira");
        q.setParameter("cadeira", cadeira);
        q.setMaxResults(1);
        
        return q.getResultList();
    }
    
    public List<ArCadeira> findCadeiraByCurso(int codigoCurso)
    {
        Query q = em.createQuery("SELECT c FROM ArCadeira c WHERE c.fkCurso.pkCurso =:codigoCurso");
        q.setParameter("codigoCurso", codigoCurso);
        
        return q.getResultList();
    }
    
    public List<ArCadeira> findCadeiraByDocente(int codigoDocente)
    {
        Query q = em.createQuery("SELECT c FROM ArCadeira c WHERE c.fkDocente.pkDocente =:codigoDocente");
        q.setParameter("codigoDocente", codigoDocente);
        
        return q.getResultList();
    }
}
