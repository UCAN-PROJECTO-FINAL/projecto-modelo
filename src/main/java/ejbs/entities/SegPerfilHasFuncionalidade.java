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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author smakambo
 */
@Entity
@Table(name = "seg_perfil_has_funcionalidade", catalog = "sig_ucan_db", schema = "public")
@NamedQueries(
{
    @NamedQuery(name = "SegPerfilHasFuncionalidade.findAll", query = "SELECT s FROM SegPerfilHasFuncionalidade s"),
    @NamedQuery(name = "SegPerfilHasFuncionalidade.findByPkSegPerfilHasFuncionalidade", query = "SELECT s FROM SegPerfilHasFuncionalidade s WHERE s.pkSegPerfilHasFuncionalidade = :pkSegPerfilHasFuncionalidade"),
    @NamedQuery(name = "SegPerfilHasFuncionalidade.findByStatus", query = "SELECT s FROM SegPerfilHasFuncionalidade s WHERE s.status = :status")
})
public class SegPerfilHasFuncionalidade implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "pk_seg_perfil_has_funcionalidade", nullable = false)
    private Integer pkSegPerfilHasFuncionalidade;
    private Boolean status;
    @JoinColumn(name = "fk_seg_funcionalidade", referencedColumnName = "pk_seg_funcionalidade")
    @ManyToOne
    private SegFuncionalidade fkSegFuncionalidade;
    @JoinColumn(name = "fk_seg_perfil", referencedColumnName = "pk_seg_perfil")
    @ManyToOne
    private SegPerfil fkSegPerfil;

    public SegPerfilHasFuncionalidade()
    {
    }

    public SegPerfilHasFuncionalidade(Integer pkSegPerfilHasFuncionalidade)
    {
        this.pkSegPerfilHasFuncionalidade = pkSegPerfilHasFuncionalidade;
    }

    public Integer getPkSegPerfilHasFuncionalidade()
    {
        return pkSegPerfilHasFuncionalidade;
    }

    public void setPkSegPerfilHasFuncionalidade(Integer pkSegPerfilHasFuncionalidade)
    {
        this.pkSegPerfilHasFuncionalidade = pkSegPerfilHasFuncionalidade;
    }

    public Boolean getStatus()
    {
        return status;
    }

    public void setStatus(Boolean status)
    {
        this.status = status;
    }

    public SegFuncionalidade getFkSegFuncionalidade()
    {
        return fkSegFuncionalidade;
    }

    public void setFkSegFuncionalidade(SegFuncionalidade fkSegFuncionalidade)
    {
        this.fkSegFuncionalidade = fkSegFuncionalidade;
    }

    public SegPerfil getFkSegPerfil()
    {
        return fkSegPerfil;
    }

    public void setFkSegPerfil(SegPerfil fkSegPerfil)
    {
        this.fkSegPerfil = fkSegPerfil;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (pkSegPerfilHasFuncionalidade != null ? pkSegPerfilHasFuncionalidade.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SegPerfilHasFuncionalidade))
        {
            return false;
        }
        SegPerfilHasFuncionalidade other = (SegPerfilHasFuncionalidade) object;
        if ((this.pkSegPerfilHasFuncionalidade == null && other.pkSegPerfilHasFuncionalidade != null) || (this.pkSegPerfilHasFuncionalidade != null && !this.pkSegPerfilHasFuncionalidade.equals(other.pkSegPerfilHasFuncionalidade)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.SegPerfilHasFuncionalidade[ pkSegPerfilHasFuncionalidade=" + pkSegPerfilHasFuncionalidade + " ]";
    }
    
}
