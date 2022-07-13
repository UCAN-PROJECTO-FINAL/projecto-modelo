/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.cache;

//import ejbs.entities.ComumMoeda;
//import ejbs.facades.ComumMoedaFacade;
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
public class ComumMoedaCache implements Serializable
{

//    @EJB
//    private ComumMoedaFacade comumMoedaFacade;
//    private List<ComumMoeda> listComumMoeda;
//    private HashMap<Integer, ComumMoeda> hashTabela;
//
//    /**
//     * Creates a new instance of PaisBean
//     */
//    public ComumMoedaCache()
//    {
//    }
//
//    /**
//     *
//     */
//    @PostConstruct
//    public void init()
//    {
//        listComumMoeda = comumMoedaFacade.findAll();
//        hashTabela = new HashMap();
//        int  pkMoeda;
//        for (ComumMoeda reg : listComumMoeda)
//        {
//            pkMoeda = reg.getPkMoeda();
//            //System.out.println("pkMoedaCache: "+pkMoeda);
//            hashTabela.put(pkMoeda, reg);
//        }
//    }
//    
//    public ComumMoeda findComumMoedaRoot()
//    {
//        //System.err.println(""+listaComumMoeda);
//        return hashTabela.get(0);
//        //return listaComumMoeda.get(0);
//    }
//
//    public ComumMoeda findComumMoeda(Integer pkMoeda)
//    {
//        return hashTabela.get(pkMoeda);
//    }
//
//    public List<ComumMoeda> getListaComumMoedas()
//    {
//        return listComumMoeda;
//    }
//
////    public List<ComumMoeda> getListaPaisComumMoedas()
////    {
////        List<ComumMoeda> resultList = new ArrayList();
////        for (ComumMoeda reg : this.hashTabela.values())
////        {
////            if (reg.getFkComumMoeda() == null)
////            {
////                resultList.add(reg);
////            }
////        }
////
////        return resultList;
////    }
//    
//    public int getComumMoedaLevel()
//    {
//        int lastId = 0;
//        for (ComumMoeda reg : this.hashTabela.values())
//        {
//            if (reg.getPkMoeda()> lastId)
//            {
//                lastId = reg.getPkMoeda();
//            }
//        }
//        
////        lastId = (lastId.trim()).replace(".","");
//        //System.err.println("´´´´´´´´´´´´´´´´"+lastId.length());
//        return lastId;
//        //return 3;
//    }
//    
//    public int getComumMoedaID()
//    {
//        return comumMoedaFacade.count() + 1;
//    }
//
//    public List<ComumMoeda> getListaSonsByPkComumMoeda(int pkComumMoeda)
//    {
//
//        List<ComumMoeda> resultList = new ArrayList();
//        for (ComumMoeda reg : this.hashTabela.values())
//        {
//            if (reg.getPkMoeda()!= null)
//            //System.err.println(""+pkComumMoeda+"------"+reg.getFkComumMoeda().getPkComumMoeda());
//            {
//                if (reg.getPkMoeda().equals(pkComumMoeda))
//                {
//                    resultList.add(reg);
//                }
//            }
//        }
//
//        return resultList;
//    }
//    
////    public List findAllIndefinido()
////    {
////        List<ComumMoeda> resultList = new ArrayList();
////        for (ComumMoeda reg : listaComumMoeda)
////        {
////            if (reg.getDesignacao().contains("Indefinido") && ! reg.getPkComumMoeda().equals("0"))
////            {
////                    resultList.add(reg);
////            }
////        }
////        return resultList;
////    }
//
//    public void create(ComumMoeda reg)
//    {
//        comumMoedaFacade.create(reg);
//    }
//
//    public void edit(ComumMoeda reg)
//    {
//        comumMoedaFacade.edit(reg);
//    }
//
//    public void remove(ComumMoeda reg)
//    {
//        comumMoedaFacade.remove(reg);
//    }

}
