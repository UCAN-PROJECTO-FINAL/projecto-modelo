/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author smakambo
 */
@Entity
@Table(name = "fin_cambio", catalog = "sig_ucan_db", schema = "public")
@NamedQueries(
{
    @NamedQuery(name = "FinCambio.findAll", query = "SELECT f FROM FinCambio f"),
    @NamedQuery(name = "FinCambio.findByPkCambio", query = "SELECT f FROM FinCambio f WHERE f.pkCambio = :pkCambio"),
    @NamedQuery(name = "FinCambio.findByValorCompra", query = "SELECT f FROM FinCambio f WHERE f.valorCompra = :valorCompra"),
    @NamedQuery(name = "FinCambio.findByValorVenda", query = "SELECT f FROM FinCambio f WHERE f.valorVenda = :valorVenda"),
    @NamedQuery(name = "FinCambio.findByReferencia", query = "SELECT f FROM FinCambio f WHERE f.referencia = :referencia"),
    @NamedQuery(name = "FinCambio.findByData", query = "SELECT f FROM FinCambio f WHERE f.data = :data")
})
public class FinCambio implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "pk_cambio", nullable = false)
    private Integer pkCambio;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valor_compra", precision = 18, scale = 2)
    private BigDecimal valorCompra;
    @Column(name = "valor_venda", precision = 18, scale = 2)
    private BigDecimal valorVenda;
    @Column(precision = 18, scale = 2)
    private BigDecimal referencia;
    @Temporal(TemporalType.DATE)
    private Date data;
    @JoinColumn(name = "fk_moeda", referencedColumnName = "pk_moeda")
    @ManyToOne
    private FinMoeda fkMoeda;
    @JoinColumn(name = "fk_utilizador", referencedColumnName = "pk_seg_conta")
    @ManyToOne
    private SegConta fkUtilizador;

    public FinCambio()
    {
    }

    public FinCambio(Integer pkCambio)
    {
        this.pkCambio = pkCambio;
    }

    public Integer getPkCambio()
    {
        return pkCambio;
    }

    public void setPkCambio(Integer pkCambio)
    {
        this.pkCambio = pkCambio;
    }

    public BigDecimal getValorCompra()
    {
        return valorCompra;
    }

    public void setValorCompra(BigDecimal valorCompra)
    {
        this.valorCompra = valorCompra;
    }

    public BigDecimal getValorVenda()
    {
        return valorVenda;
    }

    public void setValorVenda(BigDecimal valorVenda)
    {
        this.valorVenda = valorVenda;
    }

    public BigDecimal getReferencia()
    {
        return referencia;
    }

    public void setReferencia(BigDecimal referencia)
    {
        this.referencia = referencia;
    }

    public Date getData()
    {
        return data;
    }

    public void setData(Date data)
    {
        this.data = data;
    }

    public FinMoeda getFkMoeda()
    {
        return fkMoeda;
    }

    public void setFkMoeda(FinMoeda fkMoeda)
    {
        this.fkMoeda = fkMoeda;
    }

    public SegConta getFkUtilizador()
    {
        return fkUtilizador;
    }

    public void setFkUtilizador(SegConta fkUtilizador)
    {
        this.fkUtilizador = fkUtilizador;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (pkCambio != null ? pkCambio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FinCambio))
        {
            return false;
        }
        FinCambio other = (FinCambio) object;
        if ((this.pkCambio == null && other.pkCambio != null) || (this.pkCambio != null && !this.pkCambio.equals(other.pkCambio)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.FinCambio[ pkCambio=" + pkCambio + " ]";
    }
    
}
