/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seg.utils;

import ejbs.entities.SegFuncionalidade;
import java.util.ArrayList;
import java.util.List;
import utils.StringUtils;

/**
 *
 * @author aires
 */
public class SegFuncionalidadeFormsLista
{

    private SegFuncionalidade segFuncionalidade;
    private List<Integer> pkFormsLista;

    public SegFuncionalidadeFormsLista()
    {
        pkFormsLista = new ArrayList();
    }

    private int toInteger(String st)
    {
        return Integer.parseInt(st);
    }

    
    // Getters and Setters
    public SegFuncionalidade getSegFuncionalidade()
    {
        return segFuncionalidade;
    }

    public void setSegFuncionalidade(SegFuncionalidade segFuncionalidade)
    {
        this.segFuncionalidade = segFuncionalidade;
    }

    public List<Integer> getPkFormsLista()
    {
        return pkFormsLista;
    }

    public void setPkFormsLista(List<Integer> pkFormsLista)
    {
        this.pkFormsLista = pkFormsLista;
    }

    public void setPkFormsLista(String pkFormsListaString)
    {
//System.err.println("0: SegFuncionalidadeFormsLista.setPkFormsLista()\tpkFormsListaString:" +
//            pkFormsListaString);        
        if (StringUtils.isNull(pkFormsListaString))
        {
//System.err.println("1: SegFuncionalidadeFormsLista.setPkFormsLista()\tpkFormsListaString:" +
//            pkFormsListaString);            
            return;
        }
//System.err.println("2: SegFuncionalidadeFormsLista.setPkFormsLista()\tpkFormsListaString:" +
//            pkFormsListaString);
        SegFuncionalidade segFuncionalidadePai = this.segFuncionalidade.getFkSegFuncionalidadePai();
        int pkSegFuncionalidadePai = segFuncionalidadePai != null ? segFuncionalidadePai.getPkSegFuncionalidade() : 0;

        Integer pkRedirection;
        pkFormsListaString = pkFormsListaString.trim();
        if (pkFormsListaString.indexOf(';') == -1)
        {
            pkRedirection = toInteger(pkFormsListaString);
            if (pkSegFuncionalidadePai != pkRedirection)
            {
                pkFormsLista.add(pkRedirection);
            }
//System.err.println("3: SegFuncionalidadeFormsLista.setPkFormsLista()\tpkFormsListaString:" +
//            pkFormsListaString);            
            return;
        }

        String pkForms[] = pkFormsListaString.split(";");
        
        for (String pkForm : pkForms)
        {
            if (StringUtils.isNull(pkForm))
            {
                continue;
            }
            pkRedirection = toInteger(pkForm.trim());
            if (pkSegFuncionalidadePai != pkRedirection)
            {
                pkFormsLista.add(pkRedirection);
            }
        }
//System.err.println("4: SegFuncionalidadeFormsLista.setPkFormsLista()\tpkFormsListaString:" +
//            pkFormsListaString);        
    }

}
