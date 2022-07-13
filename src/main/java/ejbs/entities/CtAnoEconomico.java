/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.entities;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author smakambo
 */
@Entity
@Table(name = "ct_ano_economico", catalog = "sig_ucan_db", schema = "public")
@NamedQueries(
{
    @NamedQuery(name = "CtAnoEconomico.findAll", query = "SELECT c FROM CtAnoEconomico c"),
    @NamedQuery(name = "CtAnoEconomico.findByPkAnoEconomico", query = "SELECT c FROM CtAnoEconomico c WHERE c.pkAnoEconomico = :pkAnoEconomico"),
    @NamedQuery(name = "CtAnoEconomico.findByAnoEconomico", query = "SELECT c FROM CtAnoEconomico c WHERE c.anoEconomico = :anoEconomico"),
    @NamedQuery(name = "CtAnoEconomico.findByDataAnoEconomico", query = "SELECT c FROM CtAnoEconomico c WHERE c.dataAnoEconomico = :dataAnoEconomico"),
    @NamedQuery(name = "CtAnoEconomico.findByValorAnual", query = "SELECT c FROM CtAnoEconomico c WHERE c.valorAnual = :valorAnual"),
    @NamedQuery(name = "CtAnoEconomico.findByStateAnoEconomico", query = "SELECT c FROM CtAnoEconomico c WHERE c.stateAnoEconomico = :stateAnoEconomico"),
    @NamedQuery(name = "CtAnoEconomico.findByValorDisponivel", query = "SELECT c FROM CtAnoEconomico c WHERE c.valorDisponivel = :valorDisponivel")
})
public class CtAnoEconomico implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_ano_economico", nullable = false)
    private Integer pkAnoEconomico;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ano_economico", nullable = false)
    private int anoEconomico;
    @Basic(optional = false)
    @NotNull
    @Column(name = "data_ano_economico", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAnoEconomico;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor_anual", nullable = false)
    private double valorAnual;
    @Column(name = "state_ano_economico")
    private Boolean stateAnoEconomico;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valor_disponivel", precision = 17, scale = 17)
    private Double valorDisponivel;
    @OneToMany(mappedBy = "fkAnoEconomico")
    private List<CtMontanteClasse> ctMontanteClasseList;
    @OneToMany(mappedBy = "fkAnoEconomico")
    private List<CtLancamento> ctLancamentoList;
    @OneToMany(mappedBy = "fkAnoEconomico")
    private List<CtContraPartidai> ctContraPartidaiList;
    @OneToMany(mappedBy = "fkAnoEconomico")
    private List<CtMontanteRubrica> ctMontanteRubricaList;
    @JoinColumn(name = "fk_utilizador", referencedColumnName = "pk_seg_conta")
    @ManyToOne
    private SegConta fkUtilizador;

    public CtAnoEconomico()
    {
    }

    public CtAnoEconomico(Integer pkAnoEconomico)
    {
        this.pkAnoEconomico = pkAnoEconomico;
    }

    public CtAnoEconomico(Integer pkAnoEconomico, int anoEconomico, Date dataAnoEconomico, double valorAnual)
    {
        this.pkAnoEconomico = pkAnoEconomico;
        this.anoEconomico = anoEconomico;
        this.dataAnoEconomico = dataAnoEconomico;
        this.valorAnual = valorAnual;
    }

    public Integer getPkAnoEconomico()
    {
        return pkAnoEconomico;
    }

    public void setPkAnoEconomico(Integer pkAnoEconomico)
    {
        this.pkAnoEconomico = pkAnoEconomico;
    }

    public int getAnoEconomico()
    {
        return anoEconomico;
    }

    public void setAnoEconomico(int anoEconomico)
    {
        this.anoEconomico = anoEconomico;
    }

    public Date getDataAnoEconomico()
    {
        return dataAnoEconomico;
    }

    public void setDataAnoEconomico(Date dataAnoEconomico)
    {
        this.dataAnoEconomico = dataAnoEconomico;
    }

    public double getValorAnual()
    {
        return valorAnual;
    }

    public void setValorAnual(double valorAnual)
    {
        this.valorAnual = valorAnual;
    }

    public Boolean getStateAnoEconomico()
    {
        return stateAnoEconomico;
    }

    public void setStateAnoEconomico(Boolean stateAnoEconomico)
    {
        this.stateAnoEconomico = stateAnoEconomico;
    }

    public Double getValorDisponivel()
    {
        return valorDisponivel;
    }

    public void setValorDisponivel(Double valorDisponivel)
    {
        this.valorDisponivel = valorDisponivel;
    }

    public List<CtMontanteClasse> getCtMontanteClasseList()
    {
        return ctMontanteClasseList;
    }

    public void setCtMontanteClasseList(List<CtMontanteClasse> ctMontanteClasseList)
    {
        this.ctMontanteClasseList = ctMontanteClasseList;
    }

    public List<CtLancamento> getCtLancamentoList()
    {
        return ctLancamentoList;
    }

    public void setCtLancamentoList(List<CtLancamento> ctLancamentoList)
    {
        this.ctLancamentoList = ctLancamentoList;
    }

    public List<CtContraPartidai> getCtContraPartidaiList()
    {
        return ctContraPartidaiList;
    }

    public void setCtContraPartidaiList(List<CtContraPartidai> ctContraPartidaiList)
    {
        this.ctContraPartidaiList = ctContraPartidaiList;
    }

    public List<CtMontanteRubrica> getCtMontanteRubricaList()
    {
        return ctMontanteRubricaList;
    }

    public void setCtMontanteRubricaList(List<CtMontanteRubrica> ctMontanteRubricaList)
    {
        this.ctMontanteRubricaList = ctMontanteRubricaList;
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
        hash += (pkAnoEconomico != null ? pkAnoEconomico.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CtAnoEconomico))
        {
            return false;
        }
        CtAnoEconomico other = (CtAnoEconomico) object;
        if ((this.pkAnoEconomico == null && other.pkAnoEconomico != null) || (this.pkAnoEconomico != null && !this.pkAnoEconomico.equals(other.pkAnoEconomico)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.CtAnoEconomico[ pkAnoEconomico=" + pkAnoEconomico + " ]";
    }
    
}
