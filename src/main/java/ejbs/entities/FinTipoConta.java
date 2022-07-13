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
@Table(name = "fin_tipo_conta", catalog = "sig_ucan_db", schema = "public")
@NamedQueries(
{
    @NamedQuery(name = "FinTipoConta.findAll", query = "SELECT f FROM FinTipoConta f"),
    @NamedQuery(name = "FinTipoConta.findByPkTipoConta", query = "SELECT f FROM FinTipoConta f WHERE f.pkTipoConta = :pkTipoConta"),
    @NamedQuery(name = "FinTipoConta.findByDescricao", query = "SELECT f FROM FinTipoConta f WHERE f.descricao = :descricao")
})
public class FinTipoConta implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "pk_tipo_conta", nullable = false)
    private Integer pkTipoConta;
    @Size(max = 2147483647)
    @Column(length = 2147483647)
    private String descricao;
    @OneToMany(mappedBy = "fkTipoConta")
    private List<FinConta> finContaList;

    public FinTipoConta()
    {
    }

    public FinTipoConta(Integer pkTipoConta)
    {
        this.pkTipoConta = pkTipoConta;
    }

    public Integer getPkTipoConta()
    {
        return pkTipoConta;
    }

    public void setPkTipoConta(Integer pkTipoConta)
    {
        this.pkTipoConta = pkTipoConta;
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
        hash += (pkTipoConta != null ? pkTipoConta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FinTipoConta))
        {
            return false;
        }
        FinTipoConta other = (FinTipoConta) object;
        if ((this.pkTipoConta == null && other.pkTipoConta != null) || (this.pkTipoConta != null && !this.pkTipoConta.equals(other.pkTipoConta)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.FinTipoConta[ pkTipoConta=" + pkTipoConta + " ]";
    }
    
}
