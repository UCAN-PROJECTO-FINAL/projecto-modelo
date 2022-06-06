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
@Table(name = "ct_contra_partidai", catalog = "sig_ucan_db", schema = "public")
@NamedQueries(
{
    @NamedQuery(name = "CtContraPartidai.findAll", query = "SELECT c FROM CtContraPartidai c"),
    @NamedQuery(name = "CtContraPartidai.findByPkContraPartidai", query = "SELECT c FROM CtContraPartidai c WHERE c.pkContraPartidai = :pkContraPartidai"),
    @NamedQuery(name = "CtContraPartidai.findByOrigemCpi", query = "SELECT c FROM CtContraPartidai c WHERE c.origemCpi = :origemCpi"),
    @NamedQuery(name = "CtContraPartidai.findByDestinoCpi", query = "SELECT c FROM CtContraPartidai c WHERE c.destinoCpi = :destinoCpi"),
    @NamedQuery(name = "CtContraPartidai.findByRegistroCpi", query = "SELECT c FROM CtContraPartidai c WHERE c.registroCpi = :registroCpi"),
    @NamedQuery(name = "CtContraPartidai.findByMotivoCpi", query = "SELECT c FROM CtContraPartidai c WHERE c.motivoCpi = :motivoCpi"),
    @NamedQuery(name = "CtContraPartidai.findByDateMovimento", query = "SELECT c FROM CtContraPartidai c WHERE c.dateMovimento = :dateMovimento"),
    @NamedQuery(name = "CtContraPartidai.findByValor", query = "SELECT c FROM CtContraPartidai c WHERE c.valor = :valor"),
    @NamedQuery(name = "CtContraPartidai.findByState", query = "SELECT c FROM CtContraPartidai c WHERE c.state = :state")
})
public class CtContraPartidai implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_contra_partidai", nullable = false)
    private Integer pkContraPartidai;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "origem_cpi", nullable = false, length = 2147483647)
    private String origemCpi;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "destino_cpi", nullable = false, length = 2147483647)
    private String destinoCpi;
    @Basic(optional = false)
    @NotNull
    @Column(name = "registro_cpi", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date registroCpi;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "motivo_cpi", nullable = false, length = 2147483647)
    private String motivoCpi;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date_movimento", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateMovimento;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private double valor;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private boolean state;
    @JoinColumn(name = "fk_ano_economico", referencedColumnName = "pk_ano_economico")
    @ManyToOne
    private CtAnoEconomico fkAnoEconomico;
    @JoinColumn(name = "fk_utilizador", referencedColumnName = "pk_seg_conta")
    @ManyToOne
    private SegConta fkUtilizador;

    public CtContraPartidai()
    {
    }

    public CtContraPartidai(Integer pkContraPartidai)
    {
        this.pkContraPartidai = pkContraPartidai;
    }

    public CtContraPartidai(Integer pkContraPartidai, String origemCpi, String destinoCpi, Date registroCpi, String motivoCpi, Date dateMovimento, double valor, boolean state)
    {
        this.pkContraPartidai = pkContraPartidai;
        this.origemCpi = origemCpi;
        this.destinoCpi = destinoCpi;
        this.registroCpi = registroCpi;
        this.motivoCpi = motivoCpi;
        this.dateMovimento = dateMovimento;
        this.valor = valor;
        this.state = state;
    }

    public Integer getPkContraPartidai()
    {
        return pkContraPartidai;
    }

    public void setPkContraPartidai(Integer pkContraPartidai)
    {
        this.pkContraPartidai = pkContraPartidai;
    }

    public String getOrigemCpi()
    {
        return origemCpi;
    }

    public void setOrigemCpi(String origemCpi)
    {
        this.origemCpi = origemCpi;
    }

    public String getDestinoCpi()
    {
        return destinoCpi;
    }

    public void setDestinoCpi(String destinoCpi)
    {
        this.destinoCpi = destinoCpi;
    }

    public Date getRegistroCpi()
    {
        return registroCpi;
    }

    public void setRegistroCpi(Date registroCpi)
    {
        this.registroCpi = registroCpi;
    }

    public String getMotivoCpi()
    {
        return motivoCpi;
    }

    public void setMotivoCpi(String motivoCpi)
    {
        this.motivoCpi = motivoCpi;
    }

    public Date getDateMovimento()
    {
        return dateMovimento;
    }

    public void setDateMovimento(Date dateMovimento)
    {
        this.dateMovimento = dateMovimento;
    }

    public double getValor()
    {
        return valor;
    }

    public void setValor(double valor)
    {
        this.valor = valor;
    }

    public boolean getState()
    {
        return state;
    }

    public void setState(boolean state)
    {
        this.state = state;
    }

    public CtAnoEconomico getFkAnoEconomico()
    {
        return fkAnoEconomico;
    }

    public void setFkAnoEconomico(CtAnoEconomico fkAnoEconomico)
    {
        this.fkAnoEconomico = fkAnoEconomico;
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
        hash += (pkContraPartidai != null ? pkContraPartidai.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CtContraPartidai))
        {
            return false;
        }
        CtContraPartidai other = (CtContraPartidai) object;
        if ((this.pkContraPartidai == null && other.pkContraPartidai != null) || (this.pkContraPartidai != null && !this.pkContraPartidai.equals(other.pkContraPartidai)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.CtContraPartidai[ pkContraPartidai=" + pkContraPartidai + " ]";
    }
    
}
