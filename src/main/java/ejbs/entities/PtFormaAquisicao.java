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
@Table(name = "pt_forma_aquisicao", catalog = "sig_ucan_db", schema = "public")
@NamedQueries(
{
    @NamedQuery(name = "PtFormaAquisicao.findAll", query = "SELECT p FROM PtFormaAquisicao p"),
    @NamedQuery(name = "PtFormaAquisicao.findByPkPtFormaAquisicao", query = "SELECT p FROM PtFormaAquisicao p WHERE p.pkPtFormaAquisicao = :pkPtFormaAquisicao"),
    @NamedQuery(name = "PtFormaAquisicao.findByDescricao", query = "SELECT p FROM PtFormaAquisicao p WHERE p.descricao = :descricao")
})
public class PtFormaAquisicao implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_pt_forma_aquisicao", nullable = false)
    private Integer pkPtFormaAquisicao;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(nullable = false, length = 2147483647)
    private String descricao;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkPtFormaAquisicao")
    private List<PtBemEntrada> ptBemEntradaList;

    public PtFormaAquisicao()
    {
    }

    public PtFormaAquisicao(Integer pkPtFormaAquisicao)
    {
        this.pkPtFormaAquisicao = pkPtFormaAquisicao;
    }

    public PtFormaAquisicao(Integer pkPtFormaAquisicao, String descricao)
    {
        this.pkPtFormaAquisicao = pkPtFormaAquisicao;
        this.descricao = descricao;
    }

    public Integer getPkPtFormaAquisicao()
    {
        return pkPtFormaAquisicao;
    }

    public void setPkPtFormaAquisicao(Integer pkPtFormaAquisicao)
    {
        this.pkPtFormaAquisicao = pkPtFormaAquisicao;
    }

    public String getDescricao()
    {
        return descricao;
    }

    public void setDescricao(String descricao)
    {
        this.descricao = descricao;
    }

    public List<PtBemEntrada> getPtBemEntradaList()
    {
        return ptBemEntradaList;
    }

    public void setPtBemEntradaList(List<PtBemEntrada> ptBemEntradaList)
    {
        this.ptBemEntradaList = ptBemEntradaList;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (pkPtFormaAquisicao != null ? pkPtFormaAquisicao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PtFormaAquisicao))
        {
            return false;
        }
        PtFormaAquisicao other = (PtFormaAquisicao) object;
        if ((this.pkPtFormaAquisicao == null && other.pkPtFormaAquisicao != null) || (this.pkPtFormaAquisicao != null && !this.pkPtFormaAquisicao.equals(other.pkPtFormaAquisicao)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.PtFormaAquisicao[ pkPtFormaAquisicao=" + pkPtFormaAquisicao + " ]";
    }
    
}
