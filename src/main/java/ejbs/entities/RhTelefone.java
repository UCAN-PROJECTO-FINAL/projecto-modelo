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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author smakambo
 */
@Entity
@Table(name = "rh_telefone", catalog = "sig_ucan_db", schema = "public")
@NamedQueries(
{
    @NamedQuery(name = "RhTelefone.findAll", query = "SELECT r FROM RhTelefone r"),
    @NamedQuery(name = "RhTelefone.findByPkTelefone", query = "SELECT r FROM RhTelefone r WHERE r.pkTelefone = :pkTelefone"),
    @NamedQuery(name = "RhTelefone.findByTelefonePrincipal", query = "SELECT r FROM RhTelefone r WHERE r.telefonePrincipal = :telefonePrincipal"),
    @NamedQuery(name = "RhTelefone.findByTelefoneSecundario", query = "SELECT r FROM RhTelefone r WHERE r.telefoneSecundario = :telefoneSecundario")
})
public class RhTelefone implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "pk_telefone", nullable = false)
    private Integer pkTelefone;
    @Size(max = 50)
    @Column(name = "telefone_principal", length = 50)
    private String telefonePrincipal;
    @Size(max = 50)
    @Column(name = "telefone_secundario", length = 50)
    private String telefoneSecundario;
    @JoinColumn(name = "fk_pessoa", referencedColumnName = "pk_pessoa")
    @ManyToOne
    private RhPessoa fkPessoa;

    public RhTelefone()
    {
    }

    public RhTelefone(Integer pkTelefone)
    {
        this.pkTelefone = pkTelefone;
    }

    public Integer getPkTelefone()
    {
        return pkTelefone;
    }

    public void setPkTelefone(Integer pkTelefone)
    {
        this.pkTelefone = pkTelefone;
    }

    public String getTelefonePrincipal()
    {
        return telefonePrincipal;
    }

    public void setTelefonePrincipal(String telefonePrincipal)
    {
        this.telefonePrincipal = telefonePrincipal;
    }

    public String getTelefoneSecundario()
    {
        return telefoneSecundario;
    }

    public void setTelefoneSecundario(String telefoneSecundario)
    {
        this.telefoneSecundario = telefoneSecundario;
    }

    public RhPessoa getFkPessoa()
    {
        return fkPessoa;
    }

    public void setFkPessoa(RhPessoa fkPessoa)
    {
        this.fkPessoa = fkPessoa;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (pkTelefone != null ? pkTelefone.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RhTelefone))
        {
            return false;
        }
        RhTelefone other = (RhTelefone) object;
        if ((this.pkTelefone == null && other.pkTelefone != null) || (this.pkTelefone != null && !this.pkTelefone.equals(other.pkTelefone)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.RhTelefone[ pkTelefone=" + pkTelefone + " ]";
    }
    
}
