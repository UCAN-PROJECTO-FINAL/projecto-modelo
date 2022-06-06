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

/**
 *
 * @author smakambo
 */
@Entity
@Table(name = "ar_curso", catalog = "sig_ucan_db", schema = "public")
@NamedQueries(
{
    @NamedQuery(name = "ArCurso.findAll", query = "SELECT a FROM ArCurso a"),
    @NamedQuery(name = "ArCurso.findByPkCurso", query = "SELECT a FROM ArCurso a WHERE a.pkCurso = :pkCurso"),
    @NamedQuery(name = "ArCurso.findByDescricao", query = "SELECT a FROM ArCurso a WHERE a.descricao = :descricao")
})
public class ArCurso implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_curso", nullable = false)
    private Integer pkCurso;
    @Size(max = 2147483647)
    @Column(length = 2147483647)
    private String descricao;
    @JoinColumn(name = "fk_faculdade", referencedColumnName = "pk_faculdade")
    @ManyToOne
    private ArFaculdade fkFaculdade;
    @OneToMany(mappedBy = "fkCurso")
    private List<ArCadeira> arCadeiraList;

    public ArCurso()
    {
    }

    public ArCurso(Integer pkCurso)
    {
        this.pkCurso = pkCurso;
    }

    public Integer getPkCurso()
    {
        return pkCurso;
    }

    public void setPkCurso(Integer pkCurso)
    {
        this.pkCurso = pkCurso;
    }

    public String getDescricao()
    {
        return descricao;
    }

    public void setDescricao(String descricao)
    {
        this.descricao = descricao;
    }

    public ArFaculdade getFkFaculdade()
    {
        return fkFaculdade;
    }

    public void setFkFaculdade(ArFaculdade fkFaculdade)
    {
        this.fkFaculdade = fkFaculdade;
    }

    public List<ArCadeira> getArCadeiraList()
    {
        return arCadeiraList;
    }

    public void setArCadeiraList(List<ArCadeira> arCadeiraList)
    {
        this.arCadeiraList = arCadeiraList;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (pkCurso != null ? pkCurso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ArCurso))
        {
            return false;
        }
        ArCurso other = (ArCurso) object;
        if ((this.pkCurso == null && other.pkCurso != null) || (this.pkCurso != null && !this.pkCurso.equals(other.pkCurso)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.ArCurso[ pkCurso=" + pkCurso + " ]";
    }
    
}
