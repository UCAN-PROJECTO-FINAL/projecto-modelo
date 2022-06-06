/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.facades;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import ejbs.entities.LocLocalidade;
import ejbs.facades.AbstractFacade;

/**
 *
 * @author mdnext
 */
@Stateless
public class LocLocalidadeFacade extends AbstractFacade<LocLocalidade> {

    @PersistenceContext(unitName = "SIG_UCAN-PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LocLocalidadeFacade() {
        super(LocLocalidade.class);
    }
    
    public String getFkLocalidade(String pkLocalidade)
    {
        pkLocalidade = pkLocalidade.trim();

        if (!pkLocalidade.contains("."))
        {
            return null;
        }
        return pkLocalidade.substring(0, pkLocalidade.lastIndexOf("."));
    }
    
    public List<LocLocalidade> findAllOrderByNome()
    {
        Query query = em.createQuery("SELECT m FROM LocLocalidade m ORDER BY m.designacao");
        List<LocLocalidade> result = query.getResultList();
        return result;
    }
    //ALTERAR TO DESTRITO-------------------------------------------
   public List<LocLocalidade> findAllDistritoOrderByNome()
    {
        Query query = em.createQuery("SELECT m FROM LocLocalidade m "
                + "WHERE m.fkLocLocalidade IS NOT NULL ORDER BY m.designacao");
        List<LocLocalidade> result = query.getResultList();
        return result;
    }
    
    public String toString(LocLocalidade reg)
    {
        if (reg != null)
            return "[" + reg.getPkLocLocalidade()+ ", "
            + reg.getDesignacao()+"]";
        return "";
    }
    
    public LocLocalidade findByPkLocalidade(String pkLocLocalidade)
    {
        Query query = em.createQuery("SELECT m FROM LocLocalidade m WHERE m.pkLocLocalidade = :pkLocLocalidade");
        query.setParameter("pkLocLocalidade", pkLocLocalidade);
        List<LocLocalidade> result = query.getResultList();
        return result.isEmpty() ? null : result.get(0);
    }
    
}
