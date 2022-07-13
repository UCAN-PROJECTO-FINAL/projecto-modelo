/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seg.configuracoes;

import ejbs.entities.SegConta;
import ejbs.facades.SegContaFacade;
import ejbs.facades.SegTempoInactividadeTodosFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import org.omnifaces.cdi.ViewScoped;
import org.primefaces.PrimeFaces;
import seg.beans.SegIdleMonitorBean;
import seg.beans.SegLoginBean;
import utils.mensagens.LogFile;

/**
 *
 * @author helena
 */
@Named(value = "segTempoMaximoInactividadeClientBean")
@ViewScoped
public class SegTempoMaximoInactividadeClientBean implements Serializable
{

    @EJB
    private SegContaFacade segContaFacade;

    @EJB
    private SegTempoInactividadeTodosFacade segTempoInactividadeTodosFacade;

    @Inject
    private SegLoginBean segLoginBean;

    @Inject
    private SegIdleMonitorBean segIdleMonitorBean;

    private HttpSession sessao; //= request.getSession();
    /**
     * Creates a new instance of SegTempoMaximoInactividadeClientBean
     */

    private SegConta contaUtilizador;
    private int tempo, maxIdleTime, prevMaxIdleTime;

    private Boolean activarTempoMaximoInactividade, prevActivarTempoMaximoInactividade;

    private boolean starting;

    public SegTempoMaximoInactividadeClientBean()
    {
        starting = true;
    }

    @PostConstruct
    public void init()
    {
        System.err.println("0: SegTempoMaximoInactividadeClientBean.init()");
        sessao = segLoginBean.getSessao();
        System.err.println("1: SegTempoMaximoInactividadeClientBean.init()");

        contaUtilizador = segLoginBean.getContaUtilizador();
        System.err.println("2: SegTempoMaximoInactividadeClientBean.init()\tcontaUtilizador: "
            + (contaUtilizador == null ? "null" : contaUtilizador.getNomeUtilizador()));
        contaUtilizador = segContaFacade.find(contaUtilizador.getPkSegConta());

        System.err.println("3: SegTempoMaximoInactividadeClientBean.init()\tcontaUtilizador: "
            + (contaUtilizador == null ? "null" : contaUtilizador.getNomeUtilizador()));

        System.err.println("4: SegTempoMaximoInactividadeClientBean.init()");
        if (!starting)
        {
            prevActivarTempoMaximoInactividade = activarTempoMaximoInactividade;
        }
        activarTempoMaximoInactividade = this.segLoginBean.getActivarTempoInactividade();
        if (starting)
        {
            prevActivarTempoMaximoInactividade = activarTempoMaximoInactividade;
        }
        System.err.println("5: SegTempoMaximoInactividadeClientBean.init()");

        tempo = segLoginBean.getTempoInactividade() / 60;
        System.err.println("6: SegTempoMaximoInactividadeClientBean.init()");

        if (!starting)
        {
            prevMaxIdleTime = maxIdleTime;
        }

        maxIdleTime = this.segLoginBean.getMaxIdleTime() / (1000 * 60);

        if (starting)
        {
            prevMaxIdleTime = maxIdleTime;
        }

        starting = false;

        System.err.println("7: SegTempoMaximoInactividadeClientBean.init()");
    }

    public void guardar()
    {
        System.err.println("0: SegTempoMaximoInactividadeClientBean.guardar()");
        if (this.prevMaxIdleTime != 0 && this.maxIdleTime == 0)
        {
            System.err.println("1: SegTempoMaximoInactividadeClientBean.guardar()");
            PrimeFaces.current().executeScript("PF('idle').pause()");
        }
        else if (this.prevMaxIdleTime == 0 && this.maxIdleTime != 0)
        {
            System.err.println("2: SegTempoMaximoInactividadeClientBean.guardar()");
            PrimeFaces.current().executeScript("PF('idle').resume()");
        }

        if (activarTempoMaximoInactividade)
        {
            contaUtilizador.setTempoInactividade(tempo * 60);
        }
        if (activarTempoMaximoInactividade  && !prevActivarTempoMaximoInactividade)
        {
            PrimeFaces.current().executeScript("PF('idle_session').resume()");
        }
        else if (!activarTempoMaximoInactividade  && prevActivarTempoMaximoInactividade)
        {
            PrimeFaces.current().executeScript("PF('idle_session').pause()");
        }

        contaUtilizador.setMaxIdleTime(maxIdleTime * 1000 * 60);

        contaUtilizador.setActivarTempoInactividade(activarTempoMaximoInactividade);
        segContaFacade.edit(contaUtilizador);
        SegLoginBean.getInstanciaBean().setSessaoActual(contaUtilizador);
        SegLoginBean.getInstanciaBean().getSessao().setMaxInactiveInterval(tempo * 60);
        segIdleMonitorBean.init();
        LogFile.sucessoMsg(null, "Tempo máximo de inactividade actualizado!");
    }

    public void processarTempoMaximoInactividade()
    {
//        contaUtilizador.setActivarTempoInactividade(activarTempoMaximoInactividade);
    }

// Getters and Setters
    public int getTempo()
    {
        return tempo;
    }

    public void setTempo(int tempo)
    {
        this.tempo = tempo;
    }

    public int getMaxIdleTime()
    {
        return maxIdleTime;
    }

    public void setMaxIdleTime(int maxIdleTime)
    {
        this.maxIdleTime = maxIdleTime;
    }

    public Boolean getActivarTempoMaximoInactividade()
    {
        return activarTempoMaximoInactividade;
    }

    public void setActivarTempoMaximoInactividade(Boolean activarTempoMaximoInactividade)
    {
        this.activarTempoMaximoInactividade = activarTempoMaximoInactividade;
    }

   
}
