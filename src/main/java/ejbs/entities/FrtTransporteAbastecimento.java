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
@Table(name = "frt_transporte_abastecimento", catalog = "sig_ucan_db", schema = "public")
@NamedQueries(
{
    @NamedQuery(name = "FrtTransporteAbastecimento.findAll", query = "SELECT f FROM FrtTransporteAbastecimento f"),
    @NamedQuery(name = "FrtTransporteAbastecimento.findByPkAbastecimento", query = "SELECT f FROM FrtTransporteAbastecimento f WHERE f.pkAbastecimento = :pkAbastecimento"),
    @NamedQuery(name = "FrtTransporteAbastecimento.findByDescricao", query = "SELECT f FROM FrtTransporteAbastecimento f WHERE f.descricao = :descricao"),
    @NamedQuery(name = "FrtTransporteAbastecimento.findByDataAbastecimento", query = "SELECT f FROM FrtTransporteAbastecimento f WHERE f.dataAbastecimento = :dataAbastecimento"),
    @NamedQuery(name = "FrtTransporteAbastecimento.findByPrecoPorLitro", query = "SELECT f FROM FrtTransporteAbastecimento f WHERE f.precoPorLitro = :precoPorLitro"),
    @NamedQuery(name = "FrtTransporteAbastecimento.findByQtdLitros", query = "SELECT f FROM FrtTransporteAbastecimento f WHERE f.qtdLitros = :qtdLitros"),
    @NamedQuery(name = "FrtTransporteAbastecimento.findByPrecoTotal", query = "SELECT f FROM FrtTransporteAbastecimento f WHERE f.precoTotal = :precoTotal"),
    @NamedQuery(name = "FrtTransporteAbastecimento.findByKmAcual", query = "SELECT f FROM FrtTransporteAbastecimento f WHERE f.kmAcual = :kmAcual"),
    @NamedQuery(name = "FrtTransporteAbastecimento.findByDataPagamento", query = "SELECT f FROM FrtTransporteAbastecimento f WHERE f.dataPagamento = :dataPagamento"),
    @NamedQuery(name = "FrtTransporteAbastecimento.findByDataCadastro", query = "SELECT f FROM FrtTransporteAbastecimento f WHERE f.dataCadastro = :dataCadastro"),
    @NamedQuery(name = "FrtTransporteAbastecimento.findByObservacao", query = "SELECT f FROM FrtTransporteAbastecimento f WHERE f.observacao = :observacao")
})
public class FrtTransporteAbastecimento implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_abastecimento", nullable = false)
    private Integer pkAbastecimento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(nullable = false, length = 2147483647)
    private String descricao;
    @Basic(optional = false)
    @NotNull
    @Column(name = "data_abastecimento", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataAbastecimento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "preco_por_litro", nullable = false)
    private double precoPorLitro;
    @Basic(optional = false)
    @NotNull
    @Column(name = "qtd_litros", nullable = false)
    private int qtdLitros;
    @Basic(optional = false)
    @NotNull
    @Column(name = "preco_total", nullable = false)
    private double precoTotal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "km_acual", nullable = false)
    private double kmAcual;
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
    @JoinColumn(name = "fk_transporte_agenda", referencedColumnName = "pk_agendar_transporte")
    @ManyToOne
    private FrtTransporteAgendar fkTransporteAgenda;
    @JoinColumn(name = "fk_bombas_combustivel", referencedColumnName = "pk_bomba_combustivel")
    @ManyToOne
    private FrtTransporteBombaCombustivel fkBombasCombustivel;
    @JoinColumn(name = "fk_tipo_combustivel", referencedColumnName = "pk_tipo_combustivel")
    @ManyToOne
    private FrtTransporteTipoCombustivel fkTipoCombustivel;

    public FrtTransporteAbastecimento()
    {
    }

    public FrtTransporteAbastecimento(Integer pkAbastecimento)
    {
        this.pkAbastecimento = pkAbastecimento;
    }

    public FrtTransporteAbastecimento(Integer pkAbastecimento, String descricao, Date dataAbastecimento, double precoPorLitro, int qtdLitros, double precoTotal, double kmAcual, Date dataPagamento, Date dataCadastro)
    {
        this.pkAbastecimento = pkAbastecimento;
        this.descricao = descricao;
        this.dataAbastecimento = dataAbastecimento;
        this.precoPorLitro = precoPorLitro;
        this.qtdLitros = qtdLitros;
        this.precoTotal = precoTotal;
        this.kmAcual = kmAcual;
        this.dataPagamento = dataPagamento;
        this.dataCadastro = dataCadastro;
    }

    public Integer getPkAbastecimento()
    {
        return pkAbastecimento;
    }

    public void setPkAbastecimento(Integer pkAbastecimento)
    {
        this.pkAbastecimento = pkAbastecimento;
    }

    public String getDescricao()
    {
        return descricao;
    }

    public void setDescricao(String descricao)
    {
        this.descricao = descricao;
    }

    public Date getDataAbastecimento()
    {
        return dataAbastecimento;
    }

    public void setDataAbastecimento(Date dataAbastecimento)
    {
        this.dataAbastecimento = dataAbastecimento;
    }

    public double getPrecoPorLitro()
    {
        return precoPorLitro;
    }

    public void setPrecoPorLitro(double precoPorLitro)
    {
        this.precoPorLitro = precoPorLitro;
    }

    public int getQtdLitros()
    {
        return qtdLitros;
    }

    public void setQtdLitros(int qtdLitros)
    {
        this.qtdLitros = qtdLitros;
    }

    public double getPrecoTotal()
    {
        return precoTotal;
    }

    public void setPrecoTotal(double precoTotal)
    {
        this.precoTotal = precoTotal;
    }

    public double getKmAcual()
    {
        return kmAcual;
    }

    public void setKmAcual(double kmAcual)
    {
        this.kmAcual = kmAcual;
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

    public FrtTransporteAgendar getFkTransporteAgenda()
    {
        return fkTransporteAgenda;
    }

    public void setFkTransporteAgenda(FrtTransporteAgendar fkTransporteAgenda)
    {
        this.fkTransporteAgenda = fkTransporteAgenda;
    }

    public FrtTransporteBombaCombustivel getFkBombasCombustivel()
    {
        return fkBombasCombustivel;
    }

    public void setFkBombasCombustivel(FrtTransporteBombaCombustivel fkBombasCombustivel)
    {
        this.fkBombasCombustivel = fkBombasCombustivel;
    }

    public FrtTransporteTipoCombustivel getFkTipoCombustivel()
    {
        return fkTipoCombustivel;
    }

    public void setFkTipoCombustivel(FrtTransporteTipoCombustivel fkTipoCombustivel)
    {
        this.fkTipoCombustivel = fkTipoCombustivel;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (pkAbastecimento != null ? pkAbastecimento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FrtTransporteAbastecimento))
        {
            return false;
        }
        FrtTransporteAbastecimento other = (FrtTransporteAbastecimento) object;
        if ((this.pkAbastecimento == null && other.pkAbastecimento != null) || (this.pkAbastecimento != null && !this.pkAbastecimento.equals(other.pkAbastecimento)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.FrtTransporteAbastecimento[ pkAbastecimento=" + pkAbastecimento + " ]";
    }
    
}
