/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import ejbs.entities.ArDocente;
import ejbs.facades.ArDocenteFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author david-salgueiro
 */
@Named(value = "arDocenteCadastrarBean")
@SessionScoped
public class ArDocenteCadastrarBean implements Serializable {

    @EJB
    private ArDocenteFacade docenteFacade;
    
    private ArDocente docente;

    private boolean btnSalvar = true;
    private boolean btnFormularioCadastro;
    private boolean tabListar = true;
    private boolean estado;
    
    public ArDocenteCadastrarBean() {
    }
    
    @PostConstruct
    public void init()
    {
        docente = new ArDocente();
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
        docente = new ArDocente();

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
               this.docente = new ArDocente();
               FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Docente já existe"));
               return "";
           }

            this.docenteFacade.create(this.docente);
            init();
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Dados salvos com sucesso!"));
        } 
        catch (Exception ex)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao salvar os dados, verifique todos os parametros de inserção!", "Erro ao salvar os dados, verifique todos os parametros de inserção!"));
            ex.printStackTrace();
        }
           return "";
           
    } // Fim método gravar   

    public List<ArDocente> verificarRegistro() 
    {
        return this.docenteFacade.findDocenteByDescricao(this.docente.getNomeCompleto());
    }
    
    public List<ArDocente> listaDocente() 
    {
        return this.docenteFacade.findAll();
    }
 
    public String alterar(ArDocente docente) 
    {

        btnEditar();
        this.docente = docente;

        return "";

    }
    
    public void editar()
    {

        try
        {
            if (!verificarRegistro().isEmpty())
            {this.docente = new ArDocente();
                
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Docente já existe"));
                return ;
            }

            this.docenteFacade.edit(this.docente);
            init();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Os Dados foram actualizados com sucesso!"));

        } catch (Exception exception)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Impossível alterar os dados , entre em contato com o Administrador de Sistemas", "Impossível alterar os dados , entre em contato com o Administrador de Sistemas"));
        }
        this.estado = false;
        btnListar();

    } // Fim método editar
 
    
    
    public void eliminar(ArDocente docente)
    {
        try
        {
            docenteFacade.remove(docenteFacade.find(docente.getPkDocente()));
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Dados excluídos com sucesso!", "Dados excluídos com sucesso!"));
            init();
        } 
        catch (Exception exception)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Impossível eliminar Tipo Documento, Verifique se está sendo utilizado em algum registro, ou entre em contacto com o Administrador de Sistema."));
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

    public ArDocente getDocente() {
        return docente;
    }

    public void setDocente(ArDocente docente) {
        this.docente = docente;
    }
    
}
