/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contabilidade.beans;

import ejbs.entities.FinDocumento;
import ejbs.entities.CtAccount;
import ejbs.entities.CtAnoEconomico;
import ejbs.entities.CtBalancet;
import ejbs.entities.CtClass;
import ejbs.entities.CtDiario;
import ejbs.entities.CtDocument;
import ejbs.entities.CtLancamento;
import ejbs.entities.CtMontanteClasse;
import ejbs.entities.CtMontanteRubrica;
import ejbs.entities.CtOcorrenciaContabilidade;
import ejbs.entities.CtRubrica;
import ejbs.facades.FinDocumentoFacade;
import ejbs.facades.CtAccountFacade;
import ejbs.facades.CtAnoEconomicoFacade;
import ejbs.facades.CtBalancetFacade;
import ejbs.facades.CtDiarioFacade;
import ejbs.facades.CtDocumentFacade;
import ejbs.facades.CtLancamentoFacade;
import ejbs.facades.CtMontanteClasseFacade;
import ejbs.facades.CtMontanteRubricaFacade;
import ejbs.facades.CtOcorrenciaContabilidadeFacade;
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
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import utils.Defs;
import utils.mensagens.Mensagem;

/**
 *
 * @author majoao
 */
@Named(value = "ctLancamentoDiarioCadastrarBean")
@ViewScoped
public class CtLancamentoDiarioCadastrarBean implements Serializable {

    /**
     * Creates a new instance of CtLancamentoDiarioCadastrarBean
     */
    @EJB
    private FinDocumentoFacade fnDocumentoFacade;
    @EJB
    private CtLancamentoFacade ctLancamentoFacade;
    @EJB
    private CtOcorrenciaContabilidadeFacade ctOcorrenciacontabilidadeFacade;
    @EJB
    private CtAccountFacade ctAccountFacade;
    @EJB
    private CtBalancetFacade ctBalancetFacade;
    @EJB
    private CtAnoEconomicoFacade anoEconomicoFacade;
    @EJB
    private CtDocumentFacade ctDocumentFacade;
    @EJB
    private CtMontanteClasseFacade montanteClasseFacade;
    @EJB
    private CtMontanteRubricaFacade montanteRubricaFacade;
    @EJB
    private CtDiarioFacade ctDiarioFacade;

    @Resource
    private UserTransaction userTransaction;

    private int  codigoYear, codigoDiario;
    private double valorFacturaSelecionada;
    private int codigoTermo;
    private FinDocumento termo;

    private Integer codigoAccountDebitar;
    private Integer codigoAccountCreditar;
    private Integer codigoDocumento;
    private double valor;
    private CtLancamento lancamento;
    private CtOcorrenciaContabilidade historico;

    private FinDocumento documentoProcessFast;

    private String tipoDocuSelecionado;

    private List<FinDocumento> listTermo;
    private List<FinDocumento> listFacturas;
    private List<FinDocumento> listOrdensSaque;
    private List<FinDocumento> FinDocumentoNaoClass;
    private List<CtDiario> listDiario;
    private List<CtDocument> listDocumento;

    //variáveis balancete
    private double debito;
    private double credito;
    private Date data;
    private String opcao;
    
    public CtLancamentoDiarioCadastrarBean() {

        termo = new FinDocumento();
        opcao = "Fact";
        codigoTermo = 0;
        valorFacturaSelecionada = 0;
        valor = 0;
        codigoDocumento = 0;
        codigoAccountCreditar = 0;
        codigoAccountDebitar = 0;
        codigoDiario = 0;

        historico = new CtOcorrenciaContabilidade();

        lancamento = new CtLancamento();

        this.tipoDocuSelecionado = "Factura";
    }

    @PostConstruct
    public void init() {
        this.listTermo = fnDocumentoFacade.getDocummento();
        this.listFacturas = ctLancamentoFacade.getFactura();
        this.listOrdensSaque = ctLancamentoFacade.getOrdemDeSaque();
        this.FinDocumentoNaoClass = ctLancamentoFacade.getFinDocumentoNaoClass(opcao);
        this.listDiario = ctDiarioFacade.listDiarios();
        verificarExercicio();

    }

