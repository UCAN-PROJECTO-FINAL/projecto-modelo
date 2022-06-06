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

/**
 *
 * @author smakambo
 */
@Entity
@Table(name = "estrutura_grl_updade", catalog = "sig_ucan_db", schema = "public")
@NamedQueries(
{
    @NamedQuery(name = "EstruturaGrlUpdade.findAll", query = "SELECT e FROM EstruturaGrlUpdade e"),
    @NamedQuery(name = "EstruturaGrlUpdade.findByPkEstruturaGrlUpdate", query = "SELECT e FROM EstruturaGrlUpdade e WHERE e.pkEstruturaGrlUpdate = :pkEstruturaGrlUpdate"),
    @NamedQuery(name = "EstruturaGrlUpdade.findByEstruturaLogicaDate", query = "SELECT e FROM EstruturaGrlUpdade e WHERE e.estruturaLogicaDate = :estruturaLogicaDate"),
    @NamedQuery(name = "EstruturaGrlUpdade.findByEstruturaFisicaDate", query = "SELECT e FROM EstruturaGrlUpdade e WHERE e.estruturaFisicaDate = :estruturaFisicaDate")
})
public class EstruturaGrlUpdade implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_estrutura_grl_update", nullable = false)
    private Integer pkEstruturaGrlUpdate;
    @Column(name = "estrutura_logica_date")
    @Temporal(TemporalType.DATE)
    private Date estruturaLogicaDate;
    @Column(name = "estrutura_fisica_date")
    @Temporal(TemporalType.DATE)
    private Date estruturaFisicaDate;

    public EstruturaGrlUpdade()
    {
    }

    public EstruturaGrlUpdade(Integer pkEstruturaGrlUpdate)
    {
        this.pkEstruturaGrlUpdate = pkEstruturaGrlUpdate;
    }

    public Integer getPkEstruturaGrlUpdate()
    {
        return pkEstruturaGrlUpdate;
    }

    public void setPkEstruturaGrlUpdate(Integer pkEstruturaGrlUpdate)
    {
        this.pkEstruturaGrlUpdate = pkEstruturaGrlUpdate;
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
        hash += (pkEstruturaGrlUpdate != null ? pkEstruturaGrlUpdate.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstruturaGrlUpdade))
        {
            return false;
        }
        EstruturaGrlUpdade other = (EstruturaGrlUpdade) object;
        if ((this.pkEstruturaGrlUpdate == null && other.pkEstruturaGrlUpdate != null) || (this.pkEstruturaGrlUpdate != null && !this.pkEstruturaGrlUpdate.equals(other.pkEstruturaGrlUpdate)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.EstruturaGrlUpdade[ pkEstruturaGrlUpdate=" + pkEstruturaGrlUpdate + " ]";
    }
    
}
