/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transaction.utils;

import java.util.ArrayList;
import java.util.List;
import notas.utils.Nota;

/**
 *
 * @author Aires Veloso
 */
public class NotasAndStatus
{
    private List<Nota> notas;
    private boolean status;

    public NotasAndStatus(List<Nota> notas, boolean status)
    {
        this.notas = notas;
        this.status = status;
    }

    public NotasAndStatus()
    {
        this.notas = new ArrayList();
        this.status = false;
    }

    
    // Getters and Setters
    public List<Nota> getNotas()
    {
        return notas;
    }

    public void setNotas(List<Nota> notas)
    {
        this.notas = notas;
    }

    public boolean isStatus()
    {
        return status;
    }

    public void setStatus(boolean status)
    {
        this.status = status;
    }
    
    
}
