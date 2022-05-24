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
@Table(name = "grl_sexo")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "GrlSexo.findAll", query = "SELECT g FROM GrlSexo g"),
    @NamedQuery(name = "GrlSexo.findByPkGrlSexo", query = "SELECT g FROM GrlSexo g WHERE g.pkGrlSexo = :pkGrlSexo"),
    @NamedQuery(name = "GrlSexo.findByNome", query = "SELECT g FROM GrlSexo g WHERE g.nome = :nome")
})
public class GrlSexo implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "pk_grl_sexo")
    private Integer pkGrlSexo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "nome")
    private String nome;
    @OneToMany(mappedBy = "fkSexo")
    private List<GrlPessoa> grlPessoaList;

    public GrlSexo()
    {
    }

    public GrlSexo(Integer pkGrlSexo)
    {
        this.pkGrlSexo = pkGrlSexo;
    }

    public GrlSexo(Integer pkGrlSexo, String nome)
    {
        this.pkGrlSexo = pkGrlSexo;
        this.nome = nome;
    }

    public Integer getPkGrlSexo()
    {
        return pkGrlSexo;
    }

    public void setPkGrlSexo(Integer pkGrlSexo)
    {
        this.pkGrlSexo = pkGrlSexo;
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
        hash += (pkGrlSexo != null ? pkGrlSexo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GrlSexo))
        {
            return false;
        }
        GrlSexo other = (GrlSexo) object;
        if ((this.pkGrlSexo == null && other.pkGrlSexo != null) || (this.pkGrlSexo != null && !this.pkGrlSexo.equals(other.pkGrlSexo)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.GrlSexo[ pkGrlSexo=" + pkGrlSexo + " ]";
    }
    
}
