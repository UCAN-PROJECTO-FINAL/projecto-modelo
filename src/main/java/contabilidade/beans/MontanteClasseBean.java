/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contabilidade.beans;

import ejbs.entities.CtAnoEconomico;
import ejbs.entities.CtClass;
import ejbs.entities.CtMontanteClasse;
import ejbs.facades.CtAnoEconomicoFacade;
import ejbs.facades.CtClassFacade;
import ejbs.facades.CtMontanteClasseFacade;
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
@Named(value = "montanteClasseBean")
@ViewScoped
public class MontanteClasseBean implements Serializable{

    /**
     * Creates a new instance of MontanteClasseBean
     */
    
    @EJB
    private CtMontanteClasseFacade ctMontanteclasseFacade;

    @EJB
    private CtAnoEconomicoFacade ctAnoeconomicoFacade;

    @EJB
    private CtClassFacade ctClassFacade;

    private CtMontanteClasse montante;
    private CtClass classe;

    private List<CtMontanteClasse> listMontanteClasse = null;
    private List<CtClass> listClasse;
    private List<CtAnoEconomico> listYear = null;
    private Mensagem mensagem = null;

    private int codigoClasse = 0;
    private int codigoYear;
    private boolean estado;
    private double montanteDisponivel = 0.00;

    public MontanteClasseBean() {

        this.montante = new CtMontanteClasse();
        this.classe = new CtClass();
        this.listClasse = new ArrayList<>();
        this.mensagem = new Mensagem();
        this.listYear = new ArrayList<>();
        this.listMontanteClasse = new ArrayList<>();
        this.codigoClasse = 0;
        
//        //#FilipeTuza_2019_06_10
//        CtAnoEconomico anoEconomicoAtual = ctAnoeconomicoFacade.getByAno(Defs.anoActual);
////        this.codigoYear = anoEconomicoAtual.getPkAnoEconomico();
//        montanteDisponivel = anoEconomicoAtual.getValorDisponivel ();
//        //#FilipeTuza_2019_06_10
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
            anoEconomicoAtual = ctAnoeconomicoFacade.getPkAnoEconomico().get(0);
            montanteDisponivel = anoEconomicoAtual.getValorDisponivel ();
            
            System.err.println("ULTIMO EXERCICIO ATIVO : " + ctAnoeconomicoFacade.getPkAnoEconomico().get(0).getAnoEconomico());
        }else{
        
            this.codigoYear = lista.get(0).getPkAnoEconomico();
            anoEconomicoAtual = lista.get(0);
            montanteDisponivel = anoEconomicoAtual.getValorDisponivel ();
            System.err.println("EXERCICIO CORRENTE ATIVO: " + lista.get(0).getAnoEconomico());
        }
        
    }//Fim verificarExercicio
    
    

    public CtMontanteClasse getMontante() {
        return montante;
    }

    public void setMontante(CtMontanteClasse montante) {
        this.montante = montante;
    }

    public CtClass getClasse() {
        return classe;
    }

    public void setClasse(CtClass classe) {
        this.classe = classe;
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
    
    public List<CtClass> getListClasseSemValorNoExercicioEconomico() {

        this.listClasse = new ArrayList<>();

        this.listClasse = ctClassFacade.listCategoriaSemValorNoExercicio(this.codigoYear);

        if (this.listClasse != null) {
            return this.listClasse;
        }

        return this.listClasse;
    }


    public List<CtClass> getListClasse() {

        this.listClasse = new ArrayList<>();

        this.listClasse = ctClassFacade.listCategoria();

        if (this.listClasse != null) {

            return this.listClasse;
        }

        return this.listClasse;
    }

    public void setListClasse(List<CtClass> listClasse) {
        this.listClasse = listClasse;
    }

    public int getCodigoClasse() {
        return codigoClasse;
    }

    public void setCodigoClasse(int codigoClasse) {
        this.codigoClasse = codigoClasse;
    }

    

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String gravar() {

        List<CtMontanteClasse> list = getListMontanteClasse();
        CtMontanteClasse item;

        try {

            if (this.montante.getValorAnualClasse() > getValorAnual(this.codigoYear)) {
                Mensagem.enviarMensagem(null,FacesMessage.SEVERITY_ERROR, "O Montante a ser adicionado na Categoria, é superior ao montante Anual.");
                return "";
            } else if (this.getValorAnual(this.codigoYear) == this.getTotalPorCategoria()) {

                Mensagem.enviarMensagem(null,FacesMessage.SEVERITY_ERROR, "Já não há valores disponíveis para essa Categoria, porque o montante Anual já foi atengido.");
                return "";
            }

            //Verifico se o item já está no Banco de Dados
            if (list != null) {

                for (int i = 0; i < list.size(); i++) {

                    item = list.get(i);
                    if (item.getFkAnoEconomico()!= null&&item.getFkAnoEconomico().getPkAnoEconomico() == this.codigoYear && item.getFkClass().getPkClass() == this.codigoClasse) {
                        Mensagem.enviarMensagem(null,FacesMessage.SEVERITY_ERROR, "No Exercício " + item.getFkAnoEconomico().getAnoEconomico() + ", já existe a Categoria " + item.getFkClass().getDescricaoClass() + ".");
                        return "";

                    }

                }
            }
            
            this.classe = ctClassFacade.find(this.codigoClasse);
            this.montante.setValorDisponivel(this.montante.getValorAnualClasse());
            this.montante.setDataRegistroMontanteClasse(new Date());
            this.montante.setStateMontanteClasse(true);
            this.montante.setFkAnoEconomico(ctAnoeconomicoFacade.find(this.codigoYear));
            this.montante.setFkClass(this.classe);
            this.ctMontanteclasseFacade.create(this.montante);
            this.montante = new CtMontanteClasse();
            this.codigoClasse = 0;
            this.codigoYear = 0;
            this.montanteDisponivel = 0.00;
            Mensagem.enviarMensagem(null,FacesMessage.SEVERITY_INFO, "O Montante foi adicionado com sucesso!");
            return "";
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao adicionar o montante, verifique todos os parametros de inserção!", ""));
            ex.printStackTrace();
        }
        return "";
    }

    public void editar() {

        try {

   
           //this.montante.setUpdat("O montante anual da Categoria  foi alterado  pelo funcionário " + this.mensagem.utilizadorLogado().getFkFuncionario().getFkPessoa().getNome() + ".");
            //this.montante.setDateUpdat(new Date());
            this.montante.setDataRegistroMontanteClasse(new Date());
            this.montante.setStateMontanteClasse(true);
            //this.montante.setFkContas(this.mensagem.utilizadorLogado());
            this.montante.setFkAnoEconomico(ctAnoeconomicoFacade.find(this.montante.getFkAnoEconomico().getPkAnoEconomico()));
            this.montante.setFkClass(ctClassFacade.find(this.montante.getFkClass().getPkClass()));
            this.ctMontanteclasseFacade.edit(this.montante);
            this.montante = new CtMontanteClasse();
            this.codigoClasse = 0;
            this.codigoYear = 0;
            this.valorDisponivelYear();
            Mensagem.enviarMensagem(null,FacesMessage.SEVERITY_INFO, "O Montante foi alterado com sucesso!");

        } catch (Exception exception) {
            Mensagem.enviarMensagem(null,FacesMessage.SEVERITY_INFO, "Erro na alteração do montante, entre em contacto com o seu Administrador de Sistemas.");
            exception.printStackTrace();

        }
        this.estado = false;

    }

    public void eliminar(int montante) {

        try {

            this.montante = ctMontanteclasseFacade.find(montante);
            //this.montante.setFkContas(this.mensagem.utilizadorLogado());
            //this.montante.setDelet("O Funcionário " + this.mensagem.utilizadorLogado().getFkFuncionario().getFkPessoa().getNome() + " excluíu da Categoria "+this.montante.getFkClass().getDescricaoClass()+ " o seu montante anual.");
            //this.montante.setDateDelet(new Date());
            this.montante.setDataRegistroMontanteClasse(new Date());
            this.montante.setStateMontanteClasse(false);
            this.ctMontanteclasseFacade.edit(this.montante);

            Mensagem.enviarMensagem(null,FacesMessage.SEVERITY_INFO, "O Montante foi eliminado com sucesso!");

        } catch (Exception exception) {
            Mensagem.enviarMensagem(null,FacesMessage.SEVERITY_INFO, "Erro ao eliminar o montante, entre em contacto com o seu Administrador de Sistemas.");
            exception.printStackTrace();
        }
    }

    public String alterar(CtMontanteClasse montante) {

        this.montante = montante;

        return "";

    }

    public List<CtAnoEconomico> getListYear() {

        this.listYear = ctAnoeconomicoFacade.listYear();

        return this.listYear;
    }

    public void setListYear(List<CtAnoEconomico> listYear) {
        this.listYear = listYear;
    }

    public int getCodigoYear() {
        return codigoYear;
    }

    public void setCodigoYear(int codigoYear) {
        this.codigoYear = codigoYear;
    }

    public double getValorAnual(int codigoYear) {

        List<CtAnoEconomico> list = ctAnoeconomicoFacade.listYear();

        if (list != null) {

            for (CtAnoEconomico year : list) {

                if (year.getPkAnoEconomico() == codigoYear) {
                    return year.getValorAnual();
                }

            }

        }

        return 0.00;
    }

    public double getTotalPorCategoria() {

        List<CtMontanteClasse> list = ctMontanteclasseFacade.getCategoriaPorYear(new CtAnoEconomico(this.codigoYear));
        double total = 0.00;

        if (list != null) {
            for (CtMontanteClasse categoria : list) {

                total = total + categoria.getValorAnualClasse();

            }

            return total;
        }

        return total;
    }

    public double valorDisponivelYear(){

        CtAnoEconomico objecto = ctAnoeconomicoFacade.find(this.codigoYear);
        double disponivel = 0.00;
        double totalCategoriaYear = this.getTotalPorCategoria();

        if (objecto != null) {

            disponivel = (objecto.getValorAnual() - totalCategoriaYear);
            this.setMontanteDisponivel(disponivel);
           
            return disponivel;
        }

        return disponivel;

    }

    public double getMontanteDisponivel() {

        return montanteDisponivel;
    }

    public void setMontanteDisponivel(double montanteDisponivel) {
        this.montanteDisponivel = montanteDisponivel;
    }
    
}
