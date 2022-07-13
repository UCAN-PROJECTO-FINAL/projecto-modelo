/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contabilidade.beans;

import contabilidade.util.Numero;
import ejbs.entities.CtAccount;
import ejbs.entities.CtClass;
import ejbs.entities.CtMontanteClasse;
import ejbs.entities.CtRubrica;
import ejbs.facades.CtAccountFacade;
import ejbs.facades.CtClassFacade;
import ejbs.facades.CtMontanteClasseFacade;
import ejbs.facades.CtRubricaFacade;
import java.io.Serializable;
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
@Named(value = "ctRubricaManagedBean")
@ViewScoped
public class CtRubricaManagedBean implements Serializable {

    /**
     * Creates a new instance of CtRubricaManagedBean
     */
    @EJB
    private CtMontanteClasseFacade ctMontanteclasseFacade;
    @EJB
    private CtClassFacade ctClassFacade;
    @EJB
    private CtRubricaFacade ctRubricaFacade;

    @EJB
    private CtAccountFacade ctAccountFacade;

    @Resource
    UserTransaction userTransaction;

    /**
     * Creates a new instance of CtRubricaManagedBean
     */
    private CtRubrica rubrica = null;
    private Integer codigoRubrica = 0;
    private Integer codigoClasse = 0;
    private Integer codigoMontante = 0;
    private Numero numero;
    private Mensagem mensagem = new Mensagem();
    private CtClass classe = null;
    private CtMontanteClasse montante = null;
    private List<CtRubrica> listRubrica = null;
    private List<CtClass> listClass = null;
    private List<Numero> listNumero = null;
    private List<CtMontanteClasse> listMontante = null;

    private int subRubrica, codigoRubricaPrincipal;

    public CtRubricaManagedBean() {
        this.rubrica = new CtRubrica();
        this.classe = new CtClass();
        this.listClass = new ArrayList<>();
        this.listRubrica = new ArrayList<>();
        this.listNumero = new ArrayList<>();
        this.montante = new CtMontanteClasse();
        this.listMontante = new ArrayList<>();
        this.numero = new Numero();
        this.codigoClasse = 0;
        this.codigoMontante = 0;
        this.subRubrica = 0;
    }
    
    @PostConstruct
    public void init(){
        this.rubrica = new CtRubrica();
        this.classe = new CtClass();
        this.listClass = new ArrayList<>();
        this.listRubrica = new ArrayList<>();
        this.listNumero = new ArrayList<>();
        this.montante = new CtMontanteClasse();
        this.listMontante = new ArrayList<>();
        this.numero = new Numero();
        this.codigoClasse = 0;
        this.codigoMontante = 0;
        this.subRubrica = 0;
    }
    
    public int getSubRubrica() {
        return subRubrica;
    }

    public void setSubRubrica(int subRubrica) {
        this.subRubrica = subRubrica;
    }

    public CtRubrica getRubrica() {
        return rubrica;
    }

    public void setRubrica(CtRubrica rubrica) {
        this.rubrica = rubrica;
    }

    public Integer getCodigoRubrica() {
        return codigoRubrica;
    }

    public void setCodigoRubrica(Integer codigoRubrica) {
        this.codigoRubrica = codigoRubrica;
    }

    public Integer getCodigoClasse() {
        return codigoClasse;
    }

    public void setCodigoClasse(Integer codigoClasse) {
        this.codigoClasse = codigoClasse;
    }

    public Integer getCodigoMontante() {
        return codigoMontante;
    }

    public void setCodigoMontante(Integer codigoMontante) {
        this.codigoMontante = codigoMontante;
    }

    public Numero getNumero() {
        return numero;
    }

    public void setNumero(Numero numero) {
        this.numero = numero;
    }

    public Mensagem getMensagem() {
        return mensagem;
    }

    public void setMensagem(Mensagem mensagem) {
        this.mensagem = mensagem;
    }

    public CtClass getClasse() {
        return classe;
    }

    public void setClasse(CtClass classe) {
        this.classe = classe;
    }

    public CtMontanteClasse getMontante() {
        return montante;
    }

    public void setMontante(CtMontanteClasse montante) {
        this.montante = montante;
    }

