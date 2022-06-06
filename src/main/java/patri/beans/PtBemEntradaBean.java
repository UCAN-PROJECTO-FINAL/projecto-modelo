 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patri.beans;

import com.sun.mail.handlers.image_gif;
import estrutura.ejbs.cache.EstruturaFisicaCache;
import ejbs.entities.EstruturaFisica;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import org.omnifaces.cdi.ViewScoped;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import patri.ejbs.cache.PtBemEntradaCache;
import patri.ejbs.cache.PtBemImovelCache;
import patri.ejbs.cache.PtBemItangivelCache;
import patri.ejbs.cache.PtBemMovelCache;
import patri.ejbs.cache.PtCategoriaCache;
import ejbs.facades.FinContratoCompraFacade;
import patri.ejbs.cache.PtEstadoConservacaoCache;
import patri.ejbs.cache.PtFormaAquisicaoCache;
import patri.ejbs.cache.PtMarcaCache;
import patri.ejbs.cache.PtModeloCache;
import ejbs.entities.PtBemEntrada;
import ejbs.entities.PtBemImovel;
import ejbs.entities.PtBemItangivel;
import ejbs.entities.PtBemMovel;
import ejbs.entities.PtCategoria;
import ejbs.entities.FinContratoCompra;
import ejbs.entities.PtEstadoConservacao;
import ejbs.entities.PtFormaAquisicao;
import ejbs.entities.PtMarca;
import ejbs.entities.PtModelo;
import javax.ejb.EJB;
import patri.utils.Defs;
import static patri.utils.Ficheiro.gravar;
import utils.mensagens.LogFile;

/**
 *
 * @author mdnext
 */
@Named(value = "ptBemEntradaBean")
@ViewScoped
public class PtBemEntradaBean implements Serializable
{
    @Resource
    private UserTransaction userTransaction;
    
    @Inject
    private PtBemEntradaCache ptBemEntradaCache;
    
    @Inject
    private PtBemItangivelCache ptBemItangivelCache;
    
    @Inject
    private PtBemMovelCache ptBemMovelCache;
    
    @Inject
    private PtBemImovelCache ptBemImovelCache;
    
    @Inject
    private PtEstadoConservacaoCache ptEstadoConservacaoCache;
    
    @Inject
    private PtMarcaCache ptMarcaCache;
    
    @Inject
    private PtModeloCache ptModeloCache;
    
    @Inject
    private EstruturaFisicaCache estruturaFisicaCache;
    
    @Inject
    private PtFormaAquisicaoCache ptFormaAquisicaoCache;
    
//    @Inject
//    private PtContratoCompraCache ptContratoCompraCache;
    @EJB
    private FinContratoCompraFacade ptContratoCompraCache;
    
    @Inject
    private PtCategoriaCache ptCategoriaCache;
    
    private int quantidade,fkPtFormaAquisicao,fkPtContratoCompra;
    
    private Date dataRegisto;
    
    private double custoUnidade;
    
    private String fkPtCategoriaLevel1,fkPtCategoriaLevel2,fkPtCategoriaLevel3;
    
    private List<PtCategoria> listPtCategoriasLevel1,listPtCategoriasLevel2,listPtCategoriasLevel3;
    
    private List<PtFormaAquisicao> listaPtFormaAquisicao;
    
    private List<FinContratoCompra> listaPtContratoCompra;
    
    private boolean bemImovelEnabled,bemMovelEnabled,bemItangivelEnabled;
    //________________________________________________________________________
    private String imgPath = "",docPath = "",cod;
    
    private UploadedFile uploadedImageFile,uploadedDocFile; 
    
    private boolean imgFileEnabled,docFileEnabled;
    
    //_____________________________________MOVEL
    private String detalhesMovel;
        
    private int taxaDepreciacaoMes,fkPtEstadoConservacaoMovel,fkPtMarca,fkPtModelo;

    private double valorActualMovel;

    private List<PtEstadoConservacao> listPtEstadoConservacaoMovel;

    private List<PtMarca> listPtMarca;

    private List<PtModelo> listPtModelo;
//______________________________________________imovel________________________________________________
    private String descricaoCartorio,fkEstruturaFisica,detalhesImovel;

