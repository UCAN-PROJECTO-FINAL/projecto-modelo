/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author smakambo
 */
@Entity
@Table(name = "seg_tempo_inactividade_todos")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "SegTempoInactividadeTodos.findAll", query = "SELECT s FROM SegTempoInactividadeTodos s"),
    @NamedQuery(name = "SegTempoInactividadeTodos.findByPkSegTempoInactividadeTodos", query = "SELECT s FROM SegTempoInactividadeTodos s WHERE s.pkSegTempoInactividadeTodos = :pkSegTempoInactividadeTodos"),
    @NamedQuery(name = "SegTempoInactividadeTodos.findByTempo", query = "SELECT s FROM SegTempoInactividadeTodos s WHERE s.tempo = :tempo")
})
public class SegTempoInactividadeTodos implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_seg_tempo_inactividade_todos")
    private Integer pkSegTempoInactividadeTodos;
    @Column(name = "tempo")
    private Integer tempo;

    public SegTempoInactividadeTodos()
    {
    }

    public SegTempoInactividadeTodos(Integer pkSegTempoInactividadeTodos)
    {
        this.pkSegTempoInactividadeTodos = pkSegTempoInactividadeTodos;
    }

    public Integer getPkSegTempoInactividadeTodos()
    {
        return pkSegTempoInactividadeTodos;
    }

    public void setPkSegTempoInactividadeTodos(Integer pkSegTempoInactividadeTodos)
    {
        this.pkSegTempoInactividadeTodos = pkSegTempoInactividadeTodos;
    }

    public Integer getTempo()
    {
        return tempo;
    }

    public void setTempo(Integer tempo)
    {
        this.tempo = tempo;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (pkSegTempoInactividadeTodos != null ? pkSegTempoInactividadeTodos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SegTempoInactividadeTodos))
        {
            return false;
        }
        SegTempoInactividadeTodos other = (SegTempoInactividadeTodos) object;
        if ((this.pkSegTempoInactividadeTodos == null && other.pkSegTempoInactividadeTodos != null) || (this.pkSegTempoInactividadeTodos != null && !this.pkSegTempoInactividadeTodos.equals(other.pkSegTempoInactividadeTodos)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.SegTempoInactividadeTodos[ pkSegTempoInactividadeTodos=" + pkSegTempoInactividadeTodos + " ]";
    }
    
}
