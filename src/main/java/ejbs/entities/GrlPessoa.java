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
import javax.persistence.Column;
import javax.persistence.Entity;
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

/**
 *
 * @author smakambo
 */
@Entity
@Table(name = "grl_pessoa", catalog = "sig_ucan_db", schema = "public")
@NamedQueries(
{
    @NamedQuery(name = "GrlPessoa.findAll", query = "SELECT g FROM GrlPessoa g"),
    @NamedQuery(name = "GrlPessoa.findByPkGrlPessoa", query = "SELECT g FROM GrlPessoa g WHERE g.pkGrlPessoa = :pkGrlPessoa"),
    @NamedQuery(name = "GrlPessoa.findByNome", query = "SELECT g FROM GrlPessoa g WHERE g.nome = :nome"),
    @NamedQuery(name = "GrlPessoa.findByPai", query = "SELECT g FROM GrlPessoa g WHERE g.pai = :pai"),
    @NamedQuery(name = "GrlPessoa.findByMae", query = "SELECT g FROM GrlPessoa g WHERE g.mae = :mae"),
    @NamedQuery(name = "GrlPessoa.findByNumeroIdentificacao", query = "SELECT g FROM GrlPessoa g WHERE g.numeroIdentificacao = :numeroIdentificacao"),
    @NamedQuery(name = "GrlPessoa.findByDataEmissao", query = "SELECT g FROM GrlPessoa g WHERE g.dataEmissao = :dataEmissao"),
    @NamedQuery(name = "GrlPessoa.findByPrazoValidade", query = "SELECT g FROM GrlPessoa g WHERE g.prazoValidade = :prazoValidade"),
    @NamedQuery(name = "GrlPessoa.findByFkNaturalidadeMunicipio", query = "SELECT g FROM GrlPessoa g WHERE g.fkNaturalidadeMunicipio = :fkNaturalidadeMunicipio"),
    @NamedQuery(name = "GrlPessoa.findByDataNascimento", query = "SELECT g FROM GrlPessoa g WHERE g.dataNascimento = :dataNascimento"),
    @NamedQuery(name = "GrlPessoa.findByEhNacional", query = "SELECT g FROM GrlPessoa g WHERE g.ehNacional = :ehNacional"),
    @NamedQuery(name = "GrlPessoa.findByNif", query = "SELECT g FROM GrlPessoa g WHERE g.nif = :nif"),
    @NamedQuery(name = "GrlPessoa.findByDataCadastro", query = "SELECT g FROM GrlPessoa g WHERE g.dataCadastro = :dataCadastro"),
    @NamedQuery(name = "GrlPessoa.findByVitalicio", query = "SELECT g FROM GrlPessoa g WHERE g.vitalicio = :vitalicio"),
    @NamedQuery(name = "GrlPessoa.findByTelefonePrincipal", query = "SELECT g FROM GrlPessoa g WHERE g.telefonePrincipal = :telefonePrincipal"),
    @NamedQuery(name = "GrlPessoa.findByTelefoneAlternativo", query = "SELECT g FROM GrlPessoa g WHERE g.telefoneAlternativo = :telefoneAlternativo"),
    @NamedQuery(name = "GrlPessoa.findByEmailPrincipal", query = "SELECT g FROM GrlPessoa g WHERE g.emailPrincipal = :emailPrincipal"),
    @NamedQuery(name = "GrlPessoa.findByEmailAlternativo", query = "SELECT g FROM GrlPessoa g WHERE g.emailAlternativo = :emailAlternativo"),
    @NamedQuery(name = "GrlPessoa.findByRuaCasa", query = "SELECT g FROM GrlPessoa g WHERE g.ruaCasa = :ruaCasa"),
    @NamedQuery(name = "GrlPessoa.findByComentarioSexo", query = "SELECT g FROM GrlPessoa g WHERE g.comentarioSexo = :comentarioSexo"),
    @NamedQuery(name = "GrlPessoa.findByComentarioDataNascimento", query = "SELECT g FROM GrlPessoa g WHERE g.comentarioDataNascimento = :comentarioDataNascimento"),
    @NamedQuery(name = "GrlPessoa.findByComentarioEmail", query = "SELECT g FROM GrlPessoa g WHERE g.comentarioEmail = :comentarioEmail"),
    @NamedQuery(name = "GrlPessoa.findByComentarioTelefone", query = "SELECT g FROM GrlPessoa g WHERE g.comentarioTelefone = :comentarioTelefone"),
    @NamedQuery(name = "GrlPessoa.findByComentarioNome", query = "SELECT g FROM GrlPessoa g WHERE g.comentarioNome = :comentarioNome"),
    @NamedQuery(name = "GrlPessoa.findByComentarioNumeroIdentificacao", query = "SELECT g FROM GrlPessoa g WHERE g.comentarioNumeroIdentificacao = :comentarioNumeroIdentificacao"),
    @NamedQuery(name = "GrlPessoa.findByOk", query = "SELECT g FROM GrlPessoa g WHERE g.ok = :ok")
})
public class GrlPessoa implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "pk_grl_pessoa", nullable = false)
    private Integer pkGrlPessoa;
    @Size(max = 2147483647)
    @Column(length = 2147483647)
    private String nome;
    @Size(max = 2147483647)
    @Column(length = 2147483647)
    private String pai;
    @Size(max = 2147483647)
    @Column(length = 2147483647)
    private String mae;
    @Size(max = 2147483647)
    @Column(name = "numero_identificacao", length = 2147483647)
    private String numeroIdentificacao;
    @Column(name = "data_emissao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataEmissao;
    @Column(name = "prazo_validade")
    @Temporal(TemporalType.TIMESTAMP)
    private Date prazoValidade;
    @Column(name = "fk_naturalidade_municipio")
    private Integer fkNaturalidadeMunicipio;
    @Column(name = "data_nascimento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataNascimento;
    @Column(name = "eh_nacional")
    private Boolean ehNacional;
    @Size(max = 2147483647)
    @Column(length = 2147483647)
    private String nif;
    @Column(name = "data_cadastro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCadastro;
    private Boolean vitalicio;
    @Size(max = 2147483647)
    @Column(name = "telefone_principal", length = 2147483647)
    private String telefonePrincipal;
    @Size(max = 2147483647)
    @Column(name = "telefone_alternativo", length = 2147483647)
    private String telefoneAlternativo;
    @Size(max = 2147483647)
    @Column(name = "email_principal", length = 2147483647)
    private String emailPrincipal;
    @Size(max = 2147483647)
    @Column(name = "email_alternativo", length = 2147483647)
    private String emailAlternativo;
    @Size(max = 2147483647)
    @Column(name = "rua_casa", length = 2147483647)
    private String ruaCasa;
    @Size(max = 2147483647)
    @Column(name = "comentario_sexo", length = 2147483647)
    private String comentarioSexo;
    @Size(max = 2147483647)
    @Column(name = "comentario_data_nascimento", length = 2147483647)
    private String comentarioDataNascimento;
    @Size(max = 2147483647)
    @Column(name = "comentario_email", length = 2147483647)
    private String comentarioEmail;
    @Size(max = 2147483647)
    @Column(name = "comentario_telefone", length = 2147483647)
    private String comentarioTelefone;
    @Size(max = 2147483647)
    @Column(name = "comentario_nome", length = 2147483647)
    private String comentarioNome;
    @Size(max = 2147483647)
    @Column(name = "comentario_numero_identificacao", length = 2147483647)
    private String comentarioNumeroIdentificacao;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private boolean ok;
    @OneToMany(mappedBy = "fkPessoa")
    private List<RhFuncionario> rhFuncionarioList;
    @JoinColumn(name = "fk_residencia_bairro", referencedColumnName = "pk_id_endereco")
    @ManyToOne
    private GrlEndereco fkResidenciaBairro;
    @JoinColumn(name = "fk_estado_civil", referencedColumnName = "pk_grl_estado_civil")
    @ManyToOne
    private GrlEstadoCivil fkEstadoCivil;
    @JoinColumn(name = "fk_sexo", referencedColumnName = "pk_grl_sexo")
    @ManyToOne
    private GrlSexo fkSexo;
    @OneToMany(mappedBy = "fkSegPessoa")
    private List<SegConta> segContaList;

    public GrlPessoa()
    {
    }

    public GrlPessoa(Integer pkGrlPessoa)
    {
        this.pkGrlPessoa = pkGrlPessoa;
    }

    public GrlPessoa(Integer pkGrlPessoa, boolean ok)
    {
        this.pkGrlPessoa = pkGrlPessoa;
        this.ok = ok;
    }

    public Integer getPkGrlPessoa()
    {
        return pkGrlPessoa;
    }

    public void setPkGrlPessoa(Integer pkGrlPessoa)
    {
        this.pkGrlPessoa = pkGrlPessoa;
    }

    public String getNome()
    {
        return nome;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    public String getPai()
    {
        return pai;
    }

    public void setPai(String pai)
    {
        this.pai = pai;
    }

    public String getMae()
    {
        return mae;
    }

    public void setMae(String mae)
    {
        this.mae = mae;
    }

    public String getNumeroIdentificacao()
    {
        return numeroIdentificacao;
    }

    public void setNumeroIdentificacao(String numeroIdentificacao)
    {
        this.numeroIdentificacao = numeroIdentificacao;
    }

    public Date getDataEmissao()
    {
        return dataEmissao;
    }

    public void setDataEmissao(Date dataEmissao)
    {
        this.dataEmissao = dataEmissao;
    }

    public Date getPrazoValidade()
    {
        return prazoValidade;
    }

    public void setPrazoValidade(Date prazoValidade)
    {
        this.prazoValidade = prazoValidade;
    }

    public Integer getFkNaturalidadeMunicipio()
    {
        return fkNaturalidadeMunicipio;
    }

    public void setFkNaturalidadeMunicipio(Integer fkNaturalidadeMunicipio)
    {
        this.fkNaturalidadeMunicipio = fkNaturalidadeMunicipio;
    }

    public Date getDataNascimento()
    {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento)
    {
        this.dataNascimento = dataNascimento;
    }

    public Boolean getEhNacional()
    {
        return ehNacional;
    }

    public void setEhNacional(Boolean ehNacional)
    {
        this.ehNacional = ehNacional;
    }

    public String getNif()
    {
        return nif;
    }

    public void setNif(String nif)
    {
        this.nif = nif;
    }

    public Date getDataCadastro()
    {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro)
    {
        this.dataCadastro = dataCadastro;
    }

    public Boolean getVitalicio()
    {
        return vitalicio;
    }

    public void setVitalicio(Boolean vitalicio)
    {
        this.vitalicio = vitalicio;
    }

    public String getTelefonePrincipal()
    {
        return telefonePrincipal;
    }

    public void setTelefonePrincipal(String telefonePrincipal)
    {
        this.telefonePrincipal = telefonePrincipal;
    }

    public String getTelefoneAlternativo()
    {
        return telefoneAlternativo;
    }

    public void setTelefoneAlternativo(String telefoneAlternativo)
    {
        this.telefoneAlternativo = telefoneAlternativo;
    }

    public String getEmailPrincipal()
    {
        return emailPrincipal;
    }

    public void setEmailPrincipal(String emailPrincipal)
    {
        this.emailPrincipal = emailPrincipal;
    }

    public String getEmailAlternativo()
    {
        return emailAlternativo;
    }

    public void setEmailAlternativo(String emailAlternativo)
    {
        this.emailAlternativo = emailAlternativo;
    }

    public String getRuaCasa()
    {
        return ruaCasa;
    }

    public void setRuaCasa(String ruaCasa)
    {
        this.ruaCasa = ruaCasa;
    }

    public String getComentarioSexo()
    {
        return comentarioSexo;
    }

    public void setComentarioSexo(String comentarioSexo)
    {
        this.comentarioSexo = comentarioSexo;
    }

    public String getComentarioDataNascimento()
    {
        return comentarioDataNascimento;
    }

    public void setComentarioDataNascimento(String comentarioDataNascimento)
    {
        this.comentarioDataNascimento = comentarioDataNascimento;
    }

    public String getComentarioEmail()
    {
        return comentarioEmail;
    }

    public void setComentarioEmail(String comentarioEmail)
    {
        this.comentarioEmail = comentarioEmail;
    }

    public String getComentarioTelefone()
    {
        return comentarioTelefone;
    }

    public void setComentarioTelefone(String comentarioTelefone)
    {
        this.comentarioTelefone = comentarioTelefone;
    }

    public String getComentarioNome()
    {
        return comentarioNome;
    }

    public void setComentarioNome(String comentarioNome)
    {
        this.comentarioNome = comentarioNome;
    }

    public String getComentarioNumeroIdentificacao()
    {
        return comentarioNumeroIdentificacao;
    }

    public void setComentarioNumeroIdentificacao(String comentarioNumeroIdentificacao)
    {
        this.comentarioNumeroIdentificacao = comentarioNumeroIdentificacao;
    }

    public boolean getOk()
    {
        return ok;
    }

    public void setOk(boolean ok)
    {
        this.ok = ok;
    }

    public List<RhFuncionario> getRhFuncionarioList()
    {
        return rhFuncionarioList;
    }

    public void setRhFuncionarioList(List<RhFuncionario> rhFuncionarioList)
    {
        this.rhFuncionarioList = rhFuncionarioList;
    }

    public GrlEndereco getFkResidenciaBairro()
    {
        return fkResidenciaBairro;
    }

    public void setFkResidenciaBairro(GrlEndereco fkResidenciaBairro)
    {
        this.fkResidenciaBairro = fkResidenciaBairro;
    }

    public GrlEstadoCivil getFkEstadoCivil()
    {
        return fkEstadoCivil;
    }

    public void setFkEstadoCivil(GrlEstadoCivil fkEstadoCivil)
    {
        this.fkEstadoCivil = fkEstadoCivil;
    }

    public GrlSexo getFkSexo()
    {
        return fkSexo;
    }

    public void setFkSexo(GrlSexo fkSexo)
    {
        this.fkSexo = fkSexo;
    }

    public List<SegConta> getSegContaList()
    {
        return segContaList;
    }

    public void setSegContaList(List<SegConta> segContaList)
    {
        this.segContaList = segContaList;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (pkGrlPessoa != null ? pkGrlPessoa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GrlPessoa))
        {
            return false;
        }
        GrlPessoa other = (GrlPessoa) object;
        if ((this.pkGrlPessoa == null && other.pkGrlPessoa != null) || (this.pkGrlPessoa != null && !this.pkGrlPessoa.equals(other.pkGrlPessoa)))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ejbs.entities.GrlPessoa[ pkGrlPessoa=" + pkGrlPessoa + " ]";
    }
    
}
