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
public class Despesa  implements Serializable{

   
    private String codigo = "";
    private int exercicio = 0;
    private String descricao = "";
    private double  saldoAnual = 0.00;
    private double  totalSaldoAnual = 0.00;
    private double saldoDisponivel = 0.00;
    private double totalSaldoDisponivel = 0.00;
    private double saldoUtilizado = 0.00;
    private double grauexecucao = 0.00;
    private double  totalgraudexecucao = 0.00;
    private double totalSaldoUtilizado = 0.00;
    private double saldoTotalValorAprovado = 0.00;
    private double saldoTotal = 0.00;
    private String totais = "";
    private double saldoTotalRecurosDisponibilizados = 0.00;
    private double saldoTotalDespesasCabimentadas = 0.00;
    private double saldoTotalDespesasPagas = 0.00;
    
    public Despesa() {
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getSaldoAnual() {
        return saldoAnual;
    }

    public void setSaldoAnual(double saldoAnual) {
        this.saldoAnual = saldoAnual;
    }

    public double getSaldoDisponivel() {
        return saldoDisponivel;
    }

    public void setSaldoDisponivel(double saldoDisponivel) {
        this.saldoDisponivel = saldoDisponivel;
    }

    public int getExercicio() {
        return exercicio;
    }

    public void setExercicio(int exercicio) {
        this.exercicio = exercicio;
    }

    public double getSaldoUtilizado() {
        return saldoUtilizado;
    }

    public void setSaldoUtilizado(double saldoUtilizado) {
        this.saldoUtilizado = saldoUtilizado;
    }

    public double getSaldoTotalValorAprovado() {
        return saldoTotalValorAprovado;
    }

    public void setSaldoTotalValorAprovado(double saldoTotalValorAprovado) {
        this.saldoTotalValorAprovado = saldoTotalValorAprovado;
    }

    public double getSaldoTotal() {
        return saldoTotal;
    }

    public void setSaldoTotal(double saldoTotal) {
        this.saldoTotal = saldoTotal;
    }

    public String getTotais() {
        return totais;
    }

    public void setTotais(String totais) {
        this.totais = totais;
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

    public double getTotalSaldoDisponivel() {
        return totalSaldoDisponivel;
    }

    public void setTotalSaldoDisponivel(double totalSaldoDisponivel) {
        this.totalSaldoDisponivel = totalSaldoDisponivel;
    }

    public double getTotalSaldoUtilizado() {
        return totalSaldoUtilizado;
    }

    public void setTotalSaldoUtilizado(double totalSaldoUtilizado) {
        this.totalSaldoUtilizado = totalSaldoUtilizado;
    }

    public double getTotalSaldoAnual() {
        return totalSaldoAnual;
    }

    public void setTotalSaldoAnual(double totalSaldoAnual) {
        this.totalSaldoAnual = totalSaldoAnual;
    }

    public double getGrauexecucao() {
        return grauexecucao;
    }

    public void setGrauexecucao(double grauexecucao) {
        this.grauexecucao = grauexecucao;
    }

    public double getTotalgraudexecucao() {
        return totalgraudexecucao;
    }

    public void setTotalgraudexecucao(double totalgraudexecucao) {
        this.totalgraudexecucao = totalgraudexecucao;
    }
    
}
