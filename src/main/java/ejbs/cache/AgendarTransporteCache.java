/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.cache;


import ejbs.entities.PtTransporte;
import ejbs.entities.FrtTransporteAgendar;
import ejbs.facades.FrtTransporteAgendarFacade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import utils.Defs;

/**
 *
 * @author samuel
 */
@Named
@ApplicationScoped
public class AgendarTransporteCache implements Serializable
{
     @EJB
    private FrtTransporteAgendarFacade frAgendarTransporteFacade;
    //private FrAgendarTransporteFacade frAgendarTransporteFacade;
    private List<FrtTransporteAgendar> listaFrAgendarTransporte;
    private HashMap<Integer, FrtTransporteAgendar> hashTabela;

    public AgendarTransporteCache() 
    {
        
    }


    @PostConstruct
    public void init()
    {
        listaFrAgendarTransporte = frAgendarTransporteFacade.findAllOrderByNome();
        hashTabela = new HashMap();
        int pkFrAgendarTransporte;
        for (FrtTransporteAgendar reg : listaFrAgendarTransporte)
        {
            pkFrAgendarTransporte = reg.getPkAgendarTransporte();

            hashTabela.put(pkFrAgendarTransporte, reg);
        }
        //listaEstruturaFisica = localidadeFacade.findAll();
    } 

    public List<FrtTransporteAgendar> getListaFrAgendarTransporte() 
    {
        return listaFrAgendarTransporte;
    }
    
    public boolean isHoraSelectedByOthersTransporte(Calendar hora,PtTransporte selectedPtTransporte)
    {
        return frAgendarTransporteFacade.isHoraSelectedByOthersTransporte(hora, selectedPtTransporte);
    }
    
    public boolean isDiaSelectedByOthersTransporte(Calendar dia,PtTransporte selectedPtTransporte)
    {
        //System.err.println("data:"+dia);
        return frAgendarTransporteFacade.isDiaSelectedByOthersTransporte(dia, selectedPtTransporte);
    }
    
    public String getDiaColorTransporte(Calendar dia,PtTransporte selectedPtTransporte)
    {
        boolean own_entity=frAgendarTransporteFacade.isDiaSelectedByTransporte(dia, selectedPtTransporte);
        boolean other_entity=frAgendarTransporteFacade.isDiaSelectedByTransporte(dia, selectedPtTransporte); 
        boolean is_full = frAgendarTransporteFacade.isDayFullySelectedTransporte(dia);
        //System.err.println("mes__year"+(cld_mes.get(Calendar.MONTH) + 1)+"/"+(cld_mes.get(Calendar.YEAR))+""+own_entity+"__"+other_entity+"___"+is_full);
        if(is_full)
        {
            if(own_entity && other_entity)
                return Defs.COR_COMPLETAMENTE_OCUPADO_ENTIDADE_OUTROS;
            if(other_entity && !own_entity)
                return Defs.COR_COMPLETAMENTE_OCUPADO_OUTROS;
            return Defs.COR_COMPLETAMENTE_OCUPADO_ENTIDADE;
        }
        else
        {
            if(own_entity && other_entity)
                return Defs.COR_PARCIAL_OCUPADO_ENTIDADE_OUTROS;
            if(other_entity && !own_entity)
                return Defs.COR_PARCIAL_OCUPADO_OUTROS;
            if(own_entity && !other_entity)
                return Defs.COR_PARCIAL_OCUPADO_ENTIDADE;
            return Defs.COR_COMPLETAMENTE_LIVRE;
        }
    }
    
    
    public boolean isMesSelectedByOthersTransport(Date mes1,PtTransporte selectedPtTransporte)
    {
        
        Calendar cld = Calendar.getInstance();
        cld.setTime(mes1);
        
        return frAgendarTransporteFacade.isMonthSelectedByOthersTransport(cld, selectedPtTransporte);
    }
    
