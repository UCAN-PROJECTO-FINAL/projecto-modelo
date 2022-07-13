/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.facades;

import ejbs.entities.GdTipoDocumento;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author david-salgueiro
 */
@Stateless
public class GdTipoDocumentoFacade extends AbstractFacade<GdTipoDocumento>
{

    @PersistenceContext(unitName = "SIG_UCAN-PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager()
    {
        return em;
    }

    public GdTipoDocumentoFacade()
    {
        super(GdTipoDocumento.class);
    }

    public List<GdTipoDocumento> findTipoDocumentoByDescricao(String tipoDocumento)
    {
        Query q = em.createQuery("SELECT c FROM GdTipoDocumento c WHERE c.descricao =:tipoDocumento");
        q.setParameter("tipoDocumento", tipoDocumento);
        q.setMaxResults(1);

        return q.getResultList();
    }

    public List<GdTipoDocumento> findTipoDocumentoFinancas()
    {
        Query q = em.createQuery("SELECT c FROM GdTipoDocumento c WHERE c.modulo LIKE '%FIN%'");
        return q.getResultList();
    }

}
