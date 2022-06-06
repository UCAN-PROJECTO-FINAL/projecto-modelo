/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contabilidade.util;

import java.io.Serializable;

/**
 *
 * @author majoao
 */
public class MapaDespesaPorNaturaza implements Serializable {

    private String totais = "";
    private String categoria = "";
    private String codigoCategoria = "";
    private String rubrica = "";
    private String codigoRubrica = "";
    private double saldoDotacaoAprovada = 0;
    private double saldoRecurosDisponibilizados = 0;
    private double saldoDespesasCabimentadas = 0;
    private double saldoDespesasPagas = 0;
    private double saldoTotalDotacaoAprovada = 0;
    private double saldoTotalRecurosDisponibilizados = 0;
    private double saldoTotalDespesasCabimentadas = 0;
    private double saldoTotalDespesasPagas = 0;
    private String dotacaoAprovada = "";
    private String recurosDisponibilizados = "";
    private String despesasCabimentadas = "";
    private String despesasPagas = "";
    
    
    public MapaDespesaPorNaturaza() {
    }

    public String getTotais() {
        return totais;
    }

    public void setTotais(String totais) {
        this.totais = totais;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getCodigoCategoria() {
        return codigoCategoria;
    }

    public void setCodigoCategoria(String codigoCategoria) {
        this.codigoCategoria = codigoCategoria;
    }

    public String getRubrica() {
        return rubrica;
    }

    public void setRubrica(String rubrica) {
        this.rubrica = rubrica;
    }

    public String getCodigoRubrica() {
        return codigoRubrica;
    }

    public void setCodigoRubrica(String codigoRubrica) {
        this.codigoRubrica = codigoRubrica;
    }

    public double getSaldoDotacaoAprovada() {
        return saldoDotacaoAprovada;
    }

    public void setSaldoDotacaoAprovada(double saldoDotacaoAprovada) {
        this.saldoDotacaoAprovada = saldoDotacaoAprovada;
    }

    public double getSaldoRecurosDisponibilizados() {
        return saldoRecurosDisponibilizados;
    }

    public void setSaldoRecurosDisponibilizados(double saldoRecurosDisponibilizados) {
        this.saldoRecurosDisponibilizados = saldoRecurosDisponibilizados;
    }

    public double getSaldoDespesasCabimentadas() {
        return saldoDespesasCabimentadas;
    }

    public void setSaldoDespesasCabimentadas(double saldoDespesasCabimentadas) {
        this.saldoDespesasCabimentadas = saldoDespesasCabimentadas;
    }

    public double getSaldoDespesasPagas() {
        return saldoDespesasPagas;
    }

    public void setSaldoDespesasPagas(double saldoDespesasPagas) {
        this.saldoDespesasPagas = saldoDespesasPagas;
    }

    public String getDotacaoAprovada() {
        return dotacaoAprovada;
    }

    public void setDotacaoAprovada(String dotacaoAprovada) {
        this.dotacaoAprovada = dotacaoAprovada;
    }

    public String getRecurosDisponibilizados() {
        return recurosDisponibilizados;
    }

    public void setRecurosDisponibilizados(String recurosDisponibilizados) {
        this.recurosDisponibilizados = recurosDisponibilizados;
    }

    public String getDespesasCabimentadas() {
        return despesasCabimentadas;
    }

    public void setDespesasCabimentadas(String despesasCabimentadas) {
        this.despesasCabimentadas = despesasCabimentadas;
    }

    public String getDespesasPagas() {
        return despesasPagas;
    }

    public void setDespesasPagas(String despesasPagas) {
        this.despesasPagas = despesasPagas;
    }

    public double getSaldoTotalDotacaoAprovada() {
        return saldoTotalDotacaoAprovada;
    }

    public void setSaldoTotalDotacaoAprovada(double saldoTotalDotacaoAprovada) {
        this.saldoTotalDotacaoAprovada = saldoTotalDotacaoAprovada;
    }

    public double getSaldoTotalRecurosDisponibilizados() {
        return saldoTotalRecurosDisponibilizados;
    }

    public void setSaldoTotalRecurosDisponibilizados(double saldoTotalRecurosDisponibilizados) {
        this.saldoTotalRecurosDisponibilizados = saldoTotalRecurosDisponibilizados;
    }

    public double getSaldoTotalDespesasCabimentadas() {
        return saldoTotalDespesasCabimentadas;
    }

    public void setSaldoTotalDespesasCabimentadas(double saldoTotalDespesasCabimentadas) {
        this.saldoTotalDespesasCabimentadas = saldoTotalDespesasCabimentadas;
    }

    public double getSaldoTotalDespesasPagas() {
        return saldoTotalDespesasPagas;
    }

    public void setSaldoTotalDespesasPagas(double saldoTotalDespesasPagas) {
        this.saldoTotalDespesasPagas = saldoTotalDespesasPagas;
    }
    
    
}
