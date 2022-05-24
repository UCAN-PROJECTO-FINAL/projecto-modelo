/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seg.beans;

import ejbs.entities.SegPerfil;
import ejbs.facades.SegPerfilFacade;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import utils.GeradorCodigo;                   

/**
 *
 * @author helena
 */
@Named(value = "segPerfilBean")
@ApplicationScoped
public class SegPerfilBean
{
    private List<SegPerfil> lista_perfil;
    private List<SegPerfil> listaPerfisPermetidosParaUsuario;
    
    @EJB
    private SegPerfilFacade segPerfilFacade;
    
    public SegPerfilBean()
    {
    }
    
    public static SegPerfilBean getInstanciaBean()
    {
        return (SegPerfilBean) GeradorCodigo.getInstanciaBean("segPerfilBean");
    }

    public static SegPerfil getInstancia()
    {
        SegPerfil segPerfil = new SegPerfil();
        return segPerfil;
    }
    
    @PostConstruct
    public void init()
    {
        lista_perfil = this.segPerfilFacade.findAllOrderByDescricao();
		
    }
	
    public boolean rendereListaPerfil()
    {
        return this.lista_perfil.size() != 0;
    }

    public List<SegPerfil> getListaPerfil()
    {
        return lista_perfil;
    }
    
    public void findPerfisByDescricao(String  str)
    {
        listaPerfisPermetidosParaUsuario = segPerfilFacade.findByNomeWithString(str);
    }

    public List<SegPerfil> getListaPerfisPermetidosParaUsuario()
    {
        return listaPerfisPermetidosParaUsuario;
    }

    public void setListaPerfisPermetidosParaUsuario(List<SegPerfil> listaPerfisPermetidosParaUsuario)
    {
        this.listaPerfisPermetidosParaUsuario = listaPerfisPermetidosParaUsuario;
    } 
    
}
