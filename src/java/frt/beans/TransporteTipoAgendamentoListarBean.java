/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frt.beans;

import java.io.Serializable;
import javax.inject.Named;
//import org.omnifaces.cdi.ViewScoped;
import ejbs.entities.FrtTransporteTipoAgendamento;
import ejbs.facades.FrtTransporteTipoAgendamentoFacade;        
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
//import javax.enterprise.context.SessionScoped;
import javax.faces.view.ViewScoped;

/**
 *
 * @author samuel
 */
@Named(value = "transporteTipoAgendamentoListarBean")
@ViewScoped
public class TransporteTipoAgendamentoListarBean implements Serializable
{
    
    @EJB
    private FrtTransporteTipoAgendamentoFacade frTipoAgendamentoFacade;
    
    private List<FrtTransporteTipoAgendamento> listaTipoAgendamento;
    

    /**
     * Creates a new instance of TipoAgendamentoListarBean
     */
    public TransporteTipoAgendamentoListarBean() 
    {
        
    }
    
    
    @PostConstruct
    public void init()
    {
        listaTipoAgendamento = frTipoAgendamentoFacade.findAllTipoAgendamentoOrderByDescricao();
    }
    
    public List<FrtTransporteTipoAgendamento> getListaTipoAgendamento() {
        return listaTipoAgendamento;
    }

    public void setListaTipoAgendamento(List<FrtTransporteTipoAgendamento> listaTipoAgendamento) {
        this.listaTipoAgendamento = listaTipoAgendamento;
    }
    
}
