/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.facades;

import ejbs.entities.CtClass;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author majoao
 */
@Stateless
public class CtClassFacade extends AbstractFacade<CtClass> {

    @PersistenceContext(unitName = "SIG_UCAN-PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CtClassFacade() {
        super(CtClass.class);
    }
    
    public List<CtClass> listCategoria() {
        TypedQuery<CtClass> query;
        query = em.createQuery("SELECT c FROM CtClass c WHERE c.stateClass = true "
                + "ORDER BY c.pkClass", CtClass.class);

        List<CtClass> results = query.getResultList();

        if (results == null || results.isEmpty()) {
            return null;
        }
        return results;
    }

    public List<CtClass> listCategoriaValorRubrica() {
        TypedQuery<CtClass> query;
        query = em.createQuery("SELECT c FROM CtClass c WHERE c.stateClass = true "
             //   + "AND c.descricaoClass = 'Bens e Serviços' OR c.descricaoClass = 'Pessoal' "
        + "ORDER BY c.pkClass", CtClass.class);

        
        List<CtClass> results = query.getResultList();

        if (results.isEmpty()) {
            return null;
        }
        return results;
    }

    public List<CtClass> listCategoriaSemValorNoExercicio(int codigoYear) {
        TypedQuery<CtClass> query;
        query = em.createQuery("select c from CtClass c "
                + "where "
                + "c.stateClass = true AND "
                + "c.pkClass NOT IN "
                + "(select mc.fkClass.pkClass from CtMontanteClasse mc "
                + "WHERE mc.fkAnoEconomico.pkAnoEconomico = :codigoYear "
                + "AND mc.stateMontanteClasse = true)", CtClass.class);
        
        query.setParameter("codigoYear", codigoYear);

        List<CtClass> results = query.getResultList();

        if (results.isEmpty()) {
            return null;
        }
        return results;
    }

}
