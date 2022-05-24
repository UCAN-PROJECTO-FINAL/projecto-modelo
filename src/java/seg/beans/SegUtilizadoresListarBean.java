/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seg.beans;

import ejbs.entities.SegConta;
import ejbs.entities.SegPerfil;
import ejbs.entities.SegTipoConta;
import ejbs.facades.SegContaFacade;
import ejbs.facades.SegPerfilFacade;
import ejbs.facades.SegTipoContaFacade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.omnifaces.cdi.ViewScoped;
import seg.utils.EncriptacaoDecriptacao;
import utils.mensagens.LogFile;

/**
 *
 * @author helena
 */
@Named
@ViewScoped
public class SegUtilizadoresListarBean implements Serializable
{

    @EJB
    private SegTipoContaFacade segTipoContaFacade;

    @EJB
    private SegContaFacade segContaFacade;

    @EJB
    private SegPerfilFacade segPerfilFacade;

    @Inject
    SegPerfilBean perfilBean;

    private SegConta contaPesquisar, contaEditar;
    private List<SegConta> contasPesquisadas, contasPesquisadasTotal;

    private List<Integer> pkSegContaSelecionadaList, pkSegPerfisSelecionadosList;

    private String resposta2;
    private int idPerfil = -1;
    private String password2;
    private String senhaActual;
    private String senhaConfirma;
    private String passActual;
    private String userName;

    private String passNova, passConfirmada;

    private boolean rendedMostraPass, todosUtilizadores, todosPerfis;

    private SegLoginBean segLoginBean;

    private List<SegPerfil> perfisDasContasPesquisadas, perfisDasContasPesquisadasTotal;

    private boolean first;

    /**
     * Creates a new instance of SegUtilizadoresListar
     */
    public SegUtilizadoresListarBean()
    {
        inic();
    }
    
    public void inic()
    {
        contaPesquisar = getInstanciaConta();
        this.rendedMostraPass = false;
        first = true;
        pkSegContaSelecionadaList = new ArrayList();
        pkSegPerfisSelecionadosList = new ArrayList();
        todosUtilizadores = todosPerfis = false;
    }
    
    @PostConstruct
    public void init()
    {
        contaPesquisar = this.segContaFacade.getInstancia();
        // System.err.println("0: SegUtilizadoresListarBean.init()");
        contasPesquisadasTotal = this.segContaFacade.findAllContaOrderByNomeUtilizador();
        // System.err.println("00: SegUtilizadoresListarBean.init()");
        gerarPerfisDasContasPesquisadasTotal();
        // System.err.println("1: SegUtilizadoresListarBean.init()");
        // System.err.println("2: SegUtilizadoresListarBean.init()\tperfisDasContasPesquisadasTotal: "
//            + ListUtils.toString(this.perfisDasContasPesquisadasTotal, SegPerfilFacade::toString));

        gerarContasPesquisadas();
        // System.err.println("3: SegUtilizadoresListarBean.init()");
        // System.err.println("4: SegUtilizadoresListarBean.init()\tcontasPesquisadas: "
//            + ListUtils.toString(contasPesquisadas, this.segContaFacade::toString));

        gerarPerfisDasContasPesquisadas();
        // System.err.println("5: SegUtilizadoresListarBean.init()");
        // System.err.println("6: SegUtilizadoresListarBean.init()\tcontasPesquisadas: "
//            + ListUtils.toString(this.perfisDasContasPesquisadas, segPerfilFacade::toString));
        first = false;
    }
    
    public void initt()
    {
        inic();
        init();
    }
    
    public void selecionarTodosPerfis()
    {
        pkSegPerfisSelecionadosList = new ArrayList();
        if (this.todosPerfis)
        {
            contasPesquisadas = new ArrayList();
            for (SegConta conta : this.contasPesquisadasTotal)
            {
                contasPesquisadas.add(conta);
            }
            this.perfisDasContasPesquisadas = new ArrayList();
            for (SegPerfil perfil : this.perfisDasContasPesquisadasTotal)
            {
                this.pkSegPerfisSelecionadosList.add(perfil.getPkSegPerfil());
                perfisDasContasPesquisadas.add(perfil);
            }

        }
        // System.err.println("0: SegUtilizadoresListarBean.selecionarTodosPerfis()\nperfisDasContasPesquisadas: "
//            + ListUtils.toString(perfisDasContasPesquisadas, this.SegPerfilFacade::toString) + "\npkSegPerfisSelecionadosList: "
//            + ListUtils.toString(pkSegPerfisSelecionadosList));
//        init();
    }

