/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.cache;

import ejbs.entities.LocLocalidade;
import ejbs.facades.LocLocalidadeFacade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

/**
 *
 * @author KiamiSoft_ACosta
 */
@Named
@ApplicationScoped
public class LocLocalidadeCache implements Serializable
{

    @EJB
    private LocLocalidadeFacade localidadeFacade;
    private List<LocLocalidade> listaLocLocalidade;
    private HashMap<String, LocLocalidade> hashTabela;
    private List<LocLocalidade> listaLocLocalidadeProvincia;
    private List<LocLocalidade> listaLocLocalidadeMunicipio;
    private List<LocLocalidade> listaLocLocalidadeComuna;

    /**
     * Creates a new instance of PaisBean
     */
    public LocLocalidadeCache()
    {
    }

    /**
     *
     */
    @PostConstruct
    public void init()
    {
        listaLocLocalidade = localidadeFacade.findAllOrderByNome();
        listaLocLocalidadeProvincia = new ArrayList();
        listaLocLocalidadeMunicipio = new ArrayList();
        listaLocLocalidadeComuna = new ArrayList();

        hashTabela = new HashMap();
        String pkLocLocalidade;
        for (LocLocalidade reg : listaLocLocalidade)
        {
            pkLocLocalidade = reg.getPkLocLocalidade();

            hashTabela.put(pkLocLocalidade, reg);
        }
    }

    public void create(LocLocalidade reg)
    {
        localidadeFacade.create(reg);
    }

    public void edit(LocLocalidade reg)
    {
        localidadeFacade.edit(reg);
    }

    public void remove(LocLocalidade reg)
    {
        localidadeFacade.remove(reg);
    }

    public List<LocLocalidade> getListaPaisLocLocalidade()
    {
        List<LocLocalidade> resultList = new ArrayList();
        for (LocLocalidade reg : this.hashTabela.values())
        {
            if (reg.getFkLocLocalidade() == null)
            {
                resultList.add(reg);
            }
        }

        return resultList;
    }

    public List<LocLocalidade> getListaSonsByPkLocLocalidade(String pkLocLocalidade)
    {

        List<LocLocalidade> resultList = new ArrayList();
        for (LocLocalidade reg : this.hashTabela.values())
        {
            if (reg.getFkLocLocalidade() != null)
            //System.err.println(""+pkEstruturaFisica+"------"+reg.getFkEstruturaFisica().getPkEstruturaFisica());
            {
                if (reg.getFkLocLocalidade().getPkLocLocalidade().equals(pkLocLocalidade))
                {
                    resultList.add(reg);
                }
            }
        }

        return resultList;
    }

    public void getListaPaisByProvinciaLocLocalidade(String pais)
    {
        listaLocLocalidadeProvincia = new ArrayList();
        try
        {
            for (LocLocalidade reg : this.hashTabela.values())
            {
                if (reg.getFkLocLocalidade() != null && reg.getFkLocLocalidade().getPkLocLocalidade().equalsIgnoreCase(pais))
                {
                    listaLocLocalidadeProvincia.add(reg);
                }
            }
        } catch (Exception e)
        {
            System.out.println("e: " + e.getMessage());
        }

        //return resultList;
    }

    public void getListaProvinciaByMunicipioLocLocalidade(String provincia)
    {
        //List<LocLocalidade> resultList = new ArrayList();
        try
        {
            for (LocLocalidade reg : this.hashTabela.values())
            {
                if (reg.getFkLocLocalidade() != null && reg.getFkLocLocalidade().getPkLocLocalidade().equalsIgnoreCase(provincia))
                {
                    listaLocLocalidadeMunicipio.add(reg);
                }
            }
        } catch (Exception e)
        {
        }

        //return resultList;
    }

    public void getListaMunicipioByDistritoLocLocalidade(String municipio)
    {
        // List<LocLocalidade> resultList = new ArrayList();
        if (municipio == null || municipio.isEmpty())
        {
            return;
        }
        try
        {
            this.localidadeFacade.findAllDistritoOrderByNome().stream().forEach(x ->
            {
                if (x.getFkLocLocalidade().getPkLocLocalidade().equalsIgnoreCase(municipio))
                {
                    listaLocLocalidadeComuna.add(x);
                }

            });
        } catch (Exception e)
        {
        }

        // return resultList;
    }

    public boolean getDistritoLocLocalidade(String municipio)
    {
        if (municipio == null || municipio.isEmpty())
        {
            return false;
        }
        try
        {
            for (LocLocalidade x : localidadeFacade.findAllDistritoOrderByNome())
            {
                if (x.getEhDestrito() != null && x.getEhDestrito() == true && x.getFkLocLocalidade().getPkLocLocalidade().equalsIgnoreCase(municipio))
                {
                    return true;
                }
            }

        } catch (Exception e)
        {
            System.out.println("Erro: " + e.getMessage());
        }

        return false;
    }
    
    public LocLocalidade findLocLocalidade(String localidade)
    {
        for (Map.Entry<String, LocLocalidade> entry : hashTabela.entrySet()) {
            if (localidade.equalsIgnoreCase(entry.getKey())) {
                return entry.getValue();
            }
        }
        return null;
    }
    
    

    public List<LocLocalidade> getListaLocLocalidadeProvincia()
    {
        return listaLocLocalidadeProvincia;
    }

    public void setListaLocLocalidadeProvincia(List<LocLocalidade> listaLocLocalidadeProvincia)
    {
        this.listaLocLocalidadeProvincia = listaLocLocalidadeProvincia;
    }

    public List<LocLocalidade> getListaLocLocalidadeMunicipio()
    {
        return listaLocLocalidadeMunicipio;
    }

    public void setListaLocLocalidadeMunicipio(List<LocLocalidade> listaLocLocalidadeMunicipio)
    {
        this.listaLocLocalidadeMunicipio = listaLocLocalidadeMunicipio;
    }

    public List<LocLocalidade> getListaLocLocalidadeComuna()
    {
        return listaLocLocalidadeComuna;
    }

    public void setListaLocLocalidadeComuna(List<LocLocalidade> listaLocLocalidadeComuna)
    {
        this.listaLocLocalidadeComuna = listaLocLocalidadeComuna;
    }

    public List<LocLocalidade> getListaLocLocalidade()
    {
        return listaLocLocalidade;
    }

    public void setListaLocLocalidade(List<LocLocalidade> listaLocLocalidade)
    {
        this.listaLocLocalidade = listaLocLocalidade;
    }

   
    
    
}
