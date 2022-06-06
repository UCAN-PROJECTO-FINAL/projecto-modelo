/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seg.beans;

import ejbs.entities.SegConta;
import ejbs.facades.SegPerfilHasFuncionalidadeFacade;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author adalberto
 */
public class SegControloDeAcesso implements Serializable
{

    @EJB
    private SegPerfilHasFuncionalidadeFacade perfilFuncionalidadeFacade;
    private SegConta contaUtilizador;
    

    public SegControloDeAcesso()
    {
    }

    public void preRenderView()
    {
//        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
    }

    /*verificar injeccoes via URL consoante as permissoes das regras nos submodulo*/
    public void injeccaoViaURL(String url)
    {
        //boolean temPermissao = regraPerfilFacade.temAcessoRegraPermissaoUtilizador(contaUtilizador.getFksegperf().getPksegperf(), permissao, regra);
        boolean temPermissao = perfilFuncionalidadeFacade.temAcessoPermissaoUtilizador(contaUtilizador.getFkSegPerfil().getPkSegPerfil(), url);
        if (temPermissao)
        {
            /* System.out.println("tem regra");*/
        }
        else
        {
            redirectedForPage("../loginAcessoNegadoSeg.xhtml");

        }
    }

    /*controlar permissões forçadas via URL*/
    public void injeccaoPermissaViaURL() throws IOException
    {

        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String ipAdress = request.getHeader("X-FORWARDED-FOR");
        
        if (ipAdress == null)
        {
            ipAdress = request.getRemoteAddr();
        }

        if (contaUtilizador.getPkSegConta() != null)
        {
            //gravar o registo de logs na base de dados
            /*Seglogacess logAcesso = new Seglogacess();
             logAcesso.setEventoseglogacess("Hacker");
             logAcesso.setRiscoseglogacess("Alta");
             logAcesso.setOperadorseglogacess(username);
             logAcesso.setTipousuarioseglogacess(contaUtilizador.getFksegtipoutil().getDescsegtipoutil());
             logAcesso.setDataregseglogacess(new Date());
             logAcesso.setIpseglogacess(getIpAdressClient());
             logAcesso.setResultseglogacess("Sucesso");
             logAcesso.setFksegcontutil(contaUtilizador);
             logAcesso.setDetalhesseglogacess("Forcar Permissao via URL negado, Nome do utilizador: " + contaUtilizador.getFksegtipoutil().getDescsegtipoutil() + ": " + contaUtilizador.getFkfuncionario().getFkpessoa().getNome() + " " + contaUtilizador.getFkfuncionario().getFkpessoa().getSobrenome());

             logAcessoFacade.create(logAcesso);
             */
        }
    }

    public void redirectedForPage(String page)
    {
        try
        {

            FacesContext.getCurrentInstance().getExternalContext().redirect(page);
        }
        catch (IOException ex)
        {
            java.util.logging.Logger.getLogger(SegLoginBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public SegConta getContaUtilizador()
    {
        return contaUtilizador;
    }

    public void setContaUtilizador(SegConta contaUtilizador)
    {
        this.contaUtilizador = contaUtilizador;
    }

}
