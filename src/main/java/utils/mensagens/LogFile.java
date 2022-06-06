/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
// CHANGED
package utils.mensagens;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.DataUtils;
import utils.Defs;
import utils.StringUtils;
import utils.Utils;

/**
 *
 * @author Aires Veloso
 */
public class LogFile extends BufferedWriter
{

	private static int nLogs = 0;

	private static LogFile logFile;

	private String filename;
	private static String logFilename = Defs.LOG_FILENAME_PREFIX;
    
    private static boolean initiated = false;

	static
	{
		init();
	}

	public LogFile(String filename) throws FileNotFoundException, UnsupportedEncodingException
	{
		super(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filename), "UTF-16")));

		this.filename = filename;
	}

	public static void closeLogFile()
	{
		logFile.close();
	}

	@Override
	public void close()
	{
		try
		{
			super.close();
		}
		catch (IOException ex)
		{
			Logger.getLogger(LogFile.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public static boolean init()
	{       
        if (initiated)
            return true;
//        System.err.println("0: LogFile.init()\tresultado: " + StringUtils.nomesSemelhantes("Aires Manuel Araujo Veloso", "Aires Manuel"));
//        System.err.println("1: LogFile.init()\tresultado: " + StringUtils.nomesSemelhantes("Aires Manuel Araujo Veloso", "Aires A. C. Manuel"));
		if (criarDirectorioResultados(Defs.RESULTADOS_DIR) &&
                        criarDirectorioResultados(Defs.LOGS_DIR))
		{
			if (!criarResultadosTree())
			{
				return false;
			}
			if ( LogFile.generateLogFile() != null)
            {
                initiated = true;
                return true;
            }
		}
		return false;
	}

	public static boolean criarResultadosTree()
	{
		List<String> resultadosDirList = new ArrayList();
//		resultadosDirList.add(Defs.RESULTADOS_DOCUMENTOS_ENTREGUES_DIR);
//		resultadosDirList.add(Defs.TERMOS_RENUNCIA_PDF_DIR);
//		resultadosDirList.add(Defs.DECLARACOES_INVIABILIDADE_PDF_DIR);
//		resultadosDirList.add(Defs.DECLARACOES_VIABILIDADE_PDF_DIR);
//		resultadosDirList.add(Defs.CONTRATOS_DIREITO_SUPERFICIE_PDF_DIR);
//		resultadosDirList.add(Defs.RESULTADOS_DOCUMENTOS_DIR);
//		resultadosDirList.add(Defs.CONTRATOS_FINAIS_ATRIBUICOES_LOTES_DIR);
//		resultadosDirList.add(Defs.DIRECTORIO_RESULTADOS_ENTREVISTAS_XLS);

		for (String dir : resultadosDirList)
		{
			if (!criarDirectorioResultados(dir))
			{
				return false;
			}
		}
		return true;
	}

	public static boolean criarDirectorioResultados(String path)
	{
            //path = "/home/mdnex/SchoolProjects/PF1_Patrimonio/resultados";
		File file = new File(path);
		if (file.exists() && file.isDirectory())
		{
			return true;
		}
		if (Utils.pathExists(path))
		{
			return true;
		}
		if (file.mkdir())
		{
			Mensagem.sucessoMsg(null, "O directório \"" + path + "\" foi criado com sucesso");
			return true;
		}
		else
		{
			Mensagem.warnMsg(null, "Falhou a criação do directório \"" + path + "\"");
			return false;
		}
	}

	public static int getNLogs()
	{
		return nLogs;
	}

	public static void incrNLogs()
	{
		nLogs++;
	}

	public static String toStringNLogsToMessage()
	{
		return ++nLogs + ". ";
	}

	public static String headLogsMessage()
	{
		return "\n" + toStringNLogsToMessage() + geraLogDataStamp() + getIpMaquinaCliente() + getUserLogin();
	}

	private static String getIpMaquinaCliente()
	{
//		return "[" + SegLoginBean.getInstanciaBean().getIpMaquinaCliente() + "]"; 888888888888888888888888888888
		return "";
	}

	private static String getUserLogin()
	{
//		return "[" + SegLoginBean.getInstanciaBean().getSessaoActual().getNomeUtilizador() + "]: ";  888888888888888888888888888888
		return "";
	}

	private static String geraLogDataStamp()
	{
		return "[" + DataUtils.toStringNow() + "]";
	}

	public static void writeLogMessage(String msg)
	{
		try
		{
			logFile.write(msg);
			logFile.append(msg);
			logFile.flush();
		}
		catch (IOException ex)
		{
			Logger.getLogger(LogFile.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public static void writeLogMessageWithHead(String logMessage)
	{
        System.err.println(logMessage);
		LogFile.writeLogMessage(headLogsMessage() + logMessage);
	}

	public synchronized static void sucessoMsg(String identifier, String logMessage)
	{
		//writeLogMessageWithHead(logMessage);
		Mensagem.enviarMensagemInformacao(identifier, logMessage);
	}

	public synchronized static void erroMsg(String identifier, String logMessage)
	{
		//writeLogMessageWithHead(logMessage);
		Mensagem.enviarMensagemErro(identifier, logMessage);
	}

	public synchronized static void warnMsg(String identifier, String logMessage)
	{
//System.err.println("0: LogFile.warnMsg");        
		//writeLogMessageWithHead(logMessage);
//System.err.println("1: LogFile.warnMsg");
		Mensagem.enviarMensagemAlerta(identifier, logMessage);
//System.err.println("2: LogFile.warnMsg");        
	}

	// ################################################################################
	public synchronized static void sucessoMsg(String identifier, List<String> logMessages)
	{
		for (String logMessage : logMessages)
		{
			writeLogMessageWithHead(logMessage);
			Mensagem.enviarMensagemInformacao(identifier, logMessage);
		}
	}

	public synchronized static void erroMsg(String identifier, List<String> logMessages)
	{
		for (String logMessage : logMessages)
		{
			writeLogMessageWithHead(logMessage);
			Mensagem.enviarMensagemErro(identifier, logMessage);
		}
	}

	public synchronized static void warnMsg(String identifier, List<String> logMessages)
	{
		for (String logMessage : logMessages)
		{
			writeLogMessageWithHead(logMessage);
			Mensagem.enviarMensagemAlerta(identifier, logMessage);
		}
	}
    
	// ################################################################################
	
    public synchronized static void sucessoMsg(String identifier, String... logMessages)
	{
        List<String> mensagens = StringUtils.toList(logMessages);
        sucessoMsg(identifier, mensagens);
	}

	public synchronized static void erroMsg(String identifier, String... logMessages)
	{
		List<String> mensagens = StringUtils.toList(logMessages);
        erroMsg(identifier, mensagens);
	}

	public synchronized static void warnMsg(String identifier, String... logMessages)
	{
		List<String> mensagens = StringUtils.toList(logMessages);
        warnMsg(identifier, mensagens);
	}
    
	// ################################################################################
	public static String geraLogFilename()
	{
		String data = DataUtils.getDataAgora();
		logFilename += data + ".txt";
		/*
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        //String data = new SimpleDateFormat("dd/MMMMM/yyyy", new Locale("pt", "PT")).format(timestamp.getTime());

        String dia = new SimpleDateFormat("dd", new Locale("pt", "PT")).format(timestamp.getTime());
        String mes = new SimpleDateFormat("MMMMM", new Locale("pt", "PT")).format(timestamp.getTime());
        String ano = new SimpleDateFormat("yyyy", new Locale("pt", "PT")).format(timestamp.getTime());
        String horas = new SimpleDateFormat("HH").format(timestamp.getTime());
        String minutos = new SimpleDateFormat("mm").format(timestamp.getTime());
        String segundos = new SimpleDateFormat("ss").format(timestamp.getTime());
		 */
		return logFilename;
	}

	public static LogFile generateLogFile()
	{
		logFile = null;
		geraLogFilename();
//        System.err.println("0: LogFile.generateLogFile\tlogFilename: " + logFilename);

		try
		{
			logFile = new LogFile(logFilename);
		}
		catch (FileNotFoundException | UnsupportedEncodingException ex)
		{
			Mensagem.erroMsg(null, "Falhou a tentativa de criação de ficheiro do Logs");
		}

		return logFile;
	}

	public static LogFile generateLogFileComm()
	{
		logFile = null;
		geraLogCommFilename();
//System.err.println("0: Sorteador.generateLogFile\tlogFilename: " + logFilename);
		try
		{
			logFile = new LogFile(logFilename);
		}
		catch (FileNotFoundException ex)
		{
			Logger.getLogger(LogFile.class.getName()).log(Level.SEVERE, null, ex);
		}
		catch (UnsupportedEncodingException ex)
		{
			Logger.getLogger(LogFile.class.getName()).log(Level.SEVERE, null, ex);
		}
		return logFile;
	}

	public static String geraLogCommFilename()
	{
		String data = DataUtils.getTimeStampNow();
		logFilename += "comm_" + data + ".txt";
		return logFilename;
	}

}
