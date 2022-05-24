/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seg.beans;

import ejbs.cache.SegFuncionalidadeCache;         
import ejbs.entities.SegConta;
import ejbs.entities.SegFuncionalidade;
import ejbs.entities.SegPerfil;
import ejbs.entities.SegPerfilHasFuncionalidade;
import ejbs.facades.SegContaFacade;
import ejbs.facades.SegFuncionalidadeFacade;
import ejbs.facades.SegPerfilFacade;
import ejbs.facades.SegPerfilHasFuncionalidadeFacade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.inject.Named;
import org.omnifaces.cdi.ViewScoped;
import org.primefaces.PrimeFaces;
import org.primefaces.event.NodeCollapseEvent;
import org.primefaces.event.NodeExpandEvent;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.event.NodeUnselectEvent;
import org.primefaces.model.TreeNode;
import seg.utils.FuncionalidadePermissaoTreeNode;
import seg.utils.SegFuncionalidadePermissaoNode;
import seg.utils.SegTipoFuncionalidadeEnum;
import utils.TreeNodeUtilities;               
import utils.mensagens.LogFile;
import utils.parametros_entre_formularios.ParametrosEntreFormulariosBean;     

/**
 *
 * @author helena
 */
@Named(value = "segPermissaoBean")
@ViewScoped
public class SegPermissaoBean implements Serializable
{

    @EJB
    private SegContaFacade segContaFacade;

    @EJB
    private SegPerfilHasFuncionalidadeFacade segPerfilHasFuncionalidadeFacade;

    @EJB
    private SegPerfilFacade segPerfilFacade;

    @EJB
    private SegFuncionalidadeFacade segFuncionalidadeFacade;

    @Inject
    private SegFuncionalidadeCache segFuncionalidadeCache;    

    @Inject
    ParametrosEntreFormulariosBean parametrosEntreFormulariosBean;    

    private FuncionalidadePermissaoTreeNode root, selectedNode;
    private List<FuncionalidadePermissaoTreeNode> selectedNodes;
    private TreeNode[] nosSelecionados;
    private TreeNode[] checkboxSelectedTreeNodes;
    private TreeNode modulos;
    private SegPerfil perfilSelecionado, perfilCorrente;
    private List<SegPerfilHasFuncionalidade> funcionalidadesDoPerfil = new ArrayList();
    private List<SegPerfilHasFuncionalidade> removerListaPermissao = new ArrayList();
    private List<SegFuncionalidade> saveListaPermissao = new ArrayList();
    private List<SegFuncionalidade> funcionalidades = new ArrayList();
    private List<SegFuncionalidade> funcionalidadesPermitidos = new ArrayList<>();
    private SegConta segConta;

    private Integer pkSegFuncionalidadeDataNode;
    private HashMap<Integer, FuncionalidadePermissaoTreeNode> tabelaTreeNodes;

//    @ManagedProperty(value = "#{segPerfilListarBean}")
//    private SegPerfilListarBean segPerfilListarBean;
    public SegPermissaoBean()
    {
    }

    @PostConstruct
    public void init()
    {
        pkSegFuncionalidadeDataNode = 1;
        tabelaTreeNodes = new HashMap();
        selectedNodes = new ArrayList();
//        System.err.println("0: SegPermissaoBean.init()");
        segConta = SegLoginBean.getInstanciaBean().getContaUtilizador();
//        System.err.println("1: SegPermissaoBean.init()");
        recuperarPerfilSelecionado();
//        System.err.println("2: SegPermissaoBean.init()");
    }

    public FuncionalidadePermissaoTreeNode getRoot()
    {
        return root;
    }

    public void setRoot(FuncionalidadePermissaoTreeNode root)
    {
        this.root = root;
    }

    public TreeNode[] getNosSelecionados()
    {
        return nosSelecionados;
    }

    public void setNosSelecionados(TreeNode[] nosSelecionados)
    {
        this.nosSelecionados = nosSelecionados;
    }

    public SegPerfil recuperarPerfilSelecionado()
    {
        perfilSelecionado = (SegPerfil) this.parametrosEntreFormulariosBean.getParametro("perfilSelecionado");
        carregaPermissoesPerfil();
        return perfilSelecionado;
    }

    public SegPerfil getPerfilSelecionado()
    {
        return perfilSelecionado;
    }

    public void setPerfilSelecionado(SegPerfil perfilSelecionado)
    {
        this.perfilSelecionado = perfilSelecionado;

    }

    public List<SegPerfilHasFuncionalidade> getListaPermissao()
    {
        if (funcionalidadesDoPerfil == null)
        {
            funcionalidadesDoPerfil = new ArrayList<>();
        }
        return funcionalidadesDoPerfil;
    }

    public void setListaPermissao(List<SegPerfilHasFuncionalidade> funcionalidadesDoPerfil)
    {
        this.funcionalidadesDoPerfil = funcionalidadesDoPerfil;
    }

    public List<SegFuncionalidade> getListaFuncionalidade()
    {
        return funcionalidades;
    }

    public void setListaFuncionalidade(List<SegFuncionalidade> funcionalidades)
    {
        this.funcionalidades = funcionalidades;
    }

    public List<SegFuncionalidade> getFuncionalidadesPermitidos()
    {
        return funcionalidadesPermitidos;
    }

    public void setFuncionalidadesPermitidos(List<SegFuncionalidade> funcionalidadesPermitidos)
    {
        this.funcionalidadesPermitidos = funcionalidadesPermitidos;
    }

