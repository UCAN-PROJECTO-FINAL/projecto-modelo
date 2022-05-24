/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rh.beans;

import ejbs.entities.RhFuncao;
import ejbs.facades.RhFuncaoFacade;
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
@Named(value = "rhFuncaoListarBean")
@ViewScoped

public class RhFuncaoListarBean implements Serializable
{
    @EJB
    private RhFuncaoFacade rhFuncaoFacade;
    
    private List<RhFuncao> listar_RhFuncao;
    

    public RhFuncaoListarBean()
    {
        
    }
    
    @PostConstruct
    public void init()
    {
        listar_RhFuncao = rhFuncaoFacade.findAllFuncaoOrderByDescricao();
    }

    public List<RhFuncao> getListar_RhFuncao()
    {
        return listar_RhFuncao;
    }

    public void setListar_RhFuncao(List<RhFuncao> listar_RhFuncao)
    {
        this.listar_RhFuncao = listar_RhFuncao;
    }
    
    
    
    
    
    
    
    
}
