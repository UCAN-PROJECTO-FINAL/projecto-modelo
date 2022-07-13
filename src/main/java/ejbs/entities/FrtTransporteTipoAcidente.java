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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "frt_transporte_tipo_acidente", catalog = "sig_ucan_db", schema = "public")
@NamedQueries(
{
    @NamedQuery(name = "FrtTransporteTipoAcidente.findAll", query = "SELECT f FROM FrtTransporteTipoAcidente f"),
    @NamedQuery(name = "FrtTransporteTipoAcidente.findByPkTipoAcidente", query = "SELECT f FROM FrtTransporteTipoAcidente f WHERE f.pkTipoAcidente = :pkTipoAcidente"),
    @NamedQuery(name = "FrtTransporteTipoAcidente.findByDescricao", query = "SELECT f FROM FrtTransporteTipoAcidente f WHERE f.descricao = :descricao")
})
public class FrtTransporteTipoAcidente implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_tipo_acidente", nullable = false)
    private Integer pkTipoAcidente;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(nullable = false, length = 2147483647)
    private String descricao;
    @OneToMany(mappedBy = "fkTipoAcidente")
    private List<FrtTransporteAcidente> frtTransporteAcidenteList;
    @OneToMany(mappedBy = "fkTipoAcidente")
    private List<FrtTransporteConfiguracoes> frtTransporteConfiguracoesList;

    public FrtTransporteTipoAcidente()
    {
    }

    public FrtTransporteTipoAcidente(Integer pkTipoAcidente)
    {
        this.pkTipoAcidente = pkTipoAcidente;
    }

    public FrtTransporteTipoAcidente(Integer pkTipoAcidente, String descricao)
    {
        this.pkTipoAcidente = pkTipoAcidente;
        this.descricao = descricao;
    }

    public Integer getPkTipoAcidente()
    {
        return pkTipoAcidente;
    }

    public void setPkTipoAcidente(Integer pkTipoAcidente)
    {
        this.pkTipoAcidente = pkTipoAcidente;
    }

    public String getDescricao()
    {
        return descricao;
    }

    public void setDescricao(String descricao)
    {
        this.descricao = descricao;
    }

    public List<FrtTransporteAcidente> getFrtTransporteAcidenteList()
    {
        return frtTransporteAcidenteList;
    }

    public void setFrtTransporteAcidenteList(List<FrtTransporteAcidente> frtTransporteAcidenteList)
    {
        this.frtTransporteAcidenteList = frtTransporteAcidenteList;
    }

    public List<FrtTransporteConfiguracoes> getFrtTransporteConfiguracoesList()
    {
        return frtTransporteConfiguracoesList;
    }

    public void setFrtTransporteConfiguracoesList(List<FrtTransporteConfiguracoes> frtTransporteConfiguracoesList)
    {
        this.frtTransporteConfiguracoesList = frtTransporteConfiguracoesList;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (pkTipoAcidente != null ? pkTipoAcidente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FrtTransporteTipoAcidente))
        {
            return false;
        }
        FrtTransporteTipoAcidente other = (FrtTransporteTipoAcidente) object;
        if ((this.pkTipoAcidente == null && other.pkTipoAcidente != null) || (this.pkTipoAcidente != null && !this.pkTipoAcidente.equals(other.pkTipoAcidente)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.FrtTransporteTipoAcidente[ pkTipoAcidente=" + pkTipoAcidente + " ]";
    }
    
}
