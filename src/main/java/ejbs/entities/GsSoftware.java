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
import javax.persistence.CascadeType;
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
import javax.validation.constraints.Size;

/**
 *
 * @author smakambo
 */
@Entity
@Table(name = "gs_software", catalog = "sig_ucan_db", schema = "public")
@NamedQueries(
{
    @NamedQuery(name = "GsSoftware.findAll", query = "SELECT g FROM GsSoftware g"),
    @NamedQuery(name = "GsSoftware.findByPkGsSoftware", query = "SELECT g FROM GsSoftware g WHERE g.pkGsSoftware = :pkGsSoftware"),
    @NamedQuery(name = "GsSoftware.findByNome", query = "SELECT g FROM GsSoftware g WHERE g.nome = :nome"),
    @NamedQuery(name = "GsSoftware.findByDataAquisicao", query = "SELECT g FROM GsSoftware g WHERE g.dataAquisicao = :dataAquisicao"),
    @NamedQuery(name = "GsSoftware.findByVersao", query = "SELECT g FROM GsSoftware g WHERE g.versao = :versao")
})
public class GsSoftware implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_gs_software", nullable = false)
    private Integer pkGsSoftware;
    @Size(max = 50)
    @Column(length = 50)
    private String nome;
    @Column(name = "data_aquisicao")
    @Temporal(TemporalType.DATE)
    private Date dataAquisicao;
    @Size(max = 50)
    @Column(length = 50)
    private String versao;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkGsSoftware")
    private List<GsLicenca> gsLicencaList;
    @JoinColumn(name = "fk_gs_fornecedor", referencedColumnName = "pk_gs_fornecedor")
    @ManyToOne
    private GsFornecedor fkGsFornecedor;
    @JoinColumn(name = "fk_gs_subcategoria", referencedColumnName = "pk_gs_subcategoria")
    @ManyToOne
    private GsSubcategoria fkGsSubcategoria;

    public GsSoftware()
    {
    }

    public GsSoftware(Integer pkGsSoftware)
    {
        this.pkGsSoftware = pkGsSoftware;
    }

    public Integer getPkGsSoftware()
    {
        return pkGsSoftware;
    }

    public void setPkGsSoftware(Integer pkGsSoftware)
    {
        this.pkGsSoftware = pkGsSoftware;
    }

    public String getNome()
    {
        return nome;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    public Date getDataAquisicao()
    {
        return dataAquisicao;
    }

    public void setDataAquisicao(Date dataAquisicao)
    {
        this.dataAquisicao = dataAquisicao;
    }

    public String getVersao()
    {
        return versao;
    }

    public void setVersao(String versao)
    {
        this.versao = versao;
    }

    public List<GsLicenca> getGsLicencaList()
    {
        return gsLicencaList;
    }

    public void setGsLicencaList(List<GsLicenca> gsLicencaList)
    {
        this.gsLicencaList = gsLicencaList;
    }

    public GsFornecedor getFkGsFornecedor()
    {
        return fkGsFornecedor;
    }

    public void setFkGsFornecedor(GsFornecedor fkGsFornecedor)
    {
        this.fkGsFornecedor = fkGsFornecedor;
    }

    public GsSubcategoria getFkGsSubcategoria()
    {
        return fkGsSubcategoria;
    }

    public void setFkGsSubcategoria(GsSubcategoria fkGsSubcategoria)
    {
        this.fkGsSubcategoria = fkGsSubcategoria;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (pkGsSoftware != null ? pkGsSoftware.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GsSoftware))
        {
            return false;
        }
        GsSoftware other = (GsSoftware) object;
        if ((this.pkGsSoftware == null && other.pkGsSoftware != null) || (this.pkGsSoftware != null && !this.pkGsSoftware.equals(other.pkGsSoftware)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.GsSoftware[ pkGsSoftware=" + pkGsSoftware + " ]";
    }
    
}
