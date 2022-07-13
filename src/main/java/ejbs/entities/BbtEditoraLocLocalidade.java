/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
@Table(name = "bbt_editora_loc_localidade", catalog = "sig_ucan_db", schema = "public")
@NamedQueries(
{
    @NamedQuery(name = "BbtEditoraLocLocalidade.findAll", query = "SELECT b FROM BbtEditoraLocLocalidade b"),
    @NamedQuery(name = "BbtEditoraLocLocalidade.findByFkBbtEditora", query = "SELECT b FROM BbtEditoraLocLocalidade b WHERE b.bbtEditoraLocLocalidadePK.fkBbtEditora = :fkBbtEditora"),
    @NamedQuery(name = "BbtEditoraLocLocalidade.findByFkLocLocalidade", query = "SELECT b FROM BbtEditoraLocLocalidade b WHERE b.bbtEditoraLocLocalidadePK.fkLocLocalidade = :fkLocLocalidade"),
    @NamedQuery(name = "BbtEditoraLocLocalidade.findByNumOrdem", query = "SELECT b FROM BbtEditoraLocLocalidade b WHERE b.numOrdem = :numOrdem")
})
public class BbtEditoraLocLocalidade implements Serializable
{

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected BbtEditoraLocLocalidadePK bbtEditoraLocLocalidadePK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "num_ordem", nullable = false)
    private int numOrdem;
    @JoinColumn(name = "fk_bbt_editora", referencedColumnName = "pk_bbt_editora", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private BbtEditora bbtEditora;
    @JoinColumn(name = "fk_loc_localidade", referencedColumnName = "pk_loc_localidade", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private LocLocalidade locLocalidade;

    public BbtEditoraLocLocalidade()
    {
    }

    public BbtEditoraLocLocalidade(BbtEditoraLocLocalidadePK bbtEditoraLocLocalidadePK)
    {
        this.bbtEditoraLocLocalidadePK = bbtEditoraLocLocalidadePK;
    }

    public BbtEditoraLocLocalidade(BbtEditoraLocLocalidadePK bbtEditoraLocLocalidadePK, int numOrdem)
    {
        this.bbtEditoraLocLocalidadePK = bbtEditoraLocLocalidadePK;
        this.numOrdem = numOrdem;
    }

    public BbtEditoraLocLocalidade(int fkBbtEditora, String fkLocLocalidade)
    {
        this.bbtEditoraLocLocalidadePK = new BbtEditoraLocLocalidadePK(fkBbtEditora, fkLocLocalidade);
    }

    public BbtEditoraLocLocalidadePK getBbtEditoraLocLocalidadePK()
    {
        return bbtEditoraLocLocalidadePK;
    }

    public void setBbtEditoraLocLocalidadePK(BbtEditoraLocLocalidadePK bbtEditoraLocLocalidadePK)
    {
        this.bbtEditoraLocLocalidadePK = bbtEditoraLocLocalidadePK;
    }

    public int getNumOrdem()
    {
        return numOrdem;
    }

    public void setNumOrdem(int numOrdem)
    {
        this.numOrdem = numOrdem;
    }

    public BbtEditora getBbtEditora()
    {
        return bbtEditora;
    }

    public void setBbtEditora(BbtEditora bbtEditora)
    {
        this.bbtEditora = bbtEditora;
    }

    public LocLocalidade getLocLocalidade()
    {
        return locLocalidade;
    }

    public void setLocLocalidade(LocLocalidade locLocalidade)
    {
        this.locLocalidade = locLocalidade;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (bbtEditoraLocLocalidadePK != null ? bbtEditoraLocLocalidadePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BbtEditoraLocLocalidade))
        {
            return false;
        }
        BbtEditoraLocLocalidade other = (BbtEditoraLocLocalidade) object;
        if ((this.bbtEditoraLocLocalidadePK == null && other.bbtEditoraLocLocalidadePK != null) || (this.bbtEditoraLocLocalidadePK != null && !this.bbtEditoraLocLocalidadePK.equals(other.bbtEditoraLocLocalidadePK)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.BbtEditoraLocLocalidade[ bbtEditoraLocLocalidadePK=" + bbtEditoraLocLocalidadePK + " ]";
    }
    
}
