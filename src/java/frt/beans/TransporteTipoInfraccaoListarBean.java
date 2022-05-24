/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frt.beans;

import java.io.Serializable;
import ejbs.entities.FrtTransporteTipoInfraccao;
import ejbs.facades.FrtTransporteTipoInfraccaoFacade; 
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author smakambo
 */
@Named(value = "transporteTipoInfraccaoListarBean")
@ViewScoped
public class TransporteTipoInfraccaoListarBean implements Serializable
{
    @EJB
    private FrtTransporteTipoInfraccaoFacade frtTransporteTipoInfraccaoFacade;
    
    private List<FrtTransporteTipoInfraccao> listar;

    public TransporteTipoInfraccaoListarBean()
    {
        
    }
    
     @PostConstruct
    public void init()
    {
        listar = frtTransporteTipoInfraccaoFacade.findAllTipoInfraccaoOrderByDescricao();
    }

    public List<FrtTransporteTipoInfraccao> getListar()
    {
        return listar;
    }

    public void setListar(List<FrtTransporteTipoInfraccao> listar)
    {
        this.listar = listar;
    }
    
    
    
    
    
}
