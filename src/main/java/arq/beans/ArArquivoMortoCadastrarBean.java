/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arq.beans;

import estrutura.ejbs.cache.EstruturaFisicaCache;
import estrutura.ejbs.cache.EstruturaLogicaCache;
import estrutura.ejbs.cache.EstruturaLogicaFisicaCache;
import estrutura.beans.EstruturaLogicaFisicaCarregarBean;
import ejbs.entities.ArAnoCurricular;
import ejbs.entities.ArAnoLectivo;
import ejbs.entities.ArArquivoMorto;
import ejbs.entities.ArCadeira;
import ejbs.entities.ArConfiguracoes;
import ejbs.entities.ArCurso;
import ejbs.entities.ArDocente;
import ejbs.entities.EstruturaFisica;
import ejbs.entities.EstruturaLogica;
import ejbs.entities.EstruturaLogicaFisica;
import ejbs.entities.ArFaculdade;
import ejbs.entities.ArPeriodo;
import ejbs.entities.ArSemestre;
import ejbs.entities.ArTipoDocumento;
import ejbs.entities.ArTurma;
import ejbs.facades.ArAnoCurricularFacade;
import ejbs.facades.ArAnoLectivoFacade;
import ejbs.facades.ArArquivoMortoFacade;
import ejbs.facades.ArCadeiraFacade;
import ejbs.facades.ArConfiguracoesFacade;
import ejbs.facades.ArCursoFacade;
import ejbs.facades.ArDocenteFacade;
import ejbs.facades.ArFaculdadeFacade;
import ejbs.facades.ArPeriodoFacade;
import ejbs.facades.ArSemestreFacade;
import ejbs.facades.ArTipoDocumentoFacade;
import ejbs.facades.ArTurmaFacade;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;


/**
 *
 * @author david-salgueiro
 */
@Named(value = "arArquivoMortoCadastrarBean")
@SessionScoped
public class ArArquivoMortoCadastrarBean implements Serializable {

    @EJB
    private ArTurmaFacade turmaFacade;

    @EJB
    private ArPeriodoFacade periodoFacade;

    @EJB
    private ArDocenteFacade docenteFacade;

    @EJB
    private ArFaculdadeFacade faculdadeFacade;

    @EJB
    private ArCursoFacade cursoFacade;

    @EJB
    private ArCadeiraFacade cadeiraFacade;

    @EJB
    private ArAnoLectivoFacade anoLectivoFacade;

    @EJB
    private ArAnoCurricularFacade anoCurricularFacade;

    
    @EJB
    private ArTipoDocumentoFacade tipoDocumentoFacade;
    
    @EJB
    private ArArquivoMortoFacade arquivoFacade;
    
    @EJB
    private ArConfiguracoesFacade configuracoesFacade;
    
    @EJB
    private ArSemestreFacade semestreFacade;
    
    @Inject
    private EstruturaLogicaFisicaCache estruturaLogicaFisicaCache;
    
    @Inject
    private EstruturaFisicaCache estruturaFisicaCache;
    
    @Inject
    private EstruturaLogicaCache estruturaLogicaCache;
    
    @Inject
    private EstruturaLogicaFisicaCarregarBean estruturaLogicaFisicaCarregarBean;
    
    private List<ArTipoDocumento> listaTipoDocumento;
    private List<ArArquivoMorto> listaArquivoMorto;
    private List<ArArquivoMortoCadastrarBean.EFisicaNode> listaEstruturaFisica;
    private List<ArArquivoMortoCadastrarBean.ELogicaNode> listaEstruturaLogica;
    private int limitEstruturaFisicaLength,limitEstruturaLogicaLength;
    private String estruturaFisicaSOMLabel="eFisicaList",estruturaLogicaSOMLabel="eLogicaList";
    private UploadedFile ficheiroOriginal;
    Path filePath;
    private ArArquivoMorto arquivo;
    private String pasta = Paths.get(System.getProperty("jboss.server.data.dir"), "arquivoMorto").toString();
    private Path pasta2 = Paths.get(System.getProperty("jboss.server.data.dir"), "arquivoMorto");
    ArConfiguracoes configuracoes;
    private StreamedContent file2;

    private boolean btnSalvar = true;
    private boolean btnFormularioCadastro;
    private boolean tabListar = true;
    private boolean estado;
    
