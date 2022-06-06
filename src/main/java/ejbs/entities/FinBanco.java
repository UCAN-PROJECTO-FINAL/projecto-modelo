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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(name = "fin_banco", catalog = "sig_ucan_db", schema = "public")
@NamedQueries(
{
    @NamedQuery(name = "FinBanco.findAll", query = "SELECT f FROM FinBanco f"),
    @NamedQuery(name = "FinBanco.findByPkBanco", query = "SELECT f FROM FinBanco f WHERE f.pkBanco = :pkBanco"),
    @NamedQuery(name = "FinBanco.findByDescricao", query = "SELECT f FROM FinBanco f WHERE f.descricao = :descricao"),
    @NamedQuery(name = "FinBanco.findByDataCadastro", query = "SELECT f FROM FinBanco f WHERE f.dataCadastro = :dataCadastro"),
    @NamedQuery(name = "FinBanco.findByDescricaoCompleta", query = "SELECT f FROM FinBanco f WHERE f.descricaoCompleta = :descricaoCompleta")
})
public class FinBanco implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "pk_banco", nullable = false)
    private Integer pkBanco;
    @Size(max = 2147483647)
    @Column(length = 2147483647)
    private String descricao;
    @Column(name = "data_cadastro")
    @Temporal(TemporalType.DATE)
    private Date dataCadastro;
    @Size(max = 2147483647)
    @Column(name = "descricao_completa", length = 2147483647)
    private String descricaoCompleta;
    @JoinColumn(name = "fk_utilizador", referencedColumnName = "pk_seg_conta")
    @ManyToOne
    private SegConta fkUtilizador;

    public FinBanco()
    {
    }

    public FinBanco(Integer pkBanco)
    {
        this.pkBanco = pkBanco;
    }

    public Integer getPkBanco()
    {
        return pkBanco;
    }

    public void setPkBanco(Integer pkBanco)
    {
        this.pkBanco = pkBanco;
    }

    public String getDescricao()
    {
        return descricao;
    }

    public void setDescricao(String descricao)
    {
        this.descricao = descricao;
    }

    public Date getDataCadastro()
    {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro)
    {
        this.dataCadastro = dataCadastro;
    }

    public String getDescricaoCompleta()
    {
        return descricaoCompleta;
    }

    public void setDescricaoCompleta(String descricaoCompleta)
    {
        this.descricaoCompleta = descricaoCompleta;
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
        hash += (pkBanco != null ? pkBanco.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FinBanco))
        {
            return false;
        }
        FinBanco other = (FinBanco) object;
        if ((this.pkBanco == null && other.pkBanco != null) || (this.pkBanco != null && !this.pkBanco.equals(other.pkBanco)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.FinBanco[ pkBanco=" + pkBanco + " ]";
    }
    
}
