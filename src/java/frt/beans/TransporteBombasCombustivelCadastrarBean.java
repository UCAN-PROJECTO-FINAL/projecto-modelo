/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frt.beans;

import java.io.Serializable;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import ejbs.entities.FrtTransporteBombaCombustivel;
import ejbs.facades.FrtTransporteBombaCombustivelFacade;        
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

@Named(value = "transporteBombasCombustivelCadastrarBean")
@ViewScoped
public class TransporteBombasCombustivelCadastrarBean implements Serializable
{
    
    @EJB
    private FrtTransporteBombaCombustivelFacade frtTransporteBombaCombustivelFacade;
    
    @Inject
    TransporteBombasCombustivelCadastrarBean transporteBombasCombustivelCadastrarBean;
    
    private FrtTransporteBombaCombustivel frtTransporteBombaCombustivel;
    
    private String descricao;

    public TransporteBombasCombustivelCadastrarBean()
    {
        
    }
    
    @PostConstruct
    public void init()
    {
        descricao = "";
        frtTransporteBombaCombustivel = new FrtTransporteBombaCombustivel();
        
    }
    
    
    public String gravar()
    {
        System.err.println("descricao: "+ descricao);
        try
        {
           if (!verificarRegistro().isEmpty())
           {
               this.frtTransporteBombaCombustivel = new FrtTransporteBombaCombustivel();
               FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Tipo de Solicitacao já existe"));
               return "";
           }
           
           
            //this.grlTipoSolicitacao.setPkTipoSolicitacao(grlTipoSolicitacaoFacade.codigo());
            this.frtTransporteBombaCombustivel.setDescricao(this.descricao);
            this.frtTransporteBombaCombustivelFacade.create(this.frtTransporteBombaCombustivel);
            init();
            
            LogFile.sucessoMsg(null,"Bomba de Combústivel Cadastrado Com Sucesso !!!");
            
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
     
     
    public List<FrtTransporteBombaCombustivel> verificarRegistro() 
    {
        //return this.grlTipoSolicitacaoFacade.findTipoSolicitacaoByDescricao(this.grlTipoSolicitacao.getDescricao());
        return this.frtTransporteBombaCombustivelFacade.findBombasCombustivelByDescricao(this.descricao);
    }
    
    public String alterar(FrtTransporteBombaCombustivel bombas)
    {        
        this.frtTransporteBombaCombustivel = frtTransporteBombaCombustivel;
        

        return " /index.xhtml?faces-redirect=true";   

    }
    
    public void editar()
    {

        try
        {
            if (!verificarRegistro().isEmpty())
            {this.frtTransporteBombaCombustivel = new FrtTransporteBombaCombustivel();
                
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Tipo de Documento já existe"));
                return ;
            }

            this.frtTransporteBombaCombustivelFacade.edit(this.frtTransporteBombaCombustivel);
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
        {   System.out.println("bombas: " + codigo);
            frtTransporteBombaCombustivelFacade.remove(frtTransporteBombaCombustivelFacade.find(codigo));
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Dados excluídos com sucesso !!! ", "Dados excluídos com sucesso !!!"));
            init();
            transporteBombasCombustivelCadastrarBean.init();
            
        } 
        catch (Exception exception)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Impossível eliminar Dados, Verifique se está sendo utilizado em algum registro, ou entre em contacto com o Administrador de Sistema."));
        }

    }
    
    

    public FrtTransporteBombaCombustivel getFrtTransporteBombaCombustivel() {
        return frtTransporteBombaCombustivel;
    }

    public void setFrtTransporteBombaCombustivel(FrtTransporteBombaCombustivel frtTransporteBombaCombustivel) {
        this.frtTransporteBombaCombustivel = frtTransporteBombaCombustivel;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    
    
    
    
    
    
}
