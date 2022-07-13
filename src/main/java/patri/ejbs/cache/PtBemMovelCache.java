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
import ejbs.entities.PtBemMovel;
import ejbs.facades.PtBemMovelFacade;

/**
 *
 * @author mdnext
 */
@Named
@ApplicationScoped
public class PtBemMovelCache implements Serializable {
    
    @EJB
    private PtBemMovelFacade ptBemFacade;
    private List<PtBemMovel> listaPtBemMovel;
    private HashMap<Integer, PtBemMovel> hashTabela;
    
     public PtBemMovelCache()
    {
    }

    /**
     *
     */
    @PostConstruct
    public void init()
    {
        listaPtBemMovel = ptBemFacade.findAll();
        hashTabela = new HashMap();
        int pkPtBemMovel;
        for (PtBemMovel reg : listaPtBemMovel)
        {
            pkPtBemMovel = reg.getPkPtBemMovel();

            hashTabela.put(pkPtBemMovel, reg);
        }
    }    
    
    public void create(PtBemMovel reg)
    {
        ptBemFacade.create(reg);
    }
    public void edit(PtBemMovel reg)
    {
        ptBemFacade.edit(reg);
    }

    public void remove(PtBemMovel reg)
    {
        ptBemFacade.remove(reg);
    }
    public List<PtBemMovel> getListaPtBemMovel()
    {
        return listaPtBemMovel;
    }
    public PtBemMovel findPtBemMovel(int pkPtBemMovel)
    {
        return hashTabela.get(pkPtBemMovel);
    }
    
    
    public int getLastRegisted()
    {
        List<PtBemMovel> list = this.ptBemFacade.findAll();
        return list.size();
    }
}
