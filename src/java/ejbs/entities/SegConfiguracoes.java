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
@Table(name = "seg_configuracoes")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "SegConfiguracoes.findAll", query = "SELECT s FROM SegConfiguracoes s"),
    @NamedQuery(name = "SegConfiguracoes.findByPkSegConfiguracao", query = "SELECT s FROM SegConfiguracoes s WHERE s.pkSegConfiguracao = :pkSegConfiguracao")
})
public class SegConfiguracoes implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_seg_configuracao")
    private Integer pkSegConfiguracao;
    @JoinColumn(name = "fk_seg_perfil", referencedColumnName = "pk_seg_perfil")
    @ManyToOne
    private SegPerfil fkSegPerfil;
    @JoinColumn(name = "fk_seg_tipo_funcionalidade", referencedColumnName = "pk_seg_tipo_funcionalidade")
    @ManyToOne
    private SegTipoFuncionalidade fkSegTipoFuncionalidade;

    public SegConfiguracoes()
    {
    }

    public SegConfiguracoes(Integer pkSegConfiguracao)
    {
        this.pkSegConfiguracao = pkSegConfiguracao;
    }

    public Integer getPkSegConfiguracao()
    {
        return pkSegConfiguracao;
    }

    public void setPkSegConfiguracao(Integer pkSegConfiguracao)
    {
        this.pkSegConfiguracao = pkSegConfiguracao;
    }

    public SegPerfil getFkSegPerfil()
    {
        return fkSegPerfil;
    }

    public void setFkSegPerfil(SegPerfil fkSegPerfil)
    {
        this.fkSegPerfil = fkSegPerfil;
    }

    public SegTipoFuncionalidade getFkSegTipoFuncionalidade()
    {
        return fkSegTipoFuncionalidade;
    }

    public void setFkSegTipoFuncionalidade(SegTipoFuncionalidade fkSegTipoFuncionalidade)
    {
        this.fkSegTipoFuncionalidade = fkSegTipoFuncionalidade;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (pkSegConfiguracao != null ? pkSegConfiguracao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SegConfiguracoes))
        {
            return false;
        }
        SegConfiguracoes other = (SegConfiguracoes) object;
        if ((this.pkSegConfiguracao == null && other.pkSegConfiguracao != null) || (this.pkSegConfiguracao != null && !this.pkSegConfiguracao.equals(other.pkSegConfiguracao)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.SegConfiguracoes[ pkSegConfiguracao=" + pkSegConfiguracao + " ]";
    }
    
}