    private int taxaMes,fkPtEstadoConservacaoImovel;

    private Date dataConstrucao;

    private double valorActualImovel;

    private List<EstruturaFisica> listEstruturaFisica;

    private List<PtEstadoConservacao> listPtEstadoConservacaoImovel;

//____________________________________________________ITANGIVEL_________________________________________
    private String descricaoDetalhada,nome;
 //________________________________________
    
    @PostConstruct
    private void init()
    {
        quantidade = 1;
        
        listPtCategoriasLevel1 = ptCategoriaCache.getListaPaisPtCategoria();
        fkPtCategoriaLevel1 = listPtCategoriasLevel1.get(0).getPkPtCategoria();
        actualizaListPtCategoriasLevel2();
        
        bemImovelEnabled = false;
        bemMovelEnabled = false;
        bemItangivelEnabled = false;
        
        listaPtFormaAquisicao = ptFormaAquisicaoCache.getListaPtFormaAquisicaos();
        fkPtFormaAquisicao = listaPtFormaAquisicao.get(0).getPkPtFormaAquisicao();
        listaPtContratoCompra = ptContratoCompraCache.findAll();
        fkPtContratoCompra = listaPtContratoCompra.get(0).getPkPtContratoCompra();
        //BEM MOVEL FORM
        this.listPtMarca = ptMarcaCache.getListaPtMarcas();
        this.fkPtMarca = listPtMarca.get(0).getPkPtMarca();
        this.listPtEstadoConservacaoMovel = ptEstadoConservacaoCache.getListaPtEstadoConservacaos();
        this.fkPtEstadoConservacaoMovel = listPtEstadoConservacaoMovel.get(0).getPkPtEstadoConservacao();
        changeModeloList();
        //BEM IMOVEL FORM
        this.listEstruturaFisica = estruturaFisicaCache.getListaPaisEstruturaFisicas();
        this.fkEstruturaFisica = listEstruturaFisica.get(0).getPkEstruturaFisica();

        this.listPtEstadoConservacaoImovel = ptEstadoConservacaoCache.getListaPtEstadoConservacaos();
        this.fkPtEstadoConservacaoImovel = listPtEstadoConservacaoImovel.get(0).getPkPtEstadoConservacao();
        //BEM ITANGIVEL FORM
        
        
        imgFileEnabled = false;
        docFileEnabled = false;

         
    }
    
    public void actualizaListPtCategoriasLevel2()
    {
        listPtCategoriasLevel2 = ptCategoriaCache.getListaSonsByPkPtCategoria(fkPtCategoriaLevel1);
        fkPtCategoriaLevel2 = listPtCategoriasLevel2.get(0).getPkPtCategoria();
        actualizaListPtCategoriasLevel3();
    }
    
    public void actualizaListPtCategoriasLevel3()
    {
        listPtCategoriasLevel3 = ptCategoriaCache.getListaSonsByPkPtCategoria(fkPtCategoriaLevel2);
        fkPtCategoriaLevel3 = listPtCategoriasLevel3.get(0).getPkPtCategoria();
        changeFormStatus();
    }
    
     public void changeModeloList()
        {
            this.listPtModelo = ptModeloCache.getModeloByFkPtMarca(fkPtMarca);
            this.fkPtModelo = listPtModelo.get(0).getPkPtModelo();
        }
    
    public void changeFormStatus()
    {
       PtCategoria categoria = ptCategoriaCache.findPtCategoria(fkPtCategoriaLevel3).getFkPtCategoria();
       
       if(categoria.getDescricao().equals("Movel"))
       {
           System.err.println("categoria" + categoria.getDescricao());
           
            bemImovelEnabled = false;
            bemMovelEnabled = true;
            bemItangivelEnabled = false;
            imgFileEnabled = true;
            docFileEnabled = false;
       }
       else if (categoria.getDescricao().equals("Imóvel"))
        {
            
            bemImovelEnabled = true;
            bemMovelEnabled = false;
            bemItangivelEnabled = false;
            imgFileEnabled = true;
            docFileEnabled = true;
        }
       else if(categoria.getFkPtCategoria().getDescricao().equals("Itángivel"))
       {
            bemImovelEnabled = false;
            bemMovelEnabled = false;
            bemItangivelEnabled = true;
            imgFileEnabled = false;
            docFileEnabled = true;
       }
    }
    
