 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patri.beans;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import org.omnifaces.cdi.ViewScoped;

/**
 *
 * @author mdnext
 */
@Named(value = "ptBemEditarBean")
@ViewScoped
public class PtBemEditar implements Serializable
{
    
    @PostConstruct
    private void init()
    {
        
         
    }
    
}
