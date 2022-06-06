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
@Table(name = "fin_moeda", catalog = "sig_ucan_db", schema = "public")
@NamedQueries(
{
    @NamedQuery(name = "FinMoeda.findAll", query = "SELECT f FROM FinMoeda f"),
    @NamedQuery(name = "FinMoeda.findByPkMoeda", query = "SELECT f FROM FinMoeda f WHERE f.pkMoeda = :pkMoeda"),
    @NamedQuery(name = "FinMoeda.findByAbreviatura", query = "SELECT f FROM FinMoeda f WHERE f.abreviatura = :abreviatura"),
    @NamedQuery(name = "FinMoeda.findByDescricao", query = "SELECT f FROM FinMoeda f WHERE f.descricao = :descricao"),
    @NamedQuery(name = "FinMoeda.findByData", query = "SELECT f FROM FinMoeda f WHERE f.data = :data")
})
public class FinMoeda implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "pk_moeda", nullable = false)
    private Integer pkMoeda;
    @Size(max = 50)
    @Column(length = 50)
    private String abreviatura;
    @Size(max = 2147483647)
    @Column(length = 2147483647)
    private String descricao;
    @Temporal(TemporalType.DATE)
    private Date data;
    @OneToMany(mappedBy = "fkMoeda")
    private List<FinModoPagamento> finModoPagamentoList;
    @JoinColumn(name = "fk_utilizador", referencedColumnName = "pk_seg_conta")
    @ManyToOne
    private SegConta fkUtilizador;
    @OneToMany(mappedBy = "fkMoeda")
    private List<FinConta> finContaList;
    @OneToMany(mappedBy = "fkMoeda")
    private List<FinCambio> finCambioList;
    @OneToMany(mappedBy = "fkMoeda")
    private List<FinCofre> finCofreList;
    @OneToMany(mappedBy = "fkMoeda")
    private List<FinOperacoesCaixa> finOperacoesCaixaList;

    public FinMoeda()
    {
    }

    public FinMoeda(Integer pkMoeda)
    {
        this.pkMoeda = pkMoeda;
    }

    public Integer getPkMoeda()
    {
        return pkMoeda;
    }

    public void setPkMoeda(Integer pkMoeda)
    {
        this.pkMoeda = pkMoeda;
    }

    public String getAbreviatura()
    {
        return abreviatura;
    }

    public void setAbreviatura(String abreviatura)
    {
        this.abreviatura = abreviatura;
    }

    public String getDescricao()
    {
        return descricao;
    }

    public void setDescricao(String descricao)
    {
        this.descricao = descricao;
    }

    public Date getData()
    {
        return data;
    }

    public void setData(Date data)
    {
        this.data = data;
    }

    public List<FinModoPagamento> getFinModoPagamentoList()
    {
        return finModoPagamentoList;
    }

    public void setFinModoPagamentoList(List<FinModoPagamento> finModoPagamentoList)
    {
        this.finModoPagamentoList = finModoPagamentoList;
    }

    public SegConta getFkUtilizador()
    {
        return fkUtilizador;
    }

    public void setFkUtilizador(SegConta fkUtilizador)
    {
        this.fkUtilizador = fkUtilizador;
    }

    public List<FinConta> getFinContaList()
    {
        return finContaList;
    }

    public void setFinContaList(List<FinConta> finContaList)
    {
        this.finContaList = finContaList;
    }

    public List<FinCambio> getFinCambioList()
    {
        return finCambioList;
    }

    public void setFinCambioList(List<FinCambio> finCambioList)
    {
        this.finCambioList = finCambioList;
    }

    public List<FinCofre> getFinCofreList()
    {
        return finCofreList;
    }

    public void setFinCofreList(List<FinCofre> finCofreList)
    {
        this.finCofreList = finCofreList;
    }

    public List<FinOperacoesCaixa> getFinOperacoesCaixaList()
    {
        return finOperacoesCaixaList;
    }

    public void setFinOperacoesCaixaList(List<FinOperacoesCaixa> finOperacoesCaixaList)
    {
        this.finOperacoesCaixaList = finOperacoesCaixaList;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (pkMoeda != null ? pkMoeda.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FinMoeda))
        {
            return false;
        }
        FinMoeda other = (FinMoeda) object;
        if ((this.pkMoeda == null && other.pkMoeda != null) || (this.pkMoeda != null && !this.pkMoeda.equals(other.pkMoeda)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.FinMoeda[ pkMoeda=" + pkMoeda + " ]";
    }
    
}
