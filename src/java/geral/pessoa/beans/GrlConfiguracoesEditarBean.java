/*
 * To actualizar this license header, choose License Headers in Project Properties.
 * To actualizar this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geral.pessoa.beans;

//import ejbs.cache.DocDocumentoNecessarioCache;
//import ejbs.cache.DocInstituicaoPessoaCache;
//import ejbs.cache.DocTipoDocumentoTabelaCache;
//import ejbs.cache.EveTipoEventoCache;
//import ejbs.cache.FinPagamentoTipoCache;
//import ejbs.cache.GrlEstadoCivilCache;
//import ejbs.cache.GrlSexoCache;
//import ejbs.cache.LocBairrosCache;
//import ejbs.cache.LocComunasCache;
//import ejbs.cache.LocMunicipiosCache;
//import ejbs.cache.LocPaisCache;
//import ejbs.cache.LocProvinciasCache;
//import ejbs.cache.ProcEstadoCandidaturaCache;
//import ejbs.cache.ProcTipoEncaminhamentoCache;
//import ejbs.cache.RhEgtiMapeamentoFisicoCache;
//import ejbs.cache.SegFuncionalidadeCache;
//import ejbs.cache.SegTipoFuncionalidadeCache;
//import ejbs.cache.utils.InitInterface;
//import ejbs.cache2.ComInstituicaoCache2;
//import ejbs.cache2.DocDocumentoEmitidoCache2;
//import ejbs.cache2.DocDocumentoEntregueCache2;
//import ejbs.cache2.DocTipoDocumentoCache2;
//import ejbs.cache2.EveEventoCache2;
//import ejbs.cache2.FinLotePrecoCache2;
//import ejbs.cache2.GrlPessoaCache2;
//import ejbs.cache2.PrjCandidaturasGerirCache2;
//import ejbs.cache2.PrjLoteCache2;
//import ejbs.cache2.PrjProcessoCandidaturaCache2;
//import ejbs.cache2.PrjProjectoCache2;
//import ejbs.cache2.PrjTipologiaLoteCache2;
//import ejbs.cache2.PrjTipologiaLoteProjectoCache2;
//import ejbs.cache2.RhColaboradorCache2;
//import ejbs.entities.GrlConfiguracoes;
//import ejbs.entities.GrlSexo;
//import ejbs.entities.LocBairros;
//import ejbs.entities.LocComunas;
//import ejbs.entities.LocMunicipios;
//import ejbs.entities.LocProvincias;
//import ejbs.facades.GrlConfiguracoesFacade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.UserTransaction;
import org.omnifaces.cdi.ViewScoped;
//import prj.projectos.beans.PrjTipologiaLoteBean;
import utils.ExcepcaoCarregamentoTabelasExcel;
import utils.mensagens.Mensagem;

/**
 *
 * @author KiamiSoft_ACosta
 */
