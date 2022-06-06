/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gs.beans;

import ejbs.entities.GsCategoria;
import ejbs.entities.GsFornecedor;
import ejbs.entities.GsSoftware;
import ejbs.entities.GsSubcategoria;
import ejbs.facades.GsSoftwareFacade;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;

/**
 *
 * @author jesus
 */
@Named(value = "gsSoftwareBean")
@ViewScoped
public class GsSoftwareBean implements Serializable {

    @EJB
    private GsSoftwareFacade gsSoftwareFacade;

    private GsSoftware softwareSelecionado;

    private GsSoftware software = new GsSoftware();

    private String nome;

    private Date dataAquisicao;

    private String versao;

    private GsFornecedor fkGsFornecedor = new GsFornecedor();

    private GsSubcategoria fkGsSubcategoria = new GsSubcategoria();

    private GsCategoria categoria = new GsCategoria();

    public GsSoftwareBean() {
    }

    public GsSoftware getSoftwareSelecionado() {
        return softwareSelecionado;
    }

    public void setSoftwareSelecionado(GsSoftware softwareSelecionado) {
        this.softwareSelecionado = softwareSelecionado;
    }

    public GsSoftware getSoftware() {
        return software;
    }

    public void setSoftware(GsSoftware software) {
        this.software = software;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataAquisicao() {
        return dataAquisicao;
    }

    public void setDataAquisicao(Date dataAquisicao) {
        this.dataAquisicao = dataAquisicao;
    }

    public String getVersao() {
        return versao;
    }

    public void setVersao(String versao) {
        this.versao = versao;
    }

    public GsFornecedor getFkGsFornecedor() {
        return fkGsFornecedor;
    }

    public void setFkGsFornecedor(GsFornecedor fkGsFornecedor) {
        this.fkGsFornecedor = fkGsFornecedor;
    }

    public GsSubcategoria getFkGsSubcategoria() {
        return fkGsSubcategoria;
    }

    public void setFkGsSubcategoria(GsSubcategoria fkGsSubcategoria) {
        this.fkGsSubcategoria = fkGsSubcategoria;
    }

    public GsCategoria getCategoria() {
        return categoria;
    }

    public void setCategoria(GsCategoria categoria) {
        this.categoria = categoria;
    }


    public List<GsSoftware> listarTodos() {
        return this.gsSoftwareFacade.findAllOrderByName();
    }

    public void emptyVariables() {
        this.nome = "";
        this.dataAquisicao = null;
        this.versao = "";
        this.fkGsFornecedor = null;
        this.fkGsSubcategoria = null;
    }

    public String gravar() {
        this.software.setNome(this.nome);
        this.software.setDataAquisicao(this.dataAquisicao);
        this.software.setVersao(this.versao);
        this.software.setFkGsFornecedor(this.fkGsFornecedor);
        this.software.setFkGsSubcategoria(this.fkGsSubcategoria);
        this.gsSoftwareFacade.create(this.software);
        this.emptyVariables();
        return "/modulos/gsVisao/gs_software_cadastrar?faces-redirect=true";
    }

    public String actualizar() {
        this.gsSoftwareFacade.edit(this.softwareSelecionado);
        return "/modulos/gsVisao/gs_software_cadastrar?faces-redirect=true";
    }

    public String eliminar(GsSoftware software) {

        this.gsSoftwareFacade.remove(software);
        return "/modulos/gsVisao/gs_software_cadastrar?faces-redirect=true";
    }
}
