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
import ejbs.entities.PtBemEntrada;
import ejbs.facades.PtBemEntradaFacade;

/**
 *
 * @author mdnext
 */
@Named
@ApplicationScoped
public class PtBemEntradaCache implements Serializable {
    
    @EJB
    private PtBemEntradaFacade ptBemFacade;
    private List<PtBemEntrada> listaPtBemEntrada;
    private HashMap<Integer, PtBemEntrada> hashTabela;
    
     public PtBemEntradaCache()
    {
    }

    /**
     *
     */
    @PostConstruct
    public void init()
    {
        listaPtBemEntrada = ptBemFacade.findAll();
        hashTabela = new HashMap();
        int pkPtBemEntrada;
        for (PtBemEntrada reg : listaPtBemEntrada)
        {
            pkPtBemEntrada = reg.getPkPtBemEntrada();

            hashTabela.put(pkPtBemEntrada, reg);
        }
    }    
    
    public List<PtBemEntrada> getListaPtBemEntrada()
    {
        return listaPtBemEntrada;
    }
    
    public PtBemEntrada getLastRegisted()
    {
        List<PtBemEntrada> list = this.ptBemFacade.findAllPtBemEntrada();
        return list.get(list.size() - 1);
    }
    
    public void create(PtBemEntrada reg)
    {
        ptBemFacade.create(reg);
    }
    public void edit(PtBemEntrada reg)
    {
        ptBemFacade.edit(reg);
    }

    public void remove(PtBemEntrada reg)
    {
        ptBemFacade.remove(reg);
    }
    public List<PtBemEntrada> getListaPtBemEntradas()
    {
        return listaPtBemEntrada;
    }
    
    public PtBemEntrada findPtBemEntrada(int pkPtBemEntrada)
    {
        return hashTabela.get(pkPtBemEntrada);
    }
}
