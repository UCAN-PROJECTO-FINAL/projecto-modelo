/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frt.beans;

import estrutura.beans.EstruturaLogicaFisicaCarregarBean;
import ejbs.entities.FrtTransporteConfiguracoes;
import ejbs.entities.EstruturaLogica;
import ejbs.entities.EstruturaLogicaFisica;
import ejbs.entities.GrlEndereco;

import ejbs.entities.GrlEstadoCivil;
import ejbs.entities.RhFuncionario;
import ejbs.entities.GrlPessoa;
import ejbs.entities.GrlSexo;
import ejbs.entities.RhEspecialidade;
import ejbs.entities.RhFuncao;
import ejbs.entities.RhNivelAcademico;
import ejbs.entities.RhTipoFuncionario;
import ejbs.facades.EstruturaLogicaFacade;
import ejbs.facades.FrtTransporteConfiguracoesFacade;
import ejbs.facades.GrlEstadoCivilFacade;
import ejbs.facades.GrlSexoFacade;
import ejbs.facades.RhEspecialidadeFacade;
import ejbs.facades.RhFuncaoFacade;
import ejbs.facades.RhNivelAcademicoFacade;
import ejbs.facades.RhTipoFuncionarioFacade;
import ejbs.facades.RhFuncionarioFacade;
import ejbs.facades.GrlPessoaFacade;

import java.io.Serializable;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import utils.mensagens.LogFile;

/**
 *
 * @author smakambo
 */
@Named(value = "transporteFuncionarioCadastrarBean")
@ViewScoped
public class FrtTransporteFuncionarioCadastrarBean implements Serializable
{

    @EJB
    FrtTransporteConfiguracoesFacade frtConfiguracoesFacade;

    @EJB
    EstruturaLogicaFacade estruturaLogicaFacade;

    @EJB
    GrlSexoFacade grlSexoFacade;

    @EJB
    GrlEstadoCivilFacade grlEstadoCivilFacade;

    @EJB
    RhFuncaoFacade rhFuncaoFacade;

    @EJB
    RhEspecialidadeFacade rhEspecialidadeFacade;

    @EJB
    RhTipoFuncionarioFacade rhTipoFuncionarioFacade;

    @EJB
    RhNivelAcademicoFacade rhNivelAcademicoFacade;

    @EJB
    GrlPessoaFacade grlPessoaFacade;

    @EJB
    RhFuncionarioFacade rhFuncionarioFacade;

    @Inject
    private geral.beans.GrlEnderecoBean grlEnderecoBean;

    @Inject
    private EstruturaLogicaFisicaCarregarBean estruturaLogicaFisicaCarregarBean;
//    @EJB
//    RhTipoFuncionarioFacade rhTipoFuncionarioFacade;
    private GrlSexo grlSexo;
    private GrlEstadoCivil grlEstadoCivil;
    private RhFuncao rhFuncao;
    private RhEspecialidade rhEspecialidade;
    private RhTipoFuncionario rhTipoFuncionario;
    private RhNivelAcademico rhNivelAcademico;
    private FrtTransporteConfiguracoes frtConfiguracoes;
    private GrlPessoa grlPessoa;
    private RhFuncionario rhFuncionario;
    private EstruturaLogica estruturaLogica;
    private GrlEndereco grlEndereco;

    private int codigoGrlSexo = 0;
    private int codigoGrlEstadoCivil = 0;
    private int codigoRhFuncao = 0;
    private int codigoRhEspecialidade = 0;
    private int codigoRhTipoFuncionario = 0;
    private int codigoRhNivelAcademico = 0;

    /**
     * Creates a new instance of TransporteFuncionarioCadastrarBean
     */
    public FrtTransporteFuncionarioCadastrarBean()
    {

    }

