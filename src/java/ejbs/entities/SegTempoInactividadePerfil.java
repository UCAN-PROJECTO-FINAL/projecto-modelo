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
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author smakambo
 */
@Entity
@Table(name = "seg_tempo_inactividade_perfil")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "SegTempoInactividadePerfil.findAll", query = "SELECT s FROM SegTempoInactividadePerfil s"),
    @NamedQuery(name = "SegTempoInactividadePerfil.findByPkSegTempoInactividadePerfil", query = "SELECT s FROM SegTempoInactividadePerfil s WHERE s.pkSegTempoInactividadePerfil = :pkSegTempoInactividadePerfil"),
    @NamedQuery(name = "SegTempoInactividadePerfil.findByFkSegPerfil", query = "SELECT s FROM SegTempoInactividadePerfil s WHERE s.fkSegPerfil = :fkSegPerfil"),
    @NamedQuery(name = "SegTempoInactividadePerfil.findByTempo", query = "SELECT s FROM SegTempoInactividadePerfil s WHERE s.tempo = :tempo")
})
public class SegTempoInactividadePerfil implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_seg_tempo_inactividade_perfil")
    private Integer pkSegTempoInactividadePerfil;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fk_seg_perfil")
    private int fkSegPerfil;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tempo")
    private int tempo;

    public SegTempoInactividadePerfil()
    {
    }

    public SegTempoInactividadePerfil(Integer pkSegTempoInactividadePerfil)
    {
        this.pkSegTempoInactividadePerfil = pkSegTempoInactividadePerfil;
    }

    public SegTempoInactividadePerfil(Integer pkSegTempoInactividadePerfil, int fkSegPerfil, int tempo)
    {
        this.pkSegTempoInactividadePerfil = pkSegTempoInactividadePerfil;
        this.fkSegPerfil = fkSegPerfil;
        this.tempo = tempo;
    }

    public Integer getPkSegTempoInactividadePerfil()
    {
        return pkSegTempoInactividadePerfil;
    }

    public void setPkSegTempoInactividadePerfil(Integer pkSegTempoInactividadePerfil)
    {
        this.pkSegTempoInactividadePerfil = pkSegTempoInactividadePerfil;
    }

    public int getFkSegPerfil()
    {
        return fkSegPerfil;
    }

    public void setFkSegPerfil(int fkSegPerfil)
    {
        this.fkSegPerfil = fkSegPerfil;
    }

    public int getTempo()
    {
        return tempo;
    }

    public void setTempo(int tempo)
    {
        this.tempo = tempo;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (pkSegTempoInactividadePerfil != null ? pkSegTempoInactividadePerfil.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SegTempoInactividadePerfil))
        {
            return false;
        }
        SegTempoInactividadePerfil other = (SegTempoInactividadePerfil) object;
        if ((this.pkSegTempoInactividadePerfil == null && other.pkSegTempoInactividadePerfil != null) || (this.pkSegTempoInactividadePerfil != null && !this.pkSegTempoInactividadePerfil.equals(other.pkSegTempoInactividadePerfil)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.SegTempoInactividadePerfil[ pkSegTempoInactividadePerfil=" + pkSegTempoInactividadePerfil + " ]";
    }
    
}
