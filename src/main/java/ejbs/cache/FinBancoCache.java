/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.cache;

import ejbs.entities.FinBanco;
import ejbs.facades.FinBancoFacade;
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
public class FinBancoCache implements Serializable
{

    @EJB
    private FinBancoFacade bancoFacade;
    private List<FinBanco> listFinBanco;
    private HashMap<Integer, FinBanco> hashTabela;

    /**
     * Creates a new instance of PaisBean
     */
    public FinBancoCache()
    {
    }

    /**
     *
     */
    @PostConstruct
    public void init()
    {
        listFinBanco = bancoFacade.findAllOrderByDesignacao();
        hashTabela = new HashMap();
        int  pkBanco;
        for (FinBanco reg : listFinBanco)
        {
            pkBanco = reg.getPkBanco();

            hashTabela.put(pkBanco, reg);
        }
    }
    
    public FinBanco findFinBancoRoot()
    {
        //System.err.println(""+listaFinBanco);
        return hashTabela.get(0);
        //return listaFinBanco.get(0);
    }

    public FinBanco findFinBanco(Integer pkBanco)
    {
        return hashTabela.get(pkBanco);
    }

    public List<FinBanco> getListaFinBancos()
    {
        return listFinBanco;
    }

//    public List<FinBanco> getListaPaisFinBancos()
//    {
//        List<FinBanco> resultList = new ArrayList();
//        for (FinBanco reg : this.hashTabela.values())
//        {
//            if (reg.getFkFinBanco() == null)
//            {
//                resultList.add(reg);
//            }
//        }
//
//        return resultList;
//    }
    
    public int getFinBancoLevel()
    {
        int lastId = 0;
        for (FinBanco reg : this.hashTabela.values())
        {
            if (reg.getPkBanco() > lastId)
            {
                lastId = reg.getPkBanco();
            }
        }
        
//        lastId = (lastId.trim()).replace(".","");
        //System.err.println("´´´´´´´´´´´´´´´´"+lastId.length());
        return lastId;
        //return 3;
    }
    
    public int getFinBancoID()
    {
        return bancoFacade.count() + 1;
    }

    public List<FinBanco> getListaSonsByPkFinBanco(int pkFinBanco)
    {

        List<FinBanco> resultList = new ArrayList();
        for (FinBanco reg : this.hashTabela.values())
        {
            if (reg.getPkBanco()!= null)
            //System.err.println(""+pkFinBanco+"------"+reg.getFkFinBanco().getPkFinBanco());
            {
                if (reg.getPkBanco().equals(pkFinBanco))
                {
                    resultList.add(reg);
                }
            }
        }

        return resultList;
    }
    
//    public List findAllIndefinido()
//    {
//        List<FinBanco> resultList = new ArrayList();
//        for (FinBanco reg : listaFinBanco)
//        {
//            if (reg.getDesignacao().contains("Indefinido") && ! reg.getPkFinBanco().equals("0"))
//            {
//                    resultList.add(reg);
//            }
//        }
//        return resultList;
//    }

    public void create(FinBanco reg)
    {
        bancoFacade.create(reg);
    }

    public void edit(FinBanco reg)
    {
        bancoFacade.edit(reg);
    }

    public void remove(FinBanco reg)
    {
        bancoFacade.remove(reg);
    }

}
