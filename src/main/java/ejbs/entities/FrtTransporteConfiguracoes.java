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

/**
 *
 * @author smakambo
 */
@Entity
@Table(name = "frt_transporte_configuracoes", catalog = "sig_ucan_db", schema = "public")
@NamedQueries(
{
    @NamedQuery(name = "FrtTransporteConfiguracoes.findAll", query = "SELECT f FROM FrtTransporteConfiguracoes f"),
    @NamedQuery(name = "FrtTransporteConfiguracoes.findByPkFrtTransporteConfiguracoes", query = "SELECT f FROM FrtTransporteConfiguracoes f WHERE f.pkFrtTransporteConfiguracoes = :pkFrtTransporteConfiguracoes"),
    @NamedQuery(name = "FrtTransporteConfiguracoes.findByFkTipoPavimento", query = "SELECT f FROM FrtTransporteConfiguracoes f WHERE f.fkTipoPavimento = :fkTipoPavimento")
})
public class FrtTransporteConfiguracoes implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_frt_transporte_configuracoes", nullable = false)
    private Integer pkFrtTransporteConfiguracoes;
    @Column(name = "fk_tipo_pavimento")
    private Integer fkTipoPavimento;
    @JoinColumn(name = "fk_tipo_combustivel", referencedColumnName = "pk_tipo_combustivel")
    @ManyToOne
    private FrtTransporteTipoCombustivel fkTipoCombustivel;
    @JoinColumn(name = "fk_estrutura_logica", referencedColumnName = "pk_estrutura_logica")
    @ManyToOne
    private EstruturaLogica fkEstruturaLogica;
    @JoinColumn(name = "fk_modo_pagamento", referencedColumnName = "pk_modo_pagamento")
    @ManyToOne
    private FinModoPagamento fkModoPagamento;
    @JoinColumn(name = "fk_bombas_combustivel", referencedColumnName = "pk_bomba_combustivel")
    @ManyToOne
    private FrtTransporteBombaCombustivel fkBombasCombustivel;
    @JoinColumn(name = "fk_pt_tipo_transporte", referencedColumnName = "pk_tipo_transporte")
    @ManyToOne
    private PtTransporteTipo fkPtTipoTransporte;
    @JoinColumn(name = "fk_especialidade", referencedColumnName = "pk_especialidade")
    @ManyToOne
    private RhEspecialidade fkEspecialidade;
    @JoinColumn(name = "fk_estado_solicitacao", referencedColumnName = "pk_transporte_solicitacao_estado")
    @ManyToOne
    private FrtTransporteSolicitacaoEstado fkEstadoSolicitacao;
    @JoinColumn(name = "fk_sexo", referencedColumnName = "pk_grl_sexo")
    @ManyToOne
    private GrlSexo fkSexo;
    @JoinColumn(name = "fk_tipo_acidente", referencedColumnName = "pk_tipo_acidente")
    @ManyToOne
    private FrtTransporteTipoAcidente fkTipoAcidente;
    @JoinColumn(name = "fk_frt_tipo_agendamento", referencedColumnName = "pk_tipo_agendamento")
    @ManyToOne
    private FrtTransporteTipoAgendamento fkFrtTipoAgendamento;
    @JoinColumn(name = "fk_tipo_funcionario", referencedColumnName = "pk_tipo_funcionario")
    @ManyToOne
    private RhTipoFuncionario fkTipoFuncionario;
    @JoinColumn(name = "fk_seg_conta", referencedColumnName = "pk_seg_conta")
    @ManyToOne
    private SegConta fkSegConta;
    @JoinColumn(name = "fk_tipo_iluminacao", referencedColumnName = "pk_tipo_iluminacao")
    @ManyToOne
    private FrtTransporteTipoIluminacao fkTipoIluminacao;
    @JoinColumn(name = "fk_grl_tipo_solicitacao", referencedColumnName = "pk_tipo_solicitacao")
    @ManyToOne
    private GrlTipoSolicitacao fkGrlTipoSolicitacao;
    @JoinColumn(name = "fk_tipo_infracao", referencedColumnName = "pk_tipo_infracao")
    @ManyToOne
    private FrtTransporteTipoInfraccao fkTipoInfracao;
    @JoinColumn(name = "fk_funcao", referencedColumnName = "pk_funcao")
    @ManyToOne
    private RhFuncao fkFuncao;
    @JoinColumn(name = "fk_nivel_academico", referencedColumnName = "pk_nivel_academico")
    @ManyToOne
    private RhNivelAcademico fkNivelAcademico;
    @JoinColumn(name = "fk_tipo_manutencao", referencedColumnName = "pk_tipo_manutencao")
    @ManyToOne
    private FrtTransporteTipoManutencao fkTipoManutencao;
    @JoinColumn(name = "fk_estado_civil", referencedColumnName = "pk_grl_estado_civil")
    @ManyToOne
    private GrlEstadoCivil fkEstadoCivil;

    public FrtTransporteConfiguracoes()
    {
    }

    public FrtTransporteConfiguracoes(Integer pkFrtTransporteConfiguracoes)
    {
        this.pkFrtTransporteConfiguracoes = pkFrtTransporteConfiguracoes;
    }

    public Integer getPkFrtTransporteConfiguracoes()
    {
        return pkFrtTransporteConfiguracoes;
    }

    public void setPkFrtTransporteConfiguracoes(Integer pkFrtTransporteConfiguracoes)
    {
        this.pkFrtTransporteConfiguracoes = pkFrtTransporteConfiguracoes;
    }

    public Integer getFkTipoPavimento()
    {
        return fkTipoPavimento;
    }

    public void setFkTipoPavimento(Integer fkTipoPavimento)
    {
        this.fkTipoPavimento = fkTipoPavimento;
    }

    public FrtTransporteTipoCombustivel getFkTipoCombustivel()
    {
        return fkTipoCombustivel;
    }

    public void setFkTipoCombustivel(FrtTransporteTipoCombustivel fkTipoCombustivel)
    {
        this.fkTipoCombustivel = fkTipoCombustivel;
    }

    public EstruturaLogica getFkEstruturaLogica()
    {
        return fkEstruturaLogica;
    }

    public void setFkEstruturaLogica(EstruturaLogica fkEstruturaLogica)
    {
        this.fkEstruturaLogica = fkEstruturaLogica;
    }

    public FinModoPagamento getFkModoPagamento()
    {
        return fkModoPagamento;
    }

    public void setFkModoPagamento(FinModoPagamento fkModoPagamento)
    {
        this.fkModoPagamento = fkModoPagamento;
    }

    public FrtTransporteBombaCombustivel getFkBombasCombustivel()
    {
        return fkBombasCombustivel;
    }

    public void setFkBombasCombustivel(FrtTransporteBombaCombustivel fkBombasCombustivel)
    {
        this.fkBombasCombustivel = fkBombasCombustivel;
    }

    public PtTransporteTipo getFkPtTipoTransporte()
    {
        return fkPtTipoTransporte;
    }

    public void setFkPtTipoTransporte(PtTransporteTipo fkPtTipoTransporte)
    {
        this.fkPtTipoTransporte = fkPtTipoTransporte;
    }

    public RhEspecialidade getFkEspecialidade()
    {
        return fkEspecialidade;
    }

    public void setFkEspecialidade(RhEspecialidade fkEspecialidade)
    {
        this.fkEspecialidade = fkEspecialidade;
    }

    public FrtTransporteSolicitacaoEstado getFkEstadoSolicitacao()
    {
        return fkEstadoSolicitacao;
    }

    public void setFkEstadoSolicitacao(FrtTransporteSolicitacaoEstado fkEstadoSolicitacao)
    {
        this.fkEstadoSolicitacao = fkEstadoSolicitacao;
    }

    public GrlSexo getFkSexo()
    {
        return fkSexo;
    }

    public void setFkSexo(GrlSexo fkSexo)
    {
        this.fkSexo = fkSexo;
    }

    public FrtTransporteTipoAcidente getFkTipoAcidente()
    {
        return fkTipoAcidente;
    }

    public void setFkTipoAcidente(FrtTransporteTipoAcidente fkTipoAcidente)
    {
        this.fkTipoAcidente = fkTipoAcidente;
    }

    public FrtTransporteTipoAgendamento getFkFrtTipoAgendamento()
    {
        return fkFrtTipoAgendamento;
    }

    public void setFkFrtTipoAgendamento(FrtTransporteTipoAgendamento fkFrtTipoAgendamento)
    {
        this.fkFrtTipoAgendamento = fkFrtTipoAgendamento;
    }

    public RhTipoFuncionario getFkTipoFuncionario()
    {
        return fkTipoFuncionario;
    }

    public void setFkTipoFuncionario(RhTipoFuncionario fkTipoFuncionario)
    {
        this.fkTipoFuncionario = fkTipoFuncionario;
    }

    public SegConta getFkSegConta()
    {
        return fkSegConta;
    }

    public void setFkSegConta(SegConta fkSegConta)
    {
        this.fkSegConta = fkSegConta;
    }

    public FrtTransporteTipoIluminacao getFkTipoIluminacao()
    {
        return fkTipoIluminacao;
    }

    public void setFkTipoIluminacao(FrtTransporteTipoIluminacao fkTipoIluminacao)
    {
        this.fkTipoIluminacao = fkTipoIluminacao;
    }

    public GrlTipoSolicitacao getFkGrlTipoSolicitacao()
    {
        return fkGrlTipoSolicitacao;
    }

    public void setFkGrlTipoSolicitacao(GrlTipoSolicitacao fkGrlTipoSolicitacao)
    {
        this.fkGrlTipoSolicitacao = fkGrlTipoSolicitacao;
    }

    public FrtTransporteTipoInfraccao getFkTipoInfracao()
    {
        return fkTipoInfracao;
    }

    public void setFkTipoInfracao(FrtTransporteTipoInfraccao fkTipoInfracao)
    {
        this.fkTipoInfracao = fkTipoInfracao;
    }

    public RhFuncao getFkFuncao()
    {
        return fkFuncao;
    }

    public void setFkFuncao(RhFuncao fkFuncao)
    {
        this.fkFuncao = fkFuncao;
    }

    public RhNivelAcademico getFkNivelAcademico()
    {
        return fkNivelAcademico;
    }

    public void setFkNivelAcademico(RhNivelAcademico fkNivelAcademico)
    {
        this.fkNivelAcademico = fkNivelAcademico;
    }

    public FrtTransporteTipoManutencao getFkTipoManutencao()
    {
        return fkTipoManutencao;
    }

    public void setFkTipoManutencao(FrtTransporteTipoManutencao fkTipoManutencao)
    {
        this.fkTipoManutencao = fkTipoManutencao;
    }

    public GrlEstadoCivil getFkEstadoCivil()
    {
        return fkEstadoCivil;
    }

    public void setFkEstadoCivil(GrlEstadoCivil fkEstadoCivil)
    {
        this.fkEstadoCivil = fkEstadoCivil;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (pkFrtTransporteConfiguracoes != null ? pkFrtTransporteConfiguracoes.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FrtTransporteConfiguracoes))
        {
            return false;
        }
        FrtTransporteConfiguracoes other = (FrtTransporteConfiguracoes) object;
        if ((this.pkFrtTransporteConfiguracoes == null && other.pkFrtTransporteConfiguracoes != null) || (this.pkFrtTransporteConfiguracoes != null && !this.pkFrtTransporteConfiguracoes.equals(other.pkFrtTransporteConfiguracoes)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.FrtTransporteConfiguracoes[ pkFrtTransporteConfiguracoes=" + pkFrtTransporteConfiguracoes + " ]";
    }
    
}
