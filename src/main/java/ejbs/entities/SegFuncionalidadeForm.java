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
@Table(name = "seg_funcionalidade_form", catalog = "sig_ucan_db", schema = "public")
@NamedQueries(
{
    @NamedQuery(name = "SegFuncionalidadeForm.findAll", query = "SELECT s FROM SegFuncionalidadeForm s"),
    @NamedQuery(name = "SegFuncionalidadeForm.findByPkSegFuncionalidadeForm", query = "SELECT s FROM SegFuncionalidadeForm s WHERE s.pkSegFuncionalidadeForm = :pkSegFuncionalidadeForm")
})
public class SegFuncionalidadeForm implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "pk_seg_funcionalidade_form", nullable = false)
    private Integer pkSegFuncionalidadeForm;
    @JoinColumn(name = "fk_seg_form", referencedColumnName = "pk_seg_funcionalidade")
    @ManyToOne
    private SegFuncionalidade fkSegForm;
    @JoinColumn(name = "fk_seg_funcionalidade", referencedColumnName = "pk_seg_funcionalidade")
    @ManyToOne
    private SegFuncionalidade fkSegFuncionalidade;

    public SegFuncionalidadeForm()
    {
    }

    public SegFuncionalidadeForm(Integer pkSegFuncionalidadeForm)
    {
        this.pkSegFuncionalidadeForm = pkSegFuncionalidadeForm;
    }

    public Integer getPkSegFuncionalidadeForm()
    {
        return pkSegFuncionalidadeForm;
    }

    public void setPkSegFuncionalidadeForm(Integer pkSegFuncionalidadeForm)
    {
        this.pkSegFuncionalidadeForm = pkSegFuncionalidadeForm;
    }

    public SegFuncionalidade getFkSegForm()
    {
        return fkSegForm;
    }

    public void setFkSegForm(SegFuncionalidade fkSegForm)
    {
        this.fkSegForm = fkSegForm;
    }

    public SegFuncionalidade getFkSegFuncionalidade()
    {
        return fkSegFuncionalidade;
    }

    public void setFkSegFuncionalidade(SegFuncionalidade fkSegFuncionalidade)
    {
        this.fkSegFuncionalidade = fkSegFuncionalidade;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (pkSegFuncionalidadeForm != null ? pkSegFuncionalidadeForm.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SegFuncionalidadeForm))
        {
            return false;
        }
        SegFuncionalidadeForm other = (SegFuncionalidadeForm) object;
        if ((this.pkSegFuncionalidadeForm == null && other.pkSegFuncionalidadeForm != null) || (this.pkSegFuncionalidadeForm != null && !this.pkSegFuncionalidadeForm.equals(other.pkSegFuncionalidadeForm)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.SegFuncionalidadeForm[ pkSegFuncionalidadeForm=" + pkSegFuncionalidadeForm + " ]";
    }
    
}
