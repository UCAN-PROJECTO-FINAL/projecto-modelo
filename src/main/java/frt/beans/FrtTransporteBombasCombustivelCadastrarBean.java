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
public class FrtTransporteBombasCombustivelCadastrarBean implements Serializable
{
    
    @EJB
    private FrtTransporteBombaCombustivelFacade frtTransporteBombaCombustivelFacade;

    private FrtTransporteBombaCombustivel bombaCombustivel;

    private boolean btnSalvar = true;
    private boolean btnFormularioCadastro;
    private boolean tabListar = true;
    private boolean estado;

    public FrtTransporteBombasCombustivelCadastrarBean()
    {
        
    }
    
    @PostConstruct
    public void init()
    {

        bombaCombustivel = new FrtTransporteBombaCombustivel();
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
        bombaCombustivel = new FrtTransporteBombaCombustivel();

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
                this.bombaCombustivel = new FrtTransporteBombaCombustivel();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Tipo de solicitação  já existe"));
                LogFile.warnMsg(null, " Esta Informação já existe !!!");
                return "";
            }
            
            //bombaCombustivel.setPkBombaCombustivel(frtTransporteBombaCombustivelFacade.codigo());
            this.frtTransporteBombaCombustivelFacade.create(this.bombaCombustivel);
            init();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Dados salvos com sucesso!"));
            LogFile.sucessoMsg(null, "Dados Cadastrado Com Sucesso !!!");
        } catch (Exception ex)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao salvar os dados, verifique todos os parametros de inserção!", "Erro ao salvar os dados, verifique todos os parametros de inserção!"));
            LogFile.erroMsg(null, " Erro ao salvar os dados, verifique todos os parametros de inserção! !!!");
            ex.printStackTrace();
        }
        return "";

    } // Fim método gravar 

    public List<FrtTransporteBombaCombustivel> verificarRegistro()
    {
        return this.frtTransporteBombaCombustivelFacade.findBombasCombustivelByDescricao(this.bombaCombustivel.getDescricao());
    }

    public String alterar(FrtTransporteBombaCombustivel bombaCombustivel)
    {

        btnEditar();
        this.bombaCombustivel = bombaCombustivel;

        return "";

    }

    public void editar()
    {

        try
        {
            if (!verificarRegistro().isEmpty())
            {
                this.bombaCombustivel = new FrtTransporteBombaCombustivel();

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Faculdade já existe"));
                return;
            }

            this.frtTransporteBombaCombustivelFacade.edit(this.bombaCombustivel);
            init();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Os Dados foram actualizados com sucesso!"));

        } catch (Exception exception)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Impossível alterar os dados , entre em contato com o Administrador de Sistemas", "Impossível alterar os dados , entre em contato com o Administrador de Sistemas"));
        }
        this.estado = false;
        btnListar();

    } // Fim método editar

    public void eliminar(FrtTransporteBombaCombustivel bombaCombustivel)
    {
        try
        {
            frtTransporteBombaCombustivelFacade.remove(frtTransporteBombaCombustivelFacade.find(bombaCombustivel.getPkBombaCombustivel()));
            //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Dados excluídos com sucesso!", "Dados excluídos com sucesso!"));
            LogFile.erroMsg(null, "Dados excluido Com Sucesso !!!");
            init();
        } catch (Exception exception)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Impossível eliminar Tipo Documento, Verifique se está sendo utilizado em algum registro, ou entre em contacto com o Administrador de Sistema."));
        }

    }

    public List<FrtTransporteBombaCombustivel> listaBombasCombustivel()
    {
        return this.frtTransporteBombaCombustivelFacade.findAll();
    }


    public FrtTransporteBombaCombustivel getBombaCombustivel()
    {
        return bombaCombustivel;
    }

    public void setBombaCombustivel(FrtTransporteBombaCombustivel bombaCombustivel)
    {
        this.bombaCombustivel = bombaCombustivel;
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
