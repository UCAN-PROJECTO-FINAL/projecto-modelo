/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.cache;

import ejbs.entities.EstruturaLogica;
import ejbs.facades.EstruturaLogicaFacade;
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
public class EstruturaLogicaCache implements Serializable
{

    @EJB
    private EstruturaLogicaFacade estruturaLogicaFacade;
    private List<EstruturaLogica> listaEstruturaLogica;
    private HashMap<String, EstruturaLogica> hashTabela;

    /**
     * Creates a new instance of PaisBean
     */
    public EstruturaLogicaCache()
    {
    }

    /**
     *
     */
    @PostConstruct
    public void init()
    {
        listaEstruturaLogica = estruturaLogicaFacade.findAllOrderByNome();
        hashTabela = new HashMap();
        String pkEstruturaLogica;
        for (EstruturaLogica reg : listaEstruturaLogica)
        {
            pkEstruturaLogica = reg.getPkEstruturaLogica();

            hashTabela.put(pkEstruturaLogica, reg);
        }
        //listaEstruturaFisica = localidadeFacade.findAll();
    }

    public void create(EstruturaLogica reg)
    {
        estruturaLogicaFacade.create(reg);
    }

    public void edit(EstruturaLogica reg)
    {
        estruturaLogicaFacade.edit(reg);
    }

    public void remove(EstruturaLogica reg)
    {
        estruturaLogicaFacade.remove(reg);
    }

    public List<EstruturaLogica> getListaEstruturaLogicas()
    {
        return listaEstruturaLogica;
    }

    public EstruturaLogica findEstruturaLogica(String pkEstruturaLogica)
    {
        return hashTabela.get(pkEstruturaLogica);
    }

    public EstruturaLogica findEstruturaLogicaRoot()
    {
        //System.err.println(""+listaEstruturaLogica);
        return hashTabela.get("0");
        
        //return listaEstruturaLogica.get(0);
    }
    
    public List<EstruturaLogica> getListaPaisEstruturaLogica()
    {
        List<EstruturaLogica> resultList = new ArrayList();
        for (EstruturaLogica reg : this.hashTabela.values())
        {
            if (reg.getFkEstruturaLogica() == null)
            {
                resultList.add(reg);
            }
        }

        return resultList;
    }
    
    public int getEstruturaLogicaLevel()
    {
        String lastId = "";
        for (EstruturaLogica reg : this.hashTabela.values())
        {
            if (reg.getPkEstruturaLogica().length() > lastId.length())
            {
                lastId = reg.getPkEstruturaLogica();
            }
        }
        lastId = (lastId.trim()).replace(".","");
        //System.err.println(""+lastId.length());
        return lastId.length();
        //return 3;
    }

    public List<EstruturaLogica> getListaSonsByPkEstruturaLogica(String pkEstruturaLogica)
    {
        List<EstruturaLogica> resultList = new ArrayList();
        for (EstruturaLogica reg : this.hashTabela.values())
        {
            if (reg.getFkEstruturaLogica() != null)
            {
                if (reg.getFkEstruturaLogica().getPkEstruturaLogica().equals(pkEstruturaLogica))
                {
                    resultList.add(reg);
                }
            }
        }

        return resultList;
    }

}
