/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notas.utils;

import java.util.Date;
import utils.DataUtils;

/**
 *
 * @author aires
 */
public abstract class Nota
{
    private Date data;
    private String nota;
    private NotaTipoEnum notaTipo;

    public Nota(String nota, NotaTipoEnum notaTipo)
    {
        data = new Date();
        this.nota = nota;
        this.notaTipo = notaTipo;
    }

    // Business Methods
   
    public String geraReport()
    {
        return "[" + DataUtils.dateToString(data) + "]: " + nota; 
    }
    
    public String geraReportWithoutData()
    {
        return nota; 
    }
    
    
    // Getters and Setters
    
    public String getNota()
    {
        return nota;
    }

    public void setNota(String nota)
    {
        this.nota = nota;
    }

    public NotaTipoEnum getNotaTipo()
    {
        return notaTipo;
    }

    public void setNotaTipo(NotaTipoEnum notaTipo)
    {
        this.notaTipo = notaTipo;
    }

    public Date getData()
    {
        return data;
    }
    
    
}
