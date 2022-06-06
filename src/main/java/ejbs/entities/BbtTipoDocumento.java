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
@Table(name = "bbt_tipo_documento", catalog = "sig_ucan_db", schema = "public")
@NamedQueries(
{
    @NamedQuery(name = "BbtTipoDocumento.findAll", query = "SELECT b FROM BbtTipoDocumento b"),
    @NamedQuery(name = "BbtTipoDocumento.findByPkBbtTipoDocumento", query = "SELECT b FROM BbtTipoDocumento b WHERE b.pkBbtTipoDocumento = :pkBbtTipoDocumento"),
    @NamedQuery(name = "BbtTipoDocumento.findByDesignacao", query = "SELECT b FROM BbtTipoDocumento b WHERE b.designacao = :designacao")
})
public class BbtTipoDocumento implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_bbt_tipo_documento", nullable = false)
    private Integer pkBbtTipoDocumento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(nullable = false, length = 2147483647)
    private String designacao;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkBbtTipoDocumento")
    private List<BbtDocumento> bbtDocumentoList;

    public BbtTipoDocumento()
    {
    }

    public BbtTipoDocumento(Integer pkBbtTipoDocumento)
    {
        this.pkBbtTipoDocumento = pkBbtTipoDocumento;
    }

    public BbtTipoDocumento(Integer pkBbtTipoDocumento, String designacao)
    {
        this.pkBbtTipoDocumento = pkBbtTipoDocumento;
        this.designacao = designacao;
    }

    public Integer getPkBbtTipoDocumento()
    {
        return pkBbtTipoDocumento;
    }

    public void setPkBbtTipoDocumento(Integer pkBbtTipoDocumento)
    {
        this.pkBbtTipoDocumento = pkBbtTipoDocumento;
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
        hash += (pkBbtTipoDocumento != null ? pkBbtTipoDocumento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BbtTipoDocumento))
        {
            return false;
        }
        BbtTipoDocumento other = (BbtTipoDocumento) object;
        if ((this.pkBbtTipoDocumento == null && other.pkBbtTipoDocumento != null) || (this.pkBbtTipoDocumento != null && !this.pkBbtTipoDocumento.equals(other.pkBbtTipoDocumento)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.BbtTipoDocumento[ pkBbtTipoDocumento=" + pkBbtTipoDocumento + " ]";
    }
    
}
