/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contabilidade.beans;

import ejbs.entities.CtAnoEconomico;
import ejbs.entities.CtMontanteClasse;
import ejbs.facades.CtAnoEconomicoFacade;
import ejbs.facades.CtMontanteClasseFacade;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import utils.Defs;

/**
 *
 * @author majoao
 */
@Named(value = "ctDespesaCategoriaBean")
@ViewScoped
public class CtDespesaCategoriaBean implements Serializable{

    /**
     * Creates a new instance of CtDespesaCategoriaBean
     */
     @EJB
    private CtAnoEconomicoFacade ctAnoeconomicoFacade;
     @EJB
    private CtMontanteClasseFacade ctMontanteclasseFacade;
    private int codigoYear;
    private List<CtMontanteClasse> listaMontanteClasses;

    private double totalAnualClasses, totalSaldoUtilizado, totalSaldoDisponivel;

    public CtDespesaCategoriaBean() {
        listaMontanteClasses = new ArrayList<>();
        this.totalAnualClasses = 0.00;
        this.totalSaldoDisponivel = 0.00;
        this.totalSaldoUtilizado = 0.00;
       // this.codigoYear = ctAnoeconomicoFacade.getByAno(Defs.anoActual).getPkAnoEconomico();
    }

    @PostConstruct
    public void init ()
    {
        verificarExercicio ();
    }
    
    
    public void verificarExercicio ()
    {
        List<CtAnoEconomico> lista = ctAnoeconomicoFacade.findExercicioEconomicoByAno(getAno());
        
        if (lista.isEmpty()){
        
            this.codigoYear = ctAnoeconomicoFacade.getPkAnoEconomico().get(0).getPkAnoEconomico();
            System.err.println("ULTIMO EXERCICIO ATIVO : " + ctAnoeconomicoFacade.getPkAnoEconomico().get(0).getAnoEconomico());
        }else{
        
            this.codigoYear = lista.get(0).getPkAnoEconomico();
            System.err.println("EXERCICIO CORRENTE ATIVO: " + lista.get(0).getAnoEconomico());
        }
        
    }//Fim verificarExercicio
    
    private int getAno() {
        DateFormat formatar = new SimpleDateFormat("yyyy");
        return Integer.parseInt(formatar.format(new Date()));
    }
    
    public int getCodigoYear() {
        return codigoYear;
    }

    public void setCodigoYear(int codigoYear) {
        this.codigoYear = codigoYear;
    }

    public List<CtAnoEconomico> getListYear() {
        return ctAnoeconomicoFacade.listYear();
    }

    public void actualizarListDespesaCategoria() {
        this.listaMontanteClasses = ctMontanteclasseFacade.getCategoriaPorYear(new CtAnoEconomico(codigoYear));
    }

    public List<CtMontanteClasse> getListDespesaCategoria() {
        return this.listaMontanteClasses;
    }

    public double getTotalAnualClasses() {
        this.totalAnualClasses = 0.00;
        if (listaMontanteClasses != null) {
            for (CtMontanteClasse ctMontanteClasse : listaMontanteClasses) {
                this.totalAnualClasses += ctMontanteClasse.getValorAnualClasse();
            }
        }
        return totalAnualClasses;
    }

    public double getTotalSaldoDisponivel() {
        this.totalSaldoDisponivel = 0.00;
        if (listaMontanteClasses != null) {
            for (CtMontanteClasse ctMontanteClasse : listaMontanteClasses) {
                this.totalSaldoDisponivel += ctMontanteClasse.getValorDisponivel();
            }
        }
        return totalSaldoDisponivel;
    }

    public double getTotalSaldoUtilizado() {
        this.totalSaldoUtilizado = 0.00;
        this.totalSaldoUtilizado = this.totalAnualClasses - this.totalSaldoDisponivel;
        return totalSaldoUtilizado;
    }

    
}
