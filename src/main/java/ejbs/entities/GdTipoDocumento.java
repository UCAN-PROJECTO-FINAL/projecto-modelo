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
import javax.validation.constraints.Size;

/**
 *
 * @author smakambo
 */
@Entity
@Table(name = "gd_tipo_documento", catalog = "sig_ucan_db", schema = "public")
@NamedQueries(
{
    @NamedQuery(name = "GdTipoDocumento.findAll", query = "SELECT g FROM GdTipoDocumento g"),
    @NamedQuery(name = "GdTipoDocumento.findByPkTipoDocumento", query = "SELECT g FROM GdTipoDocumento g WHERE g.pkTipoDocumento = :pkTipoDocumento"),
    @NamedQuery(name = "GdTipoDocumento.findByDescricao", query = "SELECT g FROM GdTipoDocumento g WHERE g.descricao = :descricao"),
    @NamedQuery(name = "GdTipoDocumento.findByModulo", query = "SELECT g FROM GdTipoDocumento g WHERE g.modulo = :modulo")
})
public class GdTipoDocumento implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_tipo_documento", nullable = false)
    private Integer pkTipoDocumento;
    @Size(max = 2147483647)
    @Column(length = 2147483647)
    private String descricao;
    @Size(max = 2147483647)
    @Column(length = 2147483647)
    private String modulo;
    @OneToMany(mappedBy = "fkTipoDoc")
    private List<FinDocumento> finDocumentoList;
    @OneToMany(mappedBy = "fkTipoDocumento")
    private List<GdDocumento> gdDocumentoList;
    @OneToMany(mappedBy = "fkTipoDocumento")
    private List<GdConfiguracoes> gdConfiguracoesList;

    public GdTipoDocumento()
    {
    }

    public GdTipoDocumento(Integer pkTipoDocumento)
    {
        this.pkTipoDocumento = pkTipoDocumento;
    }

    public Integer getPkTipoDocumento()
    {
        return pkTipoDocumento;
    }

    public void setPkTipoDocumento(Integer pkTipoDocumento)
    {
        this.pkTipoDocumento = pkTipoDocumento;
    }

    public String getDescricao()
    {
        return descricao;
    }

    public void setDescricao(String descricao)
    {
        this.descricao = descricao;
    }

    public String getModulo()
    {
        return modulo;
    }

    public void setModulo(String modulo)
    {
        this.modulo = modulo;
    }

    public List<FinDocumento> getFinDocumentoList()
    {
        return finDocumentoList;
    }

    public void setFinDocumentoList(List<FinDocumento> finDocumentoList)
    {
        this.finDocumentoList = finDocumentoList;
    }

    public List<GdDocumento> getGdDocumentoList()
    {
        return gdDocumentoList;
    }

    public void setGdDocumentoList(List<GdDocumento> gdDocumentoList)
    {
        this.gdDocumentoList = gdDocumentoList;
    }

    public List<GdConfiguracoes> getGdConfiguracoesList()
    {
        return gdConfiguracoesList;
    }

    public void setGdConfiguracoesList(List<GdConfiguracoes> gdConfiguracoesList)
    {
        this.gdConfiguracoesList = gdConfiguracoesList;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (pkTipoDocumento != null ? pkTipoDocumento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GdTipoDocumento))
        {
            return false;
        }
        GdTipoDocumento other = (GdTipoDocumento) object;
        if ((this.pkTipoDocumento == null && other.pkTipoDocumento != null) || (this.pkTipoDocumento != null && !this.pkTipoDocumento.equals(other.pkTipoDocumento)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.GdTipoDocumento[ pkTipoDocumento=" + pkTipoDocumento + " ]";
    }
    
}
