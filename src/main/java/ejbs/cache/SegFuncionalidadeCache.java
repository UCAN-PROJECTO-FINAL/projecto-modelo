/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.cache;

import ejbs.cache.utils.InitInterface;
import ejbs.entities.SegFuncionalidade;
import ejbs.facades.SegFuncionalidadeFacade;
import ejbs.facades.SegFuncionalidadeFormFacade;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.UserTransaction;
import seg.utils.SegFuncionalidadeAbstract;
import seg.utils.SegTipoFuncionalidadeEnum;

/**
 *
 * @author aires
 */
@Named(value = "segFuncionalidadeCache")
@ApplicationScoped
public class SegFuncionalidadeCache extends SegFuncionalidadeAbstract implements InitInterface
{

    @EJB
    private SegFuncionalidadeFacade segFuncionalidadeFacade;

    @EJB
    private SegFuncionalidadeFormFacade segFuncionalidadeFormFacade;

    @Inject
    private SegTipoFuncionalidadeCache segTipoFuncionalidadeCache;

    @Resource
    private UserTransaction userTransaction;

    /**
     * Creates a new instance of SegFuncionalidadeCadastrarBean
     */
    public SegFuncionalidadeCache()
    {
    }

    @PostConstruct
    @Override
    public void init()
    {
        //System.err.println("0: SegFuncionalidadeCache.init()");
        inicializar();
        //System.err.println("1: SegFuncionalidadeCache.init()");
    }

    public List<SegFuncionalidade> findAll()
    {
        return this.seqTreeFuncionalidades.getSegFuncionalidades();
    }

    public SegFuncionalidade find(int pkSegFuncionalidade)
    {
        return this.seqTreeFuncionalidades.getTabelaSegFuncionalidades().get(pkSegFuncionalidade);
    }

    public boolean saoParecidos(SegFuncionalidade s1, SegFuncionalidade s2)
    {
//System.err.println("0: SegFuncionalidadeCache.saoParecidos()\ts1: "
//            + segFuncionalidadeFacade.toString(s1) + "\ts2: " + 
//                    segFuncionalidadeFacade.toString(s2));        
        if (s1.getFkSegTipoFuncionalidade().getPkSegTipoFuncionalidade().intValue() != s2.getFkSegTipoFuncionalidade().getPkSegTipoFuncionalidade().intValue())
        {
//System.err.println("1: SegFuncionalidadeCache.saoParecidos()\ts1: "
//            + segFuncionalidadeFacade.toString(s1) + "\ts2: " + 
//                    segFuncionalidadeFacade.toString(s2));            
            return false;
        }

        SegFuncionalidade seg1FuncionalidadePai = s1.getFkSegFuncionalidadePai();
        SegFuncionalidade seg2FuncionalidadePai = s2.getFkSegFuncionalidadePai();
        if ((seg1FuncionalidadePai == null) && (seg2FuncionalidadePai != null))
        {
//System.err.println("2: SegFuncionalidadeCache.saoParecidos()\ts1: "
//            + segFuncionalidadeFacade.toString(s1) + "\ts2: " + 
//                    segFuncionalidadeFacade.toString(s2));            
            return false;
        }
        if ((seg2FuncionalidadePai == null) && (seg1FuncionalidadePai != null))
        {
//System.err.println("3: SegFuncionalidadeCache.saoParecidos()\ts1: "
//            + segFuncionalidadeFacade.toString(s1) + "\ts2: " + 
//                    segFuncionalidadeFacade.toString(s2));            
            return false;
        }
        if ((seg1FuncionalidadePai != null) && (seg2FuncionalidadePai != null))
        {
//System.err.println("4: SegFuncionalidadeCache.saoParecidos()\ts1: "
//            + segFuncionalidadeFacade.toString(s1) + "\ts2: " + 
//                    segFuncionalidadeFacade.toString(s2));            
            if (seg1FuncionalidadePai.getPkSegFuncionalidade().intValue() != seg2FuncionalidadePai.getPkSegFuncionalidade().intValue())
            {
//System.err.println("5: SegFuncionalidadeCache.saoParecidos()\ts1: "
//            + segFuncionalidadeFacade.toString(s1) + "\ts2: " + 
//                    segFuncionalidadeFacade.toString(s2));                
                return false;
            }
        }
//System.err.println("6: SegFuncionalidadeCache.saoParecidos()\ts1: "
//            + segFuncionalidadeFacade.toString(s1) + "\ts2: " + 
//                    segFuncionalidadeFacade.toString(s2));
        return s1.getDescricao().trim().equals(s2.getDescricao().trim());
    }

