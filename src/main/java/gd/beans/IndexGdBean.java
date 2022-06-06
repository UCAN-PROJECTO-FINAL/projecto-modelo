/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author david-salgueiro
 */
@Named(value = "indexBean")
@SessionScoped
public class IndexGdBean implements Serializable {

    /**
     * Creates a new instance of IndexBean
     */
    public IndexGdBean() {
    }
    
    
    
     public void inicio()
     {
        ;
        try
        {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            ExternalContext externalContext = facesContext.getExternalContext();
            externalContext.redirect("../../index.xhtml");
            
        } catch (IOException ex) {
            Logger.getLogger(IndexGdBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
 
    
}
