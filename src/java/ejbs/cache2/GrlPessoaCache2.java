/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.cache2;

import ejbs.cache.GrlEstadoCivilCache;
import ejbs.cache.GrlSexoCache;
//import ejbs.cache.LocBairrosCache;
//import ejbs.cache.LocMunicipiosCache;
import ejbs.cache.utils.TabelaPadraoCacheUtils;
import ejbs.entities.GrlPessoa;
import ejbs.facades.GrlPessoaFacade;
import ejbs.facades.SegContaFacade;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
//import prj.utils.ProcessoCandidaturaDados;
import utils.DataUtils;
import utils.Defs;
import utils.ListUtils;
import utils.StringUtils;
import utils.mensagens.LogFile;

/**
 *
 * @author Aires Veloso
 */
@Named(value = "grlPessoaCache2")
@ApplicationScoped
public class GrlPessoaCache2 extends TabelaPadraoCacheUtils<GrlPessoa>
{

    @EJB
    private SegContaFacade segContaFacade;

    @EJB
    private GrlPessoaFacade grlPessoaFacade;

    @Inject
    private GrlSexoCache grlSexoCache;

//    @Inject
//    private RhColaboradorCache2 rhColaboradorCache2;

    @Inject
    private GrlEstadoCivilCache grlEstadoCivilCache;
//
//    @Inject
//    private LocMunicipiosCache locMunicipiosCache;

//    @Inject
//    private LocBairrosCache locBairrosCache;

    private List<GrlPessoa> grlPessoaColaboradorList,
        grlPessoaColaboradorSemContaList;

    private boolean colaboradorAdded;

    /**
     * Creates a new instance of GrlPessoaCache2
     */
    public GrlPessoaCache2()
    {
    }

    @PostConstruct
    @Override
    public void init()
    {
        colaboradorAdded = false;
//        System.err.println("0: GrlPessoaCache2.init()");
        List<GrlPessoa> list = this.grlPessoaFacade.findAll();
//        verificarDuplicados(list);
//        System.err.println("1: GrlPessoaCache2.init()\tlist.size: " + list.size());
        grlPessoaColaboradorList = new ArrayList();
        grlPessoaColaboradorSemContaList = new ArrayList();

        for (GrlPessoa p : list)
        {
            put(p);
        }
        this.tabelaStringListObject.put("All", list);
        putAllOrderedByNome(list);
        putAllPessoaColaboradorOrderedByNome();
//        System.err.println("2: GrlPessoaCache2.init()\tlist.size: " + list.size());
    }

    @Override
    public int getPKEntity(Object ob)
    {
        GrlPessoa ci = (GrlPessoa) ob;
        return ci.getPkGrlPessoa();
    }

    @Override
    public String toStringKey(Object obj)
    {
        GrlPessoa ci = (GrlPessoa) obj;
        return ci.getNumeroIdentificacao();
    }

    @Override
    public String nomeEntidade()
    {
        return "GrlPessoa";
    }

    @Override
    public boolean areTwins(Object o1, Object o2)
    {
        GrlPessoa t1 = (GrlPessoa) o1;
        GrlPessoa t2 = (GrlPessoa) o2;

        return t1.getNumeroIdentificacao().trim().equalsIgnoreCase(t2.getNumeroIdentificacao().trim());
    }

    @Override
    public void resetAll()
    {
        super.resetAll();
        grlPessoaColaboradorList = new ArrayList();
        grlPessoaColaboradorSemContaList = new ArrayList();
        System.gc();
    }

    @Override
    public void resetParcial()
    {
        super.resetParcial();
        grlPessoaColaboradorList = new ArrayList();
        grlPessoaColaboradorSemContaList = new ArrayList();
        System.gc();
    }

    public void restartAll()
    {
        this.resetAll();
        this.init();
    }

    private void putAllOrderedByNome(List<GrlPessoa> list)
    {
        if (list.size() > 1)
        {
            Collections.sort(list, (o1, o2) ->
            {
                GrlPessoa c1 = (GrlPessoa) o1;
                GrlPessoa c2 = (GrlPessoa) o2;
                return c1.getNome().compareToIgnoreCase(c2.getNome());
            });
        }
        this.tabelaStringListObject.put("AllBYN", list);
    }