    public List<SegPerfil> getListaPerfil()
    {
        return segPerfilFacade.findAll();
    }

    private boolean isPermitido(SegFuncionalidade funcionalidade)
    {
        for (SegFuncionalidade f : funcionalidadesPermitidos)
        {
            if (f.equals(funcionalidade))
            {
                return true;
            }
        }
        return false;
    }

    public void gerarTreeNodes(FuncionalidadePermissaoTreeNode noPai)
    {
//        System.err.println("0: SegPermissaoBean.gerarTreeNodes()\nnoPai: " + (noPai == null ? "null" : noPai));
        SegFuncionalidadePermissaoNode segFuncionalidadePai = (SegFuncionalidadePermissaoNode) noPai.getData();
//        System.err.println("01: SegPermissaoBean.gerarTreeNodes()\nnoPai: " + noPai);
        List<SegFuncionalidade> listaSegFuncionalidadeFilhos = segFuncionalidadeCache.findListaSegFuncionalidadeFilhos(segFuncionalidadePai);
//        System.err.println("02: SegPermissaoBean.gerarTreeNodes()\nnoPai: " + noPai
//            + "\nlistaSegFuncionalidadeFilhos.size: " + (listaSegFuncionalidadeFilhos == null ? "null" : listaSegFuncionalidadeFilhos.size()));
//debugNodeTeste();
        if (listaSegFuncionalidadeFilhos == null || listaSegFuncionalidadeFilhos.size() == 0)
        {
//            System.err.println("1.1: SegPermissaoBean.gerarTreeNodes()");
//debugNodeTeste();
            return;
        }
//        System.err.println("2: SegPermissaoBean.gerarTreeNodes()");
//debugNodeTeste();
        FuncionalidadePermissaoTreeNode treeNode;
        SegFuncionalidadePermissaoNode segFuncionalidadeFilhoTmp;
        SegFuncionalidadePermissaoNode segFuncionalidadePermissaoNode;
        for (SegFuncionalidade segFuncionalidadeFilho : listaSegFuncionalidadeFilhos)
        {
            segFuncionalidadeFilho.setFkSegFuncionalidadePai(segFuncionalidadePai);
//System.err.println("3: SegPermissaoBean.gerarTreeNodes()");            
//            System.err.println("3.3: SegPermissaoBean.gerarTreeNodes()\tsegFuncionalidadeFilho.nome: " + ((SegFuncionalidade) segFuncionalidadeFilho).getDescricao());

//            System.err.println("3.4: SegPermissaoBean.gerarTreeNodes()\tsegFuncionalidadeFilho.nome: " + ((SegFuncionalidade) segFuncionalidadeFilho).getDescricao());
            segFuncionalidadePermissaoNode = new SegFuncionalidadePermissaoNode(new SegFuncionalidadePermissaoNode(segFuncionalidadeFilho));
            segFuncionalidadePermissaoNode.setPkSegFuncionalidadeDataNode(pkSegFuncionalidadeDataNode++);

            treeNode = new FuncionalidadePermissaoTreeNode(segFuncionalidadePermissaoNode, noPai);

            tabelaTreeNodes.put(treeNode.getData().getPkSegFuncionalidadeDataNode(), treeNode);

            segFuncionalidadeFilhoTmp = (SegFuncionalidadePermissaoNode) treeNode.getData();
            segFuncionalidadePai.addFilho(segFuncionalidadeFilhoTmp);

            if (isPermitido(segFuncionalidadeFilho))
            {
                treeNode.setSelected(true);
                this.addNodeToSelectedNodes(treeNode);
            }
//            System.err.println("4: SegPermissaoBean.gerarTreeNodes()");
//            System.err.println("5: SegPermissaoBean.gerarTreeNodes()\ttreeNode.name: " + ((SegFuncionalidade) treeNode.getData()).getDescricao());

            gerarTreeNodes(treeNode);
//            System.err.println("6: SegPermissaoBean.gerarTreeNodes()");
        }
//        System.err.println("7: SegPermissaoBean.gerarTreeNodes()");
    }

    private void geraRoot()
    {
        SegFuncionalidadePermissaoNode segFuncionalidadePermissaoNode = new SegFuncionalidadePermissaoNode(segFuncionalidadeFacade.geraSegFuncionalidadeRoot());
        segFuncionalidadePermissaoNode.setPkSegFuncionalidadeDataNode(pkSegFuncionalidadeDataNode++);

        root = new FuncionalidadePermissaoTreeNode(segFuncionalidadePermissaoNode, null);

        tabelaTreeNodes.put(root.getData().getPkSegFuncionalidadeDataNode(), root);
    }

