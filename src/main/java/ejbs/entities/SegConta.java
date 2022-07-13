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
@Table(name = "seg_conta", catalog = "sig_ucan_db", schema = "public")
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
    @Basic(optional = false)
    @NotNull
    @Column(name = "pk_seg_conta", nullable = false)
    private Integer pkSegConta;
    @Size(max = 2147483647)
    @Column(name = "nome_utilizador", length = 2147483647)
    private String nomeUtilizador;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "palavra_passe", nullable = false, length = 2147483647)
    private String palavraPasse;
    @Basic(optional = false)
    @NotNull
    @Column(name = "data_cadastro", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCadastro;
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
    @OneToMany(mappedBy = "fkUtilizador")
    private List<CtClass> ctClassList;
    @OneToMany(mappedBy = "fkUtilizador")
    private List<CtOcorrenciaContabilidade> ctOcorrenciaContabilidadeList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkSegContaCreatedBy")
    private List<BbtDocumento> bbtDocumentoList;
    @OneToMany(mappedBy = "fkSegContaUpdatedBy")
    private List<BbtDocumento> bbtDocumentoList1;
    @OneToMany(mappedBy = "fkUtilizador")
    private List<CtDiario> ctDiarioList;
    @OneToMany(mappedBy = "fkUtilizador")
    private List<FinOperacoesContaCorrente> finOperacoesContaCorrenteList;
    @OneToMany(mappedBy = "fkUtilizador")
    private List<FinContaTransferencia> finContaTransferenciaList;
    @OneToMany(mappedBy = "fkUtilizador")
    private List<FinTipoMovBancarios> finTipoMovBancariosList;
    @OneToMany(mappedBy = "fkUtilizador")
    private List<FinContasPagar> finContasPagarList;
    @OneToMany(mappedBy = "fkUtilizador")
    private List<CtMontanteClasse> ctMontanteClasseList;
    @OneToMany(mappedBy = "fkUtilizador")
    private List<FinMoeda> finMoedaList;
    @OneToMany(mappedBy = "fkUtilizador")
    private List<CtDocument> ctDocumentList;
    @OneToMany(mappedBy = "fkUtilizador")
    private List<FinDocumento> finDocumentoList;
    @OneToMany(mappedBy = "fkUtilizador")
    private List<FinContratoCompra> finContratoCompraList;
    @OneToMany(mappedBy = "fkBanco")
    private List<FinConta> finContaList;
    @OneToMany(mappedBy = "fkUtilizador")
    private List<FinConta> finContaList1;
    @OneToMany(mappedBy = "fkUtilizador")
    private List<FinCambio> finCambioList;
    @OneToMany(mappedBy = "fkUtilizador")
    private List<CtLancamento> ctLancamentoList;
    @OneToMany(mappedBy = "fkUtilizador")
    private List<CtContraPartidai> ctContraPartidaiList;
    @OneToMany(mappedBy = "fkUtilizador")
    private List<FinCaixa> finCaixaList;
    @OneToMany(mappedBy = "fkUtilizador")
    private List<CtAccount> ctAccountList;
    @OneToMany(mappedBy = "fkUtilizador")
    private List<FinContaCorrente> finContaCorrenteList;
    @OneToMany(mappedBy = "fkUtilizador")
    private List<FinCofre> finCofreList;
    @OneToMany(mappedBy = "fkSegConta")
    private List<FrtTransporteConfiguracoes> frtTransporteConfiguracoesList;
    @OneToMany(mappedBy = "fkSegConta")
    private List<SegLogAcesso> segLogAcessoList;
    @OneToMany(mappedBy = "fkUtilizador")
    private List<FinBanco> finBancoList;
    @OneToMany(mappedBy = "fkUtilizador")
    private List<FinCategorias> finCategoriasList;
    @OneToMany(mappedBy = "fkUtilizador")
    private List<FinPagamento> finPagamentoList;
    @OneToMany(mappedBy = "fkUtilizador")
    private List<CtRubrica> ctRubricaList;
    @OneToMany(mappedBy = "fkUtilizador")
    private List<CtMontanteRubrica> ctMontanteRubricaList;
    @OneToMany(mappedBy = "fkConta")
    private List<FrtTransporteSolicitacao> frtTransporteSolicitacaoList;
    @OneToMany(mappedBy = "fkUtilizador")
    private List<CtAnoEconomico> ctAnoEconomicoList;
    @OneToMany(mappedBy = "fkUtilizadorClose")
    private List<FinOperacoesCaixa> finOperacoesCaixaList;
    @OneToMany(mappedBy = "fkUtilizador")
    private List<FinOperacoesCaixa> finOperacoesCaixaList1;
    @OneToMany(mappedBy = "fkUtilizadorOpen")
    private List<FinOperacoesCaixa> finOperacoesCaixaList2;
    @OneToMany(mappedBy = "fkUtilizador")
    private List<FinContasReceber> finContasReceberList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkSegConta")
    private List<SegAuditoria> segAuditoriaList;
    @OneToMany(mappedBy = "fkUtilizador")
    private List<FinMovimentosBancarios> finMovimentosBancariosList;
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

    public List<CtClass> getCtClassList()
    {
        return ctClassList;
    }

    public void setCtClassList(List<CtClass> ctClassList)
    {
        this.ctClassList = ctClassList;
    }

    public List<CtOcorrenciaContabilidade> getCtOcorrenciaContabilidadeList()
    {
        return ctOcorrenciaContabilidadeList;
    }

    public void setCtOcorrenciaContabilidadeList(List<CtOcorrenciaContabilidade> ctOcorrenciaContabilidadeList)
    {
        this.ctOcorrenciaContabilidadeList = ctOcorrenciaContabilidadeList;
    }

    public List<BbtDocumento> getBbtDocumentoList()
    {
        return bbtDocumentoList;
    }

    public void setBbtDocumentoList(List<BbtDocumento> bbtDocumentoList)
    {
        this.bbtDocumentoList = bbtDocumentoList;
    }

    public List<BbtDocumento> getBbtDocumentoList1()
    {
        return bbtDocumentoList1;
    }

    public void setBbtDocumentoList1(List<BbtDocumento> bbtDocumentoList1)
    {
        this.bbtDocumentoList1 = bbtDocumentoList1;
    }

    public List<CtDiario> getCtDiarioList()
    {
        return ctDiarioList;
    }

    public void setCtDiarioList(List<CtDiario> ctDiarioList)
    {
        this.ctDiarioList = ctDiarioList;
    }

    public List<FinOperacoesContaCorrente> getFinOperacoesContaCorrenteList()
    {
        return finOperacoesContaCorrenteList;
    }

    public void setFinOperacoesContaCorrenteList(List<FinOperacoesContaCorrente> finOperacoesContaCorrenteList)
    {
        this.finOperacoesContaCorrenteList = finOperacoesContaCorrenteList;
    }

    public List<FinContaTransferencia> getFinContaTransferenciaList()
    {
        return finContaTransferenciaList;
    }

    public void setFinContaTransferenciaList(List<FinContaTransferencia> finContaTransferenciaList)
    {
        this.finContaTransferenciaList = finContaTransferenciaList;
    }

    public List<FinTipoMovBancarios> getFinTipoMovBancariosList()
    {
        return finTipoMovBancariosList;
    }

    public void setFinTipoMovBancariosList(List<FinTipoMovBancarios> finTipoMovBancariosList)
    {
        this.finTipoMovBancariosList = finTipoMovBancariosList;
    }

    public List<FinContasPagar> getFinContasPagarList()
    {
        return finContasPagarList;
    }

    public void setFinContasPagarList(List<FinContasPagar> finContasPagarList)
    {
        this.finContasPagarList = finContasPagarList;
    }

    public List<CtMontanteClasse> getCtMontanteClasseList()
    {
        return ctMontanteClasseList;
    }

    public void setCtMontanteClasseList(List<CtMontanteClasse> ctMontanteClasseList)
    {
        this.ctMontanteClasseList = ctMontanteClasseList;
    }

    public List<FinMoeda> getFinMoedaList()
    {
        return finMoedaList;
    }

    public void setFinMoedaList(List<FinMoeda> finMoedaList)
    {
        this.finMoedaList = finMoedaList;
    }

    public List<CtDocument> getCtDocumentList()
    {
        return ctDocumentList;
    }

    public void setCtDocumentList(List<CtDocument> ctDocumentList)
    {
        this.ctDocumentList = ctDocumentList;
    }

    public List<FinDocumento> getFinDocumentoList()
    {
        return finDocumentoList;
    }

    public void setFinDocumentoList(List<FinDocumento> finDocumentoList)
    {
        this.finDocumentoList = finDocumentoList;
    }

    public List<FinContratoCompra> getFinContratoCompraList()
    {
        return finContratoCompraList;
    }

    public void setFinContratoCompraList(List<FinContratoCompra> finContratoCompraList)
    {
        this.finContratoCompraList = finContratoCompraList;
    }

    public List<FinConta> getFinContaList()
    {
        return finContaList;
    }

    public void setFinContaList(List<FinConta> finContaList)
    {
        this.finContaList = finContaList;
    }

    public List<FinConta> getFinContaList1()
    {
        return finContaList1;
    }

    public void setFinContaList1(List<FinConta> finContaList1)
    {
        this.finContaList1 = finContaList1;
    }

    public List<FinCambio> getFinCambioList()
    {
        return finCambioList;
    }

    public void setFinCambioList(List<FinCambio> finCambioList)
    {
        this.finCambioList = finCambioList;
    }

    public List<CtLancamento> getCtLancamentoList()
    {
        return ctLancamentoList;
    }

    public void setCtLancamentoList(List<CtLancamento> ctLancamentoList)
    {
        this.ctLancamentoList = ctLancamentoList;
    }

    public List<CtContraPartidai> getCtContraPartidaiList()
    {
        return ctContraPartidaiList;
    }

    public void setCtContraPartidaiList(List<CtContraPartidai> ctContraPartidaiList)
    {
        this.ctContraPartidaiList = ctContraPartidaiList;
    }

    public List<FinCaixa> getFinCaixaList()
    {
        return finCaixaList;
    }

    public void setFinCaixaList(List<FinCaixa> finCaixaList)
    {
        this.finCaixaList = finCaixaList;
    }

    public List<CtAccount> getCtAccountList()
    {
        return ctAccountList;
    }

    public void setCtAccountList(List<CtAccount> ctAccountList)
    {
        this.ctAccountList = ctAccountList;
    }

    public List<FinContaCorrente> getFinContaCorrenteList()
    {
        return finContaCorrenteList;
    }

    public void setFinContaCorrenteList(List<FinContaCorrente> finContaCorrenteList)
    {
        this.finContaCorrenteList = finContaCorrenteList;
    }

    public List<FinCofre> getFinCofreList()
    {
        return finCofreList;
    }

    public void setFinCofreList(List<FinCofre> finCofreList)
    {
        this.finCofreList = finCofreList;
    }

    public List<FrtTransporteConfiguracoes> getFrtTransporteConfiguracoesList()
    {
        return frtTransporteConfiguracoesList;
    }

    public void setFrtTransporteConfiguracoesList(List<FrtTransporteConfiguracoes> frtTransporteConfiguracoesList)
    {
        this.frtTransporteConfiguracoesList = frtTransporteConfiguracoesList;
    }

    public List<SegLogAcesso> getSegLogAcessoList()
    {
        return segLogAcessoList;
    }

    public void setSegLogAcessoList(List<SegLogAcesso> segLogAcessoList)
    {
        this.segLogAcessoList = segLogAcessoList;
    }

    public List<FinBanco> getFinBancoList()
    {
        return finBancoList;
    }

    public void setFinBancoList(List<FinBanco> finBancoList)
    {
        this.finBancoList = finBancoList;
    }

    public List<FinCategorias> getFinCategoriasList()
    {
        return finCategoriasList;
    }

    public void setFinCategoriasList(List<FinCategorias> finCategoriasList)
    {
        this.finCategoriasList = finCategoriasList;
    }

    public List<FinPagamento> getFinPagamentoList()
    {
        return finPagamentoList;
    }

    public void setFinPagamentoList(List<FinPagamento> finPagamentoList)
    {
        this.finPagamentoList = finPagamentoList;
    }

    public List<CtRubrica> getCtRubricaList()
    {
        return ctRubricaList;
    }

    public void setCtRubricaList(List<CtRubrica> ctRubricaList)
    {
        this.ctRubricaList = ctRubricaList;
    }

    public List<CtMontanteRubrica> getCtMontanteRubricaList()
    {
        return ctMontanteRubricaList;
    }

    public void setCtMontanteRubricaList(List<CtMontanteRubrica> ctMontanteRubricaList)
    {
        this.ctMontanteRubricaList = ctMontanteRubricaList;
    }

    public List<FrtTransporteSolicitacao> getFrtTransporteSolicitacaoList()
    {
        return frtTransporteSolicitacaoList;
    }

    public void setFrtTransporteSolicitacaoList(List<FrtTransporteSolicitacao> frtTransporteSolicitacaoList)
    {
        this.frtTransporteSolicitacaoList = frtTransporteSolicitacaoList;
    }

    public List<CtAnoEconomico> getCtAnoEconomicoList()
    {
        return ctAnoEconomicoList;
    }

    public void setCtAnoEconomicoList(List<CtAnoEconomico> ctAnoEconomicoList)
    {
        this.ctAnoEconomicoList = ctAnoEconomicoList;
    }

    public List<FinOperacoesCaixa> getFinOperacoesCaixaList()
    {
        return finOperacoesCaixaList;
    }

    public void setFinOperacoesCaixaList(List<FinOperacoesCaixa> finOperacoesCaixaList)
    {
        this.finOperacoesCaixaList = finOperacoesCaixaList;
    }

    public List<FinOperacoesCaixa> getFinOperacoesCaixaList1()
    {
        return finOperacoesCaixaList1;
    }

    public void setFinOperacoesCaixaList1(List<FinOperacoesCaixa> finOperacoesCaixaList1)
    {
        this.finOperacoesCaixaList1 = finOperacoesCaixaList1;
    }

    public List<FinOperacoesCaixa> getFinOperacoesCaixaList2()
    {
        return finOperacoesCaixaList2;
    }

    public void setFinOperacoesCaixaList2(List<FinOperacoesCaixa> finOperacoesCaixaList2)
    {
        this.finOperacoesCaixaList2 = finOperacoesCaixaList2;
    }

    public List<FinContasReceber> getFinContasReceberList()
    {
        return finContasReceberList;
    }

    public void setFinContasReceberList(List<FinContasReceber> finContasReceberList)
    {
        this.finContasReceberList = finContasReceberList;
    }

    public List<SegAuditoria> getSegAuditoriaList()
    {
        return segAuditoriaList;
    }

    public void setSegAuditoriaList(List<SegAuditoria> segAuditoriaList)
    {
        this.segAuditoriaList = segAuditoriaList;
    }

    public List<FinMovimentosBancarios> getFinMovimentosBancariosList()
    {
        return finMovimentosBancariosList;
    }

    public void setFinMovimentosBancariosList(List<FinMovimentosBancarios> finMovimentosBancariosList)
    {
        this.finMovimentosBancariosList = finMovimentosBancariosList;
    }

    public GrlPessoa getFkSegPessoa()
    {
        return fkSegPessoa;
    }

    public void setFkSegPessoa(GrlPessoa fkSegPessoa)
    {
        this.fkSegPessoa = fkSegPessoa;
    }

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
