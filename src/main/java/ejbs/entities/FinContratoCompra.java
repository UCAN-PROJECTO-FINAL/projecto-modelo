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
@Table(name = "fin_contrato_compra", catalog = "sig_ucan_db", schema = "public")
@NamedQueries(
{
    @NamedQuery(name = "FinContratoCompra.findAll", query = "SELECT f FROM FinContratoCompra f"),
    @NamedQuery(name = "FinContratoCompra.findByPkPtContratoCompra", query = "SELECT f FROM FinContratoCompra f WHERE f.pkPtContratoCompra = :pkPtContratoCompra"),
    @NamedQuery(name = "FinContratoCompra.findByData", query = "SELECT f FROM FinContratoCompra f WHERE f.data = :data"),
    @NamedQuery(name = "FinContratoCompra.findByPathFile", query = "SELECT f FROM FinContratoCompra f WHERE f.pathFile = :pathFile"),
    @NamedQuery(name = "FinContratoCompra.findByDescicao", query = "SELECT f FROM FinContratoCompra f WHERE f.descicao = :descicao")
})
public class FinContratoCompra implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_pt_contrato_compra", nullable = false)
    private Integer pkPtContratoCompra;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date data;
    @Size(max = 2147483647)
    @Column(name = "path_file", length = 2147483647)
    private String pathFile;
    @Size(max = 2147483647)
    @Column(length = 2147483647)
    private String descicao;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkPtContratoCompra")
    private List<PtBemEntrada> ptBemEntradaList;
    @JoinColumn(name = "fk_grl_entidade", referencedColumnName = "pk_entidade", nullable = false)
    @ManyToOne(optional = false)
    private GrlEntidade fkGrlEntidade;
    @JoinColumn(name = "fk_utilizador", referencedColumnName = "pk_seg_conta")
    @ManyToOne
    private SegConta fkUtilizador;

    public FinContratoCompra()
    {
    }

    public FinContratoCompra(Integer pkPtContratoCompra)
    {
        this.pkPtContratoCompra = pkPtContratoCompra;
    }

    public FinContratoCompra(Integer pkPtContratoCompra, Date data)
    {
        this.pkPtContratoCompra = pkPtContratoCompra;
        this.data = data;
    }

    public Integer getPkPtContratoCompra()
    {
        return pkPtContratoCompra;
    }

    public void setPkPtContratoCompra(Integer pkPtContratoCompra)
    {
        this.pkPtContratoCompra = pkPtContratoCompra;
    }

    public Date getData()
    {
        return data;
    }

    public void setData(Date data)
    {
        this.data = data;
    }

    public String getPathFile()
    {
        return pathFile;
    }

    public void setPathFile(String pathFile)
    {
        this.pathFile = pathFile;
    }

    public String getDescicao()
    {
        return descicao;
    }

    public void setDescicao(String descicao)
    {
        this.descicao = descicao;
    }

    public List<PtBemEntrada> getPtBemEntradaList()
    {
        return ptBemEntradaList;
    }

    public void setPtBemEntradaList(List<PtBemEntrada> ptBemEntradaList)
    {
        this.ptBemEntradaList = ptBemEntradaList;
    }

    public GrlEntidade getFkGrlEntidade()
    {
        return fkGrlEntidade;
    }

    public void setFkGrlEntidade(GrlEntidade fkGrlEntidade)
    {
        this.fkGrlEntidade = fkGrlEntidade;
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
        hash += (pkPtContratoCompra != null ? pkPtContratoCompra.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FinContratoCompra))
        {
            return false;
        }
        FinContratoCompra other = (FinContratoCompra) object;
        if ((this.pkPtContratoCompra == null && other.pkPtContratoCompra != null) || (this.pkPtContratoCompra != null && !this.pkPtContratoCompra.equals(other.pkPtContratoCompra)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.FinContratoCompra[ pkPtContratoCompra=" + pkPtContratoCompra + " ]";
    }
    
}
