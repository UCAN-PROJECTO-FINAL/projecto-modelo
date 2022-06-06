/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contabilidade.beans;

import contabilidade.util.Numero;
import ejbs.entities.CtClass;
import ejbs.entities.CtOcorrenciaContabilidade;
import ejbs.facades.CtClassFacade;
import ejbs.facades.CtOcorrenciaContabilidadeFacade;
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
@Named(value = "classBean")
@ViewScoped
public class ClassBean implements Serializable{

    /**
     * Creates a new instance of ClassBean
     */
    
    @EJB
    private CtOcorrenciaContabilidadeFacade ctOcorrenciacontabilidadeFacade;

    @EJB
    private CtClassFacade ctClassFacade;
    private CtClass classe;
    private CtClass classeSelecionada;
    private Numero numero;
    private Mensagem mensagem = null;

    private List<CtClass> listClass = null;
    private List<Numero> listNumero = null;
    private CtOcorrenciaContabilidade ocorrencia = null;

    private Integer namberClass = 0;

    public ClassBean() {

        this.classe = new CtClass();
        this.listClass = new ArrayList<>();
        this.numero = new Numero();
        this.ocorrencia = new CtOcorrenciaContabilidade();
        this.mensagem = new Mensagem();
        this.listNumero = new ArrayList<>();
    }


    public CtClass getClasse() {
        return classe;
    }

    public void setClasse(CtClass classe) {
        this.classe = classe;
    }

    public List<CtClass> getListClass() {

        this.listClass = new ArrayList<>();

        this.listClass = ctClassFacade.listCategoria();

        if (this.listClass != null) {

            return this.listClass;
        }

        return this.listClass;
    }

    public void setListClass(List<CtClass> listClass) {
        this.listClass = listClass;
    }


    public String gravar() {

        List<CtClass> list = getListClass();
        CtClass cls;

        try {

            this.classe.setDataRegistroClass(new Date());
            this.classe.setStateClass(true);
            //this.classe.setFkContas(this.mensagem.utilizadorLogado());

            //Verifico se o item já está no Banco de Dados
            if (list != null) {
                for (int i = 0; i < list.size(); i++) {

                    cls = list.get(i);

                    if (cls.getDescricaoClass().equalsIgnoreCase(this.classe.getDescricaoClass())) {

                       
                        Mensagem.enviarMensagem(FacesMessage.SEVERITY_ERROR,"A Classe " + classe.getDescricaoClass() + " já existe.",null);
                        return "";

                    }

                }
            }

           
           // this.classe.setFkContas(this.mensagem.utilizadorLogado());
            this.classe.setDataRegistroClass(new Date());
            //this.classe.setDateop(new Date());
            this.ctClassFacade.create(this.classe);
            this.classe = new CtClass();
            this.ocorrencia = new CtOcorrenciaContabilidade();
            Mensagem.enviarMensagem(FacesMessage.SEVERITY_INFO,"Dados salvos com sucesso!",null);
            return "";

        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao salvar os dados, verifique todos os parametros de inserção!", ""));
            ex.printStackTrace();
        }
        return "";
    }

    public String eliminar(int classe) {

        try {

            this.classe = ctClassFacade.find(classe);
            //this.classe.setFkContas(this.mensagem.utilizadorLogado());
            this.classe.setDataRegistroClass(new Date());
            this.classe.setStateClass(false);
            this.ctClassFacade.edit(this.classe);
            this.classe = new CtClass();

            Mensagem.enviarMensagem(FacesMessage.SEVERITY_INFO, "Dados excluídos com sucesso!",null);
            return "";
        } catch (Exception exception) {
            Mensagem.enviarMensagem(FacesMessage.SEVERITY_ERROR, "Impossível excluir o Ano Econômico, entre em contato com o Administrador de Sistemas",null);
            exception.printStackTrace();
        }
        return "";
    }

    public void editar() {

        try {

            
            //this.classe.setFkContas(this.mensagem.utilizadorLogado());
            this.classe.setDataRegistroClass(new Date());
            this.classe.setStateClass(true);
            this.ctClassFacade.edit(this.classe);
            this.classe = new CtClass();
            Mensagem.enviarMensagem(FacesMessage.SEVERITY_INFO,"Dados Actualizados com sucesso!",null);

        } catch (Exception exception) {
            Mensagem.enviarMensagem(FacesMessage.SEVERITY_ERROR,null, "Erro ao gravar os dados, verifique todos os parâmetros de inserção ou entre em contacto o Administrador de Sistemas.");
            exception.printStackTrace();
        }

    }

    public Integer getNamberClass() {
        return namberClass;
    }

    public void setNamberClass(Integer namberClass) {
        this.namberClass = namberClass;
    }

    public List<Numero> getListNumero() {
        return numero.Numeros();
    }

    public void setListNumero(List<Numero> listNumero) {
        this.listNumero = listNumero;
    }

    public CtClass getClasseSelecionada() {
        return classeSelecionada;
    }

    public void setClasseSelecionada(CtClass classeSelecionada) {
        this.classeSelecionada = classeSelecionada;
    }

    public Mensagem getMensagem() {
        return mensagem;
    }

    public void setMensagem(Mensagem mensagem) {
        this.mensagem = mensagem;
    }

    public CtOcorrenciaContabilidade getOcorrencia() {
        return ocorrencia;
    }

    public void setOcorrencia(CtOcorrenciaContabilidade ocorrencia) {
        this.ocorrencia = ocorrencia;
    }

}
