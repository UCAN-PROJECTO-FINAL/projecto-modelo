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
@Table(name = "frt_transporte_tipo_combustivel")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "FrtTransporteTipoCombustivel.findAll", query = "SELECT f FROM FrtTransporteTipoCombustivel f"),
    @NamedQuery(name = "FrtTransporteTipoCombustivel.findByPkTipoCombustivel", query = "SELECT f FROM FrtTransporteTipoCombustivel f WHERE f.pkTipoCombustivel = :pkTipoCombustivel"),
    @NamedQuery(name = "FrtTransporteTipoCombustivel.findByDescricao", query = "SELECT f FROM FrtTransporteTipoCombustivel f WHERE f.descricao = :descricao")
})
public class FrtTransporteTipoCombustivel implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_tipo_combustivel")
    private Integer pkTipoCombustivel;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "descricao")
    private String descricao;

    public FrtTransporteTipoCombustivel()
    {
    }

    public FrtTransporteTipoCombustivel(Integer pkTipoCombustivel)
    {
        this.pkTipoCombustivel = pkTipoCombustivel;
    }

    public FrtTransporteTipoCombustivel(Integer pkTipoCombustivel, String descricao)
    {
        this.pkTipoCombustivel = pkTipoCombustivel;
        this.descricao = descricao;
    }

    public Integer getPkTipoCombustivel()
    {
        return pkTipoCombustivel;
    }

    public void setPkTipoCombustivel(Integer pkTipoCombustivel)
    {
        this.pkTipoCombustivel = pkTipoCombustivel;
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
        hash += (pkTipoCombustivel != null ? pkTipoCombustivel.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FrtTransporteTipoCombustivel))
        {
            return false;
        }
        FrtTransporteTipoCombustivel other = (FrtTransporteTipoCombustivel) object;
        if ((this.pkTipoCombustivel == null && other.pkTipoCombustivel != null) || (this.pkTipoCombustivel != null && !this.pkTipoCombustivel.equals(other.pkTipoCombustivel)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.FrtTransporteTipoCombustivel[ pkTipoCombustivel=" + pkTipoCombustivel + " ]";
    }
    
}
