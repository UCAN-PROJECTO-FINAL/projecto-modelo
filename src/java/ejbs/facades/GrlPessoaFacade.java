/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.facades;

//import ejbs.entities.GrlPessoa;
//import java.util.List;
//import javax.ejb.Stateless;
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import utils.StringUtils;
//import utils.mensagens.LogFile;
import ejbs.cache.GrlEstadoCivilCache;
import ejbs.cache.GrlSexoCache;
import ejbs.entities.GrlPessoa;
//import javax.persistence.Query;
import utils.DataUtils;
import utils.Defs;
//import utils.ListUtils;
import utils.StringUtils;
import utils.mensagens.LogFile;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author smakambo
 */
@Stateless
public class GrlPessoaFacade extends AbstractFacade<GrlPessoa> {
    
    @Inject
    private GrlSexoCache grlSexoCache;

    @Inject
    private GrlEstadoCivilCache grlEstadoCivilCache;

    @PersistenceContext(unitName = "SIG_FRT_Master-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GrlPessoaFacade() {
        super(GrlPessoa.class);
    }
    
      public boolean isMasculino(GrlPessoa reg)
    {
        return reg.getFkSexo().getNome().equals("Masculino");
    }

    public String toString(GrlPessoa reg)
    {
        String msg = "{ ";

        boolean first = true;
        if (reg.getPkGrlPessoa() != null)
        {
            msg += "pkGrlPessoa: " + reg.getPkGrlPessoa();
            first = false;
        }
        if (reg.getNome() != null)
        {
            msg += (first ? "" : ", ") + "nome: " + reg.getNome();
        }
        if (reg.getPai() != null)
        {
            msg += ", pai: " + reg.getPai();
        }
        if (reg.getMae() != null)
        {
            msg += ", mae: " + reg.getMae();
        }
        if (reg.getFkEstadoCivil() != null)
        {
            msg += ", estadoCivil: " + reg.getFkEstadoCivil().getNome();
        }
//        if (reg.getFkNaturalidadeMunicipio() != null)
//        {
//            msg += ", naturalidade: " + this.locMunicipiosCache.toStringExtended(reg.getFkNaturalidadeMunicipio());
//        }
//        if (reg.getFkResidenciaBairro() != null)
//        {
//            msg += ", residencia: " + this.locBairrosCache.toStringExtended(reg.getFkResidenciaBairro());
//        }
        if (reg.getFkSexo() != null)
        {
            msg += ", sexo: " + reg.getFkSexo().getNome();
        }
        if (reg.getDataCadastro() != null)
        {
            msg += ", dataCadastro: " + DataUtils.toString(reg.getDataCadastro());
        }
        if (reg.getDataEmissao() != null)
        {
            msg += ", dataEmissao: " + DataUtils.toString(reg.getDataEmissao());
        }
        if (reg.getDataNascimento() != null)
        {
            msg += ", dataNascimento: " + DataUtils.toString(reg.getDataNascimento());
        }

        msg += ", nacional: " + (reg.getEhNacional() ? "sim" : "não");
        if (reg.getNif() != null)
        {
            msg += ", NIF: " + reg.getNif();
        }
        if (reg.getNumeroIdentificacao() != null)
        {
            msg += ", numeroIdentificacao: " + reg.getNumeroIdentificacao();
        }
        if (reg.getPrazoValidade() != null)
        {
            msg += ", prazoValidade: " + DataUtils.toString(reg.getPrazoValidade());
        }

        msg += ", vitalicio: " + (reg.getVitalicio() ? "sim" : "não");

        if (!StringUtils.isNull(reg.getTelefonePrincipal()))
        {
            msg += ", telefonePrincipal: " + reg.getTelefonePrincipal();
        }
        if (!StringUtils.isNull(reg.getTelefoneAlternativo()))
        {
            msg += ", telefoneAlternativo: " + reg.getTelefoneAlternativo();
        }

        if (!StringUtils.isNull(reg.getEmailPrincipal()))
        {
            msg += ", emailPrincipal: " + reg.getEmailPrincipal();
        }
        if (!StringUtils.isNull(reg.getEmailAlternativo()))
        {
            msg += ", emailAlternativo: " + reg.getEmailAlternativo();
        }

        return msg + " }";
    }

