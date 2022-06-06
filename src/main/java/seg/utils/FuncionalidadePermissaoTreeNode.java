/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seg.utils;

import java.util.ArrayList;
import java.util.List;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

/**
 *
 * @author aires
 */
public class FuncionalidadePermissaoTreeNode extends DefaultTreeNode
{

    public FuncionalidadePermissaoTreeNode(SegFuncionalidadePermissaoNode segFuncionalidadePermissaoNode,
        FuncionalidadePermissaoTreeNode funcionalidadePermissaoTreeNode)
    {
        super(segFuncionalidadePermissaoNode, funcionalidadePermissaoTreeNode);
    }

    @Override
    public void setSelected(boolean state)
    {
        super.setSelected(state);
//System.err.println("0: FuncionalidadePermissaoTreeNode.setSelected()\tstate: " + state);        
        SegFuncionalidadePermissaoNode segFuncionalidade = (SegFuncionalidadePermissaoNode) this.getData();
        segFuncionalidade.setSelected(state);
    }

    @Override
    public SegFuncionalidadePermissaoNode getData()
    {
        return (SegFuncionalidadePermissaoNode) super.getData();
    }

    /**
     *
     * @return
     */
    public List<FuncionalidadePermissaoTreeNode> getSuns()
    {
        List<FuncionalidadePermissaoTreeNode> list = new ArrayList();
        List<TreeNode> treeNodeList = super.getChildren();
        treeNodeList.forEach((node) ->
        {
            list.add((FuncionalidadePermissaoTreeNode) node);
        }
        );
        return list;
    }

    public boolean isForm()
    {
        SegFuncionalidadePermissaoNode segFuncionalidade = (SegFuncionalidadePermissaoNode) getData();
        switch (SegTipoFuncionalidadeEnum.fromSegTipoFuncionalidade(segFuncionalidade.getFkSegTipoFuncionalidade()))
        {
            case FORM:
                return true;
            default:
                return false;
        }
    }

    public SegTipoFuncionalidadeEnum getSegTipoFuncionalidadeEnum()
    {
        SegFuncionalidadePermissaoNode segFuncionalidade = (SegFuncionalidadePermissaoNode) getData();

        return SegTipoFuncionalidadeEnum.fromSegTipoFuncionalidade(segFuncionalidade.getFkSegTipoFuncionalidade());
    }

    @Override
    public String toString()
    {
        return this.getData().toString();
    }

    public void resetAutoStart()
    {
        SegFuncionalidadePermissaoNode segFuncionalidade = this.getData();
        segFuncionalidade.resetAutoStart();
    }
}
