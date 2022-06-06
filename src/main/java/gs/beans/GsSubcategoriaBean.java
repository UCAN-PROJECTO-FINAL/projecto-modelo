/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gs.beans;

import ejbs.entities.GsCategoria;
import ejbs.entities.GsSubcategoria;
import ejbs.facades.GsSubcategoriaFacade;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author jesus
 */
@Named(value = "gsSubcategoriaBean")
@ViewScoped
public class GsSubcategoriaBean implements Serializable {

    @EJB
    private GsSubcategoriaFacade gsSubcategoriaFacade;

    private String descricao;

    public GsSubcategoriaBean() {
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<GsSubcategoria> listarTodos()  {
        return this.gsSubcategoriaFacade.findAllOrderByDescricao();

    }

    public List<GsSubcategoria> listarSubcategoriaPorCategoria(String categoria) {
        return this.gsSubcategoriaFacade.findAllOrderBypkGsCategoria(categoria);

    }
}
