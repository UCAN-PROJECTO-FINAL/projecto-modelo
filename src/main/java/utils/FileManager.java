/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import utils.mensagens.LogFile;

/**
 *
 * @author aires
 */
public class FileManager
{

	/*
     abre um ficheiro excel de extensao xls
	 */

	/**
	 *
	 * @param filename
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static HSSFWorkbook openXLS_File(String filename) throws FileNotFoundException, IOException
	{
		InputStream ExcelFileToRead = new FileInputStream(filename);
		return new HSSFWorkbook(ExcelFileToRead);
	}

	/**
	 *
	 * @param filename
	 * @param sheetName
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static HSSFSheet getSheetFromXLS_File(String filename, String sheetName) throws FileNotFoundException, IOException
	{
		HSSFWorkbook hSSFWorkbook = openXLS_File(filename);
		return hSSFWorkbook.getSheet(sheetName);
	}

	/**
	 *
	 * @param filename
	 * @param sheetIndex
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static HSSFSheet getSheetFromXLS_File(String filename, int sheetIndex) throws FileNotFoundException, IOException
	{
		HSSFWorkbook hSSFWorkbook = openXLS_File(filename);
		return hSSFWorkbook.getSheetAt(sheetIndex);
	}

	/**
	 *
	 * @param filename
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static HSSFSheet getSheetFromXLS_File(String filename) throws FileNotFoundException, IOException
	{
		HSSFWorkbook hSSFWorkbook = openXLS_File(filename);
		return hSSFWorkbook.getSheetAt(0);
	}

	/**
	 *
	 * @param versionAttribute
	 * @param filename
	 * @return
	 */
	public static Date lerDataVersaoTabela(String versionAttribute, String filename)
	{
		String msg = null;
		String parametros[] = versionAttribute.split("=");
		if (parametros.length != 2)
		{
			msg = (filename != null) ? "A tabela existente no ficheiro '" + filename + "' tem data de versao mal definida. Fale com o administrador do sistema"
				: "A tabela existente no fichero '" + filename + "' tem data de versao mal definida. Fale com o administrador do sistema";
			LogFile.warnMsg(null, msg);
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", new Locale("pt", "PT"));
		Date dataSheet = null;
		try
		{
			dataSheet = sdf.parse(parametros[1]);
		}
		catch (ParseException ex)
		{
			msg = (filename != null) ? "A data da tabela existenteno ficheiro '" + filename + "' tem formato improprio. Fale com o administrador do sistema"
				: "A data da tabela existente no fichero '" + filename + "' tem formato improprio. Fale com o administrador do sistema";
			LogFile.warnMsg(null, msg);

			return null;
		}
		return dataSheet;
	}

	/**
	 *
	 * @param row
	 * @param filename
	 * @return
	 */
	public static Date lerVersaoTabela(HSSFRow row, String filename)
	{
		Iterator cells = row.cellIterator();
		HSSFCell cell = (HSSFCell) cells.next();
		Date dataSheet = lerDataVersaoTabela(cell.getStringCellValue().trim(), filename.trim());
		return dataSheet;
	}

	public static boolean copyFile(String inputFilename, String outputFilename)
	{
		InputStream in = null;
		try
		{
LogFile.warnMsg(null, "0: FileManager.copyFile()\tinputFilename: " + inputFilename);			
LogFile.warnMsg(null, "0: FileManager.copyFile()\toutputFilename: " + outputFilename);
			in = new FileInputStream(inputFilename);
		}
		catch (IOException ex)
		{
			LogFile.warnMsg(null, "Falhou a abertura do ficheiro \"" + inputFilename + "\"");
			File file = new File(inputFilename).getAbsoluteFile();
//			System.err.println("0: FileManager.copyFile()\tfileExiste: " + file.exists() + "\t" + DataUtils.dataTimeAgoraFull());
//			if (!file.exists())
			return false;
		}
LogFile.warnMsg(null, "1: FileManager.copyFile()\tdefault_filename: " + outputFilename);
		FileOutputStream out;
		try
		{
			out = new FileOutputStream(outputFilename);
		}
		catch (IOException ex)
		{
			LogFile.warnMsg(null, "Falhou a abertura do ficheiro \"" + outputFilename + "\"");
			return false;
		}
LogFile.warnMsg(null, "2: FileManager.copyFile()\tdefault_filename: " + outputFilename);
		int count;
		byte[] buffer = new byte[1024];
		try
		{
			while ((count = in.read(buffer)) != -1)
			{
				out.write(buffer, 0, count);
			}
LogFile.warnMsg(null, "3: FileManager.copyFile()");			
		}
		catch (IOException ex)
		{
			LogFile.warnMsg(null, "Falhou a cópia do ficheiro \"" + inputFilename + "\" para o o ficheiro \"" + outputFilename + "\"");
			return false;
		}
		LogFile.sucessoMsg(null, "A cópia do ficheiro \"" + inputFilename + "\" para o o ficheiro \"" + outputFilename + "\" foi efectuada com sucesso");
		return true;
	}
}