    public void verificarExercicio() {
        List<CtAnoEconomico> lista = anoEconomicoFacade.findExercicioEconomicoByAno(Defs.ANOACTUAL);

        if (lista.isEmpty()) {

            this.codigoYear = anoEconomicoFacade.getPkAnoEconomico().get(0).getPkAnoEconomico();
            System.err.println("ULTIMO EXERCICIO ATIVO : " + anoEconomicoFacade.getPkAnoEconomico().get(0).getAnoEconomico());
        } else {

            this.codigoYear = lista.get(0).getPkAnoEconomico();
            System.err.println("EXERCICIO CORRENTE ATIVO: " + lista.get(0).getAnoEconomico());
        }

    }

    public FinDocumento getDocumentoProcessFast() {
        return documentoProcessFast;
    }

    public void setDocumentoProcessFast(FinDocumento documentoProcessFast) {
        this.documentoProcessFast = documentoProcessFast;
    }

    public void alterarTipoDoc() {
        if (opcao.equalsIgnoreCase("Fact")) {
            tipoDocuSelecionado = "Fact";
        } else {
            tipoDocuSelecionado = "Ordem";
        }
    }

    public String getTipoDocuSelecionado() {
        return this.tipoDocuSelecionado;
    }

    public Integer getCodigoDocumento() {
        return codigoDocumento;
    }

    public void setCodigoDocumento(Integer codigoDocumento) {
        this.codigoDocumento = codigoDocumento;
    }

    public Integer getCodigoAccountCreditar() {
        return codigoAccountCreditar;
    }

    public Integer getCodigoAccountDebitar() {
        return codigoAccountDebitar;
    }

    public void setCodigoAccountCreditar(Integer codigoAccountCreditar) {
        this.codigoAccountCreditar = codigoAccountCreditar;
    }

    public void setCodigoAccountDebitar(Integer codigoAccountDebitar) {
        this.codigoAccountDebitar = codigoAccountDebitar;
    }

    public int getCodigoYear() {
        return codigoYear;
    }

    public void setCodigoYear(int codigoYear) {
        this.codigoYear = codigoYear;
    }

    public int getCodigoDiario() {
        return codigoDiario;
    }

    public void setCodigoDiario(int codigoDiario) {
        this.codigoDiario = codigoDiario;
    }

    public String getOpcao() {
        return opcao;
    }

    public void setOpcao(String opcao) {
        this.opcao = opcao;
    }

    public List<CtAnoEconomico> getListYear() {
        return anoEconomicoFacade.listYear() == null ? new ArrayList<>() : anoEconomicoFacade.listYear();
    }

    public List<FinDocumento> getListTermo() {
        if (this.listTermo == null) {
            this.listTermo = fnDocumentoFacade.getDocummento();
        }
        return this.listTermo;
    }

    public double getValorFacturaSelecionada() {

        if (codigoTermo != 0) {
            FinDocumento factura = fnDocumentoFacade.find(codigoTermo);
            valorFacturaSelecionada = factura.getValorDocumento().doubleValue();
        }

        return valorFacturaSelecionada;
    }

    public void setValorFacturaSelecionada(double valorFacturaSelecionada) {
        this.valorFacturaSelecionada = valorFacturaSelecionada;
    }

    public void seleccionar(FinDocumento documento) {
        RequestContext.getCurrentInstance().closeDialog(documento);

    }

    public void seleccionarDocumento(SelectEvent evento) {
        documentoProcessFast = (FinDocumento) evento.getObject();

        this.opcao = documentoProcessFast.getFkTipoDoc().getDescricao().contains("Fact") ? "Fact" : "Ordem";
        this.codigoTermo = documentoProcessFast.getPkDocumento();

        alterarTipoDoc();

    }

    public int getCodigoTermo() {
        return codigoTermo;
    }

