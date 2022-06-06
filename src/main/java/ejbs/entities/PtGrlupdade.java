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
@Table(name = "pt_grlupdade", catalog = "sig_ucan_db", schema = "public")
@NamedQueries(
{
    @NamedQuery(name = "PtGrlupdade.findAll", query = "SELECT p FROM PtGrlupdade p"),
    @NamedQuery(name = "PtGrlupdade.findByPkPtGrlupdate", query = "SELECT p FROM PtGrlupdade p WHERE p.pkPtGrlupdate = :pkPtGrlupdate"),
    @NamedQuery(name = "PtGrlupdade.findByPtMarcaDate", query = "SELECT p FROM PtGrlupdade p WHERE p.ptMarcaDate = :ptMarcaDate"),
    @NamedQuery(name = "PtGrlupdade.findByPtModeloDate", query = "SELECT p FROM PtGrlupdade p WHERE p.ptModeloDate = :ptModeloDate"),
    @NamedQuery(name = "PtGrlupdade.findByPtCategoriaDate", query = "SELECT p FROM PtGrlupdade p WHERE p.ptCategoriaDate = :ptCategoriaDate"),
    @NamedQuery(name = "PtGrlupdade.findByPtFormaAquisicaoDate", query = "SELECT p FROM PtGrlupdade p WHERE p.ptFormaAquisicaoDate = :ptFormaAquisicaoDate"),
    @NamedQuery(name = "PtGrlupdade.findByPtEstadoConservacaoDate", query = "SELECT p FROM PtGrlupdade p WHERE p.ptEstadoConservacaoDate = :ptEstadoConservacaoDate"),
    @NamedQuery(name = "PtGrlupdade.findByPtTipoSaidaBemDate", query = "SELECT p FROM PtGrlupdade p WHERE p.ptTipoSaidaBemDate = :ptTipoSaidaBemDate")
})
public class PtGrlupdade implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_pt_grlupdate", nullable = false)
    private Integer pkPtGrlupdate;
    @Column(name = "pt_marca_date")
    @Temporal(TemporalType.DATE)
    private Date ptMarcaDate;
    @Column(name = "pt_modelo_date")
    @Temporal(TemporalType.DATE)
    private Date ptModeloDate;
    @Column(name = "pt_categoria_date")
    @Temporal(TemporalType.DATE)
    private Date ptCategoriaDate;
    @Column(name = "pt_forma_aquisicao_date")
    @Temporal(TemporalType.DATE)
    private Date ptFormaAquisicaoDate;
    @Column(name = "pt_estado_conservacao_date")
    @Temporal(TemporalType.DATE)
    private Date ptEstadoConservacaoDate;
    @Column(name = "pt_tipo_saida_bem_date")
    @Temporal(TemporalType.DATE)
    private Date ptTipoSaidaBemDate;

    public PtGrlupdade()
    {
    }

    public PtGrlupdade(Integer pkPtGrlupdate)
    {
        this.pkPtGrlupdate = pkPtGrlupdate;
    }

    public Integer getPkPtGrlupdate()
    {
        return pkPtGrlupdate;
    }

    public void setPkPtGrlupdate(Integer pkPtGrlupdate)
    {
        this.pkPtGrlupdate = pkPtGrlupdate;
    }

    public Date getPtMarcaDate()
    {
        return ptMarcaDate;
    }

    public void setPtMarcaDate(Date ptMarcaDate)
    {
        this.ptMarcaDate = ptMarcaDate;
    }

    public Date getPtModeloDate()
    {
        return ptModeloDate;
    }

    public void setPtModeloDate(Date ptModeloDate)
    {
        this.ptModeloDate = ptModeloDate;
    }

    public Date getPtCategoriaDate()
    {
        return ptCategoriaDate;
    }

    public void setPtCategoriaDate(Date ptCategoriaDate)
    {
        this.ptCategoriaDate = ptCategoriaDate;
    }

    public Date getPtFormaAquisicaoDate()
    {
        return ptFormaAquisicaoDate;
    }

    public void setPtFormaAquisicaoDate(Date ptFormaAquisicaoDate)
    {
        this.ptFormaAquisicaoDate = ptFormaAquisicaoDate;
    }

    public Date getPtEstadoConservacaoDate()
    {
        return ptEstadoConservacaoDate;
    }

    public void setPtEstadoConservacaoDate(Date ptEstadoConservacaoDate)
    {
        this.ptEstadoConservacaoDate = ptEstadoConservacaoDate;
    }

    public Date getPtTipoSaidaBemDate()
    {
        return ptTipoSaidaBemDate;
    }

    public void setPtTipoSaidaBemDate(Date ptTipoSaidaBemDate)
    {
        this.ptTipoSaidaBemDate = ptTipoSaidaBemDate;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (pkPtGrlupdate != null ? pkPtGrlupdate.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PtGrlupdade))
        {
            return false;
        }
        PtGrlupdade other = (PtGrlupdade) object;
        if ((this.pkPtGrlupdate == null && other.pkPtGrlupdate != null) || (this.pkPtGrlupdate != null && !this.pkPtGrlupdate.equals(other.pkPtGrlupdate)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.PtGrlupdade[ pkPtGrlupdate=" + pkPtGrlupdate + " ]";
    }
    
}
