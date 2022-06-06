/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seg.beans;

import ejbs.entities.SegTipoConta;
import ejbs.facades.SegTipoContaFacade;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

/**
 *
 * @author benvindo
 */
@Named(value = "tipoContaBean")
@ApplicationScoped
public class TipoContaBean
{
    
    @EJB
    private SegTipoContaFacade segTipoContaFacade; 
    private List<SegTipoConta> lista;

    /**
     * Creates a new instance of TipoContaBean
     */
    public TipoContaBean()
    {
        lista = new ArrayList<>();
    }
    
    @PostConstruct
    public void init()
    {
        lista = segTipoContaFacade.findAll();
    }

    public List<SegTipoConta> getLista()
    {
        return lista;
    }

    public void setLista(List<SegTipoConta> lista)
    {
        this.lista = lista;
    }
    
    
    
}
