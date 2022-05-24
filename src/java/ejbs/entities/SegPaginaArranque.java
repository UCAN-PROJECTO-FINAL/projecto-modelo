/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author smakambo
 */
@Entity
@Table(name = "seg_pagina_arranque")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "SegPaginaArranque.findAll", query = "SELECT s FROM SegPaginaArranque s"),
    @NamedQuery(name = "SegPaginaArranque.findByPkSegPaginaArranque", query = "SELECT s FROM SegPaginaArranque s WHERE s.pkSegPaginaArranque = :pkSegPaginaArranque"),
    @NamedQuery(name = "SegPaginaArranque.findByUrl", query = "SELECT s FROM SegPaginaArranque s WHERE s.url = :url")
})
public class SegPaginaArranque implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_seg_pagina_arranque")
    private Integer pkSegPaginaArranque;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "url")
    private String url;
    @OneToMany(mappedBy = "fkSegPaginaArranque")
    private List<SegPerfil> segPerfilList;
    @OneToMany(mappedBy = "fkSegPaginaArranque")
    private List<SegConta> segContaList;

    public SegPaginaArranque()
    {
    }

    public SegPaginaArranque(Integer pkSegPaginaArranque)
    {
        this.pkSegPaginaArranque = pkSegPaginaArranque;
    }

    public SegPaginaArranque(Integer pkSegPaginaArranque, String url)
    {
        this.pkSegPaginaArranque = pkSegPaginaArranque;
        this.url = url;
    }

    public Integer getPkSegPaginaArranque()
    {
        return pkSegPaginaArranque;
    }

    public void setPkSegPaginaArranque(Integer pkSegPaginaArranque)
    {
        this.pkSegPaginaArranque = pkSegPaginaArranque;
    }

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    @XmlTransient
    public List<SegPerfil> getSegPerfilList()
    {
        return segPerfilList;
    }

    public void setSegPerfilList(List<SegPerfil> segPerfilList)
    {
        this.segPerfilList = segPerfilList;
    }

    @XmlTransient
    public List<SegConta> getSegContaList()
    {
        return segContaList;
    }

    public void setSegContaList(List<SegConta> segContaList)
    {
        this.segContaList = segContaList;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (pkSegPaginaArranque != null ? pkSegPaginaArranque.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SegPaginaArranque))
        {
            return false;
        }
        SegPaginaArranque other = (SegPaginaArranque) object;
        if ((this.pkSegPaginaArranque == null && other.pkSegPaginaArranque != null) || (this.pkSegPaginaArranque != null && !this.pkSegPaginaArranque.equals(other.pkSegPaginaArranque)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.SegPaginaArranque[ pkSegPaginaArranque=" + pkSegPaginaArranque + " ]";
    }
    
}
