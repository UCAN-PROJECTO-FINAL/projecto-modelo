/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import javax.faces.context.FacesContext;

/**
 *
 * @author hermann
 */
public class Defss
{

    public static String ROOT_PATH_LINUX = "/" ;
    public static String ROOT_PATH_WINDOWS_PADRAO = "C:\\";
    private static String rootPathWindows = ROOT_PATH_WINDOWS_PADRAO;

    public static String FILES_EXCEL ;
    public static String CAMINHO_FILES_EXCEL ;
    public static String CAMINHO_PORTFOLIO ;
    
    public static String INDEFINIDO ;

    private static String operatingSystem = null;
    
    private static boolean jaInicializado = false;
    
    public static final String FILES_EXCEL_LOCALIZACAO = getPathWEB_INF("files_excel") + pathSeparator();
    public static final String FILES_EXCEL_LOCALIZACAO1 = "files_excel" + pathSeparator();
    
    public static final String NOME_FILE_LOCALIDADE = "localidades.xls";
    public static final String NOME_FILE_ESTRUTURA_FISICA ="estrutura_fisica.xls";
    public static final String NOME_FILE_ESTRUTURA_LOGICA = "estrutura_logica.xls";
    
    public static final String CAMINHO_LOCALIDADE = getPathWEB_INF(FILES_EXCEL_LOCALIZACAO1 + "localidades.xls");
    public static final String CAMINHO_ESTRUTURA_FISICA = getPathWEB_INF(FILES_EXCEL_LOCALIZACAO1 + "estrutura_fisica.xls");
    public static final String CAMINHO_ESTRUTURA_LOGICA = getPathWEB_INF(FILES_EXCEL_LOCALIZACAO1 + "estrutura_logica.xls");
    public static final String CAMINHO_ESTRUTURA_LOGICA_FISICA = getPathWEB_INF(FILES_EXCEL_LOCALIZACAO1 + "estrutura_logica_fisica.xls");
    
    public static final String RESULTADOS_DIR = rootPath();
    public static final String LOGS_DIR = RESULTADOS_DIR + "logs" + pathSeparator();
    public static final String LOG_FILENAME_PREFIX = LOGS_DIR + "logs_";
    
    public static final String ID_GROWL_FORM_GERAL = "gr_geral";
    
    public static final String COR_COMPLETAMENTE_LIVRE = "green";
    public static final String COR_COMPLETAMENTE_OCUPADO_OUTROS = "red";
    public static final String COR_COMPLETAMENTE_OCUPADO_ENTIDADE = "blue";
    public static final String COR_COMPLETAMENTE_OCUPADO_ENTIDADE_OUTROS = "purple";
    public static final String COR_PARCIAL_OCUPADO_OUTROS = "orange";
    public static final String COR_PARCIAL_OCUPADO_ENTIDADE = "yellow";
    public static final String COR_PARCIAL_OCUPADO_ENTIDADE_OUTROS = "rose";
    
    
    public static void init()
    {
        if (jaInicializado)
            return; 
        
        
        ROOT_PATH_LINUX = "/";
        ROOT_PATH_WINDOWS_PADRAO = "C:\\";
        rootPathWindows = ROOT_PATH_WINDOWS_PADRAO;

        FILES_EXCEL = "files_excel";
        CAMINHO_FILES_EXCEL = getPathWEB_INF(FILES_EXCEL) + pathSeparator();
        CAMINHO_PORTFOLIO = CAMINHO_FILES_EXCEL + "portfolio.xls";

        operatingSystem = null;
        
        jaInicializado = true; 

    }
    
    public static final String rootPath()
    {
        return isWindows() ? rootPathWindows + "psgl_egti" + pathSeparator() : ROOT_PATH_LINUX + "usr" + pathSeparator() + "local" + pathSeparator() + "resultados" + pathSeparator();
    }

    public static final String getPathWEB_INF(String filename)
    {
        String relativePath = pathSeparator() + "WEB-INF" + pathSeparator() + filename;
        return FacesContext.getCurrentInstance().getExternalContext().getRealPath(relativePath);
    }

    public static final String getOsName()
    {
        if (operatingSystem == null)
        {
            operatingSystem = System.getProperty("os.name");
        }
        return operatingSystem;
    }

    public static boolean isWindows()
    {
        return getOsName().startsWith("Windows");
    }

    public static boolean isLinux() // and so on
    {
        return getOsName().startsWith("Linux");
    }

    /*
        returns pathSeparator() for Linux e "/" for Windows
     */
    public static final String pathSeparator()
    {
        return isWindows() ? "\\" : "/";
    }
}
