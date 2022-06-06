/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package financas.beans;

import ejbs.cache.FinMoedaCache;
import ejbs.entities.FinCaixa;
import ejbs.entities.FinOperacoesCaixa;
import ejbs.entities.SegConta;
import ejbs.facades.FinCaixaFacade;
import ejbs.facades.FinMoedaFacade;
import ejbs.facades.FinOperacoesCaixaFacade;
import financas.util.Caixa;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.annotation.PostConstruct;
import javax.transaction.UserTransaction;
import seg.beans.SegLoginBean;
import utils.mensagens.Mensagem;

/**
 *
 * @author majoao
 */
@Named(value = "finOperacoesCaixaBean")
@ViewScoped
public class FinOperacoesCaixaBean implements Serializable {

    /**
     * Creates a new instance of FinOperacoesCaixaBean
     */
    @EJB
    private FinOperacoesCaixaFacade fnOperacoescaixaFacade;

    @EJB
    private FinMoedaFacade fnMoedaFacade;

    @EJB
    private FinCaixaFacade fnCaixaFacade;

    @Resource
    UserTransaction userTransaction;

    /**
     * Creates a new instance of operacoesCaixaBean
     */
    private List<FinCaixa> listCaixa = null;
    private List<FinOperacoesCaixa> listCaixaAbertos = null;
    private List<FinOperacoesCaixa> lista = null;
    private List<FinOperacoesCaixa> listaCxOperacoes = null;
    private List<FinCaixa> list = null;
    private List<FinCaixa> listCaixasOpen = null;
    private List<FinCaixa> listCaixasClose = null;
    private FinOperacoesCaixa operacoesCaixa = null;

    private int codigoFuncionario = 0;
    private int codigoUser = 0;
    private List<SegConta> listConta = null;
    private SegConta user = null;

    private double quebraDeCaixa = 0.00;
    private double totalVendas = 0.00;
    private double saldoFinal = 0.00;
    private double totalVendasFechoExtraordinario = 0.00;
    private double quebraDeCaixaFechoExtraordinario = 0.00;
    private double credito = 0;
    private double debito = 0.00;
    private boolean openCx = true;
    private boolean closeCx = false, flag = false;
    private FinCaixa caixa = null;
    private int codigoCaixa = 0;
    private int codigoCaixaFuncionario = 0;
    private int idMoeda = 0;
    private Caixa item = null;
    private Date data;

    private Date dataUltimoMov;
    private String obsOperacoesCaixa;

    @Inject
    SegLoginBean segLoginBean;
    @Inject
    FinMoedaCache moedaCache;

    public FinOperacoesCaixaBean() {

        this.caixa = new FinCaixa();
        this.user = new SegConta();
        this.listConta = new ArrayList<>();
        this.list = new ArrayList<>();
        this.lista = new ArrayList<>();
        this.listCaixasOpen = new ArrayList<>();
        this.listCaixasClose = new ArrayList<>();
        this.operacoesCaixa = new FinOperacoesCaixa();
        this.totalVendas = 0.00;
        this.item = new Caixa();
        this.obsOperacoesCaixa = "";
        this.dataUltimoMov = null;
    }

    @PostConstruct
    public void init() {
        this.listCaixa = fnOperacoescaixaFacade.listFinCaixasDisponiveis();
        this.listCaixaAbertos = fnOperacoescaixaFacade.listCaixasAbertos();
        listaCxOperacoes = fnOperacoescaixaFacade.obterUltimoMovimentoCaixaPorUserAccount(segLoginBean.getContaUtilizador());
        verificarCaixaAbertoUtilizadorLogado();
    }

