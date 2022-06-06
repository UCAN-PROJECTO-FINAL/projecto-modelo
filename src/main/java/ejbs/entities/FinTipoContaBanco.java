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
@Table(name = "fin_tipo_conta_banco", catalog = "sig_ucan_db", schema = "public")
@NamedQueries(
{
    @NamedQuery(name = "FinTipoContaBanco.findAll", query = "SELECT f FROM FinTipoContaBanco f"),
    @NamedQuery(name = "FinTipoContaBanco.findByPkTipoContaBanco", query = "SELECT f FROM FinTipoContaBanco f WHERE f.pkTipoContaBanco = :pkTipoContaBanco"),
    @NamedQuery(name = "FinTipoContaBanco.findByTipoContaBanco", query = "SELECT f FROM FinTipoContaBanco f WHERE f.tipoContaBanco = :tipoContaBanco"),
    @NamedQuery(name = "FinTipoContaBanco.findByFkUtilizador", query = "SELECT f FROM FinTipoContaBanco f WHERE f.fkUtilizador = :fkUtilizador")
})
public class FinTipoContaBanco implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "pk_tipo_conta_banco", nullable = false)
    private Integer pkTipoContaBanco;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "tipo_conta_banco", nullable = false, length = 50)
    private String tipoContaBanco;
    @Column(name = "fk_utilizador")
    private Integer fkUtilizador;

    public FinTipoContaBanco()
    {
    }

    public FinTipoContaBanco(Integer pkTipoContaBanco)
    {
        this.pkTipoContaBanco = pkTipoContaBanco;
    }

    public FinTipoContaBanco(Integer pkTipoContaBanco, String tipoContaBanco)
    {
        this.pkTipoContaBanco = pkTipoContaBanco;
        this.tipoContaBanco = tipoContaBanco;
    }

    public Integer getPkTipoContaBanco()
    {
        return pkTipoContaBanco;
    }

    public void setPkTipoContaBanco(Integer pkTipoContaBanco)
    {
        this.pkTipoContaBanco = pkTipoContaBanco;
    }

    public String getTipoContaBanco()
    {
        return tipoContaBanco;
    }

    public void setTipoContaBanco(String tipoContaBanco)
    {
        this.tipoContaBanco = tipoContaBanco;
    }

    public Integer getFkUtilizador()
    {
        return fkUtilizador;
    }

    public void setFkUtilizador(Integer fkUtilizador)
    {
        this.fkUtilizador = fkUtilizador;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (pkTipoContaBanco != null ? pkTipoContaBanco.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FinTipoContaBanco))
        {
            return false;
        }
        FinTipoContaBanco other = (FinTipoContaBanco) object;
        if ((this.pkTipoContaBanco == null && other.pkTipoContaBanco != null) || (this.pkTipoContaBanco != null && !this.pkTipoContaBanco.equals(other.pkTipoContaBanco)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.FinTipoContaBanco[ pkTipoContaBanco=" + pkTipoContaBanco + " ]";
    }
    
}
