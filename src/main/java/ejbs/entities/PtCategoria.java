/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
@Table(name = "pt_categoria", catalog = "sig_ucan_db", schema = "public")
@NamedQueries(
{
    @NamedQuery(name = "PtCategoria.findAll", query = "SELECT p FROM PtCategoria p"),
    @NamedQuery(name = "PtCategoria.findByPkPtCategoria", query = "SELECT p FROM PtCategoria p WHERE p.pkPtCategoria = :pkPtCategoria"),
    @NamedQuery(name = "PtCategoria.findByDescricao", query = "SELECT p FROM PtCategoria p WHERE p.descricao = :descricao"),
    @NamedQuery(name = "PtCategoria.findByCod", query = "SELECT p FROM PtCategoria p WHERE p.cod = :cod")
})
public class PtCategoria implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "pk_pt_categoria", nullable = false, length = 2147483647)
    private String pkPtCategoria;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(nullable = false, length = 2147483647)
    private String descricao;
    @Size(max = 2147483647)
    @Column(length = 2147483647)
    private String cod;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkPtCategoria")
    private List<PtBemEntrada> ptBemEntradaList;
    @OneToMany(mappedBy = "fkPtCategoria")
    private List<PtCategoria> ptCategoriaList;
    @JoinColumn(name = "fk_pt_categoria", referencedColumnName = "pk_pt_categoria")
    @ManyToOne
    private PtCategoria fkPtCategoria;

    public PtCategoria()
    {
    }

    public PtCategoria(String pkPtCategoria)
    {
        this.pkPtCategoria = pkPtCategoria;
    }

    public PtCategoria(String pkPtCategoria, String descricao)
    {
        this.pkPtCategoria = pkPtCategoria;
        this.descricao = descricao;
    }

    public String getPkPtCategoria()
    {
        return pkPtCategoria;
    }

    public void setPkPtCategoria(String pkPtCategoria)
    {
        this.pkPtCategoria = pkPtCategoria;
    }

    public String getDescricao()
    {
        return descricao;
    }

    public void setDescricao(String descricao)
    {
        this.descricao = descricao;
    }

    public String getCod()
    {
        return cod;
    }

    public void setCod(String cod)
    {
        this.cod = cod;
    }

    public List<PtBemEntrada> getPtBemEntradaList()
    {
        return ptBemEntradaList;
    }

    public void setPtBemEntradaList(List<PtBemEntrada> ptBemEntradaList)
    {
        this.ptBemEntradaList = ptBemEntradaList;
    }

    public List<PtCategoria> getPtCategoriaList()
    {
        return ptCategoriaList;
    }

    public void setPtCategoriaList(List<PtCategoria> ptCategoriaList)
    {
        this.ptCategoriaList = ptCategoriaList;
    }

    public PtCategoria getFkPtCategoria()
    {
        return fkPtCategoria;
    }

    public void setFkPtCategoria(PtCategoria fkPtCategoria)
    {
        this.fkPtCategoria = fkPtCategoria;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (pkPtCategoria != null ? pkPtCategoria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PtCategoria))
        {
            return false;
        }
        PtCategoria other = (PtCategoria) object;
        if ((this.pkPtCategoria == null && other.pkPtCategoria != null) || (this.pkPtCategoria != null && !this.pkPtCategoria.equals(other.pkPtCategoria)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.PtCategoria[ pkPtCategoria=" + pkPtCategoria + " ]";
    }
    
}
