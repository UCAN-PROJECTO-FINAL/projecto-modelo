/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.facades;

import ejbs.entities.PtTipoSaidaBem;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author mdnext
 */
@Stateless
public class PtTipoSaidaBemFacade extends AbstractFacade<PtTipoSaidaBem> {

    @PersistenceContext(unitName = "SIG_UCAN-PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PtTipoSaidaBemFacade() {
        super(PtTipoSaidaBem.class);
    }
    
    public PtTipoSaidaBem findByDescricao(String descricao)
    {
        Query query = em.createQuery("SELECT m FROM PtTipoSaidaBem m WHERE m.descricao LIKE :descricao");
        query.setParameter("descricao", descricao);
        List<PtTipoSaidaBem> result = query.getResultList();
        return result.isEmpty() ? null : result.get(0);
    }
}
