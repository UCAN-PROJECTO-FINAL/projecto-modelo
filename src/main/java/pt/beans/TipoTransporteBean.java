/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.beans;

import ejbs.entities.PtTransporteTipo;
import ejbs.facades.PtTransporteTipoFacade;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author samuel
 */
@Named(value = "tipoTransporteBean")
@ViewScoped
public class TipoTransporteBean implements Serializable
{
    
     @EJB
    private PtTransporteTipoFacade ptTransporteTipoFacade;
    
    private List<PtTransporteTipo> listaPtTransporteTipo;

    /**
     * Creates a new instance of TipoTransporteBean
     */
    public TipoTransporteBean() 
    {
        
    }
    
     
    @PostConstruct
    public void init()
    {
        listaPtTransporteTipo = ptTransporteTipoFacade.findAllTipoTransporteOrderByDescricao();
    }

    public List<PtTransporteTipo> getListaPtTransporteTipo() {
        return listaPtTransporteTipo;
    }

    public void setListaPtTransporteTipo(List<PtTransporteTipo> listaPtTransporteTipo) {
        this.listaPtTransporteTipo = listaPtTransporteTipo;
    }
    
    
    
}
