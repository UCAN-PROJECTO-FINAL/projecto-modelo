/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author smakambo
 */
@Embeddable
public class SegPerfilAssociadoPK implements Serializable
{

    @Basic(optional = false)
    @NotNull
    @Column(name = "id_perfil_filho", nullable = false)
    private int idPerfilFilho;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_perfil_pai", nullable = false)
    private int idPerfilPai;

    public SegPerfilAssociadoPK()
    {
    }

    public SegPerfilAssociadoPK(int idPerfilFilho, int idPerfilPai)
    {
        this.idPerfilFilho = idPerfilFilho;
        this.idPerfilPai = idPerfilPai;
    }

    public int getIdPerfilFilho()
    {
        return idPerfilFilho;
    }

    public void setIdPerfilFilho(int idPerfilFilho)
    {
        this.idPerfilFilho = idPerfilFilho;
    }

    public int getIdPerfilPai()
    {
        return idPerfilPai;
    }

    public void setIdPerfilPai(int idPerfilPai)
    {
        this.idPerfilPai = idPerfilPai;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (int) idPerfilFilho;
        hash += (int) idPerfilPai;
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SegPerfilAssociadoPK))
        {
            return false;
        }
        SegPerfilAssociadoPK other = (SegPerfilAssociadoPK) object;
        if (this.idPerfilFilho != other.idPerfilFilho)
            return false;
        if (this.idPerfilPai != other.idPerfilPai)
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.SegPerfilAssociadoPK[ idPerfilFilho=" + idPerfilFilho + ", idPerfilPai=" + idPerfilPai + " ]";
    }
    
}
