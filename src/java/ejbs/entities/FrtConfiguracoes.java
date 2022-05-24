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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author smakambo
 */
@Entity
@Table(name = "frt_configuracoes")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "FrtConfiguracoes.findAll", query = "SELECT f FROM FrtConfiguracoes f"),
    @NamedQuery(name = "FrtConfiguracoes.findByPkFrtConfiguracoes", query = "SELECT f FROM FrtConfiguracoes f WHERE f.pkFrtConfiguracoes = :pkFrtConfiguracoes")
})
public class FrtConfiguracoes implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_frt_configuracoes")
    private Integer pkFrtConfiguracoes;
    @JoinColumn(name = "fk_estrutura_logica", referencedColumnName = "pk_estrutura_logica")
    @ManyToOne
    private EstruturaLogica fkEstruturaLogica;
    @JoinColumn(name = "fk_frt_tipo_agendamento", referencedColumnName = "pk_tipo_agendamento")
    @ManyToOne
    private FrtTransporteTipoAgendamento fkFrtTipoAgendamento;
    @JoinColumn(name = "fk_grl_tipo_solicitacao", referencedColumnName = "pk_tipo_solicitacao")
    @ManyToOne
    private GrlTipoSolicitacao fkGrlTipoSolicitacao;
    @JoinColumn(name = "fk_pt_tipo_transporte", referencedColumnName = "pk_tipo_transporte")
    @ManyToOne
    private PtTransporteTipo fkPtTipoTransporte;

    public FrtConfiguracoes()
    {
    }

    public FrtConfiguracoes(Integer pkFrtConfiguracoes)
    {
        this.pkFrtConfiguracoes = pkFrtConfiguracoes;
    }

    public Integer getPkFrtConfiguracoes()
    {
        return pkFrtConfiguracoes;
    }

    public void setPkFrtConfiguracoes(Integer pkFrtConfiguracoes)
    {
        this.pkFrtConfiguracoes = pkFrtConfiguracoes;
    }

    public EstruturaLogica getFkEstruturaLogica()
    {
        return fkEstruturaLogica;
    }

    public void setFkEstruturaLogica(EstruturaLogica fkEstruturaLogica)
    {
        this.fkEstruturaLogica = fkEstruturaLogica;
    }

    public FrtTransporteTipoAgendamento getFkFrtTipoAgendamento()
    {
        return fkFrtTipoAgendamento;
    }

    public void setFkFrtTipoAgendamento(FrtTransporteTipoAgendamento fkFrtTipoAgendamento)
    {
        this.fkFrtTipoAgendamento = fkFrtTipoAgendamento;
    }

    public GrlTipoSolicitacao getFkGrlTipoSolicitacao()
    {
        return fkGrlTipoSolicitacao;
    }

    public void setFkGrlTipoSolicitacao(GrlTipoSolicitacao fkGrlTipoSolicitacao)
    {
        this.fkGrlTipoSolicitacao = fkGrlTipoSolicitacao;
    }

    public PtTransporteTipo getFkPtTipoTransporte()
    {
        return fkPtTipoTransporte;
    }

    public void setFkPtTipoTransporte(PtTransporteTipo fkPtTipoTransporte)
    {
        this.fkPtTipoTransporte = fkPtTipoTransporte;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (pkFrtConfiguracoes != null ? pkFrtConfiguracoes.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FrtConfiguracoes))
        {
            return false;
        }
        FrtConfiguracoes other = (FrtConfiguracoes) object;
        if ((this.pkFrtConfiguracoes == null && other.pkFrtConfiguracoes != null) || (this.pkFrtConfiguracoes != null && !this.pkFrtConfiguracoes.equals(other.pkFrtConfiguracoes)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.FrtConfiguracoes[ pkFrtConfiguracoes=" + pkFrtConfiguracoes + " ]";
    }
    
}
