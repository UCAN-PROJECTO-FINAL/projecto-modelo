/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seg.utils;

import ejbs.cache.SegTipoFuncionalidadeCache;
import ejbs.entities.SegFuncionalidade;
import ejbs.facades.SegFuncionalidadeFacade;
import java.util.HashMap;
import java.util.List;
import org.primefaces.event.NodeCollapseEvent;
import org.primefaces.event.NodeExpandEvent;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.event.NodeUnselectEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import utils.StringUtils;
import utils.TreeNodeInterface;           
import utils.TreeNodeUtilities;           

/**
 *
 * @author aires
 */

                         
public abstract class SegTreeFuncionalidadesAbstract implements TreeNodeInterface
{

    protected SegTipoFuncionalidadeCache segTipoFuncionalidadeCache;
    protected SegFuncionalidadeFacade segFuncionalidadeFacade;
    protected TreeNode root, selectedNode;

    protected List<SegFuncionalidade> segFuncionalidades;
    protected HashMap<Integer, SegFuncionalidade> tabelaSegFuncionalidades;
    
    protected HashMap<Integer, TreeNode> tabelaSegFuncionalidadesTreNodes;
    
    private int pkSegFuncionalidadeDataNode;
    
    public SegTreeFuncionalidadesAbstract(SegFuncionalidadeFacade segFuncionalidadeFacade,
        SegTipoFuncionalidadeCache segTipoFuncionalidadeCache)
    {
        this.segFuncionalidadeFacade = segFuncionalidadeFacade;
        
        segFuncionalidades = segFuncionalidadeFacade.findAll();
        tabelaSegFuncionalidadesTreNodes = new HashMap();
        pkSegFuncionalidadeDataNode = 1;
        
        initTabelaSegFuncionalidades();
        
        this.segTipoFuncionalidadeCache = segTipoFuncionalidadeCache;
        System.err.println("0: SegTreeFuncionalidadesAbstract.SegTreeFuncionalidadesAbstract()\tsegTipoFuncionalidadeCache: "
            + (this.segTipoFuncionalidadeCache == null ? "null" : "not null"));
    }
    

    // Inicio da implementação default dos métodos da Interface TreeNodeInterface
    @Override
    public void onNodeSelect(NodeSelectEvent event)
    {
        if (selectedNode != null)
        {
            selectedNode.setSelected(false);
        }
//System.err.println("0: SegTreeFuncionalidadesAbstract.onNodeSelect()");        
//debugNodeTeste();
        selectedNode = event.getTreeNode();
        selectedNode.setSelected(true);
        SegFuncionalidade segFuncionalidade = (SegFuncionalidade) selectedNode.getData();

        SegFuncionalidade segFuncionalidadePai = segFuncionalidade.getFkSegFuncionalidadePai();
        switch (SegTipoFuncionalidadeEnum.fromSegTipoFuncionalidade(segFuncionalidade.getFkSegTipoFuncionalidade()))
        {
            case FORM:
                System.err.println("1: SegTreeFuncionalidadesAbstract.onNodeSelect(SegFuncionalidade)\npkIdFuncionalidade: "
                    + segFuncionalidade.getPkSegFuncionalidade() + "\t"
                    + (segFuncionalidadePai == null ? "null" : segFuncionalidadePai.getDescricao()));
                if (!this.segFuncionalidadeFacade.isRoot(segFuncionalidadePai))
                {
                    String selectedNodeName = selectedNode.toString();
                    TreeNode node = getRootSun(selectedNodeName);
                    if (node != null)
                    {
                        node.setSelected(true);
                    }
                    System.err.println("2: SegTreeFuncionalidadesAbstract.onNodeSelect(SegFuncionalidade)\tpkIdFuncionalidade: " + segFuncionalidade.getPkSegFuncionalidade());
                    return;
                }
            default:
//System.err.println("1: SegTreeFuncionalidadesAbstract.onNodeSelect()\tselectedNode: " + node);

        }
    }

    public TreeNode getRootSun(String nodeName)
    {
        List<TreeNode> listaChildren = root.getChildren();
        for (TreeNode node : listaChildren)
        {
            if (node.getData().toString().equals(nodeName))
            {
                return node;
            }
        }
        return null;
    }

    void debugNodeTeste()
    {
        TreeNode treeNode = TreeNodeUtilities.getTreeNode(root, "Gerir Perfis");
        if (treeNode == null)
        {
            return;
        }
//System.err.println("0: SegTreeFuncionalidadesAbstract.debugNodeTeste()\tselectedNode.name: " + ((SegFuncionalidade)treeNode.getData()).getNome());

    }

    @Override
    public void onNodeUnSelect(NodeUnselectEvent event
    )
    {
        //System.err.println("Node Data ::" + ((SegFuncionalidade)event.getTreeNode().getData()).getNome() + " :: UnSelected");
    }

    @Override
    public void onNodeExpand(NodeExpandEvent event
    )
    {
        String node = ((SegFuncionalidade) event.getTreeNode().getData()).getDescricao();
//System.err.println("0: SegTreeFuncionalidadesAbstract.onNodeExpand()\tnode: " + node);
//debugNodeTeste();
    }

