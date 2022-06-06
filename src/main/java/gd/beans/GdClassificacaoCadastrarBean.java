/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gd.beans;

import ejbs.entities.GdClassificacao;
import ejbs.facades.GdClassificacaoFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
/**
 *
 * @author david-salgueiro
 */
@Named(value = "gdClassificacaoCadastrarBean")
@SessionScoped
public class GdClassificacaoCadastrarBean implements Serializable {

    @EJB
    private GdClassificacaoFacade classificacaoFacade;
    
    @Inject
    GdDocumentoCadastrarBean gestaoDocumentoBean;

    private GdClassificacao classificacao;
    private List<GdClassificacao> listaClassificacao;
    
    
    private boolean btnSalvar = true;
    private boolean btnFormularioCadastro;
    private boolean tabListar = true;
    private boolean estado;
    
   
    public GdClassificacaoCadastrarBean() {
    }
    
    @PostConstruct
    public void init()
    {
        listaClassificacao = classificacaoFacade.findAll();
        classificacao = new GdClassificacao();
    }
    
    
    //Metodos para formulário
    
    public void voltar()
    {

        this.btnSalvar = true;
        this.btnFormularioCadastro = false;
        this.tabListar = false;
        this.estado = false;

    }

    public void novo()
    {

        this.btnSalvar = false;
        this.btnFormularioCadastro = true;
        this.tabListar = false;
        this.estado = false;
        classificacao = new GdClassificacao();

    }

    public void btnListar()
    {

        this.tabListar = true;
        this.btnFormularioCadastro = false;
        this.btnSalvar = true;
        this.estado = false;
    }

    public void btnEditar()
    {

        this.estado = true;
        this.tabListar = false;

    }
    
    
    public String gravar()
    {
        try
        {
           if (!verificarRegistro().isEmpty())
           {
               this.classificacao = new GdClassificacao();
               FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "A Classificacao já existe", "A Classificacao já existe"));
               return "";
           }

            this.classificacaoFacade.create(this.classificacao);
            init();
            gestaoDocumentoBean.initTClassificacao();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Dados salvos com sucesso!", "Dados salvos com sucesso!"));
        } 
        catch (Exception ex)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao salvar os dados, verifique todos os parametros de inserção!", "Erro ao salvar os dados, verifique todos os parametros de inserção!"));
            ex.printStackTrace();
        }
           return "";
           
    } // Fim método gravar   

    public List<GdClassificacao> verificarRegistro() 
    {
        return this.classificacaoFacade.findClassificacaoByDescricao(this.classificacao.getDescricao());
    }
    
    
    public String alterar(GdClassificacao classificacao) 
    {

        btnEditar();
        this.classificacao = classificacao;

        return "";

    }
    
    public void editar()
    {

        try
        {
            if (!verificarRegistro().isEmpty())
            {this.classificacao = new GdClassificacao();
                
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Classificacao já existe", "A Classificacao já existe"));
                return ;
            }

            this.classificacaoFacade.edit(this.classificacao);
            init();
            gestaoDocumentoBean.initTClassificacao();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Os Dados foram actualizados com sucesso!", "Os Dados foram actualizados com sucesso!"));

        } catch (Exception exception)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Impossível alterar os dados , entre em contato com o Administrador de Sistemas", "Impossível alterar os dados , entre em contato com o Administrador de Sistemas"));
        }
        this.estado = false;
        btnListar();

    } // Fim método editar
 
    
    
    public void eliminar(GdClassificacao classificacao)
    {
        try
        {

            classificacaoFacade.remove(classificacaoFacade.find(classificacao.getPkClassificacao()));
            gestaoDocumentoBean.initTClassificacao();
            init();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Dados excluídos com sucesso!", "Dados excluídos com sucesso!"));

        } 
        catch (Exception exception)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Impossível eliminar Tipo de Documento, Verifique se está sendo utilizado em algum registro, ou entre em contacto com o Administrador de Sistema.", "Impossível eliminar Tipo de Documento, Verifique se está sendo utilizado em algum registro, ou entre em contacto com o Administrador de Sistema."));
        }

    }
    
    
    public boolean isBtnSalvar() {
        return btnSalvar;
    }

    public void setBtnSalvar(boolean btnSalvar) {
        this.btnSalvar = btnSalvar;
    }

    public boolean isBtnFormularioCadastro() {
        return btnFormularioCadastro;
    }

    public void setBtnFormularioCadastro(boolean btnFormularioCadastro) {
        this.btnFormularioCadastro = btnFormularioCadastro;
    }

    public boolean isTabListar() {
        return tabListar;
    }

    public void setTabListar(boolean tabListar) {
        this.tabListar = tabListar;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public GdClassificacao getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(GdClassificacao classificacao) {
        this.classificacao = classificacao;
    }

    public List<GdClassificacao> getListaClassificacao() {
        return listaClassificacao;
    }

    public void setListaClassificacao(List<GdClassificacao> listaClassificacao) {
        this.listaClassificacao = listaClassificacao;
    }
    
}
