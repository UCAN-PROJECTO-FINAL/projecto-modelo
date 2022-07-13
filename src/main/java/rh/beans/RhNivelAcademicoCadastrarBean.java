/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rh.beans;

import ejbs.entities.RhNivelAcademico;
import ejbs.facades.RhNivelAcademicoFacade;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author smakambo
 */
@Named(value = "rhNivelAcademicoCadastrarBean")
@ViewScoped

public class RhNivelAcademicoCadastrarBean implements Serializable
{
    
    @EJB
    private RhNivelAcademicoFacade rhNivelAcademicoFacade;
    
    @Inject
    RhFuncaoCadastrarBean funcao;
    
    private RhNivelAcademico rhNivelAcademico;
    
    private String descricao;

    public RhNivelAcademicoCadastrarBean()
    {
        
    }
    
    
        
    
    @PostConstruct
    public void init()
    {
        descricao = "";
        rhNivelAcademico = new RhNivelAcademico();
        
    }
    
    public String gravar()
    {
        System.err.println("descricao: "+ descricao);
        try
        {
           if (!verificarRegistro().isEmpty())
           {
               this.rhNivelAcademico = new RhNivelAcademico();
               FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "A Especialidade já existe"));
               return "";
           }
           
           
            
            this.rhNivelAcademico.setDescricao(this.descricao);
            this.rhNivelAcademicoFacade.create(this.rhNivelAcademico);
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

    public List<RhNivelAcademico> verificarRegistro() 
    {
//        return this.grlTipoSolicitacaoFacade.findTipoSolicitacaoByDescricao(this.grlTipoSolicitacao.getDescricao());
        return this.rhNivelAcademicoFacade.findRhNivelAcademicoByDescricao(this.descricao);
    }
    
    
     
    public String alterar(RhNivelAcademico nivel_academico) 
    {

        this.rhNivelAcademico = rhNivelAcademico;
        

        return " /modulos/frtVisao/frtTransporte/frt_transporte_tipo_solicitacao_alterar.xhtml?faces-redirect=true";   

    }
    
    public void editar()
    {

        try
        {
            if (!verificarRegistro().isEmpty())
            {this.rhNivelAcademico = new RhNivelAcademico();
                
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Tipo de Documento já existe"));
                return ;
            }

            this.rhNivelAcademicoFacade.edit(this.rhNivelAcademico);
            init();
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Os Dados foram actualizados com sucesso!"));

        } catch (Exception exception)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Impossível alterar os dados , entre em contato com o Administrador de Sistemas", "Impossível alterar os dados , entre em contato com o Administrador de Sistemas"));
        }
        

    } // Fim método editar
 
    
    
    public void eliminar(int codigo)
    {
        try
        {   System.out.println("Função: " + codigo);
            rhNivelAcademicoFacade.remove(rhNivelAcademicoFacade.find(codigo));
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Dados excluídos com sucesso!", "Dados excluídos com sucesso!"));
            init();
            funcao.init();
            
        } 
        catch (Exception exception)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Impossível eliminar Ta Especialidade, Verifique se está sendo utilizado em algum registro, ou entre em contacto com o Administrador de Sistema."));
        }

    }

    public RhNivelAcademico getRhNivelAcademico()
    {
        return rhNivelAcademico;
    }

    public void setRhNivelAcademico(RhNivelAcademico rhNivelAcademico)
    {
        this.rhNivelAcademico = rhNivelAcademico;
    }

    public String getDescricao()
    {
        return descricao;
    }

    public void setDescricao(String descricao)
    {
        this.descricao = descricao;
    }
    
    
    
    
}
