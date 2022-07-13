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
import javax.validation.constraints.Size;

/**
 *
 * @author smakambo
 */
@Embeddable
public class BbtEditoraLocLocalidadePK implements Serializable
{

    @Basic(optional = false)
    @NotNull
    @Column(name = "fk_bbt_editora", nullable = false)
    private int fkBbtEditora;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "fk_loc_localidade", nullable = false, length = 2147483647)
    private String fkLocLocalidade;

    public BbtEditoraLocLocalidadePK()
    {
    }

    public BbtEditoraLocLocalidadePK(int fkBbtEditora, String fkLocLocalidade)
    {
        this.fkBbtEditora = fkBbtEditora;
        this.fkLocLocalidade = fkLocLocalidade;
    }

    public int getFkBbtEditora()
    {
        return fkBbtEditora;
    }

    public void setFkBbtEditora(int fkBbtEditora)
    {
        this.fkBbtEditora = fkBbtEditora;
    }

    public String getFkLocLocalidade()
    {
        return fkLocLocalidade;
    }

    public void setFkLocLocalidade(String fkLocLocalidade)
    {
        this.fkLocLocalidade = fkLocLocalidade;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (int) fkBbtEditora;
        hash += (fkLocLocalidade != null ? fkLocLocalidade.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BbtEditoraLocLocalidadePK))
        {
            return false;
        }
        BbtEditoraLocLocalidadePK other = (BbtEditoraLocLocalidadePK) object;
        if (this.fkBbtEditora != other.fkBbtEditora)
            return false;
        if ((this.fkLocLocalidade == null && other.fkLocLocalidade != null) || (this.fkLocLocalidade != null && !this.fkLocLocalidade.equals(other.fkLocLocalidade)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.BbtEditoraLocLocalidadePK[ fkBbtEditora=" + fkBbtEditora + ", fkLocLocalidade=" + fkLocLocalidade + " ]";
    }
    
}
