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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 *
 * @author smakambo
 */
@Entity
@Table(name = "gd_versao_documento", catalog = "sig_ucan_db", schema = "public")
@NamedQueries(
{
    @NamedQuery(name = "GdVersaoDocumento.findAll", query = "SELECT g FROM GdVersaoDocumento g"),
    @NamedQuery(name = "GdVersaoDocumento.findByPkVersaoDocumento", query = "SELECT g FROM GdVersaoDocumento g WHERE g.pkVersaoDocumento = :pkVersaoDocumento"),
    @NamedQuery(name = "GdVersaoDocumento.findByMudancaRealizada", query = "SELECT g FROM GdVersaoDocumento g WHERE g.mudancaRealizada = :mudancaRealizada"),
    @NamedQuery(name = "GdVersaoDocumento.findByCaminhoFicheiro", query = "SELECT g FROM GdVersaoDocumento g WHERE g.caminhoFicheiro = :caminhoFicheiro"),
    @NamedQuery(name = "GdVersaoDocumento.findByDataRegisto", query = "SELECT g FROM GdVersaoDocumento g WHERE g.dataRegisto = :dataRegisto"),
    @NamedQuery(name = "GdVersaoDocumento.findByEstado", query = "SELECT g FROM GdVersaoDocumento g WHERE g.estado = :estado")
})
public class GdVersaoDocumento implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_versao_documento", nullable = false)
    private Integer pkVersaoDocumento;
    @Size(max = 2147483647)
    @Column(name = "mudanca_realizada", length = 2147483647)
    private String mudancaRealizada;
    @Size(max = 2147483647)
    @Column(name = "caminho_ficheiro", length = 2147483647)
    private String caminhoFicheiro;
    @Column(name = "data_registo")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataRegisto;
    private Boolean estado;
    @JoinColumn(name = "fk_documento", referencedColumnName = "pk_documento")
    @ManyToOne
    private GdDocumento fkDocumento;

    public GdVersaoDocumento()
    {
    }

    public GdVersaoDocumento(Integer pkVersaoDocumento)
    {
        this.pkVersaoDocumento = pkVersaoDocumento;
    }

    public Integer getPkVersaoDocumento()
    {
        return pkVersaoDocumento;
    }

    public void setPkVersaoDocumento(Integer pkVersaoDocumento)
    {
        this.pkVersaoDocumento = pkVersaoDocumento;
    }

    public String getMudancaRealizada()
    {
        return mudancaRealizada;
    }

    public void setMudancaRealizada(String mudancaRealizada)
    {
        this.mudancaRealizada = mudancaRealizada;
    }

    public String getCaminhoFicheiro()
    {
        return caminhoFicheiro;
    }

    public void setCaminhoFicheiro(String caminhoFicheiro)
    {
        this.caminhoFicheiro = caminhoFicheiro;
    }

    public Date getDataRegisto()
    {
        return dataRegisto;
    }

    public void setDataRegisto(Date dataRegisto)
    {
        this.dataRegisto = dataRegisto;
    }

    public Boolean getEstado()
    {
        return estado;
    }

    public void setEstado(Boolean estado)
    {
        this.estado = estado;
    }

    public GdDocumento getFkDocumento()
    {
        return fkDocumento;
    }

    public void setFkDocumento(GdDocumento fkDocumento)
    {
        this.fkDocumento = fkDocumento;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (pkVersaoDocumento != null ? pkVersaoDocumento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GdVersaoDocumento))
        {
            return false;
        }
        GdVersaoDocumento other = (GdVersaoDocumento) object;
        if ((this.pkVersaoDocumento == null && other.pkVersaoDocumento != null) || (this.pkVersaoDocumento != null && !this.pkVersaoDocumento.equals(other.pkVersaoDocumento)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.GdVersaoDocumento[ pkVersaoDocumento=" + pkVersaoDocumento + " ]";
    }
    
}