    private int codigoTipoDocumento;
    private int codigoArquivoSelecionado;
    private int codigoFaculdade;
    private int codigoCurso;
    private int codigoCadeira;
    private int codigoDocente;
    private int codigoAnoLectivo;
    private int codigoAnoCurricular;
    private int codigoTurma;
    private int codigoPeriodo;
    private int codigoSemestre;
    
    private int docente;
    private int anoLetivo;
    private int cadeira;
    
    public ArArquivoMortoCadastrarBean()
    {
        
    }
    
    @PostConstruct
    public void init()
    {
        
        limitEstruturaFisicaLength = estruturaFisicaCache.getEstruturaFisicaLevel();
        
        limitEstruturaLogicaLength = estruturaLogicaCache.getEstruturaLogicaLevel();
        inicializaListas();
         
        listaTipoDocumento = tipoDocumentoFacade.findAll();
        listaArquivoMorto = arquivoFacade.findAll();
        initConfiguracao();
        arquivo = new ArArquivoMorto();
        docente = 0;
        anoLetivo = 0;
        cadeira = 0;
        codigoSemestre = 0;
    }
    
    
    public void initObjecto()
    {
         arquivo = new ArArquivoMorto();
    }
    
    public void initConfiguracao()
    {
        configuracoes = configuracoesFacade.find();
        codigoTipoDocumento = configuracoes.getFkTipoDocumento().getPkTipoDocumento();
        codigoAnoLectivo = this.configuracoes.getFkAnoLectivo().getPkAnoLectivo();
        codigoAnoCurricular = this.configuracoes.getFkAnoCurricular().getPkAnoCurricular();
        codigoTurma = this.configuracoes.getFkTurma().getPkTurma();
        codigoPeriodo = this.configuracoes.getFkPeriodo().getPkPeriodo();
    }
    
    public void inicializaListas()
    {
        listaEstruturaFisica = new ArrayList<>();
        listaEstruturaFisica.add(new EFisicaNode(estruturaFisicaCache.getListaPaisEstruturaFisicas()));
        
        for(int i=1;i<limitEstruturaFisicaLength;i++)
            listaEstruturaFisica.add(new EFisicaNode());
        
        actualizaEstruturaFisicaByNivel(1);
        
        listaEstruturaLogica = new ArrayList<>();
        listaEstruturaLogica.add(new ELogicaNode(estruturaLogicaCache.getListaPaisEstruturaLogica()));
        
        for(int i=1;i<limitEstruturaLogicaLength;i++)
            listaEstruturaLogica.add(new ELogicaNode());
        
        actualizaEstruturaLogicaByNivel(1);
    }
    
    //Os Níveis são contados de 0 acima
    //A função abaixo só deve ser usada para actualizar elementos do nivel 1 acima
    public void actualizaEstruturaFisicaByNivel(int nivel)
    {
       for(int i=nivel; i < limitEstruturaFisicaLength; i++)
       {
        listaEstruturaFisica.get(i).setLista(estruturaFisicaCache.getListaSonsByPkEstruturaFisica(listaEstruturaFisica.get(i - 1).getSelectedFk()));
        listaEstruturaFisica.get(i).setSelectedFk(listaEstruturaFisica.get(i).getLista().get(0).getPkEstruturaFisica());
       }
    }
    
    public void eFisicaChangeListener(int index)
    {
        actualizaEstruturaFisicaByNivel(index+1);
        
    }
    
    public void actualizaEstruturaLogicaByNivel(int nivel)
    {
       for(int i=nivel; i < limitEstruturaLogicaLength; i++)
       {
        listaEstruturaLogica.get(i).setLista(estruturaLogicaCache.getListaSonsByPkEstruturaLogica(listaEstruturaLogica.get(i - 1).getSelectedFk()));
        listaEstruturaLogica.get(i).setSelectedFk(listaEstruturaLogica.get(i).getLista().get(0).getPkEstruturaLogica());
       }
    }
    
    public void eLogicaChangeListener(int index)
    {
        actualizaEstruturaLogicaByNivel(index+1);
    }
    
