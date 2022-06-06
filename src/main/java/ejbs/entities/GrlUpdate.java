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
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author smakambo
 */
@Entity
@Table(name = "grl_update", catalog = "sig_ucan_db", schema = "public")
@NamedQueries(
{
    @NamedQuery(name = "GrlUpdate.findAll", query = "SELECT g FROM GrlUpdate g"),
    @NamedQuery(name = "GrlUpdate.findByPkGrlupdate", query = "SELECT g FROM GrlUpdate g WHERE g.pkGrlupdate = :pkGrlupdate"),
    @NamedQuery(name = "GrlUpdate.findByLocalidadesDate", query = "SELECT g FROM GrlUpdate g WHERE g.localidadesDate = :localidadesDate"),
    @NamedQuery(name = "GrlUpdate.findByEstruturaLogicaDate", query = "SELECT g FROM GrlUpdate g WHERE g.estruturaLogicaDate = :estruturaLogicaDate"),
    @NamedQuery(name = "GrlUpdate.findByEstruturaFisicaDate", query = "SELECT g FROM GrlUpdate g WHERE g.estruturaFisicaDate = :estruturaFisicaDate"),
    @NamedQuery(name = "GrlUpdate.findByBancoDate", query = "SELECT g FROM GrlUpdate g WHERE g.bancoDate = :bancoDate"),
    @NamedQuery(name = "GrlUpdate.findByTipoCartaoDate", query = "SELECT g FROM GrlUpdate g WHERE g.tipoCartaoDate = :tipoCartaoDate"),
    @NamedQuery(name = "GrlUpdate.findByTipoContaDate", query = "SELECT g FROM GrlUpdate g WHERE g.tipoContaDate = :tipoContaDate"),
    @NamedQuery(name = "GrlUpdate.findByMoedaDate", query = "SELECT g FROM GrlUpdate g WHERE g.moedaDate = :moedaDate"),
    @NamedQuery(name = "GrlUpdate.findByComunaDate", query = "SELECT g FROM GrlUpdate g WHERE g.comunaDate = :comunaDate"),
    @NamedQuery(name = "GrlUpdate.findByDistritoDate", query = "SELECT g FROM GrlUpdate g WHERE g.distritoDate = :distritoDate"),
    @NamedQuery(name = "GrlUpdate.findByMunicipioDate", query = "SELECT g FROM GrlUpdate g WHERE g.municipioDate = :municipioDate"),
    @NamedQuery(name = "GrlUpdate.findByPaisDate", query = "SELECT g FROM GrlUpdate g WHERE g.paisDate = :paisDate"),
    @NamedQuery(name = "GrlUpdate.findByProvinciaDate", query = "SELECT g FROM GrlUpdate g WHERE g.provinciaDate = :provinciaDate"),
    @NamedQuery(name = "GrlUpdate.findByTipoCategoria", query = "SELECT g FROM GrlUpdate g WHERE g.tipoCategoria = :tipoCategoria"),
    @NamedQuery(name = "GrlUpdate.findByCategoriaDate", query = "SELECT g FROM GrlUpdate g WHERE g.categoriaDate = :categoriaDate"),
    @NamedQuery(name = "GrlUpdate.findByTipoCategoriaDate", query = "SELECT g FROM GrlUpdate g WHERE g.tipoCategoriaDate = :tipoCategoriaDate"),
    @NamedQuery(name = "GrlUpdate.findBySubCategoriaDate", query = "SELECT g FROM GrlUpdate g WHERE g.subCategoriaDate = :subCategoriaDate")
})
public class GrlUpdate implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "pk_grlupdate", nullable = false)
    private Integer pkGrlupdate;
    @Column(name = "localidades_date")
    @Temporal(TemporalType.DATE)
    private Date localidadesDate;
    @Column(name = "estrutura_logica_date")
    @Temporal(TemporalType.DATE)
    private Date estruturaLogicaDate;
    @Column(name = "estrutura_fisica_date")
    @Temporal(TemporalType.DATE)
    private Date estruturaFisicaDate;
    @Column(name = "banco_date")
    @Temporal(TemporalType.DATE)
    private Date bancoDate;
    @Column(name = "tipo_cartao_date")
    @Temporal(TemporalType.DATE)
    private Date tipoCartaoDate;
    @Column(name = "tipo_conta_date")
    @Temporal(TemporalType.DATE)
    private Date tipoContaDate;
    @Column(name = "moeda_date")
    @Temporal(TemporalType.DATE)
    private Date moedaDate;
    @Column(name = "comuna_date")
    @Temporal(TemporalType.DATE)
    private Date comunaDate;
    @Column(name = "distrito_date")
    @Temporal(TemporalType.DATE)
    private Date distritoDate;
    @Column(name = "municipio_date")
    @Temporal(TemporalType.DATE)
    private Date municipioDate;
    @Column(name = "pais_date")
    @Temporal(TemporalType.DATE)
    private Date paisDate;
    @Column(name = "provincia_date")
    @Temporal(TemporalType.DATE)
    private Date provinciaDate;
    @Column(name = "tipo_categoria")
    @Temporal(TemporalType.DATE)
    private Date tipoCategoria;
    @Column(name = "categoria_date")
    @Temporal(TemporalType.DATE)
    private Date categoriaDate;
    @Column(name = "tipo_categoria_date")
    @Temporal(TemporalType.DATE)
    private Date tipoCategoriaDate;
    @Column(name = "sub_categoria_date")
    @Temporal(TemporalType.DATE)
    private Date subCategoriaDate;

    public GrlUpdate()
    {
    }

    public GrlUpdate(Integer pkGrlupdate)
    {
        this.pkGrlupdate = pkGrlupdate;
    }

    public Integer getPkGrlupdate()
    {
        return pkGrlupdate;
    }

    public void setPkGrlupdate(Integer pkGrlupdate)
    {
        this.pkGrlupdate = pkGrlupdate;
    }

    public Date getLocalidadesDate()
    {
        return localidadesDate;
    }

    public void setLocalidadesDate(Date localidadesDate)
    {
        this.localidadesDate = localidadesDate;
    }

    public Date getEstruturaLogicaDate()
    {
        return estruturaLogicaDate;
    }

    public void setEstruturaLogicaDate(Date estruturaLogicaDate)
    {
        this.estruturaLogicaDate = estruturaLogicaDate;
    }

    public Date getEstruturaFisicaDate()
    {
        return estruturaFisicaDate;
    }

    public void setEstruturaFisicaDate(Date estruturaFisicaDate)
    {
        this.estruturaFisicaDate = estruturaFisicaDate;
    }

    public Date getBancoDate()
    {
        return bancoDate;
    }

    public void setBancoDate(Date bancoDate)
    {
        this.bancoDate = bancoDate;
    }

    public Date getTipoCartaoDate()
    {
        return tipoCartaoDate;
    }

    public void setTipoCartaoDate(Date tipoCartaoDate)
    {
        this.tipoCartaoDate = tipoCartaoDate;
    }

    public Date getTipoContaDate()
    {
        return tipoContaDate;
    }

    public void setTipoContaDate(Date tipoContaDate)
    {
        this.tipoContaDate = tipoContaDate;
    }

    public Date getMoedaDate()
    {
        return moedaDate;
    }

    public void setMoedaDate(Date moedaDate)
    {
        this.moedaDate = moedaDate;
    }

    public Date getComunaDate()
    {
        return comunaDate;
    }

    public void setComunaDate(Date comunaDate)
    {
        this.comunaDate = comunaDate;
    }

    public Date getDistritoDate()
    {
        return distritoDate;
    }

    public void setDistritoDate(Date distritoDate)
    {
        this.distritoDate = distritoDate;
    }

    public Date getMunicipioDate()
    {
        return municipioDate;
    }

    public void setMunicipioDate(Date municipioDate)
    {
        this.municipioDate = municipioDate;
    }

    public Date getPaisDate()
    {
        return paisDate;
    }

    public void setPaisDate(Date paisDate)
    {
        this.paisDate = paisDate;
    }

    public Date getProvinciaDate()
    {
        return provinciaDate;
    }

    public void setProvinciaDate(Date provinciaDate)
    {
        this.provinciaDate = provinciaDate;
    }

    public Date getTipoCategoria()
    {
        return tipoCategoria;
    }

    public void setTipoCategoria(Date tipoCategoria)
    {
        this.tipoCategoria = tipoCategoria;
    }

    public Date getCategoriaDate()
    {
        return categoriaDate;
    }

    public void setCategoriaDate(Date categoriaDate)
    {
        this.categoriaDate = categoriaDate;
    }

    public Date getTipoCategoriaDate()
    {
        return tipoCategoriaDate;
    }

    public void setTipoCategoriaDate(Date tipoCategoriaDate)
    {
        this.tipoCategoriaDate = tipoCategoriaDate;
    }

    public Date getSubCategoriaDate()
    {
        return subCategoriaDate;
    }

    public void setSubCategoriaDate(Date subCategoriaDate)
    {
        this.subCategoriaDate = subCategoriaDate;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (pkGrlupdate != null ? pkGrlupdate.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GrlUpdate))
        {
            return false;
        }
        GrlUpdate other = (GrlUpdate) object;
        if ((this.pkGrlupdate == null && other.pkGrlupdate != null) || (this.pkGrlupdate != null && !this.pkGrlupdate.equals(other.pkGrlupdate)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.GrlUpdate[ pkGrlupdate=" + pkGrlupdate + " ]";
    }
    
}
