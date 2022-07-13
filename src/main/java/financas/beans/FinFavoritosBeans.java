/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package financas.beans;

import ejbs.entities.FinFavoritos;
import ejbs.entities.FinTipoCategoria;
import ejbs.facades.FinFavoritosFacade;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author majoao
 */
@Named(value = "finFavoritosBeans")
@ViewScoped
public class FinFavoritosBeans implements Serializable {

    /**
     * Creates a new instance of FinFavoritosBeans
     */
    @EJB
    private FinFavoritosFacade finFavoritosFacade;

    private FinFavoritos finFavoritos;

    private List<FinFavoritos> listFinFavoritos;

    public FinFavoritosBeans() {
    }

    @PostConstruct
    public void init() {
        finFavoritos = new FinFavoritos();
        listFinFavoritos = finFavoritosFacade.findAllFavoritos();
    }

    public void salvar(String urlPagina, String descricao) {
        finFavoritos = verificarFavoritos(urlPagina);
        if (finFavoritos == null) {
            finFavoritos = new FinFavoritos();
            finFavoritos.setDescricao(descricao);
            finFavoritos.setUrlPagina(urlPagina);
            finFavoritos.setStatus(Boolean.TRUE);
            finFavoritos.setNumeroAcesso(1);
            finFavoritosFacade.create(finFavoritos);
        } else {
            //finFavoritos = new FinFavoritos();
            finFavoritos.setNumeroAcesso(finFavoritos.getNumeroAcesso() + 1);
            finFavoritosFacade.edit(finFavoritos);
        }
    }

    private FinFavoritos verificarFavoritos(String urlPagina) {
        if(finFavoritosFacade.findAllFavoritos() == null) return null;
        for (FinFavoritos listFinFavorito : finFavoritosFacade.findAllFavoritos()) {
            if (listFinFavorito.getUrlPagina().equalsIgnoreCase(urlPagina)) {
                return listFinFavorito;
            }
        }
        return null;
    }

    public FinFavoritos getFinFavoritos() {
        return finFavoritos;
    }

    public void setFinFavoritos(FinFavoritos finFavoritos) {
        this.finFavoritos = finFavoritos;
    }

    public List<FinFavoritos> getListFinFavoritos() {
        return listFinFavoritos;
    }

    public void setListFinFavoritos(List<FinFavoritos> listFinFavoritos) {
        this.listFinFavoritos = listFinFavoritos;
    }
    
    
}
