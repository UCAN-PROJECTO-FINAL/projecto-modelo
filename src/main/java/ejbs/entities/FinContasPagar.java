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
@Table(name = "fin_contas_pagar", catalog = "sig_ucan_db", schema = "public")
@NamedQueries(
{
    @NamedQuery(name = "FinContasPagar.findAll", query = "SELECT f FROM FinContasPagar f"),
    @NamedQuery(name = "FinContasPagar.findByDescricao", query = "SELECT f FROM FinContasPagar f WHERE f.descricao = :descricao"),
    @NamedQuery(name = "FinContasPagar.findByValor", query = "SELECT f FROM FinContasPagar f WHERE f.valor = :valor"),
    @NamedQuery(name = "FinContasPagar.findByDataEmissao", query = "SELECT f FROM FinContasPagar f WHERE f.dataEmissao = :dataEmissao"),
    @NamedQuery(name = "FinContasPagar.findByDataVencimento", query = "SELECT f FROM FinContasPagar f WHERE f.dataVencimento = :dataVencimento"),
    @NamedQuery(name = "FinContasPagar.findByObservacao", query = "SELECT f FROM FinContasPagar f WHERE f.observacao = :observacao"),
    @NamedQuery(name = "FinContasPagar.findByDataCadastro", query = "SELECT f FROM FinContasPagar f WHERE f.dataCadastro = :dataCadastro"),
    @NamedQuery(name = "FinContasPagar.findByPkContasPagar", query = "SELECT f FROM FinContasPagar f WHERE f.pkContasPagar = :pkContasPagar"),
    @NamedQuery(name = "FinContasPagar.findByValorMulta", query = "SELECT f FROM FinContasPagar f WHERE f.valorMulta = :valorMulta"),
    @NamedQuery(name = "FinContasPagar.findByValorImposto", query = "SELECT f FROM FinContasPagar f WHERE f.valorImposto = :valorImposto"),
    @NamedQuery(name = "FinContasPagar.findByDataPagamento", query = "SELECT f FROM FinContasPagar f WHERE f.dataPagamento = :dataPagamento")
})
public class FinContasPagar implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Size(max = 2147483647)
    @Column(length = 2147483647)
    private String descricao;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(precision = 18, scale = 2)
    private BigDecimal valor;
    @Column(name = "data_emissao")
    @Temporal(TemporalType.DATE)
    private Date dataEmissao;
    @Column(name = "data_vencimento")
    @Temporal(TemporalType.DATE)
    private Date dataVencimento;
    @Size(max = 2147483647)
    @Column(length = 2147483647)
    private String observacao;
    @Column(name = "data_cadastro")
    @Temporal(TemporalType.DATE)
    private Date dataCadastro;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_contas_pagar", nullable = false)
    private Integer pkContasPagar;
    @Column(name = "valor_multa", precision = 18, scale = 2)
    private BigDecimal valorMulta;
    @Column(name = "valor_imposto", precision = 18, scale = 2)
    private BigDecimal valorImposto;
    @Column(name = "data_pagamento")
    @Temporal(TemporalType.DATE)
    private Date dataPagamento;
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
    @JoinColumn(name = "fk_fornecedor", referencedColumnName = "pk_entidade")
    @ManyToOne
    private GrlEntidade fkFornecedor;
    @JoinColumn(name = "fk_utilizador", referencedColumnName = "pk_seg_conta")
    @ManyToOne
    private SegConta fkUtilizador;

    public FinContasPagar()
    {
    }

    public FinContasPagar(Integer pkContasPagar)
    {
        this.pkContasPagar = pkContasPagar;
    }

    public String getDescricao()
    {
        return descricao;
    }

    public void setDescricao(String descricao)
    {
        this.descricao = descricao;
    }

    public BigDecimal getValor()
    {
        return valor;
    }

    public void setValor(BigDecimal valor)
    {
        this.valor = valor;
    }

    public Date getDataEmissao()
    {
        return dataEmissao;
    }

    public void setDataEmissao(Date dataEmissao)
    {
        this.dataEmissao = dataEmissao;
    }

    public Date getDataVencimento()
    {
        return dataVencimento;
    }

    public void setDataVencimento(Date dataVencimento)
    {
        this.dataVencimento = dataVencimento;
    }

    public String getObservacao()
    {
        return observacao;
    }

    public void setObservacao(String observacao)
    {
        this.observacao = observacao;
    }

    public Date getDataCadastro()
    {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro)
    {
        this.dataCadastro = dataCadastro;
    }

    public Integer getPkContasPagar()
    {
        return pkContasPagar;
    }

    public void setPkContasPagar(Integer pkContasPagar)
    {
        this.pkContasPagar = pkContasPagar;
    }

    public BigDecimal getValorMulta()
    {
        return valorMulta;
    }

    public void setValorMulta(BigDecimal valorMulta)
    {
        this.valorMulta = valorMulta;
    }

    public BigDecimal getValorImposto()
    {
        return valorImposto;
    }

    public void setValorImposto(BigDecimal valorImposto)
    {
        this.valorImposto = valorImposto;
    }

    public Date getDataPagamento()
    {
        return dataPagamento;
    }

    public void setDataPagamento(Date dataPagamento)
    {
        this.dataPagamento = dataPagamento;
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

    public GrlEntidade getFkFornecedor()
    {
        return fkFornecedor;
    }

    public void setFkFornecedor(GrlEntidade fkFornecedor)
    {
        this.fkFornecedor = fkFornecedor;
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
        hash += (pkContasPagar != null ? pkContasPagar.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FinContasPagar))
        {
            return false;
        }
        FinContasPagar other = (FinContasPagar) object;
        if ((this.pkContasPagar == null && other.pkContasPagar != null) || (this.pkContasPagar != null && !this.pkContasPagar.equals(other.pkContasPagar)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.FinContasPagar[ pkContasPagar=" + pkContasPagar + " ]";
    }
    
}
