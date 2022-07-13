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
import javax.validation.constraints.Size;

/**
 *
 * @author smakambo
 */
@Entity
@Table(name = "ar_periodo", catalog = "sig_ucan_db", schema = "public")
@NamedQueries(
{
    @NamedQuery(name = "ArPeriodo.findAll", query = "SELECT a FROM ArPeriodo a"),
    @NamedQuery(name = "ArPeriodo.findByPkPeriodo", query = "SELECT a FROM ArPeriodo a WHERE a.pkPeriodo = :pkPeriodo"),
    @NamedQuery(name = "ArPeriodo.findByDescricao", query = "SELECT a FROM ArPeriodo a WHERE a.descricao = :descricao")
})
public class ArPeriodo implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_periodo", nullable = false)
    private Integer pkPeriodo;
    @Size(max = 2147483647)
    @Column(length = 2147483647)
    private String descricao;
    @OneToMany(mappedBy = "fkPeriodo")
    private List<ArConfiguracoes> arConfiguracoesList;
    @OneToMany(mappedBy = "fkPeriodo")
    private List<ArArquivoMorto> arArquivoMortoList;

    public ArPeriodo()
    {
    }

    public ArPeriodo(Integer pkPeriodo)
    {
        this.pkPeriodo = pkPeriodo;
    }

    public Integer getPkPeriodo()
    {
        return pkPeriodo;
    }

    public void setPkPeriodo(Integer pkPeriodo)
    {
        this.pkPeriodo = pkPeriodo;
    }

    public String getDescricao()
    {
        return descricao;
    }

    public void setDescricao(String descricao)
    {
        this.descricao = descricao;
    }

    public List<ArConfiguracoes> getArConfiguracoesList()
    {
        return arConfiguracoesList;
    }

    public void setArConfiguracoesList(List<ArConfiguracoes> arConfiguracoesList)
    {
        this.arConfiguracoesList = arConfiguracoesList;
    }

    public List<ArArquivoMorto> getArArquivoMortoList()
    {
        return arArquivoMortoList;
    }

    public void setArArquivoMortoList(List<ArArquivoMorto> arArquivoMortoList)
    {
        this.arArquivoMortoList = arArquivoMortoList;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (pkPeriodo != null ? pkPeriodo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ArPeriodo))
        {
            return false;
        }
        ArPeriodo other = (ArPeriodo) object;
        if ((this.pkPeriodo == null && other.pkPeriodo != null) || (this.pkPeriodo != null && !this.pkPeriodo.equals(other.pkPeriodo)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.ArPeriodo[ pkPeriodo=" + pkPeriodo + " ]";
    }
    
}
