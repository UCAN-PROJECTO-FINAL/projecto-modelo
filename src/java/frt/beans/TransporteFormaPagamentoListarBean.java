/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frt.beans;

import java.io.Serializable;
import ejbs.entities.FrtTransporteFormaPagamento;
import ejbs.facades.FrtTransporteFormaPagamentoFacade; 
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author smakambo
 */
@Named(value = "transporteFormaPagamentoListarBean")
@ViewScoped
public class TransporteFormaPagamentoListarBean implements Serializable
{
    @EJB
    private FrtTransporteFormaPagamentoFacade frtTransporteFormaPagamentoFacade;
    
    private List<FrtTransporteFormaPagamento> listar;

    public TransporteFormaPagamentoListarBean()
    {
        
    }
    
    @PostConstruct
    public void init()
    {
        listar = frtTransporteFormaPagamentoFacade.findAllFormaPagamentoOrderByDescricao();
    }

    public List<FrtTransporteFormaPagamento> getListar()
    {
        return listar;
    }

    public void setListar(List<FrtTransporteFormaPagamento> listar)
    {
        this.listar = listar;
    }
    
    
    
    
    
}
