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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author smakambo
 */
@Entity
@Table(name = "gs_equipamento", catalog = "sig_ucan_db", schema = "public")
@NamedQueries(
{
    @NamedQuery(name = "GsEquipamento.findAll", query = "SELECT g FROM GsEquipamento g"),
    @NamedQuery(name = "GsEquipamento.findByPkGsEquipamento", query = "SELECT g FROM GsEquipamento g WHERE g.pkGsEquipamento = :pkGsEquipamento"),
    @NamedQuery(name = "GsEquipamento.findByMarca", query = "SELECT g FROM GsEquipamento g WHERE g.marca = :marca"),
    @NamedQuery(name = "GsEquipamento.findByModelo", query = "SELECT g FROM GsEquipamento g WHERE g.modelo = :modelo"),
    @NamedQuery(name = "GsEquipamento.findByNumeroSerie", query = "SELECT g FROM GsEquipamento g WHERE g.numeroSerie = :numeroSerie"),
    @NamedQuery(name = "GsEquipamento.findByMacAdress", query = "SELECT g FROM GsEquipamento g WHERE g.macAdress = :macAdress")
})
public class GsEquipamento implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_gs_equipamento", nullable = false)
    private Integer pkGsEquipamento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(nullable = false, length = 2147483647)
    private String marca;
    @Size(max = 2147483647)
    @Column(length = 2147483647)
    private String modelo;
    @Size(max = 2147483647)
    @Column(name = "numero_serie", length = 2147483647)
    private String numeroSerie;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "mac_adress", nullable = false, length = 2147483647)
    private String macAdress;
    @OneToMany(mappedBy = "fkGsEquipamento")
    private List<GsInstalacao> gsInstalacaoList;

    public GsEquipamento()
    {
    }

    public GsEquipamento(Integer pkGsEquipamento)
    {
        this.pkGsEquipamento = pkGsEquipamento;
    }

    public GsEquipamento(Integer pkGsEquipamento, String marca, String macAdress)
    {
        this.pkGsEquipamento = pkGsEquipamento;
        this.marca = marca;
        this.macAdress = macAdress;
    }

    public Integer getPkGsEquipamento()
    {
        return pkGsEquipamento;
    }

    public void setPkGsEquipamento(Integer pkGsEquipamento)
    {
        this.pkGsEquipamento = pkGsEquipamento;
    }

    public String getMarca()
    {
        return marca;
    }

    public void setMarca(String marca)
    {
        this.marca = marca;
    }

    public String getModelo()
    {
        return modelo;
    }

    public void setModelo(String modelo)
    {
        this.modelo = modelo;
    }

    public String getNumeroSerie()
    {
        return numeroSerie;
    }

    public void setNumeroSerie(String numeroSerie)
    {
        this.numeroSerie = numeroSerie;
    }

    public String getMacAdress()
    {
        return macAdress;
    }

    public void setMacAdress(String macAdress)
    {
        this.macAdress = macAdress;
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
        hash += (pkGsEquipamento != null ? pkGsEquipamento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GsEquipamento))
        {
            return false;
        }
        GsEquipamento other = (GsEquipamento) object;
        if ((this.pkGsEquipamento == null && other.pkGsEquipamento != null) || (this.pkGsEquipamento != null && !this.pkGsEquipamento.equals(other.pkGsEquipamento)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.GsEquipamento[ pkGsEquipamento=" + pkGsEquipamento + " ]";
    }
    
}
