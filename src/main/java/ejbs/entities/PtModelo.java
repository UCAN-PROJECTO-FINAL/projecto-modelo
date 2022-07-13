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
@Table(name = "pt_modelo", catalog = "sig_ucan_db", schema = "public")
@NamedQueries(
{
    @NamedQuery(name = "PtModelo.findAll", query = "SELECT p FROM PtModelo p"),
    @NamedQuery(name = "PtModelo.findByPkPtModelo", query = "SELECT p FROM PtModelo p WHERE p.pkPtModelo = :pkPtModelo"),
    @NamedQuery(name = "PtModelo.findByDescricao", query = "SELECT p FROM PtModelo p WHERE p.descricao = :descricao")
})
public class PtModelo implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_pt_modelo", nullable = false)
    private Integer pkPtModelo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(nullable = false, length = 2147483647)
    private String descricao;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkPtModelo")
    private List<PtBemMovel> ptBemMovelList;
    @JoinColumn(name = "fk_pt_marca", referencedColumnName = "pk_pt_marca", nullable = false)
    @ManyToOne(optional = false)
    private PtMarca fkPtMarca;

    public PtModelo()
    {
    }

    public PtModelo(Integer pkPtModelo)
    {
        this.pkPtModelo = pkPtModelo;
    }

    public PtModelo(Integer pkPtModelo, String descricao)
    {
        this.pkPtModelo = pkPtModelo;
        this.descricao = descricao;
    }

    public Integer getPkPtModelo()
    {
        return pkPtModelo;
    }

    public void setPkPtModelo(Integer pkPtModelo)
    {
        this.pkPtModelo = pkPtModelo;
    }

    public String getDescricao()
    {
        return descricao;
    }

    public void setDescricao(String descricao)
    {
        this.descricao = descricao;
    }

    public List<PtBemMovel> getPtBemMovelList()
    {
        return ptBemMovelList;
    }

    public void setPtBemMovelList(List<PtBemMovel> ptBemMovelList)
    {
        this.ptBemMovelList = ptBemMovelList;
    }

    public PtMarca getFkPtMarca()
    {
        return fkPtMarca;
    }

    public void setFkPtMarca(PtMarca fkPtMarca)
    {
        this.fkPtMarca = fkPtMarca;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (pkPtModelo != null ? pkPtModelo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PtModelo))
        {
            return false;
        }
        PtModelo other = (PtModelo) object;
        if ((this.pkPtModelo == null && other.pkPtModelo != null) || (this.pkPtModelo != null && !this.pkPtModelo.equals(other.pkPtModelo)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.PtModelo[ pkPtModelo=" + pkPtModelo + " ]";
    }
    
}
