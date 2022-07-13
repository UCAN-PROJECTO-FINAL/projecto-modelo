/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geral.beans;

import ejbs.cache.GdTipoDocumentoCache;
import ejbs.entities.GdTipoDocumento;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import utils.mensagens.Mensagem;

/**
 *
 * @author majoao
 */
@Named(value = "gdTipoDocumentoBean")
@ViewScoped
public class GrlTipoDocumentoBean implements Serializable {

    /**
     * Creates a new instance of TipoDocumentoBean
     */
    @Inject
    private GdTipoDocumentoCache gdTipoDocumentoCache;

    private GdTipoDocumento gdTipoDocumento;

    private List<GdTipoDocumento> listTipoDocEntidade;

    private int idDocumento;
    private String modulo;

    public GrlTipoDocumentoBean() {
    }

    @PostConstruct
    public void init() {
        listTipoDocEntidade = gdTipoDocumentoCache.getListaGdTipoDocumentos();
        gdTipoDocumento = new GdTipoDocumento();
    }

    public void salvar() {
        try {
            gdTipoDocumento.setPkTipoDocumento(gdTipoDocumentoCache.getGdTipoDocumentoID());
            gdTipoDocumento.setModulo(modulo);
            gdTipoDocumentoCache.create(gdTipoDocumento);
            Mensagem.sucessoMsg(null, "Dados Salvos !");
            init();
            gdTipoDocumentoCache.init();
        } catch (Exception e) {
            Mensagem.warnMsg(null, "Erro ao salvar dados");
        }

    }

    public GdTipoDocumento getGdTipoDocumento() {
        return gdTipoDocumento;
    }

    public void setGdTipoDocumento(GdTipoDocumento gdTipoDocumento) {
        this.gdTipoDocumento = gdTipoDocumento;
    }

    public String getModulo() {
        return modulo;
    }

    public void setModulo(String modulo) {
        this.modulo = modulo;
    }

    
    public List<GdTipoDocumento> getListTipoDocEntidade() {
        return listTipoDocEntidade;
    }

    public void setListTipoDocEntidade(List<GdTipoDocumento> listTipoDocEntidade) {
        this.listTipoDocEntidade = listTipoDocEntidade;
    }

    public int getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(int idDocumento) {
        this.idDocumento = idDocumento;
    }

}
