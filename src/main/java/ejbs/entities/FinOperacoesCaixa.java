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
@Table(name = "fin_operacoes_caixa", catalog = "sig_ucan_db", schema = "public")
@NamedQueries(
{
    @NamedQuery(name = "FinOperacoesCaixa.findAll", query = "SELECT f FROM FinOperacoesCaixa f"),
    @NamedQuery(name = "FinOperacoesCaixa.findByPkOperacoesCaixa", query = "SELECT f FROM FinOperacoesCaixa f WHERE f.pkOperacoesCaixa = :pkOperacoesCaixa"),
    @NamedQuery(name = "FinOperacoesCaixa.findByCredito", query = "SELECT f FROM FinOperacoesCaixa f WHERE f.credito = :credito"),
    @NamedQuery(name = "FinOperacoesCaixa.findByDebito", query = "SELECT f FROM FinOperacoesCaixa f WHERE f.debito = :debito"),
    @NamedQuery(name = "FinOperacoesCaixa.findByDescricaoCaixa", query = "SELECT f FROM FinOperacoesCaixa f WHERE f.descricaoCaixa = :descricaoCaixa"),
    @NamedQuery(name = "FinOperacoesCaixa.findBySaldoFinal", query = "SELECT f FROM FinOperacoesCaixa f WHERE f.saldoFinal = :saldoFinal"),
    @NamedQuery(name = "FinOperacoesCaixa.findBySaldoInicial", query = "SELECT f FROM FinOperacoesCaixa f WHERE f.saldoInicial = :saldoInicial"),
    @NamedQuery(name = "FinOperacoesCaixa.findBySaldoCaixa", query = "SELECT f FROM FinOperacoesCaixa f WHERE f.saldoCaixa = :saldoCaixa"),
    @NamedQuery(name = "FinOperacoesCaixa.findByDataMovimento", query = "SELECT f FROM FinOperacoesCaixa f WHERE f.dataMovimento = :dataMovimento"),
    @NamedQuery(name = "FinOperacoesCaixa.findByObsOperacoesCaixa", query = "SELECT f FROM FinOperacoesCaixa f WHERE f.obsOperacoesCaixa = :obsOperacoesCaixa"),
    @NamedQuery(name = "FinOperacoesCaixa.findByOpenClose", query = "SELECT f FROM FinOperacoesCaixa f WHERE f.openClose = :openClose"),
    @NamedQuery(name = "FinOperacoesCaixa.findByDataRegistoAbertura", query = "SELECT f FROM FinOperacoesCaixa f WHERE f.dataRegistoAbertura = :dataRegistoAbertura"),
    @NamedQuery(name = "FinOperacoesCaixa.findByDataRegistoFecho", query = "SELECT f FROM FinOperacoesCaixa f WHERE f.dataRegistoFecho = :dataRegistoFecho"),
    @NamedQuery(name = "FinOperacoesCaixa.findByQuebraDeCaixa", query = "SELECT f FROM FinOperacoesCaixa f WHERE f.quebraDeCaixa = :quebraDeCaixa")
})
public class FinOperacoesCaixa implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_operacoes_caixa", nullable = false)
    private Integer pkOperacoesCaixa;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(precision = 17, scale = 17)
    private Double credito;
    @Column(precision = 17, scale = 17)
    private Double debito;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "descricao_caixa", nullable = false, length = 2147483647)
    private String descricaoCaixa;
    @Column(name = "saldo_final", precision = 17, scale = 17)
    private Double saldoFinal;
    @Column(name = "saldo_inicial", precision = 17, scale = 17)
    private Double saldoInicial;
    @Column(name = "saldo_caixa", precision = 17, scale = 17)
    private Double saldoCaixa;
    @Column(name = "data_movimento")
    @Temporal(TemporalType.DATE)
    private Date dataMovimento;
    @Size(max = 2147483647)
    @Column(name = "obs_operacoes_caixa", length = 2147483647)
    private String obsOperacoesCaixa;
    @Column(name = "open_close")
    private Boolean openClose;
    @Column(name = "data_registo_abertura")
    @Temporal(TemporalType.DATE)
    private Date dataRegistoAbertura;
    @Column(name = "data_registo_fecho")
    @Temporal(TemporalType.DATE)
    private Date dataRegistoFecho;
    @Column(name = "quebra_de_caixa", precision = 17, scale = 17)
    private Double quebraDeCaixa;
    @JoinColumn(name = "fk_caixa", referencedColumnName = "pk_caixa")
    @ManyToOne
    private FinCaixa fkCaixa;
    @JoinColumn(name = "fk_moeda", referencedColumnName = "pk_moeda")
    @ManyToOne
    private FinMoeda fkMoeda;
    @JoinColumn(name = "fk_pagamento", referencedColumnName = "pk_pagamento")
    @ManyToOne
    private FinPagamento fkPagamento;
    @JoinColumn(name = "fk_utilizador_close", referencedColumnName = "pk_seg_conta")
    @ManyToOne
    private SegConta fkUtilizadorClose;
    @JoinColumn(name = "fk_utilizador", referencedColumnName = "pk_seg_conta")
    @ManyToOne
    private SegConta fkUtilizador;
    @JoinColumn(name = "fk_utilizador_open", referencedColumnName = "pk_seg_conta")
    @ManyToOne
    private SegConta fkUtilizadorOpen;

    public FinOperacoesCaixa()
    {
    }

    public FinOperacoesCaixa(Integer pkOperacoesCaixa)
    {
        this.pkOperacoesCaixa = pkOperacoesCaixa;
    }

    public FinOperacoesCaixa(Integer pkOperacoesCaixa, String descricaoCaixa)
    {
        this.pkOperacoesCaixa = pkOperacoesCaixa;
        this.descricaoCaixa = descricaoCaixa;
    }

    public Integer getPkOperacoesCaixa()
    {
        return pkOperacoesCaixa;
    }

    public void setPkOperacoesCaixa(Integer pkOperacoesCaixa)
    {
        this.pkOperacoesCaixa = pkOperacoesCaixa;
    }

    public Double getCredito()
    {
        return credito;
    }

    public void setCredito(Double credito)
    {
        this.credito = credito;
    }

    public Double getDebito()
    {
        return debito;
    }

    public void setDebito(Double debito)
    {
        this.debito = debito;
    }

    public String getDescricaoCaixa()
    {
        return descricaoCaixa;
    }

    public void setDescricaoCaixa(String descricaoCaixa)
    {
        this.descricaoCaixa = descricaoCaixa;
    }

    public Double getSaldoFinal()
    {
        return saldoFinal;
    }

    public void setSaldoFinal(Double saldoFinal)
    {
        this.saldoFinal = saldoFinal;
    }

    public Double getSaldoInicial()
    {
        return saldoInicial;
    }

    public void setSaldoInicial(Double saldoInicial)
    {
        this.saldoInicial = saldoInicial;
    }

    public Double getSaldoCaixa()
    {
        return saldoCaixa;
    }

    public void setSaldoCaixa(Double saldoCaixa)
    {
        this.saldoCaixa = saldoCaixa;
    }

    public Date getDataMovimento()
    {
        return dataMovimento;
    }

    public void setDataMovimento(Date dataMovimento)
    {
        this.dataMovimento = dataMovimento;
    }

    public String getObsOperacoesCaixa()
    {
        return obsOperacoesCaixa;
    }

    public void setObsOperacoesCaixa(String obsOperacoesCaixa)
    {
        this.obsOperacoesCaixa = obsOperacoesCaixa;
    }

    public Boolean getOpenClose()
    {
        return openClose;
    }

    public void setOpenClose(Boolean openClose)
    {
        this.openClose = openClose;
    }

    public Date getDataRegistoAbertura()
    {
        return dataRegistoAbertura;
    }

    public void setDataRegistoAbertura(Date dataRegistoAbertura)
    {
        this.dataRegistoAbertura = dataRegistoAbertura;
    }

    public Date getDataRegistoFecho()
    {
        return dataRegistoFecho;
    }

    public void setDataRegistoFecho(Date dataRegistoFecho)
    {
        this.dataRegistoFecho = dataRegistoFecho;
    }

    public Double getQuebraDeCaixa()
    {
        return quebraDeCaixa;
    }

    public void setQuebraDeCaixa(Double quebraDeCaixa)
    {
        this.quebraDeCaixa = quebraDeCaixa;
    }

    public FinCaixa getFkCaixa()
    {
        return fkCaixa;
    }

    public void setFkCaixa(FinCaixa fkCaixa)
    {
        this.fkCaixa = fkCaixa;
    }

    public FinMoeda getFkMoeda()
    {
        return fkMoeda;
    }

    public void setFkMoeda(FinMoeda fkMoeda)
    {
        this.fkMoeda = fkMoeda;
    }

    public FinPagamento getFkPagamento()
    {
        return fkPagamento;
    }

    public void setFkPagamento(FinPagamento fkPagamento)
    {
        this.fkPagamento = fkPagamento;
    }

    public SegConta getFkUtilizadorClose()
    {
        return fkUtilizadorClose;
    }

    public void setFkUtilizadorClose(SegConta fkUtilizadorClose)
    {
        this.fkUtilizadorClose = fkUtilizadorClose;
    }

    public SegConta getFkUtilizador()
    {
        return fkUtilizador;
    }

    public void setFkUtilizador(SegConta fkUtilizador)
    {
        this.fkUtilizador = fkUtilizador;
    }

    public SegConta getFkUtilizadorOpen()
    {
        return fkUtilizadorOpen;
    }

    public void setFkUtilizadorOpen(SegConta fkUtilizadorOpen)
    {
        this.fkUtilizadorOpen = fkUtilizadorOpen;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (pkOperacoesCaixa != null ? pkOperacoesCaixa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FinOperacoesCaixa))
        {
            return false;
        }
        FinOperacoesCaixa other = (FinOperacoesCaixa) object;
        if ((this.pkOperacoesCaixa == null && other.pkOperacoesCaixa != null) || (this.pkOperacoesCaixa != null && !this.pkOperacoesCaixa.equals(other.pkOperacoesCaixa)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.FinOperacoesCaixa[ pkOperacoesCaixa=" + pkOperacoesCaixa + " ]";
    }
    
}
