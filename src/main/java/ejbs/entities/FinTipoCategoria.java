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
@Table(name = "fin_tipo_categoria", catalog = "sig_ucan_db", schema = "public")
@NamedQueries(
{
    @NamedQuery(name = "FinTipoCategoria.findAll", query = "SELECT f FROM FinTipoCategoria f"),
    @NamedQuery(name = "FinTipoCategoria.findByPkTipoCategoria", query = "SELECT f FROM FinTipoCategoria f WHERE f.pkTipoCategoria = :pkTipoCategoria"),
    @NamedQuery(name = "FinTipoCategoria.findByDescricao", query = "SELECT f FROM FinTipoCategoria f WHERE f.descricao = :descricao")
})
public class FinTipoCategoria implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "pk_tipo_categoria", nullable = false)
    private Integer pkTipoCategoria;
    @Size(max = 2147483647)
    @Column(length = 2147483647)
    private String descricao;
    @OneToMany(mappedBy = "fkTipoCategoria")
    private List<FinCategorias> finCategoriasList;

    public FinTipoCategoria()
    {
    }

    public FinTipoCategoria(Integer pkTipoCategoria)
    {
        this.pkTipoCategoria = pkTipoCategoria;
    }

    public Integer getPkTipoCategoria()
    {
        return pkTipoCategoria;
    }

    public void setPkTipoCategoria(Integer pkTipoCategoria)
    {
        this.pkTipoCategoria = pkTipoCategoria;
    }

    public String getDescricao()
    {
        return descricao;
    }

    public void setDescricao(String descricao)
    {
        this.descricao = descricao;
    }

    public List<FinCategorias> getFinCategoriasList()
    {
        return finCategoriasList;
    }

    public void setFinCategoriasList(List<FinCategorias> finCategoriasList)
    {
        this.finCategoriasList = finCategoriasList;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (pkTipoCategoria != null ? pkTipoCategoria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FinTipoCategoria))
        {
            return false;
        }
        FinTipoCategoria other = (FinTipoCategoria) object;
        if ((this.pkTipoCategoria == null && other.pkTipoCategoria != null) || (this.pkTipoCategoria != null && !this.pkTipoCategoria.equals(other.pkTipoCategoria)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.FinTipoCategoria[ pkTipoCategoria=" + pkTipoCategoria + " ]";
    }
    
}
