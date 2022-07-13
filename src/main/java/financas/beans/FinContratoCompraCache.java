/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package financas.beans;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import ejbs.entities.FinContratoCompra;
import ejbs.facades.FinContratoCompraFacade;

/**
 *
 * @author mdnext
 */
@Named
@ApplicationScoped
public class FinContratoCompraCache implements Serializable {
    
    @EJB
    private FinContratoCompraFacade FinContratoCompraFacade;
    private List<FinContratoCompra> listaFinContratoCompra;
    private HashMap<Integer, FinContratoCompra> hashTabela;
    
     public FinContratoCompraCache()
    {
    }

    /**
     *
     */
    @PostConstruct
    public void init()
    {
        listaFinContratoCompra = FinContratoCompraFacade.findAll();
        hashTabela = new HashMap();
        int pkFinContratoCompra;
        for (FinContratoCompra reg : listaFinContratoCompra)
        {
            pkFinContratoCompra = reg.getPkPtContratoCompra();

            hashTabela.put(pkFinContratoCompra, reg);
        }
    }    
    public int getIdContratoCompra(){
        return FinContratoCompraFacade.count() + 1;
}
    public void create(FinContratoCompra reg)
    {
        FinContratoCompraFacade.create(reg);
    }
    public void edit(FinContratoCompra reg)
    {
        FinContratoCompraFacade.edit(reg);
    }

    public void remove(FinContratoCompra reg)
    {
        FinContratoCompraFacade.remove(reg);
    }
    public List<FinContratoCompra> getListaFinContratoCompras()
    {
        return listaFinContratoCompra;
    }
    
    public FinContratoCompra findFinContratoCompra(int pkFinContratoCompra)
    {
        return hashTabela.get(pkFinContratoCompra);
    }
    
    public int findListFinContratoCompraNextFileIndex()
    {
        return FinContratoCompraFacade.findAll().size() + 1;
    }
    
    
}
