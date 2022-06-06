/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package seg.utils;

/**
 *
 * @author henriques
 */
public class UtilSegGeral
{
    private static char [] alfabeto = {'A','B','C','D','E','F','G',
                                       'H','I','J','K','L','M','N',
                                       'O','P','Q','R','S','T','U',
                                       'V','X','W','U','Z'
                                      }; 
    
    @SuppressWarnings("empty-statement")
    public UtilSegGeral()
    {
       
    }
    
    //Verifica se numa String contém um caracter Maíusculo
    public static boolean encontrarCaracterMaiuscula(String srt)
    {
        char strAux [] = srt.trim().toCharArray();
        for (int i=0; i< strAux.length; i++)
        {
           for (int j=0; j< alfabeto.length; j++)
           {
             if (strAux[i] == alfabeto[j])
                return true; 
             //System.err.println("str["+i+"]= "+strAux[i]+ "  alfabeto["+j+"]= "+alfabeto[j]);
           }
        }    
        return false;
    }
}
