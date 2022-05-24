/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frt.beans;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import ejbs.facades.FrtTransporteSolicitacaoEstadoFacade;
import ejbs.entities.FrtTransporteSolicitacaoEstado;
import java.util.List;
import javax.annotation.PostConstruct;
//import grl.beans.TipoSolicitacaoBean;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import utils.mensagens.LogFile;

/**
 *
 * @author smakambo
 */
@Named(value = "transporteEstadoSolicitacaoCadastrarBean")
@SessionScoped
public class TransporteEstadoSolicitacaoCadastrarBean implements Serializable
{
    
    @EJB
    private FrtTransporteSolicitacaoEstadoFacade frtTransporteSolicitacaoEstadoFacade;
    
    @Inject
    TransporteEstadoSolicitacaoCadastrarBean transporteEstadoSolicitacaoCadastrarBean;
    
    private FrtTransporteSolicitacaoEstado frtTransporteSolicitacaoEstado;
    
    private String descricao;

    public TransporteEstadoSolicitacaoCadastrarBean() 
    {
        
    }
    
     @PostConstruct
    public void init()
    {
        descricao = "";
        frtTransporteSolicitacaoEstado = new FrtTransporteSolicitacaoEstado();
        
    }
    
     
    public String gravar()
    {
        System.err.println("descricao: "+ descricao);
        try
        {
           if (!verificarRegistro().isEmpty())
           {
               this.frtTransporteSolicitacaoEstado = new FrtTransporteSolicitacaoEstado();
               FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Tipo de Solicitacao já existe"));
               return "";
           }
           
           
            //this.grlTipoSolicitacao.setPkTipoSolicitacao(grlTipoSolicitacaoFacade.codigo());
            this.frtTransporteSolicitacaoEstado.setDescricao(this.descricao);
            this.frtTransporteSolicitacaoEstadoFacade.create(this.frtTransporteSolicitacaoEstado);
            init();
            
            LogFile.sucessoMsg(null,"Estado da Solicitação Cadastrado Com Sucesso !!!");
            
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
     
     
    public List<FrtTransporteSolicitacaoEstado> verificarRegistro() 
    {
        //return this.grlTipoSolicitacaoFacade.findTipoSolicitacaoByDescricao(this.grlTipoSolicitacao.getDescricao());
        return this.frtTransporteSolicitacaoEstadoFacade.findEstadoSolicitacaoByDescricao(this.descricao);
    }
    
    public String alterar(FrtTransporteSolicitacaoEstado estado)
    {        
        this.frtTransporteSolicitacaoEstado = frtTransporteSolicitacaoEstado;
        

        return " /index.xhtml?faces-redirect=true";   

    }
    
    public void editar()
    {

        try
        {
            if (!verificarRegistro().isEmpty())
            {this.frtTransporteSolicitacaoEstado = new FrtTransporteSolicitacaoEstado();
                
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Tipo de Documento já existe"));
                return ;
            }

            this.frtTransporteSolicitacaoEstadoFacade.edit(this.frtTransporteSolicitacaoEstado);
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
            frtTransporteSolicitacaoEstadoFacade.remove(frtTransporteSolicitacaoEstadoFacade.find(codigo));
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Dados excluídos com sucesso !!! ", "Dados excluídos com sucesso !!!"));
            init();
            transporteEstadoSolicitacaoCadastrarBean.init();
            
        } 
        catch (Exception exception)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Impossível eliminar Dados, Verifique se está sendo utilizado em algum registro, ou entre em contacto com o Administrador de Sistema."));
        }

    }
    
    

    public FrtTransporteSolicitacaoEstado getFrtTransporteSolicitacaoEstado() {
        return frtTransporteSolicitacaoEstado;
    }

    public void setFrtTransporteSolicitacaoEstado(FrtTransporteSolicitacaoEstado frtTransporteSolicitacaoEstado) {
        this.frtTransporteSolicitacaoEstado = frtTransporteSolicitacaoEstado;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    
    
    
}
