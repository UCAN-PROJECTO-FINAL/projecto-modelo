/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frt.beans;

import java.io.Serializable;
import javax.inject.Named;

import ejbs.facades.GrlTipoSolicitacaoFacade;
import ejbs.entities.GrlTipoSolicitacao;
import grl.beans.TipoSolicitacaoBean;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
//import utils.mensagens.LogFile;
//import utils.mensagens.Mensagem;
import utils.mensagens.LogFile;

/**
 *
 * @author samuel
 */
@Named(value = "transporteTipoSolicitacaoCadastrarBean")
@SessionScoped
public class TransporteTipoSolicitacaoCadastrarBean implements Serializable{
    
    @EJB
    private GrlTipoSolicitacaoFacade grlTipoSolicitacaoFacade;
    
    @Inject
    TipoSolicitacaoBean tipoSolicitacaoBean;
    
    private GrlTipoSolicitacao grlTipoSolicitacao;
    
    private String descricao;

    /**
     * Creates a new instance of SolicitacaoCadastrarBean
     */
    
    
    public TransporteTipoSolicitacaoCadastrarBean() {
    }
    
    @PostConstruct
    public void init()
    {
        descricao = "";
        grlTipoSolicitacao = new GrlTipoSolicitacao();
        
    }
    
    
    public String gravar()
    {
        System.err.println("descricao: "+ descricao);
        try
        {
           if (!verificarRegistro().isEmpty())
           {
               this.grlTipoSolicitacao = new GrlTipoSolicitacao();
               FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Tipo de Solicitacao já existe"));
               return "";
           }
           
           
           // this.grlTipoSolicitacao.setPkTipoSolicitacao(grlTipoSolicitacaoFacade.codigo());
            this.grlTipoSolicitacao.setDescricao(this.descricao);
            this.grlTipoSolicitacaoFacade.create(this.grlTipoSolicitacao);
            init();
            
            LogFile.sucessoMsg(null,"Tipo de Solicitação Cadastrado Com Sucesso !!!");
            
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

    public List<GrlTipoSolicitacao> verificarRegistro() 
    {
//        return this.grlTipoSolicitacaoFacade.findTipoSolicitacaoByDescricao(this.grlTipoSolicitacao.getDescricao());
        return this.grlTipoSolicitacaoFacade.findTipoSolicitacaoByDescricao(this.descricao);
    }
    
    
     
    public String alterar(GrlTipoSolicitacao tipoSolicitacao) 
    {

        this.grlTipoSolicitacao = grlTipoSolicitacao;
        

        return " /modulos/frtVisao/frtTransporte/frt_transporte_tipo_solicitacao_alterar.xhtml?faces-redirect=true";   

    }
    
    public void editar()
    {

        try
        {
            if (!verificarRegistro().isEmpty())
            {this.grlTipoSolicitacao = new GrlTipoSolicitacao();
                
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Tipo de Documento já existe"));
                return ;
            }

            this.grlTipoSolicitacaoFacade.edit(this.grlTipoSolicitacao);
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
            grlTipoSolicitacaoFacade.remove(grlTipoSolicitacaoFacade.find(codigo));
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Dados excluídos com sucesso!", "Dados excluídos com sucesso!"));
            init();
            tipoSolicitacaoBean.init();
            
        } 
        catch (Exception exception)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Impossível eliminar Tipo Documento, Verifique se está sendo utilizado em algum registro, ou entre em contacto com o Administrador de Sistema."));
        }

    }

    public GrlTipoSolicitacao getGrlTipoSolicitacao() {
        return grlTipoSolicitacao;
    }

    public void setGrlTipoSolicitacao(GrlTipoSolicitacao grlTipoSolicitacao) {
        this.grlTipoSolicitacao = grlTipoSolicitacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
 
    
    
    
}