@Named
@ViewScoped
public class GrlConfiguracoesEditarBean implements Serializable
{

//    @EJB
//    private GrlConfiguracoesFacade grlConfiguracoesFacade;
//
//    @Inject
//    private DocDocumentoNecessarioCache docDocumentoNecessarioCache;
//
//    @Inject
//    private DocInstituicaoPessoaCache docInstituicaoPessoaCache;
//
//    @Inject
//    private DocTipoDocumentoTabelaCache docTipoDocumentoTabelaCache;
//
//    @Inject
//    private EveTipoEventoCache eveTipoEventoCache;
//
//    @Inject
//    private FinPagamentoTipoCache finPagamentoTipoCache;
//
//    @Inject
//    private GrlEstadoCivilCache grlEstadoCivilCache;
//
//    @Inject
//    private GrlSexoCache grlSexoCache;
//
//    @Inject
//    private LocBairrosCache grlBairrosCache;
//
//    @Inject
//    private LocComunasCache locComunasCache;
//
//    @Inject
//    private LocMunicipiosCache locMunicipiosCache;
//
//    @Inject
//    private LocPaisCache locPaisCache;
//
//    @Inject
//    private LocProvinciasCache locProvinciasCache;
//
//    @Inject
//    private ProcEstadoCandidaturaCache procEstadoCandidaturaCache;
//
//    @Inject
//    private ProcTipoEncaminhamentoCache procTipoEncaminhamentoCache;
//
//    @Inject
//    private RhEgtiMapeamentoFisicoCache rhEgtiMapeamentoFisicoCache;
//
//    @Inject
//    private SegFuncionalidadeCache segFuncionalidadeCache;
//
//    @Inject
//    private SegTipoFuncionalidadeCache segTipoFuncionalidadeCache;
//
//    @Inject
//    private ComInstituicaoCache2 comInstituicaoCache2;
//
//    @Inject
//    private DocDocumentoEmitidoCache2 docDocumentoEmitidoCache2;
//
//    @Inject
//    private DocDocumentoEntregueCache2 docDocumentoEntregueCache2;
//
//    @Inject
//    private DocTipoDocumentoCache2 docTipoDocumentoCache2;
//
//    @Inject
//    private EveEventoCache2 eveEventoCache2;
//
//    @Inject
//    private FinLotePrecoCache2 finLotePrecoCache2;
//
//    @Inject
//    private GrlPessoaCache2 grlPessoaCache2;
//
//    @Inject
//    private RhColaboradorCache2 rhColaboradorCache2;
//
//    @Inject
//    private PrjLoteCache2 prjLoteCache2;
//
//    @Inject
//    private PrjProjectoCache2 prjProjectoCache2;
//
//    @Inject
//    private PrjTipologiaLoteBean prjTipologiaLoteBean;
//
//    @Inject
//    private PrjTipologiaLoteCache2 prjTipologiaLoteCache2;
//
//    @Inject
//    private PrjTipologiaLoteProjectoCache2 prjTipologiaLoteProjectoCache2;
//
//    @Inject
//    private PrjProcessoCandidaturaCache2 prjProcessoCandidaturaCache2;
//
//    @Inject
//    private PrjCandidaturasGerirCache2 prjCandidaturasGerirCache2;

    // 9999999999999999999999999999999999999
    @Resource
    private UserTransaction userTransaction;

//    private List<InitInterface> initInterfaceList;
//
//    private GrlConfiguracoes grlConfiguracoes;
//
//    private GrlSexo sexoPreferencial;
//    private LocBairros enderecoPreferencial;

    private int idPaisPreferencial;
    private int idProvinciaPreferencial;
    private int idMunicipioPreferencial;
    private String valorLabelDistritoComuna = "Comunas";
    private int idComunaPreferencial = 0;

//    private List<LocProvincias> provinciasPreferenciais;
//    private List<LocMunicipios> municipiosPreferenciais;
//    private List<LocComunas> comunasPreferenciais;
//    private List<LocBairros> bairrosPreferenciais;

    /**
     * Creates a new instance of ConfiguracaoEnderecoBean
     */
    public GrlConfiguracoesEditarBean()
    {
    }

