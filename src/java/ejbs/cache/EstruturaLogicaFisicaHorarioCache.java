/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs.cache;

import ejbs.entities.EstruturaLogicaFisicaHorario;
import ejbs.facades.EstruturaLogicaFisicaHorarioFacade;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

/**
 *
 * @author KiamiSoft_ACosta
 */
@Named
@ApplicationScoped
public class EstruturaLogicaFisicaHorarioCache implements Serializable
{

	@EJB
	private EstruturaLogicaFisicaHorarioFacade estruturaLogicaFisicaHorarioFacade;
	private List<EstruturaLogicaFisicaHorario> listaEstruturaLogicaFisicaHorario;
        private HashMap<Integer, EstruturaLogicaFisicaHorario> hashTabela;

	/**
	 * Creates a new instance of PaisBean
	 */
	public EstruturaLogicaFisicaHorarioCache()
	{
	}

	/**
	 *
	 */
	@PostConstruct
	public void init()
	{
		listaEstruturaLogicaFisicaHorario = estruturaLogicaFisicaHorarioFacade.findAll();
                hashTabela = new HashMap();
        int pkEstruturaLogicaFisica;
        for (EstruturaLogicaFisicaHorario reg : listaEstruturaLogicaFisicaHorario)
        {
            pkEstruturaLogicaFisica = reg.getPkEstruturaLogicaFisicaHorario();
            
            hashTabela.put(pkEstruturaLogicaFisica, reg);
        }
            //listaEstruturaFisica = localidadeFacade.findAll();
	}

	
    public void create(EstruturaLogicaFisicaHorario reg)
    {
        estruturaLogicaFisicaHorarioFacade.create(reg);
    }

    public void edit(EstruturaLogicaFisicaHorario reg)
    {
        estruturaLogicaFisicaHorarioFacade.edit(reg);
    }

    public void remove(EstruturaLogicaFisicaHorario reg)
    {
        estruturaLogicaFisicaHorarioFacade.remove(reg);
    }
    
}
