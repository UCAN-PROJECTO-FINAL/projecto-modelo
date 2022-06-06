/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 *
 * @author smakambo
 */
@Entity
@Table(name = "gs_licenca", catalog = "sig_ucan_db", schema = "public")
@NamedQueries(
{
    @NamedQuery(name = "GsLicenca.findAll", query = "SELECT g FROM GsLicenca g"),
    @NamedQuery(name = "GsLicenca.findByPkGsLicenca", query = "SELECT g FROM GsLicenca g WHERE g.pkGsLicenca = :pkGsLicenca"),
    @NamedQuery(name = "GsLicenca.findByChaveLicenca", query = "SELECT g FROM GsLicenca g WHERE g.chaveLicenca = :chaveLicenca"),
    @NamedQuery(name = "GsLicenca.findByDataEmissao", query = "SELECT g FROM GsLicenca g WHERE g.dataEmissao = :dataEmissao"),
    @NamedQuery(name = "GsLicenca.findByDataExpiracao", query = "SELECT g FROM GsLicenca g WHERE g.dataExpiracao = :dataExpiracao"),
    @NamedQuery(name = "GsLicenca.findByNumeroUtilizadores", query = "SELECT g FROM GsLicenca g WHERE g.numeroUtilizadores = :numeroUtilizadores")
})
public class GsLicenca implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_gs_licenca", nullable = false)
    private Integer pkGsLicenca;
    @Size(max = 2147483647)
    @Column(name = "chave_licenca", length = 2147483647)
    private String chaveLicenca;
    @Column(name = "data_emissao")
    @Temporal(TemporalType.DATE)
    private Date dataEmissao;
    @Column(name = "data_expiracao")
    @Temporal(TemporalType.DATE)
    private Date dataExpiracao;
    @Column(name = "numero_utilizadores")
    private Integer numeroUtilizadores;
    @JoinColumn(name = "fk_gs_software", referencedColumnName = "pk_gs_software", nullable = false)
    @ManyToOne(optional = false)
    private GsSoftware fkGsSoftware;
    @JoinColumn(name = "fk_gs_tipo", referencedColumnName = "pk_gs_tipo", nullable = false)
    @ManyToOne(optional = false)
    private GsTipo fkGsTipo;
    @OneToMany(mappedBy = "fkGsLicenca")
    private List<GsInstalacao> gsInstalacaoList;

    public GsLicenca()
    {
    }

    public GsLicenca(Integer pkGsLicenca)
    {
        this.pkGsLicenca = pkGsLicenca;
    }

    public Integer getPkGsLicenca()
    {
        return pkGsLicenca;
    }

    public void setPkGsLicenca(Integer pkGsLicenca)
    {
        this.pkGsLicenca = pkGsLicenca;
    }

    public String getChaveLicenca()
    {
        return chaveLicenca;
    }

    public void setChaveLicenca(String chaveLicenca)
    {
        this.chaveLicenca = chaveLicenca;
    }

    public Date getDataEmissao()
    {
        return dataEmissao;
    }

    public void setDataEmissao(Date dataEmissao)
    {
        this.dataEmissao = dataEmissao;
    }

    public Date getDataExpiracao()
    {
        return dataExpiracao;
    }

    public void setDataExpiracao(Date dataExpiracao)
    {
        this.dataExpiracao = dataExpiracao;
    }

    public Integer getNumeroUtilizadores()
    {
        return numeroUtilizadores;
    }

    public void setNumeroUtilizadores(Integer numeroUtilizadores)
    {
        this.numeroUtilizadores = numeroUtilizadores;
    }

    public GsSoftware getFkGsSoftware()
    {
        return fkGsSoftware;
    }

    public void setFkGsSoftware(GsSoftware fkGsSoftware)
    {
        this.fkGsSoftware = fkGsSoftware;
    }

    public GsTipo getFkGsTipo()
    {
        return fkGsTipo;
    }

    public void setFkGsTipo(GsTipo fkGsTipo)
    {
        this.fkGsTipo = fkGsTipo;
    }

    public List<GsInstalacao> getGsInstalacaoList()
    {
        return gsInstalacaoList;
    }

    public void setGsInstalacaoList(List<GsInstalacao> gsInstalacaoList)
    {
        this.gsInstalacaoList = gsInstalacaoList;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (pkGsLicenca != null ? pkGsLicenca.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GsLicenca))
        {
            return false;
        }
        GsLicenca other = (GsLicenca) object;
        if ((this.pkGsLicenca == null && other.pkGsLicenca != null) || (this.pkGsLicenca != null && !this.pkGsLicenca.equals(other.pkGsLicenca)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.GsLicenca[ pkGsLicenca=" + pkGsLicenca + " ]";
    }
    
}
