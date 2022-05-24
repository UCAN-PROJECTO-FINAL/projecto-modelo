/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rh.beans;

import ejbs.entities.RhNivelAcademico;
import ejbs.facades.RhNivelAcademicoFacade;
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
@Named(value = "rhNivelAcademicoListarBean")
@ViewScoped

public class RhNivelAcademicoListarBean implements Serializable
{
    
    @EJB
    private RhNivelAcademicoFacade rhNivelAcademicoFacade;
    
    private List<RhNivelAcademico> listar_RhNivelAcademico;

    public RhNivelAcademicoListarBean()
    {
        
    }
    
    
    
    @PostConstruct
    public void init()
    {
        listar_RhNivelAcademico = rhNivelAcademicoFacade.findAllFuncaoOrderByDescricao();
    }

    public List<RhNivelAcademico> getListar_RhNivelAcademico()
    {
        return listar_RhNivelAcademico;
    }

    public void setListar_RhNivelAcademico(List<RhNivelAcademico> listar_RhNivelAcademico)
    {
        this.listar_RhNivelAcademico = listar_RhNivelAcademico;
    }
    
    
    
    
    
}
