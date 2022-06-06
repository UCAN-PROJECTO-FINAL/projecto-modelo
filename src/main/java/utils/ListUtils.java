/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.DefaultListModel;

/**
 *
 * @author aires
 */
public class ListUtils
{

    public static <T> List<T> reverse(List<T> lista)
    {        
        List<T> listaTmp = new ArrayList<>();
        int sz = lista.size();
        for (int i = sz - 1; i >= 0; i--)
        {
            listaTmp.add(lista.get(i));
        }
        return listaTmp;
    }

    public static <T> String toString(List<T> lista)
    {
        String msg = "{ ";
        boolean first = true;

        for (T numero : lista)
        {
            msg += (first ? "" : ", ");
            msg += numero;
            first = false;
        }
        msg += " }";
        return msg;
    }
/*
    public static <T> String toString(List<T> lista, ToStringFacade<T> toStringFacade)
    {
        String msg = "{ ";
        boolean first = true;

        for (T obj : lista)
        {
            msg += (first ? "" : ", ");
            msg += toStringFacade.toString(obj);
            first = false;
        }
        msg += " }";
        return msg;
    }
*/
    public static <T> List<T> addAll(List<T> listaSrc, List<T> lista)
    {
        for (T obj : lista)
        {
            listaSrc.add(obj);
        }
        return listaSrc;
    }

    public static boolean includes(List<Integer> list, int value)
    {
        for (Integer item : list)
        {
            if (value == item.intValue())
            {
                return true;
            }
        }
        return false;
    }
    
    public static boolean includes(List<String> list, String value)
    {
        for (String item : list)
        {
            if (value.equalsIgnoreCase(item))
            {
                return true;
            }
        }
        return false;
    }

    public static Associacao<Integer, Boolean> sourceEventOfDocRazoesInviabilidadeList(
        List<Integer> prevPkDocRazoesInviabilidadeList,
        List<Integer> pkDocRazoesInviabilidadeList)
    {
        for (Integer pkRazoesInviabilidade : prevPkDocRazoesInviabilidadeList)
        {
            if (!includes(pkDocRazoesInviabilidadeList, pkRazoesInviabilidade))
            {
                return new Associacao(pkRazoesInviabilidade, Boolean.FALSE);
            }
        }
        for (Integer pkRazoesInviabilidade : pkDocRazoesInviabilidadeList)
        {
            if (!includes(prevPkDocRazoesInviabilidadeList, pkRazoesInviabilidade))
            {
                return new Associacao(pkRazoesInviabilidade, Boolean.TRUE);
            }
        }
        return null;
    }

    public static DefaultListModel<String> generateDefaultListModel(List<String> list)
    {
        DefaultListModel<String> defaultListModel = new DefaultListModel();
        for (String st : list)
        {
            defaultListModel.addElement(st);
        }
        return defaultListModel;
    }

    public static Vector<String> remove(Vector<String> lista, String item)
    {
        Vector<String> lista2 = new Vector();

        for (String palavra : lista)
        {
            if (!palavra.equals(item))
            {
                lista2.add(palavra);
            }
        }
        return lista2;
    }

    public static List<Integer> parseInts(String st)
    {
        List<Integer> list = new ArrayList();
        String numerosSt[] = st.trim().split(",");
        int nl = numerosSt.length;
        int numero;
        for (int i = 0; i < nl; i++)
        {
            try
            {
                list.add(Integer.parseInt(numerosSt[i].trim()));
            }
            catch (NumberFormatException e)
            {
                return null;
            }
        }
        return list;
    }

}
