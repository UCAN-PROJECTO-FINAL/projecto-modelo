/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.facades;

import ejbs.entities.SegFuncionalidade;
import ejbs.entities.SegPerfil;
import ejbs.entities.SegPerfilHasFuncionalidade;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
//import java.sql.Connection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author smakambo
 */
@Stateless
public class SegPerfilHasFuncionalidadeFacade extends AbstractFacade<SegPerfilHasFuncionalidade> {
    
    @EJB
    private SegFuncionalidadeFormFacade segFuncionalidadeFormFacade;

    @PersistenceContext(unitName = "SIG_FRT_Master-ejbPU")
    private EntityManager em;
    
     private Connection con;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SegPerfilHasFuncionalidadeFacade() {
        super(SegPerfilHasFuncionalidade.class);
    }
    
    
     /*
     * verificar funcionalidades ou recursos 
     * permitido de um determinado
     * perfil atribuido no determinado funcionario
     */
    public List<SegFuncionalidade> findSegFuncionalidadesFromPerfilWithAcessToUrl(Integer idPerfil, String url)
    {
//System.err.println("0: SegPerfilHasFuncionalidadeFacade.findSegFuncionalidadesFromPerfilWithAcessToUrl()\turl: " + url +
//        "\tidPerfil: " + idPerfil);        
        Query query;
        url = url.trim();
//System.err.println("1: SegPerfilHasFuncionalidadeFacade.findSegFuncionalidadesFromPerfilWithAcessToUrl()\turl: " + url +
//        "\tidPerfil: " + idPerfil);        
        List<SegFuncionalidade> segFuncionalidades = getFuncionalidadesByPerfil(idPerfil);
//System.err.println("2: SegPerfilHasFuncionalidadeFacade.findSegFuncionalidadesFromPerfilWithAcessToUrl()\tsegFuncionalidades: " + 
//            (segFuncionalidades == null ? "null" : segFuncionalidades.size()));        
         List<SegFuncionalidade> lista = segFuncionalidadeFormFacade.filterSegFuncionalidadeWithAcessToUrl(segFuncionalidades, url);
//System.err.println("3: SegPerfilHasFuncionalidadeFacade.findSegFuncionalidadesFromPerfilWithAcessToUrl()\tlista: " + 
//            (lista == null ? "null" : lista.size()));      
        return lista;
    }

    // perfil atribuido no determinado funcionario
    // by benvindo
    public SegFuncionalidade findByPerfilAndFuncionalidade(Integer idPerfil, Integer pkIdFuncionalidade)
    {

        Query query;
        query = em.createQuery("SELECT rp.fkSegFuncionalidade FROM SegPerfilHasFuncionalidade rp WHERE rp.fkSegPerfil.pkSegPerfil = :idPerfil AND rp.fkSegFuncionalidade.pkSegFuncionalidade = :pkIdFuncionalidade");

        query.setParameter("idPerfil", idPerfil)
            .setParameter("pkIdFuncionalidade", pkIdFuncionalidade);

        List<SegFuncionalidade> results = query.getResultList();

        return results.isEmpty() ? null : results.get(0);
    }

    /*permissao as funcionalidades dos submodulos consoante o perfil*/
    public boolean temAcessoPermissaoUtilizador(Integer idPerfil, String permissao)
    {
        permissao = permissao.trim();
        Query query = em.createQuery("SELECT p.fkSegFuncionalidade FROM SegPerfilHasFuncionalidade p WHERE p.fkSegPerfil.pkSegPerfil = :idPerfil AND p.fkSegFuncionalidade.urlPadrao = :permissao");

        query.setParameter("idPerfil", idPerfil)
            .setParameter("permissao", permissao);

        List<SegPerfilHasFuncionalidade> results = (List<SegPerfilHasFuncionalidade>) query.getResultList();
        System.out.println("query: " + results);
        return results.isEmpty() ? false : true;
    }

