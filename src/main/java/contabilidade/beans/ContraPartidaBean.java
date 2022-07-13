/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contabilidade.beans;

import ejbs.entities.CtAnoEconomico;
import ejbs.entities.CtBalancet;
import ejbs.entities.CtClass;
import ejbs.entities.CtContraPartidai;
import ejbs.entities.CtMontanteClasse;
import ejbs.entities.CtMontanteRubrica;
import ejbs.facades.CtAnoEconomicoFacade;
import ejbs.facades.CtBalancetFacade;
import ejbs.facades.CtClassFacade;
import ejbs.facades.CtContraPartidaiFacade;
import ejbs.facades.CtMontanteClasseFacade;
import ejbs.facades.CtMontanteRubricaFacade;
import ejbs.facades.CtRubricaFacade;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import utils.mensagens.Mensagem;

/**
 *
 * @author majoao
 */
@Named(value = "contraPartidaBean")
@ViewScoped
public class ContraPartidaBean implements Serializable {

    /**
     * Creates a new instance of ContraPartidaBean
     */
//    @Inject
//    private CttConfiguracaoTranferenciaBean fnConfiguracaoBean;
    @EJB
    private CtBalancetFacade ctBalancetFacade;

    @EJB
    private CtMontanteRubricaFacade ctMontanterubricaFacade;
    @EJB
    private CtMontanteClasseFacade ctMontanteclasseFacade;
    @EJB
    private CtAnoEconomicoFacade ctAnoeconomicoFacade;

    @EJB
    private CtRubricaFacade ctRubricaFacade;
    @EJB
    private CtClassFacade ctClassFacade;
    @EJB
    private CtContraPartidaiFacade ctContrapartidaiFacade;

    @Resource
    UserTransaction userTransaction;

    /**
     * Creates a new instance of ContraPartidaBean
     */
    private CtContraPartidai contraPartidaInterna = null;
    private CtMontanteRubrica rubrica = null;

    private CtMontanteRubrica ctMontanteRubricaOrigem = null;
    private CtMontanteRubrica ctMontanteRubricaDestino = null;

    //Listas
    private List<CtContraPartidai> listContraPartidadeInterna = null;
    private List<CtContraPartidai> listContraPartidadeInternaAux = null;
    private List<CtContraPartidai> listAux = null;
    private List<CtAnoEconomico> listAnoEconomico = null;
    private List<CtClass> listCategoria = null;
//    private List<CtMontanteClasse> listMontanteCategoria;
    private List<CtMontanteRubrica> listMontanteRubrica;
    private List<CtBalancet> listBalancet = null;

    private List<CtMontanteClasse> listarMontanteCategoria;

    private List<CtMontanteRubrica> listMontanteRubricaOrigem;
    private List<CtMontanteRubrica> listMontanteRubricaDestino;

    private Date dataMovimento;

    private int codigoYear;
    private int codigoCategoria;
    private int codigoCategoriaTwo = 0;
    private int codigoRubricaOrigem;
    private int codigoRubricaDestino;
    private double valorDisponivelDestino = 0.00;
    private double valorDisponivelOrigem = 0.00;
    private double credito = 0.00;
    private double creditoValorOrigem = 0.00;
    private double valorAnualDestino = 0.00;
    private double valorAnualOrigem = 0.00;

    public ContraPartidaBean() {
        this.contraPartidaInterna = new CtContraPartidai();
        this.listAnoEconomico = new ArrayList<>();
        this.listBalancet = new ArrayList<>();
        this.listContraPartidadeInterna = new ArrayList<>();
        this.listCategoria = new ArrayList<>();
        this.dataMovimento = new Date();
        this.rubrica = new CtMontanteRubrica();
        this.listContraPartidadeInternaAux = new ArrayList<>();
        this.listAux = new ArrayList<>();
//        this.listMontanteCategoria = new ArrayList<>();
        this.listMontanteRubrica = new ArrayList<>();

//        codigoYear = ctAnoeconomicoFacade.getByAno(Defs.anoActual).getPkAnoEconomico();
//        
//        System.out.println("ContraPartidaBean() > codigoYear : " + codigoYear);
    }

