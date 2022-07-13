/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.facades;

import ejbs.entities.EstruturaGrlUpdade;
import ejbs.facades.AbstractFacade;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author mdnex
 */
@Stateless
public class EstruturaGrlUpdadeFacade extends AbstractFacade<EstruturaGrlUpdade>
{

    @PersistenceContext(unitName = "SIG_UCAN-PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager()
    {
        return em;
    }

    public EstruturaGrlUpdadeFacade()
    {
        super(EstruturaGrlUpdade.class);
    }
    
    public EstruturaGrlUpdade obterRegistoEstruturaGrlUpdades()
    {
        List<EstruturaGrlUpdade> list = this.findAll();
        return list.isEmpty() ? null : list.get(0);
    }
    
    public Date dataEstruturaFisicaTabela()
    {
        EstruturaGrlUpdade reg = obterRegistoEstruturaGrlUpdades();
        return reg == null ? null : reg.getEstruturaFisicaDate();
    }

   
    public void escreverDataActualizacaoEstruturaFisica(Date date)
    {
        EstruturaGrlUpdade reg = obterRegistoEstruturaGrlUpdades();

        if (reg == null)
        {
            reg = new EstruturaGrlUpdade();
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
        EstruturaGrlUpdade reg = obterRegistoEstruturaGrlUpdades();
        return reg == null ? null : reg.getEstruturaLogicaDate();
    }
    
    public void escreverDataActualizacaoEstruturaLogica(Date date)
    {
        EstruturaGrlUpdade reg = obterRegistoEstruturaGrlUpdades();

        if (reg == null)
        {
            reg = new EstruturaGrlUpdade();
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
        EstruturaGrlUpdade reg = obterRegistoEstruturaGrlUpdades();
        return reg == null ? null : reg.getEstruturaLogicaDate();
    }
    
}
