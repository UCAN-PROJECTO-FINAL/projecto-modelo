/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.facades;

import ejbs.entities.FinDocumento;
import ejbs.entities.FinContaCorrente;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author majoao
 */
@Stateless
public class FinContaCorrenteFacade extends AbstractFacade<FinContaCorrente> {

    @PersistenceContext(unitName = "SIG_UCAN-PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FinContaCorrenteFacade() {
        super(FinContaCorrente.class);
    }
    // FnEntidade entidade, 
    public List<FinContaCorrente> getContaCorrenteByRegularizacao (boolean estado )
    {

        TypedQuery<FinContaCorrente> query;
        query = em.createQuery ( "SELECT f FROM FinContaCorrente f WHERE "
                + "f.fkEntidade = :entidade and f.estado = :estado", FinContaCorrente.class )
                //.setParameter ( "entidade", entidade )
                .setParameter ( "estado", estado );

        List<FinContaCorrente> results = query.getResultList ();

        if ( results.isEmpty () )
        {
            return null;
        }

        return results;
    }

    /*listar as contas correntes em função da categoria e o seu estado*/
     // FnEntidade entidade, 
    public List<FinContaCorrente> getContaCorrenteByEntidadeDoc ( boolean estado )
    {

        TypedQuery<FinContaCorrente> query;
        query = em.createQuery ( "SELECT f FROM FinContaCorrente f "
                + "WHERE f.fkEntidade = :entidade "
                + "and f.estado = :estado ORDER BY f.dataDoc", FinContaCorrente.class )
                //.setParameter ( "entidade", entidade )
                .setParameter ( "estado", estado );

        List<FinContaCorrente> results = query.getResultList ();

        if ( results.isEmpty () )
        {
            return null;
        }

        return results;
    }
     // FnEntidade entidade, 
    public List<FinContaCorrente> getContaCorrenteByEntidade ( boolean estado )
    {

        TypedQuery<FinContaCorrente> query;
        query = em.createQuery ( "SELECT f FROM FinContaCorrente f WHERE "
                + "f.fkEntidade = :entidade and f.estado = :estado", FinContaCorrente.class )
                //.setParameter ( "entidade", entidade )
                .setParameter ( "estado", estado );

        List<FinContaCorrente> results = query.getResultList ();

        if ( results == null )
        {
            return null;
        }

        return results;
    }
     // FnEntidade entidade, 
    public FinContaCorrente getContaCorrenteByEntidadeEstadoDocumento ( boolean estado, FinDocumento documento )
    {

        TypedQuery<FinContaCorrente> query;
        query = em.createQuery ( "SELECT f FROM FinContaCorrente f WHERE "
                + "f.fkEntidade = :entidade and f.estado = :estado and f.fkDocumento = :documento ", FinContaCorrente.class )
                //.setParameter ( "entidade", entidade )
                .setParameter ( "estado", estado ).setParameter ( "documento", documento );

        List<FinContaCorrente> results = query.getResultList ();

        if ( results == null )
        {
            return null;
        }

        return results.get ( 0 );
    }

    public FinContaCorrente getContaCorrenteByDocumento ( FinDocumento documento )
    {
        
        TypedQuery<FinContaCorrente> query;
        query = em.createQuery ( "SELECT f FROM FinContaCorrente f WHERE "
                + "f.fkDocumento = :documento ", FinContaCorrente.class )
                .setParameter ( "documento", documento );

        List<FinContaCorrente> results = query.getResultList ();

        if ( results.isEmpty () )
        {
            return null;
        }

        return results.get ( 0 );
    }

    public FinContaCorrente findInterData ( String data1, String data2 )
    {

        TypedQuery<FinContaCorrente> query;
        query = em.createQuery ( "SELECT f FROM FinContaCorrente f WHERE "
                + "f.dataRegistro BETWEEN :dataInicio AND :dataFim ORDER BY F.dataRegistro ASC", FinContaCorrente.class )
                .setParameter ( "dataInicio", data1 )
                .setParameter ( "dataFim", data2 );

        List<FinContaCorrente> results = query.getResultList ();

        if ( results == null )
        {
            return null;
        }

        return results.get ( 0 );
    }
    // FnEntidade entidade, 
    public List<FinContaCorrente> getTodosContaCorrenteByEntidade (  )
    {

        TypedQuery<FinContaCorrente> query;
        query = em.createQuery ( "SELECT f FROM FinContaCorrente f WHERE "
                + "f.fkEntidade = :entidade", FinContaCorrente.class );
                //.setParameter ( "entidade", entidade );

        List<FinContaCorrente> results = query.getResultList ();

        if ( results == null )
        {
            return null;
        }

        return results;
    }
    //FnContaBancaria numero
    public List<FinContaCorrente> getTodosContaCorrenteByNumeroConta (  )
    {

        TypedQuery<FinContaCorrente> query;
        query = em.createQuery ( "SELECT f FROM FinContaCorrente f WHERE "
                + "f.fkModopagamento.fkContaBancaria = :numero order by f.dataDoc", FinContaCorrente.class );
                //.setParameter ( "numero", numero );

        List<FinContaCorrente> results = query.getResultList ();

        if ( results == null )
        {
            return null;
        }

        return results;
    }

    public List<FinContaCorrente> getContaCorrenteByModoPagamento ( String modo, boolean estado )
    {

        TypedQuery<FinContaCorrente> query;
        query = em.createQuery ( "SELECT f FROM FinContaCorrente f WHERE "
                + "f.fkModoPagamento.modo = :modo and f.estado = :estado order by "
                + "f.fkEntidade.fkTipoEntidade.pkTipoEntidade", FinContaCorrente.class )
                .setParameter ( "modo", modo )
                .setParameter ( "estado", estado );

        List<FinContaCorrente> results = query.getResultList ();

        if ( results == null )
        {
            return null;
        }

        return results;
    }

    public List<FinContaCorrente> getContaCorrenteByIntervaloVencimento ( Date data1, Date data2, boolean estado )
    {

        TypedQuery<FinContaCorrente> query;
        query = em.createQuery ( "SELECT f FROM FinContaCorrente f "
                + "WHERE f.dataVencimento between :data1 and :data2 and f.estado = :estado "
                + "order by f.fkEntidade.fkTipoEntidade.pkTipoEntidade", FinContaCorrente.class )
                .setParameter ( "data1", data1 )
                .setParameter ( "data2", data2 )
                .setParameter ( "estado", estado );

        List<FinContaCorrente> results = query.getResultList ();

        if ( results == null )
        {
            return null;
        }

        return results;
    }

    public List<FinContaCorrente> getContaCorrenteByEstado ( boolean estado )
    {

        TypedQuery<FinContaCorrente> query = em.createNamedQuery ( "FinContaCorrente.findByEstado",
                FinContaCorrente.class )
                .setParameter ( "estado", estado );

        List<FinContaCorrente> results = query.getResultList ();

        if ( results == null )
        {
            return null;
        }

        return results;
    }

    public double getTotalPendente ()
    {

        TypedQuery<Double> query;
        query = em.createQuery ( "SELECT sum(f.pendente) FROM FinContaCorrente f "
                + "where f.estadoFn = true", Double.class );

        List<Double> results = query.getResultList ();

        if ( results != null && !results.isEmpty () )
        {
            if ( results.get ( 0 ) != null )
            {
                return results.get ( 0 );
            }
        }

        return 0.00;
    }

    
}
