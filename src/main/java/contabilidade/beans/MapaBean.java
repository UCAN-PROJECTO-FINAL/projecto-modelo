/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contabilidade.beans;

import contabilidade.util.MapaDespesaPorNaturaza;
import contabilidade.util.MapaProgramacao;
import ejbs.entities.CtAccount;
import ejbs.entities.CtAnoEconomico;
import ejbs.entities.CtBalancet;
import ejbs.entities.CtMontanteClasse;
import ejbs.entities.CtMontanteRubrica;
import ejbs.entities.CtRubrica;
import ejbs.facades.CtAccountFacade;
import ejbs.facades.CtAnoEconomicoFacade;
import ejbs.facades.CtBalancetFacade;
import ejbs.facades.CtClassFacade;
import ejbs.facades.CtMontanteClasseFacade;
import ejbs.facades.CtMontanteRubricaFacade;
import ejbs.facades.CtRubricaFacade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import utils.Defs;
import utils.mensagens.Mensagem;

/**
 *
 * @author majoao
 */
@Named(value = "mapaBean")
@ViewScoped
public class MapaBean implements Serializable{

    /**
     * Creates a new instance of MapaBean
     */
    @EJB
    private CtBalancetFacade ctBalancetFacade;

    @EJB
    private CtMontanteRubricaFacade ctMontanterubricaFacade;

    @EJB
    private CtClassFacade ctClassFacade;

    @EJB
    private CtAnoEconomicoFacade ctAnoeconomicoFacade;

    @EJB
    private CtMontanteClasseFacade ctMontanteclasseFacade;

    @EJB
    private CtAccountFacade ctAccountFacade;

    @EJB
    private CtRubricaFacade ctRubricaFacade;
//
//    @ManagedProperty( value = "#{relatorioBeanCT}" )
//    RelatorioBeanCT relatorioBeanCT;
//
//    public RelatorioBeanCT getRelatorioBeanCT ()
//    {
//        return relatorioBeanCT;
//    }
//
//    public void setRelatorioBeanCT ( RelatorioBeanCT relatorioBeanCT )
//    {
//        this.relatorioBeanCT = relatorioBeanCT;
//    }

    //objectos
    private CtRubrica rubrica;
    private CtBalancet balancet;
    private CtAnoEconomico anoeconomico;
    private MapaProgramacao mapa;
    private MapaDespesaPorNaturaza mapaDespesaPorNaturezaAux = null;
    //listas
    private List<CtRubrica> listRubrica = null;
    private List<CtMontanteRubrica> listMontanteRubrica = null;
    private List<CtMontanteClasse> listCategoriaDespesa = null;
    private List<CtBalancet> listBalancet = null;
    private List<CtAnoEconomico> listAnoEconomico = null;
    private List<MapaProgramacao> listMapa = null;
    private List<MapaProgramacao> listMapaAux = null;
    private List<MapaDespesaPorNaturaza> listMapaDespesaPorNatureza = null;
    private List<MapaDespesaPorNaturaza> listTotaisMapaDespesaPorNatureza = null;
    private List<MapaDespesaPorNaturaza> list = null;

    private List<CtAccount> listAccount = null;
    private List<CtMontanteClasse> listCategoria = null;

    // variáveis
    private double totalDebito = 0.00;
    private double totalCredito = 0.00;
    private double totalSaldoDevedor = 0.00;
    private double totalSaldoCredor = 0.00;
    private double totalSaldoDA = 0.00;
    private double totalSaldoRD = 0.00;
    private double totalSaldoDC = 0.00;
    private double totalSaldoDP = 0.00;
    private double debito = 0.00;
    private double credito = 0.00;
    private double saldoDevedor = 0.00;
    private double saldoCredor = 0.00;
    private int codigoYaer = 0;
    private int codigoClasse = 0;
    private int codigoRubrica = 0;
    private int codigoConta = 0;

