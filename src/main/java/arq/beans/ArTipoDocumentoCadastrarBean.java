/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arq.beans;

import ejbs.facades.ArTipoDocumentoFacade;
import ejbs.entities.ArTipoDocumento;
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
@Named(value = "arTipoDocumentoCadastrarBean")
@SessionScoped
public class ArTipoDocumentoCadastrarBean implements Serializable {

    
    @EJB
    private ArTipoDocumentoFacade tipoDocumentoFacade;

    @Inject
    ArArquivoMortoCadastrarBean arquivoBean;
    
    private ArTipoDocumento tipoDocumento;
    private List<ArTipoDocumento> listaTipoDocumento;
    
    private boolean btnSalvar = true;
    private boolean btnFormularioCadastro;
    private boolean tabListar = true;
    private boolean estado;
    
    public ArTipoDocumentoCadastrarBean()
    {
    }
    
     @PostConstruct
    public void init()
    {
        listaTipoDocumento = tipoDocumentoFacade.findAll();
        tipoDocumento = new ArTipoDocumento();
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
        tipoDocumento = new ArTipoDocumento();

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
               this.tipoDocumento = new ArTipoDocumento();
               FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Tipo de Documento já existe"));
               return "";
           }

            this.tipoDocumentoFacade.create(this.tipoDocumento);
            init();
            arquivoBean.initTipoDoc();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Dados salvos com sucesso!"));
        } 
        catch (Exception ex)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao salvar os dados, verifique todos os parametros de inserção!", "Erro ao salvar os dados, verifique todos os parametros de inserção!"));
            ex.printStackTrace();
        }
           return "";
           
    } // Fim método gravar   

    public List<ArTipoDocumento> verificarRegistro() 
    {
        return this.tipoDocumentoFacade.findTipoDocumentoByDescricao(this.tipoDocumento.getDescricao());
    }
    
 
    public String alterar(ArTipoDocumento tipoDocumento) 
    {

        btnEditar();
        this.tipoDocumento = tipoDocumento;

        return "";

    }
    
    public void editar()
    {

        try
        {
            if (!verificarRegistro().isEmpty())
            {this.tipoDocumento = new ArTipoDocumento();
                
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Tipo de Documento já existe"));
                return ;
            }

            this.tipoDocumentoFacade.edit(this.tipoDocumento);
            init();
            arquivoBean.initTipoDoc();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Os Dados foram actualizados com sucesso!"));

        } catch (Exception exception)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Impossível alterar os dados , entre em contato com o Administrador de Sistemas", "Impossível alterar os dados , entre em contato com o Administrador de Sistemas"));
        }
        this.estado = false;
        btnListar();

    } // Fim método editar
 
    
    
    public void eliminar(ArTipoDocumento tipoDocumento)
    {
        try
        {
            tipoDocumentoFacade.remove(tipoDocumentoFacade.find(tipoDocumento.getPkTipoDocumento()));
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Dados excluídos com sucesso!", "Dados excluídos com sucesso!"));
            init();
            arquivoBean.initTipoDoc();
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

    public ArTipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(ArTipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public List<ArTipoDocumento> getListaTipoDocumento() {
        return listaTipoDocumento;
    }

    public void setListaTipoDocumento(List<ArTipoDocumento> listaTipoDocumento) {
        this.listaTipoDocumento = listaTipoDocumento;
    }

    
}
