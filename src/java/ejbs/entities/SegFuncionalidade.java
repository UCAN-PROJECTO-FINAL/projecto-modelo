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
@Table(name = "seg_funcionalidade")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "SegFuncionalidade.findAll", query = "SELECT s FROM SegFuncionalidade s"),
    @NamedQuery(name = "SegFuncionalidade.findByPkSegFuncionalidade", query = "SELECT s FROM SegFuncionalidade s WHERE s.pkSegFuncionalidade = :pkSegFuncionalidade"),
    @NamedQuery(name = "SegFuncionalidade.findByDescricao", query = "SELECT s FROM SegFuncionalidade s WHERE s.descricao = :descricao"),
    @NamedQuery(name = "SegFuncionalidade.findById", query = "SELECT s FROM SegFuncionalidade s WHERE s.id = :id")
})
public class SegFuncionalidade implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "pk_seg_funcionalidade")
    private Integer pkSegFuncionalidade;
    @Size(max = 2147483647)
    @Column(name = "descricao")
    private String descricao;
    @Size(max = 2147483647)
    @Column(name = "id")
    private String id;
    @OneToMany(mappedBy = "fkSegForm")
    private List<SegFuncionalidadeForm> segFuncionalidadeFormList;
    @OneToMany(mappedBy = "fkSegFuncionalidade")
    private List<SegFuncionalidadeForm> segFuncionalidadeFormList1;
    @OneToMany(mappedBy = "fkSegFuncionalidade")
    private List<SegPerfilHasFuncionalidade> segPerfilHasFuncionalidadeList;
    @OneToMany(mappedBy = "fkSegFuncionalidadePai")
    private List<SegFuncionalidade> segFuncionalidadeList;
    @JoinColumn(name = "fk_seg_funcionalidade_pai", referencedColumnName = "pk_seg_funcionalidade")
    @ManyToOne
    private SegFuncionalidade fkSegFuncionalidadePai;
    @JoinColumn(name = "fk_seg_tipo_funcionalidade", referencedColumnName = "pk_seg_tipo_funcionalidade")
    @ManyToOne
    private SegTipoFuncionalidade fkSegTipoFuncionalidade;

    public SegFuncionalidade()
    {
    }

    public SegFuncionalidade(Integer pkSegFuncionalidade)
    {
        this.pkSegFuncionalidade = pkSegFuncionalidade;
    }

    public Integer getPkSegFuncionalidade()
    {
        return pkSegFuncionalidade;
    }

    public void setPkSegFuncionalidade(Integer pkSegFuncionalidade)
    {
        this.pkSegFuncionalidade = pkSegFuncionalidade;
    }

    public String getDescricao()
    {
        return descricao;
    }

    public void setDescricao(String descricao)
    {
        this.descricao = descricao;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    @XmlTransient
    public List<SegFuncionalidadeForm> getSegFuncionalidadeFormList()
    {
        return segFuncionalidadeFormList;
    }

    public void setSegFuncionalidadeFormList(List<SegFuncionalidadeForm> segFuncionalidadeFormList)
    {
        this.segFuncionalidadeFormList = segFuncionalidadeFormList;
    }

    @XmlTransient
    public List<SegFuncionalidadeForm> getSegFuncionalidadeFormList1()
    {
        return segFuncionalidadeFormList1;
    }

    public void setSegFuncionalidadeFormList1(List<SegFuncionalidadeForm> segFuncionalidadeFormList1)
    {
        this.segFuncionalidadeFormList1 = segFuncionalidadeFormList1;
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
    public List<SegFuncionalidade> getSegFuncionalidadeList()
    {
        return segFuncionalidadeList;
    }

    public void setSegFuncionalidadeList(List<SegFuncionalidade> segFuncionalidadeList)
    {
        this.segFuncionalidadeList = segFuncionalidadeList;
    }

    public SegFuncionalidade getFkSegFuncionalidadePai()
    {
        return fkSegFuncionalidadePai;
    }

    public void setFkSegFuncionalidadePai(SegFuncionalidade fkSegFuncionalidadePai)
    {
        this.fkSegFuncionalidadePai = fkSegFuncionalidadePai;
    }

    public SegTipoFuncionalidade getFkSegTipoFuncionalidade()
    {
        return fkSegTipoFuncionalidade;
    }

    public void setFkSegTipoFuncionalidade(SegTipoFuncionalidade fkSegTipoFuncionalidade)
    {
        this.fkSegTipoFuncionalidade = fkSegTipoFuncionalidade;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (pkSegFuncionalidade != null ? pkSegFuncionalidade.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SegFuncionalidade))
        {
            return false;
        }
        SegFuncionalidade other = (SegFuncionalidade) object;
        if ((this.pkSegFuncionalidade == null && other.pkSegFuncionalidade != null) || (this.pkSegFuncionalidade != null && !this.pkSegFuncionalidade.equals(other.pkSegFuncionalidade)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.SegFuncionalidade[ pkSegFuncionalidade=" + pkSegFuncionalidade + " ]";
    }
    
}
