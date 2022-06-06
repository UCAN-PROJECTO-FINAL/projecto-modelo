/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.facades;

import ejbs.entities.SegConta;
import ejbs.entities.SegPerfil;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import seg.beans.SegLoginBean;

/**
 *
 * @author smakambo
 */
@Stateless
public class SegPerfilFacade extends AbstractFacade<SegPerfil> {
    
    @EJB
    private SegPaginaArranqueFacade segPaginaArranqueFacade;

    @EJB
    private SegContaFacade segContaFacade;
    
    private SegConta contaCorrente;

    @PersistenceContext(unitName = "SIG_UCAN-PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SegPerfilFacade() {
        super(SegPerfil.class);
    }
    
    
    public List<SegPerfil> findPerfil(SegPerfil item)
	{
System.err.println("0: SegPerfilFacade.findPerfil");        
		contaCorrente = SegLoginBean.getInstanciaBean().getContaUtilizador();
System.err.println("1: SegPerfilFacade.findPerfil");
		String query = constroiQuery(item);
System.err.println("2: SegPerfilFacade.findPerfil");
		TypedQuery<SegPerfil> t = em.createQuery(query, SegPerfil.class);
System.err.println("3: SegPerfilFacade.findPerfil");
		if (item.getDescricao() != null && !item.getDescricao().isEmpty())
		{
			t.setParameter("descricao", "%" + item.getDescricao() + "%");
		}
System.err.println("4: SegPerfilFacade.findPerfil");		
		return t.getResultList();
	}

	public String constroiQuery(SegPerfil item)
	{
System.err.println("0: SegPerfilFacade.constroiQuery");        
		String query = "SELECT f FROM SegPerfil f WHERE f.pkSegPerfil = f.pkSegPerfil";

		if (item.getDescricao() != null && !item.getDescricao().isEmpty())
		{
			query += " AND f.descricao LIKE :descricao";
		}
		
		query += " ORDER BY f.descricao";

		return query;
	}

	public boolean existeRegisto(int pkSegPerfil)
	{
		SegPerfil reg = this.find(pkSegPerfil);
		return !(reg == null);
	}

	public List<SegPerfil> findAllOrderByDescricao()
	{
		Query query = em.createQuery("SELECT p FROM SegPerfil p ORDER BY p.descricao");

		return query.getResultList();
	}

	public String toString(SegPerfil reg)
	{
		return "[" + reg.getPkSegPerfil() + ", "
			+ reg.getDescricao() + "]";

	}

	public String toString(List<SegPerfil> lista)
	{
		String msg = "{ ";
		boolean first = true;

		for (SegPerfil perfil : lista)
		{
			msg += (first ? "" : ", ");
			msg += perfil;
			first = false;
		}
		msg += " }";
		return msg;
	}

	public Integer findAllQuantidadePerfil()
	{
		TypedQuery<Integer> query;
		if (this.findAll().isEmpty())
		{
			return 0;
		}
		query = em.createQuery("SELECT MAX(p.pkSegPerfil) FROM SegPerfil p ", Integer.class);
		return query.getResultList().get(0);
	}

	public SegPerfil findByDescricao(String nome)
	{
        nome = nome.trim();
		Query query = em.createQuery("SELECT perfil FROM SegPerfil perfil WHERE perfil.descricao  = :descricao", SegPerfil.class).setParameter("descricao", nome);
		query.setMaxResults(1);
		List<SegPerfil> results = query.getResultList();
		return results.isEmpty() ? null : results.get(0);
	}

	public List<SegPerfil> findByNomeWithString(String nome)
	{
        nome = nome.trim();
		Query query = em.createQuery("SELECT perfil FROM SegPerfil perfil WHERE perfil.descricao LIKE :descricao", SegPerfil.class).setParameter("descricao", "%" + nome + "%");
		List<SegPerfil> results = query.getResultList();
		return results.isEmpty() ? null : results;

	}

	public List<SegPerfil> findByPageArranque(int pkPage)
	{
		Query query = em.createQuery("SELECT perfil FROM SegPerfil perfil WHERE perfil.fkSegPaginaArranque.pkSegPaginaArranque  = :pkPage", SegPerfil.class).setParameter("pkPage", pkPage);
		return query.getResultList();
	}

	public void removerPaginaArraque(List<SegPerfil> perfis)
	{
		for (SegPerfil perfil : perfis)
		{
			removerPaginaArraque(perfil);
		}
	}

	public void removerPaginaArraque(SegPerfil perfil)
	{
		perfil.setFkSegPaginaArranque(null);
		this.edit(perfil);
	}

	public String toStringNames(List<SegPerfil> perfis)
	{
		String names = "";
		boolean first = true;
		for (SegPerfil perfil : perfis)
		{
			names += (first ? "" : ", ") + perfil.getDescricao();
			first = false;
		}
		return names;
	}

	public boolean evalido(Integer pkPerfil)
	{
//		if (perfil == null)
//			return false;
//		Integer pkPerfil = perfil.getPkSegPerfil();
		if (pkPerfil == null || pkPerfil < 1)
		{
			return false;
		}
		return find(pkPerfil) != null;
	}

	public SegPerfil getInstancia()
	{
		SegPerfil reg = new SegPerfil();
		reg.setFkSegPaginaArranque(this.segPaginaArranqueFacade.getInstancia());
		return reg;
	}

    
}