    public String toString(List<GrlPessoa> lista)
    {
        String msg = lista.get(0).getNome();
        int sz = lista.size();
        if (sz == 1)
        {
            return msg;
        }
        for (int i = 1; i < sz; i++)
        {
            msg += ", " + lista.get(i).getNome();
        }
        return msg;
    }

    public String toStringIdentificacao(GrlPessoa reg)
    {
        String msg = "portador do ";
        boolean ehNacional = reg.getEhNacional();
        msg += ehNacional ? "BI " : "Cartão de Residencia nº ";
        msg += reg.getNumeroIdentificacao();
        msg += ", emitido aos " + DataUtils.toStringExtended(reg.getDataEmissao());

        return msg;
    }

//    public String toStringResidencia(GrlPessoa reg)
//    {
//        return "residente " + this.locBairrosCache.toStringExtended(reg.getFkResidenciaBairro());
//    }
//
//    public String toStringNaturalidade(GrlPessoa reg)
//    {
//        return "natural " + this.locMunicipiosCache.toStringExtended(reg.getFkNaturalidadeMunicipio());
//    }

    public String toStringEstadoCivil(GrlPessoa reg)
    {
        ////////System.err.println("0. GrlPessoaFacade.toStringEstadoCivil()\reg.fksexo: " +
//            reg.getFkSexo() == null ? "null" : "not null");        
        boolean sexoMasculino = reg.getFkSexo().getNome().equals("Masculino");

        String estadoCivil = reg.getFkEstadoCivil().getNome();
        if (estadoCivil.equals("Solteiro(a)"))
        {
            return sexoMasculino ? "Solteiro" : "Solteira";
        }
        else if (estadoCivil.equals("Casado(a)"))
        {
            return sexoMasculino ? "Casado" : "Casada";
        }
        //else if (estadoCivil.equals("Divorciado(a)"))
        return sexoMasculino ? "Divorciado" : "Divorciada";
    }

    public String toStringEstadoCivilComConjuge(GrlPessoa reg)
    {
        boolean sexoMasculino = reg.getFkSexo().getNome().equals("Masculino");

        return sexoMasculino ? "Casado com" : "Casada com";
    }

    public String toString_Senhor_Senhora(GrlPessoa reg)
    {
        boolean sexoMasculino = reg.getFkSexo().getNome().equals("Masculino");

        return sexoMasculino ? "o senhor" : "a senhora";
    }

