/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import ejbs.entities.ArCurso;
import ejbs.entities.ArFaculdade;
import ejbs.facades.ArCursoFacade;
import ejbs.facades.ArFaculdadeFacade;
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
@Named(value = "arCursoCadastrarBean")
@SessionScoped
public class arCursoCadastrarBean implements Serializable {

    @EJB
    private ArFaculdadeFacade faculdadeFacade;

    @EJB
    private ArCursoFacade cursoFacade;
    
    private ArCurso curso;

    private boolean btnSalvar = true;
    private boolean btnFormularioCadastro;
    private boolean tabListar = true;
    private boolean estado;
    
    private int codigoFaculdade;
    
    public arCursoCadastrarBean() {
    }
    
    @PostConstruct
    public void init()
    {
        curso = new ArCurso();
        codigoFaculdade = 0;
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
        curso = new ArCurso();
        codigoFaculdade = 0;

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
               this.curso = new ArCurso();
               FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Curso já existe"));
               return "";
           }

            this.curso.setFkFaculdade(new ArFaculdade(this.codigoFaculdade));
            this.cursoFacade.create(this.curso);
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

    public List<ArCurso> verificarRegistro() 
    {
        return this.cursoFacade.findCursoByDescricao(this.curso.getDescricao());
    }
    
    public List<ArCurso> listaCurso() 
    {
        return this.cursoFacade.findAll();
    }
    
    
    public List<ArFaculdade> listaFaculdade() 
    {
        return this.faculdadeFacade.findAll();
    }
 
    public String alterar(ArCurso curso) 
    {

        btnEditar();
        this.curso = curso;
        this.codigoFaculdade = curso.getFkFaculdade().getPkFaculdade();
        return "";

    }
    
    public void editar()
    {

        try
        {
            if (!verificarRegistro().isEmpty())
            {this.curso = new ArCurso();
                
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Curso já existe"));
                return ;
            }

            this.curso.setFkFaculdade(new ArFaculdade(this.codigoFaculdade));
            this.cursoFacade.edit(this.curso);
            init();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Os Dados foram actualizados com sucesso!"));

        } catch (Exception exception)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Impossível alterar os dados , entre em contato com o Administrador de Sistemas", "Impossível alterar os dados , entre em contato com o Administrador de Sistemas"));
        }
        this.estado = false;
        btnListar();

    } // Fim método editar
 
    
    
    public void eliminar(ArCurso curso)
    {
        try
        {
            cursoFacade.remove(cursoFacade.find(curso.getPkCurso()));
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Dados excluídos com sucesso!", "Dados excluídos com sucesso!"));
            init();
        } 
        catch (Exception exception)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Impossível eliminar Curso,esta a ser usado em um outro registo, Verifique se está sendo utilizado em algum registro, ou entre em contacto com o Administrador de Sistema."));
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

    public int getCodigoFaculdade() {
        return codigoFaculdade;
    }

    public void setCodigoFaculdade(int codigoFaculdade) {
        this.codigoFaculdade = codigoFaculdade;
    }

    public ArCurso getCurso() {
        return curso;
    }

    public void setCurso(ArCurso curso) {
        this.curso = curso;
    }
    
}
