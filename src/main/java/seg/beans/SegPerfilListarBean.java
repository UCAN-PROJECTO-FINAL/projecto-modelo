/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seg.beans;

import ejbs.entities.SegConta;
import ejbs.entities.SegFuncionalidade;
import ejbs.entities.SegPerfil;
import ejbs.entities.SegPerfilHasFuncionalidade;
import ejbs.facades.SegContaFacade;
import ejbs.facades.SegFuncionalidadeFacade;
import ejbs.facades.SegPerfilAssociadoFacade;
import ejbs.facades.SegPerfilFacade;
import ejbs.facades.SegPerfilHasFuncionalidadeFacade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import org.omnifaces.cdi.ViewScoped;
import org.primefaces.event.TransferEvent;
import org.primefaces.model.DualListModel;
import utils.mensagens.LogFile;
import utils.parametros_entre_formularios.ParametrosEntreFormulariosBean;     

/**
 *
 * @author helena
 */
@Named
@ViewScoped
public class SegPerfilListarBean implements Serializable
{

    @Inject
    ParametrosEntreFormulariosBean parametrosEntreFormulariosBean;  

    @Inject
    private SegPerfilBean segPerfilBean;

    @EJB
    private SegFuncionalidadeFacade segFuncionalidadeFacade;

    @EJB
    private SegContaFacade segContaFacade;

    @Resource
    private UserTransaction userTransaction;

    @EJB
    private SegPerfilFacade segPerfilFacade;

    @EJB
    private SegFuncionalidadeFacade funcionalidadeFacade;

    @EJB
    private SegPerfilHasFuncionalidadeFacade segPerfilHasFuncionalidadeFacade;

    @EJB
    private SegPerfilAssociadoFacade perfilNovoFacade;

    private SegPerfil perfilPesquisa, perfilEditar;
    private List<SegPerfil> perfisPesquisados;
    private String novoNome;

    private SegPerfil perfilSelecionado;
    private String descricao;
    private String nome;

    // para picklist
    private boolean resposta = false;
    private boolean resposta1 = false;
    private boolean resposta2 = false;

    //Funcionalidade
    private DualListModel<SegFuncionalidade> recursos;
    private List<SegFuncionalidade> recursosOrigem;
    private List<SegFuncionalidade> recursosDestino;

    //Perfil
    private DualListModel<SegPerfil> perfis;
    private List<SegPerfil> perfisOrigem;
    private List<SegPerfil> perfisDestino;

    private ArrayList<SegFuncionalidade> removedModulos = new ArrayList<>();
    private ArrayList<SegPerfil> removedPerfis = new ArrayList<>();

    /**
     * Creates a new instance of SegPerfilFacade
     */
    public SegPerfilListarBean()
    {
    }

    @PostConstruct
    public void init()
    {
        instanciarLista();
        this.perfisPesquisados = this.segPerfilFacade.findAllOrderByDescricao();
    }

    public void initDescricao()
    {
        descricao = "";
    }

    public void instanciarLista()
    {
        //Funcionalidade
        recursosOrigem = listaFuncionalidades();
        recursosDestino = new ArrayList();
        recursos = new DualListModel(recursosOrigem, recursosDestino);
        //Perfil
        perfisOrigem = listaPerfis();
        perfisDestino = new ArrayList();
        perfis = new DualListModel(perfisOrigem, perfisDestino);
    }

    public void preRenderView()
    {
//		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
    }

    public void pesquisarPerfils(SegConta user)
    {
        perfisPesquisados = segPerfilFacade.findPerfil(perfilPesquisa);

        if (perfisPesquisados.isEmpty())
        {
            LogFile.warnMsg(null, "Nenhum registo encontrado para esta pesquisa.");
        }
        else
        {
            LogFile.sucessoMsg(null, "Pesquisa efectuada com sucesso. " + perfisPesquisados.size() + " registo(s) retornado(s).");
        }
    }

    //Criar um perfil com varias funcionalidades ou com Perfis existentes
    public void criarPeril()
    {
//              userTransaction.begin();
        SegPerfil perfilBuscar = segPerfilFacade.findByDescricao(descricao);

        if (perfilBuscar == null)
        {
            SegPerfil perfil1 = new SegPerfil();
            perfil1.setDescricao(descricao);
            perfil1.setPkSegPerfil(segPerfilFacade.findAllQuantidadePerfil() + 1);
            segPerfilFacade.create(perfil1);
            this.segPerfilBean.init();
            LogFile.sucessoMsg(null, "O perfil de nome " + descricao + " criado com sucesso...");
        }
        else
        {
            LogFile.warnMsg(null, "O perfil de nome " + descricao + " já existe...");
        }

//
//                userTransaction.commit();
//
//               
        setPerfilEditar(null);
        this.perfisPesquisados = this.segPerfilFacade.findAllOrderByDescricao();
    }

