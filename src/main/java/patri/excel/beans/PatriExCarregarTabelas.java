/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patri.excel.beans;

//import geral.excel.GrlEstadoCivilExcelBean;
//import geral.excel.GrlSexoExcelBean;
//import geral.excel.GrlTipoPessoaExcelBean;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import localidade.excel.beans.LocLocalidadeExcelBean;

/**
 *
 * @author KiamiSoft_ACosta
 */
@Named
@RequestScoped
public class PatriExCarregarTabelas implements Serializable
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
    private PtCategoriaExcelBean ptCategoriaExcelBean;
    
    @Inject
    private PtFormaAquisicaoExcelBean ptFormaAquisicaoExcelBea;
     
    @Inject
    private PtEstadoConservacaoExcelBean ptEstadoConservacaoExcelBean;
      
    @Inject
    private PtMarcaExcelBean ptMarcaExcelBean;
       
    @Inject
    private PtModeloExcelBean ptModeloExcelBean;
    
    @Inject
    private PtTipoSaidaBemExcel ptTipoSaidaBemExcel;


//    @Inject
//    LocCountryCodesExcelBean locCountryCodesExcelBean;
//
//    @Inject
//    NotTipoExcelBean notTipoExcelBean;


    /**
     * Creates a new instance of CarregarTabelas
     */
    public PatriExCarregarTabelas()
    {

    }

    /**
     *
     */
    public void carregarTabelas()
    {
//
        ptCategoriaExcelBean.carregar();
        ptFormaAquisicaoExcelBea.carregar();
        ptEstadoConservacaoExcelBean.carregar();
        ptMarcaExcelBean.carregar();
        ptModeloExcelBean.carregar();
        ptTipoSaidaBemExcel.carregar();
    }

}
