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
public class BalanceteGeral  implements Serializable {
    
    
    private String codigo = "";
    private String descricao = "";
    private String tipo = "";
    private double debito = 0.00;
    private double credito = 0.00;
    private double totalDebito = 0.00;
    private double totalCredito = 0.00;
    private double SaldoDevedor = 0.00;
    private double SaldoCredor = 0.00;
    private double totalSaldoDevedor = 0.00;
    private double totalSaldoCredor = 0.00;
    private String totais= "";
    public BalanceteGeral() {
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
        return SaldoDevedor;
    }

    public void setSaldoDevedor(double SaldoDevedor) {
        this.SaldoDevedor = SaldoDevedor;
    }

    public double getSaldoCredor() {
        return SaldoCredor;
    }

    public void setSaldoCredor(double SaldoCredor) {
        this.SaldoCredor = SaldoCredor;
    }

    public double getTotalSaldoDevedor() {
        return totalSaldoDevedor;
    }

    public void setTotalSaldoDevedor(double totalSaldoDevedor){
        this.totalSaldoDevedor = totalSaldoDevedor;
    }

    public double getTotalSaldoCredor(){
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
    
     public double SaldoD(double  debito, double  credito){
        
        Double resultado = (debito) - (credito);

        if(resultado < 0){
            
            return 0.00;
        }
        
        return resultado;
        
    }
    
    
    public double SaldoC(double  debito, double  credito){
        
         Double resultado = (credito) - (debito);

        if(resultado < 0){
            
            return 0.00;
        }
        
        return resultado;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
