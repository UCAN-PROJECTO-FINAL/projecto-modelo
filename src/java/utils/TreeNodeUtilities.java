/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.List;
import org.primefaces.model.TreeNode;

/**
 *
 * @author aires
 */
public class TreeNodeUtilities
{

    public static boolean isUnSelectedAllSuns(TreeNode node)
    {
        List<TreeNode> listaChildren = node.getChildren();
        if (listaChildren == null || listaChildren.isEmpty())
        {
            return true;
        }
        for (TreeNode sun : listaChildren)
        {
            if (sun.isSelected())
            {
                return false;
            }
            if (!isUnSelectedAllSuns(sun))
            {
                return false;
            }
        }
        return true;
    }

    public static boolean allTreeIsUnSelected(TreeNode node)
    {
        if (node.isSelected())
        {
            return false;
        }
        return isUnSelectedAllSuns(node);
    }

    public static boolean isSelectedAllSuns(TreeNode node)
    {
        List<TreeNode> listaChildren = node.getChildren();
        if (listaChildren == null || listaChildren.isEmpty())
        {
            return true;
        }
        for (TreeNode sun : listaChildren)
        {
            if (!sun.isSelected())
            {
                return false;
            }
            if (!isSelectedAllSuns(sun))
            {
                return false;
            }
        }
        return true;
    }

    public static boolean allTreeIsSelected(TreeNode node)
    {
        if (!node.isSelected())
        {
            return false;
        }
        return isSelectedAllSuns(node);
    }

    public static void selectOrUnSelectAllTree(TreeNode node, boolean state)
    {
        node.setSelected(state);
        List<TreeNode> listaChildren = node.getChildren();
        if (listaChildren == null || listaChildren.isEmpty())
        {
            return;
        }
        listaChildren.forEach((sun) ->
        {
            selectOrUnSelectAllTree(sun, state);
        });
    }
    
    public static void selectOrUnSelectAllSuns(TreeNode node, boolean state)
    {
        List<TreeNode> listaChildren = node.getChildren();
System.err.println("0: TreeNodeUtilities.selectOrUnSelectAllSuns()\tnode: " + node);        
        if (listaChildren == null || listaChildren.isEmpty())
        {
System.err.println("1: TreeNodeUtilities.selectOrUnSelectAllSuns()\tnode: " + node);            
            return;
        }
System.err.println("2: TreeNodeUtilities.selectOrUnSelectAllSuns()\tnode: " + node);        
        for (TreeNode sun : listaChildren)
        {
System.err.println("3: TreeNodeUtilities.selectOrUnSelectAllSuns()\tnode: " + node);            
            selectOrUnSelectAllTree(sun, state);
System.err.println("4: TreeNodeUtilities.selectOrUnSelectAllSuns()\tnode: " + node);            
        }
System.err.println("5: TreeNodeUtilities.selectOrUnSelectAllSuns()\tnode: " + node);        
    }

    public static TreeNode getTreeNode(TreeNode root, String nome)
    {
        if (root.getData().toString().equals(nome))
        {
            return root;
        }

        TreeNode result = null;
        List<TreeNode> listaChildren = root.getChildren();
        for (TreeNode node : listaChildren)
        {
            result = getTreeNode(node, nome);
            if (result != null)
            {
                return result;
            }
        }
        return null;
    }

    public static void setSelected(TreeNode root, TreeNode selectedNode)
    {
        for (TreeNode t = selectedNode;; t = t.getParent())
        {
            t.setExpanded(true);
            if (t == root)
            {
                break;
            }
        }
        selectedNode.setSelected(true);
    }

    public static void setSelectedAllAncesters(TreeNode node)
    {
        System.err.println("0: TreeNodeUtilities.setSelectedAllAncesters()");
        TreeNode t = node.getParent();
        if (t == null)
        {
            System.err.println("1: TreeNodeUtilities.setSelectedAllAncesters()");
            return;
        }

        System.err.println("2: TreeNodeUtilities.setSelectedAllAncesters()\tt: " + t);
        for (; t != null; t = t.getParent())
        {
            System.err.println("3: TreeNodeUtilities.setSelectedAllAncesters()\tt: " + t);
            t.setExpanded(true);
            t.setSelected(true);
        }
    }
}
