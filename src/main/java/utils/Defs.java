/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import javax.faces.context.FacesContext;

/**
 * width="200"
 *
 * @author adilson
 */
public class Defs
{

    /*
    
     */
 /*
    public static final String getPathWEB_INF(String filename)
    {
        String relativePath = pathSeparator() + "WEB-INF" + pathSeparator() + filename;
        return FacesContext.getCurrentInstance().getExternalContext().getRealPath(relativePath);
    }*/
    public static final String FILES_EXCEL_CATALOGACAO = "files_excel" + pathSeparator() + "catalogacao" + pathSeparator();
    public static final String FILES_EXCEL_LOCALIZACAO1 = "files_excel" + pathSeparator();
    public static final String FILES_EXCEL_LOCALIZACAO = getPathWEB_INF("files_excel") + pathSeparator();

    public static final String CAMINHO_LOCALIDADE = getPathWEB_INF(FILES_EXCEL_LOCALIZACAO1 + "localidades.xls");

    public static final String ID_GROWL_FORM_GERAL = "gr_geral";

    public static final String NOME_FILE_ESTRUTURA_FISICA = "estrutura_fisica.xls";

    public static final String NOME_FILE_LOCALIDADE = "localidades.xls";

    public static final String COR_COMPLETAMENTE_LIVRE = "green";
    public static final String COR_COMPLETAMENTE_OCUPADO_OUTROS = "red";
    public static final String COR_COMPLETAMENTE_OCUPADO_ENTIDADE = "blue";
    public static final String COR_COMPLETAMENTE_OCUPADO_ENTIDADE_OUTROS = "purple";
    public static final String COR_PARCIAL_OCUPADO_OUTROS = "orange";
    public static final String COR_PARCIAL_OCUPADO_ENTIDADE = "yellow";
    public static final String COR_PARCIAL_OCUPADO_ENTIDADE_OUTROS = "rose";

    public static final String ENTIDADE_COLECTIVA = " de Empresa";
    public static final String ENTIDADE_SINGULAR = " de Singular";

    public static final String PREFIXO_PROJECTO_KILAMBA_PADRAO = "Kilamba";

    public static final String NOME_INSTITUICAO_TEMPORARIO = "NomeInstituicaoTemporario_";
    public static final String NIF_INSTITUICAO_TEMPORARIO = "NifTemporario_";

    public static final String NOME_REPRESENTANTE_TEMPORARIO = "NomeRepresentanteTemp_";
    public static final String BI_REPRESENTANTE_TEMPORARIO = "BiRepresentanteTemp_";

    public static final String NOME_PESSOA_TEMPORARIO = "NomePessoaTemp_";
    public static final String BI_PESSOA_TEMPORARIO = "BiPessoaTemp_";

    public static final String EGTI = "EGTI";

    public static final String ROOT_PATH_LINUX = "/";
    public static final String ROOT_PATH_WINDOWS_PADRAO = "C:\\";
    private static String rootPathWindows = ROOT_PATH_WINDOWS_PADRAO;

//    public static final String ID_GROWL_FORM_GERAL = "gr_geral";
    public static final String ID_GROWL_INFO = "msg_info";
    public static final String ID_GROWL_WARN = "msg_warn";
    public static final String ID_GROWL_ERROR = "msg_error";

    public static final String URL_REDIRECT = "?faces-redirect=true";

    public static final String INDEFINIDO = "Indefinido";
    public static final String INDEFINIDA = "Indefinida";

