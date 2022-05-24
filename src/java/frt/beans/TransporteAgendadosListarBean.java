/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frt.beans;

import ejbs.entities.FrtTransporteAgendar;
import ejbs.facades.FrtTransporteAgendarFacade;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
//import javax.enterprise.context.ApplicationScoped;
import javax.faces.view.ViewScoped;

/**
 *
 * @author samuel
 */
@Named(value = "transporteAgendadosListarBean")
@ViewScoped
public class TransporteAgendadosListarBean implements Serializable
{
    @EJB
    private FrtTransporteAgendarFacade frtTransporteAgendarFacade;
    
    private List<FrtTransporteAgendar> listarFrtTransporteAgendar;
    
     private List<FrtTransporteAgendar> listarViagensLocal;

    /**
     * Creates a new instance of TransporteAgendadosListarBean
     */
    public TransporteAgendadosListarBean() 
    {
        
    }
    
    @PostConstruct
    public void init()
    {
        listarFrtTransporteAgendar = frtTransporteAgendarFacade.findAllOrderByNome();
        
        listarViagensLocal = frtTransporteAgendarFacade.findAllViagensLocal();
    }
    
    public List<FrtTransporteAgendar> listar() 
    {
        return frtTransporteAgendarFacade.findAll();
    }

    public List<FrtTransporteAgendar> getListarFrtTransporteAgendar() 
    {
        return listarFrtTransporteAgendar;
    }

    public void setListarFrtTransporteAgendar(List<FrtTransporteAgendar> listarFrtTransporteAgendar) 
    {
        this.listarFrtTransporteAgendar = listarFrtTransporteAgendar;
    }

    public List<FrtTransporteAgendar> getListarViagensLocal()
    {
        return listarViagensLocal;
    }

    public void setListarViagensLocal(List<FrtTransporteAgendar> listarViagensLocal)
    {
        this.listarViagensLocal = listarViagensLocal;
    }
    
    
    
    
    
}
