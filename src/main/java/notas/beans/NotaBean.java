/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notas.beans;

//import ejbs.entities.PrjHistoricoNotaCandidaturaExcel;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import notas.utils.Nota;
import notas.utils.NotaTipoEnum;

/**
 *
 * @author aires
 */
@Named(value = "notaBean")
@ApplicationScoped
public class NotaBean
{

    /**
     * Creates a new instance of NotaBean
     */
    public NotaBean()
    {
    }
    
    public String getNota(Nota nota)
    {
        return nota.getNota();
    }
    
    public String geraReport(Nota nota)
    {
        return nota.geraReport();
    }
    
    public String geraReportWithoutData(Nota nota)
    {
        return nota.geraReportWithoutData();
    }
    
    public String getColor(Nota nota)
    {
        return nota == null ? "orange" : nota.getNotaTipo().getColor();
    }
    
//    public String getColor(PrjHistoricoNotaCandidaturaExcel it)
//    {
//        NotaTipoEnum notaTipoEnum = NotaTipoEnum.fromInteger(it.getNotaTipo());
//        return notaTipoEnum.getColor();
//    }
    
    
    public String heightNotasReport(List<Nota> notas)
    {
        int sz = notas.size();
        return notas.isEmpty() ? "5vw" : (int) ((sz > 15 ? 15 : 5) * 1.2) + "vw";
    }

}
