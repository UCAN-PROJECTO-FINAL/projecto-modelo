/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils.converters;

import ejbs.entities.LocLocalidade;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author herman
 */
@FacesConverter("utils.converters.ObjectConverter")
public class ObjectConverter implements Converter
{

    @Override
    public Object getAsObject(FacesContext ctx, UIComponent component, String value)
    {

        return component.getAttributes().get(value);

    }

    @Override
    public String getAsString(FacesContext ctx, UIComponent component, Object value)
    {

        String objAsString = null;

        if (value != null)
        {

            objAsString = Integer.toString(System.identityHashCode(value));

            component.getAttributes().put(objAsString, value);

        }

        return objAsString;

    }
}
