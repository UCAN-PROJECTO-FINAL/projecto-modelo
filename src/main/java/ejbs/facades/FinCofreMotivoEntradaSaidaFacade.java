/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.facades;

import ejbs.entities.FinCofreMotivoEntradaSaida;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author majoao
 */
@Stateless
public class FinCofreMotivoEntradaSaidaFacade extends AbstractFacade<FinCofreMotivoEntradaSaida> {

    @PersistenceContext(unitName = "SIG_UCAN-PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FinCofreMotivoEntradaSaidaFacade() {
        super(FinCofreMotivoEntradaSaida.class);
    }
    
}
