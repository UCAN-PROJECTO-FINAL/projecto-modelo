/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frt.beans;

import java.io.Serializable;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import ejbs.entities.FrtTransporteBombaCombustivel;
import ejbs.facades.FrtTransporteBombaCombustivelFacade;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;

/**
 *
 * @author smakambo
 */

@Named(value = "transporteBombaCombustivelListarBean")
@ViewScoped
public class TransporteBombaCombustivelListarBean implements Serializable
{
    
    @EJB
    private FrtTransporteBombaCombustivelFacade frtTransporteBombaCombustivelFacade;
    
      private List<FrtTransporteBombaCombustivel> listar;

    public TransporteBombaCombustivelListarBean()
    {
        
    }
    
    
     @PostConstruct
    public void init()
    {
        listar = frtTransporteBombaCombustivelFacade.findAllBombaCombustivelOrderByDescricao();
    }

    public List<FrtTransporteBombaCombustivel> getListar()
    {
        return listar;
    }

    public void setListar(List<FrtTransporteBombaCombustivel> listar)
    {
        this.listar = listar;
    }
    
    
    
    
    
    
    
    
    
    
    
}
