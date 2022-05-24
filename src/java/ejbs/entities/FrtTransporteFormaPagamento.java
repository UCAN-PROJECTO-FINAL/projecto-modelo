/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author smakambo
 */
@Entity
@Table(name = "frt_transporte_forma_pagamento")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "FrtTransporteFormaPagamento.findAll", query = "SELECT f FROM FrtTransporteFormaPagamento f"),
    @NamedQuery(name = "FrtTransporteFormaPagamento.findByPkFormaPagamento", query = "SELECT f FROM FrtTransporteFormaPagamento f WHERE f.pkFormaPagamento = :pkFormaPagamento"),
    @NamedQuery(name = "FrtTransporteFormaPagamento.findByDescricao", query = "SELECT f FROM FrtTransporteFormaPagamento f WHERE f.descricao = :descricao")
})
public class FrtTransporteFormaPagamento implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_forma_pagamento")
    private Integer pkFormaPagamento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "descricao")
    private String descricao;

    public FrtTransporteFormaPagamento()
    {
    }

    public FrtTransporteFormaPagamento(Integer pkFormaPagamento)
    {
        this.pkFormaPagamento = pkFormaPagamento;
    }

    public FrtTransporteFormaPagamento(Integer pkFormaPagamento, String descricao)
    {
        this.pkFormaPagamento = pkFormaPagamento;
        this.descricao = descricao;
    }

    public Integer getPkFormaPagamento()
    {
        return pkFormaPagamento;
    }

    public void setPkFormaPagamento(Integer pkFormaPagamento)
    {
        this.pkFormaPagamento = pkFormaPagamento;
    }

    public String getDescricao()
    {
        return descricao;
    }

    public void setDescricao(String descricao)
    {
        this.descricao = descricao;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (pkFormaPagamento != null ? pkFormaPagamento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FrtTransporteFormaPagamento))
        {
            return false;
        }
        FrtTransporteFormaPagamento other = (FrtTransporteFormaPagamento) object;
        if ((this.pkFormaPagamento == null && other.pkFormaPagamento != null) || (this.pkFormaPagamento != null && !this.pkFormaPagamento.equals(other.pkFormaPagamento)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.FrtTransporteFormaPagamento[ pkFormaPagamento=" + pkFormaPagamento + " ]";
    }
    
}
