/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patri.utils;

import static utils.Defs.FILES_EXCEL_LOCALIZACAO;
import static utils.Defs.pathSeparator;
import static utils.Defs.rootPath;
import static utils.Defs.getPathWEB_INF;

/**
 *
 * @author mdnex
 */

public class Defs
{
    
    public static String INDEFINIDO;
    
    public static final String NOME_FILE_PATRIMONIO = "patrimonio.xls";
    public static final String NOME_FILE_MARCA = "patri_marca.xls";
    public static final String NOME_FILE_CATEGORIA = "patri_categoria.xls";
    public static final String NOME_FILE_ESTADO_CONSERVACAO = "patri_estado_conservacao.xls";
    public static final String NOME_FILE_FORMA_AQUISICAO = "patri_forma_aquisicao.xls";
    public static final String NOME_FILE_MODELO = "patri_modelo.xls";
    public static final String NOME_FILE_FORNECEDOR = "patri_fornecedor.xls";
    public static final String NOME_FILE_TIPO_SAIDA = "patri_tipo_saida_bem.xls";
    
    public static final String CAMINHO_PATRIMONIO = getPathWEB_INF(FILES_EXCEL_LOCALIZACAO + "patrimonio.xls");
    public static final String CAMINHO_MARCA = getPathWEB_INF(FILES_EXCEL_LOCALIZACAO + "patri_marca.xls");
    public static final String CAMINHO_CATEGORIA = getPathWEB_INF(FILES_EXCEL_LOCALIZACAO + "patri_categoria.xls");
    public static final String CAMINHO_ESTADO_CONSERVACAO = getPathWEB_INF(FILES_EXCEL_LOCALIZACAO + "patri_estado_conservacao.xls");
    public static final String CAMINHO_FORMA_AQUISICAO = getPathWEB_INF(FILES_EXCEL_LOCALIZACAO + "patri_forma_aquisicao.xls");
    public static final String CAMINHO_MODELO = getPathWEB_INF(FILES_EXCEL_LOCALIZACAO + "patri_modelo.xls");
    public static final String CAMINHO_FORNECEDOR = getPathWEB_INF(FILES_EXCEL_LOCALIZACAO + "patri_fornecedor.xls");
    public static final String CAMINHO_TIPO_SAIDA = getPathWEB_INF(FILES_EXCEL_LOCALIZACAO + "patri_tipo_saida_bem.xls");
    
    public static final String DOCS_PATRI_DIR = rootPath() + "documentos" + pathSeparator() + "patrimonio";
    public static final String IMG_PATRI_DIR = rootPath() + "imagens" + pathSeparator() + "patrimonio";
    public static final String DOCS_PATRI_DIRECTORIO = rootPath() + "documentos" + pathSeparator() + "patrimonio" + pathSeparator() + "bens" + pathSeparator();
    public static final String IMG_PATRI_DIRECTORIO = rootPath() + "imagens" + pathSeparator() + "patrimonio" + pathSeparator() + "bens" + pathSeparator();
    public static final String CONTRATOS_CARREGADOS_DIR = DOCS_PATRI_DIR + pathSeparator() + "contractos" + pathSeparator();
    public static final String DOCUMENTOS_BENS_ITANGIVEIS_CARREGADOS_DIR = DOCS_PATRI_DIR + pathSeparator() + "bem_itangivel" + pathSeparator();
    public static final String DOCUMENTOS_BENS_MOVEIS_CARREGADOS_DIR = DOCS_PATRI_DIR + pathSeparator() + "bem_movel" + pathSeparator();
    public static final String DOCUMENTOS_BENS_IMOVEIS_CARREGADOS_DIR = DOCS_PATRI_DIR + pathSeparator() + "bem_imovel" + pathSeparator();
    public static final String IMAGENS_BENS_IMOVEIS_CARREGADOS_DIR = IMG_PATRI_DIR + pathSeparator() + "bem_imovel" + pathSeparator();
    public static final String IMAGENS_BENS_MOVEIS_CARREGADOS_DIR = IMG_PATRI_DIR + pathSeparator() + "bem_movel" + pathSeparator();
    
   
    
}
