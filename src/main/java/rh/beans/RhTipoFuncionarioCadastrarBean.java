/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rh.beans;

import ejbs.entities.RhTipoFuncionario;
import ejbs.facades.RhTipoFuncionarioFacade;
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
@Named(value = "rhTipoFuncionarioCadastrarBean")
@ViewScoped

public class RhTipoFuncionarioCadastrarBean implements Serializable
{
    
    @EJB
    private RhTipoFuncionarioFacade rhTipoFuncionarioFacade;
    
    @Inject
    RhTipoFuncionarioCadastrarBean tipo_funcionario;
    
    private RhTipoFuncionario rhTipoFuncionario;
    
    private String descricao;

    public RhTipoFuncionarioCadastrarBean()
    {
        
        
    }
    
     
    
    @PostConstruct
    public void init()
    {
        descricao = "";
        rhTipoFuncionario = new RhTipoFuncionario();
        
    }
    
    public String gravar()
    {
        System.err.println("descricao: "+ descricao);
        try
        {
           if (!verificarRegistro().isEmpty())
           {
               this.rhTipoFuncionario = new RhTipoFuncionario();
               FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "A Especialidade já existe"));
               return "";
           }
           
           
            
            this.rhTipoFuncionario.setDescricao(this.descricao);
            this.rhTipoFuncionarioFacade.create(this.rhTipoFuncionario);
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

    public List<RhTipoFuncionario> verificarRegistro() 
    {
//        return this.grlTipoSolicitacaoFacade.findTipoSolicitacaoByDescricao(this.grlTipoSolicitacao.getDescricao());
        return this.rhTipoFuncionarioFacade.findRhTipoFuncionarioByDescricao(this.descricao);
    }
    
    
     
    public String alterar(RhTipoFuncionario tipo_funcionario) 
    {

        this.rhTipoFuncionario = rhTipoFuncionario;
        

        return " /modulos/frtVisao/frtTransporte/frt_transporte_tipo_solicitacao_alterar.xhtml?faces-redirect=true";   

    }
    
    public void editar()
    {

        try
        {
            if (!verificarRegistro().isEmpty())
            {this.rhTipoFuncionario = new RhTipoFuncionario();
                
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Tipo de Documento já existe"));
                return ;
            }

            this.rhTipoFuncionarioFacade.edit(this.rhTipoFuncionario);
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
            rhTipoFuncionarioFacade.remove(rhTipoFuncionarioFacade.find(codigo));
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Dados excluídos com sucesso!", "Dados excluídos com sucesso!"));
            init();
            tipo_funcionario.init();
            
        } 
        catch (Exception exception)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Impossível eliminar Ta Especialidade, Verifique se está sendo utilizado em algum registro, ou entre em contacto com o Administrador de Sistema."));
        }

    }


    public RhTipoFuncionario getRhTipoFuncionario()
    {
        return rhTipoFuncionario;
    }

    public void setRhTipoFuncionario(RhTipoFuncionario rhTipoFuncionario)
    {
        this.rhTipoFuncionario = rhTipoFuncionario;
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
