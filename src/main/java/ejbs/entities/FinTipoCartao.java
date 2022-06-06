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

/**
 *
 * @author smakambo
 */
@Entity
@Table(name = "fin_tipo_cartao", catalog = "sig_ucan_db", schema = "public")
@NamedQueries(
{
    @NamedQuery(name = "FinTipoCartao.findAll", query = "SELECT f FROM FinTipoCartao f"),
    @NamedQuery(name = "FinTipoCartao.findByPkTipoCartao", query = "SELECT f FROM FinTipoCartao f WHERE f.pkTipoCartao = :pkTipoCartao"),
    @NamedQuery(name = "FinTipoCartao.findByDescricao", query = "SELECT f FROM FinTipoCartao f WHERE f.descricao = :descricao")
})
public class FinTipoCartao implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "pk_tipo_cartao", nullable = false)
    private Integer pkTipoCartao;
    @Size(max = 100)
    @Column(length = 100)
    private String descricao;
    @OneToMany(mappedBy = "fkTipoCartao")
    private List<FinConta> finContaList;

    public FinTipoCartao()
    {
    }

    public FinTipoCartao(Integer pkTipoCartao)
    {
        this.pkTipoCartao = pkTipoCartao;
    }

    public Integer getPkTipoCartao()
    {
        return pkTipoCartao;
    }

    public void setPkTipoCartao(Integer pkTipoCartao)
    {
        this.pkTipoCartao = pkTipoCartao;
    }

    public String getDescricao()
    {
        return descricao;
    }

    public void setDescricao(String descricao)
    {
        this.descricao = descricao;
    }

    public List<FinConta> getFinContaList()
    {
        return finContaList;
    }

    public void setFinContaList(List<FinConta> finContaList)
    {
        this.finContaList = finContaList;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (pkTipoCartao != null ? pkTipoCartao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FinTipoCartao))
        {
            return false;
        }
        FinTipoCartao other = (FinTipoCartao) object;
        if ((this.pkTipoCartao == null && other.pkTipoCartao != null) || (this.pkTipoCartao != null && !this.pkTipoCartao.equals(other.pkTipoCartao)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.FinTipoCartao[ pkTipoCartao=" + pkTipoCartao + " ]";
    }
    
}
