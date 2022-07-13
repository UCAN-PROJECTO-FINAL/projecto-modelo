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

/**
 *
 * @author smakambo
 */
@Entity
@Table(name = "loc_localidade", catalog = "sig_ucan_db", schema = "public")
@NamedQueries(
{
    @NamedQuery(name = "LocLocalidade.findAll", query = "SELECT l FROM LocLocalidade l"),
    @NamedQuery(name = "LocLocalidade.findByPkLocLocalidade", query = "SELECT l FROM LocLocalidade l WHERE l.pkLocLocalidade = :pkLocLocalidade"),
    @NamedQuery(name = "LocLocalidade.findByDesignacao", query = "SELECT l FROM LocLocalidade l WHERE l.designacao = :designacao"),
    @NamedQuery(name = "LocLocalidade.findByEhDestrito", query = "SELECT l FROM LocLocalidade l WHERE l.ehDestrito = :ehDestrito")
})
public class LocLocalidade implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "pk_loc_localidade", nullable = false, length = 2147483647)
    private String pkLocLocalidade;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(nullable = false, length = 2147483647)
    private String designacao;
    @Column(name = "eh_destrito")
    private Boolean ehDestrito;
    @OneToMany(mappedBy = "fkLocLocalidade")
    private List<BbtDocumento> bbtDocumentoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkLocLocalidade")
    private List<BbtAutoridade> bbtAutoridadeList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "locLocalidade")
    private List<BbtEditoraLocLocalidade> bbtEditoraLocLocalidadeList;
    @OneToMany(mappedBy = "fkLocLocalidade")
    private List<LocLocalidade> locLocalidadeList;
    @JoinColumn(name = "fk_loc_localidade", referencedColumnName = "pk_loc_localidade")
    @ManyToOne
    private LocLocalidade fkLocLocalidade;
    @OneToMany(mappedBy = "fkLocalidade")
    private List<EstruturaFisica> estruturaFisicaList;
    @OneToMany(mappedBy = "fkLoclocalidade")
    private List<GrlEndereco> grlEnderecoList;

    public LocLocalidade()
    {
    }

    public LocLocalidade(String pkLocLocalidade)
    {
        this.pkLocLocalidade = pkLocLocalidade;
    }

    public LocLocalidade(String pkLocLocalidade, String designacao)
    {
        this.pkLocLocalidade = pkLocLocalidade;
        this.designacao = designacao;
    }

    public String getPkLocLocalidade()
    {
        return pkLocLocalidade;
    }

    public void setPkLocLocalidade(String pkLocLocalidade)
    {
        this.pkLocLocalidade = pkLocLocalidade;
    }

    public String getDesignacao()
    {
        return designacao;
    }

    public void setDesignacao(String designacao)
    {
        this.designacao = designacao;
    }

    public Boolean getEhDestrito()
    {
        return ehDestrito;
    }

    public void setEhDestrito(Boolean ehDestrito)
    {
        this.ehDestrito = ehDestrito;
    }

    public List<BbtDocumento> getBbtDocumentoList()
    {
        return bbtDocumentoList;
    }

    public void setBbtDocumentoList(List<BbtDocumento> bbtDocumentoList)
    {
        this.bbtDocumentoList = bbtDocumentoList;
    }

    public List<BbtAutoridade> getBbtAutoridadeList()
    {
        return bbtAutoridadeList;
    }

    public void setBbtAutoridadeList(List<BbtAutoridade> bbtAutoridadeList)
    {
        this.bbtAutoridadeList = bbtAutoridadeList;
    }

    public List<BbtEditoraLocLocalidade> getBbtEditoraLocLocalidadeList()
    {
        return bbtEditoraLocLocalidadeList;
    }

    public void setBbtEditoraLocLocalidadeList(List<BbtEditoraLocLocalidade> bbtEditoraLocLocalidadeList)
    {
        this.bbtEditoraLocLocalidadeList = bbtEditoraLocLocalidadeList;
    }

    public List<LocLocalidade> getLocLocalidadeList()
    {
        return locLocalidadeList;
    }

    public void setLocLocalidadeList(List<LocLocalidade> locLocalidadeList)
    {
        this.locLocalidadeList = locLocalidadeList;
    }

    public LocLocalidade getFkLocLocalidade()
    {
        return fkLocLocalidade;
    }

    public void setFkLocLocalidade(LocLocalidade fkLocLocalidade)
    {
        this.fkLocLocalidade = fkLocLocalidade;
    }

    public List<EstruturaFisica> getEstruturaFisicaList()
    {
        return estruturaFisicaList;
    }

    public void setEstruturaFisicaList(List<EstruturaFisica> estruturaFisicaList)
    {
        this.estruturaFisicaList = estruturaFisicaList;
    }

    public List<GrlEndereco> getGrlEnderecoList()
    {
        return grlEnderecoList;
    }

    public void setGrlEnderecoList(List<GrlEndereco> grlEnderecoList)
    {
        this.grlEnderecoList = grlEnderecoList;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (pkLocLocalidade != null ? pkLocLocalidade.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LocLocalidade))
        {
            return false;
        }
        LocLocalidade other = (LocLocalidade) object;
        if ((this.pkLocLocalidade == null && other.pkLocLocalidade != null) || (this.pkLocLocalidade != null && !this.pkLocLocalidade.equals(other.pkLocLocalidade)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.LocLocalidade[ pkLocLocalidade=" + pkLocLocalidade + " ]";
    }
    
}
