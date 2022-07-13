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
@Table(name = "gs_categoria", catalog = "sig_ucan_db", schema = "public")
@NamedQueries(
{
    @NamedQuery(name = "GsCategoria.findAll", query = "SELECT g FROM GsCategoria g"),
    @NamedQuery(name = "GsCategoria.findByPkGsCategoria", query = "SELECT g FROM GsCategoria g WHERE g.pkGsCategoria = :pkGsCategoria"),
    @NamedQuery(name = "GsCategoria.findByDescricao", query = "SELECT g FROM GsCategoria g WHERE g.descricao = :descricao")
})
public class GsCategoria implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "pk_gs_categoria", nullable = false, length = 2147483647)
    private String pkGsCategoria;
    @Size(max = 100)
    @Column(length = 100)
    private String descricao;
    @OneToMany(mappedBy = "fkGsCategoria")
    private List<GsSubcategoria> gsSubcategoriaList;

    public GsCategoria()
    {
    }

    public GsCategoria(String pkGsCategoria)
    {
        this.pkGsCategoria = pkGsCategoria;
    }

    public String getPkGsCategoria()
    {
        return pkGsCategoria;
    }

    public void setPkGsCategoria(String pkGsCategoria)
    {
        this.pkGsCategoria = pkGsCategoria;
    }

    public String getDescricao()
    {
        return descricao;
    }

    public void setDescricao(String descricao)
    {
        this.descricao = descricao;
    }

    public List<GsSubcategoria> getGsSubcategoriaList()
    {
        return gsSubcategoriaList;
    }

    public void setGsSubcategoriaList(List<GsSubcategoria> gsSubcategoriaList)
    {
        this.gsSubcategoriaList = gsSubcategoriaList;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (pkGsCategoria != null ? pkGsCategoria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GsCategoria))
        {
            return false;
        }
        GsCategoria other = (GsCategoria) object;
        if ((this.pkGsCategoria == null && other.pkGsCategoria != null) || (this.pkGsCategoria != null && !this.pkGsCategoria.equals(other.pkGsCategoria)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.GsCategoria[ pkGsCategoria=" + pkGsCategoria + " ]";
    }
    
}
