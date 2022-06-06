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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "bbt_autoridade", catalog = "sig_ucan_db", schema = "public")
@NamedQueries(
{
    @NamedQuery(name = "BbtAutoridade.findAll", query = "SELECT b FROM BbtAutoridade b"),
    @NamedQuery(name = "BbtAutoridade.findByPkBbtAutoridade", query = "SELECT b FROM BbtAutoridade b WHERE b.pkBbtAutoridade = :pkBbtAutoridade"),
    @NamedQuery(name = "BbtAutoridade.findByDesignacao", query = "SELECT b FROM BbtAutoridade b WHERE b.designacao = :designacao")
})
public class BbtAutoridade implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_bbt_autoridade", nullable = false)
    private Integer pkBbtAutoridade;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(nullable = false, length = 2147483647)
    private String designacao;
    @JoinColumn(name = "fk_bbt_tipo_autoridade", referencedColumnName = "pk_bbt_tipo_autoridade", nullable = false)
    @ManyToOne(optional = false)
    private BbtTipoAutoridade fkBbtTipoAutoridade;
    @JoinColumn(name = "fk_loc_localidade", referencedColumnName = "pk_loc_localidade", nullable = false)
    @ManyToOne(optional = false)
    private LocLocalidade fkLocLocalidade;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkBbtAutoridade")
    private List<BbtDocumentoBbtAutoridade> bbtDocumentoBbtAutoridadeList;

    public BbtAutoridade()
    {
    }

    public BbtAutoridade(Integer pkBbtAutoridade)
    {
        this.pkBbtAutoridade = pkBbtAutoridade;
    }

    public BbtAutoridade(Integer pkBbtAutoridade, String designacao)
    {
        this.pkBbtAutoridade = pkBbtAutoridade;
        this.designacao = designacao;
    }

    public Integer getPkBbtAutoridade()
    {
        return pkBbtAutoridade;
    }

    public void setPkBbtAutoridade(Integer pkBbtAutoridade)
    {
        this.pkBbtAutoridade = pkBbtAutoridade;
    }

    public String getDesignacao()
    {
        return designacao;
    }

    public void setDesignacao(String designacao)
    {
        this.designacao = designacao;
    }

    public BbtTipoAutoridade getFkBbtTipoAutoridade()
    {
        return fkBbtTipoAutoridade;
    }

    public void setFkBbtTipoAutoridade(BbtTipoAutoridade fkBbtTipoAutoridade)
    {
        this.fkBbtTipoAutoridade = fkBbtTipoAutoridade;
    }

    public LocLocalidade getFkLocLocalidade()
    {
        return fkLocLocalidade;
    }

    public void setFkLocLocalidade(LocLocalidade fkLocLocalidade)
    {
        this.fkLocLocalidade = fkLocLocalidade;
    }

    public List<BbtDocumentoBbtAutoridade> getBbtDocumentoBbtAutoridadeList()
    {
        return bbtDocumentoBbtAutoridadeList;
    }

    public void setBbtDocumentoBbtAutoridadeList(List<BbtDocumentoBbtAutoridade> bbtDocumentoBbtAutoridadeList)
    {
        this.bbtDocumentoBbtAutoridadeList = bbtDocumentoBbtAutoridadeList;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (pkBbtAutoridade != null ? pkBbtAutoridade.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BbtAutoridade))
        {
            return false;
        }
        BbtAutoridade other = (BbtAutoridade) object;
        if ((this.pkBbtAutoridade == null && other.pkBbtAutoridade != null) || (this.pkBbtAutoridade != null && !this.pkBbtAutoridade.equals(other.pkBbtAutoridade)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.BbtAutoridade[ pkBbtAutoridade=" + pkBbtAutoridade + " ]";
    }
    
}
