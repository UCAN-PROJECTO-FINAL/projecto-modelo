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
@Table(name = "frt_transporte_tipo_manutencao", catalog = "sig_ucan_db", schema = "public")
@NamedQueries(
{
    @NamedQuery(name = "FrtTransporteTipoManutencao.findAll", query = "SELECT f FROM FrtTransporteTipoManutencao f"),
    @NamedQuery(name = "FrtTransporteTipoManutencao.findByPkTipoManutencao", query = "SELECT f FROM FrtTransporteTipoManutencao f WHERE f.pkTipoManutencao = :pkTipoManutencao"),
    @NamedQuery(name = "FrtTransporteTipoManutencao.findByDescricao", query = "SELECT f FROM FrtTransporteTipoManutencao f WHERE f.descricao = :descricao")
})
public class FrtTransporteTipoManutencao implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_tipo_manutencao", nullable = false)
    private Integer pkTipoManutencao;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(nullable = false, length = 2147483647)
    private String descricao;
    @OneToMany(mappedBy = "fkTipoManutencao")
    private List<FrtTransporteManutencao> frtTransporteManutencaoList;
    @OneToMany(mappedBy = "fkTipoManutencao")
    private List<FrtTransporteConfiguracoes> frtTransporteConfiguracoesList;

    public FrtTransporteTipoManutencao()
    {
    }

    public FrtTransporteTipoManutencao(Integer pkTipoManutencao)
    {
        this.pkTipoManutencao = pkTipoManutencao;
    }

    public FrtTransporteTipoManutencao(Integer pkTipoManutencao, String descricao)
    {
        this.pkTipoManutencao = pkTipoManutencao;
        this.descricao = descricao;
    }

    public Integer getPkTipoManutencao()
    {
        return pkTipoManutencao;
    }

    public void setPkTipoManutencao(Integer pkTipoManutencao)
    {
        this.pkTipoManutencao = pkTipoManutencao;
    }

    public String getDescricao()
    {
        return descricao;
    }

    public void setDescricao(String descricao)
    {
        this.descricao = descricao;
    }

    public List<FrtTransporteManutencao> getFrtTransporteManutencaoList()
    {
        return frtTransporteManutencaoList;
    }

    public void setFrtTransporteManutencaoList(List<FrtTransporteManutencao> frtTransporteManutencaoList)
    {
        this.frtTransporteManutencaoList = frtTransporteManutencaoList;
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
        hash += (pkTipoManutencao != null ? pkTipoManutencao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FrtTransporteTipoManutencao))
        {
            return false;
        }
        FrtTransporteTipoManutencao other = (FrtTransporteTipoManutencao) object;
        if ((this.pkTipoManutencao == null && other.pkTipoManutencao != null) || (this.pkTipoManutencao != null && !this.pkTipoManutencao.equals(other.pkTipoManutencao)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.FrtTransporteTipoManutencao[ pkTipoManutencao=" + pkTipoManutencao + " ]";
    }
    
}