    public void carregaPermissoesPerfil()
    {
//        System.err.println("0: SegPermissaoBean.carregaPermissoesPerfil()");
        if (perfilSelecionado != null)
        {
//            System.err.println("1: SegPermissaoBean.carregaPermissoesPerfil()");
            geraRoot();

            funcionalidadesPermitidos = new ArrayList<>();
            funcionalidadesDoPerfil = segPerfilHasFuncionalidadeFacade.findByPerfil(perfilSelecionado.getPkSegPerfil());
//            System.err.println("2: SegPermissaoBean.carregaPermissoesPerfil()\tfuncionalidadesDoPerfil: "
//                + (funcionalidadesDoPerfil == null ? "null" : funcionalidadesDoPerfil.size()));
            funcionalidades = segFuncionalidadeCache.findAll();
//            System.err.println("3: SegPermissaoBean.carregaPermissoesPerfil()\tfuncionalidades: "
//                + (funcionalidades == null ? "null" : funcionalidades.size()));
            if (funcionalidades != null)
            {
//                System.err.println("3: SegPermissaoBean.carregaPermissoesPerfil()");
                if (funcionalidadesDoPerfil != null)
                {
                    funcionalidadesDoPerfil.forEach((permissao) ->
                    {
                        funcionalidadesPermitidos.add(permissao.getFkSegFuncionalidade());
                    });
//                    System.err.println("4: SegPermissaoBean.carregaPermissoesPerfil()\tfuncionalidadesPermitidos: "
//                        + (funcionalidadesPermitidos == null ? "null" : funcionalidadesPermitidos.size()));
                }
//                System.err.println("5: SegPermissaoBean.carregaPermissoesPerfil()");
                gerarTreeNodes(root);
//                System.err.println("6: SegPermissaoBean.carregaPermissoesPerfil()");
            }
            else
            {
//                System.err.println("7: SegPermissaoBean.carregaPermissoesPerfil()");
                geraRoot();
//                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, null, "Nenhuma funcionalidade encontrada"));
                LogFile.warnMsg(null, "Nenhuma funcionalidade encontrada");
//                System.err.println("8: SegPermissaoBean.carregaPermissoesPerfil()");
            }
        }
        else
        {
//            System.err.println("9: SegPermissaoBean.carregaPermissoesPerfil()");
            geraRoot();
            LogFile.warnMsg(null, "Nenhum perfil selecionado");
        }
//        System.err.println("10: SegPermissaoBean.carregaPermissoesPerfil()");
    }

    public String salvaPermissoes()
    {
        System.err.println("0: SegPermissaoBean.salvaPermissoes()");
        if (perfilSelecionado == null)
        {
            LogFile.warnMsg(null, "Nenhum perfil selecionado");
        }
        else
        {
            System.err.println("1: SegPermissaoBean.salvaPermissoes()\tperfilSelecionado: " + perfilSelecionado.getDescricao());
            removePermissao(perfilSelecionado.getPkSegPerfil());
            System.err.println("2: SegPermissaoBean.salvaPermissoes()");
            savePermissao(perfilSelecionado.getPkSegPerfil());
            System.err.println("3: SegPermissaoBean.salvaPermissoes()");

            carregaPermissoesPerfil();
            System.err.println("4: SegPermissaoBean.salvaPermissoes()");
            //inicializar
            removerListaPermissao = new ArrayList<>();
            saveListaPermissao = new ArrayList<>();
            LogFile.sucessoMsg(null, "Permissões Salvas Permissões Salvas");
        }
        return "/seg_visao/segPerfil/seg_perfil_listar.xhtml?faces-redirect=true";
    }

    public void resetAllNodes()
    {
        resetAllTree(root);
    }

    public void resetAllTree(FuncionalidadePermissaoTreeNode node)
    {
        node.resetAutoStart();
        List<TreeNode> listaChildren = node.getChildren();
        if (listaChildren == null || listaChildren.isEmpty())
        {
            return;
        }
        listaChildren.forEach((sun) ->
        {
            resetAllTree((FuncionalidadePermissaoTreeNode) sun);
        });
    }

    public List<FuncionalidadePermissaoTreeNode> findSelectedNodes()
    {
        scanSelectedNodes(root);
        return selectedNodes;
    }

    public void scanSelectedNodes(FuncionalidadePermissaoTreeNode node)
    {
        if (node.isSelected())
        {
            selectedNodes.add(node);
        }
        List<TreeNode> listaChildren = node.getChildren();
        if (listaChildren == null || listaChildren.isEmpty())
        {
            return;
        }
        listaChildren.forEach((sun) ->
        {
            scanSelectedNodes((FuncionalidadePermissaoTreeNode) sun);
        });
    }

    // excluir as funcionalidades na tabela PerfilHasFuncioalidades 
    //que nao constam nos nós nao selecionadas do perfil selecionado
    public void removePermissao(int perfilSelecionado)
    {
System.err.println("0: SegPermissaoBean.removePermissao()\tperfilSelecionado: " + perfilSelecionado);        
        funcionalidadesDoPerfil = segPerfilHasFuncionalidadeFacade.findByPerfil(perfilSelecionado);
System.err.println("1: SegPermissaoBean.removePermissao()\tfuncionalidadesDoPerfil: " + 
            (funcionalidadesDoPerfil == null ? "null" : "not null"));       
            if (funcionalidadesDoPerfil == null)
                return;
System.err.println("2: SegPermissaoBean.removePermissao()\tfuncionalidadesDoPerfil: " + 
            (funcionalidadesDoPerfil == null ? "null" : "not null"));            
        for (SegPerfilHasFuncionalidade removePermissao : funcionalidadesDoPerfil)
        {
System.err.println("3: SegPermissaoBean.removePermissao()\tfuncionalidadesDoPerfil: " + 
            (funcionalidadesDoPerfil == null ? "null" : "not null"));            
            segPerfilHasFuncionalidadeFacade.remove(removePermissao);
System.err.println("4: SegPermissaoBean.removePermissao()\tfuncionalidadesDoPerfil: " + 
            (funcionalidadesDoPerfil == null ? "null" : "not null"));            
        }
System.err.println("5: SegPermissaoBean.removePermissao()\tfuncionalidadesDoPerfil: " + 
            (funcionalidadesDoPerfil == null ? "null" : "not null"));        
    }

