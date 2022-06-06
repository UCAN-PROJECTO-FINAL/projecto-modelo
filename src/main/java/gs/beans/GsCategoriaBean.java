/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gs.beans;

import ejbs.entities.GsCategoria;
import ejbs.entities.GsTipo;
import ejbs.facades.GsCategoriaFacade;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author jesus
 */
@Named(value = "gsCategoriaBean")
@ViewScoped
public class GsCategoriaBean implements Serializable {

    @EJB
    private GsCategoriaFacade gsCategoriaFacade;

    private String descricao;

    public GsCategoriaBean() {
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<GsCategoria> listarTodos() {
        return this.gsCategoriaFacade.findAllOrderByDescricao();

    }

}
