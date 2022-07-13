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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author smakambo
 */
@Entity
@Table(name = "fin_modo_pagamento", catalog = "sig_ucan_db", schema = "public")
@NamedQueries(
{
    @NamedQuery(name = "FinModoPagamento.findAll", query = "SELECT f FROM FinModoPagamento f"),
    @NamedQuery(name = "FinModoPagamento.findByDescricao", query = "SELECT f FROM FinModoPagamento f WHERE f.descricao = :descricao"),
    @NamedQuery(name = "FinModoPagamento.findByPkModoPagamento", query = "SELECT f FROM FinModoPagamento f WHERE f.pkModoPagamento = :pkModoPagamento")
})
public class FinModoPagamento implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Size(max = 50)
    @Column(length = 50)
    private String descricao;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_modo_pagamento", nullable = false)
    private Integer pkModoPagamento;
    @OneToMany(mappedBy = "fkModoPagamento")
    private List<FinOperacoesContaCorrente> finOperacoesContaCorrenteList;
    @JoinColumn(name = "fk_moeda", referencedColumnName = "pk_moeda")
    @ManyToOne
    private FinMoeda fkMoeda;
    @OneToMany(mappedBy = "fkModoPagamento")
    private List<FrtTransporteMultas> frtTransporteMultasList;
    @OneToMany(mappedBy = "fkModoPagamento")
    private List<FrtTransporteManutencao> frtTransporteManutencaoList;
    @OneToMany(mappedBy = "fkModoPagamento")
    private List<FinContaCorrente> finContaCorrenteList;
    @OneToMany(mappedBy = "fkModoPagamento")
    private List<FrtTransporteConfiguracoes> frtTransporteConfiguracoesList;
    @OneToMany(mappedBy = "fkModoPagamento")
    private List<FinPagamento> finPagamentoList;
    @OneToMany(mappedBy = "fkModoPagamento")
    private List<FrtTransporteAbastecimento> frtTransporteAbastecimentoList;

    public FinModoPagamento()
    {
    }

    public FinModoPagamento(Integer pkModoPagamento)
    {
        this.pkModoPagamento = pkModoPagamento;
    }

    public String getDescricao()
    {
        return descricao;
    }

    public void setDescricao(String descricao)
    {
        this.descricao = descricao;
    }

    public Integer getPkModoPagamento()
    {
        return pkModoPagamento;
    }

    public void setPkModoPagamento(Integer pkModoPagamento)
    {
        this.pkModoPagamento = pkModoPagamento;
    }

    public List<FinOperacoesContaCorrente> getFinOperacoesContaCorrenteList()
    {
        return finOperacoesContaCorrenteList;
    }

    public void setFinOperacoesContaCorrenteList(List<FinOperacoesContaCorrente> finOperacoesContaCorrenteList)
    {
        this.finOperacoesContaCorrenteList = finOperacoesContaCorrenteList;
    }

    public FinMoeda getFkMoeda()
    {
        return fkMoeda;
    }

    public void setFkMoeda(FinMoeda fkMoeda)
    {
        this.fkMoeda = fkMoeda;
    }

    public List<FrtTransporteMultas> getFrtTransporteMultasList()
    {
        return frtTransporteMultasList;
    }

    public void setFrtTransporteMultasList(List<FrtTransporteMultas> frtTransporteMultasList)
    {
        this.frtTransporteMultasList = frtTransporteMultasList;
    }

    public List<FrtTransporteManutencao> getFrtTransporteManutencaoList()
    {
        return frtTransporteManutencaoList;
    }

    public void setFrtTransporteManutencaoList(List<FrtTransporteManutencao> frtTransporteManutencaoList)
    {
        this.frtTransporteManutencaoList = frtTransporteManutencaoList;
    }

    public List<FinContaCorrente> getFinContaCorrenteList()
    {
        return finContaCorrenteList;
    }

    public void setFinContaCorrenteList(List<FinContaCorrente> finContaCorrenteList)
    {
        this.finContaCorrenteList = finContaCorrenteList;
    }

    public List<FrtTransporteConfiguracoes> getFrtTransporteConfiguracoesList()
    {
        return frtTransporteConfiguracoesList;
    }

    public void setFrtTransporteConfiguracoesList(List<FrtTransporteConfiguracoes> frtTransporteConfiguracoesList)
    {
        this.frtTransporteConfiguracoesList = frtTransporteConfiguracoesList;
    }

    public List<FinPagamento> getFinPagamentoList()
    {
        return finPagamentoList;
    }

    public void setFinPagamentoList(List<FinPagamento> finPagamentoList)
    {
        this.finPagamentoList = finPagamentoList;
    }

    public List<FrtTransporteAbastecimento> getFrtTransporteAbastecimentoList()
    {
        return frtTransporteAbastecimentoList;
    }

    public void setFrtTransporteAbastecimentoList(List<FrtTransporteAbastecimento> frtTransporteAbastecimentoList)
    {
        this.frtTransporteAbastecimentoList = frtTransporteAbastecimentoList;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (pkModoPagamento != null ? pkModoPagamento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FinModoPagamento))
        {
            return false;
        }
        FinModoPagamento other = (FinModoPagamento) object;
        if ((this.pkModoPagamento == null && other.pkModoPagamento != null) || (this.pkModoPagamento != null && !this.pkModoPagamento.equals(other.pkModoPagamento)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.FinModoPagamento[ pkModoPagamento=" + pkModoPagamento + " ]";
    }
    
}
