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

/**
 *
 * @author smakambo
 */
@Entity
@Table(name = "fin_operacoes_conta_corrente", catalog = "sig_ucan_db", schema = "public")
@NamedQueries(
{
    @NamedQuery(name = "FinOperacoesContaCorrente.findAll", query = "SELECT f FROM FinOperacoesContaCorrente f"),
    @NamedQuery(name = "FinOperacoesContaCorrente.findByPkOperacoesContaConrrente", query = "SELECT f FROM FinOperacoesContaCorrente f WHERE f.pkOperacoesContaConrrente = :pkOperacoesContaConrrente"),
    @NamedQuery(name = "FinOperacoesContaCorrente.findByDataRegisto", query = "SELECT f FROM FinOperacoesContaCorrente f WHERE f.dataRegisto = :dataRegisto"),
    @NamedQuery(name = "FinOperacoesContaCorrente.findByMontante", query = "SELECT f FROM FinOperacoesContaCorrente f WHERE f.montante = :montante"),
    @NamedQuery(name = "FinOperacoesContaCorrente.findByEstado", query = "SELECT f FROM FinOperacoesContaCorrente f WHERE f.estado = :estado")
})
public class FinOperacoesContaCorrente implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_operacoes_conta_conrrente", nullable = false)
    private Integer pkOperacoesContaConrrente;
    @Column(name = "data_registo")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataRegisto;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(precision = 17, scale = 17)
    private Double montante;
    private Boolean estado;
    @JoinColumn(name = "fk_conta_corrente", referencedColumnName = "pk_conta_corrente")
    @ManyToOne
    private FinContaCorrente fkContaCorrente;
    @JoinColumn(name = "fk_documento", referencedColumnName = "pk_documento")
    @ManyToOne
    private FinDocumento fkDocumento;
    @JoinColumn(name = "fk_modo_pagamento", referencedColumnName = "pk_modo_pagamento")
    @ManyToOne
    private FinModoPagamento fkModoPagamento;
    @JoinColumn(name = "fk_tipo_pagamento", referencedColumnName = "pk_tipo_pagamento")
    @ManyToOne
    private FinTipoPagamento fkTipoPagamento;
    @JoinColumn(name = "fk_utilizador", referencedColumnName = "pk_seg_conta")
    @ManyToOne
    private SegConta fkUtilizador;

    public FinOperacoesContaCorrente()
    {
    }

    public FinOperacoesContaCorrente(Integer pkOperacoesContaConrrente)
    {
        this.pkOperacoesContaConrrente = pkOperacoesContaConrrente;
    }

    public Integer getPkOperacoesContaConrrente()
    {
        return pkOperacoesContaConrrente;
    }

    public void setPkOperacoesContaConrrente(Integer pkOperacoesContaConrrente)
    {
        this.pkOperacoesContaConrrente = pkOperacoesContaConrrente;
    }

    public Date getDataRegisto()
    {
        return dataRegisto;
    }

    public void setDataRegisto(Date dataRegisto)
    {
        this.dataRegisto = dataRegisto;
    }

    public Double getMontante()
    {
        return montante;
    }

    public void setMontante(Double montante)
    {
        this.montante = montante;
    }

    public Boolean getEstado()
    {
        return estado;
    }

    public void setEstado(Boolean estado)
    {
        this.estado = estado;
    }

    public FinContaCorrente getFkContaCorrente()
    {
        return fkContaCorrente;
    }

    public void setFkContaCorrente(FinContaCorrente fkContaCorrente)
    {
        this.fkContaCorrente = fkContaCorrente;
    }

    public FinDocumento getFkDocumento()
    {
        return fkDocumento;
    }

    public void setFkDocumento(FinDocumento fkDocumento)
    {
        this.fkDocumento = fkDocumento;
    }

    public FinModoPagamento getFkModoPagamento()
    {
        return fkModoPagamento;
    }

    public void setFkModoPagamento(FinModoPagamento fkModoPagamento)
    {
        this.fkModoPagamento = fkModoPagamento;
    }

    public FinTipoPagamento getFkTipoPagamento()
    {
        return fkTipoPagamento;
    }

    public void setFkTipoPagamento(FinTipoPagamento fkTipoPagamento)
    {
        this.fkTipoPagamento = fkTipoPagamento;
    }

    public SegConta getFkUtilizador()
    {
        return fkUtilizador;
    }

    public void setFkUtilizador(SegConta fkUtilizador)
    {
        this.fkUtilizador = fkUtilizador;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (pkOperacoesContaConrrente != null ? pkOperacoesContaConrrente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FinOperacoesContaCorrente))
        {
            return false;
        }
        FinOperacoesContaCorrente other = (FinOperacoesContaCorrente) object;
        if ((this.pkOperacoesContaConrrente == null && other.pkOperacoesContaConrrente != null) || (this.pkOperacoesContaConrrente != null && !this.pkOperacoesContaConrrente.equals(other.pkOperacoesContaConrrente)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.FinOperacoesContaCorrente[ pkOperacoesContaConrrente=" + pkOperacoesContaConrrente + " ]";
    }
    
}
