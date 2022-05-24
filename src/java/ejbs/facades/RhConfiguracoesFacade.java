/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.facades;

import ejbs.entities.EstruturaLogica;
import ejbs.entities.RhConfiguracoes;
import ejbs.entities.RhEspecialidade;
import ejbs.entities.RhFuncao;
import ejbs.entities.RhNivelAcademico;
import ejbs.entities.RhTipoFuncionario;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import utils.Defs;

/**
 *
 * @author smakambo
 */
@Stateless
public class RhConfiguracoesFacade extends AbstractFacade<RhConfiguracoes> {
    
      @EJB
    private EstruturaLogicaFacade estruturaLogicaFacade;
    
    @EJB
    private RhEspecialidadeFacade rhEspecialidadeFacade;
    
    @EJB
    private RhFuncaoFacade rhFuncaoFacade;
    
    
    @EJB
    private RhNivelAcademicoFacade rhNivelAcademicoFacade;
    
       
    @EJB
    private RhTipoFuncionarioFacade rhTipoFuncionarioFacade;

    @PersistenceContext(unitName = "SIG_FRT_Master-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RhConfiguracoesFacade() {
        super(RhConfiguracoes.class);
    }
    
    
       
    public RhConfiguracoes find()
    {
        Query query = em.createQuery("SELECT c FROM RhConfiguracoes c");
        List<RhConfiguracoes> list = query.getResultList();
        return (list == null || list.isEmpty()) ? gerarConfiguracoesPadrao() : list.get(0);
    }

    /**
     *
     * @return
     */
    public RhConfiguracoes gerarConfiguracoesPadrao()
    {
        RhConfiguracoes configuracoesPadrao = new RhConfiguracoes();

        RhFuncao rhFuncao = this.rhFuncaoFacade.find(Defs.CODIGO_FUNCAO_PADRAO_CODIGO);
        RhNivelAcademico rhNivelAcademico = this.rhNivelAcademicoFacade.find(Defs.CODIGO_NIVEL_ACADEMICO_PADRAO_CODIGO);
        RhTipoFuncionario rhTipoFuncionario = this.rhTipoFuncionarioFacade.find(Defs.CODIGO_TIPO_FUNCIONARIO_PADRAO_CODIGO);
        RhEspecialidade rhEspecialidade = this.rhEspecialidadeFacade.find(Defs.CODIGO_ESPECIALIDADE_PADRAO_CODIGO);
       
        EstruturaLogica estruturaLogica = this.estruturaLogicaFacade.find(Defs.ESTRUTURA_LOGICA_PADRAO_CODIGO);
  
        
        
        configuracoesPadrao.setFkFuncao(rhFuncao);
        configuracoesPadrao.setFkNivelAcademico(rhNivelAcademico);
        configuracoesPadrao.setFkTipoFuncionario(rhTipoFuncionario);
        configuracoesPadrao.setFkEspecialidade(rhEspecialidade);
        
        configuracoesPadrao.setFkEstruturaLogica(estruturaLogica);
          
        return this.createRegisto(configuracoesPadrao) ? configuracoesPadrao : null;
    }

    /**
     *
     * @return
     */
    public RhConfiguracoes reporConfiguracoesPadrao()
    {
        Query query = em.createQuery("SELECT c FROM RhConfiguracoes c");
        List<RhConfiguracoes> list = query.getResultList();
        if (list == null || list.isEmpty())
        {
            return gerarConfiguracoesPadrao();
        }
        RhConfiguracoes configuracoesPadrao = list.get(0);
        
        RhFuncao rhFuncao = this.rhFuncaoFacade.find(Defs.CODIGO_FUNCAO_PADRAO_CODIGO);
        RhNivelAcademico rhNivelAcademico = this.rhNivelAcademicoFacade.find(Defs.CODIGO_NIVEL_ACADEMICO_PADRAO_CODIGO);
        RhTipoFuncionario rhTipoFuncionario = this.rhTipoFuncionarioFacade.find(Defs.CODIGO_TIPO_FUNCIONARIO_PADRAO_CODIGO);
        RhEspecialidade rhEspecialidade = this.rhEspecialidadeFacade.find(Defs.CODIGO_ESPECIALIDADE_PADRAO_CODIGO);
        EstruturaLogica estruturaLogica = this.estruturaLogicaFacade.find(Defs.ESTRUTURA_LOGICA_PADRAO_CODIGO);
  
        
        
        configuracoesPadrao.setFkFuncao(rhFuncao);
        configuracoesPadrao.setFkNivelAcademico(rhNivelAcademico);
        configuracoesPadrao.setFkTipoFuncionario(rhTipoFuncionario);
        configuracoesPadrao.setFkEspecialidade(rhEspecialidade);
        configuracoesPadrao.setFkEstruturaLogica(estruturaLogica);

        return this.editRegisto(configuracoesPadrao) ? configuracoesPadrao : null;
    }

    /**
     *
     * @param configuracoes
     * @return
     */
    public boolean createRegisto(RhConfiguracoes configuracoes)
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
    public boolean editRegisto(RhConfiguracoes configuracoes)
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
