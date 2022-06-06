/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * @author aires
 */
import org.primefaces.event.*;

public interface TreeNodeInterface
{

	public void onNodeSelect(NodeSelectEvent event);

	public void onNodeUnSelect(NodeUnselectEvent event);

	public void onNodeExpand(NodeExpandEvent event);

	public void onNodeCollapse(NodeCollapseEvent event);

	public void initSelectedNode();

}
