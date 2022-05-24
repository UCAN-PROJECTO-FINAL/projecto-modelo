/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grl.beans;


import ejbs.entities.GrlEstadoCivil;
import ejbs.facades.GrlEstadoCivilFacade;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author smakambo
 */

@Named(value = "estadoCivilListarBean")
@ViewScoped
public class EstadoCivilListarBean implements Serializable
{
    
    @EJB
    private GrlEstadoCivilFacade estadoCivilFacade;
    
    private List<GrlEstadoCivil> listar_EstadoCivil;

    public EstadoCivilListarBean()
    {
        
    }
    
//    public void init()
//    {
//        listar_EstadoCivil = estadoCivilFacade.findAllEstadoCivilOrderByDescricao();
//    }

    public List<GrlEstadoCivil> getListar_EstadoCivil()
    {
        return listar_EstadoCivil;
    }

    public void setListar_EstadoCivil(List<GrlEstadoCivil> listar_EstadoCivil)
    {
        this.listar_EstadoCivil = listar_EstadoCivil;
    }

   
    
    
    
}
