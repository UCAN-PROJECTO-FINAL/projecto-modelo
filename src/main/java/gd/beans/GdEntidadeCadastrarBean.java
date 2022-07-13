/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gd.beans;


import ejbs.entities.GdEntidade;
import ejbs.facades.GdEntidadeFacade;
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
@Named(value = "gdEntidadeCadastrarBean")
@SessionScoped
public class GdEntidadeCadastrarBean implements Serializable {

   
    @EJB
    private GdEntidadeFacade entidadeFacade;

    @Inject
    GdDocumentoCadastrarBean gestaoDocumentoBean;
   
    private GdEntidade entidade;
    private List<GdEntidade> listaEntidade;
    
    private boolean btnSalvar = true;
    private boolean btnFormularioCadastro;
    private boolean tabListar = true;
    private boolean estado;
    
    public GdEntidadeCadastrarBean() {
    }
    
    
    @PostConstruct
    public void init()
    {
        listaEntidade = entidadeFacade.findAll();
        entidade = new GdEntidade();
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
        entidade = new GdEntidade();

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
               this.entidade = new GdEntidade();
               FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Entidade já existe", "Entidade já existe"));
               return "";
           }

            this.entidadeFacade.create(this.entidade);
            init();
            gestaoDocumentoBean.initEntidade();
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Dados salvos com sucesso!", "Dados salvos com sucesso!"));
        } 
        catch (Exception ex)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao salvar os dados, verifique todos os parametros de inserção!", "Erro ao salvar os dados, verifique todos os parametros de inserção!"));
            ex.printStackTrace();
        }
           return "";
           
    } // Fim método gravar   

    public List<GdEntidade> verificarRegistro() 
    {
        return this.entidadeFacade.findEntidadeFacadeByDesignacao(this.entidade.getDesignacao());
    }
    
 
    
    public String alterar(GdEntidade entidade) 
    {

        btnEditar();
        this.entidade = entidade;

        return "";

    }
    
    public void editar()
    {

        try
        {
            if (!verificarRegistro().isEmpty())
            {this.entidade = new GdEntidade();
                
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Entidade já existe"));
                return ;
            }

            this.entidadeFacade.edit(this.entidade);
            init();
            gestaoDocumentoBean.initEntidade();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Os Dados foram actualizados com sucesso!"));

        } catch (Exception exception)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Impossível alterar os dados , entre em contato com o Administrador de Sistemas"));
        }
        this.estado = false;
        btnListar();

    } // Fim método editar
 
    
    
    public void eliminar(GdEntidade entidade)
    {
        try
        {

            entidadeFacade.remove(entidadeFacade.find(entidade.getPkEntidade()));
            gestaoDocumentoBean.initEntidade();
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

    public GdEntidade getEntidade() {
        return entidade;
    }

    public void setEntidade(GdEntidade entidade) {
        this.entidade = entidade;
    }

    public List<GdEntidade> getListaEntidade() {
        return listaEntidade;
    }

    public void setListaEntidade(List<GdEntidade> listaEntidade) {
        this.listaEntidade = listaEntidade;
    }
    
}