    @SuppressWarnings("Convert2Diamond")
    public List<SegFuncionalidade> getFuncionalidadesByPerfil(Integer idperfil)
    {
        Query query;
        query = em.createQuery("SELECT rp.fkSegFuncionalidade FROM SegPerfilHasFuncionalidade rp WHERE rp.fkSegPerfil.pkSegPerfil = :idperfil");

        query.setParameter("idperfil", idperfil);

        return query.getResultList();
    }

    @SuppressWarnings("Convert2Diamond")
    public List<SegFuncionalidade> getPermissoesByRecursosPerfil(Integer idPerfil, Integer idRecursos)
    {
        Query query;
        query = em.createQuery("SELECT p.fkSegFuncionalidade FROM SegPerfilHasFuncionalidade p WHERE p.fkSegPerfil.pkSegPerfil = :idPerfil And p.fkSegFuncionalidade.pkSegFuncionalidade = :idRecursos");

        query.setParameter("idPerfil", idPerfil)
            .setParameter("idRecursos", idRecursos);

        List<SegFuncionalidade> results = (List<SegFuncionalidade>) query.getResultList();

        return results.isEmpty() ? new ArrayList<SegFuncionalidade>() : results;

    }

    public boolean buscaPorMenuUsuario1(SegFuncionalidade funcionalidade, SegPerfil perfil)
    {
        Query query = em.createQuery("SELECT p.fkSegFuncionalidade FROM SegPerfilHasFuncionalidade p WHERE p.fkSegPerfil.pkSegPerfil = :idPerfil AND p.fkSegFuncionalidade.descricao = :permissao ORDER BY p.fkSegFuncionalidade.descricao");

        query.setParameter("idPerfil", perfil)
            .setParameter("permissao", funcionalidade);

        List<SegPerfilHasFuncionalidade> results = (List<SegPerfilHasFuncionalidade>) query.getResultList();
        System.out.println("query: " + results);
        return !results.isEmpty();
        
    }

    public SegPerfilHasFuncionalidade buscaPorMenuUsuario(SegFuncionalidade funcionalidade, SegPerfil perfil) throws SQLException
    {

        SegPerfilHasFuncionalidade permissao = new SegPerfilHasFuncionalidade();
        String query = "select * from SegPerfilHasFuncionalidade where fkIdPerfil=? and fkIdFuncionalidade=?";
        con = ConnectionFactory();
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, perfil.getPkSegPerfil());
        ps.setInt(2, funcionalidade.getPkSegFuncionalidade());
        ResultSet rs = ps.executeQuery();
        if (rs.next())
        {
            //permissao.setId(rs.getInt(COL_ID));
            permissao.setFkSegFuncionalidade(funcionalidade);
            permissao.setFkSegPerfil(perfil);
        }
        con.close();
        return permissao;
    }

    private Connection ConnectionFactory()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    //buscar os todas as permissoes por  perfil selecionado
    public List<SegPerfilHasFuncionalidade> findByPerfil(Integer idperfil)
    {
        Query query;
        query = em.createQuery("SELECT rp FROM SegPerfilHasFuncionalidade rp WHERE rp.fkSegPerfil.pkSegPerfil = :idperfil ");

        query.setParameter("idperfil", idperfil);

        List<SegPerfilHasFuncionalidade> results = (List<SegPerfilHasFuncionalidade>) query.getResultList();

        //System.out.println("query: " + results);
        return results.isEmpty() ? null : results;

    }

    public SegPerfilHasFuncionalidade FindByFuncionalidadePerfil(Integer idfuncionalidade, Integer idperfil)
    {
        Query query;
        query = em.createQuery("SELECT rp FROM SegPerfilHasFuncionalidade rp WHERE rp.fkSegFuncionalidade.pkSegFuncionalidade = :idfuncionalidade AND rp.fkSegPerfil.pkSegPerfil = :idperfil");

        query.setParameter("idfuncionalidade", idfuncionalidade);
        query.setParameter("idperfil", idperfil);

        List<SegPerfilHasFuncionalidade> results = (List<SegPerfilHasFuncionalidade>) query.getResultList();

        //System.out.println("query: " + results);
        return results.isEmpty() ? null : results.get(0);
    }
    
}
