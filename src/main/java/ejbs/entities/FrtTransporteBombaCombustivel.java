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
@Table(name = "frt_transporte_bomba_combustivel", catalog = "sig_ucan_db", schema = "public")
@NamedQueries(
{
    @NamedQuery(name = "FrtTransporteBombaCombustivel.findAll", query = "SELECT f FROM FrtTransporteBombaCombustivel f"),
    @NamedQuery(name = "FrtTransporteBombaCombustivel.findByPkBombaCombustivel", query = "SELECT f FROM FrtTransporteBombaCombustivel f WHERE f.pkBombaCombustivel = :pkBombaCombustivel"),
    @NamedQuery(name = "FrtTransporteBombaCombustivel.findByDescricao", query = "SELECT f FROM FrtTransporteBombaCombustivel f WHERE f.descricao = :descricao")
})
public class FrtTransporteBombaCombustivel implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_bomba_combustivel", nullable = false)
    private Integer pkBombaCombustivel;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(nullable = false, length = 2147483647)
    private String descricao;
    @OneToMany(mappedBy = "fkBombasCombustivel")
    private List<FrtTransporteConfiguracoes> frtTransporteConfiguracoesList;
    @OneToMany(mappedBy = "fkBombasCombustivel")
    private List<FrtTransporteAbastecimento> frtTransporteAbastecimentoList;

    public FrtTransporteBombaCombustivel()
    {
    }

    public FrtTransporteBombaCombustivel(Integer pkBombaCombustivel)
    {
        this.pkBombaCombustivel = pkBombaCombustivel;
    }

    public FrtTransporteBombaCombustivel(Integer pkBombaCombustivel, String descricao)
    {
        this.pkBombaCombustivel = pkBombaCombustivel;
        this.descricao = descricao;
    }

    public Integer getPkBombaCombustivel()
    {
        return pkBombaCombustivel;
    }

    public void setPkBombaCombustivel(Integer pkBombaCombustivel)
    {
        this.pkBombaCombustivel = pkBombaCombustivel;
    }

    public String getDescricao()
    {
        return descricao;
    }

    public void setDescricao(String descricao)
    {
        this.descricao = descricao;
    }

    public List<FrtTransporteConfiguracoes> getFrtTransporteConfiguracoesList()
    {
        return frtTransporteConfiguracoesList;
    }

    public void setFrtTransporteConfiguracoesList(List<FrtTransporteConfiguracoes> frtTransporteConfiguracoesList)
    {
        this.frtTransporteConfiguracoesList = frtTransporteConfiguracoesList;
    }

    public List<FrtTransporteAbastecimento> getFrtTransporteAbastecimentoList()
    {
        return frtTransporteAbastecimentoList;
    }

    public void setFrtTransporteAbastecimentoList(List<FrtTransporteAbastecimento> frtTransporteAbastecimentoList)
    {
        this.frtTransporteAbastecimentoList = frtTransporteAbastecimentoList;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (pkBombaCombustivel != null ? pkBombaCombustivel.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FrtTransporteBombaCombustivel))
        {
            return false;
        }
        FrtTransporteBombaCombustivel other = (FrtTransporteBombaCombustivel) object;
        if ((this.pkBombaCombustivel == null && other.pkBombaCombustivel != null) || (this.pkBombaCombustivel != null && !this.pkBombaCombustivel.equals(other.pkBombaCombustivel)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.FrtTransporteBombaCombustivel[ pkBombaCombustivel=" + pkBombaCombustivel + " ]";
    }
    
}
