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
@Table(name = "ar_semestre", catalog = "sig_ucan_db", schema = "public")
@NamedQueries(
{
    @NamedQuery(name = "ArSemestre.findAll", query = "SELECT a FROM ArSemestre a"),
    @NamedQuery(name = "ArSemestre.findByPkSemestre", query = "SELECT a FROM ArSemestre a WHERE a.pkSemestre = :pkSemestre"),
    @NamedQuery(name = "ArSemestre.findByDescricao", query = "SELECT a FROM ArSemestre a WHERE a.descricao = :descricao")
})
public class ArSemestre implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_semestre", nullable = false)
    private Integer pkSemestre;
    @Size(max = 2147483647)
    @Column(length = 2147483647)
    private String descricao;
    @OneToMany(mappedBy = "fkSemestre")
    private List<ArArquivoMorto> arArquivoMortoList;

    public ArSemestre()
    {
    }

    public ArSemestre(Integer pkSemestre)
    {
        this.pkSemestre = pkSemestre;
    }

    public Integer getPkSemestre()
    {
        return pkSemestre;
    }

    public void setPkSemestre(Integer pkSemestre)
    {
        this.pkSemestre = pkSemestre;
    }

    public String getDescricao()
    {
        return descricao;
    }

    public void setDescricao(String descricao)
    {
        this.descricao = descricao;
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
        hash += (pkSemestre != null ? pkSemestre.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ArSemestre))
        {
            return false;
        }
        ArSemestre other = (ArSemestre) object;
        if ((this.pkSemestre == null && other.pkSemestre != null) || (this.pkSemestre != null && !this.pkSemestre.equals(other.pkSemestre)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.ArSemestre[ pkSemestre=" + pkSemestre + " ]";
    }
    
}
