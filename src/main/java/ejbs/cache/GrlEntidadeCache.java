/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.cache;

import ejbs.entities.GrlEntidade;
import ejbs.facades.GrlEntidadeFacade;
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
public class GrlEntidadeCache implements Serializable {
    
    @EJB
    private GrlEntidadeFacade clienteFacade;
    private List<GrlEntidade> listGrlEntidade;
    private HashMap<Integer, GrlEntidade> hashTabela;

    /**
     * Creates a new instance of PaisBean
     */
    public GrlEntidadeCache()
    {
    }

    /**
     *
     */
    @PostConstruct
    public void init()
    {
        listGrlEntidade = clienteFacade.findAll();
        hashTabela = new HashMap();
        int  pkCliente;
        for (GrlEntidade reg : listGrlEntidade)
        {
            pkCliente = reg.getPkEntidade();

            hashTabela.put(pkCliente, reg);
        }
    }
    
    public GrlEntidade findGrlEntidadeRoot()
    {
        //System.err.println(""+listaGrlEntidade);
        return hashTabela.get(0);
        //return listaGrlEntidade.get(0);
    }

    public GrlEntidade findGrlEntidade(Integer pkCliente)
    {
        return hashTabela.get(pkCliente);
    }

    public List<GrlEntidade> getListaGrlEntidades()
    {
        return listGrlEntidade;
    }

//    public List<GrlEntidade> getListaPaisGrlEntidades()
//    {
//        List<GrlEntidade> resultList = new ArrayList();
//        for (GrlEntidade reg : this.hashTabela.values())
//        {
//            if (reg.getFkGrlEntidade() == null)
//            {
//                resultList.add(reg);
//            }
//        }
//
//        return resultList;
//    }
    
    public int getGrlEntidadeLevel()
    {
        int lastId = 0;
        for (GrlEntidade reg : this.hashTabela.values())
        {
            if (reg.getPkEntidade()> lastId)
            {
                lastId = reg.getPkEntidade();
            }
        }
        
//        lastId = (lastId.trim()).replace(".","");
        //System.err.println("´´´´´´´´´´´´´´´´"+lastId.length());
        return lastId;
        //return 3;
    }
    
    public int getGrlEntidadeID()
    {
        return clienteFacade.count() + 1;
    }

    public List<GrlEntidade> getListaSonsByPkGrlEntidade(int pkGrlEntidade)
    {

        List<GrlEntidade> resultList = new ArrayList();
        for (GrlEntidade reg : this.hashTabela.values())
        {
            if (reg.getPkEntidade()!= null)
            //System.err.println(""+pkGrlEntidade+"------"+reg.getFkGrlEntidade().getPkGrlEntidade());
            {
                if (reg.getPkEntidade().equals(pkGrlEntidade))
                {
                    resultList.add(reg);
                }
            }
        }

        return resultList;
    }
    
//    public List findAllIndefinido()
//    {
//        List<GrlEntidade> resultList = new ArrayList();
//        for (GrlEntidade reg : listaGrlEntidade)
//        {
//            if (reg.getDesignacao().contains("Indefinido") && ! reg.getPkGrlEntidade().equals("0"))
//            {
//                    resultList.add(reg);
//            }
//        }
//        return resultList;
//    }

    public void create(GrlEntidade reg)
    {
        clienteFacade.create(reg);
    }

    public void edit(GrlEntidade reg)
    {
        clienteFacade.edit(reg);
    }

    public void remove(GrlEntidade reg)
    {
        clienteFacade.remove(reg);
    }

    
}
