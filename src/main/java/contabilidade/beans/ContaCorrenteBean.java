/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contabilidade.beans;

import java.io.Serializable;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author majoao
 */
@Named(value = "contaCorrenteBean")
@ViewScoped
public class ContaCorrenteBean implements Serializable{

    /**
     * Creates a new instance of ContaCorrenteBean
     */
    public ContaCorrenteBean() {
    }
    
}
