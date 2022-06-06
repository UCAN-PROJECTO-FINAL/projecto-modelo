/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.facades;

import ejbs.entities.GsInstalacao;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author jesus
 */
@Stateless
public class GsInstalacaoFacade extends AbstractFacade<GsInstalacao> {

    @PersistenceContext(unitName = "SIG_UCAN-PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GsInstalacaoFacade() {
        super(GsInstalacao.class);
    }

    public List<GsInstalacao> findAllOrderByData() {
        Query query = em.createQuery("SELECT g FROM GsInstalacao g ORDER BY g.data");
        List<GsInstalacao> result = query.getResultList();
        return result;
    }

}