    public void initTodosUtilizadores()
    {
        todosUtilizadores = false;
    }

    public void initTodosPerfis()
    {
        todosPerfis = false;
    }

    public void selecionarTodosUtilizadores()
    {
        pkSegContaSelecionadaList = new ArrayList();
        if (this.todosUtilizadores)
        {
            contasPesquisadas = new ArrayList();
            for (SegConta conta : this.contasPesquisadasTotal)
            {
                this.pkSegContaSelecionadaList.add(conta.getPkSegConta());
                contasPesquisadas.add(conta);
            }
        }
        // System.err.println("0: SegUtilizadoresListarBean.selecionarTodosUtilizadores()\ncontasPesquisadas: "
//            + ListUtils.toString(contasPesquisadas, this.segContaFacade::toString) + "\npkSegContaSelecionadaList: "
//            + ListUtils.toString(pkSegContaSelecionadaList));
//        init();
    }

    public void selecionarContasDosPerfisSelecionados()
    {
        contasPesquisadas = new ArrayList();

        SegPerfil perfil;
        Integer pkPerfil;
        for (SegConta conta : this.contasPesquisadasTotal)
        {
            perfil = conta.getFkSegPerfil();
            if (perfil == null)
            {
                continue;
            }
            pkPerfil = perfil.getPkSegPerfil();

            if (this.pkSegPerfisSelecionadosList.contains(pkPerfil))
            {
                contasPesquisadas.add(conta);
            }
        }
        // System.err.println("0: SegUtilizadoresListarBean.selecionarContasDosPerfisSelecionados()\ncontasPesquisadas: "
//            + ListUtils.toString(contasPesquisadas, this.segContaFacade::toString) + "\npkSegPerfisSelecionadosList: "
//            + ListUtils.toString(pkSegPerfisSelecionadosList));
//        init();
    }

    public SegConta getInstanciaConta()
    {
        return contaPesquisar;
    }

    private void gerarPerfisDasContasPesquisadasTotal()
    {
        // System.err.println("0: SegUtilizadoresListarBean.gerarPerfisDasContasPesquisadasTotal()");
        this.perfisDasContasPesquisadasTotal = new ArrayList();

        SegPerfil segPerfil;
        for (SegConta conta : contasPesquisadasTotal)
        {
            segPerfil = conta.getFkSegPerfil();
            if (segPerfil == null)
            {
                continue;
            }
            if (!this.perfisDasContasPesquisadasTotal.contains(segPerfil))
            {
                perfisDasContasPesquisadasTotal.add(segPerfil);
            }
        }
        // System.err.println("1: SegUtilizadoresListarBean.gerarPerfisDasContasPesquisadasTotal()\nperfisDasContasPesquisadasTotal.size: "
//            + perfisDasContasPesquisadasTotal.size());
        // System.err.println("2: SegUtilizadoresListarBean.gerarPerfisDasContasPesquisadasTotal()\nperfisDasContasPesquisadasTotal: "
//            + ListUtils.toString(perfisDasContasPesquisadasTotal, SegPerfilFacade::toString));
        if (perfisDasContasPesquisadasTotal.size() > 1)
        {
            // System.err.println("3: SegUtilizadoresListarBean.gerarPerfisDasContasPesquisadasTotal()\nperfisDasContasPesquisadasTotal.size: "
//                + perfisDasContasPesquisadasTotal.size());
            Collections.sort(perfisDasContasPesquisadasTotal, Comparator.comparing(SegPerfil::getDescricao));
        }
        // System.err.println("4: SegUtilizadoresListarBean.gerarPerfisDasContasPesquisadasTotal()");
    }

