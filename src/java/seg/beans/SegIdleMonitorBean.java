
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
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.omnifaces.cdi.ViewScoped;
import org.primefaces.PrimeFaces;
import utils.Defs;

/**
 *
 * @author aires
 */
@Named(value = "segIdleMonitorBean")
@ViewScoped
public class SegIdleMonitorBean implements Serializable
{

    @EJB
    private SegContaFacade segContaFacade;

    @Inject
    private SegLoginBean segLoginBean;

    private SegConta contaUtilizador;
    private FacesContext faceContext;
    private ExternalContext externalContext;

    private int screenW, screenH, userTimeOut, tempoMaximoInactividadeTimeOut;
    private boolean activarTempoMaximoInactividade, renderIdleMonitor;

    private HttpSession sessao, sessaoLocal;
    private FacesContext context;

    /**
     * Creates a new instance of SegIdleMonitorBean
     */
    public SegIdleMonitorBean()
    {
    }

    @PostConstruct
    public void init()
    {
        //System.err.println("0: SegIdleMonitorBean.init()");
        context = FacesContext.getCurrentInstance();
        sessaoLocal = (HttpSession) context.getExternalContext().getSession(false);
        sessao = segLoginBean.getSessao();
        //System.err.println("1: SegIdleMonitorBean.init()");
//        contaUtilizador = segLoginBean.getContaUtilizador();
        //System.err.println("2: SegIdleMonitorBean.init()\tcontaUtilizador: "
//            + (contaUtilizador == null ? "null" : contaUtilizador.getNomeUtilizador()));
//        contaUtilizador = segContaFacade.find(contaUtilizador.getPkSegConta());
        //System.err.println("3: SegIdleMonitorBean.init()\tcontaUtilizador: "
//            + (contaUtilizador == null ? "null" : contaUtilizador.getNomeUtilizador()));
        activarTempoMaximoInactividade = this.segLoginBean.getActivarTempoInactividade();
        //System.err.println("4: SegIdleMonitorBean.init()");
        tempoMaximoInactividadeTimeOut = activarTempoMaximoInactividade
            ? 4 * this.segLoginBean.getIntervaloMaximoInactividade() / 5
            : 90 * 60 * 1000;

        //System.err.println("5: SegIdleMonitorBean.init()");
        Integer idleTime = segLoginBean.getMaxIdleTime();
        //System.err.println("6: SegIdleMonitorBean.init()\tidleTime: " + (idleTime == null ? "null" : idleTime)
//            + "\nIntervaloMaximoInactividade: " + segLoginBean.getIntervaloMaximoInactividade()
//            + "\ntempoMaximoInactividadeTimeOut: " + tempoMaximoInactividadeTimeOut
////            + "\nactivarTempoMaximoInactividade: " + activarTempoMaximoInactividade);
//        this.userTimeOut = idleTime == null
//            ? 1 * this.segLoginBean.getIntervaloMaximoInactividade() / 3
//            : idleTime;

        renderIdleMonitor = userTimeOut > 0;
        //System.err.println("7: SegIdleMonitorBean.init()\tidleTime: " + (idleTime == null ? "null" : idleTime)
//            + "\nuserTimeOut: " + userTimeOut);
    }

    public String prefixoPath()
    {
        externalContext = getExternalContext();
        String uri = ((HttpServletRequest) externalContext.getRequest()).getRequestURI();
        //System.err.println("0: SegIdleMonitorBean.prefixoPath()\nuri: " + uri
//            + "\tDefs.pathSeparator: " + Defs.pathSeparator());
////        uri = FilenameUtils.separatorsToSystem(uri);

//        String uriParts[] = uri.split(Defs.pathSeparator());
        String uriParts[] = uri.split("/");
        //System.err.println("00: SegIdleMonitorBean.prefixoPath()\nuri: " + uri);

        String path = "";
        int len = uriParts.length;
        if (len <= 3)
        {
            //System.err.println("01: SegIdleMonitorBean.prefixoPath()\nuri: " + uri);
            return "";
        }
        int i = 0;
        for (; i < len; i++)
        {
            //System.err.println("1: SegIdleMonitorBean.prefixoPath()\npart[" + i + "]: " + uriParts[i]);
        }

        for (i = 3; i < len; i++)
        {
            //System.err.println("2: SegIdleMonitorBean.prefixoPath()\npart[" + i + "]: " + uriParts[i]);
            path += ".." + Defs.pathSeparator();
        }
        len = path.length();
        if (len > 2)
        {
            path = path.substring(0, len - 1);
        }
        //System.err.println("3: SegIdleMonitorBean.prefixoPath()\tpath: " + path);
        return path;
    }