    public MapaBean ()
    {

        this.listAnoEconomico = new ArrayList<> ();
        this.listBalancet = new ArrayList<> ();
        this.listMapa = new ArrayList<> ();
        this.listMapaAux = new ArrayList<> ();
        this.listRubrica = new ArrayList<> ();
        this.listMontanteRubrica = new ArrayList<> ();
        this.list = new ArrayList<> ();
        this.listMapaDespesaPorNatureza = new ArrayList<> ();
        this.listTotaisMapaDespesaPorNatureza = new ArrayList<> ();
        this.listCategoriaDespesa = new ArrayList<> ();
        this.listAccount = new ArrayList<> ();
        this.listCategoria = new ArrayList<> ();
        this.mapa = new MapaProgramacao ();
        this.anoeconomico = new CtAnoEconomico ();
        this.balancet = new CtBalancet ();
        this.rubrica = new CtRubrica ();
        this.codigoRubrica = 0;

//        this.codigoYaer = ctAnoeconomicoFacade.getByAno ( Defs.anoActual ).getPkAnoEconomico ();
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
        
            this.codigoYaer = ctAnoeconomicoFacade.getPkAnoEconomico().get(0).getPkAnoEconomico();
            System.err.println("ULTIMO EXERCICIO ATIVO : " + ctAnoeconomicoFacade.getPkAnoEconomico().get(0).getAnoEconomico());
        }else{
        
            this.codigoYaer = lista.get(0).getPkAnoEconomico();
            System.err.println("EXERCICIO CORRENTE ATIVO: " + lista.get(0).getAnoEconomico());
        }
        
    }//Fim verificarExercicio
    

    public CtRubrica getRubrica ()
    {
        return rubrica;
    }

    public void setRubrica ( CtRubrica rubrica )
    {
        this.rubrica = rubrica;
    }

    public CtBalancet getBalancet ()
    {
        return balancet;
    }

    public void setBalancet ( CtBalancet balancet )
    {
        this.balancet = balancet;
    }

    public CtAnoEconomico getAnoEconomico ()
    {
        return anoeconomico;
    }

    public void setAnoeconomico ( CtAnoEconomico anoeconomico )
    {
        this.anoeconomico = anoeconomico;
    }

    public MapaProgramacao getMapa ()
    {
        return mapa;
    }

    public void setMapa ( MapaProgramacao mapa )
    {
        this.mapa = mapa;
    }

    public void actualizarListRubrica ()
    {
        this.listRubrica = ctRubricaFacade.listRubrica();//getRubricaFornecedores ();
    }

    public List<CtRubrica> getListRubrica ()
    {
        actualizarListRubrica ();
        return this.listRubrica;
    }

    public void setListRubrica ( List<CtRubrica> listRubrica )
    {
        this.listRubrica = listRubrica;
    }

    public List<CtBalancet> getListBalancet ()
    {
        return listBalancet;
    }

    public void setListBalancet ( List<CtBalancet> listBalancet )
    {
        this.listBalancet = listBalancet;
    }

    public List<CtAnoEconomico> getListAnoEconomico ()
    {

        this.listAnoEconomico = ctAnoeconomicoFacade.listYear ();

        if ( this.listAnoEconomico != null )
        {
            //this.codigoYaer = Utilitarios.getPkExercicioActual(listAnoEconomico);
            return this.listAnoEconomico;
        }

        return new ArrayList<CtAnoEconomico> ();
    }

    public void setListAnoEconomico ( List<CtAnoEconomico> listAnoEconomico )
    {
        this.listAnoEconomico = listAnoEconomico;
    }

    public List<MapaProgramacao> getListMapa ()
    {
        return listMapa;
    }

    public void setListMapa ( List<MapaProgramacao> listMapa )
    {
        this.listMapa = listMapa;
    }

    public double getTotalDebito ()
    {
        return totalDebito;
    }

    public void setTotalDebito ( double totalDebito )
    {
        this.totalDebito = totalDebito;
    }

    public double getTotalCredito ()
    {
        return totalCredito;
    }

    public void setTotalCredito ( double totalCredito )
    {
        this.totalCredito = totalCredito;
    }

    public double getTotalSaldoDevedor ()
    {
        return totalSaldoDevedor;
    }

    public void setTotalSaldoDevedor ( double totalSaldoDevedor )
    {
        this.totalSaldoDevedor = totalSaldoDevedor;
    }

    public double getTotalSaldoCredor ()
    {
        return totalSaldoCredor;
    }

    public void setTotalSaldoCredor ( double totalSaldoCredor )
    {
        this.totalSaldoCredor = totalSaldoCredor;
    }

    public double getDebito ()
    {
        return debito;
    }

    public void setDebito ( double debito )
    {
        this.debito = debito;
    }

    public double getCredito ()
    {
        return credito;
    }

    public void setCredito ( double credito )
    {
        this.credito = credito;
    }

    public double getSaldoDevedor ()
    {
        return saldoDevedor;
    }

    public void setSaldoDevedor ( double saldoDevedor )
    {
        this.saldoDevedor = saldoDevedor;
    }

    public double getSaldoCredor ()
    {
        return saldoCredor;
    }

    public void setSaldoCredor ( double saldoCredor )
    {
        this.saldoCredor = saldoCredor;
    }

    public int getCodigoYaer ()
    {
        return codigoYaer;
    }

    public void setCodigoYaer ( int codigoYaer )
    {
        this.codigoYaer = codigoYaer;
    }

    public int getCodigoRubrica ()
    {
        return codigoRubrica;
    }

    public void setCodigoRubrica ( int codigoRubrica )
    {
        this.codigoRubrica = codigoRubrica;
    }

    List<MapaProgramacao> listTSC = new ArrayList<> ();
    List<MapaProgramacao> collections = new ArrayList<> ();

