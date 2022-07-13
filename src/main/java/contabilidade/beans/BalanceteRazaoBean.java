/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contabilidade.beans;

import contabilidade.util.BalanceteRazao;
import ejbs.entities.FinDocumento;
import ejbs.entities.GdTipoDocumento;
import ejbs.entities.CtAnoEconomico;
import ejbs.entities.CtBalancet;
import ejbs.entities.CtClass;
import ejbs.entities.CtLancamento;
import ejbs.entities.CtMontanteClasse;
import ejbs.entities.CtMontanteRubrica;
import ejbs.entities.CtOcorrenciaContabilidade;
import ejbs.entities.CtRubrica;
import ejbs.facades.FinDocumentoFacade;
import ejbs.facades.CtAnoEconomicoFacade;
import ejbs.facades.CtBalancetFacade;
import ejbs.facades.CtLancamentoFacade;
import ejbs.facades.CtMontanteClasseFacade;
import ejbs.facades.CtMontanteRubricaFacade;
import ejbs.facades.CtOcorrenciaContabilidadeFacade;
import ejbs.facades.CtRubricaFacade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import utils.mensagens.Mensagem;

/**
 *
 * @author majoao
 */
@Named(value = "balanceteRazaoBean")
@ViewScoped
public class BalanceteRazaoBean implements Serializable{

    /**
     * Creates a new instance of BalanceteRazaoBean
     */
    @EJB
    private CtOcorrenciaContabilidadeFacade ctOcorrenciacontabilidadeFacade;

    @EJB
    private FinDocumentoFacade fnDocumentoFacade;

    @EJB
    private CtLancamentoFacade ctLancamentoFacade;

//    @EJB
//    private FnContaCorrenteFacade fnContacorrenteFacade;

    //interfaces
    @EJB
    private CtBalancetFacade ctBalancetFacade;

    @EJB
    private CtRubricaFacade ctRubricaFacade;

    @EJB
    private CtAnoEconomicoFacade ctAnoeconomicoFacade;

    @EJB
    private CtMontanteClasseFacade ctMontanteClasseFacade;

    @EJB
    private CtMontanteRubricaFacade ctMontanteRubricaFacade;

    //objectos
    private CtRubrica rubrica;
    private CtBalancet balancet;
    private BalanceteRazao balancetRazao;
    private CtLancamento lancamento = new CtLancamento();
    //Listas
    private List<CtRubrica> listRubrica = null;
    private List<CtBalancet> listBalancet = null;
    private List<CtBalancet> listBalancetVerificacao = null;
    private List<BalanceteRazao> listBalancetRazao = null;
    private List<BalanceteRazao> listTotais = null;
    private CtOcorrenciaContabilidade historico = null;

    // variáveis
    private double totalDebito = 0.00;
    private double totalCredito = 0.00;
    private double totalSaldoDevedor = 0.00;
    private double totalSaldoCredor = 0.00;
    private double debito = 0.00;
    private double credito = 0.00;
    private double saldoDevedor = 0.00;
    private double saldoCredor = 0.00;


    @Resource
    UserTransaction userTransaction;

    public BalanceteRazaoBean() {

        this.balancet = new CtBalancet();
        this.rubrica = new CtRubrica();
        this.balancetRazao = new BalanceteRazao();
        this.listBalancet = new ArrayList<>();
        this.listBalancetRazao = new ArrayList<>();
        this.listRubrica = new ArrayList<>();
        this.listTotais = new ArrayList<>();
        this.historico = new CtOcorrenciaContabilidade();
    }
    
    
    
