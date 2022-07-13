/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author smakambo
 */
@Entity
@Table(name = "fin_empresa", catalog = "sig_ucan_db", schema = "public")
@NamedQueries(
{
    @NamedQuery(name = "FinEmpresa.findAll", query = "SELECT f FROM FinEmpresa f"),
    @NamedQuery(name = "FinEmpresa.findByPkEmpresa", query = "SELECT f FROM FinEmpresa f WHERE f.pkEmpresa = :pkEmpresa"),
    @NamedQuery(name = "FinEmpresa.findByDescricaoEmpresa", query = "SELECT f FROM FinEmpresa f WHERE f.descricaoEmpresa = :descricaoEmpresa"),
    @NamedQuery(name = "FinEmpresa.findByTelefoneEmpresa", query = "SELECT f FROM FinEmpresa f WHERE f.telefoneEmpresa = :telefoneEmpresa"),
    @NamedQuery(name = "FinEmpresa.findByFkEndereco", query = "SELECT f FROM FinEmpresa f WHERE f.fkEndereco = :fkEndereco"),
    @NamedQuery(name = "FinEmpresa.findByNifEmpresa", query = "SELECT f FROM FinEmpresa f WHERE f.nifEmpresa = :nifEmpresa"),
    @NamedQuery(name = "FinEmpresa.findByEmailEmpresa", query = "SELECT f FROM FinEmpresa f WHERE f.emailEmpresa = :emailEmpresa"),
    @NamedQuery(name = "FinEmpresa.findByEstadoFn", query = "SELECT f FROM FinEmpresa f WHERE f.estadoFn = :estadoFn"),
    @NamedQuery(name = "FinEmpresa.findByDataNovoFn", query = "SELECT f FROM FinEmpresa f WHERE f.dataNovoFn = :dataNovoFn")
})
public class FinEmpresa implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_empresa", nullable = false)
    private Integer pkEmpresa;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "descricao_empresa", nullable = false, length = 2147483647)
    private String descricaoEmpresa;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "telefone_empresa", nullable = false, length = 2147483647)
    private String telefoneEmpresa;
    @Column(name = "fk_endereco")
    private Integer fkEndereco;
    @Basic(optional = false)
    @NotNull
    @Column(name = "nif_empresa", nullable = false)
    private int nifEmpresa;
    @Size(max = 2147483647)
    @Column(name = "email_empresa", length = 2147483647)
    private String emailEmpresa;
    @Column(name = "estado_fn")
    private Boolean estadoFn;
    @Column(name = "data_novo_fn")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataNovoFn;

    public FinEmpresa()
    {
    }

    public FinEmpresa(Integer pkEmpresa)
    {
        this.pkEmpresa = pkEmpresa;
    }

    public FinEmpresa(Integer pkEmpresa, String descricaoEmpresa, String telefoneEmpresa, int nifEmpresa)
    {
        this.pkEmpresa = pkEmpresa;
        this.descricaoEmpresa = descricaoEmpresa;
        this.telefoneEmpresa = telefoneEmpresa;
        this.nifEmpresa = nifEmpresa;
    }

    public Integer getPkEmpresa()
    {
        return pkEmpresa;
    }

    public void setPkEmpresa(Integer pkEmpresa)
    {
        this.pkEmpresa = pkEmpresa;
    }

    public String getDescricaoEmpresa()
    {
        return descricaoEmpresa;
    }

    public void setDescricaoEmpresa(String descricaoEmpresa)
    {
        this.descricaoEmpresa = descricaoEmpresa;
    }

    public String getTelefoneEmpresa()
    {
        return telefoneEmpresa;
    }

    public void setTelefoneEmpresa(String telefoneEmpresa)
    {
        this.telefoneEmpresa = telefoneEmpresa;
    }

    public Integer getFkEndereco()
    {
        return fkEndereco;
    }

    public void setFkEndereco(Integer fkEndereco)
    {
        this.fkEndereco = fkEndereco;
    }

    public int getNifEmpresa()
    {
        return nifEmpresa;
    }

    public void setNifEmpresa(int nifEmpresa)
    {
        this.nifEmpresa = nifEmpresa;
    }

    public String getEmailEmpresa()
    {
        return emailEmpresa;
    }

    public void setEmailEmpresa(String emailEmpresa)
    {
        this.emailEmpresa = emailEmpresa;
    }

    public Boolean getEstadoFn()
    {
        return estadoFn;
    }

    public void setEstadoFn(Boolean estadoFn)
    {
        this.estadoFn = estadoFn;
    }

    public Date getDataNovoFn()
    {
        return dataNovoFn;
    }

    public void setDataNovoFn(Date dataNovoFn)
    {
        this.dataNovoFn = dataNovoFn;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (pkEmpresa != null ? pkEmpresa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FinEmpresa))
        {
            return false;
        }
        FinEmpresa other = (FinEmpresa) object;
        if ((this.pkEmpresa == null && other.pkEmpresa != null) || (this.pkEmpresa != null && !this.pkEmpresa.equals(other.pkEmpresa)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.FinEmpresa[ pkEmpresa=" + pkEmpresa + " ]";
    }
    
}
