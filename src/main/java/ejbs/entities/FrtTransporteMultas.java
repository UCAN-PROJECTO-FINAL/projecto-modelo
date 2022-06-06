/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.entities;

import java.io.Serializable;
import java.util.Date;
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
@Table(name = "frt_transporte_multas", catalog = "sig_ucan_db", schema = "public")
@NamedQueries(
{
    @NamedQuery(name = "FrtTransporteMultas.findAll", query = "SELECT f FROM FrtTransporteMultas f"),
    @NamedQuery(name = "FrtTransporteMultas.findByPkMultas", query = "SELECT f FROM FrtTransporteMultas f WHERE f.pkMultas = :pkMultas"),
    @NamedQuery(name = "FrtTransporteMultas.findByDescricao", query = "SELECT f FROM FrtTransporteMultas f WHERE f.descricao = :descricao"),
    @NamedQuery(name = "FrtTransporteMultas.findByLocalMulta", query = "SELECT f FROM FrtTransporteMultas f WHERE f.localMulta = :localMulta"),
    @NamedQuery(name = "FrtTransporteMultas.findByPrecoMulta", query = "SELECT f FROM FrtTransporteMultas f WHERE f.precoMulta = :precoMulta"),
    @NamedQuery(name = "FrtTransporteMultas.findByDataMulta", query = "SELECT f FROM FrtTransporteMultas f WHERE f.dataMulta = :dataMulta"),
    @NamedQuery(name = "FrtTransporteMultas.findByDataVencimentoMulta", query = "SELECT f FROM FrtTransporteMultas f WHERE f.dataVencimentoMulta = :dataVencimentoMulta"),
    @NamedQuery(name = "FrtTransporteMultas.findByDataPagamento", query = "SELECT f FROM FrtTransporteMultas f WHERE f.dataPagamento = :dataPagamento"),
    @NamedQuery(name = "FrtTransporteMultas.findByDataCadastro", query = "SELECT f FROM FrtTransporteMultas f WHERE f.dataCadastro = :dataCadastro"),
    @NamedQuery(name = "FrtTransporteMultas.findByObservacao", query = "SELECT f FROM FrtTransporteMultas f WHERE f.observacao = :observacao")
})
public class FrtTransporteMultas implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_multas", nullable = false)
    private Integer pkMultas;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(nullable = false, length = 2147483647)
    private String descricao;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "local_multa", nullable = false, length = 2147483647)
    private String localMulta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "preco_multa", nullable = false)
    private double precoMulta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "data_multa", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataMulta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "data_vencimento_multa", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataVencimentoMulta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "data_pagamento", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataPagamento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "data_cadastro", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataCadastro;
    @Size(max = 2147483647)
    @Column(length = 2147483647)
    private String observacao;
    @JoinColumn(name = "fk_modo_pagamento", referencedColumnName = "pk_modo_pagamento")
    @ManyToOne
    private FinModoPagamento fkModoPagamento;
    @JoinColumn(name = "fk_tipo_infraccao", referencedColumnName = "pk_tipo_infracao")
    @ManyToOne
    private FrtTransporteTipoInfraccao fkTipoInfraccao;
    @JoinColumn(name = "fk_transporte", referencedColumnName = "pk_pt_transporte")
    @ManyToOne
    private PtTransporte fkTransporte;

    public FrtTransporteMultas()
    {
    }

    public FrtTransporteMultas(Integer pkMultas)
    {
        this.pkMultas = pkMultas;
    }

    public FrtTransporteMultas(Integer pkMultas, String descricao, String localMulta, double precoMulta, Date dataMulta, Date dataVencimentoMulta, Date dataPagamento, Date dataCadastro)
    {
        this.pkMultas = pkMultas;
        this.descricao = descricao;
        this.localMulta = localMulta;
        this.precoMulta = precoMulta;
        this.dataMulta = dataMulta;
        this.dataVencimentoMulta = dataVencimentoMulta;
        this.dataPagamento = dataPagamento;
        this.dataCadastro = dataCadastro;
    }

    public Integer getPkMultas()
    {
        return pkMultas;
    }

    public void setPkMultas(Integer pkMultas)
    {
        this.pkMultas = pkMultas;
    }

    public String getDescricao()
    {
        return descricao;
    }

    public void setDescricao(String descricao)
    {
        this.descricao = descricao;
    }

    public String getLocalMulta()
    {
        return localMulta;
    }

    public void setLocalMulta(String localMulta)
    {
        this.localMulta = localMulta;
    }

    public double getPrecoMulta()
    {
        return precoMulta;
    }

    public void setPrecoMulta(double precoMulta)
    {
        this.precoMulta = precoMulta;
    }

    public Date getDataMulta()
    {
        return dataMulta;
    }

    public void setDataMulta(Date dataMulta)
    {
        this.dataMulta = dataMulta;
    }

    public Date getDataVencimentoMulta()
    {
        return dataVencimentoMulta;
    }

    public void setDataVencimentoMulta(Date dataVencimentoMulta)
    {
        this.dataVencimentoMulta = dataVencimentoMulta;
    }

    public Date getDataPagamento()
    {
        return dataPagamento;
    }

    public void setDataPagamento(Date dataPagamento)
    {
        this.dataPagamento = dataPagamento;
    }

    public Date getDataCadastro()
    {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro)
    {
        this.dataCadastro = dataCadastro;
    }

    public String getObservacao()
    {
        return observacao;
    }

    public void setObservacao(String observacao)
    {
        this.observacao = observacao;
    }

    public FinModoPagamento getFkModoPagamento()
    {
        return fkModoPagamento;
    }

    public void setFkModoPagamento(FinModoPagamento fkModoPagamento)
    {
        this.fkModoPagamento = fkModoPagamento;
    }

    public FrtTransporteTipoInfraccao getFkTipoInfraccao()
    {
        return fkTipoInfraccao;
    }

    public void setFkTipoInfraccao(FrtTransporteTipoInfraccao fkTipoInfraccao)
    {
        this.fkTipoInfraccao = fkTipoInfraccao;
    }

    public PtTransporte getFkTransporte()
    {
        return fkTransporte;
    }

    public void setFkTransporte(PtTransporte fkTransporte)
    {
        this.fkTransporte = fkTransporte;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (pkMultas != null ? pkMultas.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FrtTransporteMultas))
        {
            return false;
        }
        FrtTransporteMultas other = (FrtTransporteMultas) object;
        if ((this.pkMultas == null && other.pkMultas != null) || (this.pkMultas != null && !this.pkMultas.equals(other.pkMultas)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.FrtTransporteMultas[ pkMultas=" + pkMultas + " ]";
    }
    
}
