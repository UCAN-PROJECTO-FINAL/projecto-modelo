/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rh.beans;

import ejbs.entities.RhConfiguracoes;
import ejbs.entities.RhEspecialidade;
import ejbs.entities.RhFuncao;
import ejbs.entities.RhNivelAcademico;
import ejbs.entities.RhTipoFuncionario;
import ejbs.facades.EstruturaLogicaFacade;
import ejbs.facades.RhEspecialidadeFacade;
import ejbs.facades.RhFuncaoFacade;
import ejbs.facades.RhNivelAcademicoFacade;
import ejbs.facades.RhTipoFuncionarioFacade;
import ejbs.facades.RhConfiguracoesFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import utils.ExcepcaoCarregamentoTabelasExcel;
//import utils.mensagens.Mensagem;
import utils.mensagens.LogFile;
import utils.mensagens.Mensagem;

/**
 *
 * @author smakambo
 */

@Named(value = "rhConfiguracoesBean")
@ViewScoped
public class RhConfiguracoesBean implements Serializable
{
    
        
    @EJB
    EstruturaLogicaFacade estruturaLogicaFacade;
    
         
    @EJB
    RhConfiguracoesFacade rhConfiguracoesFacade;
    
    @EJB
    RhEspecialidadeFacade rhEspecialidadeFacade;
    
    @EJB
    RhFuncaoFacade rhFuncaoFacade;
    
    @EJB
    RhNivelAcademicoFacade rhNivelAcademicoFacade;
    
    @EJB
    RhTipoFuncionarioFacade rhTipoFuncionarioFacade;
    
    
    
    private RhConfiguracoes  rhConfiguracoes;
    private RhEspecialidade  rhEspecialidade;
    private RhFuncao  rhFuncao;
    private RhNivelAcademico  rhNivelAcademico;
    private RhTipoFuncionario  rhTipoFuncionario;
    
    
    private int codigoRhEspecialidade = 0;
    private int codigoRhFuncao = 0;
    private int codigoRhNivelAcademico = 0;  
    private int codigoRhTipoFuncionario = 0; 
    private String codigoEstruturaLogica = "";
    
    

    
    
    
    public RhConfiguracoesBean()
    {
        
        
    }
    
     @PostConstruct
    public void init()
    {
        this.rhConfiguracoes = this.rhConfiguracoesFacade.find();
        
        codigoRhEspecialidade = this.rhConfiguracoes.getFkEspecialidade().getPkEspecialidade();
        System.out.println("Código -" +codigoRhEspecialidade);
        
        codigoRhFuncao = this.rhConfiguracoes.getFkFuncao().getPkFuncao();
        System.out.println("Código -" +codigoRhFuncao);
        
        codigoRhNivelAcademico = this.rhConfiguracoes.getFkNivelAcademico().getPkNivelAcademico();
        System.out.println("Código -" +codigoRhNivelAcademico);
        
        codigoRhTipoFuncionario = this.rhConfiguracoes.getFkTipoFuncionario().getPkTipoFuncionario();
        System.out.println("Código -" +codigoRhTipoFuncionario);
        
        codigoEstruturaLogica = this.rhConfiguracoes.getFkEstruturaLogica().getPkEstruturaLogica();
        System.out.println("Código" +codigoEstruturaLogica);
        
    }
    
    
    public boolean salvarRegister() throws ExcepcaoCarregamentoTabelasExcel
    {
        if (rhConfiguracoes != null)
        {
            try
            {
                rhConfiguracoes.setFkEspecialidade(rhEspecialidadeFacade.find(codigoRhEspecialidade));
                rhConfiguracoes.setFkFuncao(rhFuncaoFacade.find(codigoRhFuncao));
                rhConfiguracoes.setFkNivelAcademico(rhNivelAcademicoFacade.find(codigoRhNivelAcademico));
                rhConfiguracoes.setFkTipoFuncionario(rhTipoFuncionarioFacade.find(codigoRhTipoFuncionario));
                //frtConfiguracoes.setFkRhFuncionario(rhFuncionarioFacade.find(codigoRhFuncionario));
                rhConfiguracoes.setFkEstruturaLogica(estruturaLogicaFacade.find(codigoEstruturaLogica));
                rhConfiguracoesFacade.edit(rhConfiguracoes);
                //gdDocumentoCadastrarBean.initConfiguracao();
            }
            catch (Exception e)
            {
                 Mensagem.enviarJanelaMensagemErro(" Tente novamente!!! ", " Tente novamente!!! ");
            }

            Mensagem.enviarJanelaMensagemInformacao("Configuração gravada com sucesso!", "Configuração gravada com sucesso!");
            return true;
        }

        return false;
    }
        
        
         
