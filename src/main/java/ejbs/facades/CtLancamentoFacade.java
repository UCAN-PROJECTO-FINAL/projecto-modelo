/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.facades;

import ejbs.entities.FinDocumento;
import ejbs.entities.CtLancamento;
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
public class CtLancamentoFacade extends AbstractFacade<CtLancamento> {

    @PersistenceContext(unitName = "SIG_UCAN-PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CtLancamentoFacade() {
        super(CtLancamento.class);
    }
    
     public List<CtLancamento> getDocummentos() {

        TypedQuery<CtLancamento> query;
        query = em.createQuery("SELECT f FROM CtLancamento f WHERE "
                + "f.stateLancamento = false ORDER BY f.pkLancamento", CtLancamento.class);

        List<CtLancamento> results = query.getResultList();

        if (results == null || results.isEmpty()) {
            return null;
        }
        return results;
    }

    public List<CtLancamento> getLancamentos() {

        TypedQuery<CtLancamento> query;
        query = em.createQuery("SELECT f FROM CtLancamento f WHERE "
                + "f.stateLancamento = true AND f.stateClssf = true ORDER BY f.pkLancamento", CtLancamento.class);

        List<CtLancamento> results = query.getResultList();

        if (results == null || results.isEmpty()) {
            return null;
        }
        return results;
    }

    public List<CtLancamento> getLancamentos(int tipoDocumento, int anoEconomico) {

        //tipoDocumento - 1 Factura
        //tipoDocumento - 2 Ordem De Saque
        TypedQuery<CtLancamento> query;
        query = em.createQuery("SELECT f FROM CtLancamento f "
                + "WHERE f.stateLancamento = true "
                + "AND f.stateClssf = true  "
                + "AND f.fkDocumento.fkTipoDoc.pkTipoDocumento = :tipoDocumento "
                + "AND f.fkAnoEconomico.pkAnoEconomico = :anoEconomico "
                + "ORDER BY f.pkLancamento", CtLancamento.class);

        query.setParameter("anoEconomico", anoEconomico);
        query.setParameter("tipoDocumento", tipoDocumento);

        List<CtLancamento> results = query.getResultList();

        if (results == null || results.isEmpty()) {
            return null;
        }
        return results;
    }

    public List<FinDocumento> getFactura() {
        TypedQuery<FinDocumento> query;

        //estadoDocumento - Classificacao Da Contabilidade
        //true - O Documento Ainda Nao Foi Classificado
        //stateDocumento - ELiminado Ou Nao No Proprio Modulo De Financas
        query = em.createQuery("SELECT f FROM FinDocumento f WHERE f.fkTipoDoc.descricao LIKE '%Fact%' "
                + "ORDER BY f.fkEntidade.nome ASC, f.dataDoc DESC  ", FinDocumento.class);
        

        List<FinDocumento> results = query.getResultList();

        if (results == null || results.isEmpty()) {
            return null;
        }
        return results;
    }

    public List<FinDocumento> getOrdemDeSaque() {
        TypedQuery<FinDocumento> query;
        query = em.createQuery("SELECT f FROM FinDocumento f WHERE "
                + "f.fkTipoDoc.descricao Like '%Ordem%' "
                + "ORDER BY f.fkEntidade.nome ASC, f.dataDoc DESC  ", FinDocumento.class);
        
        List<FinDocumento> results = query.getResultList();

        if (results == null || results.isEmpty()) {
            return null;
        }
        return results;
    }
    
    public List<FinDocumento> getFinDocumentoNaoClass(String TipoDoc) {
        //1 - Factura
        //2 - Ordem Saque
        TypedQuery<FinDocumento> query;
        query = em.createQuery("SELECT f FROM FinDocumento f WHERE f.fkTipoDoc.descricao  LIKE :TipoDoc "
                + " ORDER BY f.fkEntidade.nome ASC, f.dataDoc DESC  ", FinDocumento.class);
        query.setParameter("TipoDoc", "%"+TipoDoc+"%");
        List<FinDocumento> results = query.getResultList();

        if (results == null || results.isEmpty()) {
            return null;
        }
        return results;
    }

    
    public long getNumDocsNaoClassificadas(int pkTipoDoc) {

        String sql = "SELECT count(f.pk_documento) from fin_documento f\n"
                + " INNER JOIN gd_tipo_documento td ON f.fk_tipo_doc = td.pk_tipo_documento\n"
                + " WHERE td.pk_tipo_documento = " +pkTipoDoc+ " ";        
        Query query;
        query = em.createNativeQuery(sql);
        
        List<Object> results = (List<Object>) query.getResultList();
        
        if (results == null || results.isEmpty()) {
            return 0;
        } else {
            return (long) results.get(0);
        }
    }
    
}
