/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package localidade.excel.beans;

//import geral.excel.GrlEstadoCivilExcelBean;
//import geral.excel.GrlSexoExcelBean;
//import geral.excel.GrlTipoPessoaExcelBean;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author KiamiSoft_ACosta
 */
@Named
@RequestScoped
public class LocalidadeExCarregarTabelas implements Serializable
{

//    @Inject
//    DocTipoDocumentoExcelBean docTipoDocumentoExcelBean;

//    @Inject
//    GrlTipoPessoaExcelBean grlTipoPessaExcelBean;
//
//    @Inject
//    RhColaboradorExcelBean rhColaboradorExcelBean;
//
//    @Inject
//    RhEgtiCargoExcelBean rhEgtiCargoExcelBean;
//
//    @Inject
//    RhEgtiOrganogramaExcelBean rhEgtiOrganogramaExcelBean;
//
//    @Inject
//    RhEgtiMapeamentoFisicoExcelBean rhEgtiMapeamentoFisicoExcelBean;
//
//    @Inject
//    RhEgtiFuncaoExcelBean rhEgtiFuncaoExcelBean;
//
//    @Inject
//    GrlEstadoCivilExcelBean grlEstadoCivilExcelBean;
//
//    @Inject
//    GrlSexoExcelBean grlSexoExcelBean;

    @Inject
    LocLocalidadeExcelBean localidadeExcelBean;
    

//    @Inject
//    LocCountryCodesExcelBean locCountryCodesExcelBean;
//
//    @Inject
//    NotTipoExcelBean notTipoExcelBean;


    /**
     * Creates a new instance of CarregarTabelas
     */
    public LocalidadeExCarregarTabelas()
    {

    }

    /**
     *
     */
    public void carregarTabelas()
    {
//LogFile.warnMsg(null, "0: LocalidadeExCarregarTabelas().carregarTabelas()");		
//        this.rhEgtiOrganogramaExcelBean.carregar();
////LogFile.warnMsg(null, "1: LocalidadeExCarregarTabelas().carregarTabelas()");		
//        this.rhEgtiMapeamentoFisicoExcelBean.carregar();
////LogFile.warnMsg(null, "2: LocalidadeExCarregarTabelas().carregarTabelas()");
//        rhEgtiFuncaoExcelBean.carregar();
//        rhEgtiCargoExcelBean.carregar();
////LogFile.warnMsg(null, "3: LocalidadeExCarregarTabelas().carregarTabelas()");		
//        grlEstadoCivilExcelBean.carregar();
//        grlSexoExcelBean.carregar();
//
//        rhColaboradorExcelBean.carregar();
//        LogFile.warnMsg(null, "4: LocalidadeExCarregarTabelas().carregarTabelas()");
//
//        locCountryCodesExcelBean.carregar();
////LogFile.warnMsg(null, "5: LocalidadeExCarregarTabelas().carregarTabelas()");
//        notTipoExcelBean.carregar();
////LogFile.warnMsg(null, "6: LocalidadeExCarregarTabelas().carregarTabelas()");	
//        grlTipoPessaExcelBean.carregar();

        localidadeExcelBean.carregar();

    }

}