    public void actualizarNome()
    {
System.err.println("0: SegPerfilListarBean.actualizarNome()");        
        if (segPerfilFacade.findByDescricao(this.novoNome) != null)
        {
System.err.println("1: SegPerfilListarBean.actualizarNome()");            
            LogFile.warnMsg(null, "O nome \"" + this.novoNome + "\" já foi usado");
            return;
        }
        perfilEditar.setDescricao(this.novoNome);
        segPerfilFacade.edit(perfilEditar);
        this.perfisPesquisados = this.segPerfilFacade.findAllOrderByDescricao();
System.err.println("2: SegPerfilListarBean.actualizarNome()");         
    }
    
    public void editar()
    {
System.err.println("0: SegPerfilListarBean.editar");        
        if (segPerfilFacade.findByDescricao(this.novoNome) != null)
        {
System.err.println("1: SegPerfilListarBean.editar");            
            LogFile.warnMsg(null, "O nome \"" + this.novoNome + "\" já foi usado");
            return;
        }
System.err.println("2: SegPerfilListarBean.editar");        
        perfilEditar.setDescricao(this.novoNome);
System.err.println("3: SegPerfilListarBean.editar");        
        try
        {
System.err.println("4: SegPerfilListarBean.editar");            
            userTransaction.begin();
            segPerfilFacade.edit(perfilEditar);

            //remover as funcionalidades associada ao perfil selecionado
            if (!removedModulos.isEmpty())
            {
System.err.println("5: SegPerfilListarBean.editar");                
                for (SegFuncionalidade recursos : removedModulos)
                {
                    SegPerfilHasFuncionalidade perfilModulos = new SegPerfilHasFuncionalidade();
                    perfilModulos.setFkSegFuncionalidade(recursos);
                    perfilModulos.setFkSegPerfil(this.segPerfilFacade.find(perfilEditar.getPkSegPerfil()));

                    //remover as funcionalidades associada ao perfil selecionado
                    segPerfilHasFuncionalidadeFacade.remove(perfilModulos);
                    //perfilPermissasFacade.removerPermissoesPorModuloPerfil(perfilEditar.getPksegperf(), recursos.getPksegrecur());

                }
                removedModulos.clear();
            }
System.err.println("6: SegPerfilListarBean.editar");
            // Adicionar nova Funcionalidade ao perfil selecionado
            ArrayList<SegFuncionalidade> listaRecursos = (ArrayList<SegFuncionalidade>) recursos.getTarget();
            for (SegFuncionalidade recursos : listaRecursos)
            {
                SegPerfilHasFuncionalidade perfilModulos = new SegPerfilHasFuncionalidade();
                perfilModulos.setFkSegFuncionalidade(recursos);
                perfilModulos.setFkSegPerfil(this.segPerfilFacade.find(perfilEditar.getPkSegPerfil()));
                segPerfilHasFuncionalidadeFacade.edit(perfilModulos);

            }
System.err.println("7: SegPerfilListarBean.editar");
            recursos = null;
            userTransaction.commit();
System.err.println("8: SegPerfilListarBean.editar");            
            perfisPesquisados = segPerfilFacade.findPerfil(perfilPesquisa);
System.err.println("8.1: SegPerfilListarBean.editar");            
            LogFile.sucessoMsg(null, "Perfil editado com sucesso.");
        }
        catch (IllegalStateException | SecurityException | HeuristicMixedException | HeuristicRollbackException | NotSupportedException | RollbackException | SystemException e)
        {
            try
            {
System.err.println("9: SegPerfilListarBean.editar");                
                userTransaction.rollback();
                LogFile.erroMsg(null, e.toString());
            }
            catch (IllegalStateException | SecurityException | SystemException ex)
            {
                LogFile.erroMsg(null, "Rollback: " + ex.toString());
            }
        }
System.err.println("10: SegPerfilListarBean.editar");
        setPerfilEditar(null);
        this.perfisPesquisados = this.segPerfilFacade.findAllOrderByDescricao();
System.err.println("11: SegPerfilListarBean.editar");        
    }

