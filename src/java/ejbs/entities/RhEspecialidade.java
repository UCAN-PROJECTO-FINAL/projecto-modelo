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
@Table(name = "rh_especialidade")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "RhEspecialidade.findAll", query = "SELECT r FROM RhEspecialidade r"),
    @NamedQuery(name = "RhEspecialidade.findByPkEspecialidade", query = "SELECT r FROM RhEspecialidade r WHERE r.pkEspecialidade = :pkEspecialidade"),
    @NamedQuery(name = "RhEspecialidade.findByDescricao", query = "SELECT r FROM RhEspecialidade r WHERE r.descricao = :descricao")
})
public class RhEspecialidade implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_especialidade")
    private Integer pkEspecialidade;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "descricao")
    private String descricao;
    @OneToMany(mappedBy = "fkEspecialidade")
    private List<RhConfiguracoes> rhConfiguracoesList;
    @OneToMany(mappedBy = "fkEspecialidade")
    private List<RhFuncionario> rhFuncionarioList;

    public RhEspecialidade()
    {
    }

    public RhEspecialidade(Integer pkEspecialidade)
    {
        this.pkEspecialidade = pkEspecialidade;
    }

    public RhEspecialidade(Integer pkEspecialidade, String descricao)
    {
        this.pkEspecialidade = pkEspecialidade;
        this.descricao = descricao;
    }

    public Integer getPkEspecialidade()
    {
        return pkEspecialidade;
    }

    public void setPkEspecialidade(Integer pkEspecialidade)
    {
        this.pkEspecialidade = pkEspecialidade;
    }

    public String getDescricao()
    {
        return descricao;
    }

    public void setDescricao(String descricao)
    {
        this.descricao = descricao;
    }

    @XmlTransient
    public List<RhConfiguracoes> getRhConfiguracoesList()
    {
        return rhConfiguracoesList;
    }

    public void setRhConfiguracoesList(List<RhConfiguracoes> rhConfiguracoesList)
    {
        this.rhConfiguracoesList = rhConfiguracoesList;
    }

    @XmlTransient
    public List<RhFuncionario> getRhFuncionarioList()
    {
        return rhFuncionarioList;
    }

    public void setRhFuncionarioList(List<RhFuncionario> rhFuncionarioList)
    {
        this.rhFuncionarioList = rhFuncionarioList;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (pkEspecialidade != null ? pkEspecialidade.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RhEspecialidade))
        {
            return false;
        }
        RhEspecialidade other = (RhEspecialidade) object;
        if ((this.pkEspecialidade == null && other.pkEspecialidade != null) || (this.pkEspecialidade != null && !this.pkEspecialidade.equals(other.pkEspecialidade)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.RhEspecialidade[ pkEspecialidade=" + pkEspecialidade + " ]";
    }
    
}
