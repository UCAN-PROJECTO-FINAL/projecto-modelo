/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.facades;

import ejbs.entities.GrlEstadoCivil;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import utils.Defs;

/**
 *
 * @author smakambo
 */
@Stateless
public class GrlEstadoCivilFacade extends AbstractFacade<GrlEstadoCivil> {

    @PersistenceContext(unitName = "SIG_FRT_Master-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GrlEstadoCivilFacade() {
        super(GrlEstadoCivil.class);
    }
    
    public String toString(GrlEstadoCivil reg)
	{
		return "[" + reg.getPkGrlEstadoCivil() + ", " + reg.getNome() + "]";
	}

	public List<GrlEstadoCivil> findAllOrderedByNome()
	{
		Query query = em.createQuery("SELECT ec FROM GrlEstadoCivil ec ORDER BY ec.nome", GrlEstadoCivil.class);
		return query.getResultList();
	}

	public GrlEstadoCivil findByNome(String nome)
	{
        nome = nome.trim();
		Query query = em.createQuery("SELECT ec FROM GrlEstadoCivil ec WHERE ec.nome  = :nome", GrlEstadoCivil.class).setParameter("nome", nome);
		query.setMaxResults(1);
		List<GrlEstadoCivil> results = query.getResultList();
		return results.isEmpty() ? null : results.get(0);
	}

    public Integer findLastPkGrlEstadoCivil()
    {
        Query query = em.createQuery("SELECT MAX(gp.pkGrlEstadoCivil) FROM GrlEstadoCivil gp");
        List<Integer> results = query.getResultList();
        return results.isEmpty() ? null : results.get(0);
    }

	public GrlEstadoCivil getInstancia()
	{
		return find(Defs.PK_ESTADO_CIVIL_PADRAO);
	}

    
}
