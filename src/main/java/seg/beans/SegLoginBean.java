/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seg.beans;

//import ejbs.cache.EveTipoEventoCache;
//import ejbs.cache2.PrjCandidaturasGerirCache2;
//import ejbs.cache2.PrjProcessoCandidaturaCache2;
import ejbs.entities.GrlPessoa;
import ejbs.entities.SegAuditoria;
import ejbs.entities.SegConta;
import ejbs.entities.SegFuncionalidade;
import ejbs.entities.SegLogAcesso;
import ejbs.entities.SegPerfil;
import ejbs.entities.SegPerfilHasFuncionalidade;
import ejbs.facades.GrlPessoaFacade;
import ejbs.facades.SegAuditoriaFacade;
import ejbs.facades.SegContaFacade;
import ejbs.facades.SegFuncionalidadeFacade;
import ejbs.facades.SegLogAcessoFacade;
import ejbs.facades.SegPerfilHasFuncionalidadeFacade;
//import eve.utils.EveEventoGenerico;
//import eve.utils.EveTipoEventoEnum;
import geral.beans.AppInit;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.primefaces.PrimeFaces;
//import prj.projectos.beans.PrjProjectoPadraoBean;
import seg.utils.EncriptacaoDecriptacao;
import transaction.beans.TransactionalProcessBean;
import utils.Associacao;
import utils.ConcurrentHashMapPersonalized;
import utils.Defs;
import utils.StringUtils;
import utils.mensagens.LogFile;

/**
 *
 * @author adalberto
 */
@Named
@SessionScoped
public class SegLoginBean implements Serializable {

    @EJB
    private SegLogAcessoFacade logAcessoFacade;

    @EJB
    private SegContaFacade segContaFacade;

    @EJB
    private SegFuncionalidadeFacade funcionalidadeFacade;

    @EJB
    private SegPerfilHasFuncionalidadeFacade perfilFuncionalidadeFacade;

    @EJB
    private SegAuditoriaFacade segAuditoriaFacade;

    @EJB
    private GrlPessoaFacade grlPessoaFacade;

//    @Inject
//    private EveEventoCache2 eveEventoCache2;
//    @Inject
//    private PrjProjectoPadraoBean prjProjectoPadraoBean;
    @Inject
    AppInit appInit;

    @Inject
    private TransactionalProcessBean transactionalProcessBean;

    @Inject
    private SegPessoaBean segPessoaBean;

    @Inject
    private SegPerfilBean segPerfilBean;

//    @Inject
//    private EveTipoEventoCache eveTipoEventoCache;
//
//    @Inject
//    private PrjProcessoCandidaturaCache2 prjProcessoCandidaturaCache2;
//
//    @Inject
//    private PrjCandidaturasGerirCache2 prjCandidaturasGerirCache2;
//
//    private ConcurrentHashMapPersonalized<String, EveEventoGenerico> hashTableEveEventos;
////	private SegLogAcesso logAcesso;
    private SegConta sessaoActual;
    private GrlPessoa pessoaLogada;

    private boolean validateError;
    private int tentativas = 0, reTentativas = 0;
    private SegControloDeAcesso controloDeAcesso;
    private String servicoInter;

    private FacesContext context; //= FacesContext.getCurrentInstance();
    private HttpServletRequest request; //= (HttpServletRequest) context.getExternalContext().getRequest();
    private HttpSession sessao; //= request.getSession();

    private String username;
    private String password;

    private final static Logger log = Logger.getLogger(SegLoginBean.class);

    private String novaPergunta;
    private boolean rended, visible, estadoConta = true;
    private String senhaActual, novaSenha, novaSenhaConfirmada;

    private SegConta contaAlterar;

    /**
     * Creates a new instance of SegLoginBean
     */
    public SegLoginBean() {

    }

    @PostConstruct
    public void init() {
        this.pessoaLogada = null;
        System.err.println("0: SegLoginBean.init()");

        getContext();
        System.err.println("2: SegLoginBean.init()");
        controloDeAcesso = new SegControloDeAcesso();
        rended = false;
        System.err.println("3: SegLoginBean.init()");
        appInit.init();
        System.err.println("4: SegLoginBean.init()");
        initHashtableEveEventos();
        System.err.println("5: SegLoginBean.init()");
//		parametrosEntreFormulariosBean.init();
//		LogFile.init();
//        comEstadoCandidaturaCache.init();
//		activateEGTIGmailBoxBean.init();
    }

    public void initHashtableEveEventos() {
//System.err.println("0: SegLoginBean.initHashtableEveEventos()");        
//        hashTableEveEventos = new ConcurrentHashMapPersonalized();
//System.err.println("1: SegLoginBean.initHashtableEveEventos()");        
//        for (EveTipoEventoEnum eveTipoEventoEnum : EveTipoEventoEnum.values()) {
////System.err.println("2: SegLoginBean.initHashtableEveEventos()\teveTipoEventoEnum: " +
////    eveTipoEventoEnum.getNome());            
//            hashTableEveEventos.put(
//                    new Associacao(eveTipoEventoEnum.getNome(), eveTipoEventoCache.getEveTipoEventoManipulator(eveTipoEventoEnum))
//            );
////System.err.println("3: SegLoginBean.initHashtableEveEventos()");            
//        }
//System.err.println("4: SegLoginBean.initHashtableEveEventos()");        
    }

