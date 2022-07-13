/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gs.beans;

import ejbs.entities.GsFornecedor;
import ejbs.entities.GsSoftware;
import ejbs.facades.GsFornecedorFacade;
import javax.inject.Named;
import java.io.Serializable;
import javax.ejb.EJB;
import java.util.List;
import javax.faces.view.ViewScoped;

/**
 *
 * @author jesus
 */
@Named(value = "gsFornecedorBean")
@ViewScoped
public class GsFornecedorBean implements Serializable {

    @EJB
    private GsFornecedorFacade gsFornecedorFacade;

    private final GsFornecedor fornecedor = new GsFornecedor();

    private GsFornecedor fornecedorSelecionado;

    private String nif;

    private String nome;

    private String endereco;

    private String contacto;

    private List<GsSoftware> Softwares;

    public GsFornecedorBean() {
    }

    public GsFornecedor getFornecedorSelecionado() {
        return fornecedorSelecionado;
    }

    public void setFornecedorSelecionado(GsFornecedor fornecedorSelecionado) {
        this.fornecedorSelecionado = fornecedorSelecionado;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public List<GsSoftware> getSoftwares() {
        return Softwares;
    }

    public void setSoftwares(List<GsSoftware> Softwares) {
        this.Softwares = Softwares;
    }

    public List<GsFornecedor> listarTodos() {
        return this.gsFornecedorFacade.findAllOrderByNome();
    }

    public void emptyVariables() {
        this.nif = "";
        this.nome = "";
        this.endereco = "";
        this.contacto = "";

    }

    public void gravar() {

        this.fornecedor.setNif(this.nif);
        this.fornecedor.setNome(this.nome);
        this.fornecedor.setContacto(this.contacto);
        this.fornecedor.setEndereco(this.endereco);
        this.gsFornecedorFacade.create(this.fornecedor);
        this.emptyVariables();
//        return "/modulos/gsVisao/gs_fornecedor_cadastrar?faces-redirect=true";

    }

    public void actualizar() {
        this.gsFornecedorFacade.edit(this.fornecedorSelecionado);
//        return "/modulos/gsVisao/gs_fornecedor_cadastrar?faces-redirect=true";
    }

    public void eliminar(GsFornecedor fornecedor) {
        this.gsFornecedorFacade.remove(fornecedor);
//        return "/modulos/gsVisao/gs_fornecedor_cadastrar?faces-redirect=true";
    }

}
