/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.cache;

import ejbs.entities.EstruturaFisica;
import ejbs.facades.EstruturaFisicaFacade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

/**
 *
 * @author KiamiSoft_ACosta
 */
@Named
@ApplicationScoped
public class EstruturaFisicaCache implements Serializable
{

    @EJB
    private EstruturaFisicaFacade estruturaFisicaFacade;
    private List<EstruturaFisica> listaEstruturaFisica;
    private HashMap<String, EstruturaFisica> hashTabela;

    /**
     * Creates a new instance of PaisBean
     */
    public EstruturaFisicaCache()
    {
    }

    /**
     *
     */
    @PostConstruct
    public void init()
    {
        listaEstruturaFisica = estruturaFisicaFacade.findAllOrderByDesignacao();
        hashTabela = new HashMap();
        String pkEstruturaFisica;
        for (EstruturaFisica reg : listaEstruturaFisica)
        {
            pkEstruturaFisica = reg.getPkEstruturaFisica();

            hashTabela.put(pkEstruturaFisica, reg);
        }
    }
    
    public EstruturaFisica findEstruturaFisicaRoot()
    {
        //System.err.println(""+listaEstruturaFisica);
        return hashTabela.get("0");
        //return listaEstruturaFisica.get(0);
    }

    public EstruturaFisica findEstruturaFisica(String pkEstruturaFisica)
    {
        return hashTabela.get(pkEstruturaFisica);
    }

    public List<EstruturaFisica> getListaEstruturaFisicas()
    {
        return listaEstruturaFisica;
    }

    public List<EstruturaFisica> getListaPaisEstruturaFisicas()
    {
        List<EstruturaFisica> resultList = new ArrayList();
        for (EstruturaFisica reg : this.hashTabela.values())
        {
            if (reg.getFkEstruturaFisica() == null)
            {
                resultList.add(reg);
            }
        }

        return resultList;
    }
    
    public int getEstruturaFisicaLevel()
    {
        String lastId = "";
        for (EstruturaFisica reg : this.hashTabela.values())
        {
            if (reg.getPkEstruturaFisica().length() > lastId.length())
            {
                lastId = reg.getPkEstruturaFisica();
            }
        }
        
        lastId = (lastId.trim()).replace(".","");
        //System.err.println("´´´´´´´´´´´´´´´´"+lastId.length());
        return lastId.length();
        //return 3;
    }

    public List<EstruturaFisica> getListaSonsByPkEstruturaFisica(String pkEstruturaFisica)
    {

        List<EstruturaFisica> resultList = new ArrayList();
        for (EstruturaFisica reg : this.hashTabela.values())
        {
            if (reg.getFkEstruturaFisica() != null)
            //System.err.println(""+pkEstruturaFisica+"------"+reg.getFkEstruturaFisica().getPkEstruturaFisica());
            {
                if (reg.getFkEstruturaFisica().getPkEstruturaFisica().equals(pkEstruturaFisica))
                {
                    resultList.add(reg);
                }
            }
        }

        return resultList;
    }
    
    public List findAllIndefinido()
    {
        List<EstruturaFisica> resultList = new ArrayList();
        for (EstruturaFisica reg : listaEstruturaFisica)
        {
            if (reg.getDesignacao().contains("Indefinido") && ! reg.getPkEstruturaFisica().equals("0"))
            {
                    resultList.add(reg);
            }
        }
        return resultList;
    }

    public void create(EstruturaFisica reg)
    {
        estruturaFisicaFacade.create(reg);
    }

    public void edit(EstruturaFisica reg)
    {
        estruturaFisicaFacade.edit(reg);
    }

    public void remove(EstruturaFisica reg)
    {
        estruturaFisicaFacade.remove(reg);
    }

}
