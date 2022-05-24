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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author smakambo
 */
@Entity
@Table(name = "rh_configuracoes")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "RhConfiguracoes.findAll", query = "SELECT r FROM RhConfiguracoes r"),
    @NamedQuery(name = "RhConfiguracoes.findByPkConfiguracoes", query = "SELECT r FROM RhConfiguracoes r WHERE r.pkConfiguracoes = :pkConfiguracoes")
})
public class RhConfiguracoes implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_configuracoes")
    private Integer pkConfiguracoes;
    @JoinColumn(name = "fk_estrutura_logica", referencedColumnName = "pk_estrutura_logica")
    @ManyToOne
    private EstruturaLogica fkEstruturaLogica;
    @JoinColumn(name = "fk_especialidade", referencedColumnName = "pk_especialidade")
    @ManyToOne
    private RhEspecialidade fkEspecialidade;
    @JoinColumn(name = "fk_funcao", referencedColumnName = "pk_funcao")
    @ManyToOne
    private RhFuncao fkFuncao;
    @JoinColumn(name = "fk_nivel_academico", referencedColumnName = "pk_nivel_academico")
    @ManyToOne
    private RhNivelAcademico fkNivelAcademico;
    @JoinColumn(name = "fk_tipo_funcionario", referencedColumnName = "pk_tipo_funcionario")
    @ManyToOne
    private RhTipoFuncionario fkTipoFuncionario;

    public RhConfiguracoes()
    {
    }

    public RhConfiguracoes(Integer pkConfiguracoes)
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

    public EstruturaLogica getFkEstruturaLogica()
    {
        return fkEstruturaLogica;
    }

    public void setFkEstruturaLogica(EstruturaLogica fkEstruturaLogica)
    {
        this.fkEstruturaLogica = fkEstruturaLogica;
    }

    public RhEspecialidade getFkEspecialidade()
    {
        return fkEspecialidade;
    }

    public void setFkEspecialidade(RhEspecialidade fkEspecialidade)
    {
        this.fkEspecialidade = fkEspecialidade;
    }

    public RhFuncao getFkFuncao()
    {
        return fkFuncao;
    }

    public void setFkFuncao(RhFuncao fkFuncao)
    {
        this.fkFuncao = fkFuncao;
    }

    public RhNivelAcademico getFkNivelAcademico()
    {
        return fkNivelAcademico;
    }

    public void setFkNivelAcademico(RhNivelAcademico fkNivelAcademico)
    {
        this.fkNivelAcademico = fkNivelAcademico;
    }

    public RhTipoFuncionario getFkTipoFuncionario()
    {
        return fkTipoFuncionario;
    }

    public void setFkTipoFuncionario(RhTipoFuncionario fkTipoFuncionario)
    {
        this.fkTipoFuncionario = fkTipoFuncionario;
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
        if (!(object instanceof RhConfiguracoes))
        {
            return false;
        }
        RhConfiguracoes other = (RhConfiguracoes) object;
        if ((this.pkConfiguracoes == null && other.pkConfiguracoes != null) || (this.pkConfiguracoes != null && !this.pkConfiguracoes.equals(other.pkConfiguracoes)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.RhConfiguracoes[ pkConfiguracoes=" + pkConfiguracoes + " ]";
    }
    
}
