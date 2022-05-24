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
@Table(name = "rh_nivel_academico")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "RhNivelAcademico.findAll", query = "SELECT r FROM RhNivelAcademico r"),
    @NamedQuery(name = "RhNivelAcademico.findByPkNivelAcademico", query = "SELECT r FROM RhNivelAcademico r WHERE r.pkNivelAcademico = :pkNivelAcademico"),
    @NamedQuery(name = "RhNivelAcademico.findByDescricao", query = "SELECT r FROM RhNivelAcademico r WHERE r.descricao = :descricao")
})
public class RhNivelAcademico implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_nivel_academico")
    private Integer pkNivelAcademico;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "descricao")
    private String descricao;
    @OneToMany(mappedBy = "fkNivelAcademico")
    private List<RhConfiguracoes> rhConfiguracoesList;
    @OneToMany(mappedBy = "fkNivelAcademico")
    private List<RhFuncionario> rhFuncionarioList;

    public RhNivelAcademico()
    {
    }

    public RhNivelAcademico(Integer pkNivelAcademico)
    {
        this.pkNivelAcademico = pkNivelAcademico;
    }

    public RhNivelAcademico(Integer pkNivelAcademico, String descricao)
    {
        this.pkNivelAcademico = pkNivelAcademico;
        this.descricao = descricao;
    }

    public Integer getPkNivelAcademico()
    {
        return pkNivelAcademico;
    }

    public void setPkNivelAcademico(Integer pkNivelAcademico)
    {
        this.pkNivelAcademico = pkNivelAcademico;
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
        hash += (pkNivelAcademico != null ? pkNivelAcademico.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RhNivelAcademico))
        {
            return false;
        }
        RhNivelAcademico other = (RhNivelAcademico) object;
        if ((this.pkNivelAcademico == null && other.pkNivelAcademico != null) || (this.pkNivelAcademico != null && !this.pkNivelAcademico.equals(other.pkNivelAcademico)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.RhNivelAcademico[ pkNivelAcademico=" + pkNivelAcademico + " ]";
    }
    
}
