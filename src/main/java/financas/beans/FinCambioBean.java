/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package financas.beans;

import ejbs.cache.FinCambioCache;
import ejbs.cache.FinMoedaCache;
import ejbs.entities.FinCambio;
import java.io.Serializable;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import utils.mensagens.Mensagem;

/**
 *
 * @author majoao
 */
@Named(value = "finCambioBean")
@ViewScoped
public class FinCambioBean implements Serializable {

    /**
     * Creates a new instance of FinCambioBean
     */
    //Entidade
    private FinCambio cambioEntidade;
    //Inject
    @Inject
    FinCambioCache cambioCache;
    @Inject
    FinMoedaCache moedaCache;
    //Varivais
    private Date dataCadastro;
    private String pkMoeda = null;
    
    public FinCambioBean() {
    }

    @PostConstruct
    public void init() {
        dataCadastro = new Date();
        cambioEntidade = new  FinCambio();
        pkMoeda = null;
    }
    
    public void salvar(){
        if (pkMoeda != null) {
            cambioEntidade.setPkCambio(cambioCache.getFinCambioID());
            cambioEntidade.setData(dataCadastro);
            cambioEntidade.setFkMoeda(moedaCache.findFinMoeda(Integer.parseInt(pkMoeda)));
            cambioCache.create(cambioEntidade);
            Mensagem.sucessoMsg(null, "Dados Salvos");
            init();
            cambioCache.init();
        }else{
            Mensagem.warnMsg(null,"Informe a moeda");
        }
    }

    public FinCambio getCambioEntidade() {
        return cambioEntidade;
    }

    public void setCambioEntidade(FinCambio cambioEntidade) {
        this.cambioEntidade = cambioEntidade;
    }

    public FinCambioCache getCambioCache() {
        return cambioCache;
    }

    public void setCambioCache(FinCambioCache cambioCache) {
        this.cambioCache = cambioCache;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public String getPkMoeda() {
        return pkMoeda;
    }

    public void setPkMoeda(String pkMoeda) {
        this.pkMoeda = pkMoeda;
    }
    
    
}
