/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package localidade.ejbs.cache;

import ejbs.entities.LocLocalidade;
import ejbs.facades.LocLocalidadeFacade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import localidade.utils.Defs;
import java.util.Collections;

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
    private List<LocLocalidade> localidadeLista;

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
    
    public static List<LocLocalidade> appendIndefinidos(List<LocLocalidade> lista)
    {
        for (int i = 0; i < lista.size(); i++)
        {
            if (lista.get(i).getDesignacao().equalsIgnoreCase("Indefinido")
                    || lista.get(i).getDesignacao().equalsIgnoreCase("Indefinida"))
            {
                lista.add(lista.get(i));
                lista.remove(lista.get(i));
            }
        }
        return lista;
    }
    
    public List<LocLocalidade> findAllPaisesOrderedByDesignacao()
    {
        List<LocLocalidade> localidadeFilhos = new ArrayList<>();
       
        for (LocLocalidade l : this.localidadeLista)
        {
    
            if (l.getFkLocLocalidade() == null)
            {
                localidadeFilhos.add(l);
            }
        }

        if (localidadeFilhos.size() > 1)
        {
            Collections.sort(localidadeFilhos, Comparator.comparing(LocLocalidade::getDesignacao));
        }

        appendIndefinidos(localidadeFilhos);
        return localidadeFilhos;
    }
    
      public List<LocLocalidade> findAllOrderedByNome(String pkPai)
    {
        List<LocLocalidade> localidadeFilhos = new ArrayList<>();
        LocLocalidade pai;
        for (LocLocalidade l : this.localidadeLista)
        {
            pai = l.getFkLocLocalidade();
            if (pai == null)
            {
                continue;
            }
            if (pai.getPkLocLocalidade().equals(pkPai))
            {
                localidadeFilhos.add(l);
            }
        }

        if (localidadeFilhos.size() > 1)
        {
            Collections.sort(localidadeFilhos, Comparator.comparing(LocLocalidade::getDesignacao));
        }

        appendIndefinidos(localidadeFilhos);
        return localidadeFilhos;
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
    
    //_______________________________________________________________________________________
    
    public List<LocLocalidade> getSortListaSonsByPkLocLocalidade(String pkLocLocalidade)
    {
        LocLocalidade indefinido = null;
        List<LocLocalidade> resultList = new ArrayList();
        for (LocLocalidade reg : this.hashTabela.values())
        {
            if (reg.getFkLocLocalidade() != null) //System.err.println(""+pkEstruturaFisica+"------"+reg.getFkEstruturaFisica().getPkEstruturaFisica());
            {
                if (reg.getFkLocLocalidade().getPkLocLocalidade().equals(pkLocLocalidade))
                {
                    if (reg.getDesignacao().equalsIgnoreCase(Defs.INDEFINIDO)
                        || reg.getDesignacao().equalsIgnoreCase(Defs.INDEFINIDA))
                    {
                        indefinido = reg;
                    }
                    else
                    {
                        resultList.add(reg);
                    }

                }
            }
        }
        Collections.sort(resultList, (o1, o2) ->
        {
            LocLocalidade loc1 = (LocLocalidade) o1,
                loc2 = (LocLocalidade) o2;
            return loc1.getDesignacao().compareToIgnoreCase(loc2.getDesignacao());
        });
        if (indefinido != null)
        {
            resultList.add(indefinido);
        }
        return resultList;
    }

    public LocLocalidade getIndefinidoSonByPkLocLocalidade(String pkLocLocalidade)
    {
        for (LocLocalidade reg : this.hashTabela.values())
        {
            if (reg.getFkLocLocalidade() != null) //System.err.println(""+pkEstruturaFisica+"------"+reg.getFkEstruturaFisica().getPkEstruturaFisica());
            {
                if (reg.getFkLocLocalidade().getPkLocLocalidade().equals(pkLocLocalidade))
                {
                    if (reg.getDesignacao().equalsIgnoreCase(Defs.INDEFINIDO)
                        || reg.getDesignacao().equalsIgnoreCase(Defs.INDEFINIDA))
                    {
                        return reg;
                    }

                }
            }
        }
        return null;
    }

    public String findPkLocalidadeSonByPkLocLocalidadePaiByDesignacaoSon(String pkLocLocalidadePai, String designacaoSun)
    {
        for (LocLocalidade reg : this.hashTabela.values())
        {
            if (reg.getFkLocLocalidade() != null) //System.err.println(""+pkEstruturaFisica+"------"+reg.getFkEstruturaFisica().getPkEstruturaFisica());
            {
                if (reg.getFkLocLocalidade().getPkLocLocalidade().equals(pkLocLocalidadePai))
                {
                    if (reg.getDesignacao().equalsIgnoreCase(designacaoSun))
                    {
                        return reg.getPkLocLocalidade();
                    }

                }
            }
        }
        return null;
    }

    public LocLocalidade findLocalidadeByPkLocLocalidade(String PkLocLocalidade)
    {
        for (LocLocalidade reg : this.hashTabela.values())
        {
            if (reg.getFkLocLocalidade() != null) //System.err.println(""+pkEstruturaFisica+"------"+reg.getFkEstruturaFisica().getPkEstruturaFisica());
            {
                if (reg.getPkLocLocalidade().equals(PkLocLocalidade))
                {
                    return reg;
                }
            }
        }
        return null;
    }
    
    //____________________________________________________________________________________________


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