    @PostConstruct
    public void init() {
        verificarExercicio();

//         fnConfiguracaoBean.verificarConfiguracoes();
//        this.codigoYear = Integer.parseInt(fnConfiguracaoBean.getCttContraPartidaCodigoAno().getValor());
//        this.codigoCategoria = Integer.parseInt(fnConfiguracaoBean.getCttContraPartidaCodigoCategoria().getValor());
//        this.codigoRubricaOrigem = Integer.parseInt(fnConfiguracaoBean.getCttContraPartidaCodigoRubricaOrigem().getValor());
//        this.codigoRubricaDestino = Integer.parseInt(fnConfiguracaoBean.getCttContraPartidaCodigoRubricaDestino().getValor());
        calcularMontanteDisponivelOrigem();
        calcularMontanteDisponivelDestino();

        System.err.println("codigoYear : " + codigoYear);
        System.err.println("codigoCategoria: " + codigoCategoria);
        System.err.println("codigoRubricaOrigem: " + codigoRubricaOrigem);
        System.err.println("codigoRubricaDestino: " + codigoRubricaDestino);

        System.err.println(" ValorDisponivelOrigem: " + this.valorDisponivelOrigem);

    }

    public CtMontanteRubrica getCtMontanteRubricaOrigem() {
        return ctMontanteRubricaOrigem;
    }

    public void setCtMontanteRubricaOrigem(CtMontanteRubrica ctMontanteRubricaOrigem) {
        this.ctMontanteRubricaOrigem = ctMontanteRubricaOrigem;
    }

    public CtMontanteRubrica getCtMontanteRubricaDestino() {
        return ctMontanteRubricaDestino;
    }

    public void setCtMontanteRubricaDestino(CtMontanteRubrica ctMontanteRubricaDestino) {
        this.ctMontanteRubricaDestino = ctMontanteRubricaDestino;
    }

    public void verificarExercicio() {
        List<CtAnoEconomico> lista = ctAnoeconomicoFacade.findExercicioEconomicoByAno(getAno());

        if (lista.isEmpty()) {

            this.codigoYear = ctAnoeconomicoFacade.getPkAnoEconomico().get(0).getPkAnoEconomico();
            System.err.println("ULTIMO EXERCICIO ATIVO : " + ctAnoeconomicoFacade.getPkAnoEconomico().get(0).getAnoEconomico());
        } else {

            this.codigoYear = lista.get(0).getPkAnoEconomico();
            System.err.println("EXERCICIO CORRENTE ATIVO: " + lista.get(0).getAnoEconomico());
        }
    }

    private int getAno() {
        DateFormat formatar = new SimpleDateFormat("yyyy");
        return Integer.parseInt(formatar.format(new Date()));
    }

    public void setValorAnualOrigem(double valorAnualOrigem) {
        this.valorAnualOrigem = valorAnualOrigem;
    }

    public CtContraPartidai getContraPartidaInterna() {
        return contraPartidaInterna;
    }

    public void setContraPartidaInterna(CtContraPartidai contraPartidaInterna) {
        this.contraPartidaInterna = contraPartidaInterna;
    }

    public List<CtContraPartidai> getListContraPartidadeInterna() {
        this.listContraPartidadeInterna = ctContrapartidaiFacade.listTransferencias();

        return this.listContraPartidadeInterna;
    }

    public void setListContraPartidadeInterna(List<CtContraPartidai> listContraPartidadeInterna) {
        this.listContraPartidadeInterna = listContraPartidadeInterna;
    }

    public void actualizarListAnoEconomico() {
        this.listAnoEconomico = ctAnoeconomicoFacade.listYear();
    }

    public List<CtAnoEconomico> getListAnoEconomico() {
        actualizarListAnoEconomico();
        return this.listAnoEconomico;
    }

    public void setListAnoEconomico(List<CtAnoEconomico> listAnoEconomico) {
        this.listAnoEconomico = listAnoEconomico;
    }

    public List<CtClass> getListCategoria() {
        return ctClassFacade.listCategoria();
    }

    public void setListCategoria(List<CtClass> listCategoria) {
        this.listCategoria = listCategoria;
    }

