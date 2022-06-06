/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contabilidade.beans;

import contabilidade.util.ExtratoConta;
import ejbs.entities.CtAccount;
import ejbs.entities.CtBalancet;
import ejbs.entities.CtLancamento;
import ejbs.facades.CtAccountFacade;
import ejbs.facades.CtBalancetFacade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import utils.mensagens.Mensagem;

/**
 *
 * @author majoao
 */
@Named(value = "extratoAccountBean")
@ViewScoped
public class ExtratoAccountBean implements Serializable{

    /**
     * Creates a new instance of ExtratoAccountBean
     */
    
     @EJB
    private CtAccountFacade ctAccountFacade;

    @EJB
    private CtBalancetFacade ctBalancetFacade;

    private CtBalancet balancet;

    private ExtratoConta extratoConta;

    private List<CtBalancet> lisBalancet = null;

    private List<ExtratoConta> list = null;

    private List<ExtratoConta> list2 = null;

    List<CtBalancet> lista = null;

    private List<CtAccount> listAccount = null;

    private Integer codigoAccount = 0;

    private double totalD = 0.00;
    private double totalC = 0.00;
    private double saldoDevedor = 0.00;
    private double saldoCredor = 0.00;
    private double saldoCD = 0.00;
    private String tipoSaldo = "Saldo";

    /**
     * Creates a new instance of ExtratoAccountBean
     */
    public ExtratoAccountBean ()
    {

        this.balancet = new CtBalancet ();
        this.extratoConta = new ExtratoConta ();
        this.lisBalancet = new ArrayList<> ();
        this.list = new ArrayList<> ();
        this.list2 = new ArrayList<> ();
        this.lista = new ArrayList<> ();

        this.listAccount = new ArrayList<> ();
        this.codigoAccount = 0;

    }

    public CtBalancet getBalancet ()
    {
        return balancet;
    }

    public void setBalancet ( CtBalancet balancet )
    {
        this.balancet = balancet;
    }

    public ExtratoConta getExtratoConta ()
    {
        return extratoConta;
    }

    public void setExtratoConta ( ExtratoConta extratoConta )
    {
        this.extratoConta = extratoConta;
    }

    public List<CtBalancet> getLisBalancet ()
    {
        return lisBalancet;
    }

    public void setLisBalancet ( List<CtBalancet> lisBalancet )
    {
        this.lisBalancet = lisBalancet;
    }

