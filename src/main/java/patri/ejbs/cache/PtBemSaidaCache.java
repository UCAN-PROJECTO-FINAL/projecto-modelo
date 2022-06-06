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
import ejbs.entities.PtMarca;
import ejbs.facades.PtMarcaFacade;

/**
 *
 * @author mdnext
 */
@Named
@ApplicationScoped
public class PtBemSaidaCache implements Serializable{
    
    @EJB
    private PtMarcaFacade ptBemSaidaFacade;
    private List<PtMarca> listaPtMarca;
    private HashMap<Integer, PtMarca> hashTabela;
    
     public PtBemSaidaCache()
    {
    }

    /**
     *
     */
    @PostConstruct
    public void init()
    {
        listaPtMarca = ptBemSaidaFacade.findAll();
        hashTabela = new HashMap();
        int pkPtMarca;
        for (PtMarca reg : listaPtMarca)
        {
            pkPtMarca = reg.getPkPtMarca();

            hashTabela.put(pkPtMarca, reg);
        }
    }

    
    public void create(PtMarca reg)
    {
        ptBemSaidaFacade.create(reg);
    }
    public void edit(PtMarca reg)
    {
        ptBemSaidaFacade.edit(reg);
    }

    public void remove(PtMarca reg)
    {
        ptBemSaidaFacade.remove(reg);
    }
    public List<PtMarca> getListaPtMarcas()
    {
        return listaPtMarca;
    }
    
    public PtMarca findPtMarca(int pkPtMarca)
    {
        return hashTabela.get(pkPtMarca);
    }
    
}
