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
@Table(name = "ar_cadeira", catalog = "sig_ucan_db", schema = "public")
@NamedQueries(
{
    @NamedQuery(name = "ArCadeira.findAll", query = "SELECT a FROM ArCadeira a"),
    @NamedQuery(name = "ArCadeira.findByPkCadeira", query = "SELECT a FROM ArCadeira a WHERE a.pkCadeira = :pkCadeira"),
    @NamedQuery(name = "ArCadeira.findByDescricao", query = "SELECT a FROM ArCadeira a WHERE a.descricao = :descricao")
})
public class ArCadeira implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_cadeira", nullable = false)
    private Integer pkCadeira;
    @Size(max = 2147483647)
    @Column(length = 2147483647)
    private String descricao;
    @OneToMany(mappedBy = "fkCadeira")
    private List<ArArquivoMorto> arArquivoMortoList;
    @JoinColumn(name = "fk_curso", referencedColumnName = "pk_curso")
    @ManyToOne
    private ArCurso fkCurso;
    @JoinColumn(name = "fk_docente", referencedColumnName = "pk_docente")
    @ManyToOne
    private ArDocente fkDocente;

    public ArCadeira()
    {
    }

    public ArCadeira(Integer pkCadeira)
    {
        this.pkCadeira = pkCadeira;
    }

    public Integer getPkCadeira()
    {
        return pkCadeira;
    }

    public void setPkCadeira(Integer pkCadeira)
    {
        this.pkCadeira = pkCadeira;
    }

    public String getDescricao()
    {
        return descricao;
    }

    public void setDescricao(String descricao)
    {
        this.descricao = descricao;
    }

    public List<ArArquivoMorto> getArArquivoMortoList()
    {
        return arArquivoMortoList;
    }

    public void setArArquivoMortoList(List<ArArquivoMorto> arArquivoMortoList)
    {
        this.arArquivoMortoList = arArquivoMortoList;
    }

    public ArCurso getFkCurso()
    {
        return fkCurso;
    }

    public void setFkCurso(ArCurso fkCurso)
    {
        this.fkCurso = fkCurso;
    }

    public ArDocente getFkDocente()
    {
        return fkDocente;
    }

    public void setFkDocente(ArDocente fkDocente)
    {
        this.fkDocente = fkDocente;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (pkCadeira != null ? pkCadeira.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ArCadeira))
        {
            return false;
        }
        ArCadeira other = (ArCadeira) object;
        if ((this.pkCadeira == null && other.pkCadeira != null) || (this.pkCadeira != null && !this.pkCadeira.equals(other.pkCadeira)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.ArCadeira[ pkCadeira=" + pkCadeira + " ]";
    }
    
}
