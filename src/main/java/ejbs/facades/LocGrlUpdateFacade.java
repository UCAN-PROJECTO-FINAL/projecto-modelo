/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.facades;

import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import ejbs.entities.LocGrlUpdate;
import ejbs.facades.AbstractFacade;

/**
 *
 * @author KiamiSoft_ACosta
 */
@Stateless
public class LocGrlUpdateFacade extends AbstractFacade<LocGrlUpdate>
{

    @PersistenceContext(unitName = "SIG_UCAN-PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager()
    {
        return em;
    }

    public LocGrlUpdateFacade()
    {
        super(LocGrlUpdate.class);
    }

    public LocGrlUpdate obterRegistoGrlUpdates()
    {
        List<LocGrlUpdate> list = this.findAll();
        return list.isEmpty() ? null : list.get(0);
    }

    public Date dataPaisTabela()
    {
        LocGrlUpdate reg = obterRegistoGrlUpdates();
        return reg == null ? null : reg.getLocalidadesDate();
    }

   
    public void escreverDataActualizacaoLocalidade(Date date)
    {
        LocGrlUpdate reg = obterRegistoGrlUpdates();

        if (reg == null)
        {
            reg = new LocGrlUpdate();
            reg.setLocalidadesDate(date);
            this.create(reg);
        }
        else
        {
            reg.setLocalidadesDate(date);
            this.edit(reg);
        }
    }
}
