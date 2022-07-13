/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.entities;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author smakambo
 */
@Entity
@Table(name = "frt_transporte_acidente", catalog = "sig_ucan_db", schema = "public")
@NamedQueries(
{
    @NamedQuery(name = "FrtTransporteAcidente.findAll", query = "SELECT f FROM FrtTransporteAcidente f"),
    @NamedQuery(name = "FrtTransporteAcidente.findByPkAcidente", query = "SELECT f FROM FrtTransporteAcidente f WHERE f.pkAcidente = :pkAcidente"),
    @NamedQuery(name = "FrtTransporteAcidente.findByDescricao", query = "SELECT f FROM FrtTransporteAcidente f WHERE f.descricao = :descricao"),
    @NamedQuery(name = "FrtTransporteAcidente.findByDataAcidente", query = "SELECT f FROM FrtTransporteAcidente f WHERE f.dataAcidente = :dataAcidente"),
    @NamedQuery(name = "FrtTransporteAcidente.findByQtdVitimasFeridas", query = "SELECT f FROM FrtTransporteAcidente f WHERE f.qtdVitimasFeridas = :qtdVitimasFeridas"),
    @NamedQuery(name = "FrtTransporteAcidente.findByQtdVitimasMortais", query = "SELECT f FROM FrtTransporteAcidente f WHERE f.qtdVitimasMortais = :qtdVitimasMortais"),
    @NamedQuery(name = "FrtTransporteAcidente.findByCondicaoVia", query = "SELECT f FROM FrtTransporteAcidente f WHERE f.condicaoVia = :condicaoVia"),
    @NamedQuery(name = "FrtTransporteAcidente.findByDataCadastro", query = "SELECT f FROM FrtTransporteAcidente f WHERE f.dataCadastro = :dataCadastro"),
    @NamedQuery(name = "FrtTransporteAcidente.findByObservacao", query = "SELECT f FROM FrtTransporteAcidente f WHERE f.observacao = :observacao")
})
public class FrtTransporteAcidente implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_acidente", nullable = false)
    private Integer pkAcidente;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(nullable = false, length = 2147483647)
    private String descricao;
    @Basic(optional = false)
    @NotNull
    @Column(name = "data_acidente", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataAcidente;
    @Basic(optional = false)
    @NotNull
    @Column(name = "qtd_vitimas_feridas", nullable = false)
    private int qtdVitimasFeridas;
    @Basic(optional = false)
    @NotNull
    @Column(name = "qtd_vitimas_mortais", nullable = false)
    private int qtdVitimasMortais;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "condicao_via", nullable = false, length = 2147483647)
    private String condicaoVia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "data_cadastro", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataCadastro;
    @Size(max = 2147483647)
    @Column(length = 2147483647)
    private String observacao;
    @JoinColumn(name = "fk_tipo_acidente", referencedColumnName = "pk_tipo_acidente")
    @ManyToOne
    private FrtTransporteTipoAcidente fkTipoAcidente;
    @JoinColumn(name = "fk_tipo_iluminacao", referencedColumnName = "pk_tipo_iluminacao")
    @ManyToOne
    private FrtTransporteTipoIluminacao fkTipoIluminacao;
    @JoinColumn(name = "fk_transporte", referencedColumnName = "pk_pt_transporte")
    @ManyToOne
    private PtTransporte fkTransporte;

    public FrtTransporteAcidente()
    {
    }

    public FrtTransporteAcidente(Integer pkAcidente)
    {
        this.pkAcidente = pkAcidente;
    }

    public FrtTransporteAcidente(Integer pkAcidente, String descricao, Date dataAcidente, int qtdVitimasFeridas, int qtdVitimasMortais, String condicaoVia, Date dataCadastro)
    {
        this.pkAcidente = pkAcidente;
        this.descricao = descricao;
        this.dataAcidente = dataAcidente;
        this.qtdVitimasFeridas = qtdVitimasFeridas;
        this.qtdVitimasMortais = qtdVitimasMortais;
        this.condicaoVia = condicaoVia;
        this.dataCadastro = dataCadastro;
    }

    public Integer getPkAcidente()
    {
        return pkAcidente;
    }

    public void setPkAcidente(Integer pkAcidente)
    {
        this.pkAcidente = pkAcidente;
    }

    public String getDescricao()
    {
        return descricao;
    }

    public void setDescricao(String descricao)
    {
        this.descricao = descricao;
    }

    public Date getDataAcidente()
    {
        return dataAcidente;
    }

    public void setDataAcidente(Date dataAcidente)
    {
        this.dataAcidente = dataAcidente;
    }

    public int getQtdVitimasFeridas()
    {
        return qtdVitimasFeridas;
    }

    public void setQtdVitimasFeridas(int qtdVitimasFeridas)
    {
        this.qtdVitimasFeridas = qtdVitimasFeridas;
    }

    public int getQtdVitimasMortais()
    {
        return qtdVitimasMortais;
    }

    public void setQtdVitimasMortais(int qtdVitimasMortais)
    {
        this.qtdVitimasMortais = qtdVitimasMortais;
    }

    public String getCondicaoVia()
    {
        return condicaoVia;
    }

    public void setCondicaoVia(String condicaoVia)
    {
        this.condicaoVia = condicaoVia;
    }

    public Date getDataCadastro()
    {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro)
    {
        this.dataCadastro = dataCadastro;
    }

    public String getObservacao()
    {
        return observacao;
    }

    public void setObservacao(String observacao)
    {
        this.observacao = observacao;
    }

    public FrtTransporteTipoAcidente getFkTipoAcidente()
    {
        return fkTipoAcidente;
    }

    public void setFkTipoAcidente(FrtTransporteTipoAcidente fkTipoAcidente)
    {
        this.fkTipoAcidente = fkTipoAcidente;
    }

    public FrtTransporteTipoIluminacao getFkTipoIluminacao()
    {
        return fkTipoIluminacao;
    }

    public void setFkTipoIluminacao(FrtTransporteTipoIluminacao fkTipoIluminacao)
    {
        this.fkTipoIluminacao = fkTipoIluminacao;
    }

    public PtTransporte getFkTransporte()
    {
        return fkTransporte;
    }

    public void setFkTransporte(PtTransporte fkTransporte)
    {
        this.fkTransporte = fkTransporte;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (pkAcidente != null ? pkAcidente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FrtTransporteAcidente))
        {
            return false;
        }
        FrtTransporteAcidente other = (FrtTransporteAcidente) object;
        if ((this.pkAcidente == null && other.pkAcidente != null) || (this.pkAcidente != null && !this.pkAcidente.equals(other.pkAcidente)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.FrtTransporteAcidente[ pkAcidente=" + pkAcidente + " ]";
    }
    
}
