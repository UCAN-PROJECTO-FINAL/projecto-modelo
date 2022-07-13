/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author smakambo
 */
@Entity
@Table(name = "ar_classificacao", catalog = "sig_ucan_db", schema = "public")
@NamedQueries(
{
    @NamedQuery(name = "ArClassificacao.findAll", query = "SELECT a FROM ArClassificacao a"),
    @NamedQuery(name = "ArClassificacao.findByPkClassificacao", query = "SELECT a FROM ArClassificacao a WHERE a.pkClassificacao = :pkClassificacao"),
    @NamedQuery(name = "ArClassificacao.findByDescricao", query = "SELECT a FROM ArClassificacao a WHERE a.descricao = :descricao")
})
public class ArClassificacao implements Serializable
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

    public ArClassificacao()
    {
    }

    public ArClassificacao(Integer pkClassificacao)
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
        if (!(object instanceof ArClassificacao))
        {
            return false;
        }
        ArClassificacao other = (ArClassificacao) object;
        if ((this.pkClassificacao == null && other.pkClassificacao != null) || (this.pkClassificacao != null && !this.pkClassificacao.equals(other.pkClassificacao)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.ArClassificacao[ pkClassificacao=" + pkClassificacao + " ]";
    }
    
}
