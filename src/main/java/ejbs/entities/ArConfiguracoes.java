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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author smakambo
 */
@Entity
@Table(name = "ar_configuracoes", catalog = "sig_ucan_db", schema = "public")
@NamedQueries(
{
    @NamedQuery(name = "ArConfiguracoes.findAll", query = "SELECT a FROM ArConfiguracoes a"),
    @NamedQuery(name = "ArConfiguracoes.findByPkConfiguracoes", query = "SELECT a FROM ArConfiguracoes a WHERE a.pkConfiguracoes = :pkConfiguracoes")
})
public class ArConfiguracoes implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_configuracoes", nullable = false)
    private Integer pkConfiguracoes;
    @JoinColumn(name = "fk_ano_curricular", referencedColumnName = "pk_ano_curricular")
    @ManyToOne
    private ArAnoCurricular fkAnoCurricular;
    @JoinColumn(name = "fk_ano_lectivo", referencedColumnName = "pk_ano_lectivo")
    @ManyToOne
    private ArAnoLectivo fkAnoLectivo;
    @JoinColumn(name = "fk_periodo", referencedColumnName = "pk_periodo")
    @ManyToOne
    private ArPeriodo fkPeriodo;
    @JoinColumn(name = "fk_tipo_documento", referencedColumnName = "pk_tipo_documento")
    @ManyToOne
    private ArTipoDocumento fkTipoDocumento;
    @JoinColumn(name = "fk_turma", referencedColumnName = "pk_turma")
    @ManyToOne
    private ArTurma fkTurma;

    public ArConfiguracoes()
    {
    }

    public ArConfiguracoes(Integer pkConfiguracoes)
    {
        this.pkConfiguracoes = pkConfiguracoes;
    }

    public Integer getPkConfiguracoes()
    {
        return pkConfiguracoes;
    }

    public void setPkConfiguracoes(Integer pkConfiguracoes)
    {
        this.pkConfiguracoes = pkConfiguracoes;
    }

    public ArAnoCurricular getFkAnoCurricular()
    {
        return fkAnoCurricular;
    }

    public void setFkAnoCurricular(ArAnoCurricular fkAnoCurricular)
    {
        this.fkAnoCurricular = fkAnoCurricular;
    }

    public ArAnoLectivo getFkAnoLectivo()
    {
        return fkAnoLectivo;
    }

    public void setFkAnoLectivo(ArAnoLectivo fkAnoLectivo)
    {
        this.fkAnoLectivo = fkAnoLectivo;
    }

    public ArPeriodo getFkPeriodo()
    {
        return fkPeriodo;
    }

    public void setFkPeriodo(ArPeriodo fkPeriodo)
    {
        this.fkPeriodo = fkPeriodo;
    }

    public ArTipoDocumento getFkTipoDocumento()
    {
        return fkTipoDocumento;
    }

    public void setFkTipoDocumento(ArTipoDocumento fkTipoDocumento)
    {
        this.fkTipoDocumento = fkTipoDocumento;
    }

    public ArTurma getFkTurma()
    {
        return fkTurma;
    }

    public void setFkTurma(ArTurma fkTurma)
    {
        this.fkTurma = fkTurma;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (pkConfiguracoes != null ? pkConfiguracoes.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ArConfiguracoes))
        {
            return false;
        }
        ArConfiguracoes other = (ArConfiguracoes) object;
        if ((this.pkConfiguracoes == null && other.pkConfiguracoes != null) || (this.pkConfiguracoes != null && !this.pkConfiguracoes.equals(other.pkConfiguracoes)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.ArConfiguracoes[ pkConfiguracoes=" + pkConfiguracoes + " ]";
    }
    
}
