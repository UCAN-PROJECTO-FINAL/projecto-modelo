/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contabilidade.beans;

import ejbs.entities.CtAnoEconomico;
import ejbs.entities.CtClass;
import ejbs.entities.CtMontanteClasse;
import ejbs.entities.CtMontanteRubrica;
import ejbs.entities.CtRubrica;
import ejbs.facades.CtAnoEconomicoFacade;
import ejbs.facades.CtClassFacade;
import ejbs.facades.CtMontanteClasseFacade;
import ejbs.facades.CtMontanteRubricaFacade;
import ejbs.facades.CtRubricaFacade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import utils.Defs;
import utils.mensagens.Mensagem;

/**
 *
 * @author majoao
 */
@Named(value = "montanteRubricaBean")
@ViewScoped
public class MontanteRubricaBean implements Serializable {

    @EJB
    private CtRubricaFacade ctRubricaFacade;

    @EJB
    private CtMontanteRubricaFacade ctMontanterubricaFacade;

    @EJB
    private CtAnoEconomicoFacade ctAnoeconomicoFacade;

    @EJB
    private CtClassFacade ctClassFacade;

    @EJB
    private CtMontanteClasseFacade ctMontanteclasseFacade;

    private CtAnoEconomico year;
    private CtRubrica rubrica;
    private List<CtClass> listClasse;
    private CtMontanteRubrica montante;
    private CtMontanteRubrica montanteSelecionado;
    private CtMontanteClasse mmcl;
    private CtClass classe;

    private List<CtAnoEconomico> listYear = null;
    private List<CtMontanteRubrica> listMontante = null;
    private List<CtRubrica> listRubrica = null;
    private List<CtRubrica> listRubric = null;
    private Mensagem mensagem = null;
    private List<CtMontanteClasse> listMontanteClasse = null;

    private int codigoRubrica = 0;
    private int codigoYear = 0;
    private int codigoClasse = 0;
    private boolean btnSalvar = true;
    private boolean btnFormularioCadastro;
    private boolean tabListar;
    private boolean estado;
    
    private double valorDisponivelClass;

    public MontanteRubricaBean() {
        this.montante = new CtMontanteRubrica();
        this.rubrica = new CtRubrica();
        this.classe = new CtClass();
        this.listClasse = new ArrayList<>();
        this.year = new CtAnoEconomico();
        this.mmcl = new CtMontanteClasse();
        this.listMontante = new ArrayList<>();
        this.montanteSelecionado = new CtMontanteRubrica();
        this.listRubrica = new ArrayList<>();
        this.listRubric = new ArrayList<>();
        this.listMontanteClasse = new ArrayList<>();
        this.listYear = new ArrayList<>();
        this.codigoRubrica = 0;
        this.codigoClasse = 0;
        this.mensagem = new Mensagem();
        
//        //#FilipeTuza_2019_06_10        
//        CtAnoEconomico anoEconomicoAtual = ctAnoeconomicoFacade.getByAno(Defs.anoActual);
//        this.codigoYear = anoEconomicoAtual.getPkAnoEconomico();
        valorDisponivelClass = 0.0;
        //#FilipeTuza_2019_06_10

    }
    
    @PostConstruct
    public void init ()
    {
        verificarExercicio ();
    }
    
   
    public void verificarExercicio ()
    {
        List<CtAnoEconomico> lista = ctAnoeconomicoFacade.findExercicioEconomicoByAno(Defs.ANOACTUAL);
        CtAnoEconomico anoEconomicoAtual;
        
        if (lista.isEmpty()){
        
            this.codigoYear = ctAnoeconomicoFacade.getPkAnoEconomico().get(0).getPkAnoEconomico();
            System.err.println("ULTIMO EXERCICIO ATIVO : " + ctAnoeconomicoFacade.getPkAnoEconomico().get(0).getAnoEconomico());
        }else{
        
            this.codigoYear = lista.get(0).getPkAnoEconomico();
            System.err.println("EXERCICIO CORRENTE ATIVO: " + lista.get(0).getAnoEconomico());
        }
        
    }//Fim verificarExercicio

