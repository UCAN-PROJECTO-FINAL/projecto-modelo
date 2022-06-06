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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author smakambo
 */
@Entity
@Table(name = "pt_bem_historico", catalog = "sig_ucan_db", schema = "public")
@NamedQueries(
{
    @NamedQuery(name = "PtBemHistorico.findAll", query = "SELECT p FROM PtBemHistorico p"),
    @NamedQuery(name = "PtBemHistorico.findByPkPtBemHistorico", query = "SELECT p FROM PtBemHistorico p WHERE p.pkPtBemHistorico = :pkPtBemHistorico"),
    @NamedQuery(name = "PtBemHistorico.findByDetalhes", query = "SELECT p FROM PtBemHistorico p WHERE p.detalhes = :detalhes")
})
public class PtBemHistorico implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_pt_bem_historico", nullable = false)
    private Integer pkPtBemHistorico;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(nullable = false, length = 2147483647)
    private String detalhes;
    @JoinColumn(name = "fk_pt_bem_imovel", referencedColumnName = "pk_pt_bem_imovel")
    @ManyToOne
    private PtBemImovel fkPtBemImovel;
    @JoinColumn(name = "fk_pt_bem_itangivel", referencedColumnName = "pk_pt_bem_itangivel")
    @ManyToOne
    private PtBemItangivel fkPtBemItangivel;
    @JoinColumn(name = "fk_pt_bem_movel", referencedColumnName = "pk_pt_bem_movel")
    @ManyToOne
    private PtBemMovel fkPtBemMovel;

    public PtBemHistorico()
    {
    }

    public PtBemHistorico(Integer pkPtBemHistorico)
    {
        this.pkPtBemHistorico = pkPtBemHistorico;
    }

    public PtBemHistorico(Integer pkPtBemHistorico, String detalhes)
    {
        this.pkPtBemHistorico = pkPtBemHistorico;
        this.detalhes = detalhes;
    }

    public Integer getPkPtBemHistorico()
    {
        return pkPtBemHistorico;
    }

    public void setPkPtBemHistorico(Integer pkPtBemHistorico)
    {
        this.pkPtBemHistorico = pkPtBemHistorico;
    }

    public String getDetalhes()
    {
        return detalhes;
    }

    public void setDetalhes(String detalhes)
    {
        this.detalhes = detalhes;
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
        hash += (pkPtBemHistorico != null ? pkPtBemHistorico.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PtBemHistorico))
        {
            return false;
        }
        PtBemHistorico other = (PtBemHistorico) object;
        if ((this.pkPtBemHistorico == null && other.pkPtBemHistorico != null) || (this.pkPtBemHistorico != null && !this.pkPtBemHistorico.equals(other.pkPtBemHistorico)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.PtBemHistorico[ pkPtBemHistorico=" + pkPtBemHistorico + " ]";
    }
    
}
