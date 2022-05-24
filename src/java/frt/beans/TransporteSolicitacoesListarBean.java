/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frt.beans;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import ejbs.entities.FrtTransporteSolicitacao;
import ejbs.facades.FrtTransporteSolicitacaoFacade;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author samuel
 */
@Named(value = "transporteSolicitacoesListarBean")
@ViewScoped
public class TransporteSolicitacoesListarBean implements Serializable
{
    
    @EJB
    private FrtTransporteSolicitacaoFacade frtTransporteSolicitacaoFacade;
    
    private List<FrtTransporteSolicitacao> listaSolicitacoes;
    
    private List<FrtTransporteSolicitacao> listaSolicitacoesEmEspera;
    
    private List<FrtTransporteSolicitacao> listaSolicitacoesPorCodigo;
    
    //private FrtTransporteSolicitacao frtTransporteSolicitacao;

    /**
     * Creates a new instance of TransporteSolicitacoesListarBean
     */
    public TransporteSolicitacoesListarBean() 
    {
        
    }
    
    @PostConstruct
    public void init()
    {
        listaSolicitacoes = frtTransporteSolicitacaoFacade.findAllSolicitacoesOrderByPkSolicitacoes();
        
        listaSolicitacoesEmEspera = frtTransporteSolicitacaoFacade.findAllSolicitacoesEmEspera();
        
        
        //listaSolicitacoesPorCodigo = frtTransporteSolicitacaoFacade.findAllSolicitacoesByPk(frtTransporteSolicitacao.getPkSolicitacao());
    }
    
    
     public List<FrtTransporteSolicitacao> listarItens(int id) {
        System.out.println("CODIGO DOC: " + id);
        List<FrtTransporteSolicitacao> lista = frtTransporteSolicitacaoFacade.findAllSolicitacoesByPk(id);
        System.out.println("LISTA: " + lista);
        if (lista != null && !lista.isEmpty()) {
            for (int i = 0; i < lista.size(); i++) {

                FrtTransporteSolicitacao frtTransporteSolicitacao = lista.get(i);

                if (frtTransporteSolicitacao.getQtdPessoasSolicitacao() == 0) {
                    lista.remove(frtTransporteSolicitacao);
                    i--;
                }
            }
            return lista;
        }

        return new ArrayList<>();

    }

    public List<FrtTransporteSolicitacao> getListaSolicitacoes() {
        return listaSolicitacoes;
    }

    public void setListaSolicitacoes(List<FrtTransporteSolicitacao> listaSolicitacoes) {
        this.listaSolicitacoes = listaSolicitacoes;
    }

    public List<FrtTransporteSolicitacao> getListaSolicitacoesEmEspera()
    {
        return listaSolicitacoesEmEspera;
    }

    public void setListaSolicitacoesEmEspera(List<FrtTransporteSolicitacao> listaSolicitacoesEmEspera)
    {
        this.listaSolicitacoesEmEspera = listaSolicitacoesEmEspera;
    }

    public List<FrtTransporteSolicitacao> getListaSolicitacoesPorCodigo()
    {
        return listaSolicitacoesPorCodigo;
    }

    public void setListaSolicitacoesPorCodigo(List<FrtTransporteSolicitacao> listaSolicitacoesPorCodigo)
    {
        this.listaSolicitacoesPorCodigo = listaSolicitacoesPorCodigo;
    }

//    public FrtTransporteSolicitacao getFrtTransporteSolicitacao()
//    {
//        return frtTransporteSolicitacao;
//    }
//
//    public void setFrtTransporteSolicitacao(FrtTransporteSolicitacao frtTransporteSolicitacao)
//    {
//        this.frtTransporteSolicitacao = frtTransporteSolicitacao;
//    }
    
    
  
}
