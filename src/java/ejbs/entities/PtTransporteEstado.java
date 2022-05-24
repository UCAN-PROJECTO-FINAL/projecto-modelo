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
@Table(name = "pt_transporte_estado")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "PtTransporteEstado.findAll", query = "SELECT p FROM PtTransporteEstado p"),
    @NamedQuery(name = "PtTransporteEstado.findByPkEstado", query = "SELECT p FROM PtTransporteEstado p WHERE p.pkEstado = :pkEstado"),
    @NamedQuery(name = "PtTransporteEstado.findByDescricao", query = "SELECT p FROM PtTransporteEstado p WHERE p.descricao = :descricao")
})
public class PtTransporteEstado implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "pk_estado")
    private Integer pkEstado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "descricao")
    private String descricao;
    @OneToMany(mappedBy = "fkEstado")
    private List<PtTransporte> ptTransporteList;

    public PtTransporteEstado()
    {
    }

    public PtTransporteEstado(Integer pkEstado)
    {
        this.pkEstado = pkEstado;
    }

    public PtTransporteEstado(Integer pkEstado, String descricao)
    {
        this.pkEstado = pkEstado;
        this.descricao = descricao;
    }

    public Integer getPkEstado()
    {
        return pkEstado;
    }

    public void setPkEstado(Integer pkEstado)
    {
        this.pkEstado = pkEstado;
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
        hash += (pkEstado != null ? pkEstado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PtTransporteEstado))
        {
            return false;
        }
        PtTransporteEstado other = (PtTransporteEstado) object;
        if ((this.pkEstado == null && other.pkEstado != null) || (this.pkEstado != null && !this.pkEstado.equals(other.pkEstado)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.PtTransporteEstado[ pkEstado=" + pkEstado + " ]";
    }
    
}
