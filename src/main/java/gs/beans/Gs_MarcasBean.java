/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gs.beans;

import ejbs.entities.PtMarca;
import ejbs.facades.PtMarcaFacade;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import patri.excel.beans.PtMarcaExcelBean;

/**
 *
 * @author jesus
 */
@Named(value = "gs_MarcasBean")
@ViewScoped
public class Gs_MarcasBean implements Serializable
{

    @EJB
    private PtMarcaFacade ptMarcaFacade;

    public Gs_MarcasBean() {
    }
    
        public List<PtMarca> listartodos()
    {
    return ptMarcaFacade.findAll();
    } 
    
    public String CarregarMarcas()
    {
    PtMarcaExcelBean.getInstanciaBean().carregar();
    return "/modulos/gsVisao/backups/gs_dashboard?faces-redirect=true";
    }
    
}
