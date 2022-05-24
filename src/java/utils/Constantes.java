/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.Serializable;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import seg.beans.SegcontroloSessaoBean;

/**
 *
 * @author Ornela F. Boaventura
 */
public class Constantes implements Serializable
{

	//TRIAGEM    
	public static final int[] FREQUENCIA_RESPIRATORIA_INICIAL =
	{
		12, 30
	};
	public static final int[] FREQUENCIA_RESPIRATORIA_FINAL =
	{
		20, 60
	};
	public static final int[] TEMPERATURA =
	{
		20, 40
	};
	public static final int[] GLICEMIA =
	{
		70, 200
	};
	public static final int[] PESO =
	{
		10, 140
	};
	public static final int[] PULSO_INICIAL =
	{
		60, 100
	};
	public static final int[] PULSO_FINAL =
	{
		100, 160
	};
	public static final int[] TENSAO_ARTERIAL_INICIAL =
	{
		120, 180
	};
	public static final int[] TENSAO_ARTERIAL_FINAL =
	{
		80, 110
	};

	//MEDICAÇÃO
	public static final int[] QUANTIDADE_MEDICAMENTO =
	{
		1, 20
	};

	//@Author Garcia Paulo
	//Constantes fundamentais para certas funcionalidades nos módulos de Admissões e de Finanças 
	//Estados De Agendamento
	public static final String CHEGOU = "chegou";
	public static final String AGENDAR = "agendar";
	public static final String NAO_APARECEU = "nao apareceu";
	public static final String CANCELADOADMS = "cancelado";
	public static final String EFETUADO = "efetuado";

	//Cores Dos Estados Dos Agendamentos
	public static final String AZUL = "blue";
	public static final String LARANJA = "#fbb450";
	public static final String VERDE = "#00ba8b";
	public static final String VERMELHO = "#FF0000";
	public static final String CINZA = "#A2A2A2";
	public static final String PRETO = "#000000";

	//Estados Pagamento Financas
	public static final String ATIVO = "Ativo";
	public static final String CANCELADOFINANCAS = "CANCELADO";

	//pool
	public static final String POOL = "pool";

	//public static final int PROFISSAO_ENFERMEIRO = 3;
	public static final int DEPARTAMENTO_ENFERMAGEM = 3;
	public static final int PROFISSAO_MEDICO = 1;
	public static final int PROFISSAO_AUXILIAR_DE_LIMPEZA = 2;
	public static final int PROFISSAO_RECEPCIONISTA = 8;
	public static final int PROFISSAO_TECNICO_DE_LABORATORIO = 7;
	public static final boolean ACEITE = true;
	public static final boolean NEGADO = false;
	public static final int ACTIVO = 1;
	public static final int DESACTIVO = 2;

	//@Author Elisangela Gaspar
	//Constantes fundamentais para funcionalidades no módulo de FARM_TIPO_LOCAL_FARMACIA 
	// Tipos de produto
	public static final int FARM_TIPO_PRODUTOMEDICAMENTO = 1;
	public static final int FARM_TIPO_PRODUTO_MATERIAL_GASTAVEL = 2;
	public static final int FARM_TIPO_PRODUTO_MEDICAMENTO_GRANDE_VOLUME = 3;

	// TIPOS DE LOCAL DE ARMAZENAMENTO
	public static final int FARM_TIPO_LOCAL_ARMAZEM = 1;
	public static final int FARM_TIPO_LOCAL_FARMACIA = 2;
	public static final int FARM_TIPO_LOCAL_AREA_INTERNA = 3;
	public static final int FARM_TIPO_LOCAL_AREA_EXTERNA = 4;

	//FORMA FARMACEUTICA e VIA DE ADMINISTRACAO DEFAULT
	public static final int FARM_N_A = 1;

	// TIPOS DE FORNECIMENTO
	public static final int FARM_TIPO_FORNECIMENTO_COMPRA = 1;
	public static final int FARM_TIPO_FORNECIMENTO_DOACAO = 2;
	public static final int FARM_TIPO_FORNECIMENTO_DPSL = 3;

	// ESTADOS DE UM PEDIDO NA FARM_TIPO_LOCAL_FARMACIA
	public static final int FARM_ESTADO_PEDIDO_PENDENTE = 1;
	public static final int FARM_ESTADO_PEDIDO_CANCELADO = 2;
	public static final int FARM_ESTADO_PEDIDO_CONFIRMADO = 3;

	// TURNOS DE ATENDIMENTO AOS UTENTES
	public static final String FARM_TURNO_MANHA = "MANHÃ";
	public static final String FARM_TURNO_TARDE = "TARDE";
	public static final String FARM_TURNO_NOITE = "NOITE";

	// TEMPO DEFINIDO EM MESES PARA AVISO DE VALIDADE DE PRODUTO
	public static final int FARM_MESES_DE_ANTECEDENCIA = 3;

	// TIPOS DE NOFIFICACAO NA FARM_TIPO_LOCAL_FARMACIA
	public static final int FARM_TIPO_NOTIF_LOTE_EXPIRADO = 1;
	public static final int FARM_TIPO_NOTIF_POS_RUPTURA = 2;
	public static final int FARM_TIPO_NOTIF_LOTE_QUASE_EXPIRADO = 3;
	public static final int FARM_TIPO_NOTIF_PRE_RUPTURA = 4;

	// ESTADOS DE NOTIFICACAO
	public static final int FARM_ESTADO_NOTIF_PENDENTE = 1;
	public static final int FARM_ESTADO_NOTIF_ADIADO = 1;
	public static final int FARM_ESTADO_NOTIF_RESOLVIDO = 1;