    public List<CtRubrica> getListRubrica() {

        this.listRubrica = new ArrayList<>();
        this.listRubrica = ctRubricaFacade.listRubrica();

        if (this.listRubrica != null) {

            return this.listRubrica;

        }
        return new ArrayList<>();
    }

    public List<CtRubrica> getListRubricaByClass() {

        if (subRubrica == 1) {
            this.listRubrica = ctRubricaFacade.getRubricaPorClasse(new CtClass(codigoClasse));
            return this.listRubrica;
        }

        return new ArrayList<>();
    }

    public void setListRubrica(List<CtRubrica> listRubrica) {
        this.listRubrica = listRubrica;
    }

    public List<CtClass> getListClass() {

        this.listClass = new ArrayList<>();
        this.listClass = ctClassFacade.listCategoria();

        if (this.listClass != null) {

            return this.listClass;
        }

        return this.listClass;
    }

    public void setListClass(List<CtClass> listClass) {
        this.listClass = listClass;
    }

    public List<Numero> getListNumero() {
        return numero.Numeros();
    }

    public void setListNumero(List<Numero> listNumero) {
        this.listNumero = listNumero;
    }

    public List<CtMontanteClasse> getListMontante() {

        this.listMontante = new ArrayList<>();

        this.listMontante = ctMontanteclasseFacade.listMontanteClasse();

        if (this.listMontante != null) {

            return this.listMontante;

        }

        return this.listMontante;
    }

    public void setListMontante(List<CtMontanteClasse> listMontante) {
        this.listMontante = listMontante;
    }

    private boolean validarRubrica(String descricao) {
        return getListRubrica().stream().anyMatch((ctRubrica) -> (ctRubrica.getDescricaoRubrica().equalsIgnoreCase(descricao)));
    }

