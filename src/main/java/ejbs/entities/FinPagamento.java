/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author smakambo
 */
@Entity
@Table(name = "fin_pagamento", catalog = "sig_ucan_db", schema = "public")
@NamedQueries(
{
    @NamedQuery(name = "FinPagamento.findAll", query = "SELECT f FROM FinPagamento f"),
    @NamedQuery(name = "FinPagamento.findByPkPagamento", query = "SELECT f FROM FinPagamento f WHERE f.pkPagamento = :pkPagamento"),
    @NamedQuery(name = "FinPagamento.findByValorPagamento", query = "SELECT f FROM FinPagamento f WHERE f.valorPagamento = :valorPagamento"),
    @NamedQuery(name = "FinPagamento.findByDataPagemnto", query = "SELECT f FROM FinPagamento f WHERE f.dataPagemnto = :dataPagemnto"),
    @NamedQuery(name = "FinPagamento.findByDescricaoPagamento", query = "SELECT f FROM FinPagamento f WHERE f.descricaoPagamento = :descricaoPagamento"),
    @NamedQuery(name = "FinPagamento.findByValorEntregue", query = "SELECT f FROM FinPagamento f WHERE f.valorEntregue = :valorEntregue"),
    @NamedQuery(name = "FinPagamento.findByTroco", query = "SELECT f FROM FinPagamento f WHERE f.troco = :troco"),
    @NamedQuery(name = "FinPagamento.findByEstado", query = "SELECT f FROM FinPagamento f WHERE f.estado = :estado")
})
public class FinPagamento implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_pagamento", nullable = false)
    private Integer pkPagamento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor_pagamento", nullable = false)
    private double valorPagamento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "data_pagemnto", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataPagemnto;
    @Size(max = 2147483647)
    @Column(name = "descricao_pagamento", length = 2147483647)
    private String descricaoPagamento;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valor_entregue", precision = 17, scale = 17)
    private Double valorEntregue;
    @Column(precision = 17, scale = 17)
    private Double troco;
    private Boolean estado;
    @JoinColumn(name = "fk_modo_pagamento", referencedColumnName = "pk_modo_pagamento")
    @ManyToOne
    private FinModoPagamento fkModoPagamento;
    @JoinColumn(name = "fk_utilizador", referencedColumnName = "pk_seg_conta")
    @ManyToOne
    private SegConta fkUtilizador;
    @OneToMany(mappedBy = "fkPagamento")
    private List<FinOperacoesCaixa> finOperacoesCaixaList;

    public FinPagamento()
    {
    }

    public FinPagamento(Integer pkPagamento)
    {
        this.pkPagamento = pkPagamento;
    }

    public FinPagamento(Integer pkPagamento, double valorPagamento, Date dataPagemnto)
    {
        this.pkPagamento = pkPagamento;
        this.valorPagamento = valorPagamento;
        this.dataPagemnto = dataPagemnto;
    }

    public Integer getPkPagamento()
    {
        return pkPagamento;
    }

    public void setPkPagamento(Integer pkPagamento)
    {
        this.pkPagamento = pkPagamento;
    }

    public double getValorPagamento()
    {
        return valorPagamento;
    }

    public void setValorPagamento(double valorPagamento)
    {
        this.valorPagamento = valorPagamento;
    }

    public Date getDataPagemnto()
    {
        return dataPagemnto;
    }

    public void setDataPagemnto(Date dataPagemnto)
    {
        this.dataPagemnto = dataPagemnto;
    }

    public String getDescricaoPagamento()
    {
        return descricaoPagamento;
    }

    public void setDescricaoPagamento(String descricaoPagamento)
    {
        this.descricaoPagamento = descricaoPagamento;
    }

    public Double getValorEntregue()
    {
        return valorEntregue;
    }

    public void setValorEntregue(Double valorEntregue)
    {
        this.valorEntregue = valorEntregue;
    }

    public Double getTroco()
    {
        return troco;
    }

    public void setTroco(Double troco)
    {
        this.troco = troco;
    }

    public Boolean getEstado()
    {
        return estado;
    }

    public void setEstado(Boolean estado)
    {
        this.estado = estado;
    }

    public FinModoPagamento getFkModoPagamento()
    {
        return fkModoPagamento;
    }

    public void setFkModoPagamento(FinModoPagamento fkModoPagamento)
    {
        this.fkModoPagamento = fkModoPagamento;
    }

    public SegConta getFkUtilizador()
    {
        return fkUtilizador;
    }

    public void setFkUtilizador(SegConta fkUtilizador)
    {
        this.fkUtilizador = fkUtilizador;
    }

    public List<FinOperacoesCaixa> getFinOperacoesCaixaList()
    {
        return finOperacoesCaixaList;
    }

    public void setFinOperacoesCaixaList(List<FinOperacoesCaixa> finOperacoesCaixaList)
    {
        this.finOperacoesCaixaList = finOperacoesCaixaList;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (pkPagamento != null ? pkPagamento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FinPagamento))
        {
            return false;
        }
        FinPagamento other = (FinPagamento) object;
        if ((this.pkPagamento == null && other.pkPagamento != null) || (this.pkPagamento != null && !this.pkPagamento.equals(other.pkPagamento)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.FinPagamento[ pkPagamento=" + pkPagamento + " ]";
    }
    
}