    private String obterNomesDasContas(List<SegConta> segContaList)
    {
        String nomes = "";
        boolean first = true;
        for (SegConta p : segContaList)
        {
            if (!first)
            {
                nomes += ", ";
            }
            else
            {
                first = false;
            }
            nomes += "\"" + p.getNomeUtilizador() + "\"";
        }
        return nomes;
    }

    public boolean podeEliminar()
    {
        List<SegConta> segContaList = this.segContaFacade.findAllContaOrderByPkSegPerfil(perfilEditar.getPkSegPerfil());
        if (!segContaList.isEmpty())
        {
            String nomesDasConta = obterNomesDasContas(segContaList);
            String logMessage = "Não foi possível remover o perfil \"" + perfilEditar.getDescricao()
                + "\" porque está a ser usado "
                + (segContaList.size() == 1 ? "na conta " : "nas contas ") + nomesDasConta;
            LogFile.warnMsg(null, logMessage);
            return false;
        }
        return true;
    }

    public void eliminar(SegConta user)
    {
        if (!podeEliminar())
        {
            return;
        }
        try
        {
            userTransaction.begin();
            segPerfilFacade.remove(perfilEditar);
            userTransaction.commit();
            LogFile.sucessoMsg(null, "Perfil eliminado com sucesso.");
            perfisPesquisados = segPerfilFacade.findAllOrderByDescricao();
        }
        catch (IllegalStateException | SecurityException | HeuristicMixedException | HeuristicRollbackException | NotSupportedException | RollbackException | SystemException e)
        {
            try
            {
                userTransaction.rollback();
                LogFile.warnMsg(null, "O perfil que pretende eliminar Encontra-se em uso");
            }
            catch (IllegalStateException | SecurityException | SystemException ex)
            {
                LogFile.erroMsg(null, "O perfil que pretende eliminar Encontra-se em uso");
            }
        }
        this.segPerfilBean.init();
        setPerfilEditar(null);
    }

    public String redirectToSegPermissao()
    {
//        return haPermissoes() ? "/seg_visao/segPermissao/seg_permissao.xhtml?faces-redirect=true" : "/seg_visao/segPermissao/seg_nao_existe_permissoes.xhtml?faces-redirect=true";
        return "/seg_visao/segPermissao/seg_permissao.xhtml?faces-redirect=true";
    }

    public boolean haPermissoes()
    {
        return this.segPerfilHasFuncionalidadeFacade.findByPerfil(perfilSelecionado.getPkSegPerfil()) != null;
    }

    public String limparCampos()
    {
        perfisPesquisados = null;
        perfilPesquisa = null;

        return "seg_perfil_listar.xhtml?faces-redirect=true";
    }

    public boolean disableIfPerfilIt(SegPerfil perfil)
    {
        SegConta conta = SegLoginBean.getInstanciaBean().getSessaoActual();
System.err.println("0: SegPerfilListarBean.disableIfPerfilIt()");        
        if (this.segContaFacade.isRootAccount(conta))
        {
System.err.println("1: SegPerfilListarBean.disableIfPerfilIt()");             
            return false;
        }
System.err.println("2: SegPerfilListarBean.disableIfPerfilIt()");         
        if (conta.getFkSegPerfil().getPkSegPerfil() == perfil.getPkSegPerfil())
        {
            return true;
        }
        return false;

    }

    public List<SegFuncionalidade> listaFuncionalidades()
    {
        return funcionalidadeFacade.funcionalidadesPai();
    }

    public List<SegPerfil> listaPerfis()
    {
        return segPerfilFacade.findAll();
    }

    public List<SegFuncionalidade> listaFuncionalidadesByPerfil()
    {

        if (perfilEditar != null)
        {
            return segPerfilHasFuncionalidadeFacade.getFuncionalidadesByPerfil(perfilEditar.getPkSegPerfil());
        }
        return new ArrayList<SegFuncionalidade>();
    }

    /*
     picklit
     */
    public void updatepicklist()
    {
        recursosOrigem = listaFuncionalidades();
        recursosDestino = listaFuncionalidadesByPerfil();
        removeRepeatedValues(recursosOrigem, recursosDestino);
        recursos = new DualListModel(recursosOrigem, recursosDestino);
    }

