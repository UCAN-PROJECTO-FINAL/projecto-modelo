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
@Table(name = "ct_balancet", catalog = "sig_ucan_db", schema = "public")
@NamedQueries(
{
    @NamedQuery(name = "CtBalancet.findAll", query = "SELECT c FROM CtBalancet c"),
    @NamedQuery(name = "CtBalancet.findByPkBalancet", query = "SELECT c FROM CtBalancet c WHERE c.pkBalancet = :pkBalancet"),
    @NamedQuery(name = "CtBalancet.findByDebitoBalancet", query = "SELECT c FROM CtBalancet c WHERE c.debitoBalancet = :debitoBalancet"),
    @NamedQuery(name = "CtBalancet.findByCreditoBalancet", query = "SELECT c FROM CtBalancet c WHERE c.creditoBalancet = :creditoBalancet"),
    @NamedQuery(name = "CtBalancet.findByDataBalancet", query = "SELECT c FROM CtBalancet c WHERE c.dataBalancet = :dataBalancet"),
    @NamedQuery(name = "CtBalancet.findByHistoricoBalancet", query = "SELECT c FROM CtBalancet c WHERE c.historicoBalancet = :historicoBalancet"),
    @NamedQuery(name = "CtBalancet.findByStateBalancet", query = "SELECT c FROM CtBalancet c WHERE c.stateBalancet = :stateBalancet"),
    @NamedQuery(name = "CtBalancet.findByMonth", query = "SELECT c FROM CtBalancet c WHERE c.month = :month")
})
public class CtBalancet implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_balancet", nullable = false)
    private Integer pkBalancet;
    @Basic(optional = false)
    @NotNull
    @Column(name = "debito_balancet", nullable = false)
    private double debitoBalancet;
    @Basic(optional = false)
    @NotNull
    @Column(name = "credito_balancet", nullable = false)
    private double creditoBalancet;
    @Basic(optional = false)
    @NotNull
    @Column(name = "data_balancet", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataBalancet;
    @Size(max = 2147483647)
    @Column(name = "historico_balancet", length = 2147483647)
    private String historicoBalancet;
    @Column(name = "state_balancet")
    private Boolean stateBalancet;
    private Integer month;
    @JoinColumn(name = "fk_account", referencedColumnName = "pk_account")
    @ManyToOne
    private CtAccount fkAccount;
    @JoinColumn(name = "fk_lancamento", referencedColumnName = "pk_lancamento")
    @ManyToOne
    private CtLancamento fkLancamento;

    public CtBalancet()
    {
    }

    public CtBalancet(Integer pkBalancet)
    {
        this.pkBalancet = pkBalancet;
    }

    public CtBalancet(Integer pkBalancet, double debitoBalancet, double creditoBalancet, Date dataBalancet)
    {
        this.pkBalancet = pkBalancet;
        this.debitoBalancet = debitoBalancet;
        this.creditoBalancet = creditoBalancet;
        this.dataBalancet = dataBalancet;
    }

    public Integer getPkBalancet()
    {
        return pkBalancet;
    }

    public void setPkBalancet(Integer pkBalancet)
    {
        this.pkBalancet = pkBalancet;
    }

    public double getDebitoBalancet()
    {
        return debitoBalancet;
    }

    public void setDebitoBalancet(double debitoBalancet)
    {
        this.debitoBalancet = debitoBalancet;
    }

    public double getCreditoBalancet()
    {
        return creditoBalancet;
    }

    public void setCreditoBalancet(double creditoBalancet)
    {
        this.creditoBalancet = creditoBalancet;
    }

    public Date getDataBalancet()
    {
        return dataBalancet;
    }

    public void setDataBalancet(Date dataBalancet)
    {
        this.dataBalancet = dataBalancet;
    }

    public String getHistoricoBalancet()
    {
        return historicoBalancet;
    }

    public void setHistoricoBalancet(String historicoBalancet)
    {
        this.historicoBalancet = historicoBalancet;
    }

    public Boolean getStateBalancet()
    {
        return stateBalancet;
    }

    public void setStateBalancet(Boolean stateBalancet)
    {
        this.stateBalancet = stateBalancet;
    }

    public Integer getMonth()
    {
        return month;
    }

    public void setMonth(Integer month)
    {
        this.month = month;
    }

    public CtAccount getFkAccount()
    {
        return fkAccount;
    }

    public void setFkAccount(CtAccount fkAccount)
    {
        this.fkAccount = fkAccount;
    }

    public CtLancamento getFkLancamento()
    {
        return fkLancamento;
    }

    public void setFkLancamento(CtLancamento fkLancamento)
    {
        this.fkLancamento = fkLancamento;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (pkBalancet != null ? pkBalancet.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CtBalancet))
        {
            return false;
        }
        CtBalancet other = (CtBalancet) object;
        if ((this.pkBalancet == null && other.pkBalancet != null) || (this.pkBalancet != null && !this.pkBalancet.equals(other.pkBalancet)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.CtBalancet[ pkBalancet=" + pkBalancet + " ]";
    }
    
}
