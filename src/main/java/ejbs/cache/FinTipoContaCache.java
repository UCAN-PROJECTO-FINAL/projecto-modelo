/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.cache;

import ejbs.entities.FinTipoConta;
import ejbs.facades.FinTipoContaFacade;
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
 * @author majoao
 */
@Named
@ApplicationScoped
public class FinTipoContaCache implements Serializable {
    
     @EJB
    private FinTipoContaFacade tipoCartaoFacade;
    private List<FinTipoConta> listFinTipoConta;
    private HashMap<Integer, FinTipoConta> hashTabela;

    /**
     * Creates a new instance of PaisBean
     */
    public FinTipoContaCache()
    {
    }

    /**
     *
     */
    @PostConstruct
    public void init()
    {
        listFinTipoConta = tipoCartaoFacade.findAll();
        hashTabela = new HashMap();
        int  pkFinTipoConta;
        for (FinTipoConta reg : listFinTipoConta)
        {
            pkFinTipoConta = reg.getPkTipoConta();

            hashTabela.put(pkFinTipoConta, reg);
        }
    }
    
    public FinTipoConta findFinTipoContaRoot()
    {
        //System.err.println(""+listaFinTipoConta);
        return hashTabela.get(0);
        //return listaFinTipoConta.get(0);
    }

    public FinTipoConta findFinTipoConta(Integer pkFinTipoConta)
    {
        return hashTabela.get(pkFinTipoConta);
    }

    public List<FinTipoConta> getListaFinTipoConta()
    {
        return listFinTipoConta;
    }

//    public List<FinTipoConta> getListaPaisFinTipoContas()
//    {
//        List<FinTipoConta> resultList = new ArrayList();
//        for (FinTipoConta reg : this.hashTabela.values())
//        {
//            if (reg.getFkFinTipoConta() == null)
//            {
//                resultList.add(reg);
//            }
//        }
//
//        return resultList;
//    }
    
    public int getFinTipoContaLevel()
    {
        int lastId = 0;
        for (FinTipoConta reg : this.hashTabela.values())
        {
            if (reg.getPkTipoConta()> lastId)
            {
                lastId = reg.getPkTipoConta();
            }
        }
        
//        lastId = (lastId.trim()).replace(".","");
        //System.err.println("´´´´´´´´´´´´´´´´"+lastId.length());
        return lastId;
        //return 3;
    }
    
    public int getFinTipoContaID()
    {
        return tipoCartaoFacade.count() + 1;
    }

    public List<FinTipoConta> getListaSonsByPkFinTipoConta(int pkFinTipoConta)
    {

        List<FinTipoConta> resultList = new ArrayList();
        for (FinTipoConta reg : this.hashTabela.values())
        {
            if (reg.getPkTipoConta()!= null)
            //System.err.println(""+pkFinTipoConta+"------"+reg.getFkFinTipoConta().getPkFinTipoConta());
            {
                if (reg.getPkTipoConta().equals(pkFinTipoConta))
                {
                    resultList.add(reg);
                }
            }
        }

        return resultList;
    }
    
//    public List findAllIndefinido()
//    {
//        List<FinTipoConta> resultList = new ArrayList();
//        for (FinTipoConta reg : listaFinTipoConta)
//        {
//            if (reg.getDesignacao().contains("Indefinido") && ! reg.getPkFinTipoConta().equals("0"))
//            {
//                    resultList.add(reg);
//            }
//        }
//        return resultList;
//    }

    public void create(FinTipoConta reg)
    {
        tipoCartaoFacade.create(reg);
    }

    public void edit(FinTipoConta reg)
    {
        tipoCartaoFacade.edit(reg);
    }

    public void remove(FinTipoConta reg)
    {
        tipoCartaoFacade.remove(reg);
    }
    
}
