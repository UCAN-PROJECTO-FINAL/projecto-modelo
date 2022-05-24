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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "frt_transporte_tipo_agendamento")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "FrtTransporteTipoAgendamento.findAll", query = "SELECT f FROM FrtTransporteTipoAgendamento f"),
    @NamedQuery(name = "FrtTransporteTipoAgendamento.findByPkTipoAgendamento", query = "SELECT f FROM FrtTransporteTipoAgendamento f WHERE f.pkTipoAgendamento = :pkTipoAgendamento"),
    @NamedQuery(name = "FrtTransporteTipoAgendamento.findByDescricao", query = "SELECT f FROM FrtTransporteTipoAgendamento f WHERE f.descricao = :descricao")
})
public class FrtTransporteTipoAgendamento implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_tipo_agendamento")
    private Integer pkTipoAgendamento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "descricao")
    private String descricao;
    @OneToMany(mappedBy = "fkFrtTipoAgendamento")
    private List<FrtConfiguracoes> frtConfiguracoesList;
    @OneToMany(mappedBy = "fkTipoAgendamento")
    private List<FrtTransporteAgendar> frtTransporteAgendarList;

    public FrtTransporteTipoAgendamento()
    {
    }

    public FrtTransporteTipoAgendamento(Integer pkTipoAgendamento)
    {
        this.pkTipoAgendamento = pkTipoAgendamento;
    }

    public FrtTransporteTipoAgendamento(Integer pkTipoAgendamento, String descricao)
    {
        this.pkTipoAgendamento = pkTipoAgendamento;
        this.descricao = descricao;
    }

    public Integer getPkTipoAgendamento()
    {
        return pkTipoAgendamento;
    }

    public void setPkTipoAgendamento(Integer pkTipoAgendamento)
    {
        this.pkTipoAgendamento = pkTipoAgendamento;
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
    public List<FrtTransporteAgendar> getFrtTransporteAgendarList()
    {
        return frtTransporteAgendarList;
    }

    public void setFrtTransporteAgendarList(List<FrtTransporteAgendar> frtTransporteAgendarList)
    {
        this.frtTransporteAgendarList = frtTransporteAgendarList;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (pkTipoAgendamento != null ? pkTipoAgendamento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FrtTransporteTipoAgendamento))
        {
            return false;
        }
        FrtTransporteTipoAgendamento other = (FrtTransporteTipoAgendamento) object;
        if ((this.pkTipoAgendamento == null && other.pkTipoAgendamento != null) || (this.pkTipoAgendamento != null && !this.pkTipoAgendamento.equals(other.pkTipoAgendamento)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.FrtTransporteTipoAgendamento[ pkTipoAgendamento=" + pkTipoAgendamento + " ]";
    }
    
}
