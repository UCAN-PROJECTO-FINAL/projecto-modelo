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
@Table(name = "ct_lancamento", catalog = "sig_ucan_db", schema = "public")
@NamedQueries(
{
    @NamedQuery(name = "CtLancamento.findAll", query = "SELECT c FROM CtLancamento c"),
    @NamedQuery(name = "CtLancamento.findByPkLancamento", query = "SELECT c FROM CtLancamento c WHERE c.pkLancamento = :pkLancamento"),
    @NamedQuery(name = "CtLancamento.findByValorLancamento", query = "SELECT c FROM CtLancamento c WHERE c.valorLancamento = :valorLancamento"),
    @NamedQuery(name = "CtLancamento.findByDataLancamento", query = "SELECT c FROM CtLancamento c WHERE c.dataLancamento = :dataLancamento"),
    @NamedQuery(name = "CtLancamento.findByDataRegistroLancamento", query = "SELECT c FROM CtLancamento c WHERE c.dataRegistroLancamento = :dataRegistroLancamento"),
    @NamedQuery(name = "CtLancamento.findByObsLancamento", query = "SELECT c FROM CtLancamento c WHERE c.obsLancamento = :obsLancamento"),
    @NamedQuery(name = "CtLancamento.findByStateLancamento", query = "SELECT c FROM CtLancamento c WHERE c.stateLancamento = :stateLancamento"),
    @NamedQuery(name = "CtLancamento.findByStateClssf", query = "SELECT c FROM CtLancamento c WHERE c.stateClssf = :stateClssf")
})
public class CtLancamento implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_lancamento", nullable = false)
    private Integer pkLancamento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor_lancamento", nullable = false)
    private double valorLancamento;
    @Column(name = "data_lancamento")
    @Temporal(TemporalType.DATE)
    private Date dataLancamento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "data_registro_lancamento", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataRegistroLancamento;
    @Size(max = 2147483647)
    @Column(name = "obs_lancamento", length = 2147483647)
    private String obsLancamento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "state_lancamento", nullable = false)
    private boolean stateLancamento;
    @Column(name = "state_clssf")
    private Boolean stateClssf;
    @OneToMany(mappedBy = "fkLancamento")
    private List<CtBalancet> ctBalancetList;
    @JoinColumn(name = "fk_ano_economico", referencedColumnName = "pk_ano_economico")
    @ManyToOne
    private CtAnoEconomico fkAnoEconomico;
    @JoinColumn(name = "fk_document", referencedColumnName = "pk_document")
    @ManyToOne
    private CtDocument fkDocument;
    @JoinColumn(name = "fk_documento", referencedColumnName = "pk_documento")
    @ManyToOne
    private FinDocumento fkDocumento;
    @JoinColumn(name = "fk_utilizador", referencedColumnName = "pk_seg_conta")
    @ManyToOne
    private SegConta fkUtilizador;

    public CtLancamento()
    {
    }

    public CtLancamento(Integer pkLancamento)
    {
        this.pkLancamento = pkLancamento;
    }

    public CtLancamento(Integer pkLancamento, double valorLancamento, Date dataRegistroLancamento, boolean stateLancamento)
    {
        this.pkLancamento = pkLancamento;
        this.valorLancamento = valorLancamento;
        this.dataRegistroLancamento = dataRegistroLancamento;
        this.stateLancamento = stateLancamento;
    }

    public Integer getPkLancamento()
    {
        return pkLancamento;
    }

    public void setPkLancamento(Integer pkLancamento)
    {
        this.pkLancamento = pkLancamento;
    }

    public double getValorLancamento()
    {
        return valorLancamento;
    }

    public void setValorLancamento(double valorLancamento)
    {
        this.valorLancamento = valorLancamento;
    }

    public Date getDataLancamento()
    {
        return dataLancamento;
    }

    public void setDataLancamento(Date dataLancamento)
    {
        this.dataLancamento = dataLancamento;
    }

    public Date getDataRegistroLancamento()
    {
        return dataRegistroLancamento;
    }

    public void setDataRegistroLancamento(Date dataRegistroLancamento)
    {
        this.dataRegistroLancamento = dataRegistroLancamento;
    }

    public String getObsLancamento()
    {
        return obsLancamento;
    }

    public void setObsLancamento(String obsLancamento)
    {
        this.obsLancamento = obsLancamento;
    }

    public boolean getStateLancamento()
    {
        return stateLancamento;
    }

    public void setStateLancamento(boolean stateLancamento)
    {
        this.stateLancamento = stateLancamento;
    }

    public Boolean getStateClssf()
    {
        return stateClssf;
    }

    public void setStateClssf(Boolean stateClssf)
    {
        this.stateClssf = stateClssf;
    }

    public List<CtBalancet> getCtBalancetList()
    {
        return ctBalancetList;
    }

    public void setCtBalancetList(List<CtBalancet> ctBalancetList)
    {
        this.ctBalancetList = ctBalancetList;
    }

    public CtAnoEconomico getFkAnoEconomico()
    {
        return fkAnoEconomico;
    }

    public void setFkAnoEconomico(CtAnoEconomico fkAnoEconomico)
    {
        this.fkAnoEconomico = fkAnoEconomico;
    }

    public CtDocument getFkDocument()
    {
        return fkDocument;
    }

    public void setFkDocument(CtDocument fkDocument)
    {
        this.fkDocument = fkDocument;
    }

    public FinDocumento getFkDocumento()
    {
        return fkDocumento;
    }

    public void setFkDocumento(FinDocumento fkDocumento)
    {
        this.fkDocumento = fkDocumento;
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
        hash += (pkLancamento != null ? pkLancamento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CtLancamento))
        {
            return false;
        }
        CtLancamento other = (CtLancamento) object;
        if ((this.pkLancamento == null && other.pkLancamento != null) || (this.pkLancamento != null && !this.pkLancamento.equals(other.pkLancamento)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.CtLancamento[ pkLancamento=" + pkLancamento + " ]";
    }
    
}
