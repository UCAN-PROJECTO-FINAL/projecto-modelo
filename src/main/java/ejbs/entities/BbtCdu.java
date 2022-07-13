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
@Table(name = "bbt_cdu", catalog = "sig_ucan_db", schema = "public")
@NamedQueries(
{
    @NamedQuery(name = "BbtCdu.findAll", query = "SELECT b FROM BbtCdu b"),
    @NamedQuery(name = "BbtCdu.findByPkBbtCdu", query = "SELECT b FROM BbtCdu b WHERE b.pkBbtCdu = :pkBbtCdu"),
    @NamedQuery(name = "BbtCdu.findByDesignacao", query = "SELECT b FROM BbtCdu b WHERE b.designacao = :designacao")
})
public class BbtCdu implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "pk_bbt_cdu", nullable = false, length = 2147483647)
    private String pkBbtCdu;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(nullable = false, length = 2147483647)
    private String designacao;
    @OneToMany(mappedBy = "fkBbtCdu")
    private List<BbtCdu> bbtCduList;
    @JoinColumn(name = "fk_bbt_cdu", referencedColumnName = "pk_bbt_cdu")
    @ManyToOne
    private BbtCdu fkBbtCdu;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkBbtCdu")
    private List<BbtDocumentoBbtCdu> bbtDocumentoBbtCduList;

    public BbtCdu()
    {
    }

    public BbtCdu(String pkBbtCdu)
    {
        this.pkBbtCdu = pkBbtCdu;
    }

    public BbtCdu(String pkBbtCdu, String designacao)
    {
        this.pkBbtCdu = pkBbtCdu;
        this.designacao = designacao;
    }

    public String getPkBbtCdu()
    {
        return pkBbtCdu;
    }

    public void setPkBbtCdu(String pkBbtCdu)
    {
        this.pkBbtCdu = pkBbtCdu;
    }

    public String getDesignacao()
    {
        return designacao;
    }

    public void setDesignacao(String designacao)
    {
        this.designacao = designacao;
    }

    public List<BbtCdu> getBbtCduList()
    {
        return bbtCduList;
    }

    public void setBbtCduList(List<BbtCdu> bbtCduList)
    {
        this.bbtCduList = bbtCduList;
    }

    public BbtCdu getFkBbtCdu()
    {
        return fkBbtCdu;
    }

    public void setFkBbtCdu(BbtCdu fkBbtCdu)
    {
        this.fkBbtCdu = fkBbtCdu;
    }

    public List<BbtDocumentoBbtCdu> getBbtDocumentoBbtCduList()
    {
        return bbtDocumentoBbtCduList;
    }

    public void setBbtDocumentoBbtCduList(List<BbtDocumentoBbtCdu> bbtDocumentoBbtCduList)
    {
        this.bbtDocumentoBbtCduList = bbtDocumentoBbtCduList;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (pkBbtCdu != null ? pkBbtCdu.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BbtCdu))
        {
            return false;
        }
        BbtCdu other = (BbtCdu) object;
        if ((this.pkBbtCdu == null && other.pkBbtCdu != null) || (this.pkBbtCdu != null && !this.pkBbtCdu.equals(other.pkBbtCdu)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.BbtCdu[ pkBbtCdu=" + pkBbtCdu + " ]";
    }
    
}
