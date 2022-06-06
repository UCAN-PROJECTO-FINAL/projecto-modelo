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
@Table(name = "bbt_campo", catalog = "sig_ucan_db", schema = "public")
@NamedQueries(
{
    @NamedQuery(name = "BbtCampo.findAll", query = "SELECT b FROM BbtCampo b"),
    @NamedQuery(name = "BbtCampo.findByPkBbtCampo", query = "SELECT b FROM BbtCampo b WHERE b.pkBbtCampo = :pkBbtCampo"),
    @NamedQuery(name = "BbtCampo.findByDesignacao", query = "SELECT b FROM BbtCampo b WHERE b.designacao = :designacao"),
    @NamedQuery(name = "BbtCampo.findByIsRendered", query = "SELECT b FROM BbtCampo b WHERE b.isRendered = :isRendered")
})
public class BbtCampo implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "pk_bbt_campo", nullable = false, length = 2147483647)
    private String pkBbtCampo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(nullable = false, length = 2147483647)
    private String designacao;
    @Basic(optional = false)
    @NotNull
    @Column(name = "is_rendered", nullable = false)
    private boolean isRendered;
    @OneToMany(mappedBy = "fkBbtCampo")
    private List<BbtCampo> bbtCampoList;
    @JoinColumn(name = "fk_bbt_campo", referencedColumnName = "pk_bbt_campo")
    @ManyToOne
    private BbtCampo fkBbtCampo;

    public BbtCampo()
    {
    }

    public BbtCampo(String pkBbtCampo)
    {
        this.pkBbtCampo = pkBbtCampo;
    }

    public BbtCampo(String pkBbtCampo, String designacao, boolean isRendered)
    {
        this.pkBbtCampo = pkBbtCampo;
        this.designacao = designacao;
        this.isRendered = isRendered;
    }

    public String getPkBbtCampo()
    {
        return pkBbtCampo;
    }

    public void setPkBbtCampo(String pkBbtCampo)
    {
        this.pkBbtCampo = pkBbtCampo;
    }

    public String getDesignacao()
    {
        return designacao;
    }

    public void setDesignacao(String designacao)
    {
        this.designacao = designacao;
    }

    public boolean getIsRendered()
    {
        return isRendered;
    }

    public void setIsRendered(boolean isRendered)
    {
        this.isRendered = isRendered;
    }

    public List<BbtCampo> getBbtCampoList()
    {
        return bbtCampoList;
    }

    public void setBbtCampoList(List<BbtCampo> bbtCampoList)
    {
        this.bbtCampoList = bbtCampoList;
    }

    public BbtCampo getFkBbtCampo()
    {
        return fkBbtCampo;
    }

    public void setFkBbtCampo(BbtCampo fkBbtCampo)
    {
        this.fkBbtCampo = fkBbtCampo;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (pkBbtCampo != null ? pkBbtCampo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BbtCampo))
        {
            return false;
        }
        BbtCampo other = (BbtCampo) object;
        if ((this.pkBbtCampo == null && other.pkBbtCampo != null) || (this.pkBbtCampo != null && !this.pkBbtCampo.equals(other.pkBbtCampo)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.BbtCampo[ pkBbtCampo=" + pkBbtCampo + " ]";
    }
    
}
