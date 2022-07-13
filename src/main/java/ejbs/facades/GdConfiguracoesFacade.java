/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.facades;

import ejbs.entities.GdClassificacao;
import ejbs.entities.GdConfiguracoes;
import ejbs.entities.GdEntidade;
import ejbs.entities.GdTipoDocumento;
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
public class GdConfiguracoesFacade extends AbstractFacade<GdConfiguracoes> {

    @EJB
    private GdClassificacaoFacade classificacaoFacade;

    @EJB
    private GdEntidadeFacade entidadeFacade;

    @EJB
    private GdTipoDocumentoFacade tipoDocumentoFacade;

    @PersistenceContext(unitName = "SIG_UCAN-PU")
    private EntityManager em;

    
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GdConfiguracoesFacade() {
        super(GdConfiguracoes.class);
    }
    
    
    public GdConfiguracoes find()
    {
        Query query = em.createQuery("SELECT c FROM GdConfiguracoes c");
        List<GdConfiguracoes> list = query.getResultList();
        return (list == null || list.isEmpty()) ? gerarGdConfiguracoesPadrao() : list.get(0);
    }

    /**
     *
     * @return
     */
    public GdConfiguracoes gerarGdConfiguracoesPadrao()
    {
        GdConfiguracoes configuracoesPadrao = new GdConfiguracoes();

        GdTipoDocumento tipoDocumento = this.tipoDocumentoFacade.find(Defs.GD_TIPO_DOCUMENTO_PADRAO_CODIGO);
        GdClassificacao classificacao = this.classificacaoFacade.find(Defs.CLASSIFICACAO_PADRAO_CODIGO);
        GdEntidade entidade = this.entidadeFacade.find(Defs.ENTIDADE_PADRAO_CODIGO);
        
        configuracoesPadrao.setFkTipoDocumento(tipoDocumento);
        configuracoesPadrao.setFkClassificacao(classificacao);
        configuracoesPadrao.setFkEntidade(entidade);
          
        return this.createRegisto(configuracoesPadrao) ? configuracoesPadrao : null;
    }

    /**
     *
     * @return
     */
    public GdConfiguracoes reporGdConfiguracoesPadrao()
    {
        Query query = em.createQuery("SELECT c FROM GdConfiguracoes c");
        List<GdConfiguracoes> list = query.getResultList();
        if (list == null || list.isEmpty())
        {
            return gerarGdConfiguracoesPadrao();
        }
        GdConfiguracoes configuracoesPadrao = list.get(0);
        
        GdTipoDocumento tipoDocumento = this.tipoDocumentoFacade.find(Defs.TIPO_DOCUMENTO_PADRAO_CODIGO);
        GdClassificacao classificacao = this.classificacaoFacade.find(Defs.CLASSIFICACAO_PADRAO_CODIGO);
        GdEntidade entidade = this.entidadeFacade.find(Defs.ENTIDADE_PADRAO_CODIGO);
        
        configuracoesPadrao.setFkTipoDocumento(tipoDocumento);
        configuracoesPadrao.setFkClassificacao(classificacao);
        configuracoesPadrao.setFkEntidade(entidade);

        return this.editRegisto(configuracoesPadrao) ? configuracoesPadrao : null;
    }

    /**
     *
     * @param configuracoes
     * @return
     */
    public boolean createRegisto(GdConfiguracoes configuracoes)
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
    public boolean editRegisto(GdConfiguracoes configuracoes)
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
