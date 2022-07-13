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
public class MapaProgramacao  implements Serializable {

    private String numero = "";
    private int exercicio = 0;
    private String codigo = "";
    private String descricao = "";
    private String rubrica = "";
    private double debito = 0.00;
    private double credito = 0.00;
    private double totalDebito = 0.00;
    private double totalCredito = 0.00;
    private double saldoDevedor = 0.00;
    private double saldoCredor = 0.00;
    private double totalSaldoDevedor = 0.00;
    private double totalSaldoCredor = 0.00;
    private String totais= "";
    
    public MapaProgramacao() {
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getRubrica() {
        return rubrica;
    }

    public void setRubrica(String rubrica) {
        this.rubrica = rubrica;
    }

    public double getDebito() {
        return debito;
    }

    public void setDebito(double debito) {
        this.debito = debito;
    }

    public double getCredito() {
        return credito;
    }

    public void setCredito(double credito) {
        this.credito = credito;
    }

    public double getTotalDebito() {
        return totalDebito;
    }

    public void setTotalDebito(double totalDebito) {
        this.totalDebito = totalDebito;
    }

    public double getTotalCredito() {
        return totalCredito;
    }

    public void setTotalCredito(double totalCredito) {
        this.totalCredito = totalCredito;
    }

    public double getSaldoDevedor() {
        return saldoDevedor;
    }

    public void setSaldoDevedor(double SaldoDevedor) {
        this.saldoDevedor = SaldoDevedor;
    }

    public double getSaldoCredor() {
        return saldoCredor;
    }

    public void setSaldoCredor(double SaldoCredor) {
        this.saldoCredor = SaldoCredor;
    }

    public double getTotalSaldoDevedor() {
        return totalSaldoDevedor;
    }

    public void setTotalSaldoDevedor(double totalSaldoDevedor) {
        this.totalSaldoDevedor = totalSaldoDevedor;
    }

    public double getTotalSaldoCredor() {
        return totalSaldoCredor;
    }

    public void setTotalSaldoCredor(double totalSaldoCredor) {
        this.totalSaldoCredor = totalSaldoCredor;
    }

    public String getTotais() {
        return totais;
    }

    public void setTotais(String totais) {
        this.totais = totais;
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

    public int getExercicio() {
        return exercicio;
    }

    public void setExercicio(int exercicio) {
        this.exercicio = exercicio;
    }
    
    
}
