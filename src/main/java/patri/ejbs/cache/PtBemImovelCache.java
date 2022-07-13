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
import ejbs.entities.PtBemImovel;
import ejbs.facades.PtBemImovelFacade;

/**
 *
 * @author mdnext
 */
@Named
@ApplicationScoped
public class PtBemImovelCache implements Serializable {
    
    @EJB
    private PtBemImovelFacade ptBemFacade;
    private List<PtBemImovel> listaPtBemImovel;
    private HashMap<Integer, PtBemImovel> hashTabela;
    
     public PtBemImovelCache()
    {
    }

    /**
     *
     */
    @PostConstruct
    public void init()
    {
        listaPtBemImovel = ptBemFacade.findAll();
        hashTabela = new HashMap();
        int pkPtBemImovel;
        for (PtBemImovel reg : listaPtBemImovel)
        {
            pkPtBemImovel = reg.getPkPtBemImovel();

            hashTabela.put(pkPtBemImovel, reg);
        }
    }    
    
    public void create(PtBemImovel reg)
    {
        ptBemFacade.create(reg);
    }
    public void edit(PtBemImovel reg)
    {
        ptBemFacade.edit(reg);
    }

    public void remove(PtBemImovel reg)
    {
        ptBemFacade.remove(reg);
    }
    public List<PtBemImovel> getListaPtBemImovel()
    {
        return listaPtBemImovel;
    }
    
    public PtBemImovel findPtBemImovel(int pkPtBemImovel)
    {
        return hashTabela.get(pkPtBemImovel);
    }
    
    public int getLastRegisted()
    {
        List<PtBemImovel> list = this.ptBemFacade.findAll();
        return list.size() > 0 ? list.size(): 0;
    }
    
}
