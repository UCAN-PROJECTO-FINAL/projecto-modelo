/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grl.beans;

import ejbs.entities.GrlEstadoCivil;
import ejbs.facades.GrlEstadoCivilFacade;
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

@Named(value = "estadoCivilCadastarBean")
@ViewScoped
public class EstadoCivilCadastarBean implements Serializable
{
    @EJB
    private GrlEstadoCivilFacade estadoCivilFacade;
    
    @Inject
    EstadoCivilCadastarBean id_estado_civil;
    
    private GrlEstadoCivil estadoCivil;
    
    private String descricao;

    public EstadoCivilCadastarBean()
    {
        
    }
    
    
    @PostConstruct
    public void init()
    {
        descricao = "";
        estadoCivil = new GrlEstadoCivil();
        
    }
    
//    public String gravar()
//    {
//        System.err.println("descricao: "+ descricao);
//        try
//        {
//           if (!verificarRegistro().isEmpty())
//           {
//               this.estadoCivil = new GrlEstadoCivil();
//               FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "O Estado Civil já existe"));
//               return "";
//           }
//           
//           
//            
//            this.estadoCivil.setNome(this.descricao);
//            this.estadoCivilFacade.create(this.estadoCivil);
//            init();
//            
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Dados salvos com sucesso!"));
//        } 
//        catch (Exception ex)
//        {
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao salvar os dados, verifique todos os parametros de inserção!", "Erro ao salvar os dados, verifique todos os parametros de inserção!"));
//            ex.printStackTrace();
//        }
//           return "";
//           
//    } // Fim método gravar   
//
//    public List<GrlEstadoCivil> verificarRegistro() 
//    {
////        return this.grlTipoSolicitacaoFacade.findTipoSolicitacaoByDescricao(this.grlTipoSolicitacao.getDescricao());
////        return this.estadoCivilFacade.findRhEstadoCivilByDescricao(this.descricao);
//    }
    
    
     
    public String alterar(GrlEstadoCivil estado_civil) 
    {

        this.estadoCivil = estadoCivil;
        

        return " /modulos/frtVisao/frtTransporte/frt_transporte_tipo_solicitacao_alterar.xhtml?faces-redirect=true";   

    }
//    
//    public void editar()
//    {
//
//        try
//        {
//            if (!verificarRegistro().isEmpty())
//            {this.estadoCivil = new GrlEstadoCivil();
//                
//                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Tipo de Documento já existe"));
//                return ;
//            }
//
//            this.estadoCivilFacade.edit(this.estadoCivil);
//            init();
//            
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Os Dados foram actualizados com sucesso!"));
//
//        } catch (Exception exception)
//        {
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Impossível alterar os dados , entre em contato com o Administrador de Sistemas", "Impossível alterar os dados , entre em contato com o Administrador de Sistemas"));
//        }
//        
//
//    } // Fim método editar
 
    
    
    public void eliminar(int codigo)
    {
        try
        {   System.out.println("Código: " + codigo);
            estadoCivilFacade.remove(estadoCivilFacade.find(codigo));
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Dados excluídos com sucesso!", "Dados excluídos com sucesso!"));
            init();
            id_estado_civil.init();
            
        } 
        catch (Exception exception)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Impossível eliminar Ta Especialidade, Verifique se está sendo utilizado em algum registro, ou entre em contacto com o Administrador de Sistema."));
        }

    }

    public GrlEstadoCivil getEstadoCivil()
    {
        return estadoCivil;
    }

    public void setEstadoCivil(GrlEstadoCivil estadoCivil)
    {
        this.estadoCivil = estadoCivil;
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
