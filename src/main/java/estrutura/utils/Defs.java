/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estrutura.utils;

import patri.utils.*;
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
    public static final String INDEFINIDO = "Indefinido", INDEFINIDA = "Indefinida";
    
    public static final String COR_COMPLETAMENTE_LIVRE = "green";
    public static final String COR_COMPLETAMENTE_OCUPADO_OUTROS = "red";
    public static final String COR_COMPLETAMENTE_OCUPADO_ENTIDADE = "blue";
    public static final String COR_COMPLETAMENTE_OCUPADO_ENTIDADE_OUTROS = "purple";
    public static final String COR_PARCIAL_OCUPADO_OUTROS = "orange";
    public static final String COR_PARCIAL_OCUPADO_ENTIDADE = "yellow";
    public static final String COR_PARCIAL_OCUPADO_ENTIDADE_OUTROS = "rose";
    
    public static final String NOME_FILE_ESTRUTURA_FISICA ="estrutura_fisica.xls";
    public static final String NOME_FILE_ESTRUTURA_LOGICA = "estrutura_logica.xls";
    
    
    public static final String CAMINHO_ESTRUTURA_FISICA = getPathWEB_INF(FILES_EXCEL_LOCALIZACAO + "estrutura_fisica.xls");
    public static final String CAMINHO_ESTRUTURA_LOGICA = getPathWEB_INF(FILES_EXCEL_LOCALIZACAO + "estrutura_logica.xls");
    public static final String CAMINHO_ESTRUTURA_LOGICA_FISICA = getPathWEB_INF(FILES_EXCEL_LOCALIZACAO + "estrutura_logica_fisica.xls");
    
}
