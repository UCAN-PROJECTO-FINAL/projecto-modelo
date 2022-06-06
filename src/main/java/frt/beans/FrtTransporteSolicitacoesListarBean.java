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
import ejbs.entities.FrtTransporteSolicitacaoEstado;
import ejbs.facades.FrtTransporteSolicitacaoFacade;

import ejbs.facades.FrtTransporteSolicitacaoEstadoFacade;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author samuel
 */
@Named(value = "transporteSolicitacoesListarBean")
@ViewScoped
public class FrtTransporteSolicitacoesListarBean implements Serializable
{

    @EJB
    private FrtTransporteSolicitacaoFacade frtTransporteSolicitacaoFacade;
    
    @EJB
    private FrtTransporteSolicitacaoEstadoFacade frtTransporteSolicitacaoEstadoFacade;

    private List<FrtTransporteSolicitacao> listaSolicitacoes;

    private List<FrtTransporteSolicitacao> listaSolicitacoesEmEspera;

    private List<FrtTransporteSolicitacao> listaSolicitacoesPorCodigo;
    
    
    private List<FrtTransporteSolicitacaoEstado> listaEstadoSolicitacao;
    
    private FrtTransporteSolicitacaoEstado frtTransporteSolicitacaoEstado;
    private int codigoEstado = 0;
    
    //
     private List<FrtTransporteSolicitacao> listaSolicitacaoDislog;
    

    //private FrtTransporteSolicitacao frtTransporteSolicitacao;
    /**
     * Creates a new instance of TransporteSolicitacoesListarBean
     */
    public FrtTransporteSolicitacoesListarBean()
    {

    }

    @PostConstruct
    public void init()
    {
        listaSolicitacoes = frtTransporteSolicitacaoFacade.findAllSolicitacoesOrderByPkSolicitacoes();

        listaSolicitacoesEmEspera = frtTransporteSolicitacaoFacade.findAllSolicitacoesEmEspera();
        
        listaEstadoSolicitacao = frtTransporteSolicitacaoEstadoFacade.findAllSolicitacaoEstadoOrderByDescricao();
        
        listaSolicitacaoDislog = new ArrayList<>();

        //listaSolicitacoesPorCodigo = frtTransporteSolicitacaoFacade.findAllSolicitacoesByPk(frtTransporteSolicitacao.getPkSolicitacao());
    }

    // Business Methods
    public String corEstado(FrtTransporteSolicitacao dados_solicitacao)
    {
        String estado = dados_solicitacao.getFkTransporteSolicitacaoEstado().getDescricao();
        if (estado.equalsIgnoreCase("Espera"))
        {
            return "yellow";
        }
        if (estado.equalsIgnoreCase("Andamento"))
        {
            return "blue";
        }
        if (estado.equalsIgnoreCase("Finalizado"))
        {
            return "green";
        }
        //Rejeitado
        return "red";

    }

    public void listarItens(int id)
    {
        System.out.println("CODIGO DOC: " + id);
        if(id <= 0 )return;
        
        listaSolicitacaoDislog = frtTransporteSolicitacaoFacade.findAllSolicitacoesByPk(id);
        
        System.out.println("LISTA: " + listaSolicitacaoDislog);
        if (listaSolicitacaoDislog != null && !listaSolicitacaoDislog.isEmpty())
        {
            for (int i = 0; i < listaSolicitacaoDislog.size(); i++)
            {

                FrtTransporteSolicitacao frtTransporteSolicitacao = listaSolicitacaoDislog.get(i);

                if (frtTransporteSolicitacao.getQtdPessoasSolicitacao() == 0)
                {
                    listaSolicitacaoDislog.remove(frtTransporteSolicitacao);
                    i--;
                }
            }
            
              
        }

        

    }

    public List<FrtTransporteSolicitacao> getListaSolicitacoes()
    {
        return listaSolicitacoes;
    }
    
    public void listaSolicitacoesEstado()
    {
        this.listaSolicitacoes = frtTransporteSolicitacaoFacade.findAllSolicitacoesOrderByEstado(this.codigoEstado);
    }

    public void setListaSolicitacoes(List<FrtTransporteSolicitacao> listaSolicitacoes)
    {
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

    public FrtTransporteSolicitacaoEstado getFrtTransporteSolicitacaoEstado()
    {
        return frtTransporteSolicitacaoEstado;
    }

    public void setFrtTransporteSolicitacaoEstado(FrtTransporteSolicitacaoEstado frtTransporteSolicitacaoEstado)
    {
        this.frtTransporteSolicitacaoEstado = frtTransporteSolicitacaoEstado;
    }

    public List<FrtTransporteSolicitacaoEstado> getListaEstadoSolicitacao()
    {
        return listaEstadoSolicitacao;
    }

    public void setListaEstadoSolicitacao(List<FrtTransporteSolicitacaoEstado> listaEstadoSolicitacao)
    {
        this.listaEstadoSolicitacao = listaEstadoSolicitacao;
    }

    public List<FrtTransporteSolicitacao> getListaSolicitacaoDislog()
    {
        System.out.println("listaSolicitacaoDislog:" +listaSolicitacaoDislog.size());
        return listaSolicitacaoDislog == null ? new ArrayList<>() : listaSolicitacaoDislog;
    }

    public void setListaSolicitacaoDislog(List<FrtTransporteSolicitacao> listaSolicitacaoDislog)
    {
        this.listaSolicitacaoDislog = listaSolicitacaoDislog;
    }

    public int getCodigoEstado()
    {
        return codigoEstado;
    }

    public void setCodigoEstado(int codigoEstado)
    {
        this.codigoEstado = codigoEstado;
    }
    
    
    
    
}
