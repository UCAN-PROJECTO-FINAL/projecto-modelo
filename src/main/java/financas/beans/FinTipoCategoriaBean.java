/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package financas.beans;

import ejbs.cache.FinTipoCategoriaCache;
import ejbs.entities.FinTipoCategoria;
import java.io.Serializable;
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
@Named(value = "finTipoCategoriaBean")
@ViewScoped
public class FinTipoCategoriaBean implements Serializable {

    /**
     * Creates a new instance of FinTipoCategoria
     */
    @Inject
    private FinTipoCategoriaCache tipoCategoriaCache;
    
    private FinTipoCategoria tipoCategoriaEntidade, tipoCategoriaSelected;
    
    public FinTipoCategoriaBean() {
    }
    
    @PostConstruct
    public void init() {
        if (editarBanco()) {
            tipoCategoriaEntidade = new FinTipoCategoria();
        }
        
    }
    
    public void salvar() {
        if (!isEdit()) {
            try {
                tipoCategoriaEntidade.setPkTipoCategoria(tipoCategoriaCache.getFinTipoCategoriaID());
                tipoCategoriaCache.create(tipoCategoriaEntidade);
                Mensagem.sucessoMsg(null, "Dados Salvo");
                init();
                tipoCategoriaCache.init();
            } catch (Exception e) {
                Mensagem.enviarMensagemFatal(null, "Erro ao salvar dados, por favor contacte o administrador de sistemas !");
            }
        } else {
            try {
                //tipoCategoriaEntidade.setPkTipoCategoria(tipoCategoriaCache.getFinTipoCategoriaID());
                tipoCategoriaCache.edit(tipoCategoriaEntidade);
                Mensagem.sucessoMsg(null, "Dados Alterados");
                init();
                tipoCategoriaCache.init();
            } catch (Exception e) {
                Mensagem.enviarMensagemFatal(null, "Erro ao alterar dados, por favor contacte o administrador de sistemas !");
            }
        }
        
    }
    
    public boolean isEdit() {
        try {
            return tipoCategoriaEntidade.getPkTipoCategoria() != null;
        } catch (NullPointerException e) {
        }
        return false;
    }
    
    public boolean editarBanco() {
        boolean flag = true;
        try {
            tipoCategoriaEntidade = new FinTipoCategoria();
            Map<String, String> parametros = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
            int pkTipoCategoria = Integer.parseInt(parametros.get("pkTipoCategoria"));
            tipoCategoriaEntidade = tipoCategoriaCache.findFinTipoCategoria(pkTipoCategoria);
            //System.out.println("tipoCategoriaEntidade: "+tipoCategoriaEntidade.getDescricao());
            //pkTipoCartao = finTipoCartaoEntidade.getPkTipoCartao();
            flag = false;
        } catch (NumberFormatException | NullPointerException e) {
            System.out.println("Error: "+e.getMessage());
        }
        return flag;
    }
    
    public String eliminar() {
        try {
            tipoCategoriaCache.remove(tipoCategoriaSelected);
            tipoCategoriaCache.init();
            init();
        } catch (Exception e) {
        }
        return "fin_cadastro_tipo_categoria?faces-redirect=true";
    }

    public FinTipoCategoria getTipoCategoriaSelected() {
        return tipoCategoriaSelected;
    }

    public void setTipoCategoriaSelected(FinTipoCategoria tipoCategoriaSelected) {
        this.tipoCategoriaSelected = tipoCategoriaSelected;
    }
    
    public FinTipoCategoria getTipoCategoriaEntidade() {
        return tipoCategoriaEntidade;
    }
    
    public void setTipoCategoriaEntidade(FinTipoCategoria tipoCategoriaEntidade) {
        this.tipoCategoriaEntidade = tipoCategoriaEntidade;
    }
    
    public FinTipoCategoriaCache getTipoCategoriaCache() {
        return tipoCategoriaCache;
    }
    
    public void setTipoCategoriaCache(FinTipoCategoriaCache tipoCategoriaCache) {
        this.tipoCategoriaCache = tipoCategoriaCache;
    }
    
}
