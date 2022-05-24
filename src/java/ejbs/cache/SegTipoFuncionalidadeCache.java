/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.cache;

import ejbs.cache.utils.TabelaPadraoCacheUtils;
import ejbs.entities.SegTipoFuncionalidade;
import ejbs.facades.SegTipoFuncionalidadeFacade;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import seg.utils.SegTipoFuncionalidadeEnum;

/**
 *
 * @author aires
 */
@Named(value = "segTipoFuncionalidadeCache")
@ApplicationScoped
public class SegTipoFuncionalidadeCache extends TabelaPadraoCacheUtils<SegTipoFuncionalidade>
{

    @EJB
    private SegTipoFuncionalidadeFacade segTipoFuncionalidadeFacade;

//    private List<SegTipoFuncionalidade> segTipoFuncionalidadeLista;
//    private ConcurrentHashMap<Integer, SegTipoFuncionalidade> tabelaIntegerObject;
    /**
     * Creates a new instance of SegTipoFuncionalidadeCache
     */
    public SegTipoFuncionalidadeCache()
    {
    }

    @PostConstruct
    @Override
    public void init()
    {
//System.err.println("0: SegTipoFuncionalidadeCache.init()");

        SegTipoFuncionalidade segTipoFuncionalidade;

        List<SegTipoFuncionalidade> segTipoFuncionalidadeLista = new ArrayList();

        int pkSegTipoFuncionalidade;
        for (SegTipoFuncionalidadeEnum estado : SegTipoFuncionalidadeEnum.values())
        {
//System.err.println("1: SegTipoFuncionalidadeCache.init()\testado.inidice: " + estado.toInteger() + ", " + estado.getNome());            
            pkSegTipoFuncionalidade = estado.toInteger();
            segTipoFuncionalidade = this.segTipoFuncionalidadeFacade.find(pkSegTipoFuncionalidade);
            if (segTipoFuncionalidade != null)
            {
//System.err.println("2: SegTipoFuncionalidadeCache.init()\testado.inidice: " + estado.toInteger() + ", " + estado.getNome());                
                segTipoFuncionalidade.setNome(estado.getNome());
                segTipoFuncionalidadeFacade.edit(segTipoFuncionalidade);
            }
            else
            {
//System.err.println("3: SegTipoFuncionalidadeCache.init()\testado.inidice: " + estado.toInteger() + ", " + estado.getNome());                
                segTipoFuncionalidade = new SegTipoFuncionalidade(pkSegTipoFuncionalidade);
                segTipoFuncionalidade.setNome(estado.getNome());
                segTipoFuncionalidadeFacade.create(segTipoFuncionalidade);
//                segTipoFuncionalidade = this.segTipoFuncionalidadeCache.find(pkSegTipoFuncionalidade);
            }
            tabelaIntegerObject.put(pkSegTipoFuncionalidade, segTipoFuncionalidade);
            segTipoFuncionalidadeLista.add(segTipoFuncionalidade);
//System.err.println("4: SegTipoFuncionalidadeCache.init()\testado.inidice: " + estado.toInteger() + ", " + estado.getNome());            
        }
        this.tabelaStringListObject.put("All", segTipoFuncionalidadeLista);
//System.err.println("5: SegTipoFuncionalidadeCache.init()");
    }

    // implement abstract Methods
    @Override
    public int getPKEntity(Object ob)
    {
        SegTipoFuncionalidade stf = (SegTipoFuncionalidade)ob;
        return stf.getPkSegTipoFuncionalidade();
    }

    // CRUD Methods 
    public boolean create(SegTipoFuncionalidade reg)
    {
        try
        {
            this.segTipoFuncionalidadeFacade.create(reg);
            put(reg);
            return true;
        }
        catch (Exception ex)
        {
            return false;
        }
    }

    public boolean edit(SegTipoFuncionalidade reg)
    {
        try
        {
            this.segTipoFuncionalidadeFacade.edit(reg);
            this.tabelaIntegerObject.put(reg.getPkSegTipoFuncionalidade(), reg);
            this.restart();
//            addToTiposDocumentos(reg);
            return true;
        }
        catch (Exception ex)
        {
            return false;
        }
    }

    @Override
    public boolean remove(SegTipoFuncionalidade reg)
    {
        boolean rt = false;
        try
        {
            rt = tabelaIntegerObject.remove(reg.getPkSegTipoFuncionalidade()) != null;
//            removeFromEstadosCandidatura(reg);
            this.segTipoFuncionalidadeFacade.remove(reg);
            this.restart();
        }
        catch (Exception ex)
        {
        }
        return rt;
    }

    public List<SegTipoFuncionalidade> findAllOrderedByNome()
    {
        List<SegTipoFuncionalidade> allOrderedByNome = tabelaStringListObject.get("AllOrderedByNome");

        if (allOrderedByNome != null)
        {
            return allOrderedByNome;
        }

        allOrderedByNome = findAll();
//////System.err.println("0: SegTipoFuncionalidadeCache.findAll()");

        ////System.err.println("1: SegTipoFuncionalidadeCache.findAllOrderedByNome(int)");
        Collections.sort(allOrderedByNome, Comparator.comparing(SegTipoFuncionalidade::getNome));
        ////System.err.println("2: SegTipoFuncionalidadeCache.findAllOrderedByNome(int)");

        tabelaStringListObject.put("AllOrderedByNome", allOrderedByNome);
        return allOrderedByNome;
    }

    public SegTipoFuncionalidade findByNome(String nome)
    {
        nome = nome.trim();

        String chave = this.geraChave("ByNome", nome);
        SegTipoFuncionalidade segTipoFuncionalidadeByNome = (SegTipoFuncionalidade) tabelaStringObject.get(chave);

        if (segTipoFuncionalidadeByNome != null)
        {
            return segTipoFuncionalidadeByNome;
        }
        //////System.err.println("0: SegTipoFuncionalidadeCache.findByNome(String)\nnome: " + nome);	

        List<SegTipoFuncionalidade> segTipoFuncionalidadeLista = findAll();
        for (SegTipoFuncionalidade estado : segTipoFuncionalidadeLista)
        {
//////System.err.println("2.1: SegTipoFuncionalidadeCache.findByNome()");   
            if (estado.getNome().equals(nome))
            {
                tabelaStringObject.get(chave);
                return estado;
            }
//////System.err.println("5: SegTipoFuncionalidadeCache.findByNome(int)");                
        }
        return null;
    }

    // Business Methods
    public String toString(SegTipoFuncionalidade reg)
    {
        return "[" + reg.getPkSegTipoFuncionalidade() + ", " + reg.getNome() + "]";
    }

    public boolean evalido(Integer pkSegTipoFuncionalidade)
    {
        return (pkSegTipoFuncionalidade == null || pkSegTipoFuncionalidade < 1) ? false : (find(pkSegTipoFuncionalidade) != null);
    }

}
