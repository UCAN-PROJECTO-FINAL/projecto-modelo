/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.facades;

import ejbs.entities.EstruturaLogica;
import ejbs.entities.EstruturaLogicaFisica;
import ejbs.entities.FrtTransporteAgendar;
import java.util.Calendar;
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
public class EstruturaLogicaFisicaFacade extends AbstractFacade<EstruturaLogicaFisica> {

    @PersistenceContext(unitName = "SIG_FRT_Master-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EstruturaLogicaFisicaFacade() {
        super(EstruturaLogicaFisica.class);
    }
    
    
    public List<EstruturaLogicaFisica> findAllOrderByNome()
    {
        Query query = em.createQuery("SELECT m FROM EstruturaLogicaFisica m");
        List<EstruturaLogicaFisica> result = query.getResultList();
        return result;
    }
    
    public boolean isHoraSelectedBy(Calendar data,EstruturaLogica estrutura)
    {
        Query query = em.createQuery("SELECT m FROM EstruturaLogicaFisica m WHERE EXTRACT (DAY FROM m.data) = :dia "+
                "AND EXTRACT (MONTH FROM m.data) = :mes "+
                "AND EXTRACT(YEAR FROM m.data) = :ano "+
                "AND EXTRACT (HOUR FROM m.hora) = :hora "+
                "AND m.fkEstruturaLogica.pkEstruturaLogica = :pkEstruturaLogica");
        query.setParameter("pkEstruturaLogica", estrutura.getPkEstruturaLogica());
        query.setParameter("dia", data.get(Calendar.DAY_OF_MONTH));
        query.setParameter("mes", data.get(Calendar.MONTH) + 1);
        query.setParameter("ano", data.get(Calendar.YEAR));
        query.setParameter("hora", data.get(Calendar.HOUR));
        List<EstruturaLogicaFisica> result = query.getResultList();
        if(result.isEmpty())
            return false;
        return true;
    }
    
    public boolean isHoraSelectedByOthers(Calendar data,EstruturaLogica estrutura)
    {
        Query query = em.createQuery("SELECT m FROM EstruturaLogicaFisica m WHERE EXTRACT(DAY FROM m.data) = :dia "+
                "AND EXTRACT(MONTH FROM m.data) = :mes "+
                "AND EXTRACT(YEAR FROM m.data) = :ano "+
                "AND EXTRACT(HOUR FROM m.hora) = :hora "+
                "AND m.fkEstruturaLogica.pkEstruturaLogica != :pkEstruturaLogica");
        query.setParameter("pkEstruturaLogica", estrutura.getPkEstruturaLogica());
        query.setParameter("dia", data.get(Calendar.DAY_OF_MONTH));
        query.setParameter("mes", data.get(Calendar.MONTH) + 1);
        query.setParameter("ano", data.get(Calendar.YEAR));
        query.setParameter("hora", data.get(Calendar.HOUR));
        List<EstruturaLogicaFisica> result = query.getResultList();
        if(result.isEmpty())
            return false;
        return true;
    }
    
    public boolean isDayFullySelected(Calendar data)
    {
        Query query = em.createQuery("SELECT m FROM EstruturaLogicaFisica m WHERE EXTRACT (DAY FROM m.data) = :dia "+
                "AND EXTRACT (MONTH FROM m.data) = :mes "+
                "AND EXTRACT (YEAR FROM m.data) = :ano ");
        query.setParameter("dia", data.get(Calendar.DAY_OF_MONTH));
        query.setParameter("mes", data.get(Calendar.MONTH) + 1);
        query.setParameter("ano", data.get(Calendar.YEAR));
        List<EstruturaLogicaFisica> result = query.getResultList();
        if(result.size() >= 24)
            return true;
        return false;
    }
    
    public boolean isDiaSelectedBy(Calendar data,EstruturaLogica estrutura)
    {
        Query query = em.createQuery("SELECT m FROM EstruturaLogicaFisica m WHERE EXTRACT(DAY FROM m.data) = :dia "+
                "AND EXTRACT(MONTH FROM m.data) = :mes "+
                "AND EXTRACT(YEAR FROM m.data) = :ano "+
                "AND m.fkEstruturaLogica.pkEstruturaLogica = :pkEstruturaLogica");
        query.setParameter("pkEstruturaLogica", estrutura.getPkEstruturaLogica());
        query.setParameter("dia", data.get(Calendar.DAY_OF_MONTH));
        query.setParameter("mes", data.get(Calendar.MONTH) + 1);
        query.setParameter("ano", data.get(Calendar.YEAR));
        List<EstruturaLogicaFisica> result = query.getResultList();
        if(result.isEmpty())
            return false;
        return true;
    }
    