    // Business Methods
    public String urlLogo()
    {
        //System.err.println("0: SegIdleMonitorBean.urlLogo()\tpathLogo: " + prefixoPath() + "/resources/Imagens/EGTIlogo.png");
        return this.prefixoPath() + "/resources/Imagens/EGTIlogo.png";
    }

    private ExternalContext getExternalContext()
    {
        faceContext = FacesContext.getCurrentInstance();
        externalContext = faceContext.getExternalContext();
        sessao = (HttpSession) externalContext.getSession(false);
        return externalContext;
    }

    public void processTempoMaximoInactividadeTimeOut()
    {
        PrimeFaces.current().executeScript("PF('idle_session').pause()");
        //System.err.println("0: SegIdleMonitorBean.processTempoMaximoInactividadeTimeOut()\tcontaUtilizador: "
////            + (contaUtilizador == null ? "null" : contaUtilizador.getNomeUtilizador()));

        contaUtilizador = segContaFacade.find(contaUtilizador.getPkSegConta());
        //System.err.println("1: SegIdleMonitorBean.processTempoMaximoInactividadeTimeOut()\tcontaUtilizador: "
//            + (contaUtilizador == null ? "null" : contaUtilizador.getNomeUtilizador()));
        activarTempoMaximoInactividade = this.segLoginBean.getActivarTempoInactividade();
        //System.err.println("2: SegIdleMonitorBean.processTempoMaximoInactividadeTimeOut()\tactivarTempoMaximoInactividade: "
//            + activarTempoMaximoInactividade);
        if (this.activarTempoMaximoInactividade)
        {
            //System.err.println("3: SegIdleMonitorBean.processTempoMaximoInactividadeTimeOut()\tMaxInactiveInterval: "
//                + sessao.getMaxInactiveInterval());
            return;
        }
        //System.err.println("4: SegIdleMonitorBean.processTempoMaximoInactividadeTimeOut()\tcontaUtilizador: "
//            + (contaUtilizador == null ? "null" : contaUtilizador.getNomeUtilizador()));
        int maxInactiveInterval = sessao.getMaxInactiveInterval();
        int currentInactiveTime = (int) ((System.currentTimeMillis() - sessao.getLastAccessedTime()) / 1000);
//        sessao.setMaxInactiveInterval(maxInactiveInterval + currentInactiveTime);
        sessao.setMaxInactiveInterval(100 * 60);
        //System.err.println("5: SegIdleMonitorBean.processTempoMaximoInactividadeTimeOut()\tmaxInactiveInterval: "
//            + maxInactiveInterval + "\tcurrentInactiveTime: " + currentInactiveTime
//            + "\ntotalTempo: " + (maxInactiveInterval + currentInactiveTime));
        PrimeFaces.current().executeScript("PF('idle_session').resume()");
        tempoMaximoInactividadeTimeOut = 90 * 60 * 1000;
    }

    public void processUserTimeOut()
    {
        init();
        PrimeFaces.current().executeScript("PF('idle').pause()");
        String loginFile = "/login.xhtml";

        String loginPath = prefixoPath();
        loginFile = loginPath + loginFile;

        //System.err.println("0: SegIdleMonitorBean.processUserTimeOut()\nloginPath: " + loginFile);
        if (userTimeOut > 0)
        {
            this.segLoginBean.setUsername("");
            this.segLoginBean.setPassword("");
            //System.err.println("1: SegIdleMonitorBean.processUserTimeOut()\nloginPath: " + loginFile);
            PrimeFaces.current().executeScript("PF('w_form_login').show()");
        }
    }

    // Getters and Setters
    public int getUserTimeOut()
    {
        return userTimeOut;
    }

    public void setUserTimeOut(int userTimeOut)
    {
        this.userTimeOut = userTimeOut;
    }

    public int getTempoMaximoInactividadeTimeOut()
    {
        return tempoMaximoInactividadeTimeOut;
    }

    public void setTempoMaximoInactividadeTimeOut(int tempoMaximoInactividadeTimeOut)
    {
        this.tempoMaximoInactividadeTimeOut = tempoMaximoInactividadeTimeOut;
    }

    public boolean isActivarTempoMaximoInactividade()
    {
        return activarTempoMaximoInactividade;
    }

    public int getScreenW()
    {
        return screenW;
    }

    public int getScreenH()
    {
        return screenH;
    }

    public boolean isRenderIdleMonitor()
    {
        return renderIdleMonitor;
    }

}