    public static final int ANOACTUAL = 2022;
    public static final int PAIS_PADRAO_CODIGO = 1;
    public static final int PROVINCIA_PADRAO_CODIGO = 1;
    public static final int MUNICIPIO_PADRAO_CODIGO = 1;
    public static final int COMUNA_PADRAO_CODIGO = 1;
    public static final int PAIS_INDEFINIDO = 198;
    public static final int PROVINCIA_INDEFINIDA_PADRAO = 216;
    public static final int MUNICIPIO_INDEFINIDO_PADRAO = 360;
    public static final int COMUNA_INDEFINIDA_PADRAO = 739;
    public static final int BAIRRO_INDEFINIDO_PADRAO = 747;
    public static int bairroPadraoCodigo = BAIRRO_INDEFINIDO_PADRAO;
//	public static int BAIRRO_PADRAO_CODIGO = 1;
//	public static final int SEXO_PADRAO_CODIGO = 2;
    public static final int SEXO_PADRAO_CODIGO = 2;
    public static final int TIPO_MANUTENCAO_PADRAO_CODIGO = 1;
    public static final int MODO_PAGAMENTO_PADRAO_CODIGO = 1;
    public static final int TIPO_ACIDENTE_PADRAO_CODIGO = 4;
    public static final int TIPO_INFRACAO_PADRAO_CODIGO = 1;
    public static final int TIPO_ILUMINACAO_PADRAO_CODIGO = 1;
    public static final int TIPO_PAVIMENTO_PADRAO_CODIGO = 1;
    public static final int TIPO_COMBUSTIVEL_PADRAO_CODIGO = 1;
    public static final int BOMBAS_COMBUSTIVEL_PADRAO_CODIGO = 1;
    public static final int ESTADO_CIVIL_PADRAO_CODIGO = 2;
    public static final int FUNCAO_FINCIONARIO_PADRAO_CODIGO = 2;
    public static final int ESPECIALIDADE_PADRAO_CODIGO = 2;
    public static final int TIPO_FUNCIONARIO_PADRAO_CODIGO = 2;
    public static final int NIVEL_ACADEMICO_PADRAO_CODIGO = 2;
    public static final int TIPO_SOLICITACAO_PADRAO_CODIGO = 2;
    public static final int TIPO_TRANSPORTE_PADRAO_CODIGO = 1;
    public static final int TIPO_AGENDAMENTO_PADRAO_CODIGO = 5;
    public static final int CODIGO_FUNCAO_PADRAO_CODIGO = 1;
    public static final int CODIGO_NIVEL_ACADEMICO_PADRAO_CODIGO = 1;
    public static final int CODIGO_TIPO_FUNCIONARIO_PADRAO_CODIGO = 1;
    public static final int CODIGO_ESPECIALIDADE_PADRAO_CODIGO = 1;
    public static final String ESTRUTURA_LOGICA_PADRAO_CODIGO = "0.1.1";

    //
    public static final int TIPO_DOCUMENTO_PADRAO_CODIGO = 1;
    public static final int ANO_LECTIVO_PADRAO_CODIGO = 1;
    public static final int ANO_CURRICULAR_PADRAO_CODIGO = 1;
    public static final int TURMA_PADRAO_CODIGO = 1;
    public static final int PERIODO_PADRAO_CODIGO = 3;

    //
    //
    public static final int GD_TIPO_DOCUMENTO_PADRAO_CODIGO = 1;
    public static final int CLASSIFICACAO_PADRAO_CODIGO = 1;
    public static final int ENTIDADE_PADRAO_CODIGO = 1;

    //
//	888888888888888888888888888888888888888888888
    public static final int PK_CARGO_PADRAO = 9;
    public static final int PK_FUNCAO_PADRAO = 10;
    public static final int PK_MAPEAMENTO_FISICO_PADRAO = 14;
    public static final int PK_ORGANOGRAMA_PADRAO = 20;

    public static final int PK_SEXO_PADRAO = 1;
    public static final int PK_ESTADO_CIVIL_PADRAO = 1;
//    public static final String PROJECT_NAME = "PSGL_EGTI_NEW";
    public static final String PROJECT_NAME = "SIG_FRT_Master";

//	888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888    
    public static final String IMAGENS = "imagens" + pathSeparator();

    public static final String DOCUMENT_ICON = getPathWEB_INF(IMAGENS + "document.png");

//	888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888
    public static final String AUDIO_LABAMBA = getPathWEB_INF("audio" + pathSeparator() + "LaBamba.mp3");

