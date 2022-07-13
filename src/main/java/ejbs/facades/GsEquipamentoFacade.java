/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.facades;

import ejbs.entities.GsEquipamento;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author jesus
 */
@Stateless
public class GsEquipamentoFacade extends AbstractFacade<GsEquipamento> {

    @PersistenceContext(unitName = "SIG_UCAN-PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GsEquipamentoFacade() {
        super(GsEquipamento.class);
    }
    
        public List<GsEquipamento> findAllOrderByMarca() {
        Query query = em.createQuery("SELECT g FROM GsEquipamento g ORDER BY g.marca");
        List<GsEquipamento> result = query.getResultList();
        return result;
    }
    
}
