/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author smakambo
 */
@Entity
@Table(name = "fin_tipo_mov_bancarios", catalog = "sig_ucan_db", schema = "public")
@NamedQueries(
{
    @NamedQuery(name = "FinTipoMovBancarios.findAll", query = "SELECT f FROM FinTipoMovBancarios f"),
    @NamedQuery(name = "FinTipoMovBancarios.findByPkTipoMovBancarios", query = "SELECT f FROM FinTipoMovBancarios f WHERE f.pkTipoMovBancarios = :pkTipoMovBancarios"),
    @NamedQuery(name = "FinTipoMovBancarios.findByTipo", query = "SELECT f FROM FinTipoMovBancarios f WHERE f.tipo = :tipo")
})
public class FinTipoMovBancarios implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "pk_tipo_mov_bancarios", nullable = false)
    private Integer pkTipoMovBancarios;
    @Size(max = 2147483647)
    @Column(length = 2147483647)
    private String tipo;
    @JoinColumn(name = "fk_utilizador", referencedColumnName = "pk_seg_conta")
    @ManyToOne
    private SegConta fkUtilizador;

    public FinTipoMovBancarios()
    {
    }

    public FinTipoMovBancarios(Integer pkTipoMovBancarios)
    {
        this.pkTipoMovBancarios = pkTipoMovBancarios;
    }

    public Integer getPkTipoMovBancarios()
    {
        return pkTipoMovBancarios;
    }

    public void setPkTipoMovBancarios(Integer pkTipoMovBancarios)
    {
        this.pkTipoMovBancarios = pkTipoMovBancarios;
    }

    public String getTipo()
    {
        return tipo;
    }

    public void setTipo(String tipo)
    {
        this.tipo = tipo;
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
        hash += (pkTipoMovBancarios != null ? pkTipoMovBancarios.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FinTipoMovBancarios))
        {
            return false;
        }
        FinTipoMovBancarios other = (FinTipoMovBancarios) object;
        if ((this.pkTipoMovBancarios == null && other.pkTipoMovBancarios != null) || (this.pkTipoMovBancarios != null && !this.pkTipoMovBancarios.equals(other.pkTipoMovBancarios)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.FinTipoMovBancarios[ pkTipoMovBancarios=" + pkTipoMovBancarios + " ]";
    }
    
}