    private List<GrlPessoa> getAllOrderedByNome()
    {
        return this.tabelaStringListObject.get("AllBYN");
    }

    private void putAllPessoaColaboradorOrderedByNome()
    {
        sortAllPessoaColaborador(grlPessoaColaboradorList);
        sortAllPessoaColaborador(this.grlPessoaColaboradorSemContaList);
    }

    private void sortAllPessoaColaborador(List<GrlPessoa> list)
    {
//        Query query = em.createQuery("SELECT p FROM GrlPessoa p WHERE p.pkGrlPessoa IN (SELECT c.fkGrlPessoa.pkGrlPessoa FROM RhColaborador c ) ORDER BY p.nome ", GrlPessoa.class);
//        return query.getResultList();
        if (list.size() > 1)
        {
            Collections.sort(list, (o1, o2) ->
            {
                GrlPessoa c1 = (GrlPessoa) o1;
                GrlPessoa c2 = (GrlPessoa) o2;
                return c1.getNome().compareToIgnoreCase(c2.getNome());
            });
        }
    }

    private void updateInGrlPessoaColaboradorSemContaList(GrlPessoa p)
    {
        int i = 0;
        int pkGrlPessoa = p.getPkGrlPessoa();
        for (GrlPessoa cl : this.grlPessoaColaboradorSemContaList)
        {
            if (pkGrlPessoa == cl.getPkGrlPessoa())
            {
                if (this.segContaFacade.findAllQuantidadeDeContaByPkGrlPessoa(p.getPkGrlPessoa()) == 0)
                {
                    this.grlPessoaColaboradorSemContaList.set(i, p);
                }
                return;
            }
            i++;
        }
    }

    private void updateInGrlPessoaColaboradorList(GrlPessoa p)
    {
        int i = 0;
        int pkGrlPessoa = p.getPkGrlPessoa();
        for (GrlPessoa cl : this.grlPessoaColaboradorList)
        {
            if (pkGrlPessoa == cl.getPkGrlPessoa())
            {
                grlPessoaColaboradorList.set(i, p);
                if (this.segContaFacade.findAllQuantidadeDeContaByPkGrlPessoa(p.getPkGrlPessoa()) == 0)
                {
                    updateInGrlPessoaColaboradorSemContaList(p);
                }
                return;
            }
            i++;
        }
        grlPessoaColaboradorList.add(p);
        if (this.segContaFacade.findAllQuantidadeDeContaByPkGrlPessoa(p.getPkGrlPessoa()) == 0)
        {
            grlPessoaColaboradorSemContaList.add(p);
        }
    }

    @Override
    public void put(GrlPessoa p)
    {
        super.put(p);

        putParcial(p);
    }

    public void putSimple(GrlPessoa p)
    {
        super.put(p);
    }

    public void putParcial(GrlPessoa p)
    {
        colaboradorAdded = false;

        putByNumeroIdentificacao(p);
        putByNome(p);
//        putByNif(p);

      /*  if (this.rhColaboradorCache2.findByPkGrlPessoa(p.getPkGrlPessoa()) != null)
        {
            colaboradorAdded = true;
            updateInGrlPessoaColaboradorList(p);
//SELECT p FROM GrlPessoa p WHERE p.pkGrlPessoa IN (SELECT c.fkGrlPessoa.pkGrlPessoa FROM RhColaborador c ) AND p.pkGrlPessoa NOT IN (SELECT ct.fkSegPessoa FROM SegConta ct)            

        }*/
    }

//    private void putAll(GrlPessoa p)
//    {
//        put(p);
//        List<GrlPessoa> listAll = findAll();
//        listAll.add(p);
//
//        this.tabelaStringListObject.put("All", listAll);
//        putAllOrderedByNome(listAll);
//
//        if (colaboradorAdded)
//        {
//            this.putAllPessoaColaboradorOrderedByNome();
//        }
//    }
    @Override
    protected void restart()
    {
//System.err.println("0: GrlPessoaCache2.restart()");        
        if (updated)
        {
//System.err.println("1: GrlPessoaCache2.restart()");            
            return;
        }
        this.resetParcial();
//System.err.println("2: GrlPessoaCache2.restart()");        
        super.restart();

        List<GrlPessoa> grlPessoaList = this.toList();
//        verificarDuplicados(grlPessoaList);
        if ((grlPessoaList == null) || grlPessoaList.isEmpty())
        {
            updated = true;
//System.err.println("3: GrlPessoaCache2.restart()");            
            return;
        }

        this.tabelaStringListObject.put("All", grlPessoaList);
        putAllOrderedByNome(grlPessoaList);
//System.err.println("4: GrlPessoaCache2.restart()\tprjProcessoCandidaturaList.size: " +
//            (prjProcessoCandidaturaList == null ? "null" : prjProcessoCandidaturaList.size()));        
        grlPessoaList.forEach((grlPessoa) ->
        {
            putParcial(grlPessoa);

            if (colaboradorAdded)
            {
                this.putAllPessoaColaboradorOrderedByNome();
            }
        });
//System.err.println("5: GrlPessoaCache2.restart()");    
        updated = true;
    }

