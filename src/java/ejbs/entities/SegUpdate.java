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
@Table(name = "seg_update")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "SegUpdate.findAll", query = "SELECT s FROM SegUpdate s"),
    @NamedQuery(name = "SegUpdate.findByPkSegUpdates", query = "SELECT s FROM SegUpdate s WHERE s.pkSegUpdates = :pkSegUpdates"),
    @NamedQuery(name = "SegUpdate.findByFuncionalidade", query = "SELECT s FROM SegUpdate s WHERE s.funcionalidade = :funcionalidade")
})
public class SegUpdate implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_seg_updates")
    private Integer pkSegUpdates;
    @Column(name = "funcionalidade")
    @Temporal(TemporalType.TIMESTAMP)
    private Date funcionalidade;

    public SegUpdate()
    {
    }

    public SegUpdate(Integer pkSegUpdates)
    {
        this.pkSegUpdates = pkSegUpdates;
    }

    public Integer getPkSegUpdates()
    {
        return pkSegUpdates;
    }

    public void setPkSegUpdates(Integer pkSegUpdates)
    {
        this.pkSegUpdates = pkSegUpdates;
    }

    public Date getFuncionalidade()
    {
        return funcionalidade;
    }

    public void setFuncionalidade(Date funcionalidade)
    {
        this.funcionalidade = funcionalidade;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (pkSegUpdates != null ? pkSegUpdates.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SegUpdate))
        {
            return false;
        }
        SegUpdate other = (SegUpdate) object;
        if ((this.pkSegUpdates == null && other.pkSegUpdates != null) || (this.pkSegUpdates != null && !this.pkSegUpdates.equals(other.pkSegUpdates)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.SegUpdate[ pkSegUpdates=" + pkSegUpdates + " ]";
    }
    
}
