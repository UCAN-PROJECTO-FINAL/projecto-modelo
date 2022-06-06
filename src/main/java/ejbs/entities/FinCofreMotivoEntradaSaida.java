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
@Table(name = "fin_cofre_motivo_entrada_saida", catalog = "sig_ucan_db", schema = "public")
@NamedQueries(
{
    @NamedQuery(name = "FinCofreMotivoEntradaSaida.findAll", query = "SELECT f FROM FinCofreMotivoEntradaSaida f"),
    @NamedQuery(name = "FinCofreMotivoEntradaSaida.findByPkCofreMotivoEntradaSaida", query = "SELECT f FROM FinCofreMotivoEntradaSaida f WHERE f.pkCofreMotivoEntradaSaida = :pkCofreMotivoEntradaSaida"),
    @NamedQuery(name = "FinCofreMotivoEntradaSaida.findByDesignacao", query = "SELECT f FROM FinCofreMotivoEntradaSaida f WHERE f.designacao = :designacao"),
    @NamedQuery(name = "FinCofreMotivoEntradaSaida.findByStatus", query = "SELECT f FROM FinCofreMotivoEntradaSaida f WHERE f.status = :status")
})
public class FinCofreMotivoEntradaSaida implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "pk_cofre_motivo_entrada_saida", nullable = false)
    private Integer pkCofreMotivoEntradaSaida;
    @Size(max = 2147483647)
    @Column(length = 2147483647)
    private String designacao;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private boolean status;

    public FinCofreMotivoEntradaSaida()
    {
    }

    public FinCofreMotivoEntradaSaida(Integer pkCofreMotivoEntradaSaida)
    {
        this.pkCofreMotivoEntradaSaida = pkCofreMotivoEntradaSaida;
    }

    public FinCofreMotivoEntradaSaida(Integer pkCofreMotivoEntradaSaida, boolean status)
    {
        this.pkCofreMotivoEntradaSaida = pkCofreMotivoEntradaSaida;
        this.status = status;
    }

    public Integer getPkCofreMotivoEntradaSaida()
    {
        return pkCofreMotivoEntradaSaida;
    }

    public void setPkCofreMotivoEntradaSaida(Integer pkCofreMotivoEntradaSaida)
    {
        this.pkCofreMotivoEntradaSaida = pkCofreMotivoEntradaSaida;
    }

    public String getDesignacao()
    {
        return designacao;
    }

    public void setDesignacao(String designacao)
    {
        this.designacao = designacao;
    }

    public boolean getStatus()
    {
        return status;
    }

    public void setStatus(boolean status)
    {
        this.status = status;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (pkCofreMotivoEntradaSaida != null ? pkCofreMotivoEntradaSaida.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FinCofreMotivoEntradaSaida))
        {
            return false;
        }
        FinCofreMotivoEntradaSaida other = (FinCofreMotivoEntradaSaida) object;
        if ((this.pkCofreMotivoEntradaSaida == null && other.pkCofreMotivoEntradaSaida != null) || (this.pkCofreMotivoEntradaSaida != null && !this.pkCofreMotivoEntradaSaida.equals(other.pkCofreMotivoEntradaSaida)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.FinCofreMotivoEntradaSaida[ pkCofreMotivoEntradaSaida=" + pkCofreMotivoEntradaSaida + " ]";
    }
    
}
