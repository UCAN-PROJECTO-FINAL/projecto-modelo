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
public class FrtTransporteTipoAgendamentoCadastrarBean implements Serializable
{
    
    @EJB
    private FrtTransporteTipoAgendamentoFacade frtTransporteTipoAgendamentoFacade;

    private FrtTransporteTipoAgendamento frtTransporteTipoAgendamento;

    private boolean btnSalvar = true;
    private boolean btnFormularioCadastro;
    private boolean tabListar = true;
    private boolean estado;

    /**
     * Creates a new instance of TransporteTipoAgendamentoBean
     */
    public FrtTransporteTipoAgendamentoCadastrarBean()
    {
        
    }
    
    
    @PostConstruct
    public void init()
    {

        frtTransporteTipoAgendamento = new FrtTransporteTipoAgendamento();
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
        frtTransporteTipoAgendamento = new FrtTransporteTipoAgendamento();

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
                this.frtTransporteTipoAgendamento = new FrtTransporteTipoAgendamento();
                //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Tipo de solicitação  já existe"));
                LogFile.warnMsg(null, " Esta Informção já existe !!!");
                return "";
            }
            
            //frtTransporteTipoAgendamento.setPkTipoSolicitacao(frtTransporteTipoAgendamentoFacade.codigo());
            this.frtTransporteTipoAgendamentoFacade.create(this.frtTransporteTipoAgendamento);
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

    public List<FrtTransporteTipoAgendamento> verificarRegistro()
    {
        return this.frtTransporteTipoAgendamentoFacade.findTipoAgendamentoByDescricao(this.frtTransporteTipoAgendamento.getDescricao());
    }

    public String alterar(FrtTransporteTipoAgendamento frtTransporteTipoAgendamento)
    {

        btnEditar();
        this.frtTransporteTipoAgendamento = frtTransporteTipoAgendamento;

        return "";

    }

    public void editar()
    {

        try
        {
            if (!verificarRegistro().isEmpty())
            {
                this.frtTransporteTipoAgendamento = new FrtTransporteTipoAgendamento();

                //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Faculdade já existe"));
                LogFile.warnMsg(null, " Esta Informação Ja Existe !!!");
                return;
            }

            this.frtTransporteTipoAgendamentoFacade.edit(this.frtTransporteTipoAgendamento);
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

    public void eliminar(FrtTransporteTipoAgendamento frtTransporteTipoAgendamento)
    {
        try
        {
            frtTransporteTipoAgendamentoFacade.remove(frtTransporteTipoAgendamentoFacade.find(frtTransporteTipoAgendamento.getPkTipoAgendamento()));
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Dados excluídos com sucesso!", "Dados excluídos com sucesso!"));
             LogFile.sucessoMsg(null, " Dados Excluidos Com Sucesso !!!");
            init();
        } catch (Exception exception)
        {
            //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Impossível eliminar Tipo Documento, Verifique se está sendo utilizado em algum registro, ou entre em contacto com o Administrador de Sistema."));
            LogFile.erroMsg(null, "\"Erro\", \"Impossível eliminar esta Informação, Verifique se está sendo utilizado em algum registro, ou entre em contacto com o Administrador de Sistema.");
        }

    }

    public List<FrtTransporteTipoAgendamento> listaTipoAgendamento()
    {
        return this.frtTransporteTipoAgendamentoFacade.findAll();
    }

    public FrtTransporteTipoAgendamento getFrtTransporteTipoAgendamento()
    {
        return frtTransporteTipoAgendamento;
    }

    public void setFrtTransporteTipoAgendamento(FrtTransporteTipoAgendamento frtTransporteTipoAgendamento)
    {
        this.frtTransporteTipoAgendamento = frtTransporteTipoAgendamento;
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
