/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contabilidade.util;

import ejbs.entities.CtRubrica;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.primefaces.component.picklist.PickList;
import org.primefaces.model.DualListModel;

/**
 *
 * @author majoao
 */
@FacesConverter(value = "rubricaConverter")
public class PickListRubricaConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {

        DualListModel<CtRubrica> model = (DualListModel<CtRubrica>) ((PickList) uic).getValue();

        for (CtRubrica rubrica : model.getSource()) {
            if ((rubrica.getPkRubrica().intValue() + "").equals(string)) {
                return rubrica;
            }
        }
        for (CtRubrica rubrica : model.getTarget()) {
            if ((rubrica.getPkRubrica().intValue() + "").equals(string)) {
                return rubrica;
            }
        }
        return null;

    }

    @Override
    public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
