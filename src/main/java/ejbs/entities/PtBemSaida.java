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

/**
 *
 * @author smakambo
 */
@Entity
@Table(name = "pt_bem_saida", catalog = "sig_ucan_db", schema = "public")
@NamedQueries(
{
    @NamedQuery(name = "PtBemSaida.findAll", query = "SELECT p FROM PtBemSaida p"),
    @NamedQuery(name = "PtBemSaida.findByPkPtBemSaida", query = "SELECT p FROM PtBemSaida p WHERE p.pkPtBemSaida = :pkPtBemSaida"),
    @NamedQuery(name = "PtBemSaida.findByFkPtTipoSaidaBem", query = "SELECT p FROM PtBemSaida p WHERE p.fkPtTipoSaidaBem = :fkPtTipoSaidaBem"),
    @NamedQuery(name = "PtBemSaida.findByDataSaida", query = "SELECT p FROM PtBemSaida p WHERE p.dataSaida = :dataSaida")
})
public class PtBemSaida implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_pt_bem_saida", nullable = false)
    private Integer pkPtBemSaida;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fk_pt_tipo_saida_bem", nullable = false)
    private int fkPtTipoSaidaBem;
    @Basic(optional = false)
    @NotNull
    @Column(name = "data_saida", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataSaida;
    @JoinColumn(name = "fk_pt_bem_imovel", referencedColumnName = "pk_pt_bem_imovel")
    @ManyToOne
    private PtBemImovel fkPtBemImovel;
    @JoinColumn(name = "fk_pt_bem_itangivel", referencedColumnName = "pk_pt_bem_itangivel")
    @ManyToOne
    private PtBemItangivel fkPtBemItangivel;
    @JoinColumn(name = "fk_pt_bem_movel", referencedColumnName = "pk_pt_bem_movel")
    @ManyToOne
    private PtBemMovel fkPtBemMovel;

    public PtBemSaida()
    {
    }

    public PtBemSaida(Integer pkPtBemSaida)
    {
        this.pkPtBemSaida = pkPtBemSaida;
    }

    public PtBemSaida(Integer pkPtBemSaida, int fkPtTipoSaidaBem, Date dataSaida)
    {
        this.pkPtBemSaida = pkPtBemSaida;
        this.fkPtTipoSaidaBem = fkPtTipoSaidaBem;
        this.dataSaida = dataSaida;
    }

    public Integer getPkPtBemSaida()
    {
        return pkPtBemSaida;
    }

    public void setPkPtBemSaida(Integer pkPtBemSaida)
    {
        this.pkPtBemSaida = pkPtBemSaida;
    }

    public int getFkPtTipoSaidaBem()
    {
        return fkPtTipoSaidaBem;
    }

    public void setFkPtTipoSaidaBem(int fkPtTipoSaidaBem)
    {
        this.fkPtTipoSaidaBem = fkPtTipoSaidaBem;
    }

    public Date getDataSaida()
    {
        return dataSaida;
    }

    public void setDataSaida(Date dataSaida)
    {
        this.dataSaida = dataSaida;
    }

    public PtBemImovel getFkPtBemImovel()
    {
        return fkPtBemImovel;
    }

    public void setFkPtBemImovel(PtBemImovel fkPtBemImovel)
    {
        this.fkPtBemImovel = fkPtBemImovel;
    }

    public PtBemItangivel getFkPtBemItangivel()
    {
        return fkPtBemItangivel;
    }

    public void setFkPtBemItangivel(PtBemItangivel fkPtBemItangivel)
    {
        this.fkPtBemItangivel = fkPtBemItangivel;
    }

    public PtBemMovel getFkPtBemMovel()
    {
        return fkPtBemMovel;
    }

    public void setFkPtBemMovel(PtBemMovel fkPtBemMovel)
    {
        this.fkPtBemMovel = fkPtBemMovel;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (pkPtBemSaida != null ? pkPtBemSaida.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PtBemSaida))
        {
            return false;
        }
        PtBemSaida other = (PtBemSaida) object;
        if ((this.pkPtBemSaida == null && other.pkPtBemSaida != null) || (this.pkPtBemSaida != null && !this.pkPtBemSaida.equals(other.pkPtBemSaida)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.PtBemSaida[ pkPtBemSaida=" + pkPtBemSaida + " ]";
    }
    
}
