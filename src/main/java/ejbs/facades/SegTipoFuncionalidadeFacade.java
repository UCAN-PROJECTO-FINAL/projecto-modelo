/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.facades;

import ejbs.entities.SegTipoFuncionalidade;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author smakambo
 */
@Stateless
public class SegTipoFuncionalidadeFacade extends AbstractFacade<SegTipoFuncionalidade> {

    @PersistenceContext(unitName = "SIG_UCAN-PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SegTipoFuncionalidadeFacade() {
        super(SegTipoFuncionalidade.class);
    }
    
    
	public List<SegTipoFuncionalidade> findAllOrderByNome()
	{
		Query q = em.createQuery("SELECT a FROM SegTipoFuncionalidade a ORDER BY a.nome");
		return q.getResultList();
	}

	public boolean existeRegisto(int pkIdTipoFuncionalidade)
	{
		SegTipoFuncionalidade reg = this.find(pkIdTipoFuncionalidade);
		return !(reg == null);
	}

	public String toString(SegTipoFuncionalidade reg)
	{
		return "[" + reg.getPkSegTipoFuncionalidade() + ", " + reg.getNome() + "]";
	}

	public SegTipoFuncionalidade findByNome(String nome)
	{
        nome = nome.trim();
		Query query = em.createQuery("SELECT tipoFuncionalidade FROM SegTipoFuncionalidade tipoFuncionalidade WHERE tipoFuncionalidade.nome  = :nome", SegTipoFuncionalidade.class).setParameter("nome", nome);
		query.setMaxResults(1);
		List<SegTipoFuncionalidade> results = query.getResultList();
		return results.isEmpty() ? null : results.get(0);
	}

	public SegTipoFuncionalidade getInstancia()
	{
		return new SegTipoFuncionalidade();
	}
	
		
	public boolean evalido(Integer pkSegTipoFuncionalidade)
	{
//		if (tf == null)
//			return false;
//		Integer pkSegTipoFuncionalidade = tf.getPkSegTipoFuncionalidade();
		return (pkSegTipoFuncionalidade == null || pkSegTipoFuncionalidade < 1) ? false : (find(pkSegTipoFuncionalidade) != null);
	}
    
}