    public void salvar()
    {
        try {
                this.userTransaction.begin();
                
                PtBemEntrada bem = new PtBemEntrada();
                
                bem.setCustoUnidade(custoUnidade);
                bem.setDataRegisto(dataRegisto);
                bem.setQuantidade(quantidade);
                bem.setFkPtCategoria(ptCategoriaCache.findPtCategoria(fkPtCategoriaLevel3));
                bem.setFkPtFormaAquisicao(ptFormaAquisicaoCache.findPtFormaAquisicao(fkPtFormaAquisicao));
                bem.setFkPtContratoCompra(ptContratoCompraCache.find(fkPtContratoCompra));
                
                
                bem.setImagemPath(imgPath);
                System.err.println("ssssssssssssssssssss"+bem.getImagemPath());
                
                bem.setDocumentacaoPath(docPath);
                
                ptBemEntradaCache.create(bem);
                
                salvarTipo();
                
                this.userTransaction.commit(); 
        } 
        catch (IllegalStateException | SecurityException | SystemException ex) {
            try
            {
                this.userTransaction.rollback();
            }
            catch (IllegalStateException | SecurityException | SystemException e)
            {
                System.err.println("errouuuuuuuuuuuu");
            }
        } catch (NotSupportedException ex) {
            Logger.getLogger(PtBemEntradaBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RollbackException ex) {
            Logger.getLogger(PtBemEntradaBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (HeuristicMixedException ex) {
            Logger.getLogger(PtBemEntradaBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (HeuristicRollbackException ex) {
            Logger.getLogger(PtBemEntradaBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    private void salvarTipo()
    {
        PtBemEntrada bem = ptBemEntradaCache.getLastRegisted();
        if(bemMovelEnabled)
            salvarBemMovel(bem);
        if(bemImovelEnabled)
            salvarBemImovel(bem);
        if(bemImovelEnabled)
            salvarBemItangivel(bem);
    }
    
    private void salvarBemMovel(PtBemEntrada bem)
    {
        PtBemMovel bemMovel;
        
        for(int i = 0;i<=quantidade;i++)
        {
            bemMovel = new PtBemMovel();
            bemMovel.setFkPtBemEntrada(bem);
        
            bemMovel.setCod("PT-BMO"+bem.getFkPtCategoria().getCod()+ (ptBemMovelCache.getLastRegisted()));
            bemMovel.setDetalhes(detalhesMovel);
            bemMovel.setImagemPath("____");
            bemMovel.setTaxaDepreciacaoMes(taxaDepreciacaoMes);
            bemMovel.setFkPtModelo(ptModeloCache.findPtModelo(fkPtModelo));
            bemMovel.setFkPtEstadoConservacao(ptEstadoConservacaoCache.findPtEstadoConservacao(fkPtEstadoConservacaoMovel));

            ptBemMovelCache.create(bemMovel);
            
        }
    }
    
    private void salvarBemImovel(PtBemEntrada bem)
    {
        PtBemImovel bemImovel = new PtBemImovel();
        
        bemImovel.setFkPtBemEntrada(bem);
        
        bemImovel.setCod("PT-BIM"+bem.getFkPtCategoria().getCod()+ (ptBemImovelCache.getLastRegisted()));
        bemImovel.setDataConstrucao(dataConstrucao);
        bemImovel.setDetalhes(detalhesMovel);
        bemImovel.setDescricaoCartorio(descricaoCartorio);
        bemImovel.setFkEstruturaFisica(estruturaFisicaCache.findEstruturaFisica(fkEstruturaFisica));
        bemImovel.setTaxaMes(taxaMes);
        bemImovel.setValorActual(valorActualImovel);
        bemImovel.setImagemPath("____");
        bemImovel.setDocumentoPath("____");
        bemImovel.setFkPtEstadoConservacao(ptEstadoConservacaoCache.findPtEstadoConservacao(fkPtEstadoConservacaoImovel));
        
        
        ptBemImovelCache.create(bemImovel);
        
    }
    
    private void salvarBemItangivel(PtBemEntrada bem)
    {
        PtBemItangivel bemItangivel = new PtBemItangivel();
        
        bemItangivel.setFkPtBemEntrada(bem);
        
        bemItangivel.setCod("PT-BIT"+bem.getFkPtCategoria().getCod()+ (ptBemItangivelCache.getLastRegisted()));
        bemItangivel.setDescricaoDetalhada(descricaoDetalhada);
        bemItangivel.setDocumentacaoPath("____");
        bemItangivel.setNome(nome);
        
        ptBemItangivelCache.create(bemItangivel);
        
    }
    
    public String getImagePathString()
    {
        String path = "";
        PtCategoria categoria = ptCategoriaCache.findPtCategoria(fkPtCategoriaLevel3).getFkPtCategoria();
       
       if(categoria.getDescricao().equals("Movel"))
       {
           return Defs.IMAGENS_BENS_MOVEIS_CARREGADOS_DIR;
       }
       else if (categoria.getDescricao().equals("Imóvel"))
        {
            
            return Defs.IMAGENS_BENS_IMOVEIS_CARREGADOS_DIR;
        }
        
        return path;
    }

    public String getDocsPathString()
    {
        String path = "";
        PtCategoria categoria = ptCategoriaCache.findPtCategoria(fkPtCategoriaLevel3).getFkPtCategoria();
       
       if(categoria.getDescricao().equals("Movel"))
       {
           return Defs.DOCUMENTOS_BENS_MOVEIS_CARREGADOS_DIR;
       }
       else if (categoria.getDescricao().equals("Imóvel"))
        {
            
            return Defs.DOCUMENTOS_BENS_IMOVEIS_CARREGADOS_DIR;
        }
       else if(categoria.getFkPtCategoria().getDescricao().equals("Itángivel"))
       {
           return Defs.DOCUMENTOS_BENS_ITANGIVEIS_CARREGADOS_DIR;
       }
        
        return path;
    }
    
    public void carregarImagem(FileUploadEvent event)
        {
            uploadedImageFile = event.getFile();
            String a = "index.jpeg";
            InputStream ficheiro;
            String path = getImagePathString();
             try {
                 ficheiro = uploadedImageFile.getInputstream();
                 String extensao = getEstensao(uploadedImageFile.getFileName());
                 String filename = "imagem_bem" + (ptBemEntradaCache.getListaPtBemEntrada().size() + 1);
                 gravar(ficheiro,filename,extensao,Defs.IMG_PATRI_DIRECTORIO);
                 this.imgPath = Defs.IMG_PATRI_DIRECTORIO+filename+"."+extensao;

             } catch (IOException ex) {
//                 Logger.getLogger(PtContratoCompraRegistarBean.class.getName()).log(Level.SEVERE, null, ex);
                 System.err.println("ERRO");
             }
        }
    
    private String getEstensao(String file)
    {
        if(file.contains(".jpeg"))
            return "jpeg";
        if(file.contains(".jpg"))
            return "jpg";
        if(file.contains(".png"))
            return "png";
        return "";
    }
        
    public void carregarDocumento(FileUploadEvent event)
    {
        uploadedDocFile = event.getFile();
        InputStream ficheiro;
        String path = getDocsPathString();
         try {
             ficheiro = uploadedDocFile.getInputstream();
             String filename = "documento_bem" + (ptBemEntradaCache.getListaPtBemEntrada().size() + 1);
             gravar(ficheiro,filename,"pdf",Defs.DOCS_PATRI_DIRECTORIO);
             this.docPath = Defs.DOCS_PATRI_DIRECTORIO+filename+".pdf";

         } catch (IOException ex) {
            System.err.println("ERRO");
         }
    }
    //__________________________getters & setters_____________

    public PtBemEntradaCache getPtBemEntradaCache()
    {
        return ptBemEntradaCache;
    }

    public void setPtBemEntradaCache(PtBemEntradaCache ptBemEntradaCache)
    {
        this.ptBemEntradaCache = ptBemEntradaCache;
    }

    public PtBemItangivelCache getPtBemItangivelCache()
    {
        return ptBemItangivelCache;
    }

    public void setPtBemItangivelCache(PtBemItangivelCache ptBemItangivelCache)
    {
        this.ptBemItangivelCache = ptBemItangivelCache;
    }

    public PtBemMovelCache getPtBemMovelCache()
    {
        return ptBemMovelCache;
    }

    public void setPtBemMovelCache(PtBemMovelCache ptBemMovelCache)
    {
        this.ptBemMovelCache = ptBemMovelCache;
    }

    public PtBemImovelCache getPtBemImovelCache()
    {
        return ptBemImovelCache;
    }

    public void setPtBemImovelCache(PtBemImovelCache ptBemImovelCache)
    {
        this.ptBemImovelCache = ptBemImovelCache;
    }

    public PtEstadoConservacaoCache getPtEstadoConservacaoCache()
    {
        return ptEstadoConservacaoCache;
    }

    public void setPtEstadoConservacaoCache(PtEstadoConservacaoCache ptEstadoConservacaoCache)
    {
        this.ptEstadoConservacaoCache = ptEstadoConservacaoCache;
    }

    public PtMarcaCache getPtMarcaCache()
    {
        return ptMarcaCache;
    }

    public void setPtMarcaCache(PtMarcaCache ptMarcaCache)
    {
        this.ptMarcaCache = ptMarcaCache;
    }

    public PtModeloCache getPtModeloCache()
    {
        return ptModeloCache;
    }

    public void setPtModeloCache(PtModeloCache ptModeloCache)
    {
        this.ptModeloCache = ptModeloCache;
    }

    public EstruturaFisicaCache getEstruturaFisicaCache()
    {
        return estruturaFisicaCache;
    }

    public void setEstruturaFisicaCache(EstruturaFisicaCache estruturaFisicaCache)
    {
        this.estruturaFisicaCache = estruturaFisicaCache;
    }

    public PtFormaAquisicaoCache getPtFormaAquisicaoCache()
    {
        return ptFormaAquisicaoCache;
    }

    public void setPtFormaAquisicaoCache(PtFormaAquisicaoCache ptFormaAquisicaoCache)
    {
        this.ptFormaAquisicaoCache = ptFormaAquisicaoCache;
    }

    public FinContratoCompraFacade getPtContratoCompraCache()
    {
        return ptContratoCompraCache;
    }

    public void setPtContratoCompraCache(FinContratoCompraFacade ptContratoCompraCache)
    {
        this.ptContratoCompraCache = ptContratoCompraCache;
    }

    public PtCategoriaCache getPtCategoriaCache()
    {
        return ptCategoriaCache;
    }

    public void setPtCategoriaCache(PtCategoriaCache ptCategoriaCache)
    {
        this.ptCategoriaCache = ptCategoriaCache;
    }

    public int getQuantidade()
    {
        return quantidade;
    }

    public void setQuantidade(int quantidade)
    {
        this.quantidade = quantidade;
    }

    public int getFkPtFormaAquisicao()
    {
        return fkPtFormaAquisicao;
    }

    public void setFkPtFormaAquisicao(int fkPtFormaAquisicao)
    {
        this.fkPtFormaAquisicao = fkPtFormaAquisicao;
    }

    public int getFkPtContratoCompra()
    {
        return fkPtContratoCompra;
    }

    public void setFkPtContratoCompra(int fkPtContratoCompra)
    {
        this.fkPtContratoCompra = fkPtContratoCompra;
    }

    public Date getDataRegisto()
    {
        return dataRegisto;
    }

    public void setDataRegisto(Date dataRegisto)
    {
        this.dataRegisto = dataRegisto;
    }

    public double getCustoUnidade()
    {
        return custoUnidade;
    }

    public void setCustoUnidade(double custoUnidade)
    {
        this.custoUnidade = custoUnidade;
    }

    public String getFkPtCategoriaLevel1()
    {
        return fkPtCategoriaLevel1;
    }

    public void setFkPtCategoriaLevel1(String fkPtCategoriaLevel1)
    {
        this.fkPtCategoriaLevel1 = fkPtCategoriaLevel1;
    }

    public String getFkPtCategoriaLevel2()
    {
        return fkPtCategoriaLevel2;
    }

    public void setFkPtCategoriaLevel2(String fkPtCategoriaLevel2)
    {
        this.fkPtCategoriaLevel2 = fkPtCategoriaLevel2;
    }

    public String getFkPtCategoriaLevel3()
    {
        return fkPtCategoriaLevel3;
    }

    public void setFkPtCategoriaLevel3(String fkPtCategoriaLevel3)
    {
        this.fkPtCategoriaLevel3 = fkPtCategoriaLevel3;
    }

    public List<PtCategoria> getListPtCategoriasLevel1()
    {
        return listPtCategoriasLevel1;
    }

    public void setListPtCategoriasLevel1(List<PtCategoria> listPtCategoriasLevel1)
    {
        this.listPtCategoriasLevel1 = listPtCategoriasLevel1;
    }

    public List<PtCategoria> getListPtCategoriasLevel2()
    {
        return listPtCategoriasLevel2;
    }

    public void setListPtCategoriasLevel2(List<PtCategoria> listPtCategoriasLevel2)
    {
        this.listPtCategoriasLevel2 = listPtCategoriasLevel2;
    }

    public List<PtCategoria> getListPtCategoriasLevel3()
    {
        return listPtCategoriasLevel3;
    }

    public void setListPtCategoriasLevel3(List<PtCategoria> listPtCategoriasLevel3)
    {
        this.listPtCategoriasLevel3 = listPtCategoriasLevel3;
    }

    public List<PtFormaAquisicao> getListaPtFormaAquisicao()
    {
        return listaPtFormaAquisicao;
    }

    public void setListaPtFormaAquisicao(List<PtFormaAquisicao> listaPtFormaAquisicao)
    {
        this.listaPtFormaAquisicao = listaPtFormaAquisicao;
    }

    public List<FinContratoCompra> getListaPtContratoCompra()
    {
        return listaPtContratoCompra;
    }

    public void setListaPtContratoCompra(List<FinContratoCompra> listaPtContratoCompra)
    {
        this.listaPtContratoCompra = listaPtContratoCompra;
    }


    public boolean isBemImovelEnabled()
    {
        return bemImovelEnabled;
    }

    public void setBemImovelEnabled(boolean bemImovelEnabled)
    {
        this.bemImovelEnabled = bemImovelEnabled;
    }

    public boolean isBemMovelEnabled()
    {
        return bemMovelEnabled;
    }

    public void setBemMovelEnabled(boolean bemMovelEnabled)
    {
        this.bemMovelEnabled = bemMovelEnabled;
    }

    public boolean isBemItangivelEnabled()
    {
        return bemItangivelEnabled;
    }

    public void setBemItangivelEnabled(boolean bemItangivelEnabled)
    {
        this.bemItangivelEnabled = bemItangivelEnabled;
    }

    public UserTransaction getUserTransaction() {
        return userTransaction;
    }

    public void setUserTransaction(UserTransaction userTransaction) {
        this.userTransaction = userTransaction;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getDocPath() {
        return docPath;
    }

    public void setDocPath(String docPath) {
        this.docPath = docPath;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public UploadedFile getUploadedImageFile() {
        return uploadedImageFile;
    }

    public void setUploadedImageFile(UploadedFile uploadedImageFile) {
        this.uploadedImageFile = uploadedImageFile;
    }

    public UploadedFile getUploadedDocFile() {
        return uploadedDocFile;
    }

    public void setUploadedDocFile(UploadedFile uploadedDocFile) {
        this.uploadedDocFile = uploadedDocFile;
    }

    public String getDetalhesMovel() {
        return detalhesMovel;
    }

    public void setDetalhesMovel(String detalhesMovel) {
        this.detalhesMovel = detalhesMovel;
    }

    public int getTaxaDepreciacaoMes() {
        return taxaDepreciacaoMes;
    }

    public void setTaxaDepreciacaoMes(int taxaDepreciacaoMes) {
        this.taxaDepreciacaoMes = taxaDepreciacaoMes;
    }

    public int getFkPtEstadoConservacaoMovel() {
        return fkPtEstadoConservacaoMovel;
    }

    public void setFkPtEstadoConservacaoMovel(int fkPtEstadoConservacaoMovel) {
        this.fkPtEstadoConservacaoMovel = fkPtEstadoConservacaoMovel;
    }

    public int getFkPtMarca() {
        return fkPtMarca;
    }

    public void setFkPtMarca(int fkPtMarca) {
        this.fkPtMarca = fkPtMarca;
    }

    public int getFkPtModelo() {
        return fkPtModelo;
    }

    public void setFkPtModelo(int fkPtModelo) {
        this.fkPtModelo = fkPtModelo;
    }

    public double getValorActualMovel() {
        return valorActualMovel;
    }

    public void setValorActualMovel(double valorActualMovel) {
        this.valorActualMovel = valorActualMovel;
    }

    public List<PtEstadoConservacao> getListPtEstadoConservacaoMovel() {
        return listPtEstadoConservacaoMovel;
    }

    public void setListPtEstadoConservacaoMovel(List<PtEstadoConservacao> listPtEstadoConservacaoMovel) {
        this.listPtEstadoConservacaoMovel = listPtEstadoConservacaoMovel;
    }

    public List<PtMarca> getListPtMarca() {
        return listPtMarca;
    }

    public void setListPtMarca(List<PtMarca> listPtMarca) {
        this.listPtMarca = listPtMarca;
    }

    public List<PtModelo> getListPtModelo() {
        return listPtModelo;
    }

    public void setListPtModelo(List<PtModelo> listPtModelo) {
        this.listPtModelo = listPtModelo;
    }

    public String getDescricaoCartorio() {
        return descricaoCartorio;
    }

    public void setDescricaoCartorio(String descricaoCartorio) {
        this.descricaoCartorio = descricaoCartorio;
    }

    public String getFkEstruturaFisica() {
        return fkEstruturaFisica;
    }

    public void setFkEstruturaFisica(String fkEstruturaFisica) {
        this.fkEstruturaFisica = fkEstruturaFisica;
    }

    public String getDetalhesImovel() {
        return detalhesImovel;
    }

    public void setDetalhesImovel(String detalhesImovel) {
        this.detalhesImovel = detalhesImovel;
    }

    public int getTaxaMes() {
        return taxaMes;
    }

    public void setTaxaMes(int taxaMes) {
        this.taxaMes = taxaMes;
    }

    public int getFkPtEstadoConservacaoImovel() {
        return fkPtEstadoConservacaoImovel;
    }

    public void setFkPtEstadoConservacaoImovel(int fkPtEstadoConservacaoImovel) {
        this.fkPtEstadoConservacaoImovel = fkPtEstadoConservacaoImovel;
    }

    public Date getDataConstrucao() {
        return dataConstrucao;
    }

    public void setDataConstrucao(Date dataConstrucao) {
        this.dataConstrucao = dataConstrucao;
    }

    public double getValorActualImovel() {
        return valorActualImovel;
    }

    public void setValorActualImovel(double valorActualImovel) {
        this.valorActualImovel = valorActualImovel;
    }

    public List<EstruturaFisica> getListEstruturaFisica() {
        return listEstruturaFisica;
    }

    public void setListEstruturaFisica(List<EstruturaFisica> listEstruturaFisica) {
        this.listEstruturaFisica = listEstruturaFisica;
    }

    public List<PtEstadoConservacao> getListPtEstadoConservacaoImovel() {
        return listPtEstadoConservacaoImovel;
    }

    public void setListPtEstadoConservacaoImovel(List<PtEstadoConservacao> listPtEstadoConservacaoImovel) {
        this.listPtEstadoConservacaoImovel = listPtEstadoConservacaoImovel;
    }

    public String getDescricaoDetalhada() {
        return descricaoDetalhada;
    }

    public void setDescricaoDetalhada(String descricaoDetalhada) {
        this.descricaoDetalhada = descricaoDetalhada;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isImgFileEnabled() {
        return imgFileEnabled;
    }

    public void setImgFileEnabled(boolean imgFileEnabled) {
        this.imgFileEnabled = imgFileEnabled;
    }

    public boolean isDocFileEnabled() {
        return docFileEnabled;
    }

    public void setDocFileEnabled(boolean docFileEnabled) {
        this.docFileEnabled = docFileEnabled;
    }
    
    
    
}
