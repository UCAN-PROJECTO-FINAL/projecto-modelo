/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.entities;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author smakambo
 */
@Entity
@Table(name = "rh_funcionario", catalog = "sig_ucan_db", schema = "public")
@NamedQueries(
{
    @NamedQuery(name = "RhFuncionario.findAll", query = "SELECT r FROM RhFuncionario r"),
    @NamedQuery(name = "RhFuncionario.findByPkFuncionario", query = "SELECT r FROM RhFuncionario r WHERE r.pkFuncionario = :pkFuncionario"),
    @NamedQuery(name = "RhFuncionario.findByNumeroSegurancaSocial", query = "SELECT r FROM RhFuncionario r WHERE r.numeroSegurancaSocial = :numeroSegurancaSocial"),
    @NamedQuery(name = "RhFuncionario.findByDataCadastro", query = "SELECT r FROM RhFuncionario r WHERE r.dataCadastro = :dataCadastro")
})
public class RhFuncionario implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_funcionario", nullable = false)
    private Integer pkFuncionario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "numero_seguranca_social", nullable = false, length = 2147483647)
    private String numeroSegurancaSocial;
    @Basic(optional = false)
    @NotNull
    @Column(name = "data_cadastro", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataCadastro;
    @JoinColumn(name = "fk_estrutura_logica_fisica", referencedColumnName = "pk_estrutura_logica_fisica")
    @ManyToOne
    private EstruturaLogicaFisica fkEstruturaLogicaFisica;
    @JoinColumn(name = "fk_pessoa", referencedColumnName = "pk_grl_pessoa")
    @ManyToOne
    private GrlPessoa fkPessoa;
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

    public RhFuncionario()
    {
    }

    public RhFuncionario(Integer pkFuncionario)
    {
        this.pkFuncionario = pkFuncionario;
    }

    public RhFuncionario(Integer pkFuncionario, String numeroSegurancaSocial, Date dataCadastro)
    {
        this.pkFuncionario = pkFuncionario;
        this.numeroSegurancaSocial = numeroSegurancaSocial;
        this.dataCadastro = dataCadastro;
    }

    public Integer getPkFuncionario()
    {
        return pkFuncionario;
    }

    public void setPkFuncionario(Integer pkFuncionario)
    {
        this.pkFuncionario = pkFuncionario;
    }

    public String getNumeroSegurancaSocial()
    {
        return numeroSegurancaSocial;
    }

    public void setNumeroSegurancaSocial(String numeroSegurancaSocial)
    {
        this.numeroSegurancaSocial = numeroSegurancaSocial;
    }

    public Date getDataCadastro()
    {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro)
    {
        this.dataCadastro = dataCadastro;
    }

    public EstruturaLogicaFisica getFkEstruturaLogicaFisica()
    {
        return fkEstruturaLogicaFisica;
    }

    public void setFkEstruturaLogicaFisica(EstruturaLogicaFisica fkEstruturaLogicaFisica)
    {
        this.fkEstruturaLogicaFisica = fkEstruturaLogicaFisica;
    }

    public GrlPessoa getFkPessoa()
    {
        return fkPessoa;
    }

    public void setFkPessoa(GrlPessoa fkPessoa)
    {
        this.fkPessoa = fkPessoa;
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
        hash += (pkFuncionario != null ? pkFuncionario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RhFuncionario))
        {
            return false;
        }
        RhFuncionario other = (RhFuncionario) object;
        if ((this.pkFuncionario == null && other.pkFuncionario != null) || (this.pkFuncionario != null && !this.pkFuncionario.equals(other.pkFuncionario)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.RhFuncionario[ pkFuncionario=" + pkFuncionario + " ]";
    }
    
}
