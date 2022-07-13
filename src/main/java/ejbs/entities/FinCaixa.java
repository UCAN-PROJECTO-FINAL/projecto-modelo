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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author smakambo
 */
@Entity
@Table(name = "fin_caixa", catalog = "sig_ucan_db", schema = "public")
@NamedQueries(
{
    @NamedQuery(name = "FinCaixa.findAll", query = "SELECT f FROM FinCaixa f"),
    @NamedQuery(name = "FinCaixa.findByPkCaixa", query = "SELECT f FROM FinCaixa f WHERE f.pkCaixa = :pkCaixa"),
    @NamedQuery(name = "FinCaixa.findByNome", query = "SELECT f FROM FinCaixa f WHERE f.nome = :nome"),
    @NamedQuery(name = "FinCaixa.findByEstadoCaixa", query = "SELECT f FROM FinCaixa f WHERE f.estadoCaixa = :estadoCaixa"),
    @NamedQuery(name = "FinCaixa.findByDataRegistroCaixa", query = "SELECT f FROM FinCaixa f WHERE f.dataRegistroCaixa = :dataRegistroCaixa")
})
public class FinCaixa implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_caixa", nullable = false)
    private Integer pkCaixa;
    @Size(max = 50)
    @Column(length = 50)
    private String nome;
    @Column(name = "estado_caixa")
    private Boolean estadoCaixa;
    @Basic(optional = false)
    @NotNull
    @Column(name = "data_registro_caixa", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataRegistroCaixa;
    @JoinColumn(name = "fk_utilizador", referencedColumnName = "pk_seg_conta")
    @ManyToOne
    private SegConta fkUtilizador;
    @OneToMany(mappedBy = "fkCaixa")
    private List<FinOperacoesCaixa> finOperacoesCaixaList;

    public FinCaixa()
    {
    }

    public FinCaixa(Integer pkCaixa)
    {
        this.pkCaixa = pkCaixa;
    }

    public FinCaixa(Integer pkCaixa, Date dataRegistroCaixa)
    {
        this.pkCaixa = pkCaixa;
        this.dataRegistroCaixa = dataRegistroCaixa;
    }

    public Integer getPkCaixa()
    {
        return pkCaixa;
    }

    public void setPkCaixa(Integer pkCaixa)
    {
        this.pkCaixa = pkCaixa;
    }

    public String getNome()
    {
        return nome;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    public Boolean getEstadoCaixa()
    {
        return estadoCaixa;
    }

    public void setEstadoCaixa(Boolean estadoCaixa)
    {
        this.estadoCaixa = estadoCaixa;
    }

    public Date getDataRegistroCaixa()
    {
        return dataRegistroCaixa;
    }

    public void setDataRegistroCaixa(Date dataRegistroCaixa)
    {
        this.dataRegistroCaixa = dataRegistroCaixa;
    }

    public SegConta getFkUtilizador()
    {
        return fkUtilizador;
    }

    public void setFkUtilizador(SegConta fkUtilizador)
    {
        this.fkUtilizador = fkUtilizador;
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
        hash += (pkCaixa != null ? pkCaixa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FinCaixa))
        {
            return false;
        }
        FinCaixa other = (FinCaixa) object;
        if ((this.pkCaixa == null && other.pkCaixa != null) || (this.pkCaixa != null && !this.pkCaixa.equals(other.pkCaixa)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.FinCaixa[ pkCaixa=" + pkCaixa + " ]";
    }
    
}
