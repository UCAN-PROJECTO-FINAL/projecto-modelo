/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rh.beans;

import ejbs.entities.RhEspecialidade;
import ejbs.facades.RhEspecialidadeFacade;
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

@Named(value = "rhEspecialidadeCadastrarBean")
@ViewScoped

public class RhEspecialidadeCadastrarBean implements Serializable
{
    @EJB
    private RhEspecialidadeFacade rhEspecialidadeFacade;
    
    @Inject
    RhEspecialidadeCadastrarBean Especialidade;
    
    private RhEspecialidade rhEspecialidade;
    
    private String descricao;

    public RhEspecialidadeCadastrarBean()
    {
        
    }
    
    @PostConstruct
    public void init()
    {
        descricao = "";
        rhEspecialidade = new RhEspecialidade();
        
    }
    
    public String gravar()
    {
        System.err.println("descricao: "+ descricao);
        try
        {
           if (!verificarRegistro().isEmpty())
           {
               this.rhEspecialidade = new RhEspecialidade();
               FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "A Especialidade já existe"));
               return "";
           }
           
           
            
            this.rhEspecialidade.setDescricao(this.descricao);
            this.rhEspecialidadeFacade.create(this.rhEspecialidade);
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

    public List<RhEspecialidade> verificarRegistro() 
    {
//        return this.grlTipoSolicitacaoFacade.findTipoSolicitacaoByDescricao(this.grlTipoSolicitacao.getDescricao());
        return this.rhEspecialidadeFacade.findRhEspecialidadeByDescricao(this.descricao);
    }
    
    
     
    public String alterar(RhEspecialidade especialidade) 
    {

        this.rhEspecialidade = rhEspecialidade;
        

        return " /modulos/frtVisao/frtTransporte/frt_transporte_tipo_solicitacao_alterar.xhtml?faces-redirect=true";   

    }
    
    public void editar()
    {

        try
        {
            if (!verificarRegistro().isEmpty())
            {this.rhEspecialidade = new RhEspecialidade();
                
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Tipo de Documento já existe"));
                return ;
            }

            this.rhEspecialidadeFacade.edit(this.rhEspecialidade);
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
        {   System.out.println("tipoSolicitacao: " + codigo);
            rhEspecialidadeFacade.remove(rhEspecialidadeFacade.find(codigo));
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Dados excluídos com sucesso!", "Dados excluídos com sucesso!"));
            init();
            Especialidade.init();
            
        } 
        catch (Exception exception)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Impossível eliminar Ta Especialidade, Verifique se está sendo utilizado em algum registro, ou entre em contacto com o Administrador de Sistema."));
        }

    }

    public RhEspecialidade getRhEspecialidade()
    {
        return rhEspecialidade;
    }

    public void setRhEspecialidade(RhEspecialidade rhEspecialidade)
    {
        this.rhEspecialidade = rhEspecialidade;
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
