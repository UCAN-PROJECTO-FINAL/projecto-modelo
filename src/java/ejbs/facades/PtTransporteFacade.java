/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.facades;

import ejbs.entities.PtTransporte;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author smakambo
 */
@Stateless
public class PtTransporteFacade extends AbstractFacade<PtTransporte> {

    @PersistenceContext(unitName = "SIG_FRT_Master-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PtTransporteFacade() {
        super(PtTransporte.class);
    }
    
    
      
    public PtTransporte findTransporteByPk(int pkPtTransporte)
    {
        Query query = em.createQuery("SELECT m FROM PtTransporte m WHERE m.pkPtTransporte = :pkPtTransporte");
        query.setParameter("pkPtTransporte", pkPtTransporte);
        List<PtTransporte> result = query.getResultList();
        return result.isEmpty() ? null : result.get(0);
    }
    
    public List<PtTransporte> findAllTransporteOrderByMatricula()
    {
        Query query = em.createQuery("SELECT t FROM PtTransporte t ORDER BY t.matricula");
        List<PtTransporte> result = query.getResultList();
        return result;
    }
    
    /*
     public List<PtTransporte> findAllTransporteById(int codigoSolicitacao)
    {
        Query query = em.createQuery("SELECT m FROM FrtTransporteSolicitacao m WHERE m.pkSolicitacao = :id");
        query.setParameter("id", codigoSolicitacao);
        //query.setParameter("tipoagendamento2", "Local");
        //query.setParameter("estado", "true");
        List<PtTransporte> result = query.getResultList();
        return result;
    }*/
    
}
