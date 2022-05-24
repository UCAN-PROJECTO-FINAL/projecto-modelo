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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author smakambo
 */
@Entity
@Table(name = "frt_transporte_solicitacao")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "FrtTransporteSolicitacao.findAll", query = "SELECT f FROM FrtTransporteSolicitacao f"),
    @NamedQuery(name = "FrtTransporteSolicitacao.findByPkSolicitacao", query = "SELECT f FROM FrtTransporteSolicitacao f WHERE f.pkSolicitacao = :pkSolicitacao"),
    @NamedQuery(name = "FrtTransporteSolicitacao.findByDestinoSolicitacao", query = "SELECT f FROM FrtTransporteSolicitacao f WHERE f.destinoSolicitacao = :destinoSolicitacao"),
    @NamedQuery(name = "FrtTransporteSolicitacao.findByQtdPessoasSolicitacao", query = "SELECT f FROM FrtTransporteSolicitacao f WHERE f.qtdPessoasSolicitacao = :qtdPessoasSolicitacao"),
    @NamedQuery(name = "FrtTransporteSolicitacao.findByMotivoSolicitacao", query = "SELECT f FROM FrtTransporteSolicitacao f WHERE f.motivoSolicitacao = :motivoSolicitacao"),
    @NamedQuery(name = "FrtTransporteSolicitacao.findByDataSaidaSolicitacao", query = "SELECT f FROM FrtTransporteSolicitacao f WHERE f.dataSaidaSolicitacao = :dataSaidaSolicitacao"),
    @NamedQuery(name = "FrtTransporteSolicitacao.findByFkSolicitante", query = "SELECT f FROM FrtTransporteSolicitacao f WHERE f.fkSolicitante = :fkSolicitante"),
    @NamedQuery(name = "FrtTransporteSolicitacao.findByDataSolicitacao", query = "SELECT f FROM FrtTransporteSolicitacao f WHERE f.dataSolicitacao = :dataSolicitacao"),
    @NamedQuery(name = "FrtTransporteSolicitacao.findByObservacaoTransporteSolicitacao", query = "SELECT f FROM FrtTransporteSolicitacao f WHERE f.observacaoTransporteSolicitacao = :observacaoTransporteSolicitacao")
})
public class FrtTransporteSolicitacao implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_solicitacao")
    private Integer pkSolicitacao;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "destino_solicitacao")
    private String destinoSolicitacao;
    @Basic(optional = false)
    @NotNull
    @Column(name = "qtd_pessoas_solicitacao")
    private int qtdPessoasSolicitacao;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "motivo_solicitacao")
    private String motivoSolicitacao;
    @Basic(optional = false)
    @NotNull
    @Column(name = "data_saida_solicitacao")
    @Temporal(TemporalType.DATE)
    private Date dataSaidaSolicitacao;
    @Column(name = "fk_solicitante")
    private Integer fkSolicitante;
    @Basic(optional = false)
    @NotNull
    @Column(name = "data_solicitacao")
    @Temporal(TemporalType.DATE)
    private Date dataSolicitacao;
    @Size(max = 2147483647)
    @Column(name = "observacao_transporte_solicitacao")
    private String observacaoTransporteSolicitacao;
    @OneToMany(mappedBy = "fkTransporteSolicitacao")
    private List<FrtTransporteAtendimento> frtTransporteAtendimentoList;
    @JoinColumn(name = "fk_estrututura_logica", referencedColumnName = "pk_estrutura_logica")
    @ManyToOne
    private EstruturaLogica fkEstrututuraLogica;
    @JoinColumn(name = "fk_transporte_solicitacao_estaso", referencedColumnName = "pk_transporte_solicitacao_estado")
    @ManyToOne
    private FrtTransporteSolicitacaoEstado fkTransporteSolicitacaoEstaso;
    @JoinColumn(name = "fk_tipo_solicitacao", referencedColumnName = "pk_tipo_solicitacao")
    @ManyToOne
    private GrlTipoSolicitacao fkTipoSolicitacao;
    @JoinColumn(name = "fk_tipo_transporte", referencedColumnName = "pk_tipo_transporte")
    @ManyToOne
    private PtTransporteTipo fkTipoTransporte;

    public FrtTransporteSolicitacao()
    {
    }

    public FrtTransporteSolicitacao(Integer pkSolicitacao)
    {
        this.pkSolicitacao = pkSolicitacao;
    }

    public FrtTransporteSolicitacao(Integer pkSolicitacao, String destinoSolicitacao, int qtdPessoasSolicitacao, String motivoSolicitacao, Date dataSaidaSolicitacao, Date dataSolicitacao)
    {
        this.pkSolicitacao = pkSolicitacao;
        this.destinoSolicitacao = destinoSolicitacao;
        this.qtdPessoasSolicitacao = qtdPessoasSolicitacao;
        this.motivoSolicitacao = motivoSolicitacao;
        this.dataSaidaSolicitacao = dataSaidaSolicitacao;
        this.dataSolicitacao = dataSolicitacao;
    }

    public Integer getPkSolicitacao()
    {
        return pkSolicitacao;
    }

    public void setPkSolicitacao(Integer pkSolicitacao)
    {
        this.pkSolicitacao = pkSolicitacao;
    }

    public String getDestinoSolicitacao()
    {
        return destinoSolicitacao;
    }

    public void setDestinoSolicitacao(String destinoSolicitacao)
    {
        this.destinoSolicitacao = destinoSolicitacao;
    }

    public int getQtdPessoasSolicitacao()
    {
        return qtdPessoasSolicitacao;
    }

    public void setQtdPessoasSolicitacao(int qtdPessoasSolicitacao)
    {
        this.qtdPessoasSolicitacao = qtdPessoasSolicitacao;
    }

    public String getMotivoSolicitacao()
    {
        return motivoSolicitacao;
    }

    public void setMotivoSolicitacao(String motivoSolicitacao)
    {
        this.motivoSolicitacao = motivoSolicitacao;
    }

    public Date getDataSaidaSolicitacao()
    {
        return dataSaidaSolicitacao;
    }

    public void setDataSaidaSolicitacao(Date dataSaidaSolicitacao)
    {
        this.dataSaidaSolicitacao = dataSaidaSolicitacao;
    }

    public Integer getFkSolicitante()
    {
        return fkSolicitante;
    }

    public void setFkSolicitante(Integer fkSolicitante)
    {
        this.fkSolicitante = fkSolicitante;
    }

    public Date getDataSolicitacao()
    {
        return dataSolicitacao;
    }

    public void setDataSolicitacao(Date dataSolicitacao)
    {
        this.dataSolicitacao = dataSolicitacao;
    }

    public String getObservacaoTransporteSolicitacao()
    {
        return observacaoTransporteSolicitacao;
    }

    public void setObservacaoTransporteSolicitacao(String observacaoTransporteSolicitacao)
    {
        this.observacaoTransporteSolicitacao = observacaoTransporteSolicitacao;
    }

    @XmlTransient
    public List<FrtTransporteAtendimento> getFrtTransporteAtendimentoList()
    {
        return frtTransporteAtendimentoList;
    }

    public void setFrtTransporteAtendimentoList(List<FrtTransporteAtendimento> frtTransporteAtendimentoList)
    {
        this.frtTransporteAtendimentoList = frtTransporteAtendimentoList;
    }

    public EstruturaLogica getFkEstrututuraLogica()
    {
        return fkEstrututuraLogica;
    }

    public void setFkEstrututuraLogica(EstruturaLogica fkEstrututuraLogica)
    {
        this.fkEstrututuraLogica = fkEstrututuraLogica;
    }

    public FrtTransporteSolicitacaoEstado getFkTransporteSolicitacaoEstaso()
    {
        return fkTransporteSolicitacaoEstaso;
    }

    public void setFkTransporteSolicitacaoEstaso(FrtTransporteSolicitacaoEstado fkTransporteSolicitacaoEstaso)
    {
        this.fkTransporteSolicitacaoEstaso = fkTransporteSolicitacaoEstaso;
    }

    public GrlTipoSolicitacao getFkTipoSolicitacao()
    {
        return fkTipoSolicitacao;
    }

    public void setFkTipoSolicitacao(GrlTipoSolicitacao fkTipoSolicitacao)
    {
        this.fkTipoSolicitacao = fkTipoSolicitacao;
    }

    public PtTransporteTipo getFkTipoTransporte()
    {
        return fkTipoTransporte;
    }

    public void setFkTipoTransporte(PtTransporteTipo fkTipoTransporte)
    {
        this.fkTipoTransporte = fkTipoTransporte;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (pkSolicitacao != null ? pkSolicitacao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FrtTransporteSolicitacao))
        {
            return false;
        }
        FrtTransporteSolicitacao other = (FrtTransporteSolicitacao) object;
        if ((this.pkSolicitacao == null && other.pkSolicitacao != null) || (this.pkSolicitacao != null && !this.pkSolicitacao.equals(other.pkSolicitacao)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.FrtTransporteSolicitacao[ pkSolicitacao=" + pkSolicitacao + " ]";
    }
    
}
