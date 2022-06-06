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
@Table(name = "loc_grl_update", catalog = "sig_ucan_db", schema = "public")
@NamedQueries(
{
    @NamedQuery(name = "LocGrlUpdate.findAll", query = "SELECT l FROM LocGrlUpdate l"),
    @NamedQuery(name = "LocGrlUpdate.findByPkLocGrlUpdate", query = "SELECT l FROM LocGrlUpdate l WHERE l.pkLocGrlUpdate = :pkLocGrlUpdate"),
    @NamedQuery(name = "LocGrlUpdate.findByLocalidadesDate", query = "SELECT l FROM LocGrlUpdate l WHERE l.localidadesDate = :localidadesDate"),
    @NamedQuery(name = "LocGrlUpdate.findByEstruturaLogicaDate", query = "SELECT l FROM LocGrlUpdate l WHERE l.estruturaLogicaDate = :estruturaLogicaDate"),
    @NamedQuery(name = "LocGrlUpdate.findByEstruturaFisicaDate", query = "SELECT l FROM LocGrlUpdate l WHERE l.estruturaFisicaDate = :estruturaFisicaDate"),
    @NamedQuery(name = "LocGrlUpdate.findByBancoDate", query = "SELECT l FROM LocGrlUpdate l WHERE l.bancoDate = :bancoDate"),
    @NamedQuery(name = "LocGrlUpdate.findByTipoCartaoDate", query = "SELECT l FROM LocGrlUpdate l WHERE l.tipoCartaoDate = :tipoCartaoDate"),
    @NamedQuery(name = "LocGrlUpdate.findByTipoContaDate", query = "SELECT l FROM LocGrlUpdate l WHERE l.tipoContaDate = :tipoContaDate"),
    @NamedQuery(name = "LocGrlUpdate.findByMoedaDate", query = "SELECT l FROM LocGrlUpdate l WHERE l.moedaDate = :moedaDate"),
    @NamedQuery(name = "LocGrlUpdate.findByComunaDate", query = "SELECT l FROM LocGrlUpdate l WHERE l.comunaDate = :comunaDate"),
    @NamedQuery(name = "LocGrlUpdate.findByDistritoDate", query = "SELECT l FROM LocGrlUpdate l WHERE l.distritoDate = :distritoDate"),
    @NamedQuery(name = "LocGrlUpdate.findByMunicipioDate", query = "SELECT l FROM LocGrlUpdate l WHERE l.municipioDate = :municipioDate"),
    @NamedQuery(name = "LocGrlUpdate.findByPaisDate", query = "SELECT l FROM LocGrlUpdate l WHERE l.paisDate = :paisDate"),
    @NamedQuery(name = "LocGrlUpdate.findByProvinciaDate", query = "SELECT l FROM LocGrlUpdate l WHERE l.provinciaDate = :provinciaDate"),
    @NamedQuery(name = "LocGrlUpdate.findByTipoCategoria", query = "SELECT l FROM LocGrlUpdate l WHERE l.tipoCategoria = :tipoCategoria"),
    @NamedQuery(name = "LocGrlUpdate.findByCategoriaDate", query = "SELECT l FROM LocGrlUpdate l WHERE l.categoriaDate = :categoriaDate"),
    @NamedQuery(name = "LocGrlUpdate.findBySubCategoriaDate", query = "SELECT l FROM LocGrlUpdate l WHERE l.subCategoriaDate = :subCategoriaDate")
})
public class LocGrlUpdate implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_loc_grl_update", nullable = false)
    private Integer pkLocGrlUpdate;
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
    @Column(name = "sub_categoria_date")
    @Temporal(TemporalType.DATE)
    private Date subCategoriaDate;

    public LocGrlUpdate()
    {
    }

    public LocGrlUpdate(Integer pkLocGrlUpdate)
    {
        this.pkLocGrlUpdate = pkLocGrlUpdate;
    }

    public Integer getPkLocGrlUpdate()
    {
        return pkLocGrlUpdate;
    }

    public void setPkLocGrlUpdate(Integer pkLocGrlUpdate)
    {
        this.pkLocGrlUpdate = pkLocGrlUpdate;
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
        hash += (pkLocGrlUpdate != null ? pkLocGrlUpdate.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LocGrlUpdate))
        {
            return false;
        }
        LocGrlUpdate other = (LocGrlUpdate) object;
        if ((this.pkLocGrlUpdate == null && other.pkLocGrlUpdate != null) || (this.pkLocGrlUpdate != null && !this.pkLocGrlUpdate.equals(other.pkLocGrlUpdate)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.LocGrlUpdate[ pkLocGrlUpdate=" + pkLocGrlUpdate + " ]";
    }
    
}
