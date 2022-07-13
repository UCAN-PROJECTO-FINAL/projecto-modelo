/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import ejbs.entities.ArCadeira;
import ejbs.entities.ArCurso;
import ejbs.entities.ArDocente;
import ejbs.facades.ArCadeiraFacade;
import ejbs.facades.ArCadeiraFacade;
import ejbs.facades.ArCursoFacade;
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
@Named(value = "arCadeiraCadastrarBean")
@SessionScoped
public class arCadeiraCadastrarBean implements Serializable {

   
    @EJB
    private ArCursoFacade cursoFacade;
    
    @EJB
    private ArCadeiraFacade cadeiraFacade;

    @EJB
    private ArDocenteFacade docenteFacade;
    
    private ArCadeira cadeira;

    private int codigoCurso;
    private int codigoDocente;
    
    
    private boolean btnSalvar = true;
    private boolean btnFormularioCadastro;
    private boolean tabListar = true;
    private boolean estado;
    
    
    public arCadeiraCadastrarBean() {
    }

    @PostConstruct
    public void init()
    {
        cadeira = new ArCadeira();
        codigoCurso = 0;
        codigoDocente = 0;
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
        cadeira = new ArCadeira();

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
               this.cadeira = new ArCadeira();
               FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Cadeira já existe"));
               return "";
           }

            this.cadeira.setFkCurso(new ArCurso(this.codigoCurso));
            this.cadeira.setFkDocente(new ArDocente(this.codigoDocente));
            this.cadeiraFacade.create(this.cadeira);
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

    public List<ArCadeira> verificarRegistro() 
    {
        return this.cadeiraFacade.findCadeiraByDescricao(this.cadeira.getDescricao());
    }
    
    public List<ArCadeira> listaCadeira() 
    {
        return this.cadeiraFacade.findAll();
    }
    
    
    public List<ArCurso> listaCurso() 
    {
        return this.cursoFacade.findAll();
    }
 
    public String alterar(ArCadeira cadeira) 
    {

        btnEditar();
        this.cadeira = cadeira;
        this.codigoCurso = this.cadeira.getFkCurso().getPkCurso();
        this.codigoDocente = this.cadeira.getFkDocente().getPkDocente();
        return "";

    }
    
    public void editar()
    {

        try
        {
            if (!verificarRegistro().isEmpty())
            {this.cadeira = new ArCadeira();
                
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Cadeira já existe"));
                return ;
            }

            this.cadeira.setFkCurso(new ArCurso(this.codigoCurso));
            this.cadeira.setFkDocente(new ArDocente(this.codigoDocente));
            this.cadeiraFacade.edit(this.cadeira);
            init();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Os Dados foram actualizados com sucesso!"));

        } catch (Exception exception)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Impossível alterar os dados , entre em contato com o Administrador de Sistemas", "Impossível alterar os dados , entre em contato com o Administrador de Sistemas"));
        }
        this.estado = false;
        btnListar();

    } // Fim método editar
 
    
    
    public void eliminar(ArCadeira cadeira)
    {
        try
        {
            cadeiraFacade.remove(cadeiraFacade.find(cadeira.getPkCadeira()));
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Dados excluídos com sucesso!", "Dados excluídos com sucesso!"));
            init();
        } 
        catch (Exception exception)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Impossível eliminar Cadeira,esta a ser usado em um outro registo, Verifique se está sendo utilizado em algum registro, ou entre em contacto com o Administrador de Sistema."));
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
    
    
    public ArCadeira getCadeira() {
        return cadeira;
    }

    public void setCadeira(ArCadeira cadeira) {
        this.cadeira = cadeira;
    }

    public int getCodigoCurso() {
        return codigoCurso;
    }

    public void setCodigoCurso(int codigoCurso) {
        this.codigoCurso = codigoCurso;
    }

    public int getCodigoDocente()
    {
        return codigoDocente;
    }

    public void setCodigoDocente(int codigoDocente)
    {
        this.codigoDocente = codigoDocente;
    }
    
}
