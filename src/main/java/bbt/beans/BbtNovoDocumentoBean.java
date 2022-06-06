/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bbt.beans;

import localidade.ejbs.cache.LocLocalidadeCache;
import ejbs.entities.BbtAutoridade;
import ejbs.entities.BbtCdu;
import ejbs.entities.BbtColecao;
import ejbs.entities.BbtDocumento;
import ejbs.entities.BbtDocumentoBbtAutoridade;
import ejbs.entities.BbtDocumentoBbtCdu;
import ejbs.entities.BbtEdicao;
import ejbs.entities.BbtEditora;
import ejbs.entities.BbtTipoDocumento;
import ejbs.entities.GrlLingua;
import ejbs.entities.LocLocalidade;
import ejbs.facades.BbtAutoridadeFacade;
import ejbs.facades.BbtCduFacade;
import ejbs.facades.BbtColecaoFacade;
import ejbs.facades.BbtDocumentoBbtAutoridadeFacade;
import ejbs.facades.BbtDocumentoBbtCduFacade;
import ejbs.facades.BbtDocumentoFacade;
import ejbs.facades.BbtEdicaoFacade;
import ejbs.facades.BbtEditoraFacade;
import ejbs.facades.BbtTipoDocumentoFacade;
import ejbs.facades.GrlLinguaFacade;
import ejbs.facades.LocLocalidadeFacade;

import java.io.Serializable;
import java.util.ArrayList;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author herman_diya-hd
 */
@Named(value = "bbtNovoDocumentoBean")
@ViewScoped
public class BbtNovoDocumentoBean implements Serializable
{

    @EJB
    private LocLocalidadeFacade locLocalidadeFacade;

    @EJB
    private BbtDocumentoBbtCduFacade bbtDocumentoBbtCduFacade;

    @EJB
    private BbtDocumentoBbtAutoridadeFacade bbtDocumentoBbtAutoridadeFacade;

    @EJB
    private BbtTipoDocumentoFacade bbtTipoDocumentoFacade;

    @EJB
    private BbtDocumentoFacade bbtDocumentoFacade;

    @Inject
    LocLocalidadeCache localidadeCache;
    
    

    @EJB
    private BbtAutoridadeFacade bbtAutoridadeFacade;

    @EJB
    private BbtEdicaoFacade bbtEdicaoFacade;

    @EJB
    private BbtCduFacade bbtCduFacade;

    @EJB
    private BbtColecaoFacade bbtColecaoFacade;

    @EJB
    private BbtEditoraFacade bbtEditoraFacade;

    @EJB
    private LocLocalidadeFacade grlLocalidadeFacade;

    @EJB
    private GrlLinguaFacade linguaFacade;

    private String depositoLegal, tipoForm, titulo, isbn, subTitulo, notas, pkProvincia, pkPais, depLegal;
    private Integer pkGrlLingua, pkBbtEditora, pkBbtEdicao,
            pkBbtColecao, pkLingua, pkAutor, numPaginas, ano;

    private Float altura, largura;

    private List<BbtEditora> editoras;
    private List<BbtEdicao> edicoes;
    private List<GrlLingua> linguas;
    private List<LocLocalidade> paises, provincias;
    private List<BbtAutoridade> autoridades;
    private List<BbtCdu> cdus;
    private List<BbtCdu> cdusSelecionados;
    private List<BbtAutoridade> autoridadesSelecionadas;
    private List<BbtTipoDocumento> tiposDocumentos;
    private List<BbtColecao> colecoes;

    private BbtDocumento documento;
    private BbtColecao colecao; 
    private BbtEditora editora;
    private BbtCdu cdu;
    private BbtDocumentoBbtCdu bbtDocumentoBbtCdu;
    private BbtDocumentoBbtAutoridade documentoAutoridade;
    private LocLocalidade provincia;

    /**
     * Creates a new instance of BbtNovoDefaultBean
     */
    public BbtNovoDocumentoBean()
    {

    }