    //save novos nós selecionada para BD
    public void savePermissao(int perfilSelecionado)
    {
        System.err.println("0: SegPermissaoBean.savePermissao(int)");
        saveListaPermissao = new ArrayList();
//        funcionalidadesDoPerfil = perfilPermissoesFacade.findByPerfil(perfilSelecionado);
        for (TreeNode no : selectedNodes)
        {
            SegFuncionalidade funcionalidadeSelecionado = ((SegFuncionalidadePermissaoNode) no.getData()).getSegFuncionalidade();

            //adicionar o no na lista
            if (funcionalidadeSelecionado.getPkSegFuncionalidade() != 0)
            {
                saveListaPermissao.add(funcionalidadeSelecionado);
            }
        }

        SegPerfil segPerfil = this.segPerfilFacade.find(perfilSelecionado);

        saveListaPermissao.forEach((func) ->
        {
            System.err.println("1: SegPermissaoBean.savePermissao(int)\tfunc" + func);
            SegPerfilHasFuncionalidade permissao = new SegPerfilHasFuncionalidade();
            permissao.setFkSegFuncionalidade(func);
            permissao.setFkSegPerfil(segPerfil);
            segPerfilHasFuncionalidadeFacade.create(permissao);
        });
    }

    public void setCheckboxSelectedTreeNodes(TreeNode[] checkboxSelectedTreeNodes)
    {
        this.checkboxSelectedTreeNodes = checkboxSelectedTreeNodes;
    }

    public FuncionalidadePermissaoTreeNode getRootSun(String nodeName)
    {
        List<FuncionalidadePermissaoTreeNode> listaChildren = root.getSuns();
        for (FuncionalidadePermissaoTreeNode node : listaChildren)
        {
            if (node.getData().toString().equals(nodeName))
            {
                return node;
            }
        }
        return null;
    }

    public void selectOrUnSelectAllSuns(FuncionalidadePermissaoTreeNode node, boolean state)
    {
        List<TreeNode> listaChildren = node.getChildren();
        System.err.println("0: SegPermissaoBean.selectOrUnSelectAllSuns()\tnode: " + node);
        if (listaChildren == null || listaChildren.isEmpty())
        {
            System.err.println("1: SegPermissaoBean.selectOrUnSelectAllSuns()\tnode: " + node);
            return;
        }
        System.err.println("2: SegPermissaoBean.selectOrUnSelectAllSuns()\tnode: " + node);
        for (TreeNode sun : listaChildren)
        {
            System.err.println("3: SegPermissaoBean.selectOrUnSelectAllSuns()\tnode: " + node);
            selectOrUnSelectAllTree((FuncionalidadePermissaoTreeNode) sun, state);
            System.err.println("4: SegPermissaoBean.selectOrUnSelectAllSuns()\tnode: " + node);
        }
        System.err.println("5: SegPermissaoBean.selectOrUnSelectAllSuns()\tnode: " + node);
    }

    public void unSelectAllSuns(SegFuncionalidadePermissaoNode segFuncionalidade)
    {
//        segFuncionalidade.setAutoStart(false);
        FuncionalidadePermissaoTreeNode node = (FuncionalidadePermissaoTreeNode) this.tabelaTreeNodes.get(segFuncionalidade.getPkSegFuncionalidadeDataNode());
//        node.setSelected(true);
        System.err.println("0: SegPermissaoBean.unSelectAllSuns()\tnode: " + node);
        selectOrUnSelectAllSuns(node, false);
    }