    public double getValorDisponivelClass() {
        
        if (codigoClasse == 0)
            return 0;
        
        double montanteclass = ctMontanteclasseFacade.findByYearAndClass(this.codigoYear, codigoClasse).getValorAnualClasse();
        
        double disponibilizado = ctMontanterubricaFacade.getTotalDisponibilizado(this.codigoYear, codigoClasse);
        
        
        this.valorDisponivelClass = montanteclass - disponibilizado;
        return valorDisponivelClass;
    }
    
    

    public CtAnoEconomico getYear() {
        return year;
    }

    public void setYear(CtAnoEconomico year) {
        this.year = year;
    }

    public CtRubrica getRubrica() {
        return rubrica;
    }

    public void setRubrica(CtRubrica rubrica) {
        this.rubrica = rubrica;
    }

    public CtMontanteRubrica getMontante() {
        return montante;
    }

    public void setMontante(CtMontanteRubrica montante) {
        this.montante = montante;
    }

    public List<CtAnoEconomico> getListYear() {

        this.listYear = ctAnoeconomicoFacade.listYear();

        return this.listYear;

    }

    public void setListYear(List<CtAnoEconomico> listYear) {
        this.listYear = listYear;
    }

    public List<CtMontanteRubrica> getListMontante() {

        this.listMontante = new ArrayList<>();

        this.listMontante = ctMontanterubricaFacade.listMontanteRubrica();

        if (this.listMontante != null) {

            return this.listMontante;
        }

        return this.listMontante;

    }

    public void setListMontante(List<CtMontanteRubrica> listMontante) {
        this.listMontante = listMontante;
    }

    public List<CtRubrica> getListRubrica() {

        this.listRubrica = new ArrayList<>();

        this.listRubrica = ctRubricaFacade.getRubricaPorClasse(new CtClass(codigoClasse));

        if (this.listRubrica != null) {
            return this.listRubrica;
        }

        return this.listRubrica;
    }
    
    public List<CtRubrica> getListRubricaSemValorDefinidoNoExcercicioEconomico() {

        this.listRubrica = new ArrayList<>();

        this.listRubrica = ctRubricaFacade.getRubricaPorClasseSemValorDefinidoNoExercicio(codigoClasse, this.codigoYear);

        if (this.listRubrica != null) {
            return this.listRubrica;
        }

        return this.listRubrica;
    }
    
    

    public void setListRubrica(List<CtRubrica> listRubrica) {
        this.listRubrica = listRubrica;
    }

    public int getCodigoRubrica() {
        return codigoRubrica;
    }

    public void setCodigoRubrica(int codigoRubrica) {
        this.codigoRubrica = codigoRubrica;
    }

    public int getCodigoYear() {
        return codigoYear;
    }

    public void setCodigoYear(int codigoYear) {
        this.codigoYear = codigoYear;
    }

    public boolean isBtnSalvar() {
        return btnSalvar;
    }

    public void setBtnSalvar(boolean btnSalvar) {
        this.btnSalvar = btnSalvar;
    }

    public boolean isBtnFormularioCadastro() {
        return btnFormularioCadastro;
    }

    public void setBtnFormularioCadastro(boolean btnFormularioCadastro) {
        this.btnFormularioCadastro = btnFormularioCadastro;
    }

    public boolean isTabListar() {
        return tabListar;
    }