    public SegFuncionalidade temDuplicado(List<SegFuncionalidade> segFuncionalidades, SegFuncionalidade segFuncionalidade)
    {
//System.err.println("0: SegFuncionalidadeCache.temDuplicado()\tsegFuncionalidade: "
//            + segFuncionalidadeFacade.toString(segFuncionalidade));
        for (SegFuncionalidade s : segFuncionalidades)
        {
//System.err.println("1: SegFuncionalidadeCache.temDuplicado()\tsegFuncionalidade: "
//            + segFuncionalidadeFacade.toString(segFuncionalidade) + "\ts: " + 
//                    segFuncionalidadeFacade.toString(s));
            
            if (saoParecidos(s, segFuncionalidade))
            {
//System.err.println("2: SegFuncionalidadeCache.temDuplicado()\tsegFuncionalidade: "
//            + segFuncionalidadeFacade.toString(segFuncionalidade) + "\ts: " + 
//                    segFuncionalidadeFacade.toString(s));
                
                return s;
            }
//System.err.println("3: SegFuncionalidadeCache.temDuplicado()\tsegFuncionalidade: "
//            + segFuncionalidadeFacade.toString(segFuncionalidade) + "\ts: " + 
//                    segFuncionalidadeFacade.toString(s));            
        }
//System.err.println("4: SegFuncionalidadeCache.temDuplicado()\tsegFuncionalidade: "
//            + segFuncionalidadeFacade.toString(segFuncionalidade));        
        return null;
    }

    public boolean contains(List<SegFuncionalidade> lista, SegFuncionalidade segFuncionalidade)
    {
        int pkSegFuncionalidade = segFuncionalidade.getPkSegFuncionalidade();
        for (SegFuncionalidade segFuncionalidadeTmp : lista)
        {
            if (segFuncionalidadeTmp.getPkSegFuncionalidade() == pkSegFuncionalidade)
            {
                return true;
            }
        }
        return false;
    }

    public List<SegFuncionalidade> findListaSegFuncionalidadeFilhosComPai(int pkIdFuncionalidade)
    {
        //System.err.println("0: SegFuncionalidadeCache.findListaSegFuncionalidadeFilhosComPai()");
        List<SegFuncionalidade> lista = new ArrayList();
        List<SegFuncionalidade> segFuncionalidades = this.findAll();
        //System.err.println("0: SegFuncionalidadeCache.findListaSegFuncionalidadeFilhosComPai()");
        Integer pkIdFuncionalidadePai;
        SegFuncionalidade segFuncionalidadePai;
        for (SegFuncionalidade segFuncionalidade : segFuncionalidades)
        {
            segFuncionalidadePai = segFuncionalidade.getFkSegFuncionalidadePai();
            if (segFuncionalidadePai == null)
            {
                continue;
            }
            pkIdFuncionalidadePai = segFuncionalidadePai.getPkSegFuncionalidade();
            if ((pkIdFuncionalidadePai == null) || (pkIdFuncionalidadePai == 0))
            {
                continue;
            }
            if (pkIdFuncionalidadePai == pkIdFuncionalidade)
            {
                if (!contains(lista, segFuncionalidade))
                {
                    lista.add(segFuncionalidade);
                }
            }
        }
        return lista;
    }