    public void onTransfer(TransferEvent event)
    {
        if (event.isRemove())
        {
            for (Object modulos : event.getItems())
            {
                SegFuncionalidade modulos1 = (SegFuncionalidade) modulos;
                removedModulos.add(modulos1);
            }
        }
    }

    public void removeRepeatedValues(List<SegFuncionalidade> eliminarItens, List<SegFuncionalidade> comparar)
    {
        for (SegFuncionalidade p : comparar)
        {
            int val = existeNaLista(eliminarItens, p);
            if (val != -1)
            {
                eliminarItens.remove(val);
            }
        }
    }

    public int existeNaLista(List<SegFuncionalidade> lista, SegFuncionalidade recursos)
    {
        for (int i = 0; i < lista.size(); i++)
        {
            if (recursos.getDescricao().equals(lista.get(i).getDescricao()))
            {
                return i;
            }
        }
        return -1;
    }

    public boolean isResposta1()
    {
        return resposta1;
    }

    public void setResposta1(boolean resposta1)
    {
        this.resposta1 = resposta1;
    }

    public boolean isResposta2()
    {
        return resposta2;
    }

    public void setResposta2(boolean resposta2)
    {
        this.resposta2 = resposta2;
    }

    public boolean isResposta()
    {
        return resposta;
    }

    public void setResposta(boolean resposta)
    {
        this.resposta = resposta;
    }

    public DualListModel<SegFuncionalidade> getRecursos()
    {
        return recursos;
    }

    public void setRecursos(DualListModel<SegFuncionalidade> recursos)
    {
        this.recursos = recursos;
    }

    public List<SegFuncionalidade> getRecursosOrigem()
    {
        return recursosOrigem;
    }

    public void setRecursosOrigem(List<SegFuncionalidade> recursosOrigem)
    {
        this.recursosOrigem = recursosOrigem;
    }

    public List<SegPerfil> getPerfisOrigem()
    {
        return perfisOrigem;
    }

    public void setPerfisOrigem(List<SegPerfil> perfisOrigem)
    {
        this.perfisOrigem = perfisOrigem;
    }

    public List<SegFuncionalidade> getRecursosDestino()
    {
        return recursosDestino;
    }

    public void setRecursosDestino(List<SegFuncionalidade> recursosDestino)
    {
        this.recursosDestino = recursosDestino;
    }

    /* listar todos o recusos ou modulos disponiveis no sistema
     public List<SegFuncionalidade> listarSegfuncionalidade() {
     return funcionalidadeFacade.findAll();
     }*/
    public SegPerfil getPerfilSelecionado()
    {
        return perfilSelecionado;
    }

    public void salvarPerfilSelecionado(SegPerfil perfilSelecionado)
    {
        this.perfilSelecionado = perfilSelecionado;
        parametrosEntreFormulariosBean.setParametro("perfilSelecionado", perfilSelecionado);
    }

    public void setPerfilSelecionado(SegPerfil perfilSelecionado)
    {
        this.perfilSelecionado = perfilSelecionado;
    }

    public ArrayList<SegFuncionalidade> getRemovedModulos()
    {
        return removedModulos;
    }

    public void setRemovedModulos(ArrayList<SegFuncionalidade> removedModulos)
    {
        this.removedModulos = removedModulos;
    }

    public String getDescricao()
    {
        return descricao;
    }

    public void setDescricao(String descricao)
    {
        this.descricao = descricao;
    }

    public DualListModel<SegPerfil> getPerfis()
    {
        return perfis;
    }

    public void setPerfis(DualListModel<SegPerfil> perfis)
    {
        this.perfis = perfis;
    }

    /**
     * @return the perfilPesquisa
     */
    public SegPerfil getPerfilPesquisa()
    {
        if (perfilPesquisa == null)
        {
            perfilPesquisa = SegPerfilBean.getInstancia();
        }
        return perfilPesquisa;
    }

    /**
     * @param perfilPesquisa the perfilPesquisa to set
     */
    public void setPerfilPesquisa(SegPerfil perfilPesquisa)
    {
        this.perfilPesquisa = perfilPesquisa;
    }

    /**
     * @return the perfilEditar
     */
    public SegPerfil getPerfilEditar()
    {
        if (perfilEditar == null)
        {
            perfilEditar = SegPerfilBean.getInstancia();
        }

        return perfilEditar;
    }

