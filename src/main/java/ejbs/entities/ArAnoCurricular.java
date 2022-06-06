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
@Table(name = "ar_ano_curricular", catalog = "sig_ucan_db", schema = "public")
@NamedQueries(
{
    @NamedQuery(name = "ArAnoCurricular.findAll", query = "SELECT a FROM ArAnoCurricular a"),
    @NamedQuery(name = "ArAnoCurricular.findByPkAnoCurricular", query = "SELECT a FROM ArAnoCurricular a WHERE a.pkAnoCurricular = :pkAnoCurricular"),
    @NamedQuery(name = "ArAnoCurricular.findByDescricao", query = "SELECT a FROM ArAnoCurricular a WHERE a.descricao = :descricao")
})
public class ArAnoCurricular implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_ano_curricular", nullable = false)
    private Integer pkAnoCurricular;
    @Size(max = 2147483647)
    @Column(length = 2147483647)
    private String descricao;
    @OneToMany(mappedBy = "fkAnoCurricular")
    private List<ArConfiguracoes> arConfiguracoesList;
    @OneToMany(mappedBy = "fkAnoCurricular")
    private List<ArArquivoMorto> arArquivoMortoList;

    public ArAnoCurricular()
    {
    }

    public ArAnoCurricular(Integer pkAnoCurricular)
    {
        this.pkAnoCurricular = pkAnoCurricular;
    }

    public Integer getPkAnoCurricular()
    {
        return pkAnoCurricular;
    }

    public void setPkAnoCurricular(Integer pkAnoCurricular)
    {
        this.pkAnoCurricular = pkAnoCurricular;
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
        hash += (pkAnoCurricular != null ? pkAnoCurricular.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ArAnoCurricular))
        {
            return false;
        }
        ArAnoCurricular other = (ArAnoCurricular) object;
        if ((this.pkAnoCurricular == null && other.pkAnoCurricular != null) || (this.pkAnoCurricular != null && !this.pkAnoCurricular.equals(other.pkAnoCurricular)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.ArAnoCurricular[ pkAnoCurricular=" + pkAnoCurricular + " ]";
    }
    
}
