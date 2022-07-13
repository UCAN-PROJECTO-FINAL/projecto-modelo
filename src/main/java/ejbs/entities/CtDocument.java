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
@Table(name = "ct_document", catalog = "sig_ucan_db", schema = "public")
@NamedQueries(
{
    @NamedQuery(name = "CtDocument.findAll", query = "SELECT c FROM CtDocument c"),
    @NamedQuery(name = "CtDocument.findByPkDocument", query = "SELECT c FROM CtDocument c WHERE c.pkDocument = :pkDocument"),
    @NamedQuery(name = "CtDocument.findByDescricaoDocumento", query = "SELECT c FROM CtDocument c WHERE c.descricaoDocumento = :descricaoDocumento"),
    @NamedQuery(name = "CtDocument.findByDataRegistroDocumento", query = "SELECT c FROM CtDocument c WHERE c.dataRegistroDocumento = :dataRegistroDocumento"),
    @NamedQuery(name = "CtDocument.findByNumeroDocumento", query = "SELECT c FROM CtDocument c WHERE c.numeroDocumento = :numeroDocumento"),
    @NamedQuery(name = "CtDocument.findByNumber", query = "SELECT c FROM CtDocument c WHERE c.number = :number"),
    @NamedQuery(name = "CtDocument.findByStateDocument", query = "SELECT c FROM CtDocument c WHERE c.stateDocument = :stateDocument")
})
public class CtDocument implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "pk_document", nullable = false)
    private Integer pkDocument;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "descricao_documento", nullable = false, length = 2147483647)
    private String descricaoDocumento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "data_registro_documento", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataRegistroDocumento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "numero_documento", nullable = false, length = 2147483647)
    private String numeroDocumento;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private int number;
    @Column(name = "state_document")
    private Boolean stateDocument;
    @JoinColumn(name = "fk_diario", referencedColumnName = "pk_diario")
    @ManyToOne
    private CtDiario fkDiario;
    @JoinColumn(name = "fk_utilizador", referencedColumnName = "pk_seg_conta")
    @ManyToOne
    private SegConta fkUtilizador;
    @OneToMany(mappedBy = "fkDocument")
    private List<CtLancamento> ctLancamentoList;

    public CtDocument()
    {
    }

    public CtDocument(Integer pkDocument)
    {
        this.pkDocument = pkDocument;
    }

    public CtDocument(Integer pkDocument, String descricaoDocumento, Date dataRegistroDocumento, String numeroDocumento, int number)
    {
        this.pkDocument = pkDocument;
        this.descricaoDocumento = descricaoDocumento;
        this.dataRegistroDocumento = dataRegistroDocumento;
        this.numeroDocumento = numeroDocumento;
        this.number = number;
    }

    public Integer getPkDocument()
    {
        return pkDocument;
    }

    public void setPkDocument(Integer pkDocument)
    {
        this.pkDocument = pkDocument;
    }

    public String getDescricaoDocumento()
    {
        return descricaoDocumento;
    }

    public void setDescricaoDocumento(String descricaoDocumento)
    {
        this.descricaoDocumento = descricaoDocumento;
    }

    public Date getDataRegistroDocumento()
    {
        return dataRegistroDocumento;
    }

    public void setDataRegistroDocumento(Date dataRegistroDocumento)
    {
        this.dataRegistroDocumento = dataRegistroDocumento;
    }

    public String getNumeroDocumento()
    {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento)
    {
        this.numeroDocumento = numeroDocumento;
    }

    public int getNumber()
    {
        return number;
    }

    public void setNumber(int number)
    {
        this.number = number;
    }

    public Boolean getStateDocument()
    {
        return stateDocument;
    }

    public void setStateDocument(Boolean stateDocument)
    {
        this.stateDocument = stateDocument;
    }

    public CtDiario getFkDiario()
    {
        return fkDiario;
    }

    public void setFkDiario(CtDiario fkDiario)
    {
        this.fkDiario = fkDiario;
    }

    public SegConta getFkUtilizador()
    {
        return fkUtilizador;
    }

    public void setFkUtilizador(SegConta fkUtilizador)
    {
        this.fkUtilizador = fkUtilizador;
    }

    public List<CtLancamento> getCtLancamentoList()
    {
        return ctLancamentoList;
    }

    public void setCtLancamentoList(List<CtLancamento> ctLancamentoList)
    {
        this.ctLancamentoList = ctLancamentoList;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (pkDocument != null ? pkDocument.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CtDocument))
        {
            return false;
        }
        CtDocument other = (CtDocument) object;
        if ((this.pkDocument == null && other.pkDocument != null) || (this.pkDocument != null && !this.pkDocument.equals(other.pkDocument)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.CtDocument[ pkDocument=" + pkDocument + " ]";
    }
    
}
