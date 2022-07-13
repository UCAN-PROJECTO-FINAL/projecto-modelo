/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contabilidade.beans;

import contabilidade.util.Utilitarios;
import ejbs.entities.FinDocumento;
import ejbs.entities.CtAccount;
import ejbs.entities.CtAnoEconomico;
import ejbs.entities.CtBalancet;
import ejbs.entities.CtContraPartidai;
import ejbs.entities.CtDiario;
import ejbs.entities.CtDocument;
import ejbs.entities.CtLancamento;
import ejbs.entities.CtOcorrenciaContabilidade;
import ejbs.entities.CtRubrica;
import ejbs.facades.FinDocumentoFacade;
import ejbs.facades.CtAccountFacade;
import ejbs.facades.CtAnoEconomicoFacade;
import ejbs.facades.CtBalancetFacade;
import ejbs.facades.CtContraPartidaiFacade;
import ejbs.facades.CtDiarioFacade;
import ejbs.facades.CtDocumentFacade;
import ejbs.facades.CtLancamentoFacade;
import ejbs.facades.CtMontanteClasseFacade;
import ejbs.facades.CtMontanteRubricaFacade;
import ejbs.facades.CtOcorrenciaContabilidadeFacade;
import ejbs.facades.CtRubricaFacade;
import ejbs.facades.FinContaCorrenteFacade;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.event.SelectEvent;
import utils.Defs;
import utils.mensagens.Mensagem;

/**
 *
 * @author majoao
 */
@Named(value = "movimentoBean")
@ViewScoped
public class MovimentoBean implements Serializable {

    @EJB
    private CtOcorrenciaContabilidadeFacade ctOcorrenciacontabilidadeFacade;

    @EJB
    private FinDocumentoFacade fnDocumentoFacade;

    // interfaces
    @EJB
    private CtAccountFacade ctAccountFacade;
    @EJB
    private CtRubricaFacade ctRubricaFacade;
    @EJB
    private CtBalancetFacade ctBalancetFacade;
    @EJB
    private CtLancamentoFacade ctLancamentoFacade;
    @EJB
    private FinContaCorrenteFacade fnContacorrenteFacade;
    @EJB
    private CtDocumentFacade ctDocumentoFacade;
    @EJB
    private CtAnoEconomicoFacade ctAnoeconomicoFacade;
    @EJB
    private CtDiarioFacade ctDiarioFacade;

    @EJB
    private CtContraPartidaiFacade ctContrapartidaiFacade;
    @EJB
    private CtMontanteClasseFacade montanteClasseFacade;

    @EJB
    private CtMontanteRubricaFacade montanteRubricaFacade;

    // objectos
    private CtRubrica rubrica;
    private CtBalancet balancet;
    private CtLancamento lancamento;
    private CtLancamento lancamentoSelecionado;
    private FinDocumento termo = new FinDocumento();
    private CtDocument documento;
    private CtDiario diario;
    private CtAnoEconomico year;
    private CtAccount account;

    // listas
    private List<CtRubrica> listRubrica = null;
    private List<CtBalancet> listBalancet = null;
    private List<CtBalancet> listBalancetPorLancamento = null;
    private List<CtLancamento> listLancamento = null;
    private List<FinDocumento> listTermo = null;
    private List<FinDocumento> listFacturas = null;
    private List<FinDocumento> listOrdensSaque = null;
    private List<CtDiario> listDiario = null;
    private List<CtDocument> listDocumento = null;
    private List<CtAnoEconomico> listYear = null;
    private List<CtAccount> listAccount = null;
    private List<CtAccount> listAccountActivoDebitoCredito = null;
    private List<CtAccount> listAccountPassivoDebitoCredito = null;
    private List<CtContraPartidai> listContraPartidadeInternaAux = null;
    private List<CtContraPartidai> listAux = null;

    // variáveis
    private Integer codigoAccountDebitar = 0;
    private Integer codigoAccountCreditar = 0;
    private Integer codigoYear;
    private int codigoOrdemSaque = 0;
    private int codigoFactura = 0;
    private Integer codigoDocumento = 0;
    private Integer codigoDiario = 0;

    private double valorFacturaSelecionada = 0;

    private int opcao = 0;
    private int codigoTermo = 0;
    private boolean estado;
    private boolean renderizarDetalhes = false;
    private boolean renderPainel = true;
    private boolean tabela = true;
    private boolean form = false;
    private CtOcorrenciaContabilidade historico = null;
    private Date dataMovimento;

   
    //variáveis balancete
    private double debito;
    private double credito;
    private Date data;

    // private variáveis lançamento
    private double valor = 0;
    private String obs = "";

