/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.facades;

import ejbs.entities.FinCaixa;
import ejbs.entities.FinOperacoesCaixa;
import ejbs.entities.SegConta;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
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
public class FinOperacoesCaixaFacade extends AbstractFacade<FinOperacoesCaixa> {

    @PersistenceContext(unitName = "SIG_UCAN-PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FinOperacoesCaixaFacade() {
        super(FinOperacoesCaixa.class);
    }

    public List<FinOperacoesCaixa> getOpcaixasByCaixa(FinCaixa tipo) {

        TypedQuery<FinOperacoesCaixa> query;
        query = em.createQuery("SELECT f FROM FinOperacoesCaixa f "
                + "WHERE f.fkCaixa = :idcaixa", FinOperacoesCaixa.class)
                .setParameter("idcaixa", tipo);

        List<FinOperacoesCaixa> results = query.getResultList();

        if (results == null || results.isEmpty()) {
            return null;
        }

        return results;
    }

    /*Listar todas entidades em função do tipo*/
    public List<FinOperacoesCaixa> findOperacoesCaixaInterDataUser(String data1, String data2, String nomeUser) {
        Query query = em.createQuery("SELECT f FROM FinOperacoesCaixa f "
                + "WHERE f.fkUtilizador.nomeUtilizador = '" + nomeUser + "' "
                + "AND f.dataMovimento BETWEEN '" + data1 + "'AND '" + data2 + "'");

        List<FinOperacoesCaixa> results = (List<FinOperacoesCaixa>) query.getResultList();
        //System.out.println("lista" + results);
        if (results.isEmpty()) {
            return null;
        }

        return results;
    }

    public List<FinOperacoesCaixa> listCaixasAbertos() {

        TypedQuery<FinOperacoesCaixa> query;
        query = em.createQuery("SELECT f FROM FinOperacoesCaixa f WHERE f.openClose = TRUE", FinOperacoesCaixa.class);

        List<FinOperacoesCaixa> results = query.getResultList();

        if (results == null || results.isEmpty()) {
            return null;
        }
        return results;
    }

    public List<FinCaixa> listFinCaixasDisponiveis() {

        TypedQuery<FinCaixa> query;
        query = em.createQuery("SELECT f FROM FinCaixa f WHERE f.pkCaixa  "
                + "NOT IN(SELECT c.fkCaixa FROM FinOperacoesCaixa c WHERE c.openClose = TRUE )", FinCaixa.class);

        List<FinCaixa> results = query.getResultList();

        if (results == null || results.isEmpty()) {
            return new ArrayList<>();
        }
        return results;
    }

    public List<FinOperacoesCaixa> listTotalVendas(SegConta conta, Date data) {

        TypedQuery<FinOperacoesCaixa> query;
        query = em.createQuery("SELECT f FROM FinOperacoesCaixa f WHERE "
                + "f.fkUtilizador = :conta AND f.dataMovimento = :data", FinOperacoesCaixa.class)
                .setParameter("conta", conta)
                .setParameter("data", data);

        List<FinOperacoesCaixa> results = query.getResultList();

        if (results.isEmpty()) {
            return null;
        }
        return results;
    }

    public List<FinOperacoesCaixa> listTotalVendasFechoExtraordinario(SegConta conta, Date data) {

        TypedQuery<FinOperacoesCaixa> query;
        query = em.createQuery("SELECT f FROM FinOperacoesCaixa f WHERE "
                + "f.fkUtilizador = :conta "
                + "AND f.dataMovimento = :data", FinOperacoesCaixa.class)
                .setParameter("conta", conta)
                .setParameter("data", data);

        List<FinOperacoesCaixa> results = query.getResultList();

        if (results.isEmpty()) {
            return null;
        }
        return results;

    }

    public FinOperacoesCaixa valorInicial(SegConta conta, Date data) {

        Query query = em.createQuery("SELECT f FROM FinOperacoesCaixa f "
                + "WHERE f.fkUtilizador = :conta AND f.dataMovimento = :data "
                + "AND f.openClose = TRUE "
                + "AND f.descricaoCaixa = 'Saldo Inicial'");

        query.setParameter("conta", conta);
        query.setParameter("data", data);

        if (query.getResultList().isEmpty()) {
            return null;
        }

        return (FinOperacoesCaixa) query.getSingleResult();
    }

    public FinOperacoesCaixa valorInicialFechoExtraordinario(SegConta conta, Date data) {

        Query query = em.createQuery("SELECT f FROM FinOperacoesCaixa f "
                + "WHERE f.fkUtilizador = :conta AND f.dataMovimento = :data "
                + "AND f.descricaoCaixa = 'Saldo Inicial'");

        query.setParameter("conta", conta);
        query.setParameter("data", data);

        if (query.getResultList().isEmpty()) {
            return null;
        }

        return (FinOperacoesCaixa) query.getSingleResult();
    }

