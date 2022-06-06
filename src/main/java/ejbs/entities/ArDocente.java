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
@Table(name = "ar_docente", catalog = "sig_ucan_db", schema = "public")
@NamedQueries(
{
    @NamedQuery(name = "ArDocente.findAll", query = "SELECT a FROM ArDocente a"),
    @NamedQuery(name = "ArDocente.findByPkDocente", query = "SELECT a FROM ArDocente a WHERE a.pkDocente = :pkDocente"),
    @NamedQuery(name = "ArDocente.findByNomeCompleto", query = "SELECT a FROM ArDocente a WHERE a.nomeCompleto = :nomeCompleto")
})
public class ArDocente implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_docente", nullable = false)
    private Integer pkDocente;
    @Size(max = 2147483647)
    @Column(name = "nome_completo", length = 2147483647)
    private String nomeCompleto;
    @OneToMany(mappedBy = "fkDocente")
    private List<ArArquivoMorto> arArquivoMortoList;
    @OneToMany(mappedBy = "fkDocente")
    private List<ArCadeira> arCadeiraList;

    public ArDocente()
    {
    }

    public ArDocente(Integer pkDocente)
    {
        this.pkDocente = pkDocente;
    }

    public Integer getPkDocente()
    {
        return pkDocente;
    }

    public void setPkDocente(Integer pkDocente)
    {
        this.pkDocente = pkDocente;
    }

    public String getNomeCompleto()
    {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto)
    {
        this.nomeCompleto = nomeCompleto;
    }

    public List<ArArquivoMorto> getArArquivoMortoList()
    {
        return arArquivoMortoList;
    }

    public void setArArquivoMortoList(List<ArArquivoMorto> arArquivoMortoList)
    {
        this.arArquivoMortoList = arArquivoMortoList;
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
        hash += (pkDocente != null ? pkDocente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ArDocente))
        {
            return false;
        }
        ArDocente other = (ArDocente) object;
        if ((this.pkDocente == null && other.pkDocente != null) || (this.pkDocente != null && !this.pkDocente.equals(other.pkDocente)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.ArDocente[ pkDocente=" + pkDocente + " ]";
    }
    
}
