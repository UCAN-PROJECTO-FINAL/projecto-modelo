/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estrutura.ejbs.cache;

import ejbs.entities.EstruturaFisica;
import ejbs.entities.EstruturaLogica;
import ejbs.entities.EstruturaLogicaFisica;
import ejbs.facades.EstruturaLogicaFisicaFacade;
import estrutura.utils.Defs;
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
/**
 *
 * @author KiamiSoft_ACosta
 */
@Named
@ApplicationScoped
public class EstruturaLogicaFisicaCache implements Serializable
{

    @EJB
    private EstruturaLogicaFisicaFacade estruturaLogicaFisicaFacade;
    private List<EstruturaLogicaFisica> listaEstruturaLogicaFisica;
    private HashMap<Integer, EstruturaLogicaFisica> hashTabela;

    /**
     * Creates a new instance of PaisBean
     */
    public EstruturaLogicaFisicaCache()
    {
    }

    /**
     *
     */
    @PostConstruct
    public void init()
    {
        listaEstruturaLogicaFisica = estruturaLogicaFisicaFacade.findAllOrderByNome();
        hashTabela = new HashMap();
        int pkEstruturaLogicaFisica;
        for (EstruturaLogicaFisica reg : listaEstruturaLogicaFisica)
        {
            pkEstruturaLogicaFisica = reg.getPkEstruturaLogicaFisica();

            hashTabela.put(pkEstruturaLogicaFisica, reg);
        }
        //listaEstruturaFisica = localidadeFacade.findAll();
    }

    public List<EstruturaLogicaFisica> getListaEstruturaFisicas()
    {
        return listaEstruturaLogicaFisica;
    }

    public List<EstruturaFisica> findByEstruturaLogicaPk(String pkEstruturaLogica)
    {
        Date data = new Date();
        
        Calendar current_date = Calendar.getInstance(), regDate = Calendar.getInstance();
        current_date.setTime(data);
        
        List<EstruturaFisica> resultList = new ArrayList();
        for (EstruturaLogicaFisica reg : this.hashTabela.values())
        {
            //if (reg.getFkEstruturaLogica().getPkEstruturaLogica().equals(pkEstruturaLogica) && data.compareTo(reg.getData()) <= 0)
            if (reg.getFkEstruturaLogica().getPkEstruturaLogica().equals(pkEstruturaLogica))
            {
                if(reg.getData() == null)
                    resultList.add(reg.getFkEstruturaFisica());
                else if(data.compareTo(reg.getData()) >= 0)
                    resultList.add(reg.getFkEstruturaFisica());
                
            }
        }

        return resultList;
    }
    
    public boolean isHoraSelectedBy(Calendar hora,EstruturaLogica selectedEstruturaLogica)
    {   
        return estruturaLogicaFisicaFacade.isHoraSelectedBy(hora, selectedEstruturaLogica);
    }
    
    public boolean isDiaSelectedBy(Calendar dia,EstruturaLogica selectedEstruturaLogica)
    {
        String strData = dia.get(Calendar.YEAR)+"-"+(dia.get(Calendar.MONTH) + 1)+"-"+dia.get(Calendar.DAY_OF_MONTH);
        Date data = dia.getTime();
        return estruturaLogicaFisicaFacade.isDiaSelectedBy(dia, selectedEstruturaLogica);
    }
    
    public boolean isMesSelectedBy(Date mes,EstruturaLogica selectedEstruturaLogica)
    {
        
        Calendar cld = Calendar.getInstance();
        cld.setTime(mes);
        
        return estruturaLogicaFisicaFacade.isMonthSelectedBy(cld, selectedEstruturaLogica);
    }
    
    public boolean isHoraSelectedByOthers(Calendar hora,EstruturaLogica selectedEstruturaLogica)
    {
        return estruturaLogicaFisicaFacade.isHoraSelectedByOthers(hora, selectedEstruturaLogica);
    }
    
    public boolean isDiaSelectedByOthers(Calendar dia,EstruturaLogica selectedEstruturaLogica)
    {
        //System.err.println("data:"+dia);
        return estruturaLogicaFisicaFacade.isDiaSelectedByOthers(dia, selectedEstruturaLogica);
    }
    
    public boolean isMesSelectedByOthers(Date mes,EstruturaLogica selectedEstruturaLogica)
    {
        
        Calendar cld = Calendar.getInstance();
        cld.setTime(mes);
        
        return estruturaLogicaFisicaFacade.isMonthSelectedByOthers(cld, selectedEstruturaLogica);
    }
    
    public String getHoraColor(Calendar hora,EstruturaLogica selectedEstruturaLogica)
    {
        
        boolean own_entity=estruturaLogicaFisicaFacade.isHoraSelectedBy(hora, selectedEstruturaLogica);
        boolean other_entity=estruturaLogicaFisicaFacade.isHoraSelectedByOthers(hora, selectedEstruturaLogica);

        if(other_entity && !own_entity)
            return Defs.COR_COMPLETAMENTE_OCUPADO_OUTROS;
        if(own_entity && !other_entity)
            return Defs.COR_COMPLETAMENTE_OCUPADO_ENTIDADE;
        return Defs.COR_COMPLETAMENTE_LIVRE;
    }
    
    public String getDiaColor(Calendar dia,EstruturaLogica selectedEstruturaLogica)
    {
        boolean own_entity=estruturaLogicaFisicaFacade.isDiaSelectedBy(dia, selectedEstruturaLogica);
        boolean other_entity=estruturaLogicaFisicaFacade.isDiaSelectedByOthers(dia, selectedEstruturaLogica); 
        boolean is_full = estruturaLogicaFisicaFacade.isDayFullySelected(dia);
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
    
    public String getMesColor(Calendar cld_mes,EstruturaLogica selectedEstruturaLogica)
    {
        
        boolean own_entity=estruturaLogicaFisicaFacade.isMonthSelectedBy(cld_mes, selectedEstruturaLogica);
        boolean other_entity=estruturaLogicaFisicaFacade.isMonthSelectedByOthers(cld_mes, selectedEstruturaLogica); 
        boolean is_full = estruturaLogicaFisicaFacade.isMonthFullySelected(cld_mes);
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

    public void create(EstruturaLogicaFisica reg)
    {
        estruturaLogicaFisicaFacade.create(reg);
    }

    public void edit(EstruturaLogicaFisica reg)
    {
        estruturaLogicaFisicaFacade.edit(reg);
    }

    public void remove(EstruturaLogicaFisica reg)
    {
        estruturaLogicaFisicaFacade.remove(reg);
    }

}
