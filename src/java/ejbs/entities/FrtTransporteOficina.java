/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author smakambo
 */
@Entity
@Table(name = "frt_transporte_oficina")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "FrtTransporteOficina.findAll", query = "SELECT f FROM FrtTransporteOficina f"),
    @NamedQuery(name = "FrtTransporteOficina.findByPkOficina", query = "SELECT f FROM FrtTransporteOficina f WHERE f.pkOficina = :pkOficina"),
    @NamedQuery(name = "FrtTransporteOficina.findByDescricao", query = "SELECT f FROM FrtTransporteOficina f WHERE f.descricao = :descricao")
})
public class FrtTransporteOficina implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_oficina")
    private Integer pkOficina;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "descricao")
    private String descricao;

    public FrtTransporteOficina()
    {
    }

    public FrtTransporteOficina(Integer pkOficina)
    {
        this.pkOficina = pkOficina;
    }

    public FrtTransporteOficina(Integer pkOficina, String descricao)
    {
        this.pkOficina = pkOficina;
        this.descricao = descricao;
    }

    public Integer getPkOficina()
    {
        return pkOficina;
    }

    public void setPkOficina(Integer pkOficina)
    {
        this.pkOficina = pkOficina;
    }

    public String getDescricao()
    {
        return descricao;
    }

    public void setDescricao(String descricao)
    {
        this.descricao = descricao;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (pkOficina != null ? pkOficina.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FrtTransporteOficina))
        {
            return false;
        }
        FrtTransporteOficina other = (FrtTransporteOficina) object;
        if ((this.pkOficina == null && other.pkOficina != null) || (this.pkOficina != null && !this.pkOficina.equals(other.pkOficina)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.FrtTransporteOficina[ pkOficina=" + pkOficina + " ]";
    }
    
}
