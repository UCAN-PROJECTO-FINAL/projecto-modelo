/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frt.beans;

import ejbs.entities.PtTransporte;
import ejbs.facades.PtTransporteFacade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
//import javax.enterprise.context.SessionScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
//import org.omnifaces.cdi.ViewScoped;

/**
 *
 * @author samuel
 */
@Named(value = "transporteListarBean")

@ViewScoped
public class TransporteListarBean implements Serializable
{
    
    
    @EJB
    private PtTransporteFacade ptTransporteFacade;
    
    private List<PtTransporte> listaTransporte;

    /**
     * Creates a new instance of TransporteListarBean
     */
    public TransporteListarBean() 
    {
        
    }
    
    
    @PostConstruct
    public void init()
    {
        listaTransporte = ptTransporteFacade.findAllTransporteOrderByMatricula();
    }
    
    
    public List<PtTransporte> listarItens(int id) 
    {
        System.out.println("CODIGO DOC: " + id);
        List<PtTransporte> lista = (List<PtTransporte>) ptTransporteFacade.findTransporteByPk(id);
        System.out.println("LISTA: " + lista);
        if (lista != null && !lista.isEmpty()) {
            for (int i = 0; i < lista.size(); i++) {

                PtTransporte ptTransporte = lista.get(i);

                if (ptTransporte.getLotacao() == 0) {
                    lista.remove(ptTransporte);
                    i--;
                }
            }
            return lista;
        }

        return new ArrayList<>();

    }
    
    public List<PtTransporte> listar() 
    {
        return ptTransporteFacade.findAll();
    }

    public List<PtTransporte> getListaTransporte() 
    {
        return listaTransporte;
    }

    public void setListaTransporte(List<PtTransporte> listaTransporte) 
    {
        this.listaTransporte = listaTransporte;
    }
    
}
