/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seg.beans;

import ejbs.cache.SegFuncionalidadeCache;
import ejbs.cache.SegTipoFuncionalidadeCache;
import ejbs.facades.SegFuncionalidadeFacade;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.UserTransaction;
import org.omnifaces.cdi.ViewScoped;
import seg.utils.SegFuncionalidadeAbstract;
import utils.GeradorCodigo;

/**
 *
 * @author aires
 */
@Named
@ViewScoped
public class SegFuncionalidadeGerirBean extends SegFuncionalidadeAbstract {

    @EJB
    private SegFuncionalidadeFacade segFuncionalidadeFacade;

    @Inject
    private SegTipoFuncionalidadeCache segTipoFuncionalidadeCache;

    @Inject
    private SegFuncionalidadeCache segFuncionalidadeCache;

    @Resource
    private UserTransaction userTransaction;

    /**
     * Creates a new instance of SegFuncionalidadeCadastrarBean
     */
    public SegFuncionalidadeGerirBean() {
    }

    @PostConstruct
    public void init() {
        inicializar();
    }

    @Override
    public void inicializar() {
        System.err.println("0: SegFuncionalidadeGerirBean.inicializar()\tsegTipoFuncionalidadeCache: "
                + (segTipoFuncionalidadeCache == null ? "null" : "not null"));
        this.setSeqTreeFuncionalidades(this.segFuncionalidadeCache.getSeqTreeFuncionalidades());
        System.err.println("1: SegFuncionalidadeGerirBean.inicializar()\tsegTipoFuncionalidadeCache: "
                + (segTipoFuncionalidadeCache == null ? "null" : "not null"));
    }

    public static SegFuncionalidadeGerirBean getInstanciaBean() {
        return (SegFuncionalidadeGerirBean) GeradorCodigo.getInstanciaBean("segFuncionalidadeGerirBean");
    }

    @Override
    public SegFuncionalidadeFacade getSegFuncionalidadeFacade() {
        return segFuncionalidadeFacade;
    }

    public void setSegFuncionalidadeFacade(SegFuncionalidadeFacade segFuncionalidadeFacade) {
        this.segFuncionalidadeFacade = segFuncionalidadeFacade;
    }

    @Override
    public SegTipoFuncionalidadeCache getSegTipoFuncionalidadeCache() {
        return segTipoFuncionalidadeCache;
    }

    @Override
    public UserTransaction getUserTransaction() {
        return userTransaction;
    }

}
