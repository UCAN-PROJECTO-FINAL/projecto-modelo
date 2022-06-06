/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.facades;

import ejbs.entities.SegFuncionalidade;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import seg.utils.SegTipoFuncionalidadeEnum;
import ejbs.cache.SegTipoFuncionalidadeCache;
import ejbs.entities.SegFuncionalidade;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import seg.utils.Defs;

/**
 *
 * @author smakambo
 */
@Stateless
public class SegFuncionalidadeFacade extends AbstractFacade<SegFuncionalidade> {
    
    @EJB
    private SegFuncionalidadeFormFacade segFuncionalidadeFormFacade;

    @Inject
    private SegTipoFuncionalidadeCache segTipoFuncionalidadeCache;

    @PersistenceContext(unitName = "SIG_UCAN-PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SegFuncionalidadeFacade() {
        super(SegFuncionalidade.class);
    }
    
    
    
    public boolean ehFilhoDoRoot(SegFuncionalidade segFuncionalidade)
    {
        return segFuncionalidade.getFkSegFuncionalidadePai() == null;
    }

    public List<SegFuncionalidade> sort(List<SegFuncionalidade> segFuncionalidades)
    {
        List<SegFuncionalidade> segFuncionalidadesExtraForms = new ArrayList();
        List<SegFuncionalidade> segFuncionalidadesForms = new ArrayList();

        for (SegFuncionalidade segFuncionalidade : segFuncionalidades)
        {
            switch (SegTipoFuncionalidadeEnum.fromSegTipoFuncionalidade(segFuncionalidade.getFkSegTipoFuncionalidade()))
            {
                case FORM:
                   segFuncionalidadesForms.add(segFuncionalidade);
                    break;
                default:
                    segFuncionalidadesExtraForms.add(segFuncionalidade);
                    break;
            }
        }
        Collections.sort(segFuncionalidadesForms, Comparator.comparing(SegFuncionalidade::getDescricao));
        Collections.sort(segFuncionalidadesExtraForms, Comparator.comparing(SegFuncionalidade::getDescricao));
        
        segFuncionalidadesExtraForms.addAll(segFuncionalidadesForms);
        return segFuncionalidadesExtraForms;
    }

    public List<SegFuncionalidade> findListaSegFuncionalidadeFilhos(SegFuncionalidade segFuncionalidade)
    {
        //System.err.println("0: SegFuncionalidadeFacade.findListaSegFuncionalidadeFilhos(SegFuncionalidade)\tpkIdFuncionalidade: " + segFuncionalidade.getPkSegFuncionalidade());
        SegFuncionalidade segFuncionalidadePai = segFuncionalidade.getFkSegFuncionalidadePai();
        switch (SegTipoFuncionalidadeEnum.fromSegTipoFuncionalidade(segFuncionalidade.getFkSegTipoFuncionalidade()))
        {
            case FORM:
                //System.err.println("1: SegFuncionalidadeFacade.findListaSegFuncionalidadeFilhos(SegFuncionalidade)\npkIdFuncionalidade: "
//                    + segFuncionalidade.getPkSegFuncionalidade() + "\t"
//                    + (segFuncionalidadePai == null ? "null" : segFuncionalidadePai.getDescricao()));
                if (!isRoot(segFuncionalidadePai))
                {
                    //System.err.println("2: SegFuncionalidadeFacade.findListaSegFuncionalidadeFilhos(SegFuncionalidade)\tpkIdFuncionalidade: " + segFuncionalidade.getPkSegFuncionalidade());
                    return new ArrayList();
                }
                break;
            case MENUITEM_FORM:
            case BOTAO_FORM:
            case HYPERLINK_FORM:
                //System.err.println("3: SegFuncionalidadeFacade.findListaSegFuncionalidadeFilhos(SegFuncionalidade)\tpkIdFuncionalidade: " + segFuncionalidade.getPkSegFuncionalidade());
                return this.sort(segFuncionalidadeFormFacade.findRedirectedForms(segFuncionalidade));
            default:
                //System.err.println("4: SegFuncionalidadeFacade.findListaSegFuncionalidadeFilhos(SegFuncionalidade)\tpkIdFuncionalidade: " + segFuncionalidade.getPkSegFuncionalidade());
                break;
        }

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
//            + ListUtils.toString(lista, (reg) -> this.toString(reg)));
        return this.sort(lista);
    }

