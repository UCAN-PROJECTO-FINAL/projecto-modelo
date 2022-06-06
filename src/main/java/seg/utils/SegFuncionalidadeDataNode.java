/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package seg.utils;

import ejbs.entities.SegFuncionalidade;
import ejbs.entities.SegTipoFuncionalidade;


/**
 *
 * @author aires
 */
public class SegFuncionalidadeDataNode extends SegFuncionalidade
{
    
    protected int pkSegFuncionalidadeDataNode;

    
    public SegFuncionalidadeDataNode(SegFuncionalidade segFuncionalidade)
    {
        super(segFuncionalidade.getPkSegFuncionalidade());
        
        this.setFkSegFuncionalidadePai(segFuncionalidade.getFkSegFuncionalidadePai());
        this.setFkSegTipoFuncionalidade(segFuncionalidade.getFkSegTipoFuncionalidade());
        this.setDescricao(segFuncionalidade.getDescricao());
        this.setId(segFuncionalidade.getId());
        
//        this.setSegFuncionalidadeList(segFuncionalidade.getSegFuncionalidadeList());
//        this.setUrlPadrao(segFuncionalidade.getUrlPadrao());
    }
    
    public SegFuncionalidade getSegFuncionalidade()
    {
        SegFuncionalidade segFuncionalidade = new SegFuncionalidade(getPkSegFuncionalidade());
        segFuncionalidade.setDescricao(this.getDescricao());
        segFuncionalidade.setFkSegFuncionalidadePai(this.getFkSegFuncionalidadePai());
        segFuncionalidade.setFkSegTipoFuncionalidade(this.getFkSegTipoFuncionalidade());
        segFuncionalidade.setId(this.getId());
        
        return segFuncionalidade;
    }
    
    public String geraLabel()
    {
        SegTipoFuncionalidade segTipoFuncionalidade = this.getFkSegTipoFuncionalidade();
        return SegTipoFuncionalidadeEnum.fromSegTipoFuncionalidade(segTipoFuncionalidade).getDescricao()
            + "(" + this.getDescricao() + ")";
    }

    public String toString()
    {
        return geraLabel();
    }

    // Getters and Setters

    public int getPkSegFuncionalidadeDataNode()
    {
        return pkSegFuncionalidadeDataNode;
    }

    public void setPkSegFuncionalidadeDataNode(int pkSegFuncionalidadeDataNode)
    {
        this.pkSegFuncionalidadeDataNode = pkSegFuncionalidadeDataNode;
    }
    

}
