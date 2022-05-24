/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.facades;

import ejbs.entities.EstruturaLogicaFisicaHorario;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author smakambo
 */
@Stateless
public class EstruturaLogicaFisicaHorarioFacade extends AbstractFacade<EstruturaLogicaFisicaHorario> {

    @PersistenceContext(unitName = "SIG_FRT_Master-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EstruturaLogicaFisicaHorarioFacade() {
        super(EstruturaLogicaFisicaHorario.class);
    }
    
}
