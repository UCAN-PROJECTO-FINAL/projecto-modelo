/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.facades;

import ejbs.entities.CtAccount;
import ejbs.entities.CtAnoEconomico;
import ejbs.entities.CtBalancet;
import ejbs.entities.CtLancamento;
import ejbs.entities.CtRubrica;
import java.util.Date;
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
public class CtBalancetFacade extends AbstractFacade<CtBalancet> {

    @PersistenceContext(unitName = "SIG_UCAN-PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CtBalancetFacade() {
        super(CtBalancet.class);
    }
    
    
    public List<CtBalancet> getBalancetPorAccount(CtAccount codigoAccount) {
        TypedQuery<CtBalancet> query;
        query = em.createQuery("SELECT c FROM CtBalancet c WHERE c.fkAccount = :codigoAccount AND c.stateBalancet = true ORDER BY c.fkLancamento.fkDocument.fkDiario.descricaoDiario, c.fkLancamento.fkDocumento.numDoc", CtBalancet.class)
                .setParameter("codigoAccount", codigoAccount);

        List<CtBalancet> results = query.getResultList();

        if (results.isEmpty()) {
            return null;
        }
        return results;
    }

    public List<CtBalancet> getBalancetPorYear(CtAnoEconomico year) {
        TypedQuery<CtBalancet> query;
        query = em.createQuery("SELECT c FROM CtBalancet c WHERE "
                + "c.fkLancamento.fkAnoEconomico = :codigoYear AND c.stateBalancet = true "
                + "ORDER BY c.pkBalancet", CtBalancet.class);

        query.setParameter("codigoYear", year);

        List<CtBalancet> results = query.getResultList();

        if (results.isEmpty()) {
            return null;
        }
        return results;
    }

    public double getBalancetTotalCreditoPorYear(int pkAnoEconomico) {

        Query query;
        query = em.createQuery("select sum(b.creditoBalancet) from CtBalancet b\n"
                + "where b.fkLancamento.fkAnoEconomico.pkAnoEconomico = :pkAnoEconomico \n"
                + "and b.stateBalancet = true");

        query.setParameter("pkAnoEconomico", pkAnoEconomico);

        List<Object> results = query.getResultList();

        if (results == null || results.isEmpty()) {
            return 0.00;
        }

        if (results.get(0) != null) {
            return Double.parseDouble(results.get(0).toString());
        }

        return 0.00;
    }

    public double getBalancetTotalCreditoPorYearAndCategoria(int pkAnoEconomico, int pkClass) {

        Query query;
        query = em.createQuery("select sum(b.creditoBalancet) from CtBalancet b\n"
                + "where b.fkLancamento.fkAnoEconomico.pkAnoEconomico = :pkAnoEconomico\n"
                + "and b.stateBalancet = true and\n"
                + "b.fkAccount.fkRubrica.fkClass.pkClass = :pkClass");

        query.setParameter("pkAnoEconomico", pkAnoEconomico);
        query.setParameter("pkClass", pkClass);

        List<Object> results = query.getResultList();
        
        

        if (results == null || results.isEmpty()) {
            return 0.00;
        }

        if (results.get(0) != null) {
            return Double.parseDouble(results.get(0).toString());
        }

        return 0.00;
    }

    public List<Object> getBalancetTotalCreditoPorYearANDRubricaG(int codigoYear, int codigoRubrica) {

        Query query;
        query = em.createQuery("SELECT SUM(c.creditoBalancet - c.debitoBalancet) "
                + "FROM CtBalancet c WHERE c.fkLancamento.fkAnoEconomico.pkAnoEconomico = :pkAno "
                + " AND c.fkAccount.fkRubrica.pkRubrica = :pkRubrica AND c.stateBalancet = true");

        query.setParameter("pkRubrica", codigoRubrica);
        query.setParameter("pkAno", codigoRubrica);

        List<Object> results = query.getResultList();
        
        

        if (results.isEmpty()) {
            return null;
        }
        return results;
    }

    public List<Object[]> getBalancetPorYearANDRubricaG(int codigoYear, int codigoRubrica) {

        Query query;
        query = em.createQuery("SELECT SUM(c.creditoBalancet - c.debitoBalancet), "
                + "c.fkAccount.descricaoAccount FROM CtBalancet c WHERE "
                + "c.fkLancamento.fkAnoEconomico.pkAnoEconomico = :pkAno "
                + "AND c.fkAccount.fkRubrica.pkRubrica = :pkRubrica AND c.stateBalancet = true "
                + "GROUP BY c.fkAccount.descricaoAccount");

        query.setParameter("pkRubrica", codigoRubrica);
        query.setParameter("pkAno", codigoRubrica);

        List<Object[]> results = query.getResultList();
        
        

        if (results.isEmpty()) {
            return null;
        }
        return results;
    }

