/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.facades;

import ejbs.entities.ArAnoCurricular;
import ejbs.entities.ArAnoLectivo;
import ejbs.entities.ArConfiguracoes;
import ejbs.entities.ArPeriodo;
import ejbs.entities.ArTipoDocumento;
import ejbs.entities.ArTurma;
import utils.Defs;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author david-salgueiro
 */
@Stateless
public class ArConfiguracoesFacade extends AbstractFacade<ArConfiguracoes> {

    @PersistenceContext(unitName = "SIG_UCAN-PU")
    private EntityManager em;

    @EJB
    private ArTipoDocumentoFacade tipoDocumentoFacade;
    
    @EJB
    private ArTurmaFacade turmaFacade;

    @EJB
    private ArPeriodoFacade periodoFacade;

    @EJB
    private ArAnoLectivoFacade anoLectivoFacade;

    @EJB
    private ArAnoCurricularFacade anoCurricularFacade;
    
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ArConfiguracoesFacade() {
        super(ArConfiguracoes.class);
    }
    
    
    public ArConfiguracoes find()
    {
        Query query = em.createQuery("SELECT c FROM ArConfiguracoes c");
        List<ArConfiguracoes> list = query.getResultList();
        return (list == null || list.isEmpty()) ? gerarConfiguracoesPadrao() : list.get(0);
    }

    /**
     *
     * @return
     */
    public ArConfiguracoes gerarConfiguracoesPadrao()
    {
        ArConfiguracoes configuracoesPadrao = new ArConfiguracoes();

        ArTipoDocumento tipoDocumento = this.tipoDocumentoFacade.find(Defs.TIPO_DOCUMENTO_PADRAO_CODIGO);
        ArTurma turma = this.turmaFacade.find(Defs.TURMA_PADRAO_CODIGO);
        ArPeriodo periodo = this.periodoFacade.find(Defs.PERIODO_PADRAO_CODIGO);
        ArAnoLectivo anoLectivo = this.anoLectivoFacade.find(Defs.ANO_LECTIVO_PADRAO_CODIGO);
        ArAnoCurricular anoCurricular = this.anoCurricularFacade.find(Defs.ANO_CURRICULAR_PADRAO_CODIGO);
    
        configuracoesPadrao.setFkTipoDocumento(tipoDocumento);
        configuracoesPadrao.setFkTurma(turma);
        configuracoesPadrao.setFkPeriodo(periodo);
        configuracoesPadrao.setFkAnoLectivo(anoLectivo);
        configuracoesPadrao.setFkAnoCurricular(anoCurricular);
          
        return this.createRegisto(configuracoesPadrao) ? configuracoesPadrao : null;
    }

    /**
     *
     * @return
     */
    public ArConfiguracoes reporConfiguracoesPadrao()
    {
        Query query = em.createQuery("SELECT c FROM ArConfiguracoes c");
        List<ArConfiguracoes> list = query.getResultList();
        if (list == null || list.isEmpty())
        {
            return gerarConfiguracoesPadrao();
        }
        ArConfiguracoes configuracoesPadrao = list.get(0);
        
        ArTipoDocumento tipoDocumento = this.tipoDocumentoFacade.find(Defs.TIPO_DOCUMENTO_PADRAO_CODIGO);
        ArTurma turma = this.turmaFacade.find(Defs.TURMA_PADRAO_CODIGO);
        ArPeriodo periodo = this.periodoFacade.find(Defs.PERIODO_PADRAO_CODIGO);
        ArAnoLectivo anoLectivo = this.anoLectivoFacade.find(Defs.ANO_LECTIVO_PADRAO_CODIGO);
        ArAnoCurricular anoCurricular = this.anoCurricularFacade.find(Defs.ANO_CURRICULAR_PADRAO_CODIGO);
    
        configuracoesPadrao.setFkTipoDocumento(tipoDocumento);
        configuracoesPadrao.setFkTurma(turma);
        configuracoesPadrao.setFkPeriodo(periodo);
        configuracoesPadrao.setFkAnoLectivo(anoLectivo);
        configuracoesPadrao.setFkAnoCurricular(anoCurricular);

        return this.editRegisto(configuracoesPadrao) ? configuracoesPadrao : null;
    }

    /**
     *
     * @param configuracoes
     * @return
     */
    public boolean createRegisto(ArConfiguracoes configuracoes)
    {
        try
        {
            this.create(configuracoes);
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }

    /**
     *
     * @param configuracoes
     * @return
     */
    public boolean editRegisto(ArConfiguracoes configuracoes)
    {
        try
        {
            this.edit(configuracoes);
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }
    
}
