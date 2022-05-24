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
@Table(name = "seg_tipo_funcionalidade")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "SegTipoFuncionalidade.findAll", query = "SELECT s FROM SegTipoFuncionalidade s"),
    @NamedQuery(name = "SegTipoFuncionalidade.findByPkSegTipoFuncionalidade", query = "SELECT s FROM SegTipoFuncionalidade s WHERE s.pkSegTipoFuncionalidade = :pkSegTipoFuncionalidade"),
    @NamedQuery(name = "SegTipoFuncionalidade.findByNome", query = "SELECT s FROM SegTipoFuncionalidade s WHERE s.nome = :nome")
})
public class SegTipoFuncionalidade implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "pk_seg_tipo_funcionalidade")
    private Integer pkSegTipoFuncionalidade;
    @Size(max = 2147483647)
    @Column(name = "nome")
    private String nome;
    @OneToMany(mappedBy = "fkSegTipoFuncionalidade")
    private List<SegConfiguracoes> segConfiguracoesList;
    @OneToMany(mappedBy = "fkSegTipoFuncionalidade")
    private List<SegFuncionalidade> segFuncionalidadeList;

    public SegTipoFuncionalidade()
    {
    }

    public SegTipoFuncionalidade(Integer pkSegTipoFuncionalidade)
    {
        this.pkSegTipoFuncionalidade = pkSegTipoFuncionalidade;
    }

    public Integer getPkSegTipoFuncionalidade()
    {
        return pkSegTipoFuncionalidade;
    }

    public void setPkSegTipoFuncionalidade(Integer pkSegTipoFuncionalidade)
    {
        this.pkSegTipoFuncionalidade = pkSegTipoFuncionalidade;
    }

    public String getNome()
    {
        return nome;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
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

    @XmlTransient
    public List<SegFuncionalidade> getSegFuncionalidadeList()
    {
        return segFuncionalidadeList;
    }

    public void setSegFuncionalidadeList(List<SegFuncionalidade> segFuncionalidadeList)
    {
        this.segFuncionalidadeList = segFuncionalidadeList;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (pkSegTipoFuncionalidade != null ? pkSegTipoFuncionalidade.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SegTipoFuncionalidade))
        {
            return false;
        }
        SegTipoFuncionalidade other = (SegTipoFuncionalidade) object;
        if ((this.pkSegTipoFuncionalidade == null && other.pkSegTipoFuncionalidade != null) || (this.pkSegTipoFuncionalidade != null && !this.pkSegTipoFuncionalidade.equals(other.pkSegTipoFuncionalidade)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.SegTipoFuncionalidade[ pkSegTipoFuncionalidade=" + pkSegTipoFuncionalidade + " ]";
    }
    
}
