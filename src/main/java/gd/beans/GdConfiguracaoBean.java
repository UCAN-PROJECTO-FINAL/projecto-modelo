/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gd.beans;

import ejbs.entities.GdClassificacao;
import ejbs.entities.GdConfiguracoes;
import ejbs.entities.GdEntidade;
import ejbs.entities.GdTipoDocumento;
import ejbs.facades.GdClassificacaoFacade;
import ejbs.facades.GdConfiguracoesFacade;
import ejbs.facades.GdEntidadeFacade;
import ejbs.facades.GdTipoDocumentoFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Inject;
import utils.ExcepcaoCarregamentoTabelasExcel;

import utils.mensagens.LogFile;


/**
 *
 * @author david-salgueiro
 */
@Named(value = "gdConfiguracaoBean")
@SessionScoped
public class GdConfiguracaoBean implements Serializable {

    @EJB
    private GdTipoDocumentoFacade tipoDocumentoFacade;
  
    @EJB
    private GdClassificacaoFacade classificacaoFacade;

    @EJB
    private GdEntidadeFacade entidadeFacade;

    @EJB
    private GdConfiguracoesFacade configuracoesFacade;
    
    @Inject
    GdDocumentoCadastrarBean gdDocumentoCadastrarBean;

    private GdConfiguracoes configuracoes;
    private GdTipoDocumento tipoDocumento;
    private GdClassificacao classificacao;
    private GdEntidade entidade;
    
    private int codigoTipoDocumento = 0;
    private int codigoClassificacao = 0;
    private int codigoEntidade = 0;
    
    private boolean btnSalvar = true;
    private boolean btnFormularioCadastro;
    private boolean tabListar = true;
    private boolean estado;
    
    
    public GdConfiguracaoBean()
    {
        
    }
    
    @PostConstruct
    public void init()
    {
        this.configuracoes = this.configuracoesFacade.find();
        codigoTipoDocumento = this.configuracoes.getFkTipoDocumento().getPkTipoDocumento();
        codigoClassificacao = this.configuracoes.getFkClassificacao().getPkClassificacao();
        codigoEntidade = this.configuracoes.getFkEntidade().getPkEntidade();
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
    
    
    public boolean salvarRegister() throws ExcepcaoCarregamentoTabelasExcel
    {
        if (configuracoes != null)
        {
            try
            {
                configuracoes.setFkTipoDocumento(tipoDocumentoFacade.find(codigoTipoDocumento));
                configuracoes.setFkClassificacao(classificacaoFacade.find(codigoClassificacao));
                configuracoes.setFkEntidade(entidadeFacade.find(codigoEntidade));
                configuracoesFacade.edit(configuracoes);
                gdDocumentoCadastrarBean.initConfiguracao();
            }
            catch (Exception e)
            {
                  LogFile.sucessoMsg(null, "Tente novamente!");
            }

             LogFile.sucessoMsg(null, "Configuração gravada com sucesso!");
            return true;
        }

        return false;
    }
    
    public GdConfiguracoes reporConfiguracoesPadrao()
    {
        configuracoes = this.configuracoesFacade.reporGdConfiguracoesPadrao();
        System.out.println("INICIALIZADA");
        return configuracoes;
    }
    
    
    public String alterar(GdConfiguracoes configuracoes) 
    {

        btnEditar();
        this.configuracoes = configuracoes;

        return "";

    }
    
    public List<GdConfiguracoes> listarConfiguracao()
    {
       return this.configuracoesFacade.findAll();
    }
    
    public List<GdTipoDocumento> listaTipoDocumento() 
    {
        return this.tipoDocumentoFacade.findAll();
    }
    
    public List<GdClassificacao> listaClassificacao() 
    {
        return this.classificacaoFacade.findAll();
    }
    
    public List<GdEntidade> listaEntidade() 
    {
        return this.entidadeFacade.findAll();
    }
    
    public GdConfiguracoes getConfiguracoes() {
        return configuracoes;
    }

    public void setConfiguracoes(GdConfiguracoes configuracoes) {
        this.configuracoes = configuracoes;
    }

    public GdTipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(GdTipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public int getCodigoTipoDocumento() {
        return codigoTipoDocumento;
    }

    public void setCodigoTipoDocumento(int codigoTipoDocumento) {
        this.codigoTipoDocumento = codigoTipoDocumento;
    }
    
    public boolean isBtnSalvar() {
        return btnSalvar;
    }

    public void setBtnSalvar(boolean btnSalvar) {
        this.btnSalvar = btnSalvar;
    }

    public boolean isBtnFormularioCadastro() {
        return btnFormularioCadastro;
    }

    public void setBtnFormularioCadastro(boolean btnFormularioCadastro) {
        this.btnFormularioCadastro = btnFormularioCadastro;
    }

    public boolean isTabListar() {
        return tabListar;
    }

    public void setTabListar(boolean tabListar) {
        this.tabListar = tabListar;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public GdClassificacao getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(GdClassificacao classificacao) {
        this.classificacao = classificacao;
    }

    public GdEntidade getEntidade() {
        return entidade;
    }

    public void setEntidade(GdEntidade entidade) {
        this.entidade = entidade;
    }

    public int getCodigoClassificacao() {
        return codigoClassificacao;
    }

    public void setCodigoClassificacao(int codigoClassificacao) {
        this.codigoClassificacao = codigoClassificacao;
    }

    public int getCodigoEntidade() {
        return codigoEntidade;
    }

    public void setCodigoEntidade(int codigoEntidade) {
        this.codigoEntidade = codigoEntidade;
    }

    
    
}
