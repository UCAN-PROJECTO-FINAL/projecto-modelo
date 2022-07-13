/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.cache;

import ejbs.entities.GdTipoDocumento;
import ejbs.facades.GdTipoDocumentoFacade;
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
public class GdTipoDocumentoCache implements Serializable
{

    @EJB
    private GdTipoDocumentoFacade contaFacade;
    private List<GdTipoDocumento> listGdTipoDocumento;
    private HashMap<Integer, GdTipoDocumento> hashTabela;

    /**
     * Creates a new instance of PaisBean
     */
    public GdTipoDocumentoCache()
    {
    }

    /**
     *
     */
    @PostConstruct
    public void init()
    {
        listGdTipoDocumento = contaFacade.findAll();
        hashTabela = new HashMap();
        int  pkTipoDocumento;
        for (GdTipoDocumento reg : listGdTipoDocumento)
        {
            pkTipoDocumento = reg.getPkTipoDocumento();

            hashTabela.put(pkTipoDocumento, reg);
        }
    }
    
    public GdTipoDocumento findGdTipoDocumentoRoot()
    {
        //System.err.println(""+listaGdTipoDocumento);
        return hashTabela.get(0);
        //return listaGdTipoDocumento.get(0);
    }

    public GdTipoDocumento findGdTipoDocumento(Integer pkTipoDocumento)
    {
        return hashTabela.get(pkTipoDocumento);
    }

    public List<GdTipoDocumento> getListaGdTipoDocumentos()
    {
        return listGdTipoDocumento;
    }

//    public List<GdTipoDocumento> getListaPaisGdTipoDocumentos()
//    {
//        List<GdTipoDocumento> resultList = new ArrayList();
//        for (GdTipoDocumento reg : this.hashTabela.values())
//        {
//            if (reg.getFkGdTipoDocumento() == null)
//            {
//                resultList.add(reg);
//            }
//        }
//
//        return resultList;
//    }
    
    public int getGdTipoDocumentoLevel()
    {
        int lastId = 0;
        for (GdTipoDocumento reg : this.hashTabela.values())
        {
            if (reg.getPkTipoDocumento()> lastId)
            {
                lastId = reg.getPkTipoDocumento();
            }
        }
        
//        lastId = (lastId.trim()).replace(".","");
        //System.err.println("´´´´´´´´´´´´´´´´"+lastId.length());
        return lastId;
        //return 3;
    }
    
    public int getGdTipoDocumentoID()
    {
        return contaFacade.count() + 1;
    }

    public List<GdTipoDocumento> getListaSonsByPkGdTipoDocumento(int pkGdTipoDocumento)
    {

        List<GdTipoDocumento> resultList = new ArrayList();
        for (GdTipoDocumento reg : this.hashTabela.values())
        {
            if (reg.getPkTipoDocumento()!= null)
            //System.err.println(""+pkGdTipoDocumento+"------"+reg.getFkGdTipoDocumento().getPkGdTipoDocumento());
            {
                if (reg.getPkTipoDocumento().equals(pkGdTipoDocumento))
                {
                    resultList.add(reg);
                }
            }
        }

        return resultList;
    }
    
//    public List findAllIndefinido()
//    {
//        List<GdTipoDocumento> resultList = new ArrayList();
//        for (GdTipoDocumento reg : listaGdTipoDocumento)
//        {
//            if (reg.getDesignacao().contains("Indefinido") && ! reg.getPkGdTipoDocumento().equals("0"))
//            {
//                    resultList.add(reg);
//            }
//        }
//        return resultList;
//    }

    public void create(GdTipoDocumento reg)
    {
        contaFacade.create(reg);
    }

    public void edit(GdTipoDocumento reg)
    {
        contaFacade.edit(reg);
    }

    public void remove(GdTipoDocumento reg)
    {
        contaFacade.remove(reg);
    }

}
