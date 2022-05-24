/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author smakambo
 */
@Entity
@Table(name = "estrutura_logica")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "EstruturaLogica.findAll", query = "SELECT e FROM EstruturaLogica e"),
    @NamedQuery(name = "EstruturaLogica.findByPkEstruturaLogica", query = "SELECT e FROM EstruturaLogica e WHERE e.pkEstruturaLogica = :pkEstruturaLogica"),
    @NamedQuery(name = "EstruturaLogica.findByDesignacao", query = "SELECT e FROM EstruturaLogica e WHERE e.designacao = :designacao")
})
public class EstruturaLogica implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "pk_estrutura_logica")
    private String pkEstruturaLogica;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "designacao")
    private String designacao;
    @OneToMany(mappedBy = "fkEstruturaLogica")
    private List<EstruturaLogica> estruturaLogicaList;
    @JoinColumn(name = "fk_estrutura_logica", referencedColumnName = "pk_estrutura_logica")
    @ManyToOne
    private EstruturaLogica fkEstruturaLogica;
    @OneToMany(mappedBy = "fkEstruturaLogica")
    private List<RhConfiguracoes> rhConfiguracoesList;
    @OneToMany(mappedBy = "fkEstruturaLogica")
    private List<FrtConfiguracoes> frtConfiguracoesList;
    @OneToMany(mappedBy = "fkEstrututuraLogica")
    private List<FrtTransporteSolicitacao> frtTransporteSolicitacaoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkEstruturaLogica")
    private List<EstruturaLogicaFisica> estruturaLogicaFisicaList;
    @OneToMany(mappedBy = "fkEstruturaLogica")
    private List<RhFuncionario> rhFuncionarioList;

    public EstruturaLogica()
    {
    }

    public EstruturaLogica(String pkEstruturaLogica)
    {
        this.pkEstruturaLogica = pkEstruturaLogica;
    }

    public EstruturaLogica(String pkEstruturaLogica, String designacao)
    {
        this.pkEstruturaLogica = pkEstruturaLogica;
        this.designacao = designacao;
    }

    public String getPkEstruturaLogica()
    {
        return pkEstruturaLogica;
    }

    public void setPkEstruturaLogica(String pkEstruturaLogica)
    {
        this.pkEstruturaLogica = pkEstruturaLogica;
    }

    public String getDesignacao()
    {
        return designacao;
    }

    public void setDesignacao(String designacao)
    {
        this.designacao = designacao;
    }

    @XmlTransient
    public List<EstruturaLogica> getEstruturaLogicaList()
    {
        return estruturaLogicaList;
    }

    public void setEstruturaLogicaList(List<EstruturaLogica> estruturaLogicaList)
    {
        this.estruturaLogicaList = estruturaLogicaList;
    }

    public EstruturaLogica getFkEstruturaLogica()
    {
        return fkEstruturaLogica;
    }

    public void setFkEstruturaLogica(EstruturaLogica fkEstruturaLogica)
    {
        this.fkEstruturaLogica = fkEstruturaLogica;
    }

    @XmlTransient
    public List<RhConfiguracoes> getRhConfiguracoesList()
    {
        return rhConfiguracoesList;
    }

    public void setRhConfiguracoesList(List<RhConfiguracoes> rhConfiguracoesList)
    {
        this.rhConfiguracoesList = rhConfiguracoesList;
    }

    @XmlTransient
    public List<FrtConfiguracoes> getFrtConfiguracoesList()
    {
        return frtConfiguracoesList;
    }

    public void setFrtConfiguracoesList(List<FrtConfiguracoes> frtConfiguracoesList)
    {
        this.frtConfiguracoesList = frtConfiguracoesList;
    }

    @XmlTransient
    public List<FrtTransporteSolicitacao> getFrtTransporteSolicitacaoList()
    {
        return frtTransporteSolicitacaoList;
    }

    public void setFrtTransporteSolicitacaoList(List<FrtTransporteSolicitacao> frtTransporteSolicitacaoList)
    {
        this.frtTransporteSolicitacaoList = frtTransporteSolicitacaoList;
    }

    @XmlTransient
    public List<EstruturaLogicaFisica> getEstruturaLogicaFisicaList()
    {
        return estruturaLogicaFisicaList;
    }

    public void setEstruturaLogicaFisicaList(List<EstruturaLogicaFisica> estruturaLogicaFisicaList)
    {
        this.estruturaLogicaFisicaList = estruturaLogicaFisicaList;
    }

    @XmlTransient
    public List<RhFuncionario> getRhFuncionarioList()
    {
        return rhFuncionarioList;
    }

    public void setRhFuncionarioList(List<RhFuncionario> rhFuncionarioList)
    {
        this.rhFuncionarioList = rhFuncionarioList;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (pkEstruturaLogica != null ? pkEstruturaLogica.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstruturaLogica))
        {
            return false;
        }
        EstruturaLogica other = (EstruturaLogica) object;
        if ((this.pkEstruturaLogica == null && other.pkEstruturaLogica != null) || (this.pkEstruturaLogica != null && !this.pkEstruturaLogica.equals(other.pkEstruturaLogica)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.EstruturaLogica[ pkEstruturaLogica=" + pkEstruturaLogica + " ]";
    }
    
}