    /**
     *
     */
//    @PostConstruct
//    public void init()
//    {
//        this.grlConfiguracoes = this.grlConfiguracoesFacade.find();
//        this.sexoPreferencial = this.grlConfiguracoes.getFkSexoPreferencial();
//        this.enderecoPreferencial = this.grlConfiguracoes.getFkEnderecoPreferencial();
//
//        inicializarLocalizacoes();
//    }

//    public GrlConfiguracoes reporConfiguracoesPadrao()
//    {
//        grlConfiguracoes = this.grlConfiguracoesFacade.reporConfiguracoesPadrao();
//
//        this.sexoPreferencial = this.grlConfiguracoes.getFkSexoPreferencial();
//
//        this.enderecoPreferencial = this.grlConfiguracoes.getFkEnderecoPreferencial();
//
//        inicializarLocalizacoes();
//        return grlConfiguracoes;
//    }

//    public void initCaches()
//    {
//        System.err.println("0: GrlConfiguracoesEditarBean.initCaches()");
//        this.initInterfaceList = new ArrayList();
//        initInterfaceList.add(docDocumentoNecessarioCache);
//        initInterfaceList.add(docInstituicaoPessoaCache);
//        initInterfaceList.add(docTipoDocumentoTabelaCache);
//        initInterfaceList.add(eveTipoEventoCache);
//        initInterfaceList.add(eveEventoCache2);
//
//        initInterfaceList.add(finPagamentoTipoCache);
//        initInterfaceList.add(grlEstadoCivilCache);
//
//        initInterfaceList.add(grlSexoCache);
//
//        initInterfaceList.add(locPaisCache);
//        initInterfaceList.add(locProvinciasCache);
//        initInterfaceList.add(locMunicipiosCache);
//        initInterfaceList.add(locComunasCache);
//        initInterfaceList.add(grlBairrosCache);
//
//        initInterfaceList.add(procEstadoCandidaturaCache);
//        initInterfaceList.add(procTipoEncaminhamentoCache);
//        initInterfaceList.add(rhEgtiMapeamentoFisicoCache);
//        initInterfaceList.add(rhEgtiMapeamentoFisicoCache);
//
//        initInterfaceList.add(segTipoFuncionalidadeCache);
//        initInterfaceList.add(segFuncionalidadeCache);
//        initInterfaceList.add(comInstituicaoCache2);
//
//        initInterfaceList.add(docDocumentoEmitidoCache2);
//        initInterfaceList.add(docDocumentoEntregueCache2);
//
//        initInterfaceList.add(docTipoDocumentoCache2);
//
//        initInterfaceList.add(finLotePrecoCache2);
//        initInterfaceList.add(grlPessoaCache2);
//
//        initInterfaceList.add(rhColaboradorCache2);
//        initInterfaceList.add(prjLoteCache2);
//        initInterfaceList.add(prjProjectoCache2);
//        initInterfaceList.add(prjTipologiaLoteBean);
//        initInterfaceList.add(prjTipologiaLoteCache2);
//        initInterfaceList.add(prjTipologiaLoteProjectoCache2);
//        initInterfaceList.add(prjProcessoCandidaturaCache2);
//        initInterfaceList.add(prjCandidaturasGerirCache2);
//System.err.println("1: GrlConfiguracoesEditarBean.initCaches()");
//        int i = 0;
//        for (InitInterface initInterface : initInterfaceList)
//        {
//System.err.println("2: GrlConfiguracoesEditarBean.initCaches()\ti: " + i);            
//            initInterface.init();
//System.err.println("3: GrlConfiguracoesEditarBean.initCaches()\ti: " + i);            
//            i++;
//        }
//System.err.println("4: GrlConfiguracoesEditarBean.initCaches()");        
//
//    }

    /**
     *
     */
//    public void inicializarLocalizacoes()
//    {
//        this.idComunaPreferencial = this.enderecoPreferencial.getFkLocComunas().getPkLocComunas();
//        this.bairrosPreferenciais = this.grlBairrosCache.findAllByPkLocComunasOrderByNome(idComunaPreferencial);
//        this.idMunicipioPreferencial = this.enderecoPreferencial.getFkLocComunas().getFkLocMunicipios().getPkLocMunicipios();
//        this.comunasPreferenciais = this.locComunasCache.findAllByPkLocMunicipioOrderByNome(idMunicipioPreferencial);
//        actualizarValorLabelDistritoComuna();
//
//        this.idProvinciaPreferencial = this.enderecoPreferencial.getFkLocComunas().getFkLocMunicipios().getFkLocProvincias().getPkLocProvincias();
//        this.municipiosPreferenciais = this.locMunicipiosCache.findAllByPkLocProvinciasOrderByNome(idProvinciaPreferencial);
//
//        this.idPaisPreferencial = this.enderecoPreferencial.getFkLocComunas().getFkLocMunicipios().getFkLocProvincias().getFkLocPais().getPkLocPais();
//        this.provinciasPreferenciais = this.locProvinciasCache.findAllByPaisOrderByNome(idPaisPreferencial);
//
//    }

    /**
     *
     */
//    public void actualizarProvincias()
//    {
//        this.idPaisPreferencial = this.enderecoPreferencial.getFkLocComunas().
//            getFkLocMunicipios().getFkLocProvincias().getFkLocPais().
//            getPkLocPais();
//        this.provinciasPreferenciais = this.locProvinciasCache.findAllByPaisOrderByNome(idPaisPreferencial);
//        this.enderecoPreferencial.getFkLocComunas().getFkLocMunicipios().
//            setFkLocProvincias(provinciasPreferenciais.get(0));
//        actualizarMunicipios();
//    }

