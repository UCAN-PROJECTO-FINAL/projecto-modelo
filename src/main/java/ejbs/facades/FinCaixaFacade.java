/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.facades;

import ejbs.entities.FinCaixa;
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
public class FinCaixaFacade extends AbstractFacade<FinCaixa> {

    @PersistenceContext(unitName = "SIG_UCAN-PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FinCaixaFacade() {
        super(FinCaixa.class);
    }
    
     public List<FinCaixa> getCaixasAbertas(boolean estado) {

        TypedQuery<FinCaixa> query = em.createNamedQuery("FinCaixa.findByEstado",
                FinCaixa.class)
                .setParameter("estado", estado);

        List<FinCaixa> results = query.getResultList();

        if (results.isEmpty()) {
            return null;
        }

        return results;
    }

    public List<FinCaixa> getCaixasAbertasDiferente(boolean estado, int id) {

        TypedQuery<FinCaixa> query;
        query = em.createQuery("SELECT f FROM FinCaixa f WHERE "
                + "f.estadoCaixa = :estado and f.pkCaixa != :idfinCaixa", FinCaixa.class)
                .setParameter("estado", estado)
                .setParameter("idfinCaixa", id);

        List<FinCaixa> results = query.getResultList();

        if (results.isEmpty()) {
            return null;
        }

        return results;
    }

    public List<FinCaixa> getCaixaFechadas() {

        TypedQuery<FinCaixa> query;
        query = em.createQuery("SELECT f FROM FinCaixa f WHERE f.estadoCaixa = 'false'", FinCaixa.class);

        List<FinCaixa> results = query.getResultList();

        if (results.isEmpty()) {
            return null;
        }

        return results;
    }

    public FinCaixa existeCaixa(String nome) {
        TypedQuery<FinCaixa> query;
        query = em.createQuery("SELECT f FROM FinCaixa f WHERE f.nome = :nomecaixa", FinCaixa.class);

        query.setParameter("nomecaixa", nome);

        List<FinCaixa> results = query.getResultList();

        if (results.isEmpty()) {
            return null;
        } else {
            return results.get(0);
        }

    }

    public List<FinCaixa> listCxs() {

        TypedQuery<FinCaixa> query;
        query = em.createQuery("SELECT c FROM FinCaixa c WHERE "
                + "c.estadoCaixa = true ORDER BY c.pkCaixa", FinCaixa.class);

        List<FinCaixa> results = query.getResultList();

        if (results.isEmpty()) {
            return null;
        }
        return results;
    }

    public List<FinCaixa> listCxsAbertos() {

        TypedQuery<FinCaixa> query;
        query = em.createQuery("SELECT c FROM FinCaixa c WHERE c.estadoCaixa = TRUE AND c.pkCaixa NOT IN( SELECT f.fkCaixa FROM FinOperacoesCaixa f WHERE f.openClose = TRUE )", FinCaixa.class);

        List<FinCaixa> results = query.getResultList();

        if (results.isEmpty()) {
            return null;
        }
        return results;
    }

//    public List<FinCaixa> listCxsAbertosUsuario(SgConta conta) {
//
//        TypedQuery<FinCaixa> query;
//        query = em.createQuery("SELECT c FROM FinCaixa c WHERE c.estadoCaixa = true "
//                + "AND c.fkConta = :conta", FinCaixa.class)
//                .setParameter("conta", conta);
//
//        List<FinCaixa> results = query.getResultList();
//
//        if (results.isEmpty()) {
//            return null;
//        }
//        return results;
//    }

//    public List<FinCaixa> listCxsCloseUsuario(SgConta conta) {
//
//        TypedQuery<FinCaixa> query;
//        query = em.createQuery("SELECT c FROM FinCaixa c WHERE c.estadoCaixa = false "
//                + "AND c.fkConta = :conta", FinCaixa.class)
//                .setParameter("conta", conta);
//
//        List<FinCaixa> results = query.getResultList();
//
//        if (results.isEmpty()) {
//            return null;
//        }
//        return results;
//    }
//
//    public FinCaixa cxUser(SgConta conta) {
//
//        TypedQuery<FinCaixa> query;
//        query = em.createQuery("SELECT c FROM FinCaixa c WHERE c.estadoCaixa = true "
//                + "AND c.fkConta = :conta", FinCaixa.class)
//                .setParameter("conta", conta);
//
//        FinCaixa results = query.getSingleResult();
//
//        if (results == null) {
//            return null;
//        }
//        return results;
//    }

    //c.estadoCaixa = true > Caixa Aberto
    public List<FinCaixa> listaCxAbertos() {

        TypedQuery<FinCaixa> query;
        query = em.createQuery("SELECT c FROM FinCaixa c WHERE "
                + "c.estadoCaixa = true", FinCaixa.class);

        List<FinCaixa> results = query.getResultList();

        if (results.isEmpty()) {
            return null;
        }
        return results;
    }

    //c.estadoCaixa = false > Caixa Fechado
    public List<FinCaixa> listaCxFechados() {

        TypedQuery<FinCaixa> query;
        query = em.createQuery("SELECT c FROM FinCaixa c WHERE c.estadoCaixa = false", FinCaixa.class);

        List<FinCaixa> results = query.getResultList();

        if (results.isEmpty()) {
            return null;
        }
        return results;
    }

    //c.stateCaixa = true > Caixa Nao Eliminado
//    public List<FinCaixa> listCxsParaValidar(SgConta conta) {
//
//        TypedQuery<FinCaixa> query;
//        query = em.createQuery("SELECT c FROM FinCaixa c WHERE c.stateCaixa = true "
//                + "AND c.fkConta = :conta", FinCaixa.class)
//                .setParameter("conta", conta);
//
//        List<FinCaixa> results = query.getResultList();
//
//        if (results.isEmpty()) {
//            return null;
//        }
//        return results;
//    }

    //c.stateCaixa = true > Caixa Nao Eliminado
    public FinCaixa verificarCaixaDisponivel(int codigoCaixa) {

        Query query = em.createQuery("SELECT c FROM FinCaixa c WHERE c.estadoCaixa = true "
                + "AND c.pkCaixa = codigoCaixa ORDER BY c.pkCaixa");
        query.setParameter("codigoCaixa", codigoCaixa);

        List<FinCaixa> resultado = query.getResultList();

        if (query.getResultList().isEmpty()) {
            return null;
        }

        return resultado.get(0);
    }

    //c.stateCaixa = true > Caixa Nao Eliminado
    public List<FinCaixa> caixaDisponivel() {

        Query query = em.createQuery("SELECT c FROM FinCaixa c WHERE c.estadoCaixa = true "
                + "AND c.estadoCaixa = false ORDER BY c.pkCaixa");

        List<FinCaixa> results = query.getResultList();

        if (query.getResultList().isEmpty()) {
            return null;
        }

        return results;
    }
    
}
