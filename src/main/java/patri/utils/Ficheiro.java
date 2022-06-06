/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patri.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author amaro
 */
public class Ficheiro
{

    /*
    retorna:
            null se nao estiver gravado
            extensao se estiver gravado
     */
    public static String estaGravado(String dir, String nomeDocumento)
    {
//        System.err.println("0. Ficheiro.estaGravado()\tdir: " + dir + "\tnomeDocumento: " + nomeDocumento);
        File f = new File(dir);

        // Populates the array with names of files and directories
        String[] pathnames = f.list();
        if (pathnames == null || pathnames.length == 0)
        {
            return null;
        }
//        System.err.println("1. Ficheiro.estaGravado()\tdir: " + dir + "\tnomeDocumento: " + nomeDocumento);
        // For each pathname in the pathnames array
        for (String pathname : pathnames)
        {
//            System.err.println("2. Ficheiro.estaGravado()\tdir: " + dir + "\tpathname: " + pathname + "\tnomeDocumento: " + nomeDocumento);
            // Print the names of files and directories
            if ((new File(pathname)).isDirectory())
            {
                continue;
            }
//            System.err.println("3. Ficheiro.estaGravado()\tdir: " + dir + "\tpathname: " + pathname + "\tnomeDocumento: " + nomeDocumento);
            if (!pathname.startsWith(nomeDocumento))
            {
                continue;
            }
//            System.err.println("4. Ficheiro.estaGravado()\tdir: " + dir + "\tpathname: " + pathname + "\tnomeDocumento: " + nomeDocumento);
            int index = pathname.lastIndexOf(".");
            if (index == -1 || (index != nomeDocumento.length()))
            {
                continue;
            }
//            System.err.println("5. Ficheiro.estaGravado()\tdir: " + dir + "\tpathname: " + pathname + "\tnomeDocumento: " + nomeDocumento);

            return pathname.substring(index + 1, pathname.length());
        }
//        System.err.println("6. Ficheiro.estaGravado()\tdir: " + dir + "\tnomeDocumento: " + nomeDocumento);
        return null;
    }

    public static boolean gravarDocumentoNoDirectiroPadrao(InputStream inputStream, String nome, String extensao, int tipoDocumento)
    {
//        System.err.println("0: Ficheiro.gravar()\tnome: " + nome + "\textensao: " + extensao
//            + "\ttipoDocumento: " + tipoDocumento);
        try
        {
            String diretorio = Utils.findDirectorioByTipoDocumento(tipoDocumento);
            File file = new File(diretorio + nome + "." + extensao);
//            System.err.println("00: Ficheiro.gravar()\tdiretorio: " + diretorio + "\textensao: " + extensao
//                + "\ttipoDocumento: " + tipoDocumento);
            if (file.exists())
            {
//                System.err.println("1: Ficheiro.gravar()\tnome: " + nome + "\textensao: " + extensao
//                    + "\ttipoDocumento: " + tipoDocumento);
                file.delete();
            }
            Files.copy(inputStream, file.toPath());
//            System.err.println("2: Ficheiro.gravar()\tfile.toPath(): " + file.getPath() + "\textensao: " + extensao
//                + "\ttipoDocumento: " + tipoDocumento);
            return true;
        }
        catch (IOException ex)
        {
//            System.err.println("3: Ficheiro.gravar()\tnome: " + nome + "\textensao: " + extensao
//                + "\ttipoDocumento: " + tipoDocumento);
            return false;
        }
    }

    public static boolean removerDocumentoDoDirectorioPadrao(InputStream inputStream, String nome, String extensao, int tipoDocumento)
    {
        try
        {
            String diretorio = Utils.findDirectorioByTipoDocumento(tipoDocumento);
            File file = new File(diretorio + nome + "." + extensao);
            if (file.exists())
            {
                file.delete();
            }
            Files.copy(inputStream, file.toPath());
            return true;
        }
        catch (IOException ex)
        {
            return false;
        }
    }

    public static boolean tranferir(String sourceDir, String destDir, String filenameSource, String filenameDest)
    {
        File file = new File(destDir + filenameDest);
        if (file.exists())
        {
            file.delete();
        }
        try
        {
            Path dest = Paths.get(destDir + filenameDest);
            Path source = Paths.get(sourceDir + filenameSource);
            Files.copy(source, dest, StandardCopyOption.REPLACE_EXISTING);
            return true;
        }
        catch (IOException ex)
        {
            return false;
        }
    }

