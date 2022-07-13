/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contabilidade.util;

import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author majoao
 */
public class SaldoCredor {
    
    
   // ConexaoPostgresSQL conexao;
    ResultSet resultado;
    private List<DebitoCredito> list = null;
    public static final String CONSULTA = "select debito_balancet as debito, credito_balancet as credito from ct_balancet\n"
            + "inner join ct_account on ct_account.pk_account = ct_balancet.fk_account\n"
            + "inner join ct_rubrica on ct_rubrica.pk_rubrica = ct_account.fk_rubrica\n"
            + "inner join ct_class on ct_class.pk_class = ct_rubrica.fk_class\n"
            + "where ct_balancet.data_balancet between ? and ? \n"
            + "and ct_class.descricao_class = 'Fornecedores'\n"
            + "and ct_balancet.state_balancet = 'true'";

    private static final String CONSULTAJPAQL = "select ctb.debitoBalancet as debito, ctb.creditoBalancet as credito from CtBalancet ctb\n"
            + " inner join CtAccount cta on cta = ctb.fkAccount\n"
            + " inner join CtRubrica ctr on ctr = cta.fkRubrica\n"
            + " inner join CtClass ctc on ctc = ctr.fkClass\n"
            + " where\n"
            + " ctc.descricaoClass = 'Fornecedores'\n"
            + " and ctb.stateBalancet = 'true'\n"
            + " and ctb.dataBalancet between :data1 and :data2";

    public SaldoCredor() {
    }

    public List<DebitoCredito> getList(Date dateone, Date datetwo) {

        this.list = new ArrayList<>();

        try {
//
//            conexao = new ConexaoPostgresSQL();
//            conexao.criarStatement(CONSULTA);
//            conexao.getStatement().setDate(1, new java.sql.Date(dateone.getTime()));
//            conexao.getStatement().setDate(2, new java.sql.Date(datetwo.getTime()));
//            resultado = conexao.executarQuery();

            while (resultado.next()) {

                DebitoCredito debitoCredito = new DebitoCredito();

                debitoCredito.setDebito(resultado.getDouble(1));
                debitoCredito.setCredito(resultado.getDouble(2));
                list.add(debitoCredito);

            }

            //conexao.closeConnection();
            return list;

        } catch (Exception ex) {

            ex.printStackTrace();

        }

        return list;
    }

    public String data(Date date) {

        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return dateFormat.format(date);
    }

}
