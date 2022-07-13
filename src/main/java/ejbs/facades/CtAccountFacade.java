/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.facades;

import ejbs.entities.CtAccount;
import ejbs.entities.CtClass;
import ejbs.entities.CtRubrica;
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
public class CtAccountFacade extends AbstractFacade<CtAccount> {

    @PersistenceContext(unitName = "SIG_UCAN-PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CtAccountFacade() {
        super(CtAccount.class);
    }
    
    public List<CtAccount> getAccountPorRubrica(int codigoRubrica) {
        TypedQuery<CtAccount> query;
        query = em.createQuery("SELECT c FROM CtAccount c "
                + "WHERE c.fkRubrica.pkRubrica = :id", CtAccount.class)
                .setParameter("id", codigoRubrica);

        List<CtAccount> results = query.getResultList();

        if (results == null || results.isEmpty()) {
            return null;
        }
        return results;
    }

    public List<CtAccount> getAccountFornecedor() {
        TypedQuery<CtAccount> query;
        query = em.createQuery("SELECT c FROM CtAccount c WHERE "
                + "c.fkRubrica.fkClass.descricaoClass = 'Fornecedores' AND c.stateAccount = true "
                + "ORDER BY c.pkAccount", CtAccount.class);


        List<CtAccount> results = query.getResultList();

        if (results.isEmpty()) {
            return null;
        }
        return results;
    }

    public List<CtRubrica> getRubricaPorClassAlterar(int codigoClass) {
        TypedQuery<CtRubrica> query;
        query = em.createQuery("SELECT c FROM CtRubrica c WHERE "
                + "c.fkClass.pkClass = :id AND c.stateRubrica = true ORDER BY c.pkRubrica", CtRubrica.class)
                .setParameter("id", codigoClass);

        List<CtRubrica> results = query.getResultList();

        if (results.isEmpty()) {
            return null;
        }
        return results;
    }

    public List<CtRubrica> getRubricaPorClass(CtClass codigoClass) {

        TypedQuery<CtRubrica> query;
        query = em.createQuery("SELECT c FROM CtRubrica c WHERE c.fkClass = :id "
                + "AND c.stateRubrica = true ORDER BY c.pkRubrica", CtRubrica.class)
                .setParameter("id", codigoClass);

        List<CtRubrica> results = query.getResultList();

        if (results.isEmpty()) {
            return null;
        }
        return results;
    }

    public List<CtAccount> getAccount() {
        TypedQuery<CtAccount> query;
        query = em.createQuery("SELECT c FROM CtAccount c WHERE c.stateAccount = true "
                + "ORDER BY c.pkAccount", CtAccount.class);

        List<CtAccount> results = query.getResultList();

        if (results.isEmpty()) {
            return null;
        }
        return results;
    }

    public List<CtAccount> getAccountDebitoPassivo() {
        TypedQuery<CtAccount> query;
        query = em.createQuery("SELECT c FROM CtAccount c WHERE c.stateAccount = true "
                + "AND c.fkRubrica.fkClass.descricaoClass = 'Fornecedores' ORDER BY c.pkAccount", CtAccount.class);


        List<CtAccount> results = query.getResultList();

        if (results.isEmpty()) {
            return null;
        }
        return results;
    }

    public List<CtAccount> getAccountDebitoCreditoPassivo() {
        TypedQuery<CtAccount> query;
        query = em.createQuery("SELECT c FROM CtAccount c "
                + "WHERE c.stateAccount = true "
                + "AND c.fkRubrica.fkClass.descricaoClass = 'Fornecedores' "
                + "ORDER BY c.pkAccount", CtAccount.class);


        List<CtAccount> results = query.getResultList();

        if (results.isEmpty()) {
            return null;
        }
        return results;
    }

    public List<CtAccount> getAccountDebitoCreditoActivo() {
        TypedQuery<CtAccount> query;
        query = em.createQuery("SELECT c FROM CtAccount c WHERE "
                + "c.stateAccount = true "
                + "AND c.fkRubrica.fkClass.descricaoClass = 'Bens e Serviços' "
                + "OR c.fkRubrica.fkClass.descricaoClass = 'Pessoal'  ORDER BY c.pkAccount", CtAccount.class);


        List<CtAccount> results = query.getResultList();

        if (results.isEmpty()) {
            return null;
        }
        return results;
    }

    public List<CtAccount> getAccountDebitoActivo() {
        TypedQuery<CtAccount> query;
        query = em.createQuery("SELECT c FROM CtAccount c WHERE c.stateAccount = true "
                + "AND c.fkRubrica.fkClass.descricaoClass = 'Bens e Serviços' ORDER BY c.pkAccount", CtAccount.class);

        List<CtAccount> results = query.getResultList();

        if (results.isEmpty()) {
            return null;
        }
        return results;
    }

    public List<CtAccount> getByRubrica(CtRubrica rub) {
        
        TypedQuery<CtAccount> query;
        query = em.createQuery("SELECT c FROM CtAccount c "
                + "WHERE c.stateAccount = true "
                + "AND c.fkRubrica = :rub ORDER BY c.pkAccount", CtAccount.class);
        
        query.setParameter("rub", rub);
        
        List<CtAccount> results = query.getResultList();

        if (results == null || results.isEmpty()) {
            return null;
        }
        
        return results;
        
    }

    public CtAccount getAccount(Integer pkAccount) {
        
        TypedQuery<CtAccount> query;
        query = em.createQuery("SELECT c FROM CtAccount c "
                + "WHERE c.stateAccount = true "
                + "AND c.pkAccount = :pkAccount", CtAccount.class);
        
        query.setParameter("pkAccount", pkAccount);
        
        List<CtAccount> results = query.getResultList();

        if (results == null || results.isEmpty()) {
            return null;
        }
        
        return results.get(0);
        
    }

    public CtAccount getByEntidade(int pkEntidade) {
        
        TypedQuery<CtAccount> query;
        query = em.createQuery("SELECT c FROM CtAccount c "
                + "WHERE c.stateAccount = true "
                + "AND c.fkEntidade.pkEntidade =:pkEntidade", CtAccount.class);
        
        query.setParameter("pkEntidade", pkEntidade);
        
        List<CtAccount> results = query.getResultList();

        if (results == null || results.isEmpty()) {
            return null;
        }
        
        return results.get(0);
        
    }

    
}