    public String confirmarEliminacaoPerfil()
    {
        //System.err.println("0: SegPerfilListarBean.confirmarEliminacaoPerfil()");
        this.perfilEditar = (SegPerfil) parametrosEntreFormulariosBean.getParametro("perfilEditar");
        //System.err.println("00: SegPerfilListarBean.confirmarEliminacaoPerfil()");
        //System.err.println("1: SegPerfilListarBean.confirmarEliminacaoPerfil()\tperfilEditar: "
//            + (this.perfilEditar == null ? "null" : this.segPerfilFacade.toString(this.perfilEditar)));
//		perfilEditar = (SegPerfil) ParametrosEntreFormulariosBean.getParametro("perfilEditar");
////System.err.println("2: SegPerfilListarBean.confirmarEliminacaoPerfil()\tperfilEditar: " + 
//			(this.perfilEditar == null ? "null" : this.segPerfilFacade.toString(this.perfilEditar) ) );
//		return "Deseja eliminar o perfil \"" + perfilEditar.getDescricao() + "\" ? Pode estar a ser utilizado... ";
        return "teste";
    }

    public void preAlterarNomeDoPerfil(SegPerfil perfilEditar)
    {
System.err.println("0: SegPerfilListarBean.preAlterarNomeDoPerfil()");        
         this.perfilEditar = perfilEditar;
         this.novoNome = perfilEditar.getDescricao();         
System.err.println("1: SegPerfilListarBean.preAlterarNomeDoPerfil()\tnovoNome: " + novoNome);         
    }
    /**
     * @param perfilEditar the perfilEditar to set
     */
    public void setPerfilEditar(SegPerfil perfilEditar)
    {
        this.perfilEditar = perfilEditar;
//		ParametrosEntreFormulariosBean.setParametro("perfilEditar", perfilEditar);

        //System.err.println("0: SegPerfilListarBean.setPerfilEditar()\tperfilEditar: "
//            + (this.perfilEditar == null ? "null" : this.segPerfilFacade.toString(this.perfilEditar)));
    }

    /**
     * @return the perfisPesquisados
     */
    public List<SegPerfil> getPerfilsPesquisados()
    {
        if (perfisPesquisados == null)
        {
            perfisPesquisados = new ArrayList<>();
        }
        return perfisPesquisados;
    }

    /**
     * @param perfisPesquisados the perfisPesquisados to set
     */
    public void setPerfilsPesquisados(List<SegPerfil> perfisPesquisados)
    {
        this.perfisPesquisados = perfisPesquisados;
    }

    public String getNome()
    {
        return nome;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    //Elementos para o Perfil
    public void updatepicklist2()
    {
        perfisOrigem = listaPerfis();
        perfisDestino = listaPerfisByPerfil();
        removerepeatedvalue2(perfisOrigem, perfisDestino);
        perfis = new DualListModel(perfisOrigem, perfisDestino);
    }

    public void onTransfer2(TransferEvent event)
    {
        if (event.isRemove())
        {
            for (Object modulos : event.getItems())
            {
                SegPerfil perfilf1 = (SegPerfil) modulos;
                removedPerfis.add(perfilf1);
            }
        }
    }

    public void removerepeatedvalue2(List<SegPerfil> eliminarItens, List<SegPerfil> comparar)
    {
        for (SegPerfil p : comparar)
        {
            int val = existeNaLista2(eliminarItens, p);
            if (val != -1)
            {
                eliminarItens.remove(val);
            }
        }
    }

    public int existeNaLista2(List<SegPerfil> lista, SegPerfil recursos)
    {
        for (int i = 0; i < lista.size(); i++)
        {
            if (recursos.getDescricao().equals(lista.get(i).getDescricao()))
            {
                return i;
            }
        }
        return -1;
    }

    public List<SegPerfil> listaPerfisByPerfil()
    {

        if (perfilEditar != null)
        {
            return perfilNovoFacade.getPerfilByPerfil(perfilEditar.getPkSegPerfil());
        }
        return new ArrayList();
    }

    public ArrayList<SegPerfil> getRemovedPerfis()
    {
        return removedPerfis;
    }

    public void setRemovedPerfis(ArrayList<SegPerfil> removedPerfis)
    {
        this.removedPerfis = removedPerfis;
    }

    public String getNovoNome()
    {
        return novoNome;
    }

    public void setNovoNome(String novoNome)
    {
        this.novoNome = novoNome;
    }

}
