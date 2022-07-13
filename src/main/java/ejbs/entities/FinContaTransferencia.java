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
@Table(name = "fin_conta_transferencia", catalog = "sig_ucan_db", schema = "public")
@NamedQueries(
{
    @NamedQuery(name = "FinContaTransferencia.findAll", query = "SELECT f FROM FinContaTransferencia f"),
    @NamedQuery(name = "FinContaTransferencia.findByPkContaTransferencia", query = "SELECT f FROM FinContaTransferencia f WHERE f.pkContaTransferencia = :pkContaTransferencia"),
    @NamedQuery(name = "FinContaTransferencia.findByValor", query = "SELECT f FROM FinContaTransferencia f WHERE f.valor = :valor"),
    @NamedQuery(name = "FinContaTransferencia.findByDataTransferencia", query = "SELECT f FROM FinContaTransferencia f WHERE f.dataTransferencia = :dataTransferencia")
})
public class FinContaTransferencia implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "pk_conta_transferencia", nullable = false)
    private Integer pkContaTransferencia;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false, precision = 18, scale = 2)
    private BigDecimal valor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "data_transferencia", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataTransferencia;
    @JoinColumn(name = "fk_conta_destino", referencedColumnName = "pk_conta", nullable = false)
    @ManyToOne(optional = false)
    private FinConta fkContaDestino;
    @JoinColumn(name = "fk_conta_origem", referencedColumnName = "pk_conta", nullable = false)
    @ManyToOne(optional = false)
    private FinConta fkContaOrigem;
    @JoinColumn(name = "fk_utilizador", referencedColumnName = "pk_seg_conta")
    @ManyToOne
    private SegConta fkUtilizador;

    public FinContaTransferencia()
    {
    }

    public FinContaTransferencia(Integer pkContaTransferencia)
    {
        this.pkContaTransferencia = pkContaTransferencia;
    }

    public FinContaTransferencia(Integer pkContaTransferencia, BigDecimal valor, Date dataTransferencia)
    {
        this.pkContaTransferencia = pkContaTransferencia;
        this.valor = valor;
        this.dataTransferencia = dataTransferencia;
    }

    public Integer getPkContaTransferencia()
    {
        return pkContaTransferencia;
    }

    public void setPkContaTransferencia(Integer pkContaTransferencia)
    {
        this.pkContaTransferencia = pkContaTransferencia;
    }

    public BigDecimal getValor()
    {
        return valor;
    }

    public void setValor(BigDecimal valor)
    {
        this.valor = valor;
    }

    public Date getDataTransferencia()
    {
        return dataTransferencia;
    }

    public void setDataTransferencia(Date dataTransferencia)
    {
        this.dataTransferencia = dataTransferencia;
    }

    public FinConta getFkContaDestino()
    {
        return fkContaDestino;
    }

    public void setFkContaDestino(FinConta fkContaDestino)
    {
        this.fkContaDestino = fkContaDestino;
    }

    public FinConta getFkContaOrigem()
    {
        return fkContaOrigem;
    }

    public void setFkContaOrigem(FinConta fkContaOrigem)
    {
        this.fkContaOrigem = fkContaOrigem;
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
        hash += (pkContaTransferencia != null ? pkContaTransferencia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FinContaTransferencia))
        {
            return false;
        }
        FinContaTransferencia other = (FinContaTransferencia) object;
        if ((this.pkContaTransferencia == null && other.pkContaTransferencia != null) || (this.pkContaTransferencia != null && !this.pkContaTransferencia.equals(other.pkContaTransferencia)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.FinContaTransferencia[ pkContaTransferencia=" + pkContaTransferencia + " ]";
    }
    
}
