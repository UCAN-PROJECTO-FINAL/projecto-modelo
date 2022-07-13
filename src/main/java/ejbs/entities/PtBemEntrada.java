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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author smakambo
 */
@Entity
@Table(name = "pt_bem_entrada", catalog = "sig_ucan_db", schema = "public")
@NamedQueries(
{
    @NamedQuery(name = "PtBemEntrada.findAll", query = "SELECT p FROM PtBemEntrada p"),
    @NamedQuery(name = "PtBemEntrada.findByPkPtBemEntrada", query = "SELECT p FROM PtBemEntrada p WHERE p.pkPtBemEntrada = :pkPtBemEntrada"),
    @NamedQuery(name = "PtBemEntrada.findByQuantidade", query = "SELECT p FROM PtBemEntrada p WHERE p.quantidade = :quantidade"),
    @NamedQuery(name = "PtBemEntrada.findByCustoUnidade", query = "SELECT p FROM PtBemEntrada p WHERE p.custoUnidade = :custoUnidade"),
    @NamedQuery(name = "PtBemEntrada.findByDataRegisto", query = "SELECT p FROM PtBemEntrada p WHERE p.dataRegisto = :dataRegisto"),
    @NamedQuery(name = "PtBemEntrada.findByDocumentacaoPath", query = "SELECT p FROM PtBemEntrada p WHERE p.documentacaoPath = :documentacaoPath"),
    @NamedQuery(name = "PtBemEntrada.findByImagemPath", query = "SELECT p FROM PtBemEntrada p WHERE p.imagemPath = :imagemPath")
})
public class PtBemEntrada implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_pt_bem_entrada", nullable = false)
    private Integer pkPtBemEntrada;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private int quantidade;
    @Basic(optional = false)
    @NotNull
    @Column(name = "custo_unidade", nullable = false)
    private double custoUnidade;
    @Basic(optional = false)
    @NotNull
    @Column(name = "data_registo", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataRegisto;
    @Size(max = 2147483647)
    @Column(name = "documentacao_path", length = 2147483647)
    private String documentacaoPath;
    @Size(max = 2147483647)
    @Column(name = "imagem_path", length = 2147483647)
    private String imagemPath;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkPtBemEntrada")
    private List<PtBemImovel> ptBemImovelList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkPtBemEntrada")
    private List<PtBemItangivel> ptBemItangivelList;
    @JoinColumn(name = "fk_pt_contrato_compra", referencedColumnName = "pk_pt_contrato_compra", nullable = false)
    @ManyToOne(optional = false)
    private FinContratoCompra fkPtContratoCompra;
    @JoinColumn(name = "fk_pt_categoria", referencedColumnName = "pk_pt_categoria", nullable = false)
    @ManyToOne(optional = false)
    private PtCategoria fkPtCategoria;
    @JoinColumn(name = "fk_pt_forma_aquisicao", referencedColumnName = "pk_pt_forma_aquisicao", nullable = false)
    @ManyToOne(optional = false)
    private PtFormaAquisicao fkPtFormaAquisicao;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkPtBemEntrada")
    private List<PtBemMovel> ptBemMovelList;

    public PtBemEntrada()
    {
    }

    public PtBemEntrada(Integer pkPtBemEntrada)
    {
        this.pkPtBemEntrada = pkPtBemEntrada;
    }

    public PtBemEntrada(Integer pkPtBemEntrada, int quantidade, double custoUnidade, Date dataRegisto)
    {
        this.pkPtBemEntrada = pkPtBemEntrada;
        this.quantidade = quantidade;
        this.custoUnidade = custoUnidade;
        this.dataRegisto = dataRegisto;
    }

    public Integer getPkPtBemEntrada()
    {
        return pkPtBemEntrada;
    }

    public void setPkPtBemEntrada(Integer pkPtBemEntrada)
    {
        this.pkPtBemEntrada = pkPtBemEntrada;
    }

    public int getQuantidade()
    {
        return quantidade;
    }

    public void setQuantidade(int quantidade)
    {
        this.quantidade = quantidade;
    }

    public double getCustoUnidade()
    {
        return custoUnidade;
    }

    public void setCustoUnidade(double custoUnidade)
    {
        this.custoUnidade = custoUnidade;
    }

    public Date getDataRegisto()
    {
        return dataRegisto;
    }

    public void setDataRegisto(Date dataRegisto)
    {
        this.dataRegisto = dataRegisto;
    }

    public String getDocumentacaoPath()
    {
        return documentacaoPath;
    }

    public void setDocumentacaoPath(String documentacaoPath)
    {
        this.documentacaoPath = documentacaoPath;
    }

    public String getImagemPath()
    {
        return imagemPath;
    }

    public void setImagemPath(String imagemPath)
    {
        this.imagemPath = imagemPath;
    }

    public List<PtBemImovel> getPtBemImovelList()
    {
        return ptBemImovelList;
    }

    public void setPtBemImovelList(List<PtBemImovel> ptBemImovelList)
    {
        this.ptBemImovelList = ptBemImovelList;
    }

    public List<PtBemItangivel> getPtBemItangivelList()
    {
        return ptBemItangivelList;
    }

    public void setPtBemItangivelList(List<PtBemItangivel> ptBemItangivelList)
    {
        this.ptBemItangivelList = ptBemItangivelList;
    }

    public FinContratoCompra getFkPtContratoCompra()
    {
        return fkPtContratoCompra;
    }

    public void setFkPtContratoCompra(FinContratoCompra fkPtContratoCompra)
    {
        this.fkPtContratoCompra = fkPtContratoCompra;
    }

    public PtCategoria getFkPtCategoria()
    {
        return fkPtCategoria;
    }

    public void setFkPtCategoria(PtCategoria fkPtCategoria)
    {
        this.fkPtCategoria = fkPtCategoria;
    }

    public PtFormaAquisicao getFkPtFormaAquisicao()
    {
        return fkPtFormaAquisicao;
    }

    public void setFkPtFormaAquisicao(PtFormaAquisicao fkPtFormaAquisicao)
    {
        this.fkPtFormaAquisicao = fkPtFormaAquisicao;
    }

    public List<PtBemMovel> getPtBemMovelList()
    {
        return ptBemMovelList;
    }

    public void setPtBemMovelList(List<PtBemMovel> ptBemMovelList)
    {
        this.ptBemMovelList = ptBemMovelList;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (pkPtBemEntrada != null ? pkPtBemEntrada.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PtBemEntrada))
        {
            return false;
        }
        PtBemEntrada other = (PtBemEntrada) object;
        if ((this.pkPtBemEntrada == null && other.pkPtBemEntrada != null) || (this.pkPtBemEntrada != null && !this.pkPtBemEntrada.equals(other.pkPtBemEntrada)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.PtBemEntrada[ pkPtBemEntrada=" + pkPtBemEntrada + " ]";
    }
    
}