    public static boolean gravar(InputStream inputStream, String nome, String extensao, String destDir)
    {
        try
        {
            File file = new File(destDir + nome + "." + extensao);
            if (file.exists())
            {
                file.delete();
            }
            File newFile = new File(destDir + nome + "." + extensao);
            Files.copy(inputStream, newFile.toPath());
            return true;
        }
        catch (IOException ex)
        {
            System.err.println("sssssssssssssss"+ex);
            return false;
        }
    }

    public static boolean reeditarFicheiroComNovaExtensao(InputStream inputStream, String nome, String extensaoNova, String extensaoAntiga)
    {
        try
        {
            File file = new File(Defs.DOCS_PATRI_DIR + nome + "." + extensaoAntiga);
            if (file.exists())
            {
                file.delete();
            }
            File newFile = new File(Defs.DOCS_PATRI_DIR + nome + "." + extensaoNova);
            Files.copy(inputStream, newFile.toPath());
            return true;
        }
        catch (IOException ex)
        {
            return false;
        }
    }

    public static boolean apagarDocumento(String dir, String nome, String extensao)
    {
        File file = new File(dir + nome + "." + extensao);
        if (file.exists())
        {
            file.delete();
            return true;
        }
        return false;
    }

    public static boolean apagarDocumentosDoDirectorioPadrao(String nome, String extensao)
    {
        File file = new File(Defs.DOCS_PATRI_DIR + nome + "." + extensao);
        if (file.exists())
        {
            file.delete();
            return true;
        }
        return false;
    }

    static boolean existe(String dirProv, String nome, String extensao)
    {
//System.err.println("0. Ficheiro.abrirResourcesTmp()\tnome: " + nome + "\textensao: " + extensao);
        File file = new File(dirProv + nome + "." + extensao);
//System.err.println("1. Ficheiro.abrirResourcesTmp()\tnome: " + (dirProv + nome + "." + extensao));        
        return file.exists();
    }

    public static boolean visualizarNoBrowser(String sourcPath, String nome, String extensao, int tipoDocumento)
    {
//        System.err.println("0. Ficheiro.abrirResourcesTmp()\tnome: " + nome + "\textensao: " + extensao
//            + "\ttipoDocumento: " + tipoDocumento);
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
//        System.err.println("00. Ficheiro.abrirResourcesTmp()\tnome: " + nome + "\textensao: " + extensao
//            + "\ttipoDocumento: " + tipoDocumento);
        OutputStream output = null;
        try
        {
//            String dir = Defs.RESULTADOS_DOCUMENTOS_ENTREGUES_DIR;
//            System.err.println("01. Ficheiro.abrirResourcesTmp()\tnome: " + nome + "\textensao: " + extensao
//                + "\ttipoDocumento: " + tipoDocumento + "\nsourcPath: " + sourcPath);
            String contentType = geraContentType(extensao);
//            System.err.println("1. Ficheiro.abrirResourcesTmp()\tnome: " + nome + "\textensao: " + extensao
//                + "\tcontentType: " + contentType);
            String nomeExtensao = geraNomeExtensao(nome, extensao);
//            System.err.println("2. Ficheiro.abrirResourcesTmp()\tnome: " + nome + "\textensao: " + extensao
//                + "\tnomeExtensao: " + nomeExtensao);
            response.setContentType(contentType);
//            System.err.println("3. Ficheiro.abrirResourcesTmp()\tnome: " + nome + "\textensao: " + extensao
//                + "\ttipoDocumento: " + tipoDocumento);
            response.setHeader("Content-disposition", "inline; filename=" + nomeExtensao);
//            System.err.println("4. Ficheiro.abrirResourcesTmp()\tnome: " + nome + "\textensao: " + extensao
//                + "\ttipoDocumento: " + tipoDocumento);
            output = response.getOutputStream();
//            System.err.println("5. Ficheiro.abrirResourcesTmp()\tnome: " + nome + "\textensao: " + extensao
//                + "\ttipoDocumento: " + tipoDocumento + "\ndir + nomeExtensao: " + sourcPath + nomeExtensao);
            File ficheiro = new File(sourcPath + nomeExtensao);
//            System.err.println("5.1. Ficheiro.abrirResourcesTmp()\tnome: " + nome + "\textensao: " + extensao
//                + "\ttipoDocumento: " + tipoDocumento + "\ndir + nomeExtensao: " + sourcPath + nomeExtensao);
            output.write(Files.readAllBytes(ficheiro.toPath()));
//            System.err.println("6. Ficheiro.abrirResourcesTmp()\tnome: " + nome + "\textensao: " + extensao
//                + "\nficheiro.toPath(): " + ficheiro.toPath());
            response.flushBuffer();
            output.flush();
//            System.err.println("7. Ficheiro.abrirResourcesTmp()\tnome: " + nome + "\textensao: " + extensao
//                + "\ttipoDocumento: " + tipoDocumento);
//            System.err.println("8. Ficheiro.abrirResourcesTmp()\tnome: " + nome + "\textensao: " + extensao
//                + "\ttipoDocumento: " + tipoDocumento);
//            System.err.println("9. Ficheiro.abrirResourcesTmp()\tnome: " + nome + "\textensao: " + extensao
//                + "\ttipoDocumento: " + tipoDocumento);
        }
        catch (IOException e)
        {
//            System.err.println("10. Ficheiro.abrirResourcesTmp()\tnome: " + nome + "\textensao: " + extensao
//                + "\ttipoDocumento: " + tipoDocumento);
            return false;
        }
        finally
        {
//System.err.println("11. Ficheiro.abrirResourcesTmp()\tnome: " + nome + "\textensao: " + extensao
//                + "\ttipoDocumento: " + tipoDocumento);            
            if (output != null)
            {
                try
                {
                    output.close();
                }
                catch (IOException ex)
                {
//System.err.println("12. Ficheiro.abrirResourcesTmp()\tnome: " + nome + "\textensao: " + extensao
//                + "\ttipoDocumento: " + tipoDocumento);                    
                    return false;
                }
            }
//System.err.println("13. Ficheiro.abrirResourcesTmp()\tnome: " + nome + "\textensao: " + extensao
//                + "\ttipoDocumento: " + tipoDocumento);
            FacesContext.getCurrentInstance().responseComplete();

        }
//        System.err.println("14. Ficheiro.abrirResourcesTmp()\tnome: " + nome + "\textensao: " + extensao
//            + "\ttipoDocumento: " + tipoDocumento);
        return true;
    }

