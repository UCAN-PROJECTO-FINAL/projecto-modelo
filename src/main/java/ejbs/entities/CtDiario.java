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
@Table(name = "ct_diario", catalog = "sig_ucan_db", schema = "public")
@NamedQueries(
{
    @NamedQuery(name = "CtDiario.findAll", query = "SELECT c FROM CtDiario c"),
    @NamedQuery(name = "CtDiario.findByPkDiario", query = "SELECT c FROM CtDiario c WHERE c.pkDiario = :pkDiario"),
    @NamedQuery(name = "CtDiario.findByDescricaoDiario", query = "SELECT c FROM CtDiario c WHERE c.descricaoDiario = :descricaoDiario"),
    @NamedQuery(name = "CtDiario.findByDesignacaoDiario", query = "SELECT c FROM CtDiario c WHERE c.designacaoDiario = :designacaoDiario"),
    @NamedQuery(name = "CtDiario.findByDataRegistroDiario", query = "SELECT c FROM CtDiario c WHERE c.dataRegistroDiario = :dataRegistroDiario"),
    @NamedQuery(name = "CtDiario.findByStateDiario", query = "SELECT c FROM CtDiario c WHERE c.stateDiario = :stateDiario")
})
public class CtDiario implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_diario", nullable = false)
    private Integer pkDiario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "descricao_diario", nullable = false, length = 2147483647)
    private String descricaoDiario;
    @Size(max = 2147483647)
    @Column(name = "designacao_diario", length = 2147483647)
    private String designacaoDiario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "data_registro_diario", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataRegistroDiario;
    @Column(name = "state_diario")
    private Boolean stateDiario;
    @JoinColumn(name = "fk_utilizador", referencedColumnName = "pk_seg_conta")
    @ManyToOne
    private SegConta fkUtilizador;
    @OneToMany(mappedBy = "fkDiario")
    private List<CtDocument> ctDocumentList;

    public CtDiario()
    {
    }

    public CtDiario(Integer pkDiario)
    {
        this.pkDiario = pkDiario;
    }

    public CtDiario(Integer pkDiario, String descricaoDiario, Date dataRegistroDiario)
    {
        this.pkDiario = pkDiario;
        this.descricaoDiario = descricaoDiario;
        this.dataRegistroDiario = dataRegistroDiario;
    }

    public Integer getPkDiario()
    {
        return pkDiario;
    }

    public void setPkDiario(Integer pkDiario)
    {
        this.pkDiario = pkDiario;
    }

    public String getDescricaoDiario()
    {
        return descricaoDiario;
    }

    public void setDescricaoDiario(String descricaoDiario)
    {
        this.descricaoDiario = descricaoDiario;
    }

    public String getDesignacaoDiario()
    {
        return designacaoDiario;
    }

    public void setDesignacaoDiario(String designacaoDiario)
    {
        this.designacaoDiario = designacaoDiario;
    }

    public Date getDataRegistroDiario()
    {
        return dataRegistroDiario;
    }

    public void setDataRegistroDiario(Date dataRegistroDiario)
    {
        this.dataRegistroDiario = dataRegistroDiario;
    }

    public Boolean getStateDiario()
    {
        return stateDiario;
    }

    public void setStateDiario(Boolean stateDiario)
    {
        this.stateDiario = stateDiario;
    }

    public SegConta getFkUtilizador()
    {
        return fkUtilizador;
    }

    public void setFkUtilizador(SegConta fkUtilizador)
    {
        this.fkUtilizador = fkUtilizador;
    }

    public List<CtDocument> getCtDocumentList()
    {
        return ctDocumentList;
    }

    public void setCtDocumentList(List<CtDocument> ctDocumentList)
    {
        this.ctDocumentList = ctDocumentList;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (pkDiario != null ? pkDiario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CtDiario))
        {
            return false;
        }
        CtDiario other = (CtDiario) object;
        if ((this.pkDiario == null && other.pkDiario != null) || (this.pkDiario != null && !this.pkDiario.equals(other.pkDiario)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.CtDiario[ pkDiario=" + pkDiario + " ]";
    }
    
}
