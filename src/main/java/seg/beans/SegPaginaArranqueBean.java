/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seg.beans;

import ejbs.entities.SegConta;
import ejbs.entities.SegPaginaArranque;
import ejbs.entities.SegPerfil;
import ejbs.facades.SegContaFacade;
import ejbs.facades.SegPaginaArranqueFacade;
import ejbs.facades.SegPerfilFacade;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import org.omnifaces.cdi.ViewScoped;
import org.primefaces.PrimeFaces;
import utils.Defs;

/**
 *
 * @author helena
 */
@Named(value = "segPaginaArranqueBean")
@ViewScoped
public class SegPaginaArranqueBean implements Serializable
{

    @EJB
    private SegContaFacade segContaFacade;
    @EJB
    private SegPerfilFacade segPerfilFacade;
    @EJB
    private SegPaginaArranqueFacade segPaginaArranqueFacade;
    @Inject
    private SegConfiguracoesBean segConfiguracoesBean;

    private SegConta conta;
    private SegPaginaArranque paginaArranque;
    private Boolean rendered = true;
    private SegPerfil perfil;
    private int pkPerfil;

    private List<SegPerfil> perfisComPaginasDeArranqueAEliminar;
    private List<SegConta> contasComPaginasDeArranqueAEliminar;

    private String listaPerfisContas;

    public SegPaginaArranqueBean()
    {

    }

    @PostConstruct
    public void init()
    {
        paginaArranque = new SegPaginaArranque();
        perfil = new SegPerfil();
        if (segConfiguracoesBean.getConfiguracao() != null)
        {
            pkPerfil = segConfiguracoesBean.getConfiguracao().getFkSegPerfil().getPkSegPerfil();
        }
    }

    public String pegarUrl(String urlCompleta)
    {
////System.err.println("0: SegPaginaArranqueBean.pegarUrl()\turlCompleta: " + urlCompleta);         
        if (urlCompleta.indexOf('S') == -1)
            return urlCompleta;
////System.err.println("1: SegPaginaArranqueBean.pegarUrl()\turlCompleta: " + urlCompleta);        
        int i = 0;
        while (urlCompleta.charAt(i) != 'S')
        {
            i = i + 1;
        }
////System.err.println("2: SegPaginaArranqueBean.pegarUrl()\turlCompleta: " + urlCompleta + "\ti: " + i);
        return urlCompleta.substring(i - 1, urlCompleta.length());
    }

    public void adicionarNaListaDePaginasDeArranque()
    {
        //System.err.println("0: SegPaginaArranqueBean.adicionarNaListaDePaginasDeArranque()");
        HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String url = pegarUrl(req.getRequestURL().toString());
        //System.err.println("1: SegPaginaArranqueBean.adicionarNaListaDePaginasDeArranque()\tturl: " + url);
        if (!Defs.PROJECT_NAME.equals(""))
        {
            if (url.startsWith(Defs.PROJECT_NAME))
            {
                //System.err.println("2: SegPaginaArranqueBean.adicionarNaListaDePaginasDeArranque()\tturl: " + url);
                url = url.substring(Defs.PROJECT_NAME.length());
                //System.err.println("3: SegPaginaArranqueBean.adicionarNaListaDePaginasDeArranque()\tturl: " + url);
            }
        }
        //System.err.println("4: SegPaginaArranqueBean.adicionarNaListaDePaginasDeArranque()\tturl: " + url);
        paginaArranque = new SegPaginaArranque();
        paginaArranque.setUrl(url);
        segPaginaArranqueFacade.create(paginaArranque);
        //System.err.println("5: SegPaginaArranqueBean.adicionarNaListaDePaginasDeArranque()\tturl: " + url);
    }

    public boolean usadaComoPaginaArranque(SegPaginaArranque paginaArranque)
    {
        // 8888888888888888888888888
        List<SegPerfil> perfis = this.segPerfilFacade.findByPageArranque(paginaArranque.getPkSegPaginaArranque());
        if (perfis != null && !perfis.isEmpty())
        {
            return true;
        }
        List<SegConta> contas = this.segContaFacade.findByPageArranque(paginaArranque.getPkSegPaginaArranque());
        return (contas != null && !contas.isEmpty());
    }

