/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seg.beans;


import ejbs.entities.SegConta;
import ejbs.facades.SegContaFacade;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import utils.Constantes;                  
import utils.mensagens.LogFile;

/**
 *
 * @author délcio benga
 */
@Named
@SessionScoped
public class SegcontroloSessaoBean implements Serializable
{

   @EJB
   private SegContaFacade segContaFacade;
   
   private SegConta conta;

   private FacesContext context = FacesContext.getCurrentInstance();
   private HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
   private HttpSession sessao = request.getSession();

   /**
    * Creates a new instance of SegcontroloSessaoBean
    */
   public SegcontroloSessaoBean()
   {
   }

   public static SegcontroloSessaoBean getInstanciaBean()
    {
        FacesContext context = FacesContext.getCurrentInstance();
        SegcontroloSessaoBean segcontroloSessaoBean
            = (SegcontroloSessaoBean) context.getELContext().
            getELResolver().getValue(FacesContext.getCurrentInstance().
                getELContext(), null, "segcontroloSessaoBean");

        return segcontroloSessaoBean;
    }
   
   public void validarSessao()
   {
      SegConta sessaoActual = (SegConta) sessao.getAttribute("sessaoActual");
      String urlSessaoExpiradaSeg = "http://" + getIpMaquinaServidor() + ":8080/PSGL_EGTI_NEW/faces/seg_acesso_login_expirado.xhtml?faces-redirect=true";
      if (sessaoActual == null || sessaoActual.getPkSegConta() == null)
      {
         try
         {          
            sessaoActual = segContaFacade.find(Constantes.idUltimaSessao);
            if(sessaoActual != null)
            {
               sessaoActual.setUltimoAcessoConta(new Date());
               segContaFacade.edit(sessaoActual);
            }            
            context.getExternalContext().redirect(urlSessaoExpiradaSeg);
         }
         catch (IOException ex)
         {
            LogFile.warnMsg(null, "Pagina de redireccionamento não encontrada");
         }
      }
      
   }

   public String getIpMaquinaServidor() 
   {
      context = FacesContext.getCurrentInstance();
        
      request = (HttpServletRequest) context.getExternalContext().getRequest();
        
      return request.getLocalAddr();
   }

   public SegConta getInstanciaConta()
   {
      conta = this.segContaFacade.getInstancia();
      return conta;
   }

   /**
    * @return the conta
    */
   public SegConta getConta()
   {
      if (conta.getPkSegConta() == null)
         conta = getInstanciaConta();
      return conta;
   }

   /**
    * @param conta the conta to set
    */
   public void setConta(SegConta conta)
   {
      this.conta = conta;
   }
}
