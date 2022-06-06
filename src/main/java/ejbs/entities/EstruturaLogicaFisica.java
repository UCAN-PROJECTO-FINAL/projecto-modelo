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
import javax.validation.constraints.NotNull;

/**
 *
 * @author smakambo
 */
@Entity
@Table(name = "estrutura_logica_fisica", catalog = "sig_ucan_db", schema = "public")
@NamedQueries(
{
    @NamedQuery(name = "EstruturaLogicaFisica.findAll", query = "SELECT e FROM EstruturaLogicaFisica e"),
    @NamedQuery(name = "EstruturaLogicaFisica.findByPkEstruturaLogicaFisica", query = "SELECT e FROM EstruturaLogicaFisica e WHERE e.pkEstruturaLogicaFisica = :pkEstruturaLogicaFisica"),
    @NamedQuery(name = "EstruturaLogicaFisica.findByData", query = "SELECT e FROM EstruturaLogicaFisica e WHERE e.data = :data"),
    @NamedQuery(name = "EstruturaLogicaFisica.findByHora", query = "SELECT e FROM EstruturaLogicaFisica e WHERE e.hora = :hora")
})
public class EstruturaLogicaFisica implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_estrutura_logica_fisica", nullable = false)
    private Integer pkEstruturaLogicaFisica;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date data;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date hora;
    @JoinColumn(name = "fk_estrutura_fisica", referencedColumnName = "pk_estrutura_fisica", nullable = false)
    @ManyToOne(optional = false)
    private EstruturaFisica fkEstruturaFisica;
    @JoinColumn(name = "fk_estrutura_logica", referencedColumnName = "pk_estrutura_logica", nullable = false)
    @ManyToOne(optional = false)
    private EstruturaLogica fkEstruturaLogica;
    @OneToMany(mappedBy = "fkEstruturaLogicaFisica")
    private List<ArArquivoMorto> arArquivoMortoList;
    @OneToMany(mappedBy = "fkEstruturaLogicaFisica")
    private List<RhFuncionario> rhFuncionarioList;
    @OneToMany(mappedBy = "fkEstruturaLogicaFisica")
    private List<GdDocumento> gdDocumentoList;
    @OneToMany(mappedBy = "fkEstruturaLogicaFisica")
    private List<FrtTransporteSolicitacao> frtTransporteSolicitacaoList;

    public EstruturaLogicaFisica()
    {
    }

    public EstruturaLogicaFisica(Integer pkEstruturaLogicaFisica)
    {
        this.pkEstruturaLogicaFisica = pkEstruturaLogicaFisica;
    }

    public EstruturaLogicaFisica(Integer pkEstruturaLogicaFisica, Date data, Date hora)
    {
        this.pkEstruturaLogicaFisica = pkEstruturaLogicaFisica;
        this.data = data;
        this.hora = hora;
    }

    public Integer getPkEstruturaLogicaFisica()
    {
        return pkEstruturaLogicaFisica;
    }

    public void setPkEstruturaLogicaFisica(Integer pkEstruturaLogicaFisica)
    {
        this.pkEstruturaLogicaFisica = pkEstruturaLogicaFisica;
    }

    public Date getData()
    {
        return data;
    }

    public void setData(Date data)
    {
        this.data = data;
    }

    public Date getHora()
    {
        return hora;
    }

    public void setHora(Date hora)
    {
        this.hora = hora;
    }

    public EstruturaFisica getFkEstruturaFisica()
    {
        return fkEstruturaFisica;
    }

    public void setFkEstruturaFisica(EstruturaFisica fkEstruturaFisica)
    {
        this.fkEstruturaFisica = fkEstruturaFisica;
    }

    public EstruturaLogica getFkEstruturaLogica()
    {
        return fkEstruturaLogica;
    }

    public void setFkEstruturaLogica(EstruturaLogica fkEstruturaLogica)
    {
        this.fkEstruturaLogica = fkEstruturaLogica;
    }

    public List<ArArquivoMorto> getArArquivoMortoList()
    {
        return arArquivoMortoList;
    }

    public void setArArquivoMortoList(List<ArArquivoMorto> arArquivoMortoList)
    {
        this.arArquivoMortoList = arArquivoMortoList;
    }

    public List<RhFuncionario> getRhFuncionarioList()
    {
        return rhFuncionarioList;
    }

    public void setRhFuncionarioList(List<RhFuncionario> rhFuncionarioList)
    {
        this.rhFuncionarioList = rhFuncionarioList;
    }

    public List<GdDocumento> getGdDocumentoList()
    {
        return gdDocumentoList;
    }

    public void setGdDocumentoList(List<GdDocumento> gdDocumentoList)
    {
        this.gdDocumentoList = gdDocumentoList;
    }

    public List<FrtTransporteSolicitacao> getFrtTransporteSolicitacaoList()
    {
        return frtTransporteSolicitacaoList;
    }

    public void setFrtTransporteSolicitacaoList(List<FrtTransporteSolicitacao> frtTransporteSolicitacaoList)
    {
        this.frtTransporteSolicitacaoList = frtTransporteSolicitacaoList;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (pkEstruturaLogicaFisica != null ? pkEstruturaLogicaFisica.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstruturaLogicaFisica))
        {
            return false;
        }
        EstruturaLogicaFisica other = (EstruturaLogicaFisica) object;
        if ((this.pkEstruturaLogicaFisica == null && other.pkEstruturaLogicaFisica != null) || (this.pkEstruturaLogicaFisica != null && !this.pkEstruturaLogicaFisica.equals(other.pkEstruturaLogicaFisica)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.EstruturaLogicaFisica[ pkEstruturaLogicaFisica=" + pkEstruturaLogicaFisica + " ]";
    }
    
}
