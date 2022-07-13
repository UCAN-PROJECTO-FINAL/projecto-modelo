/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.facades;

import ejbs.entities.BbtCampo;
import ejbs.facades.AbstractFacade;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author hermann
 */
@Stateless
public class BbtCampoFacade extends AbstractFacade<BbtCampo> {

    @PersistenceContext(unitName = "SIG_UCAN-PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BbtCampoFacade() {
        super(BbtCampo.class);
    }
    
    /**
     * Verifica se um determinado campo é um subcampo
     * @return 
     */
    public boolean isSubCampo(BbtCampo campo)
    {
        return (campo.getPkBbtCampo().trim().contains("$"));
    }
    
    
    public BbtCampo getBbtCampoPai(BbtCampo campo)
    {
        String pk = campo.getPkBbtCampo();
        String pkPai = pk.substring(0, pk.indexOf("$")).trim();
//        System.err.println("INDEX OF $: "+pk.indexOf("$"));
//        System.err.println("SUBSTRING $: "+pk.substring(0, 4));
        System.err.println("BbtCampoPai. Key:" + pkPai);
        return this.find(pkPai);
    }
}
