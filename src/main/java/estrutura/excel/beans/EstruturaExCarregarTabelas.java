/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estrutura.excel.beans;

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
public class EstruturaExCarregarTabelas implements Serializable
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
    EstruturaFisicaExcelBean estruturaFisicaExcelBean;
    
    @Inject
    EstruturaLogicaExcelBean estruturaLogicaExcelBean;
    

//    @Inject
//    LocCountryCodesExcelBean locCountryCodesExcelBean;
//
//    @Inject
//    NotTipoExcelBean notTipoExcelBean;


    /**
     * Creates a new instance of CarregarTabelas
     */
    public EstruturaExCarregarTabelas()
    {

    }

    /**
     *
     */
    public void carregarTabelas()
    {
//LogFile.warnMsg(null, "0: EstruturaExCarregarTabelas().carregarTabelas()");		
//        this.rhEgtiOrganogramaExcelBean.carregar();
////LogFile.warnMsg(null, "1: EstruturaExCarregarTabelas().carregarTabelas()");		
//        this.rhEgtiMapeamentoFisicoExcelBean.carregar();
////LogFile.warnMsg(null, "2: EstruturaExCarregarTabelas().carregarTabelas()");
//        rhEgtiFuncaoExcelBean.carregar();
//        rhEgtiCargoExcelBean.carregar();
////LogFile.warnMsg(null, "3: EstruturaExCarregarTabelas().carregarTabelas()");		
//        grlEstadoCivilExcelBean.carregar();
//        grlSexoExcelBean.carregar();
//
//        rhColaboradorExcelBean.carregar();
//        LogFile.warnMsg(null, "4: EstruturaExCarregarTabelas().carregarTabelas()");
//
//        locCountryCodesExcelBean.carregar();
////LogFile.warnMsg(null, "5: EstruturaExCarregarTabelas().carregarTabelas()");
//        notTipoExcelBean.carregar();
////LogFile.warnMsg(null, "6: EstruturaExCarregarTabelas().carregarTabelas()");	
//        grlTipoPessaExcelBean.carregar();
        estruturaFisicaExcelBean.carregar();
        estruturaLogicaExcelBean.carregar();

    }

}
