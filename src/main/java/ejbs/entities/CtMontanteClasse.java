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

/**
 *
 * @author smakambo
 */
@Entity
@Table(name = "ct_montante_classe", catalog = "sig_ucan_db", schema = "public")
@NamedQueries(
{
    @NamedQuery(name = "CtMontanteClasse.findAll", query = "SELECT c FROM CtMontanteClasse c"),
    @NamedQuery(name = "CtMontanteClasse.findByPkMontanteClasse", query = "SELECT c FROM CtMontanteClasse c WHERE c.pkMontanteClasse = :pkMontanteClasse"),
    @NamedQuery(name = "CtMontanteClasse.findByValorAnualClasse", query = "SELECT c FROM CtMontanteClasse c WHERE c.valorAnualClasse = :valorAnualClasse"),
    @NamedQuery(name = "CtMontanteClasse.findByDataRegistroMontanteClasse", query = "SELECT c FROM CtMontanteClasse c WHERE c.dataRegistroMontanteClasse = :dataRegistroMontanteClasse"),
    @NamedQuery(name = "CtMontanteClasse.findByStateMontanteClasse", query = "SELECT c FROM CtMontanteClasse c WHERE c.stateMontanteClasse = :stateMontanteClasse"),
    @NamedQuery(name = "CtMontanteClasse.findByValorDisponivel", query = "SELECT c FROM CtMontanteClasse c WHERE c.valorDisponivel = :valorDisponivel")
})
public class CtMontanteClasse implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_montante_classe", nullable = false)
    private Integer pkMontanteClasse;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor_anual_classe", nullable = false)
    private double valorAnualClasse;
    @Basic(optional = false)
    @NotNull
    @Column(name = "data_registro_montante_classe", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataRegistroMontanteClasse;
    @Column(name = "state_montante_classe")
    private Boolean stateMontanteClasse;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valor_disponivel", precision = 17, scale = 17)
    private Double valorDisponivel;
    @JoinColumn(name = "fk_ano_economico", referencedColumnName = "pk_ano_economico")
    @ManyToOne
    private CtAnoEconomico fkAnoEconomico;
    @JoinColumn(name = "fk_class", referencedColumnName = "pk_class")
    @ManyToOne
    private CtClass fkClass;
    @JoinColumn(name = "fk_utilizador", referencedColumnName = "pk_seg_conta")
    @ManyToOne
    private SegConta fkUtilizador;

    public CtMontanteClasse()
    {
    }

    public CtMontanteClasse(Integer pkMontanteClasse)
    {
        this.pkMontanteClasse = pkMontanteClasse;
    }

    public CtMontanteClasse(Integer pkMontanteClasse, double valorAnualClasse, Date dataRegistroMontanteClasse)
    {
        this.pkMontanteClasse = pkMontanteClasse;
        this.valorAnualClasse = valorAnualClasse;
        this.dataRegistroMontanteClasse = dataRegistroMontanteClasse;
    }

    public Integer getPkMontanteClasse()
    {
        return pkMontanteClasse;
    }

    public void setPkMontanteClasse(Integer pkMontanteClasse)
    {
        this.pkMontanteClasse = pkMontanteClasse;
    }

    public double getValorAnualClasse()
    {
        return valorAnualClasse;
    }

    public void setValorAnualClasse(double valorAnualClasse)
    {
        this.valorAnualClasse = valorAnualClasse;
    }

    public Date getDataRegistroMontanteClasse()
    {
        return dataRegistroMontanteClasse;
    }

    public void setDataRegistroMontanteClasse(Date dataRegistroMontanteClasse)
    {
        this.dataRegistroMontanteClasse = dataRegistroMontanteClasse;
    }

    public Boolean getStateMontanteClasse()
    {
        return stateMontanteClasse;
    }

    public void setStateMontanteClasse(Boolean stateMontanteClasse)
    {
        this.stateMontanteClasse = stateMontanteClasse;
    }

    public Double getValorDisponivel()
    {
        return valorDisponivel;
    }

    public void setValorDisponivel(Double valorDisponivel)
    {
        this.valorDisponivel = valorDisponivel;
    }

    public CtAnoEconomico getFkAnoEconomico()
    {
        return fkAnoEconomico;
    }

    public void setFkAnoEconomico(CtAnoEconomico fkAnoEconomico)
    {
        this.fkAnoEconomico = fkAnoEconomico;
    }

    public CtClass getFkClass()
    {
        return fkClass;
    }

    public void setFkClass(CtClass fkClass)
    {
        this.fkClass = fkClass;
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
        hash += (pkMontanteClasse != null ? pkMontanteClasse.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CtMontanteClasse))
        {
            return false;
        }
        CtMontanteClasse other = (CtMontanteClasse) object;
        if ((this.pkMontanteClasse == null && other.pkMontanteClasse != null) || (this.pkMontanteClasse != null && !this.pkMontanteClasse.equals(other.pkMontanteClasse)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.CtMontanteClasse[ pkMontanteClasse=" + pkMontanteClasse + " ]";
    }
    
}