    public List<GrlPessoa> findAllOrderedByNome()
    {
        Query query = em.createQuery("SELECT p FROM GrlPessoa p ORDER BY p.nome ", GrlPessoa.class);
        return query.getResultList();
    }
//
//    public List<GrlPessoa> findAllByProjectoPadrao()
//    {
////System.err.println("0: GrlPessoaFacade.findAllByProjectoPadrao()");        
//        PrjProjecto projectoPadrao = prjProjectoPadraoBean.getProjectoPadrao();
////System.err.println("1: GrlPessoaFacade.findAllByProjectoPadrao()");        
//        if (projectoPadrao == null)
//            return new ArrayList();
////System.err.println("2: GrlPessoaFacade.findAllByProjectoPadrao()");        
//        int pkPrjProjectoPadrao = projectoPadrao.getPkProjecto();        
//        
//        Query query = em.createQuery("SELECT DISTINCT p FROM GrlPessoa p WHERE (p.pkGrlPessoa IN (SELECT DISTINCT c.fkEntidade FROM PrjProcessoCandidatura c WHERE c.entidadeEhInstituicao = FALSE AND c.fkTipologiaLoteProjecto.fkProjecto.pkProjecto = :pkPrjProjectoPadrao))", GrlPessoa.class);
////System.err.println("3: GrlPessoaFacade.findAllByProjectoPadrao()");        
//        query.setParameter("pkPrjProjectoPadrao", pkPrjProjectoPadrao);
//        List<GrlPessoa> titularesIndividuais = query.getResultList();
////System.err.println("4: GrlPessoaFacade.findAllByProjectoPadrao()");        
//        query = em.createQuery("SELECT DISTINCT p.fkRepresentante FROM ComInstituicao p WHERE (p.pkComInstituicao IN (SELECT DISTINCT c.fkEntidade FROM PrjProcessoCandidatura c WHERE c.entidadeEhInstituicao = TRUE AND c.fkTipologiaLoteProjecto.fkProjecto.pkProjecto = :pkPrjProjectoPadrao))", GrlPessoa.class);
////System.err.println("5: GrlPessoaFacade.findAllByProjectoPadrao()");        
//        query.setParameter("pkPrjProjectoPadrao", pkPrjProjectoPadrao);
//        List<GrlPessoa> representantes = query.getResultList();
////System.err.println("6: GrlPessoaFacade.findAllByProjectoPadrao()");        
//        
//        List<GrlPessoa> pessoasListByProjectoPadrao = new ArrayList();
//        List<Integer> pkPessoaByProjectoPadrao = new ArrayList();
//        int pkPessoa;
//        for (GrlPessoa pessoa : titularesIndividuais)
//        {
//            pkPessoa = pessoa.getPkGrlPessoa();
//            if (!ListUtils.includes(pkPessoaByProjectoPadrao, pkPessoa))
//            {
//                pkPessoaByProjectoPadrao.add(pkPessoa);
//                pessoasListByProjectoPadrao.add(pessoa);
//            }
//        }
//System.err.println("7: GrlPessoaFacade.findAllByProjectoPadrao()\tpessoasListByProjectoPadrao.size: " +
//    pessoasListByProjectoPadrao.size());        
//        for (GrlPessoa pessoa : representantes)
//        {
//            pkPessoa = pessoa.getPkGrlPessoa();
//            if (!ListUtils.includes(pkPessoaByProjectoPadrao, pkPessoa))
//            {
//                pkPessoaByProjectoPadrao.add(pkPessoa);
//                pessoasListByProjectoPadrao.add(pessoa);
//            }
//        }
//System.err.println("8: GrlPessoaFacade.findAllByProjectoPadrao()\tpessoasListByProjectoPadrao.size: " +
//    pessoasListByProjectoPadrao.size());        
//        
//        return pessoasListByProjectoPadrao;
//    }

    public List<GrlPessoa> findAllPessoaColaboradorOrderedByNome()
    {
        Query query = em.createQuery("SELECT p FROM GrlPessoa p WHERE p.pkGrlPessoa IN (SELECT c.fkGrlPessoa.pkGrlPessoa FROM RhColaborador c ) ORDER BY p.nome ", GrlPessoa.class);
        return query.getResultList();
    }

    public GrlPessoa findByNome(String nome)
    {
        if (!StringUtils.isNull(nome))
        {
            return null;
        }
        nome = nome.trim().toLowerCase();
        Query query = em.createQuery("SELECT p FROM GrlPessoa p WHERE LOWER(p.nome) LIKE :nome", GrlPessoa.class).setParameter("nome", nome);
        query.setMaxResults(1);
        List<GrlPessoa> results = query.getResultList();
        return results.isEmpty() ? null : results.get(0);
    }

    public GrlPessoa findByNumeroIdentificacao(boolean ehNacional, String numeroIdentificacao)
    {
        numeroIdentificacao = numeroIdentificacao.trim().toLowerCase();
        Query query = em.createQuery("SELECT gp FROM GrlPessoa gp WHERE LOWER(gp.numeroIdentificacao) LIKE :numeroIdentificacao AND gp.ehNacional = :ehNacional", GrlPessoa.class);
        query.setParameter("numeroIdentificacao", numeroIdentificacao);
        query.setParameter("ehNacional", ehNacional);
        query.setMaxResults(1);
        List<GrlPessoa> results = query.getResultList();
        return results.isEmpty() ? null : results.get(0);
    }

