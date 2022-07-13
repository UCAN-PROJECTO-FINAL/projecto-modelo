/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grl.beans;

import ejbs.entities.GrlSexo;
import ejbs.facades.GrlSexoFacade;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author smakambo
 */

@Named(value = "sexoListarBean")
@ViewScoped
public class SexoListarBean implements Serializable
{
    
    @EJB
    private GrlSexoFacade sexoFacade;
    
    private List<GrlSexo> listar_Sexo;

    public SexoListarBean()
    {
        
        
    }
    
    public void init()
    {
        listar_Sexo = sexoFacade.findAllSexoOrderByDescricao();
    }

    public List<GrlSexo> getListar_Sexo()
    {
        return listar_Sexo;
    }

    public void setListar_Sexo(List<GrlSexo> listar_Sexo)
    {
        this.listar_Sexo = listar_Sexo;
    }

    
    
     
}