    public MovimentoBean() {

        this.lancamento = new CtLancamento();
        this.balancet = new CtBalancet();

        this.rubrica = new CtRubrica();
        this.diario = new CtDiario();
        this.documento = new CtDocument();
        this.historico = new CtOcorrenciaContabilidade();
        this.year = new CtAnoEconomico();
        this.listContraPartidadeInternaAux = new ArrayList<>();
        this.listAux = new ArrayList<>();
        this.listBalancet = new ArrayList<>();
        this.listAccountActivoDebitoCredito = new ArrayList<>();
        this.listAccountPassivoDebitoCredito = new ArrayList<>();
        this.listYear = new ArrayList<>();
        this.listDocumento = new ArrayList<>();
        this.listFacturas = new ArrayList<>();
        this.listOrdensSaque = new ArrayList<>();
        this.listLancamento = new ArrayList<>();
        this.lancamentoSelecionado = new CtLancamento();
        this.listRubrica = new ArrayList<>();
        this.listBalancetPorLancamento = new ArrayList<>();
        this.listTermo = new ArrayList<>();
        this.listDiario = new ArrayList<>();
        this.codigoAccountDebitar = 0;
        this.codigoAccountCreditar = 0;

        this.codigoDocumento = 0;
        this.codigoDiario = 0;
        this.codigoFactura = 0;
        this.codigoOrdemSaque = 0;
        this.codigoTermo = 0;
        this.dataMovimento = new Date();

        //this.codigoYear = Calendar.getInstance().get(Calendar.YEAR);
    }

    @PostConstruct
    public void init ()
    {
        verificarExercicio ();
    }
    
   
    public void verificarExercicio ()
    {
        List<CtAnoEconomico> lista = ctAnoeconomicoFacade.findExercicioEconomicoByAno(Defs.ANOACTUAL);
        //CtAnoEconomico anoEconomicoAtual;
        
        if (lista.isEmpty()){
        
            this.codigoYear = ctAnoeconomicoFacade.getPkAnoEconomico().get(0).getPkAnoEconomico();
            System.err.println("ULTIMO EXERCICIO ATIVO : " + ctAnoeconomicoFacade.getPkAnoEconomico().get(0).getAnoEconomico());
        }else{
        
            this.codigoYear = lista.get(0).getPkAnoEconomico();
            System.err.println("EXERCICIO CORRENTE ATIVO: " + lista.get(0).getAnoEconomico());
        }
        
    }//Fim verificarExercicio
    
    

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

    public CtRubrica getRubrica() {
        return rubrica;
    }

    public void setRubrica(CtRubrica rubrica) {
        this.rubrica = rubrica;
    }

    public CtBalancet getBalancet() {
        return balancet;
    }

    public void setBalancet(CtBalancet balancet) {
        this.balancet = balancet;
    }

    public CtLancamento getLancamento() {
        return lancamento;
    }

    public void setLancamento(CtLancamento lancamento) {
        this.lancamento = lancamento;
    }

    public FinDocumento getTermo() {
        return termo;
    }

    public void setTermo(FinDocumento termo) {
        this.termo = termo;
    }

    public List<CtRubrica> getListRubrica() {

        this.listRubrica = new ArrayList<>();
        this.listRubrica = ctRubricaFacade.listRubrica();

        if (this.listRubrica != null) {

            return this.listRubrica;

        }
        return this.listRubrica;
    }

    public void setListRubrica(List<CtRubrica> listRubrica) {
        this.listRubrica = listRubrica;
    }

    public List<CtBalancet> getListBalancet() {

        this.listBalancet = new ArrayList<>();

        this.listBalancet = ctBalancetFacade.listBalancetData(new Date());

        if (this.listBalancet != null) {

            return this.listBalancet;
        }

        return this.listBalancet;
    }

    public void setListBalancet(List<CtBalancet> listBalancet) {
        this.listBalancet = listBalancet;
    }

    public List<CtLancamento> getListLancamento() {

        this.listLancamento = new ArrayList<>();

        //Facturas
        if (this.opcao == 0) {
            this.listLancamento = ctLancamentoFacade.getLancamentos(1, this.codigoYear);
        } //Ordem De Saque
        else {
            this.listLancamento = ctLancamentoFacade.getLancamentos(2, this.codigoYear);
        }

        if (this.listLancamento != null) {

            return this.listLancamento;
        }

        return this.listLancamento;
    }

    public void setListLancamento(List<CtLancamento> listLancamento) {

        this.listLancamento = listLancamento;
    }

    public List<FinDocumento> getListTermo() {

        return fnDocumentoFacade.getDocummento();
    }

    public void setListTermo(List<FinDocumento> listTermo) {
        this.listTermo = listTermo;
    }

   

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public void setDocumento(CtDocument documento) {
        this.documento = documento;
    }

