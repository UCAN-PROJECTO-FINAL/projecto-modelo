/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author smakambo
 */
@Entity
@Table(name = "bbt_documento_bbt_cdu", catalog = "sig_ucan_db", schema = "public")
@NamedQueries(
{
    @NamedQuery(name = "BbtDocumentoBbtCdu.findAll", query = "SELECT b FROM BbtDocumentoBbtCdu b"),
    @NamedQuery(name = "BbtDocumentoBbtCdu.findByPkBbtDocumentoBbtCdu", query = "SELECT b FROM BbtDocumentoBbtCdu b WHERE b.pkBbtDocumentoBbtCdu = :pkBbtDocumentoBbtCdu")
})
public class BbtDocumentoBbtCdu implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_bbt_documento_bbt_cdu", nullable = false)
    private Integer pkBbtDocumentoBbtCdu;
    @JoinColumn(name = "fk_bbt_cdu", referencedColumnName = "pk_bbt_cdu", nullable = false)
    @ManyToOne(optional = false)
    private BbtCdu fkBbtCdu;
    @JoinColumn(name = "fk_bbt_documento", referencedColumnName = "pk_bbt_documento", nullable = false)
    @ManyToOne(optional = false)
    private BbtDocumento fkBbtDocumento;

    public BbtDocumentoBbtCdu()
    {
    }

    public BbtDocumentoBbtCdu(Integer pkBbtDocumentoBbtCdu)
    {
        this.pkBbtDocumentoBbtCdu = pkBbtDocumentoBbtCdu;
    }

    public Integer getPkBbtDocumentoBbtCdu()
    {
        return pkBbtDocumentoBbtCdu;
    }

    public void setPkBbtDocumentoBbtCdu(Integer pkBbtDocumentoBbtCdu)
    {
        this.pkBbtDocumentoBbtCdu = pkBbtDocumentoBbtCdu;
    }

    public BbtCdu getFkBbtCdu()
    {
        return fkBbtCdu;
    }

    public void setFkBbtCdu(BbtCdu fkBbtCdu)
    {
        this.fkBbtCdu = fkBbtCdu;
    }

    public BbtDocumento getFkBbtDocumento()
    {
        return fkBbtDocumento;
    }

    public void setFkBbtDocumento(BbtDocumento fkBbtDocumento)
    {
        this.fkBbtDocumento = fkBbtDocumento;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (pkBbtDocumentoBbtCdu != null ? pkBbtDocumentoBbtCdu.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BbtDocumentoBbtCdu))
        {
            return false;
        }
        BbtDocumentoBbtCdu other = (BbtDocumentoBbtCdu) object;
        if ((this.pkBbtDocumentoBbtCdu == null && other.pkBbtDocumentoBbtCdu != null) || (this.pkBbtDocumentoBbtCdu != null && !this.pkBbtDocumentoBbtCdu.equals(other.pkBbtDocumentoBbtCdu)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.BbtDocumentoBbtCdu[ pkBbtDocumentoBbtCdu=" + pkBbtDocumentoBbtCdu + " ]";
    }
    
}