    public void gravar()
    {
        try
        {
            
            EstruturaLogicaFisica reg = estruturaLogicaFisicaCarregarBean.saveFroHora();
            System.out.println("registar() ---: " + reg);
            this.arquivo.setFkTipoDocumento(new ArTipoDocumento(codigoTipoDocumento));
            this.arquivo.setCaminho(filePath.getFileName().toString());
            this.arquivo.setDataRegistro(new Date());
            this.arquivo.setEstado(Boolean.TRUE);
            this.arquivo.setFkAnoCurricular(new ArAnoCurricular(this.codigoAnoCurricular));
            this.arquivo.setFkAnoLectivo(new ArAnoLectivo(this.codigoAnoLectivo));
            this.arquivo.setFkCadeira(new ArCadeira(this.codigoCadeira));
            this.arquivo.setFkDocente(new ArDocente(this.codigoDocente));
            this.arquivo.setFkPeriodo(new ArPeriodo(this.codigoPeriodo));
            this.arquivo.setFkTurma(new ArTurma(this.codigoTurma));
            this.arquivo.setFkSemestre(new ArSemestre(this.codigoSemestre));
            this.arquivo.setFkEstruturaLogicaFisica(reg);

            System.err.println("gravar()-- ficheiro: " + ficheiroOriginal.getFileName());
            arquivoFacade.create(arquivo);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "Arquivo registrado com Sucesso!"));
            init();
            initObjecto();
           
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        
    }
             
    public void eliminar(ArArquivoMorto arquivo)
    {
        try
        {
            ArArquivoMorto doc = arquivoFacade.find(arquivo.getPkArquivoMorto());
            arquivoFacade.remove(doc);
            System.err.println("Caminho do Documento eliminado: " + doc.getCaminho());
            File file2 = new File(pasta+doc.getCaminho());
            Files.deleteIfExists(file2.toPath());
            init();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Arquivo excluídos com sucesso!", "Arquivo excluídos com sucesso!"));

        } 
        catch (Exception exception)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Impossível eliminar Documento, Verifique se está sendo utilizado em algum registro, ou entre em contacto com o Administrador de Sistema.", "Impossível eliminar Documento, Verifique se está sendo utilizado em algum registro, ou entre em contacto com o Administrador de Sistema."));
        }

    }
      
    
    public List<ArArquivoMorto> listarDocumento()
    {
        return arquivoFacade.findAllArquivoMortoOrderByData();
    }
    
    
    public List<ArFaculdade> listarFaculdade()
    {
        return faculdadeFacade.findAll();
    }
    
    
    public List<ArCurso> listarCurso(int codigo)
    {
        return cursoFacade.findCursoByFaculdade(this.codigoFaculdade);
    }
    
    public List<ArCadeira> listarCadeira(int codigo)
    {
        return cadeiraFacade.findCadeiraByCurso(codigo);
    }
    
    
    public List<ArCadeira> listarDisciplina(int codigo)
    {
        return cadeiraFacade.findCadeiraByDocente(codigo);
    }
    
    public List<ArAnoLectivo> listarAnoLectivo()
    {
        return anoLectivoFacade.findAll();
    }
    
    public List<ArAnoCurricular> listarAnoCurricular()
    {
        return anoCurricularFacade.findAll();
    }
    
    public List<ArDocente> listarDocente()
    {
        return docenteFacade.findAll();
    }
    
    public List<ArPeriodo> listarPeriodo()
    {
        return periodoFacade.findAll();
    }
    
    public List<ArTurma> listarTurma()
    {
        return turmaFacade.findAll();
    }
    
    public List<ArSemestre> listarSemestre()
    {
        return semestreFacade.findAll();
    }
    
    public void uploadListener(FileUploadEvent evento)
    {
        this.ficheiroOriginal = evento.getFile();
        carregaDoc();
    }
    
 
    public void carregaDoc()
    {
        try
        {
            String nomeArquivo = ficheiroOriginal.getFileName(); //Nome do arquivo enviado
           
            try (InputStream fos =  ficheiroOriginal.getInputstream())
            {
                Path folder = Paths.get(System.getProperty("jboss.server.data.dir"), "arquivoMorto");
                System.err.println("folder: " + folder.toString());
                if (!folder.toFile().exists())
                {
                    if (!folder.toFile().mkdirs())
                    {
                        folder = Paths.get(System.getProperty("jboss.server.data.dir")); 
                    }
                
                }
                
//                filePath = Files.createTempFile(folder, nomeArquivo+"-", ".pdf");
                filePath = Files.createTempFile(folder, nomeArquivo+"-", "."+extensao(nomeArquivo));
                Files.copy(fos, filePath, StandardCopyOption.REPLACE_EXISTING);
                System.err.println("filePath: " + filePath.getFileName().toString());
                System.out.println("ENTROU");
                System.out.println("EXTENSAO: " + "."+extensao(nomeArquivo));
                
            }
           
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.err.println(e);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Documento não anexado", "Documento não anexado"));
            
        }
    }
    
    
   public String extensao(String strNome)
   {
       String novo = strNome.replace(".", "-");
       String[] arrayStr2 = novo.split("-");
       return arrayStr2[arrayStr2.length-1];
   }

    
    public void fileDownloadView(String nomeArquivo)
    {
        try
        {
        
            Path folder = Paths.get(System.getProperty("jboss.server.data.dir"), "arquivoMorto");
            System.err.println("FileDownloadView: " + folder.toString()+"/"+nomeArquivo);

            FacesContext context = FacesContext.getCurrentInstance();
            String nomeRelatorio = nomeArquivo;
            HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
            ServletContext sc = (ServletContext) context.getExternalContext().getContext();

            response.setContentType("application/xml");
            response.addHeader("Content-disposition", "attachment; filename =\"" + nomeRelatorio + "\"");
            OutputStream output = response.getOutputStream();

            File ficheiro = new File(folder + "/" + nomeArquivo);
            output.write(Files.readAllBytes(ficheiro.toPath()));

            response.flushBuffer();
            output.flush();
            
            ServletOutputStream responseStream = response.getOutputStream();
            responseStream.flush();
            responseStream.close();

            FacesContext.getCurrentInstance().renderResponse();
            FacesContext.getCurrentInstance().responseComplete();               


        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }
    
        
    public List<ArArquivoMorto> findArquivo()
    {
        return arquivoFacade.findAllArquivoMortoByPk(this.codigoArquivoSelecionado);
    }
    
//    List<ArquivoMorto>
    public void findArquivoByDocenteAnoLetivoCadeira()
    {
        System.err.println("this.docente: " + this.docente);
        System.err.println("this.anoLetivo: " + this.anoLetivo);
        System.err.println("this.cadeira: " + this.cadeira);
        listaArquivoMorto = arquivoFacade.findArquivoByDocenteAnoLetivoCadeira(this.docente, this.anoLetivo, this.cadeira);
    }
    
    public void selecionar(ActionEvent event) {
        this.codigoArquivoSelecionado = Integer.parseInt(event.getComponent().getAttributes().get("pk_arquivo").toString());
        System.err.println("pk_arquivo: " + arquivoFacade.find(codigoArquivoSelecionado));
        System.err.println("pasta: " + pasta);
    }
    
    public void selecionarArquivo(int codigo) {
        this.codigoArquivoSelecionado = codigo;
        System.err.println("pk_arquivo: " + arquivoFacade.find(codigoArquivoSelecionado));
        System.err.println("pasta: " + pasta);
    }
    
    
    
    public String getestruturaFisicaSOMLabelByLevel(int nivel)
    {
        String combinedSOM = "";
        for(int i=nivel+1; i < limitEstruturaFisicaLength; i++)
        {
           combinedSOM+=(estruturaFisicaSOMLabel+i)+" ";
        }
        return combinedSOM;
    }
    
    public String getestruturaLogicaSOMLabelByLevel(int nivel)
    {
        String combinedSOM = "";
        for(int i=nivel+1; i < limitEstruturaLogicaLength; i++)
        {
           combinedSOM+=(estruturaLogicaSOMLabel+i)+" ";
        }
        return combinedSOM;
    }
    //GETTERS AND SETTERS----------------------------------------------------------------
    public EstruturaLogicaFisicaCache getEstruturaLogicaFisicaCache()
    {
        return estruturaLogicaFisicaCache;
    }

    public void setEstruturaLogicaFisicaCache(EstruturaLogicaFisicaCache estruturaLogicaFisicaCache)
    {
        this.estruturaLogicaFisicaCache = estruturaLogicaFisicaCache;
    }

    public EstruturaFisicaCache getEstruturaFisicaCache()
    {
        return estruturaFisicaCache;
    }

    public void setEstruturaFisicaCache(EstruturaFisicaCache estruturaFisicaCache)
    {
        this.estruturaFisicaCache = estruturaFisicaCache;
    }

    public EstruturaLogicaCache getEstruturaLogicaCache()
    {
        return estruturaLogicaCache;
    }

    public void setEstruturaLogicaCache(EstruturaLogicaCache estruturaLogicaCache)
    {
        this.estruturaLogicaCache = estruturaLogicaCache;
    }

    public List<EFisicaNode> getListaEstruturaFisica()
    {
        return listaEstruturaFisica;
    }

    public void setListaEstruturaFisica(List<EFisicaNode> listaEstruturaFisica)
    {
        this.listaEstruturaFisica = listaEstruturaFisica;
    }

    public List<ELogicaNode> getListaEstruturaLogica()
    {
        return listaEstruturaLogica;
    }

    public void setListaEstruturaLogica(List<ELogicaNode> listaEstruturaLogica)
    {
        this.listaEstruturaLogica = listaEstruturaLogica;
    }
    
    public int getLimitEstruturaFisicaLength()
    {
        return limitEstruturaFisicaLength;
    }

    public void setLimitEstruturaFisicaLength(int limitEstruturaFisicaLength)
    {
        this.limitEstruturaFisicaLength = limitEstruturaFisicaLength;
    }

    public int getLimitEstruturaLogicaLength()
    {
        return limitEstruturaLogicaLength;
    }

    public void setLimitEstruturaLogicaLength(int limitEstruturaLogicaLength)
    {
        this.limitEstruturaLogicaLength = limitEstruturaLogicaLength;
    }

    public String getEstruturaFisicaSOMLabel()
    {
        return estruturaFisicaSOMLabel;
    }

    public void setEstruturaFisicaSOMLabel(String estruturaFisicaSOMLabel)
    {
        this.estruturaFisicaSOMLabel = estruturaFisicaSOMLabel;
    }

    public String getEstruturaLogicaSOMLabel()
    {
        return estruturaLogicaSOMLabel;
    }

    public void setEstruturaLogicaSOMLabel(String estruturaLogicaSOMLabel)
    {
        this.estruturaLogicaSOMLabel = estruturaLogicaSOMLabel;
    }

    
    public void initTipoDoc()
    {
        listaTipoDocumento = tipoDocumentoFacade.findAll();
    }

    public int getCodigoTipoDocumento() {
        return codigoTipoDocumento;
    }

    public void setCodigoTipoDocumento(int codigoTipoDocumento) {
        this.codigoTipoDocumento = codigoTipoDocumento;
    }

    public ArArquivoMorto getArquivo() {
        return arquivo;
    }

    public void setArquivo(ArArquivoMorto arquivo) {
        this.arquivo = arquivo;
    }

    public int getCodigoArquivoSelecionado() {
        return codigoArquivoSelecionado;
    }

    public void setCodigoArquivoSelecionado(int codigoArquivoSelecionado) {
        this.codigoArquivoSelecionado = codigoArquivoSelecionado;
    }

    public String getPasta() {
        return pasta;
    }

    public void setPasta(String pasta) {
        this.pasta = pasta;
    }

    public StreamedContent getFile2() {
        return file2;
    }

    public void setFile2(StreamedContent file2) {
        this.file2 = file2;
    }

    public int getCodigoFaculdade() {
        return codigoFaculdade;
    }

    public void setCodigoFaculdade(int codigoFaculdade) {
        this.codigoFaculdade = codigoFaculdade;
    }

    public int getCodigoCurso() {
        return codigoCurso;
    }

    public void setCodigoCurso(int codigoCurso) {
        this.codigoCurso = codigoCurso;
    }

    public int getCodigoCadeira() {
        return codigoCadeira;
    }

    public void setCodigoCadeira(int codigoCadeira) {
        this.codigoCadeira = codigoCadeira;
    }

    public int getCodigoDocente() {
        return codigoDocente;
    }

    public void setCodigoDocente(int codigoDocente) {
        this.codigoDocente = codigoDocente;
    }

    public int getCodigoAnoLectivo() {
        return codigoAnoLectivo;
    }

    public void setCodigoAnoLectivo(int codigoAnoLectivo) {
        this.codigoAnoLectivo = codigoAnoLectivo;
    }

    public int getCodigoAnoCurricular() {
        return codigoAnoCurricular;
    }

    public void setCodigoAnoCurricular(int codigoAnoCurricular) {
        this.codigoAnoCurricular = codigoAnoCurricular;
    }

    public int getCodigoTurma() {
        return codigoTurma;
    }

    public void setCodigoTurma(int codigoTurma) {
        this.codigoTurma = codigoTurma;
    }

    public int getCodigoPeriodo() {
        return codigoPeriodo;
    }

    public void setCodigoPeriodo(int codigoPeriodo) {
        this.codigoPeriodo = codigoPeriodo;
    }

    public int getDocente() {
        return docente;
    }

    public void setDocente(int docente) {
        this.docente = docente;
    }

    public int getAnoLetivo() {
        return anoLetivo;
    }

    public void setAnoLetivo(int anoLetivo) {
        this.anoLetivo = anoLetivo;
    }

    public int getCadeira() {
        return cadeira;
    }

    public void setCadeira(int cadeira) {
        this.cadeira = cadeira;
    }

    public List<ArArquivoMorto> getListaArquivoMorto() {
        return listaArquivoMorto;
    }

    public void setListaArquivoMorto(List<ArArquivoMorto> listaArquivoMorto) {
        this.listaArquivoMorto = listaArquivoMorto;
    }

    public int getCodigoSemestre()
    {
        return codigoSemestre;
    }

    public void setCodigoSemestre(int codigoSemestre)
    {
        this.codigoSemestre = codigoSemestre;
    }
    
    
        //------INNER CLASSES--------------------------------------------------

    public static class EFisicaNode
    {
        private List<EstruturaFisica> lista;
        private String selectedFk;

        public EFisicaNode(List<EstruturaFisica> lista)
        {
            this.lista = lista;
            this.selectedFk = lista.get(0).getPkEstruturaFisica();
        }
        
        public EFisicaNode()
        {
            this.lista = new ArrayList<>();
        }

        public List<EstruturaFisica> getLista()
        {
            return lista;
        }

        public void setLista(List<EstruturaFisica> lista)
        {
            this.lista = lista;
        }

        public String getSelectedFk()
        {
            return selectedFk;
        }

        public void setSelectedFk(String selectedFk)
        {
            this.selectedFk = selectedFk;
        }
    }
    
    public static class ELogicaNode
    {
        private List<EstruturaLogica> lista;
        private String selectedFk;

        public ELogicaNode(List<EstruturaLogica> lista)
        {
            this.lista = lista;
            this.selectedFk = lista.get(0).getPkEstruturaLogica();
        }
        
        public ELogicaNode()
        {
            this.lista = new ArrayList<>();
        }

        public List<EstruturaLogica> getLista()
        {
            return lista;
        }

        public void setLista(List<EstruturaLogica> lista)
        {
            this.lista = lista;
        }

        public String getSelectedFk()
        {
            return selectedFk;
        }

        public void setSelectedFk(String selectedFk)
        {
            this.selectedFk = selectedFk;
        }
    }
    
     public UploadedFile getFicheiroOriginal() {
        return ficheiroOriginal;
    }

    public void setFicheiroOriginal(UploadedFile ficheiroOriginal) {
        this.ficheiroOriginal = ficheiroOriginal;
    }

    public boolean isBtnSalvar() {
        return btnSalvar;
    }

    public void setBtnSalvar(boolean btnSalvar) {
        this.btnSalvar = btnSalvar;
    }

    public boolean isBtnFormularioCadastro() {
        return btnFormularioCadastro;
    }

    public void setBtnFormularioCadastro(boolean btnFormularioCadastro) {
        this.btnFormularioCadastro = btnFormularioCadastro;
    }

    public boolean isTabListar() {
        return tabListar;
    }

    public void setTabListar(boolean tabListar) {
        this.tabListar = tabListar;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    
    public List<ArTipoDocumento> getListaTipoDocumento() {
        return listaTipoDocumento;
    }

    public void setListaTipoDocumento(List<ArTipoDocumento> listaTipoDocumento) {
        this.listaTipoDocumento = listaTipoDocumento;
    }
    
    
    //Metodos para formulário  void
    public String voltar()
    {

        this.btnSalvar = true;
        this.btnFormularioCadastro = false;
        this.tabListar = false;
        this.estado = false;
        
        return "arq_home?faces-redirect=true";

    }

    public void novo()
    {

        this.btnSalvar = false;
        this.btnFormularioCadastro = true;
        this.tabListar = false;
        this.estado = false;

    }

    public void btnListar()
    {

        this.tabListar = true;
        this.btnFormularioCadastro = false;
        this.btnSalvar = true;
        this.estado = false;
    }

    public void btnEditar()
    {

        this.estado = true;
        this.tabListar = false;

    }

    
}
