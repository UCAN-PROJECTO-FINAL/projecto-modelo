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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author smakambo
 */
@Entity
@Table(name = "gd_configuracoes", catalog = "sig_ucan_db", schema = "public")
@NamedQueries(
{
    @NamedQuery(name = "GdConfiguracoes.findAll", query = "SELECT g FROM GdConfiguracoes g"),
    @NamedQuery(name = "GdConfiguracoes.findByPkConfiguracoes", query = "SELECT g FROM GdConfiguracoes g WHERE g.pkConfiguracoes = :pkConfiguracoes")
})
public class GdConfiguracoes implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_configuracoes", nullable = false)
    private Integer pkConfiguracoes;
    @JoinColumn(name = "fk_classificacao", referencedColumnName = "pk_classificacao")
    @ManyToOne
    private GdClassificacao fkClassificacao;
    @JoinColumn(name = "fk_entidade", referencedColumnName = "pk_entidade")
    @ManyToOne
    private GdEntidade fkEntidade;
    @JoinColumn(name = "fk_tipo_documento", referencedColumnName = "pk_tipo_documento")
    @ManyToOne
    private GdTipoDocumento fkTipoDocumento;

    public GdConfiguracoes()
    {
    }

    public GdConfiguracoes(Integer pkConfiguracoes)
    {
        this.pkConfiguracoes = pkConfiguracoes;
    }

    public Integer getPkConfiguracoes()
    {
        return pkConfiguracoes;
    }

    public void setPkConfiguracoes(Integer pkConfiguracoes)
    {
        this.pkConfiguracoes = pkConfiguracoes;
    }

    public GdClassificacao getFkClassificacao()
    {
        return fkClassificacao;
    }

    public void setFkClassificacao(GdClassificacao fkClassificacao)
    {
        this.fkClassificacao = fkClassificacao;
    }

    public GdEntidade getFkEntidade()
    {
        return fkEntidade;
    }

    public void setFkEntidade(GdEntidade fkEntidade)
    {
        this.fkEntidade = fkEntidade;
    }

    public GdTipoDocumento getFkTipoDocumento()
    {
        return fkTipoDocumento;
    }

    public void setFkTipoDocumento(GdTipoDocumento fkTipoDocumento)
    {
        this.fkTipoDocumento = fkTipoDocumento;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (pkConfiguracoes != null ? pkConfiguracoes.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GdConfiguracoes))
        {
            return false;
        }
        GdConfiguracoes other = (GdConfiguracoes) object;
        if ((this.pkConfiguracoes == null && other.pkConfiguracoes != null) || (this.pkConfiguracoes != null && !this.pkConfiguracoes.equals(other.pkConfiguracoes)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.GdConfiguracoes[ pkConfiguracoes=" + pkConfiguracoes + " ]";
    }
    
}
