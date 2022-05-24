/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frt.beans;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import ejbs.facades.FrtTransporteTipoCombustivelFacade;
import ejbs.entities.FrtTransporteTipoCombustivel;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import utils.mensagens.LogFile;

/**
 *
 * @author smakambo
 */

@Named(value = "transporteTipoCombustivelCadastrarBean")
@SessionScoped
public class TransporteTipoCombustivelCadastrarBean implements Serializable
{
    @EJB
    private FrtTransporteTipoCombustivelFacade frtTransporteTipoCombustivelFacade;
    
    @Inject
    TransporteTipoCombustivelCadastrarBean transporteTipoCombustivelCadastrarBean;
    
    private FrtTransporteTipoCombustivel frtTransporteTipoCombustivel;
    
    private String descricao;

    public TransporteTipoCombustivelCadastrarBean()
    {
        
    }
    
     
    @PostConstruct
    public void init()
    {
        descricao = "";
        frtTransporteTipoCombustivel = new FrtTransporteTipoCombustivel();
        
    }
    
    
        
    public String gravar()
    {
        System.err.println("descricao: "+ descricao);
        try
        {
           if (!verificarRegistro().isEmpty())
           {
               this.frtTransporteTipoCombustivel = new FrtTransporteTipoCombustivel();
               FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Tipo de Combustivel já existe"));
               return "";
           }
           
           
            //this.grlTipoSolicitacao.setPkTipoSolicitacao(grlTipoSolicitacaoFacade.codigo());
            this.frtTransporteTipoCombustivel.setDescricao(this.descricao);
            this.frtTransporteTipoCombustivelFacade.create(this.frtTransporteTipoCombustivel);
            init();
            
            LogFile.sucessoMsg(null,"Tipo de Combústivel Cadastrado Com Sucesso !!!");
            
            //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Dados salvos com sucesso!"));
        } 
        catch (Exception ex)
        {
              LogFile.erroMsg(null," Erro ao salvar os dados, verifique todos os parametros de inserção !!!");
            //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao salvar os dados, verifique todos os parametros de inserção!", "Erro ao salvar os dados, verifique todos os parametros de inserção!"));
            ex.printStackTrace();
        }
           return "";
           
    } // Fim método gravar   

    public List<FrtTransporteTipoCombustivel> verificarRegistro() 
    {
//        return this.grlTipoSolicitacaoFacade.findTipoSolicitacaoByDescricao(this.grlTipoSolicitacao.getDescricao());
        return this.frtTransporteTipoCombustivelFacade.findTipoComustivelByDescricao(this.descricao);
    }
    
    
     
    public String alterar(FrtTransporteTipoCombustivel tipoCombustivel) 
    {

        this.frtTransporteTipoCombustivel = frtTransporteTipoCombustivel;
        

        return " /modulos/frtVisao/frtTransporte/frt_transporte_tipo_solicitacao_alterar.xhtml?faces-redirect=true";   

    }
    
    public void editar()
    {

        try
        {
            if (!verificarRegistro().isEmpty())
            {this.frtTransporteTipoCombustivel = new FrtTransporteTipoCombustivel();
                
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Tipo de Documento já existe"));
                return ;
            }

            this.frtTransporteTipoCombustivelFacade.edit(this.frtTransporteTipoCombustivel);
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
            frtTransporteTipoCombustivelFacade.remove(frtTransporteTipoCombustivelFacade.find(codigo));
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Dados excluídos com sucesso!", "Dados excluídos com sucesso!"));
            init();
            transporteTipoCombustivelCadastrarBean.init();
            
        } 
        catch (Exception exception)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Impossível eliminar Tipo Documento, Verifique se está sendo utilizado em algum registro, ou entre em contacto com o Administrador de Sistema."));
        }

    }

    public FrtTransporteTipoCombustivel getFrtTransporteTipoCombustivel()
    {
        return frtTransporteTipoCombustivel;
    }

    public void setFrtTransporteTipoCombustivel(FrtTransporteTipoCombustivel frtTransporteTipoCombustivel)
    {
        this.frtTransporteTipoCombustivel = frtTransporteTipoCombustivel;
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
