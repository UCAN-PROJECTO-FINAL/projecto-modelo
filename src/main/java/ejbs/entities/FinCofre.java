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

/**
 *
 * @author smakambo
 */
@Entity
@Table(name = "fin_cofre", catalog = "sig_ucan_db", schema = "public")
@NamedQueries(
{
    @NamedQuery(name = "FinCofre.findAll", query = "SELECT f FROM FinCofre f"),
    @NamedQuery(name = "FinCofre.findByPkCofre", query = "SELECT f FROM FinCofre f WHERE f.pkCofre = :pkCofre"),
    @NamedQuery(name = "FinCofre.findBySaldoInicial", query = "SELECT f FROM FinCofre f WHERE f.saldoInicial = :saldoInicial"),
    @NamedQuery(name = "FinCofre.findBySaldoFinal", query = "SELECT f FROM FinCofre f WHERE f.saldoFinal = :saldoFinal"),
    @NamedQuery(name = "FinCofre.findByDebitoCofre", query = "SELECT f FROM FinCofre f WHERE f.debitoCofre = :debitoCofre"),
    @NamedQuery(name = "FinCofre.findByCreditoCofre", query = "SELECT f FROM FinCofre f WHERE f.creditoCofre = :creditoCofre"),
    @NamedQuery(name = "FinCofre.findByDataRegisto", query = "SELECT f FROM FinCofre f WHERE f.dataRegisto = :dataRegisto"),
    @NamedQuery(name = "FinCofre.findByDataMovimento", query = "SELECT f FROM FinCofre f WHERE f.dataMovimento = :dataMovimento"),
    @NamedQuery(name = "FinCofre.findByFkCofreMotivoEntradaSaida", query = "SELECT f FROM FinCofre f WHERE f.fkCofreMotivoEntradaSaida = :fkCofreMotivoEntradaSaida")
})
public class FinCofre implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_cofre", nullable = false)
    private Integer pkCofre;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "saldo_inicial", precision = 17, scale = 17)
    private Double saldoInicial;
    @Column(name = "saldo_final", precision = 17, scale = 17)
    private Double saldoFinal;
    @Column(name = "debito_cofre", precision = 17, scale = 17)
    private Double debitoCofre;
    @Column(name = "credito_cofre", precision = 17, scale = 17)
    private Double creditoCofre;
    @Column(name = "data_registo")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataRegisto;
    @Column(name = "data_movimento")
    @Temporal(TemporalType.DATE)
    private Date dataMovimento;
    @Column(name = "fk_cofre_motivo_entrada_saida")
    private Integer fkCofreMotivoEntradaSaida;
    @JoinColumn(name = "fk_moeda", referencedColumnName = "pk_moeda")
    @ManyToOne
    private FinMoeda fkMoeda;
    @JoinColumn(name = "fk_utilizador", referencedColumnName = "pk_seg_conta")
    @ManyToOne
    private SegConta fkUtilizador;

    public FinCofre()
    {
    }

    public FinCofre(Integer pkCofre)
    {
        this.pkCofre = pkCofre;
    }

    public Integer getPkCofre()
    {
        return pkCofre;
    }

    public void setPkCofre(Integer pkCofre)
    {
        this.pkCofre = pkCofre;
    }

    public Double getSaldoInicial()
    {
        return saldoInicial;
    }

    public void setSaldoInicial(Double saldoInicial)
    {
        this.saldoInicial = saldoInicial;
    }

    public Double getSaldoFinal()
    {
        return saldoFinal;
    }

    public void setSaldoFinal(Double saldoFinal)
    {
        this.saldoFinal = saldoFinal;
    }

    public Double getDebitoCofre()
    {
        return debitoCofre;
    }

    public void setDebitoCofre(Double debitoCofre)
    {
        this.debitoCofre = debitoCofre;
    }

    public Double getCreditoCofre()
    {
        return creditoCofre;
    }

    public void setCreditoCofre(Double creditoCofre)
    {
        this.creditoCofre = creditoCofre;
    }

    public Date getDataRegisto()
    {
        return dataRegisto;
    }

    public void setDataRegisto(Date dataRegisto)
    {
        this.dataRegisto = dataRegisto;
    }

    public Date getDataMovimento()
    {
        return dataMovimento;
    }

    public void setDataMovimento(Date dataMovimento)
    {
        this.dataMovimento = dataMovimento;
    }

    public Integer getFkCofreMotivoEntradaSaida()
    {
        return fkCofreMotivoEntradaSaida;
    }

    public void setFkCofreMotivoEntradaSaida(Integer fkCofreMotivoEntradaSaida)
    {
        this.fkCofreMotivoEntradaSaida = fkCofreMotivoEntradaSaida;
    }

    public FinMoeda getFkMoeda()
    {
        return fkMoeda;
    }

    public void setFkMoeda(FinMoeda fkMoeda)
    {
        this.fkMoeda = fkMoeda;
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
        hash += (pkCofre != null ? pkCofre.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FinCofre))
        {
            return false;
        }
        FinCofre other = (FinCofre) object;
        if ((this.pkCofre == null && other.pkCofre != null) || (this.pkCofre != null && !this.pkCofre.equals(other.pkCofre)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.FinCofre[ pkCofre=" + pkCofre + " ]";
    }
    
}
