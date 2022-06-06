/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arq.beans;

import ejbs.entities.ArAnoCurricular;
import ejbs.entities.ArAnoLectivo;
import ejbs.entities.ArConfiguracoes;
import ejbs.entities.ArPeriodo;
import ejbs.entities.ArTipoDocumento;
import ejbs.entities.ArTurma;
import ejbs.facades.ArAnoCurricularFacade;
import ejbs.facades.ArAnoLectivoFacade;
import ejbs.facades.ArCadeiraFacade;
import ejbs.facades.ArConfiguracoesFacade;
import ejbs.facades.ArCursoFacade;
import ejbs.facades.ArDocenteFacade;
import ejbs.facades.ArFaculdadeFacade;
import ejbs.facades.ArPeriodoFacade;
import ejbs.facades.ArTipoDocumentoFacade;
import ejbs.facades.ArTurmaFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Inject;
import utils.ExcepcaoCarregamentoTabelasExcel;
//import utils.Mensagem;
import utils.mensagens.LogFile;

/**
 *
 * @author david-salgueiro
 */
@Named(value = "aRconfiguracaoBean")
@SessionScoped
public class ArConfiguracaoBean implements Serializable {

    @EJB
    private ArTipoDocumentoFacade tipoDocumentoFacade;
    
    @EJB
    private ArTurmaFacade turmaFacade;

    @EJB
    private ArPeriodoFacade periodoFacade;

    @EJB
    private ArAnoLectivoFacade anoLectivoFacade;

    @EJB
    private ArAnoCurricularFacade anoCurricularFacade;


    @EJB
    private ArConfiguracoesFacade configuracoesFacade;
    
    @Inject
    ArArquivoMortoCadastrarBean arArquivoMortoCadastrarBean;

    private ArConfiguracoes configuracoes;
    private ArTipoDocumento tipoDocumento;
    
    private int codigoTipoDocumento = 0;
    private int codigoAnoLectivo = 0;
    private int codigoAnoCurricular = 0;
    private int codigoTurma = 0;
    private int codigoPeriodo = 0;
    
    private boolean btnSalvar = true;
    private boolean btnFormularioCadastro;
    private boolean tabListar = true;
    private boolean estado;
    
    public ArConfiguracaoBean()
    {
    }
    
    @PostConstruct
    public void init()
    {
        this.configuracoes = this.configuracoesFacade.find();
        codigoTipoDocumento = this.configuracoes.getFkTipoDocumento().getPkTipoDocumento();
        codigoAnoLectivo = this.configuracoes.getFkAnoLectivo().getPkAnoLectivo();
        codigoAnoCurricular = this.configuracoes.getFkAnoCurricular().getPkAnoCurricular();
        codigoTurma = this.configuracoes.getFkTurma().getPkTurma();
        codigoPeriodo = this.configuracoes.getFkPeriodo().getPkPeriodo();
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
    
    
    
    
    public void inicializar()
    {
        this.tipoDocumento = this.configuracoes.getFkTipoDocumento();
        codigoTipoDocumento = this.tipoDocumento.getPkTipoDocumento();
    }
    
    public boolean salvarRegister() throws ExcepcaoCarregamentoTabelasExcel
    {
        if (configuracoes != null)
        {
            try
            {
                configuracoes.setFkTipoDocumento(tipoDocumentoFacade.find(codigoTipoDocumento));
                configuracoes.setFkAnoCurricular(anoCurricularFacade.find(codigoAnoCurricular));
                configuracoes.setFkAnoLectivo(anoLectivoFacade.find(codigoAnoLectivo));
                configuracoes.setFkPeriodo(periodoFacade.find(codigoPeriodo));
                configuracoes.setFkTurma(turmaFacade.find(codigoTurma));
                configuracoesFacade.edit(configuracoes);
                arArquivoMortoCadastrarBean.initConfiguracao();
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
    
    public ArConfiguracoes reporConfiguracoesPadrao()
    {
        configuracoes = this.configuracoesFacade.reporConfiguracoesPadrao();
        System.out.println("INICIALIZADA");
        return configuracoes;
    }
    
    
    public String alterar(ArConfiguracoes configuracoes) 
    {

        btnEditar();
        this.configuracoes = configuracoes;

        return "";

    }
    
    public List<ArConfiguracoes> listarConfiguracao()
    {
       return this.configuracoesFacade.findAll();
    }
    
    public List<ArTipoDocumento> listaTipoDocumento() 
    {
        return this.tipoDocumentoFacade.findAll();
    }
    
    public List<ArAnoLectivo> listaAnoLectivo() 
    {
        return this.anoLectivoFacade.findAll();
    }
    
    public List<ArAnoCurricular> listaAnoCurricular() 
    {
        return this.anoCurricularFacade.findAll();
    }
    
    public List<ArPeriodo> listaPeriodo() 
    {
        return this.periodoFacade.findAll();
    }
    
    public List<ArTurma> listaTurma() 
    {
        return this.turmaFacade.findAll();
    }
    
    
    public ArConfiguracoes getConfiguracoes() {
        return configuracoes;
    }

    public void setConfiguracoes(ArConfiguracoes configuracoes) {
        this.configuracoes = configuracoes;
    }

    public ArTipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(ArTipoDocumento tipoDocumento) {
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

   
    public int getCodigoAnoLectivo() {
        return codigoAnoLectivo;
    }

    public void setCodigoAnoLectivo(int codigoAnoLectivo) {
        this.codigoAnoLectivo = codigoAnoLectivo;
    }

    public int getCodigoAnoCurricular() {
        return codigoAnoCurricular;
    }

    public void setCodigoAnoCurricular(int codigoAnoCurricular) {
        this.codigoAnoCurricular = codigoAnoCurricular;
    }

    public int getCodigoTurma() {
        return codigoTurma;
    }

    public void setCodigoTurma(int codigoTurma) {
        this.codigoTurma = codigoTurma;
    }

    public int getCodigoPeriodo() {
        return codigoPeriodo;
    }

    public void setCodigoPeriodo(int codigoPeriodo) {
        this.codigoPeriodo = codigoPeriodo;
    }


}
