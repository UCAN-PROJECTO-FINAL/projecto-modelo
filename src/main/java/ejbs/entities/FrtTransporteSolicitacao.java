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

/**
 *
 * @author smakambo
 */
@Entity
@Table(name = "frt_transporte_solicitacao", catalog = "sig_ucan_db", schema = "public")
@NamedQueries(
{
    @NamedQuery(name = "FrtTransporteSolicitacao.findAll", query = "SELECT f FROM FrtTransporteSolicitacao f"),
    @NamedQuery(name = "FrtTransporteSolicitacao.findByPkSolicitacao", query = "SELECT f FROM FrtTransporteSolicitacao f WHERE f.pkSolicitacao = :pkSolicitacao"),
    @NamedQuery(name = "FrtTransporteSolicitacao.findByQtdPessoasSolicitacao", query = "SELECT f FROM FrtTransporteSolicitacao f WHERE f.qtdPessoasSolicitacao = :qtdPessoasSolicitacao"),
    @NamedQuery(name = "FrtTransporteSolicitacao.findByMotivoSolicitacao", query = "SELECT f FROM FrtTransporteSolicitacao f WHERE f.motivoSolicitacao = :motivoSolicitacao"),
    @NamedQuery(name = "FrtTransporteSolicitacao.findByDataSaidaSolicitacao", query = "SELECT f FROM FrtTransporteSolicitacao f WHERE f.dataSaidaSolicitacao = :dataSaidaSolicitacao"),
    @NamedQuery(name = "FrtTransporteSolicitacao.findByFkSolicitante", query = "SELECT f FROM FrtTransporteSolicitacao f WHERE f.fkSolicitante = :fkSolicitante"),
    @NamedQuery(name = "FrtTransporteSolicitacao.findByDataSolicitacao", query = "SELECT f FROM FrtTransporteSolicitacao f WHERE f.dataSolicitacao = :dataSolicitacao"),
    @NamedQuery(name = "FrtTransporteSolicitacao.findByObservacaoTransporteSolicitacao", query = "SELECT f FROM FrtTransporteSolicitacao f WHERE f.observacaoTransporteSolicitacao = :observacaoTransporteSolicitacao"),
    @NamedQuery(name = "FrtTransporteSolicitacao.findByDescricaoCarga", query = "SELECT f FROM FrtTransporteSolicitacao f WHERE f.descricaoCarga = :descricaoCarga")
})
public class FrtTransporteSolicitacao implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_solicitacao", nullable = false)
    private Integer pkSolicitacao;
    @Basic(optional = false)
    @NotNull
    @Column(name = "qtd_pessoas_solicitacao", nullable = false)
    private int qtdPessoasSolicitacao;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "motivo_solicitacao", nullable = false, length = 50)
    private String motivoSolicitacao;
    @Basic(optional = false)
    @NotNull
    @Column(name = "data_saida_solicitacao", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataSaidaSolicitacao;
    @Column(name = "fk_solicitante")
    private Integer fkSolicitante;
    @Basic(optional = false)
    @NotNull
    @Column(name = "data_solicitacao", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataSolicitacao;
    @Size(max = 2147483647)
    @Column(name = "observacao_transporte_solicitacao", length = 2147483647)
    private String observacaoTransporteSolicitacao;
    @Size(max = 2147483647)
    @Column(name = "descricao_carga", length = 2147483647)
    private String descricaoCarga;
    @OneToMany(mappedBy = "fkTransporteSolicitacao")
    private List<FrtTransporteAtendimento> frtTransporteAtendimentoList;
    @JoinColumn(name = "fk_estrutura_logica_fisica", referencedColumnName = "pk_estrutura_logica_fisica")
    @ManyToOne
    private EstruturaLogicaFisica fkEstruturaLogicaFisica;
    @JoinColumn(name = "fk_transporte_solicitacao_estado", referencedColumnName = "pk_transporte_solicitacao_estado")
    @ManyToOne
    private FrtTransporteSolicitacaoEstado fkTransporteSolicitacaoEstado;
    @JoinColumn(name = "fk_grl_endereco", referencedColumnName = "pk_id_endereco")
    @ManyToOne
    private GrlEndereco fkGrlEndereco;
    @JoinColumn(name = "fk_tipo_solicitacao", referencedColumnName = "pk_tipo_solicitacao")
    @ManyToOne
    private GrlTipoSolicitacao fkTipoSolicitacao;
    @JoinColumn(name = "fk_tipo_transporte", referencedColumnName = "pk_tipo_transporte")
    @ManyToOne
    private PtTransporteTipo fkTipoTransporte;
    @JoinColumn(name = "fk_conta", referencedColumnName = "pk_seg_conta")
    @ManyToOne
    private SegConta fkConta;

    public FrtTransporteSolicitacao()
    {
    }

    public FrtTransporteSolicitacao(Integer pkSolicitacao)
    {
        this.pkSolicitacao = pkSolicitacao;
    }

    public FrtTransporteSolicitacao(Integer pkSolicitacao, int qtdPessoasSolicitacao, String motivoSolicitacao, Date dataSaidaSolicitacao, Date dataSolicitacao)
    {
        this.pkSolicitacao = pkSolicitacao;
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

    public String getDescricaoCarga()
    {
        return descricaoCarga;
    }

    public void setDescricaoCarga(String descricaoCarga)
    {
        this.descricaoCarga = descricaoCarga;
    }

    public List<FrtTransporteAtendimento> getFrtTransporteAtendimentoList()
    {
        return frtTransporteAtendimentoList;
    }

    public void setFrtTransporteAtendimentoList(List<FrtTransporteAtendimento> frtTransporteAtendimentoList)
    {
        this.frtTransporteAtendimentoList = frtTransporteAtendimentoList;
    }

    public EstruturaLogicaFisica getFkEstruturaLogicaFisica()
    {
        return fkEstruturaLogicaFisica;
    }

    public void setFkEstruturaLogicaFisica(EstruturaLogicaFisica fkEstruturaLogicaFisica)
    {
        this.fkEstruturaLogicaFisica = fkEstruturaLogicaFisica;
    }

    public FrtTransporteSolicitacaoEstado getFkTransporteSolicitacaoEstado()
    {
        return fkTransporteSolicitacaoEstado;
    }

    public void setFkTransporteSolicitacaoEstado(FrtTransporteSolicitacaoEstado fkTransporteSolicitacaoEstado)
    {
        this.fkTransporteSolicitacaoEstado = fkTransporteSolicitacaoEstado;
    }

    public GrlEndereco getFkGrlEndereco()
    {
        return fkGrlEndereco;
    }

    public void setFkGrlEndereco(GrlEndereco fkGrlEndereco)
    {
        this.fkGrlEndereco = fkGrlEndereco;
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

    public SegConta getFkConta()
    {
        return fkConta;
    }

    public void setFkConta(SegConta fkConta)
    {
        this.fkConta = fkConta;
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