    private void putByNome(GrlPessoa p)
    {
        String nome = p.getNome();
        if (StringUtils.isNull(nome))
        {
            return;
        }
        String chave = this.geraChave("Nome", nome);
        this.tabelaStringObject.put(chave, p);
    }

    private GrlPessoa getByNome(String nome)
    {
        String chave = this.geraChave("Nome", nome);
        return this.tabelaStringObject.get(chave);
    }

    private void putByNumeroIdentificacao(GrlPessoa p)
    {
        String numeroIdentificacao = p.getNumeroIdentificacao();
//if (numeroIdentificacao.equalsIgnoreCase("001773153LA032"))
// System.err.println("0: GrlPessoaCache2.putByNumeroIdentificacao()\tp: " +
//     p.getNome() + ", " + p.getNumeroIdentificacao());   
        if (StringUtils.isNull(numeroIdentificacao))
        {
            return;
        }
        String chave = this.geraChave(p);
        this.tabelaStringObject.put(chave.toUpperCase(), p);
    }

    public String geraChave(GrlPessoa p)
    {
        return "NumeroIdentificacao_"
            + (p.getEhNacional() ? 1 : 0) + "_"
            + p.getNumeroIdentificacao();
    }

    public String geraChaveByNumeroIdentificacao(boolean ehNacional, String numeroIdentificacao)
    {
        return "NumeroIdentificacao_"
            + (ehNacional ? 1 : 0) + "_"
            + numeroIdentificacao;
    }

    private GrlPessoa getByNumeroIdentificacao(boolean ehNacional, String numeroIdentificacao)
    {
        String chave = this.geraChaveByNumeroIdentificacao(ehNacional,
            numeroIdentificacao);
        GrlPessoa grlPessoa = this.tabelaStringObject.get(chave.toUpperCase());
        if (grlPessoa != null)
        {
            return grlPessoa;
        }
        if (numeroIdentificacao.indexOf('$') != -1)
        {
            return null;
        }
        numeroIdentificacao = numeroIdentificacao + "$1";
        chave = this.geraChaveByNumeroIdentificacao(ehNacional, numeroIdentificacao);
        return this.tabelaStringObject.get(chave);
    }

    public List<GrlPessoa> findAllByNumeroIdentificacao(String numeroIdentificacao)
    {
        this.restart();
        List<GrlPessoa> list = new ArrayList();
        String numeroIdentificacaoTmp;

        numeroIdentificacao = StringUtils.getChaveOriginal(numeroIdentificacao);
        GrlPessoa pessoaTmp = this.findByNumeroIdentificacao(true, numeroIdentificacao);
        if (pessoaTmp != null)
        {
            list.add(pessoaTmp);
        }
        pessoaTmp = this.findByNumeroIdentificacao(false, numeroIdentificacao);
        if (pessoaTmp != null)
        {
            list.add(pessoaTmp);
        }
        boolean add;
        for (int i = 1;; i++)
        {
            add = false;
            numeroIdentificacaoTmp = numeroIdentificacao + "$" + i;
            pessoaTmp = this.findByNumeroIdentificacao(true, numeroIdentificacao);
            if (pessoaTmp != null)
            {
                list.add(pessoaTmp);
                add = true;
            }
            pessoaTmp = this.findByNumeroIdentificacao(false, numeroIdentificacao);
            if (pessoaTmp != null)
            {
                list.add(pessoaTmp);
                add = true;
            }
            if (!add)
            {
                break;
            }
        }
        return list;
    }

