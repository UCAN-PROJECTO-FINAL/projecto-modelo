/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.entities;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author smakambo
 */
@Entity
@Table(name = "estrutura_logica_fisica_horario")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "EstruturaLogicaFisicaHorario.findAll", query = "SELECT e FROM EstruturaLogicaFisicaHorario e"),
    @NamedQuery(name = "EstruturaLogicaFisicaHorario.findByPkEstruturaLogicaFisicaHorario", query = "SELECT e FROM EstruturaLogicaFisicaHorario e WHERE e.pkEstruturaLogicaFisicaHorario = :pkEstruturaLogicaFisicaHorario"),
    @NamedQuery(name = "EstruturaLogicaFisicaHorario.findByData", query = "SELECT e FROM EstruturaLogicaFisicaHorario e WHERE e.data = :data"),
    @NamedQuery(name = "EstruturaLogicaFisicaHorario.findByHora", query = "SELECT e FROM EstruturaLogicaFisicaHorario e WHERE e.hora = :hora")
})
public class EstruturaLogicaFisicaHorario implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_estrutura_logica_fisica_horario")
    private Integer pkEstruturaLogicaFisicaHorario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "data")
    @Temporal(TemporalType.DATE)
    private Date data;
    @Basic(optional = false)
    @NotNull
    @Column(name = "hora")
    @Temporal(TemporalType.TIMESTAMP)
    private Date hora;
    @JoinColumn(name = "fk_estrutura_logica_fisica", referencedColumnName = "pk_estrutura_logica_fisica")
    @ManyToOne(optional = false)
    private EstruturaLogicaFisica fkEstruturaLogicaFisica;

    public EstruturaLogicaFisicaHorario()
    {
    }

    public EstruturaLogicaFisicaHorario(Integer pkEstruturaLogicaFisicaHorario)
    {
        this.pkEstruturaLogicaFisicaHorario = pkEstruturaLogicaFisicaHorario;
    }

    public EstruturaLogicaFisicaHorario(Integer pkEstruturaLogicaFisicaHorario, Date data, Date hora)
    {
        this.pkEstruturaLogicaFisicaHorario = pkEstruturaLogicaFisicaHorario;
        this.data = data;
        this.hora = hora;
    }

    public Integer getPkEstruturaLogicaFisicaHorario()
    {
        return pkEstruturaLogicaFisicaHorario;
    }

    public void setPkEstruturaLogicaFisicaHorario(Integer pkEstruturaLogicaFisicaHorario)
    {
        this.pkEstruturaLogicaFisicaHorario = pkEstruturaLogicaFisicaHorario;
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

    public EstruturaLogicaFisica getFkEstruturaLogicaFisica()
    {
        return fkEstruturaLogicaFisica;
    }

    public void setFkEstruturaLogicaFisica(EstruturaLogicaFisica fkEstruturaLogicaFisica)
    {
        this.fkEstruturaLogicaFisica = fkEstruturaLogicaFisica;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (pkEstruturaLogicaFisicaHorario != null ? pkEstruturaLogicaFisicaHorario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstruturaLogicaFisicaHorario))
        {
            return false;
        }
        EstruturaLogicaFisicaHorario other = (EstruturaLogicaFisicaHorario) object;
        if ((this.pkEstruturaLogicaFisicaHorario == null && other.pkEstruturaLogicaFisicaHorario != null) || (this.pkEstruturaLogicaFisicaHorario != null && !this.pkEstruturaLogicaFisicaHorario.equals(other.pkEstruturaLogicaFisicaHorario)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.EstruturaLogicaFisicaHorario[ pkEstruturaLogicaFisicaHorario=" + pkEstruturaLogicaFisicaHorario + " ]";
    }
    
}
