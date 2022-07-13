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
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author smakambo
 */
@Entity
@Table(name = "fin_lancamento", catalog = "sig_ucan_db", schema = "public")
@NamedQueries(
{
    @NamedQuery(name = "FinLancamento.findAll", query = "SELECT f FROM FinLancamento f"),
    @NamedQuery(name = "FinLancamento.findByPkLancamento", query = "SELECT f FROM FinLancamento f WHERE f.pkLancamento = :pkLancamento"),
    @NamedQuery(name = "FinLancamento.findByDescricao", query = "SELECT f FROM FinLancamento f WHERE f.descricao = :descricao")
})
public class FinLancamento implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "pk_lancamento", nullable = false)
    private Integer pkLancamento;
    @Size(max = 100)
    @Column(length = 100)
    private String descricao;

    public FinLancamento()
    {
    }

    public FinLancamento(Integer pkLancamento)
    {
        this.pkLancamento = pkLancamento;
    }

    public Integer getPkLancamento()
    {
        return pkLancamento;
    }

    public void setPkLancamento(Integer pkLancamento)
    {
        this.pkLancamento = pkLancamento;
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
        hash += (pkLancamento != null ? pkLancamento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FinLancamento))
        {
            return false;
        }
        FinLancamento other = (FinLancamento) object;
        if ((this.pkLancamento == null && other.pkLancamento != null) || (this.pkLancamento != null && !this.pkLancamento.equals(other.pkLancamento)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.FinLancamento[ pkLancamento=" + pkLancamento + " ]";
    }
    
}