    private void gerarPerfisDasContasPesquisadas()
    {
        // System.err.println("0: SegUtilizadoresListarBean.gerarPerfisDasContasPesquisadas()\nfirst: " + first
//            + "\tpkSegPerfisSelecionadosList.size: " + pkSegPerfisSelecionadosList.size()
//            + "\npkSegPerfisSelecionadosList: " + ListUtils.toString(pkSegPerfisSelecionadosList));
        if (first)
        {
            this.perfisDasContasPesquisadas = perfisDasContasPesquisadasTotal;
            return;
        }
        perfisDasContasPesquisadas = new ArrayList();

        for (Integer pkSegPerfil : this.pkSegPerfisSelecionadosList)
        {
            for (SegPerfil perfil : this.perfisDasContasPesquisadasTotal)
            {
                if (pkSegPerfil == perfil.getPkSegPerfil())
                {
                    perfisDasContasPesquisadas.add(perfil);
                }
            }
        }
    }

    private void gerarContasPesquisadas()
    {
        // System.err.println("0: SegUtilizadoresListarBean.gerarContasPesquisadas()\nfirst: " + first
//            + "\tpkSegContaSelecionadaList.size: " + pkSegContaSelecionadaList.size()
//            + "\npkSegContaSelecionadaList: " + ListUtils.toString(pkSegContaSelecionadaList));
        if (first)
        {
            contasPesquisadas = contasPesquisadasTotal;
            return;
        }
        contasPesquisadas = new ArrayList();

        for (Integer pkConta : this.pkSegContaSelecionadaList)
        {
            for (SegConta conta : this.contasPesquisadasTotal)
            {
                if (pkConta == conta.getPkSegConta())
                {
                    contasPesquisadas.add(conta);
                }
            }
        }
    }

    public boolean isRendedMostraPass()
    {
        return rendedMostraPass;
    }

    public void setRendedMostraPass(boolean rendedMostraPass)
    {
        this.rendedMostraPass = rendedMostraPass;
    }

    /**
     * Editar a conta do usuario no sistema Utilizador nao logados
     *
     * @param conta
     * @return
     */
    public String editarContaUtilizador()
    {
//        FacesContext context = FacesContext.getCurrentInstance();
        contaEditar = segContaFacade.find(contaEditar.getPkSegConta());
        SegConta sessao = SegLoginBean.getInstanciaBean().getSessaoActual();
        if (contaEditar.getPkSegConta() == sessao.getPkSegConta())
        {
            sessao.setFkSegPerfil(this.segPerfilFacade.find(idPerfil));
            segContaFacade.edit(sessao);
//            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Perfil Actualizado Com Sucesso!", ""));
            LogFile.sucessoMsg(null, "Perfil Actualizado Com Sucesso!");
            SegLoginBean.getInstanciaBean().setSessaoActual(sessao);
            return "/home.xhtml?faces-redirect=true";
        }
        else
        {
            contaEditar.setFkSegPerfil(this.segPerfilFacade.find(idPerfil));
            segContaFacade.edit(contaEditar);
            contaEditar = segContaFacade.find(contaEditar.getPkSegConta());
//            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Perfil Actualizado Com Sucesso!", ""));
            LogFile.sucessoMsg(null, "Perfil Actualizado Com Sucesso!");
        }
        init();
        return "";
    }

    public String editPerfilUserCorrente(SegConta sessao)
    {
        FacesContext context = FacesContext.getCurrentInstance();
        sessao.setFkSegPerfil(this.segPerfilFacade.find(idPerfil));
        segContaFacade.edit(sessao);
//        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Perfil Actualizado Com Sucesso!", ""));
        LogFile.sucessoMsg(null, "Perfil Actualizado Com Sucesso!");
        SegLoginBean.getInstanciaBean().setSessaoActual(sessao);
        return "/home.xhtml?faces-redirect=true";
    }

    public String getPassNova()
    {
        return passNova;
    }

    public void setPassNova(String passNova)
    {
        this.passNova = passNova;
    }

    public String getPassConfirmada()
    {
        return passConfirmada;
    }

    public void setPassConfirmada(String passConfirmada)
    {
        this.passConfirmada = passConfirmada;
    }

    public String getPassActual()
    {
        return passActual;
    }

    public void setPassActual(String passActual)
    {
        this.passActual = passActual;
    }

    public SegConta findConta(SegConta conta)
    {
        return segContaFacade.find(conta);
    }

