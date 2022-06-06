/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author smakambo
 */
@Entity
@Table(name = "pt_tipo_saida_bem", catalog = "sig_ucan_db", schema = "public")
@NamedQueries(
{
    @NamedQuery(name = "PtTipoSaidaBem.findAll", query = "SELECT p FROM PtTipoSaidaBem p"),
    @NamedQuery(name = "PtTipoSaidaBem.findByPkPtTipoSaidaBem", query = "SELECT p FROM PtTipoSaidaBem p WHERE p.pkPtTipoSaidaBem = :pkPtTipoSaidaBem"),
    @NamedQuery(name = "PtTipoSaidaBem.findByDescricao", query = "SELECT p FROM PtTipoSaidaBem p WHERE p.descricao = :descricao")
})
public class PtTipoSaidaBem implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_pt_tipo_saida_bem", nullable = false)
    private Integer pkPtTipoSaidaBem;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(nullable = false, length = 2147483647)
    private String descricao;

    public PtTipoSaidaBem()
    {
    }

    public PtTipoSaidaBem(Integer pkPtTipoSaidaBem)
    {
        this.pkPtTipoSaidaBem = pkPtTipoSaidaBem;
    }

    public PtTipoSaidaBem(Integer pkPtTipoSaidaBem, String descricao)
    {
        this.pkPtTipoSaidaBem = pkPtTipoSaidaBem;
        this.descricao = descricao;
    }

    public Integer getPkPtTipoSaidaBem()
    {
        return pkPtTipoSaidaBem;
    }

    public void setPkPtTipoSaidaBem(Integer pkPtTipoSaidaBem)
    {
        this.pkPtTipoSaidaBem = pkPtTipoSaidaBem;
    }

    public String getDescricao()
    {
        return descricao;
    }

    public void setDescricao(String descricao)
    {
        this.descricao = descricao;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (pkPtTipoSaidaBem != null ? pkPtTipoSaidaBem.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PtTipoSaidaBem))
        {
            return false;
        }
        PtTipoSaidaBem other = (PtTipoSaidaBem) object;
        if ((this.pkPtTipoSaidaBem == null && other.pkPtTipoSaidaBem != null) || (this.pkPtTipoSaidaBem != null && !this.pkPtTipoSaidaBem.equals(other.pkPtTipoSaidaBem)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.PtTipoSaidaBem[ pkPtTipoSaidaBem=" + pkPtTipoSaidaBem + " ]";
    }
    
}
