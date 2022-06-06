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
@Table(name = "gd_entidade", catalog = "sig_ucan_db", schema = "public")
@NamedQueries(
{
    @NamedQuery(name = "GdEntidade.findAll", query = "SELECT g FROM GdEntidade g"),
    @NamedQuery(name = "GdEntidade.findByPkEntidade", query = "SELECT g FROM GdEntidade g WHERE g.pkEntidade = :pkEntidade"),
    @NamedQuery(name = "GdEntidade.findByDesignacao", query = "SELECT g FROM GdEntidade g WHERE g.designacao = :designacao")
})
public class GdEntidade implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_entidade", nullable = false)
    private Integer pkEntidade;
    @Size(max = 2147483647)
    @Column(length = 2147483647)
    private String designacao;
    @OneToMany(mappedBy = "fkEntidade")
    private List<GdDocumento> gdDocumentoList;
    @OneToMany(mappedBy = "fkEntidade")
    private List<GdConfiguracoes> gdConfiguracoesList;

    public GdEntidade()
    {
    }

    public GdEntidade(Integer pkEntidade)
    {
        this.pkEntidade = pkEntidade;
    }

    public Integer getPkEntidade()
    {
        return pkEntidade;
    }

    public void setPkEntidade(Integer pkEntidade)
    {
        this.pkEntidade = pkEntidade;
    }

    public String getDesignacao()
    {
        return designacao;
    }

    public void setDesignacao(String designacao)
    {
        this.designacao = designacao;
    }

    public List<GdDocumento> getGdDocumentoList()
    {
        return gdDocumentoList;
    }

    public void setGdDocumentoList(List<GdDocumento> gdDocumentoList)
    {
        this.gdDocumentoList = gdDocumentoList;
    }

    public List<GdConfiguracoes> getGdConfiguracoesList()
    {
        return gdConfiguracoesList;
    }

    public void setGdConfiguracoesList(List<GdConfiguracoes> gdConfiguracoesList)
    {
        this.gdConfiguracoesList = gdConfiguracoesList;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (pkEntidade != null ? pkEntidade.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GdEntidade))
        {
            return false;
        }
        GdEntidade other = (GdEntidade) object;
        if ((this.pkEntidade == null && other.pkEntidade != null) || (this.pkEntidade != null && !this.pkEntidade.equals(other.pkEntidade)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.GdEntidade[ pkEntidade=" + pkEntidade + " ]";
    }
    
}