    public List<FinOperacoesCaixa> CxFechados(int caixa) {

        TypedQuery<FinOperacoesCaixa> query;
        query = em.createQuery("SELECT c FROM FinOperacoesCaixa c WHERE "
                + "c.openClose = FALSE AND c.fkCaixa.pkCaixa =:caixa ", FinOperacoesCaixa.class)
                .setParameter("caixa", caixa);

        List<FinOperacoesCaixa> results = query.getResultList();

        if (results.isEmpty()) {
            return null;
        }
        return results;
    }

    public List<FinOperacoesCaixa> getSaldoCX(Date data, SegConta conta) {

        TypedQuery<FinOperacoesCaixa> query;
        query = em.createQuery("SELECT f FROM FinOperacoesCaixa f "
                + "WHERE f.dataMovimento = :data AND f.fkUtilizador =:conta AND f.openClose = true "
                + "ORDER BY f.pkOperacoesCaixa", FinOperacoesCaixa.class)
                .setParameter("data", data)
                .setParameter("conta", conta);

        List<FinOperacoesCaixa> results = query.getResultList();

        if (results.isEmpty()) {
            return null;
        }

        return results;
    }

    public List<FinOperacoesCaixa> lista(SegConta conta, Date data) {

        TypedQuery<FinOperacoesCaixa> query;
        query = em.createQuery("SELECT f FROM FinOperacoesCaixa f "
                + "WHERE f.fkUtilizador =:conta AND f.dataMovimento =:data  "
                + "ORDER BY f.pkOperacoesCaixa", FinOperacoesCaixa.class)
                .setParameter("conta", conta)
                .setParameter("data", data);

        List<FinOperacoesCaixa> results = query.getResultList();

        if (results.isEmpty()) {
            return null;
        }
        return results;
    }

    public List<FinOperacoesCaixa> listaSaldoInicial(SegConta conta, Date data) {

        TypedQuery<FinOperacoesCaixa> query;
        query = em.createQuery("SELECT f FROM FinOperacoesCaixa f "
                + "WHERE f.openClose = true  AND f.fkUtilizador =:conta AND f.dataMovimento =:data AND "
                + "f.descricaoCaixa = 'Saldo Inicial' ORDER BY f.pkOperacoesCaixa", FinOperacoesCaixa.class)
                .setParameter("conta", conta)
                .setParameter("data", data);

        List<FinOperacoesCaixa> results = query.getResultList();

        if (results.isEmpty()) {
            return null;
        }
        return results;
    }

    public List<SegConta> utilizadoresActivos(int codigo) {

        TypedQuery<SegConta> query;
        query = em.createQuery("SELECT c FROM SegConta c WHERE "
                + "c.activo = TRUE AND c.pkSegConta =:codigo ORDER BY c.pkSegConta", SegConta.class)
                .setParameter("codigo", codigo);
        List<SegConta> resultado = query.getResultList();

        if (resultado.isEmpty()) {
            SegConta tmpUser = new SegConta();
            resultado.add(tmpUser);
            return resultado;
        }
        return resultado;

    }

    public List<SegConta> listUserAtivos() {

        TypedQuery<SegConta> query;
        query = em.createQuery("SELECT c FROM SegConta c WHERE c.activo = TRUE "
                + "ORDER BY c.pkSegConta", SegConta.class);

        List<SegConta> resultado = query.getResultList();

        if (resultado.isEmpty()) {
            SegConta tmpUser = new SegConta();
            //tmpUser.setEstado(Boolean.FALSE);
            resultado.add(tmpUser);
            return resultado;
        }
        return resultado;

    }

    public List<FinOperacoesCaixa> obterUltimoMovimentoCaixaPorUserAccount(SegConta sgContas) {

        TypedQuery<FinOperacoesCaixa> query;
        query = em.createQuery("SELECT f FROM FinOperacoesCaixa f WHERE "
                + " f.fkUtilizador = :conta "
                + "ORDER BY f.dataMovimento DESC", FinOperacoesCaixa.class)
                .setParameter("conta", sgContas);

        List<FinOperacoesCaixa> results = query.getResultList();

        if (results == null || results.isEmpty()) {
            return null;
        }
        return results;
    }

    public List<FinOperacoesCaixa> buscarUtilizadorCaixaAbertos(SegConta sgContas) {

        TypedQuery<FinOperacoesCaixa> query;
        query = em.createQuery("SELECT f FROM FinOperacoesCaixa f WHERE f.openClose = TRUE AND"
                + " f.fkUtilizador = :conta "
                + "ORDER BY f.dataMovimento DESC", FinOperacoesCaixa.class)
                .setParameter("conta", sgContas);

        List<FinOperacoesCaixa> results = query.getResultList();

        if (results == null || results.isEmpty()) {
            return null;
        }
        return results;
    }

}
