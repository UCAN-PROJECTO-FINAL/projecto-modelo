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
@Table(name = "ar_turma", catalog = "sig_ucan_db", schema = "public")
@NamedQueries(
{
    @NamedQuery(name = "ArTurma.findAll", query = "SELECT a FROM ArTurma a"),
    @NamedQuery(name = "ArTurma.findByPkTurma", query = "SELECT a FROM ArTurma a WHERE a.pkTurma = :pkTurma"),
    @NamedQuery(name = "ArTurma.findByDescricao", query = "SELECT a FROM ArTurma a WHERE a.descricao = :descricao")
})
public class ArTurma implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_turma", nullable = false)
    private Integer pkTurma;
    @Size(max = 2147483647)
    @Column(length = 2147483647)
    private String descricao;
    @OneToMany(mappedBy = "fkTurma")
    private List<ArConfiguracoes> arConfiguracoesList;
    @OneToMany(mappedBy = "fkTurma")
    private List<ArArquivoMorto> arArquivoMortoList;

    public ArTurma()
    {
    }

    public ArTurma(Integer pkTurma)
    {
        this.pkTurma = pkTurma;
    }

    public Integer getPkTurma()
    {
        return pkTurma;
    }

    public void setPkTurma(Integer pkTurma)
    {
        this.pkTurma = pkTurma;
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
        hash += (pkTurma != null ? pkTurma.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ArTurma))
        {
            return false;
        }
        ArTurma other = (ArTurma) object;
        if ((this.pkTurma == null && other.pkTurma != null) || (this.pkTurma != null && !this.pkTurma.equals(other.pkTurma)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.ArTurma[ pkTurma=" + pkTurma + " ]";
    }
    
}
