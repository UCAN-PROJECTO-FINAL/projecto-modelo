/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frt.beans;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import ejbs.facades.FrtTransporteTipoManutencaoFacade;
import ejbs.entities.FrtTransporteTipoManutencao;
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
@Named(value = "transporteTipoManutencaoCadastrarBean")
@SessionScoped
public class FrtTransporteTipoManutencaoCadastrarBean implements Serializable
{

    @EJB
    private FrtTransporteTipoManutencaoFacade frtTransporteTipoManutencaoFacade;

    private FrtTransporteTipoManutencao frtTransporteTipoManutencao;

    private boolean btnSalvar = true;
    private boolean btnFormularioCadastro;
    private boolean tabListar = true;
    private boolean estado;

    /**
     * Creates a new instance of TransporteTipoManutencaoCadastrarBean
     */
    public FrtTransporteTipoManutencaoCadastrarBean()
    {

    }

    @PostConstruct
    public void init()
    {

        frtTransporteTipoManutencao = new FrtTransporteTipoManutencao();
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
        frtTransporteTipoManutencao = new FrtTransporteTipoManutencao();

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
                this.frtTransporteTipoManutencao = new FrtTransporteTipoManutencao();
                //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Tipo de solicitação  já existe"));
                LogFile.warnMsg(null, " Esta Informção já existe !!!");
                return "";
            }

            //frtTransporteTipoManutencao.setPkTipoSolicitacao(frtTransporteTipoManutencaoFacade.codigo());
            this.frtTransporteTipoManutencaoFacade.create(this.frtTransporteTipoManutencao);
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

    public List<FrtTransporteTipoManutencao> verificarRegistro()
    {
        return this.frtTransporteTipoManutencaoFacade.findTipoManutencaoByDescricao(this.frtTransporteTipoManutencao.getDescricao());
    }

    public String alterar(FrtTransporteTipoManutencao frtTransporteTipoManutencao)
    {

        btnEditar();
        this.frtTransporteTipoManutencao = frtTransporteTipoManutencao;

        return "";

    }

    public void editar()
    {

        try
        {
            if (!verificarRegistro().isEmpty())
            {
                this.frtTransporteTipoManutencao = new FrtTransporteTipoManutencao();

                //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Faculdade já existe"));
                LogFile.warnMsg(null, " Esta Informação Ja Existe !!!");
                return;
            }

            this.frtTransporteTipoManutencaoFacade.edit(this.frtTransporteTipoManutencao);
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

    public void eliminar(FrtTransporteTipoManutencao frtTransporteTipoManutencao)
    {
        try
        {
            frtTransporteTipoManutencaoFacade.remove(frtTransporteTipoManutencaoFacade.find(frtTransporteTipoManutencao.getPkTipoManutencao()));
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Dados excluídos com sucesso!", "Dados excluídos com sucesso!"));
            LogFile.sucessoMsg(null, " Dados Excluidos Com Sucesso !!!");
            init();
        } catch (Exception exception)
        {
            //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Impossível eliminar Tipo Documento, Verifique se está sendo utilizado em algum registro, ou entre em contacto com o Administrador de Sistema."));
            LogFile.erroMsg(null, "\"Erro\", \"Impossível eliminar esta Informação, Verifique se está sendo utilizado em algum registro, ou entre em contacto com o Administrador de Sistema.");
        }

    }

    public List<FrtTransporteTipoManutencao> listaTipoManutencao()
    {
        return this.frtTransporteTipoManutencaoFacade.findAll();
    }
    
    

    public FrtTransporteTipoManutencao getFrtTransporteTipoManutencao()
    {
        return frtTransporteTipoManutencao;
    }

    public void setFrtTransporteTipoManutencao(FrtTransporteTipoManutencao frtTransporteTipoManutencao)
    {
        this.frtTransporteTipoManutencao = frtTransporteTipoManutencao;
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
