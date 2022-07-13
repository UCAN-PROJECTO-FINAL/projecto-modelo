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
@Table(name = "grl_tipo_pessoa", catalog = "sig_ucan_db", schema = "public")
@NamedQueries(
{
    @NamedQuery(name = "GrlTipoPessoa.findAll", query = "SELECT g FROM GrlTipoPessoa g"),
    @NamedQuery(name = "GrlTipoPessoa.findByPkGrlTipoPessoa", query = "SELECT g FROM GrlTipoPessoa g WHERE g.pkGrlTipoPessoa = :pkGrlTipoPessoa"),
    @NamedQuery(name = "GrlTipoPessoa.findByNome", query = "SELECT g FROM GrlTipoPessoa g WHERE g.nome = :nome")
})
public class GrlTipoPessoa implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "pk_grl_tipo_pessoa", nullable = false)
    private Integer pkGrlTipoPessoa;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(nullable = false, length = 2147483647)
    private String nome;

    public GrlTipoPessoa()
    {
    }

    public GrlTipoPessoa(Integer pkGrlTipoPessoa)
    {
        this.pkGrlTipoPessoa = pkGrlTipoPessoa;
    }

    public GrlTipoPessoa(Integer pkGrlTipoPessoa, String nome)
    {
        this.pkGrlTipoPessoa = pkGrlTipoPessoa;
        this.nome = nome;
    }

    public Integer getPkGrlTipoPessoa()
    {
        return pkGrlTipoPessoa;
    }

    public void setPkGrlTipoPessoa(Integer pkGrlTipoPessoa)
    {
        this.pkGrlTipoPessoa = pkGrlTipoPessoa;
    }

    public String getNome()
    {
        return nome;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (pkGrlTipoPessoa != null ? pkGrlTipoPessoa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GrlTipoPessoa))
        {
            return false;
        }
        GrlTipoPessoa other = (GrlTipoPessoa) object;
        if ((this.pkGrlTipoPessoa == null && other.pkGrlTipoPessoa != null) || (this.pkGrlTipoPessoa != null && !this.pkGrlTipoPessoa.equals(other.pkGrlTipoPessoa)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.GrlTipoPessoa[ pkGrlTipoPessoa=" + pkGrlTipoPessoa + " ]";
    }
    
}
