/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contabilidade.beans;

import contabilidade.util.Despesa;
import ejbs.entities.CtAnoEconomico;
import ejbs.entities.CtBalancet;
import ejbs.entities.CtMontanteClasse;
import ejbs.entities.CtMontanteRubrica;
import ejbs.facades.CtAnoEconomicoFacade;
import ejbs.facades.CtBalancetFacade;
import ejbs.facades.CtMontanteClasseFacade;
import ejbs.facades.CtMontanteRubricaFacade;
import java.io.Serializable;
import java.util.ArrayList;
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
@Named(value = "despesaBean")
@ViewScoped
public class DespesaBean implements Serializable{

    /**
     * Creates a new instance of DespesaBean
     */
    
    @EJB
    private CtMontanteClasseFacade ctMontanteclasseFacade;

    @EJB
    private CtAnoEconomicoFacade ctAnoeconomicoFacade;

    @EJB
    private CtMontanteRubricaFacade ctMontanterubricaFacade;
    @EJB
    private CtBalancetFacade ctBalancetFacade;

    private CtAnoEconomico ye;

    //@ManagedProperty(value = "#{relatorioBeanCT}")
    //RelatorioBeanCT relatorioBeanCT;

//    public RelatorioBeanCT getRelatorioBeanCT() {
//        return relatorioBeanCT;
//    }
//
//    public void setRelatorioBeanCT(RelatorioBeanCT relatorioBeanCT) {
//        this.relatorioBeanCT = relatorioBeanCT;
//    }

    private List<CtMontanteRubrica> listMontanteRubrica = null;
    private List<CtMontanteClasse> listMontanteClasse = null;
    private List<Despesa> listDespesa = null;
    private List<Despesa> listDespesaTotais = null;
    private List<Despesa> listTotaisDespesas = null;
    private List<Despesa> listAux = null;
    private List<Despesa> listDespesaYear = null;
    private List<Despesa> listDespesaCategoria = null;
    private List<CtBalancet> listBalancet = null;
    private List<CtAnoEconomico> listYear = null;
    private int codigoYear;
    private double credito = 0.00;
    private double saldoDisponivel = 0.00;
    private double saldoTotalValorAprovado = 0.00;
    private double totalSaldoUtilizado = 0.00;
    private double saldoTotalSaldoDisponivel = 0.00;
    private double totalSaldoDisponivel = 0.00;
    private double saldoTotal = 0.00;
    private double totalSaldoAnual = 0.00;
    private double graudexe = 0.00;

    public DespesaBean() {

        this.listDespesa = new ArrayList<>();
        this.listDespesaTotais = new ArrayList<>();
        this.listTotaisDespesas = new ArrayList<>();
        this.listDespesaYear = new ArrayList<>();
        this.listDespesaCategoria = new ArrayList<>();
        this.listAux = new ArrayList<>();
        this.ye = new CtAnoEconomico();
        this.listMontanteRubrica = new ArrayList<>();
        this.listMontanteClasse = new ArrayList<>();
        this.listBalancet = new ArrayList<>();
        this.listYear = new ArrayList<>();

        this.credito = 0.00;
        this.saldoDisponivel = 0.00;

        //this.listYear = this.getListYear();
//        this.codigoYear = ctAnoeconomicoFacade.getByAno(Defs.anoActual).getPkAnoEconomico();
    }

    
    @PostConstruct
    public void init ()
    {
        verificarExercicio ();
    }
    
    
    public void verificarExercicio ()
    {
        List<CtAnoEconomico> lista = ctAnoeconomicoFacade.findExercicioEconomicoByAno(Defs.ANOACTUAL);
        
        if (lista.isEmpty()){
        
            this.codigoYear = ctAnoeconomicoFacade.getPkAnoEconomico().get(0).getPkAnoEconomico();
            System.err.println("ULTIMO EXERCICIO ATIVO : " + ctAnoeconomicoFacade.getPkAnoEconomico().get(0).getAnoEconomico());
        }else{
        
            this.codigoYear = lista.get(0).getPkAnoEconomico();
            System.err.println("EXERCICIO CORRENTE ATIVO: " + lista.get(0).getAnoEconomico());
        }
        
    }//Fim verificarExercicio
    
    
    public List<CtMontanteRubrica> getListMontanteRubrica() {
        return listMontanteRubrica;
    }