    public static final String FILES_EXCEL_GERAL = "filesExcel" + pathSeparator() + "geral" + pathSeparator();
    public static final String FILES_EXCEL_DOCUMENTO = "filesExcel" + pathSeparator() + "documento" + pathSeparator();
    public static final String FILES_EXCEL_PROJECTO = "filesExcel" + pathSeparator() + "prj" + pathSeparator();
    public static final String FILES_EXCEL_COMERCIAL = "filesExcel" + pathSeparator() + "comercial" + pathSeparator();

    public static final String FILES_EXCEL_SORTEADOS = "filesExcel" + pathSeparator() + "sorteado" + pathSeparator();

    public static final String FILES_REPPORTS = "filesReport" + pathSeparator();

    //8888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888
    public static final String RESULTADOS_DOCUMENTOS_EMITIDOS_DIR = rootPath()
            + "documentos" + pathSeparator() + "emitidos" + pathSeparator();

    public static final String RESULTADOS_DOCUMENTOS_ENTREGUES_DIR = rootPath() + "documentos" + pathSeparator() + "entregues" + pathSeparator();

    public static final String DIRECTORIO_RESULTADOS_ENTREVISTAS_XLS = rootPath()
            + "documentos" + pathSeparator() + "planificacao_entrevistas" + pathSeparator();

    public static final String DIRECTORIO_RELATORIOS_XLS = rootPath() + "relatorios"
            + pathSeparator();
    public static final String NOME_FILE_ESTRUTURA_LOGICA = "estrutura_logica.xls";
    public static final String NOME_FILE_BANCOS = "bancos.xls";
    public static final String NOME_FILE_TIPO_CARTAO = "tipo_cartao.xls";
    public static final String NOME_FILE_TIPO_CONTA = "tipo_conta.xls";
    public static final String NOME_FILE_MOEDA = "moedas.xls";

    public static final String NOME_FILE_BAIRRO = "grl_bairro.xls";
    public static final String NOME_FILE_COMUNA = "grl_comuna.xls";
    public static final String NOME_FILE_DISTRITO = "grl_distrito.xls";
    public static final String NOME_FILE_MUNICIPIO = "grl_municipio.xls";
    public static final String NOME_FILE_PAIS = "grl_pais.xls";
    public static final String NOME_FILE_PROVINCIA = "grl_provincia.xls";
    public static final String NOME_FILE_TIPOCATEGORIA = "tipo_categoria.xls";
    public static final String NOME_FILE_CATEGORIA = "categorias.xls";
    public static final String NOME_FILE_SUBCATEGORIA = "subcategorias.xls";

    public static final String CAMINHO_BANCO = getPathWEB_INF(FILES_EXCEL_LOCALIZACAO1 + "bancos.xls");
    public static final String CAMINHO_TIPOCATEGORIA = getPathWEB_INF(FILES_EXCEL_LOCALIZACAO1 + "tipo_categoria.xls");
    public static final String CAMINHO_CATEGORIA = getPathWEB_INF(FILES_EXCEL_LOCALIZACAO1 + "categorias.xls");
    public static final String CAMINHO_SUBCATEGORIA = getPathWEB_INF(FILES_EXCEL_LOCALIZACAO1 + "subcategorias.xls");
    public static final String CAMINHO_TIPOCARTAO = getPathWEB_INF(FILES_EXCEL_LOCALIZACAO1 + "tipo_cartao.xls");
    public static final String CAMINHO_TIPOCONTA = getPathWEB_INF(FILES_EXCEL_LOCALIZACAO1 + "tipo_conta.xls");
    public static final String CAMINHO_MOEDA = getPathWEB_INF(FILES_EXCEL_LOCALIZACAO1 + "moedas.xls");
    public static final String CAMINHO_ESTRUTURA_FISICA = getPathWEB_INF(FILES_EXCEL_LOCALIZACAO1 + "estrutura_fisica.xls");
    public static final String CAMINHO_ESTRUTURA_LOGICA = getPathWEB_INF(FILES_EXCEL_LOCALIZACAO1 + "estrutura_logica.xls");
    public static final String CAMINHO_ESTRUTURA_LOGICA_FISICA = getPathWEB_INF(FILES_EXCEL_LOCALIZACAO1 + "estrutura_logica_fisica.xls");