    public String gravar() {

        try {

            userTransaction.begin();

            if (this.contraPartidaInterna.getValor() > this.valorDisponivelOrigem) {

                Mensagem.enviarMensagem(FacesMessage.SEVERITY_WARN, null, "O valor a ser transferido é superior ao disponível na rubrica " + ctMontanteRubricaOrigem.getFkRubrica().getDescricaoRubrica() + "!");
                return "";

            } else if (this.contraPartidaInterna.getValor() == this.valorDisponivelOrigem) {
                Mensagem.enviarMensagem(FacesMessage.SEVERITY_WARN, null, "O valor a ser transferido é igual ao disponível na rubrica, não sera possível realizar a operação.");
                return "";
            } else if (this.codigoRubricaOrigem == this.codigoRubricaDestino) {
                Mensagem.enviarMensagem(FacesMessage.SEVERITY_WARN, null, "Seleciona rubricas diferentes para o reforço financeiro!");
                return "";
            } else if (this.contraPartidaInterna.getValor() <= 0.00) {
                Mensagem.enviarMensagem(FacesMessage.SEVERITY_WARN, null, "O valor a ser transferido não é válido!");
                return "";
            }

            this.contraPartidaInterna.setDateMovimento(new Date());
            this.contraPartidaInterna.setState(true);
            this.contraPartidaInterna.setRegistroCpi(new Date());
            this.contraPartidaInterna.setOrigemCpi(ctMontanteRubricaOrigem.getFkRubrica().getDescricaoRubrica());
            this.contraPartidaInterna.setDestinoCpi(ctMontanteRubricaDestino.getFkRubrica().getDescricaoRubrica());
            this.contraPartidaInterna.setFkAnoEconomico(new CtAnoEconomico(codigoYear));
            //this.contraPartidaInterna.setFkContas(utilizador.utilizadorLogado());
            ctContrapartidaiFacade.create(contraPartidaInterna);
            this.update(this.contraPartidaInterna.getValor());

            userTransaction.commit();

            Mensagem.enviarMensagem(FacesMessage.SEVERITY_INFO, null, "Reforço feito com sucesso!");
            this.limpar();
            return "";

        } catch (NotSupportedException | SystemException | RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException ex) {

            Mensagem.enviarMensagem(FacesMessage.SEVERITY_ERROR, null, "Erro no reforço finançeiro, entre em contacto com o seu Administrador de Sistemas.");
            Mensagem.enviarMensagem(FacesMessage.SEVERITY_ERROR, null, ex.getMessage());
            Logger.getLogger(ContraPartidaBean.class.getName()).log(Level.SEVERE, null, ex);

            try {
                userTransaction.rollback();
            } catch (IllegalStateException | SecurityException | SystemException ex1) {
                Mensagem.enviarMensagem(FacesMessage.SEVERITY_INFO, null, ex1.getMessage());
                Logger.getLogger(ContraPartidaBean.class.getName()).log(Level.SEVERE, null, ex1);
            }

        }
        return "";
    }

    public List<CtMontanteRubrica> getListMontanteRubricaOrigem() {

        if (listMontanteRubricaOrigem == null) {

            listMontanteRubricaOrigem = ctMontanterubricaFacade.getMontanteRubricaPorCategoriaANDYear(codigoYear, codigoCategoria);

        }

        return listMontanteRubricaOrigem;
    }

    public List<CtMontanteRubrica> getListMontanteRubricaDestino() {

        if (listMontanteRubricaDestino == null) {

            listMontanteRubricaDestino = ctMontanterubricaFacade.getMontanteRubricaPorCategoriaANDYear(codigoYear, codigoCategoria);

        }

        return listMontanteRubricaDestino;
    }

    public void atualizarMontanteRubricaOrigemDestino() {
        this.listMontanteRubricaDestino = null;
        this.listMontanteRubricaOrigem = null;
    }

    public int getCodigoYear() {
        return codigoYear;
    }

    public void setCodigoYear(int codigoYear) {
        this.codigoYear = codigoYear;
    }

    public int getCodigoCategoria() {
        return codigoCategoria;
    }

    public void setCodigoCategoria(int codigoCategoria) {
        this.codigoCategoria = codigoCategoria;
    }

    public int getCodigoRubricaOrigem() {
        return codigoRubricaOrigem;
    }

    public void setCodigoRubricaOrigem(int codigoRubrica) {
        this.codigoRubricaOrigem = codigoRubrica;
    }

    public void actualizarListMontanteRubrica() {

        this.listMontanteRubrica = ctMontanterubricaFacade.getMontanteRubricaPorCategoriaANDYear(this.codigoYear, this.codigoCategoria);

    }

    public List<CtMontanteRubrica> getListMontanteRubrica() {

        actualizarListMontanteRubrica();
        return this.listMontanteRubrica;
    }

    public void setListMontanteRubrica(List<CtMontanteRubrica> listMontanteRubrica) {
        this.listMontanteRubrica = listMontanteRubrica;
    }

    public double getValorDisponivelDestino() {

        return valorDisponivelDestino;
    }

    public void setValorDisponivelDestino(double valorDisponivelDestino) {
        this.valorDisponivelDestino = valorDisponivelDestino;
    }

    public double getValorDisponivelOrigem() {
        return valorDisponivelOrigem;
    }

    public void setValorDisponivelOrigem(double valorDisponivelOrigem) {
        this.valorDisponivelOrigem = valorDisponivelOrigem;
    }

    public List<CtBalancet> getListBalancet() {
        return listBalancet;
    }

    public void setListBalancet(List<CtBalancet> listBalancet) {
        this.listBalancet = listBalancet;
    }

    public double getCredito() {
        return credito;
    }

