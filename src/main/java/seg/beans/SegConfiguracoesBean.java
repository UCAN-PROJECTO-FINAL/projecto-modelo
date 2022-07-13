/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seg.beans;

import ejbs.entities.SegConfiguracoes;
import ejbs.facades.SegConfiguracoesFacade;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

/**
 *
 * @author helena
 */
@Named(value = "configuracoesBean")
@ApplicationScoped
public class SegConfiguracoesBean
{

    
    
    @EJB
    private SegConfiguracoesFacade segConfiguracoesFacade;
    
    private SegConfiguracoes configuracao;
    
    public SegConfiguracoesBean()
    {
    }
    
    @PostConstruct
    public void init()
    {
        configuracao = segConfiguracoesFacade.find();
    }

    public SegConfiguracoesFacade getSegConfiguracoesFacade()
    {
        return segConfiguracoesFacade;
    }

    public void setSegConfiguracoesFacade(SegConfiguracoesFacade segConfiguracoesFacade)
    {
        this.segConfiguracoesFacade = segConfiguracoesFacade;
    }

    public SegConfiguracoes getConfiguracao()
    {
        init();
        return configuracao;
    }

    public void setConfiguracao(SegConfiguracoes configuracao)
    {
        this.configuracao = configuracao;
    }
}
