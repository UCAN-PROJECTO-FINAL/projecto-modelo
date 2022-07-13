/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.facades;

import ejbs.entities.PtTransporteCategoria;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author smakambo
 */
@Stateless
public class PtTransporteCategoriaFacade extends AbstractFacade<PtTransporteCategoria> {

    @PersistenceContext(unitName = "SIG_UCAN-PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PtTransporteCategoriaFacade() {
        super(PtTransporteCategoria.class);
    }
    
}
