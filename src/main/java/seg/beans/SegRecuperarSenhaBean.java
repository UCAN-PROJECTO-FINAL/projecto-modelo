/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package seg.beans;
import ejbs.facades.SegContaFacade;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import org.omnifaces.cdi.ViewScoped;

/**
 *
 * @author helena
 */


@Named
@ViewScoped
public class SegRecuperarSenhaBean implements Serializable
{

    @EJB
    private SegContaFacade segContaFacade;
    
    private String userName;
    private int pergunta;
    private String resposta;
    private String senha;
    private String novaPergunta;
    private boolean rended, visible;
    
    private String key = "92AE31A79FEEB2A3";
    private String iv = "0123456789ABCDEF";
    
    private FacesContext context = FacesContext.getCurrentInstance();
    private FacesContext faceContext = FacesContext.getCurrentInstance();
    private ExternalContext externalContext = faceContext.getExternalContext();
     
    /**
     * Creates a new instance of SegRecuperarPasswordBean
     */
    
    public SegRecuperarSenhaBean()
    {
    }
    
    public String getSenha()
    {
        return senha;
    }

    public void setSenha(String senha)
    {
        this.senha = senha;
    }
    
    
    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public int getPergunta()
    {
        return pergunta;
    }

    public void setPergunta(int pergunta)
    {
        this.pergunta = pergunta;
    }

    public String getResposta()
    {
        return resposta;
    }

    public void setResposta(String resposta)
    {
        this.resposta = resposta;
    }
    
    public void preRenderView()
    {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
    }
     
//    private String recuperarSenha() throws Exception
//    {
//        for (SegConta conta : segContaFacade.findAll())
//            if (conta.getNomeUtilizador().equals(this.userName))
//            {
//                for (SegRecuperarSenha recupSenha : segRecuperarSenhaFacade.findAll())
//                    if (conta.getPkIdConta() == recupSenha.getFkConta().getPkIdConta())
//                    {
//                        if (recupSenha.getFkPergunta().getPkPergunta() == this.pergunta && 
//                            recupSenha.getResposta().equals(this.resposta))
//                        {
//                             LogFile.sucessoMsg("Senha Recuperada com Sucesso");
//                             return EncriptacaoDecriptacao.decrypt(key, iv, conta.getPalavraPasse());
//                        }
//                    }
//            }
//        return null;
//    }
    
    public void redirecionaTo(String page)
    {
        try
        {
            FacesContext.getCurrentInstance().getExternalContext().redirect(page);
        }
        catch (IOException ex)
        {
            Logger.getLogger(SegUtilizadorNovoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean findSenha() throws Exception
    {
//        for (SegConta conta : segContaFacade.findAll())
//            if (conta.getNomeUtilizador().equals(this.userName))
//            {
//                for (SegRecuperarSenha recupSenha : segRecuperarSenhaFacade.findAll())
////                    if (conta.getPkIdConta() == recupSenha.getFkConta().getPkIdConta())
//                    if (Objects.equals(conta.getPkIdConta(), recupSenha.getFkConta()))
//                    {
//                        if (recupSenha.getFkPergunta().getPkPergunta() == this.pergunta && 
//                            recupSenha.getResposta().equals(this.resposta))
//                        {
//                             setSenha(EncriptacaoDecriptacao.decrypt(key, iv, conta.getPalavraPasse()));
//                             redirecionaTo("../seg_novo_login.xhtml") ;
//                             return true;
//                        }
//                        else
//                        {
//                            LogFile.erroMsg("Resposta ou Pergunta  Errada");  
//                            return false;
//                        }
//                    }
//            }
//        LogFile.erroMsg("Nome do Utilizador Errado");  
        return false;
    }
}