    private FacesContext getFacesContext() {
        context = FacesContext.getCurrentInstance();
        return context;
    }

    private HttpServletRequest getHttpServletRequest() {
        request = (HttpServletRequest) context.getExternalContext().getRequest();
        return request;
    }

    private void getContext() {
        context = getFacesContext();
        request = getHttpServletRequest();
        sessao = request.getSession();
    }

    public void preRenderView() {
//            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
    }

    public static SegLoginBean obterSegLoginBean() {
        FacesContext c = FacesContext.getCurrentInstance();
        return (SegLoginBean) c.getELContext().getELResolver()
                .getValue(c.getELContext(), null, "segLoginBean");
    }

    public static SegLoginBean getInstanciaBean() {
        FacesContext context2 = FacesContext.getCurrentInstance();
        SegLoginBean segLoginBean
                = (SegLoginBean) context2.getELContext().
                        getELResolver().getValue(FacesContext.getCurrentInstance().
                                getELContext(), null, "segLoginBean");

        return segLoginBean;
    }

    public int getTempoInactividade() {
        Integer tempoInactividade = sessaoActual.getTempoInactividade();
        return tempoInactividade == null ? 0 : tempoInactividade;
    }

    public boolean getActivarTempoInactividade() {
//        sessaoActual = segContaFacade.find(sessaoActual.getPkSegConta());
        Boolean activar = sessaoActual.getActivarTempoInactividade();

        return activar == null ? true : activar;
    }

    public int getMaxIdleTime() {
        sessaoActual = segContaFacade.find(sessaoActual.getPkSegConta());
        Integer idleTime = sessaoActual.getMaxIdleTime();

        return idleTime == null ? 0 : idleTime;
    }

    public void continuarSessao() //throws Exception
    {
        //System.err.println("0: SegLoginBean.continuarSessao()\tusername: " + username
//            + "\tpassword: " + password);
        FacesContext faceContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = faceContext.getExternalContext();
        if (password.equals("") || username.equals("")) {
            validateError = true;
            //System.err.println("1: SegLoginBean.continuarSessao()");
            LogFile.warnMsg(null, username);
            return;
        } else {
            //System.err.println("2: SegLoginBean.continuarSessao()");
            relogin(username.trim(), EncriptacaoDecriptacao.encrypt(password));
        }
        //System.err.println("3: SegLoginBean.continuarSessao()");
    }

    /**
     * <b>descrição</b>
     *
     * @param username
     * @param password
     * @return
     */
    public void relogin(String username, String password) {
        //System.err.println("0: SegLoginBean.relogin()\tusername: " + username + "\tpassword: " + password);
        SegConta sessaoActualTmp = segContaFacade.getUtilizadorBypalavraPasseAndnomeUtilizador(username, password);
        FacesContext facesContext = FacesContext.getCurrentInstance();
        //System.err.println("00: SegLoginBean.relogin()\tsessaoActualTmp: " + (sessaoActualTmp == null ? "null" : sessaoActualTmp.getNomeUtilizador()));

        ExternalContext externalContext = facesContext.getExternalContext();

        if (sessaoActualTmp == null) {
            //System.err.println("1: SegLoginBean.relogin()");
            validateError = true;
            reTentativas++;

            registarLogsWhenSessionIsNull();

            if (reTentativas > 2) {
                //System.err.println("2: SegLoginBean.relogin()");
                redirectedForPage("seg_login_error.xhtml");
            }
            //System.err.println("3: SegLoginBean.relogin()");
//            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nome ou senha do usuario errado", "Nao foi possivel fazer o login! Por favor Tente novamente");
//            FacesContext.getCurrentInstance().addMessage(null, msg);
            LogFile.erroMsg(null, "Nome ou senha do usuario errado. Nao foi possivel fazer o login! Por favor Tente novamente");
            return;
        }
        int pkSessaoActualTmp = sessaoActualTmp.getPkSegConta();
        int pkSessaoActual = sessaoActual.getPkSegConta();
        if (pkSessaoActualTmp == pkSessaoActual) {
            //System.err.println("4: SegLoginBean.relogin()");
            PrimeFaces.current().executeScript("PF('idle').resume()");
            PrimeFaces.current().executeScript("PF('w_form_login').hide()");
            return;
        } else if (pkSessaoActualTmp != pkSessaoActual) {
            //System.err.println("5: SegLoginBean.relogin()");
            this.finalizarSessao();
            login(username, password);
            //System.err.println("6: SegLoginBean.relogin()");
        }
        //System.err.println("7: SegLoginBean.relogin");
    }

//    NOVO METODO
    public void entraNoSistema() //throws Exception
    {
        System.err.println("0: SegLoginBean.entraNoSistema()");
        criarContaRoot();
        System.err.println("1: SegLoginBean.entraNoSistema()");
        FacesContext faceContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = faceContext.getExternalContext();
        if (password.equals("") || username.equals("")) {
            try {
                validateError = true;
                System.err.println("2: SegLoginBean.entraNoSistema()");
                externalContext.redirect("login.xhtml?validar=true");
            } catch (IOException ex) {
                System.err.println("3: SegLoginBean.entraNoSistema()");
                log.error(" SegLoginBean exception: " + ex.getMessage());
            }
        } else {
            System.err.println("4: SegLoginBean.entraNoSistema()");
            System.out.println("EncriptacaoDecriptacao: "+EncriptacaoDecriptacao.encrypt(password));
            login(username.trim(), EncriptacaoDecriptacao.encrypt(password));
        }
        System.err.println("5: SegLoginBean.entraNoSistema()");
    }

