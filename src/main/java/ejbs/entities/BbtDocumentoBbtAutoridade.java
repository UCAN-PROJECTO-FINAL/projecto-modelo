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
@Table(name = "bbt_documento_bbt_autoridade", catalog = "sig_ucan_db", schema = "public")
@NamedQueries(
{
    @NamedQuery(name = "BbtDocumentoBbtAutoridade.findAll", query = "SELECT b FROM BbtDocumentoBbtAutoridade b"),
    @NamedQuery(name = "BbtDocumentoBbtAutoridade.findByPkBbtDocumentoBbtAutoridade", query = "SELECT b FROM BbtDocumentoBbtAutoridade b WHERE b.pkBbtDocumentoBbtAutoridade = :pkBbtDocumentoBbtAutoridade")
})
public class BbtDocumentoBbtAutoridade implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_bbt_documento_bbt_autoridade", nullable = false)
    private Integer pkBbtDocumentoBbtAutoridade;
    @JoinColumn(name = "fk_bbt_autoridade", referencedColumnName = "pk_bbt_autoridade", nullable = false)
    @ManyToOne(optional = false)
    private BbtAutoridade fkBbtAutoridade;
    @JoinColumn(name = "fk_bbt_documento", referencedColumnName = "pk_bbt_documento", nullable = false)
    @ManyToOne(optional = false)
    private BbtDocumento fkBbtDocumento;

    public BbtDocumentoBbtAutoridade()
    {
    }

    public BbtDocumentoBbtAutoridade(Integer pkBbtDocumentoBbtAutoridade)
    {
        this.pkBbtDocumentoBbtAutoridade = pkBbtDocumentoBbtAutoridade;
    }

    public Integer getPkBbtDocumentoBbtAutoridade()
    {
        return pkBbtDocumentoBbtAutoridade;
    }

    public void setPkBbtDocumentoBbtAutoridade(Integer pkBbtDocumentoBbtAutoridade)
    {
        this.pkBbtDocumentoBbtAutoridade = pkBbtDocumentoBbtAutoridade;
    }

    public BbtAutoridade getFkBbtAutoridade()
    {
        return fkBbtAutoridade;
    }

    public void setFkBbtAutoridade(BbtAutoridade fkBbtAutoridade)
    {
        this.fkBbtAutoridade = fkBbtAutoridade;
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
        hash += (pkBbtDocumentoBbtAutoridade != null ? pkBbtDocumentoBbtAutoridade.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BbtDocumentoBbtAutoridade))
        {
            return false;
        }
        BbtDocumentoBbtAutoridade other = (BbtDocumentoBbtAutoridade) object;
        if ((this.pkBbtDocumentoBbtAutoridade == null && other.pkBbtDocumentoBbtAutoridade != null) || (this.pkBbtDocumentoBbtAutoridade != null && !this.pkBbtDocumentoBbtAutoridade.equals(other.pkBbtDocumentoBbtAutoridade)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.BbtDocumentoBbtAutoridade[ pkBbtDocumentoBbtAutoridade=" + pkBbtDocumentoBbtAutoridade + " ]";
    }
    
}