    public void setListMontanteRubrica(List<CtMontanteRubrica> listMontanteRubrica) {
        this.listMontanteRubrica = listMontanteRubrica;
    }

    public List<Despesa> getListDespesa() {
        return listDespesa;
    }

    public void setListDespesa(List<Despesa> listDespesa) {
        this.listDespesa = listDespesa;
    }

    public List<CtBalancet> getListBalancet() {
        return listBalancet;
    }

    public void setListBalancet(List<CtBalancet> listBalancet) {
        this.listBalancet = listBalancet;
    }

    public void list() {

        List<Despesa> collection = new ArrayList<>();
        List<Despesa> collectionTotais = new ArrayList<>();
        this.listMontanteRubrica = ctMontanterubricaFacade.getRubricaPorYear(new CtAnoEconomico(codigoYear));
        this.listBalancet = ctBalancetFacade.getBalancetPorYear(new CtAnoEconomico(codigoYear));
        this.credito = 0.00;
        this.saldoDisponivel = 0.00;
        this.totalSaldoAnual = 0.00;
        this.totalSaldoUtilizado = 0.00;
        this.totalSaldoDisponivel = 0.00;
        Despesa desp;
        int codigoRubrica = 0;

        if (this.listMontanteRubrica != null && this.listBalancet != null) {

            for (CtMontanteRubrica mont : this.listMontanteRubrica) {
                desp = new Despesa();
                codigoRubrica = mont.getFkRubrica().getPkRubrica();

                for (CtBalancet bal : listBalancet) {

                    if (codigoRubrica == bal.getFkAccount().getFkRubrica().getPkRubrica()) {

                        this.credito = this.credito + bal.getCreditoBalancet();
                    }

                }

                this.totalSaldoAnual = (this.totalSaldoAnual + mont.getValorAnualRubrica());
                this.totalSaldoUtilizado = (this.totalSaldoUtilizado + this.credito);
                this.saldoDisponivel = (mont.getValorAnualRubrica() - this.credito);

                this.totalSaldoDisponivel = (this.totalSaldoDisponivel + this.saldoDisponivel);

                desp.setExercicio(mont.getFkAnoEconomico().getAnoEconomico());
                desp.setCodigo(mont.getFkRubrica().getNumberRubrica());
                desp.setDescricao(mont.getFkRubrica().getDescricaoRubrica());
                desp.setSaldoAnual(mont.getValorAnualRubrica());
                desp.setSaldoDisponivel(this.saldoDisponivel);
                desp.setSaldoUtilizado(this.credito);
                collection.add(desp);

                this.saldoDisponivel = 0.00;
                this.credito = 0.00;

            }

            setListDespesa(collection);

            // preenchendo a lista dos totais
            desp = new Despesa();
            desp.setTotais("Totais");
            desp.setTotalSaldoAnual(this.totalSaldoAnual);
            desp.setTotalSaldoUtilizado(this.totalSaldoUtilizado);
            desp.setTotalSaldoDisponivel(this.totalSaldoDisponivel);
            collectionTotais.add(desp);
            this.setListDespesaTotais(collectionTotais);
        } else {

            //limpamos as listas e passamos uma lista vazia...
            collection.clear();
            setListDespesa(collection);

            collectionTotais.clear();
            setListDespesaTotais(collectionTotais);
        }
    }

