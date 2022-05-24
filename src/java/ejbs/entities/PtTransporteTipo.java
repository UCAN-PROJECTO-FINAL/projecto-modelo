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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author smakambo
 */
@Entity
@Table(name = "pt_transporte_tipo")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "PtTransporteTipo.findAll", query = "SELECT p FROM PtTransporteTipo p"),
    @NamedQuery(name = "PtTransporteTipo.findByPkTipoTransporte", query = "SELECT p FROM PtTransporteTipo p WHERE p.pkTipoTransporte = :pkTipoTransporte"),
    @NamedQuery(name = "PtTransporteTipo.findByDescricao", query = "SELECT p FROM PtTransporteTipo p WHERE p.descricao = :descricao")
})
public class PtTransporteTipo implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "pk_tipo_transporte")
    private Integer pkTipoTransporte;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "descricao")
    private String descricao;
    @OneToMany(mappedBy = "fkPtTipoTransporte")
    private List<FrtConfiguracoes> frtConfiguracoesList;
    @OneToMany(mappedBy = "fkTipoTransporte")
    private List<PtTransporte> ptTransporteList;
    @OneToMany(mappedBy = "fkTipoTransporte")
    private List<FrtTransporteSolicitacao> frtTransporteSolicitacaoList;

    public PtTransporteTipo()
    {
    }

    public PtTransporteTipo(Integer pkTipoTransporte)
    {
        this.pkTipoTransporte = pkTipoTransporte;
    }

    public PtTransporteTipo(Integer pkTipoTransporte, String descricao)
    {
        this.pkTipoTransporte = pkTipoTransporte;
        this.descricao = descricao;
    }

    public Integer getPkTipoTransporte()
    {
        return pkTipoTransporte;
    }

    public void setPkTipoTransporte(Integer pkTipoTransporte)
    {
        this.pkTipoTransporte = pkTipoTransporte;
    }

    public String getDescricao()
    {
        return descricao;
    }

    public void setDescricao(String descricao)
    {
        this.descricao = descricao;
    }

    @XmlTransient
    public List<FrtConfiguracoes> getFrtConfiguracoesList()
    {
        return frtConfiguracoesList;
    }

    public void setFrtConfiguracoesList(List<FrtConfiguracoes> frtConfiguracoesList)
    {
        this.frtConfiguracoesList = frtConfiguracoesList;
    }

    @XmlTransient
    public List<PtTransporte> getPtTransporteList()
    {
        return ptTransporteList;
    }

    public void setPtTransporteList(List<PtTransporte> ptTransporteList)
    {
        this.ptTransporteList = ptTransporteList;
    }

    @XmlTransient
    public List<FrtTransporteSolicitacao> getFrtTransporteSolicitacaoList()
    {
        return frtTransporteSolicitacaoList;
    }

    public void setFrtTransporteSolicitacaoList(List<FrtTransporteSolicitacao> frtTransporteSolicitacaoList)
    {
        this.frtTransporteSolicitacaoList = frtTransporteSolicitacaoList;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (pkTipoTransporte != null ? pkTipoTransporte.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PtTransporteTipo))
        {
            return false;
        }
        PtTransporteTipo other = (PtTransporteTipo) object;
        if ((this.pkTipoTransporte == null && other.pkTipoTransporte != null) || (this.pkTipoTransporte != null && !this.pkTipoTransporte.equals(other.pkTipoTransporte)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.PtTransporteTipo[ pkTipoTransporte=" + pkTipoTransporte + " ]";
    }
    
}
