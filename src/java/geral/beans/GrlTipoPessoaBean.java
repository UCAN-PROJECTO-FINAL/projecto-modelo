/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geral.beans;

import ejbs.entities.GrlTipoPessoa;
import ejbs.facades.GrlTipoPessoaFacade;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import utils.GeradorCodigo;

/**
 *
 * @author amaro
 */
@Named(value = "grlTipoPessoaBean")
@ApplicationScoped
public class GrlTipoPessoaBean
{

	@EJB
	private GrlTipoPessoaFacade grlTipoPessoaFacade;

	private List<GrlTipoPessoa> tipoPessoaList;

	/**
	 * Creates a new instance of GrlTipoDocumentoBean
	 */
	public GrlTipoPessoaBean()
	{
	}

	@PostConstruct
	public void init()
	{
		tipoPessoaList = this.grlTipoPessoaFacade.findAllOrderByNome();
	}

	public static GrlTipoPessoa getInstanciaBean()
	{
		return (GrlTipoPessoa) GeradorCodigo.getInstanciaBean("grlTipoPessoaBean");
	}

	public static GrlTipoPessoa getInstancia()
	{
		GrlTipoPessoa grlTipoPessoa = new GrlTipoPessoa();
		return grlTipoPessoa;
	}

	public List<GrlTipoPessoa> getTipoPessoaList()
	{
		return tipoPessoaList;
	}

}
