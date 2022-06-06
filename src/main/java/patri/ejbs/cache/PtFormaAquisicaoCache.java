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
import ejbs.entities.PtFormaAquisicao;
import ejbs.facades.PtFormaAquisicaoFacade;

/**
 *
 * @author mdnext
 */
@Named
@ApplicationScoped
public class PtFormaAquisicaoCache implements Serializable {
    
    @EJB
    private PtFormaAquisicaoFacade ptFormaAquisicaoFacade;
    private List<PtFormaAquisicao> listaPtFormaAquisicao;
    private HashMap<Integer, PtFormaAquisicao> hashTabela;
    
     public PtFormaAquisicaoCache()
    {
    }

    /**
     *
     */
    @PostConstruct
    public void init()
    {
        listaPtFormaAquisicao = ptFormaAquisicaoFacade.findAll();
        hashTabela = new HashMap();
        int pkPtFormaAquisicao;
        for (PtFormaAquisicao reg : listaPtFormaAquisicao)
        {
            pkPtFormaAquisicao = reg.getPkPtFormaAquisicao();

            hashTabela.put(pkPtFormaAquisicao, reg);
        }
    }    
    
    public void create(PtFormaAquisicao reg)
    {
        ptFormaAquisicaoFacade.create(reg);
    }
    public void edit(PtFormaAquisicao reg)
    {
        ptFormaAquisicaoFacade.edit(reg);
    }

    public void remove(PtFormaAquisicao reg)
    {
        ptFormaAquisicaoFacade.remove(reg);
    }
    public List<PtFormaAquisicao> getListaPtFormaAquisicaos()
    {
        return listaPtFormaAquisicao;
    }
    
    public PtFormaAquisicao findPtFormaAquisicao(int pkPtFormaAquisicao)
    {
        return hashTabela.get(pkPtFormaAquisicao);
    }
    
}
