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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author smakambo
 */
@Entity
@Table(name = "grl_estado_civil")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "GrlEstadoCivil.findAll", query = "SELECT g FROM GrlEstadoCivil g"),
    @NamedQuery(name = "GrlEstadoCivil.findByPkGrlEstadoCivil", query = "SELECT g FROM GrlEstadoCivil g WHERE g.pkGrlEstadoCivil = :pkGrlEstadoCivil"),
    @NamedQuery(name = "GrlEstadoCivil.findByNome", query = "SELECT g FROM GrlEstadoCivil g WHERE g.nome = :nome")
})
public class GrlEstadoCivil implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_grl_estado_civil")
    private Integer pkGrlEstadoCivil;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "nome")
    private String nome;
    @OneToMany(mappedBy = "fkEstadoCivil")
    private List<GrlPessoa> grlPessoaList;

    public GrlEstadoCivil()
    {
    }

    public GrlEstadoCivil(Integer pkGrlEstadoCivil)
    {
        this.pkGrlEstadoCivil = pkGrlEstadoCivil;
    }

    public GrlEstadoCivil(Integer pkGrlEstadoCivil, String nome)
    {
        this.pkGrlEstadoCivil = pkGrlEstadoCivil;
        this.nome = nome;
    }

    public Integer getPkGrlEstadoCivil()
    {
        return pkGrlEstadoCivil;
    }

    public void setPkGrlEstadoCivil(Integer pkGrlEstadoCivil)
    {
        this.pkGrlEstadoCivil = pkGrlEstadoCivil;
    }

    public String getNome()
    {
        return nome;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    @XmlTransient
    public List<GrlPessoa> getGrlPessoaList()
    {
        return grlPessoaList;
    }

    public void setGrlPessoaList(List<GrlPessoa> grlPessoaList)
    {
        this.grlPessoaList = grlPessoaList;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (pkGrlEstadoCivil != null ? pkGrlEstadoCivil.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GrlEstadoCivil))
        {
            return false;
        }
        GrlEstadoCivil other = (GrlEstadoCivil) object;
        if ((this.pkGrlEstadoCivil == null && other.pkGrlEstadoCivil != null) || (this.pkGrlEstadoCivil != null && !this.pkGrlEstadoCivil.equals(other.pkGrlEstadoCivil)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.GrlEstadoCivil[ pkGrlEstadoCivil=" + pkGrlEstadoCivil + " ]";
    }
    
}