    public List<GrlPessoa> findAllByGrlPessoa(GrlPessoa pessoa)
    {
        this.restart();
//System.err.println("0: GrlPessoaCache2.findAllByGrlPessoa(GrlPessoa)\tpessoa: "
//    + (pessoa == null ? "null" : pessoa.getNome()));        
        List<GrlPessoa> list = new ArrayList();
        String numeroIdentificacao = pessoa.getNumeroIdentificacao(),
            numeroIdentificacaoTmp;
//System.err.println("1: GrlPessoaCache2.findAllByGrlPessoa(GrlPessoa)\tnumeroIdentificacao: " +
//            numeroIdentificacao);
        numeroIdentificacao = StringUtils.getChaveOriginal(numeroIdentificacao);
//System.err.println("2: GrlPessoaCache2.findAllByGrlPessoa(GrlPessoa)\tnumeroIdentificacao: " +
//            numeroIdentificacao);        
        GrlPessoa pessoaTmp = this.findByNumeroIdentificacao(pessoa.getEhNacional(), numeroIdentificacao);
//System.err.println("3: GrlPessoaCache2.findAllByGrlPessoa(GrlPessoa)\tpessoaTmp: " +
//        (pessoaTmp == null ? "null" : (pessoaTmp.getPkGrlPessoa() + ", " + pessoaTmp.getNome()) ));
        if (pessoaTmp != null)
        {
//System.err.println("4: GrlPessoaCache2.findAllByGrlPessoa(GrlPessoa)");             
            list.add(pessoaTmp);
        }
//System.err.println("5: GrlPessoaCache2.findAllByGrlPessoa(GrlPessoa)");        
        for (int i = 1;; i++)
        {
            numeroIdentificacaoTmp = numeroIdentificacao + "$" + i;
//System.err.println("6: GrlPessoaCache2.findAllByGrlPessoa(GrlPessoa)\tnumeroIdentificacaoTmp: " + numeroIdentificacaoTmp);            
            pessoaTmp = this.findByNumeroIdentificacao(pessoa.getEhNacional(), numeroIdentificacaoTmp);
//System.err.println("7: GrlPessoaCache2.findAllByGrlPessoa(GrlPessoa)\tpessoaTmp: " +
//        (pessoaTmp == null ? "null" : pessoaTmp.getNome()));            
            if (pessoaTmp != null)
            {
//System.err.println("8: GrlPessoaCache2.findAllByGrlPessoa(GrlPessoa)");                
                list.add(pessoaTmp);
            }
            else
            {
//System.err.println("9: GrlPessoaCache2.findAllByGrlPessoa(GrlPessoa)");                
                break;
            }
        }
//System.err.println("10: GrlPessoaCache2.findAllByGrlPessoa(GrlPessoa)\tlist: " + list.size());        
        return list;
    }

    public GrlPessoa findByGrlPessoa(GrlPessoa pessoa)
    {
//System.err.println("0: GrlPessoaCache2.findByGrlPessoa(GrlPessoa)");        
        return findByNumeroIdentificacao(
            pessoa.getEhNacional(), pessoa.getNumeroIdentificacao());
//System.err.println("0: GrlPessoaCache2.findByGrlPessoa(GrlPessoa)");
    }

    public GrlPessoa findByNumeroIdentificacao(boolean ehNacional, String numeroIdentificacao)
    {
//System.err.println("0: GrlPessoaCache2.findByNumeroIdentificacao(boolean)\tnumeroIdentificacao: " + numeroIdentificacao);        
        if (StringUtils.isNull(numeroIdentificacao))
        {
            return null;
        }
//System.err.println("1: GrlPessoaCache2.findByNumeroIdentificacao(boolean)\tnumeroIdentificacao: " + numeroIdentificacao);        
        restart();
        numeroIdentificacao = numeroIdentificacao.trim();
//System.err.println("2: GrlPessoaCache2.findByNumeroIdentificacao(boolean)\tnumeroIdentificacao: " + numeroIdentificacao);        
        return this.getByNumeroIdentificacao(ehNacional, numeroIdentificacao);
    }

    public GrlPessoa findByNome(String nome)
    {
        if (!StringUtils.isNull(nome))
        {
            return null;
        }
        restart();
        nome = nome.trim();
        return this.getByNome(nome);
    }

