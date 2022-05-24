/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package excel.beans;

//import geral.excel.GrlEstadoCivilExcelBean;
//import geral.excel.GrlSexoExcelBean;
//import geral.excel.GrlTipoPessoaExcelBean;
import estrutura.excel.*;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import localidade.excel.LocLocalidadeExcelBean;
import seg.excel.SegFuncionalidadeExcelBean;
//import seg.excel.SegFuncionalidadeExcelBean_backup;

/**
 *
 * @author KiamiSoft_ACosta
 */
@Named
@RequestScoped
public class ExCarregarTabelas implements Serializable
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
    
    @Inject
    EstruturaFisicaExcelBean estruturaFisicaExcelBean;
    
    @Inject
    EstruturaLogicaExcelBean estruturaLogicaExcelBean;
    
    @Inject
    SegFuncionalidadeExcelBean segFuncionalidadeExcelBean;
    

//    @Inject
//    LocCountryCodesExcelBean locCountryCodesExcelBean;
//
//    @Inject
//    NotTipoExcelBean notTipoExcelBean;


    /**
     * Creates a new instance of CarregarTabelas
     */
    public ExCarregarTabelas()
    {

    }

    /**
     *
     */
    public void carregarTabelas()
    {
//LogFile.warnMsg(null, "0: ExCarregarTabelas().carregarTabelas()");		
//        this.rhEgtiOrganogramaExcelBean.carregar();
////LogFile.warnMsg(null, "1: ExCarregarTabelas().carregarTabelas()");		
//        this.rhEgtiMapeamentoFisicoExcelBean.carregar();
////LogFile.warnMsg(null, "2: ExCarregarTabelas().carregarTabelas()");
//        rhEgtiFuncaoExcelBean.carregar();
//        rhEgtiCargoExcelBean.carregar();
////LogFile.warnMsg(null, "3: ExCarregarTabelas().carregarTabelas()");		
//        grlEstadoCivilExcelBean.carregar();
//        grlSexoExcelBean.carregar();
//
//        rhColaboradorExcelBean.carregar();
//        LogFile.warnMsg(null, "4: ExCarregarTabelas().carregarTabelas()");
//
//        locCountryCodesExcelBean.carregar();
////LogFile.warnMsg(null, "5: ExCarregarTabelas().carregarTabelas()");
//        notTipoExcelBean.carregar();
////LogFile.warnMsg(null, "6: ExCarregarTabelas().carregarTabelas()");	
//        grlTipoPessaExcelBean.carregar();

        //Gessy
        
       // localidadeExcelBean.carregar();
        //estruturaFisicaExcelBean.carregar();
        //estruturaLogicaExcelBean.carregar();
        segFuncionalidadeExcelBean.carregar();
        

    }

}