    @Override
    public void onNodeCollapse(NodeCollapseEvent event
    )
    {
        String node = ((SegFuncionalidade) event.getTreeNode().getData()).getDescricao();
        //System.err.println("0: SegTreeFuncionalidadesAbstract.onNodeCollapse()\tnode: " + node);
    }

    @Override
    public void initSelectedNode()
    {
    }
    // Fim da implementação default dos métodos da Interface TreeNodeInterface

    private void initTabelaSegFuncionalidades()
    {
        this.tabelaSegFuncionalidades = new HashMap();
        for (SegFuncionalidade segFuncionalidade : segFuncionalidades)
        {
            tabelaSegFuncionalidades.put(segFuncionalidade.getPkSegFuncionalidade(), segFuncionalidade);
        }
    }
    
    public String getSelectedNodeName()
    {
        return ((SegFuncionalidade) this.selectedNode.getData()).getDescricao();
    }

    public SegFuncionalidade getSegFuncionalidadeSelectedNode()
    {
//System.err.println ("0: SegTreeFuncionalidadesAbstract.getSegFuncionalidadeSelectedNode()\tselectedNode.name: " + ((SegFuncionalidade)selectedNode.getData()).getNome());            
        int pkIdFuncionalidadeSelectedNode = ((SegFuncionalidade) selectedNode.getData()).getPkSegFuncionalidade();

//System.err.println ("2: SegTreeFuncionalidadesAbstract.getSegFuncionalidadeSelectedNode()");            
        SegFuncionalidade segFuncionalidade = this.segFuncionalidadeFacade.find(pkIdFuncionalidadeSelectedNode);
        return segFuncionalidade;
    }

    public void gerarChildNodeInSelectedNode(SegFuncionalidade segFuncionalidadeFilho)
    {
        SegFuncionalidade segFuncionalidadeChild = this.segFuncionalidadeFacade.findByNomeByPai(segFuncionalidadeFilho.getDescricao(), segFuncionalidadeFilho.getFkSegFuncionalidadePai());

        TreeNode treeNode = new DefaultTreeNode(new SegFuncionalidadeDataNode(segFuncionalidadeChild), this.selectedNode);
        int fkIdFuncionalidadeChild = segFuncionalidadeChild.getPkSegFuncionalidade();
        //    System.err.println("0: SegTreeFuncionalidadesAbstract.gerarChildNodeInSelectedNode()\ttreeNode.name: " + ((SegFuncionalidade)treeNode.getData()).getNome());

        TreeNodeUtilities.setSelected(root, treeNode);
    }

    public void gerarTreeNodes(TreeNode noPai)
    {
System.err.println("0: SegTreeFuncionalidadesAbstract.gerarTreeNodes(TreeNode)\tnoPai: " +
            (noPai == null ? "null" : noPai.toString()));        
        /*
         perfisFilhos = obterPerfisFilhos(noPai)
         if (!perfisFilhos)
         retornar
         para cada perfil de perfisFilhos
         se ! listaAmbCidPerfisNomes contem perfil
         continuar
         criar no(perfil, noPai)
         gerarTreeNodes(no, listaAmbCidPerfisNomes)
         */
//debugNodeTeste();

//        int pkIdFuncionalidade = ((SegFuncionalidade)noPai.getData()).getPkSegFuncionalidade();
        SegFuncionalidade segFuncionalidadePai = (SegFuncionalidade) noPai.getData();
System.err.println("1: SegTreeFuncionalidadesAbstract.gerarTreeNodes(TreeNode)\tsegFuncionalidadePai: " +
            (segFuncionalidadePai == null ? "null" : segFuncionalidadePai.toString()));        
        List<SegFuncionalidade> listaSegFuncionalidadeFilhos = segFuncionalidadeFacade.findListaSegFuncionalidadeFilhos(segFuncionalidadePai);
System.err.println("2: SegTreeFuncionalidadesAbstract.gerarTreeNodes()");
//debugNodeTeste();
        if (listaSegFuncionalidadeFilhos == null || listaSegFuncionalidadeFilhos.size() == 0)
        {
System.err.println("2.1: SegTreeFuncionalidadesAbstract.gerarTreeNodes()");         
//debugNodeTeste();
            return;
        }
System.err.println("3: SegTreeFuncionalidadesAbstract.gerarTreeNodes()");    
//debugNodeTeste();
        TreeNode treeNode;
        String iconType;
        SegFuncionalidadeDataNode segFuncionalidadeDataNode;
        for (SegFuncionalidade segFuncionalidadeFilho : listaSegFuncionalidadeFilhos)
        {
            segFuncionalidadeFilho.setFkSegFuncionalidadePai(segFuncionalidadePai);
            
//System.err.println("4: SegTreeFuncionalidadesAbstract.gerarTreeNodes()");            
//debugNodeTeste();
//System.err.println("4.1: SegTreeFuncionalidadesAbstract.gerarTreeNodes()\tnoPai.name: " + ((SegFuncionalidade)noPai.getData()).getNome());
            System.err.println("4.3: SegTreeFuncionalidadesAbstract.gerarTreeNodes()\tsegFuncionalidadeFilho.nome: " + ((SegFuncionalidade) segFuncionalidadeFilho).getDescricao());
            iconType = SegTipoFuncionalidadeEnum.fromInteger(segFuncionalidadeFilho.getFkSegTipoFuncionalidade().
                getPkSegTipoFuncionalidade()).getTipo();
            System.err.println("4.4: SegTreeFuncionalidadesAbstract.gerarTreeNodes()\tsegFuncionalidadeFilho.nome: " + ((SegFuncionalidade) segFuncionalidadeFilho).getDescricao());
            segFuncionalidadeDataNode = new SegFuncionalidadeDataNode(segFuncionalidadeFilho);
            segFuncionalidadeDataNode.setPkSegFuncionalidadeDataNode(this.pkSegFuncionalidadeDataNode++);
            treeNode = StringUtils.isNull(iconType)
                ? new DefaultTreeNode(segFuncionalidadeDataNode, noPai)
                : new DefaultTreeNode(iconType, segFuncionalidadeDataNode, noPai);
            
            this.tabelaSegFuncionalidadesTreNodes.put(segFuncionalidadeDataNode.getPkSegFuncionalidadeDataNode(), treeNode);
System.err.println("5: SegTreeFuncionalidadesAbstract.gerarTreeNodes()");
//debugNodeTeste();
//            pkIdFuncionalidade = segFuncionalidadeFilho.getPkSegFuncionalidade();
//System.err.println("6: SegTreeFuncionalidadesAbstract.gerarTreeNodes()\ttreeNode.name: " + ((SegFuncionalidade)treeNode.getData()).getNome());
//this.debugNodeTeste();

            gerarTreeNodes(treeNode);
System.err.println("7: SegTreeFuncionalidadesAbstract.gerarTreeNodes()");            
        }
System.err.println("8: SegTreeFuncionalidadesAbstract.gerarTreeNodes()");        
    }

