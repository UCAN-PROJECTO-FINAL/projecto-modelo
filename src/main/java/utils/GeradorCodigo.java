/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import javax.faces.context.FacesContext;

/**
 *
 * @author aires
 */
public class GeradorCodigo
{

	public static Object getInstanciaBean(String beanName)
	{
		FacesContext c = FacesContext.getCurrentInstance();
		return c.getELContext().getELResolver().getValue(c.getELContext(), null, beanName);
	}
}
