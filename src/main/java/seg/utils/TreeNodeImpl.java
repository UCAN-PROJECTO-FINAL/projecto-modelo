/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package seg.utils;

import seg.utils.TreeNodeType;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

/**
 *
 * @author aires
 */


public class TreeNodeImpl extends DefaultTreeNode 
{
 
    private static final long serialVersionUID = 5333810777428638968L;
 
    /**
     * Constructor which sets the {@link com.bluelotussoftware.example.jsf.TreeNodeType}, {@code Object}
     * data, and parent node.
     *
     * @param type The type of node this represents.
     * @param data {@code Object} value stored in the node.
     * @param parent the {@link org.primefaces.model.TreeNode} which is the
     * parent to this object, or {@code null} if this is the "root"
     * node.
     */
    public TreeNodeImpl(TreeNodeType type, Object data, TreeNode parent) 
    {
        super(type.getType(), data, parent);
    }
 
    /**
     * Constructor which sets {@code Object} data, and parent node.
     *
     * @param data {@code Object} value stored in the node.
     * @param parent parent the {@link org.primefaces.model.TreeNode} which is
     * the parent to this object, or {@code null} if this is the
     * "root" node.
     */
    public TreeNodeImpl(Object data, TreeNode parent) 
    {
        super(data, parent);
    }
 
    /**
     * This method returns {@link com.bluelotussoftware.example.jsf.TreeNodeType#getType()}
     * depending on whether the node is a "leaf" node which contains
     * no children, or a "node" if it contains children.
     *
     * @return {@link com.bluelotussoftware.example.jsf.TreeNodeType#getType()}
     * based on whether this node has child objects.
     */
    @Override
    public String getType() 
    {
        return super.getType();
        /*
        if (isLeaf()) 
        {
            
            return TreeNodeType.LEAF.getType();
        } 
        else 
        {
            return TreeNodeType.NODE.getType();
        }
         */
    }
}