    public List<Integer> findAllPkGrlPessoaByNomeIncompleto(String nome)
    {
        if (StringUtils.isNull(nome))
        {
            return new ArrayList();
        }
        restart();
        nome = nome.trim().toLowerCase();
//        // "SELECT e FROM Employee e WHERE e.name LIKE 'D%'"
//        String query = "SELECT c.pkGrlPessoa FROM GrlPessoa c WHERE LOWER(c.nome) LIKE '%" + nome + "%'  ORDER BY c.nome";
//
//        Query q = em.createQuery(query);
//        return q.getResultList();
        List<GrlPessoa> pessoas = this.findAll();
        List<Integer> listaPk = new ArrayList();
        for (GrlPessoa p : pessoas)
        {
            if (p.getNome().toLowerCase().indexOf(nome) != -1)
            {
                listaPk.add(p.getPkGrlPessoa());
            }
        }
        return listaPk;
    }

    public List<Integer> findByNumeroIdentificacaoIncompleto(String numeroIdentificacao)
    {
        if (StringUtils.isNull(numeroIdentificacao))
        {
            return new ArrayList();
        }
        restart();
        numeroIdentificacao = numeroIdentificacao.trim().toLowerCase();
        // "SELECT e FROM Employee e WHERE e.name LIKE 'D%'"
//        String query = "SELECT c.pkGrlPessoa FROM GrlPessoa c WHERE LOWER(c.numeroIdentificacao) LIKE '%" + numeroIdentificacao + "%'  ORDER BY c.nome";
//
//        Query q = em.createQuery(query);
//        return q.getResultList();
        List<GrlPessoa> pessoas = this.findAll();
        List<GrlPessoa> pessoasTmp = new ArrayList();
        for (GrlPessoa pessoa : pessoas)
        {
            if (pessoa.getNumeroIdentificacao().trim().toLowerCase().indexOf(numeroIdentificacao) != -1)
            {
                pessoasTmp.add(pessoa);
            }
        }
        if (pessoasTmp.size() > 1)
        {
            Collections.sort(pessoasTmp, (o1, o2) ->
            {
                GrlPessoa c1 = (GrlPessoa) o1;
                GrlPessoa c2 = (GrlPessoa) o2;
                return c1.getNome().compareToIgnoreCase(c2.getNome());
            });
        }
        List<Integer> pessoasPk = new ArrayList();
        for (GrlPessoa pessoa : pessoasTmp)
        {
            if (!ListUtils.includes(pessoasPk, pessoa.getPkGrlPessoa()))
            {
                pessoasPk.add(pessoa.getPkGrlPessoa());
            }
        }
        return pessoasPk;
    }

    public List<GrlPessoa> findAllOrderedByNome()
    {
//        Query query = em.createQuery("SELECT p FROM GrlPessoa p ORDER BY p.nome ", GrlPessoa.class);
//        return query.getResultList();
        restart();
        return this.getAllOrderedByNome();
    }

    public List<GrlPessoa> findAllPessoaColaboradorOrderedByNome()
    {
//        Query query = em.createQuery("SELECT p FROM GrlPessoa p WHERE p.pkGrlPessoa IN (SELECT c.fkGrlPessoa.pkGrlPessoa FROM RhColaborador c ) ORDER BY p.nome ", GrlPessoa.class);
//        return query.getResultList();
        restart();
        return this.grlPessoaColaboradorList;
    }

    public Integer findLastPkGrlPessoa()
    {
        return this.grlPessoaFacade.findLastPkGrlPessoa();
    }

    public List<GrlPessoa> findAllPessoaColaboradorSemContaOrderedByNome(String nomePessoaPesquisar)
    {
        restart();
//SELECT p FROM GrlPessoa p WHERE p.pkGrlPessoa IN (SELECT c.fkGrlPessoa.pkGrlPessoa FROM RhColaborador c ) AND p.pkGrlPessoa NOT IN (SELECT ct.fkSegPessoa FROM SegConta ct) AND LOWER(p.nome) LIKE :nome        
        return this.grlPessoaColaboradorSemContaList;
    }

    public boolean isMasculino(GrlPessoa reg)
    {
        return reg.getFkSexo().getNome().equals("Masculino");
    }

