/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geral.beans;

//import ejbs.cache.ComumMoedaCache;
//import ejbs.entities.ComumMoeda;
import java.io.Serializable;
import java.util.Date;
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
@Named(value = "comumMoedaBean")
@ViewScoped
public class GrlMoedaBean implements Serializable {

//    /**
//     * Creates a new instance of GrlMoedaBean
//     */
//    private ComumMoeda moedaEntidade,
//
//    /**
//     * Creates a new instance of GrlMoedaBean
//     */
//    moedaSelected;
//    //Inject
//    @Inject
//    ComumMoedaCache moedaCache;
//    //variavel
//    private Date dataCadastro;
//    
//    public GrlMoedaBean() {
//    }
//    
//    @PostConstruct
//    public void init() {
//        if (editarMoeda()) {
//            moedaEntidade = new ComumMoeda();
//        }
//        dataCadastro = new Date();
//        
//    }
//    
//    public void salvar() {
//        moedaEntidade.setPkMoeda(moedaCache.getComumMoedaID());
//        moedaEntidade.setData(dataCadastro);
//        moedaCache.create(moedaEntidade);
//        Mensagem.sucessoMsg(null, "Dados Salvos");
//        init();
//        moedaCache.init();
//    }
//    
//    public String eliminar() {
//        try {
//            moedaCache.remove(moedaSelected);
//            moedaCache.init();
//        } catch (Exception e) {
//        }
//        return "comum_cadastro_moeda?faces-redirect=true";
//    }
//    
//    public boolean editarMoeda() {
//        boolean flag = true;
//        try {
//            moedaEntidade = new ComumMoeda();
//            Map<String, String> parametros = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
//            int pkMoeda = Integer.parseInt(parametros.get("pkMoeda"));
//            moedaEntidade = moedaCache.findComumMoeda(pkMoeda);
//            
//            flag = false;
//        } catch (NumberFormatException | NullPointerException e) {
//        }
//        return flag;
//    }
//    
//    public ComumMoeda getMoedaEntidade() {
//        return moedaEntidade;
//    }
//    
//    public void setMoedaEntidade(ComumMoeda moedaEntidade) {
//        this.moedaEntidade = moedaEntidade;
//    }
//    
//    public ComumMoedaCache getMoedaCache() {
//        return moedaCache;
//    }
//    
//    public void setMoedaCache(ComumMoedaCache moedaCache) {
//        this.moedaCache = moedaCache;
//    }
//    
//    public Date getDataCadastro() {
//        return dataCadastro;
//    }
//    
//    public void setDataCadastro(Date dataCadastro) {
//        this.dataCadastro = dataCadastro;
//    }
//    
//    public ComumMoeda getMoedaSelected() {
//        return moedaSelected;
//    }
//    
//    public void setMoedaSelected(ComumMoeda moedaSelected) {
//        this.moedaSelected = moedaSelected;
//    }
    
}
