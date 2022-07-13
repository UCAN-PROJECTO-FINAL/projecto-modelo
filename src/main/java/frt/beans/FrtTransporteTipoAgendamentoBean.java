/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frt.beans;

import java.io.Serializable;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import ejbs.entities.FrtTransporteTipoAgendamento;
import ejbs.facades.FrtTransporteTipoAgendamentoFacade;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;

/**
 *
 * @author smakambo
 */
@Named(value = "transporteTipoAgendamentoBean")
@ViewScoped
public class FrtTransporteTipoAgendamentoBean implements Serializable
{
    
    @EJB
    private FrtTransporteTipoAgendamentoFacade frtTransporteTipoAgendamentoFacade;
    
    private List<FrtTransporteTipoAgendamento> listarTipoAgendamento;

    /**
     * Creates a new instance of TransporteTipoAgendamentoBean
     */
    public FrtTransporteTipoAgendamentoBean()
    {
        
    }
    
      
    @PostConstruct
    public void init()
    {
        listarTipoAgendamento = frtTransporteTipoAgendamentoFacade.findAllTipoAgendamentoOrderByDescricao();
    }

    public List<FrtTransporteTipoAgendamento> getListarTipoAgendamento()
    {
        return listarTipoAgendamento;
    }

    public void setListarTipoAgendamento(List<FrtTransporteTipoAgendamento> listarTipoAgendamento)
    {
        this.listarTipoAgendamento = listarTipoAgendamento;
    }
    
    
    
}
