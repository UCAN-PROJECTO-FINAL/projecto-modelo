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
import javax.validation.constraints.Size;

/**
 *
 * @author smakambo
 */
@Entity
@Table(name = "frt_transporte_atendimento", catalog = "sig_ucan_db", schema = "public")
@NamedQueries(
{
    @NamedQuery(name = "FrtTransporteAtendimento.findAll", query = "SELECT f FROM FrtTransporteAtendimento f"),
    @NamedQuery(name = "FrtTransporteAtendimento.findByPkTransporteAtendimento", query = "SELECT f FROM FrtTransporteAtendimento f WHERE f.pkTransporteAtendimento = :pkTransporteAtendimento"),
    @NamedQuery(name = "FrtTransporteAtendimento.findByHoraSaida", query = "SELECT f FROM FrtTransporteAtendimento f WHERE f.horaSaida = :horaSaida"),
    @NamedQuery(name = "FrtTransporteAtendimento.findByQuilometroInicial", query = "SELECT f FROM FrtTransporteAtendimento f WHERE f.quilometroInicial = :quilometroInicial"),
    @NamedQuery(name = "FrtTransporteAtendimento.findByDataChegada", query = "SELECT f FROM FrtTransporteAtendimento f WHERE f.dataChegada = :dataChegada"),
    @NamedQuery(name = "FrtTransporteAtendimento.findByHoraChegada", query = "SELECT f FROM FrtTransporteAtendimento f WHERE f.horaChegada = :horaChegada"),
    @NamedQuery(name = "FrtTransporteAtendimento.findByQuilometroFinal", query = "SELECT f FROM FrtTransporteAtendimento f WHERE f.quilometroFinal = :quilometroFinal"),
    @NamedQuery(name = "FrtTransporteAtendimento.findByPercursoPercorrido", query = "SELECT f FROM FrtTransporteAtendimento f WHERE f.percursoPercorrido = :percursoPercorrido"),
    @NamedQuery(name = "FrtTransporteAtendimento.findByObservacao", query = "SELECT f FROM FrtTransporteAtendimento f WHERE f.observacao = :observacao")
})
public class FrtTransporteAtendimento implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_transporte_atendimento", nullable = false)
    private Integer pkTransporteAtendimento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "hora_saida", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date horaSaida;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "quilometro_inicial", nullable = false, length = 2147483647)
    private String quilometroInicial;
    @Column(name = "data_chegada")
    @Temporal(TemporalType.DATE)
    private Date dataChegada;
    @Column(name = "hora_chegada")
    @Temporal(TemporalType.TIMESTAMP)
    private Date horaChegada;
    @Size(max = 2147483647)
    @Column(name = "quilometro_final", length = 2147483647)
    private String quilometroFinal;
    @Size(max = 2147483647)
    @Column(name = "percurso_percorrido", length = 2147483647)
    private String percursoPercorrido;
    @Size(max = 2147483647)
    @Column(length = 2147483647)
    private String observacao;
    @JoinColumn(name = "fk_transporte_agendamento", referencedColumnName = "pk_agendar_transporte")
    @ManyToOne
    private FrtTransporteAgendar fkTransporteAgendamento;
    @JoinColumn(name = "fk_transporte_solicitacao", referencedColumnName = "pk_solicitacao")
    @ManyToOne
    private FrtTransporteSolicitacao fkTransporteSolicitacao;
    @JoinColumn(name = "fk_transporte_atendiment_estado", referencedColumnName = "pk_transporte_solicitacao_estado")
    @ManyToOne
    private FrtTransporteSolicitacaoEstado fkTransporteAtendimentEstado;

    public FrtTransporteAtendimento()
    {
    }

    public FrtTransporteAtendimento(Integer pkTransporteAtendimento)
    {
        this.pkTransporteAtendimento = pkTransporteAtendimento;
    }

    public FrtTransporteAtendimento(Integer pkTransporteAtendimento, Date horaSaida, String quilometroInicial)
    {
        this.pkTransporteAtendimento = pkTransporteAtendimento;
        this.horaSaida = horaSaida;
        this.quilometroInicial = quilometroInicial;
    }

    public Integer getPkTransporteAtendimento()
    {
        return pkTransporteAtendimento;
    }

    public void setPkTransporteAtendimento(Integer pkTransporteAtendimento)
    {
        this.pkTransporteAtendimento = pkTransporteAtendimento;
    }

    public Date getHoraSaida()
    {
        return horaSaida;
    }

    public void setHoraSaida(Date horaSaida)
    {
        this.horaSaida = horaSaida;
    }

    public String getQuilometroInicial()
    {
        return quilometroInicial;
    }

    public void setQuilometroInicial(String quilometroInicial)
    {
        this.quilometroInicial = quilometroInicial;
    }

    public Date getDataChegada()
    {
        return dataChegada;
    }

    public void setDataChegada(Date dataChegada)
    {
        this.dataChegada = dataChegada;
    }

    public Date getHoraChegada()
    {
        return horaChegada;
    }

    public void setHoraChegada(Date horaChegada)
    {
        this.horaChegada = horaChegada;
    }

    public String getQuilometroFinal()
    {
        return quilometroFinal;
    }

    public void setQuilometroFinal(String quilometroFinal)
    {
        this.quilometroFinal = quilometroFinal;
    }

    public String getPercursoPercorrido()
    {
        return percursoPercorrido;
    }

    public void setPercursoPercorrido(String percursoPercorrido)
    {
        this.percursoPercorrido = percursoPercorrido;
    }

    public String getObservacao()
    {
        return observacao;
    }

    public void setObservacao(String observacao)
    {
        this.observacao = observacao;
    }

    public FrtTransporteAgendar getFkTransporteAgendamento()
    {
        return fkTransporteAgendamento;
    }

    public void setFkTransporteAgendamento(FrtTransporteAgendar fkTransporteAgendamento)
    {
        this.fkTransporteAgendamento = fkTransporteAgendamento;
    }

    public FrtTransporteSolicitacao getFkTransporteSolicitacao()
    {
        return fkTransporteSolicitacao;
    }

    public void setFkTransporteSolicitacao(FrtTransporteSolicitacao fkTransporteSolicitacao)
    {
        this.fkTransporteSolicitacao = fkTransporteSolicitacao;
    }

    public FrtTransporteSolicitacaoEstado getFkTransporteAtendimentEstado()
    {
        return fkTransporteAtendimentEstado;
    }

    public void setFkTransporteAtendimentEstado(FrtTransporteSolicitacaoEstado fkTransporteAtendimentEstado)
    {
        this.fkTransporteAtendimentEstado = fkTransporteAtendimentEstado;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (pkTransporteAtendimento != null ? pkTransporteAtendimento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FrtTransporteAtendimento))
        {
            return false;
        }
        FrtTransporteAtendimento other = (FrtTransporteAtendimento) object;
        if ((this.pkTransporteAtendimento == null && other.pkTransporteAtendimento != null) || (this.pkTransporteAtendimento != null && !this.pkTransporteAtendimento.equals(other.pkTransporteAtendimento)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.FrtTransporteAtendimento[ pkTransporteAtendimento=" + pkTransporteAtendimento + " ]";
    }
    
}