    @PostConstruct
    public void init()
    {
        grlSexo = new GrlSexo();
        //rhFuncionario = new RhFuncionario();
        grlEstadoCivil = new GrlEstadoCivil();
        rhFuncao = new RhFuncao();
        rhEspecialidade = new RhEspecialidade();
        rhTipoFuncionario = new RhTipoFuncionario();
        rhNivelAcademico = new RhNivelAcademico();
        frtConfiguracoes = new FrtTransporteConfiguracoes();
        grlPessoa = new GrlPessoa();
        rhFuncionario = new RhFuncionario();
        estruturaLogica = new EstruturaLogica();

        grlEndereco = new GrlEndereco();

        //solicitar = new FrtTransporteSolicitacao();
        initConfiguracao();
        dataSystem();

    }

    public void initConfiguracao()
    {
        frtConfiguracoes = frtConfiguracoesFacade.find();

        codigoGrlSexo = this.frtConfiguracoes.getFkSexo().getPkGrlSexo();
        codigoGrlEstadoCivil = this.frtConfiguracoes.getFkEstadoCivil().getPkGrlEstadoCivil();
        codigoRhFuncao = this.frtConfiguracoes.getFkFuncao().getPkFuncao();
        codigoRhEspecialidade = this.frtConfiguracoes.getFkEspecialidade().getPkEspecialidade();
        codigoRhTipoFuncionario = this.frtConfiguracoes.getFkTipoFuncionario().getPkTipoFuncionario();
        codigoRhNivelAcademico = this.frtConfiguracoes.getFkNivelAcademico().getPkNivelAcademico();
    }

    public void salvar()
    {
        FacesContext context = FacesContext.getCurrentInstance();

        grlEndereco = grlEnderecoBean.salvaEGetEndereco();
        grlPessoa.setFkResidenciaBairro(grlEndereco);
       
        this.grlPessoa.setPkGrlPessoa(grlPessoaFacade.count()+1);
        grlPessoa.setDataCadastro(new Date());
        grlPessoa.setFkEstadoCivil(new GrlEstadoCivil(this.codigoGrlEstadoCivil));
        grlPessoa.setFkSexo(new GrlSexo(this.codigoGrlSexo));
        grlPessoaFacade.create(grlPessoa);

        EstruturaLogicaFisica reg = estruturaLogicaFisicaCarregarBean.saveFroHora();
        System.out.println("registar() ---: " + reg);
        rhFuncionario.setFkEstruturaLogicaFisica(reg);

        rhFuncionario.setDataCadastro(new Date());
        rhFuncionario.setFkEspecialidade(new RhEspecialidade(this.codigoRhEspecialidade));
        rhFuncionario.setFkFuncao(new RhFuncao(this.codigoRhFuncao));
        rhFuncionario.setFkNivelAcademico(new RhNivelAcademico(this.codigoRhNivelAcademico));
        //grlPessoa.setPkGrlPessoa(grlPessoaFacade.count()+1);
        rhFuncionario.setFkPessoa(grlPessoa);
        rhFuncionario.setFkTipoFuncionario(new RhTipoFuncionario(this.codigoRhTipoFuncionario));
        // rhFuncionario.setFkEstruturaLogica(estruturaLogica);
        rhFuncionarioFacade.create(rhFuncionario);

        init();

        LogFile.sucessoMsg(null, "Funcionario Registrado Com Sucesso !!!!!!");
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Funcionario Feita Com Sucesso !!!", ""));

    }

    public Date dataSystem()
    {
        Date Data;
        Data = new Date();
        return Data;
    }

    public GrlSexo getGrlSexo()
    {
        return grlSexo;
    }

    public void setGrlSexo(GrlSexo grlSexo)
    {
        this.grlSexo = grlSexo;
    }

    public GrlEstadoCivil getGrlEstadoCivil()
    {
        return grlEstadoCivil;
    }

    public void setGrlEstadoCivil(GrlEstadoCivil grlEstadoCivil)
    {
        this.grlEstadoCivil = grlEstadoCivil;
    }

    public RhFuncao getRhFuncao()
    {
        return rhFuncao;
    }

