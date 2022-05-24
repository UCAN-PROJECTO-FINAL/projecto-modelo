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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author smakambo
 */
@Entity
@Table(name = "frt_transporte_agendar")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "FrtTransporteAgendar.findAll", query = "SELECT f FROM FrtTransporteAgendar f"),
    @NamedQuery(name = "FrtTransporteAgendar.findByPkAgendarTransporte", query = "SELECT f FROM FrtTransporteAgendar f WHERE f.pkAgendarTransporte = :pkAgendarTransporte"),
    @NamedQuery(name = "FrtTransporteAgendar.findByData", query = "SELECT f FROM FrtTransporteAgendar f WHERE f.data = :data"),
    @NamedQuery(name = "FrtTransporteAgendar.findByHora", query = "SELECT f FROM FrtTransporteAgendar f WHERE f.hora = :hora"),
    @NamedQuery(name = "FrtTransporteAgendar.findByIsAgendado", query = "SELECT f FROM FrtTransporteAgendar f WHERE f.isAgendado = :isAgendado")
})
public class FrtTransporteAgendar implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_agendar_transporte")
    private Integer pkAgendarTransporte;
    @Column(name = "data")
    @Temporal(TemporalType.DATE)
    private Date data;
    @Column(name = "hora")
    @Temporal(TemporalType.TIME)
    private Date hora;
    @Column(name = "is_agendado")
    private Boolean isAgendado;
    @OneToMany(mappedBy = "fkTransporteAgendamento")
    private List<FrtTransporteAtendimento> frtTransporteAtendimentoList;
    @JoinColumn(name = "fk_tipo_agendamento", referencedColumnName = "pk_tipo_agendamento")
    @ManyToOne
    private FrtTransporteTipoAgendamento fkTipoAgendamento;
    @JoinColumn(name = "fk_transporte", referencedColumnName = "pk_pt_transporte")
    @ManyToOne
    private PtTransporte fkTransporte;

    public FrtTransporteAgendar()
    {
    }

    public FrtTransporteAgendar(Integer pkAgendarTransporte)
    {
        this.pkAgendarTransporte = pkAgendarTransporte;
    }

    public Integer getPkAgendarTransporte()
    {
        return pkAgendarTransporte;
    }

    public void setPkAgendarTransporte(Integer pkAgendarTransporte)
    {
        this.pkAgendarTransporte = pkAgendarTransporte;
    }

    public Date getData()
    {
        return data;
    }

    public void setData(Date data)
    {
        this.data = data;
    }

    public Date getHora()
    {
        return hora;
    }

    public void setHora(Date hora)
    {
        this.hora = hora;
    }

    public Boolean getIsAgendado()
    {
        return isAgendado;
    }

    public void setIsAgendado(Boolean isAgendado)
    {
        this.isAgendado = isAgendado;
    }

    @XmlTransient
    public List<FrtTransporteAtendimento> getFrtTransporteAtendimentoList()
    {
        return frtTransporteAtendimentoList;
    }

    public void setFrtTransporteAtendimentoList(List<FrtTransporteAtendimento> frtTransporteAtendimentoList)
    {
        this.frtTransporteAtendimentoList = frtTransporteAtendimentoList;
    }

    public FrtTransporteTipoAgendamento getFkTipoAgendamento()
    {
        return fkTipoAgendamento;
    }

    public void setFkTipoAgendamento(FrtTransporteTipoAgendamento fkTipoAgendamento)
    {
        this.fkTipoAgendamento = fkTipoAgendamento;
    }

    public PtTransporte getFkTransporte()
    {
        return fkTransporte;
    }

    public void setFkTransporte(PtTransporte fkTransporte)
    {
        this.fkTransporte = fkTransporte;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (pkAgendarTransporte != null ? pkAgendarTransporte.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FrtTransporteAgendar))
        {
            return false;
        }
        FrtTransporteAgendar other = (FrtTransporteAgendar) object;
        if ((this.pkAgendarTransporte == null && other.pkAgendarTransporte != null) || (this.pkAgendarTransporte != null && !this.pkAgendarTransporte.equals(other.pkAgendarTransporte)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.FrtTransporteAgendar[ pkAgendarTransporte=" + pkAgendarTransporte + " ]";
    }
    
}
