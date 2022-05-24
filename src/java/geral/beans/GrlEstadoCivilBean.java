/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geral.beans;

//import ejbs.cache.GrlEstadoCivilCache;
import ejbs.entities.GrlEstadoCivil;
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
@Named(value = "grlEstadoCivilBean")
@ApplicationScoped
public class GrlEstadoCivilBean implements Serializable
{

//	@Inject
//	private GrlEstadoCivilCache grlEstadoCivilCache;

	private List<GrlEstadoCivil> estadosCivis;

	/**
	 * Creates a new instance of GrlEstadoCivilBean
	 */
	public GrlEstadoCivilBean()
	{
	}

	@PostConstruct
	public void init()
	{
		//this.estadosCivis = this.grlEstadoCivilCache.findAllOrderedByNome();
	}

	public List<GrlEstadoCivil> getEstadosCivis()
	{
		return estadosCivis;
	}

}