    public String getMesColorTransporte(Calendar cld_mes,PtTransporte selectedTransporte)
    {
        
        boolean own_entity=frAgendarTransporteFacade.isMonthSelectedByOthersTransport(cld_mes, selectedTransporte);
        boolean other_entity=frAgendarTransporteFacade.isMonthSelectedByOthersTransport(cld_mes, selectedTransporte); 
        boolean is_full = frAgendarTransporteFacade.isMonthFullySelectedTransporte(cld_mes);
        //System.err.println("mes__year"+(cld_mes.get(Calendar.MONTH) + 1)+"/"+(cld_mes.get(Calendar.YEAR))+""+own_entity+"__"+other_entity+"___"+is_full);
        if(is_full)
        {
            if(own_entity && other_entity)
                return Defs.COR_COMPLETAMENTE_OCUPADO_ENTIDADE_OUTROS;
            if(other_entity && !own_entity)
                return Defs.COR_COMPLETAMENTE_OCUPADO_OUTROS;
            return Defs.COR_COMPLETAMENTE_OCUPADO_ENTIDADE;
        }
        else
        {
            if(own_entity && other_entity)
                return Defs.COR_PARCIAL_OCUPADO_ENTIDADE_OUTROS;
            if(other_entity && !own_entity)
                return Defs.COR_PARCIAL_OCUPADO_OUTROS;
            if(own_entity && !other_entity)
                return Defs.COR_PARCIAL_OCUPADO_ENTIDADE;
            return Defs.COR_COMPLETAMENTE_LIVRE;
        }
        
    }
    
     
    public String getHoraColorTransporte(Calendar hora,PtTransporte selectedPtTransporte)
    {
        
        boolean own_entity=frAgendarTransporteFacade.isHoraSelectedByTransporte(hora, selectedPtTransporte);
        boolean other_entity=frAgendarTransporteFacade.isDiaSelectedByOthersTransporte(hora, selectedPtTransporte);
        
        System.err.println("own" + own_entity + "other_" + other_entity);
        
        if(other_entity && !own_entity)
            return Defs.COR_COMPLETAMENTE_OCUPADO_OUTROS;
        if(own_entity && !other_entity)
            return Defs.COR_COMPLETAMENTE_OCUPADO_ENTIDADE;
        return Defs.COR_COMPLETAMENTE_LIVRE;
    }
    
    
    public List<PtTransporte> findByTransportePk(int pkTransporte)
    {
        Date data = new Date();
        
        Calendar current_date = Calendar.getInstance(), regDate = Calendar.getInstance();
        current_date.setTime(data);
        
        List<PtTransporte> resultList = new ArrayList();
        for (FrtTransporteAgendar reg : listaFrAgendarTransporte)
        {
            System.err.println("reg");
            //if (reg.getFkEstruturaLogica().getPkEstruturaLogica().equals(pkEstruturaLogica) && data.compareTo(reg.getData()) <= 0)
            if (data.compareTo(reg.getData()) >= 0)
            {
                PtTransporte t = new PtTransporte();
                t.setPkPtTransporte(reg.getFkTransporte().getPkPtTransporte());
                resultList.add(t);
            }
        }
//        for (FrAgendarTransporte reg : this.hashTabela.values())
//        {
//            System.err.println("reg");
//            //if (reg.getFkEstruturaLogica().getPkEstruturaLogica().equals(pkEstruturaLogica) && data.compareTo(reg.getData()) <= 0)
//            if (data.compareTo(reg.getData()) >= 0)
//            {
//                resultList.add(reg.getFkTransporte());
//            }
//        }

        return resultList;
    }
    
    
    public void create(FrtTransporteAgendar reg)
    {
        frAgendarTransporteFacade.create(reg);
    }

    public void edit(FrtTransporteAgendar reg)
    {
        frAgendarTransporteFacade.edit(reg);
    }

    public void remove(FrtTransporteAgendar reg)
    {
        frAgendarTransporteFacade.remove(reg);
    }
    
    
}
