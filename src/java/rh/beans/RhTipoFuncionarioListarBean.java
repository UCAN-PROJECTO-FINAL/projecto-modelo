/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rh.beans;

import ejbs.entities.RhTipoFuncionario;
import ejbs.facades.RhTipoFuncionarioFacade;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author smakambo
 */
@Named(value = "rhTipoFuncionarioListarBean")
@ViewScoped

public class RhTipoFuncionarioListarBean implements Serializable
{
    
    @EJB
    private RhTipoFuncionarioFacade rhTipoFuncionarioFacade;
    
    private List<RhTipoFuncionario> listar_RhTipoFuncionario;

    public RhTipoFuncionarioListarBean()
    {
        
    }
    
    @PostConstruct
    public void init()
    {
        listar_RhTipoFuncionario = rhTipoFuncionarioFacade.findAllTipoFuncionarioOrderByDescricao();
    }

    public List<RhTipoFuncionario> getListar_RhTipoFuncionario()
    {
        return listar_RhTipoFuncionario;
    }

    public void setListar_RhTipoFuncionario(List<RhTipoFuncionario> listar_RhTipoFuncionario)
    {
        this.listar_RhTipoFuncionario = listar_RhTipoFuncionario;
    }
    
    
    
    
}
