/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package financas.beans;

import ejbs.entities.FinCaixa;
import ejbs.facades.FinCaixaFacade;
import ejbs.facades.FinOperacoesCaixaFacade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import seg.beans.SegLoginBean;
import utils.mensagens.Mensagem;

/**
 *
 * @author majoao
 */
@Named(value = "finCaixaBean")
@ViewScoped
public class FinCaixaBean implements Serializable {

    /**
     * Creates a new instance of FinCaixaBean
     */
   
    @EJB
    private FinCaixaFacade fnCaixaFacade;
    @EJB
    private  FinOperacoesCaixaFacade finOperacoesCaixaFacade;
    
    private FinCaixa caixa = null;
    private List<FinCaixa> listCaixa;
    private List<FinCaixa> list;

    private boolean estado;
    private int codigoCaixa = 0;
    @Inject
    SegLoginBean segLoginBean;
    
    @PostConstruct

    public void init() {
        this.listCaixa = fnCaixaFacade.listCxs();
    }

    public FinCaixaBean() {

        this.caixa = new FinCaixa();
        this.list = new ArrayList<>();
    }

    public FinCaixa getCaixa() {

        return caixa;
    }

    public void setCaixa(FinCaixa caixa) {
        this.caixa = caixa;
    }

    public List<FinCaixa> getListCaixa() {

        this.list = new ArrayList<>();
        this.list = fnCaixaFacade.listCxs();

        if (list != null) {
            return list;
        }
        return list;

    }

    public void setListCaixa(List<FinCaixa> listCaixa) {
        this.listCaixa = listCaixa;
    }

    public String gravar() {

        List<FinCaixa> lista = this.getListCaixa();

        try {

            if (lista != null) {

                for (FinCaixa item : lista) {

                    if (item.getNome().equalsIgnoreCase(this.caixa.getNome())) {
                        Mensagem.enviarMensagem(FacesMessage.SEVERITY_ERROR, "O " + item.getNome() + " já existe!",null);
                        return "";
                    }
                }
            }

            this.caixa.setDataRegistroCaixa(new Date());
            this.caixa.setEstadoCaixa(true);
            this.caixa.setFkUtilizador(segLoginBean.getContaUtilizador());
            this.fnCaixaFacade.create(this.caixa);
            //System.out.print(this.caixa.getPkCaixa());
            this.caixa = new FinCaixa();
            // this.codigoFuncionario = 0;

            Mensagem.enviarMensagem(FacesMessage.SEVERITY_INFO, "O Caixa foi criado com Sucesso! ", null);

            init();
        } catch (Exception ex) {

            Mensagem.enviarMensagem(FacesMessage.SEVERITY_ERROR, null, "Erro ao salvar a verifique todos os parametros de inserção!");
            ex.printStackTrace();
        }
        return "";

    }
    
    private boolean validarCaixaOperacoes(FinCaixa cx){
        return finOperacoesCaixaFacade.getOpcaixasByCaixa(cx) != null;
    }

    public void eliminar(FinCaixa cx) {

        if (validarCaixaOperacoes(cx)) {
            String msg = "Caixa tem operações não pode ser Eliminado !";
            Mensagem.enviarMensagem(FacesMessage.SEVERITY_WARN,null, msg);
        } else {

            try {
                this.caixa = cx;
                this.caixa.setEstadoCaixa(false);
                this.fnCaixaFacade.edit(this.caixa);
                this.caixa = new FinCaixa();
                Mensagem.enviarMensagem(FacesMessage.SEVERITY_INFO,null, "Dados excluídos com sucesso!");

            } catch (Exception exception) {
                Mensagem.enviarMensagem(FacesMessage.SEVERITY_ERROR,null, "Impossível excluir o Caixa, entre em contato com o Administrador de Sistemas");
                exception.printStackTrace();
            }
        }

    }

    public void alterar() {

        try {

            //this.caixa.setDataRegistroCaixa(new Date());
            this.caixa.setFkUtilizador(segLoginBean.getContaUtilizador());
            this.fnCaixaFacade.edit(this.caixa);
            this.caixa = new FinCaixa();
            //this.codigoFuncionario = 0;
            Mensagem.enviarMensagem(FacesMessage.SEVERITY_INFO, null, "Dados Actualizados com sucesso!");

        } catch (Exception exception) {
            Mensagem.enviarMensagem(FacesMessage.SEVERITY_ERROR, null, "Impossível alterar o Diário, entre em contato com o Administrador de Sistemas");
            exception.printStackTrace();
        }

    }

    public List<FinCaixa> getList() {
        return list;
    }

    public void setList(List<FinCaixa> list) {
        this.list = list;
    }

    public int getCodigoCaixa() {
        return codigoCaixa;
    }

    public void setCodigoCaixa(int codigoCaixa) {
        this.codigoCaixa = codigoCaixa;
    }

 
    

}