    public void initRootFromDataBase()
    {
        gerarTreeNodes(root);
    }

    public void initRoot()
    {
        System.err.println("0: SegTreeFuncionalidadesAbstract.initRoot()\tsegTipoFuncionalidadeCache: "
            + (segTipoFuncionalidadeCache == null ? "null" : "not null"));

        SegFuncionalidadeDataNode segFuncionalidadeDataNode = new SegFuncionalidadeDataNode(segFuncionalidadeFacade.geraSegFuncionalidadeRoot());
        segFuncionalidadeDataNode.setPkSegFuncionalidadeDataNode(this.pkSegFuncionalidadeDataNode++);
        root = new DefaultTreeNode(segFuncionalidadeDataNode, null);
        
        this.tabelaSegFuncionalidadesTreNodes.put(segFuncionalidadeDataNode.getPkSegFuncionalidadeDataNode(), root);
        
        System.err.println("1: SegTreeFuncionalidadesAbstract.initRoot()\troot.name: " + root.toString());
        root.setRowKey(((SegFuncionalidade) root.getData()).getDescricao());
        System.err.println("2: SegTreeFuncionalidadesAbstract.initRoot()\troot.name: " + root.toString());
        initRootFromDataBase();
        System.err.println("3: SegTreeFuncionalidadesAbstract.initRoot()");
        initSelectedNode();
        System.err.println("4: SegTreeFuncionalidadesAbstract.initRoot()");
    }

    public TreeNode getRoot()
    {
        return root;
    }

    public void setRoot(TreeNode root)
    {
        this.root = root;
    }

    public TreeNode getSelectedNode()
    {
        return selectedNode;
    }

    public void setSelectedNode(TreeNode selectedNode)
    {
        this.selectedNode = selectedNode;
    }

    public SegFuncionalidadeFacade getSegFuncionalidadeFacade()
    {
        return segFuncionalidadeFacade;
    }

    public void setSegFuncionalidadeFacade(SegFuncionalidadeFacade segFuncionalidadeFacade)
    {
        this.segFuncionalidadeFacade = segFuncionalidadeFacade;
    }

    public SegTipoFuncionalidadeCache getSegTipoFuncionalidadeCache()
    {
        return segTipoFuncionalidadeCache;
    }

    public void setSegTipoFuncionalidadeCache(SegTipoFuncionalidadeCache segTipoFuncionalidadeCache)
    {
        this.segTipoFuncionalidadeCache = segTipoFuncionalidadeCache;
    }

    public List<SegFuncionalidade> getSegFuncionalidades()
    {
        return segFuncionalidades;
    }

    public HashMap<Integer, TreeNode> getTabelaSegFuncionalidadesTreNodes()
    {
        return tabelaSegFuncionalidadesTreNodes;
    }

    public HashMap<Integer, SegFuncionalidade> getTabelaSegFuncionalidades()
    {
        return tabelaSegFuncionalidades;
    }

    
}
