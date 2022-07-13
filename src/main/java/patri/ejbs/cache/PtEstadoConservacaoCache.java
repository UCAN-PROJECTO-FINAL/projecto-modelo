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
import ejbs.entities.PtEstadoConservacao;
import ejbs.facades.PtEstadoConservacaoFacade;

/**
 *
 * @author mdnext
 */
@Named
@ApplicationScoped
public class PtEstadoConservacaoCache implements Serializable {
    
    @EJB
    private PtEstadoConservacaoFacade ptEstadoConservacaoFacade;
    private List<PtEstadoConservacao> listaPtEstadoConservacao;
    private HashMap<Integer, PtEstadoConservacao> hashTabela;
    
     public PtEstadoConservacaoCache()
    {
    }

    /**
     *
     */
    @PostConstruct
    public void init()
    {
        listaPtEstadoConservacao = ptEstadoConservacaoFacade.findAll();
        hashTabela = new HashMap();
        int pkPtEstadoConservacao;
        for (PtEstadoConservacao reg : listaPtEstadoConservacao)
        {
            pkPtEstadoConservacao = reg.getPkPtEstadoConservacao();

            hashTabela.put(pkPtEstadoConservacao, reg);
        }
    }    
    
    public void create(PtEstadoConservacao reg)
    {
        ptEstadoConservacaoFacade.create(reg);
    }
    public void edit(PtEstadoConservacao reg)
    {
        ptEstadoConservacaoFacade.edit(reg);
    }

    public void remove(PtEstadoConservacao reg)
    {
        ptEstadoConservacaoFacade.remove(reg);
    }
    public List<PtEstadoConservacao> getListaPtEstadoConservacaos()
    {
        return listaPtEstadoConservacao;
    }
    
    public PtEstadoConservacao findPtEstadoConservacao(int pkPtEstadoConservacao)
    {
        return hashTabela.get(pkPtEstadoConservacao);
    }
    
}