    public List<Integer> findByNumeroIdentificacaoIncompleto(String numeroIdentificacao)
    {
        if (StringUtils.isNull(numeroIdentificacao))
        {
            return new ArrayList();
        }
        numeroIdentificacao = numeroIdentificacao.trim().toLowerCase();
        // "SELECT e FROM Employee e WHERE e.name LIKE 'D%'"
        String query = "SELECT c.pkGrlPessoa FROM GrlPessoa c WHERE LOWER(c.numeroIdentificacao) LIKE '%" + numeroIdentificacao + "%'  ORDER BY c.nome";

        Query q = em.createQuery(query);
        return q.getResultList();
    }

    public List<Integer> findAllPkGrlPessoaByNomeIncompleto(String nome)
    {
        if (StringUtils.isNull(nome))
        {
            return new ArrayList();
        }
        nome = nome.trim().toLowerCase();
        // "SELECT e FROM Employee e WHERE e.name LIKE 'D%'"
        String query = "SELECT c.pkGrlPessoa FROM GrlPessoa c WHERE LOWER(c.nome) LIKE '%" + nome + "%'  ORDER BY c.nome";

        Query q = em.createQuery(query);
        return q.getResultList();
    }

//    public GrlPessoa findByNumeroIdentificacao(String numeroIdentificacao)
//    {
//        numeroIdentificacao = numeroIdentificacao.trim().toLowerCase();
//        Query query = em.createQuery("SELECT gp FROM GrlPessoa gp WHERE LOWER(gp.numeroIdentificacao) LIKE :numeroIdentificacao", GrlPessoa.class);
//        query.setParameter("numeroIdentificacao", numeroIdentificacao);
//        query.setMaxResults(1);
//        List<GrlPessoa> results = query.getResultList();
//        return results.isEmpty() ? null : results.get(0);
//    }
    public GrlPessoa findByNif(String nif)
    {
        nif = nif.trim().toLowerCase();
        Query query = em.createQuery("SELECT gp FROM GrlPessoa gp WHERE LOWER(gp.nif) LIKE :nif", GrlPessoa.class);
        query.setParameter("nif", nif);
        query.setMaxResults(1);
        List<GrlPessoa> results = query.getResultList();
        return results.isEmpty() ? null : results.get(0);
    }

    public GrlPessoa findByGrlPessoa(GrlPessoa pessoa)
    {
        GrlPessoa resultPessoa = findByNumeroIdentificacao(pessoa.getEhNacional(), pessoa.getNumeroIdentificacao());

        return resultPessoa != null ? resultPessoa : findByNome(pessoa.getNome());
    }

    public Integer findLastPkGrlPessoa()
    {
        Query query = em.createQuery("SELECT MAX(gp.pkGrlPessoa) FROM GrlPessoa gp");
        List<Integer> results = query.getResultList();
        return results.isEmpty() ? null : results.get(0);
    }

    public List<GrlPessoa> findAllPessoaColaboradorSemContaOrderedByNome(String nomePessoaPesquisar)
    {
        nomePessoaPesquisar = nomePessoaPesquisar.trim().toLowerCase();
        String query = constroiQuery(nomePessoaPesquisar);

        TypedQuery<GrlPessoa> t = em.createQuery(query, GrlPessoa.class);

        if (nomePessoaPesquisar != null && !nomePessoaPesquisar.isEmpty())
        {
            t.setParameter("nome", nomePessoaPesquisar);
        }

        return t.getResultList();
    }

    public String constroiQuery(String nomePessoaPesquisar)
    {
        nomePessoaPesquisar = nomePessoaPesquisar.trim();
        String query = "SELECT p FROM GrlPessoa p WHERE p.pkGrlPessoa IN (SELECT c.fkGrlPessoa.pkGrlPessoa FROM RhColaborador c ) AND p.pkGrlPessoa NOT IN (SELECT ct.fkSegPessoa FROM SegConta ct )";

        if (nomePessoaPesquisar != null && !nomePessoaPesquisar.isEmpty())
        {
            query += " AND LOWER(p.nome) LIKE :nome";
        }

        query += " ORDER BY p.nome";

        return query;
    }