    public void login(String username, String password) {
        System.err.println("0: SegLoginBean.login()");
        sessaoActual = segContaFacade.getUtilizadorBypalavraPasseAndnomeUtilizador(username, password);

        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();

        if (sessaoActual == null) {
            //System.err.println("1: SegLoginBean.login()");
            validateError = true;
            tentativas++;

            registarLogsWhenSessionIsNull();

            if (tentativas > 2) {
                //System.err.println("2: SegLoginBean.login()");
                redirectedForPage("seg_login_error.xhtml");
            }

//            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nome ou senha do usuario errado", "Nao foi possivel fazer o login! Por favor Tente novamente");
//            FacesContext.getCurrentInstance().addMessage(null, msg);
            LogFile.erroMsg(null, "Nome ou senha do usuario errado. Nao foi possivel fazer o login! Por favor Tente novamente");
        } else if (sessaoActual != null) {
            System.out.println("sessaoActual.getFkSegPessoa(): "+sessaoActual.getFkSegPessoa().getPkGrlPessoa());
             pessoaLogada = grlPessoaFacade.find(sessaoActual.getFkSegPessoa().getPkGrlPessoa());
             
            //prjProjectoPadraoBean.init();
            //System.err.println("2.1: SegLoginBean.login()\tpessoaLogada: " + pessoaLogada.getNome());
            updatePerfilQuePodemSerGeridosPeloUsuarioCorrente();
            System.err.println("3.0: SegLoginBean.login()");
//            prjProcessoCandidaturaCache2.touch();
            System.err.println("3.1: SegLoginBean.login()");

            if (sessaoActual.getActivo() == true) {
                System.err.println("4: SegLoginBean.login()");
                estadoConta = true;
                if (sessaoActual.getPrimeiroLoginConta() == true) {
                    System.err.println("5: SegLoginBean.login()");
                    try {
                        validateError = false;
                        System.err.println("6: SegLoginBean.login()");
                        externalContext.redirect("seg_first_login.xhtml");
                    } catch (IOException ex) {
                        System.err.println("7: SegLoginBean.login()");
                        log.error(" SegLoginBean exception: " + ex.getMessage());
                    }
                } else {
                    System.err.println("8: SegLoginBean.login()");
                    tentativas = 0;
                    registarLogsAfterFirstLogin();
                    System.err.println("9: SegLoginBean.login()");
                    ExternalContext externalContext2 = FacesContext.getCurrentInstance().getExternalContext();
                    Map<String, Object> sessionMap = externalContext2.getSessionMap();
                    sessionMap.put("sessaoActual", sessaoActual);
                    System.err.println("9.1: SegLoginBean.login()");
                    int tempoInactividade = getTempoInactividade();
                    System.err.println("9.2: SegLoginBean.login()");
                    boolean activarTempoInactividade = getActivarTempoInactividade();
                    System.err.println("9.3: SegLoginBean.login()\tactivarTempoInactividade: " + activarTempoInactividade);
                    if (tempoInactividade != 0) {
                        tempoInactividade = activarTempoInactividade ? tempoInactividade : 110 * 60;
                        System.err.println("10: SegLoginBean.login()\ttempoInactividade: " + tempoInactividade);
                        sessao.setMaxInactiveInterval(tempoInactividade);

                        System.err.println("11.1: SegLoginBean.login()\ttempoInactividade: " + sessao.getMaxInactiveInterval());
                    }
                    System.err.println("11.2: SegLoginBean.login()");
                    try {
                        validateError = false;
                        System.err.println("11.3: SegLoginBean.login()");
                        if (sessaoActual.getFkSegPaginaArranque() == null) {
                            System.err.println("12: SegLoginBean.login()");
                            if (sessaoActual.getNomeUtilizador().equals("root")) {
                                System.err.println("13: SegLoginBean.login()");
                                LogFile.sucessoMsg(null, "root fez o login com sucesso");
                                System.err.println("14: SegLoginBean.login()");
                                externalContext.redirect("home.xhtml");
                            } else if (sessaoActual.getFkSegPerfil().getFkSegPaginaArranque() == null) {
                                System.err.println("15: SegLoginBean.login()");
                                externalContext.redirect("home.xhtml");
                                LogFile.sucessoMsg(null, "Usuário Logado Com Sucesso e Sejá Bem Vindo ao Sistema");
                            } else {
                                System.err.println("16: SegLoginBean.login()");
                                redirectedForPage("/PSGL_EGTI_NEW" + sessaoActual.getFkSegPerfil().getFkSegPaginaArranque().getUrl());
                            }
                        } else {
                            System.err.println("17: SegLoginBean.login()");
                            redirectedForPage("/SIG_FRT_Master" + sessaoActual.getFkSegPaginaArranque().getUrl());
                        }
                    } catch (IOException ex) {
                        System.err.println("18: SegLoginBean.login()");
                    }
                }
                System.err.println("19: SegLoginBean.login()");
            } else {
                System.err.println("20: SegLoginBean.login()");
                estadoConta = sessaoActual.getActivo();
                validateError = false;
            }
        } else {
            System.err.println("21: SegLoginBean.login()");
            try {
                validateError = false;
                System.err.println("22: SegLoginBean.login()");
                externalContext.redirect("login.xhtml?error=true");
            } catch (IOException ex) {
                System.err.println("23: SegLoginBean.login()");
                log.error(" SegLoginBean exception: " + ex.getMessage());
            }

        }
        System.err.println("24: SegLoginBean.login()");
    }

