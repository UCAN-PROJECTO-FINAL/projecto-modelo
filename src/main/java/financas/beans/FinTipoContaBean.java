/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package financas.beans;

import ejbs.cache.FinTipoContaCache;
import ejbs.entities.FinTipoConta;
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
@Named(value = "finTipoContaBean")
@ViewScoped
public class FinTipoContaBean implements Serializable{

    /**
     * Creates a new instance of FinTipoCategoria
     */
    @Inject
    private FinTipoContaCache tipoContaCache;

    private FinTipoConta finTipoContaEntidade, finTipoContaSelected;

    private List<FinTipoConta> listFinTipoConta;

    public FinTipoContaBean() {
    }

    @PostConstruct
    public void init() {
        try {
            if (editarBanco()) {
                finTipoContaEntidade = new FinTipoConta();
            }
        } catch (Exception e) {
        }
        
        listFinTipoConta = tipoContaCache.getListaFinTipoConta();
    }

    public void salvar() {
        if (!isEdit()) {
            try {
                finTipoContaEntidade.setPkTipoConta(tipoContaCache.getFinTipoContaID());
                tipoContaCache.create(finTipoContaEntidade);
                Mensagem.sucessoMsg(null, "Dados Salvo");
                init();
                tipoContaCache.init();
            } catch (Exception e) {
                Mensagem.enviarMensagemFatal(null, "Erro ao salvar dados, por favor contacte o administrador de sistemas !");
            }
        } else {
            try {
                //finTipoContaEntidade.setPkTipoCartao(tipoContaCache.getFinTipoContaID());
                tipoContaCache.edit(finTipoContaEntidade);
                Mensagem.sucessoMsg(null, "Dados Alterados");
                init();
                tipoContaCache.init();
            } catch (Exception e) {
                Mensagem.enviarMensagemFatal(null, "Erro ao Alterar dados, por favor contacte o administrador de sistemas !");
            }
        }

    }

    public boolean isEdit() {
        try {
            return finTipoContaEntidade.getPkTipoConta()!= null;
        } catch (NullPointerException e) {
        }
        return false;
    }

    public boolean editarBanco() {
        boolean flag = true;
        try {
            finTipoContaEntidade = new FinTipoConta();
            Map<String, String> parametros = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
            int pkTipoConta = Integer.parseInt(parametros.get("pkTipoConta"));
            finTipoContaEntidade = tipoContaCache.findFinTipoConta(pkTipoConta);
            //pkTipoCartao = finTipoContaEntidade.getPkTipoCartao();
            flag = false;
        } catch (NumberFormatException | NullPointerException e) {
        }
        return flag;
    }

    public String eliminar() {
        try {
            tipoContaCache.remove(finTipoContaSelected);
            tipoContaCache.init();
            init();
        } catch (Exception e) {
        }
        return "fin_cadastro_tipo_conta?faces-redirect=true";
    }

    public FinTipoContaCache getTipoContaCache() {
        return tipoContaCache;
    }

    public void setTipoContaCache(FinTipoContaCache tipoContaCache) {
        this.tipoContaCache = tipoContaCache;
    }

    public FinTipoConta getFinTipoContaEntidade() {
        return finTipoContaEntidade;
    }

    public void setFinTipoContaEntidade(FinTipoConta finTipoContaEntidade) {
        this.finTipoContaEntidade = finTipoContaEntidade;
    }

    public FinTipoConta getFinTipoContaSelected() {
        return finTipoContaSelected;
    }

    public void setFinTipoContaSelected(FinTipoConta finTipoContaSelected) {
        this.finTipoContaSelected = finTipoContaSelected;
    }

    public List<FinTipoConta> getListFinTipoConta() {
        return listFinTipoConta;
    }

    public void setListFinTipoConta(List<FinTipoConta> listFinTipoConta) {
        this.listFinTipoConta = listFinTipoConta;
    }

    
  
    

}
