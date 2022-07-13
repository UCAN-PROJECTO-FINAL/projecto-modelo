/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contabilidade.beans;

import ejbs.entities.CtBalancet;
import ejbs.facades.CtBalancetFacade;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import utils.mensagens.Mensagem;

/**
 *
 * @author majoao
 */
@Named(value = "ctMovimentoDataBean")
@ViewScoped
public class MovimentoDataBean implements Serializable {

    /**
     * Creates a new instance of MovimentoDataBean
     */
    @EJB
    private CtBalancetFacade ctBalancetFacade;

    private List<CtBalancet> listaMovData;

    private int opcao;

    private Date dataMovimento;
    private String dataComboSelected;

    public MovimentoDataBean() {
        listaMovData = new ArrayList<>();
        this.opcao = 0;// 1 - DatePicker ; 2 - ComboDatasExistentes
        dataMovimento = new Date();
    }

    public int getOpcao() {
        return opcao;
    }

    public void setOpcao(int opcao) {
        this.opcao = opcao;
    }

    public String getDataComboSelected() {
        return dataComboSelected;
    }

    public void setDataComboSelected(String dataComboSelected) {
        this.dataComboSelected = dataComboSelected;
    }

    public String verMovimento() {
        if (opcao == 1) {
            try {
                dataMovimento = (dataComboSelected == null ) ? new SimpleDateFormat("dd/MM/yyyy").parse("") : new SimpleDateFormat("dd/MM/yyyy").parse(dataComboSelected);
            } catch (ParseException ex) {
                Mensagem.enviarMensagem(null, FacesMessage.SEVERITY_ERROR, ex.getMessage());
                //Logger.getLogger(MovimentoDataBean.class.getName()).log(Level.SEVERE, null, ex);
            }
            //System.out.println("dataMovimento : " + dataMovimento);
        }
        try {
            listaMovData = ctBalancetFacade.listBalancetData(this.dataMovimento);
        } catch (Exception e) {
        }
        

        if (listaMovData == null || listaMovData.isEmpty()) {
            Mensagem.enviarMensagem(null,FacesMessage.SEVERITY_WARN, "Não há movimentos nessa data!");
        }

        return "";
    }

    public Date getDataMovimento() {
        return dataMovimento;
    }

    public void setDataMovimento(Date dataMovimento) {
        this.dataMovimento = dataMovimento;
    }

    public List<CtBalancet> getListaMovData() {
        return listaMovData;
    }

    public void setListaMovData(List<CtBalancet> listaMovData) {
        this.listaMovData = listaMovData;
    }

    public List<String> listaDatasComMovimentos() {

        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        List<String> newList = new ArrayList<>();
        try {
            List<Object> l = ctBalancetFacade.listDatasBalancets();
            
            for (Object object : l) {
                newList.add(df.format(((Date) object)));
            }
        } catch (Exception e) {
        }

        return newList;
    }

    public void subjectSelectionChanged(ValueChangeEvent event) {
        try {
            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            listaMovData = ctBalancetFacade.listBalancetData(df.parse((String) event.getNewValue()));
            System.out.println("inside select Event" + event.getNewValue());
        } catch (ParseException ex) {
            Logger.getLogger(MovimentoDataBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
