/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.facades;

import ejbs.entities.BbtDocumento;
import ejbs.facades.AbstractFacade;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author herman
 */
@Stateless
public class BbtDocumentoFacade extends AbstractFacade<BbtDocumento>
{

    @PersistenceContext(unitName = "SIG_UCAN-PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager()
    {
        return em;
    }

    public BbtDocumentoFacade()
    {
        super(BbtDocumento.class);
    }
    
    
    /**
     * Gera dinâmicamente uma PK
     * @return 
     */
    public Integer getNextPk()
    {
   
        List<BbtDocumento> list =this.findAll();
        int tam = list.size();
        return list.isEmpty() ? 1 : list.get(tam-1).getPkBbtDocumento()+1;
    }
    
}
