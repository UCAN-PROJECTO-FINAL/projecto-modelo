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
@Table(name = "ct_ocorrencia_contabilidade", catalog = "sig_ucan_db", schema = "public")
@NamedQueries(
{
    @NamedQuery(name = "CtOcorrenciaContabilidade.findAll", query = "SELECT c FROM CtOcorrenciaContabilidade c"),
    @NamedQuery(name = "CtOcorrenciaContabilidade.findByPkOcorrenciaContabilidade", query = "SELECT c FROM CtOcorrenciaContabilidade c WHERE c.pkOcorrenciaContabilidade = :pkOcorrenciaContabilidade"),
    @NamedQuery(name = "CtOcorrenciaContabilidade.findByHistorico", query = "SELECT c FROM CtOcorrenciaContabilidade c WHERE c.historico = :historico"),
    @NamedQuery(name = "CtOcorrenciaContabilidade.findByDateMovimento", query = "SELECT c FROM CtOcorrenciaContabilidade c WHERE c.dateMovimento = :dateMovimento"),
    @NamedQuery(name = "CtOcorrenciaContabilidade.findByDataRegistro", query = "SELECT c FROM CtOcorrenciaContabilidade c WHERE c.dataRegistro = :dataRegistro")
})
public class CtOcorrenciaContabilidade implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "pk_ocorrencia_contabilidade", nullable = false)
    private Integer pkOcorrenciaContabilidade;
    @Size(max = 2147483647)
    @Column(length = 2147483647)
    private String historico;
    @Column(name = "date_movimento")
    @Temporal(TemporalType.DATE)
    private Date dateMovimento;
    @Column(name = "data_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataRegistro;
    @JoinColumn(name = "fk_utilizador", referencedColumnName = "pk_seg_conta")
    @ManyToOne
    private SegConta fkUtilizador;

    public CtOcorrenciaContabilidade()
    {
    }

    public CtOcorrenciaContabilidade(Integer pkOcorrenciaContabilidade)
    {
        this.pkOcorrenciaContabilidade = pkOcorrenciaContabilidade;
    }

    public Integer getPkOcorrenciaContabilidade()
    {
        return pkOcorrenciaContabilidade;
    }

    public void setPkOcorrenciaContabilidade(Integer pkOcorrenciaContabilidade)
    {
        this.pkOcorrenciaContabilidade = pkOcorrenciaContabilidade;
    }

    public String getHistorico()
    {
        return historico;
    }

    public void setHistorico(String historico)
    {
        this.historico = historico;
    }

    public Date getDateMovimento()
    {
        return dateMovimento;
    }

    public void setDateMovimento(Date dateMovimento)
    {
        this.dateMovimento = dateMovimento;
    }

    public Date getDataRegistro()
    {
        return dataRegistro;
    }

    public void setDataRegistro(Date dataRegistro)
    {
        this.dataRegistro = dataRegistro;
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
        hash += (pkOcorrenciaContabilidade != null ? pkOcorrenciaContabilidade.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CtOcorrenciaContabilidade))
        {
            return false;
        }
        CtOcorrenciaContabilidade other = (CtOcorrenciaContabilidade) object;
        if ((this.pkOcorrenciaContabilidade == null && other.pkOcorrenciaContabilidade != null) || (this.pkOcorrenciaContabilidade != null && !this.pkOcorrenciaContabilidade.equals(other.pkOcorrenciaContabilidade)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.CtOcorrenciaContabilidade[ pkOcorrenciaContabilidade=" + pkOcorrenciaContabilidade + " ]";
    }
    
}
