/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seg.utils;

import ejbs.entities.SegFuncionalidade;
import java.util.ArrayList;
import java.util.List;
import org.primefaces.PrimeFaces;

/**
 *
 * @author aires
 */
public class SegFuncionalidadePermissaoNode extends SegFuncionalidadeDataNode
{

    private List<SegFuncionalidadePermissaoNode> filhos;

    private String color, backgroundColor;

    private boolean autoStart, selected;

    public SegFuncionalidadePermissaoNode(SegFuncionalidade segFuncionalidade)
    {
        super(segFuncionalidade);

        this.filhos = new ArrayList();
        this.autoStart = selected = false;
        color = "black";
        this.backgroundColor = "transparent";
    }

    public boolean temFilhos()
    {
        return !filhos.isEmpty();
    }

    public void addFilho(SegFuncionalidadePermissaoNode filho)
    {
        filhos.add(filho);
    }

    public void resetColors()
    {
        color = "black";
        this.backgroundColor = "transparent";
    }

    public void setSelectionColors()
    {
        color = "yellow";
        this.backgroundColor = "brown";
    }

    public void reverseSelectionColors()
    {
        color = "brown";
        this.backgroundColor = "yellow";
    }

    static int nn = 0;

    public void changeBlinkingColors()
    {
        ////System.err.println("0: SegFuncionalidadePermissaoNode.changeBlinkingColors()\tnn: " + ++nn);
        switch (color)
        {
            case "black":
                ////System.err.println("1: SegFuncionalidadePermissaoNode.changeBlinkingColors()\tnn: " + nn);
                this.reverseSelectionColors();
                break;
            case "yellow":
                ////System.err.println("2: SegFuncionalidadePermissaoNode.changeBlinkingColors()\tnn: " + nn);
                this.reverseSelectionColors();
                break;
        ////System.err.println("4: SegFuncionalidadePermissaoNode.changeBlinkingColors()\tnn: " + nn);
            case "brown":
                ////System.err.println("3: SegFuncionalidadePermissaoNode.changeBlinkingColors()\tnn: " + nn);
                this.setSelectionColors();
                break;
            default:
                break;
        }
    }
    
    public void setColors(boolean state)
    {
        if (state)
        {
            setSelectionColors();
        }
        else
        {
            resetColors();
        }
    }
    
    public void resetAutoStart()
    {
        if (this.autoStart)
        {
//System.err.println("0: SegFuncionalidadePermissaoNode.resetAutoStart()");            
            PrimeFaces.current().executeScript("PF('w_poller').stop()");
//System.err.println("1: SegFuncionalidadePermissaoNode.resetAutoStart()");            
            this.autoStart = false;
        }
    }

    // Getters and Setters
    public void setColors(String color, String backgroundColor)
    {
        this.color = color;
        this.backgroundColor = backgroundColor;
    }

    public String getColor()
    {
        return color;
    }

    public void setColor(String color)
    {
        this.color = color;
    }

    public String getBackgroundColor()
    {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor)
    {
        this.backgroundColor = backgroundColor;
    }

    public boolean isAutoStart()
    {
        return autoStart;
    }

    public void setAutoStart(boolean autoStart)
    {
        this.autoStart = autoStart;
    }

    public List<SegFuncionalidadePermissaoNode> getFilhos()
    {
        return filhos;
    }    

    public boolean isSelected()
    {
        return selected;
    }

    public void setSelected(boolean selected)
    {
        this.selected = selected;
        this.setColors(selected);
        if (!selected)
            this.resetAutoStart();
    }

    

}
