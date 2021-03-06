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

/**
 *
 * @author smakambo
 */
@Entity
@Table(name = "rh_especialidade", catalog = "sig_ucan_db", schema = "public")
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
    @Column(name = "pk_especialidade", nullable = false)
    private Integer pkEspecialidade;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(nullable = false, length = 2147483647)
    private String descricao;
    @OneToMany(mappedBy = "fkEspecialidade")
    private List<RhFuncionario> rhFuncionarioList;
    @OneToMany(mappedBy = "fkEspecialidade")
    private List<FrtTransporteConfiguracoes> frtTransporteConfiguracoesList;

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

    public List<RhFuncionario> getRhFuncionarioList()
    {
        return rhFuncionarioList;
    }

    public void setRhFuncionarioList(List<RhFuncionario> rhFuncionarioList)
    {
        this.rhFuncionarioList = rhFuncionarioList;
    }

    public List<FrtTransporteConfiguracoes> getFrtTransporteConfiguracoesList()
    {
        return frtTransporteConfiguracoesList;
    }

    public void setFrtTransporteConfiguracoesList(List<FrtTransporteConfiguracoes> frtTransporteConfiguracoesList)
    {
        this.frtTransporteConfiguracoesList = frtTransporteConfiguracoesList;
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
