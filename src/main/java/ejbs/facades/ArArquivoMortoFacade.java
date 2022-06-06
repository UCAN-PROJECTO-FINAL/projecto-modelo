/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.facades;

import ejbs.entities.ArArquivoMorto;
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
public class ArArquivoMortoFacade extends AbstractFacade<ArArquivoMorto> {

    @PersistenceContext(unitName = "SIG_UCAN-PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ArArquivoMortoFacade() {
        super(ArArquivoMorto.class);
    }
    
    
    public List<ArArquivoMorto> findAllArquivoMortoOrderByData()
    {
        Query q = em.createQuery("SELECT d FROM ArArquivoMorto d ORDER BY d.dataRegistro DESC");
        return q.getResultList();
    }
    
   
    public List<ArArquivoMorto> findAllArquivoMortoByPk(int codigo)
    {
        Query q = em.createQuery("SELECT d FROM ArArquivoMorto d WHERE d.pkArquivoMorto=:codigo");
        q.setParameter("codigo", codigo);
        return q.getResultList();
    }
    
    
    public List<ArArquivoMorto> findArquivoByDocenteAnoLetivoCadeira(int codigoDocente, int codigoAnoLetivo, int codigoCadeira)
    {
            Query q = em.createQuery("SELECT d FROM ArArquivoMorto d WHERE d.fkDocente.pkDocente=:codigoDocente AND d.fkAnoLectivo.pkAnoLectivo=:codigoAnoLetivo AND d.fkCadeira.pkCadeira=:codigoCadeira");
            q.setParameter("codigoDocente", codigoDocente);
            q.setParameter("codigoAnoLetivo", codigoAnoLetivo);
            q.setParameter("codigoCadeira", codigoCadeira);
            return q.getResultList();
        
        
    }
  
    
}

