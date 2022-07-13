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
@Table(name = "bbt_colecao", catalog = "sig_ucan_db", schema = "public")
@NamedQueries(
{
    @NamedQuery(name = "BbtColecao.findAll", query = "SELECT b FROM BbtColecao b"),
    @NamedQuery(name = "BbtColecao.findByPkBbtColecao", query = "SELECT b FROM BbtColecao b WHERE b.pkBbtColecao = :pkBbtColecao"),
    @NamedQuery(name = "BbtColecao.findByDesignacao", query = "SELECT b FROM BbtColecao b WHERE b.designacao = :designacao")
})
public class BbtColecao implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_bbt_colecao", nullable = false)
    private Integer pkBbtColecao;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(nullable = false, length = 2147483647)
    private String designacao;
    @OneToMany(mappedBy = "fkBbtColecao")
    private List<BbtDocumento> bbtDocumentoList;

    public BbtColecao()
    {
    }

    public BbtColecao(Integer pkBbtColecao)
    {
        this.pkBbtColecao = pkBbtColecao;
    }

    public BbtColecao(Integer pkBbtColecao, String designacao)
    {
        this.pkBbtColecao = pkBbtColecao;
        this.designacao = designacao;
    }

    public Integer getPkBbtColecao()
    {
        return pkBbtColecao;
    }

    public void setPkBbtColecao(Integer pkBbtColecao)
    {
        this.pkBbtColecao = pkBbtColecao;
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
        hash += (pkBbtColecao != null ? pkBbtColecao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BbtColecao))
        {
            return false;
        }
        BbtColecao other = (BbtColecao) object;
        if ((this.pkBbtColecao == null && other.pkBbtColecao != null) || (this.pkBbtColecao != null && !this.pkBbtColecao.equals(other.pkBbtColecao)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.BbtColecao[ pkBbtColecao=" + pkBbtColecao + " ]";
    }
    
}
