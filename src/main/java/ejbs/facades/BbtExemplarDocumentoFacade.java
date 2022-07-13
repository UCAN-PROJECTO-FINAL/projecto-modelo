/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.facades;

import ejbs.entities.BbtDocumento;
import ejbs.entities.BbtExemplarDocumento;
import ejbs.facades.AbstractFacade;
import java.util.ArrayList;
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
public class BbtExemplarDocumentoFacade extends AbstractFacade<BbtExemplarDocumento>
{

    @PersistenceContext(unitName = "SIG_UCAN-PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager()
    {
        return em;
    }

    public BbtExemplarDocumentoFacade()
    {
        super(BbtExemplarDocumento.class);
    }

    /**
     * Não sei o q faz!
     * @param documento
     * @return 
     */
    public List<String> getDadosCota(BbtDocumento documento)
    {
       
        Query q = em.createQuery(
                "SELECT ed FROM BbtExemplarDocumento ed WHERE ed.fkBbtDocumento.pkBbtDocumento = "
                + documento.getPkBbtDocumento());
        List<BbtExemplarDocumento> list = q.getResultList();
        List<String> dados = new ArrayList<>();
        
        //dados.add(list.get(0).)
        
        
        //dados.add(list.get(0).getCodInterno());
        return dados; 
    }
    
}