    /**
     * @return the contaPesquisar
     */
    public SegConta getContaPesquisar()
    {
        return contaPesquisar;
    }

    /**
     * @param contaPesquisar the contaPesquisar to set
     */
    public void setContaPesquisar(SegConta contaPesquisar)
    {
        this.contaPesquisar = contaPesquisar;
    }

    /**
     * @return the contasPesquisadas
     */
    public List<SegConta> getContasPesquisadas()
    {
        return contasPesquisadas;
    }

    /**
     * @param contasPesquisadas the contasPesquisadas to set
     */
    public void setContasPesquisadas(List<SegConta> contasPesquisadas)
    {
        this.contasPesquisadas = contasPesquisadas;
    }

    public List<SegPerfil> getPerfisDasContasPesquisadas()
    {
        return perfisDasContasPesquisadas;
    }

    public void setPerfisDasContasPesquisadas(List<SegPerfil> perfisDasContasPesquisadas)
    {
        this.perfisDasContasPesquisadas = perfisDasContasPesquisadas;
    }

    public SegConta getContaEditar()
    {
        if (contaEditar == null)
        {
            contaEditar = getInstanciaConta();
        }
        return contaEditar;
    }

    public void alterarTipo(SegConta contaEditar)
    {
        SegTipoConta segTipoConta = contaEditar.getFkTipoConta();
        int pkSegTipoConta = segTipoConta.getPkSegTipoConta();
        int newPkSegTipoConta = (pkSegTipoConta == 1) ? 2 : 1;
        SegTipoConta newSegTipoConta = this.segTipoContaFacade.find(newPkSegTipoConta);
        contaEditar.setFkTipoConta(newSegTipoConta);
        this.segContaFacade.edit(contaEditar);
        initt();
    }

    public void setContaEditar(SegConta contaEditar)
    {
        System.err.println("0. SegUtilizadoresListarBean.setContaEditar()");
        this.contaEditar = contaEditar;
        userName = contaEditar == null ? "" : contaEditar.getNomeUtilizador();

        System.err.println("1. SegUtilizadoresListarBean.setContaEditar()\tuserName: " + userName);
        passConfirmada = passNova = contaEditar == null ? "" : EncriptacaoDecriptacao.decrypt(contaEditar.getPalavraPasse());
        System.err.println("2. SegUtilizadoresListarBean.setContaEditar()\tpassConfirmada: " + passNova);
    }

    public void setContaEditarAdminModulo(SegConta contaEditar)
    {
        this.contaEditar = contaEditar;

    }

    public String getResposta2()
    {
        return resposta2;
    }

    public void setResposta2(String resposta2)
    {
        this.resposta2 = resposta2;
    }

    public int getIdPerfil()
    {
        return idPerfil;
    }

    public void setIdPerfil(int idPerfil)
    {
        this.idPerfil = idPerfil;
    }

    public String getPassword2()
    {
        return password2;
    }

    public void setPassword2(String password2)
    {
        this.password2 = password2;
    }

    public String getSenhaActual()
    {
        return senhaActual;
    }

    public void setSenhaActual(String senhaActual)
    {
        this.senhaActual = senhaActual;
    }

    public String getSenhaConfirma()
    {
        return senhaConfirma;
    }

    public void setSenhaConfirma(String senhaConfirma)
    {
        this.senhaConfirma = senhaConfirma;
    }

    public SegLoginBean getSegLoginBean()
    {
        return segLoginBean;
    }

    public void setSegLoginBean(SegLoginBean segLoginBean)
    {
        this.segLoginBean = segLoginBean;
    }

