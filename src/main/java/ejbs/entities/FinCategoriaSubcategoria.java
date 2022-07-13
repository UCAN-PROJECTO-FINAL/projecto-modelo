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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "fin_categoria_subcategoria", catalog = "sig_ucan_db", schema = "public")
@NamedQueries(
{
    @NamedQuery(name = "FinCategoriaSubcategoria.findAll", query = "SELECT f FROM FinCategoriaSubcategoria f"),
    @NamedQuery(name = "FinCategoriaSubcategoria.findByPkCategoriaSubcategoria", query = "SELECT f FROM FinCategoriaSubcategoria f WHERE f.pkCategoriaSubcategoria = :pkCategoriaSubcategoria"),
    @NamedQuery(name = "FinCategoriaSubcategoria.findByDescricao", query = "SELECT f FROM FinCategoriaSubcategoria f WHERE f.descricao = :descricao")
})
public class FinCategoriaSubcategoria implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "pk_categoria_subcategoria", nullable = false)
    private Integer pkCategoriaSubcategoria;
    @Size(max = 2147483647)
    @Column(length = 2147483647)
    private String descricao;
    @OneToMany(mappedBy = "fkCategoria")
    private List<FinContasPagar> finContasPagarList;
    @JoinColumn(name = "fk_categoria", referencedColumnName = "pk_categoria")
    @ManyToOne
    private FinCategorias fkCategoria;
    @OneToMany(mappedBy = "fkCategoria")
    private List<FinContasReceber> finContasReceberList;

    public FinCategoriaSubcategoria()
    {
    }

    public FinCategoriaSubcategoria(Integer pkCategoriaSubcategoria)
    {
        this.pkCategoriaSubcategoria = pkCategoriaSubcategoria;
    }

    public Integer getPkCategoriaSubcategoria()
    {
        return pkCategoriaSubcategoria;
    }

    public void setPkCategoriaSubcategoria(Integer pkCategoriaSubcategoria)
    {
        this.pkCategoriaSubcategoria = pkCategoriaSubcategoria;
    }

    public String getDescricao()
    {
        return descricao;
    }

    public void setDescricao(String descricao)
    {
        this.descricao = descricao;
    }

    public List<FinContasPagar> getFinContasPagarList()
    {
        return finContasPagarList;
    }

    public void setFinContasPagarList(List<FinContasPagar> finContasPagarList)
    {
        this.finContasPagarList = finContasPagarList;
    }

    public FinCategorias getFkCategoria()
    {
        return fkCategoria;
    }

    public void setFkCategoria(FinCategorias fkCategoria)
    {
        this.fkCategoria = fkCategoria;
    }

    public List<FinContasReceber> getFinContasReceberList()
    {
        return finContasReceberList;
    }

    public void setFinContasReceberList(List<FinContasReceber> finContasReceberList)
    {
        this.finContasReceberList = finContasReceberList;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (pkCategoriaSubcategoria != null ? pkCategoriaSubcategoria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FinCategoriaSubcategoria))
        {
            return false;
        }
        FinCategoriaSubcategoria other = (FinCategoriaSubcategoria) object;
        if ((this.pkCategoriaSubcategoria == null && other.pkCategoriaSubcategoria != null) || (this.pkCategoriaSubcategoria != null && !this.pkCategoriaSubcategoria.equals(other.pkCategoriaSubcategoria)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.FinCategoriaSubcategoria[ pkCategoriaSubcategoria=" + pkCategoriaSubcategoria + " ]";
    }
    
}