    public void selectOrUnSelectAllTreeCascade(FuncionalidadePermissaoTreeNode node, boolean state)
    {
        System.err.println("0: SegPermissaoBean.selectOrUnSelectAllTreeCascade()\tnode: " + node);
        node.setSelected(state);
        node.setExpanded(true);
        this.addRemoveSelectedNodes(node, state);

        List<TreeNode> listaChildren = node.getChildren();
        if (listaChildren == null || listaChildren.isEmpty())
        {
            System.err.println("1: SegPermissaoBean.selectOrUnSelectAllTreeCascade()\tnode: " + node);
            SegFuncionalidadePermissaoNode segFuncionalidade = (SegFuncionalidadePermissaoNode) node.getData();
            SegFuncionalidade segFuncionalidadePai = segFuncionalidade.getFkSegFuncionalidadePai();
            switch (SegTipoFuncionalidadeEnum.fromSegTipoFuncionalidade(segFuncionalidade.getFkSegTipoFuncionalidade()))
            {
                case FORM:
                    System.err.println("2: SegPermissaoBean.selectOrUnSelectAllTreeCascade(SegFuncionalidade)\nsegFuncionalidade: "
                        + segFuncionalidade + "\nsegFuncionalidadePai: "
                        + (segFuncionalidadePai == null ? "null" : segFuncionalidadePai));
                    if (!this.segFuncionalidadeFacade.isRoot(segFuncionalidadePai))
                    {
                        String nodeName = node.toString();
                        System.err.println("3: SegPermissaoBean.selectOrUnSelectAllTreeCascade()\nnode: " + node + "\tnodeName: "
                            + nodeName);
                        FuncionalidadePermissaoTreeNode rootSunNode = getRootSun(nodeName);
                        if (rootSunNode != null)
                        {
                            System.err.println("4: SegPermissaoBean.selectOrUnSelectAllTreeCascade()\trootSunNode: " + rootSunNode);;
                            rootSunNode.setSelected(true);
                            this.addNodeToSelectedNodes(rootSunNode);

                            rootSunNode.setExpanded(true);
                            SegFuncionalidadePermissaoNode segFuncionalidadeTmp = (SegFuncionalidadePermissaoNode) rootSunNode.getData();
                            segFuncionalidadeTmp.setAutoStart(true);
                            if (state && TreeNodeUtilities.allTreeIsSelected(rootSunNode))
                            {
                                return;
                            }
                            else if (!state && TreeNodeUtilities.allTreeIsUnSelected(rootSunNode))
                            {
                                return;
                            }
                            System.err.println("5: SegPermissaoBean.selectOrUnSelectAllTreeCascade()\trootSunNode: " + rootSunNode);
                            selectOrUnSelectAllTreeCascade(rootSunNode, state);

                            System.err.println("6: SegPermissaoBean.selectOrUnSelectAllTreeCascade(SegFuncionalidade)\trootSunNode: " + rootSunNode);
                        }
                        System.err.println("7: SegPermissaoBean.selectOrUnSelectAllTreeCascade(SegFuncionalidade)\trootSunNode: " + rootSunNode);
                        return;
                    }
                default:
                    System.err.println("8: SegPermissaoBean.selectOrUnSelectAllTreeCascade()\tnode: " + node);
            }
            return;
        }

        for (TreeNode sun : listaChildren)
        {
            System.err.println("9: SegPermissaoBean.selectOrUnSelectAllTreeCascade()\tsun: " + sun);
            if (state && TreeNodeUtilities.allTreeIsSelected(sun))
            {
                System.err.println("10: SegPermissaoBean.selectOrUnSelectAllTreeCascade()\tsun: " + sun);
                continue;
            }
            else if (!state && TreeNodeUtilities.allTreeIsUnSelected(sun))
            {
                System.err.println("11: SegPermissaoBean.selectOrUnSelectAllTreeCascade()\tsun: " + sun);
                continue;
            }
            selectOrUnSelectAllTreeCascade((FuncionalidadePermissaoTreeNode) sun, state);
            System.err.println("12: SegPermissaoBean.selectOrUnSelectAllTreeCascade()\tsun: " + sun);
        }
        System.err.println("13: SegPermissaoBean.selectOrUnSelectAllTreeCascade()\tnode: " + node);
    }

    public void confirmSelectAllTreeCascade(SegFuncionalidadePermissaoNode segFuncionalidade)
    {
        this.parametrosEntreFormulariosBean.setParametro("segFuncionalidade", segFuncionalidade);
        PrimeFaces.current().executeScript("PF('w_selecionar_sub_funcionalidades').show();");
    }

    public void selectAllTreeCascade()
    {
        SegFuncionalidadePermissaoNode segFuncionalidade = (SegFuncionalidadePermissaoNode) parametrosEntreFormulariosBean.getParametro("segFuncionalidade");
        FuncionalidadePermissaoTreeNode node = (FuncionalidadePermissaoTreeNode) this.tabelaTreeNodes.get(segFuncionalidade.getPkSegFuncionalidadeDataNode());
        System.err.println("0: SegPermissaoBean.selectAllTreeCascade()\tnode: " + node);

        selectOrUnSelectAllTreeCascade(node, true);

    }

    public void selectAllTree(SegFuncionalidadePermissaoNode segFuncionalidade)
    {
        FuncionalidadePermissaoTreeNode node = (FuncionalidadePermissaoTreeNode) this.tabelaTreeNodes.get(segFuncionalidade.getPkSegFuncionalidadeDataNode());
        System.err.println("0: SegPermissaoBean.selectAllTree()\tnode: " + node);
//        node.setSelected(true);
        selectOrUnSelectAllTree(node, true);
    }

    public boolean allTreeIsSelected(SegFuncionalidadePermissaoNode segFuncionalidade)
    {
        FuncionalidadePermissaoTreeNode node = (FuncionalidadePermissaoTreeNode) this.tabelaTreeNodes.get(segFuncionalidade.getPkSegFuncionalidadeDataNode());
//System.err.println("0: SegPermissaoBean.allTreeIsSelected()\tnode: " + node);        
        return node.isSelected() && TreeNodeUtilities.isSelectedAllSuns(node);
    }

    public boolean allTreeIsUnSelected(SegFuncionalidadePermissaoNode segFuncionalidade)
    {
        FuncionalidadePermissaoTreeNode node = (FuncionalidadePermissaoTreeNode) this.tabelaTreeNodes.get(segFuncionalidade.getPkSegFuncionalidadeDataNode());
//System.err.println("0: SegPermissaoBean.allTreeIsUnSelected()\tnode: " + node);        
        return !node.isSelected() && TreeNodeUtilities.isUnSelectedAllSuns(node);
    }

    public void resetAutoStart(SegFuncionalidadePermissaoNode segFuncionalidade)
    {
        System.err.println("0: SegPermissaoBean.resetAutoStart()\tautoStart: " + segFuncionalidade.isAutoStart());
        segFuncionalidade.setAutoStart(false);
    }

    public boolean getAutoStart(SegFuncionalidadePermissaoNode segFuncionalidade)
    {
//System.err.println("0: SegPermissaoBean.getAutoStart()\tautoStart: " + 
//            (segFuncionalidade == null ? "null" : segFuncionalidade.isAutoStart()));
        return segFuncionalidade.isAutoStart();
    }

