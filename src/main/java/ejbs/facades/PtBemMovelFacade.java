/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.facades;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import ejbs.entities.PtBemMovel;
import ejbs.facades.AbstractFacade;

/**
 *
 * @author mdnext
 */
@Stateless
public class PtBemMovelFacade extends AbstractFacade<PtBemMovel> {

    @PersistenceContext(unitName = "SIG_UCAN-PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PtBemMovelFacade() {
        super(PtBemMovel.class);
    }
    
}