    public static final String CAMINHO_BAIRRO = getPathWEB_INF(FILES_EXCEL_LOCALIZACAO1 + "grl_bairro.xls");
    public static final String CAMINHO_COMUNA = getPathWEB_INF(FILES_EXCEL_LOCALIZACAO1 + "grl_comuna.xls");
    public static final String CAMINHO_DISTRITO = getPathWEB_INF(FILES_EXCEL_LOCALIZACAO1 + "grl_distrito.xls");
    public static final String CAMINHO_MUNICIPIO = getPathWEB_INF(FILES_EXCEL_LOCALIZACAO1 + "grl_municipio.xls");
    public static final String CAMINHO_PAIS = getPathWEB_INF(FILES_EXCEL_LOCALIZACAO1 + "grl_pais.xls");
    public static final String CAMINHO_PROVINCIA = getPathWEB_INF(FILES_EXCEL_LOCALIZACAO1 + "grl_provincia.xls");

    //8888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888
    public static final String CAMINHO_TIPO_DOCUMENTO = getPathWEB_INF(FILES_EXCEL_DOCUMENTO + "doc_tipo_documento.xls");
    public static final String CAMINHO_RAZOES_RENUNCIA = getPathWEB_INF(FILES_EXCEL_DOCUMENTO + "doc_razoes_renuncia.xls");
    public static final String CAMINHO_DOCUMENTO_NECESSARIO = getPathWEB_INF(FILES_EXCEL_DOCUMENTO + "doc_documento_necessario.xls");
    public static final String CAMINHO_MOTIVO_CANCELAMENTO = getPathWEB_INF(FILES_EXCEL_PROJECTO + "prj_motivo_cancelamento.xls");
    public static final String CAMINHO_TIPO_PESSOA = getPathWEB_INF(FILES_EXCEL_GERAL + "grl_tipo_pessoa.xls");

//    public static final String FILES_EXCEL_LOCALIZACAO = "filesExcel" + pathSeparator() + "localizacao" + pathSeparator();
    //Tabelas Greais grl_estado_civil.xls
    public static final String ESTADO_CANDIDATURA = getPathWEB_INF(FILES_EXCEL_COMERCIAL + "com_estado_candidatura.xls");
    public static final String CONSERVATORIA_COMERCIAL = getPathWEB_INF(FILES_EXCEL_COMERCIAL + "com_conservatoria_comercial.xls");
    public static final String ESTADO_CIVIL = getPathWEB_INF(FILES_EXCEL_GERAL + "grl_estado_civil.xls");
    public static final String CAMINHO_SEXOS = getPathWEB_INF(FILES_EXCEL_GERAL + "grl_sexos.xls");
    public static final String CAMINHO_PAISES = getPathWEB_INF(FILES_EXCEL_LOCALIZACAO + "loc_paises.xls");
    public static final String CAMINHO_PROVINCIAS = getPathWEB_INF(FILES_EXCEL_LOCALIZACAO + "loc_provincias.xls");
    public static final String CAMINHO_MUNICIPIOS = getPathWEB_INF(FILES_EXCEL_LOCALIZACAO + "loc_municipios.xls");
    public static final String CAMINHO_COMUNAS = getPathWEB_INF(FILES_EXCEL_LOCALIZACAO + "loc_comunas.xls");
    public static final String CAMINHO_BAIRROS = getPathWEB_INF(FILES_EXCEL_LOCALIZACAO + "loc_bairros.xls");
    public static final String CAMINHO_COUNTRY_CODES = getPathWEB_INF(FILES_EXCEL_LOCALIZACAO + "loc_country_codes.xls");

    public static final String CAMINHO_PROCESSO_CANDIDATURA = getPathWEB_INF(FILES_EXCEL_SORTEADOS + "resultados_sorteio.xls");

    public static final String CAMINHO_PRJ_LOTES = getPathWEB_INF(FILES_EXCEL_PROJECTO + "prj_lotes_campanha2.xls");

