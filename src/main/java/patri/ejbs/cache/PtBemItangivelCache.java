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
import ejbs.entities.PtBemItangivel;
import ejbs.facades.PtBemItangivelFacade;

/**
 *
 * @author mdnext
 */
@Named
@ApplicationScoped
public class PtBemItangivelCache implements Serializable {
    
    @EJB
    private PtBemItangivelFacade ptBemFacade;
    private List<PtBemItangivel> listaPtBemItangivel;
    private HashMap<Integer, PtBemItangivel> hashTabela;
    
     public PtBemItangivelCache()
    {
    }

    /**
     *
     */
    @PostConstruct
    public void init()
    {
        listaPtBemItangivel = ptBemFacade.findAll();
        hashTabela = new HashMap();
        int pkPtBemItangivel;
        for (PtBemItangivel reg : listaPtBemItangivel)
        {
            pkPtBemItangivel = reg.getPkPtBemItangivel();

            hashTabela.put(pkPtBemItangivel, reg);
        }
    }    
    
    public void create(PtBemItangivel reg)
    {
        ptBemFacade.create(reg);
    }
    public void edit(PtBemItangivel reg)
    {
        ptBemFacade.edit(reg);
    }

    public void remove(PtBemItangivel reg)
    {
        ptBemFacade.remove(reg);
    }
    public List<PtBemItangivel> getListaPtBemItangivel()
    {
        return listaPtBemItangivel;
    }
    
    public PtBemItangivel findPtBemItangivel(int pkPtBemItangivel)
    {
        return hashTabela.get(pkPtBemItangivel);
    }
    
    public int getLastRegisted()
    {
        List<PtBemItangivel> list = this.ptBemFacade.findAll();
        return list.size() > 0 ? list.size(): 0;
    }
    
}
