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
import javax.validation.constraints.Size;

/**
 *
 * @author smakambo
 */
@Entity
@Table(name = "frt_transporte_manutencao", catalog = "sig_ucan_db", schema = "public")
@NamedQueries(
{
    @NamedQuery(name = "FrtTransporteManutencao.findAll", query = "SELECT f FROM FrtTransporteManutencao f"),
    @NamedQuery(name = "FrtTransporteManutencao.findByPkManutencao", query = "SELECT f FROM FrtTransporteManutencao f WHERE f.pkManutencao = :pkManutencao"),
    @NamedQuery(name = "FrtTransporteManutencao.findByDescricao", query = "SELECT f FROM FrtTransporteManutencao f WHERE f.descricao = :descricao"),
    @NamedQuery(name = "FrtTransporteManutencao.findByLocalManutencao", query = "SELECT f FROM FrtTransporteManutencao f WHERE f.localManutencao = :localManutencao"),
    @NamedQuery(name = "FrtTransporteManutencao.findByPrecoManutencao", query = "SELECT f FROM FrtTransporteManutencao f WHERE f.precoManutencao = :precoManutencao"),
    @NamedQuery(name = "FrtTransporteManutencao.findByDataPagamento", query = "SELECT f FROM FrtTransporteManutencao f WHERE f.dataPagamento = :dataPagamento"),
    @NamedQuery(name = "FrtTransporteManutencao.findByDataManutencao", query = "SELECT f FROM FrtTransporteManutencao f WHERE f.dataManutencao = :dataManutencao"),
    @NamedQuery(name = "FrtTransporteManutencao.findByDataCadastro", query = "SELECT f FROM FrtTransporteManutencao f WHERE f.dataCadastro = :dataCadastro"),
    @NamedQuery(name = "FrtTransporteManutencao.findByObservacao", query = "SELECT f FROM FrtTransporteManutencao f WHERE f.observacao = :observacao")
})
public class FrtTransporteManutencao implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_manutencao", nullable = false)
    private Integer pkManutencao;
    @Size(max = 2147483647)
    @Column(length = 2147483647)
    private String descricao;
    @Size(max = 2147483647)
    @Column(name = "local_manutencao", length = 2147483647)
    private String localManutencao;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "preco_manutencao", precision = 17, scale = 17)
    private Double precoManutencao;
    @Column(name = "data_pagamento")
    @Temporal(TemporalType.DATE)
    private Date dataPagamento;
    @Column(name = "data_manutencao")
    @Temporal(TemporalType.DATE)
    private Date dataManutencao;
    @Column(name = "data_cadastro")
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
    @JoinColumn(name = "fk_tipo_manutencao", referencedColumnName = "pk_tipo_manutencao")
    @ManyToOne
    private FrtTransporteTipoManutencao fkTipoManutencao;

    public FrtTransporteManutencao()
    {
    }

    public FrtTransporteManutencao(Integer pkManutencao)
    {
        this.pkManutencao = pkManutencao;
    }

    public Integer getPkManutencao()
    {
        return pkManutencao;
    }

    public void setPkManutencao(Integer pkManutencao)
    {
        this.pkManutencao = pkManutencao;
    }

    public String getDescricao()
    {
        return descricao;
    }

    public void setDescricao(String descricao)
    {
        this.descricao = descricao;
    }

    public String getLocalManutencao()
    {
        return localManutencao;
    }

    public void setLocalManutencao(String localManutencao)
    {
        this.localManutencao = localManutencao;
    }

    public Double getPrecoManutencao()
    {
        return precoManutencao;
    }

    public void setPrecoManutencao(Double precoManutencao)
    {
        this.precoManutencao = precoManutencao;
    }

    public Date getDataPagamento()
    {
        return dataPagamento;
    }

    public void setDataPagamento(Date dataPagamento)
    {
        this.dataPagamento = dataPagamento;
    }

    public Date getDataManutencao()
    {
        return dataManutencao;
    }

    public void setDataManutencao(Date dataManutencao)
    {
        this.dataManutencao = dataManutencao;
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

    public FrtTransporteTipoManutencao getFkTipoManutencao()
    {
        return fkTipoManutencao;
    }

    public void setFkTipoManutencao(FrtTransporteTipoManutencao fkTipoManutencao)
    {
        this.fkTipoManutencao = fkTipoManutencao;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (pkManutencao != null ? pkManutencao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FrtTransporteManutencao))
        {
            return false;
        }
        FrtTransporteManutencao other = (FrtTransporteManutencao) object;
        if ((this.pkManutencao == null && other.pkManutencao != null) || (this.pkManutencao != null && !this.pkManutencao.equals(other.pkManutencao)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.FrtTransporteManutencao[ pkManutencao=" + pkManutencao + " ]";
    }
    
}
