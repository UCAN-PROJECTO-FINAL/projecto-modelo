/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.entities;

import java.io.Serializable;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author smakambo
 */
@Entity
@Table(name = "grl_endereco")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "GrlEndereco.findAll", query = "SELECT g FROM GrlEndereco g"),
    @NamedQuery(name = "GrlEndereco.findByPkIdEndereco", query = "SELECT g FROM GrlEndereco g WHERE g.pkIdEndereco = :pkIdEndereco"),
    @NamedQuery(name = "GrlEndereco.findByBairro", query = "SELECT g FROM GrlEndereco g WHERE g.bairro = :bairro"),
    @NamedQuery(name = "GrlEndereco.findByNumeroCasa", query = "SELECT g FROM GrlEndereco g WHERE g.numeroCasa = :numeroCasa"),
    @NamedQuery(name = "GrlEndereco.findByRua", query = "SELECT g FROM GrlEndereco g WHERE g.rua = :rua"),
    @NamedQuery(name = "GrlEndereco.findByCodigoPostal", query = "SELECT g FROM GrlEndereco g WHERE g.codigoPostal = :codigoPostal")
})
public class GrlEndereco implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_id_endereco")
    private Integer pkIdEndereco;
    @Size(max = 50)
    @Column(name = "bairro")
    private String bairro;
    @Size(max = 50)
    @Column(name = "numero_casa")
    private String numeroCasa;
    @Size(max = 50)
    @Column(name = "rua")
    private String rua;
    @Size(max = 100)
    @Column(name = "codigo_postal")
    private String codigoPostal;
    @OneToMany(mappedBy = "fkResidenciaBairro")
    private List<GrlPessoa> grlPessoaList;
    @JoinColumn(name = "fk_loclocalidade", referencedColumnName = "pk_loc_localidade")
    @ManyToOne
    private LocLocalidade fkLoclocalidade;

    public GrlEndereco()
    {
    }

    public GrlEndereco(Integer pkIdEndereco)
    {
        this.pkIdEndereco = pkIdEndereco;
    }

    public Integer getPkIdEndereco()
    {
        return pkIdEndereco;
    }

    public void setPkIdEndereco(Integer pkIdEndereco)
    {
        this.pkIdEndereco = pkIdEndereco;
    }

    public String getBairro()
    {
        return bairro;
    }

    public void setBairro(String bairro)
    {
        this.bairro = bairro;
    }

    public String getNumeroCasa()
    {
        return numeroCasa;
    }

    public void setNumeroCasa(String numeroCasa)
    {
        this.numeroCasa = numeroCasa;
    }

    public String getRua()
    {
        return rua;
    }

    public void setRua(String rua)
    {
        this.rua = rua;
    }

    public String getCodigoPostal()
    {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal)
    {
        this.codigoPostal = codigoPostal;
    }

    @XmlTransient
    public List<GrlPessoa> getGrlPessoaList()
    {
        return grlPessoaList;
    }

    public void setGrlPessoaList(List<GrlPessoa> grlPessoaList)
    {
        this.grlPessoaList = grlPessoaList;
    }

    public LocLocalidade getFkLoclocalidade()
    {
        return fkLoclocalidade;
    }

    public void setFkLoclocalidade(LocLocalidade fkLoclocalidade)
    {
        this.fkLoclocalidade = fkLoclocalidade;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (pkIdEndereco != null ? pkIdEndereco.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GrlEndereco))
        {
            return false;
        }
        GrlEndereco other = (GrlEndereco) object;
        if ((this.pkIdEndereco == null && other.pkIdEndereco != null) || (this.pkIdEndereco != null && !this.pkIdEndereco.equals(other.pkIdEndereco)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.GrlEndereco[ pkIdEndereco=" + pkIdEndereco + " ]";
    }
    
}