    public static boolean visualizarDocumentoDoDirectorioPadraoNoBrowser(String nome, String extensao, int tipoDocumento)
    {
//        System.err.println("0. Ficheiro.visualizarDocumentoDoDirectorioPadraoNoBrowser()\tnome: " + nome + "\textensao: " + extensao
//            + "\ttipoDocumento: " + tipoDocumento);
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
//        System.err.println("00. Ficheiro.visualizarDocumentoDoDirectorioPadraoNoBrowser()\tnome: " + nome + "\textensao: " + extensao
//            + "\ttipoDocumento: " + tipoDocumento);
        try
        {
            String dir = Utils.findDirectorioByTipoDocumento(tipoDocumento);
//            System.err.println("01. Ficheiro.visualizarDocumentoDoDirectorioPadraoNoBrowser()\tnome: " + nome + "\textensao: " + extensao
//                + "\ttipoDocumento: " + tipoDocumento + "\ndir: " + dir);
            String contentType = geraContentType(extensao);
//            System.err.println("1. Ficheiro.visualizarDocumentoDoDirectorioPadraoNoBrowser()\tnome: " + nome + "\textensao: " + extensao
//                + "\tcontentType: " + contentType);
            String nomeExtensao = geraNomeExtensao(nome, extensao);
//            System.err.println("2. Ficheiro.visualizarDocumentoDoDirectorioPadraoNoBrowser()\tnome: " + nome + "\textensao: " + extensao
//                + "\tnomeExtensao: " + nomeExtensao);
            response.setContentType(contentType);
//            System.err.println("3. Ficheiro.visualizarDocumentoDoDirectorioPadraoNoBrowser()\tnome: " + nome + "\textensao: " + extensao
//                + "\ttipoDocumento: " + tipoDocumento);
            response.setHeader("Content-disposition", "inline; filename=" + nomeExtensao);
//            System.err.println("5. Ficheiro.visualizarDocumentoDoDirectorioPadraoNoBrowser()\tnome: " + nome + "\textensao: " + extensao
//                + "\ttipoDocumento: " + tipoDocumento + "\ndir + nomeExtensao: " + dir + nomeExtensao);
            try (//            System.err.println("4. Ficheiro.visualizarDocumentoDoDirectorioPadraoNoBrowser()\tnome: " + nome + "\textensao: " + extensao
                //                + "\ttipoDocumento: " + tipoDocumento);
                OutputStream output = response.getOutputStream())
            {
                //            System.err.println("5. Ficheiro.visualizarDocumentoDoDirectorioPadraoNoBrowser()\tnome: " + nome + "\textensao: " + extensao
//                + "\ttipoDocumento: " + tipoDocumento + "\ndir + nomeExtensao: " + dir + nomeExtensao);
                File ficheiro = new File(dir + nomeExtensao);
                output.write(Files.readAllBytes(ficheiro.toPath()));
                //            System.err.println("6. Ficheiro.visualizarDocumentoDoDirectorioPadraoNoBrowser()\tnome: " + nome + "\textensao: " + extensao
//                + "\ndir + nomeExtensao: " + dir + nomeExtensao);
                response.flushBuffer();
                output.flush();
//            System.err.println("7. Ficheiro.visualizarDocumentoDoDirectorioPadraoNoBrowser()\tnome: " + nome + "\textensao: " + extensao
//                + "\ttipoDocumento: " + tipoDocumento);
            }
//            System.err.println("8. Ficheiro.visualizarDocumentoDoDirectorioPadraoNoBrowser()\tnome: " + nome + "\textensao: " + extensao
//                + "\ttipoDocumento: " + tipoDocumento);
            FacesContext.getCurrentInstance().responseComplete();
//            System.err.println("9. Ficheiro.visualizarDocumentoDoDirectorioPadraoNoBrowser()\tnome: " + nome + "\textensao: " + extensao
//                + "\ttipoDocumento: " + tipoDocumento);
        }
        catch (IOException e)
        {
//            System.err.println("10. Ficheiro.visualizarDocumentoDoDirectorioPadraoNoBrowser()\tnome: " + nome + "\textensao: " + extensao
//                + "\ttipoDocumento: " + tipoDocumento);
            return false;
        }
//        System.err.println("11. Ficheiro.visualizarDocumentoDoDirectorioPadraoNoBrowser()\tnome: " + nome + "\textensao: " + extensao
//            + "\ttipoDocumento: " + tipoDocumento);
        return true;
    }

