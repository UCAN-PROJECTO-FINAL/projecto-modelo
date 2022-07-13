/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
@Table(name = "rh_pessoa", catalog = "sig_ucan_db", schema = "public")
@NamedQueries(
{
    @NamedQuery(name = "RhPessoa.findAll", query = "SELECT r FROM RhPessoa r"),
    @NamedQuery(name = "RhPessoa.findByPkPessoa", query = "SELECT r FROM RhPessoa r WHERE r.pkPessoa = :pkPessoa"),
    @NamedQuery(name = "RhPessoa.findByNome", query = "SELECT r FROM RhPessoa r WHERE r.nome = :nome"),
    @NamedQuery(name = "RhPessoa.findByNomePai", query = "SELECT r FROM RhPessoa r WHERE r.nomePai = :nomePai"),
    @NamedQuery(name = "RhPessoa.findByNomeMae", query = "SELECT r FROM RhPessoa r WHERE r.nomeMae = :nomeMae"),
    @NamedQuery(name = "RhPessoa.findByDataNascimento", query = "SELECT r FROM RhPessoa r WHERE r.dataNascimento = :dataNascimento")
})
public class RhPessoa implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "pk_pessoa", nullable = false)
    private Integer pkPessoa;
    @Size(max = 200)
    @Column(length = 200)
    private String nome;
    @Size(max = 200)
    @Column(name = "nome_pai", length = 200)
    private String nomePai;
    @Size(max = 200)
    @Column(name = "nome_mae", length = 200)
    private String nomeMae;
    @Column(name = "data_nascimento")
    @Temporal(TemporalType.DATE)
    private Date dataNascimento;
    @OneToMany(mappedBy = "fkPessoa")
    private List<RhTelefone> rhTelefoneList;
    @OneToMany(mappedBy = "fkPessoa")
    private List<RhEmail> rhEmailList;

    public RhPessoa()
    {
    }

    public RhPessoa(Integer pkPessoa)
    {
        this.pkPessoa = pkPessoa;
    }

    public Integer getPkPessoa()
    {
        return pkPessoa;
    }

    public void setPkPessoa(Integer pkPessoa)
    {
        this.pkPessoa = pkPessoa;
    }

    public String getNome()
    {
        return nome;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    public String getNomePai()
    {
        return nomePai;
    }

    public void setNomePai(String nomePai)
    {
        this.nomePai = nomePai;
    }

    public String getNomeMae()
    {
        return nomeMae;
    }

    public void setNomeMae(String nomeMae)
    {
        this.nomeMae = nomeMae;
    }

    public Date getDataNascimento()
    {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento)
    {
        this.dataNascimento = dataNascimento;
    }

    public List<RhTelefone> getRhTelefoneList()
    {
        return rhTelefoneList;
    }

    public void setRhTelefoneList(List<RhTelefone> rhTelefoneList)
    {
        this.rhTelefoneList = rhTelefoneList;
    }

    public List<RhEmail> getRhEmailList()
    {
        return rhEmailList;
    }

    public void setRhEmailList(List<RhEmail> rhEmailList)
    {
        this.rhEmailList = rhEmailList;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (pkPessoa != null ? pkPessoa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RhPessoa))
        {
            return false;
        }
        RhPessoa other = (RhPessoa) object;
        if ((this.pkPessoa == null && other.pkPessoa != null) || (this.pkPessoa != null && !this.pkPessoa.equals(other.pkPessoa)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.RhPessoa[ pkPessoa=" + pkPessoa + " ]";
    }
    
}