    public List<SegFuncionalidade> findListaSegFuncionalidadeFilhos(int pkIdFuncionalidade)
    {
//        //System.err.println("1: SegFuncionalidadeFacade.findListaSegFuncionalidadeFilhos()\tpkIdFuncionalidade: " + pkIdFuncionalidade);
        Query q = null;
        if (pkIdFuncionalidade != 0)
        {
            q = em.createQuery("SELECT a FROM SegFuncionalidade a WHERE a.fkSegFuncionalidadePai.pkSegFuncionalidade = :pkIdFuncionalidade ORDER BY a.descricao");
            q.setParameter("pkIdFuncionalidade", pkIdFuncionalidade);
        }
        else
        {
            q = em.createQuery("SELECT a FROM SegFuncionalidade a WHERE a.fkSegFuncionalidadePai = null ORDER BY a.descricao");
        }

        return q.getResultList();
    }

    public SegFuncionalidade findByNomeByPai(String nome, SegFuncionalidade segFuncionalidadePai)
    {
        Query q;
        nome = nome.trim();

        int pkIdFuncionalidadePai;
        if (segFuncionalidadePai != null)
        {
            pkIdFuncionalidadePai = segFuncionalidadePai.getPkSegFuncionalidade();
            q = em.createQuery("SELECT a FROM SegFuncionalidade a WHERE a.fkSegFuncionalidadePai.pkSegFuncionalidade = :pkIdFuncionalidadePai AND a.descricao = :nome");
            q.setParameter("pkIdFuncionalidadePai", pkIdFuncionalidadePai);
        }
        else
        {
            q = em.createQuery("SELECT a FROM SegFuncionalidade a WHERE a.fkSegFuncionalidadePai = null AND a.descricao = :nome");
        }

        q.setParameter("nome", nome);
        List<SegFuncionalidade> list = q.getResultList();
//System.err.println("1: SegFuncionalidadeFacade.findByNomeByPai()\tlist.size: " + list.size());                            
        return list.isEmpty() ? null : list.get(0);
    }

    public List<SegFuncionalidade> funcionalidadesPai()
    {
        Query result = em.createQuery("SELECT a FROM SegFuncionalidade a WHERE a.fkSegFuncionalidadePai = null");

        return result.getResultList();
    }

    public List<SegFuncionalidade> getFuncionalidadeMenu()
    {

        Query result = em.createQuery("SELECT a FROM SegFuncionalidade a WHERE a.fkSegFuncionalidadePai.pkSegFuncionalidade != null");

        List<SegFuncionalidade> resultado = (List<SegFuncionalidade>) result.getResultList();

        return result.getResultList();
    }

    public List<SegFuncionalidade> getPermissasByRecursos(Integer idFuncionalidadePermissao)
    {
        TypedQuery<SegFuncionalidade> query;
        query = em.createQuery("SELECT p FROM SegFuncionalidade p WHERE p.fkSegFuncionalidadePai.pkSegFuncionalidade = :idFuncionalidadePermissao", SegFuncionalidade.class);
        //query = em.createQuery("SELECT p.nome FROM SegFuncionalidade p WHERE p.fkIdTipoFuncionalidade = 2 :idFuncionalidadePermissao", SegFuncionalidade.class);

        query.setParameter("idFuncionalidadePermissao", idFuncionalidadePermissao);

        return query.getResultList();
    }

    public boolean existeRegisto(int pkIdFuncionalidade)
    {
        return this.find(pkIdFuncionalidade) != null;
    }

    public String toString(SegFuncionalidade reg)
    {
        if (reg == null)
        {
            return "";
        }
        String msg = "{ ";
        msg += "pkSegFuncionalidade: " + reg.getPkSegFuncionalidade();
        msg += ", descricao: " + reg.getDescricao();
        msg += ", segFuncionalidadePai: " + (reg.getFkSegFuncionalidadePai() == null ? 0 : reg.getFkSegFuncionalidadePai().getPkSegFuncionalidade());
        msg += ", segTipoFuncionalidade: " + (reg.getFkSegTipoFuncionalidade() == null ? "null" : segTipoFuncionalidadeCache.toString(reg.getFkSegTipoFuncionalidade()));
        return msg + " }";
    }
    
    public SegFuncionalidade findByDescricao(String nome)
    {
        nome = nome.trim();
        Query query = em.createQuery("SELECT funcionalidade FROM SegFuncionalidade funcionalidade WHERE funcionalidade.descricao  = :nome", SegFuncionalidade.class).setParameter("nome", nome);
        query.setMaxResults(1);
        List<SegFuncionalidade> results = query.getResultList();
        return results.isEmpty() ? null : results.get(0);
    }

    public List<SegFuncionalidade> findByFuncionalidadeByPkPerfil(int pkPerfil)
    {
        Query query = em.createQuery("SELECT funcionalidade FROM SegFuncionalidade funcionalidade WHERE funcionalidade.pkSegFuncionalidade IN (SELECT pf.fkSegFuncionalidade.pkSegFuncionalidade FROM SegPerfilHasFuncionalidade pf WHERE pf.fkSegPerfil.pkSegPerfil = :pkPerfil)", SegFuncionalidade.class).setParameter("pkPerfil", pkPerfil);
        return query.getResultList();
    }

