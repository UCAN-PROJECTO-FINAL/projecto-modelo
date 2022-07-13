/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gs.beans;

import ejbs.entities.EstruturaLogica;
import ejbs.entities.GsEquipamento;
import ejbs.entities.GsInstalacao;
import ejbs.entities.GsLicenca;
import ejbs.facades.EstruturaLogicaFacade;
import ejbs.facades.GsEquipamentoFacade;
import ejbs.facades.GsInstalacaoFacade;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;


/**
 *
 * @author jesus
 */
@Named(value = "gsInstalacaoBean")
@ViewScoped
public class GsInstalacaoBean implements Serializable {

    @EJB
    private GsEquipamentoFacade gsEquipamentoFacade;

    @EJB
    private EstruturaLogicaFacade estruturaLogicaFacade;

    @EJB
    private GsInstalacaoFacade gsInstalacaoFacade;

    private GsInstalacao instalacao = new GsInstalacao();

    private GsInstalacao instalacaoSelecionado;

    private Date data;

    private GsEquipamento fkGsEquipamento = new GsEquipamento();

    private GsLicenca fkGsLicenca = new GsLicenca();;

    private EstruturaLogica fkEstruturaLogica = new EstruturaLogica();;

    public GsInstalacaoBean() {
    }

    public EstruturaLogicaFacade getEstruturaLogicaFacade() {
        return estruturaLogicaFacade;
    }

    public void setEstruturaLogicaFacade(EstruturaLogicaFacade estruturaLogicaFacade) {
        this.estruturaLogicaFacade = estruturaLogicaFacade;
    }

    public GsInstalacao getInstalacao() {
        return instalacao;
    }

    public void setInstalacao(GsInstalacao instalacao) {
        this.instalacao = instalacao;
    }

    public GsInstalacao getInstalacaoSelecionado() {
        return instalacaoSelecionado;
    }

    public void setInstalacaoSelecionado(GsInstalacao instalacaoSelecionado) {
        this.instalacaoSelecionado = instalacaoSelecionado;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public GsEquipamento getFkGsEquipamento() {
        return fkGsEquipamento;
    }

    public void setFkGsEquipamento(GsEquipamento fkGsEquipamento) {
        this.fkGsEquipamento = fkGsEquipamento;
    }

    public GsLicenca getFkGsLicenca() {
        return fkGsLicenca;
    }

    public void setFkGsLicenca(GsLicenca fkGsLicenca) {
        this.fkGsLicenca = fkGsLicenca;
    }

    public EstruturaLogica getFkEstruturaLogica() {
        return fkEstruturaLogica;
    }

    public void setFkEstruturaLogica(EstruturaLogica fkEstruturaLogica) {
        this.fkEstruturaLogica = fkEstruturaLogica;
    }

    public List<GsInstalacao> listarTodos() {
        return this.gsInstalacaoFacade.findAllOrderByData();
    }

    public void emptyVariables() {
        this.data = null;
        this.fkGsEquipamento = null;
        this.fkGsLicenca = null;
        this.fkEstruturaLogica = null;
    }

    public String gravar() {

        this.instalacao.setData(new Date());
        this.instalacao.setFkGsEquipamento(this.fkGsEquipamento);
        this.instalacao.setFkGsLicenca(this.fkGsLicenca);
        this.instalacao.setFkEstruturaLogica(this.fkEstruturaLogica);
        this.gsInstalacaoFacade.create(this.instalacao);
        this.emptyVariables();
        return "/modulos/gsVisao/gs_instalacao_cadastrar?faces-redirect=true";
    }

    public String actualizar() {
        this.gsInstalacaoFacade.edit(this.instalacaoSelecionado);
        return "/modulos/gsVisao/gs_instalacao_cadastrar?faces-redirect=true";
    }

    public String eliminar(GsInstalacao instalacao) {
        this.gsInstalacaoFacade.remove(instalacao);
        return "/modulos/gsVisao/gs_instalacao_cadastrar?faces-redirect=true";
    }
}
