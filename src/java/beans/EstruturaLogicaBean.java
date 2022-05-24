/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import ejbs.entities.EstruturaLogica;
import ejbs.facades.EstruturaLogicaFacade;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author samuel
 */
@Named(value = "estruturaLogicaBean")
@ViewScoped
public class EstruturaLogicaBean implements Serializable
{
    
    @EJB
    private EstruturaLogicaFacade EstruturaLogicaFacade;
    
    private List<EstruturaLogica> listaEstruturaLogica;

    /**
     * Creates a new instance of EstruturaLogicaBean
     */
    public EstruturaLogicaBean() 
    {
        
    }
    
    @PostConstruct
    public void init()
    {
        listaEstruturaLogica = EstruturaLogicaFacade.findAllOrderByNome();
    }

    public List<EstruturaLogica> getListaEstruturaLogica() {
        return listaEstruturaLogica;
    }

    public void setListaEstruturaLogica(List<EstruturaLogica> listaEstruturaLogica) {
        this.listaEstruturaLogica = listaEstruturaLogica;
    }
    
    
    
}
