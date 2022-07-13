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
@Table(name = "pt_marca", catalog = "sig_ucan_db", schema = "public")
@NamedQueries(
{
    @NamedQuery(name = "PtMarca.findAll", query = "SELECT p FROM PtMarca p"),
    @NamedQuery(name = "PtMarca.findByPkPtMarca", query = "SELECT p FROM PtMarca p WHERE p.pkPtMarca = :pkPtMarca"),
    @NamedQuery(name = "PtMarca.findByDescricao", query = "SELECT p FROM PtMarca p WHERE p.descricao = :descricao")
})
public class PtMarca implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_pt_marca", nullable = false)
    private Integer pkPtMarca;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(nullable = false, length = 2147483647)
    private String descricao;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkPtMarca")
    private List<PtModelo> ptModeloList;

    public PtMarca()
    {
    }

    public PtMarca(Integer pkPtMarca)
    {
        this.pkPtMarca = pkPtMarca;
    }

    public PtMarca(Integer pkPtMarca, String descricao)
    {
        this.pkPtMarca = pkPtMarca;
        this.descricao = descricao;
    }

    public Integer getPkPtMarca()
    {
        return pkPtMarca;
    }

    public void setPkPtMarca(Integer pkPtMarca)
    {
        this.pkPtMarca = pkPtMarca;
    }

    public String getDescricao()
    {
        return descricao;
    }

    public void setDescricao(String descricao)
    {
        this.descricao = descricao;
    }

    public List<PtModelo> getPtModeloList()
    {
        return ptModeloList;
    }

    public void setPtModeloList(List<PtModelo> ptModeloList)
    {
        this.ptModeloList = ptModeloList;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (pkPtMarca != null ? pkPtMarca.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PtMarca))
        {
            return false;
        }
        PtMarca other = (PtMarca) object;
        if ((this.pkPtMarca == null && other.pkPtMarca != null) || (this.pkPtMarca != null && !this.pkPtMarca.equals(other.pkPtMarca)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.PtMarca[ pkPtMarca=" + pkPtMarca + " ]";
    }
    
}
