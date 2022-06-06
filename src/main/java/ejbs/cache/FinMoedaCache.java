/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.cache;

import ejbs.entities.FinMoeda;
import ejbs.facades.FinMoedaFacade;
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
public class FinMoedaCache implements Serializable
{

    @EJB
    private FinMoedaFacade FinMoedaFacade;
    private List<FinMoeda> listFinMoeda;
    private HashMap<Integer, FinMoeda> hashTabela;

    /**
     * Creates a new instance of PaisBean
     */
    public FinMoedaCache()
    {
    }

    /**
     *
     */
    @PostConstruct
    public void init()
    {
        listFinMoeda = FinMoedaFacade.findAll();
        hashTabela = new HashMap();
        int  pkMoeda;
        for (FinMoeda reg : listFinMoeda)
        {
            pkMoeda = reg.getPkMoeda();
            //System.out.println("pkMoedaCache: "+pkMoeda);
            hashTabela.put(pkMoeda, reg);
        }
    }
    
    public FinMoeda findFinMoedaRoot()
    {
        //System.err.println(""+listaFinMoeda);
        return hashTabela.get(0);
        //return listaFinMoeda.get(0);
    }

    public FinMoeda findFinMoeda(Integer pkMoeda)
    {
        return hashTabela.get(pkMoeda);
    }

    public List<FinMoeda> getListaFinMoedas()
    {
        return listFinMoeda;
    }

//    public List<FinMoeda> getListaPaisFinMoedas()
//    {
//        List<FinMoeda> resultList = new ArrayList();
//        for (FinMoeda reg : this.hashTabela.values())
//        {
//            if (reg.getFkFinMoeda() == null)
//            {
//                resultList.add(reg);
//            }
//        }
//
//        return resultList;
//    }
    
    public int getFinMoedaLevel()
    {
        int lastId = 0;
        for (FinMoeda reg : this.hashTabela.values())
        {
            if (reg.getPkMoeda()> lastId)
            {
                lastId = reg.getPkMoeda();
            }
        }
        
//        lastId = (lastId.trim()).replace(".","");
        //System.err.println("´´´´´´´´´´´´´´´´"+lastId.length());
        return lastId;
        //return 3;
    }
    
    public int getFinMoedaID()
    {
        return FinMoedaFacade.count() + 1;
    }

    public List<FinMoeda> getListaSonsByPkFinMoeda(int pkFinMoeda)
    {

        List<FinMoeda> resultList = new ArrayList();
        for (FinMoeda reg : this.hashTabela.values())
        {
            if (reg.getPkMoeda()!= null)
            //System.err.println(""+pkFinMoeda+"------"+reg.getFkFinMoeda().getPkFinMoeda());
            {
                if (reg.getPkMoeda().equals(pkFinMoeda))
                {
                    resultList.add(reg);
                }
            }
        }

        return resultList;
    }
    
//    public List findAllIndefinido()
//    {
//        List<FinMoeda> resultList = new ArrayList();
//        for (FinMoeda reg : listaFinMoeda)
//        {
//            if (reg.getDesignacao().contains("Indefinido") && ! reg.getPkFinMoeda().equals("0"))
//            {
//                    resultList.add(reg);
//            }
//        }
//        return resultList;
//    }

    public void create(FinMoeda reg)
    {
        FinMoedaFacade.create(reg);
    }

    public void edit(FinMoeda reg)
    {
        FinMoedaFacade.edit(reg);
    }

    public void remove(FinMoeda reg)
    {
        FinMoedaFacade.remove(reg);
    }

}
