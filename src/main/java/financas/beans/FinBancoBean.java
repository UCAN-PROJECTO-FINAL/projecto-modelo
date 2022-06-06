/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package financas.beans;

import ejbs.cache.FinBancoCache;
import ejbs.entities.FinBanco;
import java.io.Serializable;
import java.util.Date;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import seg.beans.SegLoginBean;
import utils.mensagens.Mensagem;

/**
 *
 * @author majoao
 */
@Named(value = "finBancoBean")
@ViewScoped
public class FinBancoBean implements Serializable {

    /**
     * Creates a new instance of FinBanco
     */
    @Inject
    private FinBancoCache bancoCahe;

    private FinBanco bancoEntidade, bancoSelected;
    private Date dataEmissao;
    @Inject
    SegLoginBean segLoginBean;
    
    public FinBancoBean() {
    }

    @PostConstruct
    public void init() {
        if (editarBanco()) {
            bancoEntidade = new FinBanco();
        }
        dataEmissao = new Date();
    }

    public void salvar() {
        if (!isEdit()) {
            try {
                bancoEntidade.setPkBanco(bancoCahe.getFinBancoID());
                bancoEntidade.setDataCadastro(dataEmissao);
                bancoEntidade.setFkUtilizador(segLoginBean.getContaUtilizador());
                bancoCahe.create(bancoEntidade);
                
                Mensagem.sucessoMsg(null, "Dados Salvo");
                init();
                bancoCahe.init();
            } catch (Exception e) {
                Mensagem.enviarMensagemFatal(null, "Erro ao salvar dados, por favor contacte o administrador de sistemas !");
            }
        }else{
            try {
                bancoCahe.edit(bancoEntidade);
                Mensagem.sucessoMsg(null, "Dados Alterados");
                init();
                bancoCahe.init();
            } catch (Exception e) {
                Mensagem.enviarMensagemFatal(null, "Erro ao alterar dados, por favor contacte o administrador de sistemas !");
            }
        }

    }

    public boolean isEdit() {
        try {
            return bancoEntidade.getPkBanco() != null;
        } catch (NullPointerException e) {
        }
        return false;
    }

    public boolean editarBanco() {
        boolean flag = true;
        try {
            bancoEntidade = new FinBanco();
            Map<String, String> parametros = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
            int pkBanco = Integer.parseInt(parametros.get("pkBanco"));
            bancoEntidade = bancoCahe.findFinBanco(pkBanco);

            flag = false;
        } catch (NumberFormatException | NullPointerException e) {
        }
        return flag;
    }

    public String eliminar() {
        try {
            bancoCahe.remove(bancoSelected);
            bancoCahe.init();
            init();
        } catch (Exception e) {
        }
        return "fin_cadastro_banco?faces-redirect=true";
    }

    public FinBanco getBancoEntidade() {
        return bancoEntidade;
    }

    public void setBancoEntidade(FinBanco bancoEntidade) {
        this.bancoEntidade = bancoEntidade;
    }

    public FinBancoCache getBancoCahe() {
        return bancoCahe;
    }

    public void setBancoCahe(FinBancoCache bancoCahe) {
        this.bancoCahe = bancoCahe;
    }

    public FinBanco getBancoSelected() {
        return bancoSelected;
    }

    public void setBancoSelected(FinBanco bancoSelected) {
        this.bancoSelected = bancoSelected;
    }

}
