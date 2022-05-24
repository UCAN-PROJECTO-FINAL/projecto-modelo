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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author smakambo
 */
@Entity
@Table(name = "seg_form")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "SegForm.findAll", query = "SELECT s FROM SegForm s"),
    @NamedQuery(name = "SegForm.findByPkSegForm", query = "SELECT s FROM SegForm s WHERE s.pkSegForm = :pkSegForm"),
    @NamedQuery(name = "SegForm.findByUrl", query = "SELECT s FROM SegForm s WHERE s.url = :url")
})
public class SegForm implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "pk_seg_form")
    private Integer pkSegForm;
    @Size(max = 2147483647)
    @Column(name = "url")
    private String url;

    public SegForm()
    {
    }

    public SegForm(Integer pkSegForm)
    {
        this.pkSegForm = pkSegForm;
    }

    public Integer getPkSegForm()
    {
        return pkSegForm;
    }

    public void setPkSegForm(Integer pkSegForm)
    {
        this.pkSegForm = pkSegForm;
    }

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (pkSegForm != null ? pkSegForm.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SegForm))
        {
            return false;
        }
        SegForm other = (SegForm) object;
        if ((this.pkSegForm == null && other.pkSegForm != null) || (this.pkSegForm != null && !this.pkSegForm.equals(other.pkSegForm)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.SegForm[ pkSegForm=" + pkSegForm + " ]";
    }
    
}
