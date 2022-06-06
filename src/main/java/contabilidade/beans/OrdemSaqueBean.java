package contabilidade.beans;

///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package contabilidade;
//
//import java.io.Serializable;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import javax.ejb.EJB;
//import javax.faces.application.FacesMessage;
//import javax.faces.context.FacesContext;
//import javax.inject.Named;
//import javax.faces.view.ViewScoped;
//import utils.mensagens.Mensagem;
//
///**
// *
// * @author majoao
// */
//@Named(value = "ordemSaqueBean")
//@ViewScoped
//public class OrdemSaqueBean implements Serializable {
//
//    @EJB
//    private FnDocumentoFacade fnDocumentoFacade;
//
//    @EJB
//    private FnContaCorrenteFacade fnContacorrenteFacade;
//
//    @EJB
//    private FnEntidadeFacade fnEntidadeFacade;
//
//    @EJB
//    private FnEmpresaFacade fnEmpresaFacade;
//
//    private FnDocumento ordemSaque;
//
//    @EJB
//    private FnTipoDocumentoFacade fnTipodocumentoFacade;
//
//    private List<FnDocumento> listOrdemSaque = null;
//    private List<FnDocumento> listDocumento = null;
//    private List<FnEmpresa> listEmpresa = null;
//    private List<FnTipoDocumento> listTipo = null;
//    private List<FnEntidade> listEntidade = null;
//    private Mensagem mensagem = null;
//
//    private boolean estado;
//    private boolean btnSalvar = true;
//    private boolean btnFormularioCadastro;
//    private boolean tabListar;
//    private boolean status = true;
//
//    private int codigoTipo = 0;
//    private int codigoEmpresa = 0;
//    private int codigoEntidade = 0;
//
//    public OrdemSaqueBean() {
//
//        this.listOrdemSaque = new ArrayList<>();
//        this.ordemSaque = new FnDocumento();
//        this.listDocumento = new ArrayList<>();
//        this.listEmpresa = new ArrayList<>();
//        this.mensagem = new Mensagem();
//        this.listTipo = new ArrayList<>();
//        this.listEntidade = new ArrayList<>();
//    }
//
//    public FnDocumento getOrdemSaque() {
//        return ordemSaque;
//    }
//
//    public void setOrdemSaque(FnDocumento ordemSaque) {
//        this.ordemSaque = ordemSaque;
//    }
//
//    public List<FnDocumento> getListOrdemSaque(){
//        return new FnDocumentoDAO().getOSList();
//    }
//
//    public void setListOrdemSaque(List<FnDocumento> listOrdemSaque) {
//        this.listOrdemSaque = listOrdemSaque;
//    }
//
//    public boolean isEstado() {
//        return estado;
//    }
//
//    public void setEstado(boolean estado) {
//        this.estado = estado;
//    }
//
//    public boolean isBtnSalvar() {
//        return btnSalvar;
//    }
//
//    public void setBtnSalvar(boolean btnSalvar) {
//        this.btnSalvar = btnSalvar;
//    }
//
//    public boolean isBtnFormularioCadastro() {
//        return btnFormularioCadastro;
//    }
//
//    public void setBtnFormularioCadastro(boolean btnFormularioCadastro) {
//        this.btnFormularioCadastro = btnFormularioCadastro;
//    }
//
//    public boolean isTabListar() {
//        return tabListar;
//    }
//
//    public void setTabListar(boolean tabListar) {
//        this.tabListar = tabListar;
//    }
//
//    //Metodos para formulário
//    public void voltar() {
//
//        this.btnSalvar = true;
//        this.btnFormularioCadastro = false;
//        this.tabListar = false;
//        this.estado = false;
//        this.ordemSaque = new FnDocumento();
//
//    }
//
//    public void novo() {
//
//        this.btnSalvar = false;
//        this.btnFormularioCadastro = true;
//        this.tabListar = false;
//        this.estado = false;
//
//    }
//
//    public void btnListar() {
//
//        this.tabListar = true;
//        this.btnFormularioCadastro = false;
//        this.btnSalvar = true;
//        this.estado = false;
//    }
//
//    public void btnEditar() {
//
//        this.estado = true;
//        this.tabListar = false;
//
//    }
//
//    public String alterar(FnDocumento termo) {
//
//        btnEditar();
//        this.ordemSaque = termo;
//
//        return "";
//
//    }
//
//    public String gravar() {
//
//        List<FnDocumento> list = this.getListDocumento();
//
//        try {
//
//            this.ordemSaque.setDataRegisto(new Date());
//
//            if (list != null) {
//
//                for (FnDocumento item : list) {
//
//                    if (item.getNumeroDocumento().equals(this.ordemSaque.getNumeroDocumento()) && item.getFkEntidade().getPkEntidade() == this.codigoEntidade) {
//                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "A Ordem de Saque já existe."));
//                        return "";
//                    }
//                }
//            }
//            this.ordemSaque.setValorDisponivel(this.ordemSaque.getValorDocumento());
//            this.ordemSaque.setNumeroDocumento(this.ordemSaque.getNumeroOrdemSaque());
//            this.ordemSaque.setDataRegisto(new Date());
//            this.ordemSaque.setFkEntidade(fnEntidadeFacade.find(this.codigoEntidade));
//            this.ordemSaque.setFkTipoDocumento(fnTipodocumentoFacade.find(2));
//            this.ordemSaque.setFkEmpresa(fnEmpresaFacade.find(1));
//            this.ordemSaque.setEstadoDocumento(true);
//            this.ordemSaque.setFkContas(mensagem.utilizadorLogado());
//            this.ordemSaque.setEstadoOrdemSaque(true);
//            this.ordemSaque.setStatePt(true);
//            this.ordemSaque.setStateDocumento(true);
//            this.ordemSaque.setNovoFn("O Funcionário " + this.mensagem.utilizadorLogado().getFkFuncionario().getFkPessoa().getNome() + " registrou o documento com o número " + this.ordemSaque.getNumeroDocumento() + " no exerício em curso.");
//            this.ordemSaque.setDataNovoFn(new Date());
//            this.fnDocumentoFacade.create(this.ordemSaque);
//            this.ordemSaque = new FnDocumento();
//            FacesUtil.adicionarMensagem(FacesMessage.SEVERITY_INFO, "Dados salvos com Sucesso!");
//
//        } catch (Exception ex) {
//            FacesUtil.adicionarMensagem(FacesMessage.SEVERITY_ERROR, "Erro ao salvar a verifique todos os parametros de inserção!");
//            ex.printStackTrace();
//        }
//        return "";
//    }
//
//    public void editar() {
//
//        try {
//
//            this.ordemSaque.setNumeroDocumento(this.ordemSaque.getNumeroOrdemSaque());
//            this.ordemSaque.setStateDocumento(true);
//            this.ordemSaque.setFkContas(mensagem.utilizadorLogado());
//            this.ordemSaque.setAlterarFn("O Funcionário " + this.mensagem.utilizadorLogado().getFkFuncionario().getFkPessoa().getNome() + " alterou o documento com o número " + this.ordemSaque.getNumeroDocumento() + ".");
//            this.ordemSaque.setDataAlterarFn(new Date());
//            this.fnDocumentoFacade.edit(this.ordemSaque);
//            this.ordemSaque = new FnDocumento();
//            FacesUtil.adicionarMensagem(FacesMessage.SEVERITY_INFO, "A Ordem de Saque foi alterada com Sucesso!");
//
//        } catch (Exception exception) {
//            FacesUtil.adicionarMensagem(FacesMessage.SEVERITY_ERROR, "Impossível alterar o termo, verifica os dados da inserção ou entre em contacto com o seu Administrador de Sistemas.");
//            exception.printStackTrace();
//        }
//        this.estado = false;
//        btnListar();
//
//    }
//
//    public void eliminar(int termo) {
//
//        try {
//
//            this.ordemSaque = fnDocumentoFacade.find(termo);
//            this.ordemSaque.setFkContas(mensagem.utilizadorLogado());
//            this.ordemSaque.setStateDocumento(false);
//            this.fnDocumentoFacade.edit(this.ordemSaque);
//            FacesUtil.adicionarMensagem(FacesMessage.SEVERITY_INFO, "A Ordem de Saque foi excluída com Sucesso!");
//
//        } catch (Exception exception) {
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Impossível eliminar a Ordem de Saque, verifque se não está ser utilizado em outro registro ou entre em contacto com o Administrador de Sistemas.", ""));
//            exception.printStackTrace();
//        }
//
//    }
//
//    public boolean isStatus() {
//        return status;
//    }
//
//    public void setStatus(boolean status) {
//        this.status = status;
//    }
//
//    public List<FnDocumento> getListDocumento() {
//
//        this.listDocumento = new ArrayList<>();
//
//        this.listDocumento = new FnDocumentoDAO().getDocummento();
//
//        if (this.listDocumento != null) {
//
//            return this.listDocumento;
//        }
//
//        return listDocumento;
//    }
//
//    public void setListDocumento(List<FnDocumento> listDocumento) {
//        this.listDocumento = listDocumento;
//    }
//
//    public List<FnEmpresa> getListEmpresa() {
//        return fnEmpresaFacade.findAll();
//    }
//
//    public void setListEmpresa(List<FnEmpresa> listEmpresa) {
//        this.listEmpresa = listEmpresa;
//    }
//
//    public List<FnTipoDocumento> getListTipo() {
//        return listTipo;
//    }
//
//    public void setListTipo(List<FnTipoDocumento> listTipo) {
//        this.listTipo = listTipo;
//    }
//
//    public List<FnEntidade> getListEntidade() {
//        return new FnEntidadeDAO().getEntidadeFornecedores();
//    }
//
//    public void setListEntidade(List<FnEntidade> listEntidade) {
//        this.listEntidade = listEntidade;
//    }
//
//    public int getCodigoTipo() {
//        return codigoTipo;
//    }
//
//    public void setCodigoTipo(int codigoTipo) {
//        this.codigoTipo = codigoTipo;
//    }
//
//    public int getCodigoEmpresa() {
//        return codigoEmpresa;
//    }
//
//    public void setCodigoEmpresa(int codigoEmpresa) {
//        this.codigoEmpresa = codigoEmpresa;
//    }
//
//    public int getCodigoEntidade() {
//        return codigoEntidade;
//    }
//
//    public void setCodigoEntidade(int codigoEntidade) {
//        this.codigoEntidade = codigoEntidade;
//    }
//
//    public Mensagem getMensagem() {
//        return mensagem;
//    }
//
//    public void setMensagem(Mensagem mensagem) {
//        this.mensagem = mensagem;
//    }
//
//}