    public boolean constrangimentosRespeitados(GrlPessoa grlPessoa)
    {
        ////////System.err.println("0 GrlPessoaFacade.constrangimentosRespeitados()\t dataNascimento: " + DataUtils.toStringFull(grlPessoa.getDataNascimento()));
        ////////System.err.println("1 GrlPessoaFacade.constrangimentosRespeitados()\t dataEmissao " + DataUtils.toStringFull(grlPessoa.getDataEmissao()));
        ////////System.err.println("2 GrlPessoaFacade.constrangimentosRespeitados()\t dataValidade: " + DataUtils.toStringFull(grlPessoa.getPrazoValidade()));
        if (!dataNascimentoValida(grlPessoa))
        {
            return false;
        }
        if (!dataEmissaoValida(grlPessoa))
        {
            return false;
        }
        return true;
    }

    private boolean dataNascimentoValida(GrlPessoa grlPessoa)
    {
        Date dataNascimento = grlPessoa.getDataNascimento();
        if (dataNascimento.after(new Date()))
        {
            ////////System.err.println("0 GrlPessoaFacade.dataNascimentoNaoValida()\tData de nascimento não pode ser posterior a data corrente");
            LogFile.erroMsg(null, "Data de nascimento não pode ser posterior a data corrente");
            return false;
        }
        else if (dataNascimento.after(grlPessoa.getDataEmissao())
            || dataNascimento.equals(grlPessoa.getDataEmissao()))
        {
            ////////System.err.println("0 GrlPessoaFacade.dataNascimentoNaoValida()\tData de nascimento não pode ser posterior ou igual a data de emissão");
            LogFile.erroMsg(null, "Data de nascimento não pode ser posterior ou igual a data de emissão");
            return false;
        }

        return true;
    }

    private boolean dataEmissaoValida(GrlPessoa grlPessoa)
    {
        Date dataEmissao = grlPessoa.getDataEmissao();

        if (dataEmissao.after(new Date()))
        {
            ////////System.err.println("0 GrlPessoaFacade.dataEmissaoNaoValida()\tData de emissão não pode ser posterior a data corrente");
            LogFile.erroMsg(null, "Data de emissão não pode ser posterior a data corrente");
            return false;
        }
        else if (dataEmissao.after(grlPessoa.getPrazoValidade())
            || dataEmissao.equals(grlPessoa.getPrazoValidade()))
        {
            ////////System.err.println("0 GrlPessoaFacade.dataEmissaoNaoValida()\tData de emissão não pode ser posterior ou igual a data de validade");
            LogFile.erroMsg(null, "Data de emissão não pode ser posterior ou igual a data de validade");
            return false;
        }

        return true;
    }

    public boolean ehAdulto(GrlPessoa grlPessoa)
    {
        Date dataNascimento = grlPessoa.getDataNascimento();
        Long idadeMiliSegundos = DataUtils.diff(new Date(), dataNascimento);
        Calendar c = Calendar.getInstance();
        c.setTime(new Date(idadeMiliSegundos));
        int anosIdade = c.get(Calendar.YEAR);

        ////////System.err.println("0: GrlPessoaFacade.ehAdulto()\tdataNascimento: " + DataUtils.toStringFull(dataNascimento)
//            + "\nanosIdade: " + anosIdade);
        return anosIdade >= 18;
    }

    public GrlPessoa capitalizeEveryWord(GrlPessoa reg)
    {
        String nome = reg.getNome();
        String pai = reg.getPai();
        String mae = reg.getMae();
        String numeroIdentificacao = reg.getNumeroIdentificacao();

//////////System.err.println("0: GrlPessoaFacade.capitalizeEveryWord()");
        reg.setNome(StringUtils.capitalizeEveryWord(nome));
//////////System.err.println("1: GrlPessoaFacade.capitalizeEveryWord()");		
        reg.setMae(StringUtils.capitalizeEveryWord(mae));
//////////System.err.println("2: GrlPessoaFacade.capitalizeEveryWord()");		
        reg.setPai(StringUtils.capitalizeEveryWord(pai));
        reg.setNumeroIdentificacao(numeroIdentificacao.toUpperCase());
//////////System.err.println("3: GrlPessoaFacade.capitalizeEveryWord()");		
        return reg;
    }