    public RhConfiguracoes reporConfiguracoesPadrao()
    {
        rhConfiguracoes = this.rhConfiguracoesFacade.reporConfiguracoesPadrao();
        System.out.println("INICIALIZADA");
        Mensagem.enviarJanelaMensagemInformacao("Configuração gravada com sucesso!", "Configuração gravada com sucesso!");
        return rhConfiguracoes;
    }

   
    
    
    
    public RhEspecialidade getRhEspecialidade()
    {
        return rhEspecialidade;
    }

    public void setRhEspecialidade(RhEspecialidade rhEspecialidade)
    {
        this.rhEspecialidade = rhEspecialidade;
    }

    public RhFuncao getRhFuncao()
    {
        return rhFuncao;
    }

    public void setRhFuncao(RhFuncao rhFuncao)
    {
        this.rhFuncao = rhFuncao;
    }

    public RhNivelAcademico getRhNivelAcademico()
    {
        return rhNivelAcademico;
    }

    public void setRhNivelAcademico(RhNivelAcademico rhNivelAcademico)
    {
        this.rhNivelAcademico = rhNivelAcademico;
    }

    public RhTipoFuncionario getRhTipoFuncionario()
    {
        return rhTipoFuncionario;
    }

    public void setRhTipoFuncionario(RhTipoFuncionario rhTipoFuncionario)
    {
        this.rhTipoFuncionario = rhTipoFuncionario;
    }

    public int getCodigoRhEspecialidade()
    {
        return codigoRhEspecialidade;
    }

    public void setCodigoRhEspecialidade(int codigoRhEspecialidade)
    {
        this.codigoRhEspecialidade = codigoRhEspecialidade;
    }

    public int getCodigoRhFuncao()
    {
        return codigoRhFuncao;
    }

    public void setCodigoRhFuncao(int codigoRhFuncao)
    {
        this.codigoRhFuncao = codigoRhFuncao;
    }

    public int getCodigoRhNivelAcademico()
    {
        return codigoRhNivelAcademico;
    }

    public void setCodigoRhNivelAcademico(int codigoRhNivelAcademico)
    {
        this.codigoRhNivelAcademico = codigoRhNivelAcademico;
    }

    public int getCodigoRhTipoFuncionario()
    {
        return codigoRhTipoFuncionario;
    }

    public void setCodigoRhTipoFuncionario(int codigoRhTipoFuncionario)
    {
        this.codigoRhTipoFuncionario = codigoRhTipoFuncionario;
    }

    public String getCodigoEstruturaLogica()
    {
        return codigoEstruturaLogica;
    }

    public void setCodigoEstruturaLogica(String codigoEstruturaLogica)
    {
        this.codigoEstruturaLogica = codigoEstruturaLogica;
    }

    public RhConfiguracoes getRhConfiguracoes()
    {
        return rhConfiguracoes;
    }

    public void setRhConfiguracoes(RhConfiguracoes rhConfiguracoes)
    {
        this.rhConfiguracoes = rhConfiguracoes;
    }
    
    
            
    
    
    
    
}
