/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.facades;

import ejbs.entities.SegPerfil;
import ejbs.entities.SegPerfilAssociado;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author smakambo
 */
@Stateless
public class SegPerfilAssociadoFacade extends AbstractFacade<SegPerfilAssociado> {

    @PersistenceContext(unitName = "SIG_UCAN-PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SegPerfilAssociadoFacade() {
        super(SegPerfilAssociado.class);
    }
    
    
    public List<SegPerfil> getPerfilByPerfil(Integer idperfil) 
    {
        Query query;
        query = em.createQuery("SELECT rp.segPerfil FROM SegPerfilAssociado rp WHERE rp.segPerfilAssociadoPK.idPerfilFilho = :idperfil");

        query.setParameter("idperfil", idperfil);

        return query.getResultList();
    }
    
}