    private void initInstancia(GrlPessoa reg)
    {
//        //System.err.println("0: GrlPessoaFacade.initInstancia()");
        reg.setNome(" ");
        reg.setPai(" ");
        reg.setMae(" ");
        reg.setNumeroIdentificacao(" ");
        reg.setNif(" ");
//        //System.err.println("01: GrlPessoaFacade.initInstancia()");
        reg.setFkEstadoCivil(this.grlEstadoCivilCache.getInstancia());
//        //System.err.println("1: GrlPessoaFacade.initInstancia()");
//        reg.setFkNaturalidadeMunicipio(this.locMunicipiosCache.findMunicipioIndefinidoDeAngola());
//        //System.err.println("2: GrlPessoaFacade.initInstancia()");
      //  reg.setFkResidenciaBairro(this.locBairrosCache.findBairroIndefinidoDeAngola());
//        //System.err.println("3: GrlPessoaFacade.initInstancia()");
        reg.setEhNacional(true);
//        //System.err.println("4: GrlPessoaFacade.initInstancia()");
        reg.setFkSexo(this.grlSexoCache.getInstancia());
        reg.setVitalicio(Boolean.FALSE);
        reg.setOk(false);
//        //System.err.println("5: GrlPessoaFacade.initInstancia()");
    }

    public boolean equals(GrlPessoa pessoa1, GrlPessoa pessoa2)
    {
        if (pessoa1.getEhNacional() != pessoa2.getEhNacional())
        {
            return false;
        }
        return pessoa1.getNumeroIdentificacao().trim().equalsIgnoreCase(pessoa2.getNumeroIdentificacao().trim());
    }

    public GrlPessoa getInstancia()
    {
//        //System.err.println("0: GrlPessoaFacade.getInstancia()");
        GrlPessoa reg = new GrlPessoa();
        initInstancia(reg);
//        //System.err.println("1: GrlPessoaFacade.getInstancia()");
        return reg;
    }

    public GrlPessoa trim(GrlPessoa reg)
    {
//////System.err.println("0: GrlPessoaFacade.trim()");        
        if (StringUtils.isNull(reg.getNif()))
        {
            reg.setNif(" ");
        }
        else
        {
            reg.setNif(reg.getNif().trim());
        }
//////System.err.println("1: GrlPessoaFacade.trim()");        
        String nome = reg.getNome();
//////System.err.println("1.0: GrlPessoaFacade.trim()\tnome: " + nome);        
        if (StringUtils.isNull(nome))
        {
//////System.err.println("1.1: GrlPessoaFacade.trim()\tnome: " + nome);            
            reg.setNome(" ");
        }
        else
        {
//////System.err.println("1.2: GrlPessoaFacade.trim()\tnome: " + nome);            
            nome = nome.trim();
//////System.err.println("1.3: GrlPessoaFacade.trim()\tnome: " + nome);            
            nome = StringUtils.capitalizeEveryWord(nome);
//////System.err.println("1.4: GrlPessoaFacade.trim()\tnome: " + nome);            
            reg.setNome(nome);
        }
//////System.err.println("2: GrlPessoaFacade.trim()");        
        if (StringUtils.isNull(reg.getNumeroIdentificacao()))
        {
            reg.setNumeroIdentificacao(" ");
        }
        else
        {
            reg.setNumeroIdentificacao(reg.getNumeroIdentificacao().trim());
        }
//////System.err.println("3: GrlPessoaFacade.trim()");        
        if (StringUtils.isNull(reg.getPai()))
        {
            reg.setPai(" ");
        }
        else
        {
            reg.setPai(reg.getPai().trim());
        }
//////System.err.println("4: GrlPessoaFacade.trim()");        
        if (StringUtils.isNull(reg.getMae()))
        {
            reg.setMae(" ");
        }
        else
        {
            reg.setMae(reg.getMae().trim());
        }
//////System.err.println("5: GrlPessoaFacade.trim()");        
        if (StringUtils.isNull(reg.getTelefonePrincipal()))
        {
            reg.setTelefonePrincipal(" ");
        }
        else
        {
            reg.setTelefonePrincipal(reg.getTelefonePrincipal().trim());
        }
//////System.err.println("6: GrlPessoaFacade.trim()");        
        if (StringUtils.isNull(reg.getTelefoneAlternativo()))
        {
            reg.setTelefoneAlternativo(" ");
        }
        else
        {
            reg.setTelefoneAlternativo(reg.getTelefoneAlternativo().trim());
        }
//////System.err.println("7: GrlPessoaFacade.trim()");        
        if (StringUtils.isNull(reg.getEmailPrincipal()))
        {
            reg.setEmailPrincipal(" ");
        }
        else
        {
            reg.setEmailPrincipal(reg.getEmailPrincipal().trim());
        }
//////System.err.println("8: GrlPessoaFacade.trim()");        
        if (StringUtils.isNull(reg.getEmailAlternativo()))
        {
            reg.setEmailAlternativo(" ");
        }
        else
        {
            reg.setEmailAlternativo(reg.getEmailAlternativo().trim());
        }
//////System.err.println("9: GrlPessoaFacade.trim()");        
        return reg;
    }