    public void verificarCaixaAbertoUtilizadorLogado() {
        if (listCaixaAbertos != null) {
            for (FinOperacoesCaixa listCaixaAberto : listCaixaAbertos) {
                if (listCaixaAberto.getFkUtilizadorOpen().getPkSegConta().intValue()
                        == segLoginBean.getContaUtilizador().getPkSegConta().intValue()) {
                    this.listCaixa.clear();
                    this.operacoesCaixa = listCaixaAberto;
                    this.caixa = listCaixaAberto.getFkCaixa();
                    idMoeda = listCaixaAberto.getFkMoeda().getPkMoeda();
                    this.listCaixa.add(caixa);
                    closeCx = true;
                    openCx = false;
                    return;
                }
            }
            flag = true;
        }
        if (flag) {
            openCx = true;
            closeCx = false;
        }

    }

    public String getObsOperacoesCaixa() {
        return obsOperacoesCaixa;
    }

    public void setObsOperacoesCaixa(String obsOperacoesCaixa) {
        this.obsOperacoesCaixa = obsOperacoesCaixa;
    }

    public void acharDataUltimoMov() {

        List<FinOperacoesCaixa> op = fnOperacoescaixaFacade.obterUltimoMovimentoCaixaPorUserAccount(new SegConta(this.codigoUser));

        if (op != null) {
            this.dataUltimoMov = op.get(0).getDataMovimento();
        } else {
            this.dataUltimoMov = null;
        }

    }

    public Date getDataUltimoMov() {

        if (this.codigoUser != 0) {
            acharDataUltimoMov();
        } else {
            this.dataUltimoMov = null;
        }

        return dataUltimoMov;
    }

    public void setDataUltimoMov(Date dataUltimoMov) {
        this.dataUltimoMov = dataUltimoMov;
    }

    public List<FinCaixa> getListCaixa() {
        this.list = new ArrayList<>();
        this.list = fnCaixaFacade.listCxsAbertos();

        if (list != null) {
            return list;
        }
        return list;
    }

    public void setListCaixa(List<FinCaixa> listCaixa) {
        this.listCaixa = listCaixa;
    }

    public List<FinCaixa> getList() {
        return list;
    }

    public void setList(List<FinCaixa> list) {
        this.list = list;
    }

    public FinOperacoesCaixa getOperacoesCaixa() {

        if (this.operacoesCaixa == null) {
            this.operacoesCaixa = new FinOperacoesCaixa();
        }
        return operacoesCaixa;
    }

    public void setOperacoesCaixa(FinOperacoesCaixa operacoesCaixa) {
        this.operacoesCaixa = operacoesCaixa;
    }

    public FinCaixa getCaixa() {
        return caixa;
    }

    public void setCaixa(FinCaixa caixa) {
        this.caixa = caixa;
    }

    public int getCodigoCaixa() {
        return codigoCaixa;
    }

    public void setCodigoCaixa(int codigoCaixa) {
        this.codigoCaixa = codigoCaixa;
    }

    private boolean vericarUtilizadorCaixaAberto() {
        if (listCaixaAbertos == null) {
            return false;
        }
        return listCaixaAbertos.stream().anyMatch((listCaixaAberto) -> (listCaixaAberto.getFkUtilizadorOpen().getPkSegConta().intValue()
                == segLoginBean.getContaUtilizador().getPkSegConta().intValue()
                && listCaixaAberto.getOpenClose() == true));
    }

    public void abrirCaixa() {
        try {
            System.out.print("vericarUtilizadorCaixaAberto(): " + vericarUtilizadorCaixaAberto());
            if (!vericarUtilizadorCaixaAberto()) {
                this.operacoesCaixa.setDataMovimento(new Date());
                this.operacoesCaixa.setDescricaoCaixa("Saldo Inicial");
                this.operacoesCaixa.setDataRegistoAbertura(new Date());
                this.operacoesCaixa.setSaldoCaixa(this.operacoesCaixa.getSaldoInicial());
                this.operacoesCaixa.setCredito(0.00);
                this.operacoesCaixa.setDebito(0.00);
                this.operacoesCaixa.setSaldoFinal(0.00);
                this.operacoesCaixa.setOpenClose(true);
                this.operacoesCaixa.setFkUtilizador(segLoginBean.getContaUtilizador());
                this.operacoesCaixa.setFkUtilizadorOpen(segLoginBean.getContaUtilizador());
                this.operacoesCaixa.setFkMoeda(moedaCache.findFinMoeda(idMoeda));
                this.operacoesCaixa.setFkCaixa(caixa);
                this.fnOperacoescaixaFacade.create(this.operacoesCaixa);
                Mensagem.enviarMensagem(FacesMessage.SEVERITY_INFO, "O Caixa foi aberto com sucesso.!", null);
                init();
            } else {
                Mensagem.enviarMensagem(FacesMessage.SEVERITY_WARN, "Utilizador já tem caixa aberto", null);
            }

        } catch (Exception e) {
            Mensagem.enviarMensagem(FacesMessage.SEVERITY_ERROR, "Erro ao efectuar a operação."
                    + "Entre em contacto com o seu Administrador de Sistemas!", null);

            System.out.print("Mensagem: " + e.getMessage());

        }

    }

