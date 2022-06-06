/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grl.beans;

import ejbs.entities.GrlTipoSolicitacao;
import ejbs.facades.GrlTipoSolicitacaoFacade;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author samuel
 */
@Named(value = "tipoSolicitacaoBean")
@ViewScoped
public class TipoSolicitacaoBean implements Serializable
{
    
    
    @EJB
    private GrlTipoSolicitacaoFacade grlTipoSolicitacaoFacade;
    
    private List<GrlTipoSolicitacao> listaGrlTipoSolicitacao;

    /**
     * Creates a new instance of TipoSolicitacaoBean
     */
    public TipoSolicitacaoBean() 
    {
        
    }
    
    @PostConstruct
    public void init()
    {
        listaGrlTipoSolicitacao = grlTipoSolicitacaoFacade.findAllTipoSolicitacaoOrderByDescricao();
    }

    public List<GrlTipoSolicitacao> getListaGrlTipoSolicitacao() {
        return listaGrlTipoSolicitacao;
    }

    public void setListaGrlTipoSolicitacao(List<GrlTipoSolicitacao> listaGrlTipoSolicitacao) {
        this.listaGrlTipoSolicitacao = listaGrlTipoSolicitacao;
    }
    
    
    
}
