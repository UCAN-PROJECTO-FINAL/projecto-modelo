/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.cache;

import ejbs.entities.FinTipoCategoria;
import ejbs.facades.FinTipoCategoriaFacade;
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
public class FinTipoCategoriaCache implements Serializable
{

    @EJB
    private FinTipoCategoriaFacade tipoCategoriaFacade;
    private List<FinTipoCategoria> listFinTipoCategoria;
    private HashMap<Integer, FinTipoCategoria> hashTabela;

    /**
     * Creates a new instance of PaisBean
     */
    public FinTipoCategoriaCache()
    {
    }

    /**
     *
     */
    @PostConstruct
    public void init()
    {
        listFinTipoCategoria = tipoCategoriaFacade.findAll();
        hashTabela = new HashMap();
        int  pkFinTipoCategoria;
        for (FinTipoCategoria reg : listFinTipoCategoria)
        {
            pkFinTipoCategoria = reg.getPkTipoCategoria();

            hashTabela.put(pkFinTipoCategoria, reg);
        }
    }
    
    public FinTipoCategoria findFinTipoCategoriaRoot()
    {
        //System.err.println(""+listaFinTipoCategoria);
        return hashTabela.get(0);
        //return listaFinTipoCategoria.get(0);
    }

    public FinTipoCategoria findFinTipoCategoria(Integer pkFinTipoCategoria)
    {
        return hashTabela.get(pkFinTipoCategoria);
    }

    public List<FinTipoCategoria> getListaFinTipoCategorias()
    {
        return listFinTipoCategoria;
    }

//    public List<FinTipoCategoria> getListaPaisFinTipoCategorias()
//    {
//        List<FinTipoCategoria> resultList = new ArrayList();
//        for (FinTipoCategoria reg : this.hashTabela.values())
//        {
//            if (reg.getFkFinTipoCategoria() == null)
//            {
//                resultList.add(reg);
//            }
//        }
//
//        return resultList;
//    }
    
    public int getFinTipoCategoriaLevel()
    {
        int lastId = 0;
        for (FinTipoCategoria reg : this.hashTabela.values())
        {
            if (reg.getPkTipoCategoria()> lastId)
            {
                lastId = reg.getPkTipoCategoria();
            }
        }
        
//        lastId = (lastId.trim()).replace(".","");
        //System.err.println("´´´´´´´´´´´´´´´´"+lastId.length());
        return lastId;
        //return 3;
    }
    
    public int getFinTipoCategoriaID()
    {
        return tipoCategoriaFacade.count() + 1;
    }

    public List<FinTipoCategoria> getListaSonsByPkFinTipoCategoria(int pkFinTipoCategoria)
    {

        List<FinTipoCategoria> resultList = new ArrayList();
        for (FinTipoCategoria reg : this.hashTabela.values())
        {
            if (reg.getPkTipoCategoria()!= null)
            //System.err.println(""+pkFinTipoCategoria+"------"+reg.getFkFinTipoCategoria().getPkFinTipoCategoria());
            {
                if (reg.getPkTipoCategoria().equals(pkFinTipoCategoria))
                {
                    resultList.add(reg);
                }
            }
        }

        return resultList;
    }
    
//    public List findAllIndefinido()
//    {
//        List<FinTipoCategoria> resultList = new ArrayList();
//        for (FinTipoCategoria reg : listaFinTipoCategoria)
//        {
//            if (reg.getDesignacao().contains("Indefinido") && ! reg.getPkFinTipoCategoria().equals("0"))
//            {
//                    resultList.add(reg);
//            }
//        }
//        return resultList;
//    }

    public void create(FinTipoCategoria reg)
    {
        tipoCategoriaFacade.create(reg);
    }

    public void edit(FinTipoCategoria reg)
    {
        tipoCategoriaFacade.edit(reg);
    }

    public void remove(FinTipoCategoria reg)
    {
        tipoCategoriaFacade.remove(reg);
    }

}