    public boolean disableIfRoot() {
        return this.username.equalsIgnoreCase("root");
    }

    public boolean isRootAccount() {
        return this.username.equalsIgnoreCase("root");
    }

    public void registarLogsWhenSessionIsNull() {
        SegLogAcesso logAcesso = new SegLogAcesso();
        logAcesso.setEventoLogAcesso("Acesso ao servidor");
        logAcesso.setRiscoLogAcesso("Baixo");
        logAcesso.setOperador(username);
        logAcesso.setTipoUsuario("Desconhecido");
        logAcesso.setDataRegistoLogAcesso(new Date());
        logAcesso.setIpLogAcesso(getIpAdressClient());
        logAcesso.setResultadoLogAcesso("Insucesso");
        logAcesso.setDetalhesLogAcesso("Causa do Probelma: Nome do usuario e senha inválida, nome do utilizador: " + username);
        logAcesso.setFkSegConta(null);
        try {
            logAcessoFacade.create(logAcesso);
        } catch (Exception e) {
        }

    }

    private String debug() {
        String msg = "";
        msg += "\nsessaoActual: " + (sessaoActual == null ? "null" : "not null");
        if (sessaoActual != null) {
            msg += "\nsessaoActual.getFkSegPessoa: " + (sessaoActual.getFkSegPessoa() == null ? "null" : sessaoActual.getNomeUtilizador());
        }
        return msg;
    }

    public void registarLogsAfterFirstLogin() {
        System.err.println("0: SegLoginBean.registarLogsAfterFirstLogin()");
        SegLogAcesso logAcesso = new SegLogAcesso();
        logAcesso.setEventoLogAcesso("Acesso ao servidor");
        logAcesso.setRiscoLogAcesso("Aviso");
        logAcesso.setOperador(username);
        logAcesso.setDataRegistoLogAcesso(new Date());
        System.err.println("1: SegLoginBean.registarLogsAfterFirstLogin()");
        logAcesso.setIpLogAcesso(getIpAdressClient());
        System.err.println("1.1: SegLoginBean.registarLogsAfterFirstLogin()");
        logAcesso.setResultadoLogAcesso("Sucesso");
        //logAcesso.setDetalhesLogAcesso("Acesso ao servidor efectuado com sucesso, Nome do " + segPessoaBean.find(sessaoActual.getFkSegPessoa()).getNome());
        logAcesso.setFkSegConta(sessaoActual);
        System.err.println("2: SegLoginBean.registarLogsAfterFirstLogin()");
        try {
            System.err.println("3: SegLoginBean.registarLogsAfterFirstLogin()");
            logAcessoFacade.create(logAcesso);
            System.err.println("4: SegLoginBean.registarLogsAfterFirstLogin()");
        } catch (Exception e) {
            System.err.println("5: SegLoginBean.registarLogsAfterFirstLogin()");
        }
        System.err.println("6: SegLoginBean.registarLogsAfterFirstLogin()");
    }

    public void registarLogsFirstLogin(SegConta conta) {
        SegAuditoria logauditoria = new SegAuditoria();
        logauditoria.setNome("Mudar senha Conta Utilizador");
        logauditoria.setNivelRisco("Baixo");
        logauditoria.setOperadorRegisto(conta.getNomeUtilizador());
        logauditoria.setDataRegisto(new Date());
        logauditoria.setCategoria("Segurança");
        logauditoria.setIpAuditoria(getIpAdressClient());
        logauditoria.setResultado("Sucesso");
        logauditoria.setDetalhes("Conta do utilizador " + conta.getNomeUtilizador() + " editado com sucesso, pelo utilizador: " + segPessoaBean.find(sessaoActual.getFkSegPessoa().getPkGrlPessoa()).getNome());
        logauditoria.setFkSegConta(new SegConta(conta.getPkSegConta()));
        try {
            segAuditoriaFacade.create(logauditoria);
        } catch (Exception e) {
        }
    }

    /**
     *
     * @throws java.lang.Exception
     */
    public void primeiroLogin() throws Exception {

        contaAlterar = sessaoActual;

        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();

        if (!novaSenha.equals(novaSenhaConfirmada) || novaSenha.equals("")) {
            validateError = true;
//            LogFile.warnMsg(null, "As senhas têm de ser iguais!");
        } else {
            validateError = false;
            contaAlterar.setPalavraPasse(EncriptacaoDecriptacao.encrypt(novaSenha));
            contaAlterar.setPrimeiroLoginConta(false);
            registarLogsFirstLogin(contaAlterar);
            segContaFacade.edit(contaAlterar);
            LogFile.sucessoMsg(null, "Conta do Utilizador editada com sucesso!");
            externalContext.redirect("login.xhtml");
        }
    }

