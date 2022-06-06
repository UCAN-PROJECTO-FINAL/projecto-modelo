/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.facades;

import ejbs.entities.SegConfiguracoes;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import ejbs.cache.SegTipoFuncionalidadeCache;
import ejbs.entities.SegTipoFuncionalidade;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author smakambo
 */
@Stateless
public class SegConfiguracoesFacade extends AbstractFacade<SegConfiguracoes> {
    
    @EJB
    private SegPerfilFacade segPerfilFacade;
    
    @Inject
    private SegTipoFuncionalidadeCache segTipoFuncionalidadeCache;

    @PersistenceContext(unitName = "SIG_UCAN-PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SegConfiguracoesFacade() {
        super(SegConfiguracoes.class);
    }
    
    public SegConfiguracoes find()
	{
		Query query = em.createQuery("SELECT c FROM SegConfiguracoes c", SegConfiguracoes.class);
		List<SegConfiguracoes> list = query.getResultList();
		return list.isEmpty() ? null : list.get(0);
	}

	public SegConfiguracoes gerarConfiguracoesPadrao()
	{
		List<SegConfiguracoes> lista = this.findAll();
		if (lista.isEmpty())
			return null;
		SegConfiguracoes configuracoesPadrao = getInstancia();

		//Query query = em.createQuery("DELETE FROM SegConfiguracoes", SegConfiguracoes.class);
		try
		{
			this.remove(lista.get(0));
			//query.executeUpdate();
		}
		catch (Exception e)
		{

		}
		return null;
	}

	public SegConfiguracoes reporConfiguracoesPadrao()
	{
		return gerarConfiguracoesPadrao();
	}

	public boolean createRegisto(SegConfiguracoes configuracoes)
	{
		try
		{
			this.create(configuracoes);
			return true;
		}
		catch (Exception e)
		{
			return false;
		}
	}

	public boolean editRegisto(SegConfiguracoes configuracoes)
	{
		try
		{
			this.edit(configuracoes);
			return true;
		}
		catch (Exception e)
		{
			return false;
		}
	}

	public SegConfiguracoes getInstancia()
	{
		SegConfiguracoes reg = new SegConfiguracoes();
		reg.setFkSegPerfil(this.segPerfilFacade.getInstancia());
		reg.setFkSegTipoFuncionalidade(new SegTipoFuncionalidade());
		return reg;
	}
    
}
