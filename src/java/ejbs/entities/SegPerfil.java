/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "seg_perfil")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "SegPerfil.findAll", query = "SELECT s FROM SegPerfil s"),
    @NamedQuery(name = "SegPerfil.findByPkSegPerfil", query = "SELECT s FROM SegPerfil s WHERE s.pkSegPerfil = :pkSegPerfil"),
    @NamedQuery(name = "SegPerfil.findByDescricao", query = "SELECT s FROM SegPerfil s WHERE s.descricao = :descricao")
})
public class SegPerfil implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "pk_seg_perfil")
    private Integer pkSegPerfil;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "descricao")
    private String descricao;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "segPerfil")
    private List<SegPerfilAssociado> segPerfilAssociadoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "segPerfil1")
    private List<SegPerfilAssociado> segPerfilAssociadoList1;
    @OneToMany(mappedBy = "fkSegPerfil")
    private List<SegConfiguracoes> segConfiguracoesList;
    @JoinColumn(name = "fk_seg_pagina_arranque", referencedColumnName = "pk_seg_pagina_arranque")
    @ManyToOne
    private SegPaginaArranque fkSegPaginaArranque;
    @OneToMany(mappedBy = "fkSegPerfil")
    private List<SegPerfilHasFuncionalidade> segPerfilHasFuncionalidadeList;
    @OneToMany(mappedBy = "fkSegPerfil")
    private List<SegConta> segContaList;

    public SegPerfil()
    {
    }

    public SegPerfil(Integer pkSegPerfil)
    {
        this.pkSegPerfil = pkSegPerfil;
    }

    public SegPerfil(Integer pkSegPerfil, String descricao)
    {
        this.pkSegPerfil = pkSegPerfil;
        this.descricao = descricao;
    }

    public Integer getPkSegPerfil()
    {
        return pkSegPerfil;
    }

    public void setPkSegPerfil(Integer pkSegPerfil)
    {
        this.pkSegPerfil = pkSegPerfil;
    }

    public String getDescricao()
    {
        return descricao;
    }

    public void setDescricao(String descricao)
    {
        this.descricao = descricao;
    }

    @XmlTransient
    public List<SegPerfilAssociado> getSegPerfilAssociadoList()
    {
        return segPerfilAssociadoList;
    }

    public void setSegPerfilAssociadoList(List<SegPerfilAssociado> segPerfilAssociadoList)
    {
        this.segPerfilAssociadoList = segPerfilAssociadoList;
    }

    @XmlTransient
    public List<SegPerfilAssociado> getSegPerfilAssociadoList1()
    {
        return segPerfilAssociadoList1;
    }

    public void setSegPerfilAssociadoList1(List<SegPerfilAssociado> segPerfilAssociadoList1)
    {
        this.segPerfilAssociadoList1 = segPerfilAssociadoList1;
    }

    @XmlTransient
    public List<SegConfiguracoes> getSegConfiguracoesList()
    {
        return segConfiguracoesList;
    }

    public void setSegConfiguracoesList(List<SegConfiguracoes> segConfiguracoesList)
    {
        this.segConfiguracoesList = segConfiguracoesList;
    }

    public SegPaginaArranque getFkSegPaginaArranque()
    {
        return fkSegPaginaArranque;
    }

    public void setFkSegPaginaArranque(SegPaginaArranque fkSegPaginaArranque)
    {
        this.fkSegPaginaArranque = fkSegPaginaArranque;
    }

    @XmlTransient
    public List<SegPerfilHasFuncionalidade> getSegPerfilHasFuncionalidadeList()
    {
        return segPerfilHasFuncionalidadeList;
    }

    public void setSegPerfilHasFuncionalidadeList(List<SegPerfilHasFuncionalidade> segPerfilHasFuncionalidadeList)
    {
        this.segPerfilHasFuncionalidadeList = segPerfilHasFuncionalidadeList;
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
        hash += (pkSegPerfil != null ? pkSegPerfil.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SegPerfil))
        {
            return false;
        }
        SegPerfil other = (SegPerfil) object;
        if ((this.pkSegPerfil == null && other.pkSegPerfil != null) || (this.pkSegPerfil != null && !this.pkSegPerfil.equals(other.pkSegPerfil)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.SegPerfil[ pkSegPerfil=" + pkSegPerfil + " ]";
    }
    
}
