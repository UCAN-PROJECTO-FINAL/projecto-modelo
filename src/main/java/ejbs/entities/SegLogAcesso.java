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
@Table(name = "seg_log_acesso", catalog = "sig_ucan_db", schema = "public")
@NamedQueries(
{
    @NamedQuery(name = "SegLogAcesso.findAll", query = "SELECT s FROM SegLogAcesso s"),
    @NamedQuery(name = "SegLogAcesso.findByPkSegLogAcesso", query = "SELECT s FROM SegLogAcesso s WHERE s.pkSegLogAcesso = :pkSegLogAcesso"),
    @NamedQuery(name = "SegLogAcesso.findByEventoLogAcesso", query = "SELECT s FROM SegLogAcesso s WHERE s.eventoLogAcesso = :eventoLogAcesso"),
    @NamedQuery(name = "SegLogAcesso.findByRiscoLogAcesso", query = "SELECT s FROM SegLogAcesso s WHERE s.riscoLogAcesso = :riscoLogAcesso"),
    @NamedQuery(name = "SegLogAcesso.findByOperador", query = "SELECT s FROM SegLogAcesso s WHERE s.operador = :operador"),
    @NamedQuery(name = "SegLogAcesso.findByTipoUsuario", query = "SELECT s FROM SegLogAcesso s WHERE s.tipoUsuario = :tipoUsuario"),
    @NamedQuery(name = "SegLogAcesso.findByDataRegistoLogAcesso", query = "SELECT s FROM SegLogAcesso s WHERE s.dataRegistoLogAcesso = :dataRegistoLogAcesso"),
    @NamedQuery(name = "SegLogAcesso.findByIpLogAcesso", query = "SELECT s FROM SegLogAcesso s WHERE s.ipLogAcesso = :ipLogAcesso"),
    @NamedQuery(name = "SegLogAcesso.findByResultadoLogAcesso", query = "SELECT s FROM SegLogAcesso s WHERE s.resultadoLogAcesso = :resultadoLogAcesso"),
    @NamedQuery(name = "SegLogAcesso.findByDetalhesLogAcesso", query = "SELECT s FROM SegLogAcesso s WHERE s.detalhesLogAcesso = :detalhesLogAcesso")
})
public class SegLogAcesso implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "pk_seg_log_acesso", nullable = false)
    private Integer pkSegLogAcesso;
    @Size(max = 2147483647)
    @Column(name = "evento_log_acesso", length = 2147483647)
    private String eventoLogAcesso;
    @Size(max = 2147483647)
    @Column(name = "risco_log_acesso", length = 2147483647)
    private String riscoLogAcesso;
    @Size(max = 2147483647)
    @Column(length = 2147483647)
    private String operador;
    @Size(max = 2147483647)
    @Column(name = "tipo_usuario", length = 2147483647)
    private String tipoUsuario;
    @Column(name = "data_registo_log_acesso")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataRegistoLogAcesso;
    @Size(max = 2147483647)
    @Column(name = "ip_log_acesso", length = 2147483647)
    private String ipLogAcesso;
    @Size(max = 2147483647)
    @Column(name = "resultado_log_acesso", length = 2147483647)
    private String resultadoLogAcesso;
    @Size(max = 2147483647)
    @Column(name = "detalhes_log_acesso", length = 2147483647)
    private String detalhesLogAcesso;
    @JoinColumn(name = "fk_seg_conta", referencedColumnName = "pk_seg_conta")
    @ManyToOne
    private SegConta fkSegConta;

    public SegLogAcesso()
    {
    }

    public SegLogAcesso(Integer pkSegLogAcesso)
    {
        this.pkSegLogAcesso = pkSegLogAcesso;
    }

    public Integer getPkSegLogAcesso()
    {
        return pkSegLogAcesso;
    }

    public void setPkSegLogAcesso(Integer pkSegLogAcesso)
    {
        this.pkSegLogAcesso = pkSegLogAcesso;
    }

    public String getEventoLogAcesso()
    {
        return eventoLogAcesso;
    }

    public void setEventoLogAcesso(String eventoLogAcesso)
    {
        this.eventoLogAcesso = eventoLogAcesso;
    }

    public String getRiscoLogAcesso()
    {
        return riscoLogAcesso;
    }

    public void setRiscoLogAcesso(String riscoLogAcesso)
    {
        this.riscoLogAcesso = riscoLogAcesso;
    }

    public String getOperador()
    {
        return operador;
    }

    public void setOperador(String operador)
    {
        this.operador = operador;
    }

    public String getTipoUsuario()
    {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario)
    {
        this.tipoUsuario = tipoUsuario;
    }

    public Date getDataRegistoLogAcesso()
    {
        return dataRegistoLogAcesso;
    }

    public void setDataRegistoLogAcesso(Date dataRegistoLogAcesso)
    {
        this.dataRegistoLogAcesso = dataRegistoLogAcesso;
    }

    public String getIpLogAcesso()
    {
        return ipLogAcesso;
    }

    public void setIpLogAcesso(String ipLogAcesso)
    {
        this.ipLogAcesso = ipLogAcesso;
    }

    public String getResultadoLogAcesso()
    {
        return resultadoLogAcesso;
    }

    public void setResultadoLogAcesso(String resultadoLogAcesso)
    {
        this.resultadoLogAcesso = resultadoLogAcesso;
    }

    public String getDetalhesLogAcesso()
    {
        return detalhesLogAcesso;
    }

    public void setDetalhesLogAcesso(String detalhesLogAcesso)
    {
        this.detalhesLogAcesso = detalhesLogAcesso;
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
        hash += (pkSegLogAcesso != null ? pkSegLogAcesso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SegLogAcesso))
        {
            return false;
        }
        SegLogAcesso other = (SegLogAcesso) object;
        if ((this.pkSegLogAcesso == null && other.pkSegLogAcesso != null) || (this.pkSegLogAcesso != null && !this.pkSegLogAcesso.equals(other.pkSegLogAcesso)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.SegLogAcesso[ pkSegLogAcesso=" + pkSegLogAcesso + " ]";
    }
    
}