	// TIPOS DE TRANSFERENCIA DE PRODUTOS
	public static final int FARM_TIPO_TRANSF_DISPENSA = 1;
	public static final int FARM_TIPO_TRANSF_DOACAO = 2;
	public static final int FARM_TIPO_TRANSF_FORNECIMENTO = 3;
	public static final int FARM_TIPO_TRANSF_MOVIMENTO = 4;
	public static final int FARM_TIPO_TRANSF_QUARENTENA = 5;

	// TIPOS DE QUANTIDADES DE PRODUTOS
	public static final int FARM_TIPO_QUANT_UNIDADE_SINGULAR = 1;
	public static final int FARM_TIPO_QUANT_CAIXA = 2;
	public static final int FARM_TIPO_QUANT_FRASCO = 3;
	public static final int FARM_TIPO_QUANT_SAQUETA = 4;
	public static final int FARM_TIPO_QUANT_LAMINA = 5;
	public static final int FARM_TIPO_QUANT_EMBALAGEM = 6;

	public static final int FARM_LOCAL_FARMACIA_INTERNA = 3;

	//CAMINHO DO SUBREPORT
	public static final String FARM_CAMINHO_DO_SUBREPORT = "/NetBeansProjects/SIG_HDP_Master/SIG_HDP_Master/SIG_HDP_Master-war/web/WEB-INF/relatorios/farm/";

	public static String obterCaminhoSubReportFarm()
	{

		ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();

		String path = ctx.getRealPath("/") + "/WEB-INF/relatorios/farm/";

		return path;
	}

	public static String obterCaminhoSubReportDiag()
	{

		ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();

		String path = ctx.getRealPath("/") + "/WEB-INF/relatorios/diag/";

		return path;
	}

	//Segurança
	public static int TempoMaxInactividade = 1800; // 1800 = 30 min
	public static int idUltimaSessao;

	//
	//Constantes fundamentais para certas funcionalidades no módulo de RH
	/**
	 * @author Ornela F. Boaventura
	 *
	 * Nome do ficheiro da foto default, geralmente usada no cadastro de pessoas
	 */
	public static final String FOTO_DEFAULT = "Foto-default.png";

	/**
	 * @author Ornela F. Boaventura
	 *
	 * Endereço ftp da pasta onde são armazenadas as fotos das pessoas
	 * cadastradas
	 */
	public static final String PASTA_FOTO = "ftp://divina:divina@" + SegcontroloSessaoBean.getInstanciaBean().getIpMaquinaServidor() + "/Upload/fotoPessoa/";

	/**
	 * @author Ornela F. Boaventura
	 *
	 * Localização da pasta onde são feitos os uploads de fotos das pessoas
	 * cadastradas.
	 */
	public static final String DESTINO_FOTO = "/home/HDP_FILES/Upload/fotoPessoa/";

	public static final String H_D_P = "H.D.P.";

	//@Author Armindo Binje
	//Constantes fundamentais para funcionalidades no módulo de Internamento
	public static final int DOENCAPRINCIPAL = 3;
	public static final int HORASATIVAS = 3;
	public static final int TEMPO_MAXIMO_CAMAS_RESERVA_EM_MIN = 15;

	public static final String GRAVE = "red";
	public static final String CUIDADOS = "yellow";
	public static final String NORMAL = "green";
	public static final String CAMARESERVADA = "green";

	public static final String COR_NOTIFICACOES_PEDIDO = "red";
	public static final String COR_NOTIFICACOES_PARAMETRO = "green";
	public static final String COR_NOTIFICACOES_MEDICACAO = "black";
	public static final String COR_NOTIFICACOES_EXAMES = "blue";
	public static final String COR_NOTIFICACOES_MEDICAMENTOS = "orange";

	public static final String CHANNEL = "/notify";

	public static final String NOTIFICACAO_PEDIDOS = "Pedido de Internamento";
	public static final String NOTIFICACAO_PARAMETROS = "Parametros Vitais";
	public static final String NOTIFICACAO_MEDICACAO = "Medicações";
	public static final String NOTIFICACAO_EXAMES = "Exames Solicitados";
	public static final String NOTIFICACAO_MEDICAMENTO = "Medicamentos Solicitados";

	public static final String NOTIFICACAO_PEDIDOS_DESCRICAO = "Pedido de internamento para o paciente ";
	public static final String NOTIFICACAO_PARAMETROS_DESCRICAO = "O paciente x precisa ser medido o parametro y referente ao dia z";
	public static final String NOTIFICACAO_PARAMETROS_DESCRICAO_ESTADO_GRAVE = "O parametro y do paciente x encontrase em estado grave";
	public static final String NOTIFICACAO_PARAMETROS_DESCRICAO_ESTADO_CUIDADOS = "O parametro x do paciente y encontrase em estado cuidados";
	public static final String NOTIFICACAO_MEDICACAO_DESCRICAO = "O paciente x não recebeu o medicamento y no dia z";
	public static final String NOTIFICACAO_EXAMES_DESCRICAO = "Os exames soicitados ao paciente x já foram realizados";
	public static final String NOTIFICACAO_MEDICAMENTO_DESCRICAO = "O estock do medicamento y foi atualizado para z";

	public static String VERSAO = "versao";

	public static int CONFIGURACAO_PAGINA_ARRANQUE = 1;
	public static int PACIENTE_PAGINA_ARRANQUE = 2;
	public static int SOLICITACOES_PAGINA_ARRANQUE = 3;
	public static int AGENDAMENTOS_PAGINA_ARRANQUE = 4;
	public static int LISTAGEM_SOLICITACAO_PAGINA_ARRANQUE = 5;
	public static int LISTAGEM_AGENDAMENTOS_PAGINA_ARRANQUE = 6;

	public static int LIMITE_CARACTERES_AREA = 13;

}
