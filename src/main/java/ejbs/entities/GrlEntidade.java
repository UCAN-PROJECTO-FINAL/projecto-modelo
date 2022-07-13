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
@Table(name = "grl_entidade", catalog = "sig_ucan_db", schema = "public")
@NamedQueries(
{
    @NamedQuery(name = "GrlEntidade.findAll", query = "SELECT g FROM GrlEntidade g"),
    @NamedQuery(name = "GrlEntidade.findByPkEntidade", query = "SELECT g FROM GrlEntidade g WHERE g.pkEntidade = :pkEntidade"),
    @NamedQuery(name = "GrlEntidade.findByNome", query = "SELECT g FROM GrlEntidade g WHERE g.nome = :nome"),
    @NamedQuery(name = "GrlEntidade.findByEmail", query = "SELECT g FROM GrlEntidade g WHERE g.email = :email"),
    @NamedQuery(name = "GrlEntidade.findByTelefone", query = "SELECT g FROM GrlEntidade g WHERE g.telefone = :telefone"),
    @NamedQuery(name = "GrlEntidade.findByFkUtilizador", query = "SELECT g FROM GrlEntidade g WHERE g.fkUtilizador = :fkUtilizador"),
    @NamedQuery(name = "GrlEntidade.findByEstado", query = "SELECT g FROM GrlEntidade g WHERE g.estado = :estado"),
    @NamedQuery(name = "GrlEntidade.findByNifEntidade", query = "SELECT g FROM GrlEntidade g WHERE g.nifEntidade = :nifEntidade")
})
public class GrlEntidade implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_entidade", nullable = false)
    private Integer pkEntidade;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(nullable = false, length = 50)
    private String nome;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 50)
    @Column(length = 50)
    private String email;
    @Size(max = 50)
    @Column(length = 50)
    private String telefone;
    @Column(name = "fk_utilizador")
    private Integer fkUtilizador;
    private Boolean estado;
    @Size(max = 30)
    @Column(name = "nif_entidade", length = 30)
    private String nifEntidade;
    @OneToMany(mappedBy = "fkFornecedor")
    private List<FinContasPagar> finContasPagarList;
    @OneToMany(mappedBy = "fkEntidade")
    private List<FinDocumento> finDocumentoList;
    @JoinColumn(name = "fk_rubrica", referencedColumnName = "pk_rubrica")
    @ManyToOne
    private CtRubrica fkRubrica;
    @JoinColumn(name = "fk_endereco", referencedColumnName = "pk_id_endereco")
    @ManyToOne
    private GrlEndereco fkEndereco;
    @JoinColumn(name = "fk_tipo_entidade", referencedColumnName = "pk_tipo_entidade")
    @ManyToOne
    private GrlTipoEntidade fkTipoEntidade;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkGrlEntidade")
    private List<FinContratoCompra> finContratoCompraList;
    @OneToMany(mappedBy = "fkEntidade")
    private List<CtAccount> ctAccountList;
    @OneToMany(mappedBy = "fkEntidade")
    private List<FinContaCorrente> finContaCorrenteList;
    @OneToMany(mappedBy = "fkCliente")
    private List<FinContasReceber> finContasReceberList;

    public GrlEntidade()
    {
    }

    public GrlEntidade(Integer pkEntidade)
    {
        this.pkEntidade = pkEntidade;
    }

    public GrlEntidade(Integer pkEntidade, String nome)
    {
        this.pkEntidade = pkEntidade;
        this.nome = nome;
    }

    public Integer getPkEntidade()
    {
        return pkEntidade;
    }

    public void setPkEntidade(Integer pkEntidade)
    {
        this.pkEntidade = pkEntidade;
    }

    public String getNome()
    {
        return nome;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getTelefone()
    {
        return telefone;
    }

    public void setTelefone(String telefone)
    {
        this.telefone = telefone;
    }

    public Integer getFkUtilizador()
    {
        return fkUtilizador;
    }

    public void setFkUtilizador(Integer fkUtilizador)
    {
        this.fkUtilizador = fkUtilizador;
    }

    public Boolean getEstado()
    {
        return estado;
    }

    public void setEstado(Boolean estado)
    {
        this.estado = estado;
    }

    public String getNifEntidade()
    {
        return nifEntidade;
    }

    public void setNifEntidade(String nifEntidade)
    {
        this.nifEntidade = nifEntidade;
    }

    public List<FinContasPagar> getFinContasPagarList()
    {
        return finContasPagarList;
    }

    public void setFinContasPagarList(List<FinContasPagar> finContasPagarList)
    {
        this.finContasPagarList = finContasPagarList;
    }

    public List<FinDocumento> getFinDocumentoList()
    {
        return finDocumentoList;
    }

    public void setFinDocumentoList(List<FinDocumento> finDocumentoList)
    {
        this.finDocumentoList = finDocumentoList;
    }

    public CtRubrica getFkRubrica()
    {
        return fkRubrica;
    }

    public void setFkRubrica(CtRubrica fkRubrica)
    {
        this.fkRubrica = fkRubrica;
    }

    public GrlEndereco getFkEndereco()
    {
        return fkEndereco;
    }

    public void setFkEndereco(GrlEndereco fkEndereco)
    {
        this.fkEndereco = fkEndereco;
    }

    public GrlTipoEntidade getFkTipoEntidade()
    {
        return fkTipoEntidade;
    }

    public void setFkTipoEntidade(GrlTipoEntidade fkTipoEntidade)
    {
        this.fkTipoEntidade = fkTipoEntidade;
    }

    public List<FinContratoCompra> getFinContratoCompraList()
    {
        return finContratoCompraList;
    }

    public void setFinContratoCompraList(List<FinContratoCompra> finContratoCompraList)
    {
        this.finContratoCompraList = finContratoCompraList;
    }

    public List<CtAccount> getCtAccountList()
    {
        return ctAccountList;
    }

    public void setCtAccountList(List<CtAccount> ctAccountList)
    {
        this.ctAccountList = ctAccountList;
    }

    public List<FinContaCorrente> getFinContaCorrenteList()
    {
        return finContaCorrenteList;
    }

    public void setFinContaCorrenteList(List<FinContaCorrente> finContaCorrenteList)
    {
        this.finContaCorrenteList = finContaCorrenteList;
    }

    public List<FinContasReceber> getFinContasReceberList()
    {
        return finContasReceberList;
    }

    public void setFinContasReceberList(List<FinContasReceber> finContasReceberList)
    {
        this.finContasReceberList = finContasReceberList;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (pkEntidade != null ? pkEntidade.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GrlEntidade))
        {
            return false;
        }
        GrlEntidade other = (GrlEntidade) object;
        if ((this.pkEntidade == null && other.pkEntidade != null) || (this.pkEntidade != null && !this.pkEntidade.equals(other.pkEntidade)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.GrlEntidade[ pkEntidade=" + pkEntidade + " ]";
    }
    
}
