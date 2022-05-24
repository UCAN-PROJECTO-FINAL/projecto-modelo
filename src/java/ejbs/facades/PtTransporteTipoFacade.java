/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.facades;

import ejbs.entities.PtTransporteTipo;
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
public class PtTransporteTipoFacade extends AbstractFacade<PtTransporteTipo> {

    @PersistenceContext(unitName = "SIG_FRT_Master-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PtTransporteTipoFacade() {
        super(PtTransporteTipo.class);
    }
    
     
    public List<PtTransporteTipo> findAllTipoTransporteOrderByDescricao()
    {
        Query query = em.createQuery("SELECT m FROM PtTransporteTipo m ORDER BY m.descricao");
        List<PtTransporteTipo> result = query.getResultList();
        return result;
    }
    
    
    
}
