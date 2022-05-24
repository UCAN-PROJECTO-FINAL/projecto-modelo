/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frt.beans;

import ejbs.facades.FrtTransporteAtendimentoFacade;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import ejbs.entities.FrtTransporteAtendimento;
import java.util.List;
import javax.annotation.PostConstruct;

/**
 *
 * @author samuel
 */
@Named(value = "transporteAtendimentoListasBean")
@ViewScoped
public class TransporteAtendimentoListasBean implements Serializable
{

    @EJB
    private FrtTransporteAtendimentoFacade frtTransporteAtendimentoFacade;
    
    private FrtTransporteAtendimento FrtTransporteAtendimento;
    
    private List<FrtTransporteAtendimento> listarFrtTransporteAtendimento;
    
    

    /**
     * Creates a new instance of TransporteAtendimentoListasBean
     */
    public TransporteAtendimentoListasBean()
    {
        
    }
    
    
     
    @PostConstruct
    public void init()
    {
        
        listarFrtTransporteAtendimento = frtTransporteAtendimentoFacade.findAllTrnasporteEmAndamento();
        
      
    }

    public FrtTransporteAtendimento getFrtTransporteAtendimento()
    {
        return FrtTransporteAtendimento;
    }

    public void setFrtTransporteAtendimento(FrtTransporteAtendimento FrtTransporteAtendimento)
    {
        this.FrtTransporteAtendimento = FrtTransporteAtendimento;
    }

    public List<FrtTransporteAtendimento> getListarFrtTransporteAtendimento()
    {
        return listarFrtTransporteAtendimento;
    }

    public void setListarFrtTransporteAtendimento(List<FrtTransporteAtendimento> listarFrtTransporteAtendimento)
    {
        this.listarFrtTransporteAtendimento = listarFrtTransporteAtendimento;
    }
    
    
    
}
