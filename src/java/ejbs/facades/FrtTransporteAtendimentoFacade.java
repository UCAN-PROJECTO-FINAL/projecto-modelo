/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.facades;

import ejbs.entities.FrtTransporteAtendimento;
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
public class FrtTransporteAtendimentoFacade extends AbstractFacade<FrtTransporteAtendimento> {

    @PersistenceContext(unitName = "SIG_FRT_Master-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FrtTransporteAtendimentoFacade() {
        super(FrtTransporteAtendimento.class);
    }
    
    
    public List<FrtTransporteAtendimento> findAllTrnasporteEmAndamento()
    {
        Query query = em.createQuery("SELECT m FROM FrtTransporteAtendimento m WHERE m.fkTransporteAtendimentEstado.descricao = :estado");
        query.setParameter("estado", "Andamento");
        List<FrtTransporteAtendimento> result = query.getResultList();
        return result;
    }
    
}
