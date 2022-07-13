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
@Table(name = "bbt_edicao", catalog = "sig_ucan_db", schema = "public")
@NamedQueries(
{
    @NamedQuery(name = "BbtEdicao.findAll", query = "SELECT b FROM BbtEdicao b"),
    @NamedQuery(name = "BbtEdicao.findByPkBbtEdicao", query = "SELECT b FROM BbtEdicao b WHERE b.pkBbtEdicao = :pkBbtEdicao"),
    @NamedQuery(name = "BbtEdicao.findByDesignacao", query = "SELECT b FROM BbtEdicao b WHERE b.designacao = :designacao")
})
public class BbtEdicao implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_bbt_edicao", nullable = false)
    private Integer pkBbtEdicao;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(nullable = false, length = 2147483647)
    private String designacao;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkBbtEdicao")
    private List<BbtDocumento> bbtDocumentoList;

    public BbtEdicao()
    {
    }

    public BbtEdicao(Integer pkBbtEdicao)
    {
        this.pkBbtEdicao = pkBbtEdicao;
    }

    public BbtEdicao(Integer pkBbtEdicao, String designacao)
    {
        this.pkBbtEdicao = pkBbtEdicao;
        this.designacao = designacao;
    }

    public Integer getPkBbtEdicao()
    {
        return pkBbtEdicao;
    }

    public void setPkBbtEdicao(Integer pkBbtEdicao)
    {
        this.pkBbtEdicao = pkBbtEdicao;
    }

    public String getDesignacao()
    {
        return designacao;
    }

    public void setDesignacao(String designacao)
    {
        this.designacao = designacao;
    }

    public List<BbtDocumento> getBbtDocumentoList()
    {
        return bbtDocumentoList;
    }

    public void setBbtDocumentoList(List<BbtDocumento> bbtDocumentoList)
    {
        this.bbtDocumentoList = bbtDocumentoList;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (pkBbtEdicao != null ? pkBbtEdicao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BbtEdicao))
        {
            return false;
        }
        BbtEdicao other = (BbtEdicao) object;
        if ((this.pkBbtEdicao == null && other.pkBbtEdicao != null) || (this.pkBbtEdicao != null && !this.pkBbtEdicao.equals(other.pkBbtEdicao)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.BbtEdicao[ pkBbtEdicao=" + pkBbtEdicao + " ]";
    }
    
}
