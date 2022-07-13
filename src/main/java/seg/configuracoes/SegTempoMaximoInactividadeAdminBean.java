/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seg.configuracoes;

import ejbs.entities.SegConta;
import ejbs.entities.SegTempoInactividadeTodos;
import ejbs.facades.SegContaFacade;
import ejbs.facades.SegTempoInactividadeTodosFacade;
import java.io.Serializable;
import java.util.List;
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
@Named(value = "segTempoMaximoInactividadeAdminBean")
@ViewScoped
public class SegTempoMaximoInactividadeAdminBean implements Serializable
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

    private SegConta contaUtilizador;

    /**
     * Creates a new instance of SegTempoMaximoInactividadeAdminBean
     */
    private SegTempoInactividadeTodos segTempoInactividadeTodos;
    private List<SegTempoInactividadeTodos> segTempoInactividadeTodosList;

    private int opcao;

    private int tempo, maxIdleTime, prevMaxIdleTime;

    private Boolean activarTempoMaximoInactividade, prevActivarTempoMaximoInactividade;

    private boolean starting;

    public SegTempoMaximoInactividadeAdminBean()
    {
        starting = true;
    }

    @PostConstruct
    public void init()
    {
        System.err.println("0: SegTempoMaximoInactividadeAdmin.init()");
        sessao = segLoginBean.getSessao();
        System.err.println("1: SegTempoMaximoInactividadeAdmin.init()");

        contaUtilizador = segLoginBean.getContaUtilizador();
        System.err.println("2: SegTempoMaximoInactividadeAdmin.init()\tcontaUtilizador: "
            + (contaUtilizador == null ? "null" : contaUtilizador.getNomeUtilizador()));
        contaUtilizador = segContaFacade.find(contaUtilizador.getPkSegConta());

        System.err.println("3: SegTempoMaximoInactividadeAdmin.init()\tcontaUtilizador: "
            + (contaUtilizador == null ? "null" : contaUtilizador.getNomeUtilizador()));

        System.err.println("4: SegTempoMaximoInactividadeAdmin.init()");
        if (!starting)
        {
            prevActivarTempoMaximoInactividade = activarTempoMaximoInactividade;
        }
        activarTempoMaximoInactividade = this.segLoginBean.getActivarTempoInactividade();
        if (starting)
        {
            prevActivarTempoMaximoInactividade = activarTempoMaximoInactividade;
        }

        System.err.println("5: SegTempoMaximoInactividadeAdmin.init()\tactivarTempoMaximoInactividade: " + activarTempoMaximoInactividade);

        tempo = segLoginBean.getTempoInactividade() / 60;
        System.err.println("6: SegTempoMaximoInactividadeAdmin.init()\ttempo: " + tempo);

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

        System.err.println("7: SegTempoMaximoInactividadeAdmin.init()");

        maxIdleTime = this.segLoginBean.getMaxIdleTime() / (1000 * 60);

        this.segTempoInactividadeTodosList = this.segTempoInactividadeTodosFacade.findAll();
        System.err.println("8: SegTempoMaximoInactividadeAdmin.init()");
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

        segTempoInactividadeTodos = new SegTempoInactividadeTodos();
        if (!this.segTempoInactividadeTodosList.isEmpty())
        {
            segTempoInactividadeTodos = this.segTempoInactividadeTodosList.get(0);
            this.segTempoInactividadeTodos.setTempo(tempo * 60);
            this.segTempoInactividadeTodosFacade.edit(segTempoInactividadeTodos);
        }
        else
        {
            this.segTempoInactividadeTodos.setTempo(tempo * 60);
            this.segTempoInactividadeTodosFacade.create(segTempoInactividadeTodos);
        }

        contaUtilizador.setActivarTempoInactividade(activarTempoMaximoInactividade);
        contaUtilizador.setMaxIdleTime(maxIdleTime * 1000 * 60);
        System.err.println("3: SegTempoMaximoInactividadeClientBean.guardar()\ttempo: " + tempo);
        if (activarTempoMaximoInactividade)
        {
            System.err.println("4: SegTempoMaximoInactividadeClientBean.guardar()\ttempo: " + tempo);
            contaUtilizador.setTempoInactividade(tempo * 60);
            if (!prevActivarTempoMaximoInactividade)
            {
System.err.println("4.1: SegTempoMaximoInactividadeClientBean.guardar()\ttempo: " + tempo);
            contaUtilizador.setTempoInactividade(tempo * 60);                
                PrimeFaces.current().executeScript("PF('idle_session').resume()");
            }
        }

        if (!activarTempoMaximoInactividade && prevActivarTempoMaximoInactividade)
        {
System.err.println("5: SegTempoMaximoInactividadeAdmin.guardar()\tcontaUtilizador: "
            + this.segContaFacade.toString(contaUtilizador));            
            PrimeFaces.current().executeScript("PF('idle_session').pause()");
        }
        System.err.println("5.1: SegTempoMaximoInactividadeAdmin.guardar()\tcontaUtilizador: "
            + this.segContaFacade.toString(contaUtilizador));
        segContaFacade.edit(contaUtilizador);

        segLoginBean.setSessaoActual(contaUtilizador);
        segLoginBean.getSessao().setMaxInactiveInterval(tempo * 60);

        segIdleMonitorBean.init();

        LogFile.sucessoMsg(null, "Tempo máximo de inactividade actualizado!");
    }

    public void processarTempoMaximoInactividade()
    {
//        contaUtilizador.setActivarTempoInactividade(activarTempoMaximoInactividade);
    }

    // Getters and Setters
    public int getOpcao()
    {
        return opcao;
    }

    public void setOpcao(int opcao)
    {
        this.opcao = opcao;
    }

    public int getTempo()
    {
        return tempo;
    }

    public void setTempo(int tempo)
    {
        this.tempo = tempo;
    }

    public Boolean getActivarTempoMaximoInactividade()
    {
        return activarTempoMaximoInactividade;
    }

    public void setActivarTempoMaximoInactividade(Boolean activarTempoMaximoInactividade)
    {
        this.activarTempoMaximoInactividade = activarTempoMaximoInactividade;
    }

    public int getMaxIdleTime()
    {
        return maxIdleTime;
    }

    public void setMaxIdleTime(int maxIdleTime)
    {
        this.maxIdleTime = maxIdleTime;
    }

}
