/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frt.beans;

import java.io.Serializable;
import javax.faces.view.ViewScoped;
//import javax.faces.bean.ViewScoped;
import javax.inject.Named;
import ejbs.entities.FrtTransporteTipoCombustivel;
import ejbs.facades.FrtTransporteTipoCombustivelFacade;  
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;

/**
 *
 * @author smakambo
 */
@Named(value = "transporteTipoCombustivelListarBean")
@ViewScoped
public class TransporteTipoCombustivelListarBean implements Serializable
{
    
    @EJB
    private FrtTransporteTipoCombustivelFacade frtTransporteTipoCombustivelFacade;
    
    private List<FrtTransporteTipoCombustivel> listar;

    public TransporteTipoCombustivelListarBean()
    {
        
    }
    
    @PostConstruct
    public void init()
    {
        listar = frtTransporteTipoCombustivelFacade.findAllTipoCombustiveOrderByDescricao();
    }

    public List<FrtTransporteTipoCombustivel> getListar()
    {
        return listar;
    }

    public void setListar(List<FrtTransporteTipoCombustivel> listar)
    {
        this.listar = listar;
    }
    
    
    
    
    
}
