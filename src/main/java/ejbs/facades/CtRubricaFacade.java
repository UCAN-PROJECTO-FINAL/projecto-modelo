/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.facades;

import ejbs.entities.CtClass;
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
public class CtRubricaFacade extends AbstractFacade<CtRubrica> {

    @PersistenceContext(unitName = "SIG_UCAN-PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CtRubricaFacade() {
        super(CtRubrica.class);
    }
    
    
     public List<CtRubrica> getRubricaPorClasse(CtClass codigoClass) {
        TypedQuery<CtRubrica> query;
        query = em.createQuery("SELECT c FROM CtRubrica c WHERE c.fkClass = :id "
                + "AND c.stateRubrica = true ORDER BY c.numberRubrica", CtRubrica.class)
                .setParameter("id", codigoClass);

        List<CtRubrica> results = query.getResultList();

        if (results == null || results.isEmpty()) {
            return null;
        }
        return results;

    }
    
    public List<CtRubrica> getRubricaNOTFornecedores() {
        TypedQuery<CtRubrica> query;
        query = em.createQuery("SELECT c FROM CtRubrica c WHERE "
                + "c.fkClass.descricaoClass != 'Fornecedores' AND c.stateRubrica = true "
                + "ORDER BY c.numberRubrica, c.descricaoRubrica", CtRubrica.class);


        List<CtRubrica> results = query.getResultList();

        if (results.isEmpty()) {
            return null;
        }
        return results;
    }
    
//    public List<CtRubrica> listRubricaPaiNOTFornecedores() {
//        TypedQuery<CtRubrica> query;
//        query = em.createQuery("SELECT c FROM CtRubrica c WHERE "
//                + "c.stateRubrica = true and c.pkRubrica = null and c.fkClass.descricaoClass != 'Fornecedores' "
//                + " ORDER BY c.numberRubrica, c.descricaoRubrica", CtRubrica.class);
//
//        List<CtRubrica> results = query.getResultList();
//
//        if (results.isEmpty()) {
//            return null;
//        }
//        return results;
//    }

    public List<CtRubrica> getRubricaFornecedores() {
        TypedQuery<CtRubrica> query;
        query = em.createQuery("SELECT c FROM CtRubrica c WHERE "
                + "c.fkClass.descricaoClass = 'Fornecedores' AND c.stateRubrica = true "
                + "ORDER BY c.numberRubrica, c.descricaoRubrica", CtRubrica.class);

        List<CtRubrica> results = query.getResultList();

        if (results.isEmpty()) {
            return null;
        }
        return results;
    }

    public List<CtRubrica> getRubricaBensServicos() {
        TypedQuery<CtRubrica> query;
        query = em.createQuery("SELECT c FROM CtRubrica c WHERE "
                + "c.fkClass.descricaoClass = 'Bens e Serviços' AND c.stateRubrica = true ORDER BY c.pkRubrica", CtRubrica.class);

        List<CtRubrica> results = query.getResultList();

        if (results.isEmpty()) {
            return null;
        }
        return results;
    }

    public List<CtRubrica> getRubricaPoClasseAlterar(int idClasse) {
        TypedQuery<CtRubrica> query;
        query = em.createQuery("SELECT c FROM CtRubrica c WHERE "
                + "c.fkClass.pkClass = :id AND c.stateRubrica = true ORDER BY c.pkRubrica", CtRubrica.class)
                .setParameter("id", idClasse);

        List<CtRubrica> results = query.getResultList();

        if (results.isEmpty()) {
            return null;
        }
        return results;
    }

    public List<CtRubrica> listRubrica() {
        TypedQuery<CtRubrica> query;
        query = em.createQuery("SELECT c FROM CtRubrica c WHERE "
                + "c.stateRubrica = true "
                + " ORDER BY c.numberRubrica, c.descricaoRubrica", CtRubrica.class);

        List<CtRubrica> results = query.getResultList();

        if (results == null ||results.isEmpty()) {
            return null;
        }
        return results;
    }

//    public List<CtRubrica> listRubricaPai() {
//        TypedQuery<CtRubrica> query;
//        query = em.createQuery("SELECT c FROM CtRubrica c WHERE "
//                + "c.stateRubrica = true and c.pkRubrica = null"
//                + " ORDER BY c.numberRubrica, c.descricaoRubrica", CtRubrica.class);
//
//        List<CtRubrica> results = query.getResultList();
//
//        if (results.isEmpty()) {
//            return null;
//        }
//        return results;
//    }

//    public List<CtRubrica> listSubRubrica() {
//        TypedQuery<CtRubrica> query;
//        query = em.createQuery("SELECT c FROM CtRubrica c WHERE "
//                + "c.stateRubrica = true and c.fkRubrica != null"
//                + " ORDER BY c.numberRubrica, c.descricaoRubrica", CtRubrica.class);
//
//        List<CtRubrica> results = query.getResultList();
//
//        if (results.isEmpty()) {
//            return null;
//        }
//        return results;
//    }

