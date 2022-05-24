/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package seg.utils;

import java.io.File;
import utils.PathManager;                 
import static utils.PathManager.saltarPara;       


/**
 *
 * @author aires
 */
public class Defs
{  
	
//	Portal de Suporte a Gestão de Lotes
    public static final String DIRECTORIO;
    
    public static final String KEY = "92AE31A79FEEB2A3";
    public static final String IV = "0123456789ABCDEF";

    static
    {
        DIRECTORIO = saltarPara(7, new File(PathManager.class.getResource("").getFile().replaceAll("%20", " "))).getAbsolutePath() + "/";
    }

	
    public static final String APPLICATION_NAME = "PSGL_EGTI";
    public static final String HOME_PAGE_URL = "/home.xhtml";

    
    public static String URL_SEG_CONCLUIR_CRIACAO_CONTA = "seg_utilizador_concluir_criacao_conta.xhtml?faces-redirect=true";
    
    public static final String EMAIL = "testeucan1@gmail.com";

    public static final String PASSWORD = "testeucan2018";

    public static final String HEADER_ACCOUNT = "Credenciais do Sistema";

    public static final String ERROR_FICHEIRO_INP = "O Ficheiro de Movimento Está Corrompido Porque "
        + "O Valor Total Das Transções Não Conscide Com Os Valores "
        + "Acumulado das Transações !";

    public static final String ADMIN = "1000010956@ucan.edu";

    ////////////////////////////////TELEFONE/////////////////////////////////////////////////
    public static final String USERNAME = "TesteOEA1";

    public static final String PASSWORD1 = "testeoea12019";

    public static final String URL = "https://bulksms.vsms.net/eapi/submission/send_sms/2/2.0";

       
}
