/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.facades;

import ejbs.entities.SegConta;
import java.text.Normalizer;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import utils.DataUtils;
import utils.StringUtils;
import seg.beans.SegUtilizadorNovoBean;
import seg.beans.SegLoginBean;

/**
 *
 * @author smakambo
 */
@Stateless
public class SegContaFacade extends AbstractFacade<SegConta> {
    
    private SegConta contaCorrente;
    @Inject
    private SegUtilizadorNovoBean segUtilizadorNovoBean;

    @PersistenceContext(unitName = "SIG_UCAN-PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SegContaFacade() {
        super(SegConta.class);
    }
    
    public boolean isRootAccount(SegConta conta)
	{
        //System.err.println("0: SegContaFacade.isRootAccount()\tconta: " +
//            (conta == null ? "null" : conta.getNomeUtilizador()));
        if (conta == null || StringUtils.isNull(conta.getNomeUtilizador()))
            return false;
		return conta.getNomeUtilizador().equals("root");
	}
        
        public String removerAcentos(String str)
	{
		String nfdNormalizedString = Normalizer.normalize(str, Normalizer.Form.NFD);
		Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
		return pattern.matcher(nfdNormalizedString).replaceAll("");
	}

	public List<SegConta> findContas(SegConta conta, SegConta user)
	{
		contaCorrente = SegLoginBean.getInstanciaBean().getContaUtilizador();

		String query = constroiQueryUtilizador(conta, user);

		TypedQuery<SegConta> t = em.createQuery(query, SegConta.class);

		if (conta.getNomeUtilizador() != null && !conta.getNomeUtilizador().isEmpty())
		{
                    String userName = removerAcentos(conta.getNomeUtilizador().toLowerCase());
                    ////System.err.println("removerAcentos(conta.getNomeUtilizador().toLowerCase()): "+ userName);
                    t.setParameter("nomeUtilizador", "%" + userName + "%" );
		}

		return t.getResultList();
	}

	public SegConta findConta(SegConta conta)
	{
		String query = constroiQueryLogin(conta);

		TypedQuery<SegConta> t = em.createQuery(query, SegConta.class);

		if (conta.getNomeUtilizador() != null && !conta.getNomeUtilizador().isEmpty())
		{
			t.setParameter("nomeUtilizador", conta.getNomeUtilizador());
		}

		if (conta.getPalavraPasse() != null && !conta.getPalavraPasse().isEmpty())
		{
			t.setParameter("palavraPasse", conta.getPalavraPasse());
		}

		List<SegConta> resultado = t.getResultList();

		return !resultado.isEmpty() ? resultado.get(0) : null;
	}

	public String constroiQueryLogin(SegConta conta)
	{
		String query = "SELECT f FROM SegConta f WHERE f.pkSegConta = f.pkSegConta";

		if (conta.getNomeUtilizador() != null && !conta.getNomeUtilizador().isEmpty())
		{
			query += " AND f.nomeUtilizador LIKE :nomeUtilizador";
		}

		if (conta.getPalavraPasse() != null && !conta.getPalavraPasse().isEmpty())
		{
			query += " AND f.palavraPasse LIKE :palavraPasse";
		}

		return query;
	}

	public String constroiQueryUtilizador(SegConta conta, SegConta user)
	{
		String query = "SELECT f FROM SegConta f WHERE f.pkSegConta = f.pkSegConta";

		if (conta.getNomeUtilizador() != null && !conta.getNomeUtilizador().isEmpty())
		{
			query += " AND f.nomeUtilizador LIKE :nomeUtilizador";
		}
System.err.println("0: SegContaFacade.constroiQueryUtilizador()\tconta: " +
            (conta == null ? "null" : conta.getNomeUtilizador()));
		if (!isRootAccount(contaCorrente))
		{
			if (user.getFkSegPerfil().getPkSegPerfil() != 1)
			{
				query += " AND f.fkSegPerfil.descricao LIKE :fkPerfil OR f.fkSegConta.pkSegConta = :adminPai";
			}
		}
		
		query += " Order By f.nomeUtilizador, f.dataCadastro";
		return query;
	}

	public SegConta getUtilizadorBypalavraPasseAndnomeUtilizador(String username, String password)
	{

		Query query;
        username = username.trim();
        password = password.trim();
		query = em.createQuery("SELECT scu FROM SegConta scu WHERE scu.palavraPasse = :password AND scu.nomeUtilizador = :username");

		query.setParameter("username", username)
			.setParameter("password", password);

		List<SegConta> results = query.getResultList();
                System.out.println("results: "+results.isEmpty());
		return results.isEmpty() ? null : results.get(0);
	}

    
	public List<SegConta> findAllContaOrderByPkSegPerfil(int pkSegPerfil)
	{
		Query query;
		query = em.createQuery("SELECT scu FROM SegConta scu WHERE scu.fkSegPerfil.pkSegPerfil = :pkSegPerfil Order By scu.fkSegConta.nomeUtilizador");
        query.setParameter("pkSegPerfil", pkSegPerfil);
		return query.getResultList();
	}
    
	public List<SegConta> findAllContaOrderByNomeUtilizador()
	{
		TypedQuery<SegConta> query;
		query = em.createQuery("SELECT conta FROM SegConta conta ORDER BY conta.nomeUtilizador", SegConta.class);

		return query.getResultList();
	}

