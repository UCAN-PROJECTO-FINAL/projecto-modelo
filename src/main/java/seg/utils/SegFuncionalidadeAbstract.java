/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seg.utils;

import ejbs.cache.SegTipoFuncionalidadeCache;
import ejbs.entities.SegFuncionalidade;
import ejbs.facades.SegFuncionalidadeFacade;
import java.io.Serializable;
import javax.transaction.UserTransaction;
import org.primefaces.event.NodeCollapseEvent;
import org.primefaces.event.NodeExpandEvent;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.event.NodeUnselectEvent;

/**
 *
 * @author aires
 */
public abstract class SegFuncionalidadeAbstract implements Serializable
{

    protected SegTreeFuncionalidadesAbstract seqTreeFuncionalidades;

    protected SegFuncionalidadeAbstract()
    {
    }
    
    public abstract SegFuncionalidadeFacade getSegFuncionalidadeFacade();
    
    public abstract SegTipoFuncionalidadeCache getSegTipoFuncionalidadeCache();

    public abstract UserTransaction getUserTransaction();

    public void inicializar()
    {
        System.err.println("0: SegFuncionalidadeAbstract.inicializar()\tgetSegTipoFuncionalidadeCache(): "
            + (getSegTipoFuncionalidadeCache() == null ? "null" : "not null"));
        initSeqTreeFuncionalidades();
        System.err.println("1: SegFuncionalidadeAbstract.inicializar()\tgetSegTipoFuncionalidadeCache(): "
            + (getSegTipoFuncionalidadeCache() == null ? "null" : "not null"));
        System.err.println("2: SegFuncionalidadeAbstract.inicializar()\tseqTreeFuncionalidades.getSegTipoFuncionalidadeCache(): "
            + (seqTreeFuncionalidades.getSegTipoFuncionalidadeCache() == null ? "null" : "not null"));

        gerarSeqTreeFuncionalidades();
        System.err.println("3: SegFuncionalidadeAbstract.inicializar()\tgetSegTipoFuncionalidadeCache(): "
            + (getSegTipoFuncionalidadeCache() == null ? "null" : "not null"));
    }

    public void gerarSeqTreeFuncionalidades()
    {
        System.err.println("0: SegFuncionalidadeAbstract.gerarSeqTreeFuncionalidades()\tgetSegTipoFuncionalidadeCache(): "
            + (getSegTipoFuncionalidadeCache() == null ? "null" : "not null"));
        System.err.println("1: SegFuncionalidadeAbstract.gerarSeqTreeFuncionalidades()\tseqTreeFuncionalidades.getSegTipoFuncionalidadeCache(): "
            + (seqTreeFuncionalidades.getSegTipoFuncionalidadeCache() == null ? "null" : "not null"));
        seqTreeFuncionalidades.initRoot();
        System.err.println("2: SegFuncionalidadeAbstract.gerarSeqTreeFuncionalidades()");
    }

    public void initSeqTreeFuncionalidades()
    {
        System.err.println("0: SegFuncionalidadeAbstract.initSeqTreeFuncionalidades()\tgetSegTipoFuncionalidadeCache(): "
            + (getSegTipoFuncionalidadeCache() == null ? "null" : "not null"));

        seqTreeFuncionalidades = new SegTreeFuncionalidadesAbstract(this.getSegFuncionalidadeFacade(), getSegTipoFuncionalidadeCache())
        {
            @Override
            public void onNodeSelect(NodeSelectEvent event)
            {
                super.onNodeSelect(event);
            }

            @Override
            public void onNodeUnSelect(NodeUnselectEvent event)
            {
                //System.err.println("Node Data ::" + event.getTreeNode().getData() + " :: UnSelected");
            }

            @Override
            public void onNodeExpand(NodeExpandEvent event)
            {
                String node = ((SegFuncionalidade) event.getTreeNode().getData()).getDescricao();
            }

            @Override
            public void onNodeCollapse(NodeCollapseEvent event)
            {
                String node = ((SegFuncionalidade) event.getTreeNode().getData()).getDescricao();
            }

            @Override
            public void initSelectedNode()
            {
                //System.err.println("0: SegFuncionalidadeAbstract.inicializarTree()");
            }

        };
        System.err.println("1: SegFuncionalidadeAbstract.initSeqTreeFuncionalidades()\tseqTreeFuncionalidades.getSegTipoFuncionalidadeCache(): "
            + (seqTreeFuncionalidades.getSegTipoFuncionalidadeCache() == null ? "null" : "not null"));

    }

    public SegTreeFuncionalidadesAbstract getSeqTreeFuncionalidades()
    {
        return seqTreeFuncionalidades;
    }

    public void setSeqTreeFuncionalidades(SegTreeFuncionalidadesAbstract seqTreeFuncionalidades)
    {
        this.seqTreeFuncionalidades = seqTreeFuncionalidades;
    }

}
