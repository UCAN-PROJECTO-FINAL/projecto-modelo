/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.cache;

import ejbs.entities.FinTipoCartao;
import ejbs.facades.FinTipoCartaoFacade;
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
public class FinTipoCartaoCache implements Serializable {
    
     @EJB
    private FinTipoCartaoFacade tipoCartaoFacade;
    private List<FinTipoCartao> listFinTipoCartao;
    private HashMap<Integer, FinTipoCartao> hashTabela;

    /**
     * Creates a new instance of PaisBean
     */
    public FinTipoCartaoCache()
    {
    }

    /**
     *
     */
    @PostConstruct
    public void init()
    {
        listFinTipoCartao = tipoCartaoFacade.findAll();
        hashTabela = new HashMap();
        int  pkFinTipoCartao;
        for (FinTipoCartao reg : listFinTipoCartao)
        {
            pkFinTipoCartao = reg.getPkTipoCartao();

            hashTabela.put(pkFinTipoCartao, reg);
        }
    }
    
    public FinTipoCartao findFinTipoCartaoRoot()
    {
        //System.err.println(""+listaFinTipoCartao);
        return hashTabela.get(0);
        //return listaFinTipoCartao.get(0);
    }

    public FinTipoCartao findFinTipoCartao(Integer pkFinTipoCartao)
    {
        return hashTabela.get(pkFinTipoCartao);
    }

    public List<FinTipoCartao> getListaFinTipoCartao()
    {
        return listFinTipoCartao;
    }

//    public List<FinTipoCartao> getListaPaisFinTipoCartaos()
//    {
//        List<FinTipoCartao> resultList = new ArrayList();
//        for (FinTipoCartao reg : this.hashTabela.values())
//        {
//            if (reg.getFkFinTipoCartao() == null)
//            {
//                resultList.add(reg);
//            }
//        }
//
//        return resultList;
//    }
    
    public int getFinTipoCartaoLevel()
    {
        int lastId = 0;
        for (FinTipoCartao reg : this.hashTabela.values())
        {
            if (reg.getPkTipoCartao()> lastId)
            {
                lastId = reg.getPkTipoCartao();
            }
        }
        
//        lastId = (lastId.trim()).replace(".","");
        //System.err.println("´´´´´´´´´´´´´´´´"+lastId.length());
        return lastId;
        //return 3;
    }
    
    public int getFinTipoCartaoID()
    {
        return tipoCartaoFacade.count() + 1;
    }

    public List<FinTipoCartao> getListaSonsByPkFinTipoCartao(int pkFinTipoCartao)
    {

        List<FinTipoCartao> resultList = new ArrayList();
        for (FinTipoCartao reg : this.hashTabela.values())
        {
            if (reg.getPkTipoCartao()!= null)
            //System.err.println(""+pkFinTipoCartao+"------"+reg.getFkFinTipoCartao().getPkFinTipoCartao());
            {
                if (reg.getPkTipoCartao().equals(pkFinTipoCartao))
                {
                    resultList.add(reg);
                }
            }
        }

        return resultList;
    }
    
//    public List findAllIndefinido()
//    {
//        List<FinTipoCartao> resultList = new ArrayList();
//        for (FinTipoCartao reg : listaFinTipoCartao)
//        {
//            if (reg.getDesignacao().contains("Indefinido") && ! reg.getPkFinTipoCartao().equals("0"))
//            {
//                    resultList.add(reg);
//            }
//        }
//        return resultList;
//    }

    public void create(FinTipoCartao reg)
    {
        tipoCartaoFacade.create(reg);
    }

    public void edit(FinTipoCartao reg)
    {
        tipoCartaoFacade.edit(reg);
    }

    public void remove(FinTipoCartao reg)
    {
        tipoCartaoFacade.remove(reg);
    }
    
}