    public void setRhFuncao(RhFuncao rhFuncao)
    {
        this.rhFuncao = rhFuncao;
    }

    public RhEspecialidade getRhEspecialidade()
    {
        return rhEspecialidade;
    }

    public void setRhEspecialidade(RhEspecialidade rhEspecialidade)
    {
        this.rhEspecialidade = rhEspecialidade;
    }

    public RhTipoFuncionario getRhTipoFuncionario()
    {
        return rhTipoFuncionario;
    }

    public void setRhTipoFuncionario(RhTipoFuncionario rhTipoFuncionario)
    {
        this.rhTipoFuncionario = rhTipoFuncionario;
    }

    public RhNivelAcademico getRhNivelAcademico()
    {
        return rhNivelAcademico;
    }

    public void setRhNivelAcademico(RhNivelAcademico rhNivelAcademico)
    {
        this.rhNivelAcademico = rhNivelAcademico;
    }

    public FrtTransporteConfiguracoes getFrtConfiguracoes()
    {
        return frtConfiguracoes;
    }

    public void setFrtConfiguracoes(FrtTransporteConfiguracoes frtConfiguracoes)
    {
        this.frtConfiguracoes = frtConfiguracoes;
    }

    public int getCodigoGrlSexo()
    {
        return codigoGrlSexo;
    }

    public void setCodigoGrlSexo(int codigoGrlSexo)
    {
        this.codigoGrlSexo = codigoGrlSexo;
    }

    public int getCodigoGrlEstadoCivil()
    {
        return codigoGrlEstadoCivil;
    }

    public void setCodigoGrlEstadoCivil(int codigoGrlEstadoCivil)
    {
        this.codigoGrlEstadoCivil = codigoGrlEstadoCivil;
    }

    public int getCodigoRhFuncao()
    {
        return codigoRhFuncao;
    }

    public void setCodigoRhFuncao(int codigoRhFuncao)
    {
        this.codigoRhFuncao = codigoRhFuncao;
    }

    public int getCodigoRhEspecialidade()
    {
        return codigoRhEspecialidade;
    }

    public void setCodigoRhEspecialidade(int codigoRhEspecialidade)
    {
        this.codigoRhEspecialidade = codigoRhEspecialidade;
    }

    public int getCodigoRhTipoFuncionario()
    {
        return codigoRhTipoFuncionario;
    }

    public void setCodigoRhTipoFuncionario(int codigoRhTipoFuncionario)
    {
        this.codigoRhTipoFuncionario = codigoRhTipoFuncionario;
    }

    public int getCodigoRhNivelAcademico()
    {
        return codigoRhNivelAcademico;
    }

    public void setCodigoRhNivelAcademico(int codigoRhNivelAcademico)
    {
        this.codigoRhNivelAcademico = codigoRhNivelAcademico;
    }

    public GrlPessoa getGrlPessoa()
    {
        return grlPessoa;
    }

    public void setGrlPessoa(GrlPessoa grlPessoa)
    {
        this.grlPessoa = grlPessoa;
    }

    public RhFuncionario getRhFuncionario()
    {
        return rhFuncionario;
    }

    public void setRhFuncionario(RhFuncionario rhFuncionario)
    {
        this.rhFuncionario = rhFuncionario;
    }

    public EstruturaLogica getEstruturaLogica()
    {
        return estruturaLogica;
    }

    public void setEstruturaLogica(EstruturaLogica estruturaLogica)
    {
        this.estruturaLogica = estruturaLogica;
    }

    public geral.beans.GrlEnderecoBean getGrlEnderecoBean()
    {
        return grlEnderecoBean;
    }

    public void setGrlEnderecoBean(geral.beans.GrlEnderecoBean grlEnderecoBean)
    {
        this.grlEnderecoBean = grlEnderecoBean;
    }

    public GrlEndereco getGrlEndereco()
    {
        return grlEndereco;
    }

    public void setGrlEndereco(GrlEndereco grlEndereco)
    {
        this.grlEndereco = grlEndereco;
    }

}
