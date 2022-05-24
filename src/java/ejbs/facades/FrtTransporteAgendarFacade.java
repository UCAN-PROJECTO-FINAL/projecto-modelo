/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.facades;

import ejbs.entities.FrtTransporteAgendar;
import ejbs.entities.PtTransporte;
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
public class FrtTransporteAgendarFacade extends AbstractFacade<FrtTransporteAgendar> {

    @PersistenceContext(unitName = "SIG_FRT_Master-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FrtTransporteAgendarFacade() {
        super(FrtTransporteAgendar.class);
    }
    
      
    
    public List<FrtTransporteAgendar> findAllOrderByNome()
    {
        Query query = em.createQuery("SELECT m FROM FrtTransporteAgendar m");
        List<FrtTransporteAgendar> result = query.getResultList();
        return result;
    }
    
    
    public List<FrtTransporteAgendar> findAllViagensLocal()
    {
        Query query = em.createQuery("SELECT m FROM FrtTransporteAgendar m WHERE m.fkTipoAgendamento.descricao = :tipoagendamento1 OR m.fkTipoAgendamento.descricao = :tipoagendamento2 AND m.isAgendado = true");
        query.setParameter("tipoagendamento1", "Viagem InterProvincial");
        query.setParameter("tipoagendamento2", "Local");
        //query.setParameter("estado", "true");
        List<FrtTransporteAgendar> result = query.getResultList();
        return result;
    }
    
    
    public boolean isMonthSelectedByOthersTransport(Calendar data,PtTransporte ptTransporte)
    {
        Query query = em.createQuery("SELECT m FROM FrtTransporteAgendar m WHERE EXTRACT(MONTH from m.data) = :mes AND EXTRACT(YEAR from m.data) = :ano AND m.fkTransporte.pkPtTransporte != :pkPtTransporte");
        query.setParameter("pkPtTransporte", ptTransporte.getPkPtTransporte());
        query.setParameter("mes", data.get(Calendar.MONTH) + 1);
        query.setParameter("ano", data.get(Calendar.YEAR));
        List<FrtTransporteAgendar> result = query.getResultList();
        if(result.isEmpty())
            return false;
        return true;
    }
    
    public boolean isHoraSelectedByOthersTransporte(Calendar data,PtTransporte ptTransporte)
    {
        Query query = em.createQuery("SELECT m FROM FrtTransporteAgendar m WHERE EXTRACT(DAY FROM m.data) = :dia "+
                "AND EXTRACT(MONTH FROM m.data) = :mes "+
                "AND EXTRACT(YEAR FROM m.data) = :ano "+
                "AND EXTRACT(HOUR FROM m.hora) = :hora "+
                "AND m.fkTransporte.pkPtTransporte != :pkPtTransporte");
        query.setParameter("pkPtTransporte", ptTransporte.getPkPtTransporte());
        query.setParameter("dia", data.get(Calendar.DAY_OF_MONTH));
        query.setParameter("mes", data.get(Calendar.MONTH) + 1);
        query.setParameter("ano", data.get(Calendar.YEAR));
        query.setParameter("hora", data.get(Calendar.HOUR));
        List<FrtTransporteAgendar> result = query.getResultList();
        if(result.isEmpty())
            return false;
        return true;
    }
    
    public boolean isDiaSelectedByOthersTransporte(Calendar data,PtTransporte ptTransporte)
    {
        Query query = em.createQuery("SELECT m FROM FrtTransporteAgendar m WHERE EXTRACT(DAY FROM m.data) = :dia "+
                "AND EXTRACT(MONTH FROM m.data) = :mes "+
                "AND EXTRACT(YEAR FROM m.data) = :ano "+
                "AND m.fkTransporte.pkPtTransporte != :pkPtTransporte");
        query.setParameter("pkPtTransporte", ptTransporte.getPkPtTransporte());
        query.setParameter("dia", data.get(Calendar.DAY_OF_MONTH));
        query.setParameter("mes", data.get(Calendar.MONTH) + 1);
        query.setParameter("ano", data.get(Calendar.YEAR));
        List<FrtTransporteAgendar> result = query.getResultList();
        if(result.isEmpty())
            return false;
        return true;
    }
    
     
    public boolean isHoraSelectedByTransporte(Calendar data,PtTransporte ptTransporte)
    {
        Query query = em.createQuery("SELECT m FROM FrtTransporteAgendar m WHERE EXTRACT (DAY FROM m.data) = :dia "+
                "AND EXTRACT (MONTH FROM m.data) = :mes "+
                "AND EXTRACT(YEAR FROM m.data) = :ano "+
                "AND EXTRACT (HOUR FROM m.hora) = :hora "+
                "AND m.fkTransporte.pkPtTransporte = :pkPtTransporte");
        query.setParameter("pkPtTransporte", ptTransporte.getPkPtTransporte());
        query.setParameter("dia", data.get(Calendar.DAY_OF_MONTH));
        query.setParameter("mes", data.get(Calendar.MONTH) + 1);
        query.setParameter("ano", data.get(Calendar.YEAR));
        query.setParameter("hora", data.get(Calendar.HOUR));
        List<FrtTransporteAgendar> result = query.getResultList();
        if(result.isEmpty())
            return false;
        return true;
    }
    
    public boolean isDiaSelectedByTransporte(Calendar data,PtTransporte ptTransporte)
    {
        Query query = em.createQuery("SELECT m FROM FrtTransporteAgendar m WHERE EXTRACT(DAY FROM m.data) = :dia "+
                "AND EXTRACT(MONTH FROM m.data) = :mes "+
                "AND EXTRACT(YEAR FROM m.data) = :ano "+
                "AND m.fkTransporte.pkPtTransporte = :pkPtTransporte");
        query.setParameter("pkPtTransporte", ptTransporte.getPkPtTransporte());
        query.setParameter("dia", data.get(Calendar.DAY_OF_MONTH));
        query.setParameter("mes", data.get(Calendar.MONTH) + 1);
        query.setParameter("ano", data.get(Calendar.YEAR));
        List<FrtTransporteAgendar> result = query.getResultList();
        if(result.isEmpty())
            return false;
        return true;
    }
    
    
    public boolean isDayFullySelectedTransporte(Calendar data)
    {
        Query query = em.createQuery("SELECT m FROM FrtTransporteAgendar m WHERE EXTRACT (DAY FROM m.data) = :dia "+
                "AND EXTRACT (MONTH FROM m.data) = :mes "+
                "AND EXTRACT (YEAR FROM m.data) = :ano ");
        query.setParameter("dia", data.get(Calendar.DAY_OF_MONTH));
        query.setParameter("mes", data.get(Calendar.MONTH) + 1);
        query.setParameter("ano", data.get(Calendar.YEAR));
        List<FrtTransporteAgendar> result = query.getResultList();
        if(result.size() >= 24)
            return true;
        return false;
    }
    
    
    public boolean isMonthFullySelectedTransporte(Calendar data)
    {
        Query query = em.createQuery("SELECT m FROM FrtTransporteAgendar m WHERE EXTRACT(MONTH from m.data) = :mes AND EXTRACT(YEAR from m.data) = :ano ");
        query.setParameter("mes", data.get(Calendar.MONTH) + 1);
        query.setParameter("ano", data.get(Calendar.YEAR));
        List<FrtTransporteAgendar> result = query.getResultList();
        if(result.size() >= data.getActualMaximum(Calendar.DAY_OF_MONTH))
            return true;
        return false;
    }
    
    
}