    public boolean constrangimentosRespeitados(GrlPessoa grlPessoa)
    {
        //System.err.println("0 GrlPessoaFacade.constrangimentosRespeitados()\t dataNascimento: " + DataUtils.toStringFull(grlPessoa.getDataNascimento()));
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
            //System.err.println("0 GrlPessoaFacade.dataNascimentoNaoValida()\tData de nascimento não pode ser posterior a data corrente");
            LogFile.erroMsg(null, "Data de nascimento não pode ser posterior a data corrente");
            return false;
        }
        else if (dataNascimento.after(grlPessoa.getDataEmissao())
            || dataNascimento.equals(grlPessoa.getDataEmissao()))
        {
            //System.err.println("0 GrlPessoaFacade.dataNascimentoNaoValida()\tData de nascimento não pode ser posterior ou igual a data de emissão");
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
            //System.err.println("0 GrlPessoaFacade.dataEmissaoNaoValida()\tData de emissão não pode ser posterior a data corrente");
            LogFile.erroMsg(null, "Data de emissão não pode ser posterior a data corrente");
            return false;
        }
        else if (dataEmissao.after(grlPessoa.getPrazoValidade())
            || dataEmissao.equals(grlPessoa.getPrazoValidade()))
        {
            //System.err.println("0 GrlPessoaFacade.dataEmissaoNaoValida()\tData de emissão não pode ser posterior ou igual a data de validade");
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

        //System.err.println("0: GrlPessoaFacade.ehAdulto()\tdataNascimento: " + DataUtils.toStringFull(dataNascimento)
//            + "\nanosIdade: " + anosIdade);
        return anosIdade >= 18;
    }

    public GrlPessoa capitalizeEveryWord(GrlPessoa reg)
    {
        String nome = reg.getNome();
        String pai = reg.getPai();
        String mae = reg.getMae();
        String numeroIdentificacao = reg.getNumeroIdentificacao();

//System.err.println("0: GrlPessoaFacade.capitalizeEveryWord()");
        reg.setNome(StringUtils.capitalizeEveryWord(nome));
//System.err.println("1: GrlPessoaFacade.capitalizeEveryWord()");		
        reg.setMae(StringUtils.capitalizeEveryWord(mae));
//System.err.println("2: GrlPessoaFacade.capitalizeEveryWord()");		
        reg.setPai(StringUtils.capitalizeEveryWord(pai));
        reg.setNumeroIdentificacao(numeroIdentificacao.toUpperCase());
//System.err.println("3: GrlPessoaFacade.capitalizeEveryWord()");		
        return reg;
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
        dest.setNumeroIdentificacao(source.getNumeroIdentificacao().toUpperCase());
        dest.setPai(source.getPai());
        dest.setPrazoValidade(source.getPrazoValidade());
        dest.setTelefoneAlternativo(source.getTelefoneAlternativo());
        dest.setTelefonePrincipal(source.getTelefonePrincipal());
        dest.setVitalicio(source.getVitalicio());

        return dest;
    }

