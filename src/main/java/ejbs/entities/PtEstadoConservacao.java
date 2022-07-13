/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
@Table(name = "pt_estado_conservacao", catalog = "sig_ucan_db", schema = "public")
@NamedQueries(
{
    @NamedQuery(name = "PtEstadoConservacao.findAll", query = "SELECT p FROM PtEstadoConservacao p"),
    @NamedQuery(name = "PtEstadoConservacao.findByPkPtEstadoConservacao", query = "SELECT p FROM PtEstadoConservacao p WHERE p.pkPtEstadoConservacao = :pkPtEstadoConservacao"),
    @NamedQuery(name = "PtEstadoConservacao.findByDescricao", query = "SELECT p FROM PtEstadoConservacao p WHERE p.descricao = :descricao")
})
public class PtEstadoConservacao implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_pt_estado_conservacao", nullable = false)
    private Integer pkPtEstadoConservacao;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(nullable = false, length = 2147483647)
    private String descricao;
    @OneToMany(mappedBy = "fkPtEstadoConservacao")
    private List<PtBemImovel> ptBemImovelList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkPtEstadoConservacao")
    private List<PtBemMovel> ptBemMovelList;

    public PtEstadoConservacao()
    {
    }

    public PtEstadoConservacao(Integer pkPtEstadoConservacao)
    {
        this.pkPtEstadoConservacao = pkPtEstadoConservacao;
    }

    public PtEstadoConservacao(Integer pkPtEstadoConservacao, String descricao)
    {
        this.pkPtEstadoConservacao = pkPtEstadoConservacao;
        this.descricao = descricao;
    }

    public Integer getPkPtEstadoConservacao()
    {
        return pkPtEstadoConservacao;
    }

    public void setPkPtEstadoConservacao(Integer pkPtEstadoConservacao)
    {
        this.pkPtEstadoConservacao = pkPtEstadoConservacao;
    }

    public String getDescricao()
    {
        return descricao;
    }

    public void setDescricao(String descricao)
    {
        this.descricao = descricao;
    }

    public List<PtBemImovel> getPtBemImovelList()
    {
        return ptBemImovelList;
    }

    public void setPtBemImovelList(List<PtBemImovel> ptBemImovelList)
    {
        this.ptBemImovelList = ptBemImovelList;
    }

    public List<PtBemMovel> getPtBemMovelList()
    {
        return ptBemMovelList;
    }

    public void setPtBemMovelList(List<PtBemMovel> ptBemMovelList)
    {
        this.ptBemMovelList = ptBemMovelList;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (pkPtEstadoConservacao != null ? pkPtEstadoConservacao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PtEstadoConservacao))
        {
            return false;
        }
        PtEstadoConservacao other = (PtEstadoConservacao) object;
        if ((this.pkPtEstadoConservacao == null && other.pkPtEstadoConservacao != null) || (this.pkPtEstadoConservacao != null && !this.pkPtEstadoConservacao.equals(other.pkPtEstadoConservacao)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.PtEstadoConservacao[ pkPtEstadoConservacao=" + pkPtEstadoConservacao + " ]";
    }
    
}
