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
@Table(name = "estrutura_fisica")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "EstruturaFisica.findAll", query = "SELECT e FROM EstruturaFisica e"),
    @NamedQuery(name = "EstruturaFisica.findByPkEstruturaFisica", query = "SELECT e FROM EstruturaFisica e WHERE e.pkEstruturaFisica = :pkEstruturaFisica"),
    @NamedQuery(name = "EstruturaFisica.findByDesignacao", query = "SELECT e FROM EstruturaFisica e WHERE e.designacao = :designacao")
})
public class EstruturaFisica implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "pk_estrutura_fisica")
    private String pkEstruturaFisica;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "designacao")
    private String designacao;
    @OneToMany(mappedBy = "fkEstruturaFisica")
    private List<EstruturaFisica> estruturaFisicaList;
    @JoinColumn(name = "fk_estrutura_fisica", referencedColumnName = "pk_estrutura_fisica")
    @ManyToOne
    private EstruturaFisica fkEstruturaFisica;
    @JoinColumn(name = "fk_localidade", referencedColumnName = "pk_loc_localidade")
    @ManyToOne
    private LocLocalidade fkLocalidade;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkEstruturaFisica")
    private List<EstruturaLogicaFisica> estruturaLogicaFisicaList;

    public EstruturaFisica()
    {
    }

    public EstruturaFisica(String pkEstruturaFisica)
    {
        this.pkEstruturaFisica = pkEstruturaFisica;
    }

    public EstruturaFisica(String pkEstruturaFisica, String designacao)
    {
        this.pkEstruturaFisica = pkEstruturaFisica;
        this.designacao = designacao;
    }

    public String getPkEstruturaFisica()
    {
        return pkEstruturaFisica;
    }

    public void setPkEstruturaFisica(String pkEstruturaFisica)
    {
        this.pkEstruturaFisica = pkEstruturaFisica;
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
    public List<EstruturaFisica> getEstruturaFisicaList()
    {
        return estruturaFisicaList;
    }

    public void setEstruturaFisicaList(List<EstruturaFisica> estruturaFisicaList)
    {
        this.estruturaFisicaList = estruturaFisicaList;
    }

    public EstruturaFisica getFkEstruturaFisica()
    {
        return fkEstruturaFisica;
    }

    public void setFkEstruturaFisica(EstruturaFisica fkEstruturaFisica)
    {
        this.fkEstruturaFisica = fkEstruturaFisica;
    }

    public LocLocalidade getFkLocalidade()
    {
        return fkLocalidade;
    }

    public void setFkLocalidade(LocLocalidade fkLocalidade)
    {
        this.fkLocalidade = fkLocalidade;
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

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (pkEstruturaFisica != null ? pkEstruturaFisica.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstruturaFisica))
        {
            return false;
        }
        EstruturaFisica other = (EstruturaFisica) object;
        if ((this.pkEstruturaFisica == null && other.pkEstruturaFisica != null) || (this.pkEstruturaFisica != null && !this.pkEstruturaFisica.equals(other.pkEstruturaFisica)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.EstruturaFisica[ pkEstruturaFisica=" + pkEstruturaFisica + " ]";
    }
    
}
