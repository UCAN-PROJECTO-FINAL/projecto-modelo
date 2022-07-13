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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author smakambo
 */
@Entity
@Table(name = "bbt_biblioteca", catalog = "sig_ucan_db", schema = "public")
@NamedQueries(
{
    @NamedQuery(name = "BbtBiblioteca.findAll", query = "SELECT b FROM BbtBiblioteca b"),
    @NamedQuery(name = "BbtBiblioteca.findByPkBbtBiblioteca", query = "SELECT b FROM BbtBiblioteca b WHERE b.pkBbtBiblioteca = :pkBbtBiblioteca"),
    @NamedQuery(name = "BbtBiblioteca.findByDesignacao", query = "SELECT b FROM BbtBiblioteca b WHERE b.designacao = :designacao"),
    @NamedQuery(name = "BbtBiblioteca.findByAcronimo", query = "SELECT b FROM BbtBiblioteca b WHERE b.acronimo = :acronimo")
})
public class BbtBiblioteca implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_bbt_biblioteca", nullable = false)
    private Integer pkBbtBiblioteca;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(nullable = false, length = 2147483647)
    private String designacao;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(nullable = false, length = 2147483647)
    private String acronimo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkBbtBiblioteca")
    private List<BbtExemplarDocumento> bbtExemplarDocumentoList;
    @JoinColumn(name = "fk_estrutura_fisica", referencedColumnName = "pk_estrutura_fisica", nullable = false)
    @ManyToOne(optional = false)
    private EstruturaFisica fkEstruturaFisica;

    public BbtBiblioteca()
    {
    }

    public BbtBiblioteca(Integer pkBbtBiblioteca)
    {
        this.pkBbtBiblioteca = pkBbtBiblioteca;
    }

    public BbtBiblioteca(Integer pkBbtBiblioteca, String designacao, String acronimo)
    {
        this.pkBbtBiblioteca = pkBbtBiblioteca;
        this.designacao = designacao;
        this.acronimo = acronimo;
    }

    public Integer getPkBbtBiblioteca()
    {
        return pkBbtBiblioteca;
    }

    public void setPkBbtBiblioteca(Integer pkBbtBiblioteca)
    {
        this.pkBbtBiblioteca = pkBbtBiblioteca;
    }

    public String getDesignacao()
    {
        return designacao;
    }

    public void setDesignacao(String designacao)
    {
        this.designacao = designacao;
    }

    public String getAcronimo()
    {
        return acronimo;
    }

    public void setAcronimo(String acronimo)
    {
        this.acronimo = acronimo;
    }

    public List<BbtExemplarDocumento> getBbtExemplarDocumentoList()
    {
        return bbtExemplarDocumentoList;
    }

    public void setBbtExemplarDocumentoList(List<BbtExemplarDocumento> bbtExemplarDocumentoList)
    {
        this.bbtExemplarDocumentoList = bbtExemplarDocumentoList;
    }

    public EstruturaFisica getFkEstruturaFisica()
    {
        return fkEstruturaFisica;
    }

    public void setFkEstruturaFisica(EstruturaFisica fkEstruturaFisica)
    {
        this.fkEstruturaFisica = fkEstruturaFisica;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (pkBbtBiblioteca != null ? pkBbtBiblioteca.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BbtBiblioteca))
        {
            return false;
        }
        BbtBiblioteca other = (BbtBiblioteca) object;
        if ((this.pkBbtBiblioteca == null && other.pkBbtBiblioteca != null) || (this.pkBbtBiblioteca != null && !this.pkBbtBiblioteca.equals(other.pkBbtBiblioteca)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.BbtBiblioteca[ pkBbtBiblioteca=" + pkBbtBiblioteca + " ]";
    }
    
}
