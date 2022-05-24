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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author smakambo
 */
@Entity
@Table(name = "estrutura_logica_fisica")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "EstruturaLogicaFisica.findAll", query = "SELECT e FROM EstruturaLogicaFisica e"),
    @NamedQuery(name = "EstruturaLogicaFisica.findByPkEstruturaLogicaFisica", query = "SELECT e FROM EstruturaLogicaFisica e WHERE e.pkEstruturaLogicaFisica = :pkEstruturaLogicaFisica"),
    @NamedQuery(name = "EstruturaLogicaFisica.findByData", query = "SELECT e FROM EstruturaLogicaFisica e WHERE e.data = :data"),
    @NamedQuery(name = "EstruturaLogicaFisica.findByHora", query = "SELECT e FROM EstruturaLogicaFisica e WHERE e.hora = :hora")
})
public class EstruturaLogicaFisica implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_estrutura_logica_fisica")
    private Integer pkEstruturaLogicaFisica;
    @Column(name = "data")
    @Temporal(TemporalType.DATE)
    private Date data;
    @Column(name = "hora")
    @Temporal(TemporalType.TIME)
    private Date hora;
    @JoinColumn(name = "fk_estrutura_fisica", referencedColumnName = "pk_estrutura_fisica")
    @ManyToOne(optional = false)
    private EstruturaFisica fkEstruturaFisica;
    @JoinColumn(name = "fk_estrutura_logica", referencedColumnName = "pk_estrutura_logica")
    @ManyToOne(optional = false)
    private EstruturaLogica fkEstruturaLogica;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkEstruturaLogicaFisica")
    private List<EstruturaLogicaFisicaHorario> estruturaLogicaFisicaHorarioList;

    public EstruturaLogicaFisica()
    {
    }

    public EstruturaLogicaFisica(Integer pkEstruturaLogicaFisica)
    {
        this.pkEstruturaLogicaFisica = pkEstruturaLogicaFisica;
    }

    public Integer getPkEstruturaLogicaFisica()
    {
        return pkEstruturaLogicaFisica;
    }

    public void setPkEstruturaLogicaFisica(Integer pkEstruturaLogicaFisica)
    {
        this.pkEstruturaLogicaFisica = pkEstruturaLogicaFisica;
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

    public EstruturaFisica getFkEstruturaFisica()
    {
        return fkEstruturaFisica;
    }

    public void setFkEstruturaFisica(EstruturaFisica fkEstruturaFisica)
    {
        this.fkEstruturaFisica = fkEstruturaFisica;
    }

    public EstruturaLogica getFkEstruturaLogica()
    {
        return fkEstruturaLogica;
    }

    public void setFkEstruturaLogica(EstruturaLogica fkEstruturaLogica)
    {
        this.fkEstruturaLogica = fkEstruturaLogica;
    }

    @XmlTransient
    public List<EstruturaLogicaFisicaHorario> getEstruturaLogicaFisicaHorarioList()
    {
        return estruturaLogicaFisicaHorarioList;
    }

    public void setEstruturaLogicaFisicaHorarioList(List<EstruturaLogicaFisicaHorario> estruturaLogicaFisicaHorarioList)
    {
        this.estruturaLogicaFisicaHorarioList = estruturaLogicaFisicaHorarioList;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (pkEstruturaLogicaFisica != null ? pkEstruturaLogicaFisica.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstruturaLogicaFisica))
        {
            return false;
        }
        EstruturaLogicaFisica other = (EstruturaLogicaFisica) object;
        if ((this.pkEstruturaLogicaFisica == null && other.pkEstruturaLogicaFisica != null) || (this.pkEstruturaLogicaFisica != null && !this.pkEstruturaLogicaFisica.equals(other.pkEstruturaLogicaFisica)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.EstruturaLogicaFisica[ pkEstruturaLogicaFisica=" + pkEstruturaLogicaFisica + " ]";
    }
    
}
