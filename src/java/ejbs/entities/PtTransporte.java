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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author smakambo
 */
@Entity
@Table(name = "pt_transporte")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "PtTransporte.findAll", query = "SELECT p FROM PtTransporte p"),
    @NamedQuery(name = "PtTransporte.findByPkPtTransporte", query = "SELECT p FROM PtTransporte p WHERE p.pkPtTransporte = :pkPtTransporte"),
    @NamedQuery(name = "PtTransporte.findByMatricula", query = "SELECT p FROM PtTransporte p WHERE p.matricula = :matricula"),
    @NamedQuery(name = "PtTransporte.findByNumeroMotor", query = "SELECT p FROM PtTransporte p WHERE p.numeroMotor = :numeroMotor"),
    @NamedQuery(name = "PtTransporte.findByNumeroChassi", query = "SELECT p FROM PtTransporte p WHERE p.numeroChassi = :numeroChassi"),
    @NamedQuery(name = "PtTransporte.findByNumeroPortas", query = "SELECT p FROM PtTransporte p WHERE p.numeroPortas = :numeroPortas"),
    @NamedQuery(name = "PtTransporte.findByCapacidadeCarga", query = "SELECT p FROM PtTransporte p WHERE p.capacidadeCarga = :capacidadeCarga"),
    @NamedQuery(name = "PtTransporte.findByLotacao", query = "SELECT p FROM PtTransporte p WHERE p.lotacao = :lotacao"),
    @NamedQuery(name = "PtTransporte.findByAnoFabrico", query = "SELECT p FROM PtTransporte p WHERE p.anoFabrico = :anoFabrico"),
    @NamedQuery(name = "PtTransporte.findByNotasAdicionais", query = "SELECT p FROM PtTransporte p WHERE p.notasAdicionais = :notasAdicionais")
})
public class PtTransporte implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_pt_transporte")
    private Integer pkPtTransporte;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "matricula")
    private String matricula;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "numero_motor")
    private String numeroMotor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "numero_chassi")
    private String numeroChassi;
    @Basic(optional = false)
    @NotNull
    @Column(name = "numero_portas")
    private int numeroPortas;
    @Basic(optional = false)
    @NotNull
    @Column(name = "capacidade_carga")
    private double capacidadeCarga;
    @Basic(optional = false)
    @NotNull
    @Column(name = "lotacao")
    private int lotacao;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ano_fabrico")
    private int anoFabrico;
    @Size(max = 100)
    @Column(name = "notas_adicionais")
    private String notasAdicionais;
    @JoinColumn(name = "fk_categoria_transporte", referencedColumnName = "pk_transporte_categoria")
    @ManyToOne
    private PtTransporteCategoria fkCategoriaTransporte;
    @JoinColumn(name = "fk_estado", referencedColumnName = "pk_estado")
    @ManyToOne
    private PtTransporteEstado fkEstado;
    @JoinColumn(name = "fk_modelo", referencedColumnName = "pk_modelo")
    @ManyToOne
    private PtTransporteModelo fkModelo;
    @JoinColumn(name = "fk_tipo_transporte", referencedColumnName = "pk_tipo_transporte")
    @ManyToOne
    private PtTransporteTipo fkTipoTransporte;
    @JoinColumn(name = "fk_tipo_combustivel", referencedColumnName = "pk_tipo_combustivel")
    @ManyToOne
    private PtTransporteTipoCombustivel fkTipoCombustivel;
    @OneToMany(mappedBy = "fkTransporte")
    private List<FrtTransporteAgendar> frtTransporteAgendarList;

    public PtTransporte()
    {
    }

    public PtTransporte(Integer pkPtTransporte)
    {
        this.pkPtTransporte = pkPtTransporte;
    }

    public PtTransporte(Integer pkPtTransporte, String matricula, String numeroMotor, String numeroChassi, int numeroPortas, double capacidadeCarga, int lotacao, int anoFabrico)
    {
        this.pkPtTransporte = pkPtTransporte;
        this.matricula = matricula;
        this.numeroMotor = numeroMotor;
        this.numeroChassi = numeroChassi;
        this.numeroPortas = numeroPortas;
        this.capacidadeCarga = capacidadeCarga;
        this.lotacao = lotacao;
        this.anoFabrico = anoFabrico;
    }

    public Integer getPkPtTransporte()
    {
        return pkPtTransporte;
    }

    public void setPkPtTransporte(Integer pkPtTransporte)
    {
        this.pkPtTransporte = pkPtTransporte;
    }

    public String getMatricula()
    {
        return matricula;
    }

    public void setMatricula(String matricula)
    {
        this.matricula = matricula;
    }

    public String getNumeroMotor()
    {
        return numeroMotor;
    }

    public void setNumeroMotor(String numeroMotor)
    {
        this.numeroMotor = numeroMotor;
    }

    public String getNumeroChassi()
    {
        return numeroChassi;
    }

    public void setNumeroChassi(String numeroChassi)
    {
        this.numeroChassi = numeroChassi;
    }

    public int getNumeroPortas()
    {
        return numeroPortas;
    }

    public void setNumeroPortas(int numeroPortas)
    {
        this.numeroPortas = numeroPortas;
    }

    public double getCapacidadeCarga()
    {
        return capacidadeCarga;
    }

    public void setCapacidadeCarga(double capacidadeCarga)
    {
        this.capacidadeCarga = capacidadeCarga;
    }

    public int getLotacao()
    {
        return lotacao;
    }

    public void setLotacao(int lotacao)
    {
        this.lotacao = lotacao;
    }

    public int getAnoFabrico()
    {
        return anoFabrico;
    }

    public void setAnoFabrico(int anoFabrico)
    {
        this.anoFabrico = anoFabrico;
    }

    public String getNotasAdicionais()
    {
        return notasAdicionais;
    }

    public void setNotasAdicionais(String notasAdicionais)
    {
        this.notasAdicionais = notasAdicionais;
    }

    public PtTransporteCategoria getFkCategoriaTransporte()
    {
        return fkCategoriaTransporte;
    }

    public void setFkCategoriaTransporte(PtTransporteCategoria fkCategoriaTransporte)
    {
        this.fkCategoriaTransporte = fkCategoriaTransporte;
    }

    public PtTransporteEstado getFkEstado()
    {
        return fkEstado;
    }

    public void setFkEstado(PtTransporteEstado fkEstado)
    {
        this.fkEstado = fkEstado;
    }

    public PtTransporteModelo getFkModelo()
    {
        return fkModelo;
    }

    public void setFkModelo(PtTransporteModelo fkModelo)
    {
        this.fkModelo = fkModelo;
    }

    public PtTransporteTipo getFkTipoTransporte()
    {
        return fkTipoTransporte;
    }

    public void setFkTipoTransporte(PtTransporteTipo fkTipoTransporte)
    {
        this.fkTipoTransporte = fkTipoTransporte;
    }

    public PtTransporteTipoCombustivel getFkTipoCombustivel()
    {
        return fkTipoCombustivel;
    }

    public void setFkTipoCombustivel(PtTransporteTipoCombustivel fkTipoCombustivel)
    {
        this.fkTipoCombustivel = fkTipoCombustivel;
    }

    @XmlTransient
    public List<FrtTransporteAgendar> getFrtTransporteAgendarList()
    {
        return frtTransporteAgendarList;
    }

    public void setFrtTransporteAgendarList(List<FrtTransporteAgendar> frtTransporteAgendarList)
    {
        this.frtTransporteAgendarList = frtTransporteAgendarList;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (pkPtTransporte != null ? pkPtTransporte.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PtTransporte))
        {
            return false;
        }
        PtTransporte other = (PtTransporte) object;
        if ((this.pkPtTransporte == null && other.pkPtTransporte != null) || (this.pkPtTransporte != null && !this.pkPtTransporte.equals(other.pkPtTransporte)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.PtTransporte[ pkPtTransporte=" + pkPtTransporte + " ]";
    }
    
}
