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
@Table(name = "bbt_tipo_autoridade", catalog = "sig_ucan_db", schema = "public")
@NamedQueries(
{
    @NamedQuery(name = "BbtTipoAutoridade.findAll", query = "SELECT b FROM BbtTipoAutoridade b"),
    @NamedQuery(name = "BbtTipoAutoridade.findByPkBbtTipoAutoridade", query = "SELECT b FROM BbtTipoAutoridade b WHERE b.pkBbtTipoAutoridade = :pkBbtTipoAutoridade"),
    @NamedQuery(name = "BbtTipoAutoridade.findByDesignacao", query = "SELECT b FROM BbtTipoAutoridade b WHERE b.designacao = :designacao")
})
public class BbtTipoAutoridade implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_bbt_tipo_autoridade", nullable = false)
    private Integer pkBbtTipoAutoridade;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(nullable = false, length = 2147483647)
    private String designacao;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkBbtTipoAutoridade")
    private List<BbtAutoridade> bbtAutoridadeList;

    public BbtTipoAutoridade()
    {
    }

    public BbtTipoAutoridade(Integer pkBbtTipoAutoridade)
    {
        this.pkBbtTipoAutoridade = pkBbtTipoAutoridade;
    }

    public BbtTipoAutoridade(Integer pkBbtTipoAutoridade, String designacao)
    {
        this.pkBbtTipoAutoridade = pkBbtTipoAutoridade;
        this.designacao = designacao;
    }

    public Integer getPkBbtTipoAutoridade()
    {
        return pkBbtTipoAutoridade;
    }

    public void setPkBbtTipoAutoridade(Integer pkBbtTipoAutoridade)
    {
        this.pkBbtTipoAutoridade = pkBbtTipoAutoridade;
    }

    public String getDesignacao()
    {
        return designacao;
    }

    public void setDesignacao(String designacao)
    {
        this.designacao = designacao;
    }

    public List<BbtAutoridade> getBbtAutoridadeList()
    {
        return bbtAutoridadeList;
    }

    public void setBbtAutoridadeList(List<BbtAutoridade> bbtAutoridadeList)
    {
        this.bbtAutoridadeList = bbtAutoridadeList;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (pkBbtTipoAutoridade != null ? pkBbtTipoAutoridade.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BbtTipoAutoridade))
        {
            return false;
        }
        BbtTipoAutoridade other = (BbtTipoAutoridade) object;
        if ((this.pkBbtTipoAutoridade == null && other.pkBbtTipoAutoridade != null) || (this.pkBbtTipoAutoridade != null && !this.pkBbtTipoAutoridade.equals(other.pkBbtTipoAutoridade)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.BbtTipoAutoridade[ pkBbtTipoAutoridade=" + pkBbtTipoAutoridade + " ]";
    }
    
}
