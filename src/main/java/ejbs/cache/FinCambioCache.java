/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.cache;

import ejbs.entities.FinCambio;
import ejbs.facades.FinCambioFacade;
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
 * @author Manuel João
 */
@Named
@ApplicationScoped
public class FinCambioCache implements Serializable
{

    @EJB
    private FinCambioFacade FinCambioFacade;
    private List<FinCambio> listFinCambio;
    private HashMap<Integer, FinCambio> hashTabela;

    /**
     * Creates a new instance of PaisBean
     */
    public FinCambioCache()
    {
    }

    /**
     *
     */
    @PostConstruct
    public void init()
    {
        listFinCambio = FinCambioFacade.findAll();
        hashTabela = new HashMap();
        int  pkCambio;
        for (FinCambio reg : listFinCambio)
        {
            pkCambio = reg.getPkCambio();

            hashTabela.put(pkCambio, reg);
        }
    }
    
    public FinCambio findFinCambioRoot()
    {
        //System.err.println(""+listaFinCambio);
        return hashTabela.get(0);
        //return listaFinCambio.get(0);
    }

    public FinCambio findFinCambio(Integer pkCambio)
    {
        return hashTabela.get(pkCambio);
    }

    public List<FinCambio> getListaFinCambios()
    {
        return listFinCambio;
    }

//    public List<FinCambio> getListaPaisFinCambios()
//    {
//        List<FinCambio> resultList = new ArrayList();
//        for (FinCambio reg : this.hashTabela.values())
//        {
//            if (reg.getFkFinCambio() == null)
//            {
//                resultList.add(reg);
//            }
//        }
//
//        return resultList;
//    }
    
    public int getFinCambioLevel()
    {
        int lastId = 0;
        for (FinCambio reg : this.hashTabela.values())
        {
            if (reg.getPkCambio()> lastId)
            {
                lastId = reg.getPkCambio();
            }
        }
        
//        lastId = (lastId.trim()).replace(".","");
        //System.err.println("´´´´´´´´´´´´´´´´"+lastId.length());
        return lastId;
        //return 3;
    }
    
    public int getFinCambioID()
    {
        return FinCambioFacade.count() + 1;
    }

    public List<FinCambio> getListaSonsByPkFinCambio(int pkFinCambio)
    {

        List<FinCambio> resultList = new ArrayList();
        for (FinCambio reg : this.hashTabela.values())
        {
            if (reg.getPkCambio()!= null)
            //System.err.println(""+pkFinCambio+"------"+reg.getFkFinCambio().getPkFinCambio());
            {
                if (reg.getPkCambio().equals(pkFinCambio))
                {
                    resultList.add(reg);
                }
            }
        }

        return resultList;
    }
    
//    public List findAllIndefinido()
//    {
//        List<FinCambio> resultList = new ArrayList();
//        for (FinCambio reg : listaFinCambio)
//        {
//            if (reg.getDesignacao().contains("Indefinido") && ! reg.getPkFinCambio().equals("0"))
//            {
//                    resultList.add(reg);
//            }
//        }
//        return resultList;
//    }

    public void create(FinCambio reg)
    {
        FinCambioFacade.create(reg);
    }

    public void edit(FinCambio reg)
    {
        FinCambioFacade.edit(reg);
    }

    public void remove(FinCambio reg)
    {
        FinCambioFacade.remove(reg);
    }

}