    @PostConstruct
    public void init()
    {
        //System.err.println("Inicializando");

        autoridadesSelecionadas = new ArrayList<>();
        cdusSelecionados = new ArrayList<>();
        documento = new BbtDocumento();
        editoras = bbtEditoraFacade.findAll();
        paises = locLocalidadeFacade.findAll();
        // provincias = localidadeCache.findAllOrderedByNome(paises.get(0).getPkLocLocalidade());
        autoridades = bbtAutoridadeFacade.findAll();
        edicoes = bbtEdicaoFacade.findAll();
        colecoes = bbtColecaoFacade.findAll();
        cdus = bbtCduFacade.findAll();
        linguas = linguaFacade.findAll();

        // System.err.println("Finalizando");
    }

    //Business methods
    public void updateProvinciaLista()
    {

//        this.provincias = locLocalidadeFacade.findAllByPkLocalidade(pkPais);

    }

    public String cduCompleto(BbtCdu cdu)
    {
        return cdu.getPkBbtCdu() + "  " + cdu.getDesignacao();
    }

    public String getCabecalho()
    {

        String nome = "", apelido = "";
        if (autoridadesSelecionadas.isEmpty())
        {
            return "";
        }
        //Pega o primeiro autor selecionado 
        BbtAutoridade autor = autoridadesSelecionadas.get(0);

//        if (autor.getFkBbtTipoAutoridade().getPkBbtTipoAutoridade() == 1)
//        {
//            String partesNome[] = autor.getDesignacao().split(" ");
//            int tam = partesNome.length;
//            apelido = partesNome[tam - 1].toUpperCase() + ",";
//
//            for (int i = 0; i < tam - 1; i++)
//            {
//                nome += partesNome[i] + " ";
//            }
//
//            return apelido + nome;
//        }
        //Se o autor for uma pessoa jurídica
        return autor.getDesignacao();
    }

    /**
     * Retorna no máximo 3 autores do referido documento
     *
     * @return
     */
    public String mencaoResponsabilidade()
    {
        String autores = "/";
        int i = 0;
        for (BbtAutoridade autoridade : autoridadesSelecionadas)
        {
            autores += autoridade.getDesignacao() + ",";
        }

        return autores;
    }

    public String cdusString()
    {
        String cdusStr = "  ";
        int i = 0;
        for (BbtCdu bbtCdu : cdusSelecionados)
        {
            cdusStr += bbtCdu.getPkBbtCdu() + "/";
        }

        return cdusStr;
    }

    public void limpar()
    {
        System.err.println("0:ProdutoRegistarEntradaBean.limpar()");
    }

    public void mostrarAutores()
    {
//        BbtDocumento doc = bbtDocumentoFacade.find(4);
//        List<BbtAutoridade> lista = doc.getBbtAutoridadeList();
//        System.err.println("Título: " + doc.getTitulo());
//        System.err.println("PK: " + doc.getPkBbtDocumento());
//        int i = 1;
//        System.err.println("Autores");
//        System.err.println("Qauntidade autores:" + lista.size());
//        for (BbtAutoridade bbtAutoridade : lista)
//        {
//
//            System.err.println("Nº:" + i);
//            System.err.println("Designação: " + bbtAutoridade.getDesignacao());
//            System.err.println("Tipo autoridade: " + bbtAutoridade.getFkBbtTipoAutoridade().getDesignacao());
//
//            System.err.println(" ------------------------- ");
//        }
    }