    public boolean isDiaSelectedByOthers(Calendar data,EstruturaLogica estrutura)
    {
        Query query = em.createQuery("SELECT m FROM EstruturaLogicaFisica m WHERE EXTRACT(DAY FROM m.data) = :dia "+
                "AND EXTRACT(MONTH FROM m.data) = :mes "+
                "AND EXTRACT(YEAR FROM m.data) = :ano "+
                "AND m.fkEstruturaLogica.pkEstruturaLogica != :pkEstruturaLogica");
        query.setParameter("pkEstruturaLogica", estrutura.getPkEstruturaLogica());
        query.setParameter("dia", data.get(Calendar.DAY_OF_MONTH));
        query.setParameter("mes", data.get(Calendar.MONTH) + 1);
        query.setParameter("ano", data.get(Calendar.YEAR));
        List<EstruturaLogicaFisica> result = query.getResultList();
        if(result.isEmpty())
            return false;
        return true;
    }
    
    public boolean isMonthFullySelected(Calendar data)
    {
        Query query = em.createQuery("SELECT m FROM EstruturaLogicaFisica m WHERE EXTRACT(MONTH from m.data) = :mes AND EXTRACT(YEAR from m.data) = :ano ");
        query.setParameter("mes", data.get(Calendar.MONTH) + 1);
        query.setParameter("ano", data.get(Calendar.YEAR));
        List<EstruturaLogicaFisica> result = query.getResultList();
        if(result.size() >= data.getActualMaximum(Calendar.DAY_OF_MONTH))
            return true;
        return false;
    }
    
    ////
    public boolean isMonthFullySelectedTransporte(Calendar data)
    {
        Query query = em.createQuery("SELECT m FROM PtAgendarTransporte m WHERE EXTRACT(MONTH from m.data) = :mes AND EXTRACT(YEAR from m.data) = :ano ");
        query.setParameter("mes", data.get(Calendar.MONTH) + 1);
        query.setParameter("ano", data.get(Calendar.YEAR));
        List<FrtTransporteAgendar> result = query.getResultList();
        if(result.size() >= data.getActualMaximum(Calendar.DAY_OF_MONTH))
            return true;
        return false;
    }
    
    public boolean isMonthSelectedBy(Calendar data,EstruturaLogica estrutura)
    {
        Query query = em.createQuery("SELECT m FROM EstruturaLogicaFisica m WHERE EXTRACT(MONTH from m.data) = :mes AND EXTRACT(YEAR from m.data) = :ano AND m.fkEstruturaLogica.pkEstruturaLogica = :pkEstruturaLogica");
        query.setParameter("pkEstruturaLogica", estrutura.getPkEstruturaLogica());
        query.setParameter("mes", data.get(Calendar.MONTH) + 1);
        query.setParameter("ano", data.get(Calendar.YEAR));
        List<EstruturaLogicaFisica> result = query.getResultList();
        if(result.isEmpty())
            return false;
        return true;
    }
    
    public boolean isMonthSelectedByOthers(Calendar data,EstruturaLogica estrutura)
    {
        Query query = em.createQuery("SELECT m FROM EstruturaLogicaFisica m WHERE EXTRACT(MONTH from m.data) = :mes AND EXTRACT(YEAR from m.data) = :ano AND m.fkEstruturaLogica.pkEstruturaLogica != :pkEstruturaLogica");
        query.setParameter("pkEstruturaLogica", estrutura.getPkEstruturaLogica());
        query.setParameter("mes", data.get(Calendar.MONTH) + 1);
        query.setParameter("ano", data.get(Calendar.YEAR));
        List<EstruturaLogicaFisica> result = query.getResultList();
        if(result.isEmpty())
            return false;
        return true;
    }
    
    public EstruturaLogicaFisica findByPkEstruturaLogicaFisica(int pkEstruturaLogicaFisica)
    {
        Query query = em.createQuery("SELECT m FROM EstruturaLogicaFisica m WHERE m.pkEstruturaLogicaFisica = :pkEstruturaLogicaFisica");
        query.setParameter("pkEstruturaLogicaFisica", pkEstruturaLogicaFisica);
        List<EstruturaLogicaFisica> result = query.getResultList();
        return result.isEmpty() ? null : result.get(0);
    }
    
    public String toString(EstruturaLogicaFisica reg)
    {
        if (reg != null)
            return "[" + reg.getPkEstruturaLogicaFisica()+ ", "
            + reg.getFkEstruturaFisica()+"]";
        return "";
    }
    
    public int findNextPkEstruturaLogicaFisica()
    {
        Query query = em.createQuery("SELECT m FROM EstruturaLogicaFisica m");
        List<EstruturaLogicaFisica> result = query.getResultList();
        return result.isEmpty() ? 1 : (result.get(result.size() - 1).getPkEstruturaLogicaFisica() + 1); 
    }
    
    
}
