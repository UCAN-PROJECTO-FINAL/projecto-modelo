/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.cache;

import ejbs.entities.FinConta;
import ejbs.facades.FinContaFacade;
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
public class FinContaCache implements Serializable
{

    @EJB
    private FinContaFacade contaFacade;
    private List<FinConta> listFinConta;
    private HashMap<Integer, FinConta> hashTabela;

    /**
     * Creates a new instance of PaisBean
     */
    public FinContaCache()
    {
    }

    /**
     *
     */
    @PostConstruct
    public void init()
    {
        listFinConta = contaFacade.findAll();
        hashTabela = new HashMap();
        int  pkConta;
        for (FinConta reg : listFinConta)
        {
            pkConta = reg.getPkConta();

            hashTabela.put(pkConta, reg);
        }
    }
    
    public FinConta findFinContaRoot()
    {
        //System.err.println(""+listaFinConta);
        return hashTabela.get(0);
        //return listaFinConta.get(0);
    }

    public FinConta findFinConta(Integer pkConta)
    {
        return hashTabela.get(pkConta);
    }

    public List<FinConta> getListaFinContas()
    {
        return listFinConta;
    }

//    public List<FinConta> getListaPaisFinContas()
//    {
//        List<FinConta> resultList = new ArrayList();
//        for (FinConta reg : this.hashTabela.values())
//        {
//            if (reg.getFkFinConta() == null)
//            {
//                resultList.add(reg);
//            }
//        }
//
//        return resultList;
//    }
    
    public int getFinContaLevel()
    {
        int lastId = 0;
        for (FinConta reg : this.hashTabela.values())
        {
            if (reg.getPkConta()> lastId)
            {
                lastId = reg.getPkConta();
            }
        }
        
//        lastId = (lastId.trim()).replace(".","");
        //System.err.println("´´´´´´´´´´´´´´´´"+lastId.length());
        return lastId;
        //return 3;
    }
    
    public int getFinContaID()
    {
        return contaFacade.count() + 1;
    }

    public List<FinConta> getListaSonsByPkFinConta(int pkFinConta)
    {

        List<FinConta> resultList = new ArrayList();
        for (FinConta reg : this.hashTabela.values())
        {
            if (reg.getPkConta()!= null)
            //System.err.println(""+pkFinConta+"------"+reg.getFkFinConta().getPkFinConta());
            {
                if (reg.getPkConta().equals(pkFinConta))
                {
                    resultList.add(reg);
                }
            }
        }

        return resultList;
    }
    
//    public List findAllIndefinido()
//    {
//        List<FinConta> resultList = new ArrayList();
//        for (FinConta reg : listaFinConta)
//        {
//            if (reg.getDesignacao().contains("Indefinido") && ! reg.getPkFinConta().equals("0"))
//            {
//                    resultList.add(reg);
//            }
//        }
//        return resultList;
//    }

    public void create(FinConta reg)
    {
        contaFacade.create(reg);
    }

    public void edit(FinConta reg)
    {
        contaFacade.edit(reg);
    }

    public void remove(FinConta reg)
    {
        contaFacade.remove(reg);
    }

}
