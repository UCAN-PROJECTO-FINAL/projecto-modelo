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
import javax.validation.constraints.Size;

/**
 *
 * @author smakambo
 */
@Entity
@Table(name = "ct_class", catalog = "sig_ucan_db", schema = "public")
@NamedQueries(
{
    @NamedQuery(name = "CtClass.findAll", query = "SELECT c FROM CtClass c"),
    @NamedQuery(name = "CtClass.findByPkClass", query = "SELECT c FROM CtClass c WHERE c.pkClass = :pkClass"),
    @NamedQuery(name = "CtClass.findByDescricaoClass", query = "SELECT c FROM CtClass c WHERE c.descricaoClass = :descricaoClass"),
    @NamedQuery(name = "CtClass.findByDataRegistroClass", query = "SELECT c FROM CtClass c WHERE c.dataRegistroClass = :dataRegistroClass"),
    @NamedQuery(name = "CtClass.findByStateClass", query = "SELECT c FROM CtClass c WHERE c.stateClass = :stateClass")
})
public class CtClass implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_class", nullable = false)
    private Integer pkClass;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "descricao_class", nullable = false, length = 2147483647)
    private String descricaoClass;
    @Basic(optional = false)
    @NotNull
    @Column(name = "data_registro_class", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataRegistroClass;
    @Basic(optional = false)
    @NotNull
    @Column(name = "state_class", nullable = false)
    private boolean stateClass;
    @JoinColumn(name = "fk_utilizador", referencedColumnName = "pk_seg_conta")
    @ManyToOne
    private SegConta fkUtilizador;
    @OneToMany(mappedBy = "fkClass")
    private List<CtMontanteClasse> ctMontanteClasseList;
    @OneToMany(mappedBy = "fkClass")
    private List<CtRubrica> ctRubricaList;

    public CtClass()
    {
    }

    public CtClass(Integer pkClass)
    {
        this.pkClass = pkClass;
    }

    public CtClass(Integer pkClass, String descricaoClass, Date dataRegistroClass, boolean stateClass)
    {
        this.pkClass = pkClass;
        this.descricaoClass = descricaoClass;
        this.dataRegistroClass = dataRegistroClass;
        this.stateClass = stateClass;
    }

    public Integer getPkClass()
    {
        return pkClass;
    }

    public void setPkClass(Integer pkClass)
    {
        this.pkClass = pkClass;
    }

    public String getDescricaoClass()
    {
        return descricaoClass;
    }

    public void setDescricaoClass(String descricaoClass)
    {
        this.descricaoClass = descricaoClass;
    }

    public Date getDataRegistroClass()
    {
        return dataRegistroClass;
    }

    public void setDataRegistroClass(Date dataRegistroClass)
    {
        this.dataRegistroClass = dataRegistroClass;
    }

    public boolean getStateClass()
    {
        return stateClass;
    }

    public void setStateClass(boolean stateClass)
    {
        this.stateClass = stateClass;
    }

    public SegConta getFkUtilizador()
    {
        return fkUtilizador;
    }

    public void setFkUtilizador(SegConta fkUtilizador)
    {
        this.fkUtilizador = fkUtilizador;
    }

    public List<CtMontanteClasse> getCtMontanteClasseList()
    {
        return ctMontanteClasseList;
    }

    public void setCtMontanteClasseList(List<CtMontanteClasse> ctMontanteClasseList)
    {
        this.ctMontanteClasseList = ctMontanteClasseList;
    }

    public List<CtRubrica> getCtRubricaList()
    {
        return ctRubricaList;
    }

    public void setCtRubricaList(List<CtRubrica> ctRubricaList)
    {
        this.ctRubricaList = ctRubricaList;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (pkClass != null ? pkClass.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CtClass))
        {
            return false;
        }
        CtClass other = (CtClass) object;
        if ((this.pkClass == null && other.pkClass != null) || (this.pkClass != null && !this.pkClass.equals(other.pkClass)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.CtClass[ pkClass=" + pkClass + " ]";
    }
    
}
