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
@Table(name = "ct_montante_rubrica", catalog = "sig_ucan_db", schema = "public")
@NamedQueries(
{
    @NamedQuery(name = "CtMontanteRubrica.findAll", query = "SELECT c FROM CtMontanteRubrica c"),
    @NamedQuery(name = "CtMontanteRubrica.findByPkMontanteRubrica", query = "SELECT c FROM CtMontanteRubrica c WHERE c.pkMontanteRubrica = :pkMontanteRubrica"),
    @NamedQuery(name = "CtMontanteRubrica.findByValorAnualRubrica", query = "SELECT c FROM CtMontanteRubrica c WHERE c.valorAnualRubrica = :valorAnualRubrica"),
    @NamedQuery(name = "CtMontanteRubrica.findByRegistroMontanteRubrica", query = "SELECT c FROM CtMontanteRubrica c WHERE c.registroMontanteRubrica = :registroMontanteRubrica"),
    @NamedQuery(name = "CtMontanteRubrica.findByStateMontanteRubrica", query = "SELECT c FROM CtMontanteRubrica c WHERE c.stateMontanteRubrica = :stateMontanteRubrica"),
    @NamedQuery(name = "CtMontanteRubrica.findByValorDisponivel", query = "SELECT c FROM CtMontanteRubrica c WHERE c.valorDisponivel = :valorDisponivel")
})
public class CtMontanteRubrica implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_montante_rubrica", nullable = false)
    private Integer pkMontanteRubrica;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor_anual_rubrica", nullable = false)
    private double valorAnualRubrica;
    @Basic(optional = false)
    @NotNull
    @Column(name = "registro_montante_rubrica", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date registroMontanteRubrica;
    @Column(name = "state_montante_rubrica")
    private Boolean stateMontanteRubrica;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valor_disponivel", precision = 17, scale = 17)
    private Double valorDisponivel;
    @JoinColumn(name = "fk_ano_economico", referencedColumnName = "pk_ano_economico")
    @ManyToOne
    private CtAnoEconomico fkAnoEconomico;
    @JoinColumn(name = "fk_rubrica", referencedColumnName = "pk_rubrica")
    @ManyToOne
    private CtRubrica fkRubrica;
    @JoinColumn(name = "fk_utilizador", referencedColumnName = "pk_seg_conta")
    @ManyToOne
    private SegConta fkUtilizador;

    public CtMontanteRubrica()
    {
    }

    public CtMontanteRubrica(Integer pkMontanteRubrica)
    {
        this.pkMontanteRubrica = pkMontanteRubrica;
    }

    public CtMontanteRubrica(Integer pkMontanteRubrica, double valorAnualRubrica, Date registroMontanteRubrica)
    {
        this.pkMontanteRubrica = pkMontanteRubrica;
        this.valorAnualRubrica = valorAnualRubrica;
        this.registroMontanteRubrica = registroMontanteRubrica;
    }

    public Integer getPkMontanteRubrica()
    {
        return pkMontanteRubrica;
    }

    public void setPkMontanteRubrica(Integer pkMontanteRubrica)
    {
        this.pkMontanteRubrica = pkMontanteRubrica;
    }

    public double getValorAnualRubrica()
    {
        return valorAnualRubrica;
    }

    public void setValorAnualRubrica(double valorAnualRubrica)
    {
        this.valorAnualRubrica = valorAnualRubrica;
    }

    public Date getRegistroMontanteRubrica()
    {
        return registroMontanteRubrica;
    }

    public void setRegistroMontanteRubrica(Date registroMontanteRubrica)
    {
        this.registroMontanteRubrica = registroMontanteRubrica;
    }

    public Boolean getStateMontanteRubrica()
    {
        return stateMontanteRubrica;
    }

    public void setStateMontanteRubrica(Boolean stateMontanteRubrica)
    {
        this.stateMontanteRubrica = stateMontanteRubrica;
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

    public CtRubrica getFkRubrica()
    {
        return fkRubrica;
    }

    public void setFkRubrica(CtRubrica fkRubrica)
    {
        this.fkRubrica = fkRubrica;
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
        hash += (pkMontanteRubrica != null ? pkMontanteRubrica.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CtMontanteRubrica))
        {
            return false;
        }
        CtMontanteRubrica other = (CtMontanteRubrica) object;
        if ((this.pkMontanteRubrica == null && other.pkMontanteRubrica != null) || (this.pkMontanteRubrica != null && !this.pkMontanteRubrica.equals(other.pkMontanteRubrica)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.CtMontanteRubrica[ pkMontanteRubrica=" + pkMontanteRubrica + " ]";
    }
    
}
