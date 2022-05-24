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
@Table(name = "pt_transporte_tipo_combustivel")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "PtTransporteTipoCombustivel.findAll", query = "SELECT p FROM PtTransporteTipoCombustivel p"),
    @NamedQuery(name = "PtTransporteTipoCombustivel.findByPkTipoCombustivel", query = "SELECT p FROM PtTransporteTipoCombustivel p WHERE p.pkTipoCombustivel = :pkTipoCombustivel"),
    @NamedQuery(name = "PtTransporteTipoCombustivel.findByDescricao", query = "SELECT p FROM PtTransporteTipoCombustivel p WHERE p.descricao = :descricao")
})
public class PtTransporteTipoCombustivel implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "pk_tipo_combustivel")
    private Integer pkTipoCombustivel;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "descricao")
    private String descricao;
    @OneToMany(mappedBy = "fkTipoCombustivel")
    private List<PtTransporte> ptTransporteList;

    public PtTransporteTipoCombustivel()
    {
    }

    public PtTransporteTipoCombustivel(Integer pkTipoCombustivel)
    {
        this.pkTipoCombustivel = pkTipoCombustivel;
    }

    public PtTransporteTipoCombustivel(Integer pkTipoCombustivel, String descricao)
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
        hash += (pkTipoCombustivel != null ? pkTipoCombustivel.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PtTransporteTipoCombustivel))
        {
            return false;
        }
        PtTransporteTipoCombustivel other = (PtTransporteTipoCombustivel) object;
        if ((this.pkTipoCombustivel == null && other.pkTipoCombustivel != null) || (this.pkTipoCombustivel != null && !this.pkTipoCombustivel.equals(other.pkTipoCombustivel)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.PtTransporteTipoCombustivel[ pkTipoCombustivel=" + pkTipoCombustivel + " ]";
    }
    
}
