/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.facades;

import ejbs.entities.CtAnoEconomico;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author majoao
 */
@Stateless
public class CtAnoEconomicoFacade extends AbstractFacade<CtAnoEconomico> {

    @PersistenceContext(unitName = "SIG_UCAN-PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CtAnoEconomicoFacade() {
        super(CtAnoEconomico.class);
    }
    
     public CtAnoEconomico getYearPorCodigo(int codigoYear) {
       Query query = em.createQuery("SELECT c FROM CtAnoEconomico c WHERE "
                + "c.pkAnoEconomico = :id AND c.stateAnoEconomico = true", CtAnoEconomico.class)
                .setParameter("id", codigoYear);

        List<CtAnoEconomico> results = query.getResultList();
        

        if (results == null || results.isEmpty()) {
            return null;
        }
        return results.get(0);
    }
    
    public CtAnoEconomico getByAno(int ano) {
        
        Query query = em.createQuery("SELECT c FROM CtAnoEconomico c WHERE "
                + "c.anoEconomico = :ano AND c.stateAnoEconomico = true", CtAnoEconomico.class)
                .setParameter("ano", ano);

        List<CtAnoEconomico> results = query.getResultList();
        

        if (results== null || results.isEmpty()) {
            return null;
        }
        
        return results.get(0);
    }
    
    
     
    // INICIO - METODO ADICIONADO
    public List<CtAnoEconomico> findExercicioEconomicoByAno(int ano) {
      
       Query query = em.createQuery("SELECT c FROM CtAnoEconomico c WHERE c.anoEconomico = :ano AND c.stateAnoEconomico = true");
       query.setParameter("ano", ano);

       return query.getResultList();
    }//Fim findExercicioEconomicoByAno
    
    
   
    
    public List<CtAnoEconomico> getPkAnoEconomico() {
      
       Query query = em.createQuery("SELECT c FROM CtAnoEconomico c WHERE c.stateAnoEconomico = true ORDER BY  c.anoEconomico DESC");
       query.setMaxResults(1);
       return query.getResultList();
    }//Fim getPkAnoEconomico
    
    // FIM - METODO ADICIONADO
    
    
    public List<CtAnoEconomico> listYear(){
       
        Query query = em.createQuery("SELECT c FROM CtAnoEconomico c "
                + "WHERE c.stateAnoEconomico = true ORDER BY c.pkAnoEconomico", CtAnoEconomico.class);
               
        List<CtAnoEconomico> results = query.getResultList();
        

        if (results.isEmpty()) {
            return null;
        }
        return results;
    }
    
    
    public List<CtAnoEconomico> listHistorico(Date data){
       
        Query query = em.createQuery("SELECT c FROM CtAnoEconomico c WHERE c.dataAnoEconomico =:data ORDER BY c.pkAnoEconomico", CtAnoEconomico.class);
               
        List<CtAnoEconomico> results = query.getResultList();
        

        if (results.isEmpty()) {
            return null;
        }
        return results;
    }
    
}
