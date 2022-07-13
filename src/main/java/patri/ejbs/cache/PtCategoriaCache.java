/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patri.ejbs.cache;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import ejbs.entities.PtCategoria;
import ejbs.facades.PtCategoriaFacade;

/**
 *
 * @author mdnext
 */
@Named
@ApplicationScoped
public class PtCategoriaCache implements Serializable {
    
    @EJB
    private PtCategoriaFacade ptCategoriaFacade;
    private List<PtCategoria> listaPtCategoria;
    private HashMap<String, PtCategoria> hashTabela;
    
     public PtCategoriaCache()
    {
    }

    /**
     *
     */
    @PostConstruct
    public void init()
    {
        listaPtCategoria = ptCategoriaFacade.findAll();
        hashTabela = new HashMap();
        String pkPtCategoria;
        for (PtCategoria reg : listaPtCategoria)
        {
            pkPtCategoria = reg.getPkPtCategoria();

            hashTabela.put(pkPtCategoria, reg);
        }
    }    
    
    public void create(PtCategoria reg)
    {
        ptCategoriaFacade.create(reg);
    }
    public void edit(PtCategoria reg)
    {
        ptCategoriaFacade.edit(reg);
    }

    public void remove(PtCategoria reg)
    {
        ptCategoriaFacade.remove(reg);
    }
    public List<PtCategoria> getListaPtCategorias()
    {
        return listaPtCategoria;
    }

    public PtCategoria findPtCategoria(String pkPtCategoria)
    {
        return hashTabela.get(pkPtCategoria);
    }

    public List<PtCategoria> getListaSonsByPkPtCategoria(String pkPtCategoria)
    {
        List<PtCategoria> resultList = new ArrayList();
        for (PtCategoria reg : this.hashTabela.values())
        {
            if (reg.getFkPtCategoria() != null)
            {
                if (reg.getFkPtCategoria().getPkPtCategoria().equals(pkPtCategoria))
                {
                    resultList.add(reg);
                }
            }
        }

        return resultList;
    }
    
    public List<PtCategoria> getListaPaisPtCategoria()
    {
        List<PtCategoria> resultList = new ArrayList();
        for (PtCategoria reg : this.hashTabela.values())
        {
            //System.err.println("getDescricao()"+reg.getDescricao()+"ssssssssssssssss"+(reg.getDescricao().trim()!= "Indefinido"));
            if (reg.getFkPtCategoria() == null && reg.getDescricao().trim()!= "Indefinido")
            {
                resultList.add(reg);
            }
        }

        return resultList;
    }
    
}