    public void fechoCaixa() {
        try {
            this.operacoesCaixa.setOpenClose(Boolean.FALSE);
            this.operacoesCaixa.setQuebraDeCaixa(quebraDeCaixa);
            this.fnOperacoescaixaFacade.edit(this.operacoesCaixa);
            Mensagem.enviarMensagem(FacesMessage.SEVERITY_INFO, "O Caixa foi fechado com sucesso.!", null);
        } catch (Exception e) {
             Mensagem.enviarMensagem(FacesMessage.SEVERITY_ERROR, "Erro ao efectuar a operação."
                    + "Entre em contacto com o seu Administrador de Sistemas!", null);
        }
    }

//    public String fecharCaixa(boolean extraordinario) {
//        try {
////            userTransaction.begin();
//
//            SegContas sgContas = null;
//            if (!extraordinario) {
//                sgContas = findUtilizadorAtual();
//            } else {
//                sgContas = sgContaFacade.find(this.codigoUser);
//            }
//
//            //Verifico se realmente o usuário logado tem um caixa criado!
//            this.listCaixaFuncionarios = new FinCaixaFuncionarioDAO().ValidarCx(sgContas.getFkFuncionario());
//
//            if (this.listCaixaFuncionarios != null) {
//                //Verificar se um cx pertence a determinado funcionário
//                this.caixaFuncionario = new FinCaixaFuncionarioDAO().findByCaixaUsuarioLogado(this.codigoCaixaFuncionario, sgContas.getFkFuncionario());
//                if (this.caixaFuncionario != null) {
//                    // verifica se o caixa do user já está fechado...
//                    this.caixaFuncionario = new FinCaixaFuncionarioDAO().findByCaixaFechado(this.codigoCaixaFuncionario, sgContas.getFkFuncionario());
//                    if (this.caixaFuncionario == null) {
//                        //verifica se realmente o usuário tem um caia aberto para fechar
//                        this.caixaFuncionario = new FinCaixaFuncionarioDAO().cxAbertoUsuaioLogado(this.codigoCaixaFuncionario, sgContas.getFkFuncionario());
//                        if (this.caixaFuncionario != null) {
//
//                            processarFechoCaixa(this.getQuebraDeCaixa(), sgContas, extraordinario);
//
////                            userTransaction.commit();
//                        }
//                    } else if (this.caixaFuncionario != null) {
//                        FacesUtil.adicionarMensagem(FacesMessage.SEVERITY_WARN, "O teu caixa já está fechado. Obrigado!");
//                        return "";
//                    }
//                } else if (this.caixaFuncionario == null) {
//                    FacesUtil.adicionarMensagem(FacesMessage.SEVERITY_WARN, "Este caixa foi associado a um outro Funcionário!");
//                    return "";
//                }
//            } else if (this.listCaixaFuncionarios == null) {
//                FacesUtil.adicionarMensagem(FacesMessage.SEVERITY_ERROR, "Você não tem um caixa criado! Entre em contacto com o seu Administrador de Sistemas.");
//                return "";
//            }
//
//            //} catch (NotSupportedException | SystemException | RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException ex) {
//        } catch (Exception ex) {
//
//            FacesUtil.adicionarMensagem(FacesMessage.SEVERITY_ERROR, ex.getMessage());
//
//            FacesUtil.adicionarMensagem(FacesMessage.SEVERITY_ERROR, "Erro ao salvar a verifique todos os parametros de inserção!");
//
//            Logger.getLogger(OperacoesCaixaBean.class.getName()).log(Level.SEVERE, null, ex);
////            try {
//////                userTransaction.rollback();
////                Logger.getLogger(OperacoesCaixaBean.class.getName()).log(Level.SEVERE, null, ex);
////            } catch (IllegalStateException | SecurityException | SystemException ex1) {
////                Logger.getLogger(OperacoesCaixaBean.class.getName()).log(Level.SEVERE, null, ex1);
////            }
//        }
//
//        return "";
//    }
//
//   
    public void setListCaixaAbertos(List<FinOperacoesCaixa> listCaixaAbertos) {
        this.listCaixaAbertos = listCaixaAbertos;
    }