    public static boolean visualizarDocumentoFinalNoBrowser(String nome, String extensao, int tipoDocumento)
    {
//        System.err.println("0. Ficheiro.visualizarDocumentoFinalNoBrowser()\tnome: " + nome + "\textensao: " + extensao
//            + "\ttipoDocumento: " + tipoDocumento);
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
//        System.err.println("00. Ficheiro.visualizarDocumentoFinalNoBrowser()\tnome: " + nome + "\textensao: " + extensao
//            + "\ttipoDocumento: " + tipoDocumento);
        try
        {
            String dir = Utils.findDirectorioDocumentosEmitidosFinaisByTipoDocumento(tipoDocumento);
//            System.err.println("01. Ficheiro.visualizarDocumentoFinalNoBrowser()\tnome: " + nome + "\textensao: " + extensao
//                + "\ttipoDocumento: " + tipoDocumento + "\ndir: " + dir);
            String contentType = geraContentType(extensao);
//            System.err.println("1. Ficheiro.visualizarDocumentoFinalNoBrowser()\tnome: " + nome + "\textensao: " + extensao
//                + "\tcontentType: " + contentType);
            String nomeExtensao = geraNomeExtensao(nome, extensao);
//            System.err.println("2. Ficheiro.visualizarDocumentoFinalNoBrowser()\tnome: " + nome + "\textensao: " + extensao
//                + "\tnomeExtensao: " + nomeExtensao);
            response.setContentType(contentType);
//            System.err.println("3. Ficheiro.visualizarDocumentoFinalNoBrowser()\tnome: " + nome + "\textensao: " + extensao
//                + "\ttipoDocumento: " + tipoDocumento);
            response.setHeader("Content-disposition", "inline; filename=" + nomeExtensao);
//            System.err.println("5. Ficheiro.visualizarDocumentoFinalNoBrowser()\tnome: " + nome + "\textensao: " + extensao
//                + "\ttipoDocumento: " + tipoDocumento + "\ndir + nomeExtensao: " + dir + nomeExtensao);
            try (//            System.err.println("4. Ficheiro.visualizarDocumentoFinalNoBrowser()\tnome: " + nome + "\textensao: " + extensao
//                + "\ttipoDocumento: " + tipoDocumento);
                OutputStream output = response.getOutputStream())
            {
                //            System.err.println("5. Ficheiro.visualizarDocumentoFinalNoBrowser()\tnome: " + nome + "\textensao: " + extensao
//                + "\ttipoDocumento: " + tipoDocumento + "\ndir + nomeExtensao: " + dir + nomeExtensao);
                File ficheiro = new File(dir + nomeExtensao);
                output.write(Files.readAllBytes(ficheiro.toPath()));
                //            System.err.println("6. Ficheiro.visualizarDocumentoFinalNoBrowser()\tnome: " + nome + "\textensao: " + extensao
//                + "\ndir + nomeExtensao: " + dir + nomeExtensao);
response.flushBuffer();
output.flush();
//            System.err.println("7. Ficheiro.visualizarDocumentoFinalNoBrowser()\tnome: " + nome + "\textensao: " + extensao
//                + "\ttipoDocumento: " + tipoDocumento);
            }
//            System.err.println("8. Ficheiro.visualizarDocumentoFinalNoBrowser()\tnome: " + nome + "\textensao: " + extensao
//                + "\ttipoDocumento: " + tipoDocumento);
            FacesContext.getCurrentInstance().responseComplete();
//            System.err.println("9. Ficheiro.visualizarDocumentoFinalNoBrowser()\tnome: " + nome + "\textensao: " + extensao
//                + "\ttipoDocumento: " + tipoDocumento);
        }
        catch (IOException e)
        {
//            System.err.println("10. Ficheiro.visualizarDocumentoFinalNoBrowser()\tnome: " + nome + "\textensao: " + extensao
//                + "\ttipoDocumento: " + tipoDocumento);
            return false;
        }
//        System.err.println("11. Ficheiro.visualizarDocumentoFinalNoBrowser()\tnome: " + nome + "\textensao: " + extensao
//            + "\ttipoDocumento: " + tipoDocumento);
        return true;
    }