    public String salvar() {
        if (validarRubrica(rubrica.getDescricaoRubrica())) {
            Mensagem.enviarMensagem(FacesMessage.SEVERITY_WARN, "Rubrica já cadastrada !",null);
            return "";
        }
        try {
            userTransaction.begin();

            List<CtRubrica> list = this.getListRubrica();

            String number = gerarProximoNumberRubrica(this.codigoClasse, this.rubrica);
            this.rubrica.setPkRubrica(getIDRubrica());
            this.rubrica.setNumberRubrica(number);
            this.rubrica.setDataRegistroRubrica(new Date());
            this.rubrica.setFkClass(ctClassFacade.find(this.codigoClasse));
            this.rubrica.setStateRubrica(true);

            if (list != null) {
                for (CtRubrica item : list) {
                    if (item.getNumberRubrica().equals(this.rubrica.getNumberRubrica())) {
                        Mensagem.enviarMensagem(FacesMessage.SEVERITY_ERROR, "Já existe uma Rubrica com o número  " + rubrica.getNumberRubrica() + ".",null);
                        return "";
                    }
                }
            }

            this.ctRubricaFacade.create(this.rubrica);

            userTransaction.commit();

            userTransaction.begin();

            gravarAccount();

            this.rubrica = new CtRubrica();
            this.listNumero = new ArrayList<>();
            this.listClass = new ArrayList<>();

            Mensagem.enviarMensagem(FacesMessage.SEVERITY_INFO, "Dados Salvos com sucessos!",null);
            userTransaction.commit();
            init();
            return "";

        } catch (NotSupportedException | SystemException | RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException e) {
            Mensagem.enviarMensagem(FacesMessage.SEVERITY_ERROR, "Impossível salvar a Rubrica, entre em contacto com o seu Administrador de Sistemas.",null);
            Logger.getLogger(CtRubricaManagedBean.class.getName()).log(Level.SEVERE, null, e);
            try {
                userTransaction.rollback();
            } catch (IllegalStateException | SecurityException | SystemException ex) {
                Logger.getLogger(CtRubricaManagedBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return "";
    }

    private int getIDRubrica() {
        return ctRubricaFacade.count() + 1;
    }

    public void editar() {

        try {

            this.codigoClasse = this.rubrica.getFkClass().getPkClass();
            this.rubrica.setStateRubrica(true);
            this.rubrica.setFkClass(ctClassFacade.find(this.codigoClasse));
            this.ctRubricaFacade.edit(this.rubrica);

            this.rubrica = new CtRubrica();
            this.listNumero = new ArrayList<>();
            this.listClass = new ArrayList<>();
            this.codigoMontante = 0;
            this.codigoClasse = 0;
            Mensagem.enviarMensagem(FacesMessage.SEVERITY_INFO, "Dados Salvos com sucessos!",null);

        } catch (Exception exception) {
            Mensagem.enviarMensagem(FacesMessage.SEVERITY_ERROR, "Impossível alterar a a Rubrica. Verifique se está sendo utilizado em algum registro","");
            exception.printStackTrace();
        }

    }

    public void eliminar(int rubrica) {

        try {

            this.rubrica = ctRubricaFacade.find(rubrica);
            //this.rubrica.setFkContas(this.mensagem.utilizadorLogado());
            this.rubrica.setStateRubrica(false);
            this.ctRubricaFacade.edit(this.rubrica);
            this.rubrica = new CtRubrica();
            Mensagem.enviarMensagem(FacesMessage.SEVERITY_INFO, "Dados excluídos com sucesso!",null);

        } catch (Exception exception) {
            Mensagem.enviarMensagem(FacesMessage.SEVERITY_ERROR, "Impossível excluir a Rubrica, entre em contato com o Administrador de Sistemas",null);
            exception.printStackTrace();
        }

    }

    private String gerarProximoNumberRubrica(Integer codigoClasse, CtRubrica rub) {

        List<CtRubrica> lista = null;
        if (this.subRubrica == 1) {

            CtRubrica ctRubrica = ctRubricaFacade.get(codigoRubrica);

            String numberPai = ctRubrica.getNumberRubrica();

            lista = ctRubricaFacade.listRubricaByPai(codigoRubrica);

            int size = 0;
            if (lista != null) {
                size = lista.size();
            }
            rub.setFkRubrica(new CtRubrica(codigoRubrica));
            rub.setNumber(size + 1);
            return numberPai + "." + (size + 1);
        }

        lista = ctRubricaFacade.getRubricaPorClasse(new CtClass(codigoClasse));

        int size = 0;
        if (lista != null) {
            size = lista.size();
        }

        rub.setNumber(size + 1);

        return codigoClasse + "." + (size + 1);
    }

    private void gravarAccount() {

        /*
         As de classe 3 (Fornecedores Devem Ser Criadas Ao Criar Entidades)
         */
        if (codigoClasse != 3 && subRubrica == 0) {

            //int proxPkRubrica = ctRubricaFacade.getMaxPk() + 1;
            CtAccount account = new CtAccount();

            account.setDescricaoAccount(this.rubrica.getDescricaoRubrica());
//            account.setFkContas(this.mensagem.utilizadorLogado());
            account.setFkRubrica(this.rubrica);
            account.setNumberAccount(1);
            account.setDataRegistro(new Date());
            account.setNumeroAccount(codigoClasse + "." + this.rubrica.getPkRubrica() + "." + account.getNumberAccount());
            account.setStateAccount(true);

            this.ctAccountFacade.create(account);
            Mensagem.enviarMensagem(FacesMessage.SEVERITY_INFO, "Foram Criadas Contas De Movimento Debito e Credito Para A Rubrica!",null);
        }

    }

    private void gravarRubricaFornecedor() {

        if (subRubrica == 0 && codigoClasse != 3) {
            CtRubrica rubFornecedor = new CtRubrica();
            rubFornecedor.setDescricaoRubrica("Fornecedor - " + this.rubrica.getDescricaoRubrica());
            rubFornecedor.setPkRubrica(getIDRubrica());
            //String number = gerarProximoNumberRubrica(3, rubFornecedor);
            rubFornecedor.setNumberRubrica(3 + "." + this.rubrica.getNumber());
            rubFornecedor.setNumber(this.rubrica.getNumber());
            rubFornecedor.setFkClass(new CtClass(3));
            //rubFornecedor.setFkContas(this.mensagem.utilizadorLogado());
            rubFornecedor.setDataRegistroRubrica(new Date());
            rubFornecedor.setStateRubrica(true);

            this.ctRubricaFacade.create(rubFornecedor);
            Mensagem.enviarMensagem(FacesMessage.SEVERITY_INFO, "Foi Criada Conta Do Tipo 'Fornecedor' Para A Rubrica '" + this.rubrica.getDescricaoRubrica() + "'",null);

        }

    }

}
