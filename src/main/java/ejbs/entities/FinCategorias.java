/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.entities;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author smakambo
 */
@Entity
@Table(name = "fin_categorias", catalog = "sig_ucan_db", schema = "public")
@NamedQueries(
{
    @NamedQuery(name = "FinCategorias.findAll", query = "SELECT f FROM FinCategorias f"),
    @NamedQuery(name = "FinCategorias.findByPkCategoria", query = "SELECT f FROM FinCategorias f WHERE f.pkCategoria = :pkCategoria"),
    @NamedQuery(name = "FinCategorias.findByDescricao", query = "SELECT f FROM FinCategorias f WHERE f.descricao = :descricao"),
    @NamedQuery(name = "FinCategorias.findByDataCadastro", query = "SELECT f FROM FinCategorias f WHERE f.dataCadastro = :dataCadastro")
})
public class FinCategorias implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "pk_categoria", nullable = false)
    private Integer pkCategoria;
    @Size(max = 200)
    @Column(length = 200)
    private String descricao;
    @Column(name = "data_cadastro")
    @Temporal(TemporalType.DATE)
    private Date dataCadastro;
    @OneToMany(mappedBy = "fkCategoria")
    private List<FinCategoriaSubcategoria> finCategoriaSubcategoriaList;
    @JoinColumn(name = "fk_tipo_categoria", referencedColumnName = "pk_tipo_categoria")
    @ManyToOne
    private FinTipoCategoria fkTipoCategoria;
    @JoinColumn(name = "fk_utilizador", referencedColumnName = "pk_seg_conta")
    @ManyToOne
    private SegConta fkUtilizador;

    public FinCategorias()
    {
    }

    public FinCategorias(Integer pkCategoria)
    {
        this.pkCategoria = pkCategoria;
    }

    public Integer getPkCategoria()
    {
        return pkCategoria;
    }

    public void setPkCategoria(Integer pkCategoria)
    {
        this.pkCategoria = pkCategoria;
    }

    public String getDescricao()
    {
        return descricao;
    }

    public void setDescricao(String descricao)
    {
        this.descricao = descricao;
    }

    public Date getDataCadastro()
    {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro)
    {
        this.dataCadastro = dataCadastro;
    }

    public List<FinCategoriaSubcategoria> getFinCategoriaSubcategoriaList()
    {
        return finCategoriaSubcategoriaList;
    }

    public void setFinCategoriaSubcategoriaList(List<FinCategoriaSubcategoria> finCategoriaSubcategoriaList)
    {
        this.finCategoriaSubcategoriaList = finCategoriaSubcategoriaList;
    }

    public FinTipoCategoria getFkTipoCategoria()
    {
        return fkTipoCategoria;
    }

    public void setFkTipoCategoria(FinTipoCategoria fkTipoCategoria)
    {
        this.fkTipoCategoria = fkTipoCategoria;
    }

    public SegConta getFkUtilizador()
    {
        return fkUtilizador;
    }

    public void setFkUtilizador(SegConta fkUtilizador)
    {
        this.fkUtilizador = fkUtilizador;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (pkCategoria != null ? pkCategoria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FinCategorias))
        {
            return false;
        }
        FinCategorias other = (FinCategorias) object;
        if ((this.pkCategoria == null && other.pkCategoria != null) || (this.pkCategoria != null && !this.pkCategoria.equals(other.pkCategoria)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.FinCategorias[ pkCategoria=" + pkCategoria + " ]";
    }
    
}