    public String getColor(SegFuncionalidadePermissaoNode segFuncionalidade)
    {
//        System.err.println("0: SegPermissaoBean.getColor()\tcolor: " + segFuncionalidade.getColor());
        return segFuncionalidade.getColor();
    }

    public String getBackGroundColor(SegFuncionalidadePermissaoNode segFuncionalidade)
    {
        return segFuncionalidade.getBackgroundColor();
    }

    public void changeBlinkingColors(SegFuncionalidadePermissaoNode segFuncionalidade)
    {
//        System.err.println("0: SegPermissaoBean.changeBlinkingColors()");
        segFuncionalidade.changeBlinkingColors();
    }

    public String seleccionarFormLabel(SegFuncionalidadePermissaoNode segFuncionalidade)
    {
        String label = "Seleccionar o Menu-Formulário"; //e expandi-lo
        selectedNode = (FuncionalidadePermissaoTreeNode) this.tabelaTreeNodes.get(segFuncionalidade.getPkSegFuncionalidadeDataNode());
        return selectedNode.isExpanded() ? label : (label + " e expandi-lo");
    }

    public boolean renderizarSelecionarForm(SegFuncionalidadePermissaoNode segFuncionalidade)
    {
        switch (SegTipoFuncionalidadeEnum.fromSegTipoFuncionalidade(segFuncionalidade.getFkSegTipoFuncionalidade()))
        {
            case FORM:
                selectedNode = (FuncionalidadePermissaoTreeNode) this.tabelaTreeNodes.get(segFuncionalidade.getPkSegFuncionalidadeDataNode());
                return !selectedNode.isExpanded() || !selectedNode.isSelected();
//                return true;
            default:
                return false;
        }

    }

    public void selecionarForm(SegFuncionalidadePermissaoNode segFuncionalidade)
    {
        System.err.println("0: SegPermissaoBean.selecionarForm(SegFuncionalidade)\nsegFuncionalidade: " + segFuncionalidade);
        selectedNode = (FuncionalidadePermissaoTreeNode) this.tabelaTreeNodes.get(segFuncionalidade.getPkSegFuncionalidadeDataNode());

        System.err.println("1: SegPermissaoBean.selecionarForm(SegFuncionalidade)\nselectedNode: " + selectedNode);
        setSelectedAllAncesters(selectedNode);
        selectedNode.setSelected(true);
        this.addNodeToSelectedNodes(selectedNode);

        SegFuncionalidade segFuncionalidadePai = segFuncionalidade.getFkSegFuncionalidadePai();
        switch (SegTipoFuncionalidadeEnum.fromSegTipoFuncionalidade(segFuncionalidade.getFkSegTipoFuncionalidade()))
        {
            case FORM:
                System.err.println("2: SegPermissaoBean.selecionarForm(SegFuncionalidade)\nsegFuncionalidade: "
                    + segFuncionalidade);
                if (!this.segFuncionalidadeFacade.isRoot(segFuncionalidadePai))
                {
                    String selectedNodeName = selectedNode.toString();
                    FuncionalidadePermissaoTreeNode node = getRootSun(selectedNodeName);
                    if (node != null)
                    {
//                        selectOrUnSelectNode(node, true);
                        node.setSelected(true);
                        node.setExpanded(true);
                        this.addNodeToSelectedNodes(node);
                        SegFuncionalidadePermissaoNode segFuncionalidadeTmp = (SegFuncionalidadePermissaoNode) node.getData();
                        segFuncionalidadeTmp.setAutoStart(true);

                        System.err.println("3 SegPermissaoBean.selecionarForm(SegFuncionalidade)\tsegFuncionalidadeTmp: " + segFuncionalidadeTmp);
                    }
                    System.err.println("4 SegPermissaoBean.selecionarForm(SegFuncionalidade)\tsegFuncionalidadePai: " + segFuncionalidadePai);
                    return;
                }
                else
                {
                    selectedNode.setSelected(true);
                    selectedNode.setExpanded(true);
                    this.addNodeToSelectedNodes(selectedNode);
                    segFuncionalidade.resetAutoStart();
                    System.err.println("5 SegPermissaoBean.selecionarForm()\tselectedNode: " + selectedNode);
                }
            default:
                System.err.println("6 SegPermissaoBean.selecionarForm()\tselectedNode: " + selectedNode);
        }

    }

    public boolean addNodeToLista(List<FuncionalidadePermissaoTreeNode> lista, FuncionalidadePermissaoTreeNode node)
    {
        int pkNode = node.getData().getPkSegFuncionalidadeDataNode();
        for (FuncionalidadePermissaoTreeNode no : this.selectedNodes)
        {
            if (no.getData().getPkSegFuncionalidadeDataNode() == pkNode)
            {
                return false;
            }
        }
        lista.add(node);
        return true;
    }

    public boolean addNodeToSelectedNodes(FuncionalidadePermissaoTreeNode node)
    {
        return this.addNodeToLista(selectedNodes, node);
    }