    public void removerNaListaDePaginasDeArranque()
    {
        //System.err.println("0: SegPaginaArranqueBean.removerNaListaDePaginasDeArranque()");
        /*
            se nao for usada como pagina de arranque
                    remover
            senao
                    listar perfis e contas q usan como pagina de arranque
                    se confirmada a eliminacao
                            retirar dos perfis e contas q usan como pagina de arranque
                            colocar nelas a pagina padrao (home page)
                            retirar pagina da lista de paginas de arranque
         */

        HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String url = pegarUrl(req.getRequestURL().toString());
        //System.err.println("1: SegPaginaArranqueBean.removerNaListaDePaginasDeArranque()\turl: " + url);
        if (!Defs.PROJECT_NAME.equals(""))
        {
            if (url.startsWith(Defs.PROJECT_NAME))
            {
                //System.err.println("2: SegPaginaArranqueBean.removerNaListaDePaginasDeArranque()\turl: " + url);
                url = url.substring(Defs.PROJECT_NAME.length());
                //System.err.println("3: SegPaginaArranqueBean.removerNaListaDePaginasDeArranque()\turl: " + url);
            }
        }
        //System.err.println("4: SegPaginaArranqueBean.removerNaListaDePaginasDeArranque()\turl: " + url);
        paginaArranque = segPaginaArranqueFacade.findPaginaArranqueByUrl(url);
        //System.err.println("5: SegPaginaArranqueBean.removerNaListaDePaginasDeArranque()\turl: " + url);
        if (!usadaComoPaginaArranque(paginaArranque))
        {
            segPaginaArranqueFacade.remove(paginaArranque);
        }
        else
        {
            perfisComPaginasDeArranqueAEliminar = this.segPerfilFacade.findByPageArranque(paginaArranque.getPkSegPaginaArranque());
            contasComPaginasDeArranqueAEliminar = this.segContaFacade.findByPageArranque(paginaArranque.getPkSegPaginaArranque());
            PrimeFaces current = PrimeFaces.current();
            current.executeScript("PF('paginaRemocaoDlg').show();");
        }
    }

    public void removerPaginaArranque(boolean resposta)
    {
        /*
                    retirar dos perfis e contas q usan como pagina de arranque
                    colocar nelas a pagina padrao (home page)
                    retirar pagina da lista de paginas de arranque
         */
        if (!resposta)
        {
            return;
        }
        this.segPerfilFacade.removerPaginaArraque(perfisComPaginasDeArranqueAEliminar);
        this.segContaFacade.removerPaginaArraque(contasComPaginasDeArranqueAEliminar);
        this.segPaginaArranqueFacade.remove(paginaArranque);
    }

    public boolean paginaCorrenteNaoEstaNaListaPaginasArranque()
    {
//System.err.println("0: SegPaginaArranqueBean.paginaCorrenteNaoEstaNaListaPaginasArranque()");        
        SegConta user = SegLoginBean.getInstanciaBean().getContaUtilizador();
//System.err.println("1: SegPaginaArranqueBean.paginaCorrenteNaoEstaNaListaPaginasArranque()\tuser: " +
//    (user == null ? "null" : user.getNomeUtilizador()));
        HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
//System.err.println("2: SegPaginaArranqueBean.paginaCorrenteNaoEstaNaListaPaginasArranque()\treq: " +
//    (req == null ? "null" : req.getRequestURL().toString()));         
        String url = pegarUrl(req.getRequestURL().toString());
//System.err.println("3: SegPaginaArranqueBean.paginaCorrenteNaoEstaNaListaPaginasArranque()\turl: " + url);
        if (!Defs.PROJECT_NAME.equals(""))
        {
//System.err.println("4: SegPaginaArranqueBean.paginaCorrenteNaoEstaNaListaPaginasArranque()\turl: " + url);            
            if (url.startsWith(Defs.PROJECT_NAME))
            {
                //System.err.println("5: SegPaginaArranqueBean.paginaCorrenteNaoEstaNaListaPaginasArranque()\turl: " + url);
                url = url.substring(Defs.PROJECT_NAME.length());
                //System.err.println("6: SegPaginaArranqueBean.paginaCorrenteNaoEstaNaListaPaginasArranque()\turl: " + url);
            }
        }
//System.err.println("7: SegPaginaArranqueBean.paginaCorrenteNaoEstaNaListaPaginasArranque()\turl: " + url);
        paginaArranque = segPaginaArranqueFacade.findPaginaArranqueByUrl(url);
        return (paginaArranque == null);
    }

    /**
     *
     * @param user
     * @return
     */
    public boolean isPaginaArranqueDoUsuario()
    {
        SegConta user = SegLoginBean.getInstanciaBean().getContaUtilizador();
        if (user.getFkSegPaginaArranque() != null)
        {
            HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            String url = pegarUrl(req.getRequestURL().toString());
            //System.err.println("0: SegPaginaArranqueBean.isPaginaArranqueDoUsuario()\turl: " + url);
            if (!Defs.PROJECT_NAME.equals(""))
            {
                if (url.startsWith(Defs.PROJECT_NAME))
                {
                    //System.err.println("1: SegPaginaArranqueBean.isPaginaArranqueDoUsuario()\turl: " + url);
                    url = url.substring(Defs.PROJECT_NAME.length());
                    //System.err.println("2: SegPaginaArranqueBean.isPaginaArranqueDoUsuario()\turl: " + url);
                }
            }
            //System.err.println("3: SegPaginaArranqueBean.isPaginaArranqueDoUsuario()\turl: " + url);
            return (user.getFkSegPaginaArranque().getUrl().equalsIgnoreCase(url));
        }
        return false;
    }

