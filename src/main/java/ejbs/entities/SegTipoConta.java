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
@Table(name = "seg_tipo_conta", catalog = "sig_ucan_db", schema = "public")
@NamedQueries(
{
    @NamedQuery(name = "SegTipoConta.findAll", query = "SELECT s FROM SegTipoConta s"),
    @NamedQuery(name = "SegTipoConta.findByPkSegTipoConta", query = "SELECT s FROM SegTipoConta s WHERE s.pkSegTipoConta = :pkSegTipoConta"),
    @NamedQuery(name = "SegTipoConta.findByNome", query = "SELECT s FROM SegTipoConta s WHERE s.nome = :nome")
})
public class SegTipoConta implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "pk_seg_tipo_conta", nullable = false)
    private Integer pkSegTipoConta;
    @Size(max = 2147483647)
    @Column(length = 2147483647)
    private String nome;
    @OneToMany(mappedBy = "fkTipoConta")
    private List<SegConta> segContaList;

    public SegTipoConta()
    {
    }

    public SegTipoConta(Integer pkSegTipoConta)
    {
        this.pkSegTipoConta = pkSegTipoConta;
    }

    public Integer getPkSegTipoConta()
    {
        return pkSegTipoConta;
    }

    public void setPkSegTipoConta(Integer pkSegTipoConta)
    {
        this.pkSegTipoConta = pkSegTipoConta;
    }

    public String getNome()
    {
        return nome;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    public List<SegConta> getSegContaList()
    {
        return segContaList;
    }

    public void setSegContaList(List<SegConta> segContaList)
    {
        this.segContaList = segContaList;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (pkSegTipoConta != null ? pkSegTipoConta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SegTipoConta))
        {
            return false;
        }
        SegTipoConta other = (SegTipoConta) object;
        if ((this.pkSegTipoConta == null && other.pkSegTipoConta != null) || (this.pkSegTipoConta != null && !this.pkSegTipoConta.equals(other.pkSegTipoConta)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.SegTipoConta[ pkSegTipoConta=" + pkSegTipoConta + " ]";
    }
    
}
