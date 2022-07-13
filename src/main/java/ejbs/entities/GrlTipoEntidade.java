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

/**
 *
 * @author smakambo
 */
@Entity
@Table(name = "grl_tipo_entidade", catalog = "sig_ucan_db", schema = "public")
@NamedQueries(
{
    @NamedQuery(name = "GrlTipoEntidade.findAll", query = "SELECT g FROM GrlTipoEntidade g"),
    @NamedQuery(name = "GrlTipoEntidade.findByPkTipoEntidade", query = "SELECT g FROM GrlTipoEntidade g WHERE g.pkTipoEntidade = :pkTipoEntidade"),
    @NamedQuery(name = "GrlTipoEntidade.findByDescricao", query = "SELECT g FROM GrlTipoEntidade g WHERE g.descricao = :descricao")
})
public class GrlTipoEntidade implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "pk_tipo_entidade", nullable = false)
    private Integer pkTipoEntidade;
    @Size(max = 2147483647)
    @Column(length = 2147483647)
    private String descricao;
    @OneToMany(mappedBy = "fkTipoEntidade")
    private List<GrlEntidade> grlEntidadeList;

    public GrlTipoEntidade()
    {
    }

    public GrlTipoEntidade(Integer pkTipoEntidade)
    {
        this.pkTipoEntidade = pkTipoEntidade;
    }

    public Integer getPkTipoEntidade()
    {
        return pkTipoEntidade;
    }

    public void setPkTipoEntidade(Integer pkTipoEntidade)
    {
        this.pkTipoEntidade = pkTipoEntidade;
    }

    public String getDescricao()
    {
        return descricao;
    }

    public void setDescricao(String descricao)
    {
        this.descricao = descricao;
    }

    public List<GrlEntidade> getGrlEntidadeList()
    {
        return grlEntidadeList;
    }

    public void setGrlEntidadeList(List<GrlEntidade> grlEntidadeList)
    {
        this.grlEntidadeList = grlEntidadeList;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (pkTipoEntidade != null ? pkTipoEntidade.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GrlTipoEntidade))
        {
            return false;
        }
        GrlTipoEntidade other = (GrlTipoEntidade) object;
        if ((this.pkTipoEntidade == null && other.pkTipoEntidade != null) || (this.pkTipoEntidade != null && !this.pkTipoEntidade.equals(other.pkTipoEntidade)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.GrlTipoEntidade[ pkTipoEntidade=" + pkTipoEntidade + " ]";
    }
    
}
