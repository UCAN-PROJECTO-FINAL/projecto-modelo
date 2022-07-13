/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frt.beans;

import java.io.Serializable;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import ejbs.entities.RhFuncionario;
import ejbs.facades.RhFuncionarioFacade;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;

/**
 *
 * @author smakambo
 */
@Named(value = "frtTransporteFuncionarioListarBean")
@ViewScoped
public class FrtTransporteFuncionarioListarBean implements Serializable
{
    
    @EJB
    private RhFuncionarioFacade rhFuncionarioFacade;
    
    private List<RhFuncionario> listaFuncionarios;

    /**
     * Creates a new instance of TransporteFuncionarioListarBean
     */
    public FrtTransporteFuncionarioListarBean()
    {
        
    }
    
     @PostConstruct
    public void init()
    {
        listaFuncionarios = rhFuncionarioFacade.findAllFuncionarios();
    }

    public List<RhFuncionario> getListaFuncionarios()
    {
        return listaFuncionarios;
    }

    public void setListaFuncionarios(List<RhFuncionario> listaFuncionarios)
    {
        this.listaFuncionarios = listaFuncionarios;
    }
    
    
    
}
