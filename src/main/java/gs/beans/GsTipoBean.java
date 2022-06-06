/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gs.beans;

import ejbs.entities.GsTipo;
import ejbs.facades.GsTipoFacade;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author jesus
 */
@Named(value = "gsTipoBean")
@ViewScoped
public class GsTipoBean implements Serializable {

    @EJB
    private GsTipoFacade gsTipoFacade;

    private String descricao;

    public GsTipoBean() {

    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<GsTipo> listarTodos() {
        return this.gsTipoFacade.findAllOrderByDescricao();

    }

}
