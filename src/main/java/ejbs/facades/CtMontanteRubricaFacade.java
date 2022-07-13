/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.facades;

import ejbs.entities.CtAnoEconomico;
import ejbs.entities.CtMontanteRubrica;
import ejbs.entities.CtRubrica;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author majoao
 */
@Stateless
public class CtMontanteRubricaFacade extends AbstractFacade<CtMontanteRubrica> {

    @PersistenceContext(unitName = "SIG_UCAN-PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CtMontanteRubricaFacade() {
        super(CtMontanteRubrica.class);
    }
    
    
    public List<CtMontanteRubrica> getRubricaPorYear(CtAnoEconomico codigoYear) {
        TypedQuery<CtMontanteRubrica> query;
        query = em.createQuery("SELECT c FROM CtMontanteRubrica c WHERE "
                + "c.fkAnoEconomico =:id AND c.stateMontanteRubrica = true ORDER BY c.pkMontanteRubrica", CtMontanteRubrica.class)
                .setParameter("id", codigoYear);

        List<CtMontanteRubrica> results = query.getResultList();

        if (results.isEmpty()) {
            return null;
        }
        return results;
    }

    public List<CtMontanteRubrica> getMontanteRubricaPorRubrica(CtRubrica codigoRubrica) {
        TypedQuery<CtMontanteRubrica> query;
        query = em.createQuery("SELECT c FROM CtMontanteRubrica c "
                + "WHERE c.fkRubrica = :id AND c.stateMontanteRubrica = true ORDER BY c.pkMontanteRubrica", CtMontanteRubrica.class)
                .setParameter("id", codigoRubrica);

        List<CtMontanteRubrica> results = query.getResultList();

        if (results.isEmpty()) {
            return null;
        }
        return results;
    }

    public List<CtMontanteRubrica> getMontanteRubricaPorCategoriaANDYear(int codigoYear, int idCategoria) {
        TypedQuery<CtMontanteRubrica> query;
        query = em.createQuery("SELECT c FROM CtMontanteRubrica c "
                + "WHERE c.fkAnoEconomico.pkAnoEconomico = :codigoYear  AND  "
                + "c.fkRubrica.fkClass.pkClass = :idCategoria AND c.stateMontanteRubrica = true "
                + "ORDER BY c.pkMontanteRubrica", CtMontanteRubrica.class);

        query.setParameter("codigoYear", codigoYear);
        query.setParameter("idCategoria", idCategoria);

        List<CtMontanteRubrica> results = query.getResultList();

        if (results.isEmpty()) {
            return null;
        }
        return results;
    }

    public List<CtMontanteRubrica> listMontanteRubrica() {
        TypedQuery<CtMontanteRubrica> query;
        query = em.createQuery("SELECT c FROM CtMontanteRubrica c "
                + "WHERE c.stateMontanteRubrica = true ORDER BY c.pkMontanteRubrica", CtMontanteRubrica.class);

        List<CtMontanteRubrica> results = query.getResultList();

        if (results.isEmpty()) {
            return null;
        }
        return results;
    }

    public CtMontanteRubrica getByAnoClasseRubrica(int codigoYear, int codigoCategoria, int codigoRubrica) {

        TypedQuery<CtMontanteRubrica> query;
        query = em.createQuery("SELECT c FROM CtMontanteRubrica c "
                + "WHERE c.fkAnoEconomico.pkAnoEconomico = :codigoYear  AND  "
                + " c.fkRubrica.fkClass.pkClass = :codigoCategoria AND"
                + " c.fkRubrica.pkRubrica = :codigoRubrica AND c.stateMontanteRubrica = true "
                + " ORDER BY c.pkMontanteRubrica", CtMontanteRubrica.class);

        query.setParameter("codigoYear", codigoYear);
        query.setParameter("codigoCategoria", codigoCategoria);
        query.setParameter("codigoRubrica", codigoRubrica);

        List<CtMontanteRubrica> results = query.getResultList();

        if (results == null || results.isEmpty()) {
            return null;
        }
        return results.get(0);

    }

    public double getTotalDisponibilizado(int pkAnoEconomico, int pkClasse) {

        TypedQuery<Object> query;

        String sql = "select sum(c.valorAnualRubrica) from CtMontanteRubrica c\n"
                + "where c.fkRubrica.fkClass.pkClass = :pkAnoEconomico \n"
                + "and c.fkAnoEconomico.pkAnoEconomico = :pkClasse\n"
                + "and c.stateMontanteRubrica = true";

        query = em.createQuery(sql, Object.class);

        query.setParameter("pkAnoEconomico", pkAnoEconomico);
        query.setParameter("pkClasse", pkClasse);

        List<Object> results = query.getResultList();
        
        if ( results == null || results.isEmpty()) {
            return 0.00;
        } else {
            
            if (results.get(0) == null) return 0.00;
            
            return Double.parseDouble(results.get(0).toString());
        }
    }

    public double getTotalDisponibilizado(int pkAnoEconomico) {

        Query query;

        String sql = "select sum(valor_anual_rubrica) from ct_montante_rubrica where fk_ano_economico =:pkAnoEconomico"
                + "and c.state_montante_rubrica = true";

        query = em.createNativeQuery(sql);

        query.setParameter("pkAnoEconomico", pkAnoEconomico);

        List<Object> results = (List<Object>) query.getResultList();
        if (results.isEmpty()) {
            return 0;
        } else {
            return (double) results.get(0);
        }
    }
}