    public void finalizarSessao() {
//        sessaoActual = segContaFacade.find(sessaoActual.getPkSegConta());
        registarLogTerminoDaSessao();
        sessaoActual.setUltimoAcessoConta(new Date());
        segContaFacade.edit(sessaoActual);
    }

    public String terminarSessao() {
//        sessaoActual = segContaFacade.find(sessaoActual.getPkSegConta());
        System.err.println("0: SegLoginBean.terminarSessao()");
        registarLogTerminoDaSessao();
        System.err.println("1: SegLoginBean.terminarSessao()");
        // prjProcessoCandidaturaCache2.removeSegConta(sessaoActual);
        System.err.println("2: SegLoginBean.terminarSessao()");

        // prjCandidaturasGerirCache2.removeSegConta(sessaoActual);
        System.err.println("3: SegLoginBean.terminarSessao()");
        sessaoActual.setUltimoAcessoConta(new Date());
        System.err.println("4: SegLoginBean.terminarSessao()");

        segContaFacade.edit(sessaoActual);
        System.err.println("5: SegLoginBean.terminarSessao()");
        sessaoActual = null;
        username = "";
        password = "";
        System.err.println("6: SegLoginBean.terminarSessao()");
        return "/login.xhtml?faces-redirect=true";
    }

    public void registarLogTerminoDaSessao() {
        SegLogAcesso logAcesso = new SegLogAcesso();
        logAcesso.setEventoLogAcesso("Termino de sessão");
        logAcesso.setRiscoLogAcesso("Aviso");
        logAcesso.setOperador(username);
        logAcesso.setDataRegistoLogAcesso(new Date());
        logAcesso.setIpLogAcesso(getIpAdressClient());
        logAcesso.setResultadoLogAcesso("Sucesso");
        logAcesso.setFkSegConta(sessaoActual);
        logAcesso.setDetalhesLogAcesso("Termino de sessão ao servidor efectuado com sucesso, Nome do " + segPessoaBean.find(sessaoActual.getFkSegPessoa().getPkGrlPessoa()).getNome());
        try {
            logAcessoFacade.create(logAcesso);
        } catch (Exception e) {
        }
    }

    public String getIpMaquinaCliente() {
        context = FacesContext.getCurrentInstance();
        request = (HttpServletRequest) context.getExternalContext().getRequest();

        return request.getRemoteAddr();
    }

    public String getIpMaquinaServidor() {
        context = FacesContext.getCurrentInstance();
        request = (HttpServletRequest) context.getExternalContext().getRequest();
        return request.getLocalAddr();
    }

    public String getIdSessao() {
        context = FacesContext.getCurrentInstance();
        sessao = (HttpSession) context.getExternalContext().getSession(false);
        return sessao.getId();
    }

    public String mensagemSessaoExpirada() {
        return "Para maior segurança da aplicação, a sessão expirou. ";
    }

    public int getIntervaloMaximoInactividade() {
        context = FacesContext.getCurrentInstance();
        sessao = (HttpSession) context.getExternalContext().getSession(false);
        return sessao.getMaxInactiveInterval();
    }

    public void setIntervaloMaximoInactividade(HttpSession sessao, int tempoEmSegundos) {
        sessao.setMaxInactiveInterval(tempoEmSegundos);
    }

    public void redirectedForPage(String page) throws IllegalStateException {
        System.err.println("0: SegLoginBean.redirectedForPage()\tpage: " + page);
        try {
            System.err.println("1: SegLoginBean.redirectedForPage()\tpage: " + page);
            FacesContext.getCurrentInstance().getExternalContext().redirect(page);
        } catch (IOException e) {
            System.err.println("2: SegLoginBean.redirectedForPage()\tpage: " + page);
            java.util.logging.Logger.getLogger(SegLoginBean.class
                    .getName()).log(Level.SEVERE, null, e);
        }
        System.err.println("3: SegLoginBean.redirectedForPage()\tpage: " + page);
    }

    private String getIpAdressClient() throws IllegalStateException {
        HttpServletRequest request2 = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String ipAddress = request2.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = request2.getRemoteAddr();
        }

