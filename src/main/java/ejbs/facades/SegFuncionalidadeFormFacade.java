/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.facades;

import ejbs.entities.SegFuncionalidadeForm;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import seg.utils.SegFuncionalidadeFormsLista;
import ejbs.entities.SegFuncionalidade;
import ejbs.entities.SegFuncionalidadeForm;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author smakambo
 */
@Stateless
public class SegFuncionalidadeFormFacade extends AbstractFacade<SegFuncionalidadeForm> {
    
    @EJB
    private SegFuncionalidadeFacade segFuncionalidadeFacade;

    @PersistenceContext(unitName = "SIG_UCAN-PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SegFuncionalidadeFormFacade() {
        super(SegFuncionalidadeForm.class);
    }
    
    
    public void create(SegFuncionalidadeFormsLista segFuncionalidadeFormLista)
    {
        SegFuncionalidade segFuncionalidade = segFuncionalidadeFormLista.getSegFuncionalidade();

        List<Integer> pkForms = segFuncionalidadeFormLista.getPkFormsLista();
        SegFuncionalidadeForm segFuncionalidadeForm;
        for (Integer pkForm : pkForms)
        {
            segFuncionalidadeForm = new SegFuncionalidadeForm();
            segFuncionalidadeForm.setFkSegFuncionalidade(segFuncionalidade);
            segFuncionalidadeForm.setFkSegForm(segFuncionalidadeFacade.findForm(pkForm));
            create(segFuncionalidadeForm);
        }
    }

    public int removeAll()
    {
        List<SegFuncionalidadeForm> list = this.findAll();
        int count = 0;
        for (SegFuncionalidadeForm segFuncionalidadeForm : list)
        {
            this.remove(segFuncionalidadeForm);
            count++;
        }
        return count;
    }

    private List<Integer> getPkSegFuncionalidades(List<SegFuncionalidade> segFuncionalidades)
    {
        List<Integer> pkSegFuncionalidades = new ArrayList();
        for (SegFuncionalidade segFuncionalidade : segFuncionalidades)
        {
            pkSegFuncionalidades.add(segFuncionalidade.getPkSegFuncionalidade());
        }
        return pkSegFuncionalidades;
    }

    public List<SegFuncionalidade> filterSegFuncionalidadeWithAcessToUrl(List<SegFuncionalidade> segFuncionalidades, String url)
    {
        //System.err.println("0: SegFuncionalidadeFormFacade.findSegFuncionalidadesFromPerfilWithAcessToUrl()\turl: " + url
//            + "\tsegFuncionalidades: " + (segFuncionalidades == null ? "null" : segFuncionalidades.size()));
        List<Integer> list = getPkSegFuncionalidades(segFuncionalidades);
//        //System.err.println("1: SegFuncionalidadeFormFacade.findSegFuncionalidadesFromPerfilWithAcessToUrl()\nlist: "
//            + ListUtils.toString(list));
        Query query = em.createQuery("SELECT sfu.fkSegFuncionalidade FROM SegFuncionalidadeForm sfu WHERE sfu.fkSegFuncionalidade.pkSegFuncionalidade IN :list");

        query.setParameter("list", list);
        return query.getResultList();
    }

    public List<SegFuncionalidade> findRedirectedForms(SegFuncionalidade segFuncionalidade)
    {
        int pkSegFuncionalidade = segFuncionalidade.getPkSegFuncionalidade();
        //System.err.println("0: SegFuncionalidadeFormFacade.findRedirectedForms()\tsegFuncionalidade: "
//            + (segFuncionalidade == null ? "null" : segFuncionalidade.getPkSegFuncionalidade()));
        Query query = em.createQuery("SELECT sfu.fkSegForm FROM SegFuncionalidadeForm sfu WHERE sfu.fkSegFuncionalidade.pkSegFuncionalidade = :pkSegFuncionalidade");

        query.setParameter("pkSegFuncionalidade", pkSegFuncionalidade);
        return query.getResultList();
    }
    
}
