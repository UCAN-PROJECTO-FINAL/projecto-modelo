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
import javax.validation.constraints.Size;

/**
 *
 * @author smakambo
 */
@Entity
@Table(name = "fin_conta_corrente", catalog = "sig_ucan_db", schema = "public")
@NamedQueries(
{
    @NamedQuery(name = "FinContaCorrente.findAll", query = "SELECT f FROM FinContaCorrente f"),
    @NamedQuery(name = "FinContaCorrente.findByPkContaCorrente", query = "SELECT f FROM FinContaCorrente f WHERE f.pkContaCorrente = :pkContaCorrente"),
    @NamedQuery(name = "FinContaCorrente.findByValor", query = "SELECT f FROM FinContaCorrente f WHERE f.valor = :valor"),
    @NamedQuery(name = "FinContaCorrente.findByPendente", query = "SELECT f FROM FinContaCorrente f WHERE f.pendente = :pendente"),
    @NamedQuery(name = "FinContaCorrente.findByDataDoc", query = "SELECT f FROM FinContaCorrente f WHERE f.dataDoc = :dataDoc"),
    @NamedQuery(name = "FinContaCorrente.findByDataVencimento", query = "SELECT f FROM FinContaCorrente f WHERE f.dataVencimento = :dataVencimento"),
    @NamedQuery(name = "FinContaCorrente.findByObservacoes", query = "SELECT f FROM FinContaCorrente f WHERE f.observacoes = :observacoes"),
    @NamedQuery(name = "FinContaCorrente.findByEstado", query = "SELECT f FROM FinContaCorrente f WHERE f.estado = :estado"),
    @NamedQuery(name = "FinContaCorrente.findByRecebido", query = "SELECT f FROM FinContaCorrente f WHERE f.recebido = :recebido"),
    @NamedQuery(name = "FinContaCorrente.findByDataRegistro", query = "SELECT f FROM FinContaCorrente f WHERE f.dataRegistro = :dataRegistro"),
    @NamedQuery(name = "FinContaCorrente.findByDescricao", query = "SELECT f FROM FinContaCorrente f WHERE f.descricao = :descricao"),
    @NamedQuery(name = "FinContaCorrente.findByNumeroDoc", query = "SELECT f FROM FinContaCorrente f WHERE f.numeroDoc = :numeroDoc")
})
public class FinContaCorrente implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_conta_corrente", nullable = false)
    private Integer pkContaCorrente;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(precision = 17, scale = 17)
    private Double valor;
    @Column(precision = 17, scale = 17)
    private Double pendente;
    @Column(name = "data_doc")
    @Temporal(TemporalType.DATE)
    private Date dataDoc;
    @Column(name = "data_vencimento")
    @Temporal(TemporalType.DATE)
    private Date dataVencimento;
    @Size(max = 2147483647)
    @Column(length = 2147483647)
    private String observacoes;
    private Boolean estado;
    private Boolean recebido;
    @Column(name = "data_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataRegistro;
    @Size(max = 2147483647)
    @Column(length = 2147483647)
    private String descricao;
    @Size(max = 50)
    @Column(name = "numero_doc", length = 50)
    private String numeroDoc;
    @OneToMany(mappedBy = "fkContaCorrente")
    private List<FinOperacoesContaCorrente> finOperacoesContaCorrenteList;
    @JoinColumn(name = "fk_documento", referencedColumnName = "pk_documento")
    @ManyToOne
    private FinDocumento fkDocumento;
    @JoinColumn(name = "fk_modo_pagamento", referencedColumnName = "pk_modo_pagamento")
    @ManyToOne
    private FinModoPagamento fkModoPagamento;
    @JoinColumn(name = "fk_entidade", referencedColumnName = "pk_entidade")
    @ManyToOne
    private GrlEntidade fkEntidade;
    @JoinColumn(name = "fk_utilizador", referencedColumnName = "pk_seg_conta")
    @ManyToOne
    private SegConta fkUtilizador;

    public FinContaCorrente()
    {
    }

    public FinContaCorrente(Integer pkContaCorrente)
    {
        this.pkContaCorrente = pkContaCorrente;
    }

    public Integer getPkContaCorrente()
    {
        return pkContaCorrente;
    }

    public void setPkContaCorrente(Integer pkContaCorrente)
    {
        this.pkContaCorrente = pkContaCorrente;
    }

    public Double getValor()
    {
        return valor;
    }

    public void setValor(Double valor)
    {
        this.valor = valor;
    }

    public Double getPendente()
    {
        return pendente;
    }

    public void setPendente(Double pendente)
    {
        this.pendente = pendente;
    }

    public Date getDataDoc()
    {
        return dataDoc;
    }

    public void setDataDoc(Date dataDoc)
    {
        this.dataDoc = dataDoc;
    }

    public Date getDataVencimento()
    {
        return dataVencimento;
    }

    public void setDataVencimento(Date dataVencimento)
    {
        this.dataVencimento = dataVencimento;
    }

    public String getObservacoes()
    {
        return observacoes;
    }

    public void setObservacoes(String observacoes)
    {
        this.observacoes = observacoes;
    }

    public Boolean getEstado()
    {
        return estado;
    }

    public void setEstado(Boolean estado)
    {
        this.estado = estado;
    }

    public Boolean getRecebido()
    {
        return recebido;
    }

    public void setRecebido(Boolean recebido)
    {
        this.recebido = recebido;
    }

    public Date getDataRegistro()
    {
        return dataRegistro;
    }

    public void setDataRegistro(Date dataRegistro)
    {
        this.dataRegistro = dataRegistro;
    }

    public String getDescricao()
    {
        return descricao;
    }

    public void setDescricao(String descricao)
    {
        this.descricao = descricao;
    }

    public String getNumeroDoc()
    {
        return numeroDoc;
    }

    public void setNumeroDoc(String numeroDoc)
    {
        this.numeroDoc = numeroDoc;
    }

    public List<FinOperacoesContaCorrente> getFinOperacoesContaCorrenteList()
    {
        return finOperacoesContaCorrenteList;
    }

    public void setFinOperacoesContaCorrenteList(List<FinOperacoesContaCorrente> finOperacoesContaCorrenteList)
    {
        this.finOperacoesContaCorrenteList = finOperacoesContaCorrenteList;
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

    public GrlEntidade getFkEntidade()
    {
        return fkEntidade;
    }

    public void setFkEntidade(GrlEntidade fkEntidade)
    {
        this.fkEntidade = fkEntidade;
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
        hash += (pkContaCorrente != null ? pkContaCorrente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FinContaCorrente))
        {
            return false;
        }
        FinContaCorrente other = (FinContaCorrente) object;
        if ((this.pkContaCorrente == null && other.pkContaCorrente != null) || (this.pkContaCorrente != null && !this.pkContaCorrente.equals(other.pkContaCorrente)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.FinContaCorrente[ pkContaCorrente=" + pkContaCorrente + " ]";
    }
    
}
