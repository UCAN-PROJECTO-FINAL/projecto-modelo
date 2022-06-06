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
@Table(name = "ar_faculdade", catalog = "sig_ucan_db", schema = "public")
@NamedQueries(
{
    @NamedQuery(name = "ArFaculdade.findAll", query = "SELECT a FROM ArFaculdade a"),
    @NamedQuery(name = "ArFaculdade.findByPkFaculdade", query = "SELECT a FROM ArFaculdade a WHERE a.pkFaculdade = :pkFaculdade"),
    @NamedQuery(name = "ArFaculdade.findByDescricao", query = "SELECT a FROM ArFaculdade a WHERE a.descricao = :descricao")
})
public class ArFaculdade implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_faculdade", nullable = false)
    private Integer pkFaculdade;
    @Size(max = 2147483647)
    @Column(length = 2147483647)
    private String descricao;
    @OneToMany(mappedBy = "fkFaculdade")
    private List<ArCurso> arCursoList;

    public ArFaculdade()
    {
    }

    public ArFaculdade(Integer pkFaculdade)
    {
        this.pkFaculdade = pkFaculdade;
    }

    public Integer getPkFaculdade()
    {
        return pkFaculdade;
    }

    public void setPkFaculdade(Integer pkFaculdade)
    {
        this.pkFaculdade = pkFaculdade;
    }

    public String getDescricao()
    {
        return descricao;
    }

    public void setDescricao(String descricao)
    {
        this.descricao = descricao;
    }

    public List<ArCurso> getArCursoList()
    {
        return arCursoList;
    }

    public void setArCursoList(List<ArCurso> arCursoList)
    {
        this.arCursoList = arCursoList;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (pkFaculdade != null ? pkFaculdade.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ArFaculdade))
        {
            return false;
        }
        ArFaculdade other = (ArFaculdade) object;
        if ((this.pkFaculdade == null && other.pkFaculdade != null) || (this.pkFaculdade != null && !this.pkFaculdade.equals(other.pkFaculdade)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.ArFaculdade[ pkFaculdade=" + pkFaculdade + " ]";
    }
    
}
