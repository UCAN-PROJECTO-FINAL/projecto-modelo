/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package financas.beans;

import ejbs.cache.FinCategoriaCache;
import ejbs.cache.FinSubCategoriaCache;
import ejbs.cache.FinTipoCategoriaCache;
import ejbs.entities.FinCategoriaSubcategoria;
import ejbs.entities.FinCategorias;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import utils.mensagens.Mensagem;

/**
 *
 * @author majoao
 */
@Named(value = "finSubCategoriaBean")
@ViewScoped
public class FinSubCategoriaBean implements Serializable {

    /**
     * Creates a new instance of FinCategorias
     */
    @Inject
    private FinCategoriaCache CategoriaCache;
    @Inject
    private FinTipoCategoriaCache tipoCategoriaCache;
    @Inject
    private FinSubCategoriaCache finSubCategoriaCache;
    //Entidades
    private FinCategoriaSubcategoria subCategoriaEntidade, subCategoriaEntidadeSelect;
    //Listas
    private List<FinCategorias> listaCategoria;
    //Variaveis
    private String pkCategoria;

    public FinSubCategoriaBean() {
    }

    @PostConstruct
    public void init() {
        if (editarMoeda()) {
            subCategoriaEntidade = new FinCategoriaSubcategoria();
            pkCategoria = null;
        }

        listaCategoria = CategoriaCache.getListaFinCategoriass();

    }

    public void salvar() {
        if (!isEdit()) {
            try {
                subCategoriaEntidade.setPkCategoriaSubcategoria(finSubCategoriaCache.getFinCategoriaSubcategoriaID());
                subCategoriaEntidade.setFkCategoria(CategoriaCache.findFinCategorias(Integer.parseInt(pkCategoria)));
                finSubCategoriaCache.create(subCategoriaEntidade);
                Mensagem.sucessoMsg(null, "Dados Salvo");
                init();
                finSubCategoriaCache.init();
            } catch (Exception e) {
                Mensagem.enviarMensagemFatal(null, "Erro ao salvar dados, por favor contacte o administrador de sistemas !");
            }
        }else{
             try {
                //subCategoriaEntidade.setPkCategoriaSubcategoria(finSubCategoriaCache.getFinCategoriaSubcategoriaID());
                subCategoriaEntidade.setFkCategoria(CategoriaCache.findFinCategorias(Integer.parseInt(pkCategoria)));
                finSubCategoriaCache.edit(subCategoriaEntidade);
                Mensagem.sucessoMsg(null, "Dados Alterados");
                init();
                finSubCategoriaCache.init();
            } catch (Exception e) {
                Mensagem.enviarMensagemFatal(null, "Erro ao alterar dados, por favor contacte o administrador de sistemas !");
            }
        }

    }

    public boolean isEdit() {
        try {
            return subCategoriaEntidade.getPkCategoriaSubcategoria() != null;
        } catch (NullPointerException e) {
        }
        return false;
    }

    public String eliminar() {
        try {
            finSubCategoriaCache.remove(subCategoriaEntidadeSelect);
            finSubCategoriaCache.init();
        } catch (Exception e) {
        }
        return "fin_cadastro_sub_categoria?faces-redirect=true";
    }

    public boolean editarMoeda() {
        boolean flag = true;
        try {
            subCategoriaEntidade = new FinCategoriaSubcategoria();
            Map<String, String> parametros = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
            int PkCategoriaSubcategoria = Integer.parseInt(parametros.get("pkCategoriaSubcategoria"));
            subCategoriaEntidade = finSubCategoriaCache.findFinCategoriaSubcategoria(PkCategoriaSubcategoria);
            pkCategoria = subCategoriaEntidade.getFkCategoria().getPkCategoria().toString();
            flag = false;
        } catch (NumberFormatException | NullPointerException e) {
        }
        return flag;
    }

    public FinCategoriaSubcategoria getSubCategoriaEntidade() {
        return subCategoriaEntidade;
    }

    public void setSubCategoriaEntidade(FinCategoriaSubcategoria subCategoriaEntidade) {
        this.subCategoriaEntidade = subCategoriaEntidade;
    }

    public List<FinCategorias> getListaCategoria() {
        return listaCategoria;
    }

    public void setListaCategoria(List<FinCategorias> listaCategoria) {
        this.listaCategoria = listaCategoria;
    }

    public String getPkCategoria() {
        return pkCategoria;
    }

    public void setPkCategoria(String pkCategoria) {
        this.pkCategoria = pkCategoria;
    }

    public FinSubCategoriaCache getFinSubCategoriaCache() {
        return finSubCategoriaCache;
    }

    public void setFinSubCategoriaCache(FinSubCategoriaCache finSubCategoriaCache) {
        this.finSubCategoriaCache = finSubCategoriaCache;
    }

    public FinCategoriaSubcategoria getSubCategoriaEntidadeSelect() {
        return subCategoriaEntidadeSelect;
    }

    public void setSubCategoriaEntidadeSelect(FinCategoriaSubcategoria subCategoriaEntidadeSelect) {
        this.subCategoriaEntidadeSelect = subCategoriaEntidadeSelect;
    }

}
