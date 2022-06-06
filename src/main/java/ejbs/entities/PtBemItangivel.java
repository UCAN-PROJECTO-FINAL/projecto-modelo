/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.entities;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author smakambo
 */
@Entity
@Table(name = "pt_bem_itangivel", catalog = "sig_ucan_db", schema = "public")
@NamedQueries(
{
    @NamedQuery(name = "PtBemItangivel.findAll", query = "SELECT p FROM PtBemItangivel p"),
    @NamedQuery(name = "PtBemItangivel.findByPkPtBemItangivel", query = "SELECT p FROM PtBemItangivel p WHERE p.pkPtBemItangivel = :pkPtBemItangivel"),
    @NamedQuery(name = "PtBemItangivel.findByDescricaoDetalhada", query = "SELECT p FROM PtBemItangivel p WHERE p.descricaoDetalhada = :descricaoDetalhada"),
    @NamedQuery(name = "PtBemItangivel.findByCod", query = "SELECT p FROM PtBemItangivel p WHERE p.cod = :cod"),
    @NamedQuery(name = "PtBemItangivel.findByDocumentacaoPath", query = "SELECT p FROM PtBemItangivel p WHERE p.documentacaoPath = :documentacaoPath"),
    @NamedQuery(name = "PtBemItangivel.findByNome", query = "SELECT p FROM PtBemItangivel p WHERE p.nome = :nome")
})
public class PtBemItangivel implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "pk_pt_bem_itangivel", nullable = false)
    private Integer pkPtBemItangivel;
    @Size(max = 2147483647)
    @Column(name = "descricao_detalhada", length = 2147483647)
    private String descricaoDetalhada;
    @Size(max = 2147483647)
    @Column(length = 2147483647)
    private String cod;
    @Size(max = 2147483647)
    @Column(name = "documentacao_path", length = 2147483647)
    private String documentacaoPath;
    @Size(max = 2147483647)
    @Column(length = 2147483647)
    private String nome;
    @JoinColumn(name = "fk_pt_bem_entrada", referencedColumnName = "pk_pt_bem_entrada", nullable = false)
    @ManyToOne(optional = false)
    private PtBemEntrada fkPtBemEntrada;
    @OneToMany(mappedBy = "fkPtBemItangivel")
    private List<PtBemSaida> ptBemSaidaList;
    @OneToMany(mappedBy = "fkPtBemItangivel")
    private List<PtBemHistorico> ptBemHistoricoList;

    public PtBemItangivel()
    {
    }

    public PtBemItangivel(Integer pkPtBemItangivel)
    {
        this.pkPtBemItangivel = pkPtBemItangivel;
    }

    public Integer getPkPtBemItangivel()
    {
        return pkPtBemItangivel;
    }

    public void setPkPtBemItangivel(Integer pkPtBemItangivel)
    {
        this.pkPtBemItangivel = pkPtBemItangivel;
    }

    public String getDescricaoDetalhada()
    {
        return descricaoDetalhada;
    }

    public void setDescricaoDetalhada(String descricaoDetalhada)
    {
        this.descricaoDetalhada = descricaoDetalhada;
    }

    public String getCod()
    {
        return cod;
    }

    public void setCod(String cod)
    {
        this.cod = cod;
    }

    public String getDocumentacaoPath()
    {
        return documentacaoPath;
    }

    public void setDocumentacaoPath(String documentacaoPath)
    {
        this.documentacaoPath = documentacaoPath;
    }

    public String getNome()
    {
        return nome;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    public PtBemEntrada getFkPtBemEntrada()
    {
        return fkPtBemEntrada;
    }

    public void setFkPtBemEntrada(PtBemEntrada fkPtBemEntrada)
    {
        this.fkPtBemEntrada = fkPtBemEntrada;
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
        hash += (pkPtBemItangivel != null ? pkPtBemItangivel.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PtBemItangivel))
        {
            return false;
        }
        PtBemItangivel other = (PtBemItangivel) object;
        if ((this.pkPtBemItangivel == null && other.pkPtBemItangivel != null) || (this.pkPtBemItangivel != null && !this.pkPtBemItangivel.equals(other.pkPtBemItangivel)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.PtBemItangivel[ pkPtBemItangivel=" + pkPtBemItangivel + " ]";
    }
    
}
