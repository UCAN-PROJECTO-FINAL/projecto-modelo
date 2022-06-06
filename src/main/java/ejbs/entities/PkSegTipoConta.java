/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author smakambo
 */
@Entity
@Table(name = "pk_seg_tipo_conta", catalog = "sig_ucan_db", schema = "public")
@NamedQueries(
{
    @NamedQuery(name = "PkSegTipoConta.findAll", query = "SELECT p FROM PkSegTipoConta p"),
    @NamedQuery(name = "PkSegTipoConta.findBySegTipoConta", query = "SELECT p FROM PkSegTipoConta p WHERE p.segTipoConta = :segTipoConta"),
    @NamedQuery(name = "PkSegTipoConta.findByNome", query = "SELECT p FROM PkSegTipoConta p WHERE p.nome = :nome")
})
public class PkSegTipoConta implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "seg_tipo_conta", nullable = false)
    private Integer segTipoConta;
    @Size(max = 2147483647)
    @Column(length = 2147483647)
    private String nome;

    public PkSegTipoConta()
    {
    }

    public PkSegTipoConta(Integer segTipoConta)
    {
        this.segTipoConta = segTipoConta;
    }

    public Integer getSegTipoConta()
    {
        return segTipoConta;
    }

    public void setSegTipoConta(Integer segTipoConta)
    {
        this.segTipoConta = segTipoConta;
    }

    public String getNome()
    {
        return nome;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (segTipoConta != null ? segTipoConta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PkSegTipoConta))
        {
            return false;
        }
        PkSegTipoConta other = (PkSegTipoConta) object;
        if ((this.segTipoConta == null && other.segTipoConta != null) || (this.segTipoConta != null && !this.segTipoConta.equals(other.segTipoConta)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.PkSegTipoConta[ segTipoConta=" + segTipoConta + " ]";
    }
    
}
