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
@Table(name = "pt_bem_imovel", catalog = "sig_ucan_db", schema = "public")
@NamedQueries(
{
    @NamedQuery(name = "PtBemImovel.findAll", query = "SELECT p FROM PtBemImovel p"),
    @NamedQuery(name = "PtBemImovel.findByPkPtBemImovel", query = "SELECT p FROM PtBemImovel p WHERE p.pkPtBemImovel = :pkPtBemImovel"),
    @NamedQuery(name = "PtBemImovel.findByDescricaoCartorio", query = "SELECT p FROM PtBemImovel p WHERE p.descricaoCartorio = :descricaoCartorio"),
    @NamedQuery(name = "PtBemImovel.findByTaxaMes", query = "SELECT p FROM PtBemImovel p WHERE p.taxaMes = :taxaMes"),
    @NamedQuery(name = "PtBemImovel.findByDataConstrucao", query = "SELECT p FROM PtBemImovel p WHERE p.dataConstrucao = :dataConstrucao"),
    @NamedQuery(name = "PtBemImovel.findByValorActual", query = "SELECT p FROM PtBemImovel p WHERE p.valorActual = :valorActual"),
    @NamedQuery(name = "PtBemImovel.findByImagemPath", query = "SELECT p FROM PtBemImovel p WHERE p.imagemPath = :imagemPath"),
    @NamedQuery(name = "PtBemImovel.findByDocumentoPath", query = "SELECT p FROM PtBemImovel p WHERE p.documentoPath = :documentoPath"),
    @NamedQuery(name = "PtBemImovel.findByCod", query = "SELECT p FROM PtBemImovel p WHERE p.cod = :cod"),
    @NamedQuery(name = "PtBemImovel.findByDetalhes", query = "SELECT p FROM PtBemImovel p WHERE p.detalhes = :detalhes")
})
public class PtBemImovel implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_pt_bem_imovel", nullable = false)
    private Integer pkPtBemImovel;
    @Size(max = 2147483647)
    @Column(name = "descricao_cartorio", length = 2147483647)
    private String descricaoCartorio;
    @Column(name = "taxa_mes")
    private Integer taxaMes;
    @Column(name = "data_construcao")
    @Temporal(TemporalType.DATE)
    private Date dataConstrucao;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valor_actual", precision = 17, scale = 17)
    private Double valorActual;
    @Size(max = 2147483647)
    @Column(name = "imagem_path", length = 2147483647)
    private String imagemPath;
    @Size(max = 2147483647)
    @Column(name = "documento_path", length = 2147483647)
    private String documentoPath;
    @Size(max = 2147483647)
    @Column(length = 2147483647)
    private String cod;
    @Size(max = 2147483647)
    @Column(length = 2147483647)
    private String detalhes;
    @JoinColumn(name = "fk_estrutura_fisica", referencedColumnName = "pk_estrutura_fisica")
    @ManyToOne
    private EstruturaFisica fkEstruturaFisica;
    @JoinColumn(name = "fk_pt_bem_entrada", referencedColumnName = "pk_pt_bem_entrada", nullable = false)
    @ManyToOne(optional = false)
    private PtBemEntrada fkPtBemEntrada;
    @JoinColumn(name = "fk_pt_estado_conservacao", referencedColumnName = "pk_pt_estado_conservacao")
    @ManyToOne
    private PtEstadoConservacao fkPtEstadoConservacao;
    @OneToMany(mappedBy = "fkPtBemImovel")
    private List<PtBemSaida> ptBemSaidaList;
    @OneToMany(mappedBy = "fkPtBemImovel")
    private List<PtBemHistorico> ptBemHistoricoList;

    public PtBemImovel()
    {
    }

    public PtBemImovel(Integer pkPtBemImovel)
    {
        this.pkPtBemImovel = pkPtBemImovel;
    }

    public Integer getPkPtBemImovel()
    {
        return pkPtBemImovel;
    }

    public void setPkPtBemImovel(Integer pkPtBemImovel)
    {
        this.pkPtBemImovel = pkPtBemImovel;
    }

    public String getDescricaoCartorio()
    {
        return descricaoCartorio;
    }

    public void setDescricaoCartorio(String descricaoCartorio)
    {
        this.descricaoCartorio = descricaoCartorio;
    }

    public Integer getTaxaMes()
    {
        return taxaMes;
    }

    public void setTaxaMes(Integer taxaMes)
    {
        this.taxaMes = taxaMes;
    }

    public Date getDataConstrucao()
    {
        return dataConstrucao;
    }

    public void setDataConstrucao(Date dataConstrucao)
    {
        this.dataConstrucao = dataConstrucao;
    }

    public Double getValorActual()
    {
        return valorActual;
    }

    public void setValorActual(Double valorActual)
    {
        this.valorActual = valorActual;
    }

    public String getImagemPath()
    {
        return imagemPath;
    }

    public void setImagemPath(String imagemPath)
    {
        this.imagemPath = imagemPath;
    }

    public String getDocumentoPath()
    {
        return documentoPath;
    }

    public void setDocumentoPath(String documentoPath)
    {
        this.documentoPath = documentoPath;
    }

    public String getCod()
    {
        return cod;
    }

    public void setCod(String cod)
    {
        this.cod = cod;
    }

    public String getDetalhes()
    {
        return detalhes;
    }

    public void setDetalhes(String detalhes)
    {
        this.detalhes = detalhes;
    }

    public EstruturaFisica getFkEstruturaFisica()
    {
        return fkEstruturaFisica;
    }

    public void setFkEstruturaFisica(EstruturaFisica fkEstruturaFisica)
    {
        this.fkEstruturaFisica = fkEstruturaFisica;
    }

    public PtBemEntrada getFkPtBemEntrada()
    {
        return fkPtBemEntrada;
    }

    public void setFkPtBemEntrada(PtBemEntrada fkPtBemEntrada)
    {
        this.fkPtBemEntrada = fkPtBemEntrada;
    }

    public PtEstadoConservacao getFkPtEstadoConservacao()
    {
        return fkPtEstadoConservacao;
    }

    public void setFkPtEstadoConservacao(PtEstadoConservacao fkPtEstadoConservacao)
    {
        this.fkPtEstadoConservacao = fkPtEstadoConservacao;
    }

    public List<PtBemSaida> getPtBemSaidaList()
    {
        return ptBemSaidaList;
    }

    public void setPtBemSaidaList(List<PtBemSaida> ptBemSaidaList)
    {
        this.ptBemSaidaList = ptBemSaidaList;
    }

    public List<PtBemHistorico> getPtBemHistoricoList()
    {
        return ptBemHistoricoList;
    }

    public void setPtBemHistoricoList(List<PtBemHistorico> ptBemHistoricoList)
    {
        this.ptBemHistoricoList = ptBemHistoricoList;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (pkPtBemImovel != null ? pkPtBemImovel.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PtBemImovel))
        {
            return false;
        }
        PtBemImovel other = (PtBemImovel) object;
        if ((this.pkPtBemImovel == null && other.pkPtBemImovel != null) || (this.pkPtBemImovel != null && !this.pkPtBemImovel.equals(other.pkPtBemImovel)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.PtBemImovel[ pkPtBemImovel=" + pkPtBemImovel + " ]";
    }
    
}
