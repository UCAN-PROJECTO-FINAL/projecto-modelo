/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.facades;

import ejbs.entities.CtAnoEconomico;
import ejbs.entities.CtMontanteClasse;
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
public class CtMontanteClasseFacade extends AbstractFacade<CtMontanteClasse> {

    @PersistenceContext(unitName = "SIG_UCAN-PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CtMontanteClasseFacade() {
        super(CtMontanteClasse.class);
    }
    
     public List<CtMontanteClasse> getCategoriaPorYear(CtAnoEconomico codigoYear) {
        TypedQuery<CtMontanteClasse> query;
        query = em.createQuery("SELECT c FROM CtMontanteClasse c WHERE "
                + "c.fkAnoEconomico = :id AND c.stateMontanteClasse = true "
                + "ORDER BY c.pkMontanteClasse", CtMontanteClasse.class)
                .setParameter("id", codigoYear);

        List<CtMontanteClasse> results = query.getResultList();

        if (results == null || results.isEmpty()) {
            return null;
        }
        return results;
    }
    
    public CtMontanteClasse findByYearAndClass(int pkAno, int pkClass) {
        
        TypedQuery<CtMontanteClasse> query;
        query = em.createQuery("SELECT c FROM CtMontanteClasse c WHERE "
                + "c.fkAnoEconomico.pkAnoEconomico = :pkAno AND c.stateMontanteClasse = true "
                + "AND c.fkClass.pkClass = :pkClass", CtMontanteClasse.class);
                
        query.setParameter("pkAno", pkAno);
        query.setParameter("pkClass", pkClass);

        List<CtMontanteClasse> results = query.getResultList();

        if (results == null || results.isEmpty()) {
            return null;
        }
        return results.get(0);
    }
    
    

    public List<CtMontanteClasse> getCategoriaPorYearANDId(int codigoYear, int idRubrica) {
        TypedQuery<CtMontanteClasse> query;
        query = em.createQuery("SELECT c FROM CtMontanteClasse c "
                + "WHERE c.fkAnoEconomico.pkAnoEconomico = :codigoYear AND c.fkClass.pkClass = :idRubrica "
                + "AND c.stateMontanteClasse = true GROUP BY c.pkMontanteClasse", CtMontanteClasse.class)
                .setParameter("codigoYear", codigoYear)
                .setParameter("idRubrica", idRubrica);

        List<CtMontanteClasse> results = query.getResultList();

        if (results.isEmpty()) {
            return null;
        }
        return results;
    }

    public List<CtMontanteClasse> listMontanteClasse() {
        TypedQuery<CtMontanteClasse> query;
        query = em.createQuery("SELECT c FROM CtMontanteClasse c "
                + "WHERE c.stateMontanteClasse = true ORDER BY c.pkMontanteClasse", CtMontanteClasse.class);

        List<CtMontanteClasse> results = query.getResultList();

        if (results.isEmpty()) {
            return null;
        }
        return results;
    }

    public int getTotalDisponibilizado(int pkAnoEconomico) {

        Query query;

        String sql = "select sum(valor_anual_classe) from ct_montante_classe WHERE fk_ano_economico =" + pkAnoEconomico;

        query = em.createNativeQuery(sql);

        List<Object> results = (List<Object>) query.getResultList();
        if (results.isEmpty()) {
            return 0;
        } else {
            return (int) results.get(0);
        }
    }

    public int getTotalDisponivelParaClasses(int pkAnoEconomico) {

        Query query;

        String sql = "SELECT (a.valor_anual - sum(c.valor_anual_classe)) as valor_disponivel\n"
                + "from ct_montante_classe c\n"
                + "INNER JOIN ct_ano_economico a ON c.fk_ano_economico = a.pk_ano_economico\n" +
                "WHERE fk_ano_economico = :pkAnoEconomico and c.state_montante_classe = true GROUP BY a.valor_anual";
        
        query = em.createNativeQuery(sql);
        
        query.setParameter("pkAnoEconomico", pkAnoEconomico);

        List<Object> results = (List<Object>) query.getResultList();
        if (results.isEmpty()) {
            return 0;
        } else {
            return (int) results.get(0);
        }
    }

    public CtMontanteClasse getByYearAndClass(Integer pkAnoEconomico, Integer pkClass) {
    
        TypedQuery<CtMontanteClasse> query;
        query = em.createQuery("SELECT c FROM CtMontanteClasse c "
                + "WHERE c.stateMontanteClasse = true "
                + "AND c.fkAnoEconomico.pkAnoEconomico = :pkAnoEconomico "
                + "AND c.fkClass.pkClass = :pkClass", CtMontanteClasse.class);
        
        query.setParameter("pkAnoEconomico", pkAnoEconomico);
        query.setParameter("pkClass", pkClass);

        List<CtMontanteClasse> results = query.getResultList();

        if (results ==null || results.isEmpty()) {
            return null;
        }
        return results.get(0);
        
    }
}
