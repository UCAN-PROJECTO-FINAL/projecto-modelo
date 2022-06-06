/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frt.beans;

import java.io.Serializable;
import javax.inject.Named;
import ejbs.facades.RhEspecialidadeFacade;
import ejbs.entities.RhEspecialidade;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
//import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import utils.mensagens.LogFile;

/**
 *
 * @author smakambo
 */
@Named(value = "transporteFuncionarioEspecialidadeCadastrarBean")
@SessionScoped
public class FrtTransporteFuncionarioEspecialidadeCadastrarBean implements Serializable
{
    @EJB
    private RhEspecialidadeFacade rhEspecialidadeFacade;

    private RhEspecialidade rhEspecialidade;

    private boolean btnSalvar = true;
    private boolean btnFormularioCadastro;
    private boolean tabListar = true;
    private boolean estado;

    /**
     * Creates a new instance of TransporteFuncionarioEspecialidadeCadastrarBean
     */
    public FrtTransporteFuncionarioEspecialidadeCadastrarBean()
    {
        
    }
    
    
    @PostConstruct
    public void init()
    {

        rhEspecialidade = new RhEspecialidade();
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
        rhEspecialidade = new RhEspecialidade();

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
                this.rhEspecialidade = new RhEspecialidade();
                //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Tipo de solicitação  já existe"));
                LogFile.warnMsg(null, " Esta Informção já existe !!!");
                return "";
            }
            
            //rhEspecialidade.setPkTipoSolicitacao(rhEspecialidadeFacade.codigo());
            this.rhEspecialidadeFacade.create(this.rhEspecialidade);
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

    public List<RhEspecialidade> verificarRegistro()
    {
        return this.rhEspecialidadeFacade.findRhEspecialidadeByDescricao(this.rhEspecialidade.getDescricao());
    }

    public String alterar(RhEspecialidade rhEspecialidade)
    {

        btnEditar();
        this.rhEspecialidade = rhEspecialidade;

        return "";

    }

    public void editar()
    {

        try
        {
            if (!verificarRegistro().isEmpty())
            {
                this.rhEspecialidade = new RhEspecialidade();

                //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Faculdade já existe"));
                LogFile.warnMsg(null, " Esta Informação Ja Existe !!!");
                return;
            }

            this.rhEspecialidadeFacade.edit(this.rhEspecialidade);
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

    public void eliminar(RhEspecialidade rhEspecialidade)
    {
        try
        {
            rhEspecialidadeFacade.remove(rhEspecialidadeFacade.find(rhEspecialidade.getPkEspecialidade()));
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Dados excluídos com sucesso!", "Dados excluídos com sucesso!"));
             LogFile.sucessoMsg(null, " Dados Excluidos Com Sucesso !!!");
            init();
        } catch (Exception exception)
        {
            //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Impossível eliminar Tipo Documento, Verifique se está sendo utilizado em algum registro, ou entre em contacto com o Administrador de Sistema."));
            LogFile.erroMsg(null, "\"Erro\", \"Impossível eliminar esta Informação, Verifique se está sendo utilizado em algum registro, ou entre em contacto com o Administrador de Sistema.");
        }

    }

    public List<RhEspecialidade> listaSolicitacao()
    {
        return this.rhEspecialidadeFacade.findAll();
    }

    public RhEspecialidade getRhEspecialidade()
    {
        return rhEspecialidade;
    }

    public void setRhEspecialidade(RhEspecialidade rhEspecialidade)
    {
        this.rhEspecialidade = rhEspecialidade;
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
