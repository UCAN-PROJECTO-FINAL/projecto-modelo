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
@Table(name = "rh_tipo_funcionario")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "RhTipoFuncionario.findAll", query = "SELECT r FROM RhTipoFuncionario r"),
    @NamedQuery(name = "RhTipoFuncionario.findByPkTipoFuncionario", query = "SELECT r FROM RhTipoFuncionario r WHERE r.pkTipoFuncionario = :pkTipoFuncionario"),
    @NamedQuery(name = "RhTipoFuncionario.findByDescricao", query = "SELECT r FROM RhTipoFuncionario r WHERE r.descricao = :descricao")
})
public class RhTipoFuncionario implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_tipo_funcionario")
    private Integer pkTipoFuncionario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "descricao")
    private String descricao;
    @OneToMany(mappedBy = "fkTipoFuncionario")
    private List<RhConfiguracoes> rhConfiguracoesList;
    @OneToMany(mappedBy = "fkTipoFuncionario")
    private List<RhFuncionario> rhFuncionarioList;

    public RhTipoFuncionario()
    {
    }

    public RhTipoFuncionario(Integer pkTipoFuncionario)
    {
        this.pkTipoFuncionario = pkTipoFuncionario;
    }

    public RhTipoFuncionario(Integer pkTipoFuncionario, String descricao)
    {
        this.pkTipoFuncionario = pkTipoFuncionario;
        this.descricao = descricao;
    }

    public Integer getPkTipoFuncionario()
    {
        return pkTipoFuncionario;
    }

    public void setPkTipoFuncionario(Integer pkTipoFuncionario)
    {
        this.pkTipoFuncionario = pkTipoFuncionario;
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
        hash += (pkTipoFuncionario != null ? pkTipoFuncionario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RhTipoFuncionario))
        {
            return false;
        }
        RhTipoFuncionario other = (RhTipoFuncionario) object;
        if ((this.pkTipoFuncionario == null && other.pkTipoFuncionario != null) || (this.pkTipoFuncionario != null && !this.pkTipoFuncionario.equals(other.pkTipoFuncionario)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.RhTipoFuncionario[ pkTipoFuncionario=" + pkTipoFuncionario + " ]";
    }
    
}