    /**
     *
     * @param user
     * @return
     */
    public boolean isPossivelPaginaArranqueAndNaoDefinido()
    {
        SegConta user = SegLoginBean.getInstanciaBean().getContaUtilizador();
        HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String url = pegarUrl(req.getRequestURL().toString());
        if (!Defs.PROJECT_NAME.equals(""))
        {
            if (url.startsWith(Defs.PROJECT_NAME))
            {
                url = url.substring(Defs.PROJECT_NAME.length());
            }
        }
        paginaArranque = segPaginaArranqueFacade.findPaginaArranqueByUrl(url);
        if (paginaArranque != null)
        {
            if (user.getFkSegPaginaArranque() != null)
            {
                return (!user.getFkSegPaginaArranque().getUrl().equalsIgnoreCase(url));
            }
            return true;
        }
        return false;
    }

    public boolean notIsInListaAndIsNoRoot()
    {
        SegConta user = SegLoginBean.getInstanciaBean().getContaUtilizador();
        HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String url = pegarUrl(req.getRequestURL().toString());
        paginaArranque = segPaginaArranqueFacade.findPaginaArranqueByUrl(url);
System.err.println("0: SegPaginaArranqueBean.notIsInListaAndIsNoRoot()");        
        return !(paginaArranque == null && this.segContaFacade.isRootAccount(user));
    }

    public boolean definirParaPerfil()
    {
        SegConta user = SegLoginBean.getInstanciaBean().getContaUtilizador();
        HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String url = pegarUrl(req.getRequestURL().toString());
        //System.err.println("0: SegPaginaArranqueBean.definirParaPerfil()\turl: " + url);
        if (!Defs.PROJECT_NAME.equals(""))
        {
            if (url.startsWith(Defs.PROJECT_NAME))
            {
                //System.err.println("1: SegPaginaArranqueBean.definirParaPerfil()\turl: " + url);
                url = url.substring(Defs.PROJECT_NAME.length());
                //System.err.println("2: SegPaginaArranqueBean.definirParaPerfil()\turl: " + url);
            }
        }
        paginaArranque = segPaginaArranqueFacade.findPaginaArranqueByUrl(url);
System.err.println("0: SegPaginaArranqueBean.definirParaPerfil()");        
        if (paginaArranque != null)
        {
System.err.println("1: SegPaginaArranqueBean.definirParaPerfil()");            
            return (this.segContaFacade.isRootAccount(user));
        }
System.err.println("2: SegPaginaArranqueBean.definirParaPerfil()");        
        return false;
    }

    /**
     *
     * @param user
     */
    public void removerPaginaDeArranqueDaContaCorrente()
    {
        SegConta user = SegLoginBean.getInstanciaBean().getContaUtilizador();
        user.setFkSegPaginaArranque(null);
        segContaFacade.edit(user);
    }

    /**
     *
     * @param user
     */
    public void definirPaginaArraqueParaContaCorrente()
    {
        SegConta user = SegLoginBean.getInstanciaBean().getContaUtilizador();
        HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String url = pegarUrl(req.getRequestURL().toString());
        //System.err.println("0: SegPaginaArranqueBean.definirPaginaArraqueParaContaCorrente()\turl: " + url);
        if (!Defs.PROJECT_NAME.equals(""))
        {
            if (url.startsWith(Defs.PROJECT_NAME))
            {
                //System.err.println("1: SegPaginaArranqueBean.definirPaginaArraqueParaContaCorrente()\turl: " + url);
                url = url.substring(Defs.PROJECT_NAME.length());
                //System.err.println("2: SegPaginaArranqueBean.definirPaginaArraqueParaContaCorrente()\turl: " + url);
            }
        }
        paginaArranque = segPaginaArranqueFacade.findPaginaArranqueByUrl(url);
        user.setFkSegPaginaArranque(paginaArranque);
        segContaFacade.edit(user);
    }

    /**
     *
     * @param pkPerfil
     */
    public void associarPaginaArranquePerfil(int pkPerfil)
    {
        HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String url = pegarUrl(req.getRequestURL().toString());
        if (!Defs.PROJECT_NAME.equals(""))
        {
            if (url.startsWith(Defs.PROJECT_NAME))
            {
                url = url.substring(Defs.PROJECT_NAME.length());
            }
        }
        paginaArranque = segPaginaArranqueFacade.findPaginaArranqueByUrl(url);
        SegPerfil perfilEditar = segPerfilFacade.find(pkPerfil);
        perfilEditar.setFkSegPaginaArranque(paginaArranque);
        segPerfilFacade.edit(perfilEditar);
    }

    public String getUrlCorrectPage()
    {
        HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String url = pegarUrl(req.getRequestURL().toString());
        return url;
    }

    public SegPerfil getPerfil()
    {
        return perfil;
    }

    public void setPerfil(SegPerfil perfil)
    {
        this.perfil = perfil;
    }

    public int getPkPerfil()
    {
        return pkPerfil;
    }

    public void setPkPerfil(int pkPerfil)
    {
        this.pkPerfil = pkPerfil;
    }

    public String getListaPerfisContas()
    {
        return listaPerfisContas;
    }
}