    public static final String CAMINHO_LOC_LOTES = getPathWEB_INF(FILES_EXCEL_LOCALIZACAO + "loc_lotes_campanha.xls");

    public static final String RESULTADOS_DIR = rootPath();
    public static final String DIRECTORIO_RESULTADOS_XLS = rootPath() + "excel" + pathSeparator();

    public static final String LOGS_DIR = RESULTADOS_DIR + "logs" + pathSeparator();
    public static final String LOG_FILENAME_PREFIX = LOGS_DIR + "logs_";

    public static final String FILES_RH = "filesExcel" + pathSeparator() + "rh" + pathSeparator();
    public static final String CAMINHO_RH_EGTI_FUNCOES = getPathWEB_INF(FILES_RH + "rh_egti_funcoes.xls");
    public static final String CAMINHO_RH_EGTI_CARGOS = getPathWEB_INF(FILES_RH + "rh_egti_cargos.xls");
    public static final String CAMINHO_RH_EGTI_COLABORADORES = getPathWEB_INF(FILES_RH + "rh_colaboradores.xls");

    public static final String FILES_EGTI = "filesExcel" + pathSeparator() + "egti" + pathSeparator();
    public static final String CAMINHO_RH_EGTI_MAPEAMENTO_FISICO = getPathWEB_INF(FILES_EGTI + "rh_egti_mapeamento_fisico.xls");
    public static final String CAMINHO_RH_EGTI_ORGANOGRAMA = getPathWEB_INF(FILES_EGTI + "rh_egti_organograma.xls");

    //888888888
    public static final String CAMINHO_CAMPOS = getPathWEB_INF(FILES_EXCEL_CATALOGACAO + "cat_campos.xls");
    public static final String CAMINHO_CDU = getPathWEB_INF(FILES_EXCEL_CATALOGACAO + "cat_cdu.xls");
    //888888
    //8888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888
    public static final String FILES_EXCEL_SEG = "filesExcel" + pathSeparator() + "seg" + pathSeparator();
    //Tabelas Greais grl_estado_civil.xls

    public static final String FILE_FUNCIONALIDADE_SEG = getPathWEB_INF(FILES_EXCEL_SEG + "seg_funcionalidades.xls");
    public static final String FILE_TIPO_FUNCIONALIDADE_SEG = getPathWEB_INF(FILES_EXCEL_SEG + "seg_tipo_funcionalidade.xls");
    public static final String FILE_PERFIL_SEG = getPathWEB_INF(FILES_EXCEL_SEG + "seg_perfil.xls");

    public static final String SHEET_TIPO_FUNCIONALIDADE = "tipoFuncionalidade";
    public static final String SHEET_PERFIL = "perfil";
    public static final String SHEET_FUNCIONALIDADE = "funcionalidade";

    //8888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888
    public static final String NOTIFICACOES_DIR = "filesExcel" + pathSeparator() + "not" + pathSeparator();
    public static final String NOTIFICACOES_RESPOSTA_TIPO = getPathWEB_INF(NOTIFICACOES_DIR + "not_tipos.xls");

    //8888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888
//														pathSeparator() + "resultados" + pathSeparator();
    public static final String NOTIFICACOES_ENTREVISTAS_DIR = RESULTADOS_DIR + "notificacoes_entrevistas" + pathSeparator();
    public static final String NOTIFICACOES_ENTREVISTAS_XLS_DIR = NOTIFICACOES_ENTREVISTAS_DIR + "xls" + pathSeparator();
    public static final String NOTIFICACOES_ENTREVISTAS_PDF_DIR = NOTIFICACOES_ENTREVISTAS_DIR + "pdf" + pathSeparator();

//8888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888
    // Email parameters GERAIS
    public static final boolean EMAIL_AUTENTICATION_DEFAULT = true;
    public static final boolean STARTTLS_ENABLE_DEFAULT = true;
    public static final int EMAIL_SMTP_TIMEOUT_DEFAULT = 30000;

