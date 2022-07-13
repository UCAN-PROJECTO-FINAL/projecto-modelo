/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package localidade.utils;

import static utils.Defs.FILES_EXCEL_LOCALIZACAO;
import static utils.Defs.getPathWEB_INF;
import static utils.Defs.pathSeparator;
import static utils.Defs.rootPath;

/**
 *
 * @author mdnex
 */

public class Defs
{
    public static final String INDEFINIDO = "Indefinido", INDEFINIDA = "Indefinida";
    public static final String PK_LOCALIDADE_ANGOLA = "1";
    
    public static final String FILES_EXCEL_LOCALIZACAO = getPathWEB_INF("files_excel") + pathSeparator();
    public static final String FILES_EXCEL_LOCALIZACAO1 = "files_excel" + pathSeparator();

    public static final String NOME_FILE_LOCALIDADE = "localidades.xls";
    public static final String CAMINHO_LOCALIDADE = getPathWEB_INF(FILES_EXCEL_LOCALIZACAO + "localidades.xls");
   
    
}
