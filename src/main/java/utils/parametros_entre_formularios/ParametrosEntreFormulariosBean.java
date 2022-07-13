/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils.parametros_entre_formularios;

//import ejbs.entities.PrjProcessoCandidatura;
//import ejbs.entities.RhColaborador;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Timer;
import java.util.TimerTask;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import utils.Defs;
//import static utils.Defs.URL_REDIRECT;
import utils.GeradorCodigo;

// ver ListaChaveParametroTimers linha 74
/**
 *
 * @author viv
 */
@Named
@SessionScoped
public class ParametrosEntreFormulariosBean implements Serializable
{

    private HashTableParametro hashtable;

    private boolean hashtableCriated;

    public ParametrosEntreFormulariosBean()
    {
////System.err.println("0: ParametrosEntreFormulariosBean.ParametrosEntreFormulariosBean()");
        createHashtable();
    }

    @PostConstruct
    public void init()
    {
        hashtableCriated = false;
    }

    private void createHashtable()
    {
        if (hashtableCriated)
        {
            return;
        }
////System.err.println("0: ParametrosEntreFormulariosBean.createHashtable()");        
        hashtable = new HashTableParametro();
        hashtableCriated = true;
    }

    public Object getParametro(String key)
    {
        //System.err.println("0: ParametrosEntreFormulariosBean.getParametro()\thashtable: " + (hashtable == null ? "null" : "not null"));
        if (!this.hashtableCriated)
        {
            return null;
        }
        //System.err.println("1: ParametrosEntreFormulariosBean.getParametro()\thashtable: " + (hashtable == null ? "null" : "not null"));
//        return hashtable.getParametro(key);
        ListaChaveParametroTimers lcpt = hashtable.get(key);
//System.err.println("2: ParametrosEntreFormulariosBean.getParametro()\tlcpt: " + (lcpt == null ? "null" : "not null"));        
        return lcpt == null ? null : lcpt.getParametro();
    }

    public void setParametro(String key, Object parametro)
    {
        createHashtable();
////System.err.println("0: ParametrosEntreFormulariosBean.getParametro()\thashtable: " + (hashtable == null ? "null" : "not null"));
        hashtable.setParametro(key, parametro);

    }
   /* 
    public void setParametro(RhColaborador rhColaborador)
    {
System.err.println("0: RhColaboradoresListarBean.prepararColaboradorEditar()\trhColaborador: " + rhColaborador.getFkGrlPessoa().getNome());        
        setParametro("rhColaborador", rhColaborador);
    }*/
/*
    public void salvarPrjProcessoCandidatura(PrjProcessoCandidatura prjProcessoCandidatura)
    {
////System.err.println("0: ParametrosEntreFormulariosBean.salvarPrjProcessoCandidatura()");         
        setParametro("prjProcessoCandidaturaSelecionado", prjProcessoCandidatura);
    }
*/
    public Object removePrjProcessoCandidatura()
    {
        ListaChaveParametroTimers listaChaveParametroTimers = hashtable.get("prjProcessoCandidaturaSelecionado");
        if (listaChaveParametroTimers == null)
        {
            return null;
        }
        Object parametro = listaChaveParametroTimers.getParametro();
        hashtable.remove("prjProcessoCandidaturaSelecionado");
        return parametro;
    }

  /*  public PrjProcessoCandidatura recuperarPrjProcessoCandidatura()
    {
////System.err.println("0: ParametrosEntreFormulariosBean.recuperarPrjProcessoCandidatura()");        
        return (PrjProcessoCandidatura) this.getParametro("prjProcessoCandidaturaSelecionado");
    }
*/
    public void removeParametro(String key)
    {
        ListaChaveParametroTimers.TimerFormulario timerFormulario = hashtable.gerarTimerFormulario(key);
        timerFormulario.cancel();
    }

// para ser eliminado
    public Object extractParametro(String key)
    {
        ListaChaveParametroTimers listaChaveParametroTimers = hashtable.get(key);
        if (listaChaveParametroTimers == null)
        {
            return null;
        }
        Object parametro = listaChaveParametroTimers.getParametro();
        hashtable.remove(key);
        return parametro;
    }

    public void remove(String key)
    {
        hashtable.remove(key);
    }

    public String redirectForm(String urlCurrentForm, String urlNextForm)
    {
        this.setParametro(urlNextForm, urlCurrentForm);
        return urlNextForm; //+ URL_REDIRECT;
    }
    
    public String redirectBackForm(String urlCurrentForm)
    {
        return (String) this.getParametro(urlCurrentForm); //+ Defs.URL_REDIRECT;
    }

    public static ParametrosEntreFormulariosBean getInstanciaBean()
    {
        return (ParametrosEntreFormulariosBean) GeradorCodigo.getInstanciaBean("parametrosEntreFormulariosBean");
    }

    // HashTableParametro
    public class HashTableParametro extends Hashtable<String, ListaChaveParametroTimers>
    {

        public void setParametro(String key, Object parametro)
        {
            ListaChaveParametroTimers lista = get(key);
            if (lista == null)
            {
                this.put(key, new ListaChaveParametroTimers(key, parametro));
            }
            else
            {
                lista.setParametro(parametro);
            }
        }

        public Object getParametro(String key)
        {
            ListaChaveParametroTimers lista = get(key);
            return (lista == null || lista.isEmpty()) ? null : lista.getParametro();
        }

        public ListaChaveParametroTimers.TimerFormulario gerarTimerFormulario(String key)
        {
            ListaChaveParametroTimers lista = get(key);
            return (lista == null) ? null : lista.gerarTimerFormulario();
        }

    }

    // lista de Timers
    public class ListaChaveParametroTimers extends ArrayList
    {

        private static final long // periodo do timerParametro do formulario = 800 milisegundos
            DELAY_TIMER_FORMULARIO = 20000; // 20 seg

        private final String key;
        private Object parametro;

        public ListaChaveParametroTimers(String key, Object parametro)
        {
            this.key = key;
            this.parametro = parametro;
        }

        public TimerFormulario gerarTimerFormulario()
        {
            TimerFormulario timerFormulario = new TimerFormulario();
            add(timerFormulario);
            return timerFormulario;
        }

        public class TimerFormulario extends Timer
        {

            @Override
            public void cancel()
            {
                TimerTask task = new TimerTask()
                {
                    @Override
                    public void run()
                    {
                        removeTimerFormulario(TimerFormulario.this);
                    }
                };
                this.schedule(task, DELAY_TIMER_FORMULARIO);
            }
        }

        public void removeTimerFormulario(TimerFormulario timerFormulario)
        {
            int ntimers = this.size();
            TimerFormulario timer;
            for (int i = 0; i < ntimers; i++)
            {
                timer = (TimerFormulario) this.get(i);
                if (timer == timerFormulario)
                {
                    remove(i);
                    if (ntimers == 1)
                    {
//                        ParametrosEntreFormulariosBean.getInstanciaBean().extractParametro(key);
                        extractParametro(key);
                    }
                    return;
                }
            }
        }

        public Object getParametro()
        {
            return parametro;
        }

        public void setParametro(Object paramtro)
        {
            this.parametro = paramtro;
        }

    }

}
