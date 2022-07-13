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
@Table(name = "fin_favoritos", catalog = "sig_ucan_db", schema = "public")
@NamedQueries(
{
    @NamedQuery(name = "FinFavoritos.findAll", query = "SELECT f FROM FinFavoritos f"),
    @NamedQuery(name = "FinFavoritos.findByPkFinFavoritos", query = "SELECT f FROM FinFavoritos f WHERE f.pkFinFavoritos = :pkFinFavoritos"),
    @NamedQuery(name = "FinFavoritos.findByDescricao", query = "SELECT f FROM FinFavoritos f WHERE f.descricao = :descricao"),
    @NamedQuery(name = "FinFavoritos.findByUrlPagina", query = "SELECT f FROM FinFavoritos f WHERE f.urlPagina = :urlPagina"),
    @NamedQuery(name = "FinFavoritos.findByObs", query = "SELECT f FROM FinFavoritos f WHERE f.obs = :obs"),
    @NamedQuery(name = "FinFavoritos.findByStatus", query = "SELECT f FROM FinFavoritos f WHERE f.status = :status"),
    @NamedQuery(name = "FinFavoritos.findByNumeroAcesso", query = "SELECT f FROM FinFavoritos f WHERE f.numeroAcesso = :numeroAcesso")
})
public class FinFavoritos implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_fin_favoritos", nullable = false)
    private Integer pkFinFavoritos;
    @Size(max = 2147483647)
    @Column(length = 2147483647)
    private String descricao;
    @Size(max = 2147483647)
    @Column(name = "url_pagina", length = 2147483647)
    private String urlPagina;
    @Size(max = 2147483647)
    @Column(length = 2147483647)
    private String obs;
    private Boolean status;
    @Column(name = "numero_acesso")
    private Integer numeroAcesso;

    public FinFavoritos()
    {
    }

    public FinFavoritos(Integer pkFinFavoritos)
    {
        this.pkFinFavoritos = pkFinFavoritos;
    }

    public Integer getPkFinFavoritos()
    {
        return pkFinFavoritos;
    }

    public void setPkFinFavoritos(Integer pkFinFavoritos)
    {
        this.pkFinFavoritos = pkFinFavoritos;
    }

    public String getDescricao()
    {
        return descricao;
    }

    public void setDescricao(String descricao)
    {
        this.descricao = descricao;
    }

    public String getUrlPagina()
    {
        return urlPagina;
    }

    public void setUrlPagina(String urlPagina)
    {
        this.urlPagina = urlPagina;
    }

    public String getObs()
    {
        return obs;
    }

    public void setObs(String obs)
    {
        this.obs = obs;
    }

    public Boolean getStatus()
    {
        return status;
    }

    public void setStatus(Boolean status)
    {
        this.status = status;
    }

    public Integer getNumeroAcesso()
    {
        return numeroAcesso;
    }

    public void setNumeroAcesso(Integer numeroAcesso)
    {
        this.numeroAcesso = numeroAcesso;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (pkFinFavoritos != null ? pkFinFavoritos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FinFavoritos))
        {
            return false;
        }
        FinFavoritos other = (FinFavoritos) object;
        if ((this.pkFinFavoritos == null && other.pkFinFavoritos != null) || (this.pkFinFavoritos != null && !this.pkFinFavoritos.equals(other.pkFinFavoritos)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.FinFavoritos[ pkFinFavoritos=" + pkFinFavoritos + " ]";
    }
    
}