    /**
     *
     */
//    public void actualizarMunicipios()
//    {
//        //System.err.println("0: LocConfiguracaoEditeBean.actualizarMunicipios()");
//        this.idProvinciaPreferencial = this.enderecoPreferencial.getFkLocComunas().getFkLocMunicipios().getFkLocProvincias().getPkLocProvincias();
//        //System.err.println("1: LocConfiguracaoEditeBean.actualizarMunicipios()\tidProvinciaPreferencial: " + idProvinciaPreferencial);
//        municipiosPreferenciais = this.locMunicipiosCache.findAllByPkLocProvinciasOrderByNome(idProvinciaPreferencial);
//        this.enderecoPreferencial.getFkLocComunas().
//            setFkLocMunicipios(municipiosPreferenciais.get(0));
//        //System.err.println("2: LocConfiguracaoEditeBean.actualizarMunicipios()\tmunicipiosPreferenciais: " + municipiosPreferenciais);
//        actualizarComunas();
//    }

    /**
     *
     */
//    public void actualizarComunas()
//    {
//        this.idMunicipioPreferencial = this.enderecoPreferencial.getFkLocComunas().getFkLocMunicipios().getPkLocMunicipios();
//        comunasPreferenciais = this.locComunasCache.findAllByPkLocMunicipioOrderByNome(idMunicipioPreferencial);
//        this.enderecoPreferencial.setFkLocComunas(comunasPreferenciais.get(0));
//        actualizarBairros();
//        actualizarValorLabelDistritoComuna();
//    }

//    public void actualizarValorLabelDistritoComuna()
//    {
//        valorLabelDistritoComuna = this.locComunasCache.actualizarValorLabelDistritoComuna(comunasPreferenciais);
//        if (this.valorLabelDistritoComuna.equalsIgnoreCase("Comunas/Distritos"))
//        {
//            locComunasCache.colocarPrefixo(comunasPreferenciais);
//        }
//    }

    /**
     *
     */
//    public void actualizarBairros()
//    {
//        this.idComunaPreferencial = this.enderecoPreferencial.getFkLocComunas().getPkLocComunas();
//        bairrosPreferenciais = this.grlBairrosCache.findAllByPkLocComunasOrderByNome(idComunaPreferencial);
//
//        this.enderecoPreferencial = bairrosPreferenciais.get(0);
//    }

    /**
     *
     * @return @throws ExcepcaoCarregamentoTabelasExcel
     */
//    public boolean salvarRegister() throws ExcepcaoCarregamentoTabelasExcel
//    {
//        if (grlConfiguracoes != null)
//        {
//            this.grlConfiguracoes.setFkSexoPreferencial(this.grlSexoCache.find(this.sexoPreferencial.getPkGrlSexo()));
//            grlConfiguracoes.setFkEnderecoPreferencial(
//                grlBairrosCache.find(this.enderecoPreferencial.getPkLocBairros()));
//            grlConfiguracoesFacade.edit(grlConfiguracoes);
//            return true;
//        }
//        return false;
//    }

    /**
     *
     * @throws ExcepcaoCarregamentoTabelasExcel
     */
//    public void editRegisterWithPersonalizedException() throws ExcepcaoCarregamentoTabelasExcel
//    {
//        if (!this.salvarRegister())
//        {
//            Mensagem.enviarJanelaMensagemErro("Tentativa falhada ao actualizar a localização preferêncial",
//                "Tentativa falhada ao actualizar a localização preferêncial ");
//            throw new ExcepcaoCarregamentoTabelasExcel();
//        }
//    }

    /**
     *
     * @return
     */
//    public LocBairros getEnderecoPreferencial()
//    {
//        return enderecoPreferencial;
//    }

    /**
     *
     * @return
     */
//    public List<LocProvincias> getProvinciasPreferenciais()
//    {
//        return provinciasPreferenciais;
//    }

    /**
     *
     * @return
     */
//    public List<LocMunicipios> getMunicipiosPreferenciais()
//    {
//        return municipiosPreferenciais;
//    }

    /**
     *
     * @return
     */
//    public List<LocComunas> getComunasPreferenciais()
//    {
//        return comunasPreferenciais;
//    }

    /**
     *
     * @return
//     */
//    public List<LocBairros> getBairrosPreferenciais()
//    {
//        return bairrosPreferenciais;
//    }

    /**
     *
     * @return
     */
    public String getValorLabelDistritoComuna()
    {
        return valorLabelDistritoComuna;
    }

    /**
     *
     * @param valorLabelDistritoComuna
     */
    public void setValorLabelDistritoComuna(String valorLabelDistritoComuna)
    {
        this.valorLabelDistritoComuna = valorLabelDistritoComuna;
    }

//    public GrlSexo getGrlSexoPreferencial()
//    {
//        return sexoPreferencial;
//    }

}
