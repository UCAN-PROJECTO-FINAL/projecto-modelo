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
@Table(name = "fin_contas_receber", catalog = "sig_ucan_db", schema = "public")
@NamedQueries(
{
    @NamedQuery(name = "FinContasReceber.findAll", query = "SELECT f FROM FinContasReceber f"),
    @NamedQuery(name = "FinContasReceber.findByPkContasReceber", query = "SELECT f FROM FinContasReceber f WHERE f.pkContasReceber = :pkContasReceber"),
    @NamedQuery(name = "FinContasReceber.findByDataCadastro", query = "SELECT f FROM FinContasReceber f WHERE f.dataCadastro = :dataCadastro"),
    @NamedQuery(name = "FinContasReceber.findByDescricao", query = "SELECT f FROM FinContasReceber f WHERE f.descricao = :descricao"),
    @NamedQuery(name = "FinContasReceber.findByObservacao", query = "SELECT f FROM FinContasReceber f WHERE f.observacao = :observacao"),
    @NamedQuery(name = "FinContasReceber.findByValor", query = "SELECT f FROM FinContasReceber f WHERE f.valor = :valor"),
    @NamedQuery(name = "FinContasReceber.findByDataVencimento", query = "SELECT f FROM FinContasReceber f WHERE f.dataVencimento = :dataVencimento"),
    @NamedQuery(name = "FinContasReceber.findByDataEmissao", query = "SELECT f FROM FinContasReceber f WHERE f.dataEmissao = :dataEmissao"),
    @NamedQuery(name = "FinContasReceber.findByDataPagamento", query = "SELECT f FROM FinContasReceber f WHERE f.dataPagamento = :dataPagamento"),
    @NamedQuery(name = "FinContasReceber.findByDesconto", query = "SELECT f FROM FinContasReceber f WHERE f.desconto = :desconto")
})
public class FinContasReceber implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_contas_receber", nullable = false)
    private Integer pkContasReceber;
    @Column(name = "data_cadastro")
    @Temporal(TemporalType.DATE)
    private Date dataCadastro;
    @Size(max = 2147483647)
    @Column(length = 2147483647)
    private String descricao;
    @Size(max = 2147483647)
    @Column(length = 2147483647)
    private String observacao;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(precision = 18, scale = 2)
    private BigDecimal valor;
    @Column(name = "data_vencimento")
    @Temporal(TemporalType.DATE)
    private Date dataVencimento;
    @Column(name = "data_emissao")
    @Temporal(TemporalType.DATE)
    private Date dataEmissao;
    @Column(name = "data_pagamento")
    @Temporal(TemporalType.DATE)
    private Date dataPagamento;
    @Column(precision = 18, scale = 2)
    private BigDecimal desconto;
    @JoinColumn(name = "fk_centro_custo", referencedColumnName = "pk_estrutura_fisica")
    @ManyToOne
    private EstruturaFisica fkCentroCusto;
    @JoinColumn(name = "fk_categoria", referencedColumnName = "pk_categoria_subcategoria")
    @ManyToOne
    private FinCategoriaSubcategoria fkCategoria;
    @JoinColumn(name = "fk_conta", referencedColumnName = "pk_conta")
    @ManyToOne
    private FinConta fkConta;
    @JoinColumn(name = "fk_documento", referencedColumnName = "pk_documento")
    @ManyToOne
    private FinDocumento fkDocumento;
    @JoinColumn(name = "fk_cliente", referencedColumnName = "pk_entidade")
    @ManyToOne
    private GrlEntidade fkCliente;
    @JoinColumn(name = "fk_utilizador", referencedColumnName = "pk_seg_conta")
    @ManyToOne
    private SegConta fkUtilizador;

    public FinContasReceber()
    {
    }

    public FinContasReceber(Integer pkContasReceber)
    {
        this.pkContasReceber = pkContasReceber;
    }

    public Integer getPkContasReceber()
    {
        return pkContasReceber;
    }

    public void setPkContasReceber(Integer pkContasReceber)
    {
        this.pkContasReceber = pkContasReceber;
    }

    public Date getDataCadastro()
    {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro)
    {
        this.dataCadastro = dataCadastro;
    }

    public String getDescricao()
    {
        return descricao;
    }

    public void setDescricao(String descricao)
    {
        this.descricao = descricao;
    }

    public String getObservacao()
    {
        return observacao;
    }

    public void setObservacao(String observacao)
    {
        this.observacao = observacao;
    }

    public BigDecimal getValor()
    {
        return valor;
    }

    public void setValor(BigDecimal valor)
    {
        this.valor = valor;
    }

    public Date getDataVencimento()
    {
        return dataVencimento;
    }

    public void setDataVencimento(Date dataVencimento)
    {
        this.dataVencimento = dataVencimento;
    }

    public Date getDataEmissao()
    {
        return dataEmissao;
    }

    public void setDataEmissao(Date dataEmissao)
    {
        this.dataEmissao = dataEmissao;
    }

    public Date getDataPagamento()
    {
        return dataPagamento;
    }

    public void setDataPagamento(Date dataPagamento)
    {
        this.dataPagamento = dataPagamento;
    }

    public BigDecimal getDesconto()
    {
        return desconto;
    }

    public void setDesconto(BigDecimal desconto)
    {
        this.desconto = desconto;
    }

    public EstruturaFisica getFkCentroCusto()
    {
        return fkCentroCusto;
    }

    public void setFkCentroCusto(EstruturaFisica fkCentroCusto)
    {
        this.fkCentroCusto = fkCentroCusto;
    }

    public FinCategoriaSubcategoria getFkCategoria()
    {
        return fkCategoria;
    }

    public void setFkCategoria(FinCategoriaSubcategoria fkCategoria)
    {
        this.fkCategoria = fkCategoria;
    }

    public FinConta getFkConta()
    {
        return fkConta;
    }

    public void setFkConta(FinConta fkConta)
    {
        this.fkConta = fkConta;
    }

    public FinDocumento getFkDocumento()
    {
        return fkDocumento;
    }

    public void setFkDocumento(FinDocumento fkDocumento)
    {
        this.fkDocumento = fkDocumento;
    }

    public GrlEntidade getFkCliente()
    {
        return fkCliente;
    }

    public void setFkCliente(GrlEntidade fkCliente)
    {
        this.fkCliente = fkCliente;
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
        hash += (pkContasReceber != null ? pkContasReceber.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FinContasReceber))
        {
            return false;
        }
        FinContasReceber other = (FinContasReceber) object;
        if ((this.pkContasReceber == null && other.pkContasReceber != null) || (this.pkContasReceber != null && !this.pkContasReceber.equals(other.pkContasReceber)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.FinContasReceber[ pkContasReceber=" + pkContasReceber + " ]";
    }
    
}
