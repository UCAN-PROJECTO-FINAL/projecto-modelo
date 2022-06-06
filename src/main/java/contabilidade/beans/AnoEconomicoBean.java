/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contabilidade.beans;

import ejbs.entities.CtAnoEconomico;
import ejbs.entities.CtOcorrenciaContabilidade;
import ejbs.facades.CtAnoEconomicoFacade;
import ejbs.facades.CtOcorrenciaContabilidadeFacade;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import utils.mensagens.Mensagem;

/**
 *
 * @author majoao
 */
@Named(value = "anoEconomicoBean")
@ViewScoped
public class AnoEconomicoBean implements Serializable {

    /**
     * Creates a new instance of AnoEconomicoBean
     */
    @EJB
    private CtOcorrenciaContabilidadeFacade ctOcorrenciacontabilidadeFacade;

    // interface
    @EJB
    private CtAnoEconomicoFacade ctAnoeconomicoFacade;

    //objectos
    private CtAnoEconomico anoEconomicoEntidade;

    //Listas
    private List<CtAnoEconomico> listAnoEconomico = null;
    private List<CtAnoEconomico> listHistorico = null;
    private CtOcorrenciaContabilidade ocorrencia = null;
    private Date data;
    private String dt = "";

    private String valorAnual = "";

    private int pkAnoEconomicoActual;

    public AnoEconomicoBean() {
    }

    @PostConstruct
    public void init() {
        this.listAnoEconomico = ctAnoeconomicoFacade.listYear();
        this.anoEconomicoEntidade = new CtAnoEconomico();
        //this.listAnoEconomico = new ArrayList<>();
        this.listHistorico = new ArrayList<>();
        this.ocorrencia = new CtOcorrenciaContabilidade();
        this.anoEconomicoEntidade.setAnoEconomico(getAno());
        verificarExercicio();
    }

    public void verificarExercicio() {
        try {
            List<CtAnoEconomico> lista = ctAnoeconomicoFacade.findExercicioEconomicoByAno(getAno());

            if (lista.isEmpty()) {

                this.pkAnoEconomicoActual = ctAnoeconomicoFacade.getPkAnoEconomico().get(0).getPkAnoEconomico();
                System.err.println("ULTIMO EXERCICIO ATIVO : " + ctAnoeconomicoFacade.getPkAnoEconomico().get(0).getAnoEconomico());
            } else {

                this.pkAnoEconomicoActual = lista.get(0).getPkAnoEconomico();
                Mensagem.warnMsg(null, "EXERCICIO CORRENTE ATIVO: " + lista.get(0).getAnoEconomico());
                //System.err.println("EXERCICIO CORRENTE ATIVO: " + lista.get(0).getAnoEconomico());
            }
        } catch (Exception e) {
        }
    }

    public boolean validarAno(){
        try {
            return ctAnoeconomicoFacade.getPkAnoEconomico().get(0).getAnoEconomico() == getAno();
        } catch (Exception e) {
        }
        return false;
    }
    private int getAno() {
        DateFormat formatar = new SimpleDateFormat("yyyy");
        return Integer.parseInt(formatar.format(new Date()));
    }

    public String gravar() {

        List<CtAnoEconomico> list = getListAnoEconomico();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-dd-MM");

        try {

            this.anoEconomicoEntidade.setDataAnoEconomico(new Date());
            if (this.anoEconomicoEntidade.getAnoEconomico() == 0) {
                return "";
            } else if (this.anoEconomicoEntidade.getValorAnual() == 0) {
                return "";
            }

            if (list != null) {

                for (CtAnoEconomico year : list) {

                    if (year.getAnoEconomico() == this.anoEconomicoEntidade.getAnoEconomico()) {

                        return "";

                    }
                }

            }
            //this.anoEconomicoEntidade.setPkAnoEconomico(this.ctAnoeconomicoFacade.count() + 1);
            this.anoEconomicoEntidade.setValorDisponivel(this.anoEconomicoEntidade.getValorAnual());
            this.anoEconomicoEntidade.setDataAnoEconomico(new Date());
            this.anoEconomicoEntidade.setStateAnoEconomico(true);
            this.ctAnoeconomicoFacade.create(this.anoEconomicoEntidade);
            this.anoEconomicoEntidade = new CtAnoEconomico();
            this.listAnoEconomico = new ArrayList<>();
            init();
            return "";

        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao salvar os dados, verifique todos os parametros de inserção, ou entre em contacto com o Administrador de Sistemas.", ""));
            ex.printStackTrace();
        }

        return "";
    }

    public void eliminar(int year) {

        try {

            this.anoEconomicoEntidade = ctAnoeconomicoFacade.find(year);
            this.anoEconomicoEntidade.setDataAnoEconomico(new Date());
            this.valorAnual = String.valueOf(this.anoEconomicoEntidade.getValorAnual());
            this.anoEconomicoEntidade.setStateAnoEconomico(false);
            this.ctAnoeconomicoFacade.edit(this.anoEconomicoEntidade);
            this.anoEconomicoEntidade = new CtAnoEconomico();
            this.listAnoEconomico = new ArrayList<>();

        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }

    public void editar() {

        try {

            this.anoEconomicoEntidade.setDataAnoEconomico(new Date());
            this.anoEconomicoEntidade.setStateAnoEconomico(true);
            this.ctAnoeconomicoFacade.edit(this.anoEconomicoEntidade);
            this.anoEconomicoEntidade = new CtAnoEconomico();
            this.listAnoEconomico = new ArrayList<>();

        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }

    public List<CtAnoEconomico> getListAnoEconomico() {

        if (this.listAnoEconomico == null) {
            this.listAnoEconomico = ctAnoeconomicoFacade.listYear();
        }

        return this.listAnoEconomico;
    }

    public void setListAnoEconomico(List<CtAnoEconomico> listAnoEconomico) {
        this.listAnoEconomico = listAnoEconomico;
    }

    public CtAnoEconomico getAnoEconomicoEntidade() {
        return anoEconomicoEntidade;
    }

    public void setAnoEconomicoEntidade(CtAnoEconomico anoEconomicoEntidade) {
        this.anoEconomicoEntidade = anoEconomicoEntidade;
    }

    public List<CtAnoEconomico> getListHistorico() {
        return listHistorico;
    }

    public void setListHistorico(List<CtAnoEconomico> listHistorico) {
        this.listHistorico = listHistorico;
    }

    public CtOcorrenciaContabilidade getOcorrencia() {
        return ocorrencia;
    }

    public void setOcorrencia(CtOcorrenciaContabilidade ocorrencia) {
        this.ocorrencia = ocorrencia;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getDt() {
        return dt;
    }

    public void setDt(String dt) {
        this.dt = dt;
    }

    public String getValorAnual() {
        return valorAnual;
    }

    public void setValorAnual(String valorAnual) {
        this.valorAnual = valorAnual;
    }

    public int getPkAnoEconomicoActual() {
        return pkAnoEconomicoActual;
    }

    public void setPkAnoEconomicoActual(int pkAnoEconomicoActual) {
        this.pkAnoEconomicoActual = pkAnoEconomicoActual;
    }

}
