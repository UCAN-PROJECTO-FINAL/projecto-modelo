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
@Table(name = "frt_transporte_tipo_iluminacao", catalog = "sig_ucan_db", schema = "public")
@NamedQueries(
{
    @NamedQuery(name = "FrtTransporteTipoIluminacao.findAll", query = "SELECT f FROM FrtTransporteTipoIluminacao f"),
    @NamedQuery(name = "FrtTransporteTipoIluminacao.findByPkTipoIluminacao", query = "SELECT f FROM FrtTransporteTipoIluminacao f WHERE f.pkTipoIluminacao = :pkTipoIluminacao"),
    @NamedQuery(name = "FrtTransporteTipoIluminacao.findByDescricao", query = "SELECT f FROM FrtTransporteTipoIluminacao f WHERE f.descricao = :descricao")
})
public class FrtTransporteTipoIluminacao implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_tipo_iluminacao", nullable = false)
    private Integer pkTipoIluminacao;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(nullable = false, length = 2147483647)
    private String descricao;
    @OneToMany(mappedBy = "fkTipoIluminacao")
    private List<FrtTransporteAcidente> frtTransporteAcidenteList;
    @OneToMany(mappedBy = "fkTipoIluminacao")
    private List<FrtTransporteConfiguracoes> frtTransporteConfiguracoesList;

    public FrtTransporteTipoIluminacao()
    {
    }

    public FrtTransporteTipoIluminacao(Integer pkTipoIluminacao)
    {
        this.pkTipoIluminacao = pkTipoIluminacao;
    }

    public FrtTransporteTipoIluminacao(Integer pkTipoIluminacao, String descricao)
    {
        this.pkTipoIluminacao = pkTipoIluminacao;
        this.descricao = descricao;
    }

    public Integer getPkTipoIluminacao()
    {
        return pkTipoIluminacao;
    }

    public void setPkTipoIluminacao(Integer pkTipoIluminacao)
    {
        this.pkTipoIluminacao = pkTipoIluminacao;
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
        hash += (pkTipoIluminacao != null ? pkTipoIluminacao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FrtTransporteTipoIluminacao))
        {
            return false;
        }
        FrtTransporteTipoIluminacao other = (FrtTransporteTipoIluminacao) object;
        if ((this.pkTipoIluminacao == null && other.pkTipoIluminacao != null) || (this.pkTipoIluminacao != null && !this.pkTipoIluminacao.equals(other.pkTipoIluminacao)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.FrtTransporteTipoIluminacao[ pkTipoIluminacao=" + pkTipoIluminacao + " ]";
    }
    
}
