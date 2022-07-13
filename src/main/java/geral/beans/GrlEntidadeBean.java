/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geral.beans;

//import ejbs.cache.LocLocalidadeCache;
import ejbs.entities.GrlEntidade;
//import ejbs.entities.GrlTipoEntidade;
import ejbs.entities.GrlEndereco;
import ejbs.entities.GrlTipoEntidade;
import ejbs.facades.GrlEntidadeFacade;
import ejbs.facades.GrlTipoEntidadeFacade;
//import ejbs.facades.GrlTipoEntidadeFacade;
//import ejbs.facades.GrlTipoEntidadeFacade;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import utils.mensagens.Mensagem;

/**
 *
 * @author majoao
 */
@Named(value = "grlEntidadeBean")
@ViewScoped
public class GrlEntidadeBean implements Serializable {

    /**
     * Creates a new instance of GrlEntidadeBean
     */
    @EJB
    private GrlEntidadeFacade entidadeFacade;
    @EJB
    private GrlTipoEntidadeFacade tipoEntidadeFacade;
    
    @Inject
    private GrlEnderecoBean comumEnderecoBean;
    
    private GrlEntidade grlEntidade;
    private GrlTipoEntidade comumTipoEntidade;
    private List<GrlTipoEntidade> listEntidade;
    private List<GrlEntidade> listGrlEntidade;
    
    private Integer tipoEntidade;
    private boolean useRubrica;
    private String numeroRubrica;
    
    public GrlEntidadeBean() {
    }
    
    @PostConstruct
    public void init() {
        grlEntidade = new GrlEntidade();
        listEntidade = tipoEntidadeFacade.findAll();
        comumTipoEntidade = new GrlTipoEntidade();
        listGrlEntidade = entidadeFacade.findAll();
        tipoEntidade = null;
    }
    
    public void salvar() {
        try {
            GrlEndereco grlEndereco = comumEnderecoBean.salvaEGetEndereco();
            if (grlEndereco != null) {
                comumTipoEntidade = tipoEntidadeFacade.find(tipoEntidade);
                //grlEntidade.setPkEntidade(entidadeFacade.count() + 1);
                grlEntidade.setFkTipoEntidade(comumTipoEntidade);
                grlEntidade.setEstado(Boolean.TRUE);
                grlEntidade.setFkEndereco(grlEndereco);
                entidadeFacade.create(grlEntidade);
                Mensagem.sucessoMsg(null, "Dados Salvo");
                init();
                comumEnderecoBean.init();
            } else {
                Mensagem.warnMsg(null, "Erro ao salvar dados, por favor contacte o administrador de sistemas !");
            }
            
        } catch (Exception e) {
            Mensagem.enviarMensagemFatal(null, "Erro ao salvar dados, por favor contacte o administrador de sistemas !");
        }
        
    }
    
    private boolean verificarNif(String nif) {
        for (GrlEntidade GrlEntidade1 : listGrlEntidade) {
            return GrlEntidade1.getNifEntidade().equalsIgnoreCase(nif);
        }
        return true;
    }

    public GrlEntidade getGrlEntidade() {
        return grlEntidade;
    }
    
    public void setGrlEntidade(GrlEntidade grlEntidade) {
        this.grlEntidade = grlEntidade;
    }
    
    public List<GrlTipoEntidade> getListEntidade() {
        return listEntidade;
    }
    
    public void setListEntidade(List<GrlTipoEntidade> listEntidade) {
        this.listEntidade = listEntidade;
    }
    
    public Integer getTipoEntidade() {
        return tipoEntidade;
    }
    
    public void setTipoEntidade(Integer tipoEntidade) {
        this.tipoEntidade = tipoEntidade;
    }
    
    public List<GrlEntidade> getListGrlEntidade() {
        return listGrlEntidade;
    }
    
    public void setListGrlEntidade(List<GrlEntidade> listGrlEntidade) {
        this.listGrlEntidade = listGrlEntidade;
    }

    public boolean isUseRubrica() {
        return useRubrica;
    }

    public void setUseRubrica(boolean useRubrica) {
        this.useRubrica = useRubrica;
    }

    public String getNumeroRubrica() {
        return numeroRubrica;
    }

    public void setNumeroRubrica(String numeroRubrica) {
        this.numeroRubrica = numeroRubrica;
    }
    
    
    
}
