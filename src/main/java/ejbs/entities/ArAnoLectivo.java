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
@Table(name = "ar_ano_lectivo", catalog = "sig_ucan_db", schema = "public")
@NamedQueries(
{
    @NamedQuery(name = "ArAnoLectivo.findAll", query = "SELECT a FROM ArAnoLectivo a"),
    @NamedQuery(name = "ArAnoLectivo.findByPkAnoLectivo", query = "SELECT a FROM ArAnoLectivo a WHERE a.pkAnoLectivo = :pkAnoLectivo"),
    @NamedQuery(name = "ArAnoLectivo.findByDescricao", query = "SELECT a FROM ArAnoLectivo a WHERE a.descricao = :descricao")
})
public class ArAnoLectivo implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_ano_lectivo", nullable = false)
    private Integer pkAnoLectivo;
    @Size(max = 2147483647)
    @Column(length = 2147483647)
    private String descricao;
    @OneToMany(mappedBy = "fkAnoLectivo")
    private List<ArConfiguracoes> arConfiguracoesList;
    @OneToMany(mappedBy = "fkAnoLectivo")
    private List<ArArquivoMorto> arArquivoMortoList;

    public ArAnoLectivo()
    {
    }

    public ArAnoLectivo(Integer pkAnoLectivo)
    {
        this.pkAnoLectivo = pkAnoLectivo;
    }

    public Integer getPkAnoLectivo()
    {
        return pkAnoLectivo;
    }

    public void setPkAnoLectivo(Integer pkAnoLectivo)
    {
        this.pkAnoLectivo = pkAnoLectivo;
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
        hash += (pkAnoLectivo != null ? pkAnoLectivo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ArAnoLectivo))
        {
            return false;
        }
        ArAnoLectivo other = (ArAnoLectivo) object;
        if ((this.pkAnoLectivo == null && other.pkAnoLectivo != null) || (this.pkAnoLectivo != null && !this.pkAnoLectivo.equals(other.pkAnoLectivo)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.ArAnoLectivo[ pkAnoLectivo=" + pkAnoLectivo + " ]";
    }
    
}