    public double getQuebraDeCaixa() {

        double valor = 0.0;//(this.getTotalVendas() - this.saldoFinal);

        return valor;
    }
    public void setQuebraDeCaixa(double quebraDeCaixa) {
        this.quebraDeCaixa = quebraDeCaixa;
    }

//    public double getTotalVendas() {
//
//        double sald = 0.00;
//        this.credito = 0.00;
//
//        try {
//
//            List<FinOperacoesCaixa> ls = fnOperacoescaixaFacade.listTotalVendas(findUtilizadorAtual(), new Date());
//            this.operacoesCaixa = fnOperacoescaixaFacade.valorInicial(findUtilizadorAtual(), new Date());
//
//            if (ls != null && this.operacoesCaixa != null) {
//                double sfinal = this.operacoesCaixa.getSaldoInicial();
//
//                for (FinOperacoesCaixa fn : ls) {
//
//                    this.credito = (this.credito + fn.getCredito());
//                    this.debito = (this.debito + fn.getDebito());
//                }
//
//                sald = ((this.credito + sfinal) - this.debito);
//                System.out.println("getTotalVendas() : " + sald);
//                return sald;
//
//            }
//        } catch (Exception ex) {
//
//            ex.printStackTrace();
//            System.out.println("getTotalVendas() exception : " + sald);
//            return sald;
//
//        }
//
//        System.out.println("getTotalVendas() zero: 0.00");
//        return 0.00;
//    }
    public void setTotalVendas(double totalVendas) {
        this.totalVendas = totalVendas;
    }

    public double getCredito() {
        return credito;
    }

    public void setCredito(double credito) {
        this.credito = credito;
    }

    public double getSaldoFinal() {
        return saldoFinal;
    }

    public void setSaldoFinal(double saldoFinal) {
        this.saldoFinal = saldoFinal;
    }

    public List<FinCaixa> getListCaixasOpen() {

        this.list = new ArrayList<>();
        this.list = fnCaixaFacade.listaCxAbertos();

        if (this.list != null) {

            return this.list;

        }
        return list;
    }

    public void setListCaixasOpen(List<FinCaixa> listCaixasOpen) {
        this.listCaixasOpen = listCaixasOpen;
    }

    public List<FinCaixa> getListCaixasClose() {

        this.list = new ArrayList<>();
        this.list = fnCaixaFacade.listaCxFechados();

        if (this.list != null) {

            return this.list;

        }
        return list;
    }

    public void setListCaixasClose(List<FinCaixa> listCaixasClose) {
        this.listCaixasClose = listCaixasClose;
    }

    public boolean isOpenCx() {
        return openCx;
    }

    public void setOpenCx(boolean openCx) {
        this.openCx = openCx;
    }

    public boolean isCloseCx() {
        return closeCx;
    }

    public void setCloseCx(boolean closeCx) {
        this.closeCx = closeCx;
    }

    public Caixa getItem() {
        return item;
    }

    public void setItem(Caixa item) {
        this.item = item;
    }