    public void salvar()
    {
        // mostrarAutores();
        System.err.println("2º autor seleccionado: "
                + autoridadesSelecionadas.get(1).getDesignacao());

        System.err.println("Qtd de itens selecionados: " + autoridadesSelecionadas.size());
        documento = new BbtDocumento(bbtDocumentoFacade.getNextPk());

        documento.setFkBbtTipoDocumento(bbtTipoDocumentoFacade.find(1)); //Tipo documento padrão
        documento.setTitulo(titulo);
        documento.setIsbn(isbn);
        //  documento.setAltura(altura);
        //documento.setLargura(largura);
        documento.setNumPaginas(numPaginas);
        documento.setAno(ano);
        documento.setNotas(notas);
        //   documento.setFkLocLocalidade(provincia);
        documento.setFkBbtEditora(editora);
        documento.setFkBbtColecao(bbtColecaoFacade.find(pkBbtColecao));
        documento.setDepositoLegal(depositoLegal);
        //   documento.setFkGrlLingua(linguaFacade.find(pkLingua));

//        System.err.println("PKBbtDocumentoBbtAutoridade gerado: " + 
//                bbtDocumentoBbtAutoridadeFacade.getNextPk());
//        documentoAutoridade = 
//                new BbtDocumentoBbtAutoridade(5);
        System.err.println("Tentando gravar");

        bbtDocumentoFacade.create(documento);
        System.err.println("Documento Gravado com sucesso");

        for (BbtAutoridade bbtAutoridade : autoridadesSelecionadas)
        {
            documentoAutoridade
                    = new BbtDocumentoBbtAutoridade(bbtDocumentoBbtAutoridadeFacade.getNextPk());
//            System.err.println("Gerado. Chave: "
//                    + documentoAutoridade.getPkBbtDocumentoBbtAutoridade());

            documentoAutoridade.setFkBbtAutoridade(bbtAutoridade);
            documentoAutoridade.setFkBbtDocumento(documento);

            bbtDocumentoBbtAutoridadeFacade.create(documentoAutoridade);
            System.err.println("documentoAutoridade Gravado com sucesso");

        }

        for (BbtCdu bbtCdu : cdusSelecionados)
        {
            bbtDocumentoBbtCdu
                    = new BbtDocumentoBbtCdu(bbtDocumentoBbtCduFacade.getNextPk());

            bbtDocumentoBbtCdu.setFkBbtCdu(bbtCdu);
            bbtDocumentoBbtCdu.setFkBbtDocumento(documento);

            bbtDocumentoBbtCduFacade.create(bbtDocumentoBbtCdu);

            System.err.println("bbtDocumentoBbtCdu Gravado com sucesso");
        }
    }

    //Getters and setters
    public LocLocalidade getProvincia()
    {
        return provincia;
    }

    public void setProvincia(LocLocalidade provincia)
    {
        this.provincia = provincia;
    }

    public BbtEditora getEditora()
    {
        return editora;
    }

    public void setEditora(BbtEditora editora)
    {
        this.editora = editora;
    }

    public BbtColecao getColecao()
    {
        return colecao;
    }

    public void setColecao(BbtColecao colecao)
    {
        this.colecao = colecao;
    }
    
    

    public BbtCdu getCdu()
    {
        return cdu;
    }

    public void setCdu(BbtCdu cdu)
    {
        this.cdu = cdu;
    }

    public String getSubTitulo()
    {
        return subTitulo;
    }

    public void setSubTitulo(String subTitulo)
    {
        this.subTitulo = subTitulo;
    }

    public Integer getPkBbtEdicao()
    {
        return pkBbtEdicao;
    }

    public void setPkBbtEdicao(Integer pkBbtEdicao)
    {
        this.pkBbtEdicao = pkBbtEdicao;
    }

    public Integer getPkLingua()
    {
        return pkLingua;
    }

    public void setPkLingua(Integer pkLingua)
    {
        this.pkLingua = pkLingua;
    }

    public List<BbtEdicao> getEdicoes()
    {
        return edicoes;
    }

    public void setEdicoes(List<BbtEdicao> edicoes)
    {
        this.edicoes = edicoes;
    }

    public Integer getPkBbtEditora()
    {
        return pkBbtEditora;
    }

    public void setPkBbtEditora(Integer pkBbtEditora)
    {
        this.pkBbtEditora = pkBbtEditora;
    }

    public Integer getPkBbtColecao()
    {
        return pkBbtColecao;
    }

    public void setPkBbtColecao(Integer pkBbtColecao)
    {
        this.pkBbtColecao = pkBbtColecao;
    }

    public String getNotas()
    {
        return notas;
    }

    public void setNotas(String notas)
    {
        this.notas = notas;
    }

    public String getTipoForm()
    {
        return tipoForm;
    }

    public void setTipoForm(String tipoForm)
    {
        this.tipoForm = tipoForm;
    }

    public String getDepositoLegal()
    {
        return depositoLegal;
    }

