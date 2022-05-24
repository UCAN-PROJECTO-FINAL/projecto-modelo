/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rh.beans;

import ejbs.entities.RhEspecialidade;
import ejbs.facades.RhEspecialidadeFacade;
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

@Named(value = "rhEspecialidadeListarBean")
@ViewScoped

public class RhEspecialidadeListarBean implements Serializable
{
    
    
    @EJB
    private RhEspecialidadeFacade rhEspecialidadeFacade;
    
    private List<RhEspecialidade> listar_RhEspecialidade;

    public RhEspecialidadeListarBean()
    {
        
    }
    
    @PostConstruct
    public void init()
    {
        listar_RhEspecialidade = rhEspecialidadeFacade.findAllEspecialidadeOrderByDescricao();
    }

    public List<RhEspecialidade> getListar_RhEspecialidade()
    {
        return listar_RhEspecialidade;
    }

    public void setListar_RhEspecialidade(List<RhEspecialidade> listar_RhEspecialidade)
    {
        this.listar_RhEspecialidade = listar_RhEspecialidade;
    }
    
    
   
    
    
}
