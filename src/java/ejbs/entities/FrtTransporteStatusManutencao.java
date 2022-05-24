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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author smakambo
 */
@Entity
@Table(name = "frt_transporte_status_manutencao")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "FrtTransporteStatusManutencao.findAll", query = "SELECT f FROM FrtTransporteStatusManutencao f"),
    @NamedQuery(name = "FrtTransporteStatusManutencao.findByPkStatusManutencao", query = "SELECT f FROM FrtTransporteStatusManutencao f WHERE f.pkStatusManutencao = :pkStatusManutencao"),
    @NamedQuery(name = "FrtTransporteStatusManutencao.findByDescricao", query = "SELECT f FROM FrtTransporteStatusManutencao f WHERE f.descricao = :descricao")
})
public class FrtTransporteStatusManutencao implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_status_manutencao")
    private Integer pkStatusManutencao;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "descricao")
    private String descricao;

    public FrtTransporteStatusManutencao()
    {
    }

    public FrtTransporteStatusManutencao(Integer pkStatusManutencao)
    {
        this.pkStatusManutencao = pkStatusManutencao;
    }

    public FrtTransporteStatusManutencao(Integer pkStatusManutencao, String descricao)
    {
        this.pkStatusManutencao = pkStatusManutencao;
        this.descricao = descricao;
    }

    public Integer getPkStatusManutencao()
    {
        return pkStatusManutencao;
    }

    public void setPkStatusManutencao(Integer pkStatusManutencao)
    {
        this.pkStatusManutencao = pkStatusManutencao;
    }

    public String getDescricao()
    {
        return descricao;
    }

    public void setDescricao(String descricao)
    {
        this.descricao = descricao;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (pkStatusManutencao != null ? pkStatusManutencao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FrtTransporteStatusManutencao))
        {
            return false;
        }
        FrtTransporteStatusManutencao other = (FrtTransporteStatusManutencao) object;
        if ((this.pkStatusManutencao == null && other.pkStatusManutencao != null) || (this.pkStatusManutencao != null && !this.pkStatusManutencao.equals(other.pkStatusManutencao)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.FrtTransporteStatusManutencao[ pkStatusManutencao=" + pkStatusManutencao + " ]";
    }
    
}
