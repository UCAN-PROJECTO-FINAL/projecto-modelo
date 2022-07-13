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
@Table(name = "ar_tipo_documento", catalog = "sig_ucan_db", schema = "public")
@NamedQueries(
{
    @NamedQuery(name = "ArTipoDocumento.findAll", query = "SELECT a FROM ArTipoDocumento a"),
    @NamedQuery(name = "ArTipoDocumento.findByPkTipoDocumento", query = "SELECT a FROM ArTipoDocumento a WHERE a.pkTipoDocumento = :pkTipoDocumento"),
    @NamedQuery(name = "ArTipoDocumento.findByDescricao", query = "SELECT a FROM ArTipoDocumento a WHERE a.descricao = :descricao")
})
public class ArTipoDocumento implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_tipo_documento", nullable = false)
    private Integer pkTipoDocumento;
    @Size(max = 2147483647)
    @Column(length = 2147483647)
    private String descricao;
    @OneToMany(mappedBy = "fkTipoDocumento")
    private List<ArConfiguracoes> arConfiguracoesList;
    @OneToMany(mappedBy = "fkTipoDocumento")
    private List<ArArquivoMorto> arArquivoMortoList;

    public ArTipoDocumento()
    {
    }

    public ArTipoDocumento(Integer pkTipoDocumento)
    {
        this.pkTipoDocumento = pkTipoDocumento;
    }

    public Integer getPkTipoDocumento()
    {
        return pkTipoDocumento;
    }

    public void setPkTipoDocumento(Integer pkTipoDocumento)
    {
        this.pkTipoDocumento = pkTipoDocumento;
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
        hash += (pkTipoDocumento != null ? pkTipoDocumento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ArTipoDocumento))
        {
            return false;
        }
        ArTipoDocumento other = (ArTipoDocumento) object;
        if ((this.pkTipoDocumento == null && other.pkTipoDocumento != null) || (this.pkTipoDocumento != null && !this.pkTipoDocumento.equals(other.pkTipoDocumento)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.ArTipoDocumento[ pkTipoDocumento=" + pkTipoDocumento + " ]";
    }
    
}
