/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seg.beans;

import ejbs.entities.SegConta;
import ejbs.facades.SegContaFacade;
import ejbs.facades.SegPerfilFacade;
import ejbs.facades.SegTipoContaFacade;
import java.io.Serializable;
import java.util.Timer;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.inject.Named;
import javax.mail.MessagingException;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import org.omnifaces.cdi.ViewScoped;
import seg.utils.EncriptacaoDecriptacao;
import utils.mensagens.LogFile;
import utils.parametros_entre_formularios.ParametrosEntreFormulariosBean;

/**
 *
 * @author helena
 */
@Named
@ViewScoped
public class SegUtilizadorConcluirCriacaoContaBean implements Serializable {

    @Inject
    ParametrosEntreFormulariosBean parametrosEntreFormulariosBean;

    @EJB
    private SegContaFacade segContaFacade;
    @EJB
    private SegTipoContaFacade segTipoContaFacade;

    @EJB
    private SegPerfilFacade segPerfilFacade;
    private boolean criou = false;

    @Resource
    private UserTransaction userTransaction;

    private int idPerfil;
    private String palavaraPasseVizualizar;

    private Timer timerFormulario;

    private int tipoConta;

    private SegConta conta;

    public SegUtilizadorConcluirCriacaoContaBean() {
    }

    @PostConstruct
    public void init() {
        System.err.println("0: SegUtilizadorConcluirCriacaoContaBean.init()");
        this.palavaraPasseVizualizar = (String) parametrosEntreFormulariosBean.getParametro("palavaraPasseVizualizar");
        System.err.println("1: SegUtilizadorConcluirCriacaoContaBean.init()\tpalavaraPasseVizualizar: "
                + palavaraPasseVizualizar);
        this.conta = (SegConta) parametrosEntreFormulariosBean.getParametro("conta");
        System.err.println("2: SegUtilizadorConcluirCriacaoContaBean.init()");
        this.tipoConta = 2;
        System.err.println("3: SegUtilizadorConcluirCriacaoContaBean.init()\tConta: " + (conta == null ? "null" : this.segContaFacade.toString(conta)));
    }

    @PreDestroy
    public void purge() {
        parametrosEntreFormulariosBean.removeParametro("palavaraPasseVizualizar");
        parametrosEntreFormulariosBean.removeParametro("conta");
    }

    public void criarConta() {
        try {
            conta.setPalavraPasse(EncriptacaoDecriptacao.encrypt(palavaraPasseVizualizar.trim()));

            if (segContaFacade.findAllContaOrderByNomeUtilizador(conta.getNomeUtilizador(), conta.getPalavraPasse()) != null) {
                LogFile.warnMsg(null, "O nome de utilizador " + conta.getNomeUtilizador() + " e a correspondente senha encontram-se em uso");
            } else if (conta.getNomeUtilizador().trim().equals("")) {
                LogFile.warnMsg(null, "O nome de utilizador é inválido");
            } else if (palavaraPasseVizualizar.trim().equals("")) {
                LogFile.warnMsg(null, "a senha é inválido");
            } else if (!conta.getPalavraPasse().trim().equals("") && !conta.getNomeUtilizador().trim().equals("") && (segContaFacade.findAllContaOrderByNomeUtilizador(conta.getNomeUtilizador(), conta.getPalavraPasse()) == null)) {
                userTransaction.begin();
                conta.setFkSegPerfil(idPerfil == 0 ? null : this.segPerfilFacade.find(this.idPerfil));
                conta.setFkTipoConta(segTipoContaFacade.find(this.tipoConta));
                segContaFacade.create(conta);
                userTransaction.commit();
                LogFile.sucessoMsg(null, "Conta Criada com Sucesso. \n Nome de utilizador: " + conta.
                        getNomeUtilizador());
                criou = true;
            }

            //enviarCredencias();
        } catch (IllegalStateException | SecurityException | HeuristicMixedException | HeuristicRollbackException | NotSupportedException | RollbackException | SystemException e) {
            //System.err.println("4: SegUtilizadorConcluirCriacaoContaBean.criarConta()");
            try {
                //System.err.println("5: SegUtilizadorConcluirCriacaoContaBean.criarConta()");
                LogFile.erroMsg(null, e.toString());
                userTransaction.rollback();
            } catch (IllegalStateException | SecurityException | SystemException ex) {
                //System.err.println("6: SegUtilizadorConcluirCriacaoContaBean.criarConta()");
                LogFile.erroMsg(null, "Rollback: " + ex.toString());
            }
        }
    }

    public void enviarCredencias() throws MessagingException {
        String msg = "Conta Criada com Sucesso. \n Nome de utilizador: " + conta.
                getNomeUtilizador() + "\n Palavra-passe: " + palavaraPasseVizualizar;

//        listaContactos = grlTelefoneFacade.findAllTelefoneOrderByPkGrlTelefone(
//            conta.getFkSegGrlPessoa().getPkSegGrlPessoa());
//        messageMovelSetBean.sendSMS(listaContactos, msg);
    }

    public SegConta getConta() {
        return conta;
    }

    public void setConta(SegConta conta) {
        this.conta = conta;
    }

    public int getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(int idPerfil) {
        this.idPerfil = idPerfil;
    }

    public String getPalavaraPasseVizualizar() {
        return palavaraPasseVizualizar;
    }

    public void setPalavaraPasseVizualizar(String palavaraPasseVizualizar) {
        this.palavaraPasseVizualizar = palavaraPasseVizualizar;
    }

    public boolean isCriou() {
        return criou;
    }

    public void setCriou(boolean criou) {
        this.criou = criou;
    }

    public int getTipoConta() {
        return tipoConta;
    }

    public void setTipoConta(int tipoConta) {
        this.tipoConta = tipoConta;
    }

}