    /**
     * Restaurar a Password
     *
     * @throws Exception
     */
    public void restaurarPassword()
    {
        boolean alterouNome = false;
        boolean alterouSenha = false;
        if (!this.passNova.equals(this.passConfirmada))
        {
            this.rendedMostraPass = false;
            this.passNova = "";
            LogFile.warnMsg(null, "A senha e a confirmação da senha devem ser iguais!");
        }
        else
        {
            if (!this.userName.trim().equals(""))
            {
                if (!contaEditar.getNomeUtilizador().equals(this.userName.trim()))
                {
                    contaEditar.setNomeUtilizador(userName);

                    alterouNome = true;
                }
            }
            else
            {
                LogFile.warnMsg(null, "Insira um nome válido!");
            }
            if (!this.passNova.trim().equals(""))
            {
                String senha = null;

                senha = EncriptacaoDecriptacao.encrypt(this.passNova);

                if (!senha.equals(contaEditar.getPalavraPasse()))
                {
                    contaEditar.setPalavraPasse(senha);

                    alterouSenha = true;
                }
            }
            if (alterouNome || alterouSenha)
            {
                if (segContaFacade.findAllContaOrderByNomeUtilizador(contaEditar.getNomeUtilizador(), contaEditar.getPalavraPasse()) == null)
                {
                    segContaFacade.edit(contaEditar);
                    if (alterouNome)
                    {
                        LogFile.sucessoMsg(null, "Nome de utilizador alterado com sucesso!");
                    }
                    if (alterouSenha)
                    {
                        LogFile.sucessoMsg(null, "Senha alterada com sucesso!");
                    }
                }
                else
                {
                    LogFile.warnMsg(null, "O nome de utilizador " + contaEditar.getNomeUtilizador() + " e a correspondente senha encontram-se em uso");
                }
            }

        }
    }

    /**
     * altera a palavra pass do utilizador
     *
     * @throws Exception
     */
    public void alterarPalavraPass() throws Exception
    {
        FacesContext context = FacesContext.getCurrentInstance();

        if (passActual.equals(EncriptacaoDecriptacao.decrypt(segLoginBean.getContaUtilizador().getPalavraPasse())))
        {

            if (!this.passNova.equals(this.passConfirmada))
            {
                this.rendedMostraPass = false;
                this.passNova = "";
                this.contaEditar.setNomeUtilizador(null);

//                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "O campo senha e confirmar senha devem ser iguais!", ""));
                LogFile.erroMsg(null, "O campo senha e confirmar senha devem ser iguais!");
            }
            else if (this.passNova.trim().equals("") || this.passConfirmada.trim().equals(""))
            {
                this.rendedMostraPass = false;
                this.passNova = "";
                this.contaEditar.setNomeUtilizador(null);
//                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Preencha os Dois Campos das Senhas!!", ""));
                LogFile.erroMsg(null, "Preencha os Dois Campos das Senhas!!");
            }
            else
            {
                contaEditar = segLoginBean.getContaUtilizador();
                contaEditar.setPalavraPasse(EncriptacaoDecriptacao.encrypt(this.passNova));
                segContaFacade.edit(contaEditar);
//                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Senha de Utilizador Actualizada com sucesso!", ""));
                LogFile.sucessoMsg(null, "Senha de Utilizador Actualizada com sucesso!");
            }
        }
        else
        {
//            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Senha de Utilizador Incorrecta!", ""));
            LogFile.erroMsg(null, "Senha de Utilizador Incorrecta!");
        }
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public List<Integer> getPkSegContaSelecionadaList()
    {
        return pkSegContaSelecionadaList;
    }

    public void setPkSegContaSelecionadaList(List<Integer> pkSegContaSelecionadaList)
    {
        this.pkSegContaSelecionadaList = pkSegContaSelecionadaList;
    }

    public List<Integer> getPkSegPerfisSelecionadosList()
    {
        return pkSegPerfisSelecionadosList;
    }

    public void setPkSegPerfisSelecionadosList(List<Integer> pkSegPerfisSelecionadosList)
    {
        this.pkSegPerfisSelecionadosList = pkSegPerfisSelecionadosList;
    }

    public List<SegConta> getContasPesquisadasTotal()
    {
        return contasPesquisadasTotal;
    }

    public boolean isTodosUtilizadores()
    {
        return todosUtilizadores;
    }

    public void setTodosUtilizadores(boolean todosUtilizadores)
    {
        this.todosUtilizadores = todosUtilizadores;
    }

    public boolean isTodosPerfis()
    {
        return todosPerfis;
    }

    public void setTodosPerfis(boolean todosPerfis)
    {
        this.todosPerfis = todosPerfis;
    }

    public List<SegPerfil> getPerfisDasContasPesquisadasTotal()
    {
        return perfisDasContasPesquisadasTotal;
    }

}
