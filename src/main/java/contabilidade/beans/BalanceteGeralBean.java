/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contabilidade.beans;

import contabilidade.util.BalanceteGeral;
import contabilidade.util.BalanceteRazao;
import contabilidade.util.PlanoConta;
import ejbs.entities.CtAccount;
import ejbs.entities.CtBalancet;
import ejbs.entities.CtClass;
import ejbs.entities.CtRubrica;
import ejbs.facades.CtAccountFacade;
import ejbs.facades.CtBalancetFacade;
import ejbs.facades.CtClassFacade;
import ejbs.facades.CtRubricaFacade;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import utils.mensagens.Mensagem;

/**
 *
 * @author majoao
 */
@Named(value = "balanceteGeralBean")
@ViewScoped
public class BalanceteGeralBean implements Serializable {

    /**
     * Creates a new instance of BalanceteGeralBean
     */
    @EJB
    private CtBalancetFacade ctBalancetFacade;

    @EJB
    private CtAccountFacade ctAccountFacade;

    @EJB
    private CtRubricaFacade ctRubricaFacade;

    @EJB
    private CtClassFacade ctClassFacade;

    //objectos
    private CtRubrica rubrica;
    private CtBalancet balancet;
    private BalanceteRazao balancetRazao;
    private CtAccount account;
    private CtClass classe;
    private PlanoConta pc;
    private BalanceteGeral balangeral;

    //listas
    private List<CtRubrica> listRubrica = null;
    private List<CtBalancet> listBalancet = null;
    private List<BalanceteRazao> listBalancetRazao = null;
    private List<BalanceteRazao> listTotais = null;
    private List<CtAccount> listAccount = null;
    private List<CtClass> listClasse = null;

    private double totalDebito = 0.00;
    private double totalCredito = 0.00;
    private double totalSaldoDevedor = 0.00;
    private double totalSaldoCredor = 0.00;
    private double debito = 0.00;
    private double credito = 0.00;
    private double saldoDevedor = 0.00;
    private double saldoCredor = 0.00;

    public BalanceteGeralBean() {
        this.balancet = new CtBalancet();
        this.rubrica = new CtRubrica();
        this.account = new CtAccount();
        this.classe = new CtClass();
        this.balangeral = new BalanceteGeral();
        this.pc = new PlanoConta();
        this.listClasse = new ArrayList<>();
        this.balancetRazao = new BalanceteRazao();
        this.listBalancet = new ArrayList<>();
        this.listBalancetRazao = new ArrayList<>();
        this.listAccount = new ArrayList<>();
        this.listRubrica = new ArrayList<>();
        this.listTotais = new ArrayList<>();
    }

    public List<PlanoConta> listPlanoConta() {

        this.listClasse = ctClassFacade.listCategoria() == null ? new ArrayList<>() : ctClassFacade.listCategoria();
        this.listRubrica = ctRubricaFacade.listRubrica() == null ? new ArrayList<>() : ctRubricaFacade.listRubrica();
        this.listAccount = ctAccountFacade.getAccount() == null ? new ArrayList<>() : ctAccountFacade.getAccount();
        List<PlanoConta> collections = new ArrayList<>();
        int codigoClasse = 0;
        int codigoRubrica = 0;
        if (listClasse != null) {
            for (CtClass cl : listClasse) {

                this.pc = new PlanoConta();
                codigoClasse = cl.getPkClass();
                this.pc.setCodigo(String.valueOf(codigoClasse));
                this.pc.setDescricao(cl.getDescricaoClass());
                collections.add(this.pc);

                for (CtRubrica rub : listRubrica) {

                    if (codigoClasse == rub.getFkClass().getPkClass()) {

                        this.pc = new PlanoConta();
                        codigoRubrica = rub.getPkRubrica();
                        this.pc.setCodigo(rub.getNumberRubrica());
                        this.pc.setDescricao(rub.getDescricaoRubrica());
                        collections.add(this.pc);

                        for (CtAccount conta : listAccount) {

                            if (codigoRubrica == conta.getFkRubrica().getPkRubrica()) {

                                this.pc = new PlanoConta();
                                this.pc.setCodigo(conta.getNumeroAccount());
                                this.pc.setDescricao(conta.getDescricaoAccount());
                                collections.add(this.pc);

                            }
                        }

                    }

                }

            }
        }

        return collections;

    }

