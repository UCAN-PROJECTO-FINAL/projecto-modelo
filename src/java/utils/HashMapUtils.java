/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.Enumeration;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author Aires Veloso
 */
public class HashMapUtils
{
    public static <T1, T2> ConcurrentHashMap<T1, T2> clone(ConcurrentHashMap<T1, T2> tabela)
    {
        ConcurrentHashMap<T1, T2> clone = new ConcurrentHashMap<>();
        Enumeration<T1> keys = tabela.keys();
        T2 object;
        T1 key;
        while (keys.hasMoreElements())
        {
            key = keys.nextElement();
            object = tabela.get(key);
            clone.put(key, object);
        }        
        return clone;
    }
    
    public static <T1, T2> ConcurrentHashMap<T1, List<T2>> cloneTabelaTypeList(ConcurrentHashMap<T1, List<T2>> tabela)
    {
        ConcurrentHashMap<T1, List<T2>> clone = new ConcurrentHashMap<>();
        Enumeration<T1> keys = tabela.keys();
        List<T2> object;
        T1 key;
        while (keys.hasMoreElements())
        {
            key = keys.nextElement();
            object = tabela.get(key);
            clone.put(key, object);
        }        
        return clone;
    }
}
