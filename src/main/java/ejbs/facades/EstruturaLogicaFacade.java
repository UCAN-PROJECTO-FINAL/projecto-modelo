/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.facades;

import ejbs.entities.EstruturaLogica;
import ejbs.facades.AbstractFacade;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author mdnex
 */
@Stateless
public class EstruturaLogicaFacade extends AbstractFacade<EstruturaLogica>
{

    @PersistenceContext(unitName = "SIG_UCAN-PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager()
    {
        return em;
    }

    public EstruturaLogicaFacade()
    {
        super(EstruturaLogica.class);
    }
    
    public EstruturaLogica findByPkEstruturaFisica(String pkEstruturaLogica)
    {
        Query query = em.createQuery("SELECT m FROM EstruturaLogica m WHERE m.pkEstruturaLogica = :pkEstruturaLogica");
        query.setParameter("pkEstruturaLogica", pkEstruturaLogica);
        List<EstruturaLogica> result = query.getResultList();
        return result.isEmpty() ? null : result.get(0);
    }
    
    public String findFkEstruturaLogica(String pkEstruturaLogica)
    {
        pkEstruturaLogica = pkEstruturaLogica.trim();

        if (!pkEstruturaLogica.contains("."))
        {
            return null;
        }
        return pkEstruturaLogica.substring(0, pkEstruturaLogica.lastIndexOf("."));
    }
    
    public List<EstruturaLogica> findAllOrderByNome()
    {
        Query query = em.createQuery("SELECT m FROM EstruturaLogica m ORDER BY designacao");
        List<EstruturaLogica> result = query.getResultList();
        return result;
    }
    
    public String toString(EstruturaLogica reg)
    {
        if (reg != null)
            return "[" + reg.getPkEstruturaLogica()+ ", "
            + reg.getDesignacao()+"]";
        return "";
    }
    
}
