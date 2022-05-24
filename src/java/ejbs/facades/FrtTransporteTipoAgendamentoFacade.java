/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.facades;

import ejbs.entities.FrtTransporteTipoAgendamento;
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
public class FrtTransporteTipoAgendamentoFacade extends AbstractFacade<FrtTransporteTipoAgendamento> {

    @PersistenceContext(unitName = "SIG_FRT_Master-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FrtTransporteTipoAgendamentoFacade() {
        super(FrtTransporteTipoAgendamento.class);
    }
    
    public List<FrtTransporteTipoAgendamento> findAllTipoAgendamentoOrderByDescricao()
    {
        Query query = em.createQuery("SELECT t FROM FrtTransporteTipoAgendamento t ORDER BY t.descricao");
        List<FrtTransporteTipoAgendamento> result = query.getResultList();
        return result;
    } 
    
    public List<FrtTransporteTipoAgendamento> findTipoAgendamentoByDescricao(String tipoAgendamento)
    {
        Query q = em.createQuery("SELECT c FROM FrtTransporteTipoAgendamento c WHERE c.descricao =:tipoAgendamento");
        q.setParameter("tipoAgendamento", tipoAgendamento);
        q.setMaxResults(1);
        
        return q.getResultList();
    }
    
}
