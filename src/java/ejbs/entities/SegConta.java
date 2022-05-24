/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author smakambo
 */
@Entity
@Table(name = "seg_conta")
@XmlRootElement
@NamedQueries(
{
    @NamedQuery(name = "SegConta.findAll", query = "SELECT s FROM SegConta s"),
    @NamedQuery(name = "SegConta.findByPkSegConta", query = "SELECT s FROM SegConta s WHERE s.pkSegConta = :pkSegConta"),
    @NamedQuery(name = "SegConta.findByNomeUtilizador", query = "SELECT s FROM SegConta s WHERE s.nomeUtilizador = :nomeUtilizador"),
    @NamedQuery(name = "SegConta.findByPalavraPasse", query = "SELECT s FROM SegConta s WHERE s.palavraPasse = :palavraPasse"),
    @NamedQuery(name = "SegConta.findByDataCadastro", query = "SELECT s FROM SegConta s WHERE s.dataCadastro = :dataCadastro"),
    @NamedQuery(name = "SegConta.findByActivo", query = "SELECT s FROM SegConta s WHERE s.activo = :activo"),
    @NamedQuery(name = "SegConta.findByPrimeiroLoginConta", query = "SELECT s FROM SegConta s WHERE s.primeiroLoginConta = :primeiroLoginConta"),
    @NamedQuery(name = "SegConta.findByUltimoAcessoConta", query = "SELECT s FROM SegConta s WHERE s.ultimoAcessoConta = :ultimoAcessoConta"),
    @NamedQuery(name = "SegConta.findByTempoInactividade", query = "SELECT s FROM SegConta s WHERE s.tempoInactividade = :tempoInactividade"),
    @NamedQuery(name = "SegConta.findByMaxIdleTime", query = "SELECT s FROM SegConta s WHERE s.maxIdleTime = :maxIdleTime"),
    @NamedQuery(name = "SegConta.findByActivarTempoInactividade", query = "SELECT s FROM SegConta s WHERE s.activarTempoInactividade = :activarTempoInactividade")
})
public class SegConta implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_seg_conta")
    private Integer pkSegConta;
    @Size(max = 2147483647)
    @Column(name = "nome_utilizador")
    private String nomeUtilizador;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "palavra_passe")
    private String palavraPasse;
    @Basic(optional = false)
    @NotNull
    @Column(name = "data_cadastro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCadastro;
    @Column(name = "activo")
    private Boolean activo;
    @Column(name = "primeiro_login_conta")
    private Boolean primeiroLoginConta;
    @Column(name = "ultimo_acesso_conta")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ultimoAcessoConta;
    @Column(name = "tempo_inactividade")
    private Integer tempoInactividade;
    @Column(name = "max_idle_time")
    private Integer maxIdleTime;
    @Column(name = "activar_tempo_inactividade")
    private Boolean activarTempoInactividade;
    @OneToMany(mappedBy = "fkSegConta")
    private List<SegLogAcesso> segLogAcessoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkSegConta")
    private List<SegAuditoria> segAuditoriaList;
    @JoinColumn(name = "fk_seg_pessoa", referencedColumnName = "pk_grl_pessoa")
    @ManyToOne
    private GrlPessoa fkSegPessoa;
    @OneToMany(mappedBy = "fkSegConta")
    private List<SegConta> segContaList;
    @JoinColumn(name = "fk_seg_conta", referencedColumnName = "pk_seg_conta")
    @ManyToOne
    private SegConta fkSegConta;
    @JoinColumn(name = "fk_seg_pagina_arranque", referencedColumnName = "pk_seg_pagina_arranque")
    @ManyToOne
    private SegPaginaArranque fkSegPaginaArranque;
    @JoinColumn(name = "fk_seg_perfil", referencedColumnName = "pk_seg_perfil")
    @ManyToOne
    private SegPerfil fkSegPerfil;
    @JoinColumn(name = "fk_tipo_conta", referencedColumnName = "pk_seg_tipo_conta")
    @ManyToOne
    private SegTipoConta fkTipoConta;

    public SegConta()
    {
    }

    public SegConta(Integer pkSegConta)
    {
        this.pkSegConta = pkSegConta;
    }

    public SegConta(Integer pkSegConta, String palavraPasse, Date dataCadastro)
    {
        this.pkSegConta = pkSegConta;
        this.palavraPasse = palavraPasse;
        this.dataCadastro = dataCadastro;
    }

    public Integer getPkSegConta()
    {
        return pkSegConta;
    }

    public void setPkSegConta(Integer pkSegConta)
    {
        this.pkSegConta = pkSegConta;
    }

    public String getNomeUtilizador()
    {
        return nomeUtilizador;
    }

    public void setNomeUtilizador(String nomeUtilizador)
    {
        this.nomeUtilizador = nomeUtilizador;
    }

    public String getPalavraPasse()
    {
        return palavraPasse;
    }

    public void setPalavraPasse(String palavraPasse)
    {
        this.palavraPasse = palavraPasse;
    }

    public Date getDataCadastro()
    {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro)
    {
        this.dataCadastro = dataCadastro;
    }

    public Boolean getActivo()
    {
        return activo;
    }

    public void setActivo(Boolean activo)
    {
        this.activo = activo;
    }

    public Boolean getPrimeiroLoginConta()
    {
        return primeiroLoginConta;
    }

    public void setPrimeiroLoginConta(Boolean primeiroLoginConta)
    {
        this.primeiroLoginConta = primeiroLoginConta;
    }

    public Date getUltimoAcessoConta()
    {
        return ultimoAcessoConta;
    }

    public void setUltimoAcessoConta(Date ultimoAcessoConta)
    {
        this.ultimoAcessoConta = ultimoAcessoConta;
    }

    public Integer getTempoInactividade()
    {
        return tempoInactividade;
    }

    public void setTempoInactividade(Integer tempoInactividade)
    {
        this.tempoInactividade = tempoInactividade;
    }

    public Integer getMaxIdleTime()
    {
        return maxIdleTime;
    }

    public void setMaxIdleTime(Integer maxIdleTime)
    {
        this.maxIdleTime = maxIdleTime;
    }

    public Boolean getActivarTempoInactividade()
    {
        return activarTempoInactividade;
    }

    public void setActivarTempoInactividade(Boolean activarTempoInactividade)
    {
        this.activarTempoInactividade = activarTempoInactividade;
    }

    @XmlTransient
    public List<SegLogAcesso> getSegLogAcessoList()
    {
        return segLogAcessoList;
    }

    public void setSegLogAcessoList(List<SegLogAcesso> segLogAcessoList)
    {
        this.segLogAcessoList = segLogAcessoList;
    }

    @XmlTransient
    public List<SegAuditoria> getSegAuditoriaList()
    {
        return segAuditoriaList;
    }

    public void setSegAuditoriaList(List<SegAuditoria> segAuditoriaList)
    {
        this.segAuditoriaList = segAuditoriaList;
    }

    public GrlPessoa getFkSegPessoa()
    {
        return fkSegPessoa;
    }

    public void setFkSegPessoa(GrlPessoa fkSegPessoa)
    {
        this.fkSegPessoa = fkSegPessoa;
    }

    @XmlTransient
    public List<SegConta> getSegContaList()
    {
        return segContaList;
    }

    public void setSegContaList(List<SegConta> segContaList)
    {
        this.segContaList = segContaList;
    }

    public SegConta getFkSegConta()
    {
        return fkSegConta;
    }

    public void setFkSegConta(SegConta fkSegConta)
    {
        this.fkSegConta = fkSegConta;
    }

    public SegPaginaArranque getFkSegPaginaArranque()
    {
        return fkSegPaginaArranque;
    }

    public void setFkSegPaginaArranque(SegPaginaArranque fkSegPaginaArranque)
    {
        this.fkSegPaginaArranque = fkSegPaginaArranque;
    }

    public SegPerfil getFkSegPerfil()
    {
        return fkSegPerfil;
    }

    public void setFkSegPerfil(SegPerfil fkSegPerfil)
    {
        this.fkSegPerfil = fkSegPerfil;
    }

    public SegTipoConta getFkTipoConta()
    {
        return fkTipoConta;
    }

    public void setFkTipoConta(SegTipoConta fkTipoConta)
    {
        this.fkTipoConta = fkTipoConta;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (pkSegConta != null ? pkSegConta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SegConta))
        {
            return false;
        }
        SegConta other = (SegConta) object;
        if ((this.pkSegConta == null && other.pkSegConta != null) || (this.pkSegConta != null && !this.pkSegConta.equals(other.pkSegConta)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.SegConta[ pkSegConta=" + pkSegConta + " ]";
    }
    
}
