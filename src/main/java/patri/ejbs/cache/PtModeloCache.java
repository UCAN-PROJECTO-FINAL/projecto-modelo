/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patri.ejbs.cache;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import ejbs.entities.PtModelo;
import ejbs.facades.PtModeloFacade;

/**
 *
 * @author mdnext
 */
@Named
@ApplicationScoped
public class PtModeloCache implements Serializable {
    
    @EJB
    private PtModeloFacade ptModeloFacade;
    private List<PtModelo> listaPtModelo;
    private HashMap<Integer, PtModelo> hashTabela;
    
     public PtModeloCache()
    {
    }

    /**
     *
     */
    @PostConstruct
    public void init()
    {
        listaPtModelo = ptModeloFacade.findAll();
        hashTabela = new HashMap();
        int pkPtModelo;
        for (PtModelo reg : listaPtModelo)
        {
            pkPtModelo = reg.getPkPtModelo();

            hashTabela.put(pkPtModelo, reg);
        }
    }    
    
    public List<PtModelo> getModeloByFkPtMarca(int pkPtMarca)
    {
        return ptModeloFacade.findModeloByFkPtMarca(pkPtMarca);
    }
    
    public void create(PtModelo reg)
    {
        ptModeloFacade.create(reg);
    }
    public void edit(PtModelo reg)
    {
        ptModeloFacade.edit(reg);
    }

    public void remove(PtModelo reg)
    {
        ptModeloFacade.remove(reg);
    }
    public List<PtModelo> getListaPtModelos()
    {
        return listaPtModelo;
    }
    
    public PtModelo findPtModelo(int pkPtModelo)
    {
        return hashTabela.get(pkPtModelo);
    }
}
