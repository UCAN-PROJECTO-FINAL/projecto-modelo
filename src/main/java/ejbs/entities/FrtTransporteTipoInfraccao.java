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
@Table(name = "frt_transporte_tipo_infraccao", catalog = "sig_ucan_db", schema = "public")
@NamedQueries(
{
    @NamedQuery(name = "FrtTransporteTipoInfraccao.findAll", query = "SELECT f FROM FrtTransporteTipoInfraccao f"),
    @NamedQuery(name = "FrtTransporteTipoInfraccao.findByPkTipoInfracao", query = "SELECT f FROM FrtTransporteTipoInfraccao f WHERE f.pkTipoInfracao = :pkTipoInfracao"),
    @NamedQuery(name = "FrtTransporteTipoInfraccao.findByDescricao", query = "SELECT f FROM FrtTransporteTipoInfraccao f WHERE f.descricao = :descricao")
})
public class FrtTransporteTipoInfraccao implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_tipo_infracao", nullable = false)
    private Integer pkTipoInfracao;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(nullable = false, length = 2147483647)
    private String descricao;
    @OneToMany(mappedBy = "fkTipoInfraccao")
    private List<FrtTransporteMultas> frtTransporteMultasList;
    @OneToMany(mappedBy = "fkTipoInfracao")
    private List<FrtTransporteConfiguracoes> frtTransporteConfiguracoesList;

    public FrtTransporteTipoInfraccao()
    {
    }

    public FrtTransporteTipoInfraccao(Integer pkTipoInfracao)
    {
        this.pkTipoInfracao = pkTipoInfracao;
    }

    public FrtTransporteTipoInfraccao(Integer pkTipoInfracao, String descricao)
    {
        this.pkTipoInfracao = pkTipoInfracao;
        this.descricao = descricao;
    }

    public Integer getPkTipoInfracao()
    {
        return pkTipoInfracao;
    }

    public void setPkTipoInfracao(Integer pkTipoInfracao)
    {
        this.pkTipoInfracao = pkTipoInfracao;
    }

    public String getDescricao()
    {
        return descricao;
    }

    public void setDescricao(String descricao)
    {
        this.descricao = descricao;
    }

    public List<FrtTransporteMultas> getFrtTransporteMultasList()
    {
        return frtTransporteMultasList;
    }

    public void setFrtTransporteMultasList(List<FrtTransporteMultas> frtTransporteMultasList)
    {
        this.frtTransporteMultasList = frtTransporteMultasList;
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
        hash += (pkTipoInfracao != null ? pkTipoInfracao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FrtTransporteTipoInfraccao))
        {
            return false;
        }
        FrtTransporteTipoInfraccao other = (FrtTransporteTipoInfraccao) object;
        if ((this.pkTipoInfracao == null && other.pkTipoInfracao != null) || (this.pkTipoInfracao != null && !this.pkTipoInfracao.equals(other.pkTipoInfracao)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.FrtTransporteTipoInfraccao[ pkTipoInfracao=" + pkTipoInfracao + " ]";
    }
    
}
