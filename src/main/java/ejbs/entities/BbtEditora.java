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
@Table(name = "bbt_editora", catalog = "sig_ucan_db", schema = "public")
@NamedQueries(
{
    @NamedQuery(name = "BbtEditora.findAll", query = "SELECT b FROM BbtEditora b"),
    @NamedQuery(name = "BbtEditora.findByPkBbtEditora", query = "SELECT b FROM BbtEditora b WHERE b.pkBbtEditora = :pkBbtEditora"),
    @NamedQuery(name = "BbtEditora.findByDesignacao", query = "SELECT b FROM BbtEditora b WHERE b.designacao = :designacao")
})
public class BbtEditora implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_bbt_editora", nullable = false)
    private Integer pkBbtEditora;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(nullable = false, length = 2147483647)
    private String designacao;
    @OneToMany(mappedBy = "fkBbtEditora")
    private List<BbtDocumento> bbtDocumentoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bbtEditora")
    private List<BbtEditoraLocLocalidade> bbtEditoraLocLocalidadeList;

    public BbtEditora()
    {
    }

    public BbtEditora(Integer pkBbtEditora)
    {
        this.pkBbtEditora = pkBbtEditora;
    }

    public BbtEditora(Integer pkBbtEditora, String designacao)
    {
        this.pkBbtEditora = pkBbtEditora;
        this.designacao = designacao;
    }

    public Integer getPkBbtEditora()
    {
        return pkBbtEditora;
    }

    public void setPkBbtEditora(Integer pkBbtEditora)
    {
        this.pkBbtEditora = pkBbtEditora;
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

    public List<BbtEditoraLocLocalidade> getBbtEditoraLocLocalidadeList()
    {
        return bbtEditoraLocLocalidadeList;
    }

    public void setBbtEditoraLocLocalidadeList(List<BbtEditoraLocLocalidade> bbtEditoraLocLocalidadeList)
    {
        this.bbtEditoraLocLocalidadeList = bbtEditoraLocLocalidadeList;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (pkBbtEditora != null ? pkBbtEditora.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BbtEditora))
        {
            return false;
        }
        BbtEditora other = (BbtEditora) object;
        if ((this.pkBbtEditora == null && other.pkBbtEditora != null) || (this.pkBbtEditora != null && !this.pkBbtEditora.equals(other.pkBbtEditora)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.BbtEditora[ pkBbtEditora=" + pkBbtEditora + " ]";
    }
    
}
