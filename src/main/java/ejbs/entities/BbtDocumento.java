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
import javax.persistence.CascadeType;
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
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author smakambo
 */
@Entity
@Table(name = "bbt_documento", catalog = "sig_ucan_db", schema = "public", uniqueConstraints =
{
    @UniqueConstraint(columnNames =
    {
        "isbn"
    })
})
@NamedQueries(
{
    @NamedQuery(name = "BbtDocumento.findAll", query = "SELECT b FROM BbtDocumento b"),
    @NamedQuery(name = "BbtDocumento.findByPkBbtDocumento", query = "SELECT b FROM BbtDocumento b WHERE b.pkBbtDocumento = :pkBbtDocumento"),
    @NamedQuery(name = "BbtDocumento.findByIsbn", query = "SELECT b FROM BbtDocumento b WHERE b.isbn = :isbn"),
    @NamedQuery(name = "BbtDocumento.findByTitulo", query = "SELECT b FROM BbtDocumento b WHERE b.titulo = :titulo"),
    @NamedQuery(name = "BbtDocumento.findBySubTitulo", query = "SELECT b FROM BbtDocumento b WHERE b.subTitulo = :subTitulo"),
    @NamedQuery(name = "BbtDocumento.findByNumPaginas", query = "SELECT b FROM BbtDocumento b WHERE b.numPaginas = :numPaginas"),
    @NamedQuery(name = "BbtDocumento.findByAltura", query = "SELECT b FROM BbtDocumento b WHERE b.altura = :altura"),
    @NamedQuery(name = "BbtDocumento.findByLargura", query = "SELECT b FROM BbtDocumento b WHERE b.largura = :largura"),
    @NamedQuery(name = "BbtDocumento.findByNotas", query = "SELECT b FROM BbtDocumento b WHERE b.notas = :notas"),
    @NamedQuery(name = "BbtDocumento.findByDepositoLegal", query = "SELECT b FROM BbtDocumento b WHERE b.depositoLegal = :depositoLegal"),
    @NamedQuery(name = "BbtDocumento.findByAno", query = "SELECT b FROM BbtDocumento b WHERE b.ano = :ano"),
    @NamedQuery(name = "BbtDocumento.findByUrlImagem", query = "SELECT b FROM BbtDocumento b WHERE b.urlImagem = :urlImagem"),
    @NamedQuery(name = "BbtDocumento.findByCreatedAt", query = "SELECT b FROM BbtDocumento b WHERE b.createdAt = :createdAt"),
    @NamedQuery(name = "BbtDocumento.findByUpdatedAt", query = "SELECT b FROM BbtDocumento b WHERE b.updatedAt = :updatedAt"),
    @NamedQuery(name = "BbtDocumento.findByIsRemoved", query = "SELECT b FROM BbtDocumento b WHERE b.isRemoved = :isRemoved")
})
public class BbtDocumento implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_bbt_documento", nullable = false)
    private Integer pkBbtDocumento;
    @Size(max = 2147483647)
    @Column(length = 2147483647)
    private String isbn;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(nullable = false, length = 2147483647)
    private String titulo;
    @Size(max = 2147483647)
    @Column(name = "sub_titulo", length = 2147483647)
    private String subTitulo;
    @Column(name = "num_paginas")
    private Integer numPaginas;
    private Integer altura;
    private Integer largura;
    @Size(max = 2147483647)
    @Column(length = 2147483647)
    private String notas;
    @Size(max = 2147483647)
    @Column(name = "deposito_legal", length = 2147483647)
    private String depositoLegal;
    private Integer ano;
    @Size(max = 2147483647)
    @Column(name = "url_imagem", length = 2147483647)
    private String urlImagem;
    @Basic(optional = false)
    @NotNull
    @Column(name = "created_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @Basic(optional = false)
    @NotNull
    @Column(name = "is_removed", nullable = false)
    private boolean isRemoved;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkBbtDocumento")
    private List<BbtExemplarDocumento> bbtExemplarDocumentoList;
    @JoinColumn(name = "fk_bbt_colecao", referencedColumnName = "pk_bbt_colecao")
    @ManyToOne
    private BbtColecao fkBbtColecao;
    @JoinColumn(name = "fk_bbt_edicao", referencedColumnName = "pk_bbt_edicao", nullable = false)
    @ManyToOne(optional = false)
    private BbtEdicao fkBbtEdicao;
    @JoinColumn(name = "fk_bbt_editora", referencedColumnName = "pk_bbt_editora")
    @ManyToOne
    private BbtEditora fkBbtEditora;
    @JoinColumn(name = "fk_bbt_tipo_documento", referencedColumnName = "pk_bbt_tipo_documento", nullable = false)
    @ManyToOne(optional = false)
    private BbtTipoDocumento fkBbtTipoDocumento;
    @JoinColumn(name = "fk_grl_lingua", referencedColumnName = "pk_grl_lingua", nullable = false)
    @ManyToOne(optional = false)
    private GrlLingua fkGrlLingua;
    @JoinColumn(name = "fk_loc_localidade", referencedColumnName = "pk_loc_localidade")
    @ManyToOne
    private LocLocalidade fkLocLocalidade;
    @JoinColumn(name = "fk_seg_conta_created_by", referencedColumnName = "pk_seg_conta", nullable = false)
    @ManyToOne(optional = false)
    private SegConta fkSegContaCreatedBy;
    @JoinColumn(name = "fk_seg_conta_updated_by", referencedColumnName = "pk_seg_conta")
    @ManyToOne
    private SegConta fkSegContaUpdatedBy;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkBbtDocumento")
    private List<BbtDocumentoBbtAutoridade> bbtDocumentoBbtAutoridadeList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkBbtDocumento")
    private List<BbtDocumentoBbtCdu> bbtDocumentoBbtCduList;

    public BbtDocumento()
    {
    }

    public BbtDocumento(Integer pkBbtDocumento)
    {
        this.pkBbtDocumento = pkBbtDocumento;
    }

    public BbtDocumento(Integer pkBbtDocumento, String titulo, Date createdAt, boolean isRemoved)
    {
        this.pkBbtDocumento = pkBbtDocumento;
        this.titulo = titulo;
        this.createdAt = createdAt;
        this.isRemoved = isRemoved;
    }

    public Integer getPkBbtDocumento()
    {
        return pkBbtDocumento;
    }

    public void setPkBbtDocumento(Integer pkBbtDocumento)
    {
        this.pkBbtDocumento = pkBbtDocumento;
    }

    public String getIsbn()
    {
        return isbn;
    }

    public void setIsbn(String isbn)
    {
        this.isbn = isbn;
    }

    public String getTitulo()
    {
        return titulo;
    }

    public void setTitulo(String titulo)
    {
        this.titulo = titulo;
    }

    public String getSubTitulo()
    {
        return subTitulo;
    }

    public void setSubTitulo(String subTitulo)
    {
        this.subTitulo = subTitulo;
    }

    public Integer getNumPaginas()
    {
        return numPaginas;
    }

    public void setNumPaginas(Integer numPaginas)
    {
        this.numPaginas = numPaginas;
    }

    public Integer getAltura()
    {
        return altura;
    }

    public void setAltura(Integer altura)
    {
        this.altura = altura;
    }

    public Integer getLargura()
    {
        return largura;
    }

    public void setLargura(Integer largura)
    {
        this.largura = largura;
    }

    public String getNotas()
    {
        return notas;
    }

    public void setNotas(String notas)
    {
        this.notas = notas;
    }

    public String getDepositoLegal()
    {
        return depositoLegal;
    }

    public void setDepositoLegal(String depositoLegal)
    {
        this.depositoLegal = depositoLegal;
    }

    public Integer getAno()
    {
        return ano;
    }

    public void setAno(Integer ano)
    {
        this.ano = ano;
    }

    public String getUrlImagem()
    {
        return urlImagem;
    }

    public void setUrlImagem(String urlImagem)
    {
        this.urlImagem = urlImagem;
    }

    public Date getCreatedAt()
    {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt)
    {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt()
    {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt)
    {
        this.updatedAt = updatedAt;
    }

    public boolean getIsRemoved()
    {
        return isRemoved;
    }

    public void setIsRemoved(boolean isRemoved)
    {
        this.isRemoved = isRemoved;
    }

    public List<BbtExemplarDocumento> getBbtExemplarDocumentoList()
    {
        return bbtExemplarDocumentoList;
    }

    public void setBbtExemplarDocumentoList(List<BbtExemplarDocumento> bbtExemplarDocumentoList)
    {
        this.bbtExemplarDocumentoList = bbtExemplarDocumentoList;
    }

    public BbtColecao getFkBbtColecao()
    {
        return fkBbtColecao;
    }

    public void setFkBbtColecao(BbtColecao fkBbtColecao)
    {
        this.fkBbtColecao = fkBbtColecao;
    }

    public BbtEdicao getFkBbtEdicao()
    {
        return fkBbtEdicao;
    }

    public void setFkBbtEdicao(BbtEdicao fkBbtEdicao)
    {
        this.fkBbtEdicao = fkBbtEdicao;
    }

    public BbtEditora getFkBbtEditora()
    {
        return fkBbtEditora;
    }

    public void setFkBbtEditora(BbtEditora fkBbtEditora)
    {
        this.fkBbtEditora = fkBbtEditora;
    }

    public BbtTipoDocumento getFkBbtTipoDocumento()
    {
        return fkBbtTipoDocumento;
    }

    public void setFkBbtTipoDocumento(BbtTipoDocumento fkBbtTipoDocumento)
    {
        this.fkBbtTipoDocumento = fkBbtTipoDocumento;
    }

    public GrlLingua getFkGrlLingua()
    {
        return fkGrlLingua;
    }

    public void setFkGrlLingua(GrlLingua fkGrlLingua)
    {
        this.fkGrlLingua = fkGrlLingua;
    }

    public LocLocalidade getFkLocLocalidade()
    {
        return fkLocLocalidade;
    }

    public void setFkLocLocalidade(LocLocalidade fkLocLocalidade)
    {
        this.fkLocLocalidade = fkLocLocalidade;
    }

    public SegConta getFkSegContaCreatedBy()
    {
        return fkSegContaCreatedBy;
    }

    public void setFkSegContaCreatedBy(SegConta fkSegContaCreatedBy)
    {
        this.fkSegContaCreatedBy = fkSegContaCreatedBy;
    }

    public SegConta getFkSegContaUpdatedBy()
    {
        return fkSegContaUpdatedBy;
    }

    public void setFkSegContaUpdatedBy(SegConta fkSegContaUpdatedBy)
    {
        this.fkSegContaUpdatedBy = fkSegContaUpdatedBy;
    }

    public List<BbtDocumentoBbtAutoridade> getBbtDocumentoBbtAutoridadeList()
    {
        return bbtDocumentoBbtAutoridadeList;
    }

    public void setBbtDocumentoBbtAutoridadeList(List<BbtDocumentoBbtAutoridade> bbtDocumentoBbtAutoridadeList)
    {
        this.bbtDocumentoBbtAutoridadeList = bbtDocumentoBbtAutoridadeList;
    }

    public List<BbtDocumentoBbtCdu> getBbtDocumentoBbtCduList()
    {
        return bbtDocumentoBbtCduList;
    }

    public void setBbtDocumentoBbtCduList(List<BbtDocumentoBbtCdu> bbtDocumentoBbtCduList)
    {
        this.bbtDocumentoBbtCduList = bbtDocumentoBbtCduList;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (pkBbtDocumento != null ? pkBbtDocumento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BbtDocumento))
        {
            return false;
        }
        BbtDocumento other = (BbtDocumento) object;
        if ((this.pkBbtDocumento == null && other.pkBbtDocumento != null) || (this.pkBbtDocumento != null && !this.pkBbtDocumento.equals(other.pkBbtDocumento)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.BbtDocumento[ pkBbtDocumento=" + pkBbtDocumento + " ]";
    }
    
}
