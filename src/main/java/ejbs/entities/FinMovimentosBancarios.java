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
@Table(name = "fin_movimentos_bancarios", catalog = "sig_ucan_db", schema = "public")
@NamedQueries(
{
    @NamedQuery(name = "FinMovimentosBancarios.findAll", query = "SELECT f FROM FinMovimentosBancarios f"),
    @NamedQuery(name = "FinMovimentosBancarios.findByPkMovimentosBancarios", query = "SELECT f FROM FinMovimentosBancarios f WHERE f.pkMovimentosBancarios = :pkMovimentosBancarios"),
    @NamedQuery(name = "FinMovimentosBancarios.findByMovimento", query = "SELECT f FROM FinMovimentosBancarios f WHERE f.movimento = :movimento"),
    @NamedQuery(name = "FinMovimentosBancarios.findByFkTipoMovBancarios", query = "SELECT f FROM FinMovimentosBancarios f WHERE f.fkTipoMovBancarios = :fkTipoMovBancarios")
})
public class FinMovimentosBancarios implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "pk_movimentos_bancarios", nullable = false)
    private Integer pkMovimentosBancarios;
    @Size(max = 50)
    @Column(length = 50)
    private String movimento;
    @Column(name = "fk_tipo_mov_bancarios")
    private Integer fkTipoMovBancarios;
    @JoinColumn(name = "fk_utilizador", referencedColumnName = "pk_seg_conta")
    @ManyToOne
    private SegConta fkUtilizador;

    public FinMovimentosBancarios()
    {
    }

    public FinMovimentosBancarios(Integer pkMovimentosBancarios)
    {
        this.pkMovimentosBancarios = pkMovimentosBancarios;
    }

    public Integer getPkMovimentosBancarios()
    {
        return pkMovimentosBancarios;
    }

    public void setPkMovimentosBancarios(Integer pkMovimentosBancarios)
    {
        this.pkMovimentosBancarios = pkMovimentosBancarios;
    }

    public String getMovimento()
    {
        return movimento;
    }

    public void setMovimento(String movimento)
    {
        this.movimento = movimento;
    }

    public Integer getFkTipoMovBancarios()
    {
        return fkTipoMovBancarios;
    }

    public void setFkTipoMovBancarios(Integer fkTipoMovBancarios)
    {
        this.fkTipoMovBancarios = fkTipoMovBancarios;
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
        hash += (pkMovimentosBancarios != null ? pkMovimentosBancarios.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FinMovimentosBancarios))
        {
            return false;
        }
        FinMovimentosBancarios other = (FinMovimentosBancarios) object;
        if ((this.pkMovimentosBancarios == null && other.pkMovimentosBancarios != null) || (this.pkMovimentosBancarios != null && !this.pkMovimentosBancarios.equals(other.pkMovimentosBancarios)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.FinMovimentosBancarios[ pkMovimentosBancarios=" + pkMovimentosBancarios + " ]";
    }
    
}
