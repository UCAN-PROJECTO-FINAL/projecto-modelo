/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.entities;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author smakambo
 */
@Entity
@Table(name = "seg_perfil_associado", catalog = "sig_ucan_db", schema = "public")
@NamedQueries(
{
    @NamedQuery(name = "SegPerfilAssociado.findAll", query = "SELECT s FROM SegPerfilAssociado s"),
    @NamedQuery(name = "SegPerfilAssociado.findByIdPerfilFilho", query = "SELECT s FROM SegPerfilAssociado s WHERE s.segPerfilAssociadoPK.idPerfilFilho = :idPerfilFilho"),
    @NamedQuery(name = "SegPerfilAssociado.findByIdPerfilPai", query = "SELECT s FROM SegPerfilAssociado s WHERE s.segPerfilAssociadoPK.idPerfilPai = :idPerfilPai"),
    @NamedQuery(name = "SegPerfilAssociado.findByStatus", query = "SELECT s FROM SegPerfilAssociado s WHERE s.status = :status")
})
public class SegPerfilAssociado implements Serializable
{

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected SegPerfilAssociadoPK segPerfilAssociadoPK;
    private Boolean status;
    @JoinColumn(name = "id_perfil_filho", referencedColumnName = "pk_seg_perfil", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private SegPerfil segPerfil;
    @JoinColumn(name = "id_perfil_pai", referencedColumnName = "pk_seg_perfil", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private SegPerfil segPerfil1;

    public SegPerfilAssociado()
    {
    }

    public SegPerfilAssociado(SegPerfilAssociadoPK segPerfilAssociadoPK)
    {
        this.segPerfilAssociadoPK = segPerfilAssociadoPK;
    }

    public SegPerfilAssociado(int idPerfilFilho, int idPerfilPai)
    {
        this.segPerfilAssociadoPK = new SegPerfilAssociadoPK(idPerfilFilho, idPerfilPai);
    }

    public SegPerfilAssociadoPK getSegPerfilAssociadoPK()
    {
        return segPerfilAssociadoPK;
    }

    public void setSegPerfilAssociadoPK(SegPerfilAssociadoPK segPerfilAssociadoPK)
    {
        this.segPerfilAssociadoPK = segPerfilAssociadoPK;
    }

    public Boolean getStatus()
    {
        return status;
    }

    public void setStatus(Boolean status)
    {
        this.status = status;
    }

    public SegPerfil getSegPerfil()
    {
        return segPerfil;
    }

    public void setSegPerfil(SegPerfil segPerfil)
    {
        this.segPerfil = segPerfil;
    }

    public SegPerfil getSegPerfil1()
    {
        return segPerfil1;
    }

    public void setSegPerfil1(SegPerfil segPerfil1)
    {
        this.segPerfil1 = segPerfil1;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (segPerfilAssociadoPK != null ? segPerfilAssociadoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SegPerfilAssociado))
        {
            return false;
        }
        SegPerfilAssociado other = (SegPerfilAssociado) object;
        if ((this.segPerfilAssociadoPK == null && other.segPerfilAssociadoPK != null) || (this.segPerfilAssociadoPK != null && !this.segPerfilAssociadoPK.equals(other.segPerfilAssociadoPK)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.SegPerfilAssociado[ segPerfilAssociadoPK=" + segPerfilAssociadoPK + " ]";
    }
    
}