    public boolean create_pessoa(GrlPessoa reg)
    {
        try
        {
            create(reg);
            return true;
        }
        catch (Exception ex)
        {
            return false;
        }
    }

    public GrlPessoa copy(GrlPessoa source, GrlPessoa dest)
    {
        dest.setDataCadastro(dest.getDataCadastro());
        dest.setDataEmissao(dest.getDataEmissao());
        dest.setDataNascimento(source.getDataNascimento());
        dest.setEhNacional(source.getEhNacional());
        dest.setEmailAlternativo(source.getEmailAlternativo());
        dest.setEmailPrincipal(source.getEmailPrincipal());
        dest.setFkEstadoCivil(source.getFkEstadoCivil());
        dest.setFkNaturalidadeMunicipio(source.getFkNaturalidadeMunicipio());
        dest.setFkResidenciaBairro(source.getFkResidenciaBairro());
        dest.setFkSexo(source.getFkSexo());
        dest.setMae(source.getMae());
        dest.setNif(source.getNif());
        dest.setNome(source.getNome());
        dest.setNumeroIdentificacao(source.getNumeroIdentificacao());
        dest.setPai(source.getPai());
        dest.setPrazoValidade(source.getPrazoValidade());
        dest.setTelefoneAlternativo(source.getTelefoneAlternativo());
        dest.setTelefonePrincipal(source.getTelefonePrincipal());
        dest.setVitalicio(source.getVitalicio());

        return dest;
    }

/*    public GrlPessoa copyComentarios(ProcessoCandidaturaDados p, GrlPessoa pessoa)
    {
        pessoa.setComentarioDataNascimento(p.getComentarioDataNascimento());
        pessoa.setComentarioNome(p.getComentarioNomePessoaSingular());
        pessoa.setComentarioNumeroIdentificacao(p.getComentarioNumeroIdentificacao());
        pessoa.setComentarioSexo(p.getComentarioSexo());
        pessoa.setComentarioTelefone(p.getComentarioTelefone());
        pessoa.setComentarioEmail(p.getComentarioEmail());
//        pessoa.setComentari-oTipologia(p.getComentarioTipologia());
        return pessoa;
    }*/

