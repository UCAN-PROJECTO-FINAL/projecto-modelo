/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gs.beans;

import ejbs.entities.GsEquipamento;
import ejbs.entities.GsInstalacao;
import ejbs.facades.GsEquipamentoFacade;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author jesus
 */
@Named(value = "gsEquipamentoBean")
@ViewScoped
public class GsEquipamentoBean implements Serializable {

    @EJB
    private GsEquipamentoFacade gsEquipamentoFacade;

    private GsEquipamento equipamento = new GsEquipamento();

    private GsEquipamento equipamentoSelecionado;

    private String marca;

    private String modelo;

    private String numeroSerie;

    private String macAdress;

    private List<GsInstalacao> gsInstalacaoList;

    public GsEquipamentoBean() {
    }

    public GsEquipamento getEquipamento() {
        return equipamento;
    }

    public void setEquipamento(GsEquipamento equipamento) {
        this.equipamento = equipamento;
    }

    public GsEquipamento getEquipamentoSelecionado() {
        return equipamentoSelecionado;
    }

    public void setEquipamentoSelecionado(GsEquipamento equipamentoSelecionado) {
        this.equipamentoSelecionado = equipamentoSelecionado;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getNumeroSerie() {
        return numeroSerie;
    }

    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    public String getMacAdress() {
        return macAdress;
    }

    public void setMacAdress(String macAdress) {
        this.macAdress = macAdress;
    }

    public List<GsInstalacao> getGsInstalacaoList() {
        return gsInstalacaoList;
    }

    public void setGsInstalacaoList(List<GsInstalacao> gsInstalacaoList) {
        this.gsInstalacaoList = gsInstalacaoList;
    }

    public void emptyVariables() {
        this.marca = "";
        this.modelo = "";
        this.numeroSerie = "";
        this.macAdress = "";

    }

    public List<GsEquipamento> listarTodos() {
        return this.gsEquipamentoFacade.findAllOrderByMarca();
    }

    public String gravar() {

        this.equipamento.setMarca(this.marca);
        this.equipamento.setModelo(this.modelo);
        this.equipamento.setNumeroSerie(this.numeroSerie);
        this.equipamento.setMacAdress(this.macAdress);
        if (this.marca == null || this.macAdress == null) {
            return "/modulos/gsVisao/gs_equipamento_cadastrar?faces-redirect=true";
        }
        this.gsEquipamentoFacade.create(this.equipamento);
        this.emptyVariables();
        return "/modulos/gsVisao/gs_equipamento_cadastrar?faces-redirect=true";

    }

    public String actualizar() {
        this.gsEquipamentoFacade.edit(this.equipamentoSelecionado);
        return "/modulos/gsVisao/gs_equipamento_cadastrar?faces-redirect=true"; 
    }

    public String eliminar(GsEquipamento equipamento) {
        this.gsEquipamentoFacade.remove(equipamento);
        return "/modulos/gsVisao/gs_equipamento_cadastrar?faces-redirect=true"; 
    }
}
