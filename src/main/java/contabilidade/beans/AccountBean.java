/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contabilidade.beans;

import ejbs.cache.GrlEntidadeCache;
import ejbs.entities.CtAccount;
import ejbs.entities.CtClass;
import ejbs.entities.CtMontanteRubrica;
import ejbs.entities.CtRubrica;
import ejbs.facades.CtAccountFacade;
import ejbs.facades.CtClassFacade;
import ejbs.facades.CtMontanteRubricaFacade;
import ejbs.facades.CtRubricaFacade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import utils.mensagens.Mensagem;

/**
 *
 * @author majoao
 */
@Named(value = "accountBean")
@ViewScoped
public class AccountBean implements Serializable {

    /**
     * Creates a new instance of AccountBean
     */
    @EJB
    private CtMontanteRubricaFacade ctMontanterubricaFacade;

    // interfaces
    @EJB
    private CtRubricaFacade ctRubricaFacade;
    @EJB
    private CtClassFacade ctClassFacade;

    @EJB
    private CtAccountFacade ctAccountFacade;

    // objectos
    private CtRubrica rubrica;
    private CtClass classe;
    private CtAccount account;
    private CtAccount accountSelecionada = null;
    //private Numero numero;
    private CtMontanteRubrica montanteRubrica;

    // listas
    private List<CtRubrica> listRubrica = null;
    private List<CtAccount> listAccount = null;
    private List<CtClass> listClasse = null;
    //private List<Numero> listNumero = null;
    private List<CtRubrica> list = null;
    private List<CtRubrica> list2 = null;

    // variáveis
    private Integer codigoClasse = 0;
    private Integer codigoRubrica = 0, pkEntidade;
    private String numeroRubrica = "";
    
    @Inject
    private GrlEntidadeCache glClienteCache;
    

    public AccountBean() {
    }