    public boolean processOk(GrlPessoa p)
    {
////System.err.println("0: GrlPessoaFacade.processOk()\tp: " + p.getNome());        
        String nome = p.getNome();
        if (nome.startsWith(Defs.NOME_PESSOA_TEMPORARIO))
        {
////System.err.println("1: GrlPessoaFacade.processOk()\tp: " + p.getNome());            
            p.setOk(false);
            return false;
        }
        else if (nome.startsWith(Defs.NOME_REPRESENTANTE_TEMPORARIO))
        {
////System.err.println("2: GrlPessoaFacade.processOk()\tp: " + p.getNome());            
            p.setOk(false);
            return false;
        }
        else
        {
////System.err.println("3: GrlPessoaFacade.processOk()\tp: " + p.getNome());            
            p.setComentarioNome(null);
        }
////System.err.println("4: GrlPessoaFacade.processOk()\tp: " + p.getNome());        
        String numeroIdentificacao = p.getNumeroIdentificacao();
        if (numeroIdentificacao.startsWith(Defs.BI_PESSOA_TEMPORARIO))
        {
////System.err.println("5: GrlPessoaFacade.processOk()\tp: " + p.getNome());            
            p.setOk(false);
            return false;
        }
        else if (numeroIdentificacao.startsWith(Defs.BI_REPRESENTANTE_TEMPORARIO))
        {
////System.err.println("6: GrlPessoaFacade.processOk()\tp: " + p.getNome());            
            p.setOk(false);
            return false;
        }
        else
        {
////System.err.println("7: GrlPessoaFacade.processOk()\tp: " + p.getNome());            
            p.setComentarioNumeroIdentificacao(null);
        }
        if (StringUtils.isNull(p.getEmailPrincipal()) || !StringUtils.isNull(p.getComentarioEmail()))
        {
////System.err.println("8: GrlPessoaFacade.processOk()\tp: " + p.getNome());            
            p.setOk(false);
            return false;
        }
////System.err.println("9: GrlPessoaFacade.processOk()\tp: " + p.getNome());        
        if (!StringUtils.isNull(p.getComentarioDataNascimento())
            || !StringUtils.isNull(p.getComentarioSexo())
            || !StringUtils.isNull(p.getComentarioTelefone()))
        {
////System.err.println("10: GrlPessoaFacade.processOk()\tp: " + p.getNome());            
            p.setOk(false);
            return false;
        }
////System.err.println("11: GrlPessoaFacade.processOk()\tp: " + p.getNome());        
        p.setOk(true);
        return true;
    }

    public boolean numeroIdentificacaoIsOk(GrlPessoa pessoa)
    {
        String numeroIdentificacao = pessoa.getNumeroIdentificacao();
        if (StringUtils.isNull(numeroIdentificacao))
        {
            return false;
        }
        return !numeroIdentificacao.startsWith(Defs.NOME_PESSOA_TEMPORARIO);
    }

    public boolean nomeIsOk(GrlPessoa pessoa)
    {
////System.err.println("0: GrlPessoaFacade.nomeIsOk()\tpessoa: " + pessoa.getNome());        
        String nome = pessoa.getNome();
        if (StringUtils.isNull(nome))
        {
            return false;
        }

        return !nome.startsWith(Defs.NOME_PESSOA_TEMPORARIO)
            && !nome.startsWith(Defs.NOME_REPRESENTANTE_TEMPORARIO)
            && StringUtils.isNull(pessoa.getComentarioNome());
    }

    public List<String> getComentarios(GrlPessoa grlPessoa)
    {
        String comentarioNome = grlPessoa.getComentarioNome(),
            comentarioNumeroIdentificacao = grlPessoa.getComentarioNumeroIdentificacao();
        boolean comentarioNomeNumeroIdentificacaoFlag = (!StringUtils.isNull(comentarioNome)
            && !StringUtils.isNull(comentarioNumeroIdentificacao));
        return comentarioNomeNumeroIdentificacaoFlag ? StringUtils.toList(
            comentarioNome,
            comentarioNumeroIdentificacao,
            "Deve corrigir primeiro o Número de Identificação e depois o Nome da Pessoa",
            grlPessoa.getComentarioEmail(),
            grlPessoa.getComentarioDataNascimento(),
            grlPessoa.getComentarioSexo(),
            grlPessoa.getComentarioTelefone())
            : StringUtils.toList(
                comentarioNome,
                comentarioNumeroIdentificacao,
                grlPessoa.getComentarioEmail(),
                grlPessoa.getComentarioDataNascimento(),
                grlPessoa.getComentarioSexo(),
                grlPessoa.getComentarioTelefone());
    }

    public boolean processarComentarios(GrlPessoa grlPessoa)
    {
        List<String> comentarios = getComentarios(grlPessoa);
        if (comentarios.isEmpty())
        {
            return false;
        }
        LogFile.warnMsg(null, comentarios);
        return true;
    }
    
}
