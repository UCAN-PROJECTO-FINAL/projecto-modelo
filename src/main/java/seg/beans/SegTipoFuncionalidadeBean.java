/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seg.beans;
import ejbs.entities.SegTipoFuncionalidade;
import ejbs.facades.SegTipoFuncionalidadeFacade;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import utils.GeradorCodigo;                       

/**
 *
 * @author Helena 
 */
@Named(value = "segTipoFuncionalidadeBean")
@ApplicationScoped
public class SegTipoFuncionalidadeBean implements Serializable
{

    @EJB
    private SegTipoFuncionalidadeFacade segTipoFuncionalidadeFacade;

    private List<SegTipoFuncionalidade> listSegTipoFuncionalidadeOrderByNome;

    /**
     * Creates a new instance of SegTipoFuncionalidadeBean
     */
    public SegTipoFuncionalidadeBean()
    {
    }
    
    @PostConstruct
    public void init()
    {
       listSegTipoFuncionalidadeOrderByNome = this.segTipoFuncionalidadeFacade.findAllOrderByNome();
       
    }

    public static SegTipoFuncionalidadeBean getInstanciaBean()
    {
        return (SegTipoFuncionalidadeBean) GeradorCodigo.getInstanciaBean("segTipoFuncionalidadeBean");
    }

    public boolean renderedTipoFuncionalidade()
    {
            return listSegTipoFuncionalidadeOrderByNome.size() != 0;
    }
	
    public List<SegTipoFuncionalidade> getListSegTipoFuncionalidadeOrderByNome()
    {
        return listSegTipoFuncionalidadeOrderByNome;
    }

}