    public List<CtBalancet> getBalancetPorYearANDRubrica(int codigoYear, int codigoRubrica) {
        TypedQuery<CtBalancet> query;
        query = em.createQuery("SELECT c FROM CtBalancet c "
                + "WHERE c.fkLancamento.fkAnoEconomico.pkAnoEconomico = :codigoYear "
                + "AND c.fkAccount.fkRubrica.pkRubrica = :codigoRubrica AND c.stateBalancet = true "
                + "ORDER BY c.pkBalancet", CtBalancet.class)
                .setParameter("codigoYear", codigoYear)
                .setParameter("codigoRubrica", codigoRubrica);

        List<CtBalancet> results = query.getResultList();
        
        

        if (results.isEmpty()) {
            return null;
        }
        return results;
    }

    public List<CtBalancet> getBalancetPorRubrica(CtRubrica codigoRubrica) {
        TypedQuery<CtBalancet> query;
        query = em.createQuery("SELECT c FROM CtBalancet c WHERE "
                + "c.fkAccount.fkRubrica =:codigoRubrica AND c.stateBalancet = true ORDER BY c.pkBalancet", CtBalancet.class)
                .setParameter("codigoRubrica", codigoRubrica);

        List<CtBalancet> results = query.getResultList();
        
        

        if (results.isEmpty()) {
            return null;
        }
        return results;
    }

    public List<CtBalancet> getBalancetPorLancamento(CtLancamento idLancamento) {
        TypedQuery<CtBalancet> query;
        query = em.createQuery("SELECT c FROM CtBalancet c WHERE "
                + "c.fkLancamento =:launch AND c.stateBalancet = true ORDER BY c.pkBalancet", CtBalancet.class)
                .setParameter("launch", idLancamento);

        List<CtBalancet> results = query.getResultList();
        
        

        if (results.isEmpty()) {
            return null;
        }
        return results;
    }

    public List<CtBalancet> getTotalSaldoCredor(CtAnoEconomico year) {
        TypedQuery<CtBalancet> query;
        query = em.createQuery("SELECT c FROM CtBalancet c WHERE "
                + "c.fkLancamento.fkAnoEconomico = :codigoYear "
                + "AND c.fkAccount.fkRubrica.fkClass.descricaoClass = 'Fornecedores' "
                + "AND c.stateBalancet = true ORDER BY c.pkBalancet", CtBalancet.class)
                .setParameter("codigoYear", year);

        List<CtBalancet> results = query.getResultList();
        
        

        if (results.isEmpty()) {
            return null;
        }
        return results;
    }

    public List<CtBalancet> getTotalSaldoCredorMensal(CtAnoEconomico codigoYear, int month) {
        TypedQuery<CtBalancet> query;
        query = em.createQuery("SELECT c FROM CtBalancet c "
                + "WHERE c.fkLancamento.fkAnoEconomico = :codigoYear AND "
                + "c.fkAccount.fkRubrica.fkClass.descricaoClass = 'Fornecedores' "
                + "AND c.stateBalancet = true AND c.month = :month ORDER BY c.pkBalancet", CtBalancet.class)
                .setParameter("codigoYear", codigoYear)
                .setParameter("month", month);

        List<CtBalancet> results = query.getResultList();
        
        

        if (results.isEmpty()) {
            return null;
        }
        return results;
    }

    public List<CtBalancet> getTotalSaldoCredorPeriodo(Date datane, Date datetwo) {
        TypedQuery<CtBalancet> query;
        query = em.createQuery("SELECT c FROM CtBalancet c WHERE  "
                + "c.dataBalancet BETWEEN  c.dataBalancet =:dataone AND c.dataBalancet =:datatwon "
                + "AND c.fkAccount.fkRubrica.fkClass.descricaoClass = 'Fornecedores' "
                + "AND c.stateBalancet = true ORDER BY c.pkBalancet", CtBalancet.class)
                .setParameter("datane", datane)
                .setParameter("datetwo", datetwo);

        List<CtBalancet> results = query.getResultList();
        
        

        if (results.isEmpty()) {
            return null;
        }
        return results;
    }

    public List<CtBalancet> listBalancetData(Date data) {
        TypedQuery<CtBalancet> query;
        query = em.createQuery("SELECT c FROM CtBalancet c WHERE "
                + "c.stateBalancet = true AND c.dataBalancet =:data ORDER BY c.pkBalancet", CtBalancet.class)
                .setParameter("data", data);
        List<CtBalancet> results = query.getResultList();
        
        

        if (results == null || results.isEmpty()) {
            return null;
        }
        return results;
    }

    public List<CtBalancet> listBalancet() {
        TypedQuery<CtBalancet> query;
        query = em.createQuery("SELECT c FROM CtBalancet c WHERE "
                + "c.stateBalancet = true  ORDER BY c.pkBalancet", CtBalancet.class);

        List<CtBalancet> results = query.getResultList();

        if (results.isEmpty()) {
            return null;
        }
        return results;
    }
    
    public List<Object> listDatasBalancets() {
        
        TypedQuery<Object> query;
        query = em.createQuery("SELECT distinct c.dataBalancet FROM CtBalancet c WHERE "
                + "c.stateBalancet = true ORDER BY c.dataBalancet", Object.class);

        List<Object> results = query.getResultList();
        
        if (results == null || results.isEmpty()) {
            return null;
        }
        
        return results;
    }
    
}
