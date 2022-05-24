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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author smakambo
 */
@Entity
@Table(name = "grl_update")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "GrlUpdate.findAll", query = "SELECT g FROM GrlUpdate g"),
    @NamedQuery(name = "GrlUpdate.findByPkGrlupdate", query = "SELECT g FROM GrlUpdate g WHERE g.pkGrlupdate = :pkGrlupdate"),
    @NamedQuery(name = "GrlUpdate.findByLocalidadesDate", query = "SELECT g FROM GrlUpdate g WHERE g.localidadesDate = :localidadesDate"),
    @NamedQuery(name = "GrlUpdate.findByEstruturaLogicaDate", query = "SELECT g FROM GrlUpdate g WHERE g.estruturaLogicaDate = :estruturaLogicaDate"),
    @NamedQuery(name = "GrlUpdate.findByEstruturaFisicaDate", query = "SELECT g FROM GrlUpdate g WHERE g.estruturaFisicaDate = :estruturaFisicaDate")
})
public class GrlUpdate implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_grlupdate")
    private Integer pkGrlupdate;
    @Column(name = "localidades_date")
    @Temporal(TemporalType.DATE)
    private Date localidadesDate;
    @Column(name = "estrutura_logica_date")
    @Temporal(TemporalType.DATE)
    private Date estruturaLogicaDate;
    @Column(name = "estrutura_fisica_date")
    @Temporal(TemporalType.DATE)
    private Date estruturaFisicaDate;

    public GrlUpdate()
    {
    }

    public GrlUpdate(Integer pkGrlupdate)
    {
        this.pkGrlupdate = pkGrlupdate;
    }

    public Integer getPkGrlupdate()
    {
        return pkGrlupdate;
    }

    public void setPkGrlupdate(Integer pkGrlupdate)
    {
        this.pkGrlupdate = pkGrlupdate;
    }

    public Date getLocalidadesDate()
    {
        return localidadesDate;
    }

    public void setLocalidadesDate(Date localidadesDate)
    {
        this.localidadesDate = localidadesDate;
    }

    public Date getEstruturaLogicaDate()
    {
        return estruturaLogicaDate;
    }

    public void setEstruturaLogicaDate(Date estruturaLogicaDate)
    {
        this.estruturaLogicaDate = estruturaLogicaDate;
    }

    public Date getEstruturaFisicaDate()
    {
        return estruturaFisicaDate;
    }

    public void setEstruturaFisicaDate(Date estruturaFisicaDate)
    {
        this.estruturaFisicaDate = estruturaFisicaDate;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (pkGrlupdate != null ? pkGrlupdate.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GrlUpdate))
        {
            return false;
        }
        GrlUpdate other = (GrlUpdate) object;
        if ((this.pkGrlupdate == null && other.pkGrlupdate != null) || (this.pkGrlupdate != null && !this.pkGrlupdate.equals(other.pkGrlupdate)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.GrlUpdate[ pkGrlupdate=" + pkGrlupdate + " ]";
    }
    
}
