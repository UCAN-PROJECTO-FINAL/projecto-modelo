/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estrutura.beans;

import ejbs.entities.EstruturaLogica;
import java.util.List;
import org.primefaces.model.DefaultTreeNode;

/**
 *
 * @author aires
 */
public class ELogicaNode extends DefaultTreeNode
{
    
    private List<ELogicaNode> filhos;
    private EstruturaLogica node;
    private ELogicaNode pai;
    private String label,value;
    
    public ELogicaNode(EstruturaLogica node, ELogicaNode pai)
    {
        this.node=node;
        this.pai=pai;
    }

    public ELogicaNode()
    {
        super();
    }

    @Override
    public void setSelected(boolean state)
    {
        super.setSelected(state);
    }
    /**
     *
     * @return
     */
    public List<ELogicaNode> getSons()
    {
       return null;
    }


    @Override
    public String toString()
    {
        return this.getData().toString();
    }
    
    public List<ELogicaNode> getFilhos()
    {
        return filhos;
    }

    public void setFilhos(List<ELogicaNode> filhos)
    {
        this.filhos = filhos;
    }

    public EstruturaLogica getNode()
    {
        return node;
    }

    public void setNode(EstruturaLogica node)
    {
        this.node = node;
    }

    public ELogicaNode getPai()
    {
        return pai;
    }

    public void setPai(ELogicaNode pai)
    {
        this.pai = pai;
    }

    public String getLabel()
    {
        return label;
    }

    public void setLabel(String label)
    {
        this.label = label;
    }

    public String getValue()
    {
        return value;
    }

    public void setValue(String value)
    {
        this.value = value;
    }
}