    public List<BalanceteRazao> collections() {

        List<BalanceteRazao> collectionsBalancete = new ArrayList<>();
        List<BalanceteRazao> collectionsTotais = new ArrayList<>();
        this.listRubrica = ctRubricaFacade.listRubrica() == null ? new ArrayList<>(): ctRubricaFacade.listRubrica();
        this.listBalancet = ctBalancetFacade.listBalancet() == null ? new ArrayList<>() : ctBalancetFacade.listBalancet();

        debito = 0.00;
        credito = 0.00;
        totalDebito = 0.00;
        totalCredito = 0.00;
        saldoDevedor = 0.00;
        saldoCredor = 0.00;
        totalSaldoDevedor = 0.00;
        totalSaldoCredor = 0.00;
        BalanceteRazao br;
        int codigoRubrica = 0;

        if (this.listRubrica != null) {

            for (CtRubrica heading : listRubrica) {

                br = new BalanceteRazao();
                codigoRubrica = heading.getPkRubrica();
                br.setNumero(heading.getNumberRubrica());
                br.setRubrica(heading.getDescricaoRubrica());

                for (CtBalancet bl : listBalancet) {

                    if (bl.getFkAccount().getFkRubrica().getPkRubrica() == codigoRubrica) {

                        this.debito = (debito + bl.getDebitoBalancet());
                        this.credito = (credito + bl.getCreditoBalancet());
                    }
                }

                this.totalDebito = (totalDebito + debito);
                this.totalCredito = (totalCredito + credito);
                this.totalSaldoDevedor = (totalSaldoDevedor + br.SaldoD(debito, credito));
                this.totalSaldoCredor = (totalSaldoCredor + br.SaldoC(debito, credito));

                br.setDebito(debito);
                br.setCredito(credito);
                br.setSaldoDevedor(br.SaldoD(debito, credito));
                br.setSaldoCredor(br.SaldoC(debito, credito));
                collectionsBalancete.add(br);

                debito = 0.00;
                credito = 0.00;

            }

            br = new BalanceteRazao();
            br.setTotais("");
            br.setTotalDebito(totalDebito);
            br.setTotalCredito(totalCredito);
            br.setTotalSaldoDevedor(totalSaldoDevedor);
            br.setTotalSaldoCredor(totalSaldoCredor);
//            System.out.print("Total de Débito: " + totalDebito);
//            System.out.print("Total Crédito: " + totalCredito);
//            System.out.print("Total de Saldo Devedor: " + totalSaldoDevedor);
//            System.out.print("Total de Saldo Credor: " + totalSaldoCredor);
            collectionsTotais.add(br);
            setListTotais(collectionsTotais);

        }

        return collectionsBalancete;

    }

    public boolean verificarLancamento() {

        if (lancamento == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Seleciona um documento por favor!", ""));

            return false;
        } else if (lancamento != null) {

            List<CtBalancet> list = ctBalancetFacade.getBalancetPorLancamento(new CtLancamento(this.lancamento.getPkLancamento()));

            for (CtBalancet b : list) {
                //System.out.print("Conta: " + b.getFkAccount().getDescricaoAccount());
            }

            this.setListBalancetVerificacao(list);

        }

        return true;

    }

