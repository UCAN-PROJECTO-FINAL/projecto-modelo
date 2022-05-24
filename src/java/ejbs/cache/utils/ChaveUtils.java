/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.cache.utils;

import java.util.List;

/**
 *
 * @author Aires Veloso
 */
public class ChaveUtils
{
    
    protected static String geraChave(String arg1, int arg2)
    {
        return geraChave(arg1, "" + arg2);
    }

    protected static String geraChave(String arg1, int arg2, int arg3)
    {
        return geraChave(arg1, "" + arg2, "" + arg3);
    }

    protected static String geraChave(String arg1, String arg2, int arg3)
    {
        return geraChave(arg1, "" + arg2, "" + arg3);
    }

    protected static String geraChave(String arg1, int arg2, String arg3)
    {
        return geraChave(arg1, "" + arg2, arg3);
    }

    protected static String geraChave(String arg1, boolean arg2)
    {
        return geraChave(arg1, "" + arg2);
    }

    protected static String geraChave(String arg1, Integer arg2, List<String> args)
    {
        String chave = arg1.toLowerCase();
        chave += "$";
        chave += arg2;
        for (String arg : args)
        {
            chave += "$";
            chave += arg.trim().toLowerCase();
        }
        return chave;
    }

    protected static String geraChave(String... args)
    {
        String chave = "";
        boolean first = true;
        for (String arg : args)
        {
            if (first)
            {
                first = false;
            }
            else
            {
                chave += "$";
            }
            chave += arg.trim().toLowerCase();
        }
        return chave;
    }


}
