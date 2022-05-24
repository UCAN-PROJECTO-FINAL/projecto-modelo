/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frt.beans;

import java.io.Serializable;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import ejbs.entities.FrtTransporteTipoAgendamento;
import ejbs.facades.FrtTransporteTipoAgendamentoFacade;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import utils.mensagens.LogFile;

/**
 *
 * @author samuel
 */
@Named(value = "transporteTipoAgendamentoCadastrarBean")
@ViewScoped
public class TransporteTipoAgendamentoCadastrarBean implements Serializable
{
    
    @EJB
    private FrtTransporteTipoAgendamentoFacade frtTransporteTipoAgendamentoFacade;
    
    @Inject
    TransporteTipoAgendamentoCadastrarBean transporteTipoAgendamentoCadastrarBean;
    
    private FrtTransporteTipoAgendamento frtTransporteTipoAgendamento;
    
    private String descricao;

    /**
     * Creates a new instance of TransporteTipoAgendamentoBean
     */
    public TransporteTipoAgendamentoCadastrarBean()
    {
        
    }
    
    @PostConstruct
    public void init()
    {
        descricao = "";
        frtTransporteTipoAgendamento = new FrtTransporteTipoAgendamento();
        
    }
    
    
     public String gravar()
    {
        System.err.println("descricao: "+ descricao);
        try
        {
           if (!verificarRegistro().isEmpty())
           {
               this.frtTransporteTipoAgendamento = new FrtTransporteTipoAgendamento();
               FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Tipo de Solicitacao já existe"));
               return "";
           }
           
           
            //this.grlTipoSolicitacao.setPkTipoSolicitacao(grlTipoSolicitacaoFacade.codigo());
            this.frtTransporteTipoAgendamento.setDescricao(this.descricao);
            this.frtTransporteTipoAgendamentoFacade.create(this.frtTransporteTipoAgendamento);
            init();
            
            LogFile.sucessoMsg(null,"Tipo de Agendamento Cadastrado Com Sucesso !!!");
            
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
     
     
    public List<FrtTransporteTipoAgendamento> verificarRegistro() 
    {
        //return this.grlTipoSolicitacaoFacade.findTipoSolicitacaoByDescricao(this.grlTipoSolicitacao.getDescricao());
        return this.frtTransporteTipoAgendamentoFacade.findTipoAgendamentoByDescricao(this.descricao);
    }
    
    public String alterar(FrtTransporteTipoAgendamento tipoAgendamento) 
    {

        this.frtTransporteTipoAgendamento = frtTransporteTipoAgendamento;
        

        return " /index.xhtml?faces-redirect=true";   

    }
    
    public void editar()
    {

        try
        {
            if (!verificarRegistro().isEmpty())
            {this.frtTransporteTipoAgendamento = new FrtTransporteTipoAgendamento();
                
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Tipo de Documento já existe"));
                return ;
            }

            this.frtTransporteTipoAgendamentoFacade.edit(this.frtTransporteTipoAgendamento);
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
            frtTransporteTipoAgendamentoFacade.remove(frtTransporteTipoAgendamentoFacade.find(codigo));
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Dados excluídos com sucesso!", "Dados excluídos com sucesso!"));
            init();
            transporteTipoAgendamentoCadastrarBean.init();
            
        } 
        catch (Exception exception)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Impossível eliminar Tipo Documento, Verifique se está sendo utilizado em algum registro, ou entre em contacto com o Administrador de Sistema."));
        }

    }
    
    
    

    public FrtTransporteTipoAgendamento getFrtTransporteTipoAgendamento()
    {
        return frtTransporteTipoAgendamento;
    }

    public void setFrtTransporteTipoAgendamento(FrtTransporteTipoAgendamento frtTransporteTipoAgendamento)
    {
        this.frtTransporteTipoAgendamento = frtTransporteTipoAgendamento;
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
