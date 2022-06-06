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
import javax.validation.constraints.Size;

/**
 *
 * @author smakambo
 */
@Entity
@Table(name = "ct_account", catalog = "sig_ucan_db", schema = "public")
@NamedQueries(
{
    @NamedQuery(name = "CtAccount.findAll", query = "SELECT c FROM CtAccount c"),
    @NamedQuery(name = "CtAccount.findByPkAccount", query = "SELECT c FROM CtAccount c WHERE c.pkAccount = :pkAccount"),
    @NamedQuery(name = "CtAccount.findByDescricaoAccount", query = "SELECT c FROM CtAccount c WHERE c.descricaoAccount = :descricaoAccount"),
    @NamedQuery(name = "CtAccount.findByNumeroAccount", query = "SELECT c FROM CtAccount c WHERE c.numeroAccount = :numeroAccount"),
    @NamedQuery(name = "CtAccount.findByNumberAccount", query = "SELECT c FROM CtAccount c WHERE c.numberAccount = :numberAccount"),
    @NamedQuery(name = "CtAccount.findByDataRegistro", query = "SELECT c FROM CtAccount c WHERE c.dataRegistro = :dataRegistro"),
    @NamedQuery(name = "CtAccount.findByStateAccount", query = "SELECT c FROM CtAccount c WHERE c.stateAccount = :stateAccount")
})
public class CtAccount implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_account", nullable = false)
    private Integer pkAccount;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "descricao_account", nullable = false, length = 2147483647)
    private String descricaoAccount;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "numero_account", nullable = false, length = 2147483647)
    private String numeroAccount;
    @Basic(optional = false)
    @NotNull
    @Column(name = "number_account", nullable = false)
    private int numberAccount;
    @Basic(optional = false)
    @NotNull
    @Column(name = "data_registro", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataRegistro;
    @Column(name = "state_account")
    private Boolean stateAccount;
    @OneToMany(mappedBy = "fkAccount")
    private List<CtBalancet> ctBalancetList;
    @JoinColumn(name = "fk_rubrica", referencedColumnName = "pk_rubrica")
    @ManyToOne
    private CtRubrica fkRubrica;
    @JoinColumn(name = "fk_entidade", referencedColumnName = "pk_entidade")
    @ManyToOne
    private GrlEntidade fkEntidade;
    @JoinColumn(name = "fk_utilizador", referencedColumnName = "pk_seg_conta")
    @ManyToOne
    private SegConta fkUtilizador;

    public CtAccount()
    {
    }

    public CtAccount(Integer pkAccount)
    {
        this.pkAccount = pkAccount;
    }

    public CtAccount(Integer pkAccount, String descricaoAccount, String numeroAccount, int numberAccount, Date dataRegistro)
    {
        this.pkAccount = pkAccount;
        this.descricaoAccount = descricaoAccount;
        this.numeroAccount = numeroAccount;
        this.numberAccount = numberAccount;
        this.dataRegistro = dataRegistro;
    }

    public Integer getPkAccount()
    {
        return pkAccount;
    }

    public void setPkAccount(Integer pkAccount)
    {
        this.pkAccount = pkAccount;
    }

    public String getDescricaoAccount()
    {
        return descricaoAccount;
    }

    public void setDescricaoAccount(String descricaoAccount)
    {
        this.descricaoAccount = descricaoAccount;
    }

    public String getNumeroAccount()
    {
        return numeroAccount;
    }

    public void setNumeroAccount(String numeroAccount)
    {
        this.numeroAccount = numeroAccount;
    }

    public int getNumberAccount()
    {
        return numberAccount;
    }

    public void setNumberAccount(int numberAccount)
    {
        this.numberAccount = numberAccount;
    }

    public Date getDataRegistro()
    {
        return dataRegistro;
    }

    public void setDataRegistro(Date dataRegistro)
    {
        this.dataRegistro = dataRegistro;
    }

    public Boolean getStateAccount()
    {
        return stateAccount;
    }

    public void setStateAccount(Boolean stateAccount)
    {
        this.stateAccount = stateAccount;
    }

    public List<CtBalancet> getCtBalancetList()
    {
        return ctBalancetList;
    }

    public void setCtBalancetList(List<CtBalancet> ctBalancetList)
    {
        this.ctBalancetList = ctBalancetList;
    }

    public CtRubrica getFkRubrica()
    {
        return fkRubrica;
    }

    public void setFkRubrica(CtRubrica fkRubrica)
    {
        this.fkRubrica = fkRubrica;
    }

    public GrlEntidade getFkEntidade()
    {
        return fkEntidade;
    }

    public void setFkEntidade(GrlEntidade fkEntidade)
    {
        this.fkEntidade = fkEntidade;
    }

    public SegConta getFkUtilizador()
    {
        return fkUtilizador;
    }

    public void setFkUtilizador(SegConta fkUtilizador)
    {
        this.fkUtilizador = fkUtilizador;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (pkAccount != null ? pkAccount.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CtAccount))
        {
            return false;
        }
        CtAccount other = (CtAccount) object;
        if ((this.pkAccount == null && other.pkAccount != null) || (this.pkAccount != null && !this.pkAccount.equals(other.pkAccount)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.CtAccount[ pkAccount=" + pkAccount + " ]";
    }
    
}
