/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frt.beans;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import ejbs.facades.FrtTransporteFormaPagamentoFacade;
import ejbs.entities.FrtTransporteFormaPagamento;
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
@Named(value = "transporteFormaPagamentoCadastrarBean")
@SessionScoped
public class TransporteFormaPagamentoCadastrarBean implements Serializable
{
    @EJB
    private FrtTransporteFormaPagamentoFacade frtTransporteFormaPagamentoFacade;
    
    @Inject
    TransporteFormaPagamentoCadastrarBean transporteFormaPagamentoCadastrarBean;
    
    private FrtTransporteFormaPagamento frtTransporteFormaPagamento;
    
    private String descricao;

    public TransporteFormaPagamentoCadastrarBean() 
    {
        
    }
    
    @PostConstruct
    public void init()
    {
        descricao = "";
        frtTransporteFormaPagamento = new FrtTransporteFormaPagamento();
        
    }
    
      public String gravar()
    {
        System.err.println("descricao: "+ descricao);
        try
        {
           if (!verificarRegistro().isEmpty())
           {
               this.frtTransporteFormaPagamento = new FrtTransporteFormaPagamento();
               FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Tipo de Solicitacao já existe"));
               return "";
           }
           
           
            //this.grlTipoSolicitacao.setPkTipoSolicitacao(grlTipoSolicitacaoFacade.codigo());
            this.frtTransporteFormaPagamento.setDescricao(this.descricao);
            this.frtTransporteFormaPagamentoFacade.create(this.frtTransporteFormaPagamento);
            init();
            
            LogFile.sucessoMsg(null,"Forma de Pagamento Cadastrado Com Sucesso !!!");
            
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

    public List<FrtTransporteFormaPagamento> verificarRegistro() 
    {
//        return this.grlTipoSolicitacaoFacade.findTipoSolicitacaoByDescricao(this.grlTipoSolicitacao.getDescricao());
        return this.frtTransporteFormaPagamentoFacade.findFormaPaByDescrigamentocao(this.descricao);
    }
    
    
     
    public String alterar(FrtTransporteFormaPagamento formapagamento) 
    {

        this.frtTransporteFormaPagamento = frtTransporteFormaPagamento;
        

        return " /modulos/frtVisao/frtTransporte/frt_transporte_tipo_solicitacao_alterar.xhtml?faces-redirect=true";   

    }
    
    public void editar()
    {

        try
        {
            if (!verificarRegistro().isEmpty())
            {this.frtTransporteFormaPagamento = new FrtTransporteFormaPagamento();
                
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Tipo de Documento já existe"));
                return ;
            }

            this.frtTransporteFormaPagamentoFacade.edit(this.frtTransporteFormaPagamento);
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
            frtTransporteFormaPagamentoFacade.remove(frtTransporteFormaPagamentoFacade.find(codigo));
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Dados excluídos com sucesso!", "Dados excluídos com sucesso!"));
            init();
            transporteFormaPagamentoCadastrarBean.init();
            
        } 
        catch (Exception exception)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Impossível eliminar Tipo Documento, Verifique se está sendo utilizado em algum registro, ou entre em contacto com o Administrador de Sistema."));
        }

    }

    public FrtTransporteFormaPagamento getFrtTransporteFormaPagamento() {
        return frtTransporteFormaPagamento;
    }

    public void setFrtTransporteFormaPagamento(FrtTransporteFormaPagamento frtTransporteFormaPagamento) {
        this.frtTransporteFormaPagamento = frtTransporteFormaPagamento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    
    
    
    
    
}
