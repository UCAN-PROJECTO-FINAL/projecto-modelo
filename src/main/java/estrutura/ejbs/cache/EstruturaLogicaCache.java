/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estrutura.ejbs.cache;

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
import estrutura.utils.Defs;
import java.util.Collections;

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
    
    public List<EstruturaLogica> getSortListaSonsByPkEstruturaLogica(String pkEstruturaLogica)
    {
        EstruturaLogica indefinido = null;
        List<EstruturaLogica> resultList = new ArrayList();
        for (EstruturaLogica reg : this.hashTabela.values())
        {
            if (reg.getFkEstruturaLogica() != null) //System.err.println(""+pkEstruturaFisica+"------"+reg.getFkEstruturaFisica().getPkEstruturaFisica());
            {
                if (reg.getFkEstruturaLogica().getPkEstruturaLogica().equals(pkEstruturaLogica))
                {
                    if (reg.getDesignacao().equalsIgnoreCase(Defs.INDEFINIDO)
                            || reg.getDesignacao().equalsIgnoreCase(Defs.INDEFINIDA))
                    {
                        indefinido = reg;
                    } else
                    {
                        resultList.add(reg);
                    }

                }
            }
        }
        Collections.sort(resultList, (o1, o2) ->
        {
            EstruturaLogica estruturaLogica1 = (EstruturaLogica) o1,
                    estruturaLogica2 = (EstruturaLogica) o2;
            return estruturaLogica1.getDesignacao().compareToIgnoreCase(estruturaLogica2.getDesignacao());
        });
        if (indefinido != null)
        {
            resultList.add(indefinido);
        }
        return resultList;
    }

    public String getFirstPkEstruturaLogica(String pkEstruturaLogica)
    {
        String indefinido = null;
        List<String> resultList = new ArrayList();
        for (EstruturaLogica reg : this.hashTabela.values())
        {
            if (reg.getFkEstruturaLogica() != null)
            {
                if (reg.getFkEstruturaLogica().getPkEstruturaLogica().equals(pkEstruturaLogica))
                {
                    if (reg.getDesignacao().equalsIgnoreCase(Defs.INDEFINIDO)
                            || reg.getDesignacao().equalsIgnoreCase(Defs.INDEFINIDA))
                    {
                        indefinido = reg.toString();
                    } else
                    {
                        resultList.add(reg.getPkEstruturaLogica());
                    }

                }
            }
        }

        if (indefinido != null)
        {
            resultList.add(indefinido);
        }

        return resultList.get(0);
    }

}
