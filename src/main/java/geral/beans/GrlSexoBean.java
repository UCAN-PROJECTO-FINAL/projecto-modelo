/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geral.beans;

import ejbs.cache.GrlSexoCache;
import ejbs.entities.GrlSexo;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author aires
 */
@Named
@ApplicationScoped
public class GrlSexoBean implements Serializable
{

	@Inject
	private GrlSexoCache sexoFacade;

	private List<GrlSexo> sexos;

	/**
	 * Creates a new instance of SexoBean
	 */
	public GrlSexoBean()
	{
	}

	@PostConstruct
	public void init()
	{
		this.sexos = this.sexoFacade.findAllOrderedByNome();
	}

	public List<GrlSexo> getSexos()
	{
		return sexos;
	}

}
