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
import javax.validation.constraints.Size;

/**
 *
 * @author smakambo
 */
@Entity
@Table(name = "gd_documento", catalog = "sig_ucan_db", schema = "public")
@NamedQueries(
{
    @NamedQuery(name = "GdDocumento.findAll", query = "SELECT g FROM GdDocumento g"),
    @NamedQuery(name = "GdDocumento.findByPkDocumento", query = "SELECT g FROM GdDocumento g WHERE g.pkDocumento = :pkDocumento"),
    @NamedQuery(name = "GdDocumento.findByTitulo", query = "SELECT g FROM GdDocumento g WHERE g.titulo = :titulo"),
    @NamedQuery(name = "GdDocumento.findByCaminho", query = "SELECT g FROM GdDocumento g WHERE g.caminho = :caminho"),
    @NamedQuery(name = "GdDocumento.findByDataCriacao", query = "SELECT g FROM GdDocumento g WHERE g.dataCriacao = :dataCriacao"),
    @NamedQuery(name = "GdDocumento.findByEstado", query = "SELECT g FROM GdDocumento g WHERE g.estado = :estado")
})
public class GdDocumento implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_documento", nullable = false)
    private Integer pkDocumento;
    @Size(max = 2147483647)
    @Column(length = 2147483647)
    private String titulo;
    @Size(max = 2147483647)
    @Column(length = 2147483647)
    private String caminho;
    @Column(name = "data_criacao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCriacao;
    private Boolean estado;
    @JoinColumn(name = "fk_estrutura_logica_fisica", referencedColumnName = "pk_estrutura_logica_fisica")
    @ManyToOne
    private EstruturaLogicaFisica fkEstruturaLogicaFisica;
    @JoinColumn(name = "fk_classificacao", referencedColumnName = "pk_classificacao")
    @ManyToOne
    private GdClassificacao fkClassificacao;
    @JoinColumn(name = "fk_entidade", referencedColumnName = "pk_entidade")
    @ManyToOne
    private GdEntidade fkEntidade;
    @JoinColumn(name = "fk_tipo_documento", referencedColumnName = "pk_tipo_documento")
    @ManyToOne
    private GdTipoDocumento fkTipoDocumento;
    @OneToMany(mappedBy = "fkDocumento")
    private List<GdVersaoDocumento> gdVersaoDocumentoList;

    public GdDocumento()
    {
    }

    public GdDocumento(Integer pkDocumento)
    {
        this.pkDocumento = pkDocumento;
    }

    public Integer getPkDocumento()
    {
        return pkDocumento;
    }

    public void setPkDocumento(Integer pkDocumento)
    {
        this.pkDocumento = pkDocumento;
    }

    public String getTitulo()
    {
        return titulo;
    }

    public void setTitulo(String titulo)
    {
        this.titulo = titulo;
    }

    public String getCaminho()
    {
        return caminho;
    }

    public void setCaminho(String caminho)
    {
        this.caminho = caminho;
    }

    public Date getDataCriacao()
    {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao)
    {
        this.dataCriacao = dataCriacao;
    }

    public Boolean getEstado()
    {
        return estado;
    }

    public void setEstado(Boolean estado)
    {
        this.estado = estado;
    }

    public EstruturaLogicaFisica getFkEstruturaLogicaFisica()
    {
        return fkEstruturaLogicaFisica;
    }

    public void setFkEstruturaLogicaFisica(EstruturaLogicaFisica fkEstruturaLogicaFisica)
    {
        this.fkEstruturaLogicaFisica = fkEstruturaLogicaFisica;
    }

    public GdClassificacao getFkClassificacao()
    {
        return fkClassificacao;
    }

    public void setFkClassificacao(GdClassificacao fkClassificacao)
    {
        this.fkClassificacao = fkClassificacao;
    }

    public GdEntidade getFkEntidade()
    {
        return fkEntidade;
    }

    public void setFkEntidade(GdEntidade fkEntidade)
    {
        this.fkEntidade = fkEntidade;
    }

    public GdTipoDocumento getFkTipoDocumento()
    {
        return fkTipoDocumento;
    }

    public void setFkTipoDocumento(GdTipoDocumento fkTipoDocumento)
    {
        this.fkTipoDocumento = fkTipoDocumento;
    }

    public List<GdVersaoDocumento> getGdVersaoDocumentoList()
    {
        return gdVersaoDocumentoList;
    }

    public void setGdVersaoDocumentoList(List<GdVersaoDocumento> gdVersaoDocumentoList)
    {
        this.gdVersaoDocumentoList = gdVersaoDocumentoList;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (pkDocumento != null ? pkDocumento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GdDocumento))
        {
            return false;
        }
        GdDocumento other = (GdDocumento) object;
        if ((this.pkDocumento == null && other.pkDocumento != null) || (this.pkDocumento != null && !this.pkDocumento.equals(other.pkDocumento)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.GdDocumento[ pkDocumento=" + pkDocumento + " ]";
    }
    
}
