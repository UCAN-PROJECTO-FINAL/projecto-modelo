/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package seg.utils;

import ejbs.entities.SegConta;
import java.io.IOException;
import java.util.logging.Level;
import javax.faces.context.FacesContext;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import seg.beans.SegLoginBean;


/**
 *
 * @author as
 * @author H. Jorge
 */
@WebFilter(filterName = "SessaoFilter", urlPatterns =
{
    "/seg_sessao_expirada.xhtml"
})
public class SessionFilter implements Filter
{

    @Override
    public void init(FilterConfig filterConfig) throws ServletException
    {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
    {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        SegConta contaDoUtilizador = (SegConta) req.getSession().getAttribute("contaUtilizador");
        if (contaDoUtilizador == null)
        {
            //retornar se não existir
            res.sendRedirect(req.getContextPath() + "/seg_acesso_login_expirado.xhtml?s=fail");
        } 
        else
        {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy()
    {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void redirectedForPage(String page) throws IllegalStateException
	{
        try
        {
            FacesContext.getCurrentInstance().getExternalContext().redirect(page);
        }
        catch (IOException e)
        {
            java.util.logging.Logger.getLogger(SessionFilter.class
                        .getName()).log(Level.SEVERE, null, e);
        }
	}


}