    public CtAnoEconomico getYear() {
        return year;
    }

    public void setYear(CtAnoEconomico year) {
        this.year = year;
    }

    public void actualizarListDocumento() {
        this.listDocumento = ctDocumentoFacade.getDocumentoPorDiario(this.codigoDiario);
    }

    public List<CtDocument> getListDocumento() {
        actualizarListDocumento();
        return this.listDocumento;
    }

    public void setListDocumento(List<CtDocument> listDocumento) {
        this.listDocumento = listDocumento;
    }

    public List<CtAnoEconomico> getListYear() {

        if (this.listYear != null) {
            actualizarListYear();
            this.codigoYear = Utilitarios.getPkExercicioActual(listYear);
            return this.listYear;
        }

        return new ArrayList<CtAnoEconomico>();
    }

    public void actualizarListYear() {

        this.listYear = new ArrayList<>();

        this.listYear = ctAnoeconomicoFacade.listYear();
    }

    public void setListYear(List<CtAnoEconomico> listYear) {
        this.listYear = listYear;
    }

    public double getDebito() {
        return debito;
    }

    public void setDebito(double debito) {
        this.debito = debito;
    }

    public double getCredito() {
        return credito;
    }

    public void setCredito(double credito) {
        this.credito = credito;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    
    public void ocultarDetalhes() {

        this.setRenderizarDetalhes(false);
        this.setRenderPainel(true);
    }

    
    public CtDocument getDocumento() {
        return documento;
    }

    public void limpar() {

        this.lancamento = new CtLancamento();
        this.balancet = new CtBalancet();
        this.termo = new FinDocumento();
        this.codigoYear = 0;
        this.codigoDiario = 0;
        this.codigoAccountCreditar = 0;
        this.codigoAccountDebitar = 0;
        this.obs = "";
        this.codigoTermo = 0;
        this.opcao = 0;
        this.codigoDocumento = 0;

    }

    public Integer getCodigoYear() {
        return codigoYear;
    }

//    public void seleccionar(FinDocumento documento) {
//        RequestContext.getCurrentInstance().closeDialog(documento);
//
//    }

    public void seleccionarDocumento(SelectEvent evento) {
        termo = (FinDocumento) evento.getObject();
        this.setCodigoTermo(this.termo.getPkDocumento());

    }

    public void setCodigoYear(Integer codigoYear) {
        this.codigoYear = codigoYear;
    }

    public Integer getCodigoDocumento() {
        return codigoDocumento;
    }

    public void setCodigoDocumento(Integer codigoDocumento) {
        this.codigoDocumento = codigoDocumento;
    }

    public Integer getCodigoDiario() {
        return codigoDiario;
    }

    public void setCodigoDiario(Integer codigoDiario) {
        this.codigoDiario = codigoDiario;
    }

    public CtDiario getDiario() {
        return diario;
    }

    public void setDiario(CtDiario diario) {
        this.diario = diario;
    }

    public void actualizarListDiario() {
        this.listDiario = ctDiarioFacade.listDiarios();
    }

    public List<CtDiario> getListDiario() {

        actualizarListDiario();
        return this.listDiario;
    }

    public void setListDiario(List<CtDiario> listDiario) {
        this.listDiario = listDiario;
    }

    public CtAccount getAccount() {
        return account;
    }

    public void setAccount(CtAccount account) {
        this.account = account;
    }

    public void actualizarListAccount() {
        this.listAccount = ctAccountFacade.getAccount();
    }

    public List<CtAccount> getListAccount() {

        actualizarListAccount();
        return this.listAccount;
    }

    public void setListAccount(List<CtAccount> listAccount) {
        this.listAccount = listAccount;
    }

    public Integer getCodigoAccountDebitar() {
        return codigoAccountDebitar;
    }

    public void setCodigoAccountDebitar(Integer codigoAccountDebitar) {
        this.codigoAccountDebitar = codigoAccountDebitar;
    }

    public Integer getCodigoAccountCreditar() {
        return codigoAccountCreditar;
    }

    public void setCodigoAccountCreditar(Integer codigoAccountCreditar) {
        this.codigoAccountCreditar = codigoAccountCreditar;
    }

    public CtLancamento getLancamentoSelecionado() {
        return lancamentoSelecionado;
    }

    public void setLancamentoSelecionado(CtLancamento lancamentoSelecionado) {
        this.lancamentoSelecionado = lancamentoSelecionado;
    }

    public List<CtBalancet> getListBalancetPorLancamento() {

        return listBalancetPorLancamento;

    }

    public void setListBalancetPorLancamento(List<CtBalancet> listBalancetPorLancamento) {
        this.listBalancetPorLancamento = listBalancetPorLancamento;
    }

    public List<FinDocumento> getListFacturas() {

        actualizarListFacturas();
        return this.listFacturas;

    }

    public void actualizarListFacturas() {
        listFacturas = ctLancamentoFacade.getFactura();

    }

    public void setListFacturas(List<FinDocumento> listFacturas) {
        this.listFacturas = listFacturas;
    }

    public void actualizarListOrdensSaque() {
        this.listOrdensSaque = ctLancamentoFacade.getOrdemDeSaque();
    }

    public List<FinDocumento> getListOrdensSaque() {
        actualizarListOrdensSaque();
        return this.listOrdensSaque;
    }

    public void setListOrdensSaque(List<FinDocumento> listOrdensSaque) {
        this.listOrdensSaque = listOrdensSaque;
    }

    public int getOpcao() {
        return opcao;
    }

    public void setOpcao(int opcao) {
        this.opcao = opcao;
    }

    public int getCodigoOrdemSaque() {
        return codigoOrdemSaque;
    }

    public void setCodigoOrdemSaque(int codigoOrdemSaque) {
        this.codigoOrdemSaque = codigoOrdemSaque;
    }

    public int getCodigoFactura() {
        return codigoFactura;
    }

    public void setCodigoFactura(int codigoFactura) {
        this.codigoFactura = codigoFactura;
    }

    public int getCodigoTermo() {
        return codigoTermo;
    }

    public void setCodigoTermo(int codigoTermo) {
        this.codigoTermo = codigoTermo;
    }

    public boolean isRenderizarDetalhes() {
        return renderizarDetalhes;
    }

    public void setRenderizarDetalhes(boolean renderizarDetalhes) {
        this.renderizarDetalhes = renderizarDetalhes;
    }

    public boolean isRenderPainel() {
        return renderPainel;
    }

    public void setRenderPainel(boolean renderPainel) {
        this.renderPainel = renderPainel;
    }

    public boolean isTabela() {
        return tabela;
    }

    public void setTabela(boolean tabela) {
        this.tabela = tabela;
    }

    public boolean isForm() {
        return form;
    }

    public void setForm(boolean form) {
        this.form = form;
    }

    

    public CtOcorrenciaContabilidade getHistorico() {
        return historico;
    }

    public void setHistorico(CtOcorrenciaContabilidade historico) {
        this.historico = historico;
    }

    public void actualizarListAccountActivoDebitoCredito() {
        this.listAccountActivoDebitoCredito = ctAccountFacade.getAccountDebitoCreditoActivo();
    }

    public List<CtAccount> getListAccountActivoDebitoCredito() {
        actualizarListAccountActivoDebitoCredito();
        return this.listAccountActivoDebitoCredito;
    }

    public void setListAccountActivoDebitoCredito(List<CtAccount> listAccountActivoDebitoCredito) {
        this.listAccountActivoDebitoCredito = listAccountActivoDebitoCredito;
    }

    public void actualizarListAccountPassivoDebitoCredito() {
        this.listAccountPassivoDebitoCredito = ctAccountFacade.getAccountDebitoCreditoPassivo();
    }

    public List<CtAccount> getListAccountPassivoDebitoCredito() {
        actualizarListAccountPassivoDebitoCredito();
        return this.listAccountPassivoDebitoCredito;
    }

    public void setListAccountPassivoDebitoCredito(List<CtAccount> listAccountPassivoDebitoCredito) {
        this.listAccountPassivoDebitoCredito = listAccountPassivoDebitoCredito;
    }

    public int getMes(Date data) {

        DateFormat dateFormat = new SimpleDateFormat("MM");
        String month = dateFormat.format(data);

        return Integer.parseInt(month);
    }

    public Date getDataMovimento() {
        return dataMovimento;
    }

    public void setDataMovimento(Date dataMovimento) {
        this.dataMovimento = dataMovimento;
    }


    public List<CtContraPartidai> getListContraPartidadeInternaAux() {
        return listContraPartidadeInternaAux;
    }

    public void setListContraPartidadeInternaAux(List<CtContraPartidai> listContraPartidadeInternaAux) {
        this.listContraPartidadeInternaAux = listContraPartidadeInternaAux;
    }

    public List<CtContraPartidai> getListAux() {
        return listAux;
    }

    public void setListAux(List<CtContraPartidai> listAux) {
        this.listAux = listAux;
    }

    public String verMovimentoContraPartida() {

        this.listAux = new ArrayList<>();

        this.listAux = ctContrapartidaiFacade.listTransferenciasPorData(this.dataMovimento);

        if (this.listAux != null) {
            System.out.print("Entrei");
            this.setListAux(listAux);
            return "";
        }

        Mensagem.enviarMensagem(null,FacesMessage.SEVERITY_WARN, "Não há movimentos nessa data!");
        return "";
    }
    
}
