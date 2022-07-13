/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package financas.beans;

import ejbs.cache.FinMoedaCache;
import ejbs.cache.FinBancoCache;
import ejbs.cache.FinContaCache;
import ejbs.cache.FinTipoCartaoCache;
import ejbs.cache.FinTipoContaCache;
import ejbs.entities.FinBanco;
import ejbs.entities.FinConta;
import ejbs.entities.FinTipoCartao;
import ejbs.entities.FinTipoConta;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import seg.beans.SegLoginBean;
import utils.mensagens.Mensagem;

/**
 *
 * @author majoao
 */
@Named(value = "finContaBean")
@RequestScoped
public class FinContaBean {

    /**
     * Creates a new instance of FinBanco
     */
    @Inject
    private FinBancoCache bancoCahe;

    @Inject
    private FinContaCache contaCahe;
    @Inject
    private FinMoedaCache finMoedaCache;
    @Inject
    private FinTipoCartaoCache tipoCartaoCache;
    @Inject
    private FinTipoContaCache tipoContaCahe;
    private FinBanco bancoEntidade;
    private FinTipoCartao tipoCartaoEntidade;
    private FinConta contaEntidade;
    private FinTipoConta tipoContaEntidade;
    private List<FinBanco> listBancos;
    private List<FinTipoCartao> listTipoCartao;
    private List<FinTipoConta> listTipoConta;
    private int pkbanco;
    private String pkTipoCartao = "", pkMoeda = "", pkTipoConta = "";
    private String nomeBanco = null, abreviatura = null;
    private Date dataEmissao;
    private BigDecimal saldoInicial;
    @Inject
    SegLoginBean segLoginBean;

    public FinContaBean() {
    }

    @PostConstruct
    public void init() {
        bancoEntidade = new FinBanco();
        contaEntidade = new FinConta();
        tipoContaEntidade = new FinTipoConta();
        listBancos = bancoCahe.getListaFinBancos();
        listTipoCartao = tipoCartaoCache.getListaFinTipoCartao();
        listTipoConta = tipoContaCahe.getListaFinTipoConta();
        dataEmissao = new Date();
        saldoInicial = BigDecimal.ZERO;
        pkTipoCartao = "";
        pkMoeda = "";
        pkTipoConta = "";
    }

    public void salvar(int tipo) {
        try {
            switch (tipo) {
                case 1:
                    tipoContaEntidade = tipoContaCahe.findFinTipoConta(Integer.parseInt(pkTipoConta));
                    contaEntidade.setPkConta(contaCahe.getFinContaID());
                    contaEntidade.setDataCadastro(dataEmissao);
                    contaEntidade.setEstado(Boolean.TRUE);
                    contaEntidade.setNome(nomeBanco);
                    contaEntidade.setFkUtilizador(segLoginBean.getContaUtilizador());
                    contaEntidade.setFkTipoConta(tipoContaEntidade);
                    contaEntidade.setFkMoeda(finMoedaCache.findFinMoeda(Integer.parseInt(pkMoeda)));
                    contaEntidade.setSaldo(saldoInicial);
                    contaEntidade.setSaldoInicial(saldoInicial);
                    break;
                case 2:
                    tipoCartaoEntidade = tipoCartaoCache.findFinTipoCartao(Integer.parseInt(pkTipoCartao));
                    contaEntidade.setPkConta(contaCahe.getFinContaID());
                    contaEntidade.setDataCadastro(dataEmissao);
                    contaEntidade.setFkUtilizador(segLoginBean.getContaUtilizador());
                    contaEntidade.setEstado(Boolean.TRUE);
                    contaEntidade.setNome(nomeBanco);
                    contaEntidade.setFkMoeda(finMoedaCache.findFinMoeda(Integer.parseInt(pkMoeda)));
                    break;
            }
            contaCahe.create(contaEntidade);
            Mensagem.sucessoMsg(null, "Dados Salvo");
            init();
            contaCahe.init();
        } catch (Exception e) {
            Mensagem.enviarMensagemFatal(null, "Erro ao salvar dados, por favor contacte o administrador de sistemas !");
        }

    }

    public void getAbreviaturaUpdate() {
        finMoedaCache.getListaFinMoedas().stream().forEach(x -> {
            try {
                if (x.getPkMoeda() == Integer.parseInt(pkMoeda)) {
                    abreviatura = x.getAbreviatura();
                }
            } catch (NumberFormatException e) {
            }

        });
    }

    public List<FinTipoCartao> getListTipoCartao() {
        return listTipoCartao;
    }

    public void setListTipoCartao(List<FinTipoCartao> listTipoCartao) {
        this.listTipoCartao = listTipoCartao;
    }

    public String getPkTipoCartao() {
        return pkTipoCartao;
    }

    public void setPkTipoCartao(String pkTipoCartao) {
        this.pkTipoCartao = pkTipoCartao;
    }

    public FinBanco getBancoEntidade() {
        return bancoEntidade;
    }

    public void setBancoEntidade(FinBanco bancoEntidade) {
        this.bancoEntidade = bancoEntidade;
    }

    public FinConta getContaEntidade() {
        return contaEntidade;
    }

    public void setContaEntidade(FinConta contaEntidade) {
        this.contaEntidade = contaEntidade;
    }

    public List<FinBanco> getListBancos() {
        return listBancos;
    }

    public void setListBancos(List<FinBanco> listBancos) {
        this.listBancos = listBancos;
    }

    public int getPkbanco() {
        return pkbanco;
    }

    public void setPkbanco(int pkbanco) {
        this.pkbanco = pkbanco;
    }

    public String getNomeBanco() {
        return nomeBanco;
    }

    public void setNomeBanco(String nomeBanco) {
        this.nomeBanco = nomeBanco;
    }

    public FinContaCache getContaCahe() {
        return contaCahe;
    }

    public void setContaCahe(FinContaCache contaCahe) {
        this.contaCahe = contaCahe;
    }

    public String getPkTipoConta() {
        return pkTipoConta;
    }

    public void setPkTipoConta(String pkTipoConta) {
        this.pkTipoConta = pkTipoConta;
    }

    public List<FinTipoConta> getListTipoConta() {
        return listTipoConta;
    }

    public void setListTipoConta(List<FinTipoConta> listTipoConta) {
        this.listTipoConta = listTipoConta;
    }

    public FinMoedaCache getFinMoedaCache() {
        return finMoedaCache;
    }

    public void setFinMoedaCache(FinMoedaCache finMoedaCache) {
        this.finMoedaCache = finMoedaCache;
    }

    public String getPkMoeda() {
        return pkMoeda;
    }

    public void setPkMoeda(String pkMoeda) {
        this.pkMoeda = pkMoeda;
    }

    public String getAbreviatura() {
        return abreviatura;
    }

    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }

    public BigDecimal getSaldoInicial() {
        return saldoInicial;
    }

    public void setSaldoInicial(BigDecimal saldoInicial) {
        this.saldoInicial = saldoInicial;
    }

}
