/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gs.beans;

import ejbs.entities.GsLicenca;
import ejbs.entities.GsSoftware;
import ejbs.entities.GsTipo;
import ejbs.facades.GsLicencaFacade;
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
@Named(value = "gsLicencaBean")
@ViewScoped
public class GsLicencaBean implements Serializable {

    @EJB
    private GsLicencaFacade gsLicencaFacade;

    private GsLicenca licenca = new GsLicenca();

    private GsLicenca licencalSelecionado;

    private String chaveLicenca;

    private Date dataEmissao;

    private Date dataExpiracao;

    private Integer numeroUtilizadores;

    private GsSoftware software = new GsSoftware();

    private GsTipo tipo = new GsTipo();

    public GsLicencaBean() {
    }

    public GsLicenca getLicenca() {
        return licenca;
    }

    public void setLicenca(GsLicenca licenca) {
        this.licenca = licenca;
    }

    public GsLicenca getLicencalSelecionado() {
        return licencalSelecionado;
    }

    public void setLicencalSelecionado(GsLicenca licencalSelecionado) {
        this.licencalSelecionado = licencalSelecionado;
    }

    public String getChaveLicenca() {
        return chaveLicenca;
    }

    public void setChaveLicenca(String chaveLicenca) {
        this.chaveLicenca = chaveLicenca;
    }

    public Date getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(Date dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public Date getDataExpiracao() {
        return dataExpiracao;
    }

    public void setDataExpiracao(Date dataExpiracao) {
        this.dataExpiracao = dataExpiracao;
    }

    public Integer getNumeroUtilizadores() {
        return numeroUtilizadores;
    }

    public void setNumeroUtilizadores(Integer numeroUtilizadores) {
        this.numeroUtilizadores = numeroUtilizadores;
    }

    public GsSoftware getSoftware() {
        return software;
    }

    public void setSoftware(GsSoftware software) {
        this.software = software;
    }

    public GsTipo getTipo() {
        return tipo;
    }

    public void setTipo(GsTipo tipo) {
        this.tipo = tipo;
    }

    public List<GsLicenca> listarTodos() {
        return this.gsLicencaFacade.findAll();
    }

    public void emptyVariables() {
        this.chaveLicenca = "";
        this.dataEmissao = null;
        this.dataExpiracao = null;
        this.numeroUtilizadores = 0;
        this.software = null;
        this.tipo = null;
    }

    public String gravar() {
        this.licenca.setChaveLicenca(this.chaveLicenca);
        this.licenca.setDataEmissao(this.dataEmissao);
        this.licenca.setDataExpiracao(this.dataExpiracao);
        this.licenca.setNumeroUtilizadores(this.numeroUtilizadores);
        this.licenca.setFkGsSoftware(this.software);
        this.licenca.setFkGsTipo(this.tipo);
        this.gsLicencaFacade.create(this.licenca);
        
        System.err.println("Chave " + chaveLicenca);
        System.err.println("data " + dataEmissao);
        System.err.println("ID do soft " + software.getPkGsSoftware());

//        this.licenca.setChaveLicenca("testechv");
//        this.licenca.setDataEmissao((" ");
//        this.licenca.setDataExpiracao("");
//        this.licenca.setNumeroUtilizadores(this.numeroUtilizadores);
//        this.software = new GsSoftware();
//        this.licenca.setFkGsSoftware(this.software);
//        this.tipo = new GsTipo();
//        this.licenca.setFkGsTipo(this.tipo);
        this.emptyVariables();
        return "/modulos/gsVisao/gs_licenca_cadastrar?faces-redirect=true";

    }

    public String actualizar() {
        this.gsLicencaFacade.edit(this.licencalSelecionado);
        return "/modulos/gsVisao/gs_licenca_cadastrar?faces-redirect=true";
    }

    public String eliminar(GsLicenca licenca) {
        this.gsLicencaFacade.remove(licenca);
        return "/modulos/gsVisao/gs_licenca_cadastrar?faces-redirect=true";
    }

}
