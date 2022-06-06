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
@Table(name = "grl_tipo_solicitacao", catalog = "sig_ucan_db", schema = "public")
@NamedQueries(
{
    @NamedQuery(name = "GrlTipoSolicitacao.findAll", query = "SELECT g FROM GrlTipoSolicitacao g"),
    @NamedQuery(name = "GrlTipoSolicitacao.findByPkTipoSolicitacao", query = "SELECT g FROM GrlTipoSolicitacao g WHERE g.pkTipoSolicitacao = :pkTipoSolicitacao"),
    @NamedQuery(name = "GrlTipoSolicitacao.findByDescricao", query = "SELECT g FROM GrlTipoSolicitacao g WHERE g.descricao = :descricao")
})
public class GrlTipoSolicitacao implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_tipo_solicitacao", nullable = false)
    private Integer pkTipoSolicitacao;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(nullable = false, length = 50)
    private String descricao;
    @OneToMany(mappedBy = "fkGrlTipoSolicitacao")
    private List<FrtTransporteConfiguracoes> frtTransporteConfiguracoesList;
    @OneToMany(mappedBy = "fkTipoSolicitacao")
    private List<FrtTransporteSolicitacao> frtTransporteSolicitacaoList;

    public GrlTipoSolicitacao()
    {
    }

    public GrlTipoSolicitacao(Integer pkTipoSolicitacao)
    {
        this.pkTipoSolicitacao = pkTipoSolicitacao;
    }

    public GrlTipoSolicitacao(Integer pkTipoSolicitacao, String descricao)
    {
        this.pkTipoSolicitacao = pkTipoSolicitacao;
        this.descricao = descricao;
    }

    public Integer getPkTipoSolicitacao()
    {
        return pkTipoSolicitacao;
    }

    public void setPkTipoSolicitacao(Integer pkTipoSolicitacao)
    {
        this.pkTipoSolicitacao = pkTipoSolicitacao;
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

    public List<FrtTransporteSolicitacao> getFrtTransporteSolicitacaoList()
    {
        return frtTransporteSolicitacaoList;
    }

    public void setFrtTransporteSolicitacaoList(List<FrtTransporteSolicitacao> frtTransporteSolicitacaoList)
    {
        this.frtTransporteSolicitacaoList = frtTransporteSolicitacaoList;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (pkTipoSolicitacao != null ? pkTipoSolicitacao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GrlTipoSolicitacao))
        {
            return false;
        }
        GrlTipoSolicitacao other = (GrlTipoSolicitacao) object;
        if ((this.pkTipoSolicitacao == null && other.pkTipoSolicitacao != null) || (this.pkTipoSolicitacao != null && !this.pkTipoSolicitacao.equals(other.pkTipoSolicitacao)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.GrlTipoSolicitacao[ pkTipoSolicitacao=" + pkTipoSolicitacao + " ]";
    }
    
}
