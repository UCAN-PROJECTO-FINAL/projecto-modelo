/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils.excel;

import utils.ExcepcaoCarregamentoTabelasExcel;
import utils.mensagens.LogFile;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author aires
 */
public abstract class ExcelBeanAbstract implements ExcelBeanInterface
{
	protected HSSFSheet sheet;
	protected UploadedFile uploadExcelFile;
	protected String filename, default_filename;
	
	public ExcelBeanAbstract()
	{
		sheet = null;
		uploadExcelFile = null;
		filename = default_filename = null;
	}
	
	@Override
	public void carregar()
	{
	}

	/**
	 *
	 * @param fileCarregarCandidaturas
	 * @return
	 */
	@Override
	public boolean carregar(UploadedFile uploadExcelFile)
	{
		this.uploadExcelFile = uploadExcelFile;
		this.filename = uploadExcelFile.getFileName();
		
		POIFSFileSystem fs;
		
//		LogFile.warnMsg(null, "0: ExcelBeanAbstract.carregar()");
		try
		{
			fs = new POIFSFileSystem(uploadExcelFile.getInputstream());
			HSSFWorkbook wb = new HSSFWorkbook(fs);
			sheet = wb.getSheetAt(0);
		}
		catch (IOException ex)
		{
			LogFile.warnMsg(null, "Falhou o processamento do ficheiro \"" + uploadExcelFile.getFileName());
		}

//LogFile.warnMsg(null, "00: ExcelBeanAbstract.carregar(UploadedFile excelFile)");

		try
		{
//LogFile.warnMsg(null, "1: ExcelBeanAbstract.carregar(UploadedFile excelFile)");			
			if (carregarTabelas())
			{
//LogFile.warnMsg(null, "2: ExcelBeanAbstract.carregar(UploadedFile excelFile)");				
				return deployUploadedFile();
				
			}
//LogFile.warnMsg(null, "3: ExcelBeanAbstract.carregar(UploadedFile excelFile)");			
			return false;
		}
		catch (ExcepcaoCarregamentoTabelasExcel ex)
		{
			LogFile.warnMsg(null, "Falhou o processamento do ficheiro \"" + uploadExcelFile.getFileName());			
			return false;
		}
	}

	private boolean deployUploadedFile()
	{
		InputStream in;
		try
		{
//LogFile.warnMsg(null, "0: ExcelBeanAbstract.deployUploadedFile()\tdefault_filename: " + this.default_filename);			
			in = uploadExcelFile.getInputstream();
		}
		catch (IOException ex)
		{
			LogFile.warnMsg(null, "Falhou a abertura do ficheiro \"" + uploadExcelFile.getFileName());
			return false;
		}
//LogFile.warnMsg(null, "1: ExcelBeanAbstract.deployUploadedFile()\tdefault_filename: " + default_filename);
		FileOutputStream out;
		try
		{
			out = new FileOutputStream(default_filename);
		}
		catch (IOException ex)
		{
			LogFile.warnMsg(null, "Falhou a abertura do ficheiro \"" + default_filename);
			return false;
		}
//LogFile.warnMsg(null, "2: ExcelBeanAbstract.deployUploadedFile()\tdefault_filename: " + default_filename);
		int count = -1;
		byte[] buffer = new byte[1024];
		try
		{
			while ((count = in.read(buffer)) != -1)
			{
				out.write(buffer, 0, count);
			}
//LogFile.warnMsg(null, "3: ExcelBeanAbstract.deployUploadedFile()");			
		}
		catch (IOException ex)
		{
			LogFile.warnMsg(null, "Falhou o deploy do ficheiro \"" + uploadExcelFile.getFileName());
			return false;
		}

		LogFile.sucessoMsg(null, "O ficheiro \"" + uploadExcelFile.getFileName() + "\" foi carregado com sucesso");
		return true;
	}


}