    public SegFuncionalidade findForm(Integer pkForm)
    {
        int pkSegTipoFuncionalidade = SegTipoFuncionalidadeEnum.FORM.toInteger();
        Query query = em.createQuery("SELECT funcionalidade FROM SegFuncionalidade funcionalidade WHERE funcionalidade.pkSegFuncionalidade  = :pkForm AND funcionalidade.fkSegTipoFuncionalidade.pkSegTipoFuncionalidade = :pkSegTipoFuncionalidade", SegFuncionalidade.class);
        query.setParameter("pkForm", pkForm);
        query.setParameter("pkSegTipoFuncionalidade", pkSegTipoFuncionalidade);
        query.setMaxResults(1);
        List<SegFuncionalidade> lista = query.getResultList();
        return lista.isEmpty() ? null : lista.get(0);
    }

    public SegFuncionalidade geraSegFuncionalidadeRoot()
    {
        //System.err.println("0: SegFuncionalidadeFacade.geraSegFuncionalidadeRoot()");
        SegFuncionalidade segFuncionalidade = this.getInstancia();
        //System.err.println("1: SegFuncionalidadeFacade.geraSegFuncionalidadeRoot()");
        segFuncionalidade.setPkSegFuncionalidade(0);
        segFuncionalidade.setDescricao(Defs.APPLICATION_NAME);
        //System.err.println("2: SegFuncionalidadeFacade.geraSegFuncionalidadeRoot()\tsegTipoFuncionalidadeCache: "
//            + (segTipoFuncionalidadeCache == null ? "null" : "not null"));
        segFuncionalidade.setFkSegTipoFuncionalidade(this.segTipoFuncionalidadeCache.find(SegTipoFuncionalidadeEnum.BOTAO_SEM_ACTIVIDADE.toInteger()));
        //System.err.println("3: SegFuncionalidadeFacade.geraSegFuncionalidadeRoot()");
        segFuncionalidade.setFkSegFuncionalidadePai(null);
        //System.err.println("4: SegFuncionalidadeFacade.geraSegFuncionalidadeRoot()");
//        segFuncionalidade.setUrlPadrao(null);
        return segFuncionalidade;
    }

    public boolean isRoot(SegFuncionalidade segFuncionalidade)
    {
        //System.err.println("0: SegFuncionalidadeFacade.isRoot()\tsegFuncionalidade: " + (segFuncionalidade == null ? "nulls" : segFuncionalidade.getPkSegFuncionalidade()));
        if (segFuncionalidade == null)
        {
            //System.err.println("1: SegFuncionalidadeFacade.isRoot()\tsegFuncionalidade: " + (segFuncionalidade == null ? "nulls" : segFuncionalidade.getPkSegFuncionalidade()));
            return false;
        }
        if (segFuncionalidade.getPkSegFuncionalidade() != 0)
        {
            //System.err.println("2: SegFuncionalidadeFacade.isRoot()\tsegFuncionalidade: " + segFuncionalidade.getPkSegFuncionalidade());
            return false;
        }
        if (!segFuncionalidade.getDescricao().trim().equals(Defs.APPLICATION_NAME))
        {
            //System.err.println("3: SegFuncionalidadeFacade.isRoot()\tsegFuncionalidade: " + segFuncionalidade.getPkSegFuncionalidade());
            return false;
        }
        if (segFuncionalidade.getFkSegTipoFuncionalidade().getPkSegTipoFuncionalidade() != SegTipoFuncionalidadeEnum.BOTAO_SEM_ACTIVIDADE.toInteger())
        {
            //System.err.println("4: SegFuncionalidadeFacade.isRoot()\tsegFuncionalidade: " + segFuncionalidade.getPkSegFuncionalidade());
            return false;
        }
        //System.err.println("5: SegFuncionalidadeFacade.isRoot()\tsegFuncionalidade: " + segFuncionalidade.getPkSegFuncionalidade());
        return segFuncionalidade.getFkSegFuncionalidadePai() == null;
    }

    public SegFuncionalidade getInstancia()
    {
        SegFuncionalidade segFuncionalidade = new SegFuncionalidade();
        segFuncionalidade.setFkSegFuncionalidadePai(null);
        segFuncionalidade.setFkSegTipoFuncionalidade(segTipoFuncionalidadeCache.find(SegTipoFuncionalidadeEnum.BOTAO_SEM_ACTIVIDADE.toInteger()));

        return segFuncionalidade;
    }
    
}
