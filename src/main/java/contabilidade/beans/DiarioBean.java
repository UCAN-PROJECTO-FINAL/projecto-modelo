/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contabilidade.beans;

import ejbs.entities.CtDiario;
import ejbs.facades.CtDiarioFacade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import utils.mensagens.Mensagem;

/**
 *
 * @author majoao
 */
@Named(value = "diarioBean")
@ViewScoped
public class DiarioBean implements Serializable {

    /**
     * Creates a new instance of DiarioBean
     */
    //interface
    @EJB
    private CtDiarioFacade ctDiarioFacade;

    //Objectos
    private CtDiario diario;

    //Lista
    private List<CtDiario> listDiario = null;

    public DiarioBean() {

        this.diario = new CtDiario();
        this.listDiario = new ArrayList<>();

    }

    public CtDiario getDiario() {
        return diario;
    }

    public void setDiario(CtDiario diario) {
        this.diario = diario;
    }

    public List<CtDiario> getListDiario() {

        this.listDiario = new ArrayList<>();

        this.listDiario = ctDiarioFacade.listDiarios();

        if (this.listDiario != null) {

            return this.listDiario;

        }

        return this.listDiario;
    }

    public void setListDiario(List<CtDiario> listDiario) {
        this.listDiario = listDiario;
    }

    public String alterar(CtDiario diario) {

        this.diario = diario;

        return "";

    }

    public String gravar() {

        List<CtDiario> list = getListDiario();
        CtDiario diar;

        try {

            this.diario.setStateDiario(true);
            //this.diario.setFkContas(this.mensagem.utilizadorLogado());
            this.diario.setDataRegistroDiario(new Date());

            if (list != null) {

                //Verifico se o item já está no Banco de Dados
                for (int i = 0; i < list.size(); i++) {

                    diar = list.get(i);

                    if (diar.getDescricaoDiario().equals(diario.getDescricaoDiario())) {
                        Mensagem.enviarMensagem(FacesMessage.SEVERITY_ERROR, null, "O Diário " + diario.getDescricaoDiario() + " já existe.");
                        return "";

                    }

                }
            }
            this.ctDiarioFacade.create(this.diario);
            this.diario = new CtDiario();
            this.listDiario = new ArrayList<>();
            Mensagem.enviarMensagem(FacesMessage.SEVERITY_INFO,"O Diário foi criado com sucesso!",null);

        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao salvar os dados, verifique todos os parametros de inserção, ou entre em contacto com o Administrador de Sistemas.", ""));
            ex.printStackTrace();
        }

        return "";
    }

    public void eliminar(int diari) {

        try {

            this.diario = ctDiarioFacade.find(diari);
//            this.diario.setFkContas(this.mensagem.utilizadorLogado());
            this.diario.setStateDiario(false);
            ctDiarioFacade.edit(this.diario);
            Mensagem.enviarMensagem(FacesMessage.SEVERITY_INFO, null, " O Diário foi  excluído com sucesso!");

        } catch (Exception exception) {
            Mensagem.enviarMensagem(FacesMessage.SEVERITY_ERROR, null, "Impossível eliminar o Diário. Verifique se está sendo utilizado em algum registro.");
            exception.printStackTrace();
        }
    }

    public void editar() {

        try {

            this.diario.setStateDiario(true);
            //this.diario.setFkContas(this.mensagem.utilizadorLogado());
            this.diario.setDataRegistroDiario(new Date());
            this.ctDiarioFacade.edit(this.diario);
            this.diario = new CtDiario();
            this.listDiario = new ArrayList<>();
            Mensagem.enviarMensagem(FacesMessage.SEVERITY_INFO, null, " O Diário foi  alterado com sucesso!");

        } catch (Exception exception) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Impossível alterar o Diário, entre em contato com o Administrador de Sistemas"));
            exception.printStackTrace();

        }
    }

}
