/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.cache;

import ejbs.entities.FinCategoriaSubcategoria;
import ejbs.facades.FinCategoriaSubcategoriaFacade;
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
public class FinSubCategoriaCache implements Serializable
{

    @EJB
    private FinCategoriaSubcategoriaFacade subCategoriaFacade;
    private List<FinCategoriaSubcategoria> listFinCategoriaSubcategoria;
    private HashMap<Integer, FinCategoriaSubcategoria> hashTabela;

    /**
     * Creates a new instance of PaisBean
     */
    public FinSubCategoriaCache()
    {
    }

    /**
     *
     */
    @PostConstruct
    public void init()
    {
        listFinCategoriaSubcategoria = subCategoriaFacade.findAll();
        hashTabela = new HashMap();
        int  pkFinCategoriaSubcategoria;
        for (FinCategoriaSubcategoria reg : listFinCategoriaSubcategoria)
        {
            pkFinCategoriaSubcategoria = reg.getPkCategoriaSubcategoria();

            hashTabela.put(pkFinCategoriaSubcategoria, reg);
        }
    }
    
    public FinCategoriaSubcategoria findFinCategoriaSubcategoriaRoot()
    {
        //System.err.println(""+listaFinCategoriaSubcategoria);
        return hashTabela.get(0);
        //return listaFinCategoriaSubcategoria.get(0);
    }

    public FinCategoriaSubcategoria findFinCategoriaSubcategoria(Integer pkFinCategoriaSubcategoria)
    {
        return hashTabela.get(pkFinCategoriaSubcategoria);
    }

    public List<FinCategoriaSubcategoria> getListaFinCategoriaSubcategorias()
    {
        return listFinCategoriaSubcategoria;
    }

//    public List<FinCategoriaSubcategoria> getListaPaisFinCategoriaSubcategorias()
//    {
//        List<FinCategoriaSubcategoria> resultList = new ArrayList();
//        for (FinCategoriaSubcategoria reg : this.hashTabela.values())
//        {
//            if (reg.getFkFinCategoriaSubcategoria() == null)
//            {
//                resultList.add(reg);
//            }
//        }
//
//        return resultList;
//    }
    
    public int getFinCategoriaSubcategoriaLevel()
    {
        int lastId = 0;
        for (FinCategoriaSubcategoria reg : this.hashTabela.values())
        {
            if (reg.getPkCategoriaSubcategoria()> lastId)
            {
                lastId = reg.getPkCategoriaSubcategoria();
            }
        }
        
//        lastId = (lastId.trim()).replace(".","");
        //System.err.println("´´´´´´´´´´´´´´´´"+lastId.length());
        return lastId;
        //return 3;
    }
    
    public int getFinCategoriaSubcategoriaID()
    {
        return subCategoriaFacade.count() + 1;
    }

    public List<FinCategoriaSubcategoria> getListaSonsByPkFinCategoriaSubcategoria(int pkFinCategoriaSubcategoria)
    {

        List<FinCategoriaSubcategoria> resultList = new ArrayList();
        for (FinCategoriaSubcategoria reg : this.hashTabela.values())
        {
            if (reg.getPkCategoriaSubcategoria()!= null)
            //System.err.println(""+pkFinCategoriaSubcategoria+"------"+reg.getFkFinCategoriaSubcategoria().getPkFinCategoriaSubcategoria());
            {
                if (reg.getPkCategoriaSubcategoria().equals(pkFinCategoriaSubcategoria))
                {
                    resultList.add(reg);
                }
            }
        }

        return resultList;
    }
    
//    public List findAllIndefinido()
//    {
//        List<FinCategoriaSubcategoria> resultList = new ArrayList();
//        for (FinCategoriaSubcategoria reg : listaFinCategoriaSubcategoria)
//        {
//            if (reg.getDesignacao().contains("Indefinido") && ! reg.getPkFinCategoriaSubcategoria().equals("0"))
//            {
//                    resultList.add(reg);
//            }
//        }
//        return resultList;
//    }

    public void create(FinCategoriaSubcategoria reg)
    {
        subCategoriaFacade.create(reg);
    }

    public void edit(FinCategoriaSubcategoria reg)
    {
        subCategoriaFacade.edit(reg);
    }

    public void remove(FinCategoriaSubcategoria reg)
    {
        subCategoriaFacade.remove(reg);
    }

}
