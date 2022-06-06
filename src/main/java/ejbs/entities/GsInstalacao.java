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

/**
 *
 * @author smakambo
 */
@Entity
@Table(name = "gs_instalacao", catalog = "sig_ucan_db", schema = "public")
@NamedQueries(
{
    @NamedQuery(name = "GsInstalacao.findAll", query = "SELECT g FROM GsInstalacao g"),
    @NamedQuery(name = "GsInstalacao.findByPkGsInstala\u00e7\u00e3o", query = "SELECT g FROM GsInstalacao g WHERE g.pkGsInstala\u00e7\u00e3o = :pkGsInstala\u00e7\u00e3o"),
    @NamedQuery(name = "GsInstalacao.findByData", query = "SELECT g FROM GsInstalacao g WHERE g.data = :data")
})
public class GsInstalacao implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_gs_instala\u00e7\u00e3o", nullable = false)
    private Integer pkGsInstalação;
    @Temporal(TemporalType.DATE)
    private Date data;
    @JoinColumn(name = "fk_estrutura_logica", referencedColumnName = "pk_estrutura_logica")
    @ManyToOne
    private EstruturaLogica fkEstruturaLogica;
    @JoinColumn(name = "fk_gs_equipamento", referencedColumnName = "pk_gs_equipamento")
    @ManyToOne
    private GsEquipamento fkGsEquipamento;
    @JoinColumn(name = "fk_gs_licenca", referencedColumnName = "pk_gs_licenca")
    @ManyToOne
    private GsLicenca fkGsLicenca;

    public GsInstalacao()
    {
    }

    public GsInstalacao(Integer pkGsInstalação)
    {
        this.pkGsInstalação = pkGsInstalação;
    }

    public Integer getPkGsInstalação()
    {
        return pkGsInstalação;
    }

    public void setPkGsInstalação(Integer pkGsInstalação)
    {
        this.pkGsInstalação = pkGsInstalação;
    }

    public Date getData()
    {
        return data;
    }

    public void setData(Date data)
    {
        this.data = data;
    }

    public EstruturaLogica getFkEstruturaLogica()
    {
        return fkEstruturaLogica;
    }

    public void setFkEstruturaLogica(EstruturaLogica fkEstruturaLogica)
    {
        this.fkEstruturaLogica = fkEstruturaLogica;
    }

    public GsEquipamento getFkGsEquipamento()
    {
        return fkGsEquipamento;
    }

    public void setFkGsEquipamento(GsEquipamento fkGsEquipamento)
    {
        this.fkGsEquipamento = fkGsEquipamento;
    }

    public GsLicenca getFkGsLicenca()
    {
        return fkGsLicenca;
    }

    public void setFkGsLicenca(GsLicenca fkGsLicenca)
    {
        this.fkGsLicenca = fkGsLicenca;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (pkGsInstalação != null ? pkGsInstalação.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GsInstalacao))
        {
            return false;
        }
        GsInstalacao other = (GsInstalacao) object;
        if ((this.pkGsInstalação == null && other.pkGsInstalação != null) || (this.pkGsInstalação != null && !this.pkGsInstalação.equals(other.pkGsInstalação)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.GsInstalacao[ pkGsInstala\u00e7\u00e3o=" + pkGsInstalação + " ]";
    }
    
}
