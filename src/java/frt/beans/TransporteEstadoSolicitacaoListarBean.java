/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frt.beans;

import java.io.Serializable;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import ejbs.entities.FrtTransporteSolicitacaoEstado;
import ejbs.facades.FrtTransporteSolicitacaoEstadoFacade;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;

/**
 *
 * @author smakambo
 */

@Named(value = "transporteEstadoSolicitacaoListarBean")
@ViewScoped
public class TransporteEstadoSolicitacaoListarBean implements Serializable
{
    @EJB
    private FrtTransporteSolicitacaoEstadoFacade frtTransporteSolicitacaoEstadoFacade;
    
    private List<FrtTransporteSolicitacaoEstado> listar;

    public TransporteEstadoSolicitacaoListarBean()
    {
        
    }
    
     
    @PostConstruct
    public void init()
    {
        listar = frtTransporteSolicitacaoEstadoFacade.findAllSolicitacaoEstadoOrderByDescricao();
    }

    public List<FrtTransporteSolicitacaoEstado> getListar()
    {
        return listar;
    }

    public void setListar(List<FrtTransporteSolicitacaoEstado> listar)
    {
        this.listar = listar;
    }
    
    
    
    
    
}
