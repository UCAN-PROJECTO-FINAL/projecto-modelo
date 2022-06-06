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
@Table(name = "grl_endereco", catalog = "sig_ucan_db", schema = "public")
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
    @Basic(optional = false)
    @NotNull
    @Column(name = "pk_id_endereco", nullable = false)
    private Integer pkIdEndereco;
    @Size(max = 50)
    @Column(length = 50)
    private String bairro;
    @Size(max = 50)
    @Column(name = "numero_casa", length = 50)
    private String numeroCasa;
    @Size(max = 50)
    @Column(length = 50)
    private String rua;
    @Size(max = 100)
    @Column(name = "codigo_postal", length = 100)
    private String codigoPostal;
    @OneToMany(mappedBy = "fkEndereco")
    private List<GrlEntidade> grlEntidadeList;
    @OneToMany(mappedBy = "fkResidenciaBairro")
    private List<GrlPessoa> grlPessoaList;
    @JoinColumn(name = "fk_loclocalidade", referencedColumnName = "pk_loc_localidade")
    @ManyToOne
    private LocLocalidade fkLoclocalidade;
    @OneToMany(mappedBy = "fkGrlEndereco")
    private List<FrtTransporteSolicitacao> frtTransporteSolicitacaoList;

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

    public List<GrlEntidade> getGrlEntidadeList()
    {
        return grlEntidadeList;
    }

    public void setGrlEntidadeList(List<GrlEntidade> grlEntidadeList)
    {
        this.grlEntidadeList = grlEntidadeList;
    }

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
