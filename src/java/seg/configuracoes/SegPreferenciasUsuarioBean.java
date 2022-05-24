/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seg.configuracoes;

import ejbs.cache.SegTipoFuncionalidadeCache;
import ejbs.entities.SegConfiguracoes;
import ejbs.entities.SegPerfil;
import ejbs.entities.SegTipoFuncionalidade;
import ejbs.facades.SegConfiguracoesFacade;
import ejbs.facades.SegPerfilFacade;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.inject.Named;
import org.omnifaces.cdi.ViewScoped;
import seg.beans.SegPerfilBean;
import seg.beans.SegTipoFuncionalidadeBean;
import seg.utils.SegCarregarTabelasExcelException;
import utils.mensagens.LogFile;

/**
 *
 * @author aires
 */
@Named(value = "segPreferenciasUsuarioBean")
@ViewScoped
public class SegPreferenciasUsuarioBean implements Serializable
{

	@Inject
    private SegTipoFuncionalidadeCache segTipoFuncionalidadeCache;

	@EJB
	private SegConfiguracoesFacade segConfiguracoesFacade;

	@EJB
	private SegPerfilFacade segPerfilFacade;

	@Inject
	private SegConfiguracoesBean segConfiguracoesBean;

	@Inject
	private SegTipoFuncionalidadeBean segTipoFuncionalidadeBean;
	@Inject
	private SegPerfilBean segPerfilBean;

	private SegPerfil perfilPadrao;
	private SegConfiguracoes configuracoes;
	private int idPerfilPadrao;

	private int idTipoFuncionalidadePadrao;
	private SegTipoFuncionalidade tipoFuncionalidadePadrao;

	public SegPreferenciasUsuarioBean()
	{
	}

	@PostConstruct
	public void init()
	{
            this.configuracoes = this.segConfiguracoesFacade.find();
            this.perfilPadrao = configuracoes == null ? new SegPerfil() : this.configuracoes.getFkSegPerfil();
            this.tipoFuncionalidadePadrao = configuracoes == null ? new SegTipoFuncionalidade() : this.configuracoes.getFkSegTipoFuncionalidade();
            initVariavelEstado();
            this.configuracoes = configuracoes == null ? new SegConfiguracoes() : configuracoes;
//            
        }
        
        public void initVariavelEstado()
        {
            if (this.configuracoes != null)
            {
                if (this.configuracoes.getFkSegPerfil() != null)
                    this.idPerfilPadrao = this.configuracoes.getFkSegPerfil().getPkSegPerfil();
                if (this.configuracoes.getFkSegTipoFuncionalidade()!= null)
                    this.idTipoFuncionalidadePadrao = this.configuracoes.getFkSegTipoFuncionalidade().getPkSegTipoFuncionalidade();
            }
        }

	public boolean renderedBtnActualizar()
	{
            return segTipoFuncionalidadeBean.renderedTipoFuncionalidade() || segPerfilBean.rendereListaPerfil();
	}

	/**
	 *
	 * @return
	 *
	 */
	public SegConfiguracoes reporConfiguracoesPadrao()
	{
		this.configuracoes = new SegConfiguracoes();
		this.segConfiguracoesFacade.reporConfiguracoesPadrao();
		this.perfilPadrao = new SegPerfil();
		this.tipoFuncionalidadePadrao = new SegTipoFuncionalidade();
                this.idPerfilPadrao = 0;
                this.idTipoFuncionalidadePadrao = 0;
		LogFile.sucessoMsg(null, "Configuracoes Repostas com Sucesso");
		return configuracoes;
	}

	/**
	 *
	 * @return @throws SegCarregarTabelasExcelException
	 */
	public boolean salvarRegister() throws SegCarregarTabelasExcelException
	{
            if (segPerfilFacade.evalido(idPerfilPadrao))
            {
                this.configuracoes.setFkSegPerfil(this.segPerfilFacade.find(idPerfilPadrao));
            }
            if (segTipoFuncionalidadeCache.evalido(this.idTipoFuncionalidadePadrao))
            {
                this.configuracoes.setFkSegTipoFuncionalidade(segTipoFuncionalidadeCache.find(this.idTipoFuncionalidadePadrao));
            }
            if (segPerfilFacade.evalido(idPerfilPadrao) || segTipoFuncionalidadeCache.evalido(this.idTipoFuncionalidadePadrao))
            {
		List<SegConfiguracoes> listSegConfiguracoes = segConfiguracoesFacade.findAll();
		if (listSegConfiguracoes != null && !listSegConfiguracoes.isEmpty())
		{
                    segConfiguracoesFacade.edit(configuracoes);
                    LogFile.sucessoMsg(null, "Configuracoes Actualizadas com Sucesso");
		}
		else
		{
                    LogFile.sucessoMsg(null, "Configuracoes Criadas com Sucesso");
                    segConfiguracoesFacade.create(configuracoes);
		}
            }
            return true;
	}

	/**
	 *
	 * @throws seg.utils.SegCarregarTabelasExcelException
	 */
	public void editRegisterWithPersonalizedException() throws SegCarregarTabelasExcelException
	{
		if (!this.salvarRegister())
		{
			LogFile.erroMsg(null,"Tentativa falhada ao actualizar a localização preferêncial ");
			throw new SegCarregarTabelasExcelException();
		}
	}

	public SegPerfil getPerfilPadrao()
	{
		return perfilPadrao;
	}

	public void setPerfilPadrao(SegPerfil perfilPadrao)
	{
		this.perfilPadrao = perfilPadrao;
	}

	public SegConfiguracoes getConfiguracoes()
	{
		return configuracoes;
	}

	public void setConfiguracoes(SegConfiguracoes configuracoes)
	{
		this.configuracoes = configuracoes;
	}

	public int getIdPerfilPadrao()
	{
		return idPerfilPadrao;
	}

	public void setIdPerfilPadrao(int idPerfilPadrao)
	{
		this.idPerfilPadrao = idPerfilPadrao;
	}

	public void setIdTipoFuncionalidadePadrao(int idTipoFuncionalidadePadrao)
	{
		this.idTipoFuncionalidadePadrao = idTipoFuncionalidadePadrao;
	}

	public SegTipoFuncionalidade getTipoFuncionalidadePadrao()
	{
		return tipoFuncionalidadePadrao;
	}

	public void setTipoFuncionalidadePadrao(SegTipoFuncionalidade tipoFuncionalidadePadrao)
	{
		this.tipoFuncionalidadePadrao = tipoFuncionalidadePadrao;
	}

	public int getIdTipoFuncionalidadePadrao()
	{
		return idTipoFuncionalidadePadrao;
	}

}
