/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.facades;

import ejbs.entities.BbtDocumentoBbtCdu;
import ejbs.facades.AbstractFacade;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author herman
 */
@Stateless
public class BbtDocumentoBbtCduFacade extends AbstractFacade<BbtDocumentoBbtCdu>
{

    @PersistenceContext(unitName = "SIG_UCAN-PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager()
    {
        return em;
    }

    public BbtDocumentoBbtCduFacade()
    {
        super(BbtDocumentoBbtCdu.class);
    }

        
    public Integer getNextPk()
    {
        List<BbtDocumentoBbtCdu> list = this.findAll();
        int tam = list.size();
        return list.isEmpty() ? 1 : list.get(tam - 1).getPkBbtDocumentoBbtCdu()+ 1;
    }
    
}
