/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package financas.beans;

import ejbs.cache.FinTipoCartaoCache;
import ejbs.entities.FinTipoCartao;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import utils.mensagens.Mensagem;

/**
 *
 * @author majoao
 */
@Named(value = "finTipoCartaoBean")
@ViewScoped
public class FinTipoCartaoBean implements Serializable{

    /**
     * Creates a new instance of FinTipoCategoria
     */
    @Inject
    private FinTipoCartaoCache tipoCartaoCache;

    private FinTipoCartao finTipoCartaoEntidade, finTipoCartaoSelected;

    private List<FinTipoCartao> listFinTipoCartao;

    public FinTipoCartaoBean() {
    }

    @PostConstruct
    public void init() {
        try {
            if (editarBanco()) {
                finTipoCartaoEntidade = new FinTipoCartao();
            }
        } catch (Exception e) {
        }
        
        listFinTipoCartao = tipoCartaoCache.getListaFinTipoCartao();
    }

    public void salvar() {
        if (!isEdit()) {
            try {
                finTipoCartaoEntidade.setPkTipoCartao(tipoCartaoCache.getFinTipoCartaoID());
                tipoCartaoCache.create(finTipoCartaoEntidade);
                Mensagem.sucessoMsg(null, "Dados Salvo");
                init();
                tipoCartaoCache.init();
            } catch (Exception e) {
                Mensagem.enviarMensagemFatal(null, "Erro ao salvar dados, por favor contacte o administrador de sistemas !");
            }
        } else {
            try {
                //finTipoCartaoEntidade.setPkTipoCartao(tipoCartaoCache.getFinTipoCartaoID());
                tipoCartaoCache.edit(finTipoCartaoEntidade);
                Mensagem.sucessoMsg(null, "Dados Alterados");
                init();
                tipoCartaoCache.init();
            } catch (Exception e) {
                Mensagem.enviarMensagemFatal(null, "Erro ao Alterar dados, por favor contacte o administrador de sistemas !");
            }
        }

    }

    public boolean isEdit() {
        try {
            return finTipoCartaoEntidade.getPkTipoCartao() != null;
        } catch (NullPointerException e) {
        }
        return false;
    }

    public boolean editarBanco() {
        boolean flag = true;
        try {
            finTipoCartaoEntidade = new FinTipoCartao();
            Map<String, String> parametros = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
            int pkTipoCartao = Integer.parseInt(parametros.get("pkTipoCartao"));
            finTipoCartaoEntidade = tipoCartaoCache.findFinTipoCartao(pkTipoCartao);
            //pkTipoCartao = finTipoCartaoEntidade.getPkTipoCartao();
            flag = false;
        } catch (NumberFormatException | NullPointerException e) {
        }
        return flag;
    }

    public String eliminar() {
        try {
            tipoCartaoCache.remove(finTipoCartaoSelected);
            tipoCartaoCache.init();
            init();
        } catch (Exception e) {
        }
        return "fin_cadastro_tipo_cartao?faces-redirect=true";
    }

    public FinTipoCartao getFinTipoCartaoEntidade() {
        return finTipoCartaoEntidade;
    }

    public void setFinTipoCartaoEntidade(FinTipoCartao finTipoCartaoEntidade) {
        this.finTipoCartaoEntidade = finTipoCartaoEntidade;
    }

    public List<FinTipoCartao> getListFinTipoCartao() {
        return listFinTipoCartao;
    }

    public void setListFinTipoCartao(List<FinTipoCartao> listFinTipoCartao) {
        this.listFinTipoCartao = listFinTipoCartao;
    }

    public FinTipoCartao getFinTipoCartaoSelected() {
        return finTipoCartaoSelected;
    }

    public void setFinTipoCartaoSelected(FinTipoCartao finTipoCartaoSelected) {
        this.finTipoCartaoSelected = finTipoCartaoSelected;
    }

}