    public void setCodigoTermo(int codigoTermo) {
        this.codigoTermo = codigoTermo;
    }

    public String gravar() {

        CtMontanteRubrica montanteRubrica = null;
        if (opcao.equalsIgnoreCase("Ordem")) {
            montanteRubrica = verifECriarMontanteRubrica(codigoAccountCreditar, codigoAccountDebitar);
        }

        try {
            userTransaction.begin();

            List<CtLancamento> list = ctLancamentoFacade.getLancamentos();
            this.termo = fnDocumentoFacade.find(this.codigoTermo);

            if (this.termo == null) {
                Mensagem.enviarMensagem(FacesMessage.SEVERITY_ERROR, null, "Seleciona um documento por favor");
                return "";
            }

            if (this.codigoAccountDebitar == this.codigoAccountCreditar) {
                Mensagem.enviarMensagem(FacesMessage.SEVERITY_ERROR, null, "Não é possível Creditar e Debitar na mesma conta! Seleciona uma conta diferente.");
                return "";
            }

            if (this.codigoDocumento == 0) {
                Mensagem.enviarMensagem(FacesMessage.SEVERITY_ERROR, null, "Deve Selecionar O Diario E O Documento!");
                return "";
            }

            this.lancamento = new CtLancamento();
            this.valor = this.termo.getValorDocumento().doubleValue();
            this.lancamento.setValorLancamento(this.valor);

            CtAnoEconomico anoEconomico = anoEconomicoFacade.getYearPorCodigo(codigoYear);
            this.lancamento.setFkAnoEconomico(anoEconomico);

            this.lancamento.setDataLancamento(new Date());
            this.lancamento.setDataRegistroLancamento(new Date());
            this.lancamento.setObsLancamento("");
            this.lancamento.setStateLancamento(true);
            this.lancamento.setStateClssf(true);
            this.lancamento.setFkDocument(new CtDocument(this.codigoDocumento));
            this.lancamento.setFkDocumento(this.termo);
            this.ctLancamentoFacade.create(this.lancamento);

            this.historico.setDataRegistro(new Date());

            this.ctOcorrenciacontabilidadeFacade.create(this.historico);

            // Conta a debitar
            CtBalancet balancet = new CtBalancet();
            CtAccount account = new CtAccount();

            account = this.ctAccountFacade.find(this.codigoAccountDebitar);
            balancet.setDebitoBalancet(this.lancamento.getValorLancamento());
            balancet.setCreditoBalancet(credito);
            balancet.setStateBalancet(true);
            balancet.setMonth(getMes(termo.getDataDoc()));
            balancet.setFkLancamento(lancamento);
            balancet.setDataBalancet(lancamento.getDataLancamento());

            ctBalancetFacade.create(balancet);

            balancet = new CtBalancet();
            account = this.ctAccountFacade.find(this.codigoAccountCreditar);
            balancet.setDebitoBalancet(this.debito);
            balancet.setStateBalancet(true);
            balancet.setMonth(this.getMes(this.termo.getDataDoc()));
            balancet.setCreditoBalancet(this.lancamento.getValorLancamento());
            balancet.setFkLancamento(this.lancamento);
            balancet.setDataBalancet(this.lancamento.getDataLancamento());

            this.ctBalancetFacade.create(balancet);

            if (updateMontantesDisponiveis(balancet, anoEconomico, montanteRubrica)) {

                userTransaction.commit();

                limpar();

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Lançamento salvo com sucesso!"));
            }
        } catch (NotSupportedException | SystemException | RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException e) {

            Logger.getLogger(CtLancamentoDiarioCadastrarBean.class.getName()).log(Level.SEVERE, null, e);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao salvar o lançamento a verifique todos os parametros de inserção!", ""));

            try {
                userTransaction.rollback();
            } catch (IllegalStateException | SecurityException | SystemException ex) {
                Logger.getLogger(CtLancamentoDiarioCadastrarBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return "";
    }

    public void limpar() {

        this.lancamento = new CtLancamento();
        this.termo = new FinDocumento();
        this.codigoYear = 0;
        this.codigoDiario = 0;
        this.codigoAccountCreditar = 0;
        this.codigoAccountDebitar = 0;
        this.codigoTermo = 0;
        this.opcao = "fact";
        this.codigoDocumento = 0;

    }

    public int getMes(Date data) {
        DateFormat dateFormat = new SimpleDateFormat("MM");
        String month = dateFormat.format(data);
        return Integer.parseInt(month);
    }

    public List<FinDocumento> getListFacturas() {
        if (this.listFacturas == null) {
            this.listFacturas = ctLancamentoFacade.getFactura();
        }
        return this.listFacturas;
    }

    public List<FinDocumento> getListOrdensSaque() {
        if (this.listOrdensSaque == null) {
            this.listOrdensSaque = ctLancamentoFacade.getOrdemDeSaque();
        }
        return this.listOrdensSaque;
    }

    public List<FinDocumento> getDocumentoNaoClass() {
        if (this.FinDocumentoNaoClass == null) {
            this.FinDocumentoNaoClass = ctLancamentoFacade.getFinDocumentoNaoClass(opcao);
        }
        return this.FinDocumentoNaoClass;
    }

    public List<CtDiario> getListDiario() {
        if (this.listDiario == null) {
            this.listDiario = ctDiarioFacade.listDiarios();
        }
        return this.listDiario;
    }

    public List<CtDocument> getListDocumento() {
        this.listDocumento = new ArrayList<>();
        if (codigoDiario != 0 && (listDocumento.isEmpty() || listDocumento == null)) {
            this.listDocumento = ctDocumentFacade.getDocumentoPorDiario(this.codigoDiario);
            this.codigoDocumento = listDocumento.get(0).getPkDocument();
        }

        return this.listDocumento != null ? this.listDocumento : new ArrayList<>(); 

    }

    public void atualizarDocumento() {

        this.listDocumento = null;

    }

    public List<CtAccount> getListAccountActivoDebitoCredito() {
        //Conta Rubrica
        System.out.println("getListAccountActivoDebitoCredito(): "+codigoTermo);
        List<CtAccount> contas = new ArrayList<>();

        if (codigoTermo != 0) {

            FinDocumento doc = fnDocumentoFacade.find(this.codigoTermo);
            CtRubrica rubEnt = doc.getFkEntidade().getFkRubrica();
            contas = ctAccountFacade.getByRubrica(rubEnt);

            if (contas != null) {

                if (opcao.equalsIgnoreCase("Fact")) {
                    codigoAccountDebitar = contas.get(0).getPkAccount();
                } else {
                    codigoAccountCreditar = contas.get(0).getPkAccount();
                }
            } else {
                codigoAccountDebitar = 0;
                codigoAccountCreditar = 0;
            }
        } else {
            codigoAccountDebitar = 0;
            codigoAccountCreditar = 0;
        }

        return contas; //ctAccountFacade.getAccountDebitoCreditoActivo();
    }

    public List<CtAccount> getListAccountPassivoDebitoCredito() {
        //Conta Entidade
        System.out.println("getListAccountPassivoDebitoCredito()");
        List<CtAccount> lista = new ArrayList<>();

        if (codigoTermo != 0) {

            FinDocumento doc = fnDocumentoFacade.find(this.codigoTermo);
            CtAccount conta = ctAccountFacade.getByEntidade(doc.getFkEntidade().getPkEntidade());

            if (conta != null) {
                codigoAccountDebitar = conta.getPkAccount();
                lista.add(conta);

                if (opcao.equalsIgnoreCase("fact")) {
                    codigoAccountCreditar = conta.getPkAccount();
                } else {
                    codigoAccountDebitar = conta.getPkAccount();
                }

            } else {
                codigoAccountDebitar = 0;
                codigoAccountCreditar = 0;
                Mensagem.enviarMensagem(FacesMessage.SEVERITY_WARN, null, "A Entidade : '" + doc.getFkEntidade().getNome() + "' Ainda Não Possui Conta De Movimento");
            }
        } else {
            codigoAccountDebitar = 0;
            codigoAccountCreditar = 0;
        }

        return lista;//ctAccountFacade.getAccountDebitoCreditoPassivo();
    }

    private boolean updateMontantesDisponiveis(CtBalancet balancet, CtAnoEconomico anoEconomico, CtMontanteRubrica montanteRubrica) {
        CtRubrica rub = balancet.getFkAccount().getFkRubrica();
        CtClass classe;
        classe = rub.getFkClass();
        CtMontanteClasse montanteClass = montanteClasseFacade.getByYearAndClass(anoEconomico.getPkAnoEconomico(), classe.getPkClass());

        if (montanteClass == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Não há um montante definido para a categoria: !" + classe.getDescricaoClass()));
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "", "Deve registar-se um montante para esta categoria no exercicio!" + anoEconomico.getAnoEconomico()));
            return false;
        }
        if (anoEconomico.getValorDisponivel() == null) {
            anoEconomico.setValorDisponivel(anoEconomico.getValorAnual() - this.lancamento.getValorLancamento());
        } else {
            anoEconomico.setValorDisponivel(anoEconomico.getValorDisponivel() - this.lancamento.getValorLancamento());
        }
        anoEconomicoFacade.edit(anoEconomico);

        if (montanteClass.getValorDisponivel() == null) {
            montanteClass.setValorDisponivel(montanteClass.getValorAnualClasse() - this.lancamento.getValorLancamento());
        } else {
            montanteClass.setValorDisponivel(montanteClass.getValorDisponivel() - this.lancamento.getValorLancamento());
        }
        montanteClasseFacade.edit(montanteClass);

        if (opcao.equalsIgnoreCase("ordem")) {
            if (montanteRubrica.getValorDisponivel() == null) {
                montanteRubrica.setValorDisponivel(montanteRubrica.getValorAnualRubrica() - this.lancamento.getValorLancamento());
            } else {
                montanteRubrica.setValorDisponivel(montanteRubrica.getValorDisponivel() - this.lancamento.getValorLancamento());
            }

            montanteRubricaFacade.edit(montanteRubrica);
        }

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Valores Anuais Para Exercicio Economico/Categoria/Rubrica Actualizados!"));
        return true;
    }

    private CtMontanteRubrica criarMontanteRubricaZero(CtRubrica rub) {

        CtMontanteRubrica montanteNovoZero = new CtMontanteRubrica();

        montanteNovoZero.setValorAnualRubrica(0.00);
        montanteNovoZero.setValorDisponivel(0.00);
        montanteNovoZero.setFkAnoEconomico(new CtAnoEconomico(codigoYear));
        montanteNovoZero.setFkRubrica(rub);
        montanteNovoZero.setStateMontanteRubrica(true);
        montanteNovoZero.setRegistroMontanteRubrica(new Date());

        montanteRubricaFacade.create(montanteNovoZero);

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Foi Definido Um Montante Para A Rubrica Automaticamente!"));

        return montanteNovoZero;
    }

    private CtMontanteRubrica verifECriarMontanteRubrica(int codigoAccountCreditar, int codigoAccountDebitar) {

        CtMontanteRubrica montanteRubrica;
        CtAccount conta;
        CtClass classe;

        conta = ctAccountFacade.getAccount(codigoAccountCreditar);
        classe = conta.getFkRubrica().getFkClass();
        montanteRubrica = montanteRubricaFacade.getByAnoClasseRubrica(codigoYear, classe.getPkClass(), conta.getFkRubrica().getPkRubrica());

        if (montanteRubrica == null) {
            montanteRubrica = criarMontanteRubricaZero(conta.getFkRubrica());
        }

        return montanteRubrica;
    }

    public Boolean condicao() {
        return (opcao.equalsIgnoreCase("Fact"));
    }

}
