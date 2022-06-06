/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.cache;

import ejbs.entities.FinCategorias;
import ejbs.facades.FinCategoriasFacade;
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
public class FinCategoriaCache implements Serializable
{

    @EJB
    private FinCategoriasFacade categoriaFacade;
    private List<FinCategorias> listFinCategorias;
    private HashMap<Integer, FinCategorias> hashTabela;

    /**
     * Creates a new instance of PaisBean
     */
    public FinCategoriaCache()
    {
    }

    /**
     *
     */
    @PostConstruct
    public void init()
    {
        listFinCategorias = categoriaFacade.findAll();
        hashTabela = new HashMap();
        int  pkFinCategorias;
        for (FinCategorias reg : listFinCategorias)
        {
            pkFinCategorias = reg.getPkCategoria();

            hashTabela.put(pkFinCategorias, reg);
        }
    }
    
    public FinCategorias findFinCategoriasRoot()
    {
        //System.err.println(""+listaFinCategorias);
        return hashTabela.get(0);
        //return listaFinCategorias.get(0);
    }

    public FinCategorias findFinCategorias(Integer pkFinCategorias)
    {
        return hashTabela.get(pkFinCategorias);
    }

    public List<FinCategorias> getListaFinCategoriass()
    {
        return listFinCategorias;
    }

//    public List<FinCategorias> getListaPaisFinCategoriass()
//    {
//        List<FinCategorias> resultList = new ArrayList();
//        for (FinCategorias reg : this.hashTabela.values())
//        {
//            if (reg.getFkFinCategorias() == null)
//            {
//                resultList.add(reg);
//            }
//        }
//
//        return resultList;
//    }
    
    public int getFinCategoriasLevel()
    {
        int lastId = 0;
        for (FinCategorias reg : this.hashTabela.values())
        {
            if (reg.getPkCategoria()> lastId)
            {
                lastId = reg.getPkCategoria();
            }
        }
        
//        lastId = (lastId.trim()).replace(".","");
        //System.err.println("´´´´´´´´´´´´´´´´"+lastId.length());
        return lastId;
        //return 3;
    }
    
    public int getFinCategoriasID()
    {
        return categoriaFacade.count() + 1;
    }

    public List<FinCategorias> getListaSonsByPkFinCategorias(int pkFinCategorias)
    {

        List<FinCategorias> resultList = new ArrayList();
        for (FinCategorias reg : this.hashTabela.values())
        {
            if (reg.getPkCategoria()!= null)
            //System.err.println(""+pkFinCategorias+"------"+reg.getFkFinCategorias().getPkFinCategorias());
            {
                if (reg.getPkCategoria().equals(pkFinCategorias))
                {
                    resultList.add(reg);
                }
            }
        }

        return resultList;
    }
    
//    public List findAllIndefinido()
//    {
//        List<FinCategorias> resultList = new ArrayList();
//        for (FinCategorias reg : listaFinCategorias)
//        {
//            if (reg.getDesignacao().contains("Indefinido") && ! reg.getPkFinCategorias().equals("0"))
//            {
//                    resultList.add(reg);
//            }
//        }
//        return resultList;
//    }

    public void create(FinCategorias reg)
    {
        categoriaFacade.create(reg);
    }

    public void edit(FinCategorias reg)
    {
        categoriaFacade.edit(reg);
    }

    public void remove(FinCategorias reg)
    {
        categoriaFacade.remove(reg);
    }

}
