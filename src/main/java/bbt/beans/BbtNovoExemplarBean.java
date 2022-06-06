/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bbt.beans;

import ejbs.entities.BbtDocumento;
import ejbs.entities.BbtExemplarDocumento;
import ejbs.facades.BbtDocumentoBbtAutoridadeFacade;
import ejbs.facades.BbtDocumentoFacade;
import ejbs.facades.BbtExemplarDocumentoFacade;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author herman
 */
@Named(value = "bbtNovoExemplarBean")
@ViewScoped
public class BbtNovoExemplarBean implements Serializable
{

    @EJB
    private BbtDocumentoBbtAutoridadeFacade bbtDocumentoBbtAutoridadeFacade;

    @EJB
    private BbtExemplarDocumentoFacade bbtExemplarDocumentoFacade;

    @EJB
    private BbtDocumentoFacade bbtDocumentoFacade;
    
    

    
    /**
     * Creates a new instance of BbtNovoExemplarBean
     */
    
    private List<BbtDocumento> documentos; 
    private List<String> dadosCota; 
    private BbtDocumento documento; 
    private BbtExemplarDocumento exemplarDocumento; 
    
    
    
    private int quantidade; 
    
    public BbtNovoExemplarBean()
    {
    }
    
    @PostConstruct
    public void init()
    {
        documentos = bbtDocumentoFacade.findAll();
    }
    
    
    
    public void dadosExemplar()
    {
        //dadosCota = bbtExemplarDocumentoFacade.getDadosCota(documento);
        
        
        
    }
    
    
    
    public BbtDocumento getDocumento()
    {
        return documento;
    }

    //Getters e Setters
    public void setDocumento(BbtDocumento documento)
    {
        this.documento = documento;
    }

    public List<BbtDocumento> getDocumentos()
    {
        return documentos;
    }

    public void setDocumentos(List<BbtDocumento> documentos)
    {
        this.documentos = documentos;
    }

    public int getQuantidade()
    {
        return quantidade;
    }

    public void setQuantidade(int quantidade)
    {
        this.quantidade = quantidade;
    }
    
    
    
}
