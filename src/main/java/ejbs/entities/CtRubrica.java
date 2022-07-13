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
@Table(name = "ct_rubrica", catalog = "sig_ucan_db", schema = "public")
@NamedQueries(
{
    @NamedQuery(name = "CtRubrica.findAll", query = "SELECT c FROM CtRubrica c"),
    @NamedQuery(name = "CtRubrica.findByPkRubrica", query = "SELECT c FROM CtRubrica c WHERE c.pkRubrica = :pkRubrica"),
    @NamedQuery(name = "CtRubrica.findByDescricaoRubrica", query = "SELECT c FROM CtRubrica c WHERE c.descricaoRubrica = :descricaoRubrica"),
    @NamedQuery(name = "CtRubrica.findByNumberRubrica", query = "SELECT c FROM CtRubrica c WHERE c.numberRubrica = :numberRubrica"),
    @NamedQuery(name = "CtRubrica.findByDataRegistroRubrica", query = "SELECT c FROM CtRubrica c WHERE c.dataRegistroRubrica = :dataRegistroRubrica"),
    @NamedQuery(name = "CtRubrica.findByNumber", query = "SELECT c FROM CtRubrica c WHERE c.number = :number"),
    @NamedQuery(name = "CtRubrica.findByStateRubrica", query = "SELECT c FROM CtRubrica c WHERE c.stateRubrica = :stateRubrica")
})
public class CtRubrica implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "pk_rubrica", nullable = false)
    private Integer pkRubrica;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "descricao_rubrica", nullable = false, length = 2147483647)
    private String descricaoRubrica;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "number_rubrica", nullable = false, length = 2147483647)
    private String numberRubrica;
    @Basic(optional = false)
    @NotNull
    @Column(name = "data_registro_rubrica", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataRegistroRubrica;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private int number;
    @Column(name = "state_rubrica")
    private Boolean stateRubrica;
    @OneToMany(mappedBy = "fkRubrica")
    private List<GrlEntidade> grlEntidadeList;
    @OneToMany(mappedBy = "fkRubrica")
    private List<CtAccount> ctAccountList;
    @JoinColumn(name = "fk_class", referencedColumnName = "pk_class")
    @ManyToOne
    private CtClass fkClass;
    @OneToMany(mappedBy = "fkRubrica")
    private List<CtRubrica> ctRubricaList;
    @JoinColumn(name = "fk_rubrica", referencedColumnName = "pk_rubrica")
    @ManyToOne
    private CtRubrica fkRubrica;
    @JoinColumn(name = "fk_utilizador", referencedColumnName = "pk_seg_conta")
    @ManyToOne
    private SegConta fkUtilizador;
    @OneToMany(mappedBy = "fkRubrica")
    private List<CtMontanteRubrica> ctMontanteRubricaList;

    public CtRubrica()
    {
    }

    public CtRubrica(Integer pkRubrica)
    {
        this.pkRubrica = pkRubrica;
    }

    public CtRubrica(Integer pkRubrica, String descricaoRubrica, String numberRubrica, Date dataRegistroRubrica, int number)
    {
        this.pkRubrica = pkRubrica;
        this.descricaoRubrica = descricaoRubrica;
        this.numberRubrica = numberRubrica;
        this.dataRegistroRubrica = dataRegistroRubrica;
        this.number = number;
    }

    public Integer getPkRubrica()
    {
        return pkRubrica;
    }

    public void setPkRubrica(Integer pkRubrica)
    {
        this.pkRubrica = pkRubrica;
    }

    public String getDescricaoRubrica()
    {
        return descricaoRubrica;
    }

    public void setDescricaoRubrica(String descricaoRubrica)
    {
        this.descricaoRubrica = descricaoRubrica;
    }

    public String getNumberRubrica()
    {
        return numberRubrica;
    }

    public void setNumberRubrica(String numberRubrica)
    {
        this.numberRubrica = numberRubrica;
    }

    public Date getDataRegistroRubrica()
    {
        return dataRegistroRubrica;
    }

    public void setDataRegistroRubrica(Date dataRegistroRubrica)
    {
        this.dataRegistroRubrica = dataRegistroRubrica;
    }

    public int getNumber()
    {
        return number;
    }

    public void setNumber(int number)
    {
        this.number = number;
    }

    public Boolean getStateRubrica()
    {
        return stateRubrica;
    }

    public void setStateRubrica(Boolean stateRubrica)
    {
        this.stateRubrica = stateRubrica;
    }

    public List<GrlEntidade> getGrlEntidadeList()
    {
        return grlEntidadeList;
    }

    public void setGrlEntidadeList(List<GrlEntidade> grlEntidadeList)
    {
        this.grlEntidadeList = grlEntidadeList;
    }

    public List<CtAccount> getCtAccountList()
    {
        return ctAccountList;
    }

    public void setCtAccountList(List<CtAccount> ctAccountList)
    {
        this.ctAccountList = ctAccountList;
    }

    public CtClass getFkClass()
    {
        return fkClass;
    }

    public void setFkClass(CtClass fkClass)
    {
        this.fkClass = fkClass;
    }

    public List<CtRubrica> getCtRubricaList()
    {
        return ctRubricaList;
    }

    public void setCtRubricaList(List<CtRubrica> ctRubricaList)
    {
        this.ctRubricaList = ctRubricaList;
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

    public List<CtMontanteRubrica> getCtMontanteRubricaList()
    {
        return ctMontanteRubricaList;
    }

    public void setCtMontanteRubricaList(List<CtMontanteRubrica> ctMontanteRubricaList)
    {
        this.ctMontanteRubricaList = ctMontanteRubricaList;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (pkRubrica != null ? pkRubrica.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CtRubrica))
        {
            return false;
        }
        CtRubrica other = (CtRubrica) object;
        if ((this.pkRubrica == null && other.pkRubrica != null) || (this.pkRubrica != null && !this.pkRubrica.equals(other.pkRubrica)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.CtRubrica[ pkRubrica=" + pkRubrica + " ]";
    }
    
}
