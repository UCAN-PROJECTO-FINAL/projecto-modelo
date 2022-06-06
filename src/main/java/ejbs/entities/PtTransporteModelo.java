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
@Table(name = "pt_transporte_modelo", catalog = "sig_ucan_db", schema = "public")
@NamedQueries(
{
    @NamedQuery(name = "PtTransporteModelo.findAll", query = "SELECT p FROM PtTransporteModelo p"),
    @NamedQuery(name = "PtTransporteModelo.findByPkModelo", query = "SELECT p FROM PtTransporteModelo p WHERE p.pkModelo = :pkModelo"),
    @NamedQuery(name = "PtTransporteModelo.findByDescricao", query = "SELECT p FROM PtTransporteModelo p WHERE p.descricao = :descricao")
})
public class PtTransporteModelo implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "pk_modelo", nullable = false)
    private Integer pkModelo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(nullable = false, length = 50)
    private String descricao;
    @OneToMany(mappedBy = "fkModelo")
    private List<PtTransporte> ptTransporteList;
    @JoinColumn(name = "fk_marca", referencedColumnName = "pk_marca")
    @ManyToOne
    private PtTransporteMarcas fkMarca;

    public PtTransporteModelo()
    {
    }

    public PtTransporteModelo(Integer pkModelo)
    {
        this.pkModelo = pkModelo;
    }

    public PtTransporteModelo(Integer pkModelo, String descricao)
    {
        this.pkModelo = pkModelo;
        this.descricao = descricao;
    }

    public Integer getPkModelo()
    {
        return pkModelo;
    }

    public void setPkModelo(Integer pkModelo)
    {
        this.pkModelo = pkModelo;
    }

    public String getDescricao()
    {
        return descricao;
    }

    public void setDescricao(String descricao)
    {
        this.descricao = descricao;
    }

    public List<PtTransporte> getPtTransporteList()
    {
        return ptTransporteList;
    }

    public void setPtTransporteList(List<PtTransporte> ptTransporteList)
    {
        this.ptTransporteList = ptTransporteList;
    }

    public PtTransporteMarcas getFkMarca()
    {
        return fkMarca;
    }

    public void setFkMarca(PtTransporteMarcas fkMarca)
    {
        this.fkMarca = fkMarca;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (pkModelo != null ? pkModelo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PtTransporteModelo))
        {
            return false;
        }
        PtTransporteModelo other = (PtTransporteModelo) object;
        if ((this.pkModelo == null && other.pkModelo != null) || (this.pkModelo != null && !this.pkModelo.equals(other.pkModelo)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.PtTransporteModelo[ pkModelo=" + pkModelo + " ]";
    }
    
}