   /* public GrlPessoa copyComentarios(ProcessoCandidaturaDados p, GrlPessoa pessoa)
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
        boolean okStatus = true;
////System.err.println("0: GrlPessoaCache2.processOk()\tp: " + p.getNome());        
        String nome = p.getNome();
        if (nome.startsWith(Defs.NOME_PESSOA_TEMPORARIO))
        {
            okStatus = false;
////System.err.println("1: GrlPessoaCache2.processOk()\tp: " + p.getNome());            
            p.setOk(okStatus);
//            return false;
        }
        else if (nome.startsWith(Defs.NOME_REPRESENTANTE_TEMPORARIO))
        {
            okStatus = false;
////System.err.println("2: GrlPessoaCache2.processOk()\tp: " + p.getNome());            
            p.setOk(okStatus);
//            return false;
        }
        else
        {
////System.err.println("3: GrlPessoaCache2.processOk()\tp: " + p.getNome());            
            p.setComentarioNome(null);
        }
////System.err.println("4: GrlPessoaCache2.processOk()\tp: " + p.getNome());        
        String numeroIdentificacao = p.getNumeroIdentificacao();
        if (numeroIdentificacao.startsWith(Defs.BI_PESSOA_TEMPORARIO))
        {
            okStatus = false;
////System.err.println("5: GrlPessoaCache2.processOk()\tp: " + p.getNome());            
            p.setOk(okStatus);
//            return false;
        }
        else if (numeroIdentificacao.startsWith(Defs.BI_REPRESENTANTE_TEMPORARIO))
        {
            okStatus = false;
////System.err.println("6: GrlPessoaCache2.processOk()\tp: " + p.getNome());            
            p.setOk(okStatus);
//            return false;
        }
        else
        {
////System.err.println("7: GrlPessoaCache2.processOk()\tp: " + p.getNome());            
            p.setComentarioNumeroIdentificacao(null);
        }
        if (StringUtils.isNull(p.getTelefonePrincipal()) /*|| !StringUtils.isNull(p.getComentarioEmail()) */)
        {
            okStatus = false;
////System.err.println("8: GrlPessoaCache2.processOk()\tp: " + p.getNome());            
            p.setOk(okStatus);
//            return false;
        }
        else
        {
////System.err.println("7: GrlPessoaCache2.processOk()\tp: " + p.getNome());            
            p.setComentarioTelefone(null);
        }
        if (StringUtils.isNull(p.getEmailPrincipal()) /*|| !StringUtils.isNull(p.getComentarioEmail()) */)
        {
            okStatus = false;
////System.err.println("8: GrlPessoaCache2.processOk()\tp: " + p.getNome());            
            p.setOk(okStatus);
//            return false;
        }
        else
        {
////System.err.println("7: GrlPessoaCache2.processOk()\tp: " + p.getNome());            
            p.setComentarioEmail(null);
        }
////System.err.println("9: GrlPessoaCache2.processOk()\tp: " + p.getNome());        
        if (!StringUtils.isNull(p.getComentarioDataNascimento())
            || !StringUtils.isNull(p.getComentarioSexo())
            || !StringUtils.isNull(p.getComentarioTelefone()))
        {
            okStatus = false;
////System.err.println("10: GrlPessoaCache2.processOk()\tp: " + p.getNome());            
            p.setOk(okStatus);
//            return false;
        }
