/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geral.beans;

//import ejbs.cache.DocDocumentoNecessarioCache;
//import ejbs.cache2.DocDocumentoEntregueCache2;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import utils.mensagens.LogFile;
import utils.parametros_entre_formularios.ParametrosEntreFormulariosBean;

/**
 *
 * @author aires
 */
@Named(value = "appInit")
@ApplicationScoped
public class AppInit
{

    @Inject
    private ParametrosEntreFormulariosBean parametrosEntreFormulariosBean;

//    @Inject
//    private DocDocumentoNecessarioCache docDocumentoNecessarioCache;
//    
//    @Inject
//    private DocDocumentoEntregueCache2 docDocumentoEntregueCache2;
//    
    /**
     * Creates a new instance of AppInit
     */
    public AppInit()
    {
    }

    public void init()
    {
//System.err.println("0: AppInit.init()");        
        LogFile.init();
//System.err.println("1: AppInit.init()");               
    }
}
