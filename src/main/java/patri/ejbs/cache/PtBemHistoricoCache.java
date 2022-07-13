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
import ejbs.entities.PtBemHistorico;
import ejbs.facades.PtBemHistoricoFacade;

/**
 *
 * @author mdnext
 */
@Named
@ApplicationScoped
public class PtBemHistoricoCache implements Serializable{
    
    @EJB
    private PtBemHistoricoFacade ptBemHistoricoFacade;
    private List<PtBemHistorico> listaPtBemHistorico;
    private HashMap<Integer, PtBemHistorico> hashTabela;
    
     public PtBemHistoricoCache()
    {
    }

    /**
     *
     */
    @PostConstruct
    public void init()
    {
        listaPtBemHistorico = ptBemHistoricoFacade.findAll();
        hashTabela = new HashMap();
        int pkPtBemHistorico;
        for (PtBemHistorico reg : listaPtBemHistorico)
        {
            pkPtBemHistorico = reg.getPkPtBemHistorico();

            hashTabela.put(pkPtBemHistorico, reg);
        }
    }

    
    public void create(PtBemHistorico reg)
    {
        ptBemHistoricoFacade.create(reg);
    }
    public void edit(PtBemHistorico reg)
    {
        ptBemHistoricoFacade.edit(reg);
    }

    public void remove(PtBemHistorico reg)
    {
        ptBemHistoricoFacade.remove(reg);
    }
    public List<PtBemHistorico> getListaPtBemHistoricos()
    {
        return listaPtBemHistorico;
    }
    
    public PtBemHistorico findPtBemHistorico(int pkPtBemHistorico)
    {
        return hashTabela.get(pkPtBemHistorico);
    }
    
}