    // Email parameters GMAIL
//    public final static String CONFIGURACAO_EGTI_GMAIL = "EGTI-GMAIL";
//    public static final String EMAIL_HOST_SENDER_EGTI_GMAIL = "smtp.gmail.com";
//    public static final int EMAIL_HOST_SENDER_PORT_EGTI_GMAIL = 587;
//    public static final String SENDER_USER_NAME_EGTI_GMAIL = "";
//    public static final String EMAIL_SENDER_PASSWORD_EGTI_GMAIL = "Soc1al@egt1ep";
//    public static final String EMAIL_CODING_EGTI_GMAIL = "UTF-8";
//    public static final String EMAIL_SENDER_USER_NAME_EGTI_GMAIL = "egti.gov.ao@gmail.com";
    // Email parameters EGTI
    public final static String CONFIGURACAO_EGTI = "EGTI";
    public static final String EMAIL_HOST_SENDER_EGTI = "webmail.egti.gov.ao";
    public static final int EMAIL_HOST_SENDER_PORT_EGTI = 25;
    public static final String SENDER_USER_NAME_EGTI = "paulino.alexandre_ad";
    public static final String EMAIL_SENDER_PASSWORD_EGTI = "Underground12";
    public static final String EMAIL_CODING_EGTI = "UTF-8";
    public static final String EMAIL_SENDER_USER_NAME_EGTI = "info@egti.gov.ao";

    public final static String CONFIGURACAO_EGTI_GMAIL_INFO = "EGTI-GMAIL-INFO";
    public static final String EMAIL_HOST_SENDER_EGTI_GMAIL_INFO = "smtp.gmail.com";
    public static final int EMAIL_HOST_SENDER_PORT_EGTI_GMAIL_INFO = 587;
    public static final String SENDER_USER_NAME_EGTI_GMAIL_INFO = "";
    public static final String EMAIL_SENDER_PASSWORD_EGTI_GMAIL_INFO = "Soc1al@egt1ep";
    public static final String EMAIL_CODING_EGTI_GMAIL_INFO = "UTF-8";
    public static final String EMAIL_SENDER_USER_NAME_EGTI_GMAIL_INFO = "info.egti.gov.ao@gmail.com";

    public static final Integer EGTI_GMAIL_TO_EGTI_GMAIL_INFO_DELAY_HORAS = 0;
    public static final Integer EGTI_GMAIL_TO_EGTI_GMAIL_INFO_DELAY_MINUTOS = 15;

    public static final Integer EGTI_GMAIL_INFO_TO_EGTI_GMAIL_DELAY_HORAS = 23;
    public static final Integer EGTI_GMAIL_INFO_TO_EGTI_GMAIL_DELAY_MINUTOS = 45;

    public static Integer TEMPO_MAXIMO_ELIMINAR_PAGAMENTO_PADRAO = 2;
    public static Integer PAGAMENTO_MINIMO_PADRAO = 1000;

//	public static final int EGTI_GMAIL_TO_EGTI_GMAIL_INFO_DELAY = 15 * 60 * 1000;
//	public static final int EGTI_GMAIL_INFO_TO_EGTI_GMAIL_DELAY = 45 * 60 * 1000;
    static
    {
        //System.err.println("0: Defs.teste()");
    }

    private static String operatingSystem = null;

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

    public static final String rootPath()
    {
        return isWindows() ? rootPathWindows + "psgl_egti" + pathSeparator() : ROOT_PATH_LINUX + "usr" + pathSeparator() + "local" + pathSeparator() + "resultados" + pathSeparator();
    }

    public static String getRootPathWindows()
    {
        return rootPathWindows;
    }

    public static void setRootPathWindows(String rootPathWindows)
    {
        Defs.rootPathWindows = rootPathWindows;
    }

    public static int getBairroPadraoCodigo()
    {
        return bairroPadraoCodigo;
    }

    public static void setBairroPadraoCodigo(int bairroPadraoCodigo)
    {
        Defs.bairroPadraoCodigo = bairroPadraoCodigo;
    }

}
