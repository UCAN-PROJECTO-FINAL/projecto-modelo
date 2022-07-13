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
@Table(name = "rh_funcao", catalog = "sig_ucan_db", schema = "public")
@NamedQueries(
{
    @NamedQuery(name = "RhFuncao.findAll", query = "SELECT r FROM RhFuncao r"),
    @NamedQuery(name = "RhFuncao.findByPkFuncao", query = "SELECT r FROM RhFuncao r WHERE r.pkFuncao = :pkFuncao"),
    @NamedQuery(name = "RhFuncao.findByDescricao", query = "SELECT r FROM RhFuncao r WHERE r.descricao = :descricao")
})
public class RhFuncao implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_funcao", nullable = false)
    private Integer pkFuncao;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(nullable = false, length = 2147483647)
    private String descricao;
    @OneToMany(mappedBy = "fkFuncao")
    private List<RhFuncionario> rhFuncionarioList;
    @OneToMany(mappedBy = "fkFuncao")
    private List<FrtTransporteConfiguracoes> frtTransporteConfiguracoesList;

    public RhFuncao()
    {
    }

    public RhFuncao(Integer pkFuncao)
    {
        this.pkFuncao = pkFuncao;
    }

    public RhFuncao(Integer pkFuncao, String descricao)
    {
        this.pkFuncao = pkFuncao;
        this.descricao = descricao;
    }

    public Integer getPkFuncao()
    {
        return pkFuncao;
    }

    public void setPkFuncao(Integer pkFuncao)
    {
        this.pkFuncao = pkFuncao;
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
        hash += (pkFuncao != null ? pkFuncao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RhFuncao))
        {
            return false;
        }
        RhFuncao other = (RhFuncao) object;
        if ((this.pkFuncao == null && other.pkFuncao != null) || (this.pkFuncao != null && !this.pkFuncao.equals(other.pkFuncao)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.RhFuncao[ pkFuncao=" + pkFuncao + " ]";
    }
    
}
