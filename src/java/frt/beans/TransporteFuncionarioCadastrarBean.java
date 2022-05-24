/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frt.beans;

import java.io.Serializable;
import java.util.Date;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author smakambo
 */
@Named(value = "transporteFuncionarioCadastrarBean")
@ViewScoped
public class TransporteFuncionarioCadastrarBean implements Serializable
{

    /**
     * Creates a new instance of TransporteFuncionarioCadastrarBean
     */
    public TransporteFuncionarioCadastrarBean()
    {
        
    }
    
    public Date dataSystem() 
    {
        Date Data;
        Data = new Date();
        return Data;
    }
    
}