//    public void emitirReportMapa ()
//    {
//
//        relatorioBeanCT.emitirReportMapa ( this.codigoYaer, this.codigoRubrica );
//
//    }
//
//    public void emitirDespesaBenServico ()
//    {
//
//        relatorioBeanCT.emitirDespesaBenServico ( this.codigoYaer, this.codigoClasse );
//
//    }

    private List<Object[]> listaBalancetYearRubricaG;
    private double totalBalancetYearRubricaG = 0.00;

    public String listMapG ()
    {

        listaBalancetYearRubricaG = ctBalancetFacade.getBalancetPorYearANDRubricaG ( this.codigoYaer, this.codigoRubrica );
        System.out.println ( "size listaBalancetYearRubricaG : " + listaBalancetYearRubricaG.size () );
        return "";
    }

    public double getTotalBalancetYearRubricaG ()
    {

        Object result = ctBalancetFacade.getBalancetTotalCreditoPorYearANDRubricaG ( this.codigoYaer, this.codigoRubrica ).get ( 0 );

        if ( result != null )
        {
            this.totalBalancetYearRubricaG = Double.parseDouble ( result.toString () );
            return this.totalBalancetYearRubricaG;
        }

        return 0.00;

    }

    public void setTotalBalancetYearRubricaG ( double totalBalancetYearRubricaG )
    {
        this.totalBalancetYearRubricaG = totalBalancetYearRubricaG;
    }

    public List<Object[]> getListaBalancetYearRubricaG ()
    {

        return listaBalancetYearRubricaG;
    }

    public void setListaBalancetYearRubricaG ( List<Object[]> listaBalancetYearRubricaG )
    {
        this.listaBalancetYearRubricaG = listaBalancetYearRubricaG;
    }

    public String listMap ()
    {

        collections = new ArrayList<> ();
        listTSC = new ArrayList<> ();
        this.listAccount = ctAccountFacade.getAccountPorRubrica ( codigoRubrica );
        //this.listBalancet = ctBalancetFacade.getBalancetPorRubrica(new CtRubrica(codigoRubrica));
        this.listBalancet = ctBalancetFacade.getBalancetPorYearANDRubrica ( this.codigoYaer, this.codigoRubrica );
        this.codigoRubrica = 0;
        this.credito = 0.00;
        this.debito = 0.00;
        this.codigoConta = 0;
        this.saldoCredor = 0.00;
        this.totalSaldoCredor = 0.00;
        MapaProgramacao objecto;

        if ( listBalancet != null && listAccount != null )
        {

            for ( CtAccount conta : listAccount )
            {

                this.codigoConta = conta.getPkAccount ();
                objecto = new MapaProgramacao ();

                for ( CtBalancet item : listBalancet )
                {

                    this.codigoYaer = item.getFkLancamento ().getFkAnoEconomico ().getAnoEconomico ();
                    if ( codigoConta == item.getFkAccount ().getPkAccount () )
                    {
                        this.debito = this.debito + item.getDebitoBalancet ();
                        this.credito = this.credito + item.getCreditoBalancet ();
                    }

                }

                this.saldoCredor = ( this.credito - this.debito );
                this.totalSaldoCredor = this.totalSaldoCredor + this.saldoCredor;
                objecto.setExercicio ( this.codigoYaer );
                objecto.setNumero ( conta.getNumeroAccount () );
                objecto.setDescricao ( conta.getDescricaoAccount () );
                objecto.setSaldoCredor ( this.saldoCredor );
                collections.add ( objecto );

                //Teste antes de implementar o método...
//               System.out.print("Fornecedor: "+conta.getDescricaoAccount());
//               System.out.print("Total de Crédito: "+this.credito);
//               System.out.print("Total de Débito: "+this.debito);
//               System.out.print("Saldo Credor: "+this.saldoCredor);
                this.credito = 0.00;
                this.debito = 0.00;
                this.saldoCredor = 0.00;

            }

            setListMapa ( collections );
            objecto = new MapaProgramacao ();
            objecto.setTotais ( "" );
            objecto.setTotalSaldoCredor ( this.totalSaldoCredor );
            listTSC.add ( objecto );
            setListMapaAux ( listTSC );

            //System.out.print("Total de Saldo Credor: "+this.totalSaldoCredor);
        }
        else
        {

            collections.clear ();
            setListMapa ( collections );
            listTSC.clear ();
            setListMapaAux ( listTSC );
            Mensagem.enviarMensagem (null, FacesMessage.SEVERITY_WARN, "Não há movimentos nesta Rubrica!" );
            return "";

        }
        return "";
    }

    public List<CtAccount> getListAccount ()
    {
        return listAccount;
    }

    public void setListAccount ( List<CtAccount> listAccount )
    {
        this.listAccount = listAccount;
    }

    public int getCodigoConta ()
    {
        return codigoConta;
    }

    public void setCodigoConta ( int codigoConta )
    {
        this.codigoConta = codigoConta;
    }

    public List<MapaProgramacao> getListMapaAux ()
    {
        return listMapaAux;
    }

    public void setListMapaAux ( List<MapaProgramacao> listMapaAux )
    {
        this.listMapaAux = listMapaAux;
    }

    public List<CtMontanteClasse> getListCategoria ()
    {
        return listCategoria;
    }

    public void setListCategoria ( List<CtMontanteClasse> listCategoria )
    {
        this.listCategoria = listCategoria;
    }

    public String mapaDespesaBenServico ()
    {

        List<MapaDespesaPorNaturaza> collection = new ArrayList<> ();
        List<MapaDespesaPorNaturaza> collectionRubricas = new ArrayList<> ();
        List<MapaDespesaPorNaturaza> collectiTotais = new ArrayList<> ();
        this.listCategoria = ctMontanteclasseFacade.getCategoriaPorYearANDId ( codigoYaer, codigoClasse );
//        System.out.print("Tamanho " + listCategoria.size());
        //this.listRubrica = new CtRubricaDAO().getRubricaPorClasse(new CtClass(codigoClasse));
        this.listMontanteRubrica = ctMontanterubricaFacade.getMontanteRubricaPorCategoriaANDYear ( this.codigoYaer, this.codigoClasse );

        this.listBalancet = ctBalancetFacade.getBalancetPorYear ( new CtAnoEconomico ( this.codigoYaer ) );

        this.codigoClasse = 0;
        this.credito = 0.00;
        this.codigoRubrica = 0;
        this.totalSaldoDA = 0;
        this.totalSaldoDC = 0;
        this.totalSaldoRD = 0;
        this.totalSaldoDP = 0;
        double sldo = 0.00;

        if ( listCategoria != null && listBalancet != null && listMontanteRubrica != null )
        {

            for ( CtMontanteClasse item : listCategoria )
            {

                this.codigoClasse = item.getFkClass ().getPkClass ();
                this.mapaDespesaPorNaturezaAux = new MapaDespesaPorNaturaza ();
                this.mapaDespesaPorNaturezaAux.setCodigoCategoria ( String.valueOf ( item.getFkClass ().getPkClass () ) );
                this.mapaDespesaPorNaturezaAux.setCategoria ( item.getFkClass ().getDescricaoClass () );
                collection.add ( mapaDespesaPorNaturezaAux );
                setListMapaDespesaPorNatureza ( collection );

                for ( CtMontanteRubrica obj : listMontanteRubrica )
                {

                    this.mapaDespesaPorNaturezaAux = new MapaDespesaPorNaturaza ();
                    this.codigoRubrica = obj.getFkRubrica ().getPkRubrica ();

                    for ( CtBalancet balancete : listBalancet )
                    {

                        if ( this.codigoRubrica == balancete.getFkAccount ().getFkRubrica ().getPkRubrica () )
                        {

                            this.credito = this.credito + balancete.getCreditoBalancet ();

                        }

                    }

//                    System.out.print("Categoria: " + item.getFkClass().getDescricaoClass());
//                    System.out.print("Rubrica: " + obj.getDescricaoRubrica());
//                    System.out.print("DotacaoAprovada: " + this.getMontanteRubrica(this.codigoRubrica));
//                    System.out.print("RecurosDisponibilizados: " + this.credito);
                    //System.out.print("Categoria: " + item.getFkClass().getDescricaoClass());
                    sldo = this.getMontanteRubrica ( this.codigoRubrica, this.codigoYaer );
                    this.totalSaldoDA = this.totalSaldoDA + sldo;
                    this.totalSaldoRD = this.totalSaldoRD + this.credito;

                    this.mapaDespesaPorNaturezaAux.setCodigoCategoria ( obj.getFkRubrica ().getNumberRubrica () );
                    this.mapaDespesaPorNaturezaAux.setRubrica ( obj.getFkRubrica ().getDescricaoRubrica () );
                    this.mapaDespesaPorNaturezaAux.setSaldoDotacaoAprovada ( this.getMontanteRubrica ( this.codigoRubrica, this.codigoYaer ) );
                    this.mapaDespesaPorNaturezaAux.setSaldoRecurosDisponibilizados ( this.credito );
                    this.mapaDespesaPorNaturezaAux.setSaldoDespesasCabimentadas ( this.credito );
                    this.mapaDespesaPorNaturezaAux.setSaldoDespesasPagas ( this.credito );
                    collectionRubricas.add ( mapaDespesaPorNaturezaAux );
                    setList ( collectionRubricas );

                    sldo = 0.00;
                    this.credito = 0.00;

                }

                this.mapaDespesaPorNaturezaAux = new MapaDespesaPorNaturaza ();
                this.mapaDespesaPorNaturezaAux.setTotais ( "Totais" );
                this.mapaDespesaPorNaturezaAux.setSaldoTotalDotacaoAprovada ( this.totalSaldoDA );
                this.mapaDespesaPorNaturezaAux.setSaldoTotalRecurosDisponibilizados ( this.totalSaldoRD );
                this.mapaDespesaPorNaturezaAux.setSaldoTotalDespesasCabimentadas ( this.totalSaldoRD );
                this.mapaDespesaPorNaturezaAux.setSaldoTotalDespesasPagas ( this.totalSaldoRD );
                collectiTotais.add ( mapaDespesaPorNaturezaAux );
                setListTotaisMapaDespesaPorNatureza ( collectiTotais );

            }

        }
        else
        {

            collection.clear ();
            setList ( collection );

            collectiTotais.clear ();
            setListTotaisMapaDespesaPorNatureza ( collectiTotais );
            Mensagem.enviarMensagem (null, FacesMessage.SEVERITY_WARN, "Não há movimentos na conta deste fornecedor!" );
            return "";

        }
        return "";
    }

    public List<CtMontanteClasse> getListCategoriaDespesa ()
    {
        return ctMontanteclasseFacade.getCategoriaPorYear ( new CtAnoEconomico ( codigoYaer ) );
    }

    public void setListCategoriaDespesa ( List<CtMontanteClasse> listCategoriaDespesa )
    {
        this.listCategoriaDespesa = listCategoriaDespesa;
    }

    public int getCodigoClasse ()
    {
        return codigoClasse;
    }

    public void setCodigoClasse ( int codigoClasse )
    {
        this.codigoClasse = codigoClasse;
    }

    public List<MapaDespesaPorNaturaza> getListMapaDespesaPorNatureza ()
    {
        return listMapaDespesaPorNatureza;
    }

    public void setListMapaDespesaPorNatureza ( List<MapaDespesaPorNaturaza> listMapaDespesaPorNatureza )
    {
        this.listMapaDespesaPorNatureza = listMapaDespesaPorNatureza;
    }

    public List<MapaDespesaPorNaturaza> getListTotaisMapaDespesaPorNatureza ()
    {
        return listTotaisMapaDespesaPorNatureza;
    }

    public void setListTotaisMapaDespesaPorNatureza ( List<MapaDespesaPorNaturaza> listTotaisMapaDespesaPorNatureza )
    {
        this.listTotaisMapaDespesaPorNatureza = listTotaisMapaDespesaPorNatureza;
    }

    public double getMontanteRubrica ( int id, int year )
    {

        List<CtMontanteRubrica> ls = ctMontanterubricaFacade.findAll ();

        if ( ls != null )
        {

            for ( CtMontanteRubrica it : ls )
            {
                //#FilipeTuza_2019_05_02
                CtRubrica rubrica = it.getFkRubrica ();
                CtAnoEconomico anoEconomico = it.getFkAnoEconomico ();
                //#FilipeTuza_2019_05_02

                //#FilipeTuza_2019_05_02
                if ( rubrica != null && anoEconomico != null )
                {
                //#FilipeTuza_2019_05_02
                    if ( it.getFkRubrica ().getPkRubrica () == id && it.getFkAnoEconomico ().getPkAnoEconomico () == year )
                    {

                        return it.getValorAnualRubrica ();
                    }
                }
            }
        }

        return 0.00;
    }

    public List<MapaDespesaPorNaturaza> getList ()
    {
        return list;
    }

    public void setList ( List<MapaDespesaPorNaturaza> list )
    {
        this.list = list;
    }

    public double getTotalSaldoDA ()
    {
        return totalSaldoDA;
    }

    public void setTotalSaldoDA ( double totalSaldoDA )
    {
        this.totalSaldoDA = totalSaldoDA;
    }

    public double getTotalSaldoRD ()
    {
        return totalSaldoRD;
    }

    public void setTotalSaldoRD ( double totalSaldoRD )
    {
        this.totalSaldoRD = totalSaldoRD;
    }

    public double getTotalSaldoDC ()
    {
        return totalSaldoDC;
    }

    public void setTotalSaldoDC ( double totalSaldoDC )
    {
        this.totalSaldoDC = totalSaldoDC;
    }

    public double getTotalSaldoDP ()
    {
        return totalSaldoDP;
    }

    public void setTotalSaldoDP ( double totalSaldoDP )
    {
        this.totalSaldoDP = totalSaldoDP;
    }

    public List<CtMontanteRubrica> getListMontanteRubrica ()
    {
        return listMontanteRubrica;
    }

    public void setListMontanteRubrica ( List<CtMontanteRubrica> listMontanteRubrica )
    {
        this.listMontanteRubrica = listMontanteRubrica;
    }
    
}
