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
@Table(name = "frt_transporte_solicitacao_estado", catalog = "sig_ucan_db", schema = "public")
@NamedQueries(
{
    @NamedQuery(name = "FrtTransporteSolicitacaoEstado.findAll", query = "SELECT f FROM FrtTransporteSolicitacaoEstado f"),
    @NamedQuery(name = "FrtTransporteSolicitacaoEstado.findByPkTransporteSolicitacaoEstado", query = "SELECT f FROM FrtTransporteSolicitacaoEstado f WHERE f.pkTransporteSolicitacaoEstado = :pkTransporteSolicitacaoEstado"),
    @NamedQuery(name = "FrtTransporteSolicitacaoEstado.findByDescricao", query = "SELECT f FROM FrtTransporteSolicitacaoEstado f WHERE f.descricao = :descricao")
})
public class FrtTransporteSolicitacaoEstado implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_transporte_solicitacao_estado", nullable = false)
    private Integer pkTransporteSolicitacaoEstado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(nullable = false, length = 2147483647)
    private String descricao;
    @OneToMany(mappedBy = "fkTransporteAtendimentEstado")
    private List<FrtTransporteAtendimento> frtTransporteAtendimentoList;
    @OneToMany(mappedBy = "fkEstadoSolicitacao")
    private List<FrtTransporteConfiguracoes> frtTransporteConfiguracoesList;
    @OneToMany(mappedBy = "fkTransporteSolicitacaoEstado")
    private List<FrtTransporteSolicitacao> frtTransporteSolicitacaoList;

    public FrtTransporteSolicitacaoEstado()
    {
    }

    public FrtTransporteSolicitacaoEstado(Integer pkTransporteSolicitacaoEstado)
    {
        this.pkTransporteSolicitacaoEstado = pkTransporteSolicitacaoEstado;
    }

    public FrtTransporteSolicitacaoEstado(Integer pkTransporteSolicitacaoEstado, String descricao)
    {
        this.pkTransporteSolicitacaoEstado = pkTransporteSolicitacaoEstado;
        this.descricao = descricao;
    }

    public Integer getPkTransporteSolicitacaoEstado()
    {
        return pkTransporteSolicitacaoEstado;
    }

    public void setPkTransporteSolicitacaoEstado(Integer pkTransporteSolicitacaoEstado)
    {
        this.pkTransporteSolicitacaoEstado = pkTransporteSolicitacaoEstado;
    }

    public String getDescricao()
    {
        return descricao;
    }

    public void setDescricao(String descricao)
    {
        this.descricao = descricao;
    }

    public List<FrtTransporteAtendimento> getFrtTransporteAtendimentoList()
    {
        return frtTransporteAtendimentoList;
    }

    public void setFrtTransporteAtendimentoList(List<FrtTransporteAtendimento> frtTransporteAtendimentoList)
    {
        this.frtTransporteAtendimentoList = frtTransporteAtendimentoList;
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
        hash += (pkTransporteSolicitacaoEstado != null ? pkTransporteSolicitacaoEstado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FrtTransporteSolicitacaoEstado))
        {
            return false;
        }
        FrtTransporteSolicitacaoEstado other = (FrtTransporteSolicitacaoEstado) object;
        if ((this.pkTransporteSolicitacaoEstado == null && other.pkTransporteSolicitacaoEstado != null) || (this.pkTransporteSolicitacaoEstado != null && !this.pkTransporteSolicitacaoEstado.equals(other.pkTransporteSolicitacaoEstado)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.FrtTransporteSolicitacaoEstado[ pkTransporteSolicitacaoEstado=" + pkTransporteSolicitacaoEstado + " ]";
    }
    
}