    public String remover() {

        try {
            userTransaction.begin();

            if (lancamento == null) {

                Mensagem.warnMsg(null, "Por favor, Seleciona um documento para corrigir!");
                return "";
            }

            List<CtBalancet> listObj = ctBalancetFacade.getBalancetPorLancamento(this.lancamento);
            FinDocumento cont = fnDocumentoFacade.find(this.lancamento.getFkDocumento().getPkDocumento());
            if (listObj != null && cont != null) {

                //cont.setEstadoDocumento(true);
                //cont.setStatedocumento(true);
                fnDocumentoFacade.edit(cont);

                GdTipoDocumento tipo = lancamento.getFkDocumento().getFkTipoDoc();
                CtAnoEconomico anoEco = lancamento.getFkAnoEconomico();
                double valor = lancamento.getValorLancamento();
                CtRubrica rub = null;
                CtClass classe;

                for (CtBalancet item : listObj) {

                    item.setStateBalancet(false);
                    ctBalancetFacade.edit(item);

                    if (item.getCreditoBalancet() != 0.00) {
                        rub = item.getFkAccount().getFkRubrica();
                    }
                }

                classe = rub.getFkClass();

                CtMontanteClasse mc = ctMontanteClasseFacade.findByYearAndClass(anoEco.getPkAnoEconomico(), classe.getPkClass());
                anoEco.setValorDisponivel(anoEco.getValorDisponivel() + valor);
                ctAnoeconomicoFacade.edit(anoEco);
                mc.setValorDisponivel(mc.getValorDisponivel() + valor);
                ctMontanteClasseFacade.edit(mc);

                if (tipo.getPkTipoDocumento() == 2) {
                    CtMontanteRubrica mr = ctMontanteRubricaFacade.getByAnoClasseRubrica(anoEco.getPkAnoEconomico(), classe.getPkClass(), rub.getPkRubrica());
                    mr.setValorDisponivel(mr.getValorDisponivel() + valor);
                    ctMontanteRubricaFacade.edit(mr);
                }

            }

            this.lancamento.setStateLancamento(false);
            this.lancamento.setStateClssf(false);
            ctLancamentoFacade.edit(this.lancamento);

            this.historico.setDataRegistro(new Date());
            this.ctOcorrenciacontabilidadeFacade.create(this.historico);

            Mensagem.sucessoMsg(null, "O Extorno Foi Realizado Com Sucesso!");

            userTransaction.commit();

            return "";
        } catch (NotSupportedException | SystemException | RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException ex) {

            Mensagem.enviarMensagem(FacesMessage.SEVERITY_ERROR,null, ex.getMessage());

            Logger.getLogger(BalanceteRazaoBean.class.getName()).log(Level.SEVERE, null, ex);
        }

        return "";

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

    public BalanceteRazao getBalancetRazao() {
        return balancetRazao;
    }

    public void setBalancetRazao(BalanceteRazao balancetRazao) {
        this.balancetRazao = balancetRazao;
    }

    public List<CtRubrica> getListRubrica() {
        return listRubrica;
    }

    public void setListRubrica(List<CtRubrica> listRubrica) {
        this.listRubrica = listRubrica;
    }

    public List<CtBalancet> getListBalancet() {
        return listBalancet;
    }

    public void setListBalancet(List<CtBalancet> listBalancet) {
        this.listBalancet = listBalancet;
    }

    public List<BalanceteRazao> getListBalancetRazao() {
        return listBalancetRazao;
    }

    public void setListBalancetRazao(List<BalanceteRazao> listBalancetRazao) {
        this.listBalancetRazao = listBalancetRazao;
    }

    public CtBalancetFacade getCtBalancetFacade() {
        return ctBalancetFacade;
    }

    public void setCtBalancetFacade(CtBalancetFacade ctBalancetFacade) {
        this.ctBalancetFacade = ctBalancetFacade;
    }

    public CtRubricaFacade getCtRubricaFacade() {
        return ctRubricaFacade;
    }

    public void setCtRubricaFacade(CtRubricaFacade ctRubricaFacade) {
        this.ctRubricaFacade = ctRubricaFacade;
    }

    public double getTotalDebito() {
        return totalDebito;
    }

    public void setTotalDebito(double totalDebito) {
        this.totalDebito = totalDebito;
    }

    public double getTotalCredito() {
        return totalCredito;
    }

    public void setTotalCredito(double totalCredito) {
        this.totalCredito = totalCredito;
    }

    public double getTotalSaldoDevedor() {
        return totalSaldoDevedor;
    }

    public void setTotalSaldoDevedor(double totalSaldoDevedor) {
        this.totalSaldoDevedor = totalSaldoDevedor;
    }

    public double getTotalSaldoCredor() {
        return totalSaldoCredor;
    }

    public void setTotalSaldoCredor(double totalSaldoCredor) {
        this.totalSaldoCredor = totalSaldoCredor;
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

    public double getSaldoDevedor() {
        return saldoDevedor;
    }

    public void setSaldoDevedor(double saldoDevedor) {
        this.saldoDevedor = saldoDevedor;
    }

    public double getSaldoCredor() {
        return saldoCredor;
    }

    public void setSaldoCredor(double saldoCredor) {
        this.saldoCredor = saldoCredor;
    }

  

    public List<BalanceteRazao> getListTotais() {
        return listTotais;
    }

    public void setListTotais(List<BalanceteRazao> listTotais) {
        this.listTotais = listTotais;
    }

    public CtLancamento getLancamento() {
        return lancamento;
    }

    public void setLancamento(CtLancamento lancamento) {
        this.lancamento = lancamento;
    }

    public List<CtBalancet> getListBalancetVerificacao() {
        return listBalancetVerificacao;
    }

    public void setListBalancetVerificacao(List<CtBalancet> listBalancetVerificacao) {
        this.listBalancetVerificacao = listBalancetVerificacao;
    }

    

    
}
