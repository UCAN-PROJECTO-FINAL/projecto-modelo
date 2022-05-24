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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author smakambo
 */
@Entity
@Table(name = "pt_transporte_categoria")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "PtTransporteCategoria.findAll", query = "SELECT p FROM PtTransporteCategoria p"),
    @NamedQuery(name = "PtTransporteCategoria.findByPkTransporteCategoria", query = "SELECT p FROM PtTransporteCategoria p WHERE p.pkTransporteCategoria = :pkTransporteCategoria"),
    @NamedQuery(name = "PtTransporteCategoria.findByDescricao", query = "SELECT p FROM PtTransporteCategoria p WHERE p.descricao = :descricao")
})
public class PtTransporteCategoria implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "pk_transporte_categoria")
    private Integer pkTransporteCategoria;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "descricao")
    private String descricao;
    @OneToMany(mappedBy = "fkCategoriaTransporte")
    private List<PtTransporte> ptTransporteList;

    public PtTransporteCategoria()
    {
    }

    public PtTransporteCategoria(Integer pkTransporteCategoria)
    {
        this.pkTransporteCategoria = pkTransporteCategoria;
    }

    public PtTransporteCategoria(Integer pkTransporteCategoria, String descricao)
    {
        this.pkTransporteCategoria = pkTransporteCategoria;
        this.descricao = descricao;
    }

    public Integer getPkTransporteCategoria()
    {
        return pkTransporteCategoria;
    }

    public void setPkTransporteCategoria(Integer pkTransporteCategoria)
    {
        this.pkTransporteCategoria = pkTransporteCategoria;
    }

    public String getDescricao()
    {
        return descricao;
    }

    public void setDescricao(String descricao)
    {
        this.descricao = descricao;
    }

    @XmlTransient
    public List<PtTransporte> getPtTransporteList()
    {
        return ptTransporteList;
    }

    public void setPtTransporteList(List<PtTransporte> ptTransporteList)
    {
        this.ptTransporteList = ptTransporteList;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (pkTransporteCategoria != null ? pkTransporteCategoria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PtTransporteCategoria))
        {
            return false;
        }
        PtTransporteCategoria other = (PtTransporteCategoria) object;
        if ((this.pkTransporteCategoria == null && other.pkTransporteCategoria != null) || (this.pkTransporteCategoria != null && !this.pkTransporteCategoria.equals(other.pkTransporteCategoria)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.PtTransporteCategoria[ pkTransporteCategoria=" + pkTransporteCategoria + " ]";
    }
    
}