    public List<SegFuncionalidade> findListaSegFuncionalidadeFilhosSemPai()
    {
//System.err.println("0: SegFuncionalidadeCache.findListaSegFuncionalidadeFilhosSemPai()");      
        List<SegFuncionalidade> lista = new ArrayList();
        List<SegFuncionalidade> segFuncionalidades = this.findAll();
//System.err.println("1: SegFuncionalidadeCache.findListaSegFuncionalidadeFilhos()\tsegFuncionalidades: " + 
//            (segFuncionalidades == null ? "null" : segFuncionalidades.size()));
        SegFuncionalidade segFuncionalidadePai;
        for (SegFuncionalidade segFuncionalidade : segFuncionalidades)
        {
            segFuncionalidadePai = segFuncionalidade.getFkSegFuncionalidadePai();
//System.err.println("2: SegFuncionalidadeCache.findListaSegFuncionalidadeFilhosSemPai()\tsegFuncionalidade: " + segFuncionalidade.getDescricao());            
            if ((segFuncionalidadePai == null) || this.segFuncionalidadeFacade.isRoot(segFuncionalidadePai))
//            if (segFuncionalidade.getFkSegFuncionalidadePai() == null)
            {
//System.err.println("3: SegFuncionalidadeCache.findListaSegFuncionalidadeFilhosSemPai()");                
                if (!contains(lista, segFuncionalidade))
                {
//System.err.println("4: SegFuncionalidadeCache.findListaSegFuncionalidadeFilhosSemPai()");                    
                    lista.add(segFuncionalidade);
                }
//System.err.println("5: SegFuncionalidadeCache.findListaSegFuncionalidadeFilhosSemPai()");                
            }
        }
//System.err.println("6: SegFuncionalidadeCache.findListaSegFuncionalidadeFilhos()\tlista: " + 
//            (lista == null ? "null" : lista.size()));        
        return lista;
    }

