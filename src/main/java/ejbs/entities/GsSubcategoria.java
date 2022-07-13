/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.entities;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author smakambo
 */
@Entity
@Table(name = "gs_subcategoria", catalog = "sig_ucan_db", schema = "public")
@NamedQueries(
{
    @NamedQuery(name = "GsSubcategoria.findAll", query = "SELECT g FROM GsSubcategoria g"),
    @NamedQuery(name = "GsSubcategoria.findByPkGsSubcategoria", query = "SELECT g FROM GsSubcategoria g WHERE g.pkGsSubcategoria = :pkGsSubcategoria"),
    @NamedQuery(name = "GsSubcategoria.findByDescricao", query = "SELECT g FROM GsSubcategoria g WHERE g.descricao = :descricao")
})
public class GsSubcategoria implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "pk_gs_subcategoria", nullable = false, length = 2147483647)
    private String pkGsSubcategoria;
    @Size(max = 50)
    @Column(length = 50)
    private String descricao;
    @JoinColumn(name = "fk_gs_categoria", referencedColumnName = "pk_gs_categoria")
    @ManyToOne
    private GsCategoria fkGsCategoria;
    @OneToMany(mappedBy = "fkGsSubcategoria")
    private List<GsSoftware> gsSoftwareList;

    public GsSubcategoria()
    {
    }

    public GsSubcategoria(String pkGsSubcategoria)
    {
        this.pkGsSubcategoria = pkGsSubcategoria;
    }

    public String getPkGsSubcategoria()
    {
        return pkGsSubcategoria;
    }

    public void setPkGsSubcategoria(String pkGsSubcategoria)
    {
        this.pkGsSubcategoria = pkGsSubcategoria;
    }

    public String getDescricao()
    {
        return descricao;
    }

    public void setDescricao(String descricao)
    {
        this.descricao = descricao;
    }

    public GsCategoria getFkGsCategoria()
    {
        return fkGsCategoria;
    }

    public void setFkGsCategoria(GsCategoria fkGsCategoria)
    {
        this.fkGsCategoria = fkGsCategoria;
    }

    public List<GsSoftware> getGsSoftwareList()
    {
        return gsSoftwareList;
    }

    public void setGsSoftwareList(List<GsSoftware> gsSoftwareList)
    {
        this.gsSoftwareList = gsSoftwareList;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (pkGsSubcategoria != null ? pkGsSubcategoria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GsSubcategoria))
        {
            return false;
        }
        GsSubcategoria other = (GsSubcategoria) object;
        if ((this.pkGsSubcategoria == null && other.pkGsSubcategoria != null) || (this.pkGsSubcategoria != null && !this.pkGsSubcategoria.equals(other.pkGsSubcategoria)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.GsSubcategoria[ pkGsSubcategoria=" + pkGsSubcategoria + " ]";
    }
    
}
