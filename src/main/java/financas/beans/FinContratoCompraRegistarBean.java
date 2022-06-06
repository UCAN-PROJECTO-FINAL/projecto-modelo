/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package financas.beans;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import org.omnifaces.cdi.ViewScoped;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import ejbs.cache.GrlEntidadeCache;
import ejbs.entities.FinContratoCompra;
import ejbs.entities.GrlEntidade;
import javax.faces.application.FacesMessage;
//import ejbs.entities.GrlEntidade;
import patri.utils.Defs;
import static patri.utils.Ficheiro.gravar;
import seg.beans.SegLoginBean;
import utils.mensagens.Mensagem;

/**
 *
 * @author mdnext
 */
@Named(value = "finContratoCompraRegistarBean")
@ViewScoped
public class FinContratoCompraRegistarBean implements Serializable
{

    @Inject
    private FinContratoCompraCache FinContratoCompraCache;
    @Inject
    private SegLoginBean segLoginBean;
    @Inject
    private GrlEntidadeCache ptFornecedorCache;

    private String descricao;

    private Date data;

    private int fkFornecedor;

    private List<GrlEntidade> ptFornecedorList;

    private UploadedFile uploadedFile;

    @PostConstruct
    private void init()
    {
        ptFornecedorList = ptFornecedorCache.getListaGrlEntidades();
    }

    public void salvar()
    {
        FinContratoCompra FinContratoCompra = new FinContratoCompra();
        // FinContratoCompra.setPkPtContratoCompra(FinContratoCompraCache.getIdContratoCompra());
        FinContratoCompra.setData(data);
        FinContratoCompra.setFkUtilizador(segLoginBean.getContaUtilizador());
        FinContratoCompra.setDescicao(descricao);
        FinContratoCompra.setFkGrlEntidade(ptFornecedorCache.findGrlEntidade(fkFornecedor));
        FinContratoCompra.setPathFile(Defs.CONTRATOS_CARREGADOS_DIR + "contracto" + FinContratoCompraCache.findListFinContratoCompraNextFileIndex() + ".pdf");
        //_--------------------------------------------------------------
        FinContratoCompraCache.create(FinContratoCompra);

        //--------------------------------------------------------------------
        Mensagem.enviarMensagem(FacesMessage.SEVERITY_INFO, "Contrato Compra criado com Sucesso! ", null);

        init();
    }

    public void carrregarFicheiro(FileUploadEvent event)
    {
        uploadedFile = event.getFile();
        InputStream ficheiro;
        try
        {
            ficheiro = uploadedFile.getInputstream();
            String filename = "contracto" + FinContratoCompraCache.findListFinContratoCompraNextFileIndex();
            gravar(ficheiro, filename, "pdf", Defs.CONTRATOS_CARREGADOS_DIR);

        } catch (IOException ex)
        {
            Logger.getLogger(FinContratoCompraRegistarBean.class.getName()).log(Level.SEVERE, null, ex);
        }

//        String fileExtensao = filename.substring((uploadedFile.getFileName().lastIndexOf(".") + 1), uploadedFile.getFileName().length());
//
//        switch (fileExtensao)
//        {
//            case "xls":
//                preProcessarDados();
//                break;
//            default:
//                //LogFile.warnMsg(null, "O ficheiro \"" + fileExtensao + "\" deve ter extensão .xls");
//                break;
//        }
    }
    //--------------------------------------------------------------------------------

    public FinContratoCompraCache getFinContratoCompraCache()
    {
        return FinContratoCompraCache;
    }

    public void setFinContratoCompraCache(FinContratoCompraCache FinContratoCompraCache)
    {
        this.FinContratoCompraCache = FinContratoCompraCache;
    }

    public GrlEntidadeCache getGrlEntidadeCache()
    {
        return ptFornecedorCache;
    }

    public void setGrlEntidadeCache(GrlEntidadeCache ptFornecedorCache)
    {
        this.ptFornecedorCache = ptFornecedorCache;
    }

    public String getDescricao()
    {
        return descricao;
    }

    public void setDescricao(String descricao)
    {
        this.descricao = descricao;
    }

    public Date getData()
    {
        return data;
    }

    public void setData(Date data)
    {
        this.data = data;
    }

    public int getFkFornecedor()
    {
        return fkFornecedor;
    }

    public void setFkFornecedor(int fkFornecedor)
    {
        this.fkFornecedor = fkFornecedor;
    }

    public List<GrlEntidade> getGrlEntidadeList()
    {
        return ptFornecedorList;
    }

    public void setGrlEntidadeList(List<GrlEntidade> ptFornecedorList)
    {
        this.ptFornecedorList = ptFornecedorList;
    }

    public UploadedFile getUploadedFile()
    {
        return uploadedFile;
    }

    public void setUploadedFile(UploadedFile uploadedFile)
    {
        this.uploadedFile = uploadedFile;
    }

}
