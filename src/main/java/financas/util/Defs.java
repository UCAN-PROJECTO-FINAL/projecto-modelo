/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package financas.util;

import static utils.Defs.pathSeparator;
import static utils.Defs.rootPath;

/**
 *
 * @author mdnex
 */
public class Defs {
   
    public static final String DOCS_FINANCAS_DIR = rootPath() + "documentos" + pathSeparator() + "contabfinanca";
    public static final String CONTRATOS_CARREGADOS_DIR = DOCS_FINANCAS_DIR + pathSeparator() + "contractos" + pathSeparator();
    public static final String DOCUMENTOS_CARREGADOS_DIR = DOCS_FINANCAS_DIR + pathSeparator() + "documento" + pathSeparator();

}
