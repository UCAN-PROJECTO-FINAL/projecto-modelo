/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bbt.beans;

import localidade.ejbs.cache.LocLocalidadeCache;
import ejbs.entities.BbtAutoridade;
import ejbs.entities.BbtTipoAutoridade;
import ejbs.entities.LocLocalidade;
import ejbs.facades.BbtAutoridadeFacade;
import ejbs.facades.BbtTipoAutoridadeFacade;
import ejbs.facades.LocLocalidadeFacade;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.inject.Inject;

/**
 *
 * @author herman
 */
@Named(value = "bbtNovaAutoridadeBean")
@ViewScoped
public class BbtNovaAutoridadeBean implements Serializable
{

    @EJB
    private BbtAutoridadeFacade bbtAutoridadeFacade;

    @EJB
    private LocLocalidadeFacade locLocalidadeFacade;

    @EJB
    private BbtTipoAutoridadeFacade bbtTipoAutoridadeFacade;

    @Inject
    LocLocalidadeCache localidadeCache;


    
    

    private String designacao, pkPais, pkProvincia;
    private Integer pkBbtTipoAutoridade; 

    private List<LocLocalidade> paises;
    private List<LocLocalidade> provincias;

    private List<BbtTipoAutoridade> bbtTipoAutoridades;

    private BbtAutoridade bbtAutoridade;
    
    
    /**
     * Creates a new instance of BbtNovaAutoridadeBean
     */
    public BbtNovaAutoridadeBean()
    {

    }

    @PostConstruct
    public void init()
    {
        paises = localidadeCache.findAllPaisesOrderedByDesignacao();
        bbtTipoAutoridades = bbtTipoAutoridadeFacade.findAll();
        provincias = localidadeCache.findAllOrderedByNome(paises.get(0).getPkLocLocalidade());
    }

    //Business 
    public void salvar()
    {
        bbtAutoridade = new BbtAutoridade();
        
        bbtAutoridade.setDesignacao(designacao);
        bbtAutoridade.setFkBbtTipoAutoridade(bbtTipoAutoridadeFacade.find(pkBbtTipoAutoridade)); 
        bbtAutoridade.setFkLocLocalidade(locLocalidadeFacade.find(pkProvincia));
        
        bbtAutoridadeFacade.create(bbtAutoridade);
        System.err.println("Salvo com sucesso finalmente");
        
        
        
    }

    public void updateProvinciaLista()
    {
        this.provincias = localidadeCache.findAllOrderedByNome(pkPais);
        
    }

    //Getters and setters 
    public String getDesignacao()
    {
        return designacao;
    }

    public void setDesignacao(String designacao)
    {
        this.designacao = designacao;
    }

  

    public List<LocLocalidade> getPaises()
    {
        return paises;
    }

    public void setPaises(List<LocLocalidade> paises)
    {
        this.paises = paises;
    }

    public List<BbtTipoAutoridade> getBbtTipoAutoridades()
    {
        return bbtTipoAutoridades;
    }

    public void setBbtTipoAutoridades(List<BbtTipoAutoridade> bbtTipoAutoridades)
    {
        this.bbtTipoAutoridades = bbtTipoAutoridades;
    }

    public String getPkPais()
    {
        return pkPais;
    }

    public void setPkPais(String pkPais)
    {
        this.pkPais = pkPais;
    }

    public List<LocLocalidade> getProvincias()
    {
        return provincias;
    }

    public void setProvincias(List<LocLocalidade> provincias)
    {
        this.provincias = provincias;
    }

    public Integer getPkBbtTipoAutoridade()
    {
        return pkBbtTipoAutoridade;
    }

    public void setPkBbtTipoAutoridade(Integer pkBbtTipoAutoridade)
    {
        this.pkBbtTipoAutoridade = pkBbtTipoAutoridade;
    }

    public String getPkProvincia()
    {
        return pkProvincia;
    }

    public void setPkProvincia(String pkProvincia)
    {
        this.pkProvincia = pkProvincia;
    }
    
    
    

}
