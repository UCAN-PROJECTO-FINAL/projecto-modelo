/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package financas.beans;

//import ejbs.cache.ComumMoedaCache;
//import ejbs.entities.ComumMoeda;
import ejbs.cache.FinMoedaCache;
import ejbs.entities.FinMoeda;
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
@Named(value = "finMoedaBean")
@ViewScoped
public class FinMoedaBean implements Serializable {

    /**
     * Creates a new instance of FinMoedaBean
     */
    private FinMoeda moedaEntidade,

    /**
     * Creates a new instance of GrlMoedaBean
     */

    /**
     * Creates a new instance of FinMoedaBean
     */
    moedaSelected;
    //Inject
    @Inject
    FinMoedaCache moedaCache;
    //variavel
    private Date dataCadastro;
    
    public FinMoedaBean() {
    }
    
    @PostConstruct
    public void init() {
        if (editarMoeda()) {
            moedaEntidade = new FinMoeda();
        }
        dataCadastro = new Date();
        
    }
    
    public void salvar() {
        moedaEntidade.setPkMoeda(moedaCache.getFinMoedaID());
        moedaEntidade.setData(dataCadastro);
        moedaCache.create(moedaEntidade);
        Mensagem.sucessoMsg(null, "Dados Salvos");
        init();
        moedaCache.init();
    }
    
    public String eliminar() {
        try {
            moedaCache.remove(moedaSelected);
            moedaCache.init();
        } catch (Exception e) {
        }
        return "comum_cadastro_moeda?faces-redirect=true";
    }
    
    public boolean editarMoeda() {
        boolean flag = true;
        try {
            moedaEntidade = new FinMoeda();
            Map<String, String> parametros = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
            int pkMoeda = Integer.parseInt(parametros.get("pkMoeda"));
            moedaEntidade = moedaCache.findFinMoeda(pkMoeda);
            
            flag = false;
        } catch (NumberFormatException | NullPointerException e) {
        }
        return flag;
    }
    
    public FinMoeda getMoedaEntidade() {
        return moedaEntidade;
    }
    
    public void setMoedaEntidade(FinMoeda moedaEntidade) {
        this.moedaEntidade = moedaEntidade;
    }
    
    public FinMoedaCache getMoedaCache() {
        return moedaCache;
    }
    
    public void setMoedaCache(FinMoedaCache moedaCache) {
        this.moedaCache = moedaCache;
    }
    
    public Date getDataCadastro() {
        return dataCadastro;
    }
    
    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
    
    public FinMoeda getMoedaSelected() {
        return moedaSelected;
    }
    
    public void setMoedaSelected(FinMoeda moedaSelected) {
        this.moedaSelected = moedaSelected;
    }
    
}