    @PostConstruct
    public void init() {
        this.account = new CtAccount();
        this.accountSelecionada = new CtAccount();
        this.classe = new CtClass();
        this.rubrica = new CtRubrica();
        this.list = new ArrayList<>();
        this.list2 = new ArrayList<>();
        this.listAccount = new ArrayList<>();
        this.listClasse = new ArrayList<>();
        this.listRubrica = new ArrayList<>();
        this.codigoClasse = 0;
        this.codigoRubrica = 0;
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

    public List<CtAccount> getListAccount() {

        this.listAccount = new ArrayList<>();

        this.listAccount = ctAccountFacade.getAccount();
        if (this.listAccount != null) {

            return this.listAccount;
        }

        return this.listAccount;
    }

    public String getNumeroAccount(String numeroRubrica, int number) {

        return numeroRubrica + "." + "" + number;
    }

    public String getNumeroAccount(int idRubrica) {

        List<CtRubrica> ls = getListRubrica();
        CtRubrica item;

        if (ls != null) {

            for (int i = 0; i < ls.size(); i++) {

                item = ls.get(i);
                if (item.getPkRubrica() == idRubrica) {
                    return item.getNumberRubrica();
                }
            }

        }

        return null;
    }

    public int getIdRubrica(String numeroRubrica) {

        List<CtRubrica> lis = getListRubrica();
        CtRubrica ct;

        if (lis != null) {
            for (int i = 0; i < lis.size(); i++) {

                ct = lis.get(i);
                if (ct.getNumberRubrica().equals(numeroRubrica)) {
                    return ct.getPkRubrica();
                }
            }
        }

        return 0;
    }

    public String gravar() {

        List<CtAccount> array = null;//this.getListAccount();

        try {
            int numeroRub = Integer.parseInt(numeroRubrica);

            String numeroAcString = generateNumeroAccount(numeroRub);
            //
            this.account.setFkEntidade(glClienteCache.findGrlEntidade(pkEntidade));//(fkEntidade);
            this.account.setNumeroAccount(numeroAcString);
            this.account.setStateAccount(true);
            this.account.setDataRegistro(new Date());
            this.account.setFkRubrica(new CtRubrica(numeroRub));

            if (array != null) {

                for (CtAccount item : array) {

                    if (item.getNumeroAccount().equals(this.account.getNumeroAccount())) {
                        Mensagem.warnMsg(null, "Já existe uma Conta com o número  " + account.getNumeroAccount() + "");
                        return "";
                    }
                }
            }

            this.ctAccountFacade.create(this.account);
            this.account = new CtAccount();
            this.codigoClasse = 0;
            this.codigoRubrica = 0;
            Mensagem.warnMsg(null, "A Conta foi criada com sucesso!");

        } catch (NumberFormatException ex) {
            Mensagem.erroMsg(null, "Erro ao salvar a verifique todos os parametros de inserção!");
        }
        return "";
    }

    public String generateNumeroAccount(int numeroRub) {

        List<CtAccount> lista = ctAccountFacade.getAccountPorRubrica(numeroRub);

        if (lista != null && !lista.isEmpty()) {

            CtRubrica rub = lista.get(0).getFkRubrica();

            String numberRubrica = rub.getNumberRubrica();

            this.account.setNumberAccount((lista.size() + 1));

            return numberRubrica + "." + (lista.size() + 1);
        }

        CtRubrica rub = ctRubricaFacade.get(numeroRub);
        this.account.setNumberAccount(1);
        return rub.getNumberRubrica() + "." + 1;

    }

    public String getNumeroAccountAlterar(int idRubrica, int number) {

        return getNumeroAccount(idRubrica) + "." + "" + number;
    }

    public void eliminar(int account) {

        try {

            this.account = ctAccountFacade.find(account);
            this.account.setStateAccount(false);
            this.ctAccountFacade.edit(this.account);
            this.ctAccountFacade.create(this.account);
            this.account = new CtAccount();
            this.codigoClasse = 0;
            this.codigoRubrica = 0;
            Mensagem.sucessoMsg(null, "A Conta foi excluída com sucesso!");

        } catch (Exception exception) {
            Mensagem.erroMsg(null, "Impossível eliminar a Conta, Verifique se está sendo utilizado em algum registro, ou entre em contacto com o Administrador de Sistema.");
        }

    }

    public void editar() {

        try {

            codigoRubrica = this.account.getFkRubrica().getPkRubrica();
            this.account.setStateAccount(true);
            this.account.setDataRegistro(new Date());
            this.account.setNumeroAccount(getNumeroAccountAlterar(this.codigoRubrica, this.account.getNumberAccount()));
            this.account.setFkRubrica(ctRubricaFacade.find(this.codigoRubrica));
            this.ctAccountFacade.edit(this.account);
            this.accountSelecionada = new CtAccount();
            this.rubrica = new CtRubrica();
            this.codigoClasse = 0;
            this.codigoRubrica = 0;
            Mensagem.sucessoMsg(null, "A Conta foi alterada com sucesso!");

        } catch (Exception exception) {
            Mensagem.erroMsg(null, "Impossível alterar a Conta, entre em contacto com o seu Administrador de Sistemas!");

        }

    }

    public CtRubrica getRubrica() {
        return rubrica;
    }

    public void setRubrica(CtRubrica rubrica) {
        this.rubrica = rubrica;
    }

    public CtClass getClasse() {
        return classe;
    }

    public void setClasse(CtClass classe) {
        this.classe = classe;
    }

    public CtAccount getAccount() {
        return account;
    }

    public void setAccount(CtAccount account) {
        this.account = account;
    }

    public CtAccount getAccountSelecionada() {
        return accountSelecionada;
    }

    public void setAccountSelecionada(CtAccount accountSelecionada) {
        this.accountSelecionada = accountSelecionada;
    }

    public CtMontanteRubrica getMontanteRubrica() {
        return montanteRubrica;
    }

    public void setMontanteRubrica(CtMontanteRubrica montanteRubrica) {
        this.montanteRubrica = montanteRubrica;
    }

    public List<CtClass> getListClasse() {
        listClasse = new ArrayList<>();
        listClasse = ctClassFacade.listCategoria();
        if (listClasse != null) {
            return this.listClasse;
        }
        return this.listClasse;
    }

    public void setListClasse(List<CtClass> listClasse) {
        this.listClasse = listClasse;
    }

    public List<CtRubrica> getList() {
        return (codigoClasse == 0) ? new ArrayList<>() : ctRubricaFacade.getRubricaPoClasseAlterar(codigoClasse);
    }

    public void setList(List<CtRubrica> list) {
        this.list = list;
    }

    public List<CtRubrica> getList2() {
        return list2;
    }

    public void setList2(List<CtRubrica> list2) {
        this.list2 = list2;
    }

    public Integer getCodigoClasse() {
        return codigoClasse;
    }

    public void setCodigoClasse(Integer codigoClasse) {
        this.codigoClasse = codigoClasse;
    }

    public Integer getCodigoRubrica() {
        return codigoRubrica;
    }

    public void setCodigoRubrica(Integer codigoRubrica) {
        this.codigoRubrica = codigoRubrica;
    }

    public String getNumeroRubrica() {
        return numeroRubrica;
    }

    public void setNumeroRubrica(String numeroRubrica) {
        this.numeroRubrica = numeroRubrica;
    }

    public Integer getPkEntidade() {
        return pkEntidade;
    }

    public void setPkEntidade(Integer pkEntidade) {
        this.pkEntidade = pkEntidade;
    }
    
    

}
