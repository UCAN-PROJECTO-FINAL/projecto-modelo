/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frt.beans;

import java.io.Serializable;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import ejbs.entities.PtTransporte;
import ejbs.facades.PtTransporteFacade;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.event.ActionEvent;


/**
 *
 * @author samuel
 */
@Named(value = "transporteAbastecimentoCadastrarBean")
@ViewScoped
public class TransporteAbastecimentoCadastrarBean implements Serializable
{
    @EJB
    private PtTransporteFacade ptTransporteFacade;
    
    private PtTransporte  selectedPtTransporte;

    /**
     * Creates a new instance of TransporteAbastecimentoCadastrarBean
     */
    public TransporteAbastecimentoCadastrarBean() 
    {
        
    }
    
    @PostConstruct
    public void init()
    {

        selectedPtTransporte = new PtTransporte();
       
    }
    
    
    public void adicionar(ActionEvent event)
    {

        int codigo = Integer.parseInt(event.getComponent().getAttributes().get("codigoTransporte").toString());
        System.err.println("Entrou seleccionarTransporte: " + codigo);
        this.selectedPtTransporte = ptTransporteFacade.find(codigo);
          
    }
    
    public Date dataSystem() 
    {
        Date Data;
        Data = new Date();
        return Data;
    }
    
    
    // Gets e Sets 

    public PtTransporte getSelectedPtTransporte() {
        return selectedPtTransporte;
    }

    public void setSelectedPtTransporte(PtTransporte selectedPtTransporte) {
        this.selectedPtTransporte = selectedPtTransporte;
    }
    
    
    
}