    /*
	extensao: xls, xlsx
	nome: nome absloluto incluindo todo pathname desde a raiz, excluindo a extensao
     */
    public static boolean abrirFicheiroExcel(String nome, String extensao)
    {
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();

        try
        {
//            System.out.println("nome: " + nome + " extensao: " + extensao);
            String nomeSemCaminhoDirectorio = nome.split("/")[nome.split("/").length - 1];
//            System.out.println("nomeWihtouPath = " + nomeSemCaminhoDirectorio);

            String contentType = geraContentType(extensao);
            String nomeExtensao = nome + "." + extensao;
            response.setContentType(contentType);
            response.setHeader("Content-disposition", "inline; filename=" + nomeSemCaminhoDirectorio + "." + extensao);
            try (OutputStream output = response.getOutputStream())
            {
                File ficheiro = new File(nomeExtensao);
                output.write(Files.readAllBytes(ficheiro.toPath()));
                response.flushBuffer();
                output.flush();
            }
            FacesContext.getCurrentInstance().responseComplete();
            return true;
        }
        catch (IOException e)
        {
        }
        return false;
    }

    public static String geraNomeTipoDocumento(int pkTipoDocumento)
    {
        return "String";
            //return DocTipoDocumentoObjecto.get(pkTipoDocumento).getFilename();
//        return DocTipoDocumentoEnum.fromInteger(pkTipoDocumento).getFilename();
//        String nomeTipoDocumento = "";
//        switch (pkTipoDocumento)
//        {
//            case Const.DOC_COMPROVATIVO_INSCRICAO:
//                nomeTipoDocumento = "ComprovativoInscricao";
//                break;
//            case Const.DOC_BILHETE_IDENTIDADE:
//                nomeTipoDocumento = "BilheteIdentidade";
//                break;
//            case Const.DOC_CARTAO_RESIDENCIA:
//                nomeTipoDocumento = "CartaoResidencia";
//                break;
//            case Const.DOC_NIF:
//                nomeTipoDocumento = "Nif";
//                break;
//            case Const.DOC_COMPROVATIVO_CAPACIDADE_FINANCEIRA:
//                nomeTipoDocumento = "ComprovativoCapacidadeFinanceira";
//                break;
//            case Const.DOC_DECLARACAO_SERVICO:
//                nomeTipoDocumento = "DeclaracaoServico";
//                break;
//            case Const.DOC_CERTIDADAO_COMERCIAL_ACTUALIZADA:
//                nomeTipoDocumento = "CertidaoComercialActualizada";
//                break;
//            case Const.DOC_DIARIO_REPUBLICA:
//                nomeTipoDocumento = "DiarioRepublicaConstituicaoSociedadeComercial";
//                break;
//            case Const.DOC_BI_REPRESENTANTES:
//                nomeTipoDocumento = "BiRepresentantesSociedade";
//                break;
//            case Const.DOC_CERTIDAO_NAO_DEVEDOR:
//                nomeTipoDocumento = "CertidaoNaoDevedorReparticaoFiscal";
//                break;
//            case Const.DOC_DOCUMENTO_ARRECADACAO_RECEITA:
//                nomeTipoDocumento = "DocumentoArrecadacaoReceita";
//                break;
//            case Const.DOC_DOCUMENTO_TERMO_RENUNCIA_ENTIDADE_SINGULAR:
//                nomeTipoDocumento = "TermoRenunciaEntidadeSingular";
//                break;
//            case Const.DOC_DOCUMENTO_TERMO_RENUNCIA_EMPRESA:
//                nomeTipoDocumento = "TermoRenunciaEntidadeColectiva";
//                break;
//            case Const.DOC_DOCUMENTO_DECLARACAO_VIABILIDADE_ENTIDADE_SINGULAR:
//                nomeTipoDocumento = "DeclaracaoViabilidadeEntidadeSingular";
//                break;
//            case Const.DOC_DOCUMENTO_DECLARACAO_VIABILIDADE_ENTIDADE_EMPRESA:
//                nomeTipoDocumento = "DeclaracaoViabilidadeEntidadeEmpresa";
//                break;
//            case Const.DOC_DOCUMENTO_OFICIO_INVIABILIDADE_ENTIDADE_SINGULAR:
//                nomeTipoDocumento = "OficioInviabilidadeEntidadeSingular";
//                break;
//            case Const.DOC_DOCUMENTO_OFICIO_INVIABILIDADE_ENTIDADE_EMPRESA:
//                nomeTipoDocumento = "OficioInviabilidadeEntidadeEmpresa";
//                break;
//            case Const.DOC_DOCUMENTO_CONTRATO_DIREITO_SUPERFICIE_ENTIDADE_SINGULAR:
//                nomeTipoDocumento = "ContratoDireitoSuperficieEntidadeSingular";
//                break;
//            case Const.DOC_DOCUMENTO_CONTRATO_DIREITO_SUPERFICIE_ENTIDADE_EMPRESA:
//                nomeTipoDocumento = "ContratoDireitoSuperficieEntidadeEmpresa";
//                break;
//            case Const.DOC_DOCUMENTO_DECLARACAO_INDICADORES_URBANISTICOS:
//                nomeTipoDocumento = "DeclaracaoIndicadoresUrbanisticos";
//                break;
//            case Const.DOC_DOCUMENTO_CONTRATO_FINAL_ATRIBUICAO_LOTE:
//                nomeTipoDocumento = "ContratoFinalAtribuicaoLote";
//                break;
//            case Const.DOC_PAGAMENTO_CONTRATO_DIREITO_SUPERFICIE:
//                nomeTipoDocumento = "PagamentoContratoDireitoSuperficie";
//                break;
//            case Const.DOC_PECAS_TECNICAS:
//                nomeTipoDocumento = "PecasTecnicas";
//                break;
//            case Const.DOC_ESCRITURA_LOTE:
//                nomeTipoDocumento = "Escritura";
//                break;
//        }

//        return nomeTipoDocumento;
    }

    private static String geraNomeExtensao(String nome, String extensao)
    {
        if (nome.endsWith("." + extensao))
        {
            return nome;
        }
        return nome + "." + extensao;
    }

    public static boolean contentTypeIsImage(String extensao)
    {
        return extensao.equals("png") || extensao.equals("jpeg") || extensao.equals("jpg");
    }

    public static boolean contentTypeIsPdf(String extensao)
    {
        return extensao.equals("pdf");
    }

    public static String geraContentType(String extensao)
    {
        switch (extensao)
        {
            case "png":
            case "jpeg":
            case "jpg":
                return "image/" + extensao;
            case "pdf":
                return "application/pdf";
            case "xls":
                return "application/vnd.ms-excel";
            case "xlsx":
                return "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
            default:
                break;
        }
        return "";
    }

}
