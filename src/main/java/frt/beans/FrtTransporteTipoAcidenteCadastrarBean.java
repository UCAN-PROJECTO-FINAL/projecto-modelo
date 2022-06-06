/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frt.beans;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import ejbs.facades.FrtTransporteTipoAcidenteFacade;
import ejbs.entities.FrtTransporteTipoAcidente;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import utils.mensagens.LogFile;

/**
 *
 * @author smakambo
 */
@Named(value = "transporteTipoAcidenteCadastrarBean")
@SessionScoped
public class FrtTransporteTipoAcidenteCadastrarBean implements Serializable
{
    @EJB
    private FrtTransporteTipoAcidenteFacade frtTransporteTipoAcidenteFacade;

    private FrtTransporteTipoAcidente frtTransporteTipoAcidente;

    private boolean btnSalvar = true;
    private boolean btnFormularioCadastro;
    private boolean tabListar = true;
    private boolean estado;

    /**
     * Creates a new instance of TransporteTipoAcidenteCadastrarBean
     */
    public FrtTransporteTipoAcidenteCadastrarBean()
    {
        
    }
    
    
    @PostConstruct
    public void init()
    {

        frtTransporteTipoAcidente = new FrtTransporteTipoAcidente();
    }

    //Metodos para formulário
    public void voltar()
    {

        this.btnSalvar = true;
        this.btnFormularioCadastro = false;
        this.tabListar = false;
        this.estado = false;

    }

    public void novo()
    {

        this.btnSalvar = false;
        this.btnFormularioCadastro = true;
        this.tabListar = false;
        this.estado = false;
        frtTransporteTipoAcidente = new FrtTransporteTipoAcidente();

    }

    public void btnListar()
    {

        this.tabListar = true;
        this.btnFormularioCadastro = false;
        this.btnSalvar = true;
        this.estado = false;
    }

    public void btnEditar()
    {

        this.estado = true;
        this.tabListar = false;

    }

    public String gravar()
    {
        try
        {
            if (!verificarRegistro().isEmpty())
            {
                this.frtTransporteTipoAcidente = new FrtTransporteTipoAcidente();
                //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Tipo de solicitação  já existe"));
                LogFile.warnMsg(null, " Esta Informção já existe !!!");
                return "";
            }
            
            //frtTransporteTipoAcidente.setPkTipoSolicitacao(frtTransporteTipoAcidenteFacade.codigo());
            this.frtTransporteTipoAcidenteFacade.create(this.frtTransporteTipoAcidente);
            init();

            //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Dados salvos com sucesso!"));
            LogFile.sucessoMsg(null, "Dados Cadastrado Com Sucesso !!!");
        } catch (Exception ex)
        {
            //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao salvar os dados, verifique todos os parametros de inserção!", "Erro ao salvar os dados, verifique todos os parametros de inserção!"));
            LogFile.erroMsg(null, " Erro ao salvar os dados, verifique todos os parametros de inserção! !!!");
            ex.printStackTrace();
        }
        return "";

    } // Fim método gravar 

    public List<FrtTransporteTipoAcidente> verificarRegistro()
    {
        return this.frtTransporteTipoAcidenteFacade.findTipoAcidenteByDescricao(this.frtTransporteTipoAcidente.getDescricao());
    }

    public String alterar(FrtTransporteTipoAcidente frtTransporteTipoAcidente)
    {

        btnEditar();
        this.frtTransporteTipoAcidente = frtTransporteTipoAcidente;

        return "";

    }

    public void editar()
    {

        try
        {
            if (!verificarRegistro().isEmpty())
            {
                this.frtTransporteTipoAcidente = new FrtTransporteTipoAcidente();

                //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Faculdade já existe"));
                LogFile.warnMsg(null, " Esta Informação Ja Existe !!!");
                return;
            }

            this.frtTransporteTipoAcidenteFacade.edit(this.frtTransporteTipoAcidente);
            init();
            //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Os Dados foram actualizados com sucesso!"));
            LogFile.sucessoMsg(null, " Os Dados foram actualizados com sucesso! !!!");

        } catch (Exception exception)
        {
           // FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Impossível alterar os dados , entre em contato com o Administrador de Sistemas", "Impossível alterar os dados , entre em contato com o Administrador de Sistemas"));
             LogFile.erroMsg(null, "Impossível alterar os dados , entre em contato com o Administrador de Sistemas\", \"Impossível alterar os dados , entre em contato com o Administrador de Sistemas !!!");
        }
        this.estado = false;
        btnListar();

    } // Fim método editar

    public void eliminar(FrtTransporteTipoAcidente frtTransporteTipoAcidente)
    {
        try
        {
            frtTransporteTipoAcidenteFacade.remove(frtTransporteTipoAcidenteFacade.find(frtTransporteTipoAcidente.getPkTipoAcidente()));
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Dados excluídos com sucesso!", "Dados excluídos com sucesso!"));
             LogFile.sucessoMsg(null, " Dados Excluidos Com Sucesso !!!");
            init();
        } catch (Exception exception)
        {
            //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Impossível eliminar Tipo Documento, Verifique se está sendo utilizado em algum registro, ou entre em contacto com o Administrador de Sistema."));
            LogFile.erroMsg(null, "\"Erro\", \"Impossível eliminar esta Informação, Verifique se está sendo utilizado em algum registro, ou entre em contacto com o Administrador de Sistema.");
        }

    }

    public List<FrtTransporteTipoAcidente> listaTipoAcidente()
    {
        return this.frtTransporteTipoAcidenteFacade.findAll();
    }

    public FrtTransporteTipoAcidente getFrtTransporteTipoAcidente()
    {
        return frtTransporteTipoAcidente;
    }

    public void setFrtTransporteTipoAcidente(FrtTransporteTipoAcidente frtTransporteTipoAcidente)
    {
        this.frtTransporteTipoAcidente = frtTransporteTipoAcidente;
    }

    public boolean isBtnSalvar()
    {
        return btnSalvar;
    }

    public void setBtnSalvar(boolean btnSalvar)
    {
        this.btnSalvar = btnSalvar;
    }

    public boolean isBtnFormularioCadastro()
    {
        return btnFormularioCadastro;
    }

    public void setBtnFormularioCadastro(boolean btnFormularioCadastro)
    {
        this.btnFormularioCadastro = btnFormularioCadastro;
    }

    public boolean isTabListar()
    {
        return tabListar;
    }

    public void setTabListar(boolean tabListar)
    {
        this.tabListar = tabListar;
    }

    public boolean isEstado()
    {
        return estado;
    }

    public void setEstado(boolean estado)
    {
        this.estado = estado;
    }
    
    
    
}
