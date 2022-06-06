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
@Table(name = "pt_transporte_marcas", catalog = "sig_ucan_db", schema = "public")
@NamedQueries(
{
    @NamedQuery(name = "PtTransporteMarcas.findAll", query = "SELECT p FROM PtTransporteMarcas p"),
    @NamedQuery(name = "PtTransporteMarcas.findByPkMarca", query = "SELECT p FROM PtTransporteMarcas p WHERE p.pkMarca = :pkMarca"),
    @NamedQuery(name = "PtTransporteMarcas.findByDescricao", query = "SELECT p FROM PtTransporteMarcas p WHERE p.descricao = :descricao")
})
public class PtTransporteMarcas implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "pk_marca", nullable = false)
    private Integer pkMarca;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(nullable = false, length = 50)
    private String descricao;
    @OneToMany(mappedBy = "fkMarca")
    private List<PtTransporteModelo> ptTransporteModeloList;

    public PtTransporteMarcas()
    {
    }

    public PtTransporteMarcas(Integer pkMarca)
    {
        this.pkMarca = pkMarca;
    }

    public PtTransporteMarcas(Integer pkMarca, String descricao)
    {
        this.pkMarca = pkMarca;
        this.descricao = descricao;
    }

    public Integer getPkMarca()
    {
        return pkMarca;
    }

    public void setPkMarca(Integer pkMarca)
    {
        this.pkMarca = pkMarca;
    }

    public String getDescricao()
    {
        return descricao;
    }

    public void setDescricao(String descricao)
    {
        this.descricao = descricao;
    }

    public List<PtTransporteModelo> getPtTransporteModeloList()
    {
        return ptTransporteModeloList;
    }

    public void setPtTransporteModeloList(List<PtTransporteModelo> ptTransporteModeloList)
    {
        this.ptTransporteModeloList = ptTransporteModeloList;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (pkMarca != null ? pkMarca.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PtTransporteMarcas))
        {
            return false;
        }
        PtTransporteMarcas other = (PtTransporteMarcas) object;
        if ((this.pkMarca == null && other.pkMarca != null) || (this.pkMarca != null && !this.pkMarca.equals(other.pkMarca)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.PtTransporteMarcas[ pkMarca=" + pkMarca + " ]";
    }
    
}