    public String listExtrato ()
    {

        this.lista = ctBalancetFacade.getBalancetPorAccount ( new CtAccount ( this.codigoAccount ) );
        List<ExtratoConta> listExtAccount = new ArrayList<> ();
        List<ExtratoConta> listSaldoDC = new ArrayList<> ();
        //System.out.print(this.lista.size());

        totalD = 0.00;
        totalC = 0.00;
        saldoDevedor = 0.00;
        saldoCredor = 0.00;
        saldoCD = 0.00;
        tipoSaldo = "Saldo";

        // verifico se a lista tem elementos...
        if ( lista != null )
        {

            //calculo o total de débito e crédito...
            for ( CtBalancet item : lista )
            {

                this.totalD = ( totalD + item.getDebitoBalancet () );
                this.totalC = ( totalC + item.getCreditoBalancet () );

            }

            this.extratoConta.setTotal ( "" );
            this.extratoConta.setTotalDebito ( this.totalD );
            this.extratoConta.setTotalCredito ( this.totalC );
            listSaldoDC.add ( extratoConta );

            // Calculando o Saldo Devedor, totalD > totalC
            if ( totalD > totalC )
            {

                this.saldoCD = ( totalD - totalC );
                this.tipoSaldo = "Saldo Devedor";
                for ( CtBalancet it : lista )
                {
                    CtLancamento lancamento = it.getFkLancamento ();

                    if ( lancamento != null )
                    {
                        this.extratoConta = new ExtratoConta ();
                        this.extratoConta.setNumeroAccount ( it.getFkAccount ().getNumeroAccount () );
                        this.extratoConta.setAccount ( it.getFkAccount ().getDescricaoAccount () );
                        this.extratoConta.setDebito ( it.getDebitoBalancet () );
                        this.extratoConta.setCredito ( it.getCreditoBalancet () );
                        this.extratoConta.setDataMovimento ( it.getDataBalancet () );

                        this.extratoConta.setNumero ( it.getFkLancamento ().getFkDocumento ().getNumDoc());

                        this.extratoConta.setDiario ( it.getFkLancamento ().getFkDocument ().getFkDiario ().getDescricaoDiario () );
                        //this.extratoConta.setDiario(it.getFkLancamento().getIddocumentoCtDocumento());
                        this.extratoConta.setDocumento ( it.getFkLancamento ().getFkDocument ().getDescricaoDocumento () );
                        listExtAccount.add ( this.extratoConta );
                    }
                }
            }
            else if ( totalC > totalD )
            {

                this.saldoCD = ( totalC - totalD );
                this.tipoSaldo = "Saldo Credor ";
                for ( CtBalancet itm : lista )
                {
                    CtLancamento lancamento = itm.getFkLancamento ();

                    if ( lancamento != null )
                    {
                        this.extratoConta = new ExtratoConta ();
                        this.extratoConta.setNumeroAccount ( itm.getFkAccount ().getNumeroAccount () );
                        this.extratoConta.setAccount ( itm.getFkAccount ().getDescricaoAccount () );
                        this.extratoConta.setDebito ( itm.getDebitoBalancet () );
                        this.extratoConta.setCredito ( itm.getCreditoBalancet () );
                        this.extratoConta.setDataMovimento ( itm.getDataBalancet () );
//                    System.out.print(itm.getCreditoBalancet());
//                    System.out.print(itm.getFkLancamento().getFkDocumento().getNumeroDocumento());
                        this.extratoConta.setNumero ( itm.getFkLancamento ().getFkDocumento ().getNumDoc());
//                    this.extratoConta.setNumero(String.valueOf(itm.getFkLancamento().getFkDocumento().getNumeroDocumento()));
                        this.extratoConta.setDiario ( itm.getFkLancamento ().getFkDocument ().getFkDiario ().getDescricaoDiario () );
                        this.extratoConta.setDocumento ( itm.getFkLancamento ().getFkDocumento ().getDescricaoDocumento () );
                        listExtAccount.add ( this.extratoConta );
                    }
                }

            }
            else if ( totalD == totalC )
            {

                for ( CtBalancet it : lista )
                {
                    this.extratoConta = new ExtratoConta ();
                    this.extratoConta.setNumeroAccount ( it.getFkAccount ().getNumeroAccount () );
                    this.extratoConta.setAccount ( it.getFkAccount ().getDescricaoAccount () );
                    this.extratoConta.setDebito ( it.getDebitoBalancet () );
                    this.extratoConta.setCredito ( it.getCreditoBalancet () );
                    this.extratoConta.setDataMovimento ( it.getDataBalancet () );
                    this.extratoConta.setNumero ( it.getFkLancamento ().getFkDocumento ().getNumDoc());
                    this.extratoConta.setDiario ( it.getFkLancamento ().getFkDocument ().getFkDiario ().getDescricaoDiario () );
                    this.extratoConta.setDocumento ( it.getFkLancamento ().getFkDocumento ().getDescricaoDocumento () );
                    listExtAccount.add ( this.extratoConta );
                }
            }

            this.setList ( listExtAccount );
            this.setList2 ( listSaldoDC );
        }
        else
        {

            listExtAccount.clear ();
            listSaldoDC.clear ();
            this.setList ( listExtAccount );
            this.setList2 ( listSaldoDC );
            Mensagem.enviarMensagem (null, FacesMessage.SEVERITY_WARN, "Não há movimentos na conta deste fornecedor!" );
            return "";
        }

        return "";
    }

    public Integer getCodigoAccount ()
    {
        return codigoAccount;
    }

    public void setCodigoAccount ( Integer codigoAccount )
    {
        this.codigoAccount = codigoAccount;
    }

    public List<ExtratoConta> getList ()
    {
        return list;
    }

    public void setList ( List<ExtratoConta> list )
    {
        this.list = list;
    }

    public void actualizarListAccount ()
    {
        this.listAccount = ctAccountFacade.getAccountFornecedor ();
    }

    public List<CtAccount> getListAccount ()
    {
        actualizarListAccount ();
        return this.listAccount;
    }

    public void setListAccount ( List<CtAccount> listAccount )
    {
        this.listAccount = listAccount;
    }

    public double getTotalD ()
    {
        return totalD;
    }

    public void setTotalD ( double totalD )
    {
        this.totalD = totalD;
    }

    public double getTotalC ()
    {
        return totalC;
    }

    public void setTotalC ( double totalC )
    {
        this.totalC = totalC;
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

    public double getSaldoCD ()
    {
        return saldoCD;
    }

    public void setSaldoCD ( double saldoCD )
    {
        this.saldoCD = saldoCD;
    }

    public List<ExtratoConta> getList2 ()
    {
        return list2;
    }

    public void setList2 ( List<ExtratoConta> list2 )
    {
        this.list2 = list2;
    }

    public String getTipoSaldo ()
    {
        return tipoSaldo;
    }

    public void setTipoSaldo ( String tipoSaldo )
    {
        this.tipoSaldo = tipoSaldo;
    }

    
    
    
}
