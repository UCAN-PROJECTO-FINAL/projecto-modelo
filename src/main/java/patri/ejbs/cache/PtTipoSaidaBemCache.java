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
import ejbs.entities.PtTipoSaidaBem;
import ejbs.facades.PtTipoSaidaBemFacade;

/**
 *
 * @author mdnext
 */
@Named
@ApplicationScoped
public class PtTipoSaidaBemCache implements Serializable{
    
    @EJB
    private PtTipoSaidaBemFacade ptTipoSaidaBemFacade;
    private List<PtTipoSaidaBem> listaPtTipoSaidaBem;
    private HashMap<Integer, PtTipoSaidaBem> hashTabela;
    
     public PtTipoSaidaBemCache()
    {
    }

    /**
     *
     */
    @PostConstruct
    public void init()
    {
        listaPtTipoSaidaBem = ptTipoSaidaBemFacade.findAll();
        hashTabela = new HashMap();
        int pkPtTipoSaidaBem;
        for (PtTipoSaidaBem reg : listaPtTipoSaidaBem)
        {
            pkPtTipoSaidaBem = reg.getPkPtTipoSaidaBem();

            hashTabela.put(pkPtTipoSaidaBem, reg);
        }
    }

    
    public void create(PtTipoSaidaBem reg)
    {
        ptTipoSaidaBemFacade.create(reg);
    }
    public void edit(PtTipoSaidaBem reg)
    {
        ptTipoSaidaBemFacade.edit(reg);
    }

    public void remove(PtTipoSaidaBem reg)
    {
        ptTipoSaidaBemFacade.remove(reg);
    }
    public List<PtTipoSaidaBem> getListaPtTipoSaidaBems()
    {
        return listaPtTipoSaidaBem;
    }
    
    public PtTipoSaidaBem findPtTipoSaidaBem(int pkPtTipoSaidaBem)
    {
        return hashTabela.get(pkPtTipoSaidaBem);
    }
    
}
