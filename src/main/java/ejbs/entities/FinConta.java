/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "fin_conta", catalog = "sig_ucan_db", schema = "public")
@NamedQueries(
{
    @NamedQuery(name = "FinConta.findAll", query = "SELECT f FROM FinConta f"),
    @NamedQuery(name = "FinConta.findByPkConta", query = "SELECT f FROM FinConta f WHERE f.pkConta = :pkConta"),
    @NamedQuery(name = "FinConta.findByNumeroConta", query = "SELECT f FROM FinConta f WHERE f.numeroConta = :numeroConta"),
    @NamedQuery(name = "FinConta.findBySaldoInicial", query = "SELECT f FROM FinConta f WHERE f.saldoInicial = :saldoInicial"),
    @NamedQuery(name = "FinConta.findByObservacao", query = "SELECT f FROM FinConta f WHERE f.observacao = :observacao"),
    @NamedQuery(name = "FinConta.findByEstado", query = "SELECT f FROM FinConta f WHERE f.estado = :estado"),
    @NamedQuery(name = "FinConta.findByDataCadastro", query = "SELECT f FROM FinConta f WHERE f.dataCadastro = :dataCadastro"),
    @NamedQuery(name = "FinConta.findByNome", query = "SELECT f FROM FinConta f WHERE f.nome = :nome"),
    @NamedQuery(name = "FinConta.findByLimite", query = "SELECT f FROM FinConta f WHERE f.limite = :limite"),
    @NamedQuery(name = "FinConta.findByDataInicio", query = "SELECT f FROM FinConta f WHERE f.dataInicio = :dataInicio"),
    @NamedQuery(name = "FinConta.findByDataFim", query = "SELECT f FROM FinConta f WHERE f.dataFim = :dataFim"),
    @NamedQuery(name = "FinConta.findBySaldo", query = "SELECT f FROM FinConta f WHERE f.saldo = :saldo"),
    @NamedQuery(name = "FinConta.findByIbanConta", query = "SELECT f FROM FinConta f WHERE f.ibanConta = :ibanConta")
})
public class FinConta implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "pk_conta", nullable = false)
    private Integer pkConta;
    @Size(max = 2147483647)
    @Column(name = "numero_conta", length = 2147483647)
    private String numeroConta;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "saldo_inicial", precision = 18, scale = 2)
    private BigDecimal saldoInicial;
    @Size(max = 2147483647)
    @Column(length = 2147483647)
    private String observacao;
    private Boolean estado;
    @Column(name = "data_cadastro")
    @Temporal(TemporalType.DATE)
    private Date dataCadastro;
    @Size(max = 2147483647)
    @Column(length = 2147483647)
    private String nome;
    @Column(precision = 18, scale = 2)
    private BigDecimal limite;
    @Column(name = "data_inicio")
    @Temporal(TemporalType.DATE)
    private Date dataInicio;
    @Column(name = "data_fim")
    @Temporal(TemporalType.DATE)
    private Date dataFim;
    @Column(precision = 18, scale = 2)
    private BigDecimal saldo;
    @Size(max = 2147483647)
    @Column(name = "iban_conta", length = 2147483647)
    private String ibanConta;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkContaDestino")
    private List<FinContaTransferencia> finContaTransferenciaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkContaOrigem")
    private List<FinContaTransferencia> finContaTransferenciaList1;
    @OneToMany(mappedBy = "fkConta")
    private List<FinContasPagar> finContasPagarList;
    @JoinColumn(name = "fk_moeda", referencedColumnName = "pk_moeda")
    @ManyToOne
    private FinMoeda fkMoeda;
    @JoinColumn(name = "fk_tipo_cartao", referencedColumnName = "pk_tipo_cartao")
    @ManyToOne
    private FinTipoCartao fkTipoCartao;
    @JoinColumn(name = "fk_tipo_conta", referencedColumnName = "pk_tipo_conta")
    @ManyToOne
    private FinTipoConta fkTipoConta;
    @JoinColumn(name = "fk_banco", referencedColumnName = "pk_seg_conta")
    @ManyToOne
    private SegConta fkBanco;
    @JoinColumn(name = "fk_utilizador", referencedColumnName = "pk_seg_conta")
    @ManyToOne
    private SegConta fkUtilizador;
    @OneToMany(mappedBy = "fkConta")
    private List<FinContasReceber> finContasReceberList;

    public FinConta()
    {
    }

    public FinConta(Integer pkConta)
    {
        this.pkConta = pkConta;
    }

    public Integer getPkConta()
    {
        return pkConta;
    }

    public void setPkConta(Integer pkConta)
    {
        this.pkConta = pkConta;
    }

    public String getNumeroConta()
    {
        return numeroConta;
    }

    public void setNumeroConta(String numeroConta)
    {
        this.numeroConta = numeroConta;
    }

    public BigDecimal getSaldoInicial()
    {
        return saldoInicial;
    }

    public void setSaldoInicial(BigDecimal saldoInicial)
    {
        this.saldoInicial = saldoInicial;
    }

    public String getObservacao()
    {
        return observacao;
    }

    public void setObservacao(String observacao)
    {
        this.observacao = observacao;
    }

    public Boolean getEstado()
    {
        return estado;
    }

    public void setEstado(Boolean estado)
    {
        this.estado = estado;
    }

    public Date getDataCadastro()
    {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro)
    {
        this.dataCadastro = dataCadastro;
    }

    public String getNome()
    {
        return nome;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    public BigDecimal getLimite()
    {
        return limite;
    }

    public void setLimite(BigDecimal limite)
    {
        this.limite = limite;
    }

    public Date getDataInicio()
    {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio)
    {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim()
    {
        return dataFim;
    }

    public void setDataFim(Date dataFim)
    {
        this.dataFim = dataFim;
    }

    public BigDecimal getSaldo()
    {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo)
    {
        this.saldo = saldo;
    }

    public String getIbanConta()
    {
        return ibanConta;
    }

    public void setIbanConta(String ibanConta)
    {
        this.ibanConta = ibanConta;
    }

    public List<FinContaTransferencia> getFinContaTransferenciaList()
    {
        return finContaTransferenciaList;
    }

    public void setFinContaTransferenciaList(List<FinContaTransferencia> finContaTransferenciaList)
    {
        this.finContaTransferenciaList = finContaTransferenciaList;
    }

    public List<FinContaTransferencia> getFinContaTransferenciaList1()
    {
        return finContaTransferenciaList1;
    }

    public void setFinContaTransferenciaList1(List<FinContaTransferencia> finContaTransferenciaList1)
    {
        this.finContaTransferenciaList1 = finContaTransferenciaList1;
    }

    public List<FinContasPagar> getFinContasPagarList()
    {
        return finContasPagarList;
    }

    public void setFinContasPagarList(List<FinContasPagar> finContasPagarList)
    {
        this.finContasPagarList = finContasPagarList;
    }

    public FinMoeda getFkMoeda()
    {
        return fkMoeda;
    }

    public void setFkMoeda(FinMoeda fkMoeda)
    {
        this.fkMoeda = fkMoeda;
    }

    public FinTipoCartao getFkTipoCartao()
    {
        return fkTipoCartao;
    }

    public void setFkTipoCartao(FinTipoCartao fkTipoCartao)
    {
        this.fkTipoCartao = fkTipoCartao;
    }

    public FinTipoConta getFkTipoConta()
    {
        return fkTipoConta;
    }

    public void setFkTipoConta(FinTipoConta fkTipoConta)
    {
        this.fkTipoConta = fkTipoConta;
    }

    public SegConta getFkBanco()
    {
        return fkBanco;
    }

    public void setFkBanco(SegConta fkBanco)
    {
        this.fkBanco = fkBanco;
    }

    public SegConta getFkUtilizador()
    {
        return fkUtilizador;
    }

    public void setFkUtilizador(SegConta fkUtilizador)
    {
        this.fkUtilizador = fkUtilizador;
    }

    public List<FinContasReceber> getFinContasReceberList()
    {
        return finContasReceberList;
    }

    public void setFinContasReceberList(List<FinContasReceber> finContasReceberList)
    {
        this.finContasReceberList = finContasReceberList;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (pkConta != null ? pkConta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FinConta))
        {
            return false;
        }
        FinConta other = (FinConta) object;
        if ((this.pkConta == null && other.pkConta != null) || (this.pkConta != null && !this.pkConta.equals(other.pkConta)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.FinConta[ pkConta=" + pkConta + " ]";
    }
    
}
