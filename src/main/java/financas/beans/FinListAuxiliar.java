/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package financas.beans;

import java.util.Date;

/**
 *
 * @author majoao
 */
public class FinListAuxiliar {
    public String descricao;
    public String fornecedor;
    public String cliente;
    public String centroCusto;
    public String contaCartao;
    private String numeroConta;
    public double valor;
    public Date dataCadastro;
    public int chave;

    public String getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(String numeroConta) {
        this.numeroConta = numeroConta;
    }
    
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(String fornecedor) {
        this.fornecedor = fornecedor;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getCentroCusto() {
        return centroCusto;
    }

    public void setCentroCusto(String centroCusto) {
        this.centroCusto = centroCusto;
    }

    public String getContaCartao() {
        return contaCartao;
    }

    public void setContaCartao(String contaCartao) {
        this.contaCartao = contaCartao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public int getChave() {
        return chave;
    }

    public void setChave(int chave) {
        this.chave = chave;
    }
    
    
    
}
