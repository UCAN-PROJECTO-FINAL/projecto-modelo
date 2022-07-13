/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grl.beans;

import ejbs.entities.GrlSexo;
import ejbs.facades.GrlSexoFacade;
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

@Named(value = "sexoCadastrarBean")
@ViewScoped
public class SexoCadastrarBean implements Serializable
{
    
    @EJB
    private GrlSexoFacade sexoFacade;
    
    private GrlSexo sexo;
    
    @Inject
    SexoCadastrarBean id_sexo;
    
    
    
    private String descricao;

    public SexoCadastrarBean()
    {
        
    }
    
    
    @PostConstruct
    public void init()
    {
        descricao = "";
        sexo = new GrlSexo();
        
    }
    
    public String gravar()
    {
        System.err.println("descricao: "+ descricao);
        try
        {
           if (!verificarRegistro().isEmpty())
           {
               this.sexo = new GrlSexo();
               FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "O Sexo já existe"));
               return "";
           }
           
           
            
            this.sexo.setNome(this.descricao);
            this.sexoFacade.create(this.sexo);
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

    public List<GrlSexo> verificarRegistro() 
    {
//        return this.grlTipoSolicitacaoFacade.findTipoSolicitacaoByDescricao(this.grlTipoSolicitacao.getDescricao());
        return this.sexoFacade.findRhSexoByDescricao(this.descricao);
    }
    
    
     
    public String alterar(GrlSexo sexo) 
    {

        this.sexo = sexo;
        

        return " /modulos/frtVisao/frtTransporte/frt_transporte_tipo_solicitacao_alterar.xhtml?faces-redirect=true";   

    }
    
    public void editar()
    {

        try
        {
            if (!verificarRegistro().isEmpty())
            {this.sexo = new GrlSexo();
                
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Tipo de Documento já existe"));
                return ;
            }

            this.sexoFacade.edit(this.sexo);
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
        {   System.out.println("Código: " + codigo);
            sexoFacade.remove(sexoFacade.find(codigo));
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Dados excluídos com sucesso!", "Dados excluídos com sucesso!"));
            init();
            id_sexo.init();
            
        } 
        catch (Exception exception)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Impossível eliminar Ta Especialidade, Verifique se está sendo utilizado em algum registro, ou entre em contacto com o Administrador de Sistema."));
        }

    }

    public GrlSexo getSexo()
    {
        return sexo;
    }

    public void setSexo(GrlSexo sexo)
    {
        this.sexo = sexo;
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
