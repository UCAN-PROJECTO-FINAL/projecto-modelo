/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.facades;

import ejbs.entities.GrlUpdate;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author smakambo
 */
@Stateless
public class GrlUpdateFacade extends AbstractFacade<GrlUpdate> {

    @PersistenceContext(unitName = "SIG_FRT_Master-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GrlUpdateFacade() {
        super(GrlUpdate.class);
    }
    
    
       
    public GrlUpdate obterRegistoGrlUpdates()
    {
        List<GrlUpdate> list = this.findAll();
        return list.isEmpty() ? null : list.get(0);
    }

    public Date dataPaisTabela()
    {
        GrlUpdate reg = obterRegistoGrlUpdates();
        return reg == null ? null : reg.getLocalidadesDate();
    }

   
    public void escreverDataActualizacaoLocalidade(Date date)
    {
        GrlUpdate reg = obterRegistoGrlUpdates();

        if (reg == null)
        {
            reg = new GrlUpdate();
            reg.setLocalidadesDate(date);
            this.create(reg);
        }
        else
        {
            reg.setLocalidadesDate(date);
            this.edit(reg);
        }
    }
    
    public Date dataEstruturaFisicaTabela()
    {
        GrlUpdate reg = obterRegistoGrlUpdates();
        return reg == null ? null : reg.getEstruturaFisicaDate();
    }

   
    public void escreverDataActualizacaoEstruturaFisica(Date date)
    {
        GrlUpdate reg = obterRegistoGrlUpdates();

        if (reg == null)
        {
            reg = new GrlUpdate();
            reg.setEstruturaFisicaDate(date);
            this.create(reg);
        }
        else
        {
            reg.setEstruturaFisicaDate(date);
            this.edit(reg);
        }
    }
    
    public Date dataEstruturaLogicaFisicaTabela()
    {
        GrlUpdate reg = obterRegistoGrlUpdates();
        return reg == null ? null : reg.getLocalidadesDate();
    }
    
    public void escreverDataActualizacaoEstruturaLogica(Date date)
    {
        GrlUpdate reg = obterRegistoGrlUpdates();

        if (reg == null)
        {
            reg = new GrlUpdate();
            reg.setEstruturaLogicaDate(date);
            this.create(reg);
        }
        else
        {
            reg.setEstruturaLogicaDate(date);
            this.edit(reg);
        }
    }
    
    public Date dataEstruturaLogicaTabela()
    {
        GrlUpdate reg = obterRegistoGrlUpdates();
        return reg == null ? null : reg.getEstruturaLogicaDate();
    }
    
   
    
}