	public SegConta findAllContaOrderByNomeUtilizador(String username, String password)
	{
		TypedQuery<SegConta> query;
        username = username.trim();
        password = password.trim();
        
		query = em.createQuery("SELECT scu FROM SegConta scu WHERE scu.palavraPasse = :password AND scu.nomeUtilizador = :username", SegConta.class);

		query.setParameter("username", username)
			.setParameter("password", password);

		List<SegConta> results = query.getResultList();

		return results.isEmpty() ? null : results.get(0);
	}

	public Long findAllQuantidadeDeContaByPkGrlPessoa(int pkIdPessoa)
	{
		////System.err.println("0: SegContaFacade.findAllQuantidadeDeContaByPkGrlPessoa()");
		Query query;
		query = em.createQuery("SELECT count(c) FROM SegConta c WHERE c.fkSegPessoa = :fkIdPessoa");
//		query = em.createQuery("SELECT count(c) FROM SegConta c WHERE c.fkSegPessoa.pkGrlPessoa = :fkIdPessoa", Integer.class);
		////System.err.println("1: SegContaFacade.findAllQuantidadeDeContaByPkGrlPessoa()");
		query.setParameter("fkIdPessoa", pkIdPessoa);
		////System.err.println("2: SegContaFacade.findAllQuantidadeDeContaByPkGrlPessoa()");
		List<Long> rs = query.getResultList();
		////System.err.println("3: SegContaFacade.findAllQuantidadeDeContaByPkGrlPessoa()");
		return rs.isEmpty() ? new Long(0) : rs.get(0);
	}

	/**
	 * Verificar se o utilizador ja existe
	 *
	 * @param nomeUtilizador
	 * @return
	 */
	public SegConta existeContaUtilizador(String nomeUtilizador)
	{
		TypedQuery<SegConta> query;
        nomeUtilizador = nomeUtilizador.trim();
        
		query = em.createQuery("SELECT scu FROM SegConta scu WHERE scu.nomeUtilizador = :nomeUtilizador", SegConta.class);

		query.setParameter("nomeUtilizador", nomeUtilizador);

		List<SegConta> results = query.getResultList();

		return results.isEmpty() ? null :results.get(0);
	}

	public List<SegConta> findByPageArranque(int pkPage)
	{
		TypedQuery<SegConta> query;
		query = em.createQuery("SELECT c FROM SegConta c WHERE c.fkSegPaginaArranque.pkSegPaginaArranque = :pkPage", SegConta.class);

		query.setParameter("pkPage", pkPage);

		return query.getResultList();
	}
	
	public void removerPaginaArraque(List<SegConta> contas)
	{
		for (SegConta conta : contas)
			removerPaginaArraque(conta);
	}
	
	public void removerPaginaArraque(SegConta conta)
	{
		conta.setFkSegPaginaArranque(null);
		this.edit(conta);
	}
	
	public String toStringNames(List<SegConta> contas)
	{
		String names = "";
		boolean first = true;
		for (SegConta conta : contas)
		{
			names += (first ? "" : ", ") + conta.getNomeUtilizador();
			first = false;
		}
		return names;
	}

	/**
	 * Verificar se o utilizador ja existe
	 *
	 * @param nomeUtilizador
	 * @return
	 */
	public SegConta existeContaUtilizadorEdit(String nomeUtilizador)
	{
		TypedQuery<SegConta> query;
        nomeUtilizador = nomeUtilizador.trim();
        
		query = em.createQuery("SELECT scu FROM SegConta scu WHERE scu.nomeUtilizador = :nomeUtilizador", SegConta.class);

		query.setParameter("username", nomeUtilizador);

		List<SegConta> results = query.getResultList();

		return results.isEmpty() ? null : results.get(0);
	}

	public String toString(SegConta reg)
	{
            String msg = "{";
            msg += " PkSegConta: " + reg.getPkSegConta();
            msg += ", NomeUtilizador: " + reg.getNomeUtilizador();
            msg += ", DataCadastro: " + DataUtils.toString(reg.getDataCadastro());
            msg += ", MaxIdleTime: " + getMaxIdleTime(reg);
            msg += ", ActivarTempoInactividade: " + getActivarTempoInactividade(reg);
            msg += ", TempoInactividade: " + getTempoInactividade(reg);
            return msg + " }";
	}
	
      
    public int getTempoInactividade(SegConta reg)
    {
        Integer tempoInactividade = reg.getTempoInactividade();
        return tempoInactividade == null ? 0 : tempoInactividade;
    }

    public boolean getActivarTempoInactividade(SegConta reg)
    {
        Boolean activar = reg.getActivarTempoInactividade();

        return activar == null ? true : activar;
    }

    public int getMaxIdleTime(SegConta reg)
    {
        Integer idleTime = reg.getMaxIdleTime();

        return idleTime == null ? 0 : idleTime;
    }

	public SegConta findContaByPkGrlPessoa(int pkGrlPessoa)
    {
        TypedQuery<SegConta> query;
        query = em.createQuery("SELECT scu FROM SegConta scu WHERE scu.fkSegPessoa = :pkGrlPessoa", SegConta.class);

        query.setParameter("pkGrlPessoa", pkGrlPessoa);

        List<SegConta> results = query.getResultList();

        return results.isEmpty() ? null : results.get(0);
    }
	
    public SegConta getInstancia()
    {
        SegConta conta = new SegConta();
        conta.setActivarTempoInactividade(Boolean.TRUE);
        conta.setDataCadastro(new Date());
        return conta;
    }
    
}
