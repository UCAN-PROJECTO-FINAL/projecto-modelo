/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seg.beans;

import ejbs.entities.SegConta;
import ejbs.facades.SegContaFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.inject.Named;
import org.omnifaces.cdi.ViewScoped;
import org.primefaces.PrimeFaces;
import seg.utils.EncriptacaoDecriptacao;
import seg.utils.UtilSegGeral;
import utils.mensagens.LogFile;

/**
 *
 * @author helena
 */
@Named(value = "alterarSenhaBean")
@ViewScoped
public class AlterarSenhaBean implements Serializable
{

    @EJB
    private SegContaFacade segContaFacade;
    
    @Inject
    private SegLoginBean segLoginBean;

    private SegConta sessaoActual;

    private String novaSenha, novaSenhaConfirmada, senhaActual;

    /**
     * Creates a new instance of AlterarSenhaBean
     */
    public AlterarSenhaBean()
    {
        
    }
    
    @PostConstruct
    public void init()
    {
        sessaoActual = segLoginBean.getSessaoActual();
    }

    public void alterarSenhaUserCorrente()
    {

        if (!novaSenha.isEmpty() && !novaSenhaConfirmada.isEmpty() && !senhaActual.isEmpty())
        {
System.err.println("0: AlterarSenhaBean()\tnovaSenha: " + novaSenha + 
    "\tsessaoActual.getPalavraPasse: " + sessaoActual.getPalavraPasse() + 
    "\tnovaSenhaConfirmada: " + novaSenhaConfirmada);
            if (!EncriptacaoDecriptacao.encrypt(senhaActual).equals(sessaoActual.getPalavraPasse()))
            {
System.err.println("1: AlterarSenhaBean()\tnovaSenha: " + novaSenha + 
    "\tsessaoActual.getPalavraPasse: " + sessaoActual.getPalavraPasse() + 
    "\tnovaSenhaConfirmada: " + novaSenhaConfirmada);
                LogFile.erroMsg(null, "A senha introduzida nao corresponde a senha do utilizador Corrente");
            }
            else if (!novaSenha.equals(novaSenhaConfirmada))
            {
System.err.println("2: AlterarSenhaBean()\tnovaSenha: " + novaSenha + 
    "\tsessaoActual.getPalavraPasse: " + sessaoActual.getPalavraPasse() + 
    "\tnovaSenhaConfirmada: " + novaSenhaConfirmada);
            }

            else if (this.novaSenha.trim().length() < 6 || !UtilSegGeral.encontrarCaracterMaiuscula(this.novaSenha))
            {
                LogFile.erroMsg(null, "A senha deve ter Pelo Menos 6 Caracteres e Um Caractér Mausculo");
                novaSenha = null;
                novaSenhaConfirmada = null;
                senhaActual = null;
            }
            else
            {

                sessaoActual.setPalavraPasse(EncriptacaoDecriptacao.encrypt(novaSenha));
                segContaFacade.edit(sessaoActual);
                novaSenha = null;
                novaSenhaConfirmada = null;
                senhaActual = null;
                LogFile.sucessoMsg(null, "A senha de utilizador foi alterada com sucesso");
                PrimeFaces.current().executeScript("PF('dialogRestaurarPass').hide()");

            }

        }
        else
        {
            LogFile.erroMsg(null, "A senha introduzida uma senha valida");
        }

    }

    public SegConta getSessaoActual()
    {
        return sessaoActual;
    }

    public void setSessaoActual(SegConta sessaoActual)
    {
        this.sessaoActual = sessaoActual;
    }

    public String getNovaSenha()
    {
        return novaSenha;
    }

    public void setNovaSenha(String novaSenha)
    {
        this.novaSenha = novaSenha;
    }

    public String getNovaSenhaConfirmada()
    {
        return novaSenhaConfirmada;
    }

    public void setNovaSenhaConfirmada(String novaSenhaConfirmada)
    {
        this.novaSenhaConfirmada = novaSenhaConfirmada;
    }

    public String getSenhaActual()
    {
        return senhaActual;
    }

    public void setSenhaActual(String senhaActual)
    {
        this.senhaActual = senhaActual;
    }

}
