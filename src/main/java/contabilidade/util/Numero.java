/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contabilidade.util;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author majoao
 */
public class Numero {
    
      private int codigo = 0;
    
    public Numero() {
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    
    
    public List<Numero> Numeros(){
        
    List<Numero> numeros = new ArrayList();
            
       for(int i = 1; i<=15; i++){
           
           Numero valor = new Numero();
           valor.setCodigo(i);
           numeros.add(valor);
           
           
       }
        
       return numeros;
    }
}