    public boolean removeNodeFromSelectedNodes(FuncionalidadePermissaoTreeNode node)
    {
        int pkNode = node.getData().getPkSegFuncionalidadeDataNode();
        int sz = selectedNodes.size();
        FuncionalidadePermissaoTreeNode no;
        for (int i = 0; i < sz; i++)
        {
            no = selectedNodes.get(i);
            if (no.getData().getPkSegFuncionalidadeDataNode() == pkNode)
            {
                selectedNodes.remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean addRemoveSelectedNodes(FuncionalidadePermissaoTreeNode node, boolean state)
    {
        if (state)
        {
            addNodeToSelectedNodes(node);
        }
        else
        {
            return removeNodeFromSelectedNodes(node);
        }
        return true;
    }

    public boolean treeNodeIncludesSegFuncionalidade(FuncionalidadePermissaoTreeNode no, FuncionalidadePermissaoTreeNode node)
    {
        int pkNoSegFuncionalidade = no.getData().getPkSegFuncionalidade();
        int pkNodeSegFuncionalidade = node.getData().getPkSegFuncionalidade();
        if (pkNoSegFuncionalidade == pkNodeSegFuncionalidade)
        {
            return true;
        }
        List<TreeNode> suns = no.getChildren();
        FuncionalidadePermissaoTreeNode sunNode;
        for (TreeNode sun : suns)
        {
            sunNode = (FuncionalidadePermissaoTreeNode) sun;

            if (treeNodeIncludesSegFuncionalidade(sunNode, node))
            {
                return true;
            }
        }
        return false;
    }

    public boolean treeNodeIncludesNode(FuncionalidadePermissaoTreeNode no, FuncionalidadePermissaoTreeNode node)
    {
        int pkNo = no.getData().getPkSegFuncionalidadeDataNode();
        int pkNode = node.getData().getPkSegFuncionalidadeDataNode();
        if (pkNo == pkNode)
        {
            return true;
        }
        List<TreeNode> suns = no.getChildren();
        FuncionalidadePermissaoTreeNode sunNode;
        for (TreeNode sun : suns)
        {
            sunNode = (FuncionalidadePermissaoTreeNode) sun;

            if (treeNodeIncludesNode(sunNode, node))
            {
                return true;
            }
        }
        return false;
    }

    public List<FuncionalidadePermissaoTreeNode> getSelectedFuncionalidadePermissaoTreeNodeLista(
        FuncionalidadePermissaoTreeNode node)
    {
        List<FuncionalidadePermissaoTreeNode> lista = new ArrayList();
        List<TreeNode> rootSuns = root.getChildren();
        FuncionalidadePermissaoTreeNode rootSunNode;
        boolean find = false;
        for (TreeNode rootSun : rootSuns)
        {
            rootSunNode = (FuncionalidadePermissaoTreeNode) rootSun;
            if (isFormRootSun(rootSunNode))
            {
                continue;
            }
            if (!find)
            {
                if (treeNodeIncludesNode(rootSunNode, node))
                {
                    find = true;
                    continue;
                }
            }
            if (treeNodeIncludesSegFuncionalidade(rootSunNode, node))
            {
                this.addNodeToLista(lista, rootSunNode);
            }
        }
        return lista;
    }

    public void processUnselectedLeafFormNode(FuncionalidadePermissaoTreeNode node)
    {
        System.err.println("0: SegPermissaoBean.processUnselectedLeafFormNode(FuncionalidadePermissaoTreeNode)\nnode: " + node);
        List<FuncionalidadePermissaoTreeNode> funcionalidadePermissaoTreeNodeLista = getSelectedFuncionalidadePermissaoTreeNodeLista(node);
        System.err.println("1: SegPermissaoBean.processUnselectedLeafFormNode(FuncionalidadePermissaoTreeNode)\nnode: " + node + 
                    "\tfuncionalidadePermissaoTreeNodeLista.size: " + funcionalidadePermissaoTreeNodeLista.size());
        if (funcionalidadePermissaoTreeNodeLista.isEmpty())
        {
            System.err.println("2: SegPermissaoBean.processUnselectedLeafFormNode(FuncionalidadePermissaoTreeNode)\nsize: "
                + funcionalidadePermissaoTreeNodeLista.size());
            return;
        }
        System.err.println("3: SegPermissaoBean.processUnselectedLeafFormNode(FuncionalidadePermissaoTreeNode)\nnode: " + node);
        String selectedNodeName = node.toString();
        FuncionalidadePermissaoTreeNode nodeForm = getRootSun(selectedNodeName);
        System.err.println("4: SegPermissaoBean.processUnselectedLeafFormNode(FuncionalidadePermissaoTreeNode)\nnodeForm: "
            + nodeForm);
        selectOrUnSelectAllTree(nodeForm, false);
    }

    public void selectOrUnSelectAllTree(FuncionalidadePermissaoTreeNode node, boolean state)
    {
        System.err.println("0: SegPermissaoBean.selectOrUnSelectAllTree(FuncionalidadePermissaoTreeNode, boolean)\nnode: " + 
            node + "\tstate: " + state);
        node.setSelected(state);
        addRemoveSelectedNodes(node, state);
        if (!state && node.isLeaf() && node.isForm())
        {
           System.err.println("1: SegPermissaoBean.selectOrUnSelectAllTree(FuncionalidadePermissaoTreeNode, boolean)\nnode: " + 
            node + "\tstate: " + state);
            processUnselectedLeafFormNode(node);
        }
        System.err.println("2: SegPermissaoBean.selectOrUnSelectAllTree(FuncionalidadePermissaoTreeNode, boolean)\nnode: " + 
            node + "\tstate: " + state);
        List<TreeNode> listaChildren = node.getChildren();
        if (listaChildren == null || listaChildren.isEmpty())
        {
System.err.println("3: SegPermissaoBean.selectOrUnSelectAllTree(FuncionalidadePermissaoTreeNode, boolean)\nnode: " + 
            node + "\tstate: " + state);            
            return;
        }
        listaChildren.forEach((sun) ->
        {
            selectOrUnSelectAllTree((FuncionalidadePermissaoTreeNode) sun, state);
        });
System.err.println("4: SegPermissaoBean.selectOrUnSelectAllTree(FuncionalidadePermissaoTreeNode, boolean)\nnode: " + 
            node + "\tstate: " + state);        
    }

    public void setSelectedAllAncesters(FuncionalidadePermissaoTreeNode node)
    {
        System.err.println("0: SegPermissaoBean.setSelectedAllAncesters()");
        FuncionalidadePermissaoTreeNode t = (FuncionalidadePermissaoTreeNode) node.getParent();
        if (t == null)
        {
            System.err.println("1: SegPermissaoBean.setSelectedAllAncesters()");
            return;
        }

        System.err.println("2: SegPermissaoBean.setSelectedAllAncesters()\tt: " + t);
        for (; t != null; t = (FuncionalidadePermissaoTreeNode) t.getParent())
        {
            System.err.println("3: SegPermissaoBean.setSelectedAllAncesters()\tt: " + t);
            t.setExpanded(true);
            t.setSelected(true);
            this.addNodeToSelectedNodes(t);
        }
    }

    public boolean isFormRootSun(FuncionalidadePermissaoTreeNode node)
    {
        SegFuncionalidadePermissaoNode segFuncionalidade = (SegFuncionalidadePermissaoNode) node.getData();

        SegFuncionalidade segFuncionalidadePai = segFuncionalidade.getFkSegFuncionalidadePai();
        switch (SegTipoFuncionalidadeEnum.fromSegTipoFuncionalidade(segFuncionalidade.getFkSegTipoFuncionalidade()))
        {
            case FORM:
                return this.segFuncionalidadeFacade.isRoot(segFuncionalidadePai);
        }
        return false;
    }

    public void onNodeSelect(NodeSelectEvent event)
    {
        System.err.println("0: SegPermissaoBean.onNodeSelect()");
//        System.out.println("Node Data ::" + event.getTreeNode().getData() + " :: Selected");
        selectedNode = (FuncionalidadePermissaoTreeNode) event.getTreeNode();
        System.err.println("0.1: SegPermissaoBean.onNodeSelect(SegFuncionalidade)\nselectedNode: " + selectedNode);
        setSelectedAllAncesters(selectedNode);
        SegFuncionalidadePermissaoNode segFuncionalidade = (SegFuncionalidadePermissaoNode) selectedNode.getData();
        selectedNode.setSelected(true);
        this.addNodeToSelectedNodes(selectedNode);

        segFuncionalidade.resetAutoStart();

        SegFuncionalidade segFuncionalidadePai = segFuncionalidade.getFkSegFuncionalidadePai();
        switch (SegTipoFuncionalidadeEnum.fromSegTipoFuncionalidade(segFuncionalidade.getFkSegTipoFuncionalidade()))
        {
            case FORM:
                System.err.println("1: SegPermissaoBean.onNodeSelect(SegFuncionalidade)\npkIdFuncionalidade: "
                    + segFuncionalidade.getPkSegFuncionalidade() + "\t"
                    + (segFuncionalidadePai == null ? "null" : segFuncionalidadePai.getDescricao()));
                if (!this.segFuncionalidadeFacade.isRoot(segFuncionalidadePai))
                {
                    String selectedNodeName = selectedNode.toString();
                    FuncionalidadePermissaoTreeNode node = getRootSun(selectedNodeName);
                    if (node != null)
                    {
//                        selectOrUnSelectNode(node, true);
                        node.setSelected(true);
                        this.addNodeToSelectedNodes(node);
                        SegFuncionalidadePermissaoNode segFuncionalidadeTmp = (SegFuncionalidadePermissaoNode) node.getData();
                        segFuncionalidadeTmp.setAutoStart(true);

                        System.err.println("2: SegPermissaoBean.onNodeSelect(SegFuncionalidade)\tpkSegFuncionalidadeTmp: " + segFuncionalidadeTmp.getPkSegFuncionalidade());
                    }
                    System.err.println("3: SegPermissaoBean.onNodeSelect(SegFuncionalidade)\tpkSegFuncionalidadePai: " + segFuncionalidadePai.getPkSegFuncionalidade());
                    return;
                }
            default:
                System.err.println("4: SegPermissaoBean.onNodeSelect()\tselectedNode: " + selectedNode);

        }

    }

    public void onNodeUnSelect(NodeUnselectEvent event)
    {
        FuncionalidadePermissaoTreeNode selectedNodeTmp = (FuncionalidadePermissaoTreeNode) event.getTreeNode();
        System.err.println("0: SegPermissaoBean.onNodeUnSelect(SegFuncionalidade)\nselectedNodeTmp: " + selectedNodeTmp);

        selectOrUnSelectAllTree(selectedNodeTmp, false);

        System.err.println("1: SegPermissaoBean.onNodeUnSelect()");
//        System.out.println("Node Data ::" + event.getTreeNode().getData() + " :: UnSelected");
    }

    public void onNodeExpand(NodeExpandEvent event)
    {
//        System.out.println("Node Data ::" + event.getTreeNode().getData() + " :: Expanded");
    }

    public void onNodeCollapse(NodeCollapseEvent event)
    {
//        System.out.println("Node Data ::" + event.getTreeNode().getData() + " :: Collapsed");
    }

}
