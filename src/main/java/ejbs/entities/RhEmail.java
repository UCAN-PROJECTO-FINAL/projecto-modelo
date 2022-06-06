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
@Table(name = "rh_email", catalog = "sig_ucan_db", schema = "public")
@NamedQueries(
{
    @NamedQuery(name = "RhEmail.findAll", query = "SELECT r FROM RhEmail r"),
    @NamedQuery(name = "RhEmail.findByPkEmail", query = "SELECT r FROM RhEmail r WHERE r.pkEmail = :pkEmail"),
    @NamedQuery(name = "RhEmail.findByEmailPrincipal", query = "SELECT r FROM RhEmail r WHERE r.emailPrincipal = :emailPrincipal"),
    @NamedQuery(name = "RhEmail.findByEmailSecundario", query = "SELECT r FROM RhEmail r WHERE r.emailSecundario = :emailSecundario")
})
public class RhEmail implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "pk_email", nullable = false)
    private Integer pkEmail;
    @Size(max = 150)
    @Column(name = "email_principal", length = 150)
    private String emailPrincipal;
    @Size(max = 150)
    @Column(name = "email_secundario", length = 150)
    private String emailSecundario;
    @JoinColumn(name = "fk_pessoa", referencedColumnName = "pk_pessoa")
    @ManyToOne
    private RhPessoa fkPessoa;

    public RhEmail()
    {
    }

    public RhEmail(Integer pkEmail)
    {
        this.pkEmail = pkEmail;
    }

    public Integer getPkEmail()
    {
        return pkEmail;
    }

    public void setPkEmail(Integer pkEmail)
    {
        this.pkEmail = pkEmail;
    }

    public String getEmailPrincipal()
    {
        return emailPrincipal;
    }

    public void setEmailPrincipal(String emailPrincipal)
    {
        this.emailPrincipal = emailPrincipal;
    }

    public String getEmailSecundario()
    {
        return emailSecundario;
    }

    public void setEmailSecundario(String emailSecundario)
    {
        this.emailSecundario = emailSecundario;
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
        hash += (pkEmail != null ? pkEmail.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RhEmail))
        {
            return false;
        }
        RhEmail other = (RhEmail) object;
        if ((this.pkEmail == null && other.pkEmail != null) || (this.pkEmail != null && !this.pkEmail.equals(other.pkEmail)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.RhEmail[ pkEmail=" + pkEmail + " ]";
    }
    
}