    public void setTabListar(boolean tabListar) {
        this.tabListar = tabListar;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    //Metodos para formulário
    public void voltar() {

        this.btnSalvar = true;
        this.btnFormularioCadastro = false;
        this.tabListar = false;
        this.estado = false;
        this.montante = new CtMontanteRubrica();

    }

    public void novo() {

        this.btnSalvar = false;
        this.btnFormularioCadastro = true;
        this.tabListar = false;
        this.estado = false;
        this.montante = new CtMontanteRubrica();

    }

    public void btnListar() {

        this.tabListar = true;
        this.btnFormularioCadastro = false;
        this.btnSalvar = true;
        this.estado = false;
    }

    public void btnEditar() {

        this.estado = true;
        this.tabListar = false;

    }

    public double getValorAnual(int codigoClasse) {

        List<CtMontanteClasse> list = ctMontanteclasseFacade.listMontanteClasse();

        if (list != null) {

            for (CtMontanteClasse yr : list) {

                if (yr.getFkClass().getPkClass() == codigoClasse) {
                    return yr.getValorAnualClasse();
                }

            }
        }
        return 0.00;
    }

    public String gravar() {

        List<CtMontanteRubrica> list = getListMontante();
        CtMontanteRubrica item;

        try {

            if (this.montante.getValorAnualRubrica() > getValorAnual(this.codigoClasse)) {
                Mensagem.enviarMensagem(null,FacesMessage.SEVERITY_ERROR, "O Montante a ser adicionado na Rubrica, é superior ao montante da Categoria.");
                return "";
            } else if (this.getValorAnual(this.codigoClasse) == this.getTotalPorRubrica()) {

                Mensagem.enviarMensagem(null,FacesMessage.SEVERITY_ERROR, "Já não há valores disponíveis para esta Rubrica, porque o montante máximo da Categoria já foi atingido.");
                return "";
            }

            //Verifico se o item já está no Banco de Dados
            if (list != null) {

                for (int i = 0; i < list.size(); i++) {

                    item = list.get(i);
                    
                    //#FilipeTuza_2019_04_25
                    if (item.getFkAnoEconomico()!= null&&item.getFkAnoEconomico().getPkAnoEconomico() == this.codigoYear && item.getFkRubrica().getPkRubrica() == this.codigoRubrica) {
                    //#FilipeTuza_2019_04_25
                    
                        Mensagem.enviarMensagem(null,FacesMessage.SEVERITY_ERROR, "No Exercício " + item.getFkAnoEconomico().getAnoEconomico() + ", já existe uma Rubrica " + item.getFkRubrica().getDescricaoRubrica() + ".");
                        return "";

                    }

                }

            }

            this.montante.setStateMontanteRubrica(true);
            this.montante.setValorDisponivel(this.montante.getValorAnualRubrica());
            this.montante.setValorAnualRubrica(this.montante.getValorAnualRubrica());
            //this.montante.setFkContas(this.mensagem.utilizadorLogado());
            this.montante.setRegistroMontanteRubrica(new Date());
            this.montante.setFkAnoEconomico(ctAnoeconomicoFacade.find(this.codigoYear));
            this.montante.setFkRubrica(ctRubricaFacade.find(this.codigoRubrica));
            this.ctMontanterubricaFacade.create(this.montante);
            this.montante = new CtMontanteRubrica();
            this.codigoClasse = 0;
            this.codigoYear = 0;
            this.codigoRubrica = 0;
            Mensagem.enviarMensagem(FacesMessage.SEVERITY_INFO, "O Montante foi adicionado com sucesso!",null);

        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao adicionar o montante, verifique todos os parametros de inserção!", ""));
            ex.printStackTrace();
        }
        return "";
    }

    public void editar() {

        try {

            this.montanteSelecionado.setStateMontanteRubrica(true);
            //this.montanteSelecionado.setFkContas(this.mensagem.utilizadorLogado());
            this.montanteSelecionado.setRegistroMontanteRubrica(new Date());
            this.montanteSelecionado.setFkAnoEconomico(ctAnoeconomicoFacade.find(this.montanteSelecionado.getFkAnoEconomico().getPkAnoEconomico()));
            this.montanteSelecionado.setFkRubrica(ctRubricaFacade.find(this.montanteSelecionado.getFkRubrica().getPkRubrica()));
            this.ctMontanterubricaFacade.edit(this.montanteSelecionado);
            this.montanteSelecionado = new CtMontanteRubrica();
            this.codigoClasse = 0;
            this.codigoYear = 0;
            this.codigoRubrica = 0;
            Mensagem.enviarMensagem(null,FacesMessage.SEVERITY_INFO, "O Montante foi alterado com sucesso!");

        } catch (Exception exception) {
            Mensagem.enviarMensagem(null,FacesMessage.SEVERITY_ERROR, "Erro na alteração do montante, entre em contacto com o seu Administrador de Sistemas.");
            exception.printStackTrace();

        }
        this.estado = false;
        btnListar();

    }

    public void eliminar(int montante) {

        try {

            this.montante = ctMontanterubricaFacade.find(montante);
//            this.montante.setFkContas(this.mensagem.utilizadorLogado());
            this.montante.setStateMontanteRubrica(false);
            this.ctMontanterubricaFacade.edit(this.montante);
            Mensagem.enviarMensagem(null,FacesMessage.SEVERITY_INFO, "O Montante foi eliminado com sucesso!");

        } catch (Exception exception) {
            Mensagem.enviarMensagem(null,FacesMessage.SEVERITY_INFO, "Erro ao eliminar o montante, entre em contacto com o seu Administrador de Sistemas.");
            exception.printStackTrace();
        }
    }

    public String alterar(CtMontanteRubrica montante){

        btnEditar();
        this.montanteSelecionado = montante;
        return "";

    }

    public List<CtClass> getListClasse() {

        this.listClasse = new ArrayList<>();

        this.listClasse = ctClassFacade.listCategoriaValorRubrica();

        if (this.listClasse != null) {

            return this.listClasse;
        }

        return this.listClasse;
    }

    public void setListClasse(List<CtClass> listClasse) {
        this.listClasse = listClasse;
    }

    public CtClass getClasse() {
        return classe;
    }

    public void setClasse(CtClass classe) {
        this.classe = classe;
    }

    public int getCodigoClasse() {
        return codigoClasse;
    }

    public void setCodigoClasse(int codigoClasse) {
        this.codigoClasse = codigoClasse;
    }

    public double getTotalPorRubrica() {

        List<CtMontanteRubrica> list = ctMontanterubricaFacade.getRubricaPorYear(new CtAnoEconomico(this.codigoYear));
        double total = 0.00;

        if (list != null) {

            for (CtMontanteRubrica rub : list) {

                total = total + rub.getValorAnualRubrica();

            }

            return total;
        }

        return total;
    }

    public double valorDisponivel() {

        List<CtMontanteRubrica> list = ctMontanterubricaFacade.getRubricaPorYear(new CtAnoEconomico(this.codigoYear));
        double total = this.getTotalPorRubrica();
        double op = 0.00;

        if (list != null) {

            for (CtMontanteRubrica rub : list) {

                if (rub.getFkRubrica().getFkClass().getPkClass() == this.codigoClasse) {
                    op = (rub.getValorAnualRubrica() - total);

                    return op;
                }

            }

        }

        return op;

    }

    public List<CtMontanteClasse> getListMontanteClasse() {

        this.listMontanteClasse = new ArrayList<>();

        this.listMontanteClasse = ctMontanteclasseFacade.listMontanteClasse();

        if (this.listMontanteClasse != null) {

            return this.listMontanteClasse;

        }

        return this.listMontanteClasse;
    }

    public void setListMontanteClasse(List<CtMontanteClasse> listMontanteClasse) {
        this.listMontanteClasse = listMontanteClasse;
    }

    public CtMontanteClasse getMmcl() {
        return mmcl;
    }

    public void setMmcl(CtMontanteClasse mmcl) {
        this.mmcl = mmcl;
    }

    public CtMontanteRubrica getMontanteSelecionado() {
        return montanteSelecionado;
    }

    public void setMontanteSelecionado(CtMontanteRubrica montanteSelecionado) {
        this.montanteSelecionado = montanteSelecionado;
    }

    public List<CtRubrica> getListRubric() {
        int i = this.montanteSelecionado.getFkRubrica().getFkClass().getPkClass();
        return ctRubricaFacade.getRubricaPoClasseAlterar(i);
    }

    public void setListRubric(List<CtRubrica> listRubric) {
        this.listRubric = listRubric;
    }

    public Mensagem getMensagem() {
        return mensagem;
    }

    public void setMensagem(Mensagem mensagem) {
        this.mensagem = mensagem;
    }

}