    public List<BbtColecao> getColecoes()
    {
        return colecoes;
    }

    public void setColecoes(List<BbtColecao> colecoes)
    {
        this.colecoes = colecoes;
    }

    public String getTitulo()
    {
        return titulo;
    }

    public void setTitulo(String titulo)
    {
        this.titulo = titulo;
    }

    public String getSubtitulo()
    {
        return subTitulo;
    }

    public void setSubtitulo(String subTitulo)
    {
        this.subTitulo = subTitulo;
    }

    public List<BbtCdu> getCdus()
    {
        return cdus;
    }

    public void setCdus(List<BbtCdu> cdus)
    {
        this.cdus = cdus;
    }

    public List<BbtCdu> getCdusSelecionados()
    {
        return cdusSelecionados;
    }

    public void setCdusSelecionados(List<BbtCdu> cdusSelecionados)
    {
        this.cdusSelecionados = cdusSelecionados;
    }

    public void setDepositoLegal(String depositoLegal)
    {
        this.depositoLegal = depositoLegal;
    }

    public List<BbtAutoridade> getAutoridadesSelecionadas()
    {
        return autoridadesSelecionadas;
    }

    public void setAutoridadesSelecionadas(List<BbtAutoridade> autoridadesSelecionadas)
    {
        this.autoridadesSelecionadas = autoridadesSelecionadas;
    }

    public List<BbtTipoDocumento> getTiposDocumentos()
    {
        return tiposDocumentos;
    }

    public void setTiposDocumentos(List<BbtTipoDocumento> tiposDocumentos)
    {
        this.tiposDocumentos = tiposDocumentos;
    }

    public Integer getPkGrlLingua()
    {
        return pkGrlLingua;
    }

    public void setPkGrlLingua(Integer pkGrlLingua)
    {
        this.pkGrlLingua = pkGrlLingua;
    }

    public Integer getPkAutor()
    {
        return pkAutor;
    }

    public void setPkAutor(Integer pkAutor)
    {
        this.pkAutor = pkAutor;
    }

    public String getPkProvincia()
    {
        return pkProvincia;
    }

    public void setPkProvincia(String pkProvincia)
    {
        this.pkProvincia = pkProvincia;
    }

    public String getPkPais()
    {
        return pkPais;
    }

    public void setPkPais(String pkPais)
    {
        this.pkPais = pkPais;
    }

    public Float getAltura()
    {
        return altura;
    }

    public void setAltura(Float altura)
    {
        this.altura = altura;
    }

    public Float getLargura()
    {
        return largura;
    }

    public void setLargura(Float largura)
    {
        this.largura = largura;
    }

    public Integer getNumPaginas()
    {
        return numPaginas;
    }

    public void setNumPaginas(Integer numPaginas)
    {
        this.numPaginas = numPaginas;
    }

    public Integer getAno()
    {
        return ano;
    }

    public void setAno(Integer ano)
    {
        this.ano = ano;
    }

    public String getIsbn()
    {
        return isbn;
    }

    public void setIsbn(String isbn)
    {
        this.isbn = isbn;
    }

    public List<BbtEditora> getEditoras()
    {
        return editoras;
    }

    public void setEditoras(List<BbtEditora> editoras)
    {
        this.editoras = editoras;
    }

    public List<GrlLingua> getLinguas()
    {
        return linguas;
    }

    public void setLinguas(List<GrlLingua> linguas)
    {
        this.linguas = linguas;
    }

    public List<LocLocalidade> getPaises()
    {
        return paises;
    }

    public void setPaises(List<LocLocalidade> paises)
    {
        this.paises = paises;
    }

    public List<LocLocalidade> getProvincias()
    {
        return provincias;
    }

    public void setProvincias(List<LocLocalidade> provincias)
    {
        this.provincias = provincias;
    }

    public List<BbtAutoridade> getAutoridades()
    {
        return autoridades;
    }

    public void setAutoridades(List<BbtAutoridade> autoridades)
    {
        this.autoridades = autoridades;
    }

    public String getDepLegal()
    {
        return depLegal;
    }

    public void setDepLegal(String depLegal)
    {
        this.depLegal = depLegal;
    }

}
