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
@Table(name = "gs_fornecedor", catalog = "sig_ucan_db", schema = "public")
@NamedQueries(
{
    @NamedQuery(name = "GsFornecedor.findAll", query = "SELECT g FROM GsFornecedor g"),
    @NamedQuery(name = "GsFornecedor.findByPkGsFornecedor", query = "SELECT g FROM GsFornecedor g WHERE g.pkGsFornecedor = :pkGsFornecedor"),
    @NamedQuery(name = "GsFornecedor.findByNome", query = "SELECT g FROM GsFornecedor g WHERE g.nome = :nome"),
    @NamedQuery(name = "GsFornecedor.findByEndereco", query = "SELECT g FROM GsFornecedor g WHERE g.endereco = :endereco"),
    @NamedQuery(name = "GsFornecedor.findByContacto", query = "SELECT g FROM GsFornecedor g WHERE g.contacto = :contacto"),
    @NamedQuery(name = "GsFornecedor.findByNif", query = "SELECT g FROM GsFornecedor g WHERE g.nif = :nif")
})
public class GsFornecedor implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_gs_fornecedor", nullable = false)
    private Integer pkGsFornecedor;
    @Size(max = 50)
    @Column(length = 50)
    private String nome;
    @Size(max = 100)
    @Column(length = 100)
    private String endereco;
    @Size(max = 2147483647)
    @Column(length = 2147483647)
    private String contacto;
    @Size(max = 2147483647)
    @Column(length = 2147483647)
    private String nif;
    @OneToMany(mappedBy = "fkGsFornecedor")
    private List<GsSoftware> gsSoftwareList;

    public GsFornecedor()
    {
    }

    public GsFornecedor(Integer pkGsFornecedor)
    {
        this.pkGsFornecedor = pkGsFornecedor;
    }

    public Integer getPkGsFornecedor()
    {
        return pkGsFornecedor;
    }

    public void setPkGsFornecedor(Integer pkGsFornecedor)
    {
        this.pkGsFornecedor = pkGsFornecedor;
    }

    public String getNome()
    {
        return nome;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    public String getEndereco()
    {
        return endereco;
    }

    public void setEndereco(String endereco)
    {
        this.endereco = endereco;
    }

    public String getContacto()
    {
        return contacto;
    }

    public void setContacto(String contacto)
    {
        this.contacto = contacto;
    }

    public String getNif()
    {
        return nif;
    }

    public void setNif(String nif)
    {
        this.nif = nif;
    }

    public List<GsSoftware> getGsSoftwareList()
    {
        return gsSoftwareList;
    }

    public void setGsSoftwareList(List<GsSoftware> gsSoftwareList)
    {
        this.gsSoftwareList = gsSoftwareList;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (pkGsFornecedor != null ? pkGsFornecedor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GsFornecedor))
        {
            return false;
        }
        GsFornecedor other = (GsFornecedor) object;
        if ((this.pkGsFornecedor == null && other.pkGsFornecedor != null) || (this.pkGsFornecedor != null && !this.pkGsFornecedor.equals(other.pkGsFornecedor)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.GsFornecedor[ pkGsFornecedor=" + pkGsFornecedor + " ]";
    }
    
}