    public List<CtRubrica> listRubricaSecundariaByRubrica(CtRubrica rubricaPai) {
        TypedQuery<CtRubrica> query;
        query = em.createQuery("SELECT c FROM CtRubrica c WHERE "
                + " c.stateRubrica = true and c.fkRubrica = :rubricaPai"
                + " ORDER BY c.numberRubrica, c.descricaoRubrica", CtRubrica.class);

        query.setParameter("rubricaPai", rubricaPai);

        List<CtRubrica> results = query.getResultList();

        if (results.isEmpty()) {
            return null;
        }
        return results;
    }

    public List<CtRubrica> getRubricaPorClasseSemValorDefinidoNoExercicio(int codigoClasse, int codigoYear) {

        TypedQuery<CtRubrica> query;
        query = em.createQuery("select r from CtRubrica r "
                + "where "
                + "r.stateRubrica = true AND "
                + "r.fkClass.pkClass = :codigoClasse AND "
                + "r.pkRubrica NOT IN "
                + "(select mc.fkRubrica.pkRubrica from CtMontanteRubrica mc "
                + "WHERE mc.fkAnoEconomico.pkAnoEconomico = :codigoYear "
                + "AND mc.stateMontanteRubrica = true) "
                + "ORDER BY r.numberRubrica", CtRubrica.class);

        query.setParameter("codigoClasse", codigoClasse);
        query.setParameter("codigoYear", codigoYear);

        List<CtRubrica> results = query.getResultList();

        if (results.isEmpty()) {
            return null;
        }
        return results;

    }

    public List<CtRubrica> listRubricasSemCC() {

        String ejbql = "select cc from CtRubrica cc\n"
                + " where cc.pkRubrica not in \n"
                + " (SELECT c.fkRubrica.pkRubrica FROM CcRubricaCdcPercentagem c WHERE c.stateRubricaCdcPercentagem = true)\n"
                + " and cc.stateRubrica = true";

        TypedQuery<CtRubrica> query;
        query = em.createQuery(ejbql, CtRubrica.class);

        List<CtRubrica> results = query.getResultList();

        if (results.isEmpty()) {
            return null;
        }
        return results;

    }

    public CtRubrica get(Integer pkRubrica) {

        String ejbql = "SELECT c FROM CtRubrica c WHERE "
                + " c.stateRubrica = true and c.pkRubrica = :pkRubrica ORDER BY c.pkRubrica";

        TypedQuery<CtRubrica> query;
        query = em.createQuery(ejbql, CtRubrica.class);

        query.setParameter("pkRubrica", pkRubrica);

        List<CtRubrica> results = query.getResultList();

        if (results == null || results.isEmpty()) {
            return null;
        }
        return results.get(0);

    }

    public List<CtRubrica> listRubricaByPai(Integer codigoPai) {

        String ejbql = "SELECT c FROM CtRubrica c WHERE "
                + " c.stateRubrica = true and c.fkRubrica.pkRubrica = :codigoPai ORDER BY c.descricaoRubrica";

        TypedQuery<CtRubrica> query;
        query = em.createQuery(ejbql, CtRubrica.class);

        query.setParameter("codigoPai", codigoPai);

        List<CtRubrica> results = query.getResultList();

        if (results == null || results.isEmpty()) {
            return null;
        }

        return results;

    }

    public int getMaxPk() {

        Query query;

        String sql = "select max (pk_rubrica) from ct_rubrica;";

        query = em.createNativeQuery(sql);

        List<Object> results = (List<Object>) query.getResultList();
        if (results == null || results.isEmpty()) {
            return 0;
        } else {
            return (int) results.get(0);
        }

    }

    public CtRubrica getByNumberAndClass(int number, int pkClass) {

        String ejbql = "SELECT c FROM CtRubrica c WHERE "
                + " c.stateRubrica = true and c.fkClass.pkClass = :pkClass "
                + "and c.number = :number"
                + " ORDER BY c.descricaoRubrica";

        TypedQuery<CtRubrica> query;
        query = em.createQuery(ejbql, CtRubrica.class);

        query.setParameter("pkClass", pkClass);
        query.setParameter("number", number);

        List<CtRubrica> results = query.getResultList();

        if (results == null || results.isEmpty()) {
            return null;
        }

        return results.get(0);

    }

    public List<CtRubrica> getRubESubRubricas(Integer pkRubrica) {
        
        String ejbql = "SELECT c FROM CtRubrica c WHERE "
                + " c.stateRubrica = true and "
                + " (c.pkRubrica = :pkRubrica OR c.fkRubrica.pkRubrica = :pkRubrica)"
                + " ORDER BY c.numberRubrica, c.descricaoRubrica";

        TypedQuery<CtRubrica> query;
        query = em.createQuery(ejbql, CtRubrica.class);

        query.setParameter("pkRubrica", pkRubrica);

        List<CtRubrica> results = query.getResultList();

        if (results == null || results.isEmpty()) {
            return null;
        }

        return results;
    }

    
}
