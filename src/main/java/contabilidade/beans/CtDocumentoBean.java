/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contabilidade.beans;

import contabilidade.util.Numero;
import ejbs.entities.CtDiario;
import ejbs.entities.CtDocument;
import ejbs.facades.CtDiarioFacade;
import ejbs.facades.CtDocumentFacade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import seg.beans.SegLoginBean;
import utils.mensagens.Mensagem;

/**
 *
 * @author majoao
 */
@Named(value = "CtDocumentoBean")
@ViewScoped
public class CtDocumentoBean implements Serializable
{

    /**
     * Creates a new instance of CtDocumentoBean
     */
    @EJB
    private CtDiarioFacade ctDiarioFacade;
    @EJB
    private CtDocumentFacade ctDocumentoFacade;
    @Inject
    private SegLoginBean segLoginBean;
    // objectos
    private CtDiario diario;
    private CtDocument documento;
    private Numero numero;

    // listas
    private List<CtDiario> listDiario = null;
    private List<CtDocument> listDocumento = null;
    private List<Numero> listNumero = null;

    //Variáveis
    private Integer codigoDiario = 0;

    public CtDocumentoBean()
    {

        this.documento = new CtDocument();
        this.diario = new CtDiario();
        this.listDiario = new ArrayList<>();
        this.listDocumento = new ArrayList<>();
        this.numero = new Numero();
        this.listNumero = new ArrayList<>();
        this.codigoDiario = 0;
    }

    public CtDiario getDiario()
    {
        return diario;
    }

    public void setDiario(CtDiario diario)
    {
        this.diario = diario;
    }

    public CtDocument getDocumento()
    {
        return documento;
    }

    public void setDocumento(CtDocument documento)
    {
        this.documento = documento;
    }

    public Numero getNumero()
    {
        return numero;
    }

    public void setNumero(Numero numero)
    {
        this.numero = numero;
    }

    public List<CtDiario> getListDiario()
    {

        this.listDiario = new ArrayList<>();

        this.listDiario = ctDiarioFacade.listDiarios();

        if (this.listDiario != null)
        {

            return this.listDiario;

        }

        return this.listDiario;
    }

    public void setListDiario(List<CtDiario> listDiario)
    {
        this.listDiario = listDiario;
    }

    public List<CtDocument> getListDocumento()
    {

        this.listDocumento = new ArrayList<>();

        this.listDocumento = ctDocumentoFacade.listDocuments();

        if (this.listDocumento != null)
        {

            return this.listDocumento;
        }
        return this.listDocumento;
    }

    public void setListDocumento(List<CtDocument> listDocumento)
    {
        this.listDocumento = listDocumento;
    }

    public List<Numero> getListNumero()
    {
        return numero.Numeros();

    }

    public void setListNumero(List<Numero> listNumero)
    {
        this.listNumero = listNumero;
    }

    public Integer getCodigoDiario()
    {
        return codigoDiario;
    }

    public void setCodigoDiario(Integer codigoDiario)
    {
        this.codigoDiario = codigoDiario;
    }

    public String getNumeroDocumento(int codigoDiario, int number)
    {
        return codigoDiario + "." + "" + number;
    }

    public String gravar()
    {

        List<CtDocument> list = this.getListDocumento();

        try
        {

            String numeroDocumento = gerarNumeroDocumento(this.codigoDiario);

            this.documento.setFkUtilizador(segLoginBean.getContaUtilizador());
            this.documento.setPkDocument(this.ctDocumentoFacade.count() + 1);
            this.documento.setStateDocument(true);
            this.documento.setNumeroDocumento(numeroDocumento);
            this.documento.setDataRegistroDocumento(new Date());
            this.documento.setFkDiario(ctDiarioFacade.find(codigoDiario));

            if (list != null)
            {

                for (CtDocument item : list)
                {

                    if (item.getDescricaoDocumento().equals(this.documento.getDescricaoDocumento()))
                    {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "O Documento " + documento.getDescricaoDocumento() + " já existe."));
                        return "";
                    } else if (item.getNumeroDocumento().equals(this.documento.getNumeroDocumento()))
                    {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Já existe um documento com o número  " + documento.getNumeroDocumento() + "."));
                        return "";
                    }
                }

            }

            this.ctDocumentoFacade.create(this.documento);
            this.documento = new CtDocument();
            this.codigoDiario = 0;
            this.listNumero = new ArrayList<>();

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "O Documento  foi criado com sucesso!"));
        } catch (Exception ex)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao salvar a verifique todos os parametros de inserção!", ""));
            ex.printStackTrace();
        }

        return "";

    }

    public void editar()
    {

        try
        {

            this.documento.setStateDocument(true);
            this.documento.setDataRegistroDocumento(new Date());
            this.documento.setNumeroDocumento(getNumeroDocumento(this.documento.getFkDiario().getPkDiario(), this.documento.getNumber()));
            this.documento.setFkDiario(ctDiarioFacade.find(this.documento.getFkDiario().getPkDiario()));
            this.ctDocumentoFacade.edit(this.documento);
            this.documento = new CtDocument();
            this.diario = new CtDiario();
            this.listNumero = new ArrayList<>();
            this.codigoDiario = 0;
            Mensagem.enviarMensagem(FacesMessage.SEVERITY_INFO, null, "O Documento foi alterado com sucesso!");

        } catch (Exception exception)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Impossível alterar o Documento, verifique os dados de inserção ou entre em contacto com o Administrador de Sistemas."));
            exception.printStackTrace();

        }
    }

    public void eliminar(int document)
    {
        try
        {

            this.documento = ctDocumentoFacade.find(document);
            this.documento.setStateDocument(false);
            this.ctDocumentoFacade.edit(this.documento);
            this.documento = new CtDocument();
            this.diario = new CtDiario();
            Mensagem.enviarMensagem(FacesMessage.SEVERITY_INFO, null, "O Documento foi eliminado com sucesso!");

        } catch (Exception exception)
        {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Impossível eliminar o Documento, verifque se não está ser utilizado em outro registro ou entre em contacto com o Administrador de Sistemas.", ""));
            exception.printStackTrace();
        }

    }

    private String gerarNumeroDocumento(Integer codigoDiario)
    {

        List<CtDocument> lista = ctDocumentoFacade.getDocumentoPorDiario(codigoDiario);

        if (lista != null)
        {
            this.documento.setNumber(lista.size() + 1);
            return codigoDiario + "." + (lista.size() + 1);
        }

        this.documento.setNumber(1);
        return codigoDiario + "." + 1;

    }

}
