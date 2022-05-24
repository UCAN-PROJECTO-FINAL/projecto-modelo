/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.facades;

import ejbs.entities.EstruturaFisica;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author smakambo
 */
@Stateless
public class EstruturaFisicaFacade extends AbstractFacade<EstruturaFisica> {

    @PersistenceContext(unitName = "SIG_FRT_Master-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EstruturaFisicaFacade() {
        super(EstruturaFisica.class);
    }
    
    
    public EstruturaFisica findByPkEstruturaFisica(String pkEstruturaFisica)
    {
        Query query = em.createQuery("SELECT m FROM EstruturaFisica m WHERE m.pkEstruturaFisica = :pkEstruturaFisica");
        query.setParameter("pkEstruturaFisica", pkEstruturaFisica);
        List<EstruturaFisica> result = query.getResultList();
        return result.isEmpty() ? null : result.get(0);
    }
    
    public String findFkEstruturaFisica(String pkEstruturaFisica)
    {
        pkEstruturaFisica = pkEstruturaFisica.trim();

        if (!pkEstruturaFisica.contains("."))
        {
            return null;
        }
        return pkEstruturaFisica.substring(0, pkEstruturaFisica.lastIndexOf("."));
    }
    
    public List<EstruturaFisica> findAllOrderByDesignacao()
    {
        Query query = em.createQuery("SELECT m FROM EstruturaFisica m ORDER BY m.designacao");
        List<EstruturaFisica> result = query.getResultList();
        return result;
    }
    
    public String toString(EstruturaFisica reg)
    {
        if (reg != null)
            return "[" + reg.getPkEstruturaFisica()+ ", "
            + reg.getDesignacao()+"]";
        return "";
    }
    
    
}
