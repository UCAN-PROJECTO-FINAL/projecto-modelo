/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
@Table(name = "gs_tipo", catalog = "sig_ucan_db", schema = "public")
@NamedQueries(
{
    @NamedQuery(name = "GsTipo.findAll", query = "SELECT g FROM GsTipo g"),
    @NamedQuery(name = "GsTipo.findByPkGsTipo", query = "SELECT g FROM GsTipo g WHERE g.pkGsTipo = :pkGsTipo"),
    @NamedQuery(name = "GsTipo.findByDescricao", query = "SELECT g FROM GsTipo g WHERE g.descricao = :descricao")
})
public class GsTipo implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "pk_gs_tipo", nullable = false, length = 2147483647)
    private String pkGsTipo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(nullable = false, length = 2147483647)
    private String descricao;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkGsTipo")
    private List<GsLicenca> gsLicencaList;

    public GsTipo()
    {
    }

    public GsTipo(String pkGsTipo)
    {
        this.pkGsTipo = pkGsTipo;
    }

    public GsTipo(String pkGsTipo, String descricao)
    {
        this.pkGsTipo = pkGsTipo;
        this.descricao = descricao;
    }

    public String getPkGsTipo()
    {
        return pkGsTipo;
    }

    public void setPkGsTipo(String pkGsTipo)
    {
        this.pkGsTipo = pkGsTipo;
    }

    public String getDescricao()
    {
        return descricao;
    }

    public void setDescricao(String descricao)
    {
        this.descricao = descricao;
    }

    public List<GsLicenca> getGsLicencaList()
    {
        return gsLicencaList;
    }

    public void setGsLicencaList(List<GsLicenca> gsLicencaList)
    {
        this.gsLicencaList = gsLicencaList;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (pkGsTipo != null ? pkGsTipo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GsTipo))
        {
            return false;
        }
        GsTipo other = (GsTipo) object;
        if ((this.pkGsTipo == null && other.pkGsTipo != null) || (this.pkGsTipo != null && !this.pkGsTipo.equals(other.pkGsTipo)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.GsTipo[ pkGsTipo=" + pkGsTipo + " ]";
    }
    
}
