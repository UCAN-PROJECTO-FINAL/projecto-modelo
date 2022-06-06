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
@Table(name = "gd_classificacao", catalog = "sig_ucan_db", schema = "public")
@NamedQueries(
{
    @NamedQuery(name = "GdClassificacao.findAll", query = "SELECT g FROM GdClassificacao g"),
    @NamedQuery(name = "GdClassificacao.findByPkClassificacao", query = "SELECT g FROM GdClassificacao g WHERE g.pkClassificacao = :pkClassificacao"),
    @NamedQuery(name = "GdClassificacao.findByDescricao", query = "SELECT g FROM GdClassificacao g WHERE g.descricao = :descricao")
})
public class GdClassificacao implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_classificacao", nullable = false)
    private Integer pkClassificacao;
    @Size(max = 100)
    @Column(length = 100)
    private String descricao;
    @OneToMany(mappedBy = "fkClassificacao")
    private List<GdDocumento> gdDocumentoList;
    @OneToMany(mappedBy = "fkClassificacao")
    private List<GdConfiguracoes> gdConfiguracoesList;

    public GdClassificacao()
    {
    }

    public GdClassificacao(Integer pkClassificacao)
    {
        this.pkClassificacao = pkClassificacao;
    }

    public Integer getPkClassificacao()
    {
        return pkClassificacao;
    }

    public void setPkClassificacao(Integer pkClassificacao)
    {
        this.pkClassificacao = pkClassificacao;
    }

    public String getDescricao()
    {
        return descricao;
    }

    public void setDescricao(String descricao)
    {
        this.descricao = descricao;
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
        hash += (pkClassificacao != null ? pkClassificacao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GdClassificacao))
        {
            return false;
        }
        GdClassificacao other = (GdClassificacao) object;
        if ((this.pkClassificacao == null && other.pkClassificacao != null) || (this.pkClassificacao != null && !this.pkClassificacao.equals(other.pkClassificacao)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.GdClassificacao[ pkClassificacao=" + pkClassificacao + " ]";
    }
    
}