    public int getCodigoCaixaFuncionario() {
        return codigoCaixaFuncionario;
    }

    public void setCodigoCaixaFuncionario(int codigoCaixaFuncionario) {
        this.codigoCaixaFuncionario = codigoCaixaFuncionario;
    }

    public double getDebito() {
        return debito;
    }

    public void setDebito(double debito) {
        this.debito = debito;
    }

    public String getCodigoMovimento(int id) {

        DateFormat dateFormat = new SimpleDateFormat("yyyy");
        String codigo = id + "/" + "" + dateFormat.format(new Date());

        return codigo;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

//    public void fechoExtraordinario() {
//
//        double sald = 0.00;
//        this.credito = 0.00;
//
//        try {
//
//            this.funcionario = new RhFuncionarios();
//            this.funcionario = rhFuncionarioFacade.find(this.codigoFuncionario);
//            System.out.print(codigoUser);
//            System.out.print(funcionario);
//            System.out.print(this.getData());
//            List<FinOperacoesCaixa> ls = fnOperacoescaixaFacade.listTotalVendasFechoExtraordinario(new SegContas(this.codigoUser), this.getData());
//            this.operacoesCaixa = fnOperacoescaixaFacade.valorInicialFechoExtraordinario(new SegContas(this.codigoUser), this.getData());
//
//            double sfinal = this.operacoesCaixa.getSaldoInicial();
//            System.out.print(sfinal);
//            if (ls != null && this.operacoesCaixa != null) {
//
//                for (FinOperacoesCaixa fn : ls) {
//
//                    this.credito = (this.credito + fn.getCredito());
//                    this.debito = (this.debito + fn.getDebito());
//                }
//
//                this.setStatefecExt(true);
//                sald = ((this.credito + sfinal) - this.debito);
//                this.setTotalVendasFechoExtraordinario(sald);
//
//            }
//        } catch (Exception ex) {
//
//            this.setStatefecExt(false);
//            this.setTotalVendasFechoExtraordinario(0.00);
//            FacesUtil.adicionarMensagem(FacesMessage.SEVERITY_WARN, "Não há resultados nessa pesquisa!");
//            ex.printStackTrace();
//        }
//
//    }
//    public double getTotalVendasFechoExtraordinario() {
//
//        double sald = 0.00;
//        this.credito = 0.00;
//
//        try {
//
//            List<FinOperacoesCaixa> ls = fnOperacoescaixaFacade.listTotalVendasFechoExtraordinario(new SegContas(this.codigoUser), this.getData());
//            this.operacoesCaixa = fnOperacoescaixaFacade.valorInicialFechoExtraordinario(new SegContas(this.codigoUser), this.getData());
//
//            double sfinal = this.operacoesCaixa.getSaldoInicial();
//
//            if (ls != null && this.operacoesCaixa != null) {
//
//                for (FinOperacoesCaixa fn : ls) {
//
//                    this.credito = (this.credito + fn.getCredito());
//                    this.debito = (this.debito + fn.getDebito());
//                }
//
//                this.setStatefecExt(true);
//                sald = ((this.credito + sfinal) - this.debito);
//                return sald;
//
//            }
//        } catch (Exception ex) {
//
//            ex.printStackTrace();
//            return sald;
//
//        }
//
//        return 0.00;
//
//    }
    public void setTotalVendasFechoExtraordinario(double totalVendasFechoExtraordinario) {
        this.totalVendasFechoExtraordinario = totalVendasFechoExtraordinario;
    }

//    public double getQuebraDeCaixaFechoExtraordinario() {
//
//        double valor = (this.getTotalVendasFechoExtraordinario() - this.saldoFinal);
//
//        return valor;
//    }
    public void setQuebraDeCaixaFechoExtraordinario(double quebraDeCaixaFechoExtraordinario) {
        this.quebraDeCaixaFechoExtraordinario = quebraDeCaixaFechoExtraordinario;
    }

    public List<FinOperacoesCaixa> getLista() {
        return lista;
    }

    public void setLista(List<FinOperacoesCaixa> lista) {
        this.lista = lista;
    }

    public int getCodigoFuncionario() {
        return codigoFuncionario;
    }

    public void setCodigoFuncionario(int codigoFuncionario) {
        this.codigoFuncionario = codigoFuncionario;
    }

    public int getCodigoUser() {
        return codigoUser;
    }

    public void setCodigoUser(int codigoUser) {
        this.codigoUser = codigoUser;
    }

//    public List<FinCaixaFuncionario> getListCaixasFuncionarios() {
//
//        List<FinCaixaFuncionario> listCf = new FinCaixaFuncionarioDAO().listCxFuncionarios();
//        //listCf.get(0).getFkFuncionario().getFkPessoa().get
//
//        return listCf;
//
//        //G:_ return fnOperacoescaixaFacade.listUserAtivos();
//    }
    public void setListConta(List<SegConta> listConta) {
        this.listConta = listConta;
    }

//    private void gravarMovimentos(DateFormat dateFormat, SegContas sgContas, boolean extraordinario) {
//
//        List<FinOperacoesCaixa> array = fnOperacoescaixaFacade.lista(sgContas, new Date());
//
//        String mov = "Movimento: " + new Date();
//        mov += " __FechoExtraordinario: " + extraordinario;
//        mov += " __CaixaFuncionario: " + this.caixaFuncionario.getFkFuncionario().getFkPessoa().getNome() + " " + this.caixaFuncionario.getFkFuncionario().getFkPessoa().getSobrenome();
//        mov += " __Operador: " + findUtilizadorAtual().getNomeUsuario();
//        mov += " __SaldoFinal: " + this.saldoFinal;
//
//        this.mv.setMov(mov);
//        this.fnMovFacade.create(this.mv);
//
//        int proxPkMov = new FnMovementDAO().getMaxPkMov() + 1;
//
//        if ((array == null || array.isEmpty()) && extraordinario) {
//            array = fnOperacoescaixaFacade.lista(sgContas, dataUltimoMov);
//            System.out.println("Data Ultimo Movimento");
//        }
//
//        System.out.println("\n\n");
//        for (int i = 0; i < array.size(); i++) {
//
//            FinOperacoesCaixa op = array.get(i);
//
//            System.out.println("gravarMovimentos() pkOpracoesCaixa : " + op.getPkOperacoesCaixa());
//
//            this.movimento = new FnMovement();
//            this.movimento.setMovimento(this.getCodigoMovimento(proxPkMov));
//            this.movimento.setSaida(op.getDebito());
//            this.movimento.setEntrada(op.getCredito());
//            this.movimento.setDescricao(op.getDescricaoCaixa());
//            this.movimento.setSaldoCaixa(op.getSaldoCaixa());
//            this.movimento.setSaldoInicial(op.getSaldoInicial());
//            this.movimento.setData(op.getDataAberturaCaixa());
//            this.movimento.setSaldoFinal(op.getSaldoFinal());
//            this.movimento.setDataf(op.getDataFechoCaixa());
//            this.movimento.setOpenCloseCx(true);
//            this.movimento.setFkFuncionario(op.getFkFuncionario());
//            this.movimento.setFkCaixaFuncionario(op.getFkCaixaFuncionario());
//            this.movimento.setFkContas(op.getFkContas());
//            this.movimento.setDataRegistro(new Date());
//            this.movimento.setDatam(dateFormat.format(op.getDataMovimento()));
//
//            op.setFkMoeda(new FnMoeda(1));
//            op.setState(false);
//
//            fnMovementFacade.create(this.movimento);
//            fnOperacoescaixaFacade.edit(op);
//
//            if (i == array.size() - 1) {
//                System.out.println("gravarMovimentos() ::::Ultimo Movimento ::::");
//                System.out.println("gravarMovimentos() Ultimo Movimento saldoFinal op :" + op.getSaldoFinal());
//            }
//        }
//
//        //fecho extraordinário
//        this.setStatefecExt(false);
//        this.operacoesCaixa = new FinOperacoesCaixa();
//        this.caixa = new FinCaixa();
//        this.codigoCaixa = 0;
//        this.codigoCaixaFuncionario = 0;
//        this.saldoFinal = 0.00;
//    }
//
//    private void fecharCaixaBD() {
//        this.caixaFuncionario.setDataRegistro(new Date());
//        this.caixaFuncionario.setOpenClose(false);
//        this.fnCaixafuncionarioFacade.edit(this.caixaFuncionario);
//    }
//
//    private void gravarOperacoesCaixa(Double quebraCaixa, SegContas user) {
//
//        FinOperacoesCaixa operCaixa = new FinOperacoesCaixa();
//        operCaixa.setQuebraDeCaixa(quebraDeCaixa);
//        operCaixa.setObsOperacoesCaixa(this.obsOperacoesCaixa);
//        operCaixa.setDataMovimento(new Date());
//        operCaixa.setDataAberturaCaixa(new FinCaixaFuncionarioDAO().findLastSaldoInicial(findUtilizadorAtual()).getDataAberturaCaixa());
//        operCaixa.setDescricaoCaixa("Saldo Final");
//        operCaixa.setDataFechoCaixa(new Date());
//        System.out.println("gravarOperacoesCaixa() this.saldoFinal : " + this.saldoFinal);
//        operCaixa.setSaldoFinal(this.saldoFinal);
//        operCaixa.setSaldoInicial(0.00);
//        operCaixa.setCredito(0.00);
//        operCaixa.setDebito(0.00);
//        operCaixa.setSaldoCaixa(0.00);
//        operCaixa.setDataRegistroCx(new Date());
//        operCaixa.setFkContas(user);
//        operCaixa.setFkFuncionario(user.getFkFuncionario());
//        operCaixa.setFkMoeda(new FnMoeda(1));
//        operCaixa.setFkCaixaFuncionario(this.caixaFuncionario);
//        operCaixa.setStateOperacoesCaixa(true);
//        operCaixa.setState(true);
//
//        this.fnOperacoescaixaFacade.create(operCaixa);
//
//    }
//    private void processarFechoCaixa(double quebraDeCaixa, SegContas sgContas, boolean extraordinario) {
//
//        DateFormat dateFormat = new SimpleDateFormat("yyyy-dd-MM");
//
//        String msgAviso = "";
//        boolean aviso = false;
//
//        if (this.getSaldoFinal() > this.getTotalVendas()) {
//            aviso = true;
//            msgAviso = "Quebra de Caixa, tem saldo a mais!";
//        } else if (this.getSaldoFinal() < this.getTotalVendas()) {
//            aviso = true;
//            msgAviso = "Quebra de Caixa, tem saldo a menos!";
//        }
//
//        //FncaixaFuncionario
//        fecharCaixaBD();
//        //FinOperacoesCaixa
//        gravarOperacoesCaixa(quebraDeCaixa, sgContas);
//        //FnMov & FnMovimento
//        gravarMovimentos(dateFormat, sgContas, extraordinario);
//
//        if (aviso) {
//            FacesUtil.adicionarMensagem(FacesMessage.SEVERITY_WARN, msgAviso);
//        }
//        FacesUtil.adicionarMensagem(FacesMessage.SEVERITY_INFO, "O Caixa foi fechado com Sucesso!");
//
//    }
    
    public FinMoedaCache getMoedaCache() {
        return moedaCache;
    }

    public void setMoedaCache(FinMoedaCache moedaCache) {
        this.moedaCache = moedaCache;
    }

    public void setIdMoeda(int idMoeda) {
        this.idMoeda = idMoeda;
    }

    public int getIdMoeda() {
        return idMoeda;
    }

    public List<FinOperacoesCaixa> getListaCxOperacoes() {
        return listaCxOperacoes;
    }

    public void setListaCxOperacoes(List<FinOperacoesCaixa> listaCxOperacoes) {
        this.listaCxOperacoes = listaCxOperacoes;
    }

}