    public void setCredito(double credito) {
        this.credito = credito;
    }

    public CtMontanteRubrica getRubrica() {
        return rubrica;
    }

    public void setRubrica(CtMontanteRubrica rubrica) {
        this.rubrica = rubrica;
    }

    public void calcularMontanteDisponivelOrigem() {
        this.ctMontanteRubricaOrigem = ctMontanterubricaFacade.getByAnoClasseRubrica(this.codigoYear, this.codigoCategoria, this.codigoRubricaOrigem);
        this.valorAnualOrigem = this.ctMontanteRubricaOrigem.getValorAnualRubrica();
        this.valorDisponivelOrigem = this.ctMontanteRubricaOrigem.getValorDisponivel();

    }

    public void calcularMontanteDisponivelDestino() {
        this.ctMontanteRubricaDestino = ctMontanterubricaFacade.getByAnoClasseRubrica(this.codigoYear, this.codigoCategoria, this.codigoRubricaDestino);
        this.valorAnualDestino = this.ctMontanteRubricaDestino.getValorAnualRubrica();
        this.valorDisponivelDestino = this.ctMontanteRubricaDestino.getValorDisponivel();

    }

    public double getValorAnualDestino() {
        return valorAnualDestino;
    }

    public void setValorAnualDestino(double valorAnualDestino) {
        this.valorAnualDestino = valorAnualDestino;
    }

    public double getCreditoValorOrigem() {
        return creditoValorOrigem;
    }

    public void setCreditoValorOrigem(double creditoValorOrigem) {
        this.creditoValorOrigem = creditoValorOrigem;
    }

    public int getCodigoCategoriaTwo() {
        return codigoCategoriaTwo;
    }

    public void setCodigoCategoriaTwo(int codigoCategoriaTwo) {
        this.codigoCategoriaTwo = codigoCategoriaTwo;
    }

    public int getCodigoRubricaDestino() {
        return codigoRubricaDestino;
    }

    public void setCodigoRubricaDestino(int codigoTwo) {
        this.codigoRubricaDestino = codigoTwo;
    }

    public void limpar() {

        this.contraPartidaInterna = new CtContraPartidai();
        this.valorDisponivelDestino = 0.00;
        this.valorDisponivelOrigem = 0.00;
        this.ctMontanteRubricaOrigem = null;
        this.ctMontanteRubricaDestino = null;
    }

    public void update(double money) {

        double novoValor = 0.00;

        if (ctMontanteRubricaOrigem != null && ctMontanteRubricaDestino != null) {

            // Primeira rubrica
            novoValor = (ctMontanteRubricaOrigem.getValorDisponivel() - money);
            ctMontanteRubricaOrigem.setValorDisponivel(novoValor);
            ctMontanterubricaFacade.edit(ctMontanteRubricaOrigem);

            novoValor = 0.00;

            double valorDestDisp = ctMontanteRubricaDestino.getValorDisponivel();

            // Segunda rubrica
            novoValor = (valorDestDisp + money);
            ctMontanteRubricaDestino.setValorDisponivel(novoValor);
            ctMontanterubricaFacade.edit(ctMontanteRubricaDestino);

        }

    }

    public void clear() {

        this.valorDisponivelDestino = 0.00;
        this.valorDisponivelOrigem = 0.00;
        this.contraPartidaInterna = new CtContraPartidai();
    }

    public List<CtContraPartidai> getListContraPartidadeInternaAux() {
        return listContraPartidadeInternaAux;
    }

    public void setListContraPartidadeInternaAux(List<CtContraPartidai> listContraPartidadeInternaAux) {
        this.listContraPartidadeInternaAux = listContraPartidadeInternaAux;
    }

    public Date getDataMovimento() {
        return dataMovimento;
    }

    public void setDataMovimento(Date dataMovimento) {
        this.dataMovimento = dataMovimento;
    }

    public List<CtContraPartidai> getListAux() {
        return listAux;
    }

    public void setListAux(List<CtContraPartidai> listAux) {
        this.listAux = listAux;
    }

    public double getValorAnualOrigem() {
        return valorAnualOrigem;
    }

    public List<CtMontanteClasse> getListarMontanteCategoria() {

        if (listarMontanteCategoria == null) {

            listarMontanteCategoria = ctMontanteclasseFacade.getCategoriaPorYear(new CtAnoEconomico(codigoYear));
        }

        return listarMontanteCategoria;
    }

    public void atualizarListarMontanteCategoria() {
        this.listarMontanteCategoria = null;
    }

    public void setListarMontanteCategoria(List<CtMontanteClasse> listarMontanteCategoria) {
        this.listarMontanteCategoria = listarMontanteCategoria;
    }

}
