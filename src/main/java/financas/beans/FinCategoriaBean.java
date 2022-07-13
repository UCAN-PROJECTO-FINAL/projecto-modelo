/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package financas.beans;

import ejbs.cache.FinCategoriaCache;
import ejbs.cache.FinTipoCategoriaCache;
import ejbs.entities.FinCategorias;
import ejbs.entities.FinTipoCategoria;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import seg.beans.SegLoginBean;
import utils.mensagens.Mensagem;

/**
 *
 * @author majoao
 */
@Named(value = "finCategoriaBean")
@ViewScoped
public class FinCategoriaBean implements Serializable {

    /**
     * Creates a new instance of FinCategorias
     */
    @Inject
    private FinCategoriaCache CategoriaCache;
    @Inject
    private FinTipoCategoriaCache tipoCategoriaCache;

    //Entidades
    private FinCategorias categoriaEntidade, categoriaSelected;

    //Listas
    private List<FinTipoCategoria> listaTipoCategoria;
    //Variaveis
    private String pkTipoCategoria;
    @Inject
    SegLoginBean segLoginBean;

    public FinCategoriaBean() {
    }

    @PostConstruct
    public void init() {
        if (editarBanco()) {
            categoriaEntidade = new FinCategorias();
            pkTipoCategoria = null;
        }

        listaTipoCategoria = tipoCategoriaCache.getListaFinTipoCategorias();

    }

    public void salvar() {
        if (!isEdit()) {
            try {
                categoriaEntidade.setPkCategoria(CategoriaCache.getFinCategoriasID());
                categoriaEntidade.setFkUtilizador(segLoginBean.getContaUtilizador());
                categoriaEntidade.setFkTipoCategoria(tipoCategoriaCache.findFinTipoCategoria(Integer.parseInt(pkTipoCategoria)));
                CategoriaCache.create(categoriaEntidade);
                Mensagem.sucessoMsg(null, "Dados Salvo");
                init();
                CategoriaCache.init();
            } catch (Exception e) {
                Mensagem.enviarMensagemFatal(null, "Erro ao salvar dados, por favor contacte o administrador de sistemas !");
            }
        } else {
            try {
                categoriaEntidade.setFkTipoCategoria(tipoCategoriaCache.findFinTipoCategoria(Integer.parseInt(pkTipoCategoria)));
                CategoriaCache.edit(categoriaEntidade);
                Mensagem.sucessoMsg(null, "Dados Alterados");
                init();
                CategoriaCache.init();
            } catch (Exception e) {
                Mensagem.enviarMensagemFatal(null, "Erro ao alterar dados, por favor contacte o administrador de sistemas !");
            }
        }

    }

    public boolean isEdit() {
        try {
            return categoriaEntidade.getPkCategoria() != null;
        } catch (NullPointerException e) {
        }
        return false;
    }

    public boolean editarBanco() {
        boolean flag = true;
        try {
            categoriaEntidade = new FinCategorias();
            Map<String, String> parametros = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
            int pkCategoria = Integer.parseInt(parametros.get("pkCategoria"));
            categoriaEntidade = CategoriaCache.findFinCategorias(pkCategoria);
            pkTipoCategoria = categoriaEntidade.getFkTipoCategoria().getPkTipoCategoria().toString();
            flag = false;
        } catch (NumberFormatException | NullPointerException e) {
        }
        return flag;
    }

    public String eliminar() {
        try {
            CategoriaCache.remove(categoriaSelected);
            CategoriaCache.init();
            init();
        } catch (Exception e) {
        }
        return "fin_cadastro_categoria?faces-redirect=true";
    }

    public FinCategorias getCategoriaEntidade() {
        return categoriaEntidade;
    }

    public void setCategoriaEntidade(FinCategorias categoriaEntidade) {
        this.categoriaEntidade = categoriaEntidade;
    }

    public List<FinTipoCategoria> getListaTipoCategoria() {
        return listaTipoCategoria;
    }

    public void setListaTipoCategoria(List<FinTipoCategoria> listaTipoCategoria) {
        this.listaTipoCategoria = listaTipoCategoria;
    }

    public String getPkTipoCategoria() {
        return pkTipoCategoria;
    }

    public void setPkTipoCategoria(String pkTipoCategoria) {
        this.pkTipoCategoria = pkTipoCategoria;
    }

    public FinCategoriaCache getCategoriaCache() {
        return CategoriaCache;
    }

    public void setCategoriaCache(FinCategoriaCache CategoriaCache) {
        this.CategoriaCache = CategoriaCache;
    }

    public FinCategorias getCategoriaSelected() {
        return categoriaSelected;
    }

    public void setCategoriaSelected(FinCategorias categoriaSelected) {
        this.categoriaSelected = categoriaSelected;
    }

}