////System.err.println("11: GrlPessoaCache2.processOk()\tp: " + p.getNome());        
        p.setOk(okStatus);
        this.edit(p);
        return okStatus;
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
//System.err.println("0: GrlPessoaFacade.nomeIsOk()\tpessoa: " + pessoa.getNome());        
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
        processOk(grlPessoa);
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

    public boolean equals(GrlPessoa pessoa1, GrlPessoa pessoa2)
    {
        if (pessoa1.getEhNacional() != pessoa2.getEhNacional())
        {
            return false;
        }
        return pessoa1.getNumeroIdentificacao().trim().equalsIgnoreCase(pessoa2.getNumeroIdentificacao().trim());
    }

    public GrlPessoa trim(GrlPessoa reg)
    {
//System.err.println("0: GrlPessoaFacade.trim()");        
        if (StringUtils.isNull(reg.getNif()))
        {
            reg.setNif(" ");
        }
        else
        {
            reg.setNif(reg.getNif().trim());
        }
//System.err.println("1: GrlPessoaFacade.trim()");        
        String nome = reg.getNome();
//System.err.println("1.0: GrlPessoaFacade.trim()\tnome: " + nome);        
        if (StringUtils.isNull(nome))
        {
//System.err.println("1.1: GrlPessoaFacade.trim()\tnome: " + nome);            
            reg.setNome(" ");
        }
        else
        {
//System.err.println("1.2: GrlPessoaFacade.trim()\tnome: " + nome);            
            nome = nome.trim();
//System.err.println("1.3: GrlPessoaFacade.trim()\tnome: " + nome);            
            nome = StringUtils.capitalizeEveryWord(nome);
//System.err.println("1.4: GrlPessoaFacade.trim()\tnome: " + nome);            
            reg.setNome(nome);
        }
//System.err.println("2: GrlPessoaFacade.trim()");        
        if (StringUtils.isNull(reg.getNumeroIdentificacao()))
        {
            reg.setNumeroIdentificacao(" ");
        }
        else
        {
            reg.setNumeroIdentificacao(reg.getNumeroIdentificacao().trim().toUpperCase());
        }
//System.err.println("3: GrlPessoaFacade.trim()");        
        if (StringUtils.isNull(reg.getPai()))
        {
            reg.setPai(" ");
        }
        else
        {
            reg.setPai(reg.getPai().trim());
        }
//System.err.println("4: GrlPessoaFacade.trim()");        
        if (StringUtils.isNull(reg.getMae()))
        {
            reg.setMae(" ");
        }
        else
        {
            reg.setMae(reg.getMae().trim());
        }
//System.err.println("5: GrlPessoaFacade.trim()");        
        if (StringUtils.isNull(reg.getTelefonePrincipal()))
        {
            reg.setTelefonePrincipal(" ");
        }
        else
        {
            reg.setTelefonePrincipal(reg.getTelefonePrincipal().trim());
        }
//System.err.println("6: GrlPessoaFacade.trim()");        
        if (StringUtils.isNull(reg.getTelefoneAlternativo()))
        {
            reg.setTelefoneAlternativo(" ");
        }
        else
        {
            reg.setTelefoneAlternativo(reg.getTelefoneAlternativo().trim());
        }
//System.err.println("7: GrlPessoaFacade.trim()");        
        if (StringUtils.isNull(reg.getEmailPrincipal()))
        {
            reg.setEmailPrincipal(" ");
        }
        else
        {
            reg.setEmailPrincipal(reg.getEmailPrincipal().trim());
        }
//System.err.println("8: GrlPessoaFacade.trim()");        
        if (StringUtils.isNull(reg.getEmailAlternativo()))
        {
            reg.setEmailAlternativo(" ");
        }
        else
        {
            reg.setEmailAlternativo(reg.getEmailAlternativo().trim());
        }
//System.err.println("9: GrlPessoaFacade.trim()");        
        return reg;
    }

    private void initInstancia(GrlPessoa reg)
    {
//        System.err.println("0: GrlPessoaFacade.initInstancia()");
        reg.setNome(" ");
        reg.setPai(" ");
        reg.setMae(" ");
        reg.setNumeroIdentificacao(" ");
        reg.setNif(" ");
//        System.err.println("01: GrlPessoaFacade.initInstancia()");
        reg.setFkEstadoCivil(this.grlEstadoCivilCache.getInstancia());
//        System.err.println("1: GrlPessoaFacade.initInstancia()");
       // reg.setFkNaturalidadeMunicipio(this.locMunicipiosCache.findMunicipioIndefinidoDeAngola());
//        System.err.println("2: GrlPessoaFacade.initInstancia()");
       // reg.setFkResidenciaBairro(this.locBairrosCache.findBairroIndefinidoDeAngola());
//        System.err.println("3: GrlPessoaFacade.initInstancia()");
        reg.setEhNacional(true);
//        System.err.println("4: GrlPessoaFacade.initInstancia()");
        reg.setFkSexo(this.grlSexoCache.getInstancia());
        reg.setVitalicio(Boolean.FALSE);
        reg.setOk(false);
//        System.err.println("5: GrlPessoaFacade.initInstancia()");
    }

    public GrlPessoa getInstancia()
    {
//        System.err.println("0: GrlPessoaFacade.getInstancia()");
        GrlPessoa reg = new GrlPessoa();
        initInstancia(reg);
//        System.err.println("1: GrlPessoaFacade.getInstancia()");
        return reg;
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
/*
    public String toStringResidencia(GrlPessoa reg)
    {
        return "residente " + this.locBairrosCache.toStringExtended(reg.getFkResidenciaBairro());
    }

    public String toStringNaturalidade(GrlPessoa reg)
    {
        return "natural " + this.locMunicipiosCache.toStringExtended(reg.getFkNaturalidadeMunicipio());
    }*/

    public String toStringEstadoCivil(GrlPessoa reg)
    {
        //System.err.println("0. GrlPessoaFacade.toStringEstadoCivil()\reg.fksexo: " +
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

    public void create(GrlPessoa p)
    {
        this.grlPessoaFacade.create(p);
//        int pkGrlPessoa = this.grlPessoaFacade.findLastPkGrlPessoa();
//        p.setPkGrlPessoa(pkGrlPessoa);
//        super.put(p);
    }

    public void edit(GrlPessoa p)
    {
        this.grlPessoaFacade.edit(p);
        super.put(p);
    }
}