    public List<BalanceteGeral> listBalanceteGeral() {
        try {

            this.listClasse = ctClassFacade.listCategoria() == null ? new ArrayList<>() : ctClassFacade.listCategoria();
            this.listRubrica = ctRubricaFacade.listRubrica() == null ? new ArrayList<>() :  ctRubricaFacade.listRubrica();
            this.listAccount = ctAccountFacade.getAccount() == null ? new ArrayList<>() : ctAccountFacade.getAccount();
            this.listBalancet = ctBalancetFacade.listBalancet() == null ? new ArrayList<>() : ctBalancetFacade.listBalancet();
            List<BalanceteGeral> listBalanct = new ArrayList<>();
            int codigoClasse = 0;
            int codigoRubrica = 0;
            int codigoConta = 0;
            for (CtClass cl : listClasse) {
                this.balangeral = new BalanceteGeral();
                codigoClasse = cl.getPkClass();
                // percorremos a lista Balancet para somar os débitos e créditos
                // if (!listBalancet.isEmpty() || listBalancet != null) {
                for (CtBalancet bl : listBalancet) {
                    if (bl.getFkAccount().getFkRubrica().getFkClass().getPkClass() == codigoClasse) {

                        this.debito = (debito + bl.getDebitoBalancet());
                        this.credito = (credito + bl.getCreditoBalancet());
                    }
                }

                this.balangeral.setCodigo(String.valueOf(codigoClasse));
                this.balangeral.setDescricao(cl.getDescricaoClass());
                this.balangeral.setDebito(debito);
                this.totalDebito = debito;
                this.balangeral.setCredito(credito);
                this.totalCredito = credito;
                this.balangeral.setSaldoDevedor(balangeral.SaldoD(debito, credito));
                this.saldoDevedor = balangeral.SaldoD(debito, credito);
                this.balangeral.setSaldoCredor(balangeral.SaldoC(debito, credito));
                this.saldoCredor = balangeral.SaldoC(debito, credito);
                listBalanct.add(this.balangeral);  //Adicionamos a classe com os seus saldos...
                for (CtRubrica rub : listRubrica) {
                    if (codigoClasse == rub.getFkClass().getPkClass()) {
                        this.balangeral = new BalanceteGeral();
                        codigoRubrica = rub.getPkRubrica();
                        debito = 0.00;
                        credito = 0.00;
                        //percorremos a lista Balancet para somar os débitos e créditos
                        for (CtBalancet bl : listBalancet) {
                            if (bl.getFkAccount().getFkRubrica().getPkRubrica() == codigoRubrica) {
                                this.debito = (debito + bl.getDebitoBalancet());
                                this.credito = (credito + bl.getCreditoBalancet());
                            }
                        }

                        this.balangeral.setCodigo(rub.getNumberRubrica());
                        this.balangeral.setDescricao(rub.getDescricaoRubrica());
                        this.balangeral.setDebito(debito);
                        this.balangeral.setCredito(credito);
                        this.balangeral.setSaldoDevedor(balangeral.SaldoD(debito, credito));
                        this.balangeral.setSaldoCredor(balangeral.SaldoC(debito, credito));
                        listBalanct.add(this.balangeral); //Adicionamos a rubrica com os seus saldos
                        // Vamos adicionar as contas de movimentos
                        for (CtAccount conta : listAccount) {
                            if (codigoRubrica == conta.getFkRubrica().getPkRubrica()) {
                                this.balangeral = new BalanceteGeral();
                                codigoConta = conta.getPkAccount();
                                debito = 0.00;
                                credito = 0.00;
                                //percorremos a lista Balancet para somar os débitos e créditos
                                for (CtBalancet bl : listBalancet) {

                                    if (bl.getFkAccount().getPkAccount() == codigoConta) {

                                        this.debito = (debito + bl.getDebitoBalancet());
                                        this.credito = (credito + bl.getCreditoBalancet());
                                    }
                                }

                                this.balangeral.setCodigo(conta.getNumeroAccount());
                                this.balangeral.setDescricao(conta.getDescricaoAccount());
                                this.balangeral.setDebito(debito);
                                this.balangeral.setCredito(credito);
                                this.balangeral.setSaldoDevedor(balangeral.SaldoD(debito, credito));
                                this.balangeral.setSaldoCredor(balangeral.SaldoC(debito, credito));
                                listBalanct.add(this.balangeral); //Adicionamos a conta com os seus saldos

                            }
                        }

                    }

                }

                this.balangeral = new BalanceteGeral();
                this.balangeral.setCodigo("");
                this.balangeral.setDescricao("Soma Líquida");
                this.balangeral.setDebito(this.totalDebito);
                this.balangeral.setCredito(this.totalCredito);
                this.balangeral.setSaldoDevedor(this.saldoDevedor);
                this.balangeral.setSaldoCredor(this.saldoCredor);
                listBalanct.add(this.balangeral);
                debito = 0.00;
                credito = 0.00;
                this.totalCredito = 0.00;
                this.totalDebito = 0.00;
                this.saldoDevedor = 0.00;
                this.saldoCredor = 0.00;

            }

            return !listBalanct.isEmpty() ? listBalanct : new ArrayList<>();

        } catch (Exception e) {
            System.err.println("E: " + e.getLocalizedMessage());
            System.err.println("E: " + e.getMessage());
            System.err.println("E: " + e.getCause().toString());
        }
        return new ArrayList<>();
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

    public CtAccount getAccount() {
        return account;
    }

    public void setAccount(CtAccount account) {
        this.account = account;
    }

    public CtClass getClasse() {
        return classe;
    }

    public void setClasse(CtClass classe) {
        this.classe = classe;
    }

    public PlanoConta getPc() {
        return pc;
    }

    public void setPc(PlanoConta pc) {
        this.pc = pc;
    }

    public BalanceteGeral getBalangeral() {
        return balangeral;
    }

    public void setBalangeral(BalanceteGeral balangeral) {
        this.balangeral = balangeral;
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

    public List<BalanceteRazao> getListTotais() {
        return listTotais;
    }

    public void setListTotais(List<BalanceteRazao> listTotais) {
        this.listTotais = listTotais;
    }

    public List<CtAccount> getListAccount() {
        return listAccount;
    }

    public void setListAccount(List<CtAccount> listAccount) {
        this.listAccount = listAccount;
    }

    public List<CtClass> getListClasse() {
        return listClasse;
    }

    public void setListClasse(List<CtClass> listClasse) {
        this.listClasse = listClasse;
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

}
