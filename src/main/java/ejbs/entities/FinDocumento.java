/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "fin_documento", catalog = "sig_ucan_db", schema = "public")
@NamedQueries(
{
    @NamedQuery(name = "FinDocumento.findAll", query = "SELECT f FROM FinDocumento f"),
    @NamedQuery(name = "FinDocumento.findByPkDocumento", query = "SELECT f FROM FinDocumento f WHERE f.pkDocumento = :pkDocumento"),
    @NamedQuery(name = "FinDocumento.findByNumDoc", query = "SELECT f FROM FinDocumento f WHERE f.numDoc = :numDoc"),
    @NamedQuery(name = "FinDocumento.findByDataDoc", query = "SELECT f FROM FinDocumento f WHERE f.dataDoc = :dataDoc"),
    @NamedQuery(name = "FinDocumento.findByValorDocumento", query = "SELECT f FROM FinDocumento f WHERE f.valorDocumento = :valorDocumento"),
    @NamedQuery(name = "FinDocumento.findByDescricaoDocumento", query = "SELECT f FROM FinDocumento f WHERE f.descricaoDocumento = :descricaoDocumento"),
    @NamedQuery(name = "FinDocumento.findByDataRegisto", query = "SELECT f FROM FinDocumento f WHERE f.dataRegisto = :dataRegisto"),
    @NamedQuery(name = "FinDocumento.findByEstadoDocumento", query = "SELECT f FROM FinDocumento f WHERE f.estadoDocumento = :estadoDocumento"),
    @NamedQuery(name = "FinDocumento.findByValorDisponivel", query = "SELECT f FROM FinDocumento f WHERE f.valorDisponivel = :valorDisponivel"),
    @NamedQuery(name = "FinDocumento.findByPathFile", query = "SELECT f FROM FinDocumento f WHERE f.pathFile = :pathFile")
})
public class FinDocumento implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "pk_documento", nullable = false)
    private Integer pkDocumento;
    @Size(max = 100)
    @Column(name = "num_doc", length = 100)
    private String numDoc;
    @Column(name = "data_doc")
    @Temporal(TemporalType.DATE)
    private Date dataDoc;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valor_documento", precision = 18, scale = 2)
    private BigDecimal valorDocumento;
    @Size(max = 2147483647)
    @Column(name = "descricao_documento", length = 2147483647)
    private String descricaoDocumento;
    @Column(name = "data_registo")
    @Temporal(TemporalType.DATE)
    private Date dataRegisto;
    @Column(name = "estado_documento")
    private Boolean estadoDocumento;
    @Column(name = "valor_disponivel", precision = 17, scale = 17)
    private Double valorDisponivel;
    @Size(max = 2147483647)
    @Column(name = "path_file", length = 2147483647)
    private String pathFile;
    @OneToMany(mappedBy = "fkDocumento")
    private List<FinOperacoesContaCorrente> finOperacoesContaCorrenteList;
    @OneToMany(mappedBy = "fkDocumento")
    private List<FinContasPagar> finContasPagarList;
    @JoinColumn(name = "fk_tipo_doc", referencedColumnName = "pk_tipo_documento")
    @ManyToOne
    private GdTipoDocumento fkTipoDoc;
    @JoinColumn(name = "fk_entidade", referencedColumnName = "pk_entidade")
    @ManyToOne
    private GrlEntidade fkEntidade;
    @JoinColumn(name = "fk_utilizador", referencedColumnName = "pk_seg_conta")
    @ManyToOne
    private SegConta fkUtilizador;
    @OneToMany(mappedBy = "fkDocumento")
    private List<CtLancamento> ctLancamentoList;
    @OneToMany(mappedBy = "fkDocumento")
    private List<FinContaCorrente> finContaCorrenteList;
    @OneToMany(mappedBy = "fkDocumento")
    private List<FinContasReceber> finContasReceberList;

    public FinDocumento()
    {
    }

    public FinDocumento(Integer pkDocumento)
    {
        this.pkDocumento = pkDocumento;
    }

    public Integer getPkDocumento()
    {
        return pkDocumento;
    }

    public void setPkDocumento(Integer pkDocumento)
    {
        this.pkDocumento = pkDocumento;
    }

    public String getNumDoc()
    {
        return numDoc;
    }

    public void setNumDoc(String numDoc)
    {
        this.numDoc = numDoc;
    }

    public Date getDataDoc()
    {
        return dataDoc;
    }

    public void setDataDoc(Date dataDoc)
    {
        this.dataDoc = dataDoc;
    }

    public BigDecimal getValorDocumento()
    {
        return valorDocumento;
    }

    public void setValorDocumento(BigDecimal valorDocumento)
    {
        this.valorDocumento = valorDocumento;
    }

    public String getDescricaoDocumento()
    {
        return descricaoDocumento;
    }

    public void setDescricaoDocumento(String descricaoDocumento)
    {
        this.descricaoDocumento = descricaoDocumento;
    }

    public Date getDataRegisto()
    {
        return dataRegisto;
    }

    public void setDataRegisto(Date dataRegisto)
    {
        this.dataRegisto = dataRegisto;
    }

    public Boolean getEstadoDocumento()
    {
        return estadoDocumento;
    }

    public void setEstadoDocumento(Boolean estadoDocumento)
    {
        this.estadoDocumento = estadoDocumento;
    }

    public Double getValorDisponivel()
    {
        return valorDisponivel;
    }

    public void setValorDisponivel(Double valorDisponivel)
    {
        this.valorDisponivel = valorDisponivel;
    }

    public String getPathFile()
    {
        return pathFile;
    }

    public void setPathFile(String pathFile)
    {
        this.pathFile = pathFile;
    }

    public List<FinOperacoesContaCorrente> getFinOperacoesContaCorrenteList()
    {
        return finOperacoesContaCorrenteList;
    }

    public void setFinOperacoesContaCorrenteList(List<FinOperacoesContaCorrente> finOperacoesContaCorrenteList)
    {
        this.finOperacoesContaCorrenteList = finOperacoesContaCorrenteList;
    }

    public List<FinContasPagar> getFinContasPagarList()
    {
        return finContasPagarList;
    }

    public void setFinContasPagarList(List<FinContasPagar> finContasPagarList)
    {
        this.finContasPagarList = finContasPagarList;
    }

    public GdTipoDocumento getFkTipoDoc()
    {
        return fkTipoDoc;
    }

    public void setFkTipoDoc(GdTipoDocumento fkTipoDoc)
    {
        this.fkTipoDoc = fkTipoDoc;
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

    public List<CtLancamento> getCtLancamentoList()
    {
        return ctLancamentoList;
    }

    public void setCtLancamentoList(List<CtLancamento> ctLancamentoList)
    {
        this.ctLancamentoList = ctLancamentoList;
    }

    public List<FinContaCorrente> getFinContaCorrenteList()
    {
        return finContaCorrenteList;
    }

    public void setFinContaCorrenteList(List<FinContaCorrente> finContaCorrenteList)
    {
        this.finContaCorrenteList = finContaCorrenteList;
    }

    public List<FinContasReceber> getFinContasReceberList()
    {
        return finContasReceberList;
    }

    public void setFinContasReceberList(List<FinContasReceber> finContasReceberList)
    {
        this.finContasReceberList = finContasReceberList;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (pkDocumento != null ? pkDocumento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FinDocumento))
        {
            return false;
        }
        FinDocumento other = (FinDocumento) object;
        if ((this.pkDocumento == null && other.pkDocumento != null) || (this.pkDocumento != null && !this.pkDocumento.equals(other.pkDocumento)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.FinDocumento[ pkDocumento=" + pkDocumento + " ]";
    }
    
}
