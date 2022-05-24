/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils.mensagens;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import utils.Defs;

/**
 *
 * @author aires
 */
public class Mensagem
{

    private static String defaultIdentifier = Defs.ID_GROWL_FORM_GERAL;

    /**
     *
     * @param identifier
     * @param msg
     */
    public static void enviarMensagemErro(String identifier, String msg, FacesContext context)
    {
        enviarMensagem(identifier, FacesMessage.SEVERITY_ERROR, msg, context);
    }

    /**
     *
     * @param identifier
     * @param msg
     */
    public static void enviarMensagemInformacao(String identifier, String msg, FacesContext context)
    {
        enviarMensagem(identifier, FacesMessage.SEVERITY_INFO, msg, context);
    }

    /**
     *
     * @param identifier
     * @param msg
     */
    public static void enviarMensagemAlerta(String identifier, String msg, FacesContext context)
    {
        enviarMensagem(identifier, FacesMessage.SEVERITY_WARN, msg, context);
    }

    public static void enviarMensagemErro(String identifier, String msg)
    {
        enviarMensagem(identifier, FacesMessage.SEVERITY_ERROR, msg);
    }

    /**
     *
     * @param identifier
     * @param msg
     */
    public static void enviarMensagemInformacao(String identifier, String msg)
    {
        enviarMensagem(identifier, FacesMessage.SEVERITY_INFO, msg);
    }

    /**
     *
     * @param identifier
     * @param msg
     */
    public static void enviarMensagemAlerta(String identifier, String msg)
    {
//System.err.println("0: LogFile.enviarMensagemAlerta(String identifier, String msg)");
        enviarMensagem(identifier, FacesMessage.SEVERITY_WARN, msg);
    }

    /**
     *
     * @param identifier
     * @param msg
     */
    public static void enviarMensagemFatal(String identifier, String msg)
    {
        enviarMensagem(identifier, FacesMessage.SEVERITY_FATAL, msg);
    }

    public static void sucessoMsg(String identifier, String logMessage)
    {
        System.err.println(logMessage);
        Mensagem.enviarMensagemInformacao(identifier, logMessage);
    }

    public static void erroMsg(String identifier, String logMessage)
    {
        System.err.println(logMessage);
        Mensagem.enviarMensagemErro(identifier, logMessage);
    }

    public static void warnMsg(String identifier, String logMessage)
    {
        System.err.println(logMessage);
        Mensagem.enviarMensagemAlerta(identifier, logMessage);
    }

    /**
     *
     * @param identifier
     * @param severity
     * @param msg
     * @param context
     */
    public static synchronized void enviarMensagem(String identifier, FacesMessage.Severity severity, String msg, FacesContext context)
    {
        if (identifier == null)
        {
            identifier = defaultIdentifier;
        }
        context = context != null ? context : FacesContext.getCurrentInstance();
//		System.err.println("0: Mensagem.enviarMensagem()\tcontext: " + (context == null ? "null" : "not null"
//			+ "\tmsg: " + msg));
        if (context == null)
        {
            return;
        }
        FacesMessage facesMessage1 = new FacesMessage(severity, msg, null);
        context.addMessage(identifier, facesMessage1);
    }

    public static void enviarMensagem(String identifier, FacesMessage.Severity severity, String msg)
    {
//System.err.println("0: LogFile.enviarMensagem()");
        if (identifier == null)
        {
            identifier = defaultIdentifier;
        }
//System.err.println("1: LogFile.enviarMensagem()");
        FacesContext context = FacesContext.getCurrentInstance();
        if (context == null)
        {
//System.err.println("2: LogFile.enviarMensagem()");            
            return;
        }
        FacesMessage facesMessage1 = new FacesMessage(severity, msg, null);
//System.err.println("3: LogFile.enviarMensagem()\tmsg: " + msg + "\tidentifier: " + identifier);        
        context.addMessage(identifier, facesMessage1);
    }

    // envio de mensagem para a janela
    /**
     *
     * @param tituloJanela
     * @param msg
     */
    public static void enviarJanelaMensagemErro(String tituloJanela, String msg)
    {
        enviarMensagem(FacesMessage.SEVERITY_ERROR, tituloJanela, msg);
    }

    /**
     *
     * @param tituloJanela
     * @param msg
     */
    public static void enviarJanelaMensagemInformacao(String tituloJanela, String msg)
    {
//System.out.println("0: Mensagem.enviarJanelaMensagemInformacao()\tmsg: " + msg);
        enviarMensagem(FacesMessage.SEVERITY_INFO, tituloJanela, msg);
    }

    /**
     *
     * @param tituloJanela
     * @param msg
     */
    public static void enviarJanelaMensagemAlerta(String tituloJanela, String msg)
    {
        enviarMensagem(FacesMessage.SEVERITY_WARN, tituloJanela, msg);
    }

    /**
     *
     * @param tituloJanela
     * @param msg
     */
    public static void enviarJanelaMensagemFatal(String tituloJanela, String msg)
    {
        enviarMensagem(FacesMessage.SEVERITY_FATAL, tituloJanela, msg);
    }

    /**
     *
     * @param severity
     * @param tituloJanela
     * @param msg
     */
    public static void enviarMensagem(FacesMessage.Severity severity, String tituloJanela, String msg)
    {
//		RequestContext context = RequestContext.getCurrentInstance();
//		context.showMessageInDialog(new FacesMessage(severity, tituloJanela, msg));

        FacesContext context = FacesContext.getCurrentInstance();
        if (context == null)
        {
            return;
        }
        context.addMessage(null, new FacesMessage(severity, tituloJanela, msg));
    }
}