    public void listDespesaAnual() {

        List<Despesa> collection = new ArrayList<>();
        Despesa desp = new Despesa();

        this.ye = ctAnoeconomicoFacade.find(codigoYear);
        this.credito = ctBalancetFacade.getBalancetTotalCreditoPorYear(codigoYear);
        this.saldoDisponivel = (ye.getValorAnual() - this.credito);

        desp.setExercicio(ye.getAnoEconomico());
        desp.setSaldoAnual(ye.getValorAnual());
        desp.setSaldoDisponivel(this.saldoDisponivel);
        desp.setSaldoUtilizado(this.credito);
        collection.add(desp);
        setListDespesaYear(collection);
    }

    public int getCodigoYear() {
        return codigoYear;
    }

    public void setCodigoYear(int codigoYear) {
        this.codigoYear = codigoYear;
    }

    public List<CtAnoEconomico> getListYear() {

        this.listYear = ctAnoeconomicoFacade.listYear();

        return this.listYear;
    }

    public void setListYear(List<CtAnoEconomico> listYear) {
        this.listYear = listYear;
    }

    public List<CtMontanteClasse> getListMontanteClasse() {
        return listMontanteClasse;
    }

    public void setListMontanteClasse(List<CtMontanteClasse> listMontanteClasse) {
        this.listMontanteClasse = listMontanteClasse;
    }

    public List<Despesa> getListDespesaCategoria() {
        return listDespesaCategoria;
    }

    public void setListDespesaCategoria(List<Despesa> listDespesaCategoria) {
        this.listDespesaCategoria = listDespesaCategoria;
    }

    public List<Despesa> getListDespesaYear() {
        return listDespesaYear;
    }

    public void setListDespesaYear(List<Despesa> listDespesaYear) {
        this.listDespesaYear = listDespesaYear;
    }

    public void setListAux(List<Despesa> listAux) {
        this.listAux = listAux;
    }

    public CtAnoEconomico getYe() {
        return ye;
    }

    public void setYe(CtAnoEconomico ye) {
        this.ye = ye;
    }

    public List<Despesa> getListTotaisDespesas() {
        return listTotaisDespesas;
    }

    public void setListTotaisDespesas(List<Despesa> listTotaisDespesas) {
        this.listTotaisDespesas = listTotaisDespesas;
    }

    public double getSaldoTotalValorAprovado() {
        return saldoTotalValorAprovado;
    }

    public void setSaldoTotalValorAprovado(double saldoTotalValorAprovado) {
        this.saldoTotalValorAprovado = saldoTotalValorAprovado;
    }

    public double getSaldoTotal() {
        return saldoTotal;
    }

    public void setSaldoTotal(double saldoTotal) {
        this.saldoTotal = saldoTotal;
    }

    public double getSaldoTotalSaldoDisponivel() {
        return saldoTotalSaldoDisponivel;
    }

    public void setSaldoTotalSaldoDisponivel(double saldoTotalSaldoDisponivel) {
        this.saldoTotalSaldoDisponivel = saldoTotalSaldoDisponivel;
    }

    public double getTotalSaldoUtilizado() {
        return totalSaldoUtilizado;
    }

    public void setTotalSaldoUtilizado(double totalSaldoUtilizado) {
        this.totalSaldoUtilizado = totalSaldoUtilizado;
    }

    public double getTotalSaldoAnual() {
        return totalSaldoAnual;
    }

    public void setTotalSaldoAnual(double totalSaldoAnual) {
        this.totalSaldoAnual = totalSaldoAnual;
    }

    public List<Despesa> getListDespesaTotais() {
        return listDespesaTotais;
    }

    public void setListDespesaTotais(List<Despesa> listDespesaTotais) {
        this.listDespesaTotais = listDespesaTotais;
    }

    public double getGraudexe() {
        return graudexe;
    }

    public void setGraudexe(double graudexe) {
        this.graudexe = graudexe;
    }

//    public void emitirReportDepesaAnualCategoria() {
//        relatorioBeanCT.emitirReportDepesaAnualCategoria(this.codigoYear);
//    }

    
}
