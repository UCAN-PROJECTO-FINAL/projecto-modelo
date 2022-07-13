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
@Table(name = "seg_auditoria", catalog = "sig_ucan_db", schema = "public")
@NamedQueries(
{
    @NamedQuery(name = "SegAuditoria.findAll", query = "SELECT s FROM SegAuditoria s"),
    @NamedQuery(name = "SegAuditoria.findByPkSegAuditoria", query = "SELECT s FROM SegAuditoria s WHERE s.pkSegAuditoria = :pkSegAuditoria"),
    @NamedQuery(name = "SegAuditoria.findByNome", query = "SELECT s FROM SegAuditoria s WHERE s.nome = :nome"),
    @NamedQuery(name = "SegAuditoria.findByNivelRisco", query = "SELECT s FROM SegAuditoria s WHERE s.nivelRisco = :nivelRisco"),
    @NamedQuery(name = "SegAuditoria.findByOperadorRegisto", query = "SELECT s FROM SegAuditoria s WHERE s.operadorRegisto = :operadorRegisto"),
    @NamedQuery(name = "SegAuditoria.findByTipoUtilizador", query = "SELECT s FROM SegAuditoria s WHERE s.tipoUtilizador = :tipoUtilizador"),
    @NamedQuery(name = "SegAuditoria.findByDataRegisto", query = "SELECT s FROM SegAuditoria s WHERE s.dataRegisto = :dataRegisto"),
    @NamedQuery(name = "SegAuditoria.findByIpAuditoria", query = "SELECT s FROM SegAuditoria s WHERE s.ipAuditoria = :ipAuditoria"),
    @NamedQuery(name = "SegAuditoria.findByCategoria", query = "SELECT s FROM SegAuditoria s WHERE s.categoria = :categoria"),
    @NamedQuery(name = "SegAuditoria.findByResultado", query = "SELECT s FROM SegAuditoria s WHERE s.resultado = :resultado"),
    @NamedQuery(name = "SegAuditoria.findByDetalhes", query = "SELECT s FROM SegAuditoria s WHERE s.detalhes = :detalhes")
})
public class SegAuditoria implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "pk_seg_auditoria", nullable = false)
    private Integer pkSegAuditoria;
    @Size(max = 2147483647)
    @Column(length = 2147483647)
    private String nome;
    @Size(max = 2147483647)
    @Column(name = "nivel_risco", length = 2147483647)
    private String nivelRisco;
    @Size(max = 2147483647)
    @Column(name = "operador_registo", length = 2147483647)
    private String operadorRegisto;
    @Size(max = 2147483647)
    @Column(name = "tipo_utilizador", length = 2147483647)
    private String tipoUtilizador;
    @Column(name = "data_registo")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataRegisto;
    @Size(max = 2147483647)
    @Column(name = "ip_auditoria", length = 2147483647)
    private String ipAuditoria;
    @Size(max = 2147483647)
    @Column(length = 2147483647)
    private String categoria;
    @Size(max = 2147483647)
    @Column(length = 2147483647)
    private String resultado;
    @Size(max = 2147483647)
    @Column(length = 2147483647)
    private String detalhes;
    @JoinColumn(name = "fk_seg_conta", referencedColumnName = "pk_seg_conta", nullable = false)
    @ManyToOne(optional = false)
    private SegConta fkSegConta;

    public SegAuditoria()
    {
    }

    public SegAuditoria(Integer pkSegAuditoria)
    {
        this.pkSegAuditoria = pkSegAuditoria;
    }

    public Integer getPkSegAuditoria()
    {
        return pkSegAuditoria;
    }

    public void setPkSegAuditoria(Integer pkSegAuditoria)
    {
        this.pkSegAuditoria = pkSegAuditoria;
    }

    public String getNome()
    {
        return nome;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    public String getNivelRisco()
    {
        return nivelRisco;
    }

    public void setNivelRisco(String nivelRisco)
    {
        this.nivelRisco = nivelRisco;
    }

    public String getOperadorRegisto()
    {
        return operadorRegisto;
    }

    public void setOperadorRegisto(String operadorRegisto)
    {
        this.operadorRegisto = operadorRegisto;
    }

    public String getTipoUtilizador()
    {
        return tipoUtilizador;
    }

    public void setTipoUtilizador(String tipoUtilizador)
    {
        this.tipoUtilizador = tipoUtilizador;
    }

    public Date getDataRegisto()
    {
        return dataRegisto;
    }

    public void setDataRegisto(Date dataRegisto)
    {
        this.dataRegisto = dataRegisto;
    }

    public String getIpAuditoria()
    {
        return ipAuditoria;
    }

    public void setIpAuditoria(String ipAuditoria)
    {
        this.ipAuditoria = ipAuditoria;
    }

    public String getCategoria()
    {
        return categoria;
    }

    public void setCategoria(String categoria)
    {
        this.categoria = categoria;
    }

    public String getResultado()
    {
        return resultado;
    }

    public void setResultado(String resultado)
    {
        this.resultado = resultado;
    }

    public String getDetalhes()
    {
        return detalhes;
    }

    public void setDetalhes(String detalhes)
    {
        this.detalhes = detalhes;
    }

    public SegConta getFkSegConta()
    {
        return fkSegConta;
    }

    public void setFkSegConta(SegConta fkSegConta)
    {
        this.fkSegConta = fkSegConta;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (pkSegAuditoria != null ? pkSegAuditoria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SegAuditoria))
        {
            return false;
        }
        SegAuditoria other = (SegAuditoria) object;
        if ((this.pkSegAuditoria == null && other.pkSegAuditoria != null) || (this.pkSegAuditoria != null && !this.pkSegAuditoria.equals(other.pkSegAuditoria)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.SegAuditoria[ pkSegAuditoria=" + pkSegAuditoria + " ]";
    }
    
}
