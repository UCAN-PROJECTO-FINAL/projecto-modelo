/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contabilidade.util;

import ejbs.entities.CtAnoEconomico;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author majoao
 */
public class Utilitarios {
    
    public static int getPkExercicioActual(List<CtAnoEconomico> listYear)
    {
        int anoActual = Calendar.getInstance().get(Calendar.YEAR);
        for (CtAnoEconomico ctAnoEconomico : listYear) {
            if (ctAnoEconomico.getAnoEconomico() == anoActual) return ctAnoEconomico.getPkAnoEconomico();
        }
        
        return 0;
    }
    
    
}
