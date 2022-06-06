/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author smakambo
 */
@Entity
@Table(name = "fin_tipo_pagamento", catalog = "sig_ucan_db", schema = "public")
@NamedQueries(
{
    @NamedQuery(name = "FinTipoPagamento.findAll", query = "SELECT f FROM FinTipoPagamento f"),
    @NamedQuery(name = "FinTipoPagamento.findByPkTipoPagamento", query = "SELECT f FROM FinTipoPagamento f WHERE f.pkTipoPagamento = :pkTipoPagamento"),
    @NamedQuery(name = "FinTipoPagamento.findByNumeroOrdemSaque", query = "SELECT f FROM FinTipoPagamento f WHERE f.numeroOrdemSaque = :numeroOrdemSaque"),
    @NamedQuery(name = "FinTipoPagamento.findByModo", query = "SELECT f FROM FinTipoPagamento f WHERE f.modo = :modo")
})
public class FinTipoPagamento implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "pk_tipo_pagamento", nullable = false)
    private Integer pkTipoPagamento;
    @Column(name = "numero_ordem_saque")
    private Integer numeroOrdemSaque;
    @Size(max = 45)
    @Column(length = 45)
    private String modo;
    @OneToMany(mappedBy = "fkTipoPagamento")
    private List<FinOperacoesContaCorrente> finOperacoesContaCorrenteList;

    public FinTipoPagamento()
    {
    }

    public FinTipoPagamento(Integer pkTipoPagamento)
    {
        this.pkTipoPagamento = pkTipoPagamento;
    }

    public Integer getPkTipoPagamento()
    {
        return pkTipoPagamento;
    }

    public void setPkTipoPagamento(Integer pkTipoPagamento)
    {
        this.pkTipoPagamento = pkTipoPagamento;
    }

    public Integer getNumeroOrdemSaque()
    {
        return numeroOrdemSaque;
    }

    public void setNumeroOrdemSaque(Integer numeroOrdemSaque)
    {
        this.numeroOrdemSaque = numeroOrdemSaque;
    }

    public String getModo()
    {
        return modo;
    }

    public void setModo(String modo)
    {
        this.modo = modo;
    }

    public List<FinOperacoesContaCorrente> getFinOperacoesContaCorrenteList()
    {
        return finOperacoesContaCorrenteList;
    }

    public void setFinOperacoesContaCorrenteList(List<FinOperacoesContaCorrente> finOperacoesContaCorrenteList)
    {
        this.finOperacoesContaCorrenteList = finOperacoesContaCorrenteList;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (pkTipoPagamento != null ? pkTipoPagamento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FinTipoPagamento))
        {
            return false;
        }
        FinTipoPagamento other = (FinTipoPagamento) object;
        if ((this.pkTipoPagamento == null && other.pkTipoPagamento != null) || (this.pkTipoPagamento != null && !this.pkTipoPagamento.equals(other.pkTipoPagamento)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.FinTipoPagamento[ pkTipoPagamento=" + pkTipoPagamento + " ]";
    }
    
}