        return ipAddress;
    }

    public String ipAdressClient() {
        HttpServletRequest request2 = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String ipAddress = request2.getRemoteAddr();

        return ipAddress;
    }

    /*verifica as autorizacoes para determinada funcionalidade (p:menu/p:menuItem )
     url: sequencia de menus ate ao menuItem separadas com o carater ponto e virgula (;) */
    public boolean temPermissaoParaAcessarURL(String url) {
//System.err.println("0: SegLoginBean.temPermissaoParaAcessarURL()\turl: " + url);        
        if (sessaoActual != null) {
//System.err.println("1: SegLoginBean.temPermissaoParaAcessarURL()\turl: " + url);             
            List<SegFuncionalidade> segFuncionalidades = perfilFuncionalidadeFacade.findSegFuncionalidadesFromPerfilWithAcessToUrl(sessaoActual.getFkSegPerfil().getPkSegPerfil(), url);
//System.err.println("2: SegLoginBean.temPermissaoParaAcessarURL()");             
            if (!segFuncionalidades.isEmpty()) {
//System.err.println("3: SegLoginBean.temPermissaoParaAcessarURL()");                 
                return true;
            }
        } else {
////////System.err.println("4: SegLoginBean.temPermissaoParaAcessarURL()");             
            return false;
        }
////////System.err.println("5: SegLoginBean.temPermissaoParaAcessarURL()");         
        return false;
    }

    /**
     * verifica se o usuario logodo tem perfissão para aceder a funcionalidade
     * passada como parametro
     *
     * @param pkIdFuncionalidade
     * @return
     */
    public boolean renderedMenuById(Integer pkIdFuncionalidade) {
//if (pkIdFuncionalidade == 9)        
//System.err.println("0: SegLoginBean.renderedMenuById()\tpkIdFuncionalidade: " + pkIdFuncionalidade);
//        sessaoActual = segContaFacade.find(sessaoActual.getPkSegConta());
        if (segContaFacade.isRootAccount(sessaoActual)) {
//if (pkIdFuncionalidade == 9)            
//System.err.println("1: SegLoginBean.renderedMenuById()\tpkIdFuncionalidade: " + pkIdFuncionalidade);            
            return true;
        } else if (sessaoActual != null) {
//if (pkIdFuncionalidade == 9)            
//System.err.println("2: SegLoginBean.renderedMenuById()\tpkIdFuncionalidade: " + pkIdFuncionalidade + "\tsessaoActual.perfil: " + sessaoActual.getFkSegPerfil().getDescricao());            
            SegFuncionalidade modulos = perfilFuncionalidadeFacade.findByPerfilAndFuncionalidade(sessaoActual.getFkSegPerfil().getPkSegPerfil(), pkIdFuncionalidade);
//if (pkIdFuncionalidade == 9)            
//System.err.println("3: SegLoginBean.renderedMenuById()\tmodulos: " + (modulos == null ? "null" : "not null"));            
            return (modulos != null);
        } else {
//if (pkIdFuncionalidade == 9)            
//System.err.println("4: SegLoginBean.renderedMenuById()");            
            redirectedForPage("/SIG_FRT_Master/seg_acesso_login_expirado.xhtml");
            return false;
        }
    }

    public boolean renderedMenuPorIdEOutraCondicao(Integer pkIdFuncionalidade, boolean condicao) {
        //System.err.println("0: SegLoginBean.renderedMenuPorIdEOutraCondicao()\npkIdFuncionalidade: " + pkIdFuncionalidade
//            + "\tcondicao: " + condicao);
        sessaoActual = segContaFacade.find(sessaoActual.getPkSegConta());
        //System.err.println("1: SegLoginBean.renderedMenuPorIdEOutraCondicao()\npsessaoActual: " + pkIdFuncionalidade
//            + "\tcondicao: " + condicao);
        HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        //System.err.println("2: SegLoginBean.renderedMenuPorIdEOutraCondicao()\npkIdFuncionalidade: " + pkIdFuncionalidade
//            + "\tcondicao: " + condicao);
        String url = pegarUrl(req.getRequestURL().toString());
        //System.err.println("3: SegLoginBean.renderedMenuPorIdEOutraCondicao()\nurl: " + url
//            + "\tDefs.PROJECT_NAME: " + Defs.PROJECT_NAME);
        if (!Defs.PROJECT_NAME.equals("")) {
            if (url.startsWith(Defs.PROJECT_NAME)) {
                System.err.println("4: SegLoginBean.renderedMenuPorIdEOutraCondicao()\nurl: " + url
                        + "\tDefs.PROJECT_NAME: " + Defs.PROJECT_NAME
                        + "\tDefs.PROJECT_NAME..length: " + Defs.PROJECT_NAME.length());
                url = StringUtils.substring(url, Defs.PROJECT_NAME.length());
                System.err.println("4.2: SegLoginBean.renderedMenuPorIdEOutraCondicao()\nurl: " + url);
                url = url.substring(Defs.PROJECT_NAME.length());
            }
        }
        //System.err.println("4.3: SegLoginBean.renderedMenuPorIdEOutraCondicao()\nHOME_PAGE_URL: " + seg.utils.Defs.HOME_PAGE_URL);
        if (StringUtils.endsWith(url, seg.utils.Defs.HOME_PAGE_URL)) {
            if (url.endsWith(seg.utils.Defs.HOME_PAGE_URL)) {
                //System.err.println("6: SegLoginBean.renderedMenuPorIdEOutraCondicao()\turl: " + url);
                return false;
            }
        }
        //System.err.println("7: SegLoginBean.renderedMenuPorIdEOutraCondicao()\turl: " + url);
        return condicao && renderedMenuById(pkIdFuncionalidade);
    }

    public String pegarUrl(String urlCompleta) {
        int i = 0;
        if (urlCompleta.indexOf('S') == -1) {
            return urlCompleta;
        }
        while (urlCompleta.charAt(i) != 'S') {
            i = i + 1;
        }
        return urlCompleta.substring(i - 1, urlCompleta.length());
    }

    /**
     * @return
     */
    public Boolean controleAcessoViaURL() {
        //System.err.println("0: SegLoginBean.controleAcessoViaURL()");
        HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String urlCompleta = req.getRequestURL().toString();
        //System.err.println("1: SegLoginBean.controleAcessoViaURL()\turlCompleta: " + urlCompleta);
        if (sessaoActual != null) {
//            System.err.println("2: SegLoginBean.controleAcessoViaURL()");
            if (segContaFacade.isRootAccount(sessaoActual) || urlCompleta.endsWith("/home.xhtml")) {
                //System.err.println("3: SegLoginBean.controleAcessoViaURL()");
                return true;
            }
            //System.err.println("4: SegLoginBean.controleAcessoViaURL()");
            String urlDaPagina = pegarUrl(urlCompleta);
            //System.err.println("5: SegLoginBean.controleAcessoViaURL()\turlDaPagina: " + urlDaPagina);
            if (!Defs.PROJECT_NAME.equals("")) {
                if (urlDaPagina.startsWith(Defs.PROJECT_NAME)) {
                    //System.err.println("6: SegLoginBean.controleAcessoViaURL()");
                    urlDaPagina = urlDaPagina.substring(Defs.PROJECT_NAME.length());
                }
            }
            //System.err.println("7: SegLoginBean.controleAcessoViaURL()");
            return temPermissaoParaAcessarURL(urlDaPagina);
        } else {
            //System.err.println("8: SegLoginBean.controleAcessoViaURL()");
            redirectedForPage("/PSGL_EGTI_NEW/seg_acesso_login_expirado.xhtml");
            return false;
        }
    }

    /**
     * verifica as autorizacoes para renderizar formulario e dialog
     *
     * @param urlPattern
     * @return
     */
    public boolean renderedFormulario(String urlPattern) {
        boolean temPermissao = perfilFuncionalidadeFacade.temAcessoPermissaoUtilizador(sessaoActual.getFkSegPerfil().getPkSegPerfil(), urlPattern);
        return temPermissao;
    }

    /**
     * add the funtionalite to the perfin
     *
     * @param perfilRoot
     */
    public void adicionarFuncionalidadesAoPerfil(SegPerfil perfilRoot) {
        for (SegFuncionalidade funcionalidade : funcionalidadeFacade.findAll()) {
            SegPerfilHasFuncionalidade perfilHasFuncionalidade = new SegPerfilHasFuncionalidade();
            perfilHasFuncionalidade.setFkSegPerfil(perfilRoot);
            perfilHasFuncionalidade.setFkSegFuncionalidade(funcionalidade);
            perfilFuncionalidadeFacade.create(perfilHasFuncionalidade);
        }
    }

    /**
     * Criação da conta default root
     *
     */
    public void criarContaRoot() {
        if (segContaFacade.findAll().isEmpty()) {
            int PK_ID_FUNCIONARIO = 1;
            int FK_ID_PERFIL = 1;
            String nomeUtilizador = "root";
            String palavraPasse = "root";

            String msgCommitSucess = "Criação da conta root com sucesso",
                    msgCommitFail = "Falha no commit da criação da conta root",
                    msgRollbackFail = "Falha no roolback da criação da conta root";
            transactionalProcessBean.runTransactionalMethod(msgCommitSucess, msgCommitFail, msgRollbackFail,
                    ()
                    -> {
                SegConta contaRoot = this.segContaFacade.getInstancia();

                contaRoot.setActivo(true);
               // contaRoot.setFkSegPessoa(segPessoaBean.getPessoaParaContaRoot().getPkSegPessoa());
                contaRoot.setNomeUtilizador(nomeUtilizador);

                contaRoot.setPalavraPasse(EncriptacaoDecriptacao.encrypt(palavraPasse));

                contaRoot.setPrimeiroLoginConta(false);
                contaRoot.setFkSegPerfil(null);
                contaRoot.setUltimoAcessoConta(new Date());
                //System.err.println("2: SegLoginBean.criarContaRoot()\tusername: " + username + "\tpassword: " + password);
                segContaFacade.create(contaRoot);
                return true;
                //System.err.println("3: SegLoginBean.criarContaRoot()\tusername: " + username + "\tpassword: " + password);
            }
            );
            //System.err.println("4: SegLoginBean.criarContaRoot()\tusername: " + username + "\tpassword: " + password);
        }
        //System.err.println("5: SegLoginBean.criarContaRoot()\tusername: " + username + "\tpassword: " + password);
    }

    public void updatePerfilQuePodemSerGeridosPeloUsuarioCorrente() {
//        System.err.println("0: SegLoginBean.updatePerfilQuePodemSerGeridosPeloUsuarioCorrente()");
        if (!segContaFacade.isRootAccount(sessaoActual)) {
//System.err.println("1: SegLoginBean.updatePerfilQuePodemSerGeridosPeloUsuarioCorrente()");            
            if (sessaoActual.getFkSegPerfil().getDescricao().startsWith("Administrador ")) {
                segPerfilBean.findPerfisByDescricao(sessaoActual.getFkSegPerfil().getDescricao());
            }
        }
//System.err.println("2: SegLoginBean.updatePerfilQuePodemSerGeridosPeloUsuarioCorrente()");
    }

    public boolean isRootOuMustAdmin() {
//        System.err.println("0: SegLoginBean.isRootOuMustAdmin()");
        if (segContaFacade.isRootAccount(sessaoActual)) {
//System.err.println("1: SegLoginBean.isRootOuMustAdmin()");            
            return true;
        }
//System.err.println("2: SegLoginBean.isRootOuMustAdmin()");        
        return sessaoActual.getFkSegPerfil().getPkSegPerfil() == 1;
    }

    public void alterarSenhaUserCorrente() {
        if (!novaSenha.isEmpty() && !novaSenhaConfirmada.isEmpty() && !senhaActual.isEmpty()) {
//System.err.println("0: SegLoginBean.alterarSenhaUserCorrente()\tnovaSenha: " + novaSenha + 
//    "\tsessaoActual.getPalavraPasse: " + sessaoActual.getPalavraPasse() + 
//    "\tnovaSenhaConfirmada: " + novaSenhaConfirmada);
            if (!EncriptacaoDecriptacao.encrypt(senhaActual).equals(sessaoActual.getPalavraPasse())) {
//System.err.println("1: SegLoginBean.alterarSenhaUserCorrente()\tnovaSenha: " + novaSenha + 
//    "\tsessaoActual.getPalavraPasse: " + sessaoActual.getPalavraPasse() + 
//    "\tnovaSenhaConfirmada: " + novaSenhaConfirmada);                
                LogFile.erroMsg(null, "A senha introduzida nao corresponde a senha do utilizador Corrente");
            } else if (!novaSenha.equals(novaSenhaConfirmada)) {
                LogFile.erroMsg(null, "A senha introduzida nao corresponde a senha de confirmacao");
            } else {
                sessaoActual.setPalavraPasse(EncriptacaoDecriptacao.encrypt(novaSenha));
                segContaFacade.edit(sessaoActual);
                novaSenha = null;
                novaSenhaConfirmada = null;
                senhaActual = null;
                LogFile.sucessoMsg(null, "A senha de utilizador foi alterada com sucesso");
                PrimeFaces.current().executeScript("PF('dialogRestaurarPass').hide()");

            }

        } else {
            LogFile.erroMsg(null, "A senha introduzida uma senha valida");
        }

    }

    public String redirectToFormTempoMaximoInactividade() {
        return currentUserIsAdministrator()
                ? "/seg_visao/seg_configuracoes/seg_tempo_maximo_inactividade_admin.xhtml?faces-redirect=true"
                : "/seg_visao/seg_configuracoes/seg_tempo_maximo_inactividade_client.xhtml?faces-redirect=true";
    }