    public List<SegFuncionalidade> findListaSegFuncionalidadeFilhos(SegFuncionalidade segFuncionalidade)
    {
        List<SegFuncionalidade> segFuncionalidadeFilhos;
        //System.err.println("0: SegFuncionalidadeCache.findListaSegFuncionalidadeFilhos(SegFuncionalidade)\tpkIdFuncionalidade: " + segFuncionalidade.getPkSegFuncionalidade());
        SegFuncionalidade segFuncionalidadePai = segFuncionalidade.getFkSegFuncionalidadePai();
        switch (SegTipoFuncionalidadeEnum.fromSegTipoFuncionalidade(segFuncionalidade.getFkSegTipoFuncionalidade()))
        {
            case FORM:
                //System.err.println("1: SegFuncionalidadeCache.findListaSegFuncionalidadeFilhos(SegFuncionalidade)\npkIdFuncionalidade: "
//                    + segFuncionalidade.getPkSegFuncionalidade() + "\t"
//                    + (segFuncionalidadePai == null ? "null" : segFuncionalidadePai.getDescricao()));
                if (!this.segFuncionalidadeFacade.isRoot(segFuncionalidadePai))
                {
                    //System.err.println("2: SegFuncionalidadeCache.findListaSegFuncionalidadeFilhos(SegFuncionalidade)\tpkIdFuncionalidade: " + segFuncionalidade.getPkSegFuncionalidade());
                    return new ArrayList();
                }
                break;
            case MENUITEM_FORM:
            case BOTAO_FORM:
            case HYPERLINK_FORM:
                //System.err.println("3: SegFuncionalidadeCache.findListaSegFuncionalidadeFilhos(SegFuncionalidade)\tpkIdFuncionalidade: " + segFuncionalidade.getPkSegFuncionalidade());
                return this.segFuncionalidadeFacade.sort(segFuncionalidadeFormFacade.findRedirectedForms(segFuncionalidade));
            default:
                //System.err.println("4: SegFuncionalidadeCache.findListaSegFuncionalidadeFilhos(SegFuncionalidade)\tpkIdFuncionalidade: " + segFuncionalidade.getPkSegFuncionalidade());
                break;
        }

        /*
        int pkIdFuncionalidade = segFuncionalidade.getPkSegFuncionalidade();
        
        //System.err.println("5: SegFuncionalidadeFacade.findListaSegFuncionalidadeFilhos(SegFuncionalidade)\tpkIdFuncionalidade: " + pkIdFuncionalidade);
        Query q;
        if (pkIdFuncionalidade != 0)
        {
            //System.err.println("6: SegFuncionalidadeFacade.findListaSegFuncionalidadeFilhos(SegFuncionalidade)\tpkIdFuncionalidade: " + pkIdFuncionalidade);
            q = em.createQuery("SELECT a FROM SegFuncionalidade a WHERE a.fkSegFuncionalidadePai.pkSegFuncionalidade = :pkIdFuncionalidade ORDER BY a.descricao");
            q.setParameter("pkIdFuncionalidade", pkIdFuncionalidade);
        }
        else
        {
            //System.err.println("7: SegFuncionalidadeFacade.findListaSegFuncionalidadeFilhos(SegFuncionalidade)\tpkIdFuncionalidade: " + segFuncionalidade.getPkSegFuncionalidade());
            q = em.createQuery("SELECT a FROM SegFuncionalidade a WHERE a.fkSegFuncionalidadePai = null ORDER BY a.descricao");
        }

        List<SegFuncionalidade> lista = q.getResultList();
        //System.err.println("7: SegFuncionalidadeFacade.findListaSegFuncionalidadeFilhos(SegFuncionalidade)\tlista: "
            + ListUtils.toString(lista, (reg) -> this.toString(reg)));
        return this.sort(lista);

         */
        int pkIdFuncionalidade = segFuncionalidade.getPkSegFuncionalidade();
        //System.err.println("5: SegFuncionalidadeCache.findListaSegFuncionalidadeFilhos()\tpkIdFuncionalidade: " + pkIdFuncionalidade);
        if (pkIdFuncionalidade != 0)
        {
            //System.err.println("5.1: SegFuncionalidadeCache.findListaSegFuncionalidadeFilhos()\tpkIdFuncionalidade: " + pkIdFuncionalidade);
            segFuncionalidadeFilhos = this.findListaSegFuncionalidadeFilhosComPai(pkIdFuncionalidade);
        }
        else
        {
            //System.err.println("5.2: SegFuncionalidadeCache.findListaSegFuncionalidadeFilhos()\tpkIdFuncionalidade: " + pkIdFuncionalidade);
            segFuncionalidadeFilhos = this.findListaSegFuncionalidadeFilhosSemPai();
        }
        //System.err.println("5.3: SegFuncionalidadeCache.findListaSegFuncionalidadeFilhos()\tsegFuncionalidadeFilhos: " + 
//            (segFuncionalidadeFilhos == null ? "null" : segFuncionalidadeFilhos.size()));
        return this.segFuncionalidadeFacade.sort(segFuncionalidadeFilhos);
    }

    // Getters and Setters
    @Override
    public SegFuncionalidadeFacade getSegFuncionalidadeFacade()
    {
        return segFuncionalidadeFacade;
    }

    public void setSegFuncionalidadeFacade(SegFuncionalidadeFacade segFuncionalidadeFacade)
    {
        this.segFuncionalidadeFacade = segFuncionalidadeFacade;
    }

    @Override
    public SegTipoFuncionalidadeCache getSegTipoFuncionalidadeCache()
    {
        return segTipoFuncionalidadeCache;
    }

    @Override
    public UserTransaction getUserTransaction()
    {
        return userTransaction;
    }
}
