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
@Table(name = "grl_lingua", catalog = "sig_ucan_db", schema = "public")
@NamedQueries(
{
    @NamedQuery(name = "GrlLingua.findAll", query = "SELECT g FROM GrlLingua g"),
    @NamedQuery(name = "GrlLingua.findByPkGrlLingua", query = "SELECT g FROM GrlLingua g WHERE g.pkGrlLingua = :pkGrlLingua"),
    @NamedQuery(name = "GrlLingua.findByDesignacao", query = "SELECT g FROM GrlLingua g WHERE g.designacao = :designacao")
})
public class GrlLingua implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_grl_lingua", nullable = false)
    private Integer pkGrlLingua;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(nullable = false, length = 2147483647)
    private String designacao;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkGrlLingua")
    private List<BbtDocumento> bbtDocumentoList;

    public GrlLingua()
    {
    }

    public GrlLingua(Integer pkGrlLingua)
    {
        this.pkGrlLingua = pkGrlLingua;
    }

    public GrlLingua(Integer pkGrlLingua, String designacao)
    {
        this.pkGrlLingua = pkGrlLingua;
        this.designacao = designacao;
    }

    public Integer getPkGrlLingua()
    {
        return pkGrlLingua;
    }

    public void setPkGrlLingua(Integer pkGrlLingua)
    {
        this.pkGrlLingua = pkGrlLingua;
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
        hash += (pkGrlLingua != null ? pkGrlLingua.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GrlLingua))
        {
            return false;
        }
        GrlLingua other = (GrlLingua) object;
        if ((this.pkGrlLingua == null && other.pkGrlLingua != null) || (this.pkGrlLingua != null && !this.pkGrlLingua.equals(other.pkGrlLingua)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.GrlLingua[ pkGrlLingua=" + pkGrlLingua + " ]";
    }
    
}