// Getters and Setters
    public boolean currentUserIsAdministrator() {
//        System.err.println("0: SegLoginBean.currentUserIsAdministrator()");
        return (segContaFacade.isRootAccount(sessaoActual) || sessaoActual.getFkTipoConta().getNome().equals("Administração"));
    }

    public SegConta getContaUtilizador() {
        return sessaoActual;
    }

    public void setContaUtilizador(SegConta sessaoActual) {
        this.sessaoActual = sessaoActual;
    }

    public boolean isValidateError() {
        return validateError;
    }

    public void setValidateError(boolean validateError) {
        this.validateError = validateError;
    }

    public int getTentativas() {
        return tentativas;
    }

    public void setTentativas(int tentativas) {
        this.tentativas = tentativas;
    }

    public SegControloDeAcesso getControloDeAcesso() {
        return controloDeAcesso;
    }

    public void setControloDeAcesso(SegControloDeAcesso controloDeAcesso) {
        this.controloDeAcesso = controloDeAcesso;
    }

    public String getUsername() {
        //System.err.println("0: SegLoginBean.getUsername()");
        return username;
    }

    public void setUsername(String username) {
        //System.err.println("0: SegLoginBean.setUsername()\tusername: " + username);
        this.username = username;
    }

    public String getPassword() {
        //System.err.println("0: SegLoginBean.getUsername()");
        return password;
    }

    public void setPassword(String password) {
        //System.err.println("0: SegLoginBean.setPassword()\tpassword: " + password);
        this.password = password;
    }

    public SegConta getSessaoActual() {
        return sessaoActual;
    }

    public void setSessaoActual(SegConta sessaoActual) {
        this.sessaoActual = sessaoActual;
    }

    public String getNovaPergunta() {
        return novaPergunta;
    }

    public void setNovaPergunta(String novaPergunta) {
        this.novaPergunta = novaPergunta;
    }

    public boolean isRended() {
        return rended;
    }

    public void setRended(boolean rended) {
        this.rended = rended;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public String getNovaSenha() {
        return novaSenha;
    }

    public void setNovaSenha(String novaSenha) {
        this.novaSenha = novaSenha;
    }

    public String getNovaSenhaConfirmada() {
        return novaSenhaConfirmada;
    }

    public void setNovaSenhaConfirmada(String novaSenhaConfirmada) {
        this.novaSenhaConfirmada = novaSenhaConfirmada;
    }

    public String getSenhaActual() {
        return senhaActual;
    }

    public void setSenhaActual(String senhaActual) {
        this.senhaActual = senhaActual;
    }

    public String getServicoInter() {
        return servicoInter;
    }

    public void setServicoInter(String servicoInter) {
        this.servicoInter = servicoInter;
    }

    public boolean isEstadoConta() {
        return estadoConta;
    }

    public void setActivo(boolean estadoConta) {
        this.estadoConta = estadoConta;
    }

    public HttpSession getSessao() {
        return sessao;
    }

    public void setSessao(HttpSession sessao) {
        this.sessao = sessao;
    }

    /*                  COMENTADO
    public ConcurrentHashMapPersonalized<String, EveEventoGenerico> getHashTableEveEventos()
    {
        return hashTableEveEventos;
    }
     */
    public GrlPessoa getPessoaLogada() {
//System.err.println("0: SegLoginBean.getPessoaLogada()");        
        return pessoaLogada;
    }

}
