/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frt.beans;

import ejbs.entities.PtTransporte;
import ejbs.facades.PtTransporteFacade;
import java.io.Serializable;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author samuel
 */
@Named(value = "transporteMultasCadastrarBean")
@ViewScoped
public class TransporteMultasCadastrar implements Serializable
{
    @EJB
    private PtTransporteFacade ptTransporteFacade;
    
    private PtTransporte  selectedPtTransporte;

    /**
     * Creates a new instance of TransporteMultasCadastrar
     */
    public TransporteMultasCadastrar() 
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

    public PtTransporte getSelectedPtTransporte() {
        return selectedPtTransporte;
    }

    public void setSelectedPtTransporte(PtTransporte selectedPtTransporte) {
        this.selectedPtTransporte = selectedPtTransporte;
    }
    
    
